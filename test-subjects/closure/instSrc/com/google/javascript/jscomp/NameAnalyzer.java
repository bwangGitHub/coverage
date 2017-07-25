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
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.GatherSideEffectSubexpressionsCallback.GetReplacementSideEffectSubexpressions;
import com.google.javascript.jscomp.GatherSideEffectSubexpressionsCallback.SideEffectAccumulator;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal.EdgeCallback;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This pass identifies all global names, simple (e.g. <code>a</code>) or
 * qualified (e.g. <code>a.b.c</code>), and the dependencies between them, then
 * removes code associated with unreferenced names. It starts by assuming that
 * only externally accessible names (e.g. <code>window</code>) are referenced,
 * then iteratively marks additional names as referenced (e.g. <code>Foo</code>
 * in <code>window['foo'] = new Foo();</code>). This makes it possible to
 * eliminate code containing circular references.
 *
 * <p>Qualified names can be defined using dotted or object literal syntax
 * (<code>a.b.c = x;</code> or <code>a.b = {c: x};</code>, respectively).
 *
 * <p>Removal of prototype classes is currently all or nothing. In other words,
 * prototype properties and methods are never individually removed.
 *
 * <p>Optionally generates pretty HTML output of data so that it is easy to
 * analyze dependencies.
 *
 * <p>Only operates on names defined in the global scope, but it would be easy
 * to extend the pass to names defined in local scopes.
 *
 * TODO(nicksantos): In the initial implementation of this pass, it was
 * important to understand namespaced names (e.g., that a.b is distinct from
 * a.b.c). Now that this pass comes after CollapseProperties, this is no longer
 * necessary. For now, I've changed so that {@code referenceParentNames}
 * creates a two-way reference between a.b and a.b.c, so that they're
 * effectively the same name. When someone has the time, we should completely
 * rip out all the logic that understands namespaces.
 *
 */
