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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal.EdgeCallback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Analyzes properties on prototypes.
 *
 * Uses a reference graph to analyze prototype properties. Each unique property
 * name is represented by a node in this graph. An edge from property A to
 * property B means that there's a GETPROP access of a property B on some
 * object inside of a method named A.
 *
 * Global functions are also represented by nodes in this graph, with
 * similar semantics.
 *
 */
class AnalyzePrototypeProperties implements CompilerPass {
  static {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.ping();
  }


  // Constants for symbol types, for easier readability.
  private final SymbolType PROPERTY = SymbolType.PROPERTY;
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[1]++;
  }
  private final SymbolType VAR = SymbolType.VAR;
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[2]++;
  }

  private final AbstractCompiler compiler;
  private final boolean canModifyExterns;
  private final boolean anchorUnusedVars;
  private final JSModuleGraph moduleGraph;
  private final JSModule firstModule;

  // Properties that are implicitly used as part of the JS language.
  private static final Set<String> IMPLICITLY_USED_PROPERTIES =
      ImmutableSet.of("length", "toString", "valueOf");
  static {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[3]++;
  }

  // A graph where the nodes are property names or variable names,
  // and the edges signify the modules where the property is referenced.
  // For example, if we had the code:
  //
  // Foo.prototype.bar = function(x) { x.baz(); }; // in module 2.;
  //
  // then this would be represented in the graph by a node representing
  // "bar", a node representing "baz", and an edge between them representing
  // module #2.
  //
  // Similarly, if we had:
  //
  // var scotch = function(f) { return f.age(); };
  //
  // then there would be a node for "scotch", a node for "age", and an edge
  // from scotch to age.
  private final LinkedDirectedGraph<NameInfo, JSModule> symbolGraph =
      LinkedDirectedGraph.createWithoutAnnotations();
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[4]++;
  }

  // A dummy node for representing global references.
  private final NameInfo globalNode = new NameInfo("[global]");
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[5]++;
  }

  // A dummy node for representing extern references.
  private final NameInfo externNode = new NameInfo("[extern]");
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[6]++;
  }

  // A dummy node for representing all anonymous functions with no names.
  private final NameInfo anonymousNode = new NameInfo("[anonymous]");
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[7]++;
  }

  // All the real NameInfo for prototype properties, hashed by the name
  // of the property that they represent.
  private final Map<String, NameInfo> propertyNameInfo = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[8]++;
  }

  // All the NameInfo for global functions, hashed by the name of the
  // global variable that it's assigned to.
  private final Map<String, NameInfo> varNameInfo = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[9]++;
  }

  /**
   * Creates a new pass for analyzing prototype properties.
   * @param compiler The compiler.
   * @param moduleGraph The graph for resolving module dependencies. May be
   *     null if we don't care about module dependencies.
   * @param canModifyExterns If true, then we can move prototype
   *     properties that are declared in the externs file.
   * @param anchorUnusedVars If true, then we must mark all vars as referenced,
   *     even if they are never used.
   */
  AnalyzePrototypeProperties(AbstractCompiler compiler,
      JSModuleGraph moduleGraph, boolean canModifyExterns,
      boolean anchorUnusedVars) {
    this.compiler = compiler;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[10]++;
    this.moduleGraph = moduleGraph;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[11]++;
    this.canModifyExterns = canModifyExterns;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[12]++;
    this.anchorUnusedVars = anchorUnusedVars;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[13]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[1]++;
      firstModule = moduleGraph.getRootModule();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[15]++;

    } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[2]++;
      firstModule = null;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[16]++;
    }

    globalNode.markReference(null);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[17]++;
    externNode.markReference(null);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[18]++;
    symbolGraph.createNode(globalNode);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[19]++;
    symbolGraph.createNode(externNode);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[20]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[1]++;



    for (String property : IMPLICITLY_USED_PROPERTIES) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[1]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[2]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[3]++;
}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[22]++;
      NameInfo nameInfo = getNameInfoForName(property, PROPERTY);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((moduleGraph == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[3]++;
        symbolGraph.connect(externNode, null, nameInfo);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[24]++;

      } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[4]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[25]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[4]++;


        for (JSModule module : moduleGraph.getAllModules()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[4]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[5]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[6]++;
}
          symbolGraph.connect(externNode, module, nameInfo);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[26]++;
        }
      }
    }
  }

  @Override
  public void process(Node externRoot, Node root) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((canModifyExterns) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[5]++;
      NodeTraversal.traverse(compiler, externRoot,
          new ProcessExternProperties());
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[28]++;

    } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[6]++;}

    NodeTraversal.traverse(compiler, root, new ProcessProperties());
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[29]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[30]++;

    FixedPointGraphTraversal<NameInfo, JSModule> t =
        FixedPointGraphTraversal.newTraversal(new PropagateReferences());
    t.computeFixedPoint(symbolGraph,
        Sets.newHashSet(externNode, globalNode));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[31]++;
  }

  /**
   * Returns information on all prototype properties.
   */
  public Collection<NameInfo> getAllNameInfo() {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[32]++;
    List<NameInfo> result = Lists.newArrayList(propertyNameInfo.values());
    result.addAll(varNameInfo.values());
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[33]++;
    return result;
  }

  /**
   * Gets the name info for the property or variable of a given name,
   * and creates a new one if necessary.
   *
   * @param name The name of the symbol.
   * @param type The type of symbol.
   */
  private NameInfo getNameInfoForName(String name, SymbolType type) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[34]++;
    Map<String, NameInfo> map = type == PROPERTY ?
        propertyNameInfo : varNameInfo;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[35]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((map.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[7]++;
      return map.get(name);

    } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[8]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[36]++;
      NameInfo nameInfo = new NameInfo(name);
      map.put(name, nameInfo);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[37]++;
      symbolGraph.createNode(nameInfo);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[38]++;
      return nameInfo;
    }
  }

  private class ProcessProperties implements NodeTraversal.ScopedCallback {
    // There are two types of context information on this stack:
    // 1) Every scope has a NameContext corresponding to its scope.
    //    Variables are given VAR contexts.
    //    Prototype properties are given PROPERTY contexts.
    //    The global scope is given the special [global] context.
    //    And function expressions that we aren't able to give a reasonable
    //    name are given a special [anonymous] context.
    // 2) Every assignment of a prototype property of a non-function is
    //    given a name context. These contexts do not have scopes.
    private Stack<NameContext> symbolStack = new Stack<NameContext>();
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[39]++;
  }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[40]++;
      Node n = t.getCurrentNode();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[9]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[42]++;
        String propName = getPrototypePropertyNameFromRValue(n);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((propName != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[11]++;
          symbolStack.push(
              new NameContext(
                  getNameInfoForName(propName, PROPERTY),
                  t.getScope()));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[44]++;

        } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[12]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[45]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isGlobalFunctionDeclaration(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[13]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[46]++;
          Node parent = n.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[47]++;
          String name = parent.isName() ?
              parent.getString() /* VAR */ :
              n.getFirstChild().getString() /* named function */;
          symbolStack.push(
              new NameContext(getNameInfoForName(name, VAR), t.getScope()));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[48]++;

        } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[14]++;
          // NOTE(nicksantos): We use the same anonymous node for all
          // functions that do not have reasonable names. I can't remember
          // at the moment why we do this. I think it's because anonymous
          // nodes can never have in-edges. They're just there as a placeholder
          // for scope information, and do not matter in the edge propagation.
          symbolStack.push(new NameContext(anonymousNode, t.getScope()));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[49]++;
        }
}

      } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[10]++;
        Preconditions.checkState(t.inGlobalScope());
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[50]++;
        symbolStack.push(new NameContext(globalNode, t.getScope()));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[51]++;
      }
    }

    @Override
    public void exitScope(NodeTraversal t) {
      symbolStack.pop();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[52]++;
    }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[53]++;
      // Process prototype assignments to non-functions.
      String propName = processNonFunctionPrototypeAssign(n, parent);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[54]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((propName != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[15]++;
        symbolStack.push(
            new NameContext(
                getNameInfoForName(propName, PROPERTY), null));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[55]++;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[16]++;}
      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[17]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[57]++;
        String propName = n.getFirstChild().getNext().getString();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[58]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[19]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[59]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((propName.equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[21]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[60]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((processPrototypeRef(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[23]++;
              return;

            } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[24]++;}

          } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[22]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[61]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(propName)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[25]++;
            addGlobalUseOfSymbol(propName, t.getModule(), PROPERTY);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[62]++;
            return;

          } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[26]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
            // Do not mark prototype prop assigns as a 'use' in the global scope.
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((n.getParent().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[27]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[64]++;
              String rValueName = getPrototypePropertyNameFromRValue(n);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[65]++;
int CodeCoverConditionCoverageHelper_C15;
              if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((rValueName != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[29]++;
                return;

              } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[28]++;}
          }
}

        } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[20]++;}

        addSymbolUse(propName, t.getModule(), PROPERTY);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[66]++;

      } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[18]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[67]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[31]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[68]++;
        // Make sure that we're not handling object literals being
        // assigned to a prototype, as in:
        // Foo.prototype = {bar: 3, baz: 5};
        String lValueName = NodeUtil.getBestLValueName(
            NodeUtil.getBestLValue(n));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((lValueName != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((lValueName.endsWith(".prototype")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[33]++;
          return;

        } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[34]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[70]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;

        // var x = {a: 1, b: 2}
        // should count as a use of property a and b.
        for (Node propNameNode = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((propNameNode != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false);
             propNameNode = propNameNode.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[7]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[8]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[9]++;
}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;
          // May be STRING, GET, or SET, but NUMBER isn't interesting.
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((propNameNode.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[35]++;
            addSymbolUse(propNameNode.getString(), t.getModule(), PROPERTY);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[72]++;

          } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[36]++;}
        }

      } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[32]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[73]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[37]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[74]++;
        String name = n.getString();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[75]++;

        Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[39]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[77]++;
int CodeCoverConditionCoverageHelper_C22;
          // Only process global functions.
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[41]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[78]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((var.getInitialValue() != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((var.getInitialValue().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[43]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[79]++;
int CodeCoverConditionCoverageHelper_C24;
              if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[45]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[80]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((processGlobalFunctionDeclaration(t, n, var)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[47]++;
                  addGlobalUseOfSymbol(name, t.getModule(), VAR);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[81]++;

                } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[48]++;}

              } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[46]++;
                addSymbolUse(name, t.getModule(), VAR);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[82]++;
              }

            } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[44]++;}


          // If it is not a global, it might be accessing a local of the outer
          // scope. If that's the case the functions between the variable's
          // declaring scope and the variable reference scope cannot be moved.
          } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[42]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[83]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((var.getScope() != t.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)){
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[49]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[84]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[10]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int i = symbolStack.size() - 1;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[10]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[11]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[12]++;
}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[85]++;
              NameContext context = symbolStack.get(i);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[86]++;
int CodeCoverConditionCoverageHelper_C28;
              if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((context.scope == var.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[51]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[87]++;
                break;

              } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[52]++;}

              context.name.readClosureVariables = true;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[88]++;
            }

          } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[50]++;}
}

        } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[40]++;}

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[38]++;}
}
}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[89]++;
int CodeCoverConditionCoverageHelper_C29;

      // Process prototype assignments to non-functions.
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((processNonFunctionPrototypeAssign(n, parent) != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[53]++;
        symbolStack.pop();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[90]++;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[54]++;}
    }

    private void addSymbolUse(String name, JSModule module, SymbolType type) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[91]++;
      NameInfo info = getNameInfoForName(name, type);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[92]++;
      NameInfo def = null;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[93]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[13]++;


