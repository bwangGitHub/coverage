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
import static com.google.javascript.rhino.jstype.JSTypeNative.CHECKED_UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A builder for union types.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class UnionTypeBuilder implements Serializable {
  static {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[1]++;
  }

  // If the best we can do is say "this object is one of twenty things",
  // then we should just give up and admit that we have no clue.
  private static final int DEFAULT_MAX_UNION_SIZE = 20;
  static {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[2]++;
  }

  private final JSTypeRegistry registry;
  private final List<JSType> alternates = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[3]++;
  }
  private boolean isAllType = false;
  {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[4]++;
  }
  private boolean isNativeUnknownType = false;
  {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[5]++;
  }
  private boolean areAllUnknownsChecked = true;
  {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[6]++;
  }
  private final int maxUnionSize;

  // Every UnionType may have at most one structural function in it.
  //
  // NOTE(nicksantos): I've read some literature that says that type-inferenced
  // languages are fundamentally incompatible with union types. I refuse
  // to believe this. But they do make the type lattice much more complicated.
  //
  // For this reason, when we deal with function types, we actually merge some
  // nodes on the lattice, and treat them as fundamentally equivalent.
  // For example, we treat
  // function(): string | function(): number
  // as equivalent to
  // function(): (string|number)
  // and normalize the first type into the second type.
  //
  // To perform this normalization, we've modified UnionTypeBuilder to disallow
  // multiple structural functions in a union. We always delegate to
  // FunctionType::getLeastSupertype, which either merges the functions into
  // one structural function, or just bails out and uses the top function type.
  private int functionTypePosition = -1;
  {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[7]++;
  }

  // Memoize the result, in case build() is called multiple times.
  private JSType result = null;
  {
    CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[8]++;
  }

  UnionTypeBuilder(JSTypeRegistry registry) {
    this(registry, DEFAULT_MAX_UNION_SIZE);
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[9]++;
  }

  UnionTypeBuilder(JSTypeRegistry registry, int maxUnionSize) {
    this.registry = registry;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[10]++;
    this.maxUnionSize = maxUnionSize;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[11]++;
  }

  Collection<JSType> getAlternates() {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[12]++;
    JSType specialCaseType = reduceAlternatesWithoutUnion();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((specialCaseType != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[1]++;
      return ImmutableList.of(specialCaseType);

    } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[2]++;}
    return Collections.unmodifiableList(alternates);
  }

  /**
   * Adds an alternate to the union type under construction. Returns this
   * for easy chaining.
   */
  UnionTypeBuilder addAlternate(JSType alternate) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
    // build() returns the bottom type by default, so we can
    // just bail out early here.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((alternate.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[3]++;
      return this;

    } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[4]++;}

    isAllType = isAllType || alternate.isAllType();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[15]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[16]++;

    boolean isAlternateUnknown = alternate instanceof UnknownType;
    isNativeUnknownType = isNativeUnknownType || isAlternateUnknown;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[17]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isAlternateUnknown) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[5]++;
      areAllUnknownsChecked = areAllUnknownsChecked &&
          alternate.isCheckedUnknownType();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[19]++;

    } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[6]++;}
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((isAllType) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isNativeUnknownType) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[7]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((alternate.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[9]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[22]++;
        UnionType union = alternate.toMaybeUnionType();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[1]++;


        for (JSType unionAlt : union.getAlternates()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[1]--;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[2]--;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[3]++;
}
          addAlternate(unionAlt);
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[24]++;
        }

      } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[10]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((alternates.size() > maxUnionSize) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[11]++;
          return this;

        } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[12]++;}
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;

        // Function types are special, because they have their
        // own bizarre sub-lattice. See the comments on
        // FunctionType#supAndInf helper and above at functionTypePosition.
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((alternate.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((functionTypePosition != -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[13]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[27]++;
          // See the comments on functionTypePosition above.
          FunctionType other =
              alternates.get(functionTypePosition).toMaybeFunctionType();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[28]++;
          FunctionType supremum =
              alternate.toMaybeFunctionType().supAndInfHelper(other, true);
          alternates.set(functionTypePosition, supremum);
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[29]++;
          result = null;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[30]++;
          return this;

        } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[14]++;}
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[31]++;

        // Look through the alternates we've got so far,
        // and check if any of them are duplicates of
        // one another.
        int currentIndex = 0;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[32]++;
        Iterator<JSType> it = alternates.iterator();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[4]--;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[5]--;
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.loops[6]++;
}
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[34]++;
          boolean removeCurrent = false;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[35]++;
          JSType current = it.next();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;

          // Unknown and NoResolved types may just be names that haven't
          // been resolved yet. So keep these in the union, and just use
          // equality checking for simple de-duping.
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2048)) == 0 || true) &&
 ((alternate.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (512)) == 0 || true) &&
 ((current.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((alternate.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((current.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((alternate.hasAnyTemplateTypes()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((current.hasAnyTemplateTypes()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 6) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 6) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[15]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((alternate.isEquivalentTo(current)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[17]++;
              // Alternate is unnecessary.
              return this;

            } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[18]++;}

          } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[16]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;

            // Because "Foo" and "Foo.<?>" are roughly equivalent
            // templatized types, special care is needed when building the
            // union. For example:
            //   Object is consider a subtype of Object.<string>
            // but we want to leave "Object" not "Object.<string>" when
            // building the subtype.
            //

            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((alternate.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((current.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[19]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[39]++;
int CodeCoverConditionCoverageHelper_C12;
              // Cases:
              // 1) alternate:Array.<string> and current:Object ==> Object
              // 2) alternate:Array.<string> and current:Array ==> Array
              // 3) alternate:Object.<string> and
              //    current:Array ==> Array|Object.<string>
              // 4) alternate:Object and current:Array.<string> ==> Object
              // 5) alternate:Array and current:Array.<string> ==> Array
              // 6) alternate:Array and
              //    current:Object.<string> ==> Array|Object.<string>
              // 7) alternate:Array.<string> and
              //    current:Array.<number> ==> Array.<?>
              // 8) alternate:Array.<string> and
              //    current:Array.<string> ==> Array.<string>
              // 9) alternate:Array.<string> and
              //    current:Object.<string> ==> Object.<string>|Array.<string>

              if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((current.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[21]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((alternate.isSubtype(current)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[23]++;
                  // case 1, 2
                  return this;

                } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[24]++;}

                // case 3: leave current, add alternate
              } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[22]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[41]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((alternate.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[25]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((current.isSubtype(alternate)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[27]++;
                  // case 4, 5
                  removeCurrent = true;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[43]++;

                } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[28]++;}

                // case 6: leave current, add alternate
              } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[26]++;
                Preconditions.checkState(current.isTemplatizedType()
                    && alternate.isTemplatizedType());
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[44]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[45]++;
                TemplatizedType templatizedAlternate = alternate.toMaybeTemplatizedType();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[46]++;
                TemplatizedType templatizedCurrent = current.toMaybeTemplatizedType();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[47]++;
int CodeCoverConditionCoverageHelper_C16;

                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((templatizedCurrent.wrapsSameRawType(templatizedAlternate)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[29]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[48]++;
int CodeCoverConditionCoverageHelper_C17;
                  if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((alternate.getTemplateTypeMap().checkEquivalenceHelper(
                      current.getTemplateTypeMap(),
                      EquivalenceMethod.IDENTITY)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[31]++;
                    // case 8
                    return this;

                  } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[32]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[49]++;
                    // TODO(johnlenz): should we leave both types?
                    // case 7: add a merged alternate
                    // We currently merge to the templatized types to "unknown"
                    // which is equivalent to the raw type.
                    JSType merged = templatizedCurrent
                        .getReferencedObjTypeInternal();
                    return addAlternate(merged);
                  }

                } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[30]++;}
                // case 9: leave current, add alternate
              }
}

              // Otherwise leave both templatized types.
            } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[20]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[50]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((alternate.isSubtype(current)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[33]++;
              // Alternate is unnecessary.
              return this;

            } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[34]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[51]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((current.isSubtype(alternate)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[35]++;
              // Alternate makes current obsolete
              removeCurrent = true;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[52]++;

            } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[36]++;}
}
}
          }
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[53]++;
int CodeCoverConditionCoverageHelper_C20;

          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((removeCurrent) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[37]++;
            it.remove();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[54]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[55]++;
int CodeCoverConditionCoverageHelper_C21;

            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((currentIndex == functionTypePosition) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[39]++;
              functionTypePosition = -1;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[56]++;

            } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[40]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[57]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((currentIndex < functionTypePosition) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[41]++;
              functionTypePosition--;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[58]++;
              currentIndex--;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[59]++;

            } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[42]++;}
}

          } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[38]++;}
          currentIndex++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[60]++;
        }
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[61]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((alternate.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[43]++;
          // See the comments on functionTypePosition above.
          Preconditions.checkState(functionTypePosition == -1);
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[62]++;
          functionTypePosition = alternates.size();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[63]++;

        } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[44]++;}

        alternates.add(alternate);
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[64]++;
        result = null;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[65]++; // invalidate the memoized result
      }

    } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[8]++;
      result = null;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[66]++;
    }
    return this;
  }

  /**
   * Reduce the alternates into a non-union type.
   * If the alternates can't be accurately represented with a non-union
   * type, return null.
   */
  private JSType reduceAlternatesWithoutUnion() {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[67]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isAllType) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[45]++;
      return registry.getNativeType(ALL_TYPE);

    } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[46]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[68]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isNativeUnknownType) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[47]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[69]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((areAllUnknownsChecked) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[49]++;
        return registry.getNativeType(CHECKED_UNKNOWN_TYPE);

      } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[50]++;
        return registry.getNativeType(UNKNOWN_TYPE);
      }

    } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[48]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[70]++;
      int size = alternates.size();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[71]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((size > maxUnionSize) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[51]++;
        return registry.getNativeType(UNKNOWN_TYPE);

      } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[52]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[72]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((size > 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[53]++;
        return null;

      } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[54]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[73]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((size == 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[55]++;
        return alternates.iterator().next();

      } else {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[56]++;
        return registry.getNativeType(NO_TYPE);
      }
}
}
    }
}
  }

  /**
   * Creates a union.
   * @return A UnionType if it has two or more alternates, the
   *    only alternate if it has one and otherwise {@code NO_TYPE}.
   */
  JSType build() {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[74]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[57]++;
      result = reduceAlternatesWithoutUnion();
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[75]++;
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[76]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[59]++;
        result = new UnionType(registry, getAlternateListCopy());
CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.statements[77]++;

      } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[60]++;}

    } else {
  CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675.branches[58]++;}
    return result;
  }

  private Collection<JSType> getAlternateListCopy() {
    return ImmutableList.copyOf(alternates);
  }
}

class CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675 ());
  }
    public static long[] statements = new long[78];
    public static long[] branches = new long[61];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.UnionTypeBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,2,1,3,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$1yyphnm3qer9ovxeisit4225ocu1fg675 () {
    super("com.google.javascript.rhino.jstype.UnionTypeBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 77; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 60; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.UnionTypeBuilder.java");
      for (int i = 1; i <= 77; i++) {
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
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

