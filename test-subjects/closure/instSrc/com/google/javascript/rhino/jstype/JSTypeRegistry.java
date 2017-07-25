/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Bob Jervis
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino.jstype;

import static com.google.javascript.rhino.jstype.JSTypeNative.ALL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.ARRAY_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.ScriptRuntime;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.RecordTypeBuilder.RecordProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type registry is used to resolve named types.
 *
 * <p>This class is not thread-safe.
 *
 */
public class JSTypeRegistry implements Serializable {
  static {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[1]++;
  }

  /**
   * The template variable corresponding to the property key type of the built-
   * in Javascript object.
   */
  public static final String OBJECT_INDEX_TEMPLATE = "Object#Key";
  static {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[2]++;
  }

  /**
   * The template variable corresponding to the property value type for
   * Javascript Objects and Arrays.
   */
  public static final String OBJECT_ELEMENT_TEMPLATE = "Object#Element";
  static {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[3]++;
  }

  /**
   * The UnionTypeBuilder caps the maximum number of alternate types it
   * remembers and then defaults to "?" (unknown type). By default this max
   * is 20, but it's very easy for the same property to appear on more than 20
   * types. Use larger unions for property checking. 3000 was picked
   * semi-randomly for use by the Google+ FE project.
   */
  private static final int PROPERTY_CHECKING_UNION_SIZE = 3000;
  static {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[4]++;
  }

  // TODO(user): An instance of this class should be used during
  // compilation. We also want to make all types' constructors package private
  // and force usage of this registry instead. This will allow us to evolve the
  // types without being tied by an open API.

  private final transient ErrorReporter reporter;

  // We use an Array instead of an immutable list because this lookup needs
  // to be very fast. When it was an immutable list, we were spending 5% of
  // CPU time on bounds checking inside get().
  private final JSType[] nativeTypes;

  private final Map<String, JSType> namesToTypes;

