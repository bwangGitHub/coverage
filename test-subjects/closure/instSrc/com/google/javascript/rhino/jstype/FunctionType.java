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

import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.U2U_CONSTRUCTOR_TYPE;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This derived type provides extended information about a function, including
 * its return type and argument types.<p>
 *
 * Note: the parameters list is the LP node that is the parent of the
 * actual NAME node containing the parsed argument list (annotated with
 * JSDOC_TYPE_PROP's for the compile-time type of each argument.
 */
public class FunctionType extends PrototypeObjectType {
  static {
    CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[1]++;
  }

  private enum Kind {
    ORDINARY,
    CONSTRUCTOR,
    INTERFACE
  }

  // relevant only for constructors
  private enum PropAccess { ANY, STRUCT, DICT }

  /**
   * {@code [[Call]]} property.
   */
  private ArrowType call;

  /**
   * The {@code prototype} property. This field is lazily initialized by
   * {@code #getPrototype()}. The most important reason for lazily
   * initializing this field is that there are cycles in the native types
   * graph, so some prototypes must temporarily be {@code null} during
   * the construction of the graph.
   *
   * If non-null, the type must be a PrototypeObjectType.
   */
  private Property prototypeSlot;

  /**
   * Whether a function is a constructor, an interface, or just an ordinary
   * function.
   */
  private final Kind kind;

  /**
   * Whether the instances are structs, dicts, or unrestricted.
   */
  private PropAccess propAccess;

  /**
   * The type of {@code this} in the scope of this function.
   */
  private JSType typeOfThis;

  /**
   * The function node which this type represents. It may be {@code null}.
   */
  private Node source;

  /**
   * The interfaces directly implemented by this function (for constructors)
   * It is only relevant for constructors. May not be {@code null}.
   */
  private List<ObjectType> implementedInterfaces = ImmutableList.of();
  {
    CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[2]++;
  }

  /**
   * The interfaces directly extended by this function (for interfaces)
   * It is only relevant for constructors. May not be {@code null}.
   */
  private List<ObjectType> extendedInterfaces = ImmutableList.of();
  {
    CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[3]++;
  }

  /**
   * The types which are subtypes of this function. It is only relevant for
   * constructors and may be {@code null}.
   */
  private List<FunctionType> subTypes;

  /** Creates an instance for a function that might be a constructor. */
  FunctionType(JSTypeRegistry registry, String name, Node source,
               ArrowType arrowType, JSType typeOfThis,
               TemplateTypeMap templateTypeMap,
               boolean isConstructor, boolean nativeType) {
    super(registry, name,
        registry.getNativeObjectType(JSTypeNative.FUNCTION_INSTANCE_TYPE),
        nativeType, templateTypeMap);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[4]++;
    setPrettyPrint(true);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[5]++;

    Preconditions.checkArgument(source == null ||
        Token.FUNCTION == source.getType());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[6]++;
    Preconditions.checkNotNull(arrowType);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[7]++;
    this.source = source;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[8]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isConstructor) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[1]++;
      this.kind = Kind.CONSTRUCTOR;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[10]++;
      this.propAccess = PropAccess.ANY;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[11]++;
      this.typeOfThis = typeOfThis != null ?
          typeOfThis : new InstanceObjectType(registry, this, nativeType);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[12]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[2]++;
      this.kind = Kind.ORDINARY;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[13]++;
      this.typeOfThis = typeOfThis != null ?
          typeOfThis :
          registry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[14]++;
    }
    this.call = arrowType;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[15]++;
  }

  /** Creates an instance for a function that is an interface. */
  private FunctionType(JSTypeRegistry registry, String name, Node source) {
    super(registry, name,
        registry.getNativeObjectType(JSTypeNative.FUNCTION_INSTANCE_TYPE));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[16]++;
    setPrettyPrint(true);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[17]++;

    Preconditions.checkArgument(source == null ||
        Token.FUNCTION == source.getType());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[18]++;
    Preconditions.checkArgument(name != null);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[19]++;
    this.source = source;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[20]++;
    this.call = new ArrowType(registry, new Node(Token.PARAM_LIST), null);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[21]++;
    this.kind = Kind.INTERFACE;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[22]++;
    this.typeOfThis = new InstanceObjectType(registry, this);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[23]++;
  }

  /** Creates an instance for a function that is an interface. */
  static FunctionType forInterface(
      JSTypeRegistry registry, String name, Node source) {
    return new FunctionType(registry, name, source);
  }

  @Override
  public boolean isInstanceType() {
    // The universal constructor is its own instance, bizarrely. It overrides
    // getConstructor() appropriately when it's declared.
    return this == registry.getNativeType(U2U_CONSTRUCTOR_TYPE);
  }

  @Override
  public boolean isConstructor() {
    return kind == Kind.CONSTRUCTOR;
  }

  @Override
  public boolean isInterface() {
    return kind == Kind.INTERFACE;
  }

  @Override
  public boolean isOrdinaryFunction() {
    return kind == Kind.ORDINARY;
  }

  /**
   * When a class B inherits from A and A is annotated as a struct, then B
   * automatically gets the annotation, even if B's constructor is not
   * explicitly annotated.
   */
  public boolean makesStructs() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[4]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((propAccess == PropAccess.STRUCT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[5]++;
      return true;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[6]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[26]++;
    FunctionType superc = getSuperClassConstructor();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((superc != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((superc.makesStructs()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[7]++;
      setStruct();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[28]++;
      return true;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[8]++;}
    return false;
  }

  /**
   * When a class B inherits from A and A is annotated as a dict, then B
   * automatically gets the annotation, even if B's constructor is not
   * explicitly annotated.
   */
  public boolean makesDicts() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[9]++;
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[10]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((propAccess == PropAccess.DICT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[11]++;
      return true;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[12]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[31]++;
    FunctionType superc = getSuperClassConstructor();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((superc != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((superc.makesDicts()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[13]++;
      setDict();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[33]++;
      return true;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[14]++;}
    return false;
  }

  public void setStruct() {
    propAccess = PropAccess.STRUCT;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[34]++;
  }

  public void setDict() {
    propAccess = PropAccess.DICT;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[35]++;
  }

  @Override
  public FunctionType toMaybeFunctionType() {
    return this;
  }

  @Override
  public boolean canBeCalled() {
    return true;
  }

  public boolean hasImplementedInterfaces() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((implementedInterfaces.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)){
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[15]++;
      return true;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[16]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[37]++;
    FunctionType superCtor = isConstructor() ?
        getSuperClassConstructor() : null;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((superCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[17]++;
      return superCtor.hasImplementedInterfaces();

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[18]++;}
    return false;
  }

  public Iterable<Node> getParameters() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[39]++;
    Node n = getParametersNode();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[40]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[19]++;
      return n.children();

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[20]++;
      return Collections.emptySet();
    }
  }

  /** Gets an LP node that contains all params. May be null. */
  public Node getParametersNode() {
    return call.parameters;
  }

  /** Gets the minimum number of arguments that this function requires. */
  public int getMinArguments() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[41]++;
    // NOTE(nicksantos): There are some native functions that have optional
    // parameters before required parameters. This algorithm finds the position
    // of the last required parameter.
    int i = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[42]++;
    int min = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[43]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[1]++;


    for (Node n : getParameters()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[1]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[2]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[3]++;
}
      i++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[44]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((n.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[21]++;
        min = i;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[46]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[22]++;}
    }
    return min;
  }

  /**
   * Gets the maximum number of arguments that this function requires,
   * or Integer.MAX_VALUE if this is a variable argument function.
   */
  public int getMaxArguments() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[47]++;
    Node params = getParametersNode();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((params != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[23]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[49]++;
      Node lastParam = params.getLastChild();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((lastParam == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((lastParam.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[25]++;
        return params.getChildCount();

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[26]++;}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[24]++;}

    return Integer.MAX_VALUE;
  }

  public JSType getReturnType() {
    return call.returnType;
  }

  public boolean isReturnTypeInferred() {
    return call.returnTypeInferred;
  }

  /** Gets the internal arrow type. For use by subclasses only. */
  ArrowType getInternalArrowType() {
    return call;
  }

  @Override
  public Property getSlot(String name) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 (("prototype".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[27]++;
      // Lazy initialization of the prototype field.
      getPrototype();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[52]++;
      return prototypeSlot;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[28]++;
      return super.getSlot(name);
    }
  }

  /**
   * Includes the prototype iff someone has created it. We do not want
   * to expose the prototype for ordinary functions.
   */
  @Override
  public Set<String> getOwnPropertyNames() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((prototypeSlot == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[29]++;
      return super.getOwnPropertyNames();

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[30]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[54]++;
      Set<String> names = Sets.newHashSet("prototype");
      names.addAll(super.getOwnPropertyNames());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[55]++;
      return names;
    }
  }

  /**
   * Gets the {@code prototype} property of this function type. This is
   * equivalent to {@code (ObjectType) getPropertyType("prototype")}.
   */
  public ObjectType getPrototype() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
    // lazy initialization of the prototype field
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((prototypeSlot == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[31]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[57]++;
      String refName = getReferenceName();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[58]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((refName == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[33]++;
        // Someone is trying to access the prototype of a structural function.
        // We don't want to give real properties to this prototype, because
        // then it would propagate to all structural functions.
        setPrototypeNoCheck(
           registry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE),
           null);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[59]++;

      } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[34]++;
        setPrototype(
            new PrototypeObjectType(
                registry,
                getReferenceName() + ".prototype",
                registry.getNativeObjectType(OBJECT_TYPE),
                isNativeObjectType(), null),
            null);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[60]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[32]++;}
    return (ObjectType) prototypeSlot.getType();
  }

  /**
   * Sets the prototype, creating the prototype object from the given
   * base type.
   * @param baseType The base type.
   */
  public void setPrototypeBasedOn(ObjectType baseType) {
    setPrototypeBasedOn(baseType, null);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[61]++;
  }

  void setPrototypeBasedOn(ObjectType baseType, Node propertyNode) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[62]++;
int CodeCoverConditionCoverageHelper_C18;
    // This is a bit weird. We need to successfully handle these
    // two cases:
    // Foo.prototype = new Bar();
    // and
    // Foo.prototype = {baz: 3};
    // In the first case, we do not want new properties to get
    // added to Bar. In the second case, we do want new properties
    // to get added to the type of the anonymous object.
    //
    // We handle this by breaking it into two cases:
    //
    // In the first case, we create a new PrototypeObjectType and set
    // its implicit prototype to the type being assigned. This ensures
    // that Bar will not get any properties of Foo.prototype, but properties
    // later assigned to Bar will get inherited properly.
    //
    // In the second case, we just use the anonymous object as the prototype.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((baseType.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((baseType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[35]++;
      baseType = new PrototypeObjectType(
          registry, getReferenceName() + ".prototype", baseType);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[63]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[36]++;}
    setPrototype(baseType, propertyNode);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[64]++;
  }

  /**
   * Sets the prototype.
   * @param prototype the prototype. If this value is {@code null} it will
   *        silently be discarded.
   */
  boolean setPrototype(ObjectType prototype, Node propertyNode) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[65]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((prototype == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[37]++;
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[38]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[66]++;
int CodeCoverConditionCoverageHelper_C20;
    // getInstanceType fails if the function is not a constructor
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((prototype == getInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[39]++;
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[40]++;}
    return setPrototypeNoCheck(prototype, propertyNode);
  }

  /** Set the prototype without doing any sanity checks. */
  private boolean setPrototypeNoCheck(ObjectType prototype, Node propertyNode) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[67]++;
    ObjectType oldPrototype = prototypeSlot == null
        ? null : (ObjectType) prototypeSlot.getType();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[68]++;
    boolean replacedPrototype = oldPrototype != null;

    this.prototypeSlot = new Property("prototype", prototype, true,
        propertyNode == null ? source : propertyNode);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[69]++;
    prototype.setOwnerFunction(this);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[70]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((oldPrototype != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[41]++;
      // Disassociating the old prototype makes this easier to debug--
      // we don't have to worry about two prototypes running around.
      oldPrototype.setOwnerFunction(null);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[72]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[42]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[73]++;
int CodeCoverConditionCoverageHelper_C22;

    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[43]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[74]++;
      FunctionType superClass = getSuperClassConstructor();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[75]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((superClass != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[45]++;
        superClass.addSubType(this);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[76]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[46]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[77]++;
int CodeCoverConditionCoverageHelper_C24;

      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[47]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[78]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[4]++;


        for (ObjectType interfaceType : getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[4]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[5]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[6]++;
}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[79]++;
int CodeCoverConditionCoverageHelper_C25;
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((interfaceType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[49]++;
            interfaceType.getConstructor().addSubType(this);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[80]++;

          } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[50]++;}
        }

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[48]++;}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[44]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[81]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((replacedPrototype) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[51]++;
      clearCachedValues();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[82]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[52]++;}

    return true;
  }

  /**
   * Returns all interfaces implemented by a class or its superclass and any
   * superclasses for any of those interfaces. If this is called before all
   * types are resolved, it may return an incomplete set.
   */
  public Iterable<ObjectType> getAllImplementedInterfaces() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[83]++;
    // Store them in a linked hash set, so that the compile job is
    // deterministic.
    Set<ObjectType> interfaces = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[84]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[7]++;



    for (ObjectType type : getImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[7]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[8]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[9]++;
}
      addRelatedInterfaces(type, interfaces);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[85]++;
    }
    return interfaces;
  }

  private void addRelatedInterfaces(ObjectType instance, Set<ObjectType> set) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[86]++;
    FunctionType constructor = instance.getConstructor();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[87]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[53]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[88]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((constructor.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[55]++;
        return;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[56]++;}

      set.add(instance);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[89]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[90]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[10]++;



      for (ObjectType interfaceType : instance.getCtorExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[10]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[11]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[12]++;
}
        addRelatedInterfaces(interfaceType, set);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[91]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[54]++;}
  }

  /** Returns interfaces implemented directly by a class or its superclass. */
  public Iterable<ObjectType> getImplementedInterfaces() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[92]++;
    FunctionType superCtor = isConstructor() ?
        getSuperClassConstructor() : null;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[93]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((superCtor == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[57]++;
      return implementedInterfaces;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[58]++;
      return Iterables.concat(
          implementedInterfaces, superCtor.getImplementedInterfaces());
    }
  }

  /** Returns interfaces directly implemented by the class. */
  public Iterable<ObjectType> getOwnImplementedInterfaces() {
    return implementedInterfaces;
  }

  public void setImplementedInterfaces(List<ObjectType> implementedInterfaces) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[94]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[59]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[95]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[13]++;


      // Records this type for each implemented interface.
      for (ObjectType type : implementedInterfaces) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[13]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[14]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[15]++;
}
        registry.registerTypeImplementingInterface(this, type);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[96]++;
      }
      this.implementedInterfaces = ImmutableList.copyOf(implementedInterfaces);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[97]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[60]++;
      throw new UnsupportedOperationException();
    }
  }

  /**
   * Returns all extended interfaces declared by an interfaces or its super-
   * interfaces. If this is called before all types are resolved, it may return
   * an incomplete set.
   */
  public Iterable<ObjectType> getAllExtendedInterfaces() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[98]++;
    // Store them in a linked hash set, so that the compile job is
    // deterministic.
    Set<ObjectType> extendedInterfaces = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[99]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[16]++;



    for (ObjectType interfaceType : getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[16]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[17]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[18]++;
}
      addRelatedExtendedInterfaces(interfaceType, extendedInterfaces);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[100]++;
    }
    return extendedInterfaces;
  }

  private void addRelatedExtendedInterfaces(ObjectType instance,
      Set<ObjectType> set) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[101]++;
    FunctionType constructor = instance.getConstructor();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[102]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[61]++;
      set.add(instance);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[103]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[104]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[19]++;



      for (ObjectType interfaceType : constructor.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[19]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[20]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[21]++;
}
        addRelatedExtendedInterfaces(interfaceType, set);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[105]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[62]++;}
  }

  /** Returns interfaces directly extended by an interface */
  public Iterable<ObjectType> getExtendedInterfaces() {
    return extendedInterfaces;
  }

  /** Returns the number of interfaces directly extended by an interface */
  public int getExtendedInterfacesCount() {
    return extendedInterfaces.size();
  }

  public void setExtendedInterfaces(List<ObjectType> extendedInterfaces)
    throws UnsupportedOperationException {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[106]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[63]++;
      this.extendedInterfaces = ImmutableList.copyOf(extendedInterfaces);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[107]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[64]++;
      throw new UnsupportedOperationException();
    }
  }

  @Override
  public JSType getPropertyType(String name) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[108]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((hasOwnProperty(name)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[65]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[109]++;
      // Define the "call", "apply", and "bind" functions lazily.
      boolean isCall = "call".equals(name);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[110]++;
      boolean isBind = "bind".equals(name);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[111]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((isCall) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isBind) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[67]++;
        defineDeclaredProperty(name, getCallOrBindSignature(isCall), source);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[112]++;

      } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[68]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[113]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 (("apply".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[69]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[114]++;
        // Define the "apply" function lazily.
        FunctionParamBuilder builder = new FunctionParamBuilder(registry);

        // ECMA-262 says that apply's second argument must be an Array
        // or an arguments object. We don't model the arguments object,
        // so let's just be forgiving for now.
        // TODO(nicksantos): Model the Arguments object.
        builder.addOptionalParams(
            registry.createNullableType(getTypeOfThis()),
            registry.createNullableType(
                registry.getNativeType(JSTypeNative.OBJECT_TYPE)));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[115]++;

        defineDeclaredProperty(name,
            new FunctionBuilder(registry)
            .withParams(builder)
            .withReturnType(getReturnType())
            .withTemplateKeys(getTemplateTypeMap().getTemplateKeys())
            .build(),
            source);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[116]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[70]++;}
}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[66]++;}

    return super.getPropertyType(name);
  }

  /**
   * Get the return value of calling "bind" on this function
   * with the specified number of arguments.
   *
   * If -1 is passed, then we will return a result that accepts
   * any parameters.
   */
  public FunctionType getBindReturnType(int argsToBind) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[117]++;
    FunctionBuilder builder = new FunctionBuilder(registry)
        .withReturnType(getReturnType())
        .withTemplateKeys(getTemplateTypeMap().getTemplateKeys());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[118]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((argsToBind >= 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[71]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[119]++;
      Node origParams = getParametersNode();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[120]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((origParams != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[73]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[121]++;
        Node params = origParams.cloneTree();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[122]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[22]++;


int CodeCoverConditionCoverageHelper_C38;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((i < argsToBind) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((params.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[22]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[23]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[24]++;
}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[123]++;
int CodeCoverConditionCoverageHelper_C39;
          if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((params.getFirstChild().isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[75]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[124]++;
            break;

          } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[76]++;}
          params.removeFirstChild();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[125]++;
        }
        builder.withParamsNode(params);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[126]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[74]++;}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[72]++;}
    return builder.build();
  }

  /**
   * Notice that "call" and "bind" have the same argument signature,
   * except that all the arguments of "bind" (except the first)
   * are optional.
   */
  private FunctionType getCallOrBindSignature(boolean isCall) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[127]++;
    boolean isBind = !isCall;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[128]++;
    FunctionBuilder builder = new FunctionBuilder(registry)
        .withReturnType(isCall ? getReturnType() : getBindReturnType(-1))
        .withTemplateKeys(getTemplateTypeMap().getTemplateKeys());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[129]++;

    Node origParams = getParametersNode();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[130]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((origParams != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[77]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[131]++;
      Node params = origParams.cloneTree();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[132]++;

      Node thisTypeNode = Node.newString(Token.NAME, "thisType");
      thisTypeNode.setJSType(
          registry.createOptionalNullableType(getTypeOfThis()));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[133]++;
      params.addChildToFront(thisTypeNode);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[134]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[135]++;
int CodeCoverConditionCoverageHelper_C41;

      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isBind) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[79]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[136]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[25]++;


int CodeCoverConditionCoverageHelper_C42;
        // The arguments of bind() are unique in that they are all
        // optional but not undefinable.
        for (Node current = thisTypeNode.getNext();(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); current = current.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[25]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[26]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[27]++;
}
          current.setOptionalArg(true);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[137]++;
        }

      } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[80]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[138]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((isCall) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[81]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[139]++;
        // The first argument of call() is optional iff all the arguments
        // are optional. It's sufficient to check the first argument.
        Node firstArg = thisTypeNode.getNext();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[140]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (32)) == 0 || true) &&
 ((firstArg == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((firstArg.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((firstArg.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 3) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 3) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[83]++;
          thisTypeNode.setOptionalArg(true);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[141]++;

        } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[84]++;}

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[82]++;}
}

      builder.withParamsNode(params);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[142]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[78]++;}

    return builder.build();
  }

  @Override
  boolean defineProperty(String name, JSType type,
      boolean inferred, Node propertyNode) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[143]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 (("prototype".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[85]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[144]++;
      ObjectType objType = type.toObjectType();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[145]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[87]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[146]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((prototypeSlot != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((objType.isEquivalentTo(prototypeSlot.getType())) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[89]++;
          return true;

        } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[90]++;}
        setPrototypeBasedOn(objType, propertyNode);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[147]++;
        return true;

      } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[88]++;
        return false;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[86]++;}
    return super.defineProperty(name, type, inferred, propertyNode);
  }

  /**
   * Computes the supremum or infimum of two functions.
   * Because sup() and inf() share a lot of logic for functions, we use
   * a single helper.
   * @param leastSuper If true, compute the supremum of {@code this} with
   *     {@code that}. Otherwise, compute the infimum.
   * @return The least supertype or greatest subtype.
   */
  FunctionType supAndInfHelper(FunctionType that, boolean leastSuper) {
    // NOTE(nicksantos): When we remove the unknown type, the function types
    // form a lattice with the universal constructor at the top of the lattice,
    // and the LEAST_FUNCTION_TYPE type at the bottom of the lattice.
    //
    // When we introduce the unknown type, it's much more difficult to make
    // heads or tails of the partial ordering of types, because there's no
    // clear hierarchy between the different components (parameter types and
    // return types) in the ArrowType.
    //
    // Rather than make the situation more complicated by introducing new
    // types (like unions of functions), we just fallback on the simpler
    // approach of getting things right at the top and the bottom of the
    // lattice.
    //
    // If there are unknown parameters or return types making things
    // ambiguous, then sup(A, B) is always the top function type, and
    // inf(A, B) is always the bottom function type.
    Preconditions.checkNotNull(that);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[148]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[149]++;
int CodeCoverConditionCoverageHelper_C48;

    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isEquivalentTo(that)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[91]++;
      return this;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[92]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[150]++;
int CodeCoverConditionCoverageHelper_C49;

    // If these are ordinary functions, then merge them.
    // Don't do this if any of the params/return
    // values are unknown, because then there will be cycles in
    // their local lattice and they will merge in weird ways.
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (128)) == 0 || true) &&
 ((isOrdinaryFunction()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (32)) == 0 || true) &&
 ((that.isOrdinaryFunction()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((this.call.hasUnknownParamsOrReturn()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((that.call.hasUnknownParamsOrReturn()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 4) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 4) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[93]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[151]++;

      // Check for the degenerate case, but double check
      // that there's not a cycle.
      boolean isSubtypeOfThat = isSubtype(that);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[152]++;
      boolean isSubtypeOfThis = that.isSubtype(this);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[153]++;
int CodeCoverConditionCoverageHelper_C50;
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((isSubtypeOfThat) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((isSubtypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[95]++;
        return leastSuper ? that : this;

      } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[96]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[154]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((isSubtypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((isSubtypeOfThat) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[97]++;
        return leastSuper ? this : that;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[98]++;}
}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[155]++;

      // Merge the two functions component-wise.
      FunctionType merged = tryMergeFunctionPiecewise(that, leastSuper);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[156]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((merged != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[99]++;
        return merged;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[100]++;}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[94]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[157]++;

    // The function instance type is a special case
    // that lives above the rest of the lattice.
    JSType functionInstance = registry.getNativeType(
        JSTypeNative.FUNCTION_INSTANCE_TYPE);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[158]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((functionInstance.isEquivalentTo(that)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[101]++;
      return leastSuper ? that : this;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[102]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[159]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((functionInstance.isEquivalentTo(this)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[103]++;
      return leastSuper ? this : that;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[104]++;}
}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[160]++;

    // In theory, we should be using the GREATEST_FUNCTION_TYPE as the
    // greatest function. In practice, we don't because it's way too
    // broad. The greatest function takes var_args None parameters, which
    // means that all parameters register a type warning.
    //
    // Instead, we use the U2U ctor type, which has unknown type args.
    FunctionType greatestFn =
        registry.getNativeFunctionType(JSTypeNative.U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[161]++;
    FunctionType leastFn =
        registry.getNativeFunctionType(JSTypeNative.LEAST_FUNCTION_TYPE);
    return leastSuper ? greatestFn : leastFn;
  }

  /**
   * Try to get the sup/inf of two functions by looking at the
   * piecewise components.
   */
  private FunctionType tryMergeFunctionPiecewise(
      FunctionType other, boolean leastSuper) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[162]++;
    Node newParamsNode = null;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[163]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((call.hasEqualParameters(other.call, EquivalenceMethod.IDENTITY)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[105]++;
      newParamsNode = call.parameters;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[164]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[106]++;
      // If the parameters are not equal, don't try to merge them.
      // Someday, we should try to merge the individual params.
      return null;
    }
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[165]++;

    JSType newReturnType = leastSuper ?
        call.returnType.getLeastSupertype(other.call.returnType) :
        call.returnType.getGreatestSubtype(other.call.returnType);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[166]++;

    JSType newTypeOfThis = null;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[167]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isEquivalent(typeOfThis, other.typeOfThis)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[107]++;
      newTypeOfThis = typeOfThis;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[168]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[108]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[169]++;
      JSType maybeNewTypeOfThis = leastSuper ?
          typeOfThis.getLeastSupertype(other.typeOfThis) :
          typeOfThis.getGreatestSubtype(other.typeOfThis);
      newTypeOfThis = maybeNewTypeOfThis;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[170]++;
    }
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[171]++;

    boolean newReturnTypeInferred =
        call.returnTypeInferred || other.call.returnTypeInferred;

    return new FunctionType(
        registry, null, null,
        new ArrowType(
            registry, newParamsNode, newReturnType, newReturnTypeInferred),
        newTypeOfThis, null, false, false);
  }

  /**
   * Given a constructor or an interface type, get its superclass constructor
   * or {@code null} if none exists.
   */
  public FunctionType getSuperClassConstructor() {
    Preconditions.checkArgument(isConstructor() || isInterface());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[172]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[173]++;
    ObjectType maybeSuperInstanceType = getPrototype().getImplicitPrototype();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[174]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((maybeSuperInstanceType == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[109]++;
      return null;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[110]++;}
    return maybeSuperInstanceType.getConstructor();
  }

  /**
   * Given an interface and a property, finds the top-most super interface
   * that has the property defined (including this interface).
   */
  public static ObjectType getTopDefiningInterface(ObjectType type,
      String propertyName) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[175]++;
    ObjectType foundType = null;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[176]++;
int CodeCoverConditionCoverageHelper_C58;
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((type.hasProperty(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[111]++;
      foundType = type;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[177]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[112]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[178]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[28]++;


    for (ObjectType interfaceType : type.getCtorExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[28]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[29]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[30]++;
}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[179]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((interfaceType.hasProperty(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[113]++;
        foundType = getTopDefiningInterface(interfaceType, propertyName);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[180]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[114]++;}
    }
    return foundType;
  }

  /**
   * Given a constructor or an interface type and a property, finds the
   * top-most superclass that has the property defined (including this
   * constructor).
   */
  public ObjectType getTopMostDefiningType(String propertyName) {
    Preconditions.checkState(isConstructor() || isInterface());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[181]++;
    Preconditions.checkArgument(getInstanceType().hasProperty(propertyName));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[182]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[183]++;
    FunctionType ctor = this;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[184]++;
int CodeCoverConditionCoverageHelper_C60;

    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[115]++;
      return getTopDefiningInterface(getInstanceType(), propertyName);

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[116]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[185]++;

    ObjectType topInstanceType = null;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[186]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[31]++;


int CodeCoverConditionCoverageHelper_C61;
    do {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[31]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[32]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[33]++;
}
      topInstanceType = ctor.getInstanceType();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[187]++;
      ctor = ctor.getSuperClassConstructor();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[188]++;
    } while ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((ctor != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((ctor.getPrototype().hasProperty(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false));

    return topInstanceType;
  }

  /**
   * Two function types are equal if their signatures match. Since they don't
   * have signatures, two interfaces are equal if their names match.
   */
  boolean checkFunctionEquivalenceHelper(
      FunctionType that, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[189]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[117]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[190]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((that.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[119]++;
        return this == that;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[120]++;}
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[118]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[191]++;
int CodeCoverConditionCoverageHelper_C64;
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[121]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[192]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((that.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[123]++;
        return getReferenceName().equals(that.getReferenceName());

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[124]++;}
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[122]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[193]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((that.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[125]++;
      return false;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[126]++;}

    return typeOfThis.checkEquivalenceHelper(that.typeOfThis, eqMethod) &&
        call.checkArrowEquivalenceHelper(that.call, eqMethod);
  }

  @Override
  public int hashCode() {
    return isInterface() ? getReferenceName().hashCode() : call.hashCode();
  }

  public boolean hasEqualCallType(FunctionType otherType) {
    return this.call.checkArrowEquivalenceHelper(
        otherType.call, EquivalenceMethod.IDENTITY);
  }

  /**
   * Informally, a function is represented by
   * {@code function (params): returnType} where the {@code params} is a comma
   * separated list of types, the first one being a special
   * {@code this:T} if the function expects a known type for {@code this}.
   */
  @Override
  String toStringHelper(boolean forAnnotations) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[194]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((isPrettyPrint()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this == registry.getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[127]++;
      return "Function";

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[128]++;}

    setPrettyPrint(false);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[195]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[196]++;

    StringBuilder b = new StringBuilder(32);
    b.append("function (");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[197]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[198]++;
    int paramNum = call.parameters.getChildCount();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[199]++;
    boolean hasKnownTypeOfThis = !(typeOfThis instanceof UnknownType);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[200]++;
int CodeCoverConditionCoverageHelper_C68;
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((hasKnownTypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[129]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[201]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[131]++;
        b.append("new:");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[202]++;

      } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[132]++;
        b.append("this:");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[203]++;
      }
      b.append(typeOfThis.toStringHelper(forAnnotations));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[204]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[130]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[205]++;
int CodeCoverConditionCoverageHelper_C70;
    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((paramNum > 0) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[133]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[206]++;
int CodeCoverConditionCoverageHelper_C71;
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((hasKnownTypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[135]++;
        b.append(", ");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[207]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[136]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[208]++;
      Node p = call.parameters.getFirstChild();
      appendArgString(b, p, forAnnotations);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[209]++;

      p = p.getNext();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[210]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[211]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[34]++;


int CodeCoverConditionCoverageHelper_C72;
      while ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[34]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[35]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[36]++;
}
        b.append(", ");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[212]++;
        appendArgString(b, p, forAnnotations);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[213]++;
        p = p.getNext();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[214]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[134]++;}
    b.append("): ");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[215]++;
    b.append(call.returnType.toStringHelper(forAnnotations));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[216]++;

    setPrettyPrint(true);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[217]++;
    return b.toString();
  }

  private void appendArgString(
      StringBuilder b, Node p, boolean forAnnotations) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[218]++;
int CodeCoverConditionCoverageHelper_C73;
    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((p.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[137]++;
      appendVarArgsString(b, p.getJSType(), forAnnotations);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[219]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[138]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[220]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((p.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[139]++;
      appendOptionalArgString(b, p.getJSType(), forAnnotations);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[221]++;

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[140]++;
      b.append(p.getJSType().toStringHelper(forAnnotations));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[222]++;
    }
}
  }

  /** Gets the string representation of a var args param. */
  private void appendVarArgsString(StringBuilder builder, JSType paramType,
      boolean forAnnotations) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[223]++;
int CodeCoverConditionCoverageHelper_C75;
    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((paramType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[141]++;
      // Remove the optionality from the var arg.
      paramType = paramType.toMaybeUnionType().getRestrictedUnion(
          registry.getNativeType(JSTypeNative.VOID_TYPE));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[224]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[142]++;}
    builder.append("...[").append(
        paramType.toStringHelper(forAnnotations)).append("]");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[225]++;
  }

  /** Gets the string representation of an optional param. */
  private void appendOptionalArgString(
      StringBuilder builder, JSType paramType, boolean forAnnotations) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[226]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((paramType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[143]++;
      // Remove the optionality from the var arg.
      paramType = paramType.toMaybeUnionType().getRestrictedUnion(
          registry.getNativeType(JSTypeNative.VOID_TYPE));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[227]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[144]++;}
    builder.append(paramType.toStringHelper(forAnnotations)).append("=");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[228]++;
  }

  /**
   * A function is a subtype of another if their call methods are related via
   * subtyping and {@code this} is a subtype of {@code that} with regard to
   * the prototype chain.
   */
  @Override
  public boolean isSubtype(JSType that) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[229]++;
int CodeCoverConditionCoverageHelper_C77;
    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((JSType.isSubtypeHelper(this, that)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[145]++;
      return true;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[146]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[230]++;
int CodeCoverConditionCoverageHelper_C78;

    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((that.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[147]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[231]++;
      FunctionType other = that.toMaybeFunctionType();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[232]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((other.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[149]++;
        // Any function can be assigned to an interface function.
        return true;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[150]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[233]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[151]++;
        // An interface function cannot be assigned to anything.
        return false;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[152]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[234]++;

      // If functionA is a subtype of functionB, then their "this" types
      // should be contravariant. However, this causes problems because
      // of the way we enforce overrides. Because function(this:SubFoo)
      // is not a subtype of function(this:Foo), our override check treats
      // this as an error. Let's punt on all this for now.
      // TODO(nicksantos): fix this.
      boolean treatThisTypesAsCovariant =
        // An interface 'this'-type is non-restrictive.
        // In practical terms, if C implements I, and I has a method m,
        // then any m doesn't necessarily have to C#m's 'this'
        // type doesn't need to match I.
        (other.typeOfThis.toObjectType() != null &&
             other.typeOfThis.toObjectType().getConstructor() != null &&
             other.typeOfThis.toObjectType().getConstructor().isInterface()) ||

        // If one of the 'this' types is covariant of the other,
        // then we'll treat them as covariant (see comment above).
        other.typeOfThis.isSubtype(this.typeOfThis) ||
        this.typeOfThis.isSubtype(other.typeOfThis);
      return treatThisTypesAsCovariant && this.call.isSubtype(other.call);

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[148]++;}

    return getNativeType(JSTypeNative.FUNCTION_PROTOTYPE).isSubtype(that);
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseFunctionType(this);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseFunctionType(this, that);
  }

  /**
   * Gets the type of instance of this function.
   * @throws IllegalStateException if this function is not a constructor
   *         (see {@link #isConstructor()}).
   */
  public ObjectType getInstanceType() {
    Preconditions.checkState(hasInstanceType());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[235]++;
    return typeOfThis.toObjectType();
  }

  /**
   * Sets the instance type. This should only be used for special
   * native types.
   */
  void setInstanceType(ObjectType instanceType) {
    typeOfThis = instanceType;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[236]++;
  }

  /**
   * Returns whether this function type has an instance type.
   */
  public boolean hasInstanceType() {
    return isConstructor() || isInterface();
  }

  /**
   * Gets the type of {@code this} in this function.
   */
  @Override
  public JSType getTypeOfThis() {
    return typeOfThis.isEmptyType() ?
        registry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE) : typeOfThis;
  }

  /**
   * Gets the source node or null if this is an unknown function.
   */
  public Node getSource() {
    return source;
  }

  /**
   * Sets the source node.
   */
  public void setSource(Node source) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[237]++;
int CodeCoverConditionCoverageHelper_C81;
    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((prototypeSlot != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[153]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[238]++;
int CodeCoverConditionCoverageHelper_C82;
      // NOTE(bashir): On one hand when source is null we want to drop any
      // references to old nodes retained in prototypeSlot. On the other hand
      // we cannot simply drop prototypeSlot, so we retain all information
      // except the propertyNode for which we use an approximation! These
      // details mostly matter in hot-swap passes.
      if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((prototypeSlot.getNode() == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[155]++;
        prototypeSlot = new Property(prototypeSlot.getName(),
            prototypeSlot.getType(), prototypeSlot.isTypeInferred(), source);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[239]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[156]++;}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[154]++;}
    this.source = source;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[240]++;
  }

  /** Adds a type to the list of subtypes for this type. */
  private void addSubType(FunctionType subType) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[241]++;
int CodeCoverConditionCoverageHelper_C83;
    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((subTypes == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[157]++;
      subTypes = Lists.newArrayList();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[242]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[158]++;}
    subTypes.add(subType);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[243]++;
  }

  @Override
  public void clearCachedValues() {
    super.clearCachedValues();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[244]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[245]++;
int CodeCoverConditionCoverageHelper_C84;

    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((subTypes != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[159]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[246]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[37]++;


      for (FunctionType subType : subTypes) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[37]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[38]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[39]++;
}
        subType.clearCachedValues();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[247]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[160]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[248]++;
int CodeCoverConditionCoverageHelper_C85;

    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[161]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[249]++;
int CodeCoverConditionCoverageHelper_C86;
      if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[163]++;
        getInstanceType().clearCachedValues();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[250]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[164]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[251]++;
int CodeCoverConditionCoverageHelper_C87;

      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((prototypeSlot != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[165]++;
        ((ObjectType) prototypeSlot.getType()).clearCachedValues();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[252]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[166]++;}

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[162]++;}
  }

  /**
   * Returns a list of types that are subtypes of this type. This is only valid
   * for constructor functions, and may be null. This allows a downward
   * traversal of the subtype graph.
   */
  public List<FunctionType> getSubTypes() {
    return subTypes;
  }

  @Override
  public boolean hasCachedValues() {
    return prototypeSlot != null || super.hasCachedValues();
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    setResolvedTypeInternal(this);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[253]++;

    call = (ArrowType) safeResolve(call, t, scope);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[254]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[255]++;
int CodeCoverConditionCoverageHelper_C88;
    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((prototypeSlot != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[167]++;
      prototypeSlot.setType(
          safeResolve(prototypeSlot.getType(), t, scope));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[256]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[168]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[257]++;

    // Warning about typeOfThis if it doesn't resolve to an ObjectType
    // is handled further upstream.
    //
    // TODO(nicksantos): Handle this correctly if we have a UnionType.
    //
    // TODO(nicksantos): In ES3, the run-time coerces "null" to the global
    // activation object. In ES5, it leaves it as null. Just punt on this
    // issue for now by coercing out null. This is complicated by the
    // fact that when most people write @this {Foo}, they really don't
    // mean "nullable Foo". For certain tags (like @extends) we de-nullify
    // the name for them.
    JSType maybeTypeOfThis = safeResolve(typeOfThis, t, scope);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[258]++;
int CodeCoverConditionCoverageHelper_C89;
    if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((maybeTypeOfThis != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[169]++;
      maybeTypeOfThis = maybeTypeOfThis.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[259]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[170]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[260]++;
int CodeCoverConditionCoverageHelper_C90;
    if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((maybeTypeOfThis instanceof ObjectType) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[171]++;
      typeOfThis = maybeTypeOfThis;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[261]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[172]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[262]++;

    boolean changed = false;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[263]++;
    ImmutableList.Builder<ObjectType> resolvedInterfaces =
        ImmutableList.builder();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[264]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[40]++;


    for (ObjectType iface : implementedInterfaces) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[40]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[41]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[42]++;
}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[265]++;
      ObjectType resolvedIface = (ObjectType) iface.resolve(t, scope);
      resolvedInterfaces.add(resolvedIface);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[266]++;
      changed |= (resolvedIface != iface);
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[267]++;
    }
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[268]++;
int CodeCoverConditionCoverageHelper_C91;
    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[173]++;
      implementedInterfaces = resolvedInterfaces.build();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[269]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[174]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[270]++;
int CodeCoverConditionCoverageHelper_C92;

    if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((subTypes != null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[175]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[271]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[43]++;


int CodeCoverConditionCoverageHelper_C93;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((i < subTypes.size()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[43]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[44]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[45]++;
}
        subTypes.set(
            i, JSType.toMaybeFunctionType(subTypes.get(i).resolve(t, scope)));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[272]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[176]++;}

    return super.resolveInternal(t, scope);
  }

  @Override
  public String toDebugHashCodeString() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[273]++;
int CodeCoverConditionCoverageHelper_C94;
    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((this == registry.getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[177]++;
      return super.toDebugHashCodeString();

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[178]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[274]++;

    StringBuilder b = new StringBuilder(32);
    b.append("function (");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[275]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[276]++;
    int paramNum = call.parameters.getChildCount();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[277]++;
    boolean hasKnownTypeOfThis = !typeOfThis.isUnknownType();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[278]++;
int CodeCoverConditionCoverageHelper_C95;
    if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((hasKnownTypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[179]++;
      b.append("this:");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[279]++;
      b.append(getDebugHashCodeStringOf(typeOfThis));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[280]++;

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[180]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[281]++;
int CodeCoverConditionCoverageHelper_C96;
    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((paramNum > 0) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[181]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[282]++;
int CodeCoverConditionCoverageHelper_C97;
      if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((hasKnownTypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[183]++;
        b.append(", ");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[283]++;

      } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[184]++;}
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[284]++;
      Node p = call.parameters.getFirstChild();
      b.append(getDebugHashCodeStringOf(p.getJSType()));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[285]++;
      p = p.getNext();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[286]++;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[287]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[46]++;


int CodeCoverConditionCoverageHelper_C98;
      while ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[46]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[47]--;
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.loops[48]++;
}
        b.append(", ");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[288]++;
        b.append(getDebugHashCodeStringOf(p.getJSType()));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[289]++;
        p = p.getNext();
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[290]++;
      }

    } else {
  CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[182]++;}
    b.append(")");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[291]++;
    b.append(": ");
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[292]++;
    b.append(getDebugHashCodeStringOf(call.returnType));
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[293]++;
    return b.toString();
  }

  private String getDebugHashCodeStringOf(JSType type) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[294]++;
int CodeCoverConditionCoverageHelper_C99;
    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((type == this) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[185]++;
      return "me";

    } else {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.branches[186]++;
      return type.toDebugHashCodeString();
    }
  }

  /** Create a new constructor with the parameters and return type stripped. */
  public FunctionType cloneWithoutArrowType() {
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[295]++;
    FunctionType result = new FunctionType(
        registry, getReferenceName(), source,
        registry.createArrowType(null, null), getInstanceType(),
        null, true, false);
    result.setPrototypeBasedOn(getInstanceType());
CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup.statements[296]++;
    return result;
  }

  @Override
  public boolean hasAnyTemplateTypesInternal() {
    return getTemplateTypeMap().numUnfilledTemplateKeys() > 0
        || typeOfThis.hasAnyTemplateTypes()
        || call.hasAnyTemplateTypes();
  }
}

class CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup ());
  }
    public static long[] statements = new long[297];
    public static long[] branches = new long[187];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[100];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.FunctionType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,2,1,1,1,2,1,2,1,1,1,1,3,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,3,1,1,2,1,3,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 99; i++) {
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

  public CodeCoverCoverageCounter$tnuy0nn37ifn9hi85lhe1fblup () {
    super("com.google.javascript.rhino.jstype.FunctionType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 296; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 186; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 99; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 48; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.FunctionType.java");
      for (int i = 1; i <= 296; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 186; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 99; i++) {
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