final class NameAnalyzer implements CompilerPass {
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.ping();
  }


  /** Reference to the JS compiler */
  private final AbstractCompiler compiler;

  /** Map of all JS names found */
  private final Map<String, JsName> allNames = Maps.newTreeMap();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[1]++;
  }

  /** Reference dependency graph */
  private DiGraph<JsName, RefType> referenceGraph =
      LinkedDirectedGraph.createWithoutAnnotations();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[2]++;
  }

  /**
   * Map of name scopes - all children of the Node key have a dependency on the
   * name value.
   *
   * If scopes.get(node).equals(name) && node2 is a child of node, then node2
   * will not get executed unless name is referenced via a get operation
   */
  private final ListMultimap<Node, NameInformation> scopes =
      LinkedListMultimap.create();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[3]++;
  }

  /** Used to parse prototype names */
  private static final String PROTOTYPE_SUBSTRING = ".prototype.";
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[4]++;
  }

  private static final int PROTOTYPE_SUBSTRING_LEN =
      PROTOTYPE_SUBSTRING.length();
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[5]++;
  }

  private static final int PROTOTYPE_SUFFIX_LEN = ".prototype".length();
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[6]++;
  }

  /** Window root */
  private static final String WINDOW = "window";
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[7]++;
  }

  /** Function class name */
  private static final String FUNCTION = "Function";
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[8]++;
  }

  /** All of these refer to global scope. These can be moved to config */
  static final Set<String> DEFAULT_GLOBAL_NAMES = ImmutableSet.of(
      "window", "goog.global");
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[9]++;
  }

  /** Whether to remove unreferenced variables in main pass */
  private final boolean removeUnreferenced;

  /** Names that refer to the global scope */
  private final Set<String> globalNames;

  /** Ast change helper */
  private final AstChangeProxy changeProxy;

  /** Names that are externally defined */
  private final Set<String> externalNames = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[10]++;
  }

  /** Name declarations or assignments, in post-order traversal order */
  private final List<RefNode> refNodes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[11]++;
  }

  /**
   * When multiple names in the global scope point to the same object, we
   * call them aliases. Store a map from each alias name to the alias set.
   */
  private final Map<String, AliasSet> aliases = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[12]++;
  }

  /**
   * All the aliases in a program form a graph, where each global name is
   * a node in the graph, and two names are connected if one directly aliases
   * the other.
   *
   * An {@code AliasSet} represents a connected component in that graph. We do
   * not explicitly track the graph--we just track the connected components.
   */
  private static class AliasSet {
    Set<String> names = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[13]++;
  }

    // Every alias set starts with exactly 2 names.
    AliasSet(String name1, String name2) {
      names.add(name1);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[14]++;
      names.add(name2);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[15]++;
    }
  }

  /**
   * Relationship between the two names.
   * Currently only two different reference types exists:
   * goog.inherits class relations and all other references.
   */
  private static enum RefType {
    REGULAR,
    INHERITANCE,
  }

  /**
   * Callback that propagates reference information.
   */
  private static class ReferencePropagationCallback
      implements EdgeCallback<JsName, RefType> {
    @Override
    public boolean traverseEdge(JsName from,
                                RefType callSite,
                                JsName to) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((from.referenced) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((to.referenced) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[1]++;
        to.referenced = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[17]++;
        return true;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[2]++;
        return false;
      }
    }
  }

  /**
   * Class to hold information that can be determined from a node tree about a
   * given name
   */
  private static class NameInformation {
    /** Fully qualified name */
    String name;

    /** Whether the name is guaranteed to be externally referenceable */
    boolean isExternallyReferenceable = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[18]++;
  }

    /** Whether this name is a prototype function */
    boolean isPrototype = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[19]++;
  }

    /** Name of the prototype class, i.e. "a" if name is "a.prototype.b" */
    String prototypeClass = null;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[20]++;
  }

    /** Local name of prototype property i.e. "b" if name is "a.prototype.b" */
    String prototypeProperty = null;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[21]++;
  }

    /** Name of the super class of name */
    String superclass = null;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[22]++;
  }

    /** Whether this is a call that only affects the class definition */
    boolean onlyAffectsClassDef = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[23]++;
  }
  }

  /**
   * Struct to hold information about a fully qualified JS name
   */
  private static class JsName implements Comparable<JsName> {
    /** Fully qualified name */
    String name;

    /** Name of prototype functions attached to this name */
    List<String> prototypeNames = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[24]++;
  }

    /** Whether this is an externally defined name */
    boolean externallyDefined = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[25]++;
  }

    /** Whether this node is referenced */
    boolean referenced = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[26]++;
  }

    /** Whether the name has descendants that are written to. */
    boolean hasWrittenDescendants = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[27]++;
  }

    /** Whether the name is used in a instanceof check */
    boolean hasInstanceOfReference = false;
  {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[28]++;
  }

    /**
     * Output the node as a string
     *
     * @return Node as a string
     */
    @Override
    public String toString() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[29]++;
      StringBuilder out = new StringBuilder();
      out.append(name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[30]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[31]++;
int CodeCoverConditionCoverageHelper_C2;

      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prototypeNames.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[3]++;
        out.append(" (CLASS)\n");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[32]++;
        out.append(" - FUNCTIONS: ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[33]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[34]++;
        Iterator<String> pIter = prototypeNames.iterator();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[35]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[1]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[2]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[3]++;
}
          out.append(pIter.next());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[36]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[37]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((pIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[5]++;
            out.append(", ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[38]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[6]++;}
        }

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[4]++;}

      return out.toString();
    }

    @Override
    public int compareTo(JsName rhs) {
      return this.name.compareTo(rhs.name);
    }
  }

  /**
   * Interface to get information about and remove unreferenced names.
   */
  interface RefNode {
    JsName name();
    void remove();
  }

  /**
   * Class for nodes that reference a fully-qualified JS name. Fully qualified
   * names are of form A or A.B (A.B.C, etc.). References can get the value or
   * set the value of the JS name.
   */
  private class JsNameRefNode implements RefNode {
    /** JsName node for this reference */
    JsName name;

    /**
     * Top GETPROP or NAME or STRING [objlit key] node defining the name of
     * this node
     */
    @SuppressWarnings("unused")
    Node node;

    /**
     * Parent node of the name access
     * (ASSIGN, VAR, FUNCTION, OBJECTLIT, or CALL)
     */
    Node parent;


    /**
     * Create a node that refers to a name
     *
     * @param name The name
     * @param node The top node representing the name (GETPROP, NAME, STRING)
     */
    JsNameRefNode(JsName name, Node node) {
      this.name = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[39]++;
      this.node = node;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[40]++;
      this.parent = node.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[41]++;
    }

    @Override
    public JsName name() {
      return name;
    }

    @Override
    public void remove() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[42]++;
      // Setters have VAR, FUNCTION, or ASSIGN parent nodes. CALL parent
      // nodes are global refs, and are handled later in this function.
      Node containingNode = parent.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[43]++;
      switch (parent.getType()) {
        case Token.VAR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[7]++;
          Preconditions.checkState(parent.hasOneChild());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[44]++;
          replaceWithRhs(containingNode, parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[45]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[46]++;
          break;
        case Token.FUNCTION:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[8]++;
          replaceWithRhs(containingNode, parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[47]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[48]++;
          break;
        case Token.ASSIGN:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[9]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[49]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((containingNode.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[10]++;
            replaceWithRhs(containingNode.getParent(), containingNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[50]++;

          } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[11]++;
            replaceWithRhs(containingNode, parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[51]++;
          }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[52]++;
          break;
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[12]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[53]++;
          // TODO(nicksantos): Come up with a way to remove this.
          // If we remove object lit keys, then we will need to also
          // create dependency scopes for them.
          break; default : CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[13]++;
      }
    }
  }


  /**
   * Class for nodes that set prototype properties or methods.
   */
  private class PrototypeSetNode extends JsNameRefNode {
    /**
     * Create a set node from the name & setter node
     *
     * @param name The name
     * @param parent Parent node that assigns the expression (an ASSIGN)
     */
    PrototypeSetNode(JsName name, Node parent) {
      super(name, parent.getFirstChild());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[54]++;

      Preconditions.checkState(parent.isAssign());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[55]++;
    }

    @Override public void remove() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[56]++;
      Node gramps = parent.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[57]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((gramps.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[14]++;
        // name.prototype.foo = function() { ... };
        changeProxy.removeChild(gramps.getParent(), gramps);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[58]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[15]++;
        // ... name.prototype.foo = function() { ... } ...
        changeProxy.replaceWith(gramps, parent,
                                parent.getLastChild().detachFromParent());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[59]++;
      }
    }
  }

  /**
   * Base class for special reference nodes.
   */
  private abstract class SpecialReferenceNode implements RefNode {
    /** JsName node for the function */
    JsName name;

    /** The CALL node */
    Node node;

    /**
     * Create a special reference node.
     *
     * @param name The name
     * @param node The CALL node
     */
    SpecialReferenceNode(JsName name, Node node) {
      this.name = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[60]++;
      this.node = node;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[61]++;
    }

    @Override
    public JsName name() {
      return name;
    }

    Node getParent() {
      return node.getParent();
    }

    Node getGramps() {
      return node.getParent() == null ? null : node.getParent().getParent();
    }
  }



  /**
   * Class for nodes that are function calls that may change a function's
   * prototype
   */
  private class ClassDefiningFunctionNode extends SpecialReferenceNode {
    /**
     * Create a class defining function node from the name & setter node
     *
     * @param name The name
     * @param node The CALL node
     */
    ClassDefiningFunctionNode(JsName name, Node node) {
      super(name, node);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[62]++;
      Preconditions.checkState(node.isCall());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[63]++;
    }

    @Override
    public void remove() {
      Preconditions.checkState(node.isCall());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[64]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[65]++;
      Node parent = getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[66]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[16]++;
        changeProxy.removeChild(getGramps(), parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[67]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[17]++;
        changeProxy.replaceWith(parent, node, IR.voidNode(IR.number(0)));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[68]++;
      }
    }
  }



  /**
   * Class for nodes that check instanceof
   */
  private class InstanceOfCheckNode extends SpecialReferenceNode {
    /**
     * Create an instanceof node from the name and parent node
     *
     * @param name The name
     * @param node The qualified name node
     */
    InstanceOfCheckNode(JsName name, Node node) {
      super(name, node);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[69]++;
      Preconditions.checkState(node.isQualifiedName());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[70]++;
      Preconditions.checkState(getParent().isInstanceOf());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[71]++;
    }

    @Override
    public void remove() {
      changeProxy.replaceWith(getGramps(), getParent(), IR.falseNode());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[72]++;
    }
  }

  /**
   * Walk through externs and mark nodes as externally declared if declared
   */
  private class ProcessExternals extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[73]++;
      NameInformation ns = null;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[74]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[18]++;
        ns = createNameInformation(t, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[75]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[19]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[76]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[20]++;
        ns = createNameInformation(t, n.getFirstChild());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[77]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[21]++;}
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[78]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[22]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[79]++;
        JsName jsName = getName(ns.name, true);
        jsName.externallyDefined = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[80]++;
        externalNames.add(ns.name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[81]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[23]++;}
    }
  }

  /**
   * <p>Identifies all dependency scopes.
   *
   * <p>A dependency scope is a relationship between a node tree and a name that
   * implies that the node tree will not execute (and thus can be eliminated) if
   * the name is never referenced.
   *
   * <p>The entire parse tree is ultimately in a dependency scope relationship
   * with <code>window</code> (or an equivalent name for the global scope), but
   * the goal here is to find finer-grained relationships. This callback creates
   * dependency scopes for every assignment statement, variable declaration, and
   * function call in the global scope.
   *
   * <p>Note that dependency scope node trees aren't necessarily disjoint.
   * In the following code snippet, for example, the function definition
   * forms a dependency scope with the name <code>f</code> and the assignment
   * inside the function forms a dependency scope with the name <code>x</code>.
   * <pre>
   * var x; function f() { x = 1; }
   * </pre>
   */
  private class FindDependencyScopes extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[82]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[24]++;
        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[25]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[83]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[26]++;
        recordAssignment(t, n, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[84]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[85]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableResult(n.getLastChild())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[28]++;
          recordConsumers(t, n, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[86]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[29]++;}

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[27]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[87]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[30]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[88]++;
        NameInformation ns = createNameInformation(t, n);
        recordDepScope(n, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[89]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[31]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[90]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[32]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[91]++;
        NameInformation ns = createNameInformation(t, n.getFirstChild());
        recordDepScope(n, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[92]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[33]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[93]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprCall(n)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[34]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[94]++;
        Node callNode = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[95]++;
        Node nameNode = callNode.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[96]++;
        NameInformation ns = createNameInformation(t, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[97]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ns.onlyAffectsClassDef) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[36]++;
          recordDepScope(n, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[98]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[37]++;}

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[35]++;}
}
}
}
    }

    private void recordConsumers(NodeTraversal t, Node n, Node recordNode) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[99]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[100]++;
      switch (parent.getType()) {
        case Token.ASSIGN:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[38]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[101]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n == parent.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[39]++;
            recordAssignment(t, parent, recordNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[102]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[40]++;}
          recordConsumers(t, parent, recordNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[103]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[104]++;
          break;
        case Token.NAME:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[41]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[105]++;
          NameInformation ns = createNameInformation(t, parent);
          recordDepScope(recordNode, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[106]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[107]++;
          break;
        case Token.OR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[42]++;
          recordConsumers(t, parent, recordNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[108]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[109]++;
          break;
        case Token.AND:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[43]++;
          // In "a && b" only "b" can be meaningfully aliased.
          // "a" must be falsy, which it must be an immutable, non-Object
        case Token.COMMA:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[44]++;
        case Token.HOOK:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[45]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[110]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n != parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[46]++;
            recordConsumers(t, parent, recordNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[111]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[47]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[112]++;
          break; default : CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[48]++;
      }
    }

    private void recordAssignment(NodeTraversal t, Node n, Node recordNode) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[113]++;
      Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[114]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[115]++;
      NameInformation ns = createNameInformation(t, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[116]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[49]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[117]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[51]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[118]++;
int CodeCoverConditionCoverageHelper_C22;
          // Patch for assignments that appear in the init,
          // condition or iteration part of a FOR loop.  Without
          // this change, all 3 of those parts try to claim the for
          // loop as their dependency scope.  The last assignment in
          // those three fields wins, which can result in incorrect
          // reference edges between referenced and assigned variables.
          //
          // TODO(user) revisit the dependency scope calculation
          // logic.
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((parent.getFirstChild().getNext() != n) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[53]++;
            recordDepScope(recordNode, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[119]++;

          } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[54]++;
            recordDepScope(nameNode, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[120]++;
          }

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[52]++;
          recordDepScope(recordNode, ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[121]++;
        }

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[50]++;}
    }

    /**
     * Defines a dependency scope.
     */
    private void recordDepScope(Node node, NameInformation name) {
      Preconditions.checkNotNull(name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[122]++;
      scopes.put(node, name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[123]++;
    }
  }

  /**
   * Create JsName objects for variable and function declarations in
   * the global scope before computing name references.  In JavaScript
   * it is legal to refer to variable and function names before the
   * actual declaration.
   */
  private class HoistVariableAndFunctionDeclarations
      extends NodeTraversal.AbstractShallowCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[124]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[55]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[125]++;
        NameInformation ns = createNameInformation(t, n);
        Preconditions.checkNotNull(ns, "NameInformation is null");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[126]++;
        createName(ns.name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[127]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[56]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[128]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[57]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[129]++;
        Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[130]++;
        NameInformation ns = createNameInformation(t, nameNode);
        Preconditions.checkNotNull(ns, "NameInformation is null");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[131]++;
        createName(nameNode.getString());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[132]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[58]++;}
}
    }
  }

  /**
   * Identifies all declarations of global names and setter statements
   * affecting global symbols (assignments to global names).
   *
   * All declarations and setters must be gathered in a single
   * traversal and stored in traversal order so "removeUnreferenced"
   * can perform modifications in traversal order.
   */
  private class FindDeclarationsAndSetters extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[133]++;
int CodeCoverConditionCoverageHelper_C25;

      // Record global variable and function declarations
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[59]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[134]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[61]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[135]++;
          NameInformation ns = createNameInformation(t, n);
          Preconditions.checkNotNull(ns);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[136]++;
          recordSet(ns.name, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[137]++;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[62]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[138]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[63]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[139]++;
          Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[140]++;
          NameInformation ns = createNameInformation(t, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[141]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[65]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[142]++;
            JsName nameInfo = getName(nameNode.getString(), true);
            recordSet(nameInfo.name, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[143]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[66]++;}

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[64]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[144]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[67]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[145]++;
          NameInformation ns = createNameInformation(t, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[146]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[69]++;
            recordSet(ns.name, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[147]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[70]++;}

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[68]++;}
}
}

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[60]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[148]++;
int CodeCoverConditionCoverageHelper_C31;

      // Record assignments and call sites
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[71]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[149]++;
        Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[150]++;

        NameInformation ns = createNameInformation(t, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[151]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[73]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[152]++;
int CodeCoverConditionCoverageHelper_C33;
          if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ns.isPrototype) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[75]++;
            recordPrototypeSet(ns.prototypeClass, ns.prototypeProperty, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[153]++;

          } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[76]++;
            recordSet(ns.name, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[154]++;
          }

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[74]++;}

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[72]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[155]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[77]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[156]++;
        Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[157]++;
        NameInformation ns = createNameInformation(t, nameNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[158]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((ns.onlyAffectsClassDef) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[79]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[159]++;
          JsName name = getName(ns.name, true);
          refNodes.add(new ClassDefiningFunctionNode(name, n));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[160]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[80]++;}

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[78]++;}
}
    }

    /**
     * Records the assignment of a value to a global name.
     *
     * @param name Fully qualified name
     * @param node The top node representing the name (GETPROP, NAME, or STRING
     * [objlit key])
     */
    private void recordSet(String name, Node node) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[161]++;
      JsName jsn = getName(name, true);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[162]++;
      JsNameRefNode nameRefNode = new JsNameRefNode(jsn, node);
      refNodes.add(nameRefNode);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[163]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[164]++;
