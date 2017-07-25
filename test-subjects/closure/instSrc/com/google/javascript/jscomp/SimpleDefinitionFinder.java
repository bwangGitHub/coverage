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
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.DefinitionsRemover.ExternalNameOnlyDefinition;
import com.google.javascript.jscomp.DefinitionsRemover.UnknownDefinition;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Simple name-based definition gatherer that implements
 * {@link DefinitionProvider}.
 *
 * It treats all variable writes as happening in the global scope and
 * treats all objects as capable of having the same set of properties.
 * The current implementation only handles definitions whose right
 * hand side is an immutable value or function expression.  All
 * complex definitions are treated as unknowns.
 *
 */
class SimpleDefinitionFinder implements CompilerPass, DefinitionProvider {
  static {
    CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.ping();
  }

  private final AbstractCompiler compiler;
  private final Map<Node, DefinitionSite> definitionSiteMap;
  private final Multimap<String, Definition> nameDefinitionMultimap;
  private final Multimap<String, UseSite> nameUseSiteMultimap;

  public SimpleDefinitionFinder(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[1]++;
    this.definitionSiteMap = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[2]++;
    this.nameDefinitionMultimap = LinkedHashMultimap.create();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[3]++;
    this.nameUseSiteMultimap = LinkedHashMultimap.create();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[4]++;
  }

  /**
   * Returns the collection of definition sites found during traversal.
   *
   * @return definition site collection.
   */
  public Collection<DefinitionSite> getDefinitionSites() {
    return definitionSiteMap.values();
  }

  private DefinitionSite getDefinitionAt(Node node) {
    return definitionSiteMap.get(node);
  }

  DefinitionSite getDefinitionForFunction(Node function) {
    Preconditions.checkState(function.isFunction());
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[5]++;
    return getDefinitionAt(getNameNodeFromFunctionNode(function));
  }

