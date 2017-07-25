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

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticScope;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CodingConvention defines a set of hooks to customize the behavior of the
 * Compiler for a specific team/company.
 *
 */
public interface CodingConvention extends Serializable {

  /**
   * This checks whether a given variable name, such as a name in all-caps
   * should be treated as if it had the @const annotation.
   *
   * @param variableName potentially constant variable name
   * @return {@code true} if the name should be treated as a constant.
   */
  public boolean isConstant(String variableName);

  /**
   * This checks whether a given key of an object literal, such as a
   * name in all-caps should be treated as if it had the @const
   * annotation.
   */
  public boolean isConstantKey(String keyName);

  /**
   * This checks that a given {@code key} may be used as a key for an enum.
   *
   * @param key the potential key to an enum
   * @return {@code true} if the {@code key} may be used as an enum key,
   *     {@code false} otherwise
   */
  public boolean isValidEnumKey(String key);

  /**
   * This checks whether a given parameter name should be treated as an
   * optional parameter as far as type checking or function call arg count
   * checking is concerned. Note that an optional function parameter may be
   * declared as a simple type and is automatically converted to a union of the
   * declared type and Undefined.
   *
   * @param parameter The parameter's node.
   * @return {@code true} if the parameter should be treated as an optional
   * parameter.
   */
  public boolean isOptionalParameter(Node parameter);

  /**
   * This checks whether a given parameter should be treated as a marker
   * for a variable argument list function. A VarArgs parameter must be the
   * last parameter in a function declaration.
   *
   * @param parameter The parameter's node.
   * @return {@code true} if the parameter should be treated as a variable
   * length parameter.
   */
  public boolean isVarArgsParameter(Node parameter);

  /**
   * Checks whether a global variable or function name should be treated as
   * exported, or externally referenceable.
   *
   * @param name A global variable or function name.
   * @param local {@code true} if the name is a local variable.
   * @return {@code true} if the name should be considered exported.
   */
  public boolean isExported(String name, boolean local);

  /**
   * Should be isExported(name, true) || isExported(name, false);
   */
  public boolean isExported(String name);

  /**
   * Checks whether a name should be considered private. Private global
   * variables and functions can only be referenced within the source file in
   * which they are declared. Private properties and methods should only be
   * accessed by the class that defines them.
   *
   * @param name The name of a global variable or function, or a method or
   *     property.
   * @return {@code true} if the name should be considered private.
   */
  public boolean isPrivate(String name);

  /**
   * Checks if the given method defines a subclass relationship,
   * and if it does, returns information on that relationship. By default,
   * always returns null. Meant to be overridden by subclasses.
   *
   * @param callNode A CALL node.
   */
  public SubclassRelationship getClassesDefinedByCall(Node callNode);

  /**
   * Returns true if passed a string referring to the superclass.  The string
   * will usually be from the string node at the right of a GETPROP, e.g.
   * this.superClass_.
   */
  public boolean isSuperClassReference(String propertyName);

  /**
   * Convenience method for determining provided dependencies amongst different
   * JS scripts.
   */
  public String extractClassNameIfProvide(Node node, Node parent);

  /**
   * Convenience method for determining required dependencies amongst different
   * JS scripts.
   */
  public String extractClassNameIfRequire(Node node, Node parent);

  /**
   * Function name used when exporting properties.
   * Signature: fn(object, publicName, symbol).
   * @return function name.
   */
  public String getExportPropertyFunction();

  /**
   * Function name used when exporting symbols.
   * Signature: fn(publicPath, object).
   * @return function name.
   */
  public String getExportSymbolFunction();

  /**
   * Checks if the given CALL node is forward-declaring any types,
   * and returns the name of the types if it is.
   */
  public List<String> identifyTypeDeclarationCall(Node n);

  /**
   * In many JS libraries, the function that produces inheritance also
   * adds properties to the superclass and/or subclass.
   */
  public void applySubclassRelationship(FunctionType parentCtor,
      FunctionType childCtor, SubclassType type);

  /**
   * Function name for abstract methods. An abstract method can be assigned to
   * an interface method instead of an function expression in order to avoid
   * linter warnings produced by assigning a function without a return value
   * where a return value is expected.
   * @return function name.
   */
  public String getAbstractMethodName();

  /**
   * Checks if the given method defines a singleton getter, and if it does,
   * returns the name of the class with the singleton getter. By default, always
   * returns null. Meant to be overridden by subclasses.
   *
   * addSingletonGetter needs a coding convention because in the general case,
   * it can't be inlined. The function inliner sees that it creates an alias
   * to the given class in an inner closure, and bails out.
   *
   * @param callNode A CALL node.
   */
  public String getSingletonGetterClassName(Node callNode);

  /**
   * In many JS libraries, the function that adds a singleton getter to a class
   * adds properties to the class.
   */
  public void applySingletonGetter(FunctionType functionType,
      FunctionType getterType, ObjectType objectType);

  /**
   * @return Whether the function is inlinable by convention.
   */
  public boolean isInlinableFunction(Node n);

  /**
   * @return the delegate relationship created by the call or null.
   */
  public DelegateRelationship getDelegateRelationship(Node callNode);

  /**
   * In many JS libraries, the function that creates a delegate relationship
   * also adds properties to the delegator and delegate base.
   */
  public void applyDelegateRelationship(
      ObjectType delegateSuperclass, ObjectType delegateBase,
      ObjectType delegator, FunctionType delegateProxy,
      FunctionType findDelegate);

  /**
   * @return the name of the delegate superclass.
   */
  public String getDelegateSuperclassName();

