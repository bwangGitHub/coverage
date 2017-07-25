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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.GlobalNamespace.Name;
import com.google.javascript.jscomp.GlobalNamespace.Ref;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSTypeExpression;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;

import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Process variables annotated as {@code @define}. A define is
 * a special constant that may be overridden by later files and
 * manipulated by the compiler, much like C preprocessor {@code #define}s.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class ProcessDefines implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.ping();
  }


  /**
   * Defines in this set will not be flagged with "unknown define" warnings.
   * There are legacy flags that always set these defines, even when they
   * might not be in the binary.
   */
  private static final Set<String> KNOWN_DEFINES =
      Sets.newHashSet("COMPILED");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final Map<String, Node> dominantReplacements;

  private GlobalNamespace namespace = null;
  {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[2]++;
  }

  // Warnings
  static final DiagnosticType UNKNOWN_DEFINE_WARNING = DiagnosticType.warning(
      "JSC_UNKNOWN_DEFINE_WARNING",
      "unknown @define variable {0}");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[3]++;
  }

  // Errors
  static final DiagnosticType INVALID_DEFINE_TYPE_ERROR =
    DiagnosticType.error(
        "JSC_INVALID_DEFINE_TYPE_ERROR",
        "@define tag only permits literal types");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[4]++;
  }

  static final DiagnosticType INVALID_DEFINE_INIT_ERROR =
      DiagnosticType.error(
          "JSC_INVALID_DEFINE_INIT_ERROR",
          "illegal initialization of @define variable {0}");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[5]++;
  }

  static final DiagnosticType NON_GLOBAL_DEFINE_INIT_ERROR =
      DiagnosticType.error(
          "JSC_NON_GLOBAL_DEFINE_INIT_ERROR",
          "@define variable {0} assignment must be global");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[6]++;
  }

  static final DiagnosticType DEFINE_NOT_ASSIGNABLE_ERROR =
      DiagnosticType.error(
          "JSC_DEFINE_NOT_ASSIGNABLE_ERROR",
          "@define variable {0} cannot be reassigned due to code at {1}.");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[7]++;
  }

  private static final MessageFormat REASON_DEFINE_NOT_ASSIGNABLE =
      new MessageFormat("line {0} of {1}");
  static {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[8]++;
  }

  /**
   * Create a pass that overrides define constants.
   *
   * TODO(nicksantos): Write a builder to help JSCompiler induce
   *    {@code replacements} from command-line flags
   *
   * @param replacements A hash table of names of defines to their replacements.
   *   All replacements <b>must</b> be literals.
   */
  ProcessDefines(AbstractCompiler compiler, Map<String, Node> replacements) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[9]++;
    dominantReplacements = replacements;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[10]++;
  }

  /**
   * Injects a pre-computed global namespace, so that the same namespace
   * can be re-used for multiple check passes. Returns {@code this} for
   * easy chaining.
   */
  ProcessDefines injectNamespace(GlobalNamespace namespace) {
    this.namespace = namespace;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[11]++;
    return this;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[1]++;
      namespace = new GlobalNamespace(compiler, root);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[13]++;

    } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[2]++;}
    overrideDefines(collectDefines(root, namespace));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[14]++;
  }

  private void overrideDefines(Map<String, DefineInfo> allDefines) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[15]++;
    boolean changed = false;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[1]++;


    for (Map.Entry<String, DefineInfo> def : allDefines.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[1]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[2]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[3]++;
}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[17]++;
      String defineName = def.getKey();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[18]++;
      DefineInfo info = def.getValue();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[19]++;
      Node inputValue = dominantReplacements.get(defineName);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[20]++;
      Node finalValue = inputValue != null ?
          inputValue : info.getLastValue();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((finalValue != info.initialValue) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[3]++;
        info.initialValueParent.replaceChild(
            info.initialValue, finalValue.cloneTree());
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[22]++;
        compiler.addToDebugLog("Overriding @define variable " + defineName);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[23]++;
        changed = changed ||
            finalValue.getType() != info.initialValue.getType() ||
            !finalValue.isEquivalentTo(info.initialValue);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[24]++;

      } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[4]++;}
    }
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[5]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[26]++;

    } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[6]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[27]++;

    Set<String> unusedReplacements = dominantReplacements.keySet();
    unusedReplacements.removeAll(allDefines.keySet());
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[28]++;
    unusedReplacements.removeAll(KNOWN_DEFINES);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[29]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[30]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[4]++;


    for (String unknownDefine : unusedReplacements) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[4]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[5]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[6]++;
}
      compiler.report(JSError.make(UNKNOWN_DEFINE_WARNING, unknownDefine));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[31]++;
    }
  }

  private static String format(MessageFormat format, Object... params) {
    return format.format(params);
  }

  /**
   * Only defines of literal number, string, or boolean are supported.
   */
  private boolean isValidDefineType(JSTypeExpression expression) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[32]++;
    JSType type = expression.evaluate(null, compiler.getTypeRegistry());
    return !type.isUnknownType() && type.isSubtype(
        compiler.getTypeRegistry().getNativeType(
            JSTypeNative.NUMBER_STRING_BOOLEAN));
  }

  /**
   * Finds all defines, and creates a {@link DefineInfo} data structure for
   * each one.
   * @return A map of {@link DefineInfo} structures, keyed by name.
   */
  private Map<String, DefineInfo> collectDefines(Node root,
      GlobalNamespace namespace) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[33]++;
    // Find all the global names with a @define annotation
    List<Name> allDefines = Lists.newArrayList();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[7]++;


    for (Name name : namespace.getNameIndex().values()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[7]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[8]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[9]++;
}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[35]++;
      Ref decl = name.getDeclaration();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((name.docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name.docInfo.isDefine()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[7]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        // Process defines should not depend on check types being enabled,
        // so we look for the JSDoc instead of the inferred type.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isValidDefineType(name.docInfo.getType())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[9]++;
          allDefines.add(name);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[38]++;

        } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[10]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[39]++;
          JSError error = JSError.make(
              decl.getSourceName(),
              decl.node, INVALID_DEFINE_TYPE_ERROR);
          compiler.report(error);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[40]++;
        }

      } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[8]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[41]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[10]++;


        for (Ref ref : name.getRefs()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[10]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[11]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[12]++;
}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ref == decl) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[11]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[43]++;
            // Declarations were handled above.
            continue;

          } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[12]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[44]++;

          Node n = ref.node;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[45]++;
          Node parent = ref.node.getParent();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[46]++;
          JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[13]++;
            info = parent.getJSDocInfo();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[48]++;

          } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[14]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[49]++;