  @Override
  public Collection<Definition> getDefinitionsReferencedAt(Node useSite) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((definitionSiteMap.containsKey(useSite)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[1]++;
      return null;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[2]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((useSite.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[3]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[8]++;
      String propName = useSite.getLastChild().getString();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((propName.equals("apply")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((propName.equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[5]++;
        useSite = useSite.getFirstChild();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[10]++;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[4]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[11]++;

    String name = getSimplifiedName(useSite);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[7]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[13]++;
      Collection<Definition> defs = nameDefinitionMultimap.get(name);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((defs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[9]++;
        return defs;

      } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[10]++;
        return null;
      }

    } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[8]++;
      return null;
    }
  }

  @Override
  public void process(Node externs, Node source) {
    NodeTraversal.traverse(
        compiler, externs, new DefinitionGatheringCallback(true));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[15]++;
    NodeTraversal.traverse(
        compiler, source, new DefinitionGatheringCallback(false));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[16]++;
    NodeTraversal.traverse(
        compiler, source, new UseSiteGatheringCallback());
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[17]++;
  }

  /**
   * Returns a collection of use sites that may refer to provided
   * definition.  Returns an empty collection if the definition is not
   * used anywhere.
   *
   * @param definition Definition of interest.
   * @return use site collection.
   */
  Collection<UseSite> getUseSites(Definition definition) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[18]++;
    String name = getSimplifiedName(definition.getLValue());
    return nameUseSiteMultimap.get(name);
  }

  /**
   * Extract a name from a node.  In the case of GETPROP nodes,
   * replace the namespace or object expression with "this" for
   * simplicity and correctness at the expense of inefficiencies due
   * to higher chances of name collisions.
   *
   * TODO(user) revisit.  it would be helpful to at least use fully
   * qualified names in the case of namespaces.  Might not matter as
   * much if this pass runs after "collapsing properties".
   */
  private static String getSimplifiedName(Node node) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[11]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[20]++;
      String name = node.getString();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[13]++;
        return name;

      } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[14]++;
        return null;
      }

    } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[12]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[22]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[15]++;
      return "this." + node.getLastChild().getString();

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[16]++;}
}
    return null;
  }

  private class DefinitionGatheringCallback extends AbstractPostOrderCallback {
    private boolean inExterns;

    DefinitionGatheringCallback(boolean inExterns) {
      this.inExterns = inExterns;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[23]++;
    }

    @Override
    public void visit(NodeTraversal traversal, Node node, Node parent) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
      // Arguments of external functions should not count as name
      // definitions.  They are placeholder names for documentation
      // purposes only which are not reachable from anywhere.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[17]++;
        return;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[18]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[25]++;

      Definition def =
          DefinitionsRemover.getDefinition(node, inExterns);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((def != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[19]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[27]++;
        String name = getSimplifiedName(def.getLValue());
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[21]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[29]++;
          Node rValue = def.getRValue();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[30]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[23]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[31]++;

            // Unhandled complex expression
            Definition unknownDef =
                new UnknownDefinition(def.getLValue(), inExterns);
            def = unknownDef;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[32]++;

          } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[24]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;

          // TODO(johnlenz) : remove this stub dropping code if it becomes
          // illegal to have untyped stubs in the externs definitions.
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[25]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[34]++;
            // We need special handling of untyped externs stubs here:
            //    the stub should be dropped if the name is provided elsewhere.

            List<Definition> stubsToRemove = Lists.newArrayList();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[35]++;
            String qualifiedName = node.getQualifiedName();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[36]++;
int CodeCoverConditionCoverageHelper_C14;

            // If there is no qualified name for this, then there will be
            // no stubs to remove. This will happen if node is an object
            // literal key.
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[27]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[37]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[1]++;


              for (Definition prevDef : nameDefinitionMultimap.get(name)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[1]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[2]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[3]++;
}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((prevDef instanceof ExternalNameOnlyDefinition) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((jsdocContainsDeclarations(node)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[29]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[39]++;
                  String prevName = prevDef.getLValue().getQualifiedName();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[40]++;
int CodeCoverConditionCoverageHelper_C16;
                  if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((qualifiedName.equals(prevName)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[31]++;
                    // Drop this stub, there is a real definition.
                    stubsToRemove.add(prevDef);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[41]++;

                  } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[32]++;}

                } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[30]++;}
              }
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[42]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[4]++;



              for (Definition prevDef : stubsToRemove) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[4]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[5]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[6]++;
}
                nameDefinitionMultimap.remove(name, prevDef);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[43]++;
              }

            } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[28]++;}

          } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[26]++;}

          nameDefinitionMultimap.put(name, def);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[44]++;
          definitionSiteMap.put(node,
                                new DefinitionSite(node,
                                                   def,
                                                   traversal.getModule(),
                                                   traversal.inGlobalScope(),
                                                   inExterns));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[45]++;

        } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[22]++;}

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[20]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[46]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[33]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[47]++;
        String name = getSimplifiedName(node);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[35]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[49]++;

          // TODO(johnlenz) : remove this code if it becomes illegal to have
          // stubs in the externs definitions.

          // We need special handling of untyped externs stubs here:
          //    the stub should be dropped if the name is provided elsewhere.
          // We can't just drop the stub now as it needs to be used as the
          //    externs definition if no other definition is provided.

          boolean dropStub = false;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[50]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((jsdocContainsDeclarations(node)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[37]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[51]++;
            String qualifiedName = node.getQualifiedName();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[52]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[39]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[53]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[7]++;


              for (Definition prevDef : nameDefinitionMultimap.get(name)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[7]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[8]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[9]++;
}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[54]++;
                String prevName = prevDef.getLValue().getQualifiedName();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[55]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((qualifiedName.equals(prevName)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[41]++;
                  dropStub = true;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[56]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[57]++;
                  break;

                } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[42]++;}
              }

            } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[40]++;}

          } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[38]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[58]++;
