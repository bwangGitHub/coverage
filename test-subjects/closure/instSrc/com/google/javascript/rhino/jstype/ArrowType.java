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

import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;

import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;

/**
 * The arrow type is an internal type that models the functional arrow type
 * seen in typical functional programming languages.  It is used solely for
 * separating the management of the arrow type from the complex
 * {@link FunctionType} that models JavaScript's notion of functions.
 */
final class ArrowType extends JSType {
  static {
    CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[1]++;
  }

  final Node parameters;
  JSType returnType;

  // Whether the return type is inferred.
  final boolean returnTypeInferred;

  ArrowType(JSTypeRegistry registry, Node parameters,
      JSType returnType) {
    this(registry, parameters, returnType, false);
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[2]++;
  }

  ArrowType(JSTypeRegistry registry, Node parameters,
      JSType returnType, boolean returnTypeInferred) {
    super(registry);
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[3]++;

    this.parameters = parameters == null ?
        registry.createParametersWithVarArgs(getNativeType(UNKNOWN_TYPE)) :
        parameters;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[4]++;
    this.returnType = returnType == null ?
        getNativeType(UNKNOWN_TYPE) : returnType;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[5]++;
    this.returnTypeInferred = returnTypeInferred;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[6]++;
  }

  @Override
  public boolean isSubtype(JSType other) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((other instanceof ArrowType) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[2]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[8]++;

    ArrowType that = (ArrowType) other;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

    // This is described in Draft 2 of the ES4 spec,
    // Section 3.4.7: Subtyping Function Types.

    // this.returnType <: that.returnType (covariant)
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.returnType.isSubtype(that.returnType)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[4]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[10]++;

    // that.paramType[i] <: this.paramType[i] (contravariant)
    //
    // If this.paramType[i] is required,
    // then that.paramType[i] is required.
    //
    // In theory, the "required-ness" should work in the other direction as
    // well. In other words, if we have
    //
    // function f(number, number) {}
    // function g(number) {}
    //
    // Then f *should* not be a subtype of g, and g *should* not be
    // a subtype of f. But in practice, we do not implement it this way.
    // We want to support the use case where you can pass g where f is
    // expected, and pretend that g ignores the second argument.
    // That way, you can have a single "no-op" function, and you don't have
    // to create a new no-op function for every possible type signature.
    //
    // So, in this case, g < f, but f !< g
    Node thisParam = parameters.getFirstChild();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[11]++;
    Node thatParam = that.parameters.getFirstChild();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
    while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((thisParam != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((thatParam != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[1]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[2]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[3]++;
}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[13]++;
      JSType thisParamType = thisParam.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[14]++;
      JSType thatParamType = thatParam.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thisParamType != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[5]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((thatParamType == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thatParamType.isSubtype(thisParamType)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[7]++;
          return false;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[8]++;}

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[6]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[17]++;

      boolean thisIsVarArgs = thisParam.isVarArgs();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[18]++;
      boolean thatIsVarArgs = thatParam.isVarArgs();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[19]++;
      boolean thisIsOptional = thisIsVarArgs || thisParam.isOptionalArg();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[20]++;
      boolean thatIsOptional = thatIsVarArgs || thatParam.isOptionalArg();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;

      // "that" can't be a supertype, because it's missing a required argument.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((thisIsOptional) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((thatIsOptional) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[9]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[22]++;
        // NOTE(nicksantos): In our type system, we use {function(...?)} and
        // {function(...NoType)} to to indicate that arity should not be
        // checked. Strictly speaking, this is not a correct formulation,
        // because now a sub-function can required arguments that are var_args
        // in the super-function. So we special-case this.
        boolean isTopFunction =
            thatIsVarArgs &&
            (thatParamType == null ||
             thatParamType.isUnknownType() ||
             thatParamType.isNoType());
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isTopFunction) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[11]++;
          return false;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[12]++;}

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[10]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;

      // don't advance if we have variable arguments
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((thisIsVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[13]++;
        thisParam = thisParam.getNext();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[25]++;

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[14]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((thatIsVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[15]++;
        thatParam = thatParam.getNext();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[27]++;

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[16]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[28]++;
int CodeCoverConditionCoverageHelper_C10;

      // both var_args indicates the end
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((thisIsVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((thatIsVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[17]++;
        thisParam = null;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[29]++;
        thatParam = null;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[30]++;

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[18]++;}
    }
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;

    // "that" can't be a supertype, because it's missing a required argument.
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (128)) == 0 || true) &&
 ((thisParam != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((thisParam.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((thisParam.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((thatParam == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 4) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 4) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[19]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[20]++;}

    return true;
  }

  /**
   * @return True if our parameter spec is equal to {@code that}'s parameter
   *     spec.
   */
  boolean hasEqualParameters(ArrowType that, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[32]++;
    Node thisParam = parameters.getFirstChild();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[33]++;
    Node otherParam = that.parameters.getFirstChild();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;
    while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((thisParam != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((otherParam != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[4]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[5]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[6]++;
}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[35]++;
      JSType thisParamType = thisParam.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[36]++;
      JSType otherParamType = otherParam.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((thisParamType != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[21]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[38]++;
int CodeCoverConditionCoverageHelper_C14;
        // Both parameter lists give a type for this param, it should be equal
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((otherParamType != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((thisParamType.checkEquivalenceHelper(
                otherParamType, eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[23]++;
          return false;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[24]++;}

      } else {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[22]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[39]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((otherParamType != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[25]++;
          return false;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[26]++;}
      }
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[40]++;
int CodeCoverConditionCoverageHelper_C16;

      // Check var_args/optionality
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((thisParam.isOptionalArg() != otherParam.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[27]++;
        return false;

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[28]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[41]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((thisParam.isVarArgs() != otherParam.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[29]++;
        return false;

      } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[30]++;}

      thisParam = thisParam.getNext();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[42]++;
      otherParam = otherParam.getNext();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[43]++;
    }
    // One of the parameters is null, so the types are only equal if both
    // parameter lists are null (they are equal).
    return thisParam == otherParam;
  }

  boolean checkArrowEquivalenceHelper(
      ArrowType that, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[44]++;
int CodeCoverConditionCoverageHelper_C18;
    // Please keep this method in sync with the hashCode() method below.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((returnType.checkEquivalenceHelper(that.returnType, eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[31]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[32]++;}
    return hasEqualParameters(that, eqMethod);
  }

  @Override
  public int hashCode() {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[45]++;
    int hashCode = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[46]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((returnType != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[33]++;
      hashCode += returnType.hashCode();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[47]++;

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[34]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[48]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((returnTypeInferred) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[35]++;
      hashCode += 1;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[49]++;

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[36]++;}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[50]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((parameters != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[37]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[51]++;
      Node param = parameters.getFirstChild();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[52]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
      while ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[7]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[8]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[9]++;
}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[53]++;
        JSType paramType = param.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[54]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((paramType != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[39]++;
          hashCode += paramType.hashCode();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[55]++;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[40]++;}
        param = param.getNext();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[56]++;
      }

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[38]++;}
    return hashCode;
  }

  @Override
  public JSType getLeastSupertype(JSType that) {
    throw new UnsupportedOperationException();
  }

  @Override
  public JSType getGreatestSubtype(JSType that) {
    throw new UnsupportedOperationException();
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    throw new UnsupportedOperationException();
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    throw new UnsupportedOperationException();
  }

  @Override
  public BooleanLiteralSet getPossibleToBooleanOutcomes() {
    return BooleanLiteralSet.TRUE;
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    returnType = safeResolve(returnType, t, scope);
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[57]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[58]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parameters != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[41]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[59]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[10]++;


int CodeCoverConditionCoverageHelper_C25;
      for (Node paramNode = parameters.getFirstChild();(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((paramNode != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); paramNode = paramNode.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[10]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[11]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[12]++;
}
        paramNode.setJSType(paramNode.getJSType().resolve(t, scope));
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[60]++;
      }

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[42]++;}
    return this;
  }

  boolean hasUnknownParamsOrReturn() {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[61]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((parameters != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[43]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[62]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
      for (Node paramNode = parameters.getFirstChild();(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((paramNode != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); paramNode = paramNode.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[13]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[14]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[15]++;
}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[63]++;
        JSType type = paramNode.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[64]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[45]++;
          return true;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[46]++;}
      }

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[44]++;}
    return returnType == null || returnType.isUnknownType();
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return "[ArrowType]";
  }

  @Override
  public boolean hasAnyTemplateTypesInternal() {
    return returnType.hasAnyTemplateTypes()
        || hasTemplatedParameterType();
  }

  private boolean hasTemplatedParameterType() {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[65]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((parameters != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[47]++;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[66]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[16]++;


int CodeCoverConditionCoverageHelper_C30;
      for (Node paramNode = parameters.getFirstChild();(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((paramNode != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); paramNode = paramNode.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[16]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[17]--;
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.loops[18]++;
}
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[67]++;
        JSType type = paramNode.getJSType();
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.statements[68]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((type.hasAnyTemplateTypes()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[49]++;
          return true;

        } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[50]++;}
      }

    } else {
  CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap.branches[48]++;}
    return false;
  }
}

class CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap ());
  }
    public static long[] statements = new long[69];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.ArrowType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,2,2,1,1,1,2,3,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2};
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

  public CodeCoverCoverageCounter$2ravvxv22pi67fhdh9taap () {
    super("com.google.javascript.rhino.jstype.ArrowType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 68; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.ArrowType.java");
      for (int i = 1; i <= 68; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
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