  /**
   * Checks for function calls that set the calling conventions on delegate
   * methods.
   */
  public void checkForCallingConventionDefiningCalls(
      Node n, Map<String, String> delegateCallingConventions);

  /**
   * Defines the delegate proxy prototype properties. Their types depend on
   * properties of the delegate base methods.
   *
   * @param delegateProxyPrototypes List of delegate proxy prototypes.
   */
  public void defineDelegateProxyPrototypeProperties(
      JSTypeRegistry registry, StaticScope<JSType> scope,
      List<ObjectType> delegateProxyPrototypes,
      Map<String, String> delegateCallingConventions);

  /**
   * Gets the name of the global object.
   */
  public String getGlobalObject();

  /**
   * A Bind instance or null.
   */
  public Bind describeFunctionBind(Node n);

  /**
   * A Bind instance or null.
   * @param useTypeInfo If we believe type information is reliable enough
   *     to use to figure out what the bind function is.
   */
  public Bind describeFunctionBind(Node n, boolean useTypeInfo);

  public static class Bind {
    // The target of the bind action
    final Node target;
    // The node representing the "this" value, maybe null
    final Node thisValue;
    // The head of a Node list representing the parameters
    final Node parameters;

    public Bind(Node target, Node thisValue, Node parameters) {
      this.target = target;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[1]++;
      this.thisValue = thisValue;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[2]++;
      this.parameters = parameters;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[3]++;
    }

    /**
     * The number of parameters bound (not including the 'this' value).
     */
    int getBoundParameterCount() {
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.branches[1]++;
        return 0;

      } else {
  CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.branches[2]++;}
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[5]++;
      Node paramParent = parameters.getParent();
      return paramParent.getChildCount() -
          paramParent.getIndexOfChild(parameters);
    }
  }

  /**
   * Whether this CALL function is testing for the existence of a property.
   */
  public boolean isPropertyTestFunction(Node call);

  /**
   * Whether this GETPROP node is an alias for an object prototype.
   */
  public boolean isPrototypeAlias(Node getProp);

  /**
   * Checks if the given method performs a object literal cast, and if it does,
   * returns information on the cast. By default, always returns null. Meant
   * to be overridden by subclasses.
   *
   * @param callNode A CALL node.
   */
  public ObjectLiteralCast getObjectLiteralCast(Node callNode);

  /**
   * Gets a collection of all properties that are defined indirectly on global
   * objects. (For example, Closure defines superClass_ in the goog.inherits
   * call).
   */
  public Collection<String> getIndirectlyDeclaredProperties();

  /**
   * Returns the set of AssertionFunction.
   */
  public Collection<AssertionFunctionSpec> getAssertionFunctions();

  static enum SubclassType {
    INHERITS,
    MIXIN
  }

  static class SubclassRelationship {
    final SubclassType type;
    final String subclassName;
    final String superclassName;

    public SubclassRelationship(SubclassType type,
        Node subclassNode, Node superclassNode) {
      this.type = type;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[6]++;
      this.subclassName = subclassNode.getQualifiedName();
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[7]++;
      this.superclassName = superclassNode.getQualifiedName();
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[8]++;
    }
  }

  /**
   * Delegates provides a mechanism and structure for identifying where classes
   * can call out to optional code to augment their functionality. The optional
   * code is isolated from the base code through the use of a subclass in the
   * optional code derived from the delegate class in the base code.
   */
  static class DelegateRelationship {
    /** The subclass in the base code. */
    final String delegateBase;

    /** The class in the base code. */
    final String delegator;

    DelegateRelationship(String delegateBase, String delegator) {
      this.delegateBase = delegateBase;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[9]++;
      this.delegator = delegator;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[10]++;
    }
  }

  /**
   * An object literal cast provides a mechanism to cast object literals to
   * other types without a warning.
   */
  static class ObjectLiteralCast {
    /** Type to cast to. */
    final String typeName;

    /** Object to cast. */
    final Node objectNode;

    /** Error message */
    final DiagnosticType diagnosticType;

    ObjectLiteralCast(String typeName, Node objectNode,
        DiagnosticType diagnosticType) {
      this.typeName = typeName;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[11]++;
      this.objectNode = objectNode;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[12]++;
      this.diagnosticType = diagnosticType;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[13]++;
    }
  }

  /**
   * A function that will throw an exception when either:
   *   -One or more of its parameters evaluate to false.
   *   -One or more of its parameters are not of a certain type.
   */
  public class AssertionFunctionSpec {
    protected final String functionName;
    protected final JSTypeNative assertedType;

    public AssertionFunctionSpec(String functionName) {
      this(functionName, null);
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[14]++;
    }

    public AssertionFunctionSpec(String functionName,
        JSTypeNative assertedType) {
      this.functionName = functionName;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[15]++;
      this.assertedType = assertedType;
CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp.statements[16]++;
    }

    /** Returns the name of the function. */
    public String getFunctionName() {
      return functionName;
    }

    /**
     * Returns the parameter of the assertion function that is being checked.
     * @param firstParam The first parameter of the function call.
     */
    public Node getAssertedParam(Node firstParam) {
      return firstParam;
    }

    /**
     * Returns the type for a type assertion, or null if the function asserts
     * that the node must not be null or undefined.
     * @param call The asserting call
     */
    public JSType getAssertedType(Node call, JSTypeRegistry registry) {
      return assertedType != null ? registry.getNativeType(assertedType) : null;
    }
  }
}

class CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CodingConvention.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$1k0k1lcujhi8ii20fuvxbg19vvpjlawyp () {
    super("com.google.javascript.jscomp.CodingConvention.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CodingConvention.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