int CodeCoverConditionCoverageHelper_C22;

          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((dropStub) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[43]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[59]++;
            // Incomplete definition
            Definition definition = new ExternalNameOnlyDefinition(node);
            nameDefinitionMultimap.put(name, definition);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[60]++;
            definitionSiteMap.put(node,
                                  new DefinitionSite(node,
                                                     definition,
                                                     traversal.getModule(),
                                                     traversal.inGlobalScope(),
                                                     inExterns));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[61]++;

          } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[36]++;}

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[34]++;}
    }

    /**
     * @return Whether the node has a JSDoc that actually declares something.
     */
    private boolean jsdocContainsDeclarations(Node node) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[62]++;
      JSDocInfo info = node.getJSDocInfo();
      return (info != null && info.containsDeclaration());
    }
  }

  private class UseSiteGatheringCallback extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal traversal, Node node, Node parent) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[63]++;

      Collection<Definition> defs = getDefinitionsReferencedAt(node);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[64]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((defs == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[45]++;
        return;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[46]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[65]++;

      Definition first = defs.iterator().next();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[66]++;

      String name = getSimplifiedName(first.getLValue());
      Preconditions.checkNotNull(name);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[67]++;
      nameUseSiteMultimap.put(
          name,
          new UseSite(node, traversal.getScope(), traversal.getModule()));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[68]++;
    }
  }

  /**
   * @param use A use site to check.
   * @return Whether the use is a call or new.
   */
  static boolean isCallOrNewSite(UseSite use) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[69]++;
    Node call = use.node.getParent();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[70]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((call == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[47]++;
      // The node has been removed from the AST.
      return false;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[48]++;}
    // We need to make sure we're dealing with a call to the function we're
    // optimizing. If the the first child of the parent is not the site, this
    // is a nested call and it's a call to another function.
    return NodeUtil.isCallOrNew(call) && call.getFirstChild() == use.node;
  }

  boolean canModifyDefinition(Definition definition) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[71]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isExported(definition)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[49]++;
      return false;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[50]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[72]++;

    // Don't modify unused definitions for two reasons:
    // 1) It causes unnecessary churn
    // 2) Other definitions might be used to reflect on this one using
    //    goog.reflect.object (the check for definitions with uses is below).
    Collection<UseSite> useSites = getUseSites(definition);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[73]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((useSites.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[51]++;
      return false;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[52]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[74]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[10]++;



    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[10]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[11]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[12]++;
}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[75]++;
      // This catches the case where an object literal in goog.reflect.object
      // and a prototype method have the same property name.

      // NOTE(nicksantos): Maps and trogedit both do this by different
      // mechanisms.

      Node nameNode = site.node;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[76]++;
      Collection<Definition> singleSiteDefinitions =
          getDefinitionsReferencedAt(nameNode);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((singleSiteDefinitions.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[53]++;
        return false;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[54]++;}

      Preconditions.checkState(!singleSiteDefinitions.isEmpty());
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[78]++;
      Preconditions.checkState(singleSiteDefinitions.contains(definition));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[79]++;
    }

    return true;
  }

  /**
   * @return Whether the definition is directly exported.
   */
  private boolean isExported(Definition definition) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[80]++;
    // Assume an exported method result is used.
    Node lValue = definition.getLValue();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[81]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((lValue == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[55]++;
      return true;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[56]++;}

    String partialName;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[82]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((lValue.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[57]++;
      partialName = lValue.getLastChild().getString();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[83]++;

    } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[58]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[84]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((lValue.isName()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[59]++;
      partialName = lValue.getString();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[85]++;

    } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[60]++;
      // GETELEM is assumed to be an export or other expression are unknown
      // uses.
      return true;
    }
}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[86]++;

    CodingConvention codingConvention = compiler.getCodingConvention();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[87]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((codingConvention.isExported(partialName)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[61]++;
      return true;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[62]++;}

    return false;
  }

  /**
   * @return Whether the function is defined in a non-aliasing expression.
   */
  static boolean isSimpleFunctionDeclaration(Node fn) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[88]++;
    Node parent = fn.getParent();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[89]++;
    Node gramps = parent.getParent();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[90]++;

    // Simple definition finder doesn't provide useful results in some
    // cases, specifically:
    //  - functions with recursive definitions
    //  - functions defined in object literals
    //  - functions defined in array literals
    // Here we defined a set of known function declaration that are 'ok'.

    // Some projects seem to actually define "JSCompiler_renameProperty"
    // rather than simply having an extern definition.  Don't mess with it.
    Node nameNode = SimpleDefinitionFinder.getNameNodeFromFunctionNode(fn);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[91]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((nameNode != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((nameNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[63]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[92]++;
      String name = nameNode.getString();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[93]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((name.equals(NodeUtil.JSC_PROPERTY_NAME_FN)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((name.equals(
                ObjectPropertyStringPreprocess.EXTERN_OBJECT_PROPERTY_STRING)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[65]++;
        return false;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[66]++;}

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[64]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[94]++;
int CodeCoverConditionCoverageHelper_C34;

    // example: function a(){};
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(fn)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[67]++;
      return true;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[68]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[95]++;
int CodeCoverConditionCoverageHelper_C35;

    // example: a = function(){};
    // example: var a = function(){};
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (32)) == 0 || true) &&
 ((fn.getFirstChild().getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((NodeUtil.isExprAssign(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[69]++;
      return true;

    } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[70]++;}

    return false;
  }

  /**
   * @return the node defining the name for this function (if any).
   */
  static Node getNameNodeFromFunctionNode(Node function) {
    Preconditions.checkState(function.isFunction());
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[96]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[97]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(function)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[71]++;
      return function.getFirstChild();

    } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[72]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[98]++;
      Node parent = function.getParent();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[99]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(parent)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[73]++;
        return parent;

      } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[74]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[100]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[75]++;
        return parent.getFirstChild();

      } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[76]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[101]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(parent)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[77]++;
        return parent;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[78]++;}
}
}
    }
    return null;
  }

  /**
   * Traverse a node and its children and remove any references to from
   * the structures.
   */
  void removeReferences(Node node) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[102]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((DefinitionsRemover.isDefinitionNode(node)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[79]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[103]++;
      DefinitionSite defSite = definitionSiteMap.get(node);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[104]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((defSite != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[81]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[105]++;
        Definition def = defSite.definition;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[106]++;
        String name = getSimplifiedName(def.getLValue());
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[107]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[83]++;
          this.definitionSiteMap.remove(node);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[108]++;
          this.nameDefinitionMultimap.remove(name, node);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[109]++;

        } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[84]++;}

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[82]++;}

    } else {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[80]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[110]++;
      Node useSite = node;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[111]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((useSite.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[85]++;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[112]++;
        String propName = useSite.getLastChild().getString();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[113]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((propName.equals("apply")) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((propName.equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[87]++;
          useSite = useSite.getFirstChild();
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[114]++;

        } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[88]++;}

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[86]++;}
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[115]++;
      String name = getSimplifiedName(useSite);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[116]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[89]++;
        this.nameUseSiteMultimap.remove(name, new UseSite(useSite, null, null));
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[117]++;

      } else {
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.branches[90]++;}
    }
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[118]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[13]++;



    for (Node child : node.children()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[13]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[14]--;
  CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.loops[15]++;
}
      removeReferences(child);
CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl.statements[119]++;
    }
  }
}

class CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl ());
  }
    public static long[] statements = new long[120];
    public static long[] branches = new long[91];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[46];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SimpleDefinitionFinder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,2,1,3,1,1,3,1,1,2,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,3,1,1,1,1,1,1,1,1,2,1};
    for (int i = 1; i <= 45; i++) {
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

  public CodeCoverCoverageCounter$5c11ih66y44it23gjn5ils51fde3derit7r62f7wbl () {
    super("com.google.javascript.jscomp.SimpleDefinitionFinder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 119; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 90; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SimpleDefinitionFinder.java");
      for (int i = 1; i <= 119; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 90; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 45; i++) {
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