  // Set of namespaces in which types (or other namespaces) exist.
  private final Set<String> namespaces = new HashSet<String>();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[5]++;
  }

  // NOTE(nicksantos): This is a terrible terrible hack. When type expressions
  // are evaluated, we need to be able to decide whether that type name
  // resolves to a nullable type or a non-nullable type. Object types are
  // nullable, but enum types are not.
  //
  // Notice that it's not good enough to just declare enum types sooner.
  // For example, if we have
  // /** @enum {MyObject} */ var MyEnum = ...;
  // we won't be to declare "MyEnum" without evaluating the expression
  // {MyObject}, and following those dependencies starts to lead us into
  // undecidable territory. Instead, we "pre-declare" enum types and typedefs,
  // so that the expression resolver can decide whether a given name is
  // nullable or not.
  private final Set<String> nonNullableTypeNames = new HashSet<String>();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[6]++;
  }

  // Types that have been "forward-declared."
  // If these types are not declared anywhere in the binary, we shouldn't
  // try to type-check them at all.
  private final Set<String> forwardDeclaredTypes = new HashSet<String>();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[7]++;
  }

  // A map of properties to the types on which those properties have been
  // declared.
  private final Map<String, UnionTypeBuilder> typesIndexedByProperty =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[8]++;
  }

  // A map of properties to each reference type on which those
  // properties have been declared. Each type has a unique name used
  // for de-duping.
  private final Map<String, Map<String, ObjectType>>
      eachRefTypeIndexedByProperty = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[9]++;
  }

  // A map of properties to the greatest subtype on which those properties have
  // been declared. This is filled lazily from the types declared in
  // typesIndexedByProperty.
  private final Map<String, JSType> greatestSubtypeByProperty =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[10]++;
  }

  // A map from interface name to types that implement it.
  private final Multimap<String, FunctionType> interfaceToImplementors =
      LinkedHashMultimap.create();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[11]++;
  }

  // All the unresolved named types.
  private final Multimap<StaticScope<JSType>, NamedType> unresolvedNamedTypes =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[12]++;
  }

  // All the resolved named types.
  private final Multimap<StaticScope<JSType>, NamedType> resolvedNamedTypes =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[13]++;
  }

  // NamedType warns about unresolved types in the last generation.
  private boolean lastGeneration = true;
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[14]++;
  }

  // The template type name.
  private Map<String, TemplateType> templateTypes = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[15]++;
  }

  // A single empty TemplateTypeMap, which can be safely reused in cases where
  // there are no template types.
  private final TemplateTypeMap emptyTemplateTypeMap;

  private final boolean tolerateUndefinedValues;

  /**
   * The type registry has three modes, which control how type ASTs are
   * converted to types in {@link #createFromTypeNodes}.
   */
  public static enum ResolveMode {
    /**
     * Expressions are converted into Unknown blobs that can be
     * resolved into complex types.
     */
    LAZY_EXPRESSIONS,

    /**
     * Expressions are evaluated. If any names in the expression point to
     * unknown types, then we create a proxy {@code NamedType} structure
     * until the type can be resolved.
     *
     * This is the legacy way of resolving ways, and may not exist in the
     * future.
     */
    LAZY_NAMES,

    /**
     * Expressions and type names are evaluated aggressively. A warning
     * will be emitted if a type name fails to resolve to a real type.
     */
    IMMEDIATE
  }

  private ResolveMode resolveMode = ResolveMode.LAZY_NAMES;
  {
    CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[16]++;
  }

  /**
   * Constructs a new type registry populated with the built-in types.
   */
  public JSTypeRegistry(ErrorReporter reporter) {
    this(reporter, false);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[17]++;
  }

  /**
   * Constructs a new type registry populated with the built-in types.
   */
  public JSTypeRegistry(
      ErrorReporter reporter, boolean tolerateUndefinedValues) {
    this.reporter = reporter;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[18]++;
    this.emptyTemplateTypeMap = new TemplateTypeMap(
        this, ImmutableList.<String>of(), ImmutableList.<JSType>of());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[19]++;
    nativeTypes = new JSType[JSTypeNative.values().length];
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[20]++;
    namesToTypes = new HashMap<String, JSType>();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[21]++;
    resetForTypeCheck();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[22]++;
    this.tolerateUndefinedValues = tolerateUndefinedValues;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[23]++;
  }

  /**
   * Set the current resolving mode of the type registry.
   * @see ResolveMode
   */
  public void setResolveMode(ResolveMode mode) {
    this.resolveMode = mode;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[24]++;
  }

  ResolveMode getResolveMode() {
    return resolveMode;
  }

  public ErrorReporter getErrorReporter() {
    return reporter;
  }

  public boolean shouldTolerateUndefinedValues() {
    return tolerateUndefinedValues;
  }

  /**
   * Reset to run the TypeCheck pass.
   */
  public void resetForTypeCheck() {
    typesIndexedByProperty.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[25]++;
    eachRefTypeIndexedByProperty.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[26]++;
    initializeBuiltInTypes();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[27]++;
    namesToTypes.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[28]++;
    namespaces.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[29]++;
    initializeRegistry();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[30]++;
  }

  private void initializeBuiltInTypes() {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[31]++;
    // These locals shouldn't be all caps.
    BooleanType BOOLEAN_TYPE = new BooleanType(this);
    registerNativeType(JSTypeNative.BOOLEAN_TYPE, BOOLEAN_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[32]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[33]++;

    NullType NULL_TYPE = new NullType(this);
    registerNativeType(JSTypeNative.NULL_TYPE, NULL_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[34]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[35]++;

    NumberType NUMBER_TYPE = new NumberType(this);
    registerNativeType(JSTypeNative.NUMBER_TYPE, NUMBER_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[36]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[37]++;

    StringType STRING_TYPE = new StringType(this);
    registerNativeType(JSTypeNative.STRING_TYPE, STRING_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[38]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[39]++;

    UnknownType UNKNOWN_TYPE = new UnknownType(this, false);
    registerNativeType(JSTypeNative.UNKNOWN_TYPE, UNKNOWN_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[40]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[41]++;
    UnknownType checkedUnknownType = new UnknownType(this, true);
    registerNativeType(
        JSTypeNative.CHECKED_UNKNOWN_TYPE, checkedUnknownType);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[42]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[43]++;

    VoidType VOID_TYPE = new VoidType(this);
    registerNativeType(JSTypeNative.VOID_TYPE, VOID_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[44]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[45]++;

    AllType ALL_TYPE = new AllType(this);
    registerNativeType(JSTypeNative.ALL_TYPE, ALL_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[46]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[47]++;

    // Top Level Prototype (the One)
    // The initializations of TOP_LEVEL_PROTOTYPE and OBJECT_FUNCTION_TYPE
    // use each other's results, so at least one of them will get null
    // instead of an actual type; however, this seems to be benign.
    PrototypeObjectType TOP_LEVEL_PROTOTYPE =
        new PrototypeObjectType(this, null, null, true, null);
    registerNativeType(JSTypeNative.TOP_LEVEL_PROTOTYPE, TOP_LEVEL_PROTOTYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[48]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[49]++;

    // Object
    FunctionType OBJECT_FUNCTION_TYPE =
        new FunctionType(this, "Object", null,
            createArrowType(createOptionalParameters(ALL_TYPE), UNKNOWN_TYPE),
            null,
            createTemplateTypeMap(ImmutableList.of(
                OBJECT_INDEX_TEMPLATE, OBJECT_ELEMENT_TEMPLATE), null),
            true, true);

    OBJECT_FUNCTION_TYPE.setPrototype(TOP_LEVEL_PROTOTYPE, null);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[50]++;
    registerNativeType(JSTypeNative.OBJECT_FUNCTION_TYPE, OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[51]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[52]++;

    ObjectType OBJECT_TYPE = OBJECT_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.OBJECT_TYPE, OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[53]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[54]++;

    ObjectType OBJECT_PROTOTYPE = OBJECT_FUNCTION_TYPE.getPrototype();
    registerNativeType(JSTypeNative.OBJECT_PROTOTYPE, OBJECT_PROTOTYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[55]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[56]++;

    // Function
    FunctionType FUNCTION_FUNCTION_TYPE =
        new FunctionType(this, "Function", null,
            createArrowType(
                createParametersWithVarArgs(ALL_TYPE), UNKNOWN_TYPE),
            null, null, true, true);
    FUNCTION_FUNCTION_TYPE.setPrototypeBasedOn(OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[57]++;
    registerNativeType(
        JSTypeNative.FUNCTION_FUNCTION_TYPE, FUNCTION_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[58]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[59]++;

    ObjectType FUNCTION_PROTOTYPE = FUNCTION_FUNCTION_TYPE.getPrototype();
    registerNativeType(JSTypeNative.FUNCTION_PROTOTYPE, FUNCTION_PROTOTYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[60]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[61]++;

    NoType NO_TYPE = new NoType(this);
    registerNativeType(JSTypeNative.NO_TYPE, NO_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[62]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[63]++;

    NoObjectType NO_OBJECT_TYPE = new NoObjectType(this);
    registerNativeType(JSTypeNative.NO_OBJECT_TYPE, NO_OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[64]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[65]++;

    NoObjectType NO_RESOLVED_TYPE = new NoResolvedType(this);
    registerNativeType(JSTypeNative.NO_RESOLVED_TYPE, NO_RESOLVED_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[66]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[67]++;

    // Array
    FunctionType ARRAY_FUNCTION_TYPE =
      new FunctionType(this, "Array", null,
          createArrowType(createParametersWithVarArgs(ALL_TYPE), null),
          null,
          createTemplateTypeMap(ImmutableList.of(
              OBJECT_ELEMENT_TEMPLATE), null),
          true, true);
    ARRAY_FUNCTION_TYPE.getInternalArrowType().returnType =
        ARRAY_FUNCTION_TYPE.getInstanceType();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[68]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[69]++;

    ObjectType arrayPrototype = ARRAY_FUNCTION_TYPE.getPrototype();
    registerNativeType(JSTypeNative.ARRAY_FUNCTION_TYPE, ARRAY_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[70]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[71]++;

    ObjectType ARRAY_TYPE = ARRAY_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.ARRAY_TYPE, ARRAY_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[72]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[73]++;

    // Boolean
    FunctionType BOOLEAN_OBJECT_FUNCTION_TYPE =
        new FunctionType(this, "Boolean", null,
            createArrowType(createOptionalParameters(ALL_TYPE), BOOLEAN_TYPE),
            null, null, true, true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[74]++;
    ObjectType booleanPrototype = BOOLEAN_OBJECT_FUNCTION_TYPE.getPrototype();
    registerNativeType(
        JSTypeNative.BOOLEAN_OBJECT_FUNCTION_TYPE,
        BOOLEAN_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[75]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[76]++;

    ObjectType BOOLEAN_OBJECT_TYPE =
        BOOLEAN_OBJECT_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.BOOLEAN_OBJECT_TYPE, BOOLEAN_OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[77]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[78]++;

    // Date
    FunctionType DATE_FUNCTION_TYPE =
      new FunctionType(this, "Date", null,
          createArrowType(
              createOptionalParameters(UNKNOWN_TYPE, UNKNOWN_TYPE, UNKNOWN_TYPE,
                  UNKNOWN_TYPE, UNKNOWN_TYPE, UNKNOWN_TYPE, UNKNOWN_TYPE),
              STRING_TYPE),
          null, null, true, true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[79]++;
    ObjectType datePrototype = DATE_FUNCTION_TYPE.getPrototype();
    registerNativeType(JSTypeNative.DATE_FUNCTION_TYPE, DATE_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[80]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[81]++;

    ObjectType DATE_TYPE = DATE_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.DATE_TYPE, DATE_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[82]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[83]++;

    // Error
    FunctionType ERROR_FUNCTION_TYPE = new ErrorFunctionType(this, "Error");
    registerNativeType(JSTypeNative.ERROR_FUNCTION_TYPE, ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[84]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[85]++;

    ObjectType ERROR_TYPE = ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.ERROR_TYPE, ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[86]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[87]++;

    // EvalError
    FunctionType EVAL_ERROR_FUNCTION_TYPE =
        new ErrorFunctionType(this, "EvalError");
    EVAL_ERROR_FUNCTION_TYPE.setPrototypeBasedOn(ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[88]++;
    registerNativeType(
        JSTypeNative.EVAL_ERROR_FUNCTION_TYPE, EVAL_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[89]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[90]++;

    ObjectType EVAL_ERROR_TYPE = EVAL_ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.EVAL_ERROR_TYPE, EVAL_ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[91]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[92]++;

    // RangeError
    FunctionType RANGE_ERROR_FUNCTION_TYPE =
        new ErrorFunctionType(this, "RangeError");
    RANGE_ERROR_FUNCTION_TYPE.setPrototypeBasedOn(ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[93]++;
    registerNativeType(
        JSTypeNative.RANGE_ERROR_FUNCTION_TYPE, RANGE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[94]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[95]++;

    ObjectType RANGE_ERROR_TYPE = RANGE_ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.RANGE_ERROR_TYPE, RANGE_ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[96]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[97]++;

    // ReferenceError
    FunctionType REFERENCE_ERROR_FUNCTION_TYPE =
        new ErrorFunctionType(this, "ReferenceError");
    REFERENCE_ERROR_FUNCTION_TYPE.setPrototypeBasedOn(ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[98]++;
    registerNativeType(
        JSTypeNative.REFERENCE_ERROR_FUNCTION_TYPE,
        REFERENCE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[99]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[100]++;

    ObjectType REFERENCE_ERROR_TYPE =
        REFERENCE_ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.REFERENCE_ERROR_TYPE, REFERENCE_ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[101]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[102]++;

    // SyntaxError
    FunctionType SYNTAX_ERROR_FUNCTION_TYPE =
        new ErrorFunctionType(this, "SyntaxError");
    SYNTAX_ERROR_FUNCTION_TYPE.setPrototypeBasedOn(ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[103]++;
    registerNativeType(
        JSTypeNative.SYNTAX_ERROR_FUNCTION_TYPE, SYNTAX_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[104]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[105]++;

    ObjectType SYNTAX_ERROR_TYPE = SYNTAX_ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.SYNTAX_ERROR_TYPE, SYNTAX_ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[106]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[107]++;

    // TypeError
    FunctionType TYPE_ERROR_FUNCTION_TYPE =
        new ErrorFunctionType(this, "TypeError");
    TYPE_ERROR_FUNCTION_TYPE.setPrototypeBasedOn(ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[108]++;
    registerNativeType(
        JSTypeNative.TYPE_ERROR_FUNCTION_TYPE, TYPE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[109]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[110]++;

    ObjectType TYPE_ERROR_TYPE = TYPE_ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.TYPE_ERROR_TYPE, TYPE_ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[111]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[112]++;

    // URIError
    FunctionType URI_ERROR_FUNCTION_TYPE =
        new ErrorFunctionType(this, "URIError");
    URI_ERROR_FUNCTION_TYPE.setPrototypeBasedOn(ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[113]++;
    registerNativeType(
        JSTypeNative.URI_ERROR_FUNCTION_TYPE, URI_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[114]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[115]++;

    ObjectType URI_ERROR_TYPE = URI_ERROR_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.URI_ERROR_TYPE, URI_ERROR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[116]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[117]++;

    // Number
    FunctionType NUMBER_OBJECT_FUNCTION_TYPE =
        new FunctionType(this, "Number", null,
            createArrowType(createOptionalParameters(ALL_TYPE), NUMBER_TYPE),
            null, null, true, true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[118]++;
    ObjectType numberPrototype = NUMBER_OBJECT_FUNCTION_TYPE.getPrototype();
    registerNativeType(
        JSTypeNative.NUMBER_OBJECT_FUNCTION_TYPE, NUMBER_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[119]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[120]++;

    ObjectType NUMBER_OBJECT_TYPE =
        NUMBER_OBJECT_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.NUMBER_OBJECT_TYPE, NUMBER_OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[121]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[122]++;

    // RegExp
    FunctionType REGEXP_FUNCTION_TYPE =
      new FunctionType(this, "RegExp", null,
          createArrowType(createOptionalParameters(ALL_TYPE, ALL_TYPE)),
          null, null, true, true);
    REGEXP_FUNCTION_TYPE.getInternalArrowType().returnType =
        REGEXP_FUNCTION_TYPE.getInstanceType();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[123]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[124]++;

    ObjectType regexpPrototype = REGEXP_FUNCTION_TYPE.getPrototype();
    registerNativeType(JSTypeNative.REGEXP_FUNCTION_TYPE, REGEXP_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[125]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[126]++;

    ObjectType REGEXP_TYPE = REGEXP_FUNCTION_TYPE.getInstanceType();
    registerNativeType(JSTypeNative.REGEXP_TYPE, REGEXP_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[127]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[128]++;

    // String
    FunctionType STRING_OBJECT_FUNCTION_TYPE =
        new FunctionType(this, "String", null,
            createArrowType(createOptionalParameters(ALL_TYPE), STRING_TYPE),
            null, null, true, true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[129]++;
    ObjectType stringPrototype = STRING_OBJECT_FUNCTION_TYPE.getPrototype();
    registerNativeType(
        JSTypeNative.STRING_OBJECT_FUNCTION_TYPE, STRING_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[130]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[131]++;

    ObjectType STRING_OBJECT_TYPE =
        STRING_OBJECT_FUNCTION_TYPE.getInstanceType();
    registerNativeType(
        JSTypeNative.STRING_OBJECT_TYPE, STRING_OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[132]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[133]++;

    // (null,void)
    JSType NULL_VOID =
        createUnionType(NULL_TYPE, VOID_TYPE);
    registerNativeType(JSTypeNative.NULL_VOID, NULL_VOID);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[134]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[135]++;

    // (Object,string,number)
    JSType OBJECT_NUMBER_STRING =
        createUnionType(OBJECT_TYPE, NUMBER_TYPE, STRING_TYPE);
    registerNativeType(JSTypeNative.OBJECT_NUMBER_STRING, OBJECT_NUMBER_STRING);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[136]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[137]++;

    // (Object,string,number,boolean)
    JSType OBJECT_NUMBER_STRING_BOOLEAN =
        createUnionType(OBJECT_TYPE, NUMBER_TYPE, STRING_TYPE, BOOLEAN_TYPE);
    registerNativeType(JSTypeNative.OBJECT_NUMBER_STRING_BOOLEAN,
        OBJECT_NUMBER_STRING_BOOLEAN);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[138]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[139]++;

    // (string,number,boolean)
    JSType NUMBER_STRING_BOOLEAN =
        createUnionType(NUMBER_TYPE, STRING_TYPE, BOOLEAN_TYPE);
    registerNativeType(JSTypeNative.NUMBER_STRING_BOOLEAN,
        NUMBER_STRING_BOOLEAN);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[140]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[141]++;

    // (string,number)
    JSType NUMBER_STRING = createUnionType(NUMBER_TYPE, STRING_TYPE);
    registerNativeType(JSTypeNative.NUMBER_STRING, NUMBER_STRING);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[142]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[143]++;

    // Native object properties are filled in by externs...

    // (String, string)
    JSType STRING_VALUE_OR_OBJECT_TYPE =
        createUnionType(STRING_OBJECT_TYPE, STRING_TYPE);
    registerNativeType(
        JSTypeNative.STRING_VALUE_OR_OBJECT_TYPE, STRING_VALUE_OR_OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[144]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[145]++;

    // (Number, number)
    JSType NUMBER_VALUE_OR_OBJECT_TYPE =
        createUnionType(NUMBER_OBJECT_TYPE, NUMBER_TYPE);
    registerNativeType(
        JSTypeNative.NUMBER_VALUE_OR_OBJECT_TYPE, NUMBER_VALUE_OR_OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[146]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[147]++;

    // unknown function type, i.e. (?...) -> ?
    FunctionType U2U_FUNCTION_TYPE =
        createFunctionType(UNKNOWN_TYPE, true, UNKNOWN_TYPE);
    registerNativeType(JSTypeNative.U2U_FUNCTION_TYPE, U2U_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[148]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[149]++;

    // unknown constructor type, i.e. (?...) -> ? with the Unknown type
    // as instance type
    FunctionType U2U_CONSTRUCTOR_TYPE =
        // This is equivalent to
        // createConstructorType(UNKNOWN_TYPE, true, UNKNOWN_TYPE), but,
        // in addition, overrides getInstanceType() to return the NoObject type
        // instead of a new anonymous object.
        new FunctionType(this, "Function", null,
            createArrowType(
                createParametersWithVarArgs(UNKNOWN_TYPE),
                UNKNOWN_TYPE),
            UNKNOWN_TYPE, null, true, true) {
          private static final long serialVersionUID = 1L;

          @Override public FunctionType getConstructor() {
            return registry.getNativeFunctionType(
                JSTypeNative.FUNCTION_FUNCTION_TYPE);
          }
        };

    // The U2U_CONSTRUCTOR is weird, because it's the supertype of its
    // own constructor.
    registerNativeType(JSTypeNative.U2U_CONSTRUCTOR_TYPE, U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[150]++;
    registerNativeType(
        JSTypeNative.FUNCTION_INSTANCE_TYPE, U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[151]++;

    FUNCTION_FUNCTION_TYPE.setInstanceType(U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[152]++;
    U2U_CONSTRUCTOR_TYPE.setImplicitPrototype(FUNCTION_PROTOTYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[153]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[154]++;

    // least function type, i.e. (All...) -> NoType
    FunctionType LEAST_FUNCTION_TYPE =
        createNativeFunctionTypeWithVarArgs(NO_TYPE, ALL_TYPE);
    registerNativeType(JSTypeNative.LEAST_FUNCTION_TYPE, LEAST_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[155]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[156]++;

    // the 'this' object in the global scope
    FunctionType GLOBAL_THIS_CTOR =
        new FunctionType(this, "global this", null,
            createArrowType(createParameters(false, ALL_TYPE), NUMBER_TYPE),
            null, null, true, true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[157]++;
    ObjectType GLOBAL_THIS = GLOBAL_THIS_CTOR.getInstanceType();
    registerNativeType(JSTypeNative.GLOBAL_THIS, GLOBAL_THIS);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[158]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[159]++;

    // greatest function type, i.e. (NoType...) -> All
    FunctionType GREATEST_FUNCTION_TYPE =
        createNativeFunctionTypeWithVarArgs(ALL_TYPE, NO_TYPE);
    registerNativeType(JSTypeNative.GREATEST_FUNCTION_TYPE,
        GREATEST_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[160]++;

    // Register the prototype property. See the comments below in
    // registerPropertyOnType about the bootstrapping process.
    registerPropertyOnType("prototype", OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[161]++;
  }

  private void initializeRegistry() {
    register(getNativeType(JSTypeNative.ARRAY_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[162]++;
    register(getNativeType(JSTypeNative.BOOLEAN_OBJECT_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[163]++;
    register(getNativeType(JSTypeNative.BOOLEAN_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[164]++;
    register(getNativeType(JSTypeNative.DATE_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[165]++;
    register(getNativeType(JSTypeNative.NULL_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[166]++;
    register(getNativeType(JSTypeNative.NULL_TYPE), "Null");
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[167]++;
    register(getNativeType(JSTypeNative.NUMBER_OBJECT_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[168]++;
    register(getNativeType(JSTypeNative.NUMBER_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[169]++;
    register(getNativeType(JSTypeNative.OBJECT_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[170]++;
    register(getNativeType(JSTypeNative.ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[171]++;
    register(getNativeType(JSTypeNative.URI_ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[172]++;
    register(getNativeType(JSTypeNative.EVAL_ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[173]++;
    register(getNativeType(JSTypeNative.TYPE_ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[174]++;
    register(getNativeType(JSTypeNative.RANGE_ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[175]++;
    register(getNativeType(JSTypeNative.REFERENCE_ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[176]++;
    register(getNativeType(JSTypeNative.SYNTAX_ERROR_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[177]++;
    register(getNativeType(JSTypeNative.REGEXP_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[178]++;
    register(getNativeType(JSTypeNative.STRING_OBJECT_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[179]++;
    register(getNativeType(JSTypeNative.STRING_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[180]++;
    register(getNativeType(JSTypeNative.VOID_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[181]++;
    register(getNativeType(JSTypeNative.VOID_TYPE), "Undefined");
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[182]++;
    register(getNativeType(JSTypeNative.VOID_TYPE), "void");
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[183]++;
    register(getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE), "Function");
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[184]++;
  }

  private void register(JSType type) {
    register(type, type.toString());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[185]++;
  }

  private void register(JSType type, String name) {
    Preconditions.checkArgument(
        !name.contains("<"), "Type names cannot contain template annotations.");
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[186]++;

    namesToTypes.put(name, type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[187]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[188]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

    // Add all the namespaces in which this name lives.
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((name.indexOf('.') > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[1]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[2]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[3]++;
}
      name = name.substring(0, name.lastIndexOf('.'));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[189]++;
      namespaces.add(name);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[190]++;
    }
  }

  private void registerNativeType(JSTypeNative typeId, JSType type) {
    nativeTypes[typeId.ordinal()] = type;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[191]++;
  }

  /**
   * Tells the type system that {@code owner} may have a property named
   * {@code propertyName}. This allows the registry to keep track of what
   * types a property is defined upon.
   *
   * This is NOT the same as saying that {@code owner} must have a property
   * named type. ObjectType#hasProperty attempts to minimize false positives
   * ("if we're not sure, then don't type check this property"). The type
   * registry, on the other hand, should attempt to minimize false negatives
   * ("if this property is assigned anywhere in the program, it must
   * show up in the type registry").
   */
  public void registerPropertyOnType(String propertyName, JSType type) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[192]++;
    UnionTypeBuilder typeSet = typesIndexedByProperty.get(propertyName);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[193]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((typeSet == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[1]++;
      typeSet = new UnionTypeBuilder(this, PROPERTY_CHECKING_UNION_SIZE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[194]++;
      typesIndexedByProperty.put(propertyName, typeSet);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[195]++;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[2]++;}

    typeSet.addAlternate(type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[196]++;
    addReferenceTypeIndexedByProperty(propertyName, type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[197]++;

    // Clear cached values that depend on typesIndexedByProperty.
    greatestSubtypeByProperty.remove(propertyName);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[198]++;
  }

  private void addReferenceTypeIndexedByProperty(
      String propertyName, JSType type) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[199]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((type instanceof ObjectType) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((((ObjectType) type).hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[3]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[200]++;
      Map<String, ObjectType> typeSet =
          eachRefTypeIndexedByProperty.get(propertyName);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[201]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((typeSet == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[5]++;
        typeSet = Maps.newHashMap();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[202]++;
        eachRefTypeIndexedByProperty.put(propertyName, typeSet);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[203]++;

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[6]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[204]++;
      ObjectType objType = (ObjectType) type;
      typeSet.put(objType.getReferenceName(), objType);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[205]++;

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[4]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[206]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type instanceof NamedType) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[7]++;
      addReferenceTypeIndexedByProperty(
          propertyName, ((NamedType) type).getReferencedType());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[207]++;

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[8]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[208]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[9]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[209]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[4]++;


      for (JSType alternate : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[4]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[5]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[6]++;
}
        addReferenceTypeIndexedByProperty(propertyName, alternate);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[210]++;
      }

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[10]++;}
}
}
  }

  /**
   * Removes the index's reference to a property on the given type (if it is
   * currently registered). If the property is not registered on the type yet,
   * this method will not change internal state.
   *
   * @param propertyName the name of the property to unregister
   * @param type the type to unregister the property on.
   */
  public void unregisterPropertyOnType(String propertyName, JSType type) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[211]++;
    // TODO(bashir): typesIndexedByProperty should also be updated!
    Map<String, ObjectType> typeSet =
        eachRefTypeIndexedByProperty.get(propertyName);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[212]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((typeSet != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[11]++;
      typeSet.remove(type.toObjectType().getReferenceName());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[213]++;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[12]++;}
  }

  /**
   * Gets the greatest subtype of the {@code type} that has a property
   * {@code propertyName} defined on it.
   */
  public JSType getGreatestSubtypeWithProperty(
      JSType type, String propertyName) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[214]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((greatestSubtypeByProperty.containsKey(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[13]++;
      return greatestSubtypeByProperty.get(propertyName)
          .getGreatestSubtype(type);

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[14]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[215]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((typesIndexedByProperty.containsKey(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[15]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[216]++;
      JSType built = typesIndexedByProperty.get(propertyName).build();
      greatestSubtypeByProperty.put(propertyName, built);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[217]++;
      return built.getGreatestSubtype(type);

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[16]++;}
    return getNativeType(NO_TYPE);
  }

  /**
   * Returns whether the given property can possibly be set on the given type.
   */
  public boolean canPropertyBeDefined(JSType type, String propertyName) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[218]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((typesIndexedByProperty.containsKey(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[17]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[219]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[7]++;


      for (JSType alt :
               typesIndexedByProperty.get(propertyName).getAlternates()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[7]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[8]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[9]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[220]++;
        JSType greatestSubtype = alt.getGreatestSubtype(type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[221]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((greatestSubtype.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[19]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[222]++;
          // We've found a type with this property. Now we just have to make
          // sure it's not a type used for internal bookkeeping.
          RecordType maybeRecordType = greatestSubtype.toMaybeRecordType();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[223]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((maybeRecordType != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((maybeRecordType.isSynthetic()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[21]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[224]++;
            continue;

          } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[22]++;}

          return true;

        } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[20]++;}
      }

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[18]++;}
    return false;
  }

  /**
   * Returns each type that has a property {@code propertyName} defined on it.
   *
   * Like most types in our type system, the collection of types returned
   * will be collapsed. This means that if a type is defined on
   * {@code Object} and on {@code Array}, it would be reasonable for this
   * method to return either {@code [Object, Array]} or just {@code [Object]}.
   */
  public Iterable<JSType> getTypesWithProperty(String propertyName) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[225]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((typesIndexedByProperty.containsKey(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[23]++;
      return typesIndexedByProperty.get(propertyName).getAlternates();

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[24]++;
      return ImmutableList.of();
    }
  }

  /**
   * Returns each reference type that has a property {@code propertyName}
   * defined on it.
   *
   * Unlike most types in our type system, the collection of types returned
   * will not be collapsed. This means that if a type is defined on
   * {@code Object} and on {@code Array}, this method must return
   * {@code [Object, Array]}. It would not be correct to collapse them to
   * {@code [Object]}.
   */
  public Iterable<ObjectType> getEachReferenceTypeWithProperty(
      String propertyName) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[226]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((eachRefTypeIndexedByProperty.containsKey(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[25]++;
      return eachRefTypeIndexedByProperty.get(propertyName).values();

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[26]++;
      return ImmutableList.of();
    }
  }

  /**
   * Finds the common supertype of the two given object types.
   */
  ObjectType findCommonSuperObject(ObjectType a, ObjectType b) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[227]++;
    List<ObjectType> stackA = getSuperStack(a);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[228]++;
    List<ObjectType> stackB = getSuperStack(b);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[229]++;

    ObjectType result = getNativeObjectType(JSTypeNative.OBJECT_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[230]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;
    while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((stackA.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((stackB.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[10]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[11]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[12]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[231]++;
      ObjectType currentA = stackA.remove(stackA.size() - 1);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[232]++;
      ObjectType currentB = stackB.remove(stackB.size() - 1);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[233]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((currentA.isEquivalentTo(currentB)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[27]++;
        result = currentA;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[234]++;

      } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[28]++;
        return result;
      }
    }
    return result;
  }

  private static List<ObjectType> getSuperStack(ObjectType a) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[235]++;
    List<ObjectType> stack = Lists.newArrayListWithExpectedSize(5);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[236]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[13]++;


int CodeCoverConditionCoverageHelper_C17;
    for (ObjectType current = a;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false);
         current = current.getImplicitPrototype()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[13]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[14]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[15]++;
}
      stack.add(current);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[237]++;
    }
    return stack;
  }

  /**
   * Increments the current generation. Clients must call this in order to
   * move to the next generation of type resolution, allowing types to attempt
   * resolution again.
   */
  public void incrementGeneration() {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[238]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[16]++;


    for (NamedType type : resolvedNamedTypes.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[16]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[17]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[18]++;
}
      type.clearResolved();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[239]++;
    }
    unresolvedNamedTypes.putAll(resolvedNamedTypes);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[240]++;
    resolvedNamedTypes.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[241]++;
  }

  boolean isLastGeneration() {
    return lastGeneration;
  }

  /**
   * Sets whether this is the last generation. In the last generation,
   * {@link NamedType} warns about unresolved types.
   */
  public void setLastGeneration(boolean lastGeneration) {
    this.lastGeneration = lastGeneration;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[242]++;
  }

  /**
   * Tells the type system that {@code type} implements interface {@code
   * interfaceInstance}.
   * {@code inter} must be an ObjectType for the instance of the interface as it
   * could be a named type and not yet have the constructor.
   */
  void registerTypeImplementingInterface(
      FunctionType type, ObjectType interfaceInstance) {
    interfaceToImplementors.put(interfaceInstance.getReferenceName(), type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[243]++;
  }

  /**
   * Returns a collection of types that directly implement {@code
   * interfaceInstance}.  Subtypes of implementing types are not guaranteed to
   * be returned.  {@code interfaceInstance} must be an ObjectType for the
   * instance of the interface.
   */
  public Collection<FunctionType> getDirectImplementors(
      ObjectType interfaceInstance) {
    return interfaceToImplementors.get(interfaceInstance.getReferenceName());
  }

  /**
   * Records declared global type names. This makes resolution faster
   * and more robust in the common case.
   *
   * @param name The name of the type to be recorded.
   * @param t The actual type being associated with the name.
   * @return True if this name is not already defined, false otherwise.
   */
  public boolean declareType(String name, JSType t) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[244]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((namesToTypes.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[29]++;
      return false;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[30]++;}
    register(t, name);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[245]++;
    return true;
  }

  /**
   * Overrides a declared global type name. Throws an exception if this
   * type name hasn't been declared yet.
   */
  public void overwriteDeclaredType(String name, JSType t) {
    Preconditions.checkState(namesToTypes.containsKey(name));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[246]++;
    register(t, name);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[247]++;
  }

  /**
   * Records a forward-declared type name. We will not emit errors if this
   * type name never resolves to anything.
   */
  public void forwardDeclareType(String name) {
    forwardDeclaredTypes.add(name);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[248]++;
  }

  /**
   * Whether this is a forward-declared type name.
   */
  public boolean isForwardDeclaredType(String name) {
    return forwardDeclaredTypes.contains(name);
  }

  /** Determines whether the given JS package exists. */
  public boolean hasNamespace(String name) {
    return namespaces.contains(name);
  }

  /**
   * Looks up a type by name.
   *
   * @param jsTypeName The name string.
   * @return the corresponding JSType object or {@code null} it cannot be found
   */
  public JSType getType(String jsTypeName) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[249]++;
    // TODO(user): Push every local type name out of namesToTypes so that
    // NamedType#resolve is correct.
    TemplateType templateType = templateTypes.get(jsTypeName);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[250]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((templateType != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[31]++;
      return templateType;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[32]++;}
    return namesToTypes.get(jsTypeName);
  }

  public JSType getNativeType(JSTypeNative typeId) {
    return nativeTypes[typeId.ordinal()];
  }

  public ObjectType getNativeObjectType(JSTypeNative typeId) {
    return (ObjectType) getNativeType(typeId);
  }

  public FunctionType getNativeFunctionType(JSTypeNative typeId) {
    return (FunctionType) getNativeType(typeId);
  }

  /**
   * Looks up a type by name. To allow for forward references to types, an
   * unrecognized string has to be bound to a NamedType object that will be
   * resolved later.
   *
   * @param scope A scope for doing type name resolution.
   * @param jsTypeName The name string.
   * @param sourceName The name of the source file where this reference appears.
   * @param lineno The line number of the reference.
   * @return a NamedType if the string argument is not one of the known types,
   *     otherwise the corresponding JSType object.
   */
  public JSType getType(StaticScope<JSType> scope, String jsTypeName,
      String sourceName, int lineno, int charno) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[251]++;
    JSType type = getType(jsTypeName);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[252]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[33]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[253]++;
      // TODO(user): Each instance should support named type creation using
      // interning.
      NamedType namedType =
          new NamedType(this, jsTypeName, sourceName, lineno, charno);
      unresolvedNamedTypes.put(scope, namedType);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[254]++;
      type = namedType;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[255]++;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[34]++;}
    return type;
  }

  /**
   * Flushes out the current resolved and unresolved Named Types from
   * the type registry.  This is intended to be used ONLY before a
   * compile is run.
   */
  public void clearNamedTypes() {
    resolvedNamedTypes.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[256]++;
    unresolvedNamedTypes.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[257]++;
  }

  /**
   * Resolve all the unresolved types in the given scope.
   */
  public void resolveTypesInScope(StaticScope<JSType> scope) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[258]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[19]++;


    for (NamedType type : unresolvedNamedTypes.get(scope)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[19]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[20]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[21]++;
}
      type.resolve(reporter, scope);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[259]++;
    }

    resolvedNamedTypes.putAll(scope, unresolvedNamedTypes.removeAll(scope));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[260]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[261]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((scope.getParentScope() == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[35]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[262]++;
      // By default, the global "this" type is just an anonymous object.
      // If the user has defined a Window type, make the Window the
      // implicit prototype of "this".
      PrototypeObjectType globalThis = (PrototypeObjectType) getNativeType(
          JSTypeNative.GLOBAL_THIS);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[263]++;
      JSType windowType = getType("Window");
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[264]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((globalThis.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[37]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[265]++;
        ObjectType windowObjType = ObjectType.cast(windowType);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[266]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((windowObjType != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[39]++;
          globalThis.setImplicitPrototype(windowObjType);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[267]++;

        } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[40]++;
          globalThis.setImplicitPrototype(
              getNativeObjectType(JSTypeNative.OBJECT_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[268]++;
        }

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[38]++;}

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[36]++;}
  }

  /**
   * Creates a type representing optional values of the given type.
   * @return the union of the type and the void type
   */
  public JSType createOptionalType(JSType type) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[269]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((type instanceof UnknownType) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((type.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[41]++;
      return type;

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[42]++;
      return createUnionType(type, getNativeType(JSTypeNative.VOID_TYPE));
    }
  }

  /**
   * Creates a type representing nullable values of the given type.
   * @return the union of the type and the Null type
   */
  public JSType createDefaultObjectUnion(JSType type) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[270]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((type.isTemplateType()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[43]++;
      // Template types represent the substituted type exactly and should
      // not be wrapped.
      return type;

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[44]++;
      return shouldTolerateUndefinedValues()
        ? createOptionalNullableType(type)
        : createNullableType(type);
    }
  }

  /**
   * Creates a type representing nullable values of the given type.
   * @return the union of the type and the Null type
   */
  public JSType createNullableType(JSType type) {
    return createUnionType(type, getNativeType(JSTypeNative.NULL_TYPE));
  }

  /**
   * Creates a nullable and undefine-able value of the given type.
   * @return The union of the type and null and undefined.
   */
  public JSType createOptionalNullableType(JSType type) {
    return createUnionType(type, getNativeType(JSTypeNative.VOID_TYPE),
        getNativeType(JSTypeNative.NULL_TYPE));
  }

  /**
   * Creates a union type whose variants are the arguments.
   */
  public JSType createUnionType(JSType... variants) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[271]++;
    UnionTypeBuilder builder = new UnionTypeBuilder(this);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[272]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[22]++;


    for (JSType type : variants) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[22]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[23]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[24]++;
}
      builder.addAlternate(type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[273]++;
    }
    return builder.build();
  }

  /**
   * Creates a union type whose variants are the built-in types specified
   * by the arguments.
   */
  public JSType createUnionType(JSTypeNative... variants) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[274]++;
    UnionTypeBuilder builder = new UnionTypeBuilder(this);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[275]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[25]++;


    for (JSTypeNative typeId : variants) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[25]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[26]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[27]++;
}
      builder.addAlternate(getNativeType(typeId));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[276]++;
    }
    return builder.build();
  }

  /**
   * Creates an enum type.
   */
  public EnumType createEnumType(
      String name, Node source, JSType elementsType) {
    return new EnumType(this, name, source, elementsType);
  }

  /**
   * Creates an arrow type, an abstract representation of the parameters
   * and return value of a function.
   *
   * @param parametersNode the parameters' types, formatted as a Node with
   *     param names and optionality info.
   * @param returnType the function's return type
   */
  ArrowType createArrowType(Node parametersNode, JSType returnType) {
    return new ArrowType(this, parametersNode, returnType);
  }

  /**
   * Creates an arrow type with an unknown return type.
   *
   * @param parametersNode the parameters' types, formatted as a Node with
   *     param names and optionality info.
   */
  ArrowType createArrowType(Node parametersNode) {
    return new ArrowType(this, parametersNode, null);
  }

  /**
   * Creates a function type.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public FunctionType createFunctionType(
      JSType returnType, JSType... parameterTypes) {
    return createFunctionType(returnType, createParameters(parameterTypes));
  }

  /**
   * Creates a function type. The last parameter type of the function is
   * considered a variable length argument.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public FunctionType createFunctionTypeWithVarArgs(
      JSType returnType, List<JSType> parameterTypes) {
    return createFunctionType(
        returnType, createParametersWithVarArgs(parameterTypes));
  }

  /**
   * Creates a function type.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public FunctionType createFunctionType(
      JSType returnType, List<JSType> parameterTypes) {
    return createFunctionType(returnType, createParameters(parameterTypes));
  }

  /**
   * Creates a function type. The last parameter type of the function is
   * considered a variable length argument.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public FunctionType createFunctionTypeWithVarArgs(
      JSType returnType, JSType... parameterTypes) {
    return createFunctionType(
        returnType, createParametersWithVarArgs(parameterTypes));
  }

  /**
   * Creates a function type. The last parameter type of the function is
   * considered a variable length argument.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  private FunctionType createNativeFunctionTypeWithVarArgs(
      JSType returnType, JSType... parameterTypes) {
    return createNativeFunctionType(
        returnType, createParametersWithVarArgs(parameterTypes));
  }

  /**
   * Creates a function type which can act as a constructor.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public FunctionType createConstructorType(
      JSType returnType, JSType... parameterTypes) {
    return createConstructorType(
        null, null, createParameters(parameterTypes), returnType, null);
  }

  /**
   * Creates a function type which can act as a constructor. The last
   * parameter type of the constructor is considered a variable length argument.
   *
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  private FunctionType createConstructorTypeWithVarArgs(
      JSType returnType, JSType... parameterTypes) {
    return createConstructorType(
        null, null, createParametersWithVarArgs(parameterTypes), returnType,
        null);
  }

  /**
   * Creates a function type in which {@code this} refers to an object instance.
   *
   * @param instanceType the type of {@code this}
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public JSType createFunctionType(ObjectType instanceType,
      JSType returnType, List<JSType> parameterTypes) {
    return new FunctionBuilder(this)
        .withParamsNode(createParameters(parameterTypes))
        .withReturnType(returnType)
        .withTypeOfThis(instanceType)
        .build();
  }

  /**
   * Creates a function type in which {@code this} refers to an object instance.
   * The last parameter type of the function is considered a variable length
   * argument.
   *
   * @param instanceType the type of {@code this}
   * @param returnType the function's return type
   * @param parameterTypes the parameters' types
   */
  public JSType createFunctionTypeWithVarArgs(ObjectType instanceType,
      JSType returnType, List<JSType> parameterTypes) {
    return new FunctionBuilder(this)
        .withParamsNode(createParametersWithVarArgs(parameterTypes))
        .withReturnType(returnType)
        .withTypeOfThis(instanceType)
        .build();
  }

  /**
   * Creates a tree hierarchy representing a typed argument list.
   *
   * @param parameterTypes the parameter types.
   * @return a tree hierarchy representing a typed argument list.
   */
  public Node createParameters(List<JSType> parameterTypes) {
    return createParameters(
        parameterTypes.toArray(new JSType[parameterTypes.size()]));
  }

  /**
   * Creates a tree hierarchy representing a typed argument list. The last
   * parameter type is considered a variable length argument.
   *
   * @param parameterTypes the parameter types. The last element of this array
   *     is considered a variable length argument.
   * @return a tree hierarchy representing a typed argument list.
   */
  public Node createParametersWithVarArgs(List<JSType> parameterTypes) {
    return createParametersWithVarArgs(
        parameterTypes.toArray(new JSType[parameterTypes.size()]));
  }

  /**
   * Creates a tree hierarchy representing a typed argument list.
   *
   * @param parameterTypes the parameter types.
   * @return a tree hierarchy representing a typed argument list.
   */
  public Node createParameters(JSType... parameterTypes) {
    return createParameters(false, parameterTypes);
  }

  /**
   * Creates a tree hierarchy representing a typed argument list. The last
   * parameter type is considered a variable length argument.
   *
   * @param parameterTypes the parameter types. The last element of this array
   *     is considered a variable length argument.
   * @return a tree hierarchy representing a typed argument list.
   */
  public Node createParametersWithVarArgs(JSType... parameterTypes) {
    return createParameters(true, parameterTypes);
  }

  /**
   * Creates a tree hierarchy representing a typed parameter list in which
   * every parameter is optional.
   */
  public Node createOptionalParameters(JSType... parameterTypes) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[277]++;
    FunctionParamBuilder builder = new FunctionParamBuilder(this);
    builder.addOptionalParams(parameterTypes);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[278]++;
    return builder.build();
  }

  /**
   * Creates a tree hierarchy representing a typed argument list.
   *
   * @param lastVarArgs whether the last type should considered as a variable
   *     length argument.
   * @param parameterTypes the parameter types. The last element of this array
   *     is considered a variable length argument is {@code lastVarArgs} is
   *     {@code true}.
   * @return a tree hierarchy representing a typed argument list
   */
  private Node createParameters(boolean lastVarArgs, JSType... parameterTypes) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[279]++;
    FunctionParamBuilder builder = new FunctionParamBuilder(this);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[280]++;
    int max = parameterTypes.length - 1;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[281]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[28]++;


int CodeCoverConditionCoverageHelper_C26;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i <= max) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[28]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[29]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[30]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[282]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((lastVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i == max) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[45]++;
        builder.addVarArgs(parameterTypes[i]);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[283]++;

      } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[46]++;
        builder.addRequiredParams(parameterTypes[i]);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[284]++;
      }
    }
    return builder.build();
  }

  /**
   * Creates a function type.
   * @param returnType the function's return type
   * @param lastVarArgs whether the last parameter type should be considered as
   * an extensible var_args parameter
   * @param parameterTypes the parameters' types
   */
  public FunctionType createFunctionType(JSType returnType,
      boolean lastVarArgs, JSType... parameterTypes) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[285]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((lastVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[47]++;
      return createFunctionTypeWithVarArgs(returnType, parameterTypes);

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[48]++;
      return createFunctionType(returnType, parameterTypes);
    }
  }

  /**
   * Creates a new function type based on an existing function type but
   * with a new return type.
   * @param existingFunctionType the existing function type.
   * @param returnType the new return type.
   */
  public FunctionType createFunctionTypeWithNewReturnType(
      FunctionType existingFunctionType, JSType returnType) {
    return new FunctionBuilder(this)
        .copyFromOtherFunction(existingFunctionType)
        .withReturnType(returnType)
        .build();
  }

  /**
   * Creates a new function type based on an existing function type but
   * with a new {@code this} type.
   * @param existingFunctionType the existing function type.
   * @param thisType the new this type.
   */
  public FunctionType createFunctionTypeWithNewThisType(
      FunctionType existingFunctionType, ObjectType thisType) {
    return new FunctionBuilder(this)
        .copyFromOtherFunction(existingFunctionType)
        .withTypeOfThis(thisType)
        .build();
  }

  /**
   * @param parameters the function's parameters or {@code null}
   *        to indicate that the parameter types are unknown.
   * @param returnType the function's return type or {@code null} to indicate
   *        that the return type is unknown.
   */
  public FunctionType createFunctionType(
      JSType returnType, Node parameters) {
    return new FunctionBuilder(this)
        .withParamsNode(parameters)
        .withReturnType(returnType)
        .build();
  }

  private FunctionType createNativeFunctionType(
      JSType returnType, Node parameters) {
    return new FunctionBuilder(this)
        .withParamsNode(parameters)
        .withReturnType(returnType)
        .forNativeType()
        .build();
  }

  /**
   * Creates a function type which can act as a constructor.
   * @param returnType the function's return type
   * @param lastVarArgs whether the last parameter type should be considered as
   * an extensible var_args parameter
   * @param parameterTypes the parameters' types
   */
  public FunctionType createConstructorType(JSType returnType,
      boolean lastVarArgs, JSType... parameterTypes) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[286]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((lastVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[49]++;
      return createConstructorTypeWithVarArgs(returnType, parameterTypes);

    } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[50]++;
      return createConstructorType(returnType, parameterTypes);
    }
  }

  /**
   * Create an object type.
   */
  public ObjectType createObjectType(ObjectType implicitPrototype) {
    return createObjectType(null, null, implicitPrototype);
  }

  /**
   * Creates a record type.
   */
  public RecordType createRecordType(Map<String, RecordProperty> properties) {
    return new RecordType(this, properties);
  }

  /**
   * Create an object type.
   */
  public ObjectType createObjectType(String name, Node n,
      ObjectType implicitPrototype) {
    return new PrototypeObjectType(this, name, implicitPrototype);
  }

  /**
   * Create an anonymous object type.
   * @param info Used to mark object literals as structs; can be {@code null}
   */
  public ObjectType createAnonymousObjectType(JSDocInfo info) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[287]++;
    PrototypeObjectType type = new PrototypeObjectType(this, null, null);
    type.setPrettyPrint(true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[288]++;
    type.setJSDocInfo(info);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[289]++;
    return type;
  }

  /**
   * Set the implicit prototype if it's possible to do so.
   * @return True if we were able to set the implicit prototype successfully,
   *     false if it was not possible to do so for some reason. There are
   *     a few different reasons why this could fail: for example, numbers
   *     can't be implicit prototypes, and we don't want to change the implicit
   *     prototype if other classes have already subclassed this one.
   */
  public boolean resetImplicitPrototype(
      JSType type, ObjectType newImplicitProto) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[290]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type instanceof PrototypeObjectType) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[51]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[291]++;
      PrototypeObjectType poType = (PrototypeObjectType) type;
      poType.clearCachedValues();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[292]++;
      poType.setImplicitPrototype(newImplicitProto);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[293]++;
      return true;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[52]++;}
    return false;
  }

  /**
   * Create an anonymous object type for a native type.
   */
  ObjectType createNativeAnonymousObjectType() {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[294]++;
    PrototypeObjectType type =
        new PrototypeObjectType(this, null, null, true, null);
    type.setPrettyPrint(true);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[295]++;
    return type;
  }

  /**
   * Creates a constructor function type.
   * @param name the function's name or {@code null} to indicate that the
   *     function is anonymous.
   * @param source the node defining this function. Its type
   *     ({@link Node#getType()}) must be {@link Token#FUNCTION}.
   * @param parameters the function's parameters or {@code null}
   *     to indicate that the parameter types are unknown.
   * @param returnType the function's return type or {@code null} to indicate
   *     that the return type is unknown.
   * @param templateKeys the templatized type keys for the class.
   */
  public FunctionType createConstructorType(String name, Node source,
      Node parameters, JSType returnType, ImmutableList<String> templateKeys) {
    return new FunctionType(this, name, source,
        createArrowType(parameters, returnType), null,
        createTemplateTypeMap(templateKeys, null), true, false);
  }

  /**
   * Creates an interface function type.
   * @param name the function's name
   * @param source the node defining this function. Its type
   *     ({@link Node#getType()}) must be {@link Token#FUNCTION}.
   */
  public FunctionType createInterfaceType(String name, Node source) {
    return FunctionType.forInterface(this, name, source);
  }

  /**
   * Creates a template type map from the specified list of template keys and
   * template value types.
   */
  public TemplateTypeMap createTemplateTypeMap(
      ImmutableList<String> templateKeys,
      ImmutableList<JSType> templateValues) {
    templateKeys = templateKeys == null ?
        ImmutableList.<String>of() : templateKeys;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[296]++;
    templateValues = templateValues == null ?
        ImmutableList.<JSType>of() : templateValues;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[297]++;

    return (templateKeys.isEmpty() && templateValues.isEmpty()) ?
        emptyTemplateTypeMap :
        new TemplateTypeMap(this, templateKeys, templateValues);
  }

  /**
   * Creates a templatized instance of the specified type.  Only ObjectTypes
   * can currently be templatized; extend the logic in this function when
   * more types can be templatized.
   * @param baseType the type to be templatized.
   * @param templatizedTypes a list of the template JSTypes. Will be matched by
   *     list order to the template keys on the base type.
   */
  public TemplatizedType createTemplatizedType(
      ObjectType baseType, ImmutableList<JSType> templatizedTypes) {
    // Only ObjectTypes can currently be templatized; extend this logic when
    // more types can be templatized.
    return new TemplatizedType(this, baseType, templatizedTypes);
  }

  /**
   * Creates a templatized instance of the specified type.  Only ObjectTypes
   * can currently be templatized; extend the logic in this function when
   * more types can be templatized.
   * @param baseType the type to be templatized.
   * @param templatizedTypes a list of the template JSTypes. Will be matched by
   *     list order to the template keys on the base type.
   */
  public TemplatizedType createTemplatizedType(
      ObjectType baseType, JSType... templatizedTypes) {
    return createTemplatizedType(
        baseType, ImmutableList.copyOf(templatizedTypes));
  }

  /**
   * Creates a named type.
   */
  @VisibleForTesting
  public JSType createNamedType(String reference,
      String sourceName, int lineno, int charno) {
    return new NamedType(this, reference, sourceName, lineno, charno);
  }

  /**
   * Identifies the name of a typedef or enum before we actually declare it.
   */
  public void identifyNonNullableName(String name) {
    Preconditions.checkNotNull(name);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[298]++;
    nonNullableTypeNames.add(name);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[299]++;
  }

  /**
   * Creates a JSType from the nodes representing a type.
   * @param n The node with type info.
   * @param sourceName The source file name.
   * @param scope A scope for doing type name lookups.
   */
  public JSType createFromTypeNodes(Node n, String sourceName,
      StaticScope<JSType> scope) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[300]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((resolveMode == ResolveMode.LAZY_EXPRESSIONS) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[53]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[301]++;
      // If the type expression doesn't contain any names, just
      // resolve it anyway.
      boolean hasNames = hasTypeName(n);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[302]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((hasNames) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[55]++;
        return new UnresolvedTypeExpression(this, n, sourceName);

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[56]++;}

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[54]++;}
    return createFromTypeNodesInternal(n, sourceName, scope);
  }

  private boolean hasTypeName(Node n) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[303]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[57]++;
      return true;

    } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[58]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[304]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[31]++;


int CodeCoverConditionCoverageHelper_C34;

    for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[31]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[32]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[33]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[305]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((hasTypeName(child)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[59]++;
        return true;

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[60]++;}
    }

    return false;
  }

  /** @see #createFromTypeNodes(Node, String, StaticScope) */
  private JSType createFromTypeNodesInternal(Node n, String sourceName,
      StaticScope<JSType> scope) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[306]++;
    switch (n.getType()) {
      case Token.LC:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[61]++; // Record type.
        return createRecordTypeFromNodes(
            n.getFirstChild(), sourceName, scope);

      case Token.BANG:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[62]++; // Not nullable
        return createFromTypeNodesInternal(
            n.getFirstChild(), sourceName, scope)
            .restrictByNotNullOrUndefined();

      case Token.QMARK:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[63]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[307]++; // Nullable or unknown
        Node firstChild = n.getFirstChild();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[308]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((firstChild == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[64]++;
          return getNativeType(UNKNOWN_TYPE);

        } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[65]++;}
        return createDefaultObjectUnion(
            createFromTypeNodesInternal(
                firstChild, sourceName, scope));

      case Token.EQUALS:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[66]++; // Optional
        return createOptionalType(
            createFromTypeNodesInternal(
                n.getFirstChild(), sourceName, scope));

      case Token.ELLIPSIS:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[67]++; // Var args
        return createOptionalType(
            createFromTypeNodesInternal(
                n.getFirstChild(), sourceName, scope));

      case Token.STAR:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[68]++; // The AllType
        return getNativeType(ALL_TYPE);

      case Token.LB:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[69]++; // Array type
        // TODO(nicksantos): Enforce membership restrictions on the Array.
        return getNativeType(ARRAY_TYPE);

      case Token.PIPE:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[70]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[309]++; // Union type
        UnionTypeBuilder builder = new UnionTypeBuilder(this);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[310]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[34]++;


int CodeCoverConditionCoverageHelper_C37;
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false);
             child = child.getNext()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[34]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[35]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[36]++;
}
          builder.addAlternate(
              createFromTypeNodesInternal(child, sourceName, scope));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[311]++;
        }
        return builder.build();

      case Token.EMPTY:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[71]++; // When the return value of a function is not specified
        return getNativeType(UNKNOWN_TYPE);

      case Token.VOID:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[72]++; // Only allowed in the return value of a function.
        return getNativeType(VOID_TYPE);

      case Token.STRING:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[73]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[312]++;
        JSType namedType = getType(scope, n.getString(), sourceName,
            n.getLineno(), n.getCharno());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[313]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((resolveMode != ResolveMode.LAZY_NAMES) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[74]++;
          namedType = namedType.resolveInternal(reporter, scope);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[314]++;

        } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[75]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[315]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((namedType instanceof ObjectType) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((nonNullableTypeNames.contains(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[76]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[316]++;
          Node typeList = n.getFirstChild();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[317]++;
          int nAllowedTypes =
              namedType.getTemplateTypeMap().numUnfilledTemplateKeys();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[318]++;
int CodeCoverConditionCoverageHelper_C40;
          if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((typeList != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((nAllowedTypes > 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[78]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[319]++;
            // Templatized types.
            ImmutableList.Builder<JSType> templateTypes =
                ImmutableList.builder();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[320]++;
int CodeCoverConditionCoverageHelper_C41;

            // Special case for Object, where Object.<X> implies Object.<?,X>.
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((n.getString().equals("Object")) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((typeList.getFirstChild() == typeList.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[80]++;
              templateTypes.add(getNativeType(UNKNOWN_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[321]++;

            } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[81]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[322]++;

            int templateNodeIndex = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[323]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[37]++;


            for (Node templateNode : typeList.getFirstChild().siblings()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[37]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[38]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[39]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[324]++;
int CodeCoverConditionCoverageHelper_C42;
              // Don't parse more templatized type nodes than the type can
              // accommodate. This is because some existing clients have
              // template annotations on non-templatized classes, for instance:
              //   goog.structs.Set.<SomeType>
              // The problem in these cases is that the previously-unparsed
              // SomeType is not actually a valid type. To prevent these clients
              // from seeing unknown type errors, we explicitly don't parse
              // these types.
              // TODO(user): Address this issue by removing bad template
              // annotations on non-templatized classes.
              if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((++templateNodeIndex > nAllowedTypes) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[82]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[325]++;
                break;

              } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[83]++;}
              templateTypes.add(createFromTypeNodesInternal(
                  templateNode, sourceName, scope));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[326]++;
            }
            namedType = createTemplatizedType(
                (ObjectType) namedType, templateTypes.build());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[327]++;
            Preconditions.checkNotNull(namedType);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[328]++;

          } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[79]++;}
          return createDefaultObjectUnion(namedType);

        } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[77]++;
          return namedType;
        }

      case Token.FUNCTION:
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[84]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[329]++;
        ObjectType thisType = null;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[330]++;
        boolean isConstructor = false;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[331]++;
        Node current = n.getFirstChild();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[332]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((current.getType() == Token.THIS) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((current.getType() == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[85]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[333]++;
          Node contextNode = current.getFirstChild();
          thisType =
              ObjectType.cast(
                  createFromTypeNodesInternal(
                      contextNode, sourceName, scope)
                  .restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[334]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[335]++;
int CodeCoverConditionCoverageHelper_C44;
          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((thisType == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[87]++;
            reporter.warning(
                ScriptRuntime.getMessage0(
                    current.getType() == Token.THIS ?
                    "msg.jsdoc.function.thisnotobject" :
                    "msg.jsdoc.function.newnotobject"),
                sourceName,
                contextNode.getLineno(), contextNode.getCharno());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[336]++;

          } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[88]++;}

          isConstructor = current.getType() == Token.NEW;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[337]++;
          current = current.getNext();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[338]++;

        } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[86]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[339]++;

        FunctionParamBuilder paramBuilder = new FunctionParamBuilder(this);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[340]++;
int CodeCoverConditionCoverageHelper_C45;

        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((current.getType() == Token.PARAM_LIST) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[89]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[341]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[40]++;


int CodeCoverConditionCoverageHelper_C46;
          for (Node arg = current.getFirstChild();(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false);
               arg = arg.getNext()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[40]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[41]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[42]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[342]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((arg.getType() == Token.ELLIPSIS) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[91]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[343]++;
int CodeCoverConditionCoverageHelper_C48;
              if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((arg.getChildCount() == 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[93]++;
                paramBuilder.addVarArgs(getNativeType(UNKNOWN_TYPE));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[344]++;

              } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[94]++;
                paramBuilder.addVarArgs(
                    createFromTypeNodesInternal(
                        arg.getFirstChild(), sourceName, scope));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[345]++;
              }

            } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[92]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[346]++;
              JSType type = createFromTypeNodesInternal(
                  arg, sourceName, scope);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[347]++;
int CodeCoverConditionCoverageHelper_C49;
              if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((arg.getType() == Token.EQUALS) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[95]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[348]++;
                boolean addSuccess = paramBuilder.addOptionalParams(type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[349]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((addSuccess) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[97]++;
                  reporter.warning(
                      ScriptRuntime.getMessage0("msg.jsdoc.function.varargs"),
                      sourceName, arg.getLineno(), arg.getCharno());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[350]++;

                } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[98]++;}

              } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[96]++;
                paramBuilder.addRequiredParams(type);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[351]++;
              }
            }
          }
          current = current.getNext();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[352]++;

        } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[90]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[353]++;

        JSType returnType =
            createFromTypeNodesInternal(current, sourceName, scope);

        return new FunctionBuilder(this)
            .withParams(paramBuilder)
            .withReturnType(returnType)
            .withTypeOfThis(thisType)
            .setIsConstructor(isConstructor)
            .build(); default : CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[99]++;
    }

    throw new IllegalStateException(
        "Unexpected node in type expression: " + n.toString());
  }

  /**
   * Creates a RecordType from the nodes representing said record type.
   * @param n The node with type info.
   * @param sourceName The source file name.
   * @param scope A scope for doing type name lookups.
   */
  private JSType createRecordTypeFromNodes(Node n, String sourceName,
      StaticScope<JSType> scope) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[354]++;

    RecordTypeBuilder builder = new RecordTypeBuilder(this);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[355]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[43]++;


int CodeCoverConditionCoverageHelper_C51;

    // For each of the fields in the record type.
    for (Node fieldTypeNode = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((fieldTypeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false);
         fieldTypeNode = fieldTypeNode.getNext()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[43]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[44]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[45]++;
}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[356]++;

      // Get the property's name.
      Node fieldNameNode = fieldTypeNode;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[357]++;
      boolean hasType = false;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[358]++;
int CodeCoverConditionCoverageHelper_C52;

      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((fieldTypeNode.getType() == Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[100]++;
        fieldNameNode = fieldTypeNode.getFirstChild();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[359]++;
        hasType = true;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[360]++;

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[101]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[361]++;

      String fieldName = fieldNameNode.getString();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[362]++;
int CodeCoverConditionCoverageHelper_C53;

      // TODO(user): Move this into the lexer/parser.
      // Remove the string literal characters around a field name,
      // if any.
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((fieldName.startsWith("'")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((fieldName.startsWith("\"")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[102]++;
        fieldName = fieldName.substring(1, fieldName.length() - 1);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[363]++;

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[103]++;}
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[364]++;

      // Get the property's type.
      JSType fieldType = null;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[365]++;
int CodeCoverConditionCoverageHelper_C54;

      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((hasType) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[104]++;
        // We have a declared type.
        fieldType = createFromTypeNodesInternal(
            fieldTypeNode.getLastChild(), sourceName, scope);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[366]++;

      } else {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[105]++;
        // Otherwise, the type is UNKNOWN.
        fieldType = getNativeType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[367]++;
      }
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[368]++;
int CodeCoverConditionCoverageHelper_C55;

      // Add the property to the record.
      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((builder.addProperty(fieldName, fieldType, fieldNameNode) == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[106]++;
        // Duplicate field name, warning and skip
        reporter.warning(
            "Duplicate record field " + fieldName,
            sourceName,
            n.getLineno(), fieldNameNode.getCharno());
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[369]++;

      } else {
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.branches[107]++;}
    }

    return builder.build();
  }

  /**
   * Sets the template type name.
   */
  public void setTemplateTypeNames(List<String> names) {
    Preconditions.checkNotNull(names);
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[370]++;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[371]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[46]++;


    for (String name : names) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[46]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[47]--;
  CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.loops[48]++;
}
      templateTypes.put(name, new TemplateType(this, name));
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[372]++;
    }
  }

  /**
   * Clears the template type name.
   */
  public void clearTemplateTypeNames() {
    templateTypes.clear();
CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl.statements[373]++;
  }
}

class CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl ());
  }
    public static long[] statements = new long[374];
    public static long[] branches = new long[108];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[56];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.JSTypeRegistry.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,2,1,1,1,1,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 55; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[49];

  public CodeCoverCoverageCounter$17yax3q7yttnhciw7p8pbsftky3srl () {
    super("com.google.javascript.rhino.jstype.JSTypeRegistry.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 373; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 107; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 48; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.JSTypeRegistry.java");
      for (int i = 1; i <= 373; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 107; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 16; i++) {
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

