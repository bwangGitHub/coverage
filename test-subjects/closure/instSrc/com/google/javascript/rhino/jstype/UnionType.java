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

import static com.google.javascript.rhino.jstype.TernaryValue.UNKNOWN;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.javascript.rhino.ErrorReporter;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The {@code UnionType} implements a common JavaScript idiom in which the
 * code is specifically designed to work with multiple input types.  Because
 * JavaScript always knows the run-time type of an object value, this is safer
 * than a C union.<p>
 *
 * For instance, values of the union type {@code (String,boolean)} can be of
 * type {@code String} or of type {@code boolean}. The commutativity of the
 * statement is captured by making {@code (String,boolean)} and
 * {@code (boolean,String)} equal.<p>
 *
 * The implementation of this class prevents the creation of nested
 * unions.<p>
 */
public class UnionType extends JSType {
  static {
    CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[1]++;
  }

  Collection<JSType> alternates;
  private int hashcode;

  /**
   * Creates a union type.
   *
   * @param alternates the alternates of the union
   */
  UnionType(JSTypeRegistry registry, Collection<JSType> alternates) {
    super(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[2]++;
    this.alternates = alternates;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[3]++;
    this.hashcode = this.alternates.hashCode();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[4]++;
  }

  /**
   * Gets the alternate types of this union type.
   * @return The alternate types of this union type. The returned set is
   *     immutable.
   */
  public Collection<JSType> getAlternates() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[1]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[1]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[2]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[3]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((t.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[1]++;
        rebuildAlternates();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[7]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[8]++;
        break;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[2]++;}
    }
    return alternates;
  }

