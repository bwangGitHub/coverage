/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

/**
 * Process goog.tweak primitives. Checks that:
 * <ul>
 * <li>parameters to goog.tweak.register* are literals of the correct type.
 * <li>the parameter to goog.tweak.get* is a string literal.
 * <li>parameters to goog.tweak.overrideDefaultValue are literals of the correct
 *     type.
 * <li>tweak IDs passed to goog.tweak.get* and goog.tweak.overrideDefaultValue
 *     correspond to registered tweaks.
 * <li>all calls to goog.tweak.register* and goog.tweak.overrideDefaultValue are
 *     within the top-level context.
 * <li>each tweak is registered only once.
 * <li>calls to goog.tweak.overrideDefaultValue occur before the call to the
 *     corresponding goog.tweak.register* function.
 * </ul>
 * @author agrieve@google.com (Andrew Grieve)
 */
class ProcessTweaks implements CompilerPass {
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.ping();
  }


  private final AbstractCompiler compiler;
  private final boolean stripTweaks;
  private final SortedMap<String, Node> compilerDefaultValueOverrides;

  private static final CharMatcher ID_MATCHER = CharMatcher.inRange('a', 'z').
      or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.anyOf("0123456789_."));
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[1]++;
  }

  // Warnings and Errors.
  static final DiagnosticType UNKNOWN_TWEAK_WARNING =
      DiagnosticType.warning(
          "JSC_UNKNOWN_TWEAK_WARNING",
          "no tweak registered with ID {0}");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[2]++;
  }

  static final DiagnosticType TWEAK_MULTIPLY_REGISTERED_ERROR =
      DiagnosticType.error(
          "JSC_TWEAK_MULTIPLY_REGISTERED_ERROR",
          "Tweak {0} has already been registered.");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[3]++;
  }

  static final DiagnosticType NON_LITERAL_TWEAK_ID_ERROR =
      DiagnosticType.error(
          "JSC_NON_LITERAL_TWEAK_ID_ERROR",
          "tweak ID must be a string literal");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[4]++;
  }

  static final DiagnosticType INVALID_TWEAK_DEFAULT_VALUE_WARNING =
      DiagnosticType.warning(
          "JSC_INVALID_TWEAK_DEFAULT_VALUE_WARNING",
          "tweak {0} registered with {1} must have a default value that is a " +
          "literal of type {2}");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[5]++;
  }

  static final DiagnosticType NON_GLOBAL_TWEAK_INIT_ERROR =
      DiagnosticType.error(
          "JSC_NON_GLOBAL_TWEAK_INIT_ERROR",
          "tweak declaration {0} must occur in the global scope");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[6]++;
  }

  static final DiagnosticType TWEAK_OVERRIDE_AFTER_REGISTERED_ERROR =
      DiagnosticType.error(
          "JSC_TWEAK_OVERRIDE_AFTER_REGISTERED_ERROR",
          "Cannot override the default value of tweak {0} after it has been " +
          "registered");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[7]++;
  }

  static final DiagnosticType TWEAK_WRONG_GETTER_TYPE_WARNING =
      DiagnosticType.warning(
          "JSC_TWEAK_WRONG_GETTER_TYPE_WARNING",
          "tweak getter function {0} used for tweak registered using {1}");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[8]++;
  }

  static final DiagnosticType INVALID_TWEAK_ID_ERROR =
      DiagnosticType.error(
          "JSC_INVALID_TWEAK_ID_ERROR",
          "tweak ID contains illegal characters. Only letters, numbers, _ " +
          "and . are allowed");
  static {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[9]++;
  }

  /**
   * An enum of goog.tweak functions.
   */
  private static enum TweakFunction {
    REGISTER_BOOLEAN("goog.tweak.registerBoolean", "boolean", Token.TRUE,
        Token.FALSE),
    REGISTER_NUMBER("goog.tweak.registerNumber", "number", Token.NUMBER),
    REGISTER_STRING("goog.tweak.registerString", "string", Token.STRING),
    OVERRIDE_DEFAULT_VALUE("goog.tweak.overrideDefaultValue"),
    GET_COMPILER_OVERRIDES("goog.tweak.getCompilerOverrides_"),
    GET_BOOLEAN("goog.tweak.getBoolean", REGISTER_BOOLEAN),
    GET_NUMBER("goog.tweak.getNumber", REGISTER_NUMBER),
    GET_STRING("goog.tweak.getString", REGISTER_STRING);

    final String name;
    final String expectedTypeName;
    final int validNodeTypeA;
    final int validNodeTypeB;
    final TweakFunction registerFunction;

    TweakFunction(String name) {
      this(name, null, Token.ERROR, Token.ERROR, null);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[10]++;
    }

    TweakFunction(String name, String expectedTypeName,
        int validNodeTypeA) {
      this(name, expectedTypeName, validNodeTypeA, Token.ERROR, null);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[11]++;
    }

    TweakFunction(String name, String expectedTypeName,
        int validNodeTypeA, int validNodeTypeB) {
      this(name, expectedTypeName, validNodeTypeA, validNodeTypeB, null);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[12]++;
    }

    TweakFunction(String name, TweakFunction registerFunction) {
      this(name, null, Token.ERROR, Token.ERROR, registerFunction);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[13]++;
    }

    TweakFunction(String name, String expectedTypeName,
        int validNodeTypeA, int validNodeTypeB,
        TweakFunction registerFunction) {
      this.name = name;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[14]++;
      this.expectedTypeName = expectedTypeName;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[15]++;
      this.validNodeTypeA = validNodeTypeA;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[16]++;
      this.validNodeTypeB = validNodeTypeB;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[17]++;
      this.registerFunction = registerFunction;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[18]++;
    }

    boolean isValidNodeType(int type) {
      return type == validNodeTypeA || type == validNodeTypeB;
    }

    boolean isCorrectRegisterFunction(TweakFunction registerFunction) {
      Preconditions.checkNotNull(registerFunction);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[19]++;
      return this.registerFunction == registerFunction;
    }

    boolean isGetterFunction() {
      return registerFunction != null;
    }

    String getName() {
      return name;
    }

    String getExpectedTypeName() {
      return expectedTypeName;
    }

    Node createDefaultValueNode() {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[20]++;
      switch (this) {
        case REGISTER_BOOLEAN:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[1]++;
          return IR.falseNode();
        case REGISTER_NUMBER:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[2]++;
          return IR.number(0);
        case REGISTER_STRING:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[3]++;
          return IR.string("");
        default:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[4]++;
          throw new IllegalStateException();
      }
    }
  }

  // A map of function name -> TweakFunction.
  private static final Map<String, TweakFunction> TWEAK_FUNCTIONS_MAP;
  static {
    TWEAK_FUNCTIONS_MAP = Maps.newHashMap();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[21]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[22]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[1]++;


    for (TweakFunction func : TweakFunction.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[1]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[2]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[3]++;
}
      TWEAK_FUNCTIONS_MAP.put(func.getName(), func);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[23]++;
    }
  }

  ProcessTweaks(AbstractCompiler compiler, boolean stripTweaks,
      Map<String, Node> compilerDefaultValueOverrides) {
    this.compiler = compiler;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[24]++;
    this.stripTweaks = stripTweaks;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[25]++;
    // Having the map sorted is required for the unit tests to be deterministic.
    this.compilerDefaultValueOverrides = Maps.newTreeMap();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[26]++;
    this.compilerDefaultValueOverrides.putAll(compilerDefaultValueOverrides);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[27]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[28]++;
    CollectTweaksResult result = collectTweaks(root);
    applyCompilerDefaultValueOverrides(result.tweakInfos);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[29]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[30]++;

    boolean changed = false;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[31]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((stripTweaks) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[5]++;
      changed = stripAllCalls(result.tweakInfos);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[32]++;

    } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[6]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[33]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((compilerDefaultValueOverrides.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[7]++;
      changed = replaceGetCompilerOverridesCalls(result.getOverridesCalls);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[34]++;

    } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[8]++;}
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[35]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[9]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[36]++;

    } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[10]++;}
  }

  /**
   * Passes the compiler default value overrides to the JS by replacing calls
   * to goog.tweak.getCompilerOverrids_ with a map of tweak ID->default value;
   */
  private boolean replaceGetCompilerOverridesCalls(
      List<TweakFunctionCall> calls) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[37]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[4]++;


    for (TweakFunctionCall call : calls) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[4]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[5]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[6]++;
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[38]++;
      Node callNode = call.callNode;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[39]++;
      Node objNode = createCompilerDefaultValueOverridesVarNode(callNode);
      callNode.getParent().replaceChild(callNode, objNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[40]++;
    }
    return !calls.isEmpty();
  }

  /**
   * Removes all CALL nodes in the given TweakInfos, replacing calls to getter
   * functions with the tweak's default value.
   */
  private boolean stripAllCalls(Map<String, TweakInfo> tweakInfos) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[41]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[7]++;


    for (TweakInfo tweakInfo : tweakInfos.values()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[7]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[8]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[9]++;
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[42]++;
      boolean isRegistered = tweakInfo.isRegistered();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[43]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[10]++;


      for (TweakFunctionCall functionCall : tweakInfo.functionCalls) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[10]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[11]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[12]++;
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[44]++;
        Node callNode = functionCall.callNode;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[45]++;
        Node parent = callNode.getParent();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[46]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((functionCall.tweakFunc.isGetterFunction()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[11]++;
          Node newValue;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[47]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isRegistered) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[13]++;
            newValue = tweakInfo.getDefaultValueNode().cloneNode();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[48]++;

          } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[14]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[49]++;
            // When we find a getter of an unregistered tweak, there has
            // already been a warning about it, so now just use a default
            // value when stripping.
            TweakFunction registerFunction =
                functionCall.tweakFunc.registerFunction;
            newValue = registerFunction.createDefaultValueNode();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[50]++;
          }
          parent.replaceChild(callNode, newValue);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[51]++;

        } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[12]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[52]++;
          Node voidZeroNode = IR.voidNode(IR.number(0).srcref(callNode))
              .srcref(callNode);
          parent.replaceChild(callNode, voidZeroNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[53]++;
        }
      }
    }
    return !tweakInfos.isEmpty();
  }

  /**
   * Creates a JS object that holds a map of tweakId -> default value override.
   */
  private Node createCompilerDefaultValueOverridesVarNode(
      Node sourceInformationNode) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[54]++;
    Node objNode = IR.objectlit().srcref(sourceInformationNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[55]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[13]++;


    for (Entry<String, Node> entry : compilerDefaultValueOverrides.entrySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[13]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[14]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[15]++;
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[56]++;
      Node objKeyNode = IR.stringKey(entry.getKey())
          .copyInformationFrom(sourceInformationNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[57]++;
      Node objValueNode = entry.getValue().cloneNode()
          .copyInformationFrom(sourceInformationNode);
      objKeyNode.addChildToBack(objValueNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[58]++;
      objNode.addChildToBack(objKeyNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[59]++;
    }
    return objNode;
  }

  /** Sets the default values of tweaks based on compiler options. */
  private void applyCompilerDefaultValueOverrides(
      Map<String, TweakInfo> tweakInfos) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[60]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[16]++;


    for (Entry<String, Node> entry : compilerDefaultValueOverrides.entrySet()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[16]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[17]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[18]++;
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[61]++;
      String tweakId = entry.getKey();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[62]++;
      TweakInfo tweakInfo = tweakInfos.get(tweakId);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[63]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((tweakInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[15]++;
        compiler.report(JSError.make(UNKNOWN_TWEAK_WARNING, tweakId));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[64]++;

      } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[16]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[65]++;
        TweakFunction registerFunc = tweakInfo.registerCall.tweakFunc;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[66]++;
        Node value = entry.getValue();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[67]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((registerFunc.isValidNodeType(value.getType())) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[17]++;
          compiler.report(JSError.make(INVALID_TWEAK_DEFAULT_VALUE_WARNING,
              tweakId, registerFunc.getName(),
              registerFunc.getExpectedTypeName()));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[68]++;

        } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[18]++;
          tweakInfo.defaultValueNode = value;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[69]++;
        }
      }
    }
  }

  /**
   * Finds all calls to goog.tweak functions and emits warnings/errors if any
   * of the calls have issues.
   * @return A map of {@link TweakInfo} structures, keyed by tweak ID.
   */
  private CollectTweaksResult collectTweaks(Node root) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[70]++;
    CollectTweaks pass = new CollectTweaks();
    NodeTraversal.traverse(compiler, root, pass);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[71]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[72]++;

    Map<String, TweakInfo> tweakInfos = pass.allTweaks;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[73]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[19]++;


    for (TweakInfo tweakInfo: tweakInfos.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[19]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[20]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[21]++;
}
      tweakInfo.emitAllWarnings();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[74]++;
    }
    return new CollectTweaksResult(tweakInfos, pass.getOverridesCalls);
  }

  private final static class CollectTweaksResult {
    final Map<String, TweakInfo> tweakInfos;
    final List<TweakFunctionCall> getOverridesCalls;

    CollectTweaksResult(Map<String, TweakInfo> tweakInfos,
        List<TweakFunctionCall> getOverridesCalls) {
      this.tweakInfos = tweakInfos;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[75]++;
      this.getOverridesCalls = getOverridesCalls;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[76]++;
    }
  }

  /**
   * Processes all calls to goog.tweak functions.
   */
  private final class CollectTweaks extends AbstractPostOrderCallback {
    final Map<String, TweakInfo> allTweaks = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[77]++;
  }
    final List<TweakFunctionCall> getOverridesCalls = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[78]++;
  }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[79]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[19]++;
        return;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[20]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[80]++;

      String callName = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[81]++;
      TweakFunction tweakFunc = TWEAK_FUNCTIONS_MAP.get(callName);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[82]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((tweakFunc == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[21]++;
        return;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[22]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[83]++;
int CodeCoverConditionCoverageHelper_C10;

      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((tweakFunc == TweakFunction.GET_COMPILER_OVERRIDES) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[23]++;
        getOverridesCalls.add(
            new TweakFunctionCall(t.getSourceName(), tweakFunc, n));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[84]++;
        return;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[24]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[85]++;

      // Ensure the first parameter (the tweak ID) is a string literal.
      Node tweakIdNode = n.getFirstChild().getNext();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[86]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((tweakIdNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[25]++;
        compiler.report(t.makeError(tweakIdNode, NON_LITERAL_TWEAK_ID_ERROR));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[87]++;
        return;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[26]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[88]++;
      String tweakId = tweakIdNode.getString();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[89]++;

      // Make sure there is a TweakInfo structure for it.
      TweakInfo tweakInfo = allTweaks.get(tweakId);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[90]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((tweakInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[27]++;
        tweakInfo = new TweakInfo(tweakId);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[91]++;
        allTweaks.put(tweakId, tweakInfo);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[92]++;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[28]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[93]++;

      switch (tweakFunc) {
        case REGISTER_BOOLEAN:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[29]++;
        case REGISTER_NUMBER:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[30]++;
        case REGISTER_STRING:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[31]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[94]++;
int CodeCoverConditionCoverageHelper_C13;
          // Ensure the ID contains only valid characters.
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((ID_MATCHER.matchesAllOf(tweakId)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[32]++;
            compiler.report(t.makeError(tweakIdNode, INVALID_TWEAK_ID_ERROR));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[95]++;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[33]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[96]++;
int CodeCoverConditionCoverageHelper_C14;

          // Ensure tweaks are registered in the global scope.
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[34]++;
            compiler.report(
                t.makeError(n, NON_GLOBAL_TWEAK_INIT_ERROR, tweakId));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[97]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[98]++;
            break;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[35]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[99]++;
int CodeCoverConditionCoverageHelper_C15;

          // Ensure tweaks are registered only once.
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((tweakInfo.isRegistered()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[36]++;
            compiler.report(
                t.makeError(n, TWEAK_MULTIPLY_REGISTERED_ERROR, tweakId));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[100]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[101]++;
            break;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[37]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[102]++;

          Node tweakDefaultValueNode = tweakIdNode.getNext().getNext();
          tweakInfo.addRegisterCall(t.getSourceName(), tweakFunc, n,
              tweakDefaultValueNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[103]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[104]++;
          break;
        case OVERRIDE_DEFAULT_VALUE:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[38]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[105]++;
int CodeCoverConditionCoverageHelper_C16;
          // Ensure tweaks overrides occur in the global scope.
          if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[39]++;
            compiler.report(
                t.makeError(n, NON_GLOBAL_TWEAK_INIT_ERROR, tweakId));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[106]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[107]++;
            break;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[40]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[108]++;
int CodeCoverConditionCoverageHelper_C17;
          // Ensure tweak overrides occur before the tweak is registered.
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((tweakInfo.isRegistered()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[41]++;
            compiler.report(
                t.makeError(n, TWEAK_OVERRIDE_AFTER_REGISTERED_ERROR, tweakId));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[109]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[110]++;
            break;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[42]++;}

          tweakDefaultValueNode = tweakIdNode.getNext();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[111]++;
          tweakInfo.addOverrideDefaultValueCall(t.getSourceName(), tweakFunc, n,
              tweakDefaultValueNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[112]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[113]++;
          break;
        case GET_BOOLEAN:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[43]++;
        case GET_NUMBER:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[44]++;
        case GET_STRING:
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[45]++;
          tweakInfo.addGetterCall(t.getSourceName(), tweakFunc, n);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[114]++; default : CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[46]++;
      }
    }
  }

  /**
   * Holds information about a call to a goog.tweak function.
   */
  private static final class TweakFunctionCall {
    final String sourceName;
    final TweakFunction tweakFunc;
    final Node callNode;
    final Node valueNode;

    TweakFunctionCall(String sourceName, TweakFunction tweakFunc,
        Node callNode) {
      this(sourceName, tweakFunc, callNode, null);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[115]++;
    }

    TweakFunctionCall(String sourceName, TweakFunction tweakFunc, Node callNode,
        Node valueNode) {
      this.sourceName = sourceName;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[116]++;
      this.callNode = callNode;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[117]++;
      this.tweakFunc = tweakFunc;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[118]++;
      this.valueNode = valueNode;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[119]++;
    }

    Node getIdNode() {
      return callNode.getFirstChild().getNext();
    }
  }

  /**
   * Stores information about a single tweak.
   */
  private final class TweakInfo {
    final String tweakId;
    final List<TweakFunctionCall> functionCalls;
    TweakFunctionCall registerCall;
    Node defaultValueNode;

    TweakInfo(String tweakId) {
      this.tweakId = tweakId;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[120]++;
      functionCalls = Lists.newArrayList();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[121]++;
    }

    /**
     * If this tweak is registered, then looks for type warnings in default
     * value parameters and getter functions. If it is not registered, emits an
     * error for each function call.
     */
    void emitAllWarnings() {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[122]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isRegistered()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[47]++;
        emitAllTypeWarnings();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[123]++;

      } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[48]++;
        emitUnknownTweakErrors();
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[124]++;
      }
    }

    /**
     * Emits a warning for each default value parameter that has the wrong type
     * and for each getter function that was used for the wrong type of tweak.
     */
    void emitAllTypeWarnings() {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[125]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[22]++;


      for (TweakFunctionCall call : functionCalls) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[22]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[23]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[24]++;
}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[126]++;
        Node valueNode = call.valueNode;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[127]++;
        TweakFunction tweakFunc = call.tweakFunc;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[128]++;
        TweakFunction registerFunc = registerCall.tweakFunc;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[129]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((valueNode != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[49]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[130]++;
int CodeCoverConditionCoverageHelper_C20;
          // For register* and overrideDefaultValue calls, ensure the default
          // value is a literal of the correct type.
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((registerFunc.isValidNodeType(valueNode.getType())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[51]++;
            compiler.report(JSError.make(call.sourceName,
                valueNode, INVALID_TWEAK_DEFAULT_VALUE_WARNING,
                tweakId, registerFunc.getName(),
                registerFunc.getExpectedTypeName()));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[131]++;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[52]++;}

        } else {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[50]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[132]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((tweakFunc.isGetterFunction()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[53]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[133]++;
int CodeCoverConditionCoverageHelper_C22;
          // For getter calls, ensure the correct getter was used.
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((tweakFunc.isCorrectRegisterFunction(registerFunc)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[55]++;
            compiler.report(JSError.make(call.sourceName,
                call.callNode, TWEAK_WRONG_GETTER_TYPE_WARNING,
                tweakFunc.getName(), registerFunc.getName()));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[134]++;

          } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[56]++;}

        } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[54]++;}
}
      }
    }

    /**
     * Emits an error for each function call that was found.
     */
    void emitUnknownTweakErrors() {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[135]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[25]++;


      for (TweakFunctionCall call : functionCalls) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[25]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[26]--;
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.loops[27]++;
}
        compiler.report(JSError.make(call.sourceName,
            call.getIdNode(), UNKNOWN_TWEAK_WARNING, tweakId));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[136]++;
      }
    }

    void addRegisterCall(String sourceName, TweakFunction tweakFunc,
        Node callNode, Node defaultValueNode) {
      registerCall = new TweakFunctionCall(sourceName, tweakFunc, callNode,
          defaultValueNode);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[137]++;
      functionCalls.add(registerCall);
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[138]++;
    }

    void addOverrideDefaultValueCall(String sourceName,
        TweakFunction tweakFunc, Node callNode, Node defaultValueNode) {
      functionCalls.add(new TweakFunctionCall(sourceName, tweakFunc, callNode,
          defaultValueNode));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[139]++;
      this.defaultValueNode = defaultValueNode;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[140]++;
    }

    void addGetterCall(String sourceName, TweakFunction tweakFunc,
        Node callNode) {
      functionCalls.add(new TweakFunctionCall(sourceName, tweakFunc, callNode));
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[141]++;
    }

    boolean isRegistered() {
      return registerCall != null;
    }

    Node getDefaultValueNode() {
      Preconditions.checkState(isRegistered());
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[142]++;
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[143]++;
int CodeCoverConditionCoverageHelper_C23;
      // Use calls to goog.tweak.overrideDefaultValue() first.
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((defaultValueNode != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[57]++;
        return defaultValueNode;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[58]++;}
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.statements[144]++;
int CodeCoverConditionCoverageHelper_C24;
      // Use the value passed to the register function next.
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((registerCall.valueNode != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[59]++;
        return registerCall.valueNode;

      } else {
  CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p.branches[60]++;}
      // Otherwise, use the default value for the tweak's type.
      return registerCall.tweakFunc.createDefaultValueNode();
    }
  }
}

class CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p ());
  }
    public static long[] statements = new long[145];
    public static long[] branches = new long[61];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ProcessTweaks.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 24; i++) {
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

  public CodeCoverCoverageCounter$6ou5jjihtx06bckmsqwb1xu8fb6p () {
    super("com.google.javascript.jscomp.ProcessTweaks.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 144; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 60; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ProcessTweaks.java");
      for (int i = 1; i <= 144; i++) {
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
    for (int i = 1; i <= 24; i++) {
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

