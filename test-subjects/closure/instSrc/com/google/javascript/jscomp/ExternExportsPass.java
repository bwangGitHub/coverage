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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Creates an externs file containing all exported symbols and properties
 * for later consumption.
 *
 */
final class ExternExportsPass extends NodeTraversal.AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.ping();
  }


  static final DiagnosticType EXPORTED_FUNCTION_UNKNOWN_PARAMETER_TYPE =
    DiagnosticType.warning(
        "JSC_EXPORTED_FUNCTION_UNKNOWN_PARAMETER_TYPE",
        "Unable to determine type of parameter {0} for exported function {1}");
  static {
    CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[1]++;
  }

  static final DiagnosticType EXPORTED_FUNCTION_UNKNOWN_RETURN_TYPE =
    DiagnosticType.warning(
        "JSC_EXPORTED_FUNCTION_UNKNOWN_RETURN_TYPE",
        "Unable to determine return type for exported function {0}");
  static {
    CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[2]++;
  }

  /** The exports found. */
  private final List<Export> exports;

  /** A map of all assigns to their parent nodes. */
  private final Map<String, Node> definitionMap;

  /** The parent compiler. */
  private final AbstractCompiler compiler;

  /** The AST root which holds the externs generated. */
  private final Node externsRoot;

  /** A mapping of internal paths to exported paths. */
  private final Map<String, String> mappedPaths;

  /** A list of exported paths. */
  private final Set<String> alreadyExportedPaths;

  /** A list of function names used to export symbols. */
  private List<String> exportSymbolFunctionNames;

  /** A list of function names used to export properties. */
  private List<String> exportPropertyFunctionNames;

  private abstract class Export {
    protected final String symbolName;
    protected final Node value;

    Export(String symbolName, Node value) {
      this.symbolName = symbolName;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[3]++;
      this.value = value;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[4]++;
    }

    /**
     * Generates the externs representation of this export and appends
     * it to the externsRoot AST.
     */
    void generateExterns() {
      appendExtern(getExportedPath(), getValue(value));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[5]++;
    }

    /**
     * Returns the path exported by this export.
     */
    abstract String getExportedPath();

    /**
     * Appends the exported function and all paths necessary for the path to be
     * declared. For example, for a property "a.b.c", the initializers for
     * paths "a", "a.b" will be appended (if they have not already) and a.b.c
     * will be initialized with the exported version of the function:
     * <pre>
     * var a = {};
     * a.b = {};
     * a.b.c = function(x,y) { }
     * </pre>
     */
    void appendExtern(String path, Node valueToExport) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[6]++;
      List<String> pathPrefixes = computePathPrefixes(path);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < pathPrefixes.size()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[1]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[2]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[3]++;
}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[8]++;
        String pathPrefix = pathPrefixes.get(i);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[9]++;

        /* The complete path (the last path prefix) must be emitted and
         * it gets initialized to the externed version of the value.
         */
        boolean isCompletePathPrefix = (i == pathPrefixes.size() - 1);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[10]++;

        boolean skipPathPrefix = pathPrefix.endsWith(".prototype")
            || (alreadyExportedPaths.contains(pathPrefix)
                && !isCompletePathPrefix);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((skipPathPrefix) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[1]++;
           Node initializer;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;

          /* Namespaces get initialized to {}, functions to
           * externed versions of their value, and if we can't
           * figure out where the value came from we initialize
           * it to {}.
           *
           * Since externs are always exported in sorted order,
           * we know that if we export a.b = function() {} and later
           * a.b.c = function then a.b will always be in alreadyExportedPaths
           * when we emit a.b.c and thus we will never overwrite the function
           * exported for a.b with a namespace.
           */

          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((isCompletePathPrefix) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((valueToExport != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[3]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((valueToExport.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[5]++;
              initializer = createExternFunction(valueToExport);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[14]++;

            } else {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[6]++;
              Preconditions.checkState(valueToExport.isObjectLit());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[15]++;
              initializer = createExternObjectLit(valueToExport);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[16]++;
            }

          } else {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[4]++;
            initializer = IR.empty();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[17]++;
          }

          appendPathDefinition(pathPrefix, initializer);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[18]++;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[2]++;}
      }
    }

    /**
     * Computes a list of the path prefixes constructed from the components
     * of the path.
     * <pre>
     * E.g., if the path is:
     *      "a.b.c"
     * then then path prefixes will be
     *    ["a","a.b","a.b.c"]:
     * </pre>
     */
    private List<String> computePathPrefixes(String path) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[19]++;
      List<String> pieces = Lists.newArrayList(path.split("\\."));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[20]++;

      List<String> pathPrefixes = Lists.newArrayList();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;

      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < pieces.size()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[4]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[5]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[6]++;
}
        pathPrefixes.add(Joiner.on(".").join(Iterables.limit(pieces, i + 1)));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[22]++;
      }

      return pathPrefixes;
    }

    private void appendPathDefinition(String path, Node initializer) {
      Node pathDefinition;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((path.contains(".")) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[7]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((initializer.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[9]++;
          pathDefinition = IR.var(IR.name(path));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[25]++;

        } else {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[10]++;
          pathDefinition = NodeUtil.newVarNode(path, initializer);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[26]++;
        }

      } else {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[8]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[27]++;
        Node qualifiedPath = NodeUtil.newQualifiedNameNode(
            compiler.getCodingConvention(), path);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((initializer.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[11]++;
          pathDefinition = NodeUtil.newExpr(qualifiedPath);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[29]++;

        } else {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[12]++;
          pathDefinition = NodeUtil.newExpr(
              IR.assign(qualifiedPath, initializer));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[30]++;
        }
      }

      externsRoot.addChildToBack(pathDefinition);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[31]++;

      alreadyExportedPaths.add(path);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[32]++;
    }

    /**
     * Given a function to export, create the empty function that
     * will be put in the externs file. This extern function should have
     * the same type as the original function and the same parameter
     * name but no function body.
     *
     * We create a warning here if the the function to export is missing
     * parameter or return types.
     */
    private Node createExternFunction(Node exportedFunction) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[33]++;
      Node paramList = NodeUtil.getFunctionParameters(exportedFunction)
          .cloneTree();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[34]++;
      Node externFunction = IR.function(IR.name(""), paramList, IR.block());

      checkForFunctionsWithUnknownTypes(exportedFunction);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[35]++;
      externFunction.setJSType(exportedFunction.getJSType());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[36]++;

      return externFunction;
    }

    /**
     * Given an object literal to export, create an object lit with all its
     * string properties. We don't care what the values of those properties
     * are because they are not checked.
     */
    private Node createExternObjectLit(Node exportedObjectLit) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[37]++;
      Node lit = IR.objectlit();
      lit.setJSType(exportedObjectLit.getJSType());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[38]++;

      // This is an indirect way of telling the typed code generator
      // "print the type of this"
      lit.setJSDocInfo(new JSDocInfo());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[39]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[40]++;

      int index = 1;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[41]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
      for (Node child = exportedObjectLit.getFirstChild();(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false);
           child = child.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[7]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[8]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[9]++;
}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        // TODO: handle getters or setters?
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((child.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[13]++;
          lit.addChildToBack(
              IR.propdef(
                  IR.stringKey(child.getString()),
                  IR.number(index++)));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[43]++;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[14]++;}
      }
      return lit;
    }

    /**
     * Warn the user if there is an exported function for which a parameter
     * or return type is unknown.
     */
    private void checkForFunctionsWithUnknownTypes(Node function) {
      Preconditions.checkArgument(function.isFunction());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[44]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[45]++;

      FunctionType functionType =
          JSType.toMaybeFunctionType(function.getJSType());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;

      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((functionType == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[15]++;
        // No type information is available (CheckTypes was probably not run)
        // so just bail.
        return;

      } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[16]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[47]++;

      JSType returnType = functionType.getReturnType();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;

      /* It is OK if a constructor doesn't have a return type */
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((functionType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((returnType == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((returnType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[17]++;
        reportUnknownReturnType(function);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[49]++;

      } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[18]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[50]++;

      /* We can't just use the function's type's getParameters() to get the
       * parameter nodes because the nodes returned from that method
       * do not have names or locations. Similarly, the function's AST parameter
       * nodes do not have JSTypes(). So we walk both lists of parameter nodes
       * in lock step getting parameter names from the first and types from the
       * second.
       */
      Node astParameterIterator = NodeUtil.getFunctionParameters(function)
        .getFirstChild();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[51]++;

      Node typeParameterIterator = functionType.getParametersNode()
        .getFirstChild();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[52]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;

      while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((astParameterIterator != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[10]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[11]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[12]++;
}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[53]++;
        JSType parameterType = typeParameterIterator.getJSType();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((parameterType == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parameterType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[19]++;
          reportUnknownParameterType(function, astParameterIterator);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[55]++;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[20]++;}

        astParameterIterator = astParameterIterator.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[56]++;
        typeParameterIterator = typeParameterIterator.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[57]++;
      }
    }

    private void reportUnknownParameterType(Node function, Node parameter) {
      compiler.report(JSError.make(NodeUtil.getSourceName(function),
          parameter, CheckLevel.WARNING,
          EXPORTED_FUNCTION_UNKNOWN_PARAMETER_TYPE,
          NodeUtil.getFunctionName(function), parameter.getString()));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[58]++;
    }

    private void reportUnknownReturnType(Node function) {
      compiler.report(JSError.make(NodeUtil.getSourceName(function),
          function, CheckLevel.WARNING, EXPORTED_FUNCTION_UNKNOWN_RETURN_TYPE,
          NodeUtil.getFunctionName(function)));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[59]++;
    }

    /**
     * If the given value is a qualified name which refers
     * a function or object literal, the node is returned. Otherwise,
     * {@code null} is returned.
     */
    protected Node getValue(Node qualifiedNameNode) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[60]++;
      String qualifiedName = value.getQualifiedName();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((qualifiedName == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[21]++;
        return null;

      } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[22]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[62]++;

      Node definitionParent = definitionMap.get(qualifiedName);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[63]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((definitionParent == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[23]++;
        return null;

      } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[24]++;}

      Node definition;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[64]++;

      switch (definitionParent.getType()) {
        case Token.ASSIGN:
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[25]++;
          definition = definitionParent.getLastChild();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[65]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[66]++;
          break;
        case Token.VAR:
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[26]++;
          definition = definitionParent.getLastChild().getLastChild();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[67]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[68]++;
          break;
        default:
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[27]++;
            return null;
      }
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((definition.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((definition.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[28]++;
        return null;

      } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[29]++;}

      return definition;
    }
  }

  /**
   * A symbol export.
   */
  private class SymbolExport extends Export {

    public SymbolExport(String symbolName, Node value) {
      super(symbolName, value);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[70]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[71]++;

      String qualifiedName = value.getQualifiedName();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;

      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[30]++;
        mappedPaths.put(qualifiedName, symbolName);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[73]++;

      } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[31]++;}
    }

    @Override
    String getExportedPath() {
      return symbolName;
    }
  }

  /**
   * A property export.
   */
  private class PropertyExport extends Export {
    private final String exportPath;

    public PropertyExport(String exportPath, String symbolName, Node value) {
      super(symbolName, value);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[74]++;

      this.exportPath = exportPath;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[75]++;
    }

    @Override
    String getExportedPath() {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[76]++;

      // Find the longest path that has been mapped (if any).
      List<String> pieces = Lists.newArrayList(exportPath.split("\\."));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[77]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;

      for (int i = pieces.size();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[13]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[14]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[15]++;
}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[78]++;
        // Find the path of the current length.
        String cPath = Joiner.on(".").join(Iterables.limit(pieces, i));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[79]++;
int CodeCoverConditionCoverageHelper_C20;

        // If this path is mapped, return the mapped path plus any remaining
        // pieces.
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((mappedPaths.containsKey(cPath)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[32]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[80]++;
          String newPath = mappedPaths.get(cPath);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[81]++;
int CodeCoverConditionCoverageHelper_C21;

          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < pieces.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[34]++;
            newPath += "." + Joiner.on(".").join(Iterables.skip(pieces, i));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[82]++;

          } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[35]++;}

          return newPath + "." + symbolName;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[33]++;}
      }

      return exportPath + "." + symbolName;
    }
  }

  /**
   * Creates an instance.
   */
  ExternExportsPass(AbstractCompiler compiler) {
    this.exports = Lists.newArrayList();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[83]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[84]++;
    this.definitionMap = Maps.newHashMap();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[85]++;
    this.externsRoot = IR.block();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[86]++;
    this.externsRoot.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[87]++;
    this.alreadyExportedPaths = Sets.newHashSet();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[88]++;
    this.mappedPaths = Maps.newHashMap();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[89]++;

    initExportMethods();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[90]++;
  }

  private void initExportMethods() {
    exportSymbolFunctionNames = Lists.newArrayList();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[91]++;
    exportPropertyFunctionNames = Lists.newArrayList();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[92]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[93]++;

    // From Closure:
    // goog.exportSymbol = function(publicName, symbol)
    // goog.exportProperty = function(object, publicName, symbol)
    CodingConvention convention = compiler.getCodingConvention();
    exportSymbolFunctionNames.add(convention.getExportSymbolFunction());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[94]++;
    exportPropertyFunctionNames.add(convention.getExportPropertyFunction());
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[95]++;

    // Another common one used inside google:
    exportSymbolFunctionNames.add("google_exportSymbol");
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[96]++;
    exportPropertyFunctionNames.add("google_exportProperty");
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[97]++;
  }

  @Override
  public void process(Node externs, Node root) {
    new NodeTraversal(compiler, this).traverse(root);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[98]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[99]++;

    // Sort by path length to ensure that the longer
    // paths (which may depend on the shorter ones)
    // come later.
    Set<Export> sorted =
        new TreeSet<Export>(new Comparator<Export>() {
          @Override
          public int compare(Export e1, Export e2) {
            return e1.getExportedPath().compareTo(e2.getExportedPath());
          }
        });

    sorted.addAll(exports);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[100]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[101]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[16]++;



    for (Export export : sorted) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[16]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[17]--;
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.loops[18]++;
}
      export.generateExterns();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[102]++;
    }
  }

  /**
   * Returns the generated externs.
   */
  public String getGeneratedExterns() {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[103]++;
    CodePrinter.Builder builder = new CodePrinter.Builder(externsRoot)
      .setPrettyPrint(true)
      .setOutputTypes(true)
      .setTypeRegistry(compiler.getTypeRegistry());

    return builder.build();
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[104]++;
    switch (n.getType()) {

      case Token.NAME:
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[36]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[37]++;
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[105]++;
        String name = n.getQualifiedName();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[106]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[38]++;
          return;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[39]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[107]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[40]++;
          definitionMap.put(name, parent);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[108]++;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[41]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[109]++;
int CodeCoverConditionCoverageHelper_C24;

        // Only handle function calls. This avoids assignments
        // that do not export items directly.
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[42]++;
          return;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[43]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[110]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((exportPropertyFunctionNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[44]++;
          handlePropertyExport(parent);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[111]++;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[45]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[112]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((exportSymbolFunctionNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[46]++;
          handleSymbolExport(parent);
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[113]++;

        } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[47]++;} default : CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[48]++;
    }
  }

  private void handleSymbolExport(Node parent) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[114]++;
int CodeCoverConditionCoverageHelper_C27;
    // Ensure that we only check valid calls with the 2 arguments
    // (plus the GETPROP node itself).
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((parent.getChildCount() != 3) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[49]++;
      return;

    } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[50]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[115]++;

    Node thisNode = parent.getFirstChild();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[116]++;
    Node nameArg = thisNode.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[117]++;
    Node valueArg = nameArg.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[118]++;
int CodeCoverConditionCoverageHelper_C28;

    // Confirm the arguments are the expected types. If they are not,
    // then we have an export that we cannot statically identify.
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((nameArg.isString()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[51]++;
      return;

    } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[52]++;}

    // Add the export to the list.
    this.exports.add(new SymbolExport(nameArg.getString(), valueArg));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[119]++;
  }

  private void handlePropertyExport(Node parent) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[120]++;
int CodeCoverConditionCoverageHelper_C29;
    // Ensure that we only check valid calls with the 3 arguments
    // (plus the GETPROP node itself).
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((parent.getChildCount() != 4) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[53]++;
      return;

    } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[54]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[121]++;

    Node thisNode = parent.getFirstChild();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[122]++;
    Node objectArg = thisNode.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[123]++;
    Node nameArg = objectArg.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[124]++;
    Node valueArg = nameArg.getNext();
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[125]++;
int CodeCoverConditionCoverageHelper_C30;

    // Confirm the arguments are the expected types. If they are not,
    // then we have an export that we cannot statically identify.
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((objectArg.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[55]++;
      return;

    } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[56]++;}
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[126]++;
int CodeCoverConditionCoverageHelper_C31;

    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((nameArg.isString()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[57]++;
      return;

    } else {
  CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.branches[58]++;}

    // Add the export to the list.
    this.exports.add(
        new PropertyExport(objectArg.getQualifiedName(),
                           nameArg.getString(),
                           valueArg));
CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h.statements[127]++;
  }
}

class CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h ());
  }
    public static long[] statements = new long[128];
    public static long[] branches = new long[59];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ExternExportsPass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,3,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$becs7ih1rhtznbt0mphaiu91vrqq4el10h () {
    super("com.google.javascript.jscomp.ExternExportsPass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 127; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 58; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ExternExportsPass.java");
      for (int i = 1; i <= 127; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 58; i++) {
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

