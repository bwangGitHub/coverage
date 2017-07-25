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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticScope;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Helper classes for dealing with coding conventions.
 */
public class CodingConventions {
  static {
    CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.ping();
  }


  private CodingConventions() {}

  /** Gets the default coding convention. */
  public static CodingConvention getDefault() {
    return new DefaultCodingConvention();
  }

  /**
   * A convention that wraps another.
   *
   * When you want to support a new library, you should subclass this
   * delegate, and override the methods that you want to customize.
   *
   * This way, a person using jQuery and Closure Library can create a new
   * coding convention by creating a jQueryCodingConvention that delegates
   * to a ClosureCodingConvention that delegates to a DefaultCodingConvention.
   */
  public static class Proxy implements CodingConvention {

    protected final CodingConvention nextConvention;

    protected Proxy(CodingConvention convention) {
      this.nextConvention = convention;
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[1]++;
    }

    @Override
    public boolean isConstant(String variableName) {
      return nextConvention.isConstant(variableName);
    }

    @Override public boolean isConstantKey(String keyName) {
      return nextConvention.isConstantKey(keyName);
    }

    @Override
    public boolean isValidEnumKey(String key) {
      return nextConvention.isValidEnumKey(key);
    }

    @Override
    public boolean isOptionalParameter(Node parameter) {
      return nextConvention.isOptionalParameter(parameter);
    }

    @Override
    public boolean isVarArgsParameter(Node parameter) {
      return nextConvention.isVarArgsParameter(parameter);
    }

    @Override
    public boolean isExported(String name, boolean local) {
      return nextConvention.isExported(name, local);
    }


    @Override
    public final boolean isExported(String name) {
      return isExported(name, false) || isExported(name, true);
    }

    @Override
    public boolean isPrivate(String name) {
      return nextConvention.isPrivate(name);
    }

    @Override
    public SubclassRelationship getClassesDefinedByCall(Node callNode) {
      return nextConvention.getClassesDefinedByCall(callNode);
    }

    @Override
    public boolean isSuperClassReference(String propertyName) {
      return nextConvention.isSuperClassReference(propertyName);
    }

    @Override
    public String extractClassNameIfProvide(Node node, Node parent) {
      return nextConvention.extractClassNameIfProvide(node, parent);
    }

    @Override
    public String extractClassNameIfRequire(Node node, Node parent) {
      return nextConvention.extractClassNameIfRequire(node, parent);
    }

    @Override
    public String getExportPropertyFunction() {
      return nextConvention.getExportPropertyFunction();
    }

    @Override
    public String getExportSymbolFunction() {
      return nextConvention.getExportSymbolFunction();
    }

    @Override
    public List<String> identifyTypeDeclarationCall(Node n) {
      return nextConvention.identifyTypeDeclarationCall(n);
    }

    @Override
    public void applySubclassRelationship(FunctionType parentCtor,
        FunctionType childCtor, SubclassType type) {
      nextConvention.applySubclassRelationship(
          parentCtor, childCtor, type);
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[2]++;
    }

    @Override
    public String getAbstractMethodName() {
      return nextConvention.getAbstractMethodName();
    }

    @Override
    public String getSingletonGetterClassName(Node callNode) {
      return nextConvention.getSingletonGetterClassName(callNode);
    }

    @Override
    public void applySingletonGetter(FunctionType functionType,
        FunctionType getterType, ObjectType objectType) {
      nextConvention.applySingletonGetter(
          functionType, getterType, objectType);
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[3]++;
    }

    @Override
    public boolean isInlinableFunction(Node n) {
      return nextConvention.isInlinableFunction(n);
    }

    @Override
    public DelegateRelationship getDelegateRelationship(Node callNode) {
      return nextConvention.getDelegateRelationship(callNode);
    }

