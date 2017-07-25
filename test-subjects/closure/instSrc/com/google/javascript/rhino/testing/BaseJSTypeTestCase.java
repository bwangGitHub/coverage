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

package com.google.javascript.rhino.testing;

import com.google.common.collect.ImmutableList;
import com.google.javascript.rhino.JSTypeExpression;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionBuilder;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.RecordTypeBuilder;
import com.google.javascript.rhino.jstype.TemplatizedType;

import junit.framework.TestCase;

public abstract class BaseJSTypeTestCase extends TestCase {
  static {
    CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.ping();
  }

  protected JSTypeRegistry registry;
  protected TestErrorReporter errorReporter;

  protected JSType ALL_TYPE;
  protected ObjectType NO_OBJECT_TYPE;
  protected ObjectType NO_TYPE;
  protected ObjectType NO_RESOLVED_TYPE;
  protected FunctionType ARRAY_FUNCTION_TYPE;
  protected ObjectType ARRAY_TYPE;
  protected JSType BOOLEAN_OBJECT_FUNCTION_TYPE;
  protected ObjectType BOOLEAN_OBJECT_TYPE;
  protected JSType BOOLEAN_TYPE;
  protected ObjectType CHECKED_UNKNOWN_TYPE;
  protected JSType DATE_FUNCTION_TYPE;
  protected ObjectType DATE_TYPE;
  protected JSType ERROR_FUNCTION_TYPE;
  protected ObjectType ERROR_TYPE;
  protected JSType EVAL_ERROR_FUNCTION_TYPE;
  protected ObjectType EVAL_ERROR_TYPE;
  protected FunctionType FUNCTION_FUNCTION_TYPE;
  protected FunctionType FUNCTION_INSTANCE_TYPE;
  protected ObjectType FUNCTION_PROTOTYPE;
  protected JSType GREATEST_FUNCTION_TYPE;
  protected JSType LEAST_FUNCTION_TYPE;
  protected JSType MATH_TYPE;
  protected JSType NULL_TYPE;
  protected JSType NUMBER_OBJECT_FUNCTION_TYPE;
  protected ObjectType NUMBER_OBJECT_TYPE;
  protected JSType NUMBER_STRING_BOOLEAN;
  protected JSType NUMBER_TYPE;
  protected FunctionType OBJECT_FUNCTION_TYPE;
  protected JSType NULL_VOID;
  protected JSType OBJECT_NUMBER_STRING;
  protected JSType OBJECT_NUMBER_STRING_BOOLEAN;
  protected JSType OBJECT_PROTOTYPE;
  protected ObjectType OBJECT_TYPE;
  protected JSType RANGE_ERROR_FUNCTION_TYPE;
  protected ObjectType RANGE_ERROR_TYPE;
  protected JSType REFERENCE_ERROR_FUNCTION_TYPE;
  protected ObjectType REFERENCE_ERROR_TYPE;
  protected JSType REGEXP_FUNCTION_TYPE;
  protected ObjectType REGEXP_TYPE;
  protected JSType STRING_OBJECT_FUNCTION_TYPE;
  protected ObjectType STRING_OBJECT_TYPE;
  protected JSType STRING_TYPE;
  protected JSType SYNTAX_ERROR_FUNCTION_TYPE;
  protected ObjectType SYNTAX_ERROR_TYPE;
  protected JSType TYPE_ERROR_FUNCTION_TYPE;
  protected ObjectType TYPE_ERROR_TYPE;
  protected FunctionType U2U_CONSTRUCTOR_TYPE;
  protected FunctionType U2U_FUNCTION_TYPE;
  protected ObjectType UNKNOWN_TYPE;
  protected JSType URI_ERROR_FUNCTION_TYPE;
  protected ObjectType URI_ERROR_TYPE;
  protected JSType VOID_TYPE;

  protected int NATIVE_PROPERTIES_COUNT;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[1]++;
    errorReporter = new TestErrorReporter(null, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[2]++;
    registry = new JSTypeRegistry(errorReporter);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[3]++;
    initTypes();
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[4]++;
  }