int CodeCoverConditionCoverageHelper_C30;
      // Skip all anonymous nodes. We care only about symbols with names.
      for (int i = symbolStack.size() - 1;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[13]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[14]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[15]++;
}
        def = symbolStack.get(i).name;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[94]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[95]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((def != anonymousNode) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[55]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[96]++;
          break;

        } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[56]++;}
      }
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[97]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((def.equals(info)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[57]++;
        symbolGraph.connect(def, module, info);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[98]++;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[58]++;}
    }

    /**
     * If this is a non-function prototype assign, return the prop name.
     * Otherwise, return null.
     */
    private String processNonFunctionPrototypeAssign(Node n, Node parent) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[99]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((isAssignRValue(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[59]++;
        return getPrototypePropertyNameFromRValue(n);

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[60]++;}
      return null;
    }

    /**
     * Determines whether {@code n} is the FUNCTION node in a global function
     * declaration.
     */
    private boolean isGlobalFunctionDeclaration(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[100]++;
      // Make sure we're either in the global scope, or the function
      // we're looking at is the root of the current local scope.
      Scope s = t.getScope();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[101]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (32)) == 0 || true) &&
 ((s.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((s.getDepth() == 1) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((s.getRootNode() == n) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 3) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 3) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[61]++;
        return false;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[62]++;}

      return NodeUtil.isFunctionDeclaration(n) ||
          n.isFunction() && n.getParent().isName();
    }

    /**
     * Returns true if this is the r-value of an assignment.
     */
    private boolean isAssignRValue(Node n, Node parent) {
      return parent != null && parent.isAssign() && parent.getFirstChild() != n;
    }

    /**
     * Returns the name of a prototype property being assigned to this r-value.
     *
     * Returns null if this is not the R-value of a prototype property, or if
     * the R-value is used in multiple expressions (i.e., if there's
     * a prototype property assignment in a more complex expression).
     */
    private String getPrototypePropertyNameFromRValue(Node rValue) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[102]++;
      Node lValue = NodeUtil.getBestLValue(rValue);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[103]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (512)) == 0 || true) &&
 ((lValue == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (128)) == 0 || true) &&
 ((lValue.getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (32)) == 0 || true) &&
 ((lValue.getParent().getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (16)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(lValue.getParent().getParent())) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 5) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 5) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[63]++;
        return null;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[64]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[104]++;

      String lValueName =
          NodeUtil.getBestLValueName(NodeUtil.getBestLValue(rValue));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[105]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((lValueName == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[65]++;
        return null;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[66]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[106]++;
      int lastDot = lValueName.lastIndexOf('.');
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[107]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((lastDot == -1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[67]++;
        return null;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[68]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[108]++;

      String firstPart = lValueName.substring(0, lastDot);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[109]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((firstPart.endsWith(".prototype")) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[69]++;
        return null;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[70]++;}

      return lValueName.substring(lastDot + 1);
    }

    /**
     * Processes a NAME node to see if it's a global function declaration.
     * If it is, record it and return true. Otherwise, return false.
     */
    private boolean processGlobalFunctionDeclaration(NodeTraversal t,
        Node nameNode, Var v) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[110]++;
      Node firstChild = nameNode.getFirstChild();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[111]++;
      Node parent = nameNode.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[112]++;
int CodeCoverConditionCoverageHelper_C39;

      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((// Check for a named FUNCTION.
          isGlobalFunctionDeclaration(t, parent)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((// Check for a VAR declaration.
          firstChild != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((isGlobalFunctionDeclaration(t, firstChild)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[71]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[113]++;
        String name = nameNode.getString();
        getNameInfoForName(name, VAR).getDeclarations().add(
            new GlobalFunction(nameNode, v, t.getModule()));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[114]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[115]++;
int CodeCoverConditionCoverageHelper_C40;

        // If the function name is exported, we should create an edge here
        // so that it's never removed.
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(name)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((anchorUnusedVars) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[73]++;
          addGlobalUseOfSymbol(name, t.getModule(), VAR);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[116]++;

        } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[74]++;}

        return true;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[72]++;}
      return false;
    }

    /**
     * Processes the GETPROP of prototype, which can either be under
     * another GETPROP (in the case of Foo.prototype.bar), or can be
     * under an assignment (in the case of Foo.prototype = ...).
     * @return True if a declaration was added.
     */
    private boolean processPrototypeRef(NodeTraversal t, Node ref) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[117]++;
      Node root = NodeUtil.getRootOfQualifiedName(ref);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[118]++;

      Node n = ref.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[119]++;
      switch (n.getType()) {
        // Foo.prototype.getBar = function() { ... }
        case Token.GETPROP:
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[75]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[120]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[121]++;
          Node parent = n.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[122]++;
          Node grandParent = parent.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[123]++;
int CodeCoverConditionCoverageHelper_C41;

          if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (32)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((NodeUtil.isExprAssign(grandParent)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[76]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[124]++;
            String name = dest.getString();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[125]++;
            Property prop = new AssignmentProperty(
                grandParent,
                maybeGetVar(t, root),
                t.getModule());
            getNameInfoForName(name, PROPERTY).getDeclarations().add(prop);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[126]++;
            return true;

          } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[77]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[127]++;
          break;

        // Foo.prototype = { "getBar" : function() { ... } }
        case Token.ASSIGN:
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[78]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[128]++;
          Node map = n.getFirstChild().getNext();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[129]++;
int CodeCoverConditionCoverageHelper_C42;
          if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((map.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[79]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[130]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[16]++;


int CodeCoverConditionCoverageHelper_C43;
            for (Node key = map.getFirstChild();(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); key = key.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[16]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[17]--;
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.loops[18]++;
}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[131]++;
              // May be STRING, GETTER_DEF, or SETTER_DEF,
              String name = key.getString();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[132]++;
              Property prop = new LiteralProperty(
                  key, key.getFirstChild(), map, n,
                  maybeGetVar(t, root),
                  t.getModule());
              getNameInfoForName(name, PROPERTY).getDeclarations().add(prop);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[133]++;
            }
            return true;

          } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[80]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[134]++;
          break; default : CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[81]++;
      }
      return false;
    }

    private Var maybeGetVar(NodeTraversal t, Node maybeName) {
      return maybeName.isName()
          ? t.getScope().getVar(maybeName.getString()) : null;
    }

    private void addGlobalUseOfSymbol(String name, JSModule module,
        SymbolType type) {
      symbolGraph.connect(globalNode, module, getNameInfoForName(name, type));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[135]++;
    }
  }

  private class ProcessExternProperties extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[136]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[82]++;
        symbolGraph.connect(externNode, firstModule,
            getNameInfoForName(n.getLastChild().getString(), PROPERTY));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[137]++;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[83]++;}
    }
  }

  private class PropagateReferences
      implements EdgeCallback<NameInfo, JSModule> {
    @Override
    public boolean traverseEdge(NameInfo start, JSModule edge, NameInfo dest) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[138]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((start.isReferenced()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[84]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[139]++;
        JSModule startModule = start.getDeepestCommonModuleRef();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[140]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((startModule != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(startModule, edge)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[86]++;
          return dest.markReference(startModule);

        } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[87]++;
          return dest.markReference(edge);
        }

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[85]++;}
      return false;
    }
  }

  // TODO(user): We can use DefinitionsRemover and UseSite here. Then all
  // we need to do is call getDefinition() and we'll magically know everything
  // about the definition.

  /**
   * The declaration of an abstract symbol.
   */
  interface Symbol {
    /**
     * Remove the declaration from the AST.
     */
    void remove();

    /**
     * The variable for the root of this symbol.
     */
    Var getRootVar();

    /**
     * Returns the module where this appears.
     */
    JSModule getModule();
  }

  private enum SymbolType {
    PROPERTY,
    VAR;
  }

  /**
   * A function initialized as a VAR statement or a function declaration.
   */
  class GlobalFunction implements Symbol {
    private final Node nameNode;
    private final Var var;
    private final JSModule module;

    GlobalFunction(Node nameNode, Var var, JSModule module) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[141]++;
      Node parent = nameNode.getParent();
      Preconditions.checkState(
          parent.isVar() ||
          NodeUtil.isFunctionDeclaration(parent));
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[142]++;
      this.nameNode = nameNode;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[143]++;
      this.var = var;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[144]++;
      this.module = module;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[145]++;
    }

    @Override
    public Var getRootVar() {
      return var;
    }

    @Override
    public void remove() {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[146]++;
      Node parent = nameNode.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[147]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((parent.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[88]++;
        NodeUtil.removeChild(parent.getParent(), parent);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[148]++;

      } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[89]++;
        Preconditions.checkState(parent.isVar());
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[149]++;
        parent.removeChild(nameNode);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[150]++;
      }
    }

    @Override
    public JSModule getModule() {
      return module;
    }

    public Node getFunctionNode() {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[151]++;
      Node parent = nameNode.getParent();
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[152]++;
int CodeCoverConditionCoverageHelper_C48;

      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[90]++;
        return parent;

      } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[91]++;
        // we are the name of a var node, so the function is name's second child
        return nameNode.getChildAtIndex(1);
      }
    }
  }

  /**
   * Since there are two ways of assigning properties to prototypes, we hide
   * then behind this interface so they can both be removed regardless of type.
   */
  interface Property extends Symbol {

    /** Returns the GETPROP node that refers to the prototype. */
    Node getPrototype();

    /** Returns the value of this property. */
    Node getValue();
  }

  /**
   * Properties created via EXPR assignment:
   *
   * <pre>function Foo() { ... };
   * Foo.prototype.bar = function() { ... };</pre>
   */
  static class AssignmentProperty implements Property {
    private final Node exprNode;
    private final Var rootVar;
    private final JSModule module;

    /**
     * @param node An EXPR node.
     */
    AssignmentProperty(Node node, Var rootVar, JSModule module) {
      this.exprNode = node;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[153]++;
      this.rootVar = rootVar;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[154]++;
      this.module = module;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[155]++;
    }

    @Override
    public Var getRootVar() {
      return rootVar;
    }

    @Override
    public void remove() {
      NodeUtil.removeChild(exprNode.getParent(), exprNode);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[156]++;
    }

    @Override
    public Node getPrototype() {
      return getAssignNode().getFirstChild().getFirstChild();
    }

    @Override
    public Node getValue() {
      return getAssignNode().getLastChild();
    }

    private Node getAssignNode() {
      return exprNode.getFirstChild();
    }

    @Override
    public JSModule getModule() {
      return module;
    }
  }

  /**
   * Properties created via object literals:
   *
   * <pre>function Foo() { ... };
   * Foo.prototype = {bar: function() { ... };</pre>
   */
  static class LiteralProperty implements Property {
    private final Node key;
    private final Node value;
    private final Node map;
    private final Node assign;
    private final Var rootVar;
    private final JSModule module;

    LiteralProperty(Node key, Node value, Node map, Node assign,
        Var rootVar, JSModule module) {
      this.key = key;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[157]++;
      this.value = value;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[158]++;
      this.map = map;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[159]++;
      this.assign = assign;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[160]++;
      this.rootVar = rootVar;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[161]++;
      this.module = module;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[162]++;
    }

    @Override
    public Var getRootVar() {
      return rootVar;
    }

    @Override
    public void remove() {
      map.removeChild(key);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[163]++;
    }

    @Override
    public Node getPrototype() {
      return assign.getFirstChild();
    }

    @Override
    public Node getValue() {
      return value;
    }

    @Override
    public JSModule getModule() {
      return module;
    }
  }

  /**
   * The context of the current name. This includes the NameInfo and the scope
   * if it is a scope defining name (function).
   */
  private class NameContext {
    final NameInfo name;

    // If this is a function context, then scope will be the scope of the
    // corresponding function. Otherwise, it will be null.
    final Scope scope;

    NameContext(NameInfo name, Scope scope) {
      this.name = name;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[164]++;
      this.scope = scope;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[165]++;
    }
  }

  /**
   * Information on all properties or global variables of a given name.
   */
  class NameInfo {

    final String name;

    private boolean referenced = false;
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[166]++;
  }
    private final Deque<Symbol> declarations = new ArrayDeque<Symbol>();
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[167]++;
  }
    private JSModule deepestCommonModuleRef = null;
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[168]++;
  }

    // True if this property is a function that reads a variable from an
    // outer scope which isn't the global scope.
    private boolean readClosureVariables = false;
  {
    CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[169]++;
  }

    /**
     * Constructs a new NameInfo.
     * @param name The name of the property that this represents. May be null
     *     to signify dummy nodes in the property graph.
     */
    NameInfo(String name) {
      this.name = name;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[170]++;
    }

    @Override public String toString() { return name; }

    /** Determines whether we've marked a reference to this property name. */
    boolean isReferenced() {
      return referenced;
    }

    /** Determines whether it reads a closure variable. */
    boolean readsClosureVariables() {
      return readClosureVariables;
    }

    /**
     * Mark a reference in a given module to this property name, and record
     * the deepest common module reference.
     * @param module The module where it was referenced.
     * @return Whether the name info has changed.
     */
    boolean markReference(JSModule module) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[171]++;
      boolean hasChanged = false;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[172]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((referenced) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[92]++;
        referenced = true;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[173]++;
        hasChanged = true;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[174]++;

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[93]++;}
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[175]++;
int CodeCoverConditionCoverageHelper_C50;

      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[94]++;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[176]++;
        JSModule originalDeepestCommon = deepestCommonModuleRef;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[177]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((deepestCommonModuleRef == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[96]++;
          deepestCommonModuleRef = module;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[178]++;

        } else {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[97]++;
          deepestCommonModuleRef =
              moduleGraph.getDeepestCommonDependencyInclusive(
                  deepestCommonModuleRef, module);
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[179]++;
        }
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[180]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((originalDeepestCommon != deepestCommonModuleRef) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[98]++;
          hasChanged = true;
CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.statements[181]++;

        } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[99]++;}

      } else {
  CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35.branches[95]++;}

      return hasChanged;
    }

    /**
     * Returns the deepest common module of all the references to this
     * property.
     */
    JSModule getDeepestCommonModuleRef() {
      return deepestCommonModuleRef;
    }

    /**
     * Returns a mutable collection of all the prototype property declarations
     * of this property name.
     */
    Deque<Symbol> getDeclarations() {
      return declarations;
    }
  }
}

class CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35 ());
  }
    public static long[] statements = new long[182];
    public static long[] branches = new long[100];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[53];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AnalyzePrototypeProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,3,3,1,1,1,3,2,3,1,1,1,1,2,2,1,1,1,1,1};
    for (int i = 1; i <= 52; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$897osexxj7zv79iaig81ryhk969sz0e9gel2cswhbnzjjj35 () {
    super("com.google.javascript.jscomp.AnalyzePrototypeProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 181; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 99; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 52; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AnalyzePrototypeProperties.java");
      for (int i = 1; i <= 181; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 99; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 52; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