int CodeCoverConditionCoverageHelper_C8;

          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((info.isDefine()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[15]++;
            allDefines.add(name);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[50]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[51]++;
            break;

          } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[16]++;}
        }
      }
    }
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[52]++;

    CollectDefines pass = new CollectDefines(compiler, allDefines);
    NodeTraversal.traverse(compiler, root, pass);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[53]++;
    return pass.getAllDefines();
  }

  /**
   * Finds all assignments to @defines, and figures out the last value of
   * the @define.
   */
  private static final class CollectDefines implements Callback {

    private final AbstractCompiler compiler;
    private final Map<String, DefineInfo> assignableDefines;
    private final Map<String, DefineInfo> allDefines;
    private final Map<Node, RefInfo> allRefInfo;

    // A hack that allows us to remove ASSIGN/VAR statements when
    // we're currently visiting one of the children of the assign.
    private Node lvalueToRemoveLater = null;
  {
    CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[54]++;
  }

    // A stack tied to the node traversal, to keep track of whether
    // we're in a conditional block. If 1 is at the top, assignment to
    // a define is allowed. Otherwise, it's not allowed.
    private final Deque<Integer> assignAllowed;

    CollectDefines(AbstractCompiler compiler, List<Name> listOfDefines) {
      this.compiler = compiler;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[55]++;
      this.allDefines = Maps.newHashMap();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[56]++;

      assignableDefines = Maps.newHashMap();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[57]++;
      assignAllowed = new ArrayDeque<Integer>();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[58]++;
      assignAllowed.push(1);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[59]++;

      // Create a map of references to defines keyed by node for easy lookup
      allRefInfo = Maps.newHashMap();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[60]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[61]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[13]++;


      for (Name name : listOfDefines) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[13]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[14]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[15]++;
}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[62]++;
        Ref decl = name.getDeclaration();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[63]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((decl != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[17]++;
          allRefInfo.put(decl.node,
                         new RefInfo(decl, name));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[64]++;

        } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[18]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[65]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[16]++;


        for (Ref ref : name.getRefs()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[16]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[17]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[18]++;
}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[66]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ref == decl) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[19]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[67]++;
            // Declarations were handled above.
            continue;

          } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[20]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[68]++;