  /**
   * Use UnionTypeBuilder to rebuild the list of alternates and hashcode
   * of the current UnionType.
   */
  private void rebuildAlternates() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[9]++;
    UnionTypeBuilder builder = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[10]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[4]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[4]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[5]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[6]++;
}
      builder.addAlternate(alternate);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[11]++;
    }
    alternates = builder.getAlternates();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[12]++;
    hashcode = alternates.hashCode();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[13]++;
  }

  /**
   * This predicate is used to test whether a given type can appear in a
   * numeric context, such as an operand of a multiply operator.
   *
   * @return true if the type can appear in a numeric context.
   */
  @Override
  public boolean matchesNumberContext() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[14]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[7]++;


    // TODO(user): Reverse this logic to make it correct instead of generous.
    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[7]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[8]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[9]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((t.matchesNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[3]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[4]++;}
    }
    return false;
  }

  /**
   * This predicate is used to test whether a given type can appear in a
   * {@code String} context, such as an operand of a string concat ({@code +})
   * operator.<p>
   *
   * All types have at least the potential for converting to {@code String}.
   * When we add externally defined types, such as a browser OM, we may choose
   * to add types that do not automatically convert to {@code String}.
   *
   * @return {@code true} if not {@link VoidType}
   */
  @Override
  public boolean matchesStringContext() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[16]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[10]++;


    // TODO(user): Reverse this logic to make it correct instead of generous.
    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[10]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[11]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[12]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t.matchesStringContext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[5]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[6]++;}
    }
    return false;
  }

  /**
   * This predicate is used to test whether a given type can appear in an
   * {@code Object} context, such as the expression in a {@code with}
   * statement.<p>
   *
   * Most types we will encounter, except notably {@code null}, have at least
   * the potential for converting to {@code Object}.  Host defined objects can
   * get peculiar.<p>
   *
   * VOID type is included here because while it is not part of the JavaScript
   * language, functions returning 'void' type can't be used as operands of
   * any operator or statement.<p>
   *
   * @return {@code true} if the type is not {@link NullType} or
   *         {@link VoidType}
   */
  @Override
  public boolean matchesObjectContext() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[18]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[13]++;


    // TODO(user): Reverse this logic to make it correct instead of generous.
    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[13]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[14]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[15]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((t.matchesObjectContext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[7]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[8]++;}
    }
    return false;
  }

  @Override
  public JSType findPropertyType(String propertyName) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[20]++;
    JSType propertyType = null;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[21]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[16]++;



    for (JSType alternate : getAlternates()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[16]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[17]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[18]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
      // Filter out the null/undefined type.
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((alternate.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((alternate.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[9]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[23]++;
        continue;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[10]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[24]++;

      JSType altPropertyType = alternate.findPropertyType(propertyName);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((altPropertyType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[11]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[26]++;
        continue;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[12]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((propertyType == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[13]++;
        propertyType = altPropertyType;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[28]++;

      } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[14]++;
        propertyType = propertyType.getLeastSupertype(altPropertyType);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[29]++;
      }
    }

    return propertyType;
  }

  @Override
  public boolean canBeCalled() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[30]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[19]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[19]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[20]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[21]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((t.canBeCalled()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[15]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[16]++;}
    }
    return true;
  }

  @Override
  public JSType autobox() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[32]++;
    UnionTypeBuilder restricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[33]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[22]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[22]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[23]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[24]++;
}
      restricted.addAlternate(t.autobox());
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[34]++;
    }
    return restricted.build();
  }

  @Override
  public JSType restrictByNotNullOrUndefined() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[35]++;
    UnionTypeBuilder restricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[36]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[25]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[25]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[26]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[27]++;
}
      restricted.addAlternate(t.restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[37]++;
    }
    return restricted.build();
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[38]++;
    TernaryValue result = null;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[39]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[28]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[28]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[29]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[30]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[40]++;
      TernaryValue test = t.testForEquality(that);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[17]++;
        result = test;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[42]++;

      } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[18]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[43]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((result.equals(test)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[19]++;
        return UNKNOWN;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[20]++;}
}
    }
    return result;
  }

  /**
   * This predicate determines whether objects of this type can have the
   * {@code null} value, and therefore can appear in contexts where
   * {@code null} is expected.
   *
   * @return {@code true} for everything but {@code Number} and
   *         {@code Boolean} types.
   */
  @Override
  public boolean isNullable() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[44]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[31]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[31]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[32]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[33]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((t.isNullable()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[21]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[22]++;}
    }
    return false;
  }

  @Override
  public boolean isUnknownType() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[46]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[34]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[34]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[35]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[36]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((t.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[23]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[24]++;}
    }
    return false;
  }

  @Override
  public boolean isStruct() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[48]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[37]++;


    for (JSType typ : getAlternates()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[37]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[38]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[39]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((typ.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[25]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[26]++;}
    }
    return false;
  }

  @Override
  public boolean isDict() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[50]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[40]++;


    for (JSType typ : getAlternates()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[40]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[41]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[42]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((typ.isDict()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[27]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[28]++;}
    }
    return false;
  }

  @Override
  public JSType getLeastSupertype(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((that.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[29]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[53]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[43]++;


      for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[43]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[44]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[45]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[54]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((alternate.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((that.isSubtype(alternate)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[31]++;
          return this;

        } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[32]++;}
      }

    } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[30]++;}

    return getLeastSupertype(this, that);
  }

  JSType meet(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[55]++;
    UnionTypeBuilder builder = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[56]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[46]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[46]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[47]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[48]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[57]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((alternate.isSubtype(that)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[33]++;
        builder.addAlternate(alternate);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[58]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[34]++;}
    }
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[59]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[35]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[60]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[49]++;


      for (JSType otherAlternate : that.toMaybeUnionType().alternates) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[49]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[50]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[51]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[61]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((otherAlternate.isSubtype(this)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[37]++;
          builder.addAlternate(otherAlternate);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[62]++;

        } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[38]++;}
      }

    } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[36]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[63]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((that.isSubtype(this)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[39]++;
      builder.addAlternate(that);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[64]++;

    } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[40]++;}
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[65]++;
    JSType result = builder.build();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[66]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((result.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[41]++;
      return result;

    } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[42]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[67]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((this.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((that.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((that.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[43]++;
      return getNativeType(JSTypeNative.NO_OBJECT_TYPE);

    } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[44]++;
      return getNativeType(JSTypeNative.NO_TYPE);
    }
}
  }

  /**
   * Two union types are equal if, after flattening nested union types,
   * they have the same number of alternates and all alternates are equal.
   */
  boolean checkUnionEquivalenceHelper(
      UnionType that, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[68]++;
    Collection<JSType> thatAlternates = that.getAlternates();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[69]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((eqMethod == EquivalenceMethod.IDENTITY) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getAlternates().size() != thatAlternates.size()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[45]++;
      return false;

    } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[46]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[70]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[52]++;


    for (JSType alternate : thatAlternates) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[52]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[53]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[54]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[71]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((hasAlternate(alternate, eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[47]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[48]++;}
    }
    return true;
  }

  private boolean hasAlternate(JSType type, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[72]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[55]++;


    for (JSType alternate : getAlternates()) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[55]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[56]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[57]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[73]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((alternate.checkEquivalenceHelper(type, eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[49]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[50]++;}
    }
    return false;
  }

  @Override
  public boolean hasProperty(String pname) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[74]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[58]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[58]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[59]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[60]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[75]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((alternate.hasProperty(pname)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[51]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[52]++;}
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.hashcode;
  }

  @Override
  public UnionType toMaybeUnionType() {
    return this;
  }

  @Override
  public boolean isObject() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[76]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[61]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[61]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[62]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[63]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((alternate.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[53]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[54]++;}
    }
    return true;
  }

  /**
   * A {@link UnionType} contains a given type (alternate) iff the member
   * vector contains it.
   *
   * @param type The alternate which might be in this union.
   *
   * @return {@code true} if the alternate is in the union
   */
  public boolean contains(JSType type) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[78]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[64]++;


    for (JSType alt : alternates) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[64]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[65]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[66]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[79]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((alt.isEquivalentTo(type)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[55]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[56]++;}
    }
    return false;
  }

  /**
   * Returns a more restricted union type than {@code this} one, in which all
   * subtypes of {@code type} have been removed.<p>
   *
   * Examples:
   * <ul>
   * <li>{@code (number,string)} restricted by {@code number} is
   *     {@code string}</li>
   * <li>{@code (null, EvalError, URIError)} restricted by
   *     {@code Error} is {@code null}</li>
   * </ul>
   *
   * @param type the supertype of the types to remove from this union type
   */
  public JSType getRestrictedUnion(JSType type) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[80]++;
    UnionTypeBuilder restricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[81]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[67]++;


    for (JSType t : alternates) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[67]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[68]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[69]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[82]++;
int CodeCoverConditionCoverageHelper_C29;
      // Keep all unknown/unresolved types.
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((t.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((t.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((t.isSubtype(type)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[57]++;
        restricted.addAlternate(t);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[83]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[58]++;}
    }
    return restricted.build();
  }

  @Override String toStringHelper(boolean forAnnotations) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[84]++;
    StringBuilder result = new StringBuilder();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[85]++;
    boolean firstAlternate = true;

    result.append("(");
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[86]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[87]++;
    SortedSet<JSType> sorted = new TreeSet<JSType>(ALPHA);
    sorted.addAll(alternates);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[88]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[89]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[70]++;


    for (JSType t : sorted) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[70]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[71]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[72]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[90]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((firstAlternate) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[59]++;
        result.append("|");
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[91]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[60]++;}
      result.append(t.toStringHelper(forAnnotations));
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[92]++;
      firstAlternate = false;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[93]++;
    }
    result.append(")");
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[94]++;
    return result.toString();
  }

  @Override
  public boolean isSubtype(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[95]++;
int CodeCoverConditionCoverageHelper_C31;
    // unknown
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((that.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[61]++;
      return true;

    } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[62]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[96]++;
int CodeCoverConditionCoverageHelper_C32;
    // all type
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((that.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[63]++;
      return true;

    } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[64]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[97]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[73]++;


    for (JSType element : alternates) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[73]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[74]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[75]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[98]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((element.isSubtype(that)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[65]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[66]++;}
    }
    return true;
  }

  @Override
  public JSType getRestrictedTypeGivenToBooleanOutcome(boolean outcome) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[99]++;
    // gather elements after restriction
    UnionTypeBuilder restricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[100]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[76]++;


    for (JSType element : alternates) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[76]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[77]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[78]++;
}
      restricted.addAlternate(
          element.getRestrictedTypeGivenToBooleanOutcome(outcome));
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[101]++;
    }
    return restricted.build();
  }

  @Override
  public BooleanLiteralSet getPossibleToBooleanOutcomes() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[102]++;
    BooleanLiteralSet literals = BooleanLiteralSet.EMPTY;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[103]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[79]++;


    for (JSType element : alternates) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[79]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[80]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[81]++;
}
      literals = literals.union(element.getPossibleToBooleanOutcomes());
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[104]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[105]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((literals == BooleanLiteralSet.BOTH) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[67]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[106]++;
        break;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[68]++;}
    }
    return literals;
  }

  @Override
  public TypePair getTypesUnderEquality(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[107]++;
    UnionTypeBuilder thisRestricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[108]++;
    UnionTypeBuilder thatRestricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[109]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[82]++;


    for (JSType element : alternates) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[82]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[83]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[84]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[110]++;
      TypePair p = element.getTypesUnderEquality(that);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[111]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((p.typeA != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[69]++;
        thisRestricted.addAlternate(p.typeA);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[112]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[70]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[113]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((p.typeB != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[71]++;
        thatRestricted.addAlternate(p.typeB);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[114]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[72]++;}
    }
    return new TypePair(
        thisRestricted.build(),
        thatRestricted.build());
  }

  @Override
  public TypePair getTypesUnderInequality(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[115]++;
    UnionTypeBuilder thisRestricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[116]++;
    UnionTypeBuilder thatRestricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[117]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[85]++;


    for (JSType element : alternates) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[85]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[86]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[87]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[118]++;
      TypePair p = element.getTypesUnderInequality(that);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[119]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((p.typeA != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[73]++;
        thisRestricted.addAlternate(p.typeA);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[120]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[74]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[121]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((p.typeB != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[75]++;
        thatRestricted.addAlternate(p.typeB);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[122]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[76]++;}
    }
    return new TypePair(
        thisRestricted.build(),
        thatRestricted.build());
  }

  @Override
  public TypePair getTypesUnderShallowInequality(JSType that) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[123]++;
    UnionTypeBuilder thisRestricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[124]++;
    UnionTypeBuilder thatRestricted = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[125]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[88]++;


    for (JSType element : alternates) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[88]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[89]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[90]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[126]++;
      TypePair p = element.getTypesUnderShallowInequality(that);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[127]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((p.typeA != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[77]++;
        thisRestricted.addAlternate(p.typeA);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[128]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[78]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[129]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((p.typeB != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[79]++;
        thatRestricted.addAlternate(p.typeB);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[130]++;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[80]++;}
    }
    return new TypePair(
        thisRestricted.build(),
        thatRestricted.build());
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseUnionType(this);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseUnionType(this, that);
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    setResolvedTypeInternal(this);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[131]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[132]++; // for circularly defined types.

    boolean changed = false;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[133]++;
    ImmutableList.Builder<JSType> resolvedTypes = ImmutableList.builder();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[134]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[91]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[91]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[92]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[93]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[135]++;
      JSType newAlternate = alternate.resolve(t, scope);
      changed |= (alternate != newAlternate);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[136]++;
      resolvedTypes.add(alternate);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[137]++;
    }
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[138]++;
int CodeCoverConditionCoverageHelper_C41;
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[81]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[139]++;
      Collection<JSType> newAlternates = resolvedTypes.build();
      Preconditions.checkState(
          newAlternates.hashCode() == this.hashcode);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[140]++;
      alternates = newAlternates;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[141]++;

    } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[82]++;}
    return this;
  }

  @Override
  public String toDebugHashCodeString() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[142]++;
    List<String> hashCodes = Lists.newArrayList();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[143]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[94]++;


    for (JSType a : alternates) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[94]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[95]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[96]++;
}
      hashCodes.add(a.toDebugHashCodeString());
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[144]++;
    }
    return "{(" + Joiner.on(",").join(hashCodes) + ")}";
  }

  @Override
  public boolean setValidator(Predicate<JSType> validator) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[145]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[97]++;


    for (JSType a : alternates) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[97]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[98]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[99]++;
}
      a.setValidator(validator);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[146]++;
    }
    return true;
  }

  @Override
  public JSType collapseUnion() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[147]++;
    JSType currentValue = null;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[148]++;
    ObjectType currentCommonSuper = null;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[149]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[100]++;


    for (JSType a : alternates) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[100]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[101]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[102]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[150]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((a.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[83]++;
        return getNativeType(JSTypeNative.UNKNOWN_TYPE);

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[84]++;}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[151]++;

      ObjectType obj = a.toObjectType();
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[152]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[85]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[153]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((currentValue == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((currentCommonSuper == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[87]++;
          // If obj is not an object, then it must be a value.
          currentValue = a;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[154]++;

        } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[88]++;
          // Multiple values and objects will always collapse to the ALL_TYPE.
          return getNativeType(JSTypeNative.ALL_TYPE);
        }

      } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[86]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[155]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((currentValue != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[89]++;
        // Values and objects will always collapse to the ALL_TYPE.
        return getNativeType(JSTypeNative.ALL_TYPE);

      } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[90]++;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[156]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((currentCommonSuper == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[91]++;
        currentCommonSuper = obj;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[157]++;

      } else {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[92]++;
        currentCommonSuper =
            registry.findCommonSuperObject(currentCommonSuper, obj);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[158]++;
      }
}
}
    }
    return currentCommonSuper;
  }

  @Override
  public void matchConstraint(JSType constraint) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[159]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[103]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[103]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[104]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[105]++;
}
      alternate.matchConstraint(constraint);
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[160]++;
    }
  }

  @Override
  public boolean hasAnyTemplateTypesInternal() {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[161]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[106]++;


    for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[106]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[107]--;
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.loops[108]++;
}
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.statements[162]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((alternate.hasAnyTemplateTypes()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[93]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t.branches[94]++;}
    }
    return false;
  }
}

class CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t ());
  }
    public static long[] statements = new long[163];
    public static long[] branches = new long[95];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[48];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.UnionType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,3,2,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 47; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[109];

  public CodeCoverCoverageCounter$3lmh4tcojfj9aak14fya1t () {
    super("com.google.javascript.rhino.jstype.UnionType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 162; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 94; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 108; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.UnionType.java");
      for (int i = 1; i <= 162; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 94; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 36; i++) {
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

