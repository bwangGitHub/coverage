/*
 * Copyright 2009 The Closure Compiler Authors.
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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.DefinitionsRemover.AssignmentDefinition;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.DefinitionsRemover.NamedFunctionDefinition;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A graph represents all the referencing of global names in the program. In
 * other words, it is a call and variable-name graph.
 *
 * <p>The NameReferenceGraph G for a program P is a directed graph G = (V, E)
 * where:
 *
 * <P>V ({@link Name}) represents all global names in P and E = (v, v'), v and
 * v' in V ({@link Reference} represents a reference use or definition from the
 * name v to v' in P.
 *
 * <p>There are two core results we are trying to compute. The first being able
 * to precisely identify the function body at any given call site with
 * {@link #getReferencesAt(Node)}.
 *
 * <p>The second result come directly from the previous one. The directed edge
 * provides us with dependency information. If A->B, B might be needed (in this
 * module) if A is needed (in this module). The converse of the this result is
 * more useful. B is not needed if A is not needed.
 *
 */
class NameReferenceGraph extends
    LinkedDirectedGraph<NameReferenceGraph.Name, NameReferenceGraph.Reference>
    implements DefinitionProvider {
  static {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.ping();
  }


  // This is the key result of the name graph. Given a node in the AST, this map
  // will give us the Reference edges. For example a CALL node will map to a
  // list of possible call edge destinations.
  private final Multimap<Node, Name>
      referenceMap = HashMultimap.create();
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[1]++;
  }

  // Given a qualified name, provides the Name object.
  private Map<String, Name> nameMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[2]++;
  }

  // The following are some implicit nodes of the graph.

  // If we have a call site that we absolutely have no idea what variable it
  // it calls or reference, we'd point it to UNKNOWN.
  final Name UNKNOWN;

  // Represents the "main" global block as well as externs.
  final Name MAIN;

  // The implicit "window" object.
  final Name WINDOW;

  final AbstractCompiler compiler;

  public NameReferenceGraph(AbstractCompiler compiler) {
    super(true, true);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[3]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[4]++;

    // Initialize builtins.
    UNKNOWN = new Name("{UNKNOWN}", true);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[5]++;
    UNKNOWN.isAliased = true;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[6]++;
    UNKNOWN.type = compiler.getTypeRegistry().getNativeType(
        JSTypeNative.NO_TYPE);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[7]++;
    this.createNode(UNKNOWN);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[8]++;

    MAIN = new Name("{Global Main}", true);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[9]++;
    this.createNode(MAIN);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[10]++;

    WINDOW = new Name("window", true);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[11]++;
    this.createNode(WINDOW);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[12]++;
  }

  public Name defineNameIfNotExists(String name, boolean isExtern) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[13]++;
    Name symbol = null;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameMap.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[1]++;
      // This is a re-declaration.
      symbol = nameMap.get(name);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[15]++;

    } else {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[2]++;
      symbol = new Name(name, isExtern);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[16]++;
      nameMap.put(name, symbol);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[17]++;
      createNode(symbol);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[18]++;
    }
    return symbol;
  }

  /**
   * Retrieves a list of all possible Names that this site is referring to.
   */
  public List<Name> getReferencesAt(Node site) {
    Preconditions.checkArgument(
        site.isGetProp() || site.isName());
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[19]++;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[20]++;
    List<Name> result = new ArrayList<Name>();
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[1]++;


    for (Name target : referenceMap.get(site)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[1]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[2]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[3]++;
}
      result.add(target);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[22]++;
    }
    return result;
  }

  @Override
  public Collection<Definition> getDefinitionsReferencedAt(Node useSite) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[23]++;
    List<Name> nameRefs = getReferencesAt(useSite);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((nameRefs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[3]++;
      return null;

    } else {
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[4]++;}
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[25]++;

    List<Definition> result = Lists.newArrayList();
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[4]++;


    for (Name nameRef : nameRefs) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[4]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[5]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[6]++;
}
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[27]++;
      List<Definition> decls = nameRef.getDeclarations();
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((decls.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[5]++;
        result.addAll(decls);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[29]++;

      } else {
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[6]++;}
    }
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((result.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[7]++;
      return result;

    } else {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[8]++;
      return null;
    }
  }

  public Name getSymbol(String name) {
    return nameMap.get(name);
  }

  @Override
  public GraphNode<Name, Reference> createNode(Name value) {
    nameMap.put(value.qName, value);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[31]++;
    return super.createNode(value);
  }

  @Override
  public void connect(Name src, Reference ref, Name dest) {
    super.connect(src, ref, dest);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[32]++;
    referenceMap.put(ref.site, dest);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[33]++;
  }

  /**
   * Represents function or variable names that can be referenced globally.
   */
  class Name {
    // Full name
    private final String qName;

    private JSType type;

    // A list (re)declarations
    private List<Definition> declarations = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[34]++;
  }

    final boolean isExtern;

    private boolean isExported = false;
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[35]++;
  }

    private boolean isAliased = false;
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[36]++;
  }

    // Function invocations that use ".call" and ".apply" syntax may prevent
    // several of the possible optimizations.  We keep track of all functions
    // invoked in this way so those passes can exclude them.
    // Ex:
    // some_func.call(some_obj, 1, 2 , 3);
    // The name graph does not currently recognize this as a call to some_func.
    // This Set is meant to keep track of such occurrence until the name graph
    // becomes aware of those cases.
    private boolean exposedToCallOrApply = false;
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[37]++;
  }

    public Name(String qName, boolean isExtern) {
      this.qName = qName;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[38]++;
      this.isExtern = isExtern;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[39]++;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[40]++;
      int lastDot = qName.lastIndexOf('.');
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[41]++;
      String name = (lastDot == -1) ? qName : qName.substring(lastDot + 1);
      this.isExported = compiler.getCodingConvention().isExported(name);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[42]++;
      this.type = compiler.getTypeRegistry().getNativeType(
          JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[43]++;
    }

    public JSType getType() {
      return type;
    }

    public void setType(JSType type) {
      this.type = type;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[44]++;
    }

    public List<Definition> getDeclarations() {
      return declarations;
    }

    public void addAssignmentDeclaration(Node node) {
      declarations.add(new AssignmentDefinition(node, isExtern));
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[45]++;
    }

    public void addFunctionDeclaration(Node node) {
      declarations.add(new NamedFunctionDefinition(node, isExtern));
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[46]++;
    }

    public boolean isExtern() {
      return isExtern;
    }

    public void markExported() {
      this.isExported = true;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[47]++;
    }

    public boolean isExported() {
      return isExported;
    }

    /** Removes all of the declarations of this name. */
    public final void remove() {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[48]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[7]++;


      for (Definition declaration : getDeclarations()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[7]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[8]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[9]++;
}
        declaration.remove();
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[49]++;
      }
    }

    /**
     * @return {@code} True if this name has been dereferenced. Removing from
     *     the program or the module is no longer safe unless further analysis
     *     can prove otherwise.
     */
    public boolean isAliased() {
      return isAliased;
    }

    public void setAliased(boolean isAliased) {
      this.isAliased = isAliased;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[50]++;
    }

    public boolean hasSideEffect() {
      return isCallable();
    }

    public String getQualifiedName() {
      return qName;
    }

    /**
     * @return The short property name of this object if it is a property, else
     *     {@code null}.
     */
    public String getPropertyName() {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[51]++;
      int lastIndexOfDot = qName.lastIndexOf('.');
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[52]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lastIndexOfDot == -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[9]++;
        return null;

      } else {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[10]++;
        return qName.substring(lastIndexOfDot + 1);
      }
    }

    public boolean isCallable() {
      return type.canBeCalled();
    }

    public boolean exposedToCallOrApply() {
      return exposedToCallOrApply;
    }

    public void markExposedToCallOrApply() {
      exposedToCallOrApply = true;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[53]++;
    }

    @Override
    public String toString() {
      return qName + " : " + type;
    }

    @Override
    public int hashCode() {
      return qName.hashCode();
    }

    /**
     * Return true if it's safe to change the signature of the function
     * references by this name. It is safe to change the signature if the Name
     * is:
     * <ul>
     * <li>callable</li>
     * <li>not an extern</li>
     * <li>not been aliased</li>
     * <li>not been exported</li>
     * <li>Referred by call or apply functions</li>
     * <li>The function uses the arguments property</li>
     * </ul>
     *
     * @return true if it's safe to change the signature of the name.
     */
    public boolean canChangeSignature() {
      // Ignore anything that is extern as they should not be changed.
      // Also skip over any non-function names. Finally if a function has been
      // alias, we don't know all of its callers and should not optimize.
      //
      // Also, if the function is called using .call or .apply, we don't try to
      // optimize those call because the name graph does not give us enough
      // information on the parameters.

      // TODO(user) We'll be able to remove the check for call or apply once
      // the name graph handles those call. The issue for now is that those
      // calls aren't edges in the graph, so we don't have enough information to
      // know if it's safe to change the method's signature.
      return !(isExtern() ||
          !isCallable() ||
          isAliased() ||
          isExported() ||
          exposedToCallOrApply() ||
          nameUsesArgumentsProperty());
    }

    /**
     * Returns true if the the arguments property is used in any of the function
     * definition.
     * Ex. function foo(a,b,c) {return arguments.size;};
     * @return True is arguments is present in one of the definitions.
     */
    private boolean nameUsesArgumentsProperty() {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[54]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[10]++;


      for (Definition definition : getDeclarations()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[10]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[11]--;
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.loops[12]++;
}
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[55]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarArgsFunction(definition.getRValue())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[11]++;
          return true;

        } else {
  CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.branches[12]++;}
      }
      return false;
    }
  }

  /**
   * A reference site for a function or a variable reference. It can be a
   * reference use or an assignment to that name.
   */
  static class Reference {
    // The node that references the name.
    public final Node site;

    // Parent pointer.
    public final Node parent;

    private JSModule module = null;
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[56]++;
  }

    // A reference is unknown because we don't know the object's type.
    // If A.x->B.y in the name graph and the edge is unknown. It implies
    // A.x() reference to someObject.y and B.y MAY be the site.
    private boolean isUnknown = false;
  {
    CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[57]++;
  }

    public Reference(Node site, Node parent) {
      this.site = site;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[58]++;
      this.parent = parent;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[59]++;
    }

    public boolean isUnknown() {
      return isUnknown;
    }

    public void setUnknown(boolean isUnknown) {
      this.isUnknown = isUnknown;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[60]++;
    }

    public JSModule getModule() {
      return module;
    }

    public void setModule(JSModule module) {
      this.module = module;
CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh.statements[61]++;
    }

    boolean isCall() {
      return site.isCall();
    }

    /**
     * Get accessor for retrieving the actual node corresponding to the
     * reference.
     *
     * @return node representing the access/reference/call site
     */
    public Node getSite() {
      return site;
    }
  }
}

class CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NameReferenceGraph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$2jgcdd5rf5jr3ricemmz7uv46b0f31wr08kh () {
    super("com.google.javascript.jscomp.NameReferenceGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameReferenceGraph.java");
      for (int i = 1; i <= 61; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

