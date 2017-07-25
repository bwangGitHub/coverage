/*
 * Copyright 2010 The Closure Compiler Authors.
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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Replaces JavaScript strings in the list of supplied methods with shortened
 * forms. Useful for replacing debug message such as: throw new
 * Error("Something bad happened"); with generated codes like: throw new
 * Error("a"); This makes the compiled JavaScript smaller and prevents us from
 * leaking details about the source code.
 *
 * Based in concept on the work by Jared Jacobs.
 */
class ReplaceStrings extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.ping();
  }

  static final DiagnosticType BAD_REPLACEMENT_CONFIGURATION =
      DiagnosticType.warning(
          "JSC_BAD_REPLACEMENT_CONFIGURATION",
          "Bad replacement configuration.");
  static {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[1]++;
  }

  private final String DEFAULT_PLACEHOLDER_TOKEN = "`";
  {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[2]++;
  }
  private final String placeholderToken;
  private static final String REPLACE_ONE_MARKER = "?";
  static {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[3]++;
  }
  private static final String REPLACE_ALL_MARKER = "*";
  static {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[4]++;
  }

  private final AbstractCompiler compiler;
  private final JSTypeRegistry registry;

  //
  private final Map<String, Config> functions = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[5]++;
  }
  private final Multimap<String, String> methods = HashMultimap.create();
  {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[6]++;
  }
  private final NameGenerator nameGenerator;
  private final Map<String, Result> results = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[7]++;
  }

  /**
   * Describes a function to look for a which parameters to replace.
   */
  private class Config {
    // TODO(johnlenz): Support name "groups" so that unrelated strings can
    // reuse strings.  For example, event-id can reuse the names used for logger
    // classes.
    final String name;
    final int parameter;
    static final int REPLACE_ALL_VALUE = 0;

    Config(String name, int parameter) {
      this.name = name;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[8]++;
      this.parameter = parameter;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[9]++;
    }
  }

  /**
   * Describes a replacement that occurred.
   */
  class Result {
    // The original message with non-static content replaced with
    // {@code placeholderToken}.
    public final String original;
    public final String replacement;
    public final List<Location> replacementLocations = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[10]++;
  }

    Result(String original, String replacement) {
      this.original = original;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[11]++;
      this.replacement = replacement;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[12]++;
    }

    void addLocation(Node n) {
      replacementLocations.add(new Location(
          n.getSourceFileName(),
          n.getLineno(), n.getCharno()));
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[13]++;
    }
  }

  /** Represent a source location where a replacement occurred. */
  class Location {
    public final String sourceFile;
    public final int line;
    public final int column;
    Location(String sourceFile, int line, int column) {
      this.sourceFile = sourceFile;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[14]++;
      this.line = line;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[15]++;
      this.column = column;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[16]++;
    }
  }

  /**
   * @param placeholderToken Separator to use between string parts. Used to replace
   *     non-static string content.
   * @param functionsToInspect A list of function configurations in the form of
   *     function($,,,)
   *   or
   *     class.prototype.method($,,,)
   * @param blacklisted A set of names that should not be used as replacement
   *     strings.  Useful to prevent unwanted strings for appearing in the
   *     final output.
   * where '$' is used to indicate which parameter should be replaced.
   */
  ReplaceStrings(
      AbstractCompiler compiler, String placeholderToken,
      List<String> functionsToInspect,
      Set<String> blacklisted,
      VariableMap previousMappings) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[17]++;
    this.placeholderToken = placeholderToken.isEmpty()
        ? DEFAULT_PLACEHOLDER_TOKEN : placeholderToken;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[18]++;
    this.registry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[19]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[20]++;

    Iterable<String> reservedNames = blacklisted;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[21]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((previousMappings != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[1]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[22]++;
      Set<String> previous =
          previousMappings.getOriginalNameToNewNameMap().keySet();
      reservedNames = Iterables.concat(blacklisted, previous);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[23]++;
      initMapping(previousMappings, blacklisted);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[24]++;

    } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[2]++;}
    this.nameGenerator = createNameGenerator(reservedNames);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[25]++;

    // Initialize the map of functions to inspect for renaming candidates.
    parseConfiguration(functionsToInspect);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[26]++;
  }

  private void initMapping(
      VariableMap previousVarMap, Set<String> reservedNames) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[27]++;
    Map<String,String> previous = previousVarMap.getOriginalNameToNewNameMap();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[1]++;


    for (Map.Entry<String,String> entry : previous.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[1]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[2]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[3]++;
}
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[29]++;
      String key = entry.getKey();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[30]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((reservedNames.contains(key)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[3]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[31]++;
        String value = entry.getValue();
        results.put(value, new Result(value, key));
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[32]++;

      } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[4]++;}
    }
  }

  static final Predicate<Result> USED_RESULTS = new Predicate<Result>() {
    @Override
    public boolean apply(Result result) {
      // The list of locations may be empty if the map
      // was pre-populated from a previous map.
      return !result.replacementLocations.isEmpty();
    }
  };
  static {
    CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[33]++;
  }

  // Get the list of all replacements performed.
  List<Result> getResult() {
    return ImmutableList.copyOf(
        Iterables.filter(results.values(), USED_RESULTS));
  }

  // Get the list of replaces as a VariableMap
  VariableMap getStringMap() {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[34]++;
    ImmutableMap.Builder<String, String> map = ImmutableMap.builder();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[35]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[4]++;


    for (Result result : Iterables.filter(results.values(), USED_RESULTS)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[4]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[5]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[6]++;
}
      map.put(result.replacement, result.original);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[36]++;
    }
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[37]++;

    VariableMap stringMap = new VariableMap(map.build());
    return stringMap;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[38]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[39]++;
    // TODO(johnlenz): Determine if it is necessary to support ".call" or
    // ".apply".
    switch (n.getType()) {
      case Token.NEW:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[5]++; // e.g. new Error('msg');
      case Token.CALL:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[6]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[40]++; // e.g. Error('msg');
        Node calledFn = n.getFirstChild();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[41]++;

        // Look for calls to static functions.
        String name = calledFn.getQualifiedName();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[42]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[7]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[43]++;
          Config config = findMatching(name);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[44]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((config != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[9]++;
            doSubstitutions(t, config, n);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[45]++;
            return;

          } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[8]++;}
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[46]++;
int CodeCoverConditionCoverageHelper_C5;

        // Look for calls to class methods.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(calledFn)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[11]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[47]++;
          Node rhs = calledFn.getLastChild();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[48]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((rhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rhs.isString()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[13]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[49]++;
            String methodName = rhs.getString();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[50]++;
            Collection<String> classes = methods.get(methodName);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((classes != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[15]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[52]++;
              Node lhs = calledFn.getFirstChild();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
              if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lhs.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[17]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[54]++;
                JSType type = lhs.getJSType().restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[55]++;
                Config config = findMatchingClass(type, classes);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((config != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[19]++;
                  doSubstitutions(t, config, n);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[57]++;
                  return;

                } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[20]++;}

              } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[18]++;}

            } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[16]++;}

          } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[12]++;}
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[58]++;
        break; default : CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[21]++;
    }
  }

  /**
   * @param name The function name to find.
   * @return The Config object for the name or null if no match was found.
   */
  private Config findMatching(String name) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[59]++;
    Config config = functions.get(name);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[60]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((config == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[22]++;
      name = name.replace('$', '.');
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[61]++;
      config = functions.get(name);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[62]++;

    } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[23]++;}
    return config;
  }

  /**
   * @return The Config object for the class match the specified type or null
   * if no match was found.
   */
  private Config findMatchingClass(
      JSType callClassType, Collection<String> declarationNames) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((callClassType.isNoObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((callClassType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[24]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[64]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[7]++;


      for (String declarationName : declarationNames) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[7]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[8]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[9]++;
}
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[65]++;
        String className = getClassFromDeclarationName(declarationName);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[66]++;
        JSType methodClassType = registry.getType(className);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[67]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((methodClassType != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((callClassType.isSubtype(methodClassType)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[26]++;
          return functions.get(declarationName);

        } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[27]++;}
      }

    } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[25]++;}
    return null;
  }

  /**
   * Replace the parameters specified in the config, if possible.
   */
  private void doSubstitutions(NodeTraversal t, Config config, Node n) {
    Preconditions.checkState(
        n.isNew() || n.isCall());
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[68]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[69]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((config.parameter != Config.REPLACE_ALL_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[28]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[70]++;
      // Note: the first child is the function, but the parameter id is 1 based.
      Node arg = n.getChildAtIndex(config.parameter);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[71]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[30]++;
        replaceExpression(t, arg, n);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[72]++;

      } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[31]++;}

    } else {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[29]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[73]++;
      // Replace all parameters.
      Node firstParam = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[74]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;
      for (Node arg = firstParam;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); arg = arg.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[10]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[11]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[12]++;
}
        arg = replaceExpression(t, arg, n);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[75]++;
      }
    }
  }

  /**
   * Replaces a string expression with a short encoded string expression.
   *
   * @param t The traversal
   * @param expr The expression node
   * @param parent The expression node's parent
   * @return The replacement node (or the original expression if no replacement
   *         is made)
   */
  private Node replaceExpression(NodeTraversal t, Node expr, Node parent) {
    Node replacement;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[76]++;
    String key = null;
    String replacementString;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[77]++;
    switch (expr.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[32]++;
        key = expr.getString();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[78]++;
        replacementString = getReplacement(key);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[79]++;
        replacement = IR.string(replacementString);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[80]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[81]++;
        break;
      case Token.ADD:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[33]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[82]++;
        StringBuilder keyBuilder = new StringBuilder();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[83]++;
        Node keyNode = IR.string("");
        replacement = buildReplacement(expr, keyNode, keyBuilder);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[84]++;
        key = keyBuilder.toString();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[85]++;
        replacementString = getReplacement(key);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[86]++;
        keyNode.setString(replacementString);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[87]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[88]++;
        break;
      case Token.NAME:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[34]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[89]++;
        // If the referenced variable is a constant, use its value.
        Scope.Var var = t.getScope().getVar(expr.getString());
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[90]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((var.isConst()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[35]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[91]++;
          Node value = var.getInitialValue();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[92]++;
int CodeCoverConditionCoverageHelper_C17;
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value.isString()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[37]++;
            key = value.getString();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[93]++;
            replacementString = getReplacement(key);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[94]++;
            replacement = IR.string(replacementString);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[95]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[96]++;
            break;

          } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[38]++;}

        } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[36]++;}
        return expr;
      default:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[39]++;
        // This may be a function call or a variable reference. We don't
        // replace these.
        return expr;
    }

    Preconditions.checkNotNull(key);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[97]++;
    Preconditions.checkNotNull(replacementString);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[98]++;
    recordReplacement(expr, key);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[99]++;

    parent.replaceChild(expr, replacement);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[100]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[101]++;
    return replacement;
  }

  /**
   * Get a replacement string for the provide key text.
   */
  private String getReplacement(String key) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[102]++;
    Result result = results.get(key);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[103]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[40]++;
      return result.replacement;

    } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[41]++;}
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[104]++;

    String replacement = nameGenerator.generateNextName();
    result = new Result(key, replacement);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[105]++;
    results.put(key, result);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[106]++;
    return replacement;
  }

  /**
   * Record the location the replacement was made.
   */
  private void recordReplacement(Node n, String key) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[107]++;
    Result result = results.get(key);
    Preconditions.checkState(result != null);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[108]++;

    result.addLocation(n);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[109]++;
  }

  /**
   * Builds a replacement abstract syntax tree for the string expression {@code
   * expr}. Appends any string literal values that are encountered to
   * {@code keyBuilder}, to build the expression's replacement key.
   *
   * @param expr A JS expression that evaluates to a string value
   * @param prefix The JS expression to which {@code expr}'s replacement is
   *        logically being concatenated. It is a partial solution to the
   *        problem at hand and will either be this method's return value or a
   *        descendant of it.
   * @param keyBuilder A builder of the string expression's replacement key
   * @return The abstract syntax tree that should replace {@code expr}
   */
  private Node buildReplacement(
      Node expr, Node prefix, StringBuilder keyBuilder) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[110]++;
    switch (expr.getType()) {
      case Token.ADD:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[42]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[111]++;
        Node left = expr.getFirstChild();
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[112]++;
        Node right = left.getNext();
        prefix = buildReplacement(left, prefix, keyBuilder);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[113]++;
        return buildReplacement(right, prefix, keyBuilder);
      case Token.STRING:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[43]++;
        keyBuilder.append(expr.getString());
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[114]++;
        return prefix;
      default:
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[44]++;
        keyBuilder.append(placeholderToken);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[115]++;
        prefix = IR.add(prefix, IR.string(placeholderToken));
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[116]++;
        return IR.add(prefix, expr.cloneTree());
    }
  }

  /**
   * From a provide name extract the method name.
   */
  private String getMethodFromDeclarationName(String fullDeclarationName) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[117]++;
    String[] parts = fullDeclarationName.split("\\.prototype\\.");
    Preconditions.checkState(parts.length == 1 || parts.length == 2);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[118]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[119]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((parts.length == 2) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[45]++;
      return parts[1];

    } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[46]++;}
    return null;
  }

  /**
   * From a provide name extract the class name.
   */
  private String getClassFromDeclarationName(String fullDeclarationName) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[120]++;
    String[] parts = fullDeclarationName.split("\\.prototype\\.");
    Preconditions.checkState(parts.length == 1 || parts.length == 2);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[121]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[122]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((parts.length == 2) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[47]++;
      return parts[0];

    } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[48]++;}
    return null;
  }

  /**
   * Build the data structures need by this pass from the provided
   * list of functions and methods.
   */
  private void parseConfiguration(List<String> functionsToInspect) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[123]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[13]++;


    for (String function : functionsToInspect) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[13]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[14]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[15]++;
}
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[124]++;
      Config config = parseConfiguration(function);
      functions.put(config.name, config);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[125]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[126]++;

      String method = getMethodFromDeclarationName(config.name);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[127]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((method != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[49]++;
        methods.put(method, config.name);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[128]++;

      } else {
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[50]++;}
    }
  }

  /**
   * Convert the provide string into a Config.  The string can be a static function:
   *    foo(,,?)
   *    foo.bar(?)
   * or a class method:
   *    foo.prototype.bar(?)
   * And is allowed to either replace all parameters using "*" or one parameter "?".
   * "," is used as a placeholder for ignored parameters.
   */
  private Config parseConfiguration(String function) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[129]++;
    // Looks like this function_name(,$,)
    int first = function.indexOf('(');
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[130]++;
    int last = function.indexOf(')');

    // TODO(johnlenz): Make parsing precondition checks JSErrors reports.
    Preconditions.checkState(first != -1 && last != -1);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[131]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[132]++;

    String name = function.substring(0, first);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[133]++;
    String params = function.substring(first+1, last);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[134]++;

    int paramCount = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[135]++;
    int replacementParameter = -1;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[136]++;
    String[] parts = params.split(",");
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[137]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[16]++;


    for (String param : parts) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[16]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[17]--;
  CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.loops[18]++;
}
      paramCount++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[138]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[139]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((param.equals(REPLACE_ALL_MARKER)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[51]++;
        Preconditions.checkState(paramCount == 1 && parts.length == 1);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[140]++;
        replacementParameter = Config.REPLACE_ALL_VALUE;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[141]++;

      } else {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[52]++;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[142]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((param.equals(REPLACE_ONE_MARKER)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[53]++;
        // TODO(johnlenz): Support multiple.
        Preconditions.checkState(replacementParameter == -1);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[143]++;
        replacementParameter = paramCount;
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[144]++;

      } else {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.branches[54]++;
        // TODO(johnlenz): report an error.
        Preconditions.checkState(param.isEmpty(), "Unknown marker", param);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[145]++;
      }
}
    }

    Preconditions.checkState(replacementParameter != -1);
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[146]++;
    return new Config(name, replacementParameter);
  }

  /**
   * Use a name generate to create names so the names overlap with the names
   * used for variable and properties.
   */
  private static NameGenerator createNameGenerator(Iterable<String> reserved) {
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[147]++;
    final String namePrefix = "";
CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht.statements[148]++;
    final char[] reservedChars = new char[0];
    return new NameGenerator(
        ImmutableSet.copyOf(reserved), namePrefix, reservedChars);
  }
}

class CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht ());
  }
    public static long[] statements = new long[149];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReplaceStrings.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,2,2,1,1,1,2,2,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$1cq4bfo3nyaz2nhwklofyinaupcuht () {
    super("com.google.javascript.jscomp.ReplaceStrings.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 148; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ReplaceStrings.java");
      for (int i = 1; i <= 148; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
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

