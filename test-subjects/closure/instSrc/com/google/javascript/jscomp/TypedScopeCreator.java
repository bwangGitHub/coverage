/*
 * Copyright 2004 The Closure Compiler Authors.
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

import static com.google.javascript.jscomp.TypeCheck.ENUM_NOT_CONSTANT;
import static com.google.javascript.jscomp.TypeCheck.MULTIPLE_VAR_DEF;
import static com.google.javascript.rhino.jstype.JSTypeNative.ARRAY_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.BOOLEAN_OBJECT_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.BOOLEAN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.DATE_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.EVAL_ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.FUNCTION_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.FUNCTION_INSTANCE_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.GLOBAL_THIS;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_OBJECT_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.RANGE_ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.REFERENCE_ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.REGEXP_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.REGEXP_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_OBJECT_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.SYNTAX_ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.TYPE_ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.U2U_CONSTRUCTOR_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.URI_ERROR_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.javascript.jscomp.CodingConvention.DelegateRelationship;
import com.google.javascript.jscomp.CodingConvention.ObjectLiteralCast;
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.CodingConvention.SubclassType;
import com.google.javascript.jscomp.FunctionTypeBuilder.AstFunctionContents;
import com.google.javascript.jscomp.NodeTraversal.AbstractScopedCallback;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowStatementCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.EnumType;
import com.google.javascript.rhino.jstype.FunctionParamBuilder;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.Property;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Creates the symbol table of variables available in the current scope and
 * their types.
 *
 * Scopes created by this class are very different from scopes created
 * by the syntactic scope creator. These scopes have type information, and
 * include some qualified names in addition to variables
 * (like Class.staticMethod).
 *
 * When building scope information, also declares relevant information
 * about types in the type registry.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
final class TypedScopeCreator implements ScopeCreator {
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.ping();
  }

  /**
   * A suffix for naming delegate proxies differently from their base.
   */
  static final String DELEGATE_PROXY_SUFFIX =
      ObjectType.createDelegateSuffix("Proxy");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[1]++;
  }

  static final DiagnosticType MALFORMED_TYPEDEF =
      DiagnosticType.warning(
          "JSC_MALFORMED_TYPEDEF",
          "Typedef for {0} does not have any type information");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[2]++;
  }

  static final DiagnosticType ENUM_INITIALIZER =
      DiagnosticType.warning(
          "JSC_ENUM_INITIALIZER_NOT_ENUM",
          "enum initializer must be an object literal or an enum");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[3]++;
  }

  static final DiagnosticType CTOR_INITIALIZER =
      DiagnosticType.warning(
          "JSC_CTOR_INITIALIZER_NOT_CTOR",
          "Constructor {0} must be initialized at declaration");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[4]++;
  }

  static final DiagnosticType IFACE_INITIALIZER =
      DiagnosticType.warning(
          "JSC_IFACE_INITIALIZER_NOT_IFACE",
          "Interface {0} must be initialized at declaration");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[5]++;
  }

  static final DiagnosticType CONSTRUCTOR_EXPECTED =
      DiagnosticType.warning(
          "JSC_REFLECT_CONSTRUCTOR_EXPECTED",
          "Constructor expected as first argument");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[6]++;
  }

  static final DiagnosticType UNKNOWN_LENDS =
      DiagnosticType.warning(
          "JSC_UNKNOWN_LENDS",
          "Variable {0} not declared before @lends annotation.");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[7]++;
  }

  static final DiagnosticType LENDS_ON_NON_OBJECT =
      DiagnosticType.warning(
          "JSC_LENDS_ON_NON_OBJECT",
          "May only lend properties to object types. {0} has type {1}.");
  static {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[8]++;
  }

  private final AbstractCompiler compiler;
  private final ErrorReporter typeParsingErrorReporter;
  private final TypeValidator validator;
  private final CodingConvention codingConvention;
  private final JSTypeRegistry typeRegistry;
  private final List<ObjectType> delegateProxyPrototypes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[9]++;
  }
  private final Map<String, String> delegateCallingConventions =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[10]++;
  }

  // Simple properties inferred about functions.
  private final Map<Node, AstFunctionContents> functionAnalysisResults =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[11]++;
  }

  // For convenience
  private final ObjectType unknownType;

  /**
   * Defer attachment of types to nodes until all type names
   * have been resolved. Then, we can resolve the type and attach it.
   */
  private class DeferredSetType {
    final Node node;
    final JSType type;

    DeferredSetType(Node node, JSType type) {
      Preconditions.checkNotNull(node);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[12]++;
      Preconditions.checkNotNull(type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[13]++;
      this.node = node;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[14]++;
      this.type = type;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[15]++;

      // Other parts of this pass may read off the node.
      // (like when we set the LHS of an assign with a typed RHS function.)
      node.setJSType(type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[16]++;
    }

    void resolve(Scope scope) {
      node.setJSType(type.resolve(typeParsingErrorReporter, scope));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[17]++;
    }
  }

  TypedScopeCreator(AbstractCompiler compiler) {
    this(compiler, compiler.getCodingConvention());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[18]++;
  }

  TypedScopeCreator(AbstractCompiler compiler,
      CodingConvention codingConvention) {
    this.compiler = compiler;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[19]++;
    this.validator = compiler.getTypeValidator();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[20]++;
    this.codingConvention = codingConvention;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[21]++;
    this.typeRegistry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[22]++;
    this.typeParsingErrorReporter = typeRegistry.getErrorReporter();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[23]++;
    this.unknownType = typeRegistry.getNativeObjectType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[24]++;
  }

  /**
   * Creates a scope with all types declared. Declares newly discovered types
   * and type properties in the type registry.
   */
  @Override
  public Scope createScope(Node root, Scope parent) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[25]++;
    // Constructing the global scope is very different than constructing
    // inner scopes, because only global scopes can contain named classes that
    // show up in the type registry.
    Scope newScope = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[26]++;
    AbstractScopeBuilder scopeBuilder = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[27]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[1]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[28]++;
      JSType globalThis =
          typeRegistry.getNativeObjectType(JSTypeNative.GLOBAL_THIS);

      // Mark the main root, the externs root, and the src root
      // with the global this type.
      root.setJSType(globalThis);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[29]++;
      root.getFirstChild().setJSType(globalThis);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[30]++;
      root.getLastChild().setJSType(globalThis);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[31]++;

      // Run a first-order analysis over the syntax tree.
      (new FirstOrderFunctionAnalyzer(compiler, functionAnalysisResults))
          .process(root.getFirstChild(), root.getLastChild());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[32]++;

      // Find all the classes in the global scope.
      newScope = createInitialScope(root);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[33]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[34]++;

      GlobalScopeBuilder globalScopeBuilder = new GlobalScopeBuilder(newScope);
      scopeBuilder = globalScopeBuilder;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[35]++;
      NodeTraversal.traverse(compiler, root, scopeBuilder);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[36]++;

    } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[2]++;
      newScope = new Scope(parent, root);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[37]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[38]++;
      LocalScopeBuilder localScopeBuilder = new LocalScopeBuilder(newScope);
      scopeBuilder = localScopeBuilder;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[39]++;
      localScopeBuilder.build();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[40]++;
    }

    scopeBuilder.resolveStubDeclarations();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[41]++;
    scopeBuilder.resolveTypes();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[42]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[43]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[1]++;



    // Gather the properties in each function that we found in the
    // global scope, if that function has a @this type that we can
    // build properties on.
    for (Node functionNode : scopeBuilder.nonExternFunctions) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[1]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[2]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[3]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[44]++;
      JSType type = functionNode.getJSType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[45]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[3]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[46]++;
        FunctionType fnType = type.toMaybeFunctionType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[47]++;
        JSType fnThisType = fnType.getTypeOfThis();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[48]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((fnThisType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[5]++;
          NodeTraversal.traverse(compiler, functionNode.getLastChild(),
              scopeBuilder.new CollectProperties(fnThisType));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[49]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[6]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[4]++;}
    }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[50]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[7]++;
      codingConvention.defineDelegateProxyPrototypeProperties(
          typeRegistry, newScope, delegateProxyPrototypes,
          delegateCallingConventions);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[51]++;

    } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[8]++;}
    return newScope;
  }

  /**
   * Patches a given global scope by removing variables previously declared in
   * a script and re-traversing a new version of that script.
   *
   * @param globalScope The global scope generated by {@code createScope}.
   * @param scriptRoot The script that is modified.
   */
  void patchGlobalScope(Scope globalScope, Node scriptRoot) {
    // Preconditions: This is supposed to be called only on (named) SCRIPT nodes
    // and a global typed scope should have been generated already.
    Preconditions.checkState(scriptRoot.isScript());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[52]++;
    Preconditions.checkNotNull(globalScope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[53]++;
    Preconditions.checkState(globalScope.isGlobal());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[54]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[55]++;

    String scriptName = NodeUtil.getSourceName(scriptRoot);
    Preconditions.checkNotNull(scriptName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[56]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[57]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[4]++;


    for (Node node : ImmutableList.copyOf(functionAnalysisResults.keySet())) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[4]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[5]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[6]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[58]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((scriptName.equals(NodeUtil.getSourceName(node))) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[9]++;
        functionAnalysisResults.remove(node);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[59]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[10]++;}
    }

    (new FirstOrderFunctionAnalyzer(
        compiler, functionAnalysisResults)).process(null, scriptRoot);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[60]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[61]++;

    // TODO(bashir): Variable declaration is not the only side effect of last
    // global scope generation but here we only wipe that part off!

    // Remove all variables that were previously declared in this scripts.
    // First find all vars to remove then remove them because of iterator!
    Iterator<Var> varIter = globalScope.getVars();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[62]++;
    List<Var> varsToRemove = Lists.newArrayList();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[63]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
    while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((varIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[7]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[8]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[9]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[64]++;
      Var oldVar = varIter.next();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[65]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((scriptName.equals(oldVar.getInputName())) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[11]++;
        varsToRemove.add(oldVar);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[66]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[12]++;}
    }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[67]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[10]++;


    for (Var var : varsToRemove) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[10]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[11]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[12]++;
}
      globalScope.undeclare(var);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[68]++;
      globalScope.getTypeOfThis().toObjectType().removeProperty(var.getName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[69]++;
    }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[70]++;

    // Now re-traverse the given script.
    GlobalScopeBuilder scopeBuilder = new GlobalScopeBuilder(globalScope);
    NodeTraversal.traverse(compiler, scriptRoot, scopeBuilder);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[71]++;
  }

  /**
   * Create the outermost scope. This scope contains native binding such as
   * {@code Object}, {@code Date}, etc.
   */
  @VisibleForTesting
  Scope createInitialScope(Node root) {

    NodeTraversal.traverse(
        compiler, root, new DiscoverEnumsAndTypedefs(typeRegistry));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[72]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[73]++;

    Scope s = Scope.createGlobalScope(root);
    declareNativeFunctionType(s, ARRAY_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[74]++;
    declareNativeFunctionType(s, BOOLEAN_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[75]++;
    declareNativeFunctionType(s, DATE_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[76]++;
    declareNativeFunctionType(s, ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[77]++;
    declareNativeFunctionType(s, EVAL_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[78]++;
    declareNativeFunctionType(s, FUNCTION_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[79]++;
    declareNativeFunctionType(s, NUMBER_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[80]++;
    declareNativeFunctionType(s, OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[81]++;
    declareNativeFunctionType(s, RANGE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[82]++;
    declareNativeFunctionType(s, REFERENCE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[83]++;
    declareNativeFunctionType(s, REGEXP_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[84]++;
    declareNativeFunctionType(s, STRING_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[85]++;
    declareNativeFunctionType(s, SYNTAX_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[86]++;
    declareNativeFunctionType(s, TYPE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[87]++;
    declareNativeFunctionType(s, URI_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[88]++;
    declareNativeValueType(s, "undefined", VOID_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[89]++;

    // There is no longer a need to special case ActiveXObject
    // but this remains here until we can get the extern forks
    // cleaned up.
    declareNativeValueType(s, "ActiveXObject", FUNCTION_INSTANCE_TYPE);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[90]++;

    return s;
  }

  private void declareNativeFunctionType(Scope scope, JSTypeNative tId) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[91]++;
    FunctionType t = typeRegistry.getNativeFunctionType(tId);
    declareNativeType(scope, t.getInstanceType().getReferenceName(), t);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[92]++;
    declareNativeType(
        scope, t.getPrototype().getReferenceName(), t.getPrototype());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[93]++;
  }

  private void declareNativeValueType(Scope scope, String name,
      JSTypeNative tId) {
    declareNativeType(scope, name, typeRegistry.getNativeType(tId));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[94]++;
  }

  private void declareNativeType(Scope scope, String name, JSType t) {
    scope.declare(name, null, t, null, false);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[95]++;
  }

  private static class DiscoverEnumsAndTypedefs
      extends AbstractShallowStatementCallback {
    private final JSTypeRegistry registry;

    DiscoverEnumsAndTypedefs(JSTypeRegistry registry) {
      this.registry = registry;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[96]++;
    }

    @Override
    public void visit(NodeTraversal t, Node node, Node parent) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[97]++;
      switch (node.getType()) {
        case Token.VAR:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[13]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[98]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[13]++;


int CodeCoverConditionCoverageHelper_C8;
          for (Node child = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[13]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[14]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[15]++;
}
            identifyNameNode(
                child, NodeUtil.getBestJSDocInfo(child));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[99]++;
          }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[100]++;
          break;
        case Token.EXPR_RESULT:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[14]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[101]++;
          Node firstChild = node.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[102]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((firstChild.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[15]++;
            identifyNameNode(
                firstChild.getFirstChild(), firstChild.getJSDocInfo());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[103]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[16]++;
            identifyNameNode(
                firstChild, firstChild.getJSDocInfo());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[104]++;
          }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[105]++;
          break; default : CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[17]++;
      }
    }

    private void identifyNameNode(
        Node nameNode, JSDocInfo info) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[106]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nameNode.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[18]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[107]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[20]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[108]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((info.hasEnumParameterType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[22]++;
            registry.identifyNonNullableName(nameNode.getQualifiedName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[109]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[23]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[110]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((info.hasTypedefType()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[24]++;
            registry.identifyNonNullableName(nameNode.getQualifiedName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[111]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[25]++;}
}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[21]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[19]++;}
    }
  }

  private JSType getNativeType(JSTypeNative nativeType) {
    return typeRegistry.getNativeType(nativeType);
  }

  private abstract class AbstractScopeBuilder
      implements NodeTraversal.Callback {

    /**
     * The scope that we're building.
     */
    final Scope scope;

    private final List<DeferredSetType> deferredSetTypes =
        Lists.newArrayList();
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[112]++;
  }

    /**
     * Functions that we found in the global scope and not in externs.
     */
    private final List<Node> nonExternFunctions = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[113]++;
  }

    /**
     * Object literals with a @lends annotation aren't analyzed until we
     * reach the root of the statement they're defined in.
     *
     * This ensures that if there are any @lends annotations on the object
     * literals, the type on the @lends annotation resolves correctly.
     *
     * For more information, see
     * http://code.google.com/p/closure-compiler/issues/detail?id=314
     */
    private List<Node> lentObjectLiterals = null;
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[114]++;
  }

    /**
     * Type-less stubs.
     *
     * If at the end of traversal, we still don't have types for these
     * stubs, then we should declare UNKNOWN types.
     */
    private final List<StubDeclaration> stubDeclarations =
        Lists.newArrayList();
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[115]++;
  }

    /**
     * The current source file that we're in.
     */
    private String sourceName = null;
  {
    CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[116]++;
  }

    /**
     * The InputId of the current node.
     */
    private InputId inputId;

    private AbstractScopeBuilder(Scope scope) {
      this.scope = scope;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[117]++;
    }

    void setDeferredType(Node node, JSType type) {
      deferredSetTypes.add(new DeferredSetType(node, type));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[118]++;
    }

    void resolveTypes() {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[119]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[16]++;


      // Resolve types and attach them to nodes.
      for (DeferredSetType deferred : deferredSetTypes) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[16]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[17]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[18]++;
}
        deferred.resolve(scope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[120]++;
      }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[121]++;

      // Resolve types and attach them to scope slots.
      Iterator<Var> vars = scope.getVars();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[122]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
      while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((vars.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[19]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[20]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[21]++;
}
        vars.next().resolveType(typeParsingErrorReporter);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[123]++;
      }

      // Tell the type registry that any remaining types
      // are unknown.
      typeRegistry.resolveTypesInScope(scope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[124]++;
    }

    @Override
    public final boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      inputId = t.getInputId();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[125]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[126]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[26]++;
        Preconditions.checkNotNull(inputId);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[127]++;
        sourceName = NodeUtil.getSourceName(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[128]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[27]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[129]++;

      // We do want to traverse the name of a named function, but we don't
      // want to traverse the arguments or body.
      boolean descend = parent == null || !parent.isFunction() ||
          n == parent.getFirstChild() || parent == scope.getRootNode();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[130]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((descend) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[28]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[131]++;
int CodeCoverConditionCoverageHelper_C17;
        // Handle hoisted functions on pre-order traversal, so that they
        // get hit before other things in the scope.
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementParent(n)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[30]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[132]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[22]++;


int CodeCoverConditionCoverageHelper_C18;
          for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false);
               child = child.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[22]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[23]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[24]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[133]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((NodeUtil.isHoistedFunctionDeclaration(child)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[32]++;
              defineFunctionLiteral(child);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[134]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[33]++;}
          }

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[31]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[29]++;}

      return descend;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
      inputId = t.getInputId();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[135]++;
      attachLiteralTypes(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[136]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[137]++;

      switch (n.getType()) {
        case Token.CALL:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[34]++;
          checkForClassDefiningCalls(t, n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[138]++;
          checkForCallingConventionDefiningCalls(n, delegateCallingConventions);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[139]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[140]++;
          break;

        case Token.FUNCTION:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[35]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[141]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((t.getInput() == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((t.getInput().isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[36]++;
            nonExternFunctions.add(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[142]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[37]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[143]++;
int CodeCoverConditionCoverageHelper_C21;

          // Hoisted functions are handled during pre-traversal.
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.isHoistedFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[38]++;
            defineFunctionLiteral(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[144]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[39]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[145]++;
          break;

        case Token.ASSIGN:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[40]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[146]++;
          // Handle initialization of properties.
          Node firstChild = n.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[147]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((firstChild.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((firstChild.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[41]++;
            maybeDeclareQualifiedName(t, n.getJSDocInfo(),
                firstChild, n, firstChild.getNext());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[148]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[42]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[149]++;
          break;

        case Token.CATCH:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[43]++;
          defineCatch(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[150]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[151]++;
          break;

        case Token.VAR:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[44]++;
          defineVar(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[152]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[153]++;
          break;

        case Token.GETPROP:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[45]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[154]++;
int CodeCoverConditionCoverageHelper_C23;
          // Handle stubbed properties.
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[46]++;
            maybeDeclareQualifiedName(t, n.getJSDocInfo(), n, parent, null);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[155]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[47]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[156]++;
          break; default : CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[48]++;
      }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[157]++;
int CodeCoverConditionCoverageHelper_C24;

      // Analyze any @lends object literals in this statement.
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((n.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((NodeUtil.isStatement(n)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((lentObjectLiterals != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[49]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[158]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[25]++;


        for (Node objLit : lentObjectLiterals) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[25]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[26]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[27]++;
}
          defineObjectLiteral(objLit);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[159]++;
        }
        lentObjectLiterals.clear();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[160]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[50]++;}
    }

    private void attachLiteralTypes(Node n) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[161]++;
      switch (n.getType()) {
        case Token.NULL:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[51]++;
          n.setJSType(getNativeType(NULL_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[162]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[163]++;
          break;

        case Token.VOID:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[52]++;
          n.setJSType(getNativeType(VOID_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[164]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[165]++;
          break;

        case Token.STRING:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[53]++;
          n.setJSType(getNativeType(STRING_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[166]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[167]++;
          break;

        case Token.NUMBER:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[54]++;
          n.setJSType(getNativeType(NUMBER_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[168]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[169]++;
          break;

        case Token.TRUE:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[55]++;
        case Token.FALSE:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[56]++;
          n.setJSType(getNativeType(BOOLEAN_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[170]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[171]++;
          break;

        case Token.REGEXP:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[57]++;
          n.setJSType(getNativeType(REGEXP_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[172]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[173]++;
          break;

        case Token.OBJECTLIT:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[58]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[174]++;
          JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[175]++;
int CodeCoverConditionCoverageHelper_C25;
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((info.getLendsName() != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[59]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[176]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lentObjectLiterals == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[61]++;
              lentObjectLiterals = Lists.newArrayList();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[177]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[62]++;}
            lentObjectLiterals.add(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[178]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[60]++;
            defineObjectLiteral(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[179]++;
          }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[180]++;
          break; default : CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[63]++;

          // NOTE(nicksantos): If we ever support Array tuples,
          // we will need to put ARRAYLIT here as well.
      }
    }

    private void defineObjectLiteral(Node objectLit) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[181]++;
      // Handle the @lends annotation.
      JSType type = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[182]++;
      JSDocInfo info = objectLit.getJSDocInfo();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[183]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((info.getLendsName() != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[64]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[184]++;
        String lendsName = info.getLendsName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[185]++;
        Var lendsVar = scope.getVar(lendsName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[186]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((lendsVar == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[66]++;
          compiler.report(
              JSError.make(sourceName, objectLit, UNKNOWN_LENDS, lendsName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[187]++;

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[67]++;
          type = lendsVar.getType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[188]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[189]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[68]++;
            type = unknownType;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[190]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[69]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[191]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type.isSubtype(typeRegistry.getNativeType(OBJECT_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[70]++;
            compiler.report(
                JSError.make(sourceName, objectLit, LENDS_ON_NON_OBJECT,
                    lendsName, type.toString()));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[192]++;
            type = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[193]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[71]++;
            objectLit.setJSType(type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[194]++;
          }
        }

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[65]++;}

      info = NodeUtil.getBestJSDocInfo(objectLit);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[195]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[196]++;
      Node lValue = NodeUtil.getBestLValue(objectLit);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[197]++;
      String lValueName = NodeUtil.getBestLValueName(lValue);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[198]++;
      boolean createdEnumType = false;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[199]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((info.hasEnumParameterType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[72]++;
        type = createEnumTypeFromNodes(objectLit, lValueName, info, lValue);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[200]++;
        createdEnumType = true;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[201]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[73]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[202]++;
int CodeCoverConditionCoverageHelper_C32;

      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[74]++;
        type = typeRegistry.createAnonymousObjectType(info);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[203]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[75]++;}

      setDeferredType(objectLit, type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[204]++;

      // If this is an enum, the properties were already taken care of above.
      processObjectLitProperties(
          objectLit, ObjectType.cast(objectLit.getJSType()), !createdEnumType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[205]++;
    }

    /**
     * Process an object literal and all the types on it.
     * @param objLit The OBJECTLIT node.
     * @param objLitType The type of the OBJECTLIT node. This might be a named
     *     type, because of the lends annotation.
     * @param declareOnOwner If true, declare properties on the objLitType as
     *     well. If false, the caller should take care of this.
     */
    void processObjectLitProperties(
        Node objLit, ObjectType objLitType,
        boolean declareOnOwner) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[206]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[28]++;


int CodeCoverConditionCoverageHelper_C33;
      for (Node keyNode = objLit.getFirstChild();(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((keyNode != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false);
           keyNode = keyNode.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[28]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[29]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[30]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[207]++;
        Node value = keyNode.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[208]++;
        String memberName = NodeUtil.getObjectLitKeyName(keyNode);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[209]++;
        JSDocInfo info = keyNode.getJSDocInfo();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[210]++;
        JSType valueType = getDeclaredType(info, keyNode, value);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[211]++;
        JSType keyType =  objLitType.isEnumType() ?
            objLitType.toMaybeEnumType().getElementsType() :
            NodeUtil.getObjectLitKeyTypeFromValueType(keyNode, valueType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[212]++;

        // Try to declare this property in the current scope if it
        // has an authoritative name.
        String qualifiedName = NodeUtil.getBestLValueName(keyNode);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[213]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[76]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[214]++;
          boolean inferred = keyType == null;
          defineSlot(keyNode, objLit, qualifiedName, keyType, inferred);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[215]++;

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[77]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[216]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((keyType != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[78]++;
          setDeferredType(keyNode, keyType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[217]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[79]++;}
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[218]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((keyType != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((objLitType != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((declareOnOwner) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[80]++;
          // Declare this property on its object literal.
          objLitType.defineDeclaredProperty(memberName, keyType, keyNode);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[219]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[81]++;}
      }
    }

    /**
     * Returns the type specified in a JSDoc annotation near a GETPROP or NAME.
     *
     * Extracts type information from either the {@code @type} tag or from
     * the {@code @return} and {@code @param} tags.
     */
    private JSType getDeclaredTypeInAnnotation(Node node, JSDocInfo info) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[220]++;
      JSType jsType = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[221]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[82]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[222]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[84]++;
          jsType = info.getType().evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[223]++;

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[85]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[224]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((FunctionTypeBuilder.isFunctionTypeDeclaration(info)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[86]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[225]++;
          String fnName = node.getQualifiedName();
          jsType = createFunctionTypeFromNodes(
              null, fnName, info, node);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[226]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[87]++;}
}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[83]++;}
      return jsType;
    }

    /**
     * Asserts that it's OK to define this node's name.
     * The node should have a source name and be of the specified type.
     */
    void assertDefinitionNode(Node n, int type) {
      Preconditions.checkState(sourceName != null);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[227]++;
      Preconditions.checkState(n.getType() == type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[228]++;
    }

    /**
     * Defines a catch parameter.
     */
    void defineCatch(Node n) {
      assertDefinitionNode(n, Token.CATCH);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[229]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[230]++;
      Node catchName = n.getFirstChild();
      defineSlot(catchName, n,
          getDeclaredType(
              catchName.getJSDocInfo(), catchName, null));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[231]++;
    }

    /**
     * Defines a VAR initialization.
     */
    void defineVar(Node n) {
      assertDefinitionNode(n, Token.VAR);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[232]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[233]++;
      JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[234]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((n.hasMoreThanOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[88]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[235]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[90]++;
          // multiple children
          compiler.report(JSError.make(sourceName, n, MULTIPLE_VAR_DEF));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[236]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[91]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[237]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[31]++;


        for (Node name : n.children()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[31]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[32]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[33]++;
}
          defineName(name, n, name.getJSDocInfo());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[238]++;
        }

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[89]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[239]++;
        Node name = n.getFirstChild();
        defineName(name, n, (info != null) ? info : name.getJSDocInfo());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[240]++;
      }
    }

    /**
     * Defines a function literal.
     */
    void defineFunctionLiteral(Node n) {
      assertDefinitionNode(n, Token.FUNCTION);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[241]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[242]++;

      // Determine the name and JSDocInfo and l-value for the function.
      // Any of these may be null.
      Node lValue = NodeUtil.getBestLValue(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[243]++;
      JSDocInfo info = NodeUtil.getBestJSDocInfo(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[244]++;
      String functionName = NodeUtil.getBestLValueName(lValue);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[245]++;
      FunctionType functionType =
          createFunctionTypeFromNodes(n, functionName, info, lValue);

      // Assigning the function type to the function node
      setDeferredType(n, functionType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[246]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[247]++;
int CodeCoverConditionCoverageHelper_C42;

      // Declare this symbol in the current scope iff it's a function
      // declaration. Otherwise, the declaration will happen in other
      // code paths.
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[92]++;
        defineSlot(n.getFirstChild(), n, functionType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[248]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[93]++;}
    }

    /**
     * Defines a variable based on the {@link Token#NAME} node passed.
     * @param name The {@link Token#NAME} node.
     * @param var The parent of the {@code name} node, which must be a
     *     {@link Token#VAR} node.
     * @param info the {@link JSDocInfo} information relating to this
     *     {@code name} node.
     */
    private void defineName(Node name, Node var, JSDocInfo info) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[249]++;
      Node value = name.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[250]++;

      // variable's type
      JSType type = getDeclaredType(info, name, value);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[251]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[94]++;
        // The variable's type will be inferred.
        type = name.isFromExterns() ? unknownType : null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[252]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[95]++;}
      defineSlot(name, var, type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[253]++;
    }

    /**
     * If a variable is assigned a function literal in the global scope,
     * make that a declared type (even if there's no doc info).
     * There's only one exception to this rule:
     * if the return type is inferred, and we're in a local
     * scope, we should assume the whole function is inferred.
     */
    private boolean shouldUseFunctionLiteralType(
        FunctionType type, JSDocInfo info, Node lValue) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[254]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[96]++;
        return true;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[97]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[255]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((lValue != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[98]++;
        return false;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[99]++;}
      return scope.isGlobal() || !type.isReturnTypeInferred();
    }

    /**
     * Creates a new function type, based on the given nodes.
     *
     * This handles two cases that are semantically very different, but
     * are not mutually exclusive:
     * - A function literal that needs a type attached to it.
     * - An assignment expression with function-type info in the JsDoc.
     *
     * All parameters are optional, and we will do the best we can to create
     * a function type.
     *
     * This function will always create a function type, so only call it if
     * you're sure that's what you want.
     *
     * @param rValue The function node.
     * @param name the function's name
     * @param info the {@link JSDocInfo} attached to the function definition
     * @param lvalueNode The node where this function is being
     *     assigned. For example, {@code A.prototype.foo = ...} would be used to
     *     determine that this function is a method of A.prototype. May be
     *     null to indicate that this is not being assigned to a qualified name.
     */
    private FunctionType createFunctionTypeFromNodes(
        @Nullable Node rValue,
        @Nullable String name,
        @Nullable JSDocInfo info,
        @Nullable Node lvalueNode) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[256]++;

      FunctionType functionType = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[257]++;
int CodeCoverConditionCoverageHelper_C46;

      // Global ctor aliases should be registered with the type registry.
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (32)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((rValue.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[100]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[258]++;
        Var var = scope.getVar(rValue.getQualifiedName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[259]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (32)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((var.getType() != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((var.getType().isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[102]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[260]++;
          FunctionType aliasedType  = var.getType().toMaybeFunctionType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[261]++;
int CodeCoverConditionCoverageHelper_C48;
          if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C48 |= (32)) == 0 || true) &&
 ((aliasedType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((aliasedType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((aliasedType.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[104]++;
            functionType = aliasedType;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[262]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[263]++;
int CodeCoverConditionCoverageHelper_C49;

            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[106]++;
              typeRegistry.declareType(name, functionType.getInstanceType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[264]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[107]++;}

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[105]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[103]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[101]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[265]++;
int CodeCoverConditionCoverageHelper_C50;

      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((functionType == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[108]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[266]++;
        Node errorRoot = rValue == null ? lvalueNode : rValue;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[267]++;
        boolean isFnLiteral =
            rValue != null && rValue.isFunction();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[268]++;
        Node fnRoot = isFnLiteral ? rValue : null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[269]++;
        Node parametersNode = isFnLiteral ?
            rValue.getFirstChild().getNext() : null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[270]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[110]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[271]++;
          JSType type = info.getType().evaluate(scope, typeRegistry);

          // Known to be not null since we have the FUNCTION token there.
          type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[272]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[273]++;
int CodeCoverConditionCoverageHelper_C52;
          if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((type.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[112]++;
            functionType = type.toMaybeFunctionType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[274]++;
            functionType.setJSDocInfo(info);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[275]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[113]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[111]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[276]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((functionType == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[114]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[277]++;
          // Find the type of any overridden function.
          Node ownerNode = NodeUtil.getBestLValueOwner(lvalueNode);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[278]++;
          String ownerName = NodeUtil.getBestLValueName(ownerNode);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[279]++;
          Var ownerVar = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[280]++;
          String propName = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[281]++;
          ObjectType ownerType = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[282]++;
int CodeCoverConditionCoverageHelper_C54;
          if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ownerName != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[116]++;
            ownerVar = scope.getVar(ownerName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[283]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[284]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((ownerVar != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[118]++;
              ownerType = ObjectType.cast(ownerVar.getType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[285]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[119]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[286]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[120]++;
              propName = name.substring(ownerName.length() + 1);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[287]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[121]++;}

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[117]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[288]++;

          FunctionType overriddenType = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[289]++;
int CodeCoverConditionCoverageHelper_C57;
          if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((propName != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[122]++;
            overriddenType = findOverriddenFunction(ownerType, propName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[290]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[123]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[291]++;

          FunctionTypeBuilder builder =
              new FunctionTypeBuilder(name, compiler, errorRoot, sourceName,
                  scope)
              .setContents(getFunctionAnalysisResults(fnRoot))
              .inferFromOverriddenFunction(overriddenType, parametersNode)
              .inferTemplateTypeName(info)
              .inferReturnType(info)
              .inferInheritance(info);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[292]++;

          // Infer the context type.
          boolean searchedForThisType = false;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[293]++;
int CodeCoverConditionCoverageHelper_C58;
          if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (32)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((ownerType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((ownerType.getOwnerFunction().hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[124]++;
            builder.inferThisType(
                info, ownerType.getOwnerFunction().getInstanceType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[294]++;
            searchedForThisType = true;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[295]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[125]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[296]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((ownerNode != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((ownerNode.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[126]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[297]++;
            // If 'this' has a type, use that instead.
            // This is a hack, necessary because CollectProperties (below)
            // doesn't run with the scope that it's building,
            // so scope.getTypeOfThis() will be wrong.
            JSType injectedThisType = ownerNode.getJSType();
            builder.inferThisType(
                info,
                injectedThisType == null ?
                scope.getTypeOfThis() : injectedThisType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[298]++;
            searchedForThisType = true;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[299]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[127]++;}
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[300]++;
int CodeCoverConditionCoverageHelper_C60;

          if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((searchedForThisType) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[128]++;
            builder.inferThisType(info);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[301]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[129]++;}

          functionType = builder
              .inferParameterTypes(parametersNode, info)
              .buildAndRegister();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[302]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[115]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[109]++;}

      // all done
      return functionType;
    }

    /**
     * Find the function that's being overridden on this type, if any.
     */
    private FunctionType findOverriddenFunction(
        ObjectType ownerType, String propName) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[303]++;
      // First, check to see if the property is implemented
      // on a superclass.
      JSType propType = ownerType.getPropertyType(propName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[304]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((propType != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((propType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[130]++;
        return propType.toMaybeFunctionType();

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[131]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[305]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[34]++;


        // If it's not, then check to see if it's implemented
        // on an implemented interface.
        for (ObjectType iface :
                 ownerType.getCtorImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[34]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[35]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[36]++;
}
          propType = iface.getPropertyType(propName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[306]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[307]++;
int CodeCoverConditionCoverageHelper_C62;
          if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((propType != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((propType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[132]++;
            return propType.toMaybeFunctionType();

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[133]++;}
        }
      }

      return null;
    }

    /**
     * Creates a new enum type, based on the given nodes.
     *
     * This handles two cases that are semantically very different, but
     * are not mutually exclusive:
     * - An object literal that needs an enum type attached to it.
     * - An assignment expression with an enum tag in the JsDoc.
     *
     * This function will always create an enum type, so only call it if
     * you're sure that's what you want.
     *
     * @param rValue The node of the enum.
     * @param name The enum's name
     * @param info The {@link JSDocInfo} attached to the enum definition.
     * @param lValueNode The node where this function is being
     *     assigned.
     */
    private EnumType createEnumTypeFromNodes(Node rValue, String name,
        JSDocInfo info, Node lValueNode) {
      Preconditions.checkNotNull(info);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[308]++;
      Preconditions.checkState(info.hasEnumParameterType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[309]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[310]++;

      EnumType enumType = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[311]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((rValue.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[134]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[312]++;
        // Handle an aliased enum.
        Var var = scope.getVar(rValue.getQualifiedName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[313]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((var.getType() instanceof EnumType) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[136]++;
          enumType = (EnumType) var.getType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[314]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[137]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[135]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[315]++;
int CodeCoverConditionCoverageHelper_C65;

      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((enumType == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[138]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[316]++;
        JSType elementsType =
            info.getEnumParameterType().evaluate(scope, typeRegistry);
        enumType = typeRegistry.createEnumType(name, rValue, elementsType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[317]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[318]++;
int CodeCoverConditionCoverageHelper_C66;

        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((rValue.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[140]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[319]++;
          // collect enum elements
          Node key = rValue.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[320]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[37]++;


int CodeCoverConditionCoverageHelper_C67;
          while ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[37]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[38]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[39]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[321]++;
            String keyName = NodeUtil.getStringValue(key);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[322]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((keyName == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[142]++;
              // GET and SET don't have a String value;
              compiler.report(
                  JSError.make(sourceName, key, ENUM_NOT_CONSTANT, keyName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[323]++;

            } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[143]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[324]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((codingConvention.isValidEnumKey(keyName)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[144]++;
              compiler.report(
                  JSError.make(sourceName, key, ENUM_NOT_CONSTANT, keyName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[325]++;

            } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[145]++;
              enumType.defineElement(keyName, key);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[326]++;
            }
}
            key = key.getNext();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[327]++;
          }

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[141]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[139]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[328]++;
int CodeCoverConditionCoverageHelper_C70;

      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[146]++;
        typeRegistry.declareType(name, enumType.getElementsType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[329]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[147]++;}

      return enumType;
    }

    /**
     * Defines a typed variable. The defining node will be annotated with the
     * variable's type or {@code null} if its type is inferred.
     * @param name the defining node. It must be a {@link Token#NAME}.
     * @param parent the {@code name}'s parent.
     * @param type the variable's type. It may be {@code null}, in which case
     *     the variable's type will be inferred.
     */
    private void defineSlot(Node name, Node parent, JSType type) {
      defineSlot(name, parent, type, type == null);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[330]++;
    }

    /**
     * Defines a typed variable. The defining node will be annotated with the
     * variable's type of {@link JSTypeNative#UNKNOWN_TYPE} if its type is
     * inferred.
     *
     * Slots may be any variable or any qualified name in the global scope.
     *
     * @param n the defining NAME or GETPROP node.
     * @param parent the {@code n}'s parent.
     * @param type the variable's type. It may be {@code null} if
     *     {@code inferred} is {@code true}.
     */
    void defineSlot(Node n, Node parent, JSType type, boolean inferred) {
      Preconditions.checkArgument(inferred || type != null);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[331]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[332]++;
int CodeCoverConditionCoverageHelper_C71;

      // Only allow declarations of NAMEs and qualified names.
      // Object literal keys will have to compute their names themselves.
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[148]++;
        Preconditions.checkArgument(
            parent.isFunction() ||
            parent.isVar() ||
            parent.isParamList() ||
            parent.isCatch());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[333]++;

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[149]++;
        Preconditions.checkArgument(
            n.isGetProp() &&
            (parent.isAssign() ||
             parent.isExprResult()));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[334]++;
      }
      defineSlot(n, parent, n.getQualifiedName(), type, inferred);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[335]++;
    }


    /**
     * Defines a symbol in the current scope.
     *
     * @param n the defining NAME or GETPROP or object literal key node.
     * @param parent the {@code n}'s parent.
     * @param variableName The name that this should be known by.
     * @param type the variable's type. It may be {@code null} if
     *     {@code inferred} is {@code true}.
     * @param inferred Whether the type is inferred or declared.
     */
    void defineSlot(Node n, Node parent, String variableName,
        JSType type, boolean inferred) {
      Preconditions.checkArgument(!variableName.isEmpty());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[336]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[337]++;

      boolean isGlobalVar = n.isName() && scope.isGlobal();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[338]++;
      boolean shouldDeclareOnGlobalThis =
          isGlobalVar &&
          (parent.isVar() ||
           parent.isFunction());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[339]++;

      // If n is a property, then we should really declare it in the
      // scope where the root object appears. This helps out people
      // who declare "global" names in an anonymous namespace.
      Scope scopeToDeclareIn = scope;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[340]++;
int CodeCoverConditionCoverageHelper_C72;
      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (32)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((isQnameRootedInGlobalScope(n)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[150]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[341]++;
        Scope globalScope = scope.getGlobalScope();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[342]++;
int CodeCoverConditionCoverageHelper_C73;

        // don't try to declare in the global scope if there's
        // already a symbol there with this name.
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((globalScope.isDeclared(variableName, false)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[152]++;
          scopeToDeclareIn = scope.getGlobalScope();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[343]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[153]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[151]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[344]++;

      // The input may be null if we are working with a AST snippet. So read
      // the extern info from the node.
      Var newVar = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[345]++;

      // declared in closest scope?
      CompilerInput input = compiler.getInput(inputId);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[346]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((scopeToDeclareIn.isDeclared(variableName, false)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[154]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[347]++;
        Var oldVar = scopeToDeclareIn.getVar(variableName);
        newVar = validator.expectUndeclaredVariable(
            sourceName, input, n, parent, oldVar, variableName, type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[348]++;

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[155]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[349]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[156]++;
          setDeferredType(n, type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[350]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[157]++;}

        newVar =
          scopeToDeclareIn.declare(variableName, n, type, input, inferred);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[351]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[352]++;
int CodeCoverConditionCoverageHelper_C76;

        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((type instanceof EnumType) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[158]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[353]++;
          Node initialValue = newVar.getInitialValue();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[354]++;
          boolean isValidValue = initialValue != null &&
              (initialValue.isObjectLit() ||
               initialValue.isQualifiedName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[355]++;
int CodeCoverConditionCoverageHelper_C77;
          if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((isValidValue) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[160]++;
            compiler.report(JSError.make(sourceName, n, ENUM_INITIALIZER));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[356]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[161]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[159]++;}
      }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[357]++;

      // We need to do some additional work for constructors and interfaces.
      FunctionType fnType = JSType.toMaybeFunctionType(type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[358]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((fnType != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((type.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[162]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[359]++;
int CodeCoverConditionCoverageHelper_C79;

        // We want to make sure that when we declare a new instance type
        // (with @constructor) that there's actually a ctor for it.
        // This doesn't apply to structural constructors (like
        // function(new:Array). Checking the constructed type against
        // the variable name is a sufficient check for this.
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C79 |= (32)) == 0 || true) &&
 ((fnType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((fnType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((variableName.equals(fnType.getReferenceName())) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[164]++;
          finishConstructorDefinition(n, variableName, fnType, scopeToDeclareIn,
                                      input, newVar);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[360]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[165]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[163]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[361]++;
int CodeCoverConditionCoverageHelper_C80;

      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((shouldDeclareOnGlobalThis) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[166]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[362]++;
        ObjectType globalThis =
            typeRegistry.getNativeObjectType(GLOBAL_THIS);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[363]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((inferred) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[168]++;
          globalThis.defineInferredProperty(variableName,
              type == null ?
              getNativeType(JSTypeNative.NO_TYPE) :
              type,
              n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[364]++;

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[169]++;
          globalThis.defineDeclaredProperty(variableName, type, n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[365]++;
        }

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[167]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[366]++;
int CodeCoverConditionCoverageHelper_C82;

      if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (512)) == 0 || true) &&
 ((isGlobalVar) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (128)) == 0 || true) &&
 (("Window".equals(variableName)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (32)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((type.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((type.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 5) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 5) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[170]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[367]++;
        FunctionType globalThisCtor =
            typeRegistry.getNativeObjectType(GLOBAL_THIS).getConstructor();
        globalThisCtor.getInstanceType().clearCachedValues();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[368]++;
        globalThisCtor.getPrototype().clearCachedValues();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[369]++;
        globalThisCtor
            .setPrototypeBasedOn((type.toMaybeFunctionType()).getInstanceType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[370]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[171]++;}
    }

    private void finishConstructorDefinition(
        Node n, String variableName, FunctionType fnType,
        Scope scopeToDeclareIn, CompilerInput input, Var newVar) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[371]++;
      // Declare var.prototype in the scope chain.
      FunctionType superClassCtor = fnType.getSuperClassConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[372]++;
      Property prototypeSlot = fnType.getSlot("prototype");

      // When we declare the function prototype implicitly, we
      // want to make sure that the function and its prototype
      // are declared at the same node. We also want to make sure
      // that the if a symbol has both a Var and a JSType, they have
      // the same node.
      //
      // This consistency is helpful to users of SymbolTable,
      // because everything gets declared at the same place.
      prototypeSlot.setNode(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[373]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[374]++;

      String prototypeName = variableName + ".prototype";
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[375]++;

      // There are some rare cases where the prototype will already
      // be declared. See TypedScopeCreatorTest#testBogusPrototypeInit.
      // Fortunately, other warnings will complain if this happens.
      Var prototypeVar = scopeToDeclareIn.getVar(prototypeName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[376]++;
int CodeCoverConditionCoverageHelper_C83;
      if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((prototypeVar != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((prototypeVar.scope == scopeToDeclareIn) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[172]++;
        scopeToDeclareIn.undeclare(prototypeVar);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[377]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[173]++;}

      scopeToDeclareIn.declare(prototypeName,
          n, prototypeSlot.getType(), input,
          /* declared iff there's an explicit supertype */
          superClassCtor == null ||
          superClassCtor.getInstanceType().isEquivalentTo(
              getNativeType(OBJECT_TYPE)));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[378]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[379]++;
int CodeCoverConditionCoverageHelper_C84;

      // Make sure the variable is initialized to something if
      // it constructs itself.
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((newVar.getInitialValue() == null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((n.isFromExterns()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[174]++;
        compiler.report(
            JSError.make(sourceName, n,
                fnType.isConstructor() ?
                CTOR_INITIALIZER : IFACE_INITIALIZER,
                variableName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[380]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[175]++;}
    }

    /**
     * Check if the given node is a property of a name in the global scope.
     */
    private boolean isQnameRootedInGlobalScope(Node n) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[381]++;
      Scope scope = getQnameRootScope(n);
      return scope != null && scope.isGlobal();
    }

    /**
     * Return the scope for the name of the given node.
     */
    private Scope getQnameRootScope(Node n) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[382]++;
      Node root = NodeUtil.getRootOfQualifiedName(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[383]++;
int CodeCoverConditionCoverageHelper_C85;
      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((root.isName()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[176]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[384]++;
        Var var = scope.getVar(root.getString());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[385]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[178]++;
          return var.getScope();

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[179]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[177]++;}
      return null;
    }

    /**
     * Look for a type declaration on a property assignment
     * (in an ASSIGN or an object literal key).
     * @param info The doc info for this property.
     * @param lValue The l-value node.
     * @param rValue The node that {@code n} is being initialized to,
     *     or {@code null} if this is a stub declaration.
     */
    private JSType getDeclaredType(JSDocInfo info, Node lValue,
        @Nullable Node rValue) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[386]++;
int CodeCoverConditionCoverageHelper_C87;
      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[180]++;
        return getDeclaredTypeInAnnotation(lValue, info);

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[181]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[387]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (32)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((shouldUseFunctionLiteralType(
              JSType.toMaybeFunctionType(rValue.getJSType()), info, lValue)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[182]++;
        return rValue.getJSType();

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[183]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[388]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[184]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[389]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((info.hasEnumParameterType()) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[186]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[390]++;
int CodeCoverConditionCoverageHelper_C91;
          if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((rValue.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[188]++;
            return rValue.getJSType();

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[189]++;
            return createEnumTypeFromNodes(
                rValue, lValue.getQualifiedName(), info, lValue);
          }

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[187]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[391]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((info.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[190]++;
          return createFunctionTypeFromNodes(
              rValue, lValue.getQualifiedName(), info, lValue);

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[191]++;}
}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[185]++;}
}
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[392]++;
int CodeCoverConditionCoverageHelper_C93;

      // Check if this is constant, and if it has a known type.
      if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((isConstantSymbol(info, lValue)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[192]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[393]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[194]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[394]++;
          JSDocInfo rValueInfo = rValue.getJSDocInfo();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[395]++;
int CodeCoverConditionCoverageHelper_C95;
          if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((rValueInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((rValueInfo.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[196]++;
            // If rValue has a type-cast, we use the type in the type-cast.
            return rValueInfo.getType().evaluate(scope, typeRegistry);

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[197]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[396]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (8)) == 0 || true) &&
 ((rValue.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((rValue.getJSType().isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[198]++;
            // If rValue's type was already computed during scope creation,
            // then we can safely use that.
            return rValue.getJSType();

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[199]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[397]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((rValue.isOr()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[200]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[398]++;
            // Check for a very specific JS idiom:
            // var x = x || TYPE;
            // This is used by Closure's base namespace for esoteric
            // reasons.
            Node firstClause = rValue.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[399]++;
            Node secondClause = firstClause.getNext();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[400]++;
            boolean namesMatch = firstClause.isName()
                && lValue.isName()
                && firstClause.getString().equals(lValue.getString());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[401]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (32)) == 0 || true) &&
 ((namesMatch) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C98 |= (8)) == 0 || true) &&
 ((secondClause.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((secondClause.getJSType().isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[202]++;
              return secondClause.getJSType();

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[203]++;}

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[201]++;}
}
}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[195]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[193]++;}

      return getDeclaredTypeInAnnotation(lValue, info);
    }

    private FunctionType getFunctionType(@Nullable Var v) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[402]++;
      JSType t = v == null ? null : v.getType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[403]++;
      ObjectType o = t == null ? null : t.dereference();
      return JSType.toMaybeFunctionType(o);
    }

    /**
     * Look for calls that set a delegate method's calling convention.
     */
    private void checkForCallingConventionDefiningCalls(
        Node n, Map<String, String> delegateCallingConventions) {
      codingConvention.checkForCallingConventionDefiningCalls(n,
          delegateCallingConventions);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[404]++;
    }

    /**
     * Look for class-defining calls.
     * Because JS has no 'native' syntax for defining classes,
     * this is often very coding-convention dependent and business-logic heavy.
     */
    private void checkForClassDefiningCalls(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[405]++;
      SubclassRelationship relationship =
          codingConvention.getClassesDefinedByCall(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[406]++;
int CodeCoverConditionCoverageHelper_C99;
      if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((relationship != null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[204]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[407]++;
        FunctionType superCtor = getFunctionType(
            scope.getVar(relationship.superclassName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[408]++;
        FunctionType subCtor = getFunctionType(
            scope.getVar(relationship.subclassName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[409]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (128)) == 0 || true) &&
 ((superCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (32)) == 0 || true) &&
 ((superCtor.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((subCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((subCtor.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 4) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 4) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[206]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[410]++;
          ObjectType superClass = superCtor.getInstanceType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[411]++;
          ObjectType subClass = subCtor.getInstanceType();

          // superCtor and subCtor might be structural constructors
          // (like {function(new:Object)}) so we need to resolve them back
          // to the original ctor objects.
          superCtor = superClass.getConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[412]++;
          subCtor = subClass.getConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[413]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[414]++;
int CodeCoverConditionCoverageHelper_C101;

          if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (32)) == 0 || true) &&
 ((relationship.type == SubclassType.INHERITS) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C101 |= (8)) == 0 || true) &&
 ((superClass.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((subClass.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[208]++;
            validator.expectSuperType(t, n, superClass, subClass);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[415]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[209]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[416]++;
int CodeCoverConditionCoverageHelper_C102;

          if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((superCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((subCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[210]++;
            codingConvention.applySubclassRelationship(
                superCtor, subCtor, relationship.type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[417]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[211]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[207]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[205]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[418]++;

      String singletonGetterClassName =
          codingConvention.getSingletonGetterClassName(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[419]++;
int CodeCoverConditionCoverageHelper_C103;
      if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((singletonGetterClassName != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[212]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[420]++;
        ObjectType objectType = ObjectType.cast(
            typeRegistry.getType(singletonGetterClassName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[421]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[214]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[422]++;
          FunctionType functionType = objectType.getConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[423]++;
int CodeCoverConditionCoverageHelper_C105;

          if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((functionType != null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[216]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[424]++;
            FunctionType getterType =
                typeRegistry.createFunctionType(objectType);
            codingConvention.applySingletonGetter(functionType, getterType,
                objectType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[425]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[217]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[215]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[213]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[426]++;

      DelegateRelationship delegateRelationship =
          codingConvention.getDelegateRelationship(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[427]++;
int CodeCoverConditionCoverageHelper_C106;
      if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((delegateRelationship != null) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[218]++;
        applyDelegateRelationship(delegateRelationship);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[428]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[219]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[429]++;

      ObjectLiteralCast objectLiteralCast =
          codingConvention.getObjectLiteralCast(n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[430]++;
int CodeCoverConditionCoverageHelper_C107;
      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((objectLiteralCast != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[220]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[431]++;
int CodeCoverConditionCoverageHelper_C108;
        if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((objectLiteralCast.diagnosticType == null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[222]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[432]++;
          ObjectType type = ObjectType.cast(
              typeRegistry.getType(objectLiteralCast.typeName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[433]++;
int CodeCoverConditionCoverageHelper_C109;
          if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((type.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[224]++;
            setDeferredType(objectLiteralCast.objectNode, type);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[434]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[225]++;
            compiler.report(JSError.make(t.getSourceName(), n,
                    CONSTRUCTOR_EXPECTED));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[435]++;
          }

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[223]++;
          compiler.report(JSError.make(t.getSourceName(), n,
                  objectLiteralCast.diagnosticType));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[436]++;
        }

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[221]++;}
    }

    /**
     * Apply special properties that only apply to delegates.
     */
    private void applyDelegateRelationship(
        DelegateRelationship delegateRelationship) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[437]++;
      ObjectType delegatorObject = ObjectType.cast(
          typeRegistry.getType(delegateRelationship.delegator));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[438]++;
      ObjectType delegateBaseObject = ObjectType.cast(
          typeRegistry.getType(delegateRelationship.delegateBase));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[439]++;
      ObjectType delegateSuperObject = ObjectType.cast(
          typeRegistry.getType(codingConvention.getDelegateSuperclassName()));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[440]++;
int CodeCoverConditionCoverageHelper_C110;
      if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (32)) == 0 || true) &&
 ((delegatorObject != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((delegateBaseObject != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((delegateSuperObject != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[226]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[441]++;
        FunctionType delegatorCtor = delegatorObject.getConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[442]++;
        FunctionType delegateBaseCtor = delegateBaseObject.getConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[443]++;
        FunctionType delegateSuperCtor = delegateSuperObject.getConstructor();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[444]++;
int CodeCoverConditionCoverageHelper_C111;

        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (32)) == 0 || true) &&
 ((delegatorCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C111 |= (8)) == 0 || true) &&
 ((delegateBaseCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((delegateSuperCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[228]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[445]++;
          FunctionParamBuilder functionParamBuilder =
              new FunctionParamBuilder(typeRegistry);
          functionParamBuilder.addRequiredParams(
              getNativeType(U2U_CONSTRUCTOR_TYPE));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[446]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[447]++;
          FunctionType findDelegate = typeRegistry.createFunctionType(
              typeRegistry.createDefaultObjectUnion(delegateBaseObject),
              functionParamBuilder.build());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[448]++;

          FunctionType delegateProxy = typeRegistry.createConstructorType(
              delegateBaseObject.getReferenceName() + DELEGATE_PROXY_SUFFIX,
              null, null, null, null);
          delegateProxy.setPrototypeBasedOn(delegateBaseObject);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[449]++;

          codingConvention.applyDelegateRelationship(
              delegateSuperObject, delegateBaseObject, delegatorObject,
              delegateProxy, findDelegate);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[450]++;
          delegateProxyPrototypes.add(delegateProxy.getPrototype());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[451]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[229]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[227]++;}
    }

    /**
     * Declare the symbol for a qualified name in the global scope.
     *
     * @param info The doc info for this property.
     * @param n A top-level GETPROP node (it should not be contained inside
     *     another GETPROP).
     * @param parent The parent of {@code n}.
     * @param rhsValue The node that {@code n} is being initialized to,
     *     or {@code null} if this is a stub declaration.
     */
    void maybeDeclareQualifiedName(NodeTraversal t, JSDocInfo info,
        Node n, Node parent, Node rhsValue) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[452]++;
      Node ownerNode = n.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[453]++;
      String ownerName = ownerNode.getQualifiedName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[454]++;
      String qName = n.getQualifiedName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[455]++;
      String propName = n.getLastChild().getString();
      Preconditions.checkArgument(qName != null && ownerName != null);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[456]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[457]++;

      // Precedence of type information on GETPROPs:
      // 1) @type annotation / @enum annotation
      // 2) ASSIGN to FUNCTION literal
      // 3) @param/@return annotation (with no function literal)
      // 4) ASSIGN to something marked @const
      // 5) ASSIGN to anything else
      //
      // 1, 3, and 4 are declarations, 5 is inferred, and 2 is a declaration iff
      // the function has JsDoc or has not been declared before.
      //
      // FUNCTION literals are special because TypedScopeCreator is very smart
      // about getting as much type information as possible for them.

      // Determining type for #1 + #2 + #3 + #4
      JSType valueType = getDeclaredType(info, n, rhsValue);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[458]++;
int CodeCoverConditionCoverageHelper_C112;
      if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (8)) == 0 || true) &&
 ((valueType == null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((rhsValue != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[230]++;
        // Determining type for #5
        valueType = rhsValue.getJSType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[459]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[231]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[460]++;
int CodeCoverConditionCoverageHelper_C113;

      // Function prototypes are special.
      // It's a common JS idiom to do:
      // F.prototype = { ... };
      // So if F does not have an explicitly declared super type,
      // allow F.prototype to be redefined arbitrarily.
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 (("prototype".equals(propName)) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[232]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[461]++;
        Var qVar = scope.getVar(qName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[462]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((qVar != null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[234]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[463]++;
          // If the programmer has declared that F inherits from Super,
          // and they assign F.prototype to an object literal,
          // then they are responsible for making sure that the object literal's
          // implicit prototype is set up appropriately. We just obey
          // the @extends tag.
          ObjectType qVarType = ObjectType.cast(qVar.getType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[464]++;
int CodeCoverConditionCoverageHelper_C115;
          if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (32)) == 0 || true) &&
 ((qVarType != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((rhsValue != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((rhsValue.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[236]++;
            typeRegistry.resetImplicitPrototype(
                rhsValue.getJSType(), qVarType.getImplicitPrototype());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[465]++;

          } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[237]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[466]++;
int CodeCoverConditionCoverageHelper_C116; if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((qVar.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[238]++;
            // If the programmer has declared that F inherits from Super,
            // and they assign F.prototype to some arbitrary expression,
            // there's not much we can do. We just ignore the expression,
            // and hope they've annotated their code in a way to tell us
            // what props are going to be on that prototype.
            return;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[239]++;}
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[467]++;
int CodeCoverConditionCoverageHelper_C117;
          if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((qVar.getScope() == scope) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[240]++;
            scope.undeclare(qVar);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[468]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[241]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[235]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[233]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[469]++;
int CodeCoverConditionCoverageHelper_C118;

      if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((valueType == null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[242]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[470]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[244]++;
          stubDeclarations.add(new StubDeclaration(
              n,
              t.getInput() != null && t.getInput().isExtern(),
              ownerName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[471]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[245]++;}

        return;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[243]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[472]++;

      boolean inferred = isQualifiedNameInferred(
          qName, n, info, rhsValue, valueType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[473]++;
int CodeCoverConditionCoverageHelper_C120;
      if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((inferred) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[246]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[474]++;
        ObjectType ownerType = getObjectSlot(ownerName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[475]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[248]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[476]++;
          // Only declare this as an official property if it has not been
          // declared yet.
          boolean isExtern = t.getInput() != null && t.getInput().isExtern();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[477]++;
int CodeCoverConditionCoverageHelper_C122;
          if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && ((!
(((CodeCoverConditionCoverageHelper_C122 |= (512)) == 0 || true) &&
 ((ownerType.hasOwnProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C122 |= (128)) == 0 || true) &&
 ((ownerType.isPropertyTypeInferred(propName)) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (64)) == 0 || true)))
) && ((
(((CodeCoverConditionCoverageHelper_C122 |= (32)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C122 |= (8)) == 0 || true) &&
 ((ownerType.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((ownerType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 5) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 5) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[250]++;
            // If the property is undeclared or inferred, declare it now.
            ownerType.defineDeclaredProperty(propName, valueType, n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[478]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[251]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[249]++;}

        // If the property is already declared, the error will be
        // caught when we try to declare it in the current scope.
        defineSlot(n, parent, valueType, inferred);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[479]++;

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[247]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[480]++;
int CodeCoverConditionCoverageHelper_C123; if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (8)) == 0 || true) &&
 ((rhsValue != null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((rhsValue.isTrue()) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[252]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[481]++;
        // We declare these for delegate proxy method properties.
        FunctionType ownerType =
            JSType.toMaybeFunctionType(getObjectSlot(ownerName));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[482]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[254]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[483]++;
          JSType ownerTypeOfThis = ownerType.getTypeOfThis();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[484]++;
          String delegateName = codingConvention.getDelegateSuperclassName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[485]++;
          JSType delegateType = delegateName == null ?
              null : typeRegistry.getType(delegateName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[486]++;
int CodeCoverConditionCoverageHelper_C125;
          if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((delegateType != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((ownerTypeOfThis.isSubtype(delegateType)) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[256]++;
            defineSlot(n, parent, getNativeType(BOOLEAN_TYPE), true);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[487]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[257]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[255]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[253]++;}
}
    }

    /**
     * Determines whether a qualified name is inferred.
     * NOTE(nicksantos): Determining whether a property is declared or not
     * is really really obnoxious.
     *
     * The problem is that there are two (equally valid) coding styles:
     *
     * (function() {
     *   /* The authoritative definition of goog.bar. /
     *   goog.bar = function() {};
     * })();
     *
     * function f() {
     *   goog.bar();
     *   /* Reset goog.bar to a no-op. /
     *   goog.bar = function() {};
     * }
     *
     * In a dynamic language with first-class functions, it's very difficult
     * to know which one the user intended without looking at lots of
     * contextual information (the second example demonstrates a small case
     * of this, but there are some really pathological cases as well).
     *
     * The current algorithm checks if either the declaration has
     * JsDoc type information, or @const with a known type,
     * or a function literal with a name we haven't seen before.
     */
    private boolean isQualifiedNameInferred(
        String qName, Node n, JSDocInfo info,
        Node rhsValue, JSType valueType) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[488]++;
int CodeCoverConditionCoverageHelper_C126;
      if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((valueType == null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[258]++;
        return true;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[259]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[489]++;

      boolean inferred = true;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[490]++;
int CodeCoverConditionCoverageHelper_C127;
      if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[260]++;
        inferred = !(info.hasType()
            || info.hasEnumParameterType()
            || (isConstantSymbol(info, n) && valueType != null
                && !valueType.isUnknownType())
            || FunctionTypeBuilder.isFunctionTypeDeclaration(info));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[491]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[261]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[492]++;
int CodeCoverConditionCoverageHelper_C128;

      if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (32)) == 0 || true) &&
 ((inferred) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((rhsValue != null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((rhsValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[262]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[493]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[264]++;
          return false;

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[265]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[494]++;
int CodeCoverConditionCoverageHelper_C130; if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C130 |= (8)) == 0 || true) &&
 ((scope.isDeclared(qName, false)) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((n.isUnscopedQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[266]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[495]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[40]++;


int CodeCoverConditionCoverageHelper_C131;

          // Check if this is in a conditional block.
          // Functions assigned in conditional blocks are inferred.
          for (Node current = n.getParent();(((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C131 |= (8)) == 0 || true) &&
 ((current.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((current.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) && false);
               current = current.getParent()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[40]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[41]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[42]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[496]++;
int CodeCoverConditionCoverageHelper_C132;
            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((NodeUtil.isControlStructure(current)) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[268]++;
              return true;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[269]++;}
          }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[497]++;

          // Check if this is assigned in an inner scope.
          // Functions assigned in inner scopes are inferred.
          AstFunctionContents contents =
              getFunctionAnalysisResults(scope.getRootNode());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[498]++;
int CodeCoverConditionCoverageHelper_C133;
          if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (8)) == 0 || true) &&
 ((contents == null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((contents.getEscapedQualifiedNames().contains(qName)) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[270]++;
            return false;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[271]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[267]++;}
}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[263]++;}
      return inferred;
    }

    private boolean isConstantSymbol(JSDocInfo info, Node node) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[499]++;
int CodeCoverConditionCoverageHelper_C134;
      if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((info.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[272]++;
        return true;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[273]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[500]++;

      switch (node.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[274]++;
          return NodeUtil.isConstantByConvention(
              compiler.getCodingConvention(), node, node.getParent());
        case Token.GETPROP:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[275]++;
          return node.isQualifiedName() && NodeUtil.isConstantByConvention(
              compiler.getCodingConvention(), node.getLastChild(), node); default : CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[276]++;
      }
      return false;
    }

    /**
     * Find the ObjectType associated with the given slot.
     * @param slotName The name of the slot to find the type in.
     * @return An object type, or null if this slot does not contain an object.
     */
    private ObjectType getObjectSlot(String slotName) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[501]++;
      Var ownerVar = scope.getVar(slotName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[502]++;
int CodeCoverConditionCoverageHelper_C135;
      if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((ownerVar != null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[277]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[503]++;
        JSType ownerVarType = ownerVar.getType();
        return ObjectType.cast(ownerVarType == null ?
            null : ownerVarType.restrictByNotNullOrUndefined());

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[278]++;}
      return null;
    }

    /**
     * Resolve any stub declarations to unknown types if we could not
     * find types for them during traversal.
     */
    void resolveStubDeclarations() {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[504]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[43]++;


      for (StubDeclaration stub : stubDeclarations) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[43]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[44]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[45]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[505]++;
        Node n = stub.node;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[506]++;
        Node parent = n.getParent();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[507]++;
        String qName = n.getQualifiedName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[508]++;
        String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[509]++;
        String ownerName = stub.ownerName;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[510]++;
        boolean isExtern = stub.isExtern;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[511]++;
int CodeCoverConditionCoverageHelper_C136;

        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((scope.isDeclared(qName, false)) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[279]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[512]++;
          continue;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[280]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[513]++;

        // If we see a stub property, make sure to register this property
        // in the type registry.
        ObjectType ownerType = getObjectSlot(ownerName);
        defineSlot(n, parent, unknownType, true);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[514]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[515]++;
int CodeCoverConditionCoverageHelper_C137;

        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (32)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C137 |= (8)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((ownerType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[281]++;
          // If this is a stub for a prototype, just declare it
          // as an unknown type. These are seen often in externs.
          ownerType.defineInferredProperty(
              propName, unknownType, n);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[516]++;

        } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[282]++;
          typeRegistry.registerPropertyOnType(
              propName, ownerType == null ? unknownType : ownerType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[517]++;
        }
      }
    }

    /**
     * Collects all declared properties in a function, and
     * resolves them relative to the global scope.
     */
    private final class CollectProperties
        extends AbstractShallowStatementCallback {
      private final JSType thisType;

      CollectProperties(JSType thisType) {
        this.thisType = thisType;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[518]++;
      }

      @Override
      public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[519]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[283]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[520]++;
          Node child = n.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[521]++;
          switch (child.getType()) {
            case Token.ASSIGN:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[285]++;
              maybeCollectMember(child.getFirstChild(), child,
                  child.getLastChild());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[522]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[523]++;
              break;
            case Token.GETPROP:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[286]++;
              maybeCollectMember(child, child, null);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[524]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[525]++;
              break; default : CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[287]++;
          }

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[284]++;}
      }

      private void maybeCollectMember(Node member,
          Node nodeWithJsDocInfo, @Nullable Node value) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[526]++;
        JSDocInfo info = nodeWithJsDocInfo.getJSDocInfo();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[527]++;
int CodeCoverConditionCoverageHelper_C139;

        // Do nothing if there is no JSDoc type info, or
        // if the node is not a member expression, or
        // if the member expression is not of the form: this.someProperty.
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (32)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C139 |= (8)) == 0 || true) &&
 ((member.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((member.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[288]++;
          return;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[289]++;}

        member.getFirstChild().setJSType(thisType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[528]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[529]++;
        JSType jsType = getDeclaredType(info, member, value);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[530]++;
        Node name = member.getLastChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[531]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (128)) == 0 || true) &&
 ((jsType != null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C140 |= (32)) == 0 || true) &&
 ((name.isName()) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C140 |= (8)) == 0 || true) &&
 ((name.isString()) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((thisType.toObjectType() != null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 4) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 4) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[290]++;
          thisType.toObjectType().defineDeclaredProperty(
              name.getString(),
              jsType,
              member);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[532]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[291]++;}
      }
    } // end CollectProperties
  }

  /**
   * A stub declaration without any type information.
   */
  private static final class StubDeclaration {
    private final Node node;
    private final boolean isExtern;
    private final String ownerName;

    private StubDeclaration(Node node, boolean isExtern, String ownerName) {
      this.node = node;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[533]++;
      this.isExtern = isExtern;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[534]++;
      this.ownerName = ownerName;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[535]++;
    }
  }

  /**
   * A shallow traversal of the global scope to build up all classes,
   * functions, and methods.
   */
  private final class GlobalScopeBuilder extends AbstractScopeBuilder {

    private GlobalScopeBuilder(Scope scope) {
      super(scope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[536]++;
    }

    /**
     * Visit a node in the global scope, and add anything it declares to the
     * global symbol table.
     *
     * @param t The current traversal.
     * @param n The node being visited.
     * @param parent The parent of n
     */
    @Override public void visit(NodeTraversal t, Node n, Node parent) {
      super.visit(t, n, parent);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[537]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[538]++;

      switch (n.getType()) {

        case Token.VAR:
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[292]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[539]++;
int CodeCoverConditionCoverageHelper_C141;
          // Handle typedefs.
          if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[293]++;
            checkForTypedef(t, n.getFirstChild(), n.getJSDocInfo());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[540]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[294]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[541]++;
          break; default : CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[295]++;
      }
    }

    @Override
    void maybeDeclareQualifiedName(
        NodeTraversal t, JSDocInfo info,
        Node n, Node parent, Node rhsValue) {
      checkForTypedef(t, n, info);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[542]++;
      super.maybeDeclareQualifiedName(t, info, n, parent, rhsValue);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[543]++;
    }

    /**
     * Handle typedefs.
     * @param t The current traversal.
     * @param candidate A qualified name node.
     * @param info JSDoc comments.
     */
    private void checkForTypedef(
        NodeTraversal t, Node candidate, JSDocInfo info) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[544]++;
int CodeCoverConditionCoverageHelper_C142;
      if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((info.hasTypedefType()) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[296]++;
        return;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[297]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[545]++;

      String typedef = candidate.getQualifiedName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[546]++;
int CodeCoverConditionCoverageHelper_C143;
      if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((typedef == null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[298]++;
        return;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[299]++;}

      // TODO(nicksantos|user): This is a terrible, terrible hack
      // to bail out on recursive typedefs. We'll eventually need
      // to handle these properly.
      typeRegistry.declareType(typedef, unknownType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[547]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[548]++;

      JSType realType = info.getTypedefType().evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[549]++;
int CodeCoverConditionCoverageHelper_C144;
      if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((realType == null) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[300]++;
        compiler.report(
            JSError.make(
                t.getSourceName(), candidate, MALFORMED_TYPEDEF, typedef));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[550]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[301]++;}

      typeRegistry.overwriteDeclaredType(typedef, realType);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[551]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[552]++;
int CodeCoverConditionCoverageHelper_C145;
      if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((candidate.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[302]++;
        defineSlot(candidate, candidate.getParent(),
            getNativeType(NO_TYPE), false);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[553]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[303]++;}
    }
  } // end GlobalScopeBuilder

  /**
   * A shallow traversal of a local scope to find all arguments and
   * local variables.
   */
  private final class LocalScopeBuilder extends AbstractScopeBuilder {
    /**
     * @param scope The scope that we're building.
     */
    private LocalScopeBuilder(Scope scope) {
      super(scope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[554]++;
    }

    /**
     * Traverse the scope root and build it.
     */
    void build() {
      NodeTraversal.traverse(compiler, scope.getRootNode(), this);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[555]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[556]++;

      AstFunctionContents contents =
          getFunctionAnalysisResults(scope.getRootNode());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[557]++;
int CodeCoverConditionCoverageHelper_C146;
      if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((contents != null) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[304]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[558]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[46]++;


        for (String varName : contents.getEscapedVarNames()) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[46]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[47]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[48]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[559]++;
          Var v = scope.getVar(varName);
          Preconditions.checkState(v.getScope() == scope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[560]++;
          v.markEscaped();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[561]++;
        }
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[562]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[49]++;



        for (Multiset.Entry<String> entry :
                 contents.getAssignedNameCounts().entrySet()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[49]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[50]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[51]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[563]++;
          Var v = scope.getVar(entry.getElement());
          Preconditions.checkState(v.getScope() == scope);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[564]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[565]++;
int CodeCoverConditionCoverageHelper_C147;
          if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((entry.getCount() == 1) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[306]++;
            v.markAssignedExactlyOnce();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[566]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[307]++;}
        }

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[305]++;}
    }

    /**
     * Visit a node in a local scope, and add any local variables or catch
     * parameters into the local symbol table.
     *
     * @param t The node traversal.
     * @param n The node being visited.
     * @param parent The parent of n
     */
    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[567]++;
int CodeCoverConditionCoverageHelper_C148;
      if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((n == scope.getRootNode()) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[308]++; return;
} else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[309]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[568]++;
int CodeCoverConditionCoverageHelper_C149;

      if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (8)) == 0 || true) &&
 ((n.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((parent == scope.getRootNode()) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[310]++;
        handleFunctionInputs(parent);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[569]++;
        return;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[311]++;}

      super.visit(t, n, parent);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[570]++;
    }

    /** Handle bleeding functions and function parameters. */
    private void handleFunctionInputs(Node fnNode) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[571]++;
      // Handle bleeding functions.
      Node fnNameNode = fnNode.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[572]++;
      String fnName = fnNameNode.getString();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[573]++;
int CodeCoverConditionCoverageHelper_C150;
      if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((fnName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[312]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[574]++;
        Scope.Var fnVar = scope.getVar(fnName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[575]++;
int CodeCoverConditionCoverageHelper_C151;
        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (32)) == 0 || true) &&
 ((fnVar == null) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C151 |= (8)) == 0 || true) &&
 ((fnVar.getNameNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((// Make sure that the function is actually bleeding by checking
                // if has already been declared.
                fnVar.getInitialValue() != fnNode) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[314]++;
          defineSlot(fnNameNode, fnNode, fnNode.getJSType(), false);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[576]++;

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[315]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[313]++;}

      declareArguments(fnNode);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[577]++;
    }

    /**
     * Declares all of a function's arguments.
     */
    private void declareArguments(Node functionNode) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[578]++;
      Node astParameters = functionNode.getFirstChild().getNext();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[579]++;
      Node iifeArgumentNode = null;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[580]++;
int CodeCoverConditionCoverageHelper_C152;

      if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((NodeUtil.isCallOrNewTarget(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[316]++;
        iifeArgumentNode = functionNode.getNext();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[581]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[317]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[582]++;

      FunctionType functionType =
          JSType.toMaybeFunctionType(functionNode.getJSType());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[583]++;
int CodeCoverConditionCoverageHelper_C153;
      if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((functionType != null) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[318]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[584]++;
        Node jsDocParameters = functionType.getParametersNode();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[585]++;
int CodeCoverConditionCoverageHelper_C154;
        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((jsDocParameters != null) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[320]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[586]++;
          Node jsDocParameter = jsDocParameters.getFirstChild();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[587]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[52]++;


          for (Node astParameter : astParameters.children()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[52]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[53]--;
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.loops[54]++;
}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[588]++;
            JSType paramType = jsDocParameter == null ?
                unknownType : jsDocParameter.getJSType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[589]++;
            boolean inferred = paramType == null || paramType == unknownType;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[590]++;
int CodeCoverConditionCoverageHelper_C155;

            if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (8)) == 0 || true) &&
 ((iifeArgumentNode != null) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((inferred) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[322]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[591]++;
              String argumentName = iifeArgumentNode.getQualifiedName();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[592]++;
              Var argumentVar =
                  argumentName == null || scope.getParent() == null
                  ? null : scope.getParent().getVar(argumentName);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[593]++;
int CodeCoverConditionCoverageHelper_C156;
              if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (8)) == 0 || true) &&
 ((argumentVar != null) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((argumentVar.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[324]++;
                paramType = argumentVar.getType();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[594]++;

              } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[325]++;}

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[323]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[595]++;
int CodeCoverConditionCoverageHelper_C157;

            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((paramType == null) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[326]++;
              paramType = unknownType;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[596]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[327]++;}

            defineSlot(astParameter, functionNode, paramType, inferred);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[597]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[598]++;
int CodeCoverConditionCoverageHelper_C158;

            if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((jsDocParameter != null) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[328]++;
              jsDocParameter = jsDocParameter.getNext();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[599]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[329]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[600]++;
int CodeCoverConditionCoverageHelper_C159;
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((iifeArgumentNode != null) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[330]++;
              iifeArgumentNode = iifeArgumentNode.getNext();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[601]++;

            } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[331]++;}
          }

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[321]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[319]++;}
    } // end declareArguments
  } // end LocalScopeBuilder

  /**
   * Does a first-order function analysis that just looks at simple things
   * like what variables are escaped, and whether 'this' is used.
   */
  private static class FirstOrderFunctionAnalyzer
      extends AbstractScopedCallback implements CompilerPass {
    private final AbstractCompiler compiler;
    private final Map<Node, AstFunctionContents> data;

    FirstOrderFunctionAnalyzer(
        AbstractCompiler compiler, Map<Node, AstFunctionContents> outParam) {
      this.compiler = compiler;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[602]++;
      this.data = outParam;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[603]++;
    }

    @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[604]++;
int CodeCoverConditionCoverageHelper_C160;
      if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((externs == null) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[332]++;
        NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[605]++;

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[333]++;
        NodeTraversal.traverseRoots(
            compiler, ImmutableList.of(externs, root), this);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[606]++;
      }
    }

    @Override public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[607]++;
int CodeCoverConditionCoverageHelper_C161;
      if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[334]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[608]++;
        Node n = t.getScopeRoot();
        data.put(n, new AstFunctionContents(n));
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[609]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[335]++;}
    }

    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[610]++;
int CodeCoverConditionCoverageHelper_C162;
      if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[336]++;
        return;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[337]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[611]++;
int CodeCoverConditionCoverageHelper_C163;

      if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (8)) == 0 || true) &&
 ((n.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((n.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[338]++;
        data.get(t.getScopeRoot()).recordNonEmptyReturn();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[612]++;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[339]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[613]++;
int CodeCoverConditionCoverageHelper_C164;

      if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((t.getScopeDepth() <= 1) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[340]++;
        // The first-order function analyzer looks at two types of variables:
        //
        // 1) Local variables that are assigned in inner scopes ("escaped vars")
        //
        // 2) Local variables that are assigned more than once.
        //
        // We treat all global variables as escaped by default, so there's
        // no reason to do this extra computation for them.
        return;

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[341]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[614]++;
int CodeCoverConditionCoverageHelper_C165;

      if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (32)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C165 |= (8)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((NodeUtil.isBleedingFunctionName(n)) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[342]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[615]++;
        String name = n.getString();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[616]++;
        Scope scope = t.getScope();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[617]++;
        Var var = scope.getVar(name);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[618]++;
int CodeCoverConditionCoverageHelper_C166;
        if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[344]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[619]++;
          Scope ownerScope = var.getScope();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[620]++;
int CodeCoverConditionCoverageHelper_C167;
          if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((ownerScope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[346]++;
            data.get(ownerScope.getRootNode()).recordAssignedName(name);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[621]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[347]++;}
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[622]++;
int CodeCoverConditionCoverageHelper_C168;

          if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (8)) == 0 || true) &&
 ((scope != ownerScope) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((ownerScope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[348]++;
            data.get(ownerScope.getRootNode()).recordEscapedVarName(name);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[623]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[349]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[345]++;}

      } else {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[343]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[624]++;
int CodeCoverConditionCoverageHelper_C169; if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (32)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C169 |= (8)) == 0 || true) &&
 ((n.isUnscopedQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 3) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 3) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[350]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[625]++;
        String name = NodeUtil.getRootOfQualifiedName(n).getString();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[626]++;
        Scope scope = t.getScope();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[627]++;
        Var var = scope.getVar(name);
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[628]++;
int CodeCoverConditionCoverageHelper_C170;
        if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[352]++;
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[629]++;
          Scope ownerScope = var.getScope();
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[630]++;
int CodeCoverConditionCoverageHelper_C171;
          if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (8)) == 0 || true) &&
 ((scope != ownerScope) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((ownerScope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 2) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 2) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[354]++;
            data.get(ownerScope.getRootNode())
                .recordEscapedQualifiedName(n.getQualifiedName());
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[631]++;

          } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[355]++;}

        } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[353]++;}

      } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[351]++;}
}
    }
  }

  private AstFunctionContents getFunctionAnalysisResults(@Nullable Node n) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.statements[632]++;
int CodeCoverConditionCoverageHelper_C172;
    if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[356]++;
      return null;

    } else {
  CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl.branches[357]++;}

    // Sometimes this will return null in things like
    // NameReferenceGraphConstruction that build partial scopes.
    return functionAnalysisResults.get(n);
  }
}

class CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl ());
  }
    public static long[] statements = new long[633];
    public static long[] branches = new long[358];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[173];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TypedScopeCreator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,2,2,3,2,1,2,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,1,2,3,3,3,2,1,2,1,1,1,1,1,2,3,2,1,2,2,2,2,1,2,1,1,1,2,1,3,1,1,1,1,1,2,3,1,1,3,2,2,1,1,2,3,1,1,2,2,1,1,2,2,1,3,1,3,3,2,1,1,1,1,1,1,2,3,3,2,1,1,3,1,1,1,1,1,1,3,2,1,2,1,1,3,1,2,2,1,2,2,1,1,3,1,3,3,1,2,1,1,1,1,1,1,2,1,3,1,1,1,2,2,1,1,1,1,1,1,2,1,3,1,1,2,3,1,2,1};
    for (int i = 1; i <= 172; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[55];

  public CodeCoverCoverageCounter$duzboky6qvflrns0wxe9mm622ygk9puijl () {
    super("com.google.javascript.jscomp.TypedScopeCreator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 632; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 357; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 172; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 54; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TypedScopeCreator.java");
      for (int i = 1; i <= 632; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 357; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 172; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 18; i++) {
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