  protected void initTypes() {
    ALL_TYPE =
        registry.getNativeType(JSTypeNative.ALL_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[5]++;
    NO_OBJECT_TYPE =
        registry.getNativeObjectType(JSTypeNative.NO_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[6]++;
    NO_TYPE =
        registry.getNativeObjectType(JSTypeNative.NO_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[7]++;
    NO_RESOLVED_TYPE =
        registry.getNativeObjectType(JSTypeNative.NO_RESOLVED_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[8]++;
    ARRAY_FUNCTION_TYPE =
        registry.getNativeFunctionType(JSTypeNative.ARRAY_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[9]++;
    ARRAY_TYPE =
        registry.getNativeObjectType(JSTypeNative.ARRAY_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[10]++;
    BOOLEAN_OBJECT_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.BOOLEAN_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[11]++;
    BOOLEAN_OBJECT_TYPE =
        registry.getNativeObjectType(JSTypeNative.BOOLEAN_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[12]++;
    BOOLEAN_TYPE =
        registry.getNativeType(JSTypeNative.BOOLEAN_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[13]++;
    CHECKED_UNKNOWN_TYPE =
        registry.getNativeObjectType(JSTypeNative.CHECKED_UNKNOWN_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[14]++;
    DATE_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.DATE_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[15]++;
    DATE_TYPE =
        registry.getNativeObjectType(JSTypeNative.DATE_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[16]++;
    ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[17]++;
    ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[18]++;
    EVAL_ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.EVAL_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[19]++;
    EVAL_ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.EVAL_ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[20]++;
    FUNCTION_FUNCTION_TYPE =
        registry.getNativeFunctionType(JSTypeNative.FUNCTION_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[21]++;
    FUNCTION_INSTANCE_TYPE =
        registry.getNativeFunctionType(JSTypeNative.FUNCTION_INSTANCE_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[22]++;
    FUNCTION_PROTOTYPE =
        registry.getNativeObjectType(JSTypeNative.FUNCTION_PROTOTYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[23]++;
    GREATEST_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.GREATEST_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[24]++;
    LEAST_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.LEAST_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[25]++;
    NULL_TYPE =
        registry.getNativeType(JSTypeNative.NULL_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[26]++;
    NUMBER_OBJECT_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.NUMBER_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[27]++;
    NUMBER_OBJECT_TYPE =
        registry.getNativeObjectType(JSTypeNative.NUMBER_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[28]++;
    NUMBER_STRING_BOOLEAN =
        registry.getNativeType(JSTypeNative.NUMBER_STRING_BOOLEAN);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[29]++;
    NUMBER_TYPE =
        registry.getNativeType(JSTypeNative.NUMBER_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[30]++;
    OBJECT_FUNCTION_TYPE =
        registry.getNativeFunctionType(JSTypeNative.OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[31]++;
    NULL_VOID =
        registry.getNativeType(JSTypeNative.NULL_VOID);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[32]++;
    OBJECT_NUMBER_STRING =
        registry.getNativeType(JSTypeNative.OBJECT_NUMBER_STRING);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[33]++;
    OBJECT_NUMBER_STRING_BOOLEAN =
        registry.getNativeType(JSTypeNative.OBJECT_NUMBER_STRING_BOOLEAN);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[34]++;
    OBJECT_PROTOTYPE =
        registry.getNativeType(JSTypeNative.OBJECT_PROTOTYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[35]++;
    OBJECT_TYPE =
        registry.getNativeObjectType(JSTypeNative.OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[36]++;
    RANGE_ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.RANGE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[37]++;
    RANGE_ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.RANGE_ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[38]++;
    REFERENCE_ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.REFERENCE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[39]++;
    REFERENCE_ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.REFERENCE_ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[40]++;
    REGEXP_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.REGEXP_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[41]++;
    REGEXP_TYPE =
        registry.getNativeObjectType(JSTypeNative.REGEXP_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[42]++;
    STRING_OBJECT_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.STRING_OBJECT_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[43]++;
    STRING_OBJECT_TYPE =
        registry.getNativeObjectType(JSTypeNative.STRING_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[44]++;
    STRING_TYPE =
        registry.getNativeType(JSTypeNative.STRING_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[45]++;
    SYNTAX_ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.SYNTAX_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[46]++;
    SYNTAX_ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.SYNTAX_ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[47]++;
    TYPE_ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.TYPE_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[48]++;
    TYPE_ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.TYPE_ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[49]++;
    U2U_CONSTRUCTOR_TYPE =
        registry.getNativeFunctionType(JSTypeNative.U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[50]++;
    U2U_FUNCTION_TYPE =
        registry.getNativeFunctionType(JSTypeNative.U2U_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[51]++;
    UNKNOWN_TYPE =
        registry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[52]++;
    URI_ERROR_FUNCTION_TYPE =
        registry.getNativeType(JSTypeNative.URI_ERROR_FUNCTION_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[53]++;
    URI_ERROR_TYPE =
        registry.getNativeObjectType(JSTypeNative.URI_ERROR_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[54]++;
    VOID_TYPE =
        registry.getNativeType(JSTypeNative.VOID_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[55]++;

    addNativeProperties(registry);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[56]++;

    NATIVE_PROPERTIES_COUNT = OBJECT_TYPE.getPropertiesCount();
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[57]++;
  }

  /** Adds a basic set of properties to the native types. */
  public static void addNativeProperties(JSTypeRegistry registry) {
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[58]++;
    JSType booleanType = registry.getNativeType(JSTypeNative.BOOLEAN_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[59]++;
    JSType numberType = registry.getNativeType(JSTypeNative.NUMBER_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[60]++;
    JSType stringType = registry.getNativeType(JSTypeNative.STRING_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[61]++;
    JSType unknownType = registry.getNativeType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[62]++;

    ObjectType objectType =
        registry.getNativeObjectType(JSTypeNative.OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[63]++;
    ObjectType arrayType =
        registry.getNativeObjectType(JSTypeNative.ARRAY_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[64]++;
    ObjectType dateType =
        registry.getNativeObjectType(JSTypeNative.DATE_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[65]++;
    ObjectType regexpType =
        registry.getNativeObjectType(JSTypeNative.REGEXP_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[66]++;
    ObjectType booleanObjectType =
        registry.getNativeObjectType(JSTypeNative.BOOLEAN_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[67]++;
    ObjectType numberObjectType =
        registry.getNativeObjectType(JSTypeNative.NUMBER_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[68]++;
    ObjectType stringObjectType =
        registry.getNativeObjectType(JSTypeNative.STRING_OBJECT_TYPE);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[69]++;

    ObjectType objectPrototype = registry
        .getNativeFunctionType(JSTypeNative.OBJECT_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, objectPrototype, "constructor", objectType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[70]++;
    addMethod(registry, objectPrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[71]++;
    addMethod(registry, objectPrototype, "toLocaleString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[72]++;
    addMethod(registry, objectPrototype, "valueOf", unknownType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[73]++;
    addMethod(registry, objectPrototype, "hasOwnProperty", booleanType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[74]++;
    addMethod(registry, objectPrototype, "isPrototypeOf", booleanType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[75]++;
    addMethod(registry, objectPrototype, "propertyIsEnumerable", booleanType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[76]++;
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[77]++;

    ObjectType arrayPrototype = registry
        .getNativeFunctionType(JSTypeNative.ARRAY_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, arrayPrototype, "constructor", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[78]++;
    addMethod(registry, arrayPrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[79]++;
    addMethod(registry, arrayPrototype, "toLocaleString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[80]++;
    addMethod(registry, arrayPrototype, "concat", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[81]++;
    addMethod(registry, arrayPrototype, "join", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[82]++;
    addMethod(registry, arrayPrototype, "pop", unknownType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[83]++;
    addMethod(registry, arrayPrototype, "push", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[84]++;
    addMethod(registry, arrayPrototype, "reverse", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[85]++;
    addMethod(registry, arrayPrototype, "shift", unknownType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[86]++;
    addMethod(registry, arrayPrototype, "slice", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[87]++;
    addMethod(registry, arrayPrototype, "sort", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[88]++;
    addMethod(registry, arrayPrototype, "splice", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[89]++;
    addMethod(registry, arrayPrototype, "unshift", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[90]++;
    arrayType.defineDeclaredProperty("length", numberType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[91]++;
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[92]++;

    ObjectType booleanPrototype = registry
        .getNativeFunctionType(JSTypeNative.BOOLEAN_OBJECT_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, booleanPrototype, "constructor", booleanObjectType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[93]++;
    addMethod(registry, booleanPrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[94]++;
    addMethod(registry, booleanPrototype, "valueOf", booleanType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[95]++;
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[96]++;

    ObjectType datePrototype = registry
        .getNativeFunctionType(JSTypeNative.DATE_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, datePrototype, "constructor", dateType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[97]++;
    addMethod(registry, datePrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[98]++;
    addMethod(registry, datePrototype, "toDateString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[99]++;
    addMethod(registry, datePrototype, "toTimeString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[100]++;
    addMethod(registry, datePrototype, "toLocaleString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[101]++;
    addMethod(registry, datePrototype, "toLocaleDateString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[102]++;
    addMethod(registry, datePrototype, "toLocaleTimeString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[103]++;
    addMethod(registry, datePrototype, "valueOf", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[104]++;
    addMethod(registry, datePrototype, "getTime", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[105]++;
    addMethod(registry, datePrototype, "getFullYear", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[106]++;
    addMethod(registry, datePrototype, "getUTCFullYear", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[107]++;
    addMethod(registry, datePrototype, "getMonth", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[108]++;
    addMethod(registry, datePrototype, "getUTCMonth", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[109]++;
    addMethod(registry, datePrototype, "getDate", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[110]++;
    addMethod(registry, datePrototype, "getUTCDate", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[111]++;
    addMethod(registry, datePrototype, "getDay", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[112]++;
    addMethod(registry, datePrototype, "getUTCDay", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[113]++;
    addMethod(registry, datePrototype, "getHours", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[114]++;
    addMethod(registry, datePrototype, "getUTCHours", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[115]++;
    addMethod(registry, datePrototype, "getMinutes", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[116]++;
    addMethod(registry, datePrototype, "getUTCMinutes", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[117]++;
    addMethod(registry, datePrototype, "getSeconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[118]++;
    addMethod(registry, datePrototype, "getUTCSeconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[119]++;
    addMethod(registry, datePrototype, "getMilliseconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[120]++;
    addMethod(registry, datePrototype, "getUTCMilliseconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[121]++;
    addMethod(registry, datePrototype, "getTimezoneOffset", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[122]++;
    addMethod(registry, datePrototype, "setTime", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[123]++;
    addMethod(registry, datePrototype, "setMilliseconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[124]++;
    addMethod(registry, datePrototype, "setUTCMilliseconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[125]++;
    addMethod(registry, datePrototype, "setSeconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[126]++;
    addMethod(registry, datePrototype, "setUTCSeconds", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[127]++;
    addMethod(registry, datePrototype, "setMinutes", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[128]++;
    addMethod(registry, datePrototype, "setUTCMinutes", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[129]++;
    addMethod(registry, datePrototype, "setHours", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[130]++;
    addMethod(registry, datePrototype, "setUTCHours", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[131]++;
    addMethod(registry, datePrototype, "setDate", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[132]++;
    addMethod(registry, datePrototype, "setUTCDate", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[133]++;
    addMethod(registry, datePrototype, "setMonth", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[134]++;
    addMethod(registry, datePrototype, "setUTCMonth", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[135]++;
    addMethod(registry, datePrototype, "setFullYear", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[136]++;
    addMethod(registry, datePrototype, "setUTCFullYear", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[137]++;
    addMethod(registry, datePrototype, "toUTCString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[138]++;
    addMethod(registry, datePrototype, "toGMTString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[139]++;
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[140]++;

    ObjectType numberPrototype = registry
        .getNativeFunctionType(JSTypeNative.NUMBER_OBJECT_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, numberPrototype, "constructor", numberObjectType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[141]++;
    addMethod(registry, numberPrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[142]++;
    addMethod(registry, numberPrototype, "toLocaleString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[143]++;
    addMethod(registry, numberPrototype, "valueOf", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[144]++;
    addMethod(registry, numberPrototype, "toFixed", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[145]++;
    addMethod(registry, numberPrototype, "toExponential", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[146]++;
    addMethod(registry, numberPrototype, "toPrecision", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[147]++;
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[148]++;

    ObjectType regexpPrototype = registry
        .getNativeFunctionType(JSTypeNative.REGEXP_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, regexpPrototype, "constructor", regexpType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[149]++;
    addMethod(registry, regexpPrototype, "exec",
        registry.createNullableType(arrayType));
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[150]++;
    addMethod(registry, regexpPrototype, "test", booleanType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[151]++;
    addMethod(registry, regexpPrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[152]++;
    regexpType.defineDeclaredProperty("source", stringType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[153]++;
    regexpType.defineDeclaredProperty("global", booleanType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[154]++;
    regexpType.defineDeclaredProperty("ignoreCase", booleanType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[155]++;
    regexpType.defineDeclaredProperty("multiline", booleanType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[156]++;
    regexpType.defineDeclaredProperty("lastIndex", numberType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[157]++;
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[158]++;

    ObjectType stringPrototype = registry
        .getNativeFunctionType(JSTypeNative.STRING_OBJECT_FUNCTION_TYPE)
        .getPrototype();
    addMethod(registry, stringPrototype, "constructor", stringObjectType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[159]++;
    addMethod(registry, stringPrototype, "toString", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[160]++;
    addMethod(registry, stringPrototype, "valueOf", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[161]++;
    addMethod(registry, stringPrototype, "charAt", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[162]++;
    addMethod(registry, stringPrototype, "charCodeAt", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[163]++;
    addMethod(registry, stringPrototype, "concat", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[164]++;
    addMethod(registry, stringPrototype, "indexOf", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[165]++;
    addMethod(registry, stringPrototype, "lastIndexOf", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[166]++;
    addMethod(registry, stringPrototype, "localeCompare", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[167]++;
    addMethod(registry, stringPrototype, "match",
        registry.createNullableType(arrayType));
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[168]++;
    addMethod(registry, stringPrototype, "replace", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[169]++;
    addMethod(registry, stringPrototype, "search", numberType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[170]++;
    addMethod(registry, stringPrototype, "slice", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[171]++;
    addMethod(registry, stringPrototype, "split", arrayType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[172]++;
    addMethod(registry, stringPrototype, "substring", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[173]++;
    addMethod(registry, stringPrototype, "toLowerCase", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[174]++;
    addMethod(registry, stringPrototype, "toLocaleLowerCase", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[175]++;
    addMethod(registry, stringPrototype, "toUpperCase", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[176]++;
    addMethod(registry, stringPrototype, "toLocaleUpperCase", stringType);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[177]++;
    stringObjectType.defineDeclaredProperty("length", numberType, null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[178]++;
  }

  private static void addMethod(
      JSTypeRegistry registry, ObjectType receivingType, String methodName,
      JSType returnType) {
    receivingType.defineDeclaredProperty(methodName,
        new FunctionBuilder(registry).withReturnType(returnType).build(),
        null);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[179]++;
  }

  protected JSType createUnionType(JSType... variants) {
    return registry.createUnionType(variants);
  }

  protected RecordTypeBuilder createRecordTypeBuilder() {
    return new RecordTypeBuilder(registry);
  }

  protected JSType createNullableType(JSType type) {
    return registry.createNullableType(type);
  }

  protected JSType createOptionalType(JSType type) {
    return registry.createOptionalType(type);
  }

  protected TemplatizedType createTemplatizedType(
      ObjectType baseType, ImmutableList<JSType> templatizedTypes) {
    return registry.createTemplatizedType(baseType, templatizedTypes);
  }

  protected TemplatizedType createTemplatizedType(
      ObjectType baseType, JSType... templatizedType) {
    return createTemplatizedType(
        baseType, ImmutableList.copyOf(templatizedType));
  }

  /**
   * Asserts that a Node representing a type expression resolves to the
   * correct {@code JSType}.
   */
  protected void assertTypeEquals(JSType expected, Node actual) {
    assertTypeEquals(expected, new JSTypeExpression(actual, ""));
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[180]++;
  }

  /**
   * Asserts that a a type expression resolves to the correct {@code JSType}.
   */
  protected void assertTypeEquals(JSType expected, JSTypeExpression actual) {
    assertEquals(expected, resolve(actual));
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[181]++;
  }

  /**
   * Resolves a type expression, expecting the given warnings.
   */
  protected JSType resolve(JSTypeExpression n, String... warnings) {
    errorReporter.setWarnings(warnings);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[182]++;
    return n.evaluate(null, registry);
  }

  /**
   * A definition of all extern types. This should be kept in sync with
   * javascript/externs/es3.js. This is used to check that the built-in types
   * declared in {@link JSTypeRegistry} have the same type as that in the
   * externs. It can also be used for any tests that want to use built-in types
   * in their externs.
   */
  public static final String ALL_NATIVE_EXTERN_TYPES =
      "/**\n"
      + " * @constructor\n"
      + " * @param {*=} opt_value\n"
      + " */\n"
      + "function Object(opt_value) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Object}\n"
      + " * @param {...*} var_args\n"
      + " */\n"
      + "\n"
      + "function Function(var_args) {}\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Object}\n"
      + " * @param {...*} var_args\n"
      + " * @return {!Array}\n"
      + " */\n"
      + "function Array(var_args) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @param {*=} opt_value\n"
      + " * @return {boolean}\n"
      + " */\n"
      + "function Boolean(opt_value) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @param {*=} opt_value\n"
      + " * @return {number}\n"
      + " */\n"
      + "function Number(opt_value) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @param {?=} opt_yr_num\n"
      + " * @param {?=} opt_mo_num\n"
      + " * @param {?=} opt_day_num\n"
      + " * @param {?=} opt_hr_num\n"
      + " * @param {?=} opt_min_num\n"
      + " * @param {?=} opt_sec_num\n"
      + " * @param {?=} opt_ms_num\n"
      + " * @return {string}\n"
      + " */\n"
      + "function Date(opt_yr_num, opt_mo_num, opt_day_num, opt_hr_num,"
      + "    opt_min_num, opt_sec_num, opt_ms_num) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Object}\n"
      + " * @param {*=} opt_str\n"
      + " * @return {string}\n"
      + " */\n"
      + "function String(opt_str) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @param {*=} opt_pattern\n"
      + " * @param {*=} opt_flags\n"
      + " * @return {!RegExp}\n"
      + " */\n"
      + "function RegExp(opt_pattern, opt_flags) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!Error}\n"
      + " */\n"
      + "function Error(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Error}\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!EvalError}\n"
      + " */\n"
      + "function EvalError(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Error}\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!RangeError}\n"
      + " */\n"
      + "function RangeError(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Error}\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!ReferenceError}\n"
      + " */\n"
      + "function ReferenceError(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Error}\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!SyntaxError}\n"
      + " */\n"
      + "function SyntaxError(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Error}\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!TypeError}\n"
      + " */\n"
      + "function TypeError(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @constructor\n"
      + " * @extends {Error}\n"
      + " * @param {*=} opt_message\n"
      + " * @param {*=} opt_file\n"
      + " * @param {*=} opt_line\n"
      + " * @return {!URIError}\n"
      + " */\n"
      + "function URIError(opt_message, opt_file, opt_line) {}\n"
      + "\n"
      + "/**\n"
      + " * @param {string} progId\n"
      + " * @param {string=} opt_location\n"
      + " * @constructor\n"
      + " */\n"
      + "function ActiveXObject(progId, opt_location) {}\n";
  static {
    CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[183]++;
  }

  protected final void assertTypeEquals(JSType a, JSType b) {
    Asserts.assertTypeEquals(a, b);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[184]++;
  }

  protected final void assertTypeEquals(String msg, JSType a, JSType b) {
    Asserts.assertTypeEquals(msg, a, b);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[185]++;
  }

  protected final void assertTypeNotEquals(JSType a, JSType b) {
    Asserts.assertTypeNotEquals(a, b);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[186]++;
  }

  protected final void assertTypeNotEquals(String msg, JSType a, JSType b) {
    Asserts.assertTypeNotEquals(msg, a, b);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[187]++;
  }

public BaseJSTypeTestCase (String name) {super(name);
CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl.statements[188]++;}
}

class CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl ());
  }
    public static long[] statements = new long[189];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$25gaj0uio6jh343k43fyca0gyexl9dszzqzl () {
    super("com.google.javascript.rhino.testing.BaseJSTypeTestCase.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 188; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.testing.BaseJSTypeTestCase.java");
      for (int i = 1; i <= 188; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