int CodeCoverConditionCoverageHelper_C11;

          // If there's a TWIN def, only put one of the twins in.
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((ref.getTwin() == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ref.getTwin().isSet()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[21]++;
            allRefInfo.put(ref.node, new RefInfo(ref, name));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[69]++;

          } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[22]++;}
        }
      }
    }

    /**
     * Get a map of {@link DefineInfo} structures, keyed by the name of
     * the define.
     */
    Map<String, DefineInfo> getAllDefines() {
      return allDefines;
    }

    /**
     * Keeps track of whether the traversal is in a conditional branch.
     * We traverse all nodes of the parse tree.
     */
    @Override
    public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
      updateAssignAllowedStack(n, true);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[70]++;
      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[71]++;
      RefInfo refInfo = allRefInfo.get(n);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[72]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((refInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[23]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[73]++;
        Ref ref = refInfo.ref;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[74]++;
        Name name = refInfo.name;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[75]++;
        String fullName = name.getFullName();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[76]++;
        switch (ref.type) {
          case SET_FROM_GLOBAL:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[25]++;
          case SET_FROM_LOCAL:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[26]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[77]++;
            Node valParent = getValueParent(ref);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[78]++;
            Node val = valParent.getLastChild();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[79]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((valParent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((name.isSimpleName()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((name.getDeclaration() == ref) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[27]++;
              // For defines, it's an error if a simple name is assigned
              // before it's declared
              compiler.report(
                  t.makeError(val, INVALID_DEFINE_INIT_ERROR, fullName));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[80]++;

            } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[28]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[81]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((processDefineAssignment(t, fullName, val, valParent)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[29]++;
              // remove the assignment so that the variable is still declared,
              // but no longer assigned to a value, e.g.,
              // DEF_FOO = 5; // becomes "5;"

              // We can't remove the ASSIGN/VAR when we're still visiting its
              // children, so we'll have to come back later to remove it.
              refInfo.name.removeRef(ref);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[82]++;
              lvalueToRemoveLater = valParent;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[83]++;

            } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[30]++;}
}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[84]++;
            break;
          default:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[31]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[85]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[32]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[86]++;
              // Treat this as a reference to a define in the global scope.
              // After this point, the define must not be reassigned,
              // or it's an error.
              DefineInfo info = assignableDefines.get(fullName);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[87]++;
int CodeCoverConditionCoverageHelper_C16;
              if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[34]++;
                setDefineInfoNotAssignable(info, t);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[88]++;
                assignableDefines.remove(fullName);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[89]++;

              } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[35]++;}

            } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[33]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[90]++;
            break;
        }

      } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[24]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[91]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((n.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((n.getJSDocInfo().isDefine()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[36]++;
        // warn about @define annotations in local scopes
        compiler.report(
            t.makeError(n, NON_GLOBAL_DEFINE_INIT_ERROR, ""));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[92]++;

      } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[37]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[93]++;
int CodeCoverConditionCoverageHelper_C18;

      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lvalueToRemoveLater == n) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[38]++;
        lvalueToRemoveLater = null;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[94]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[95]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[40]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[96]++;
          Node last = n.getLastChild();
          n.removeChild(last);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[97]++;
          parent.replaceChild(n, last);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[98]++;

        } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[41]++;
          Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[99]++;
          n.removeChild(n.getFirstChild());
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[100]++;
        }
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[101]++;

      } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[39]++;}
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[102]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[42]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[103]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[44]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[104]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[19]++;


          // If there's a function call in the global scope,
          // we just say it's unsafe and freeze all the defines.
          //
          // NOTE(nicksantos): We could be a lot smarter here. For example,
          // ReplaceOverriddenVars keeps a call graph of all functions and
          // which functions/variables that they reference, and tries
          // to statically determine which functions are "safe" and which
          // are not. But this would be overkill, especially because
          // the intended use of defines is with config_files, where
          // all the defines are at the top of the bundle.
          for (DefineInfo info : assignableDefines.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[19]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[20]--;
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.loops[21]++;
}
            setDefineInfoNotAssignable(info, t);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[105]++;
          }

          assignableDefines.clear();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[106]++;

        } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[45]++;}

      } else {
  CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[43]++;}

      updateAssignAllowedStack(n, false);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[107]++;
    }

    /**
     * Determines whether assignment to a define should be allowed
     * in the subtree of the given node, and if not, records that fact.
     *
     * @param n The node whose subtree we're about to enter or exit.
     * @param entering True if we're entering the subtree, false otherwise.
     */
    private void updateAssignAllowedStack(Node n, boolean entering) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[108]++;
      switch (n.getType()) {
        case Token.CASE:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[46]++;
        case Token.FOR:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[47]++;
        case Token.FUNCTION:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[48]++;
        case Token.HOOK:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[49]++;
        case Token.IF:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[50]++;
        case Token.SWITCH:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[51]++;
        case Token.WHILE:
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[52]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[109]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((entering) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[53]++;
            assignAllowed.push(0);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[110]++;

          } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[54]++;
            assignAllowed.remove();
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[111]++;
          }
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[112]++;
          break; default : CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[55]++;
      }
    }

    /**
     * Determines whether assignment to a define should be allowed
     * at the current point of the traversal.
     */
    private boolean isAssignAllowed() {
      return assignAllowed.element() == 1;
    }

    /**
     * Tracks the given define.
     *
     * @param t The current traversal, for context.
     * @param name The full name for this define.
     * @param value The value assigned to the define.
     * @param valueParent The parent node of value.
     * @return Whether we should remove this assignment from the parse tree.
     */
    private boolean processDefineAssignment(NodeTraversal t,
        String name, Node value, Node valueParent) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[113]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((NodeUtil.isValidDefineValue(value,
                                                        allDefines.keySet())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[56]++;
        compiler.report(
            t.makeError(value, INVALID_DEFINE_INIT_ERROR, name));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[114]++;

      } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[57]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[115]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isAssignAllowed()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[58]++;
        compiler.report(
            t.makeError(valueParent, NON_GLOBAL_DEFINE_INIT_ERROR, name));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[116]++;

      } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[59]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[117]++;
        DefineInfo info = allDefines.get(name);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[118]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[60]++;
          // First declaration of this define.
          info = new DefineInfo(value, valueParent);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[119]++;
          allDefines.put(name, info);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[120]++;
          assignableDefines.put(name, info);
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[121]++;

        } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[61]++;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[122]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((info.recordAssignment(value)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[62]++;
          // The define was already initialized, but this is a safe
          // re-assignment.
          return true;

        } else {
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.branches[63]++;
          // The define was already initialized, and this is an unsafe
          // re-assignment.
          compiler.report(
              t.makeError(valueParent, DEFINE_NOT_ASSIGNABLE_ERROR,
                  name, info.getReasonWhyNotAssignable()));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[123]++;
        }
}
      }
}

      return false;
    }

    /**
     * Gets the parent node of the value for any assignment to a Name.
     * For example, in the assignment
     * {@code var x = 3;}
     * the parent would be the NAME node.
     */
    private static Node getValueParent(Ref ref) {
      // there are two types of declarations: VARs and ASSIGNs
      return ref.node.getParent() != null &&
          ref.node.getParent().isVar() ?
          ref.node : ref.node.getParent();
    }

    /**
     * Records the fact that because of the current node in the node traversal,
     * the define can't ever be assigned again.
     *
     * @param info Represents the define variable.
     * @param t The current traversal.
     */
    private void setDefineInfoNotAssignable(DefineInfo info, NodeTraversal t) {
      info.setNotAssignable(format(REASON_DEFINE_NOT_ASSIGNABLE,
                                t.getLineNumber(), t.getSourceName()));
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[124]++;
    }

    /**
     * A simple data structure for associating a Ref with the name
     * that it references.
     */
    private static class RefInfo {
      final Ref ref;
      final Name name;

      RefInfo(Ref ref, Name name) {
        this.ref = ref;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[125]++;
        this.name = name;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[126]++;
      }
    }
  }

  /**
   * A simple class for storing information about a define.
   * Gathers the initial value, the last assigned value, and whether
   * the define can be safely assigned a new value.
   */
  private static final class DefineInfo {
    public final Node initialValueParent;
    public final Node initialValue;
    private Node lastValue;
    private boolean isAssignable;
    private String reasonNotAssignable;

    /**
     * Initializes a define.
     */
    public DefineInfo(Node initialValue, Node initialValueParent) {
      this.initialValueParent = initialValueParent;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[127]++;
      this.initialValue = initialValue;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[128]++;
      lastValue = initialValue;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[129]++;
      isAssignable = true;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[130]++;
    }

    /**
     * Records the fact that this define can't be assigned a value anymore.
     *
     * @param reason A message describing the reason why it can't be assigned.
     */
    public void setNotAssignable(String reason) {
      isAssignable = false;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[131]++;
      reasonNotAssignable = reason;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[132]++;
    }

    /**
     * Gets the reason why a define is not assignable.
     */
    public String getReasonWhyNotAssignable() {
      return reasonNotAssignable;
    }

    /**
     * Records an assigned value.
     *
     * @return False if there was an error.
     */
    public boolean recordAssignment(Node value) {
      lastValue = value;
CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5.statements[133]++;
      return isAssignable;
    }

    /**
     * Gets the last assigned value.
     */
    public Node getLastValue() {
      return lastValue;
    }
  }
}

class CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5 ());
  }
    public static long[] statements = new long[134];
    public static long[] branches = new long[64];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ProcessDefines.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,3,2,1,1,2,1,3,1,1,1,3,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 26; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$1bkmfeyrisqorjsc4g0cxri8jkknz5 () {
    super("com.google.javascript.jscomp.ProcessDefines.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 133; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 63; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ProcessDefines.java");
      for (int i = 1; i <= 133; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 63; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

