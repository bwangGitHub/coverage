/*
 * Copyright 2006 The Closure Compiler Authors.
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

import javax.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p>AliasExternals provides wrappers and aliases for external globals and
 * properties to that they can be referenced by their full name only once
 * instead of in all use sites.</p>
 *
 * <p>The property alias pass creates function wrappers for properties that need
 * to be accessed externally. These function wrappers are then used by all
 * internal calls to the property, and the names will be compressed during the
 * RenamePrototypes step.</p>
 *
 * <p>Properties that are accessed externally are either system functions
 * (i.e. window.document), or used by JavaScript embedded on a page.</p>
 *
 * <p>Properties that are r-values are changed to use array notation with
 * a string that has been defined separately and can be compressed
 * i.e. document.window -> document[PROP_window].</p>
 *
 * <p>Properties that are l-values and can be renamed are renamed to
 * SETPROP_prop. I.e. node.innerHTML = '&lt;div&gt;Hello&lt;/div&gt;' ->
 * SETPROP_innerHTML(node, '&lt;div&gt;hello&lt;/div&gt;').</p>
 *
 * <p>Properties will only be renamed if they are used more than requiredUsage_
 * times, as there is overhead for adding the accessor and mutator functions.
 * This is initialized to DEFAULT_REQUIRED_USAGE (=4), but can be
 * overridden.</p>
 *
 * <p>Certain usages (increment, decrement) won't be addressed, as they would
 * require a getprop, setprop, and custom logic, and aren't worth
 * optimizing.</p>
 *
 * <p>The global alias pass creates aliases for global variables and functions
 * that are declared or need to be used externally. These aliases are then used
 * throughout the code, and will be compressed during the RenameVars step.</p>
 *
 * <p>Globals are aliased by inserting code like "var GLOBAL_window = window;"
 * and then replacing all other uses of "window" with "GLOBAL_window."</p>
 *
 * <p>Globals that are l-values are not aliased.</p>
 *
 */
class AliasExternals implements CompilerPass {
  static {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.ping();
  }


  /** Number of times a property needs to be accessed in order to alias */
  private static final int DEFAULT_REQUIRED_USAGE = 4;
  static {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[1]++;
  }

