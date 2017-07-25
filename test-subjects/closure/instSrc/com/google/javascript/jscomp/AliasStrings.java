/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.common.collect.Maps;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/**
 * A {@link Compiler} pass for aliasing strings. String declarations
 * contribute to garbage collection, which becomes a problem in large
 * applications. Strings that should be aliased occur many times in the code,
 * or occur on codepaths that get executed frequently.
 *
 */
class AliasStrings extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.ping();
  }


  private static final Logger logger =
      Logger.getLogger(AliasStrings.class.getName());
  static {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[1]++;
  }

  /** Prefix for variable names for the aliased strings */
  private static final String STRING_ALIAS_PREFIX = "$$S_";
  static {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[2]++;
  }

  private final AbstractCompiler compiler;

  private final JSModuleGraph moduleGraph;

  // Regular expression matcher for a blacklisting strings in aliasing.
  private Matcher blacklist = null;
  {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[3]++;
  }

  /**
   * Strings that can be aliased, or null if all strings except 'undefined'
   * should be aliased
   */
  private final Set<String> aliasableStrings;

  private final boolean outputStringUsage;

  private final SortedMap<String, StringInfo> stringInfoMap = Maps.newTreeMap();
  {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[4]++;
  }

  private final Set<String> usedHashedAliases = new LinkedHashSet<String>();
  {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[5]++;
  }

  /**
   * Map from module to the node in that module that should parent any string
   * variable declarations that have to be moved into that module
   */
  private final Map<JSModule, Node> moduleVarParentMap =
      new HashMap<JSModule, Node>();
  {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[6]++;
  }

  /** package private.  This value is AND-ed with the hash function to allow
   * unit tests to reduce the range of hash values to test collision cases */
  long unitTestHashReductionMask = ~0L;
  {
    CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[7]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The compiler
   * @param moduleGraph The module graph, or null if there are no modules
   * @param strings Set of strings to be aliased. If null, all strings except
   *     'undefined' will be aliased.
   * @param blacklistRegex The regex to blacklist words in aliasing strings.
   * @param outputStringUsage Outputs all strings and the number of times they
   * were used in the application to the server log.
   */
  AliasStrings(AbstractCompiler compiler,
               JSModuleGraph moduleGraph,
               Set<String> strings,
               String blacklistRegex,
               boolean outputStringUsage) {
    this.compiler = compiler;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[8]++;
    this.moduleGraph = moduleGraph;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[9]++;
    this.aliasableStrings = strings;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[10]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((blacklistRegex.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[1]++;
      this.blacklist = Pattern.compile(blacklistRegex).matcher("");
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[12]++;

    } else {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[2]++;
      this.blacklist = null;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[13]++;
    }
    this.outputStringUsage = outputStringUsage;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[14]++;
  }

  @Override
  public void process(Node externs, Node root) {
    logger.fine("Aliasing common strings");
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[15]++;

    // Traverse the tree and collect strings
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[16]++;

    // 1st edit pass: replace some strings with aliases
    replaceStringsWithAliases();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[17]++;

    // 2nd edit pass: add variable declarations for aliased strings.
    addAliasDeclarationNodes();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[18]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((outputStringUsage) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[3]++;
      outputStringUsage();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[20]++;

    } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[4]++;}
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((n.isString()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent.isRegExp()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[5]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[22]++;

      String str = n.getString();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;

      // "undefined" is special-cased, since it needs to be used when JS code
      // is unloading and therefore variable references aren't available.
      // This is because of a bug in Firefox.
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (("undefined".equals(str)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[7]++;
        return;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[8]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((blacklist != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((blacklist.reset(str).find()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[9]++;
        return;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[10]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((aliasableStrings == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((aliasableStrings.contains(str)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[11]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[26]++;
        StringOccurrence occurrence = new StringOccurrence(n, parent);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[27]++;
        StringInfo info = getOrCreateStringInfo(str);

        info.occurrences.add(occurrence);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[28]++;
        info.numOccurrences++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[29]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isInThrowExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[13]++;
          info.numOccurrencesInfrequentlyExecuted++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[31]++;

        } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[14]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[32]++;

        // The current module.
        JSModule module = t.getModule();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((info.numOccurrences != 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[15]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
          // Check whether the current module depends on the module containing
          // the declaration.
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((module != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((info.moduleToContainDecl != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((module != info.moduleToContainDecl) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(module, info.moduleToContainDecl)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[17]++;
            // We need to declare this string in the deepest module in the
            // module dependency graph that both of these modules depend on.
            module = moduleGraph.getDeepestCommonDependency(
                module, info.moduleToContainDecl);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[35]++;

          } else {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[18]++;
            // use the previously saved insertion location.
            return;
          }

        } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[16]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[36]++;
        Node varParent = moduleVarParentMap.get(module);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((varParent == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[19]++;
          varParent = compiler.getNodeForCodeInsertion(module);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[38]++;
          moduleVarParentMap.put(module, varParent);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[39]++;

        } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[20]++;}
        info.moduleToContainDecl = module;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[40]++;
        info.parentForNewVarDecl = varParent;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[41]++;
        info.siblingToInsertVarDeclBefore = varParent.getFirstChild();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[42]++;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[12]++;}

    } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[6]++;}
  }

  /**
   * Looks up the {@link StringInfo} object for a JavaScript string. Creates
   * it if necessary.
   */
  private StringInfo getOrCreateStringInfo(String string) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[43]++;
    StringInfo info = stringInfoMap.get(string);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[21]++;
      info = new StringInfo(stringInfoMap.size());
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[45]++;
      stringInfoMap.put(string, info);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[46]++;

    } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[22]++;}
    return info;
  }

  /**
   * Is the {@link Node} currently within a 'throw' expression?
   */
  private static boolean isInThrowExpression(Node n) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[47]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[1]++;


    // Look up the traversal stack to find a THROW node
    for (Node ancestor : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[1]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[2]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[3]++;
}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[48]++;
      switch (ancestor.getType()) {
        case Token.THROW:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[23]++;
          return true;
        case Token.IF:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[24]++;
        case Token.WHILE:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[25]++;
        case Token.DO:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[26]++;
        case Token.FOR:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[27]++;
        case Token.SWITCH:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[28]++;
        case Token.CASE:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[29]++;
        case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[30]++;
        case Token.BLOCK:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[31]++;
        case Token.SCRIPT:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[32]++;
        case Token.FUNCTION:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[33]++;
        case Token.TRY:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[34]++;
        case Token.CATCH:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[35]++;
        case Token.RETURN:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[36]++;
        case Token.EXPR_RESULT:
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[37]++;
          // early exit - these nodes types can't be within a THROW
          return false; default : CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[38]++;
      }
    }
    return false;
  }

 /**
   * Replace strings with references to alias variables.
   */
  private void replaceStringsWithAliases() {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[49]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[4]++;


    for (Entry<String, StringInfo> entry : stringInfoMap.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[4]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[5]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[6]++;
}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[50]++;
      String literal = entry.getKey();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[51]++;
      StringInfo info = entry.getValue();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[52]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((shouldReplaceWithAlias(literal, info)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[39]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[53]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[7]++;


        for (StringOccurrence occurrence : info.occurrences) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[7]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[8]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[9]++;
}
          replaceStringWithAliasName(
              occurrence, info.getVariableName(literal), info);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[54]++;
        }

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[40]++;}
    }
  }

  /**
   * Creates a var declaration for each aliased string. Var declarations are
   * inserted as close to the first use of the string as possible.
   */
  private void addAliasDeclarationNodes() {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[55]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[10]++;


    for (Entry<String, StringInfo> entry : stringInfoMap.entrySet()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[10]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[11]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[12]++;
}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[56]++;
      StringInfo info = entry.getValue();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((info.isAliased) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[41]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[58]++;
        continue;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[42]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[59]++;
      String alias = info.getVariableName(entry.getKey());
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[60]++;
      Node var = IR.var(IR.name(alias), IR.string(entry.getKey()));
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((info.siblingToInsertVarDeclBefore == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[43]++;
        info.parentForNewVarDecl.addChildToFront(var);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[62]++;

      } else {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[44]++;
        info.parentForNewVarDecl.addChildBefore(
            var, info.siblingToInsertVarDeclBefore);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[63]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[64]++;
    }
  }

  /**
   *  Dictates the policy for replacing a string with an alias.
   *
   *  @param str The string literal
   *  @param info Accumulated information about a string
   */
  private static boolean shouldReplaceWithAlias(String str, StringInfo info) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[65]++;
int CodeCoverConditionCoverageHelper_C15;
    // Optimize for application performance.  If there are any uses of the
    // string that are not 'infrequent uses', assume they are frequent and
    // create an alias.
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((info.numOccurrences > info.numOccurrencesInfrequentlyExecuted) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[45]++;
      return true;

    } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[46]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[66]++;

    // Optimize for code size.  Are aliases smaller than strings?
    //
    // This logic optimizes for the size of uncompressed code, but it tends to
    // get good results for the size of the gzipped code too.
    //
    // gzip actually prefers that strings are not aliased - it compresses N
    // string literals better than 1 string literal and N+1 short variable
    // names, provided each string is within 32k of the previous copy.  We
    // follow the uncompressed logic as insurance against there being multiple
    // strings more than 32k apart.

    int sizeOfLiteral = 2 + str.length();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[67]++;
    int sizeOfStrings = info.numOccurrences * sizeOfLiteral;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[68]++;
    int sizeOfVariable = 3;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[69]++;
    //  '6' comes from: 'var =;' in var XXX="...";
    int sizeOfAliases = 6 + sizeOfVariable + sizeOfLiteral    // declaration
        + info.numOccurrences * sizeOfVariable;               // + uses

    return sizeOfAliases < sizeOfStrings;
  }

  /**
   * Replaces a string literal with a reference to the string's alias variable.
   */
  private void replaceStringWithAliasName(StringOccurrence occurrence,
                                          String name,
                                          StringInfo info) {
    occurrence.parent.replaceChild(occurrence.node,
                                   IR.name(name));
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[70]++;
    info.isAliased = true;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[71]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[72]++;
  }

  /**
   * Outputs a log of all strings used more than once in the code.
   */
  private void outputStringUsage() {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[73]++;
    StringBuilder sb = new StringBuilder("Strings used more than once:\n");
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[74]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[13]++;


    for (Entry<String, StringInfo> stringInfoEntry : stringInfoMap.entrySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[13]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[14]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[15]++;
}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[75]++;
      StringInfo info = stringInfoEntry.getValue();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info.numOccurrences > 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[47]++;
        sb.append(info.numOccurrences);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[77]++;
        sb.append(": ");
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[78]++;
        sb.append(stringInfoEntry.getKey());
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[79]++;
        sb.append('\n');
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[80]++;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[48]++;}
    }
    // TODO(user): Make this save to file OR output to the application
    logger.fine(sb.toString());
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[81]++;
  }

  // -------------------------------------------------------------------------

  /**
   * A class that holds the location of a single JavaScript string literal
   */
  private static final class StringOccurrence {
    final Node node;
    final Node parent;

    StringOccurrence(Node node, Node parent) {
      this.node = node;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[82]++;
      this.parent = parent;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[83]++;
    }
  }

  /**
   * A class that holds information about a JavaScript string that might become
   * aliased.
   */
  private final class StringInfo {
    final int id;

    boolean isAliased;      // set to 'true' when reference to alias created

    final List<StringOccurrence> occurrences;
    int numOccurrences;
    int numOccurrencesInfrequentlyExecuted;

    JSModule moduleToContainDecl;
    Node parentForNewVarDecl;
    Node siblingToInsertVarDeclBefore;

    String aliasName;

    StringInfo(int id) {
      this.id = id;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[84]++;
      this.occurrences = new ArrayList<StringOccurrence>();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[85]++;
      this.isAliased = false;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[86]++;
    }

    /** Returns the JS variable name to be substituted for this string. */
    String getVariableName(String stringLiteral) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[87]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((aliasName == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[49]++;
        aliasName =
            encodeStringAsIdentifier(STRING_ALIAS_PREFIX, stringLiteral);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[88]++;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[50]++;}
      return aliasName;
    }

    /**
     * Returns a legal identifier that uniquely characterizes string 's'.
     *
     * We want the identifier to be a function of the string value because that
     * makes the identifiers stable as the program is changed.
     *
     * The digits of a good hash function would be adequate, but for short
     * strings the following algorithm is easier to work with for unit tests.
     *
     * ASCII alphanumerics are mapped to themselves.  Other characters are
     * mapped to $XXX or $XXX_ where XXX is a variable number of hex digits.
     * The underscore is inserted as necessary to avoid ambiguity when the
     * character following is a hex digit. E.g. '\n1' maps to '$a_1',
     * distinguished by the underscore from '\u00A1' which maps to '$a1'.
     *
     * If the string is short enough, this is sufficient.  Longer strings are
     * truncated after encoding an initial prefix and appended with a hash
     * value.
     */
    String encodeStringAsIdentifier(String prefix, String s) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[89]++;
      // Limit to avoid generating very long identifiers
      final int MAX_LIMIT = 20;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[90]++;
      final int length = s.length();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[91]++;
      final int limit = Math.min(length, MAX_LIMIT);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[92]++;

      StringBuilder sb = new StringBuilder();
      sb.append(prefix);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[93]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[94]++;
      boolean protectHex = false;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[95]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[16]++;


int CodeCoverConditionCoverageHelper_C18;

      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[16]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[17]--;
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.loops[18]++;
}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[96]++;
        char ch = s.charAt(i);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[97]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((protectHex) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[51]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C20 |= (128)) == 0 || true) &&
 ((ch >= '0') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((ch <= '9') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((ch >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ch <= 'f') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[53]++; // toHexString generate lowercase
            sb.append('_');
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[99]++;

          } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[54]++;}
          protectHex = false;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[100]++;

        } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[52]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[101]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C21 |= (2048)) == 0 || true) &&
 ((ch >= '0') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (512)) == 0 || true) &&
 ((ch <= '9') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C21 |= (128)) == 0 || true) &&
 ((ch >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((ch <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((ch >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ch <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 6) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 6) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[55]++;
          sb.append(ch);
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[102]++;

        } else {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[56]++;
          sb.append('$');
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[103]++;
          sb.append(Integer.toHexString(ch));
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[104]++;
          protectHex = true;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[105]++;
        }
      }
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[106]++;
int CodeCoverConditionCoverageHelper_C22;

      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((length == limit) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[57]++;
        return sb.toString();

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[58]++;}
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[107]++;

      // The identifier is not unique because we omitted part, so add a
      // checksum as a hashcode.
      CRC32 crc32 = new CRC32();
      crc32.update(s.getBytes());
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[108]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[109]++;
      long hash = crc32.getValue() & unitTestHashReductionMask;
      sb.append('_');
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[110]++;
      sb.append(Long.toHexString(hash));
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[111]++;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[112]++;
      String encoded = sb.toString();
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[113]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((usedHashedAliases.add(encoded)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[59]++;
        // A collision has been detected (which is very rare). Use the sequence
        // id to break the tie. This means that the name is no longer invariant
        // across source code changes and recompilations.
        encoded += "_" + id;
CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.statements[114]++;

      } else {
  CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5.branches[60]++;}
      return encoded;
    }
  }
}

class CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5 ());
  }
    public static long[] statements = new long[115];
    public static long[] branches = new long[61];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AliasStrings.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,2,2,2,1,3,1,1,1,1,1,1,1,1,1,1,3,3,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$rjjo9p73cf74uaoicvj3mgpsv5 () {
    super("com.google.javascript.jscomp.AliasStrings.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 114; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 60; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AliasStrings.java");
      for (int i = 1; i <= 114; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 60; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