    @Override
    public void applyDelegateRelationship(
        ObjectType delegateSuperclass, ObjectType delegateBase,
        ObjectType delegator, FunctionType delegateProxy,
        FunctionType findDelegate) {
      nextConvention.applyDelegateRelationship(
          delegateSuperclass, delegateBase, delegator,
          delegateProxy, findDelegate);
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[4]++;
    }

    @Override
    public String getDelegateSuperclassName() {
      return nextConvention.getDelegateSuperclassName();
    }

    @Override
    public void checkForCallingConventionDefiningCalls(
        Node n, Map<String, String> delegateCallingConventions) {
      nextConvention.checkForCallingConventionDefiningCalls(
          n, delegateCallingConventions);
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[5]++;
    }

    @Override
    public void defineDelegateProxyPrototypeProperties(
        JSTypeRegistry registry, StaticScope<JSType> scope,
        List<ObjectType> delegateProxyPrototypes,
        Map<String, String> delegateCallingConventions) {
      nextConvention.defineDelegateProxyPrototypeProperties(
          registry, scope, delegateProxyPrototypes, delegateCallingConventions);
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[6]++;
    }

    @Override
    public String getGlobalObject() {
      return nextConvention.getGlobalObject();
    }

    @Override
    public Collection<AssertionFunctionSpec> getAssertionFunctions() {
      return nextConvention.getAssertionFunctions();
    }

    @Override
    public Bind describeFunctionBind(Node n) {
      return describeFunctionBind(n, false);
    }

    @Override
    public Bind describeFunctionBind(Node n, boolean useTypeInfo) {
      return nextConvention.describeFunctionBind(n, useTypeInfo);
    }

    @Override
    public boolean isPropertyTestFunction(Node call) {
      return nextConvention.isPropertyTestFunction(call);
    }

    @Override
    public boolean isPrototypeAlias(Node getProp) {
      return false;
    }

    @Override
    public ObjectLiteralCast getObjectLiteralCast(Node callNode) {
      return nextConvention.getObjectLiteralCast(callNode);
    }

    @Override
    public Collection<String> getIndirectlyDeclaredProperties() {
      return nextConvention.getIndirectlyDeclaredProperties();
    }
  }


  /**
   * The default coding convention.
   * Should be at the bottom of all proxy chains.
   */
  private static class DefaultCodingConvention implements CodingConvention {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isConstant(String variableName) {
      return false;
    }

    @Override
    public boolean isConstantKey(String variableName) {
      return false;
    }

    @Override
    public boolean isValidEnumKey(String key) {
      return key != null && key.length() > 0;
    }

    @Override
    public boolean isOptionalParameter(Node parameter) {
      // be as lax as possible, but this must be mutually exclusive from
      // var_args parameters.
      return false;
    }

    @Override
    public boolean isVarArgsParameter(Node parameter) {
      // be as lax as possible
      return false;
    }

    @Override
    public boolean isExported(String name, boolean local) {
      return local && name.startsWith("$super");
    }

    @Override
    public boolean isExported(String name) {
      return isExported(name, false) || isExported(name, true);
    }

    @Override
    public boolean isPrivate(String name) {
      return false;
    }

    @Override
    public SubclassRelationship getClassesDefinedByCall(Node callNode) {
      return null;
    }

    @Override
    public boolean isSuperClassReference(String propertyName) {
      return false;
    }

    @Override
    public String extractClassNameIfProvide(Node node, Node parent) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[7]++;
      String message = "only implemented in GoogleCodingConvention";
      throw new UnsupportedOperationException(message);
    }

