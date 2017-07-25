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

import static com.google.javascript.rhino.jstype.JSTypeNative.GLOBAL_THIS;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.StaticReference;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.StaticSourceFile;
import com.google.javascript.rhino.jstype.StaticSymbolTable;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Builds a global namespace of all the objects and their properties in
 * the global scope. Also builds an index of all the references to those names.
 *
 */
class GlobalNamespace
    implements StaticScope<JSType>,
    StaticSymbolTable<GlobalNamespace.Name, GlobalNamespace.Ref> {
  static {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.ping();
  }


  private AbstractCompiler compiler;
  private final Node root;
  private final Node externsRoot;
  private boolean inExterns;
  private Scope externsScope;
  private boolean generated = false;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[1]++;
  }

  /**
   * Each reference has an index in post-order.
   * Notice that some nodes are represented by 2 Ref objects, so
   * this index is not necessarily unique.
   */
  private int currentPreOrderIndex = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[2]++;
  }

  /** Global namespace tree */
  private List<Name> globalNames = new ArrayList<Name>();
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[3]++;
  }

  /** Maps names (e.g. "a.b.c") to nodes in the global namespace tree */
  private Map<String, Name> nameMap = new HashMap<String, Name>();
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[4]++;
  }

  /**
   * Creates an instance that may emit warnings when building the namespace.
   *
   * @param compiler The AbstractCompiler, for reporting code changes
   * @param root The root of the rest of the code to build a namespace for.
   */
  GlobalNamespace(AbstractCompiler compiler, Node root) {
    this(compiler, null, root);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[5]++;
  }

  /**
   * Creates an instance that may emit warnings when building the namespace.
   *
   * @param compiler The AbstractCompiler, for reporting code changes
   * @param externsRoot The root of the externs to build a namespace for. If
   *     this is null, externs and properties defined on extern types will not
   *     be included in the global namespace.  If non-null, it allows
   *     user-defined function on extern types to be included in the global
   *     namespace.  E.g. String.foo.
   * @param root The root of the rest of the code to build a namespace for.
   */
  GlobalNamespace(AbstractCompiler compiler, Node externsRoot, Node root) {
    this.compiler = compiler;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[6]++;
    this.externsRoot = externsRoot;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[7]++;
    this.root = root;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[8]++;
  }

  boolean hasExternsRoot() {
    return externsRoot != null;
  }

  @Override
  public Node getRootNode() {
    return root.getParent();
  }

  @Override
  public StaticScope<JSType> getParentScope() {
    return null;
  }

  @Override
  public Name getSlot(String name) {
    return getOwnSlot(name);
  }

  @Override
  public Name getOwnSlot(String name) {
    ensureGenerated();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[9]++;
    return nameMap.get(name);
  }

  @Override
  public JSType getTypeOfThis() {
    return compiler.getTypeRegistry().getNativeObjectType(GLOBAL_THIS);
  }

  @Override
  public Iterable<Ref> getReferences(Name slot) {
    ensureGenerated();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[10]++;
    return Collections.unmodifiableList(slot.getRefs());
  }

  @Override
  public StaticScope<JSType> getScope(Name slot) {
    return this;
  }

  @Override
  public Iterable<Name> getAllSymbols() {
    ensureGenerated();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[11]++;
    return Collections.unmodifiableCollection(getNameIndex().values());
  }

  private void ensureGenerated() {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((generated) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[1]++;
      process();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[13]++;

    } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[2]++;}
  }

  /**
   * Gets a list of the roots of the forest of the global names, where the
   * roots are the top-level names.
   */
  List<Name> getNameForest() {
    ensureGenerated();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[14]++;
    return globalNames;
  }

  /**
   * Gets an index of all the global names, indexed by full qualified name
   * (as in "a", "a.b.c", etc.).
   */
  Map<String, Name> getNameIndex() {
    ensureGenerated();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[15]++;
    return nameMap;
  }

  /**
   * If the client adds new nodes to the AST, scan these new nodes
   * to see if they've added any references to the global namespace.
   * @param scope The scope to scan.
   * @param newNodes New nodes to check.
   */
  void scanNewNodes(Scope scope, Set<Node> newNodes) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[16]++;
    NodeTraversal t = new NodeTraversal(compiler,
        new BuildGlobalNamespace(new NodeFilter(newNodes)));
    t.traverseAtScope(scope);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[17]++;
  }

  /**
   * A filter that looks for qualified names that contain one of the nodes
   * in the given set.
   */
  private static class NodeFilter implements Predicate<Node> {
    private final Set<Node> newNodes;

    NodeFilter(Set<Node> newNodes) {
      this.newNodes = newNodes;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[18]++;
    }

    @Override
    public boolean apply(Node n) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[3]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[4]++;}

      Node current;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[20]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
      for (current = n;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((current.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false);
           current = current.getFirstChild()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[1]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[2]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[3]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((newNodes.contains(current)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[5]++;
          return true;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[6]++;}
      }

      return current.isName() && newNodes.contains(current);
    }
  }

  /**
   * Builds the namespace lazily.
   */
  private void process() {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((externsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[7]++;
      inExterns = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[23]++;
      NodeTraversal.traverse(compiler, externsRoot, new BuildGlobalNamespace());
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[24]++;

    } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[8]++;}
    inExterns = false;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[25]++;

    NodeTraversal.traverse(compiler, root, new BuildGlobalNamespace());
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[26]++;
    generated = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[27]++;
  }

  /**
   * Determines whether a name reference in a particular scope is a global name
   * reference.
   *
   * @param name A variable or property name (e.g. "a" or "a.b.c.d")
   * @param s The scope in which the name is referenced
   * @return Whether the name reference is a global name reference
   */
  private boolean isGlobalNameReference(String name, Scope s) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[28]++;
    String topVarName = getTopVarName(name);
    return isGlobalVarReference(topVarName, s);
  }

  /**
   * Gets the top variable name from a possibly namespaced name.
   *
   * @param name A variable or qualified property name (e.g. "a" or "a.b.c.d")
   * @return The top variable name (e.g. "a")
   */
  private String getTopVarName(String name) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[29]++;
    int firstDotIndex = name.indexOf('.');
    return firstDotIndex == -1 ? name : name.substring(0, firstDotIndex);
  }

  /**
   * Determines whether a variable name reference in a particular scope is a
   * global variable reference.
   *
   * @param name A variable name (e.g. "a")
   * @param s The scope in which the name is referenced
   * @return Whether the name reference is a global variable reference
   */
  private boolean isGlobalVarReference(String name, Scope s) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[30]++;
    Scope.Var v = s.getVar(name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((externsScope != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[9]++;
      v = externsScope.getVar(name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[32]++;

    } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[10]++;}
    return v != null && !v.isLocal();
  }

  /**
   * Gets whether a scope is the global scope.
   *
   * @param s A scope
   * @return Whether the scope is the global scope
   */
  private boolean isGlobalScope(Scope s) {
    return s.getParent() == null;
  }

  // -------------------------------------------------------------------------

  /**
   * Builds a tree representation of the global namespace. Omits prototypes.
   */
  private class BuildGlobalNamespace implements NodeTraversal.Callback {

    private final Predicate<Node> nodeFilter;

    BuildGlobalNamespace() {
      this(null);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[33]++;
    }

    /**
     * Builds a global namespace, but only visits nodes that match the
     * given filter.
     */
    BuildGlobalNamespace(Predicate<Node> nodeFilter) {
      this.nodeFilter = nodeFilter;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[34]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {}

    /** Collect the references in pre-order. */
    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      collect(t, n, parent);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[35]++;
      return true;
    }

    public void collect(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((nodeFilter != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nodeFilter.apply(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[12]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;

      // If we are traversing the externs, then we save a pointer to the scope
      // generated by them, so that we can do lookups in it later.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((externsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n == externsRoot) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[13]++;
        externsScope = t.getScope();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[38]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[14]++;}

      String name;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[39]++;
      boolean isSet = false;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[40]++;
      Name.Type type = Name.Type.OTHER;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[41]++;
      boolean isPropAssign = false;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[42]++;

      switch (n.getType()) {
        case Token.GETTER_DEF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[15]++;
        case Token.SETTER_DEF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[16]++;
        case Token.STRING_KEY:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[17]++;
          // This may be a key in an object literal declaration.
          name = null;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[43]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[18]++;
            name = getNameForObjLitKey(n);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[45]++;

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[19]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[20]++; return;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[21]++;}
          isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[47]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[48]++;
          switch (n.getType()) {
            case Token.STRING_KEY:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[22]++;
              type = getValueType(n.getFirstChild());
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[49]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[50]++;
              break;
            case Token.GETTER_DEF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[23]++;
              type = Name.Type.GET;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[51]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[52]++;
              break;
            case Token.SETTER_DEF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[24]++;
              type = Name.Type.SET;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[53]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[54]++;
              break;
            default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[25]++;
              throw new IllegalStateException("unexpected:" + n);
          }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[55]++;
          break;
        case Token.NAME:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[26]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
          // This may be a variable get or set.
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[27]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[57]++;
            switch (parent.getType()) {
              case Token.VAR:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[29]++;
                isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[58]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[59]++;
                Node rvalue = n.getFirstChild();
                type = rvalue == null ? Name.Type.OTHER : getValueType(rvalue);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[60]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[61]++;
                break;
              case Token.ASSIGN:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[30]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[62]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[31]++;
                  isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[63]++;
                  type = getValueType(n.getNext());
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[64]++;

                } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[32]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[65]++;
                break;
              case Token.GETPROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[33]++;
                return;
              case Token.FUNCTION:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[34]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[66]++;
                Node gramps = parent.getParent();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[67]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((gramps == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(parent)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[35]++; return;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[36]++;}
                isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[68]++;
                type = Name.Type.FUNCTION;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[69]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[70]++;
                break;
              case Token.INC:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[37]++;
              case Token.DEC:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[38]++;
                isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[71]++;
                type = Name.Type.OTHER;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[72]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[73]++;
                break;
              default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[39]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[40]++;
                  isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[75]++;
                  type = Name.Type.OTHER;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[76]++;

                } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[41]++;}
            }

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[28]++;}
          name = n.getString();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[77]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[78]++;
          break;
        case Token.GETPROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[42]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[79]++;
int CodeCoverConditionCoverageHelper_C15;
          // This may be a namespaced name get or set.
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[43]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[80]++;
            switch (parent.getType()) {
              case Token.ASSIGN:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[45]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[81]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[46]++;
                  isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[82]++;
                  type = getValueType(n.getNext());
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[83]++;
                  isPropAssign = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[84]++;

                } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[47]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[85]++;
                break;
              case Token.INC:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[48]++;
              case Token.DEC:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[49]++;
                isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[86]++;
                type = Name.Type.OTHER;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[87]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[88]++;
                break;
              case Token.GETPROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[50]++;
                return;
              default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[51]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[89]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[52]++;
                  isSet = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[90]++;
                  type = Name.Type.OTHER;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[91]++;

                } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[53]++;}
            }

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[44]++;}
          name = n.getQualifiedName();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[92]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[93]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[54]++; return;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[55]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[94]++;
          break;
        default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[56]++;
          return;
      }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[95]++;

      // We are only interested in global names.
      Scope scope = t.getScope();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[96]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isGlobalNameReference(name, scope)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[57]++;
        return;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[58]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[97]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isSet) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[59]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[98]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((isGlobalScope(scope)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[61]++;
          handleSetFromGlobal(t, n, parent, name, isPropAssign, type);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[99]++;

        } else {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[62]++;
          handleSetFromLocal(t, n, parent, name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[100]++;
        }

      } else {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[60]++;
        handleGet(t, n, parent, name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[101]++;
      }
    }

    /**
     * Gets the fully qualified name corresponding to an object literal key,
     * as long as it and its prefix property names are valid JavaScript
     * identifiers. The object literal may be nested inside of other object
     * literals.
     *
     * For example, if called with node {@code n} representing "z" in any of
     * the following expressions, the result would be "w.x.y.z":
     * <code> var w = {x: {y: {z: 0}}}; </code>
     * <code> w.x = {y: {z: 0}}; </code>
     * <code> w.x.y = {'a': 0, 'z': 0}; </code>
     *
     * @param n A child of an OBJLIT node
     * @return The global name, or null if {@code n} doesn't correspond to the
     *   key of an object literal that can be named
     */
    String getNameForObjLitKey(Node n) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[102]++;
      Node parent = n.getParent();
      Preconditions.checkState(parent.isObjectLit());
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[103]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[104]++;

      Node gramps = parent.getParent();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[105]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((gramps == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[63]++;
        return null;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[64]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[106]++;

      Node greatGramps = gramps.getParent();
      String name;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[107]++;
      switch (gramps.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[65]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[108]++;
int CodeCoverConditionCoverageHelper_C23;
          // VAR
          //   NAME (gramps)
          //     OBJLIT (parent)
          //       STRING (n)
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((greatGramps == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((greatGramps.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[66]++;
            return null;

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[67]++;}
          name = gramps.getString();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[109]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[110]++;
          break;
        case Token.ASSIGN:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[68]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[111]++;
          // ASSIGN (gramps)
          //   NAME|GETPROP
          //   OBJLIT (parent)
          //     STRING (n)
          Node lvalue = gramps.getFirstChild();
          name = lvalue.getQualifiedName();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[112]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[113]++;
          break;
        case Token.STRING_KEY:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[69]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[114]++;
int CodeCoverConditionCoverageHelper_C24;
          // OBJLIT
          //   STRING (gramps)
          //     OBJLIT (parent)
          //       STRING (n)
          if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((greatGramps != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((greatGramps.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[70]++;
            name = getNameForObjLitKey(gramps);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[115]++;

          } else {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[71]++;
            return null;
          }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[116]++;
          break;
        default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[72]++;
          return null;
      }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[117]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[73]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[118]++;
        String key = n.getString();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[119]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(key)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[75]++;
          return name + '.' + key;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[76]++;}

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[74]++;}
      return null;
    }

    /**
     * Gets the type of a value or simple expression.
     *
     * @param n An r-value in an assignment or variable declaration (not null)
     * @return A {@link Name.Type}
     */
    Name.Type getValueType(Node n) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[120]++;
      switch (n.getType()) {
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[77]++;
          return Name.Type.OBJECTLIT;
        case Token.FUNCTION:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[78]++;
          return Name.Type.FUNCTION;
        case Token.OR:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[79]++;
          // Recurse on the second value. If the first value were an object
          // literal or function, then the OR would be meaningless and the
          // second value would be dead code. Assume that if the second value
          // is an object literal or function, then the first value will also
          // evaluate to one when it doesn't evaluate to false.
          return getValueType(n.getLastChild());
        case Token.HOOK:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[80]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[121]++;
          // The same line of reasoning used for the OR case applies here.
          Node second = n.getFirstChild().getNext();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[122]++;
          Name.Type t = getValueType(second);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[123]++;
int CodeCoverConditionCoverageHelper_C27;
          if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((t != Name.Type.OTHER) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[81]++; return t;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[82]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[124]++;
          Node third = second.getNext();
          return getValueType(third); default : CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[83]++;
      }
      return Name.Type.OTHER;
    }

    /**
     * Updates our representation of the global namespace to reflect an
     * assignment to a global name in global scope.
     *
     * @param t The traversal
     * @param n The node currently being visited
     * @param parent {@code n}'s parent
     * @param name The global name (e.g. "a" or "a.b.c.d")
     * @param isPropAssign Whether this set corresponds to a property
     *     assignment of the form <code>a.b.c = ...;</code>
     * @param type The type of the value that the name is being assigned
     */
    void handleSetFromGlobal(NodeTraversal t, Node n, Node parent, String name,
                             boolean isPropAssign, Name.Type type) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[125]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((maybeHandlePrototypePrefix(t, n, parent, name)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[84]++; return;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[85]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[126]++;

      Name nameObj = getOrCreateName(name);
      nameObj.type = type;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[127]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[128]++;

      Ref set = new Ref(t, n, nameObj, Ref.Type.SET_FROM_GLOBAL,
          currentPreOrderIndex++);
      nameObj.addRef(set);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[129]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[130]++;
int CodeCoverConditionCoverageHelper_C29;

      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isNestedAssign(parent)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[86]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[131]++;
        // This assignment is both a set and a get that creates an alias.
        Ref get = new Ref(t, n, nameObj, Ref.Type.ALIASING_GET,
            currentPreOrderIndex++);
        nameObj.addRef(get);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[132]++;
        Ref.markTwins(set, get);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[133]++;

      } else {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[87]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[134]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isTypeDeclaration(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[88]++;
        // Names with a @constructor or @enum annotation are always collapsed
        nameObj.setDeclaredType();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[135]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[89]++;}
}
    }

    /**
     * Determines whether a set operation is a constructor or enumeration
     * or interface declaration. The set operation may either be an assignment
     * to a name, a variable declaration, or an object literal key mapping.
     *
     * @param n The node that represents the name being set
     * @param parent Parent node of {@code n} (an ASSIGN, VAR, or OBJLIT node)
     * @return Whether the set operation is either a constructor or enum
     *     declaration
     */
    private boolean isTypeDeclaration(Node n, Node parent) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[136]++;
      Node valueNode = NodeUtil.getRValueOfLValue(n);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[137]++;
      JSDocInfo info = NodeUtil.getBestJSDocInfo(n);
      // Heed the annotations only if they're sensibly used.
      return info != null && valueNode != null &&
             (info.isConstructor() && valueNode.isFunction() ||
              info.isInterface() && valueNode.isFunction() ||
              info.hasEnumParameterType() && valueNode.isObjectLit());
    }

    /**
     * Updates our representation of the global namespace to reflect an
     * assignment to a global name in a local scope.
     *
     * @param t The traversal
     * @param n The node currently being visited
     * @param parent {@code n}'s parent
     * @param name The global name (e.g. "a" or "a.b.c.d")
     */
    void handleSetFromLocal(NodeTraversal t, Node n, Node parent,
                            String name) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[138]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((maybeHandlePrototypePrefix(t, n, parent, name)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[90]++; return;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[91]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[139]++;

      Name nameObj = getOrCreateName(name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[140]++;
      Ref set = new Ref(t, n, nameObj,
          Ref.Type.SET_FROM_LOCAL, currentPreOrderIndex++);
      nameObj.addRef(set);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[141]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[142]++;
int CodeCoverConditionCoverageHelper_C32;

      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isNestedAssign(parent)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[92]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[143]++;
        // This assignment is both a set and a get that creates an alias.
        Ref get = new Ref(t, n, nameObj,
            Ref.Type.ALIASING_GET, currentPreOrderIndex++);
        nameObj.addRef(get);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[144]++;
        Ref.markTwins(set, get);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[145]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[93]++;}
    }

    /**
     * Updates our representation of the global namespace to reflect a read
     * of a global name.
     *
     * @param t The traversal
     * @param n The node currently being visited
     * @param parent {@code n}'s parent
     * @param name The global name (e.g. "a" or "a.b.c.d")
     */
    void handleGet(NodeTraversal t, Node n, Node parent, String name) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[146]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((maybeHandlePrototypePrefix(t, n, parent, name)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[94]++; return;
} else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[95]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[147]++;

      Ref.Type type = Ref.Type.DIRECT_GET;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[148]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[96]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[149]++;
        switch (parent.getType()) {
          case Token.IF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[98]++;
          case Token.TYPEOF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[99]++;
          case Token.VOID:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[100]++;
          case Token.NOT:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[101]++;
          case Token.BITNOT:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[102]++;
          case Token.POS:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[103]++;
          case Token.NEG:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[104]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[150]++;
            break;
          case Token.CALL:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[105]++;
            type = n == parent.getFirstChild()
                   ? Ref.Type.CALL_GET
                   : Ref.Type.ALIASING_GET;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[151]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[152]++;
            break;
          case Token.NEW:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[106]++;
            type = n == parent.getFirstChild()
                   ? Ref.Type.DIRECT_GET
                   : Ref.Type.ALIASING_GET;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[153]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[154]++;
            break;
          case Token.OR:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[107]++;
          case Token.AND:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[108]++;
            // This node is x or y in (x||y) or (x&&y). We only know that an
            // alias is not getting created for this name if the result is used
            // in a boolean context or assigned to the same name
            // (e.g. var a = a || {}).
            type = determineGetTypeForHookOrBooleanExpr(t, parent, name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[155]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[156]++;
            break;
          case Token.HOOK:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[109]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[157]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n != parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[110]++;
              // This node is y or z in (x?y:z). We only know that an alias is
              // not getting created for this name if the result is assigned to
              // the same name (e.g. var a = a ? a : {}).
              type = determineGetTypeForHookOrBooleanExpr(t, parent, name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[158]++;

            } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[111]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[159]++;
            break;
          case Token.DELPROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[112]++;
            type = Ref.Type.DELETE_PROP;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[160]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[161]++;
            break;
          default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[113]++;
            type = Ref.Type.ALIASING_GET;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[162]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[163]++;
            break;
        }

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[97]++;}

      handleGet(t, n, parent, name, type);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[164]++;
    }

    /**
     * Determines whether the result of a hook (x?y:z) or boolean expression
     * (x||y) or (x&&y) is assigned to a specific global name.
     *
     * @param t The traversal
     * @param parent The parent of the current node in the traversal. This node
     *     should already be known to be a HOOK, AND, or OR node.
     * @param name A name that is already known to be global in the current
     *     scope (e.g. "a" or "a.b.c.d")
     * @return The expression's get type, either {@link Ref.Type#DIRECT_GET} or
     *     {@link Ref.Type#ALIASING_GET}
     */
    Ref.Type determineGetTypeForHookOrBooleanExpr(
        NodeTraversal t, Node parent, String name) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[165]++;
      Node prev = parent;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[166]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[4]++;


      for (Node anc : parent.getAncestors()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[4]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[5]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[6]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[167]++;
        switch (anc.getType()) {
          case Token.EXPR_RESULT:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[114]++;
          case Token.VAR:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[115]++;
          case Token.IF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[116]++;
          case Token.WHILE:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[117]++;
          case Token.FOR:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[118]++;
          case Token.TYPEOF:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[119]++;
          case Token.VOID:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[120]++;
          case Token.NOT:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[121]++;
          case Token.BITNOT:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[122]++;
          case Token.POS:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[123]++;
          case Token.NEG:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[124]++;
            return Ref.Type.DIRECT_GET;
          case Token.HOOK:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[125]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[168]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((anc.getFirstChild() == prev) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[126]++;
              return Ref.Type.DIRECT_GET;

            } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[127]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[169]++;
            break;
          case Token.ASSIGN:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[128]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[170]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((name.equals(anc.getFirstChild().getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[129]++;
              return Ref.Type.ALIASING_GET;

            } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[130]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[171]++;
            break;
          case Token.NAME:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[131]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[172]++;
int CodeCoverConditionCoverageHelper_C38;  // a variable declaration
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((name.equals(anc.getString())) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[132]++;
              return Ref.Type.ALIASING_GET;

            } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[133]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[173]++;
            break;
          case Token.CALL:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[134]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[174]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((anc.getFirstChild() != prev) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[135]++;
              return Ref.Type.ALIASING_GET;

            } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[136]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[175]++;
            break;
          case Token.DELPROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[137]++;
            return Ref.Type.DELETE_PROP; default : CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[138]++;
        }
        prev = anc;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[176]++;
      }
      return Ref.Type.ALIASING_GET;
    }

    /**
     * Updates our representation of the global namespace to reflect a read
     * of a global name.
     *
     * @param t The current node traversal
     * @param n The node currently being visited
     * @param parent {@code n}'s parent
     * @param name The global name (e.g. "a" or "a.b.c.d")
     * @param type The reference type
     */
    void handleGet(NodeTraversal t, Node n, Node parent,
        String name, Ref.Type type) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[177]++;
      Name nameObj = getOrCreateName(name);

      // No need to look up additional ancestors, since they won't be used.
      nameObj.addRef(new Ref(t, n, nameObj, type, currentPreOrderIndex++));
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[178]++;
    }

    /**
     * Updates our representation of the global namespace to reflect a read
     * of a global name's longest prefix before the "prototype" property if the
     * name includes the "prototype" property. Does nothing otherwise.
     *
     * @param t The current node traversal
     * @param n The node currently being visited
     * @param parent {@code n}'s parent
     * @param name The global name (e.g. "a" or "a.b.c.d")
     * @return Whether the name was handled
     */
    boolean maybeHandlePrototypePrefix(NodeTraversal t, Node n, Node parent,
        String name) {
      // We use a string-based approach instead of inspecting the parse tree
      // to avoid complexities with object literals, possibly nested, beneath
      // assignments.

      int numLevelsToRemove;
      String prefix;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[179]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((name.endsWith(".prototype")) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[139]++;
        numLevelsToRemove = 1;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[180]++;
        prefix = name.substring(0, name.length() - 10);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[181]++;

      } else {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[140]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[182]++;
        int i = name.indexOf(".prototype.");
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[183]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i == -1) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[141]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[142]++;}
        prefix = name.substring(0, i);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[184]++;
        numLevelsToRemove = 2;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[185]++;
        i = name.indexOf('.', i + 11);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[186]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[187]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[7]++;


int CodeCoverConditionCoverageHelper_C42;
        while ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[7]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[8]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[9]++;
}
          numLevelsToRemove++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[188]++;
          i = name.indexOf('.', i + 1);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[189]++;
        }
      }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[190]++;
int CodeCoverConditionCoverageHelper_C43;

      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[143]++;
        // Object literal keys have no prefix that's referenced directly per
        // key, so we're done.
        return true;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[144]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[191]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[10]++;


int CodeCoverConditionCoverageHelper_C44;

      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i < numLevelsToRemove) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[10]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[11]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[12]++;
}
        parent = n;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[192]++;
        n = n.getFirstChild();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[193]++;
      }

      handleGet(t, n, parent, prefix, Ref.Type.PROTOTYPE_GET);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[194]++;
      return true;
    }

    /**
     * Determines whether an assignment is nested (i.e. whether its return
     * value is used).
     *
     * @param parent The parent of the current traversal node (not null)
     * @return Whether it appears that the return value of the assignment is
     *     used
     */
    boolean isNestedAssign(Node parent) {
      return parent.isAssign() &&
             !parent.getParent().isExprResult();
    }

    /**
     * Gets a {@link Name} instance for a global name. Creates it if necessary,
     * as well as instances for any of its prefixes that are not yet defined.
     *
     * @param name A global name (e.g. "a", "a.b.c.d")
     * @return The {@link Name} instance for {@code name}
     */
    Name getOrCreateName(String name) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[195]++;
      Name node = nameMap.get(name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[196]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[145]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[197]++;
        int i = name.lastIndexOf('.');
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[198]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[147]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[199]++;
          String parentName = name.substring(0, i);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[200]++;
          Name parent = getOrCreateName(parentName);
          node = parent.addProperty(name.substring(i + 1), inExterns);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[201]++;

        } else {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[148]++;
          node = new Name(name, null, inExterns);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[202]++;
          globalNames.add(node);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[203]++;
        }
        nameMap.put(name, node);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[204]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[146]++;}
      return node;
    }
  }

  // -------------------------------------------------------------------------

  /**
   * A name defined in global scope (e.g. "a" or "a.b.c.d"). These form a tree.
   * As the parse tree traversal proceeds, we'll discover that some names
   * correspond to JavaScript objects whose properties we should consider
   * collapsing.
   */
  static class Name implements StaticSlot<JSType> {
    enum Type {
      OBJECTLIT,
      FUNCTION,
      GET,
      SET,
      OTHER,
    }

    private final String baseName;
    final Name parent;
    List<Name> props;

    /** The first global assignment to a name. */
    private Ref declaration;

    /** All references to a name. This must contain {@code declaration}. */
    private List<Ref> refs;

    Type type;
    private boolean declaredType = false;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[205]++;
  }
    private boolean hasDeclaredTypeDescendant = false;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[206]++;
  }
    int globalSets = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[207]++;
  }
    int localSets = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[208]++;
  }
    int aliasingGets = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[209]++;
  }
    int totalGets = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[210]++;
  }
    int callGets = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[211]++;
  }
    int deleteProps = 0;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[212]++;
  }
    final boolean inExterns;

    JSDocInfo docInfo = null;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[213]++;
  }

    Name(String name, Name parent, boolean inExterns) {
      this.baseName = name;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[214]++;
      this.parent = parent;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[215]++;
      this.type = Type.OTHER;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[216]++;
      this.inExterns = inExterns;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[217]++;
    }

    Name addProperty(String name, boolean inExterns) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[218]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((props == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[149]++;
        props = new ArrayList<Name>();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[219]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[150]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[220]++;
      Name node = new Name(name, this, inExterns);
      props.add(node);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[221]++;
      return node;
    }

    String getBaseName() {
      return baseName;
    }

    @Override
    public String getName() {
      return getFullName();
    }

    String getFullName() {
      return parent == null ? baseName : parent.getFullName() + '.' + baseName;
    }

    @Override
    public Ref getDeclaration() {
      return declaration;
    }

    @Override
    public boolean isTypeInferred() {
      return false;
    }

    @Override
    public JSType getType() {
      return null;
    }

    void addRef(Ref ref) {
      addRefInternal(ref);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[222]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[223]++;
      switch (ref.type) {
        case SET_FROM_GLOBAL:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[151]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[224]++;
int CodeCoverConditionCoverageHelper_C48;
          if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((declaration == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[152]++;
            declaration = ref;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[225]++;
            docInfo = getDocInfoForDeclaration(ref);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[226]++;

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[153]++;}
          globalSets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[227]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[228]++;
          break;
        case SET_FROM_LOCAL:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[154]++;
          localSets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[229]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[230]++;
          break;
        case PROTOTYPE_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[155]++;
        case DIRECT_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[156]++;
          totalGets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[231]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[232]++;
          break;
        case ALIASING_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[157]++;
          aliasingGets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[233]++;
          totalGets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[234]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[235]++;
          break;
        case CALL_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[158]++;
          callGets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[236]++;
          totalGets++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[237]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[238]++;
          break;
        case DELETE_PROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[159]++;
          deleteProps++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[239]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[240]++;
          break;
        default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[160]++;
          throw new IllegalStateException();
      }
    }

    void removeRef(Ref ref) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[241]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((refs != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((refs.remove(ref)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[161]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[242]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ref == declaration) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[163]++;
          declaration = null;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[243]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[244]++;
int CodeCoverConditionCoverageHelper_C51;
          if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((refs != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[165]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[245]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[13]++;


            for (Ref maybeNewDecl : refs) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[13]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[14]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[15]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[246]++;
int CodeCoverConditionCoverageHelper_C52;
              if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((maybeNewDecl.type == Ref.Type.SET_FROM_GLOBAL) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[167]++;
                declaration = maybeNewDecl;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[247]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[248]++;
                break;

              } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[168]++;}
            }

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[166]++;}

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[164]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[249]++;

        switch (ref.type) {
          case SET_FROM_GLOBAL:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[169]++;
            globalSets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[250]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[251]++;
            break;
          case SET_FROM_LOCAL:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[170]++;
            localSets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[252]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[253]++;
            break;
          case PROTOTYPE_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[171]++;
          case DIRECT_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[172]++;
            totalGets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[254]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[255]++;
            break;
          case ALIASING_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[173]++;
            aliasingGets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[256]++;
            totalGets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[257]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[258]++;
            break;
          case CALL_GET:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[174]++;
            callGets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[259]++;
            totalGets--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[260]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[261]++;
            break;
          case DELETE_PROP:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[175]++;
            deleteProps--;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[262]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[263]++;
            break;
          default:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[176]++;
            throw new IllegalStateException();
        }

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[162]++;}
    }

    List<Ref> getRefs() {
      return refs == null ? ImmutableList.<Ref>of() : refs;
    }

    void addRefInternal(Ref ref) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[264]++;
int CodeCoverConditionCoverageHelper_C53;
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((refs == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[177]++;
        refs = Lists.newArrayList();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[265]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[178]++;}
      refs.add(ref);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[266]++;
    }

    boolean canEliminate() {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[267]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((canCollapseUnannotatedChildNames()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((totalGets > 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[179]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[180]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[268]++;
int CodeCoverConditionCoverageHelper_C55;

      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((props != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[181]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[269]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[16]++;


        for (Name n : props) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[16]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[17]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[18]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[270]++;
int CodeCoverConditionCoverageHelper_C56;
          if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((n.canCollapse()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[183]++;
            return false;

          } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[184]++;}
        }

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[182]++;}
      return true;
    }

    boolean isSimpleStubDeclaration() {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[271]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((getRefs().size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[185]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[272]++;
        Ref ref = refs.get(0);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[273]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((ref.node.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((ref.node.getParent().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[187]++;
          return true;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[188]++;}

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[186]++;}
      return false;
    }

    boolean canCollapse() {
      return !inExterns && !isGetOrSetDefinition() && (declaredType ||
          (parent == null || parent.canCollapseUnannotatedChildNames()) &&
          (globalSets > 0 || localSets > 0) &&
          deleteProps == 0);
    }

    boolean isGetOrSetDefinition() {
      return this.type == Type.GET || this.type == Type.SET;
    }

    boolean canCollapseUnannotatedChildNames() {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[274]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (512)) == 0 || true) &&
 ((type == Type.OTHER) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (128)) == 0 || true) &&
 ((isGetOrSetDefinition()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (32)) == 0 || true) &&
 ((globalSets != 1) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((localSets != 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((deleteProps != 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 5) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 5) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[189]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[190]++;}

      // Don't try to collapse if the one global set is a twin reference.
      // We could theoretically handle this case in CollapseProperties, but
      // it's probably not worth the effort.
      Preconditions.checkNotNull(declaration);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[275]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[276]++;
int CodeCoverConditionCoverageHelper_C60;
      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((declaration.getTwin() != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[191]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[192]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[277]++;
int CodeCoverConditionCoverageHelper_C61;

      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((declaredType) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[193]++;
        return true;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[194]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[278]++;
int CodeCoverConditionCoverageHelper_C62;

      // If this is a key of an aliased object literal, then it will be aliased
      // later. So we won't be able to collapse its properties.
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((parent.shouldKeepKeys()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[195]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[196]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[279]++;
int CodeCoverConditionCoverageHelper_C63;

      // If this is aliased, then its properties can't be collapsed either.
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((aliasingGets > 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[197]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[198]++;}

      return (parent == null || parent.canCollapseUnannotatedChildNames());
    }

    /** Whether this is an object literal that needs to keep its keys. */
    boolean shouldKeepKeys() {
      return type == Type.OBJECTLIT && aliasingGets > 0;
    }

    boolean needsToBeStubbed() {
      return globalSets == 0 && localSets > 0;
    }

    void setDeclaredType() {
      declaredType = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[280]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[281]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[19]++;


int CodeCoverConditionCoverageHelper_C64;
      for (Name ancestor = parent;(((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((ancestor != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false);
           ancestor = ancestor.parent) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[19]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[20]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[21]++;
}
        ancestor.hasDeclaredTypeDescendant = true;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[282]++;
      }
    }

    boolean isDeclaredType() {
      return declaredType;
    }

    /**
     * Determines whether this name is a prefix of at least one class or enum
     * name. Because classes and enums are always collapsed, the namespace will
     * have different properties in compiled code than in uncompiled code.
     *
     * For example, if foo.bar.DomHelper is a class, then foo and foo.bar are
     * considered namespaces.
     */
    boolean isNamespace() {
      return hasDeclaredTypeDescendant && type == Type.OBJECTLIT;
    }

    /**
     * Determines whether this is a simple name (as opposed to a qualified
     * name).
     */
    boolean isSimpleName() {
      return parent == null;
    }

    @Override public String toString() {
      return getFullName() + " (" + type + "): globalSets=" + globalSets +
          ", localSets=" + localSets + ", totalGets=" + totalGets +
          ", aliasingGets=" + aliasingGets + ", callGets=" + callGets;
    }

    @Override
    public JSDocInfo getJSDocInfo() {
      return docInfo;
    }

    /**
     * Tries to get the doc info for a given declaration ref.
     */
    private static JSDocInfo getDocInfoForDeclaration(Ref ref) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[283]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((ref.node != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[199]++;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[284]++;
        Node refParent = ref.node.getParent();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[285]++;
        switch (refParent.getType()) {
          case Token.FUNCTION:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[201]++;
          case Token.ASSIGN:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[202]++;
            return refParent.getJSDocInfo();
          case Token.VAR:
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[203]++;
            return ref.node == refParent.getFirstChild() ?
                refParent.getJSDocInfo() : ref.node.getJSDocInfo(); default : CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[204]++;
        }

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[200]++;}

      return null;
    }
  }

  // -------------------------------------------------------------------------

  /**
   * A global name reference. Contains references to the relevant parse tree
   * node and its ancestors that may be affected.
   */
  static class Ref implements StaticReference<JSType> {
    enum Type {
      SET_FROM_GLOBAL,
      SET_FROM_LOCAL,
      PROTOTYPE_GET,
      ALIASING_GET,     // Prevents a name's properties from being collapsed
      DIRECT_GET,       // Prevents a name from being completely eliminated
      CALL_GET,         // Prevents a name from being collapsed if never set
      DELETE_PROP,      // Prevents a name from being collapsed at all.
    }

    Node node;
    final JSModule module;
    final StaticSourceFile source;
    final Name name;
    final Type type;
    final Scope scope;
    final int preOrderIndex;

    /**
     * Certain types of references are actually double-refs. For example,
     * var a = b = 0;
     * counts as both a "set" of b and an "alias" of b.
     *
     * We create two Refs for this node, and mark them as twins of each other.
     */
    private Ref twin = null;
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[286]++;
  }

    /**
     * Creates a reference at the current node.
     */
    Ref(NodeTraversal t, Node node, Name name, Type type, int index) {
      this.node = node;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[287]++;
      this.name = name;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[288]++;
      this.module = t.getInput() == null ? null : t.getInput().getModule();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[289]++;
      this.source = node.getStaticSourceFile();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[290]++;
      this.type = type;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[291]++;
      this.scope = t.getScope();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[292]++;
      this.preOrderIndex = index;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[293]++;
    }

    private Ref(Ref original, Type type, int index) {
      this.node = original.node;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[294]++;
      this.name = original.name;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[295]++;
      this.module = original.module;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[296]++;
      this.source = original.source;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[297]++;
      this.type = type;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[298]++;
      this.scope = original.scope;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[299]++;
      this.preOrderIndex = index;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[300]++;
    }

    private Ref(Type type, int index) {
      this.type = type;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[301]++;
      this.module = null;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[302]++;
      this.source = null;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[303]++;
      this.scope = null;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[304]++;
      this.name = null;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[305]++;
      this.preOrderIndex = index;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[306]++;
    }

    @Override
    public Node getNode() {
      return node;
    }

    @Override
    public StaticSourceFile getSourceFile() {
      return source;
    }

    @Override
    public StaticSlot<JSType> getSymbol() {
      return name;
    }

    JSModule getModule() {
      return module;
    }

    String getSourceName() {
      return source == null ? "" : source.getName();
    }

    Ref getTwin() {
      return twin;
    }

    boolean isSet() {
      return type == Type.SET_FROM_GLOBAL || type == Type.SET_FROM_LOCAL;
    }

    static void markTwins(Ref a, Ref b) {
      Preconditions.checkArgument(
          (a.type == Type.ALIASING_GET || b.type == Type.ALIASING_GET) &&
          (a.type == Type.SET_FROM_GLOBAL || a.type == Type.SET_FROM_LOCAL ||
           b.type == Type.SET_FROM_GLOBAL || b.type == Type.SET_FROM_LOCAL));
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[307]++;
      a.twin = b;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[308]++;
      b.twin = a;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[309]++;
    }

    /**
     * Create a new ref that is the same as this one, but of
     * a different class.
     */
    Ref cloneAndReclassify(Type type) {
      return new Ref(this, type, this.preOrderIndex);
    }

    static Ref createRefForTesting(Type type) {
      return new Ref(type, -1);
    }
  }


  /**
   * An experimental compiler pass for tracking what symbols were added/removed
   * at each stage of compilation.
   *
   * When "global namespace tracker" mode is on, we rebuild the global namespace
   * after each pass, and diff it against the last namespace built.
   */
  static class Tracker implements CompilerPass {
    private final AbstractCompiler compiler;
    private final PrintStream stream;
    private final Predicate<String> isInterestingSymbol;

    private Set<String> previousSymbolsInTree = ImmutableSet.of();
  {
    CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[310]++;
  }

    /**
       @param stream The stream to print logs to.
     * @param isInterestingSymbol A predicate to determine which symbols
     *     we care about.
     */
    Tracker(AbstractCompiler compiler, PrintStream stream,
        Predicate<String> isInterestingSymbol) {
      this.compiler = compiler;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[311]++;
      this.stream = stream;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[312]++;
      this.isInterestingSymbol = isInterestingSymbol;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[313]++;
    }

    @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[314]++;
      GlobalNamespace namespace = new GlobalNamespace(compiler, externs, root);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[315]++;

      Set<String> currentSymbols = Sets.newTreeSet();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[316]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[22]++;


      for (String name : namespace.getNameIndex().keySet()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[22]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[23]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[24]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[317]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((isInterestingSymbol.apply(name)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[205]++;
          currentSymbols.add(name);
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[318]++;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[206]++;}
      }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[319]++;

      String passName = compiler.getLastPassName();
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[320]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((passName == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[207]++;
        passName = "[Unknown pass]";
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[321]++;

      } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[208]++;}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[322]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[25]++;



      for (String sym : currentSymbols) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[25]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[26]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[27]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[323]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((previousSymbolsInTree.contains(sym)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[209]++;
          stream.println(String.format("%s: Added by %s", sym, passName));
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[324]++;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[210]++;}
      }
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[325]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[28]++;



      for (String sym : previousSymbolsInTree) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[28]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[29]--;
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.loops[30]++;
}
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[326]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((currentSymbols.contains(sym)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[211]++;
          stream.println(String.format("%s: Removed by %s", sym, passName));
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[327]++;

        } else {
  CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.branches[212]++;}
      }

      previousSymbolsInTree = currentSymbols;
CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap.statements[328]++;
    }
  }
}

class CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap ());
  }
    public static long[] statements = new long[329];
    public static long[] branches = new long[213];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[70];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GlobalNamespace.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,2,2,1,1,1,2,2,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1,1,2,3,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 69; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$8ccma9coo4wz5tuav2pmg5qn1hzvyap () {
    super("com.google.javascript.jscomp.GlobalNamespace.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 328; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 212; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 69; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.GlobalNamespace.java");
      for (int i = 1; i <= 328; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 212; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 69; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