int CodeCoverConditionCoverageHelper_C36;

      // Now, look at all parent names and record that their properties have
      // been written to.
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((node.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[81]++;
        recordWriteOnProperties(name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[165]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[82]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[166]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((name.indexOf('.') != -1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[83]++;
        recordWriteOnProperties(name.substring(0, name.lastIndexOf('.')));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[167]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[84]++;}
}
    }

    /**
     * Records the assignment to a prototype property of a global name,
     * if possible.
     *
     * @param className The name of the class.
     * @param prototypeProperty The name of the prototype property.
     * @param node The top node representing the name (GETPROP)
     */
    private void recordPrototypeSet(String className, String prototypeProperty,
        Node node) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[168]++;
      JsName name = getName(className, true);
      name.prototypeNames.add(prototypeProperty);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[169]++;
      refNodes.add(new PrototypeSetNode(name, node));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[170]++;
      recordWriteOnProperties(className);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[171]++;
    }

    /**
     * Record that the properties of this name have been written to.
     */
    private void recordWriteOnProperties(String parentName) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[172]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[4]++;


      do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[4]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[5]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[6]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[173]++;
        JsName parent = getName(parentName, true);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[174]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((parent.hasWrittenDescendants) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[85]++;
          // If we already recorded this name, then all its parents must
          // also be recorded. short-circuit this loop.
          return;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[86]++;
          parent.hasWrittenDescendants = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[175]++;
        }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[176]++;
int CodeCoverConditionCoverageHelper_C40;

        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((parentName.indexOf('.') == -1) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[87]++;
          return;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[88]++;}
        parentName = parentName.substring(0, parentName.lastIndexOf('.'));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[177]++;
      } while(true);
    }
  }

  private static final Predicate<Node> NON_LOCAL_RESULT_PREDICATE =
      new Predicate<Node>() {
        @Override
        public boolean apply(Node input) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[178]++;
int CodeCoverConditionCoverageHelper_C41;
          if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((input.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[89]++;
            return false;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[90]++;}
          // TODO(johnlenz): handle NEW calls that record their 'this'
          // in global scope and effectively return an alias.
          // Other non-local references are handled by this pass.
          return true;
        }
      };
  static {
    CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[179]++;
  }

  /**
   * <p>Identifies all references between global names.
   *
   * <p>A reference from a name <code>f</code> to a name <code>g</code> means
   * that if the name <code>f</code> must be defined, then the name
   * <code>g</code> must also be defined. This would be the case if, for
   * example, <code>f</code> were a function that called <code>g</code>.
   */
  private class FindReferences implements Callback {
    Set<Node> nodesToKeep;
    FindReferences() {
      nodesToKeep = Sets.newHashSet();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[180]++;
    }

    private void addAllChildren(Node n) {
      nodesToKeep.add(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[181]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[182]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[7]++;


int CodeCoverConditionCoverageHelper_C42;
      for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false);
           child = child.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[7]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[8]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[9]++;
}
        addAllChildren(child);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[183]++;
      }
    }

    private void addSimplifiedChildren(Node n) {
      NodeTraversal.traverse(
          compiler, n,
          new GatherSideEffectSubexpressionsCallback(
              compiler, new NodeAccumulator()));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[184]++;
    }

    private void addSimplifiedExpression(Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[185]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[91]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[186]++;
        Node value = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[187]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[93]++;
          addSimplifiedChildren(value);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[188]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[94]++;}

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[92]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[189]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (128)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C45 |= (32)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((parent.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 4) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 4) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[95]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[190]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[10]++;


        for (Node child : n.children()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[10]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[11]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[12]++;
}
          addSimplifiedChildren(child);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[191]++;
        }

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[96]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[192]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[97]++;
        addSimplifiedChildren(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[193]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[98]++;
        addAllChildren(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[194]++;
      }
}
}
    }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[195]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[99]++;
        return true;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[100]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[196]++;