  /** Number of times a property must be referenced in order to be aliased */
  private int requiredUsage = DEFAULT_REQUIRED_USAGE;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[2]++;
  }

  /** Minimum property size to be worth renaming */
  private static final int MIN_PROP_SIZE = 4;
  static {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[3]++;
  }

  /**
   * The name of the variable used for the "prototype" string value. This is
   * special-cased to make deobfuscated stack traces shorter and more readable
   * ("$MyClass$$P$$method$" rather than "$MyClass$$$PROP_prototype$method$").
   * @see NameAnonymousFunctions
   */
  static final String PROTOTYPE_PROPERTY_NAME =
      getArrayNotationNameFor("prototype");
  static {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[4]++;
  }

  /** Map of all properties that we may be renaming */
  private final Map<String, Symbol> props = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[5]++;
  }

  /** Holds the properties that can be renamed to GETPROP_ */
  private final List<Node> accessors = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[6]++;
  }

  /** Holds the properties that can be renamed to SETPROP_ */
  private final List<Node> mutators = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[7]++;
  }

  /**
   * Map of node replacements -
   * Identity map because Node implements equals() but not hashCode()
   */
  private final Map<Node, Node> replacementMap =
    new IdentityHashMap<Node, Node>();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[8]++;
  }

  /** Map of all globals that we may alias */
  private final Map<String, Symbol> globals = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[9]++;
  }

  /** Reference to JS Compiler */
  private final AbstractCompiler compiler;

  /** Reference to module inputs */
  private final JSModuleGraph moduleGraph;

  /** Root in parse tree for adding generated nodes */
  private Node defaultRoot;

  /** Root in each module for adding generated nodes, if using modules */
  private Map<JSModule, Node> moduleRoots;

  /**
   * A set of globals that can not be aliased since they may be undefined or
   * can cause errors
   */
  private final Set<String> unaliasableGlobals = Sets.newHashSet(
      // While "arguments" is declared as a global extern, it really only has
      // meaning inside function bodies and should not be aliased at a global
      // level.
      "arguments",
      // Eval should not be aliased, per the ECMA-262 spec section 15.1.2.1
      "eval",
      // "NodeFilter" is not defined in IE and throws an error if you try to
      // do var foo = NodeFilter.
      "NodeFilter",
      // Calls to this special function are eliminated by the RenameProperties
      // compiler pass.
      "JSCompiler_renameProperty");
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[10]++;
  }

  /** Whitelist of aliasable externs. */
  private final Set<String> aliasableGlobals = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[11]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The Compiler
   * @param moduleGraph The graph of input modules. May be null. If given, we'll
   *     try to push aliased externs into the deepest possible module.
   */
  AliasExternals(AbstractCompiler compiler, JSModuleGraph moduleGraph) {
    this(compiler, moduleGraph, null, null);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[12]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The Compiler
   * @param moduleGraph The graph of input modules. May be null. If given, we'll
   *     try to push aliased externs into the deepest possible module.
   * @param unaliasableGlobals Comma-separated list of additional globals that
   *     cannot be aliased since they may be undefined or can cause errors
   *     (e.g. "foo,bar"). May be null or the empty string.
   * @param aliasableGlobals Comma-separated list of globals that
   *     can be aliased. If provided, only this list of globals can be aliased.
   */
  AliasExternals(AbstractCompiler compiler, JSModuleGraph moduleGraph,
                 @Nullable String unaliasableGlobals,
                 @Nullable String aliasableGlobals) {
    this.compiler = compiler;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[13]++;
    this.moduleGraph = moduleGraph;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[14]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((Strings.isNullOrEmpty(unaliasableGlobals)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Strings.isNullOrEmpty(aliasableGlobals)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[1]++;
      throw new IllegalArgumentException(
          "Cannot pass in both unaliasable and aliasable globals; you must " +
          "choose one or the other.");

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[2]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Strings.isNullOrEmpty(unaliasableGlobals)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[3]++;
      this.unaliasableGlobals.addAll(
          Arrays.asList(unaliasableGlobals.split(",")));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[17]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[4]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((Strings.isNullOrEmpty(aliasableGlobals)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[5]++;
      this.aliasableGlobals.addAll(Arrays.asList(aliasableGlobals.split(",")));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[19]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[6]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[7]++;
      moduleRoots = Maps.newHashMap();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[21]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[8]++;}
  }

  /**
   * Sets the number of times a property needs to be referenced in order to
   * create an alias for it.
   * @param usage Number of times
   */
  public void setRequiredUsage(int usage) {
    this.requiredUsage = usage;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[22]++;
  }

  /**
   * Do all processing on the root node.
   */
  @Override
  public void process(Node externs, Node root) {
    defaultRoot = root.getFirstChild();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[23]++;
    Preconditions.checkState(defaultRoot.isScript());
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[24]++;

    aliasProperties(externs, root);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[25]++;
    aliasGlobals(externs, root);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[26]++;
  }

  private void aliasProperties(Node externs, Node root) {
    // Get the reserved names, filtered by the whitelist.
    NodeTraversal.traverse(compiler, externs,
                           new GetAliasableNames(aliasableGlobals));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[27]++;
    props.put("prototype", newSymbolForProperty("prototype"));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[28]++;

    // Find the props that can be changed
    NodeTraversal.traverse(compiler, root, new PropertyGatherer());
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[29]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[30]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[1]++;



    // Iterate through the reserved names, decide what to change
    // This could have been done during property traversal, but
    // This gives opportunity for review & modification if needed
    for (Symbol prop : props.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[1]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[2]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[3]++;
}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((prop.name.length() >= MIN_PROP_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[9]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((prop.accessorCount >= requiredUsage) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[11]++;
          prop.aliasAccessor = true;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[33]++;

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[12]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((prop.mutatorCount >= requiredUsage) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[13]++;
          prop.aliasMutator = true;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[35]++;

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[14]++;}

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[10]++;}
    }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[36]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[4]++;


    // Change the references to the property gets
    for (Node propInfo : accessors) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[4]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[5]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[6]++;
}
      replaceAccessor(propInfo);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[37]++;
    }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[7]++;



    // Change the references to the property sets
    for (Node propInfo : mutators) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[7]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[8]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[9]++;
}
      replaceMutator(propInfo);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[39]++;
    }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[40]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[10]++;



    // And add the accessor and mutator functions, if needed. Property names are
    // grouped together so that the CollapseVariableDeclarations pass can put
    // them in a single variable declaration statement.
    for (Symbol prop : props.values()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[10]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[11]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[12]++;
}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((prop.aliasAccessor) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[15]++;
        addAccessorPropName(prop.name, getAddingRoot(prop.deepestModuleAccess));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[42]++;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[16]++;}
    }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[43]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[13]++;



    for (Symbol prop : props.values()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[13]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[14]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[15]++;
}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((prop.aliasMutator) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[17]++;
        addMutatorFunction(prop.name, getAddingRoot(prop.deepestModuleMutate));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[45]++;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[18]++;}
    }
  }

  /*
   * Replaces a GETPROP with array notation, so that
   * it can be optimized.
   * I.e. prop.length -> prop[PROP_length] -> prop[a];
   */
  private void replaceAccessor(Node getPropNode) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[46]++;
    /*
     *  BEFORE
        getprop
            NODE...
            string length
        AFTER
        getelem
            NODE...
            name PROP_length
     */
    Node propNameNode = getPropNode.getLastChild();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[47]++;
    String propName = propNameNode.getString();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((props.get(propName).aliasAccessor) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[19]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[49]++;
      Node propSrc = getPropNode.getFirstChild();
      getPropNode.removeChild(propSrc);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[50]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[51]++;

      Node newNameNode =
          IR.name(getArrayNotationNameFor(propName));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[52]++;

      Node elemNode = IR.getelem(propSrc, newNameNode);
      replaceNode(getPropNode.getParent(), getPropNode, elemNode);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[53]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[54]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[20]++;}
  }

  /**
   * Changes a.prop = b to SETPROP_prop(a, b);
   */
  private void replaceMutator(Node getPropNode) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[55]++;
    /*
       BEFORE
       exprstmt 1
           assign 128
               getprop
                   NodeTree A
                   string prop
               NODE TREE B

       AFTER
       exprstmt 1
           call
               name SETPROP_prop
               NodeTree A
               NODE TREE B
    */
    Node propNameNode = getPropNode.getLastChild();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[56]++;
    Node parentNode = getPropNode.getParent();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[57]++;

    Symbol prop = props.get(propNameNode.getString());
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[58]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((prop.aliasMutator) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[21]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[59]++;
      Node propSrc = getPropNode.getFirstChild();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[60]++;
      Node propDest = parentNode.getLastChild();

      // Remove the orphaned children
      getPropNode.removeChild(propSrc);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[61]++;
      getPropNode.removeChild(propNameNode);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[62]++;
      parentNode.removeChild(propDest);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[63]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[64]++;

      // Create the call GETPROP_prop() node, using the old propSrc as the
      // one parameter to GETPROP_prop() call.
      Node callName = IR.name(
          getMutatorFor(propNameNode.getString()));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[65]++;
      Node call = IR.call( callName, propSrc, propDest);
      call.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[66]++;

      // And replace the assign statement with the new call
      replaceNode(parentNode.getParent(), parentNode, call);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[67]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[68]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[22]++;}
  }

  /**
   * Utility function to replace a Node with another node.
   * Keeps track of previous replacements so that if you try to replace
   * a child of a parent that has changed, it replaces on the new parent
   * @param parent Parent of node to be replaced
   * @param before Node to be replaced
   * @param after Replacement node
   */
  private void replaceNode(Node parent, Node before, Node after) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[69]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((replacementMap.containsKey(parent)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[23]++;
      parent = replacementMap.get(parent);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[70]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[24]++;}
    parent.replaceChild(before, after);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[71]++;
    replacementMap.put(before, after);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[72]++;
  }

  /**
   * Adds a string that can be used to reference properties by array []
   * notation.
   *
   * PROP_prototype = 'prototype';
   *
   * @param propName Name of property
   * @param root Root of output tree that function can be added to
   */
  private void addAccessorPropName(String propName, Node root) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[73]++;
    /*
     *  Target:

      var 1
        name PROP_length
            string length
     */
    Node propValue = IR.string(propName);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[74]++;
    Node propNameNode =
        IR.name(getArrayNotationNameFor(propName));
    propNameNode.addChildToFront(propValue);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[75]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[76]++;
    Node var = IR.var(propNameNode);
    root.addChildToFront(var);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[77]++;

    compiler.reportCodeChange();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[78]++;
  }

  /**
   * Create set property function in JS. Output will be:
   * SETPROP_prop(a, b) {a.prop = b;}
   *
   * @param propName Name of property
   * @param root Root of output tree that function can be added to
   */
  private void addMutatorFunction(String propName, Node root) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[79]++;
    /*
      function SETPROP_prop
        name SETPROP_prop
        lp
            name a
            name b
        block 1
            return 1
                assign
                    getprop
                        name a
                        string prop
                    name b
    */

    // Function name node
    String functionName = getMutatorFor(propName);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[80]++;

    // Function arguments
    String localPropName = getMutatorFor(propName) + "$a";
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[81]++;
    String localValueName = getMutatorFor(propName) + "$b";
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[82]++;
    // Create the function and append to front of output tree
    Node fnNode = IR.function(
        IR.name(functionName),
        IR.paramList(IR.name(localPropName), IR.name(localValueName)),
        IR.block(
            IR.returnNode(
                IR.assign(
                    IR.getprop(IR.name(localPropName), IR.string(propName)),
                    IR.name(localValueName)))));
    root.addChildToFront(fnNode);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[83]++;

    compiler.reportCodeChange();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[84]++;
  }

  /**
   * Gets a SCRIPT node for code insertion in {@code m} or, if {@code m} is
   * empty, in as deep an ancestor module of {@code m} as possible. Returns
   * {@code this.defaultRoot} if {@code m} is null.
   *
   * @param m The module to find a root in (may be null)
   * @return A root node
   */
  private Node getAddingRoot(JSModule m) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[85]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[25]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[86]++;
      Node root = moduleRoots.get(m);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[87]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[27]++;
        return root;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[28]++;}

      root = compiler.getNodeForCodeInsertion(m);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[88]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[89]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[29]++;
        moduleRoots.put(m, root);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[90]++;
        return root;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[30]++;}

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[26]++;}

    return defaultRoot;
  }

  /**
   * Gets the aliasable names from externs.js
   */
  private class GetAliasableNames extends AbstractPostOrderCallback {
    private final Set<String> whitelist;

    public GetAliasableNames(final Set<String> whitelist) {
      this.whitelist = whitelist;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[91]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[92]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[31]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[32]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[93]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[94]++;
int CodeCoverConditionCoverageHelper_C16;
          if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((whitelist.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((whitelist.contains(dest.getString())) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[33]++;
            props.put(dest.getString(), newSymbolForProperty(dest.getString()));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[95]++;

          } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[34]++;} default : CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[35]++;
      }
    }
  }

  /**
   * Gets references to all of the replaceable nodes, as well
   * as counting the usage for each property name.
   */
  private final class PropertyGatherer extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[96]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[36]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[97]++;
        Node propNameNode = n.getLastChild();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[98]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((canReplaceWithGetProp(propNameNode, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[38]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[99]++;
          String name = propNameNode.getString();
          props.get(name).recordAccessor(t);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[100]++;
          accessors.add(n);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[101]++;

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[39]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[102]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((canReplaceWithSetProp(propNameNode, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[40]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[103]++;
          String name = propNameNode.getString();

          props.get(name).recordMutator(t);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[104]++;
          mutators.add(n);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[105]++;

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[41]++;}

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[37]++;}
    }

    /**
     * Logic for when a getprop can be replaced.
     * Can't alias a call to eval per ECMA-262 spec section 15.1.2.1
     * Can't be an assign -> no a.b = c;
     * Can't be inc or dec -> no a.b++; or a.b--;
     * Must be a GETPROP (NODE, A) where A is a reserved name
     * @param propNameNode Property name node
     * @param getPropNode GETPROP node
     * @param parent parent node
     * @return True if can be replaced
     */
    private boolean canReplaceWithGetProp(Node propNameNode, Node getPropNode,
          Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[106]++;
      boolean isCallTarget = (parent.isCall())
          && (parent.getFirstChild() == getPropNode);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[107]++;
      boolean isAssignTarget = NodeUtil.isAssignmentOp(parent)
          && (parent.getFirstChild() == getPropNode);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[108]++;
      boolean isIncOrDec = (parent.isInc()) ||
          (parent.isDec());
      return (propNameNode.isString()) && !isAssignTarget
          && (!isCallTarget || !"eval".equals(propNameNode.getString()))
          && !isIncOrDec
          && props.containsKey(propNameNode.getString());
    }

    /**
     * Logic for whether a setprop can be replaced.
     *
     * True if it is target of assign (i.e. foo = A.B), and B is a reserved name
     * @param propNameNode Property name node
     * @param getPropNode GETPROP node
     * @param parent parent node
     * @return True if can be replaced
     */
    private boolean canReplaceWithSetProp(Node propNameNode, Node getPropNode,
        Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[109]++;
      boolean isAssignTarget = (parent.isAssign())
          && (parent.getFirstChild() == getPropNode);
      return (propNameNode.isString()) && isAssignTarget
          && props.containsKey(propNameNode.getString());
    }
  }

  /**
   * Gets the mutator name for a property.
   */
  private static String getMutatorFor(String prop) {
    return "SETPROP_" + prop;
  }

  /**
   * Gets the array notation name for a property.
   */
  private static String getArrayNotationNameFor(String prop) {
    return "$$PROP_" + prop;
  }

  private void aliasGlobals(Node externs, Node root) {
    // Find all the extern globals that we should alias
    NodeTraversal.traverse(compiler, externs, new GetGlobals());
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[110]++;

    // Find all the globals that can be changed
    NodeTraversal.traverse(compiler, root, new GlobalGatherer());
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[111]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[112]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[16]++;



    // Iterate through the used globals, decide what to change.
    for (Symbol global : globals.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[16]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[17]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[18]++;
}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[113]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((global.mutatorCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[42]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[114]++;
        continue;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[43]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[115]++;

      // We assume that each alias variable will end up compressed to two letter
      // names. There is also the overhead of "var xx=<global>;"
      int currentBytes = global.name.length() * global.accessorCount;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[116]++;
      int aliasedBytes = 8 + global.name.length() + 2 * global.accessorCount;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[117]++;
int CodeCoverConditionCoverageHelper_C21;

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((aliasedBytes < currentBytes) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[44]++;
        global.aliasAccessor = true;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[118]++;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[45]++;}
    }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[119]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[19]++;



    // Change the references to the globals
    for (Symbol global : globals.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[19]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[20]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[21]++;
}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[120]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[22]++;


      for (Node globalUse : global.uses) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[22]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[23]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[24]++;
}
        replaceGlobalUse(globalUse);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[121]++;
      }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[122]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((global.aliasAccessor) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[46]++;
        addGlobalAliasNode(global,
                           getAddingRoot(global.deepestModuleAccess));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[123]++;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[47]++;}
    }
  }

  /**
   * Gets the aliasable names from externs.js
   */
  private class GetGlobals extends NodeTraversal.AbstractShallowCallback {
    private void getGlobalName(NodeTraversal t, Node dest, Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[124]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((dest.isName()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[48]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[125]++;

        JSDocInfo docInfo = dest.getJSDocInfo() == null ?
            parent.getJSDocInfo() : dest.getJSDocInfo();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[126]++;
        boolean aliasable = !unaliasableGlobals.contains(dest.getString()) &&
            (docInfo == null || !docInfo.isNoAlias());
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[127]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((aliasable) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[50]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[128]++;
          String name = dest.getString();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[129]++;
          Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[130]++;
int CodeCoverConditionCoverageHelper_C25;

          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((var.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[52]++;
            globals.put(name, newSymbolForGlobalVar(dest));
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[131]++;

          } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[53]++;}

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[51]++;}

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[49]++;}
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[132]++;
      switch (n.getType()) {
        case Token.FUNCTION:
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[54]++;
          getGlobalName(t, n.getFirstChild(), n);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[133]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[134]++;
          break;
        case Token.VAR:
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[55]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[135]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[25]++;


int CodeCoverConditionCoverageHelper_C26;
          for (Node varChild = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((varChild != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false);
               varChild = varChild.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[25]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[26]--;
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.loops[27]++;
}
            getGlobalName(t, varChild, n);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[136]++;
          }
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[137]++;
          break; default : CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[56]++;
      }
    }
  }

  /**
   * Gets references to all of the replaceable nodes, as well as counting the
   * usage for each global.
   */
  private final class GlobalGatherer extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[138]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[57]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[139]++;
        String name = n.getString();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[140]++;
        Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[141]++;
int CodeCoverConditionCoverageHelper_C28;

        // It's ok for var to be null since it won't be in any scope if it's
        // an extern
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((var.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[59]++;
          return;

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[60]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[142]++;

        Symbol global = globals.get(name);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[143]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((global != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[61]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[144]++;
int CodeCoverConditionCoverageHelper_C30;
          // If a variable is declared in both externs and normal source,
          // don't alias it.
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((n.getParent().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.getParent().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[63]++;
            globals.remove(name);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[145]++;

          } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[64]++;}
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[146]++;

          boolean isFirst = parent.getFirstChild() == n;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[147]++;
int CodeCoverConditionCoverageHelper_C31;
          // If a global is being assigned to or otherwise modified, then we
          // don't want to alias it.
          // Using "new" with this global is not a mutator, but it's also
          // something that we want to avoid when aliasing, since we may be
          // dealing with external objects (e.g. ActiveXObject in MSIE)
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C31 |= (2048)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (512)) == 0 || true) &&
 ((isFirst) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C31 |= (128)) == 0 || true) &&
 ((parent.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((isFirst) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((parent.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((parent.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 6) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 6) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[65]++;
            global.recordMutator(t);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[148]++;

          } else {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[66]++;
            global.recordAccessor(t);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[149]++;
          }

          global.uses.add(n);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[150]++;

        } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[62]++;}

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[58]++;}
    }
  }

  /**
   * Replace uses of a global with its aliased name.
   */
  private void replaceGlobalUse(Node globalUse) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[151]++;
    String globalName = globalUse.getString();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[152]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((globals.get(globalName).aliasAccessor) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[67]++;
      globalUse.setString("GLOBAL_" + globalName);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[153]++;

      // None of the aliases are marked as @const.
      // Because we're reusing the original ref node,
      // we need to update it to reflect this.
      globalUse.putBooleanProp(Node.IS_CONSTANT_NAME, false);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[154]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[155]++;

    } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[68]++;}
  }

  /**
   * Adds an alias variable for the global:
   *
   * var GLOBAL_window = window;
   *
   * @param global Name of global
   * @param root Root of output tree that function can be added to
   */
  private void addGlobalAliasNode(Symbol global, Node root) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[156]++;
    /*
     *  Target:

      var 1
        name GLOBAL_window
            name window
     */

    String globalName = global.name;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[157]++;
    Node globalValue = IR.name(global.name);
    globalValue.putBooleanProp(Node.IS_CONSTANT_NAME, global.isConstant);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[158]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[159]++;

    Node globalNameNode = IR.name("GLOBAL_" + globalName);
    globalNameNode.addChildToFront(globalValue);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[160]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[161]++;
    Node var = IR.var(globalNameNode);
    root.addChildToFront(var);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[162]++;

    compiler.reportCodeChange();
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[163]++;
  }

  private Symbol newSymbolForGlobalVar(Node name) {
    return new Symbol(
        name.getString(), name.getBooleanProp(Node.IS_CONSTANT_NAME));
  }

  private Symbol newSymbolForProperty(String name) {
    return new Symbol(name, false);
  }

  /** Struct to hold information about properties & usage */
  private class Symbol {
    public final String name;
    public int accessorCount = 0;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[164]++;
  }
    public int mutatorCount = 0;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[165]++;
  }
    public boolean aliasMutator = false;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[166]++;
  }
    public boolean aliasAccessor = false;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[167]++;
  }
    public final boolean isConstant;

    JSModule deepestModuleAccess = null;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[168]++;
  }
    JSModule deepestModuleMutate = null;
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[169]++;
  }

    List<Node> uses = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[170]++;
  }

    private Symbol(String name, boolean isConstant) {
      this.name = name;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[171]++;
      this.isConstant = isConstant;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[172]++;
    }

    void recordAccessor(NodeTraversal t) {
      accessorCount++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[173]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[174]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[69]++;
        deepestModuleAccess = (deepestModuleAccess == null) ?
            t.getModule() :
            moduleGraph.getDeepestCommonDependencyInclusive(
                t.getModule(), deepestModuleAccess);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[175]++;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[70]++;}
    }

    void recordMutator(NodeTraversal t) {
      mutatorCount++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[176]++;
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[177]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[71]++;
        deepestModuleMutate = (deepestModuleMutate == null) ?
            t.getModule() :
            moduleGraph.getDeepestCommonDependencyInclusive(
                t.getModule(), deepestModuleMutate);
CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.statements[178]++;

      } else {
  CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich.branches[72]++;}
    }
  }
}

class CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich ());
  }
    public static long[] statements = new long[179];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[35];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AliasExternals.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,2,1,1,2,1,2,3,1,1,1};
    for (int i = 1; i <= 34; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$12osfbp6571mw011i4o1amq49naich () {
    super("com.google.javascript.jscomp.AliasExternals.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 178; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 72; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AliasExternals.java");
      for (int i = 1; i <= 178; i++) {
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
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