    @Override
    public String extractClassNameIfRequire(Node node, Node parent) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[8]++;
      String message = "only implemented in GoogleCodingConvention";
      throw new UnsupportedOperationException(message);
    }

    @Override
    public String getExportPropertyFunction() {
      return null;
    }

    @Override
    public String getExportSymbolFunction() {
      return null;
    }

    @Override
    public List<String> identifyTypeDeclarationCall(Node n) {
      return null;
    }

    @Override
    public void applySubclassRelationship(FunctionType parentCtor,
        FunctionType childCtor, SubclassType type) {
      // do nothing
    }

    @Override
    public String getAbstractMethodName() {
      return null;
    }

    @Override
    public String getSingletonGetterClassName(Node callNode) {
      return null;
    }

    @Override
    public void applySingletonGetter(FunctionType functionType,
        FunctionType getterType, ObjectType objectType) {
      // do nothing.
    }

    @Override
    public boolean isInlinableFunction(Node n) {
      Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[9]++;
      return true;
    }

    @Override
    public DelegateRelationship getDelegateRelationship(Node callNode) {
      return null;
    }

    @Override
    public void applyDelegateRelationship(
        ObjectType delegateSuperclass, ObjectType delegateBase,
        ObjectType delegator, FunctionType delegateProxy,
        FunctionType findDelegate) {
      // do nothing.
    }

    @Override
    public String getDelegateSuperclassName() {
      return null;
    }

    @Override
    public void checkForCallingConventionDefiningCalls(Node n,
        Map<String, String> delegateCallingConventions) {
      // do nothing.
    }

    @Override
    public void defineDelegateProxyPrototypeProperties(
        JSTypeRegistry registry, StaticScope<JSType> scope,
        List<ObjectType> delegateProxyPrototypes,
        Map<String, String> delegateCallingConventions) {
      // do nothing.
    }

    @Override
    public String getGlobalObject() {
      return "window";
    }

    @Override
    public boolean isPropertyTestFunction(Node call) {
      return false;
    }

    @Override
    public boolean isPrototypeAlias(Node getProp) {
      return false;
    }

    @Override
    public ObjectLiteralCast getObjectLiteralCast(Node callNode) {
      return null;
    }

    @Override
    public Collection<AssertionFunctionSpec> getAssertionFunctions() {
      return Collections.emptySet();
    }

    @Override
    public Bind describeFunctionBind(Node n) {
      return describeFunctionBind(n, false);
    }

    @Override
    public Bind describeFunctionBind(Node n, boolean useTypeInfo) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[1]++;
        return null;

      } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[2]++;}
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[11]++;

      Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[12]++;
      String name = callTarget.getQualifiedName();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[3]++;
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((name.equals("Function.prototype.bind.call")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[5]++;
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[15]++;
          // goog.bind(fn, self, args...);
          Node fn = callTarget.getNext();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((fn == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[7]++;
            return null;

          } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[8]++;}
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[17]++;
          Node thisValue = safeNext(fn);
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[18]++;
          Node parameters = safeNext(thisValue);
          return new Bind(fn, thisValue, parameters);

        } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[6]++;}

      } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[4]++;}
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((callTarget.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((callTarget.getLastChild().getString().equals("bind")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[9]++;
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[20]++;
        Node maybeFn = callTarget.getFirstChild();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[21]++;
        JSType maybeFnType = maybeFn.getJSType();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[22]++;
        FunctionType fnType = null;
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((useTypeInfo) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((maybeFnType != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[11]++;
          fnType = maybeFnType.restrictByNotNullOrUndefined()
              .toMaybeFunctionType();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[24]++;

        } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[12]++;}
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((fnType != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((maybeFn.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[13]++;
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[26]++;
          // (function(){}).bind(self, args...);
          Node thisValue = callTarget.getNext();
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[27]++;
          Node parameters = safeNext(thisValue);
          return new Bind(maybeFn, thisValue, parameters);

        } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[14]++;}

      } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[10]++;}

      return null;
    }

    @Override
    public Collection<String> getIndirectlyDeclaredProperties() {
      return ImmutableList.of();
    }

    private Node safeNext(Node n) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[15]++;
        return n.getNext();

      } else {
  CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash.branches[16]++;}
      return null;
    }
  }
}

class CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CodingConventions.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,2,2,1};
    for (int i = 1; i <= 8; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$b2byjbvd6khojkeb4rn0xe12bgahi1jash () {
    super("com.google.javascript.jscomp.CodingConventions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CodingConventions.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