int CodeCoverConditionCoverageHelper_C48;

      // Gather the list of nodes that either have side effects, are
      // arguments to function calls with side effects or are used in
      // control structure predicates.  These names are always
      // referenced when the enclosing function is called.
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((n.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[101]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[197]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[103]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[198]++;
          Node decl = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[199]++;
          Node pred = decl.getNext();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[200]++;
          Node step = pred.getNext();
          addSimplifiedExpression(decl, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[201]++;
          addSimplifiedExpression(pred, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[202]++;
          addSimplifiedExpression(step, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[203]++;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[104]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[204]++; // n.getChildCount() == 3
          Node decl = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[205]++;
          Node iter = decl.getNext();
          addAllChildren(decl);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[206]++;
          addAllChildren(iter);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[207]++;
        }

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[102]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[208]++;
int CodeCoverConditionCoverageHelper_C50;

      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (128)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((parent.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((parent.isThrow()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 4) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 4) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[105]++;
        addSimplifiedExpression(n, parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[209]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[106]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[210]++;
int CodeCoverConditionCoverageHelper_C51;

      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C51 |= (2048)) == 0 || true) &&
 ((parent.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (512)) == 0 || true) &&
 ((parent.isWhile()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (128)) == 0 || true) &&
 ((parent.isWith()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (32)) == 0 || true) &&
 ((parent.isSwitch()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((parent.isCase()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 6) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 6) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[107]++;
        addAllChildren(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[211]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[108]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[212]++;
int CodeCoverConditionCoverageHelper_C52;

      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((parent.isDo()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((parent.getLastChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[109]++;
        addAllChildren(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[213]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[110]++;}

      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[214]++;
int CodeCoverConditionCoverageHelper_C53;
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((NodeUtil.isGet(n)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[111]++;
        // This is not a simple or qualified name.
        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[112]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[215]++;

      NameInformation nameInfo = createNameInformation(t, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[216]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((nameInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[113]++;
        // The name is not a global name
        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[114]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[217]++;
int CodeCoverConditionCoverageHelper_C55;

      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((nameInfo.onlyAffectsClassDef) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[115]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[218]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((nameInfo.superclass != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[117]++;
          recordReference(
              nameInfo.name, nameInfo.superclass, RefType.INHERITANCE);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[219]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[118]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[220]++;

        // Make sure that we record a reference to the function that does
        // the inheritance, so that the inherits() function itself does
        // not get stripped.
        String nodeName = n.getQualifiedName();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[221]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((nodeName != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[119]++;
          recordReference(
              nameInfo.name, nodeName, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[222]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[120]++;}

        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[116]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[223]++;
int CodeCoverConditionCoverageHelper_C58;

      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (32)) == 0 || true) &&
 ((parent.isInstanceOf()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((parent.getLastChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((// Don't cover GETELEMs with a global root node.
          n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 3) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 3) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[121]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[224]++;
        JsName checkedClass = getName(nameInfo.name, true);
        refNodes.add(new InstanceOfCheckNode(checkedClass, n));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[225]++;
        checkedClass.hasInstanceOfReference = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[226]++;
        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[122]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[227]++;

      // Determine which name might be potentially referring to this one by
      // looking up the nearest enclosing dependency scope. It's unnecessary to
      // determine all enclosing dependency scopes because this callback should
      // create a chain of references between them.
      List<NameInformation> referers = getDependencyScope(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[228]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((referers.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[123]++;
        maybeRecordReferenceOrAlias(t, n, parent, nameInfo, null);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[229]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[124]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[230]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[13]++;


        for (NameInformation referring : referers) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[13]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[14]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[15]++;
}
          maybeRecordReferenceOrAlias(t, n, parent, nameInfo, referring);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[231]++;
        }
        recordAliases(referers);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[232]++;
      }
    }

    private void maybeRecordReferenceOrAlias(
        NodeTraversal t, Node n, Node parent,
        NameInformation nameInfo, NameInformation referring) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[233]++;
      String referringName = "";
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[234]++;
int CodeCoverConditionCoverageHelper_C60;
      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((referring != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[125]++;
        referringName = referring.isPrototype
                      ? referring.prototypeClass
                      : referring.name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[235]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[126]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[236]++;

      String name = nameInfo.name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[237]++;
int CodeCoverConditionCoverageHelper_C61;

      // A value whose result is the return value of a function call
      // can be an alias to global object.
      // Here we add a alias to the general "global" object
      // to act as a placeholder for the actual (unnamed) value.
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((maybeHiddenAlias(n)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[127]++;
        recordAlias(name, WINDOW);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[238]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[128]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[239]++;
int CodeCoverConditionCoverageHelper_C62;

      // An externally referenceable name must always be defined, so we add a
      // reference to it from the global scope (a.k.a. window).
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((nameInfo.isExternallyReferenceable) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[129]++;
        recordReference(WINDOW, name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[240]++;
        maybeRecordAlias(name, parent, referring, referringName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[241]++;
        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[130]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[242]++;
int CodeCoverConditionCoverageHelper_C63;

      // An assignment implies a reference from the enclosing dependency scope.
      // For example, foo references bar in: function foo() {bar=5}.
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[131]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[243]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((referring != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[133]++;
          recordReference(referringName, name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[244]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[134]++;}
        return;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[132]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[245]++;
int CodeCoverConditionCoverageHelper_C65;

      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((nodesToKeep.contains(n)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[135]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[246]++;
        List<NameInformation> functionScopes =
            getEnclosingFunctionDependencyScope(t);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[247]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((functionScopes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[137]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[248]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[16]++;


          for (NameInformation functionScope : functionScopes) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[16]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[17]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[18]++;
}
            recordReference(functionScope.name, name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[249]++;
          }

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[138]++;
          recordReference(WINDOW, name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[250]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[251]++;
int CodeCoverConditionCoverageHelper_C67;
          if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((referring != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[139]++;
            maybeRecordAlias(name, parent, referring, referringName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[252]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[140]++;}
        }

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[136]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[253]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((referring != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[141]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[254]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((maybeRecordAlias(name, parent, referring, referringName)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[143]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[255]++;
          RefType depType = referring.onlyAffectsClassDef ?
              RefType.INHERITANCE : RefType.REGULAR;
          recordReference(referringName, name, depType);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[256]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[144]++;}

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[142]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[257]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[19]++;


        // No named dependency scope found.  Unfortunately that might
        // mean that the expression is a child of an function expression
        // or assignment with a complex lhs.  In those cases,
        // protect this node by creating a reference to WINDOW.
        for (Node ancestor : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[19]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[20]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[21]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[258]++;
int CodeCoverConditionCoverageHelper_C70;
          if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(ancestor)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((ancestor.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[145]++;
            recordReference(WINDOW, name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[259]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[260]++;
            break;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[146]++;}
        }
      }
}
    }

    private void recordAliases(List<NameInformation> referers) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[261]++;
      int size = referers.size();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[262]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[22]++;


int CodeCoverConditionCoverageHelper_C71;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[22]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[23]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[24]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[263]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[25]++;


int CodeCoverConditionCoverageHelper_C72;
        for (int j = i + 1;(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((j < size) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[25]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[26]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[27]++;
}
          recordAlias(referers.get(i).name, referers.get(j).name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[264]++;
          recordAlias(referers.get(j).name, referers.get(i).name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[265]++;
        }
      }
    }

    /**
     * A value whose result is the return value of a function call
     * can be an alias to global object. The dependency on the call target will
     * prevent the removal of the function and its dependent values, but won't
     * prevent the alias' removal.
     */
    private boolean maybeHiddenAlias(Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[266]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[267]++;
int CodeCoverConditionCoverageHelper_C73;
      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[147]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[268]++;
        Node rhs = (parent.isVar())
            ? n.getFirstChild() : parent.getLastChild();
        return (rhs != null && !NodeUtil.evaluatesToLocalValue(
            rhs, NON_LOCAL_RESULT_PREDICATE));

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[148]++;}
      return false;
    }

    /**
     * @return Whether the alias was recorded.
     */
    private boolean maybeRecordAlias(
        String name, Node parent,
        NameInformation referring, String referringName) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[269]++;
      // A common type of reference is
      // function F() {}
      // F.prototype.bar = goog.nullFunction;
      //
      // In this specific case, we do not want a reference to goog.nullFunction
      // to preserve F.
      //
      // In the general case, the user could do something like
      // function F() {}
      // F.prototype.bar = goog.nullFunction;
      // F.prototype.bar.baz = 3;
      // where it would not be safe to remove F.
      //
      // So we do not treat this alias as a backdoor for people to mutate the
      // original object. We think that this heuristic will always be
      // OK in real code.
      boolean isPrototypePropAssignment =
          parent.isAssign()
          && NodeUtil.isPrototypeProperty(parent.getFirstChild());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[270]++;
int CodeCoverConditionCoverageHelper_C74;

      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C74 |= (512)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C74 |= (128)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (64)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C74 |= (32)) == 0 || true) &&
 ((isPrototypePropAssignment) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((referring != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((scopes.get(parent).contains(referring)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 5) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 5) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[149]++;
        recordAlias(referringName, name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[271]++;
        return true;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[150]++;}
      return false;
    }

    /**
     * Helper class that gathers the list of nodes that would be left
     * behind after simplification.
     */
    private class NodeAccumulator
        implements SideEffectAccumulator {

      @Override
      public boolean classDefiningCallsHaveSideEffects() {
        return false;
      }

      @Override
      public void keepSubTree(Node original) {
        addAllChildren(original);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[272]++;
      }

      @Override
      public void keepSimplifiedShortCircuitExpression(Node original) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[273]++;
        Node condition = original.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[274]++;
        Node thenBranch = condition.getNext();
        addAllChildren(condition);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[275]++;
        addSimplifiedChildren(thenBranch);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[276]++;
      }

      @Override
      public void keepSimplifiedHookExpression(Node hook,
                                               boolean thenHasSideEffects,
                                               boolean elseHasSideEffects) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[277]++;
        Node condition = hook.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[278]++;
        Node thenBranch = condition.getNext();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[279]++;
        Node elseBranch = thenBranch.getNext();
        addAllChildren(condition);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[280]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[281]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((thenHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[151]++;
          addSimplifiedChildren(thenBranch);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[282]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[152]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[283]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((elseHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[153]++;
          addSimplifiedChildren(elseBranch);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[284]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[154]++;}
      }
    }
  }

  private class RemoveListener implements AstChangeProxy.ChangeListener {
    @Override
    public void nodeRemoved(Node n) {
      compiler.reportCodeChange();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[285]++;
    }
  }

  /**
   * Creates a name analyzer, with option to remove unreferenced variables when
   * calling process().
   *
   * The analyzer make a best guess at whether functions affect global scope
   * based on usage (no assignment of return value means that a function has
   * side effects).
   *
   * @param compiler The AbstractCompiler
   * @param removeUnreferenced If true, remove unreferenced variables during
   *        process()
   */
  NameAnalyzer(AbstractCompiler compiler, boolean removeUnreferenced) {
    this.compiler = compiler;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[286]++;
    this.removeUnreferenced = removeUnreferenced;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[287]++;
    this.globalNames = DEFAULT_GLOBAL_NAMES;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[288]++;
    this.changeProxy = new AstChangeProxy();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[289]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, externs, new ProcessExternals());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[290]++;
    NodeTraversal.traverse(compiler, root, new FindDependencyScopes());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[291]++;
    NodeTraversal.traverse(
        compiler, root, new HoistVariableAndFunctionDeclarations());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[292]++;
    NodeTraversal.traverse(compiler, root, new FindDeclarationsAndSetters());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[293]++;
    NodeTraversal.traverse(compiler, root, new FindReferences());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[294]++;

    // Create bi-directional references between parent names and their
    // descendants. This may create new names.
    referenceParentNames();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[295]++;

    // If we modify the property of an alias, make sure that modification
    // gets reflected in the original object.
    referenceAliases();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[296]++;

    calculateReferences();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[297]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[298]++;
int CodeCoverConditionCoverageHelper_C77;

    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((removeUnreferenced) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[155]++;
      removeUnreferenced();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[299]++;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[156]++;}
  }

  /**
   * Records an alias of one name to another name.
   */
  private void recordAlias(String fromName, String toName) {
    recordReference(fromName, toName, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[300]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[301]++;

    // We need to add an edge to the alias graph. The alias graph is expressed
    // implicitly as a set of connected components, called AliasSets.
    //
    // There are three possibilities:
    // 1) Neither name is part of a connected component. Create a new one.
    // 2) Exactly one name is part of a connected component. Merge the new
    //    name into the component.
    // 3) The two names are already part of connected components. Merge
    //    those components together.
    AliasSet toNameAliasSet = aliases.get(toName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[302]++;
    AliasSet fromNameAliasSet = aliases.get(fromName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[303]++;
    AliasSet resultSet = null;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[304]++;
int CodeCoverConditionCoverageHelper_C78;
    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((toNameAliasSet == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((fromNameAliasSet == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[157]++;
      resultSet = new AliasSet(toName, fromName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[305]++;

    } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[158]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[306]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((toNameAliasSet != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((fromNameAliasSet != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[159]++;
      resultSet = toNameAliasSet;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[307]++;
      resultSet.names.addAll(fromNameAliasSet.names);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[308]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[309]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[28]++;


      for (String name : fromNameAliasSet.names) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[28]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[29]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[30]++;
}
        aliases.put(name, resultSet);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[310]++;
      }

    } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[160]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[311]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((toNameAliasSet != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[161]++;
      resultSet = toNameAliasSet;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[312]++;
      resultSet.names.add(fromName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[313]++;

    } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[162]++;
      resultSet = fromNameAliasSet;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[314]++;
      resultSet.names.add(toName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[315]++;
    }
}
}
    aliases.put(fromName, resultSet);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[316]++;
    aliases.put(toName, resultSet);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[317]++;
  }

  /**
   * Records a reference from one name to another name.
   */
  private void recordReference(String fromName, String toName,
                               RefType depType) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[318]++;
int CodeCoverConditionCoverageHelper_C81;
    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((fromName.equals(toName)) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[163]++;
      // Don't bother recording self-references.
      return;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[164]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[319]++;

    JsName from = getName(fromName, true);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[320]++;
    JsName to = getName(toName, true);
    referenceGraph.createNode(from);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[321]++;
    referenceGraph.createNode(to);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[322]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[323]++;
int CodeCoverConditionCoverageHelper_C82;
    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((referenceGraph.isConnectedInDirection(from, depType, to)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[165]++;
      referenceGraph.connect(from, depType, to);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[324]++;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[166]++;}
  }

  /**
   * Removes all unreferenced variables.
   */
  void removeUnreferenced() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[325]++;
    RemoveListener listener = new RemoveListener();
    changeProxy.registerListener(listener);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[326]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[327]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[31]++;



    for (RefNode refNode : refNodes) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[31]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[32]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[33]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[328]++;
      JsName name = refNode.name();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[329]++;
int CodeCoverConditionCoverageHelper_C83;
      if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((name.referenced) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((name.externallyDefined) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[167]++;
        refNode.remove();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[330]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[168]++;}
    }

    changeProxy.unregisterListener(listener);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[331]++;
  }

  /**
   * Generates an HTML report
   *
   * @return The report
   */
  String getHtmlReport() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[332]++;
    StringBuilder sb = new StringBuilder();
    sb.append("<html><body><style type=\"text/css\">"
        + "body, td, p {font-family: Arial; font-size: 83%} "
        + "ul {margin-top:2px; margin-left:0px; padding-left:1em;} "
        + "li {margin-top:3px; margin-left:24px; padding-left:0px;"
        + "padding-bottom: 4px}</style>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[333]++;
    sb.append("OVERALL STATS<ul>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[334]++;
    appendListItem(sb, "Total Names: " + countOf(TriState.BOTH, TriState.BOTH));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[335]++;
    appendListItem(sb, "Total Classes: "
        + countOf(TriState.TRUE, TriState.BOTH));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[336]++;
    appendListItem(sb, "Total Static Functions: "
        + countOf(TriState.FALSE, TriState.BOTH));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[337]++;
    appendListItem(sb, "Referenced Names: "
        + countOf(TriState.BOTH, TriState.TRUE));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[338]++;
    appendListItem(sb, "Referenced Classes: "
        + countOf(TriState.TRUE, TriState.TRUE));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[339]++;
    appendListItem(sb, "Referenced Functions: "
        + countOf(TriState.FALSE, TriState.TRUE));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[340]++;
    sb.append("</ul>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[341]++;

    sb.append("ALL NAMES<ul>\n");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[342]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[343]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[34]++;


    for (JsName node : allNames.values()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[34]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[35]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[36]++;
}
      sb.append("<li>" + nameAnchor(node.name) + "<ul>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[344]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[345]++;
int CodeCoverConditionCoverageHelper_C84;
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((node.prototypeNames.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[169]++;
        sb.append("<li>PROTOTYPES: ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[346]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[347]++;
        Iterator<String> protoIter = node.prototypeNames.iterator();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[348]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[37]++;


int CodeCoverConditionCoverageHelper_C85;
        while ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((protoIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[37]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[38]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[39]++;
}
          sb.append(protoIter.next());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[349]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[350]++;
int CodeCoverConditionCoverageHelper_C86;
          if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((protoIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[171]++;
            sb.append(", ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[351]++;

          } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[172]++;}
        }

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[170]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[352]++;
int CodeCoverConditionCoverageHelper_C87;

      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((referenceGraph.hasNode(node)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[173]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[353]++;
        List<DiGraphEdge<JsName, RefType>> refersTo =
            referenceGraph.getOutEdges(node);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[354]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((refersTo.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[175]++;
          sb.append("<li>REFERS TO: ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[355]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[356]++;
          Iterator<DiGraphEdge<JsName, RefType>> toIter = refersTo.iterator();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[357]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[40]++;


int CodeCoverConditionCoverageHelper_C89;
          while ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((toIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[40]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[41]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[42]++;
}
            sb.append(nameLink(toIter.next().getDestination().getValue().name));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[358]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[359]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((toIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[177]++;
              sb.append(", ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[360]++;

            } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[178]++;}
          }

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[176]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[361]++;

        List<DiGraphEdge<JsName, RefType>> referencedBy =
            referenceGraph.getInEdges(node);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[362]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((referencedBy.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[179]++;
          sb.append("<li>REFERENCED BY: ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[363]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[364]++;
          Iterator<DiGraphEdge<JsName, RefType>> fromIter = refersTo.iterator();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[365]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[43]++;


int CodeCoverConditionCoverageHelper_C92;
          while ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((fromIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[43]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[44]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[45]++;
}
            sb.append(
                nameLink(fromIter.next().getDestination().getValue().name));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[366]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[367]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((fromIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[181]++;
              sb.append(", ");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[368]++;

            } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[182]++;}
          }

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[180]++;}

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[174]++;}
      sb.append("</li>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[369]++;
      sb.append("</ul></li>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[370]++;
    }
    sb.append("</ul>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[371]++;
    sb.append("</body></html>");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[372]++;

    return sb.toString();
  }

  private void appendListItem(StringBuilder sb, String text) {
    sb.append("<li>" + text + "</li>\n");
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[373]++;
  }

  private String nameLink(String name) {
    return "<a href=\"#" + name + "\">" + name + "</a>";
  }

  private String nameAnchor(String name) {
    return "<a name=\"" + name + "\">" + name + "</a>";
  }

  /**
   * Looks up a {@link JsName} by name, optionally creating one if it doesn't
   * already exist.
   *
   * @param name A fully qualified name
   * @param canCreate Whether to create the object if necessary
   * @return The {@code JsName} object, or null if one can't be found and
   *   can't be created.
   */
  private JsName getName(String name, boolean canCreate) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[374]++;
int CodeCoverConditionCoverageHelper_C94;
    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((canCreate) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[183]++;
      createName(name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[375]++;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[184]++;}
    return allNames.get(name);
  }

  /**
   * Creates a {@link JsName} for the given name if it doesn't already
   * exist.
   *
   * @param name A fully qualified name
   */
  private void createName(String name) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[376]++;
    JsName jsn = allNames.get(name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[377]++;
int CodeCoverConditionCoverageHelper_C95;
    if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((jsn == null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[185]++;
      jsn = new JsName();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[378]++;
      jsn.name = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[379]++;
      allNames.put(name, jsn);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[380]++;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[186]++;}
  }

  /**
   * The NameAnalyzer algorithm works best when all objects have a canonical
   * name in the global scope. When multiple names in the global scope
   * point to the same object, things start to break down.
   *
   * For example, if we have
   * <code>
   * var a = {};
   * var b = a;
   * a.foo = 3;
   * alert(b.foo);
   * </code>
   * then a.foo and b.foo are the same name, even though NameAnalyzer doesn't
   * represent them as such.
   *
   * To handle this case, we look at all the aliases in the program.
   * If descendant properties of that alias are assigned, then we create a
   * directional reference from the original name to the alias. For example,
   * in this case, the assign to {@code a.foo} triggers a reference from
   * {@code b} to {@code a}, but NOT from a to b.
   *
   * Similarly, "instanceof" checks do not prevent the removal
   * of a unaliased name but an instanceof check on an alias can only be removed
   * if the other aliases are also removed, so we add a connection here.
   */
  private void referenceAliases() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[381]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[46]++;


    for (Map.Entry<String, AliasSet> entry : aliases.entrySet()) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[46]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[47]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[48]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[382]++;
      JsName name = getName(entry.getKey(), false);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[383]++;
int CodeCoverConditionCoverageHelper_C96;
      if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (8)) == 0 || true) &&
 ((name.hasWrittenDescendants) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((name.hasInstanceOfReference) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[187]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[384]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[49]++;


        for (String alias : entry.getValue().names) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[49]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[50]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[51]++;
}
          recordReference(alias, entry.getKey(), RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[385]++;
        }

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[188]++;}
    }
  }

  /**
   * Adds mutual references between all known global names and their parent
   * names. (e.g. between <code>a.b.c</code> and <code>a.b</code>).
   */
  private void referenceParentNames() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[386]++;
    // Duplicate set of nodes to process so we don't modify set we are
    // currently iterating over
    Set<JsName> allNamesCopy = Sets.newHashSet(allNames.values());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[387]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[52]++;



    for (JsName name : allNamesCopy) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[52]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[53]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[54]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[388]++;
      String curName = name.name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[389]++;
      JsName curJsName = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[390]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[55]++;


int CodeCoverConditionCoverageHelper_C97;
      while ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((curName.indexOf('.') != -1) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[55]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[56]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[57]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[391]++;
        String parentName = curName.substring(0, curName.lastIndexOf('.'));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[392]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((globalNames.contains(parentName)) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[189]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[393]++;

          JsName parentJsName = getName(parentName, true);

          recordReference(curJsName.name, parentJsName.name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[394]++;
          recordReference(parentJsName.name, curJsName.name, RefType.REGULAR);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[395]++;

          curJsName = parentJsName;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[396]++;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[190]++;}
        curName = parentName;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[397]++;
      }
    }
  }

  /**
   * Creates name information for the current node during a traversal.
   *
   * @param t The node traversal
   * @param n The current node
   * @return The name information, or null if the name is irrelevant to this
   *     pass
   */
  private NameInformation createNameInformation(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[398]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[399]++;
    // Build the full name and find its root node by iterating down through all
    // GETPROP/GETELEM nodes.
    String name = "";
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[400]++;
    Node rootNameNode = n;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[401]++;
    boolean bNameWasShortened = false;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[402]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[58]++;


    while (true) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[58]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[59]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[60]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[403]++;
int CodeCoverConditionCoverageHelper_C100;
      if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(rootNameNode)) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[191]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[404]++;
        Node prop = rootNameNode.getLastChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[405]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((rootNameNode.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[193]++;
          name = "." + prop.getString() + name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[406]++;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[194]++;
          // We consider the name to be "a.b" in a.b['c'] or a.b[x].d.
          bNameWasShortened = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[407]++;
          name = "";
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[408]++;
        }
        rootNameNode = rootNameNode.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[409]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[192]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[410]++;
int CodeCoverConditionCoverageHelper_C102; if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(rootNameNode)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[195]++;
        name = "." + rootNameNode.getString() + name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[411]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[412]++;

        // Check if this is an object literal assigned to something.
        Node objLit = rootNameNode.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[413]++;
        Node objLitParent = objLit.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[414]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((objLitParent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[197]++;
          // This must be the right side of the assign.
          rootNameNode = objLitParent.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[415]++;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[198]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[416]++;
int CodeCoverConditionCoverageHelper_C104; if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((objLitParent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[199]++;
          // This must be a VAR initialization.
          rootNameNode = objLitParent;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[417]++;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[200]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[418]++;
int CodeCoverConditionCoverageHelper_C105; if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((objLitParent.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[201]++;
          // This must be a object literal key initialization.
          rootNameNode = objLitParent;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[419]++;

        } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[202]++;
          return null;
        }
}
}

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[196]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[420]++;
        break;
      }
}
    }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[421]++;
int CodeCoverConditionCoverageHelper_C106;

    // Check whether this is a class-defining call. Classes may only be defined
    // in the global scope.
    if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (8)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[203]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[422]++;
      CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[423]++;
      SubclassRelationship classes = convention.getClassesDefinedByCall(parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[424]++;
int CodeCoverConditionCoverageHelper_C107;
      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((classes != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[205]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[425]++;
        NameInformation nameInfo = new NameInformation();
        nameInfo.name = classes.subclassName;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[426]++;
        nameInfo.onlyAffectsClassDef = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[427]++;
        nameInfo.superclass = classes.superclassName;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[428]++;
        return nameInfo;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[206]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[429]++;

      String singletonGetterClass =
          convention.getSingletonGetterClassName(parent);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[430]++;
int CodeCoverConditionCoverageHelper_C108;
      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((singletonGetterClass != null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[207]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[431]++;
        NameInformation nameInfo = new NameInformation();
        nameInfo.name = singletonGetterClass;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[432]++;
        nameInfo.onlyAffectsClassDef = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[433]++;
        return nameInfo;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[208]++;}

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[204]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[434]++;

    switch (rootNameNode.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[209]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[435]++;
int CodeCoverConditionCoverageHelper_C109;
        // Check whether this is an assignment to a prototype property
        // of an object defined in the global scope.
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C109 |= (128)) == 0 || true) &&
 ((bNameWasShortened) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C109 |= (32)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 (("prototype".equals(n.getLastChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 4) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 4) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[210]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[436]++;
int CodeCoverConditionCoverageHelper_C110;
          if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((createNameInformation(t, n.getFirstChild()) != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[212]++;
            name = rootNameNode.getString() + name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[437]++;
            name = name.substring(0, name.length() - PROTOTYPE_SUFFIX_LEN);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[438]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[439]++;
            NameInformation nameInfo = new NameInformation();
            nameInfo.name = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[440]++;
            return nameInfo;

          } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[213]++;
            return null;
          }

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[211]++;}
        return createNameInformation(
            rootNameNode.getString() + name, t.getScope(), rootNameNode);
      case Token.THIS:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[214]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[441]++;
int CodeCoverConditionCoverageHelper_C111;
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[215]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[442]++;
          NameInformation nameInfo = new NameInformation();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[443]++;
int CodeCoverConditionCoverageHelper_C112;
          if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((name.indexOf('.') == 0) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[217]++;
            nameInfo.name = name.substring(1);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[444]++;
  // strip leading "."
          } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[218]++;
            nameInfo.name = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[445]++;
          }
          nameInfo.isExternallyReferenceable = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[446]++;
          return nameInfo;

        } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[216]++;}
        return null;
      default:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[219]++;
        return null;
    }
  }

  /**
   * Creates name information for a particular qualified name that occurs in a
   * particular scope.
   *
   * @param name A qualified name (e.g. "x" or "a.b.c")
   * @param scope The scope in which {@code name} occurs
   * @param rootNameNode The NAME node for the first token of {@code name}
   * @return The name information, or null if the name is irrelevant to this
   *     pass
   */
  private NameInformation createNameInformation(
      String name, Scope scope, Node rootNameNode) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[447]++;
    // Check the scope. Currently we're only looking at globally scoped vars.
    String rootName = rootNameNode.getString();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[448]++;
    Var v = scope.getVar(rootName);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[449]++;
    boolean isExtern = (v == null && externalNames.contains(rootName));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[450]++;
    boolean isGlobalRef = (v != null && v.isGlobal()) || isExtern ||
        rootName.equals(WINDOW);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[451]++;
int CodeCoverConditionCoverageHelper_C113;
    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((isGlobalRef) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[220]++;
      return null;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[221]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[452]++;

    NameInformation nameInfo = new NameInformation();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[453]++;

    // If a prototype property or method, fill in prototype information.
    int idx = name.indexOf(PROTOTYPE_SUBSTRING);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[454]++;
int CodeCoverConditionCoverageHelper_C114;
    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((idx != -1) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[222]++;
      nameInfo.isPrototype = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[455]++;
      nameInfo.prototypeClass = name.substring(0, idx);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[456]++;
      nameInfo.prototypeProperty = name.substring(
          idx + PROTOTYPE_SUBSTRING_LEN);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[457]++;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[223]++;}

    nameInfo.name = name;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[458]++;
    nameInfo.isExternallyReferenceable =
        isExtern || isExternallyReferenceable(scope, name);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[459]++;
    return nameInfo;
  }

  /**
   * Checks whether a name can be referenced outside of the compiled code.
   * These names will be the root of dependency trees.
   *
   * @param scope The current variable scope
   * @param name The name
   * @return True if can be referenced outside
   */
  private boolean isExternallyReferenceable(Scope scope, String name) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[460]++;
int CodeCoverConditionCoverageHelper_C115;
    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(name)) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[224]++;
      return true;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[225]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[461]++;
int CodeCoverConditionCoverageHelper_C116;
    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((scope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[226]++;
      return false;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[227]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[462]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[61]++;


    for (String s : globalNames) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[61]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[62]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[63]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[463]++;
int CodeCoverConditionCoverageHelper_C117;
      if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((name.startsWith(s)) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[228]++;
        return true;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[229]++;}
    }
    return false;
  }

  /**
   * Gets the nearest enclosing dependency scope, or null if there isn't one.
   */
  private List<NameInformation> getDependencyScope(Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[464]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[64]++;


    for (Node node : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[64]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[65]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[66]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[465]++;
      List<NameInformation> refs = scopes.get(node);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[466]++;
int CodeCoverConditionCoverageHelper_C118;
      if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((refs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[230]++;
        return refs;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[231]++;}
    }

    return Collections.emptyList();
  }

  /**
   * Get dependency scope defined by the enclosing function, or null.
   * If enclosing function is a function expression, determine scope based on
   * its parent if the parent node is a variable declaration or
   * assignment.
   */
  private List<NameInformation> getEnclosingFunctionDependencyScope(
      NodeTraversal t) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[467]++;
    Node function = t.getEnclosingFunction();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[468]++;
int CodeCoverConditionCoverageHelper_C119;
    if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((function == null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[232]++;
      return Collections.emptyList();

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[233]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[469]++;

    List<NameInformation> refs = scopes.get(function);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[470]++;
int CodeCoverConditionCoverageHelper_C120;
    if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((refs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[234]++;
      return refs;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[235]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[471]++;

    // Function expression.  try to get a name from the parent var
    // declaration or assignment.
    Node parent = function.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[472]++;
int CodeCoverConditionCoverageHelper_C121;
    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[236]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[473]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[67]++;


int CodeCoverConditionCoverageHelper_C122;
      // Account for functions defined in the form:
      //   var a = cond ? function a() {} : function b() {};
      while ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((parent.isHook()) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[67]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[68]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[69]++;
}
        parent = parent.getParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[474]++;
      }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[475]++;
int CodeCoverConditionCoverageHelper_C123;

      if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[238]++;
        return scopes.get(parent);

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[239]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[476]++;
int CodeCoverConditionCoverageHelper_C124;

      if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[240]++;
        return scopes.get(parent);

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[241]++;}

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[237]++;}

    return Collections.emptyList();
  }

  /**
   * Propagate "referenced" property down the graph.
   */
  private void calculateReferences() {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[477]++;
    JsName window = getName(WINDOW, true);
    window.referenced = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[478]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[479]++;
    JsName function = getName(FUNCTION, true);
    function.referenced = true;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[480]++;

    // Propagate "referenced" property to a fixed point.
    FixedPointGraphTraversal.newTraversal(new ReferencePropagationCallback())
        .computeFixedPoint(referenceGraph);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[481]++;
  }


  /**
   * Enum for saying a value can be true, false, or either (cleaner than using a
   * Boolean with null)
   */
  private enum TriState {
    /** If value is true */
    TRUE,
    /** If value is false */
    FALSE,
    /** If value can be true or false */
    BOTH
  }

  /**
   * Gets the count of nodes matching the criteria
   *
   * @param isClass Whether the node is a class
   * @param referenced Whether the node is referenced
   * @return Number of matches
   */
  private int countOf(TriState isClass, TriState referenced) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[482]++;
    int count = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[483]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[70]++;


    for (JsName name : allNames.values()) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[70]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[71]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[72]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[484]++;

      boolean nodeIsClass = name.prototypeNames.size() > 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[485]++;

      boolean classMatch = isClass == TriState.BOTH
          || (nodeIsClass && isClass == TriState.TRUE)
          || (!nodeIsClass && isClass == TriState.FALSE);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[486]++;

      boolean referenceMatch = referenced == TriState.BOTH
          || (name.referenced && referenced == TriState.TRUE)
          || (!name.referenced && referenced == TriState.FALSE);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[487]++;
int CodeCoverConditionCoverageHelper_C125;

      if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (32)) == 0 || true) &&
 ((classMatch) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((referenceMatch) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((name.externallyDefined) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 3) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 3) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[242]++;
        count++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[488]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[243]++;}
    }
    return count;
  }


  /**
   * Extract a list of replacement nodes to use.
   */
  private List<Node> getSideEffectNodes(Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[489]++;
    List<Node> subexpressions = Lists.newArrayList();
    NodeTraversal.traverse(
        compiler, n,
        new GatherSideEffectSubexpressionsCallback(
            compiler,
            new GetReplacementSideEffectSubexpressions(
                compiler, subexpressions)));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[490]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[491]++;

    List<Node> replacements =
        Lists.newArrayListWithExpectedSize(subexpressions.size());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[492]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[73]++;


    for (Node subexpression : subexpressions) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[73]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[74]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[75]++;
}
      replacements.add(NodeUtil.newExpr(subexpression));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[493]++;
    }
    return replacements;
  }

  /**
   * Replace n with a simpler expression, while preserving program
   * behavior.
   *
   * If the n's value is used, replace it with its RHS; otherwise
   * replace it with the subexpressions that have side effects.
   */
  private void replaceWithRhs(Node parent, Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[494]++;
int CodeCoverConditionCoverageHelper_C126;
    if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((valueConsumedByParent(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[244]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[495]++;
      // parent reads from n directly; replace it with n's rhs + lhs
      // subexpressions with side effects.
      List<Node> replacements = getRhsSubexpressions(n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[496]++;
      List<Node> newReplacements = Lists.newArrayList();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[497]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[76]++;


int CodeCoverConditionCoverageHelper_C127;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((i < replacements.size() - 1) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[76]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[77]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[78]++;
}
        newReplacements.addAll(getSideEffectNodes(replacements.get(i)));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[498]++;
      }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[499]++;
      Node valueExpr = replacements.get(replacements.size() - 1);
      valueExpr.detachFromParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[500]++;
      newReplacements.add(valueExpr);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[501]++;
      changeProxy.replaceWith(
          parent, n, collapseReplacements(newReplacements));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[502]++;

    } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[245]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[503]++;
int CodeCoverConditionCoverageHelper_C128; if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[246]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[504]++;
      // assignment appears in a RHS expression.  we have already
      // considered names in the assignment's RHS as being referenced;
      // replace the assignment with its RHS.
      // TODO(user) make the pass smarter about these cases and/or run
      // this pass and RemoveConstantExpressions together in a loop.
      Node replacement = n.getLastChild();
      replacement.detachFromParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[505]++;
      changeProxy.replaceWith(parent, n, replacement);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[506]++;

    } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[247]++;
      replaceTopLevelExpressionWithRhs(parent, n);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[507]++;
    }
}
  }

  /**
   * Simplify a toplevel expression, while preserving program
   * behavior.
   */
  private void replaceTopLevelExpressionWithRhs(Node parent, Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[508]++;
    // validate inputs
    switch (parent.getType()) {
      case Token.BLOCK:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[248]++;
      case Token.SCRIPT:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[249]++;
      case Token.FOR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[250]++;
      case Token.LABEL:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[251]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[509]++;
        break;
      default:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[252]++;
        throw new IllegalArgumentException(
            "Unsupported parent node type in replaceWithRhs " +
            Token.name(parent.getType()));
    }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[510]++;

    switch (n.getType()) {
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[253]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[254]++;
      case Token.VAR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[255]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[511]++;
        break;
      case Token.ASSIGN:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[256]++;
        Preconditions.checkArgument(parent.isFor(),
            "Unsupported assignment in replaceWithRhs. parent: %s",
            Token.name(parent.getType()));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[512]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[513]++;
        break;
      default:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[257]++;
        throw new IllegalArgumentException(
            "Unsupported node type in replaceWithRhs " +
            Token.name(n.getType()));
    }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[514]++;

    // gather replacements
    List<Node> replacements = Lists.newArrayList();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[515]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[79]++;


    for (Node rhs : getRhsSubexpressions(n)) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[79]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[80]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[81]++;
}
      replacements.addAll(getSideEffectNodes(rhs));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[516]++;
    }
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[517]++;
int CodeCoverConditionCoverageHelper_C129;

    if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[258]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[518]++;
int CodeCoverConditionCoverageHelper_C130;
      // tweak replacements array s.t. it is a single expression node.
      if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((replacements.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[260]++;
        replacements.add(IR.empty());
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[519]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[261]++;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[520]++;
        Node expr = collapseReplacements(replacements);
        replacements.clear();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[521]++;
        replacements.add(expr);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[522]++;
      }

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[259]++;}

    changeProxy.replaceWith(parent, n, replacements);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[523]++;
  }

  /**
   * Determine if the parent reads the value of a child expression
   * directly.  This is true children used in predicates, RETURN
   * statements and, RHS of variable declarations and assignments.
   *
   * In the case of:
   * if (a) b else c
   *
   * This method returns true for "a", and false for "b" and "c": the
   * IF expression does something special based on "a"'s value.  "b"
   * and "c" are effectively outputs.  Same logic applies to FOR,
   * WHILE and DO loop predicates.  AND/OR/HOOK expressions are
   * syntactic sugar for IF statements; therefore this method returns
   * true for the predicate and false otherwise.
   */
  private boolean valueConsumedByParent(Node n, Node parent) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[524]++;
int CodeCoverConditionCoverageHelper_C131;
    if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[262]++;
      return parent.getLastChild() == n;

    } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[263]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[525]++;

    switch (parent.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[264]++;
      case Token.RETURN:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[265]++;
        return true;
      case Token.AND:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[266]++;
      case Token.OR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[267]++;
      case Token.HOOK:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[268]++;
        return parent.getFirstChild() == n;
      case Token.FOR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[269]++;
        return parent.getFirstChild().getNext() == n;
      case Token.IF:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[270]++;
      case Token.WHILE:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[271]++;
        return parent.getFirstChild() == n;
      case Token.DO:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[272]++;
        return parent.getLastChild() == n;
      default:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[273]++;
        return false;
    }
  }

  /**
   * Merge a list of nodes into a single expression.  The value of the
   * new expression is determined by the last expression in the list.
   */
  private Node collapseReplacements(List<Node> replacements) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[526]++;
    Node expr = null;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[527]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[82]++;


    for (Node rep : replacements) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[82]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[83]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[84]++;
}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[528]++;
int CodeCoverConditionCoverageHelper_C132;
      if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((rep.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[274]++;
        rep = rep.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[529]++;
        rep.detachFromParent();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[530]++;

      } else {
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[275]++;}
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[531]++;
int CodeCoverConditionCoverageHelper_C133;

      if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((expr == null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[276]++;
        expr = rep;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[532]++;

      } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[277]++;
        expr = IR.comma(expr, rep);
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[533]++;
      }
    }

    return expr;
  }

  /**
   * Extract a list of subexpressions that act as right hand sides.
   */
  private List<Node> getRhsSubexpressions(Node n) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[534]++;
    switch (n.getType()) {
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[278]++;
        // process body
        return getRhsSubexpressions(n.getFirstChild());
      case Token.FUNCTION:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[279]++;
        // function nodes have no RHS
        return Collections.emptyList();
      case Token.NAME:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[280]++;
        {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[535]++;
          // parent is a var node.  RHS is the first child
          Node rhs = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[536]++;
int CodeCoverConditionCoverageHelper_C134;
          if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((rhs != null) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[281]++;
            return Lists.newArrayList(rhs);

          } else {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[282]++;
            return Collections.emptyList();
          }
        }
      case Token.ASSIGN:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[283]++;
        {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[537]++;
          // add LHS and RHS expressions - LHS may be a complex expression
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[538]++;
          Node rhs = lhs.getNext();
          return Lists.newArrayList(lhs, rhs);
        }
      case Token.VAR:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[284]++;
        {
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[539]++;
          // recurse on all children
          List<Node> nodes = Lists.newArrayList();
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[540]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[85]++;


          for (Node child : n.children()) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[85]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[86]--;
  CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.loops[87]++;
}
            nodes.addAll(getRhsSubexpressions(child));
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.statements[541]++;
          }
          return nodes;
        }
      default:
CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1.branches[285]++;
        throw new IllegalArgumentException("AstChangeProxy::getRhs " + n);
    }
  }
}

class CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1 ());
  }
    public static long[] statements = new long[542];
    public static long[] branches = new long[286];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[135];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NameAnalyzer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,3,2,1,1,1,3,3,2,3,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,2,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,2,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 134; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[88];

  public CodeCoverCoverageCounter$wzx6qbquvp1dkh835za5x2k0o1 () {
    super("com.google.javascript.jscomp.NameAnalyzer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 541; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 285; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 134; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 87; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameAnalyzer.java");
      for (int i = 1; i <= 541; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 285; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 134; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 29; i++) {
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

