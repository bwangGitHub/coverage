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
 *   Norris Boyd
 *   Roger Lawrence
 *   Mike McCabe
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

package com.google.javascript.rhino;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.SimpleSourceFile;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class implements the root of the intermediate representation.
 *
 */

public class Node implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.ping();
  }


  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[1]++;
  }

  public static final int
      JSDOC_INFO_PROP   = 29,     // contains a TokenStream.JSDocInfo object
      VAR_ARGS_NAME     = 30,     // the name node is a variable length
                                  // argument placeholder.
      INCRDECR_PROP      = 32,    // pre or post type of increment/decrement
      QUOTED_PROP        = 36,    // set to indicate a quoted object lit key
      OPT_ARG_NAME       = 37,    // The name node is an optional argument.
      SYNTHETIC_BLOCK_PROP = 38,  // A synthetic block. Used to make
                                  // processing simpler, and does not
                                  // represent a real block in the source.
      EMPTY_BLOCK        = 39,    // Used to indicate BLOCK that replaced
                                  // EMPTY nodes.
      ORIGINALNAME_PROP  = 40,    // The original name of the node, before
                                  // renaming.
      SIDE_EFFECT_FLAGS  = 42,    // Function or constructor call side effect
                                  // flags
      // Coding convention props
      IS_CONSTANT_NAME   = 43,    // The variable or property is constant.
      IS_NAMESPACE       = 46,    // The variable creates a namespace.
      IS_DISPATCHER      = 47,    // The function is a dispatcher function,
                                  // probably generated from Java code, and
                                  // should be resolved to the proper
                                  // overload if possible.
      DIRECTIVES         = 48,    // The ES5 directives on this node.
      DIRECT_EVAL        = 49,    // ES5 distinguishes between direct and
                                  // indirect calls to eval.
      FREE_CALL          = 50,    // A CALL without an explicit "this" value.
      STATIC_SOURCE_FILE = 51,    // A StaticSourceFile indicating the file
                                  // where this node lives.
      LENGTH             = 52,    // The length of the code represented by
                                  // this node.
      INPUT_ID           = 53,    // The id of the input associated with this
                                  // node.
      SLASH_V            = 54,    // Whether a STRING node contains a \v
                                  // vertical tab escape. This is a total hack.
                                  // See comments in IRFactory about this.
      INFERRED_FUNCTION  = 55,    // Marks a function whose parameter types
                                  // have been inferred.
      LAST_PROP          = 55;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[2]++;
  }

  public static final int   // flags for INCRDECR_PROP
      DECR_FLAG = 0x1,
      POST_FLAG = 0x2;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[3]++;
  }

  private static final String propToString(int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[4]++;
      switch (propType) {
        case VAR_ARGS_NAME:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[1]++;      return "var_args_name";

        case JSDOC_INFO_PROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[2]++;    return "jsdoc_info";

        case INCRDECR_PROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[3]++;      return "incrdecr";
        case QUOTED_PROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[4]++;        return "quoted";
        case OPT_ARG_NAME:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[5]++;       return "opt_arg";

        case SYNTHETIC_BLOCK_PROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[6]++; return "synthetic";
        case EMPTY_BLOCK:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[7]++;        return "empty_block";
        case ORIGINALNAME_PROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[8]++;  return "originalname";
        case SIDE_EFFECT_FLAGS:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[9]++;  return "side_effect_flags";

        case IS_CONSTANT_NAME:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[10]++;   return "is_constant_name";
        case IS_NAMESPACE:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[11]++;       return "is_namespace";
        case IS_DISPATCHER:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[12]++;      return "is_dispatcher";
        case DIRECTIVES:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[13]++;         return "directives";
        case DIRECT_EVAL:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[14]++;        return "direct_eval";
        case FREE_CALL:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[15]++;          return "free_call";
        case STATIC_SOURCE_FILE:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[16]++;    return "source_file";
        case INPUT_ID:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[17]++;  return "input_id";
        case LENGTH:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[18]++;    return "length";
        case SLASH_V:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[19]++;   return "slash_v";
        case INFERRED_FUNCTION:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[20]++;   return "inferred";
        default:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[21]++;
          throw new IllegalStateException("unexpect prop id " + propType);
      }
  }

  private static class NumberNode extends Node {

    private static final long serialVersionUID = 1L;

    NumberNode(double number) {
      super(Token.NUMBER);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[5]++;
      this.number = number;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[6]++;
    }

    public NumberNode(double number, int lineno, int charno) {
      super(Token.NUMBER, lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[7]++;
      this.number = number;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[8]++;
    }

    @Override
    public double getDouble() {
      return this.number;
    }

    @Override
    public void setDouble(double d) {
      this.number = d;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[9]++;
    }

    @Override
    boolean isEquivalentTo(Node node, boolean compareJsType, boolean recurse) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[10]++;
      boolean equivalent = super.isEquivalentTo(node, compareJsType, recurse);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((equivalent) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[22]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[12]++;
        double thisValue = getDouble();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[13]++;
        double thatValue = ((NumberNode) node).getDouble();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisValue == thatValue) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[24]++;
          // detect the difference between 0.0 and -0.0.
          return (thisValue != 0.0) || (1/thisValue == 1/thatValue);

        } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[25]++;}

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[23]++;}
      return false;
    }

    private double number;
  }

  private static class StringNode extends Node {

    private static final long serialVersionUID = 1L;

    StringNode(int type, String str) {
      super(type);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[15]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((null == str) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[26]++;
        throw new IllegalArgumentException("StringNode: str is null");

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[27]++;}
      this.str = str;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[17]++;
    }

    StringNode(int type, String str, int lineno, int charno) {
      super(type, lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[18]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((null == str) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[28]++;
        throw new IllegalArgumentException("StringNode: str is null");

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[29]++;}
      this.str = str;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[20]++;
    }

    /**
     * returns the string content.
     * @return non null.
     */
    @Override
    public String getString() {
      return this.str;
    }

    /**
     * sets the string content.
     * @param str the new value.  Non null.
     */
    @Override
    public void setString(String str) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((null == str) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[30]++;
        throw new IllegalArgumentException("StringNode: str is null");

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[31]++;}
      this.str = str;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[22]++;
    }

    @Override
    boolean isEquivalentTo(Node node, boolean compareJsType, boolean recurse) {
      return (super.isEquivalentTo(node, compareJsType, recurse)
          && this.str.equals(((StringNode) node).str));
    }

    /**
     * If the property is not defined, this was not a quoted key.  The
     * QUOTED_PROP int property is only assigned to STRING tokens used as
     * object lit keys.
     * @return true if this was a quoted string key in an object literal.
     */
    @Override
    public boolean isQuotedString() {
      return getBooleanProp(QUOTED_PROP);
    }

    /**
     * This should only be called for STRING nodes created in object lits.
     */
    @Override
    public void setQuotedString() {
      putBooleanProp(QUOTED_PROP, true);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[23]++;
    }

    private String str;
  }

  // PropListItems must be immutable so that they can be shared.
  private interface PropListItem {
    int getType();
    PropListItem getNext();
    PropListItem chain(PropListItem next);
    Object getObjectValue();
    int getIntValue();
  }

  private static abstract class AbstractPropListItem
      implements PropListItem, Serializable {
    private static final long serialVersionUID = 1L;

    private final PropListItem next;
    private final int propType;

    AbstractPropListItem(int propType, PropListItem next) {
      this.propType = propType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[24]++;
      this.next = next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[25]++;
    }

    @Override
    public int getType() {
      return propType;
    }

    @Override
    public PropListItem getNext() {
      return next;
    }

    @Override
    public abstract PropListItem chain(PropListItem next);
  }

  // A base class for Object storing props
  private static class ObjectPropListItem
      extends AbstractPropListItem {
    private static final long serialVersionUID = 1L;

    private final Object objectValue;

    ObjectPropListItem(int propType, Object objectValue, PropListItem next) {
      super(propType, next);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[26]++;
      this.objectValue = objectValue;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[27]++;
    }

    @Override
    public int getIntValue() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object getObjectValue() {
      return objectValue;
    }

    @Override
    public String toString() {
      return objectValue == null ? "null" : objectValue.toString();
    }

    @Override
    public PropListItem chain(PropListItem next) {
      return new ObjectPropListItem(getType(), objectValue, next);
    }
  }

  // A base class for int storing props
  private static class IntPropListItem extends AbstractPropListItem {
    private static final long serialVersionUID = 1L;

    final int intValue;

    IntPropListItem(int propType, int intValue, PropListItem next) {
      super(propType, next);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[28]++;
      this.intValue = intValue;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[29]++;
    }

    @Override
    public int getIntValue() {
      return intValue;
    }

    @Override
    public Object getObjectValue() {
      throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
      return String.valueOf(intValue);
    }

    @Override
    public PropListItem chain(PropListItem next) {
      return new IntPropListItem(getType(), intValue, next);
    }
  }

  public Node(int nodeType) {
    type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[30]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[31]++;
    sourcePosition = -1;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[32]++;
  }

  public Node(int nodeType, Node child) {
    Preconditions.checkArgument(child.parent == null,
        "new child has existing parent");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[33]++;
    Preconditions.checkArgument(child.next == null,
        "new child has existing sibling");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[34]++;

    type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[35]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[36]++;
    first = last = child;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[37]++;
    child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[38]++;
    child.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[39]++;
    sourcePosition = -1;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[40]++;
  }

  public Node(int nodeType, Node left, Node right) {
    Preconditions.checkArgument(left.parent == null,
        "first new child has existing parent");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[41]++;
    Preconditions.checkArgument(left.next == null,
        "first new child has existing sibling");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[42]++;
    Preconditions.checkArgument(right.parent == null,
        "second new child has existing parent");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[43]++;
    Preconditions.checkArgument(right.next == null,
        "second new child has existing sibling");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[44]++;
    type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[45]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[46]++;
    first = left;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[47]++;
    last = right;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[48]++;
    left.next = right;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[49]++;
    left.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[50]++;
    right.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[51]++;
    right.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[52]++;
    sourcePosition = -1;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[53]++;
  }

  public Node(int nodeType, Node left, Node mid, Node right) {
    Preconditions.checkArgument(left.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[54]++;
    Preconditions.checkArgument(left.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[55]++;
    Preconditions.checkArgument(mid.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[56]++;
    Preconditions.checkArgument(mid.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[57]++;
    Preconditions.checkArgument(right.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[58]++;
    Preconditions.checkArgument(right.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[59]++;
    type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[60]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[61]++;
    first = left;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[62]++;
    last = right;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[63]++;
    left.next = mid;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[64]++;
    left.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[65]++;
    mid.next = right;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[66]++;
    mid.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[67]++;
    right.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[68]++;
    right.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[69]++;
    sourcePosition = -1;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[70]++;
  }

  public Node(int nodeType, Node left, Node mid, Node mid2, Node right) {
    Preconditions.checkArgument(left.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[71]++;
    Preconditions.checkArgument(left.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[72]++;
    Preconditions.checkArgument(mid.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[73]++;
    Preconditions.checkArgument(mid.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[74]++;
    Preconditions.checkArgument(mid2.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[75]++;
    Preconditions.checkArgument(mid2.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[76]++;
    Preconditions.checkArgument(right.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[77]++;
    Preconditions.checkArgument(right.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[78]++;
    type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[79]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[80]++;
    first = left;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[81]++;
    last = right;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[82]++;
    left.next = mid;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[83]++;
    left.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[84]++;
    mid.next = mid2;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[85]++;
    mid.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[86]++;
    mid2.next = right;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[87]++;
    mid2.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[88]++;
    right.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[89]++;
    right.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[90]++;
    sourcePosition = -1;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[91]++;
  }

  public Node(int nodeType, int lineno, int charno) {
    type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[92]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[93]++;
    sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[94]++;
  }

  public Node(int nodeType, Node child, int lineno, int charno) {
    this(nodeType, child);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[95]++;
    sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[96]++;
  }

  public Node(int nodeType, Node left, Node right, int lineno, int charno) {
    this(nodeType, left, right);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[97]++;
    sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[98]++;
  }

  public Node(int nodeType, Node left, Node mid, Node right,
      int lineno, int charno) {
    this(nodeType, left, mid, right);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[99]++;
    sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[100]++;
  }

  public Node(int nodeType, Node left, Node mid, Node mid2, Node right,
      int lineno, int charno) {
    this(nodeType, left, mid, mid2, right);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[101]++;
    sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[102]++;
  }

  public Node(int nodeType, Node[] children, int lineno, int charno) {
    this(nodeType, children);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[103]++;
    sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[104]++;
  }

  public Node(int nodeType, Node[] children) {
    this.type = nodeType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[105]++;
    parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[106]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[107]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((children.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[32]++;
      this.first = children[0];
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[108]++;
      this.last = children[children.length - 1];
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[109]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[110]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;

      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < children.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[1]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[2]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[3]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[111]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((null != children[i - 1].next) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[34]++;
          // fail early on loops. implies same node in array twice
          throw new IllegalArgumentException("duplicate child");

        } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[35]++;}
        children[i - 1].next = children[i];
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[112]++;
        Preconditions.checkArgument(children[i - 1].parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[113]++;
        children[i - 1].parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[114]++;
      }
      Preconditions.checkArgument(children[children.length - 1].parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[115]++;
      children[children.length - 1].parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[116]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[117]++;
int CodeCoverConditionCoverageHelper_C9;

      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((null != this.last.next) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[36]++;
        // fail early on loops. implies same node in array twice
        throw new IllegalArgumentException("duplicate child");

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[37]++;}

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[33]++;}
  }

  public static Node newNumber(double number) {
    return new NumberNode(number);
  }

  public static Node newNumber(double number, int lineno, int charno) {
    return new NumberNode(number, lineno, charno);
  }

  public static Node newString(String str) {
    return new StringNode(Token.STRING, str);
  }

  public static Node newString(int type, String str) {
    return new StringNode(type, str);
  }

  public static Node newString(String str, int lineno, int charno) {
    return new StringNode(Token.STRING, str, lineno, charno);
  }

  public static Node newString(int type, String str, int lineno, int charno) {
    return new StringNode(type, str, lineno, charno);
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[118]++;
  }

  public boolean hasChildren() {
    return first != null;
  }

  public Node getFirstChild() {
    return first;
  }

  public Node getLastChild() {
    return last;
  }

  public Node getNext() {
    return next;
  }

  public Node getChildBefore(Node child) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[119]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((child == first) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[38]++;
      return null;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[39]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[120]++;
    Node n = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[121]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
    while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.next != child) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[4]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[5]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[6]++;
}
      n = n.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[122]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[123]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[40]++;
        throw new RuntimeException("node is not a child");

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[41]++;}
    }
    return n;
  }

  public Node getChildAtIndex(int i) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[124]++;
    Node n = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[125]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;
    while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[7]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[8]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[9]++;
}
      n = n.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[126]++;
      i--;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[127]++;
    }
    return n;
  }

  public int getIndexOfChild(Node child) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[128]++;
    Node n = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[129]++;
    int i = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[130]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[10]++;


int CodeCoverConditionCoverageHelper_C14;
    while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[10]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[11]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[12]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[131]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((child == n) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[42]++;
        return i;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[43]++;}

      n = n.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[132]++;
      i++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[133]++;
    }
    return -1;
  }

  public Node getLastSibling() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[134]++;
    Node n = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[135]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[13]++;


int CodeCoverConditionCoverageHelper_C16;
    while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n.next != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[13]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[14]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[15]++;
}
      n = n.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[136]++;
    }
    return n;
  }

  public void addChildToFront(Node child) {
    Preconditions.checkArgument(child.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[137]++;
    Preconditions.checkArgument(child.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[138]++;
    child.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[139]++;
    child.next = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[140]++;
    first = child;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[141]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[142]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[44]++;
      last = child;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[143]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[45]++;}
  }

  public void addChildToBack(Node child) {
    Preconditions.checkArgument(child.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[144]++;
    Preconditions.checkArgument(child.next == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[145]++;
    child.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[146]++;
    child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[147]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[148]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[46]++;
      first = last = child;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[149]++;
      return;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[47]++;}
    last.next = child;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[150]++;
    last = child;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[151]++;
  }

  public void addChildrenToFront(Node children) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[152]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[16]++;


int CodeCoverConditionCoverageHelper_C19;
    for (Node child = children;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); child = child.next) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[16]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[17]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[18]++;
}
      Preconditions.checkArgument(child.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[153]++;
      child.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[154]++;
    }
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[155]++;
    Node lastSib = children.getLastSibling();
    lastSib.next = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[156]++;
    first = children;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[157]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[158]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[48]++;
      last = lastSib;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[159]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[49]++;}
  }

  public void addChildrenToBack(Node children) {
    addChildrenAfter(children, getLastChild());
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[160]++;
  }

  /**
   * Add 'child' before 'node'.
   */
  public void addChildBefore(Node newChild, Node node) {
    Preconditions.checkArgument(node != null && node.parent == this,
        "The existing child node of the parent should not be null.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[161]++;
    Preconditions.checkArgument(newChild.next == null,
        "The new child node has siblings.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[162]++;
    Preconditions.checkArgument(newChild.parent == null,
        "The new child node already has a parent.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[163]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[164]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((first == node) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[50]++;
      newChild.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[165]++;
      newChild.next = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[166]++;
      first = newChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[167]++;
      return;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[51]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[168]++;
    Node prev = getChildBefore(node);
    addChildAfter(newChild, prev);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[169]++;
  }

  /**
   * Add 'child' after 'node'.
   */
  public void addChildAfter(Node newChild, Node node) {
    Preconditions.checkArgument(newChild.next == null,
        "The new child node has siblings.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[170]++;
    addChildrenAfter(newChild, node);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[171]++;
  }

  /**
   * Add all children after 'node'.
   */
  public void addChildrenAfter(Node children, Node node) {
    Preconditions.checkArgument(node == null || node.parent == this);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[172]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[173]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[19]++;


int CodeCoverConditionCoverageHelper_C22;
    for (Node child = children;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); child = child.next) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[19]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[20]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[21]++;
}
      Preconditions.checkArgument(child.parent == null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[174]++;
      child.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[175]++;
    }
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[176]++;

    Node lastSibling = children.getLastSibling();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[177]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[52]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[178]++;
      Node oldNext = node.next;
      node.next = children;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[179]++;
      lastSibling.next = oldNext;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[180]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[181]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((node == last) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[54]++;
        last = lastSibling;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[182]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[55]++;}

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[53]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[183]++;
int CodeCoverConditionCoverageHelper_C25;
      // Append to the beginning.
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((first != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[56]++;
        lastSibling.next = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[184]++;

      } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[57]++;
        last = lastSibling;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[185]++;
      }
      first = children;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[186]++;
    }
  }

  /**
   * Detach a child from its parent and siblings.
   */
  public void removeChild(Node child) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[187]++;
    Node prev = getChildBefore(child);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[188]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((prev == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[58]++;
        first = first.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[189]++;
}
    else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[59]++;
        prev.next = child.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[190]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[191]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[60]++; last = prev;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[192]++;
} else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[61]++;}
    child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[193]++;
    child.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[194]++;
  }

  /**
   * Detaches child from Node and replaces it with newChild.
   */
  public void replaceChild(Node child, Node newChild) {
    Preconditions.checkArgument(newChild.next == null,
        "The new child node has siblings.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[195]++;
    Preconditions.checkArgument(newChild.parent == null,
        "The new child node already has a parent.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[196]++;

    // Copy over important information.
    newChild.copyInformationFrom(child);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[197]++;

    newChild.next = child.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[198]++;
    newChild.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[199]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[200]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((child == first) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[62]++;
        first = newChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[201]++;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[63]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[202]++;
        Node prev = getChildBefore(child);
        prev.next = newChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[203]++;
    }
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[204]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[64]++;
        last = newChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[205]++;
} else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[65]++;}
    child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[206]++;
    child.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[207]++;
  }

  public void replaceChildAfter(Node prevChild, Node newChild) {
    Preconditions.checkArgument(prevChild.parent == this,
      "prev is not a child of this node.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[208]++;

    Preconditions.checkArgument(newChild.next == null,
        "The new child node has siblings.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[209]++;
    Preconditions.checkArgument(newChild.parent == null,
        "The new child node already has a parent.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[210]++;

    // Copy over important information.
    newChild.copyInformationFrom(prevChild);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[211]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[212]++;

    Node child = prevChild.next;
    newChild.next = child.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[213]++;
    newChild.parent = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[214]++;
    prevChild.next = newChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[215]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[216]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[66]++;
        last = newChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[217]++;
} else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[67]++;}
    child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[218]++;
    child.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[219]++;
  }

  @VisibleForTesting
  PropListItem lookupProperty(int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[220]++;
    PropListItem x = propListHead;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[221]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[22]++;


int CodeCoverConditionCoverageHelper_C31;
    while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((propType != x.getType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[22]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[23]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[24]++;
}
      x = x.getNext();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[222]++;
    }
    return x;
  }

  /**
   * Clone the properties from the provided node without copying
   * the property object.  The receiving node may not have any
   * existing properties.
   * @param other The node to clone properties from.
   * @return this node.
   */
  public Node clonePropsFrom(Node other) {
    Preconditions.checkState(this.propListHead == null,
        "Node has existing properties.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[223]++;
    this.propListHead = other.propListHead;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[224]++;
    return this;
  }

  public void removeProp(int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[225]++;
    PropListItem result = removeProp(propListHead, propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[226]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((result != propListHead) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[68]++;
      propListHead = result;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[227]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[69]++;}
  }

  /**
   * @param item The item to inspect
   * @param propType The property to look for
   * @return The replacement list if the property was removed, or
   *   'item' otherwise.
   */
  private PropListItem removeProp(PropListItem item, int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[228]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[70]++;
      return null;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[71]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[229]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((item.getType() == propType) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[72]++;
      return item.getNext();

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[73]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[230]++;
      PropListItem result = removeProp(item.getNext(), propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[231]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((result != item.getNext()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[74]++;
        return item.chain(result);

      } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[75]++;
        return item;
      }
    }
}
  }

  public Object getProp(int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[232]++;
    PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[233]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[76]++;
      return null;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[77]++;}
    return item.getObjectValue();
  }

  public boolean getBooleanProp(int propType) {
    return getIntProp(propType) != 0;
  }

  /**
   * Returns the integer value for the property, or 0 if the property
   * is not defined.
   */
  public int getIntProp(int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[234]++;
    PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[235]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[78]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[79]++;}
    return item.getIntValue();
  }

  public int getExistingIntProp(int propType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[236]++;
    PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[237]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[80]++;
      throw new IllegalStateException("missing prop: " + propType);

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[81]++;}
    return item.getIntValue();
  }

  public void putProp(int propType, Object value) {
    removeProp(propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[238]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[239]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[82]++;
      propListHead = createProp(propType, value, propListHead);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[240]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[83]++;}
  }

  public void putBooleanProp(int propType, boolean value) {
    putIntProp(propType, value ? 1 : 0);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[241]++;
  }

  public void putIntProp(int propType, int value) {
    removeProp(propType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[242]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[243]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((value != 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[84]++;
      propListHead = createProp(propType, value, propListHead);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[244]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[85]++;}
  }

  PropListItem createProp(int propType, Object value, PropListItem next) {
    return new ObjectPropListItem(propType, value, next);
  }

  PropListItem createProp(int propType, int value, PropListItem next) {
    return new IntPropListItem(propType, value, next);
  }

  // Gets all the property types, in sorted order.
  private int[] getSortedPropTypes() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[245]++;
    int count = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[246]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[25]++;


int CodeCoverConditionCoverageHelper_C41;
    for (PropListItem x = propListHead;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); x = x.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[25]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[26]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[27]++;
}
      count++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[247]++;
    }
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[248]++;

    int[] keys = new int[count];
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[249]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[28]++;


int CodeCoverConditionCoverageHelper_C42;
    for (PropListItem x = propListHead;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); x = x.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[28]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[29]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[30]++;
}
      count--;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[250]++;
      keys[count] = x.getType();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[251]++;
    }

    Arrays.sort(keys);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[252]++;
    return keys;
  }

  /** Can only be called when <tt>getType() == TokenStream.NUMBER</tt> */
  public double getDouble() throws UnsupportedOperationException {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[253]++;
int CodeCoverConditionCoverageHelper_C43;
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.getType() == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[86]++;
      throw new IllegalStateException(
          "Number node not created with Node.newNumber");

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[87]++;
      throw new UnsupportedOperationException(this + " is not a number node");
    }
  }

  /**
   * Can only be called when <tt>getType() == Token.NUMBER</tt>
   * @param value value to set.
   */
  public void setDouble(double value) throws UnsupportedOperationException {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[254]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.getType() == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[88]++;
      throw new IllegalStateException(
          "Number node not created with Node.newNumber");

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[89]++;
      throw new UnsupportedOperationException(this + " is not a string node");
    }
  }

  /** Can only be called when node has String context. */
  public String getString() throws UnsupportedOperationException {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[255]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[90]++;
      throw new IllegalStateException(
          "String node not created with Node.newString");

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[91]++;
      throw new UnsupportedOperationException(this + " is not a string node");
    }
  }

  /**
   * Can only be called for a Token.STRING or Token.NAME.
   * @param value the value to set.
   */
  public void setString(String value) throws UnsupportedOperationException {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[256]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((this.getType() == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[92]++;
      throw new IllegalStateException(
          "String node not created with Node.newString");

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[93]++;
      throw new UnsupportedOperationException(this + " is not a string node");
    }
  }

  @Override
  public String toString() {
    return toString(true, true, true);
  }

  public String toString(
      boolean printSource,
      boolean printAnnotations,
      boolean printType) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[257]++;
    StringBuilder sb = new StringBuilder();
    toString(sb, printSource, printAnnotations, printType);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[258]++;
    return sb.toString();
  }

  private void toString(
      StringBuilder sb,
      boolean printSource,
      boolean printAnnotations,
      boolean printType) {
    sb.append(Token.name(type));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[259]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[260]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this instanceof StringNode) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[94]++;
      sb.append(' ');
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[261]++;
      sb.append(getString());
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[262]++;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[95]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[263]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((type == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[96]++;
      sb.append(' ');
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[264]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[265]++;
int CodeCoverConditionCoverageHelper_C49;
      // In the case of JsDoc trees, the first child is often not a string
      // which causes exceptions to be thrown when calling toString or
      // toStringTree.
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((first.getType() != Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[98]++;
        sb.append("<invalid>");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[266]++;

      } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[99]++;
        sb.append(first.getString());
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[267]++;
      }

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[97]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[268]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[100]++;
      sb.append(' ');
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[269]++;
      sb.append(getDouble());
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[270]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[101]++;}
}
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[271]++;
int CodeCoverConditionCoverageHelper_C51;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((printSource) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[102]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[272]++;
      int lineno = getLineno();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[273]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((lineno != -1) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[104]++;
        sb.append(' ');
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[274]++;
        sb.append(lineno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[275]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[105]++;}

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[103]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[276]++;
int CodeCoverConditionCoverageHelper_C53;

    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((printAnnotations) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[106]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[277]++;
      int[] keys = getSortedPropTypes();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[278]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[31]++;


int CodeCoverConditionCoverageHelper_C54;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < keys.length) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[31]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[32]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[33]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[279]++;
        int type = keys[i];
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[280]++;
        PropListItem x = lookupProperty(type);
        sb.append(" [");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[281]++;
        sb.append(propToString(type));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[282]++;
        sb.append(": ");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[283]++;
        String value;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[284]++;
        switch (type) {
          default:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[108]++;
            value = x.toString();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[285]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[286]++;
            break;
        }
        sb.append(value);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[287]++;
        sb.append(']');
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[288]++;
      }

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[107]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[289]++;
int CodeCoverConditionCoverageHelper_C55;

    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((printType) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[109]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[290]++;
int CodeCoverConditionCoverageHelper_C56;
      if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((jsType != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[111]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[291]++;
        String jsTypeString = jsType.toString();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[292]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((jsTypeString != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[113]++;
          sb.append(" : ");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[293]++;
          sb.append(jsTypeString);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[294]++;

        } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[114]++;}

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[112]++;}

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[110]++;}
  }


  public String toStringTree() {
    return toStringTreeImpl();
  }

  private String toStringTreeImpl() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[295]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[296]++;
      StringBuilder s = new StringBuilder();
      appendStringTree(s);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[297]++;
      return s.toString();
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[116]++;
      throw new RuntimeException("Should not happen\n" + e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[115]++;
}
  }
  }

  public void appendStringTree(Appendable appendable) throws IOException {
    toStringTreeHelper(this, 0, appendable);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[298]++;
  }

  private static void toStringTreeHelper(Node n, int level, Appendable sb)
      throws IOException {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[299]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[34]++;


int CodeCoverConditionCoverageHelper_C58;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((i != level) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[34]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[35]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[36]++;
}
      sb.append("    ");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[300]++;
    }
    sb.append(n.toString());
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[301]++;
    sb.append('\n');
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[302]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[303]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[37]++;


int CodeCoverConditionCoverageHelper_C59;
    for (Node cursor = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((cursor != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false);
         cursor = cursor.getNext()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[37]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[38]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[39]++;
}
      toStringTreeHelper(cursor, level + 1, sb);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[304]++;
    }
  }

  int type;              // type of the node; Token.NAME for example
  Node next;             // next sibling
  private Node first;    // first element of a linked list of children
  private Node last;     // last element of a linked list of children

  /**
   * Linked list of properties. Since vast majority of nodes would have
   * no more then 2 properties, linked list saves memory and provides
   * fast lookup. If this does not holds, propListHead can be replaced
   * by UintMap.
   */
  private PropListItem propListHead;

  /**
   * COLUMN_BITS represents how many of the lower-order bits of
   * sourcePosition are reserved for storing the column number.
   * Bits above these store the line number.
   * This gives us decent position information for everything except
   * files already passed through a minimizer, where lines might
   * be longer than 4096 characters.
   */
  public static final int COLUMN_BITS = 12;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[305]++;
  }

  /**
   * MAX_COLUMN_NUMBER represents the maximum column number that can
   * be represented.  JSCompiler's modifications to Rhino cause all
   * tokens located beyond the maximum column to MAX_COLUMN_NUMBER.
   */
  public static final int MAX_COLUMN_NUMBER = (1 << COLUMN_BITS) - 1;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[306]++;
  }

  /**
   * COLUMN_MASK stores a value where bits storing the column number
   * are set, and bits storing the line are not set.  It's handy for
   * separating column number from line number.
   */
  public static final int COLUMN_MASK = MAX_COLUMN_NUMBER;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[307]++;
  }

  /**
   * Source position of this node. The position is encoded with the
   * column number in the low 12 bits of the integer, and the line
   * number in the rest.  Create some handy constants so we can change this
   * size if we want.
   */
  private int sourcePosition;

  private JSType jsType;

  private Node parent;

  //==========================================================================
  // Source position management

  public void setStaticSourceFile(StaticSourceFile file) {
    this.putProp(STATIC_SOURCE_FILE, file);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[308]++;
  }

  /** Sets the source file to a non-extern file of the given name. */
  public void setSourceFileForTesting(String name) {
    this.putProp(STATIC_SOURCE_FILE, new SimpleSourceFile(name, false));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[309]++;
  }

  public String getSourceFileName() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[310]++;
    StaticSourceFile file = getStaticSourceFile();
    return file == null ? null : file.getName();
  }

  /** Returns the source file associated with this input. May be null */
  public StaticSourceFile getStaticSourceFile() {
    return ((StaticSourceFile) this.getProp(STATIC_SOURCE_FILE));
  }

  /**
   * @param inputId
   */
  public void setInputId(InputId inputId) {
    this.putProp(INPUT_ID, inputId);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[311]++;
  }

  /**
   * @return The Id of the CompilerInput associated with this Node.
   */
  public InputId getInputId() {
    return ((InputId) this.getProp(INPUT_ID));
  }

  public boolean isFromExterns() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[312]++;
    StaticSourceFile file = getStaticSourceFile();
    return file == null ? false : file.isExtern();
  }

  public int getLength() {
    return getIntProp(LENGTH);
  }

  public void setLength(int length) {
    putIntProp(LENGTH, length);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[313]++;
  }

  public int getLineno() {
    return extractLineno(sourcePosition);
  }

  public int getCharno() {
    return extractCharno(sourcePosition);
  }

  public int getSourceOffset() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[314]++;
    StaticSourceFile file = getStaticSourceFile();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[315]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((file == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[117]++;
      return -1;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[118]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[316]++;
    int lineno = getLineno();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[317]++;
int CodeCoverConditionCoverageHelper_C61;
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((lineno == -1) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[119]++;
      return -1;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[120]++;}
    return file.getLineOffset(lineno) + getCharno();
  }

  public int getSourcePosition() {
    return sourcePosition;
  }

  public void setLineno(int lineno) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[318]++;
      int charno = getCharno();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[319]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[121]++;
        charno = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[320]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[122]++;}
      sourcePosition = mergeLineCharNo(lineno, charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[321]++;
  }

  public void setCharno(int charno) {
      sourcePosition = mergeLineCharNo(getLineno(), charno);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[322]++;
  }

  public void setSourceEncodedPosition(int sourcePosition) {
    this.sourcePosition = sourcePosition;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[323]++;
  }

  public void setSourceEncodedPositionForTree(int sourcePosition) {
    this.sourcePosition = sourcePosition;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[324]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[325]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[40]++;


int CodeCoverConditionCoverageHelper_C63;

    for (Node child = getFirstChild();(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[40]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[41]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[42]++;
}
      child.setSourceEncodedPositionForTree(sourcePosition);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[326]++;
    }
  }

  /**
   * Merges the line number and character number in one integer. The Character
   * number takes the first 12 bits and the line number takes the rest. If
   * the character number is greater than <code>2<sup>12</sup>-1</code> it is
   * adjusted to <code>2<sup>12</sup>-1</code>.
   */
  protected static int mergeLineCharNo(int lineno, int charno) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[327]++;
int CodeCoverConditionCoverageHelper_C64;
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((lineno < 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((charno < 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[123]++;
      return -1;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[124]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[328]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 (((charno & ~COLUMN_MASK) != 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[125]++;
      return lineno << COLUMN_BITS | COLUMN_MASK;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[126]++;
      return lineno << COLUMN_BITS | (charno & COLUMN_MASK);
    }
}
  }

  /**
   * Extracts the line number and character number from a merged line char
   * number (see {@link #mergeLineCharNo(int, int)}).
   */
  protected static int extractLineno(int lineCharNo) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[329]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((lineCharNo == -1) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[127]++;
      return -1;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[128]++;
      return lineCharNo >>> COLUMN_BITS;
    }
  }

  /**
   * Extracts the character number and character number from a merged line
   * char number (see {@link #mergeLineCharNo(int, int)}).
   */
  protected static int extractCharno(int lineCharNo) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[330]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((lineCharNo == -1) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[129]++;
      return -1;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[130]++;
      return lineCharNo & COLUMN_MASK;
    }
  }

  //==========================================================================
  // Iteration

  /**
   * <p>Return an iterable object that iterates over this node's children.
   * The iterator does not support the optional operation
   * {@link Iterator#remove()}.</p>
   *
   * <p>To iterate over a node's siblings, one can write</p>
   * <pre>Node n = ...;
   * for (Node child : n.children()) { ...</pre>
   */
  public Iterable<Node> children() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[331]++;
int CodeCoverConditionCoverageHelper_C68;
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[131]++;
      return Collections.emptySet();

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[132]++;
      return new SiblingNodeIterable(first);
    }
  }

  /**
   * <p>Return an iterable object that iterates over this node's siblings.
   * The iterator does not support the optional operation
   * {@link Iterator#remove()}.</p>
   *
   * <p>To iterate over a node's siblings, one can write</p>
   * <pre>Node n = ...;
   * for (Node sibling : n.siblings()) { ...</pre>
   */
  public Iterable<Node> siblings() {
    return new SiblingNodeIterable(this);
  }

  /**
   * @see Node#siblings()
   */
  private static final class SiblingNodeIterable
      implements Iterable<Node>, Iterator<Node> {
    private final Node start;
    private Node current;
    private boolean used;

    SiblingNodeIterable(Node start) {
      this.start = start;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[332]++;
      this.current = start;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[333]++;
      this.used = false;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[334]++;
    }

    @Override
    public Iterator<Node> iterator() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[335]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((used) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[133]++;
        used = true;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[336]++;
        return this;

      } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[134]++;
        // We have already used the current object as an iterator;
        // we must create a new SiblingNodeIterable based on this
        // iterable's start node.
        //
        // Since the primary use case for Node.children is in for
        // loops, this branch is extremely unlikely.
        return (new SiblingNodeIterable(start)).iterator();
      }
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Node next() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[337]++;
int CodeCoverConditionCoverageHelper_C70;
      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((current == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[135]++;
        throw new NoSuchElementException();

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[136]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[338]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
      try {
CodeCoverTryBranchHelper_Try2 = true;
        return current;
      } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[137]++;
}
        current = current.getNext();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[339]++;
      }
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  // ==========================================================================
  // Accessors

  PropListItem getPropListHeadForTesting() {
    return propListHead;
  }

  public Node getParent() {
    return parent;
  }

  /**
   * Gets the ancestor node relative to this.
   *
   * @param level 0 = this, 1 = the parent, etc.
   */
  public Node getAncestor(int level) {
    Preconditions.checkArgument(level >= 0);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[340]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[341]++;
    Node node = this;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[342]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[43]++;


int CodeCoverConditionCoverageHelper_C71;
    while ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((level-- > 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[43]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[44]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[45]++;
}
      node = node.getParent();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[343]++;
    }
    return node;
  }

  /**
   * Iterates all of the node's ancestors excluding itself.
   */
  public AncestorIterable getAncestors() {
    return new AncestorIterable(this.getParent());
  }

  /**
   * Iterator to go up the ancestor tree.
   */
  public static class AncestorIterable implements Iterable<Node> {
    private Node cur;

    /**
     * @param cur The node to start.
     */
    AncestorIterable(Node cur) {
      this.cur = cur;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[344]++;
    }

    @Override
    public Iterator<Node> iterator() {
      return new Iterator<Node>() {
        @Override
        public boolean hasNext() {
          return cur != null;
        }

        @Override
        public Node next() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[345]++;
int CodeCoverConditionCoverageHelper_C72;
          if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[138]++; throw new NoSuchElementException();
} else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[139]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[346]++;
          Node n = cur;
          cur = cur.getParent();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[347]++;
          return n;
        }

        @Override
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
    }
  }

  /**
   * Check for one child more efficiently than by iterating over all the
   * children as is done with Node.getChildCount().
   *
   * @return Whether the node has exactly one child.
   */
  public boolean hasOneChild() {
    return first != null && first == last;
  }

  /**
   * Check for more than one child more efficiently than by iterating over all
   * the children as is done with Node.getChildCount().
   *
   * @return Whether the node more than one child.
   */
  public boolean hasMoreThanOneChild() {
    return first != null && first != last;
  }

  public int getChildCount() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[348]++;
    int c = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[349]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[46]++;


int CodeCoverConditionCoverageHelper_C73;
    for (Node n = first;(((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false); n = n.next) { 
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[46]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[47]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[48]++;
}
      c++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[350]++;
  }

    return c;
  }

  // Intended for testing and verification only.
  public boolean hasChild(Node child) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[351]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[49]++;


int CodeCoverConditionCoverageHelper_C74;
    for (Node n = first;(((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[49]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[50]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[51]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[352]++;
int CodeCoverConditionCoverageHelper_C75;
      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((child == n) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[140]++;
        return true;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[141]++;}
    }
    return false;
  }

  /**
   * Checks if the subtree under this node is the same as another subtree.
   * Returns null if it's equal, or a message describing the differences.
   */
  public String checkTreeEquals(Node node2) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[353]++;
      NodeMismatch diff = checkTreeEqualsImpl(node2);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[354]++;
int CodeCoverConditionCoverageHelper_C76;
      if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((diff != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[142]++;
        return "Node tree inequality:" +
            "\nTree1:\n" + toStringTree() +
            "\n\nTree2:\n" + node2.toStringTree() +
            "\n\nSubtree1: " + diff.nodeA.toStringTree() +
            "\n\nSubtree2: " + diff.nodeB.toStringTree();

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[143]++;}
      return null;
  }

  /**
   * Compare this node to node2 recursively and return the first pair of nodes
   * that differs doing a preorder depth-first traversal. Package private for
   * testing. Returns null if the nodes are equivalent.
   */
  NodeMismatch checkTreeEqualsImpl(Node node2) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[355]++;
int CodeCoverConditionCoverageHelper_C77;
    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((isEquivalentTo(node2, false, false)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[144]++;
      return new NodeMismatch(this, node2);

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[145]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[356]++;

    NodeMismatch res = null;
    Node n, n2;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[357]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[52]++;


int CodeCoverConditionCoverageHelper_C78;
    for (n = first, n2 = node2.first;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((res == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false);
         n = n.next, n2 = n2.next) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[52]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[53]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[54]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[358]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((node2 == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[146]++;
        throw new IllegalStateException();

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[147]++;}
      res = n.checkTreeEqualsImpl(n2);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[359]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[360]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((res != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[148]++;
        return res;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[149]++;}
    }
    return res;
  }

  /**
   * Compare this node to node2 recursively and return the first pair of nodes
   * that differs doing a preorder depth-first traversal. Package private for
   * testing. Returns null if the nodes are equivalent.
   */
  NodeMismatch checkTreeTypeAwareEqualsImpl(Node node2) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[361]++;
int CodeCoverConditionCoverageHelper_C81;
    // Do a non-recursive equivalents check.
    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((isEquivalentTo(node2, true, false)) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[150]++;
      return new NodeMismatch(this, node2);

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[151]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[362]++;

    NodeMismatch res = null;
    Node n, n2;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[363]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[55]++;


int CodeCoverConditionCoverageHelper_C82;
    for (n = first, n2 = node2.first;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((res == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false);
         n = n.next, n2 = n2.next) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[55]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[56]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[57]++;
}
      res = n.checkTreeTypeAwareEqualsImpl(n2);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[364]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[365]++;
int CodeCoverConditionCoverageHelper_C83;
      if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((res != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[152]++;
        return res;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[153]++;}
    }
    return res;
  }

  /** Returns true if this node is equivalent semantically to another */
  public boolean isEquivalentTo(Node node) {
    return isEquivalentTo(node, false, true);
  }

  /**
   * Returns true if this node is equivalent semantically to another and
   * the types are equivalent.
   */
  public boolean isEquivalentToTyped(Node node) {
    return isEquivalentTo(node, true, true);
  }

  /**
   * @param compareJsType Whether to compare the JSTypes of the nodes.
   * @param recurse Whether to compare the children of the current node, if
   *    not only the the count of the children are compared.
   * @return Whether this node is equivalent semantically to the provided node.
   */
  boolean isEquivalentTo(Node node, boolean compareJsType, boolean recurse) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[366]++;
int CodeCoverConditionCoverageHelper_C84;
    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (32)) == 0 || true) &&
 ((type != node.getType()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((getChildCount() != node.getChildCount()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((this.getClass() != node.getClass()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 3) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 3) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[154]++;
      return false;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[155]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[367]++;
int CodeCoverConditionCoverageHelper_C85;

    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((compareJsType) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((JSType.isEquivalent(jsType, node.getJSType())) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[156]++;
      return false;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[157]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[368]++;
int CodeCoverConditionCoverageHelper_C86;

    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((type == Token.INC) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((type == Token.DEC) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[158]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[369]++;
      int post1 = this.getIntProp(INCRDECR_PROP);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[370]++;
      int post2 = node.getIntProp(INCRDECR_PROP);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[371]++;
int CodeCoverConditionCoverageHelper_C87;
      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((post1 != post2) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[160]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[161]++;}

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[159]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[372]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((type == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((type == Token.STRING_KEY) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[162]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[373]++;
int CodeCoverConditionCoverageHelper_C89;
      if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((type == Token.STRING_KEY) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[164]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[374]++;
        int quoted1 = this.getIntProp(QUOTED_PROP);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[375]++;
        int quoted2 = node.getIntProp(QUOTED_PROP);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[376]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((quoted1 != quoted2) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[166]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[167]++;}

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[165]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[377]++;

      int slashV1 = this.getIntProp(SLASH_V);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[378]++;
      int slashV2 = node.getIntProp(SLASH_V);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[379]++;
int CodeCoverConditionCoverageHelper_C91;
      if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((slashV1 != slashV2) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[168]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[169]++;}

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[163]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[380]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((type == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[170]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[381]++;
int CodeCoverConditionCoverageHelper_C93;
      if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((this.getBooleanProp(FREE_CALL) != node.getBooleanProp(FREE_CALL)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[172]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[173]++;}

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[171]++;}
}
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[382]++;
int CodeCoverConditionCoverageHelper_C94;

    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((recurse) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[174]++;
      Node n, n2;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[383]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[58]++;


int CodeCoverConditionCoverageHelper_C95;
      for (n = first, n2 = node.first;(((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false);
           n = n.next, n2 = n2.next) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[58]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[59]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[60]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[384]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((n.isEquivalentTo(n2, compareJsType, true)) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[176]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[177]++;}
      }

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[175]++;}

    return true;
  }

  /**
   * This function takes a set of GETPROP nodes and produces a string that is
   * each property separated by dots. If the node ultimately under the left
   * sub-tree is not a simple name, this is not a valid qualified name.
   *
   * @return a null if this is not a qualified name, or a dot-separated string
   *         of the name and properties.
   */
  public String getQualifiedName() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[385]++;
int CodeCoverConditionCoverageHelper_C97;
    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((type == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[178]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[386]++;
      String name = getString();
      return name.isEmpty() ? null : name;

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[179]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[387]++;
int CodeCoverConditionCoverageHelper_C98; if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((type == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[180]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[388]++;
      String left = getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[389]++;
int CodeCoverConditionCoverageHelper_C99;
      if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[182]++;
        return null;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[183]++;}
      return left + "." + getLastChild().getString();

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[181]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[390]++;
int CodeCoverConditionCoverageHelper_C100; if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((type == Token.THIS) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[184]++;
      return "this";

    } else {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[185]++;
      return null;
    }
}
}
  }

  /**
   * Returns whether a node corresponds to a simple or a qualified name, such as
   * <code>x</code> or <code>a.b.c</code> or <code>this.a</code>.
   */
  public boolean isQualifiedName() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[391]++;
    switch (getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[186]++;
        return getString().isEmpty() ? false : true;
      case Token.THIS:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[187]++;
        return true;
      case Token.GETPROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[188]++;
        return getFirstChild().isQualifiedName();
      default:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[189]++;
        return false;
    }
  }

  /**
   * Returns whether a node corresponds to a simple or a qualified name without
   * a "this" reference, such as <code>a.b.c</code>, but not <code>this.a</code>
   * .
   */
  public boolean isUnscopedQualifiedName() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[392]++;
    switch (getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[190]++;
        return getString().isEmpty() ? false : true;
      case Token.GETPROP:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[191]++;
        return getFirstChild().isUnscopedQualifiedName();
      default:
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[192]++;
        return false;
    }
  }

  // ==========================================================================
  // Mutators

  /**
   * Removes this node from its parent. Equivalent to:
   * node.getParent().removeChild();
   */
  public Node detachFromParent() {
    Preconditions.checkState(parent != null);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[393]++;
    parent.removeChild(this);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[394]++;
    return this;
  }

  /**
   * Removes the first child of Node. Equivalent to:
   * node.removeChild(node.getFirstChild());
   *
   * @return The removed Node.
   */
  public Node removeFirstChild() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[395]++;
    Node child = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[396]++;
int CodeCoverConditionCoverageHelper_C101;
    if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[193]++;
      removeChild(child);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[397]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[194]++;}
    return child;
  }

  /**
   * @return A Node that is the head of the list of children.
   */
  public Node removeChildren() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[398]++;
    Node children = first;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[399]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[61]++;


int CodeCoverConditionCoverageHelper_C102;
    for (Node child = first;(((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[61]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[62]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[63]++;
}
      child.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[400]++;
    }
    first = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[401]++;
    last = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[402]++;
    return children;
  }

  /**
   * Removes all children from this node and isolates the children from each
   * other.
   */
  public void detachChildren() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[403]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[64]++;


int CodeCoverConditionCoverageHelper_C103;
    for (Node child = first;(((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false);) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[64]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[65]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[66]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[404]++;
      Node nextChild = child.getNext();
      child.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[405]++;
      child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[406]++;
      child = nextChild;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[407]++;
    }
    first = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[408]++;
    last = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[409]++;
  }

  public Node removeChildAfter(Node prev) {
    Preconditions.checkArgument(prev.parent == this,
        "prev is not a child of this node.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[410]++;
    Preconditions.checkArgument(prev.next != null,
        "no next sibling.");
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[411]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[412]++;

    Node child = prev.next;
    prev.next = child.next;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[413]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[414]++;
int CodeCoverConditionCoverageHelper_C104;
    if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[195]++; last = prev;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[415]++;
} else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[196]++;}
    child.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[416]++;
    child.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[417]++;
    return child;
  }

  /**
   * @return A detached clone of the Node, specifically excluding its children.
   */
  public Node cloneNode() {
    Node result;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[418]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
    try {
CodeCoverTryBranchHelper_Try3 = true;
      result = (Node) super.clone();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[419]++;
      // PropListItem lists are immutable and can be shared so there is no
      // need to clone them here.
      result.next = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[420]++;
      result.first = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[421]++;
      result.last = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[422]++;
      result.parent = null;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[423]++;
    } catch (CloneNotSupportedException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[198]++;
      throw new RuntimeException(e.getMessage());
    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[197]++;
}
  }
    return result;
  }

  /**
   * @return A detached clone of the Node and all its children.
   */
  public Node cloneTree() {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[424]++;
    Node result = cloneNode();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[425]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[67]++;


int CodeCoverConditionCoverageHelper_C105;
    for (Node n2 = getFirstChild();(((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((n2 != null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false); n2 = n2.getNext()) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[67]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[68]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[69]++;
}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[426]++;
      Node n2clone = n2.cloneTree();
      n2clone.parent = result;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[427]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[428]++;
int CodeCoverConditionCoverageHelper_C106;
      if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((result.last != null) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[199]++;
        result.last.next = n2clone;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[429]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[200]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[430]++;
int CodeCoverConditionCoverageHelper_C107;
      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((result.first == null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[201]++;
        result.first = n2clone;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[431]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[202]++;}
      result.last = n2clone;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[432]++;
    }
    return result;
  }

  /**
   * Copies source file and name information from the other
   * node given to the current node. Used for maintaining
   * debug information across node append and remove operations.
   * @return this
   */
  // TODO(nicksantos): The semantics of this method are ill-defined. Delete it.
  public Node copyInformationFrom(Node other) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[433]++;
int CodeCoverConditionCoverageHelper_C108;
    if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((getProp(ORIGINALNAME_PROP) == null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[203]++;
      putProp(ORIGINALNAME_PROP, other.getProp(ORIGINALNAME_PROP));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[434]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[204]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[435]++;
int CodeCoverConditionCoverageHelper_C109;

    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((getProp(STATIC_SOURCE_FILE) == null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[205]++;
      putProp(STATIC_SOURCE_FILE, other.getProp(STATIC_SOURCE_FILE));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[436]++;
      sourcePosition = other.sourcePosition;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[437]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[206]++;}

    return this;
  }

  /**
   * Copies source file and name information from the other node to the
   * entire tree rooted at this node.
   * @return this
   */
  // TODO(nicksantos): The semantics of this method are ill-defined. Delete it.
  public Node copyInformationFromForTree(Node other) {
    copyInformationFrom(other);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[438]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[439]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[70]++;


int CodeCoverConditionCoverageHelper_C110;
    for (Node child = getFirstChild();(((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[70]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[71]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[72]++;
}
      child.copyInformationFromForTree(other);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[440]++;
    }

    return this;
  }

  /**
   * Overwrite all the source information in this node with
   * that of {@code other}.
   */
  public Node useSourceInfoFrom(Node other) {
    putProp(ORIGINALNAME_PROP, other.getProp(ORIGINALNAME_PROP));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[441]++;
    putProp(STATIC_SOURCE_FILE, other.getProp(STATIC_SOURCE_FILE));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[442]++;
    sourcePosition = other.sourcePosition;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[443]++;
    return this;
  }

  public Node srcref(Node other) {
    return useSourceInfoFrom(other);
  }

  /**
   * Overwrite all the source information in this node and its subtree with
   * that of {@code other}.
   */
  public Node useSourceInfoFromForTree(Node other) {
    useSourceInfoFrom(other);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[444]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[445]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[73]++;


int CodeCoverConditionCoverageHelper_C111;
    for (Node child = getFirstChild();(((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[73]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[74]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[75]++;
}
      child.useSourceInfoFromForTree(other);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[446]++;
    }

    return this;
  }

  public Node srcrefTree(Node other) {
    return useSourceInfoFromForTree(other);
  }

  /**
   * Overwrite all the source information in this node with
   * that of {@code other} iff the source info is missing.
   */
  public Node useSourceInfoIfMissingFrom(Node other) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[447]++;
int CodeCoverConditionCoverageHelper_C112;
    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((getProp(ORIGINALNAME_PROP) == null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[207]++;
      putProp(ORIGINALNAME_PROP, other.getProp(ORIGINALNAME_PROP));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[448]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[208]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[449]++;
int CodeCoverConditionCoverageHelper_C113;

    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((getProp(STATIC_SOURCE_FILE) == null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[209]++;
      putProp(STATIC_SOURCE_FILE, other.getProp(STATIC_SOURCE_FILE));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[450]++;
      sourcePosition = other.sourcePosition;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[451]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[210]++;}

    return this;
  }

  /**
   * Overwrite all the source information in this node and its subtree with
   * that of {@code other} iff the source info is missing.
   */
  public Node useSourceInfoIfMissingFromForTree(Node other) {
    useSourceInfoIfMissingFrom(other);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[452]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[453]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[76]++;


int CodeCoverConditionCoverageHelper_C114;
    for (Node child = getFirstChild();(((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[76]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[77]--;
  CodeCoverCoverageCounter$8hcnruuor2mcf5.loops[78]++;
}
      child.useSourceInfoIfMissingFromForTree(other);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[454]++;
    }

    return this;
  }

  //==========================================================================
  // Custom annotations

  public JSType getJSType() {
      return jsType;
  }

  public void setJSType(JSType jsType) {
      this.jsType = jsType;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[455]++;
  }

  public FileLevelJsDocBuilder getJsDocBuilderForNode() {
    return new FileLevelJsDocBuilder();
  }

  /**
   * An inner class that provides back-door access to the license
   * property of the JSDocInfo property for this node. This is only
   * meant to be used for top-level script nodes where the
   * {@link com.google.javascript.jscomp.parsing.JsDocInfoParser} needs to
   * be able to append directly to the top-level node, not just the
   * current node.
   */
  public class FileLevelJsDocBuilder {
    public void append(String fileLevelComment) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[456]++;
      JSDocInfo jsDocInfo = getJSDocInfo();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[457]++;
int CodeCoverConditionCoverageHelper_C115;
      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((jsDocInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[211]++;
        // TODO(user): Is there a way to determine whether to
        // parse the JsDoc documentation from here?
        jsDocInfo = new JSDocInfo(false);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[458]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[212]++;}
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[459]++;
      String license = jsDocInfo.getLicense();
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[460]++;
int CodeCoverConditionCoverageHelper_C116;
      if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((license == null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[213]++;
        license = "";
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[461]++;

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[214]++;}
      jsDocInfo.setLicense(license + fileLevelComment);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[462]++;
      setJSDocInfo(jsDocInfo);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[463]++;
    }
  }

  /**
   * Get the {@link JSDocInfo} attached to this node.
   * @return the information or {@code null} if no JSDoc is attached to this
   * node
   */
  public JSDocInfo getJSDocInfo() {
    return (JSDocInfo) getProp(JSDOC_INFO_PROP);
  }

  /**
   * Sets the {@link JSDocInfo} attached to this node.
   */
  public Node setJSDocInfo(JSDocInfo info) {
      putProp(JSDOC_INFO_PROP, info);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[464]++;
      return this;
  }

  /**
   * Sets whether this node is a variable length argument node. This
   * method is meaningful only on {@link Token#NAME} nodes
   * used to define a {@link Token#FUNCTION}'s argument list.
   */
  public void setVarArgs(boolean varArgs) {
    putBooleanProp(VAR_ARGS_NAME, varArgs);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[465]++;
  }

  /**
   * Returns whether this node is a variable length argument node. This
   * method's return value is meaningful only on {@link Token#NAME} nodes
   * used to define a {@link Token#FUNCTION}'s argument list.
   */
  public boolean isVarArgs() {
    return getBooleanProp(VAR_ARGS_NAME);
  }

  /**
   * Sets whether this node is an optional argument node. This
   * method is meaningful only on {@link Token#NAME} nodes
   * used to define a {@link Token#FUNCTION}'s argument list.
   */
  public void setOptionalArg(boolean optionalArg) {
    putBooleanProp(OPT_ARG_NAME, optionalArg);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[466]++;
  }

  /**
   * Returns whether this node is an optional argument node. This
   * method's return value is meaningful only on {@link Token#NAME} nodes
   * used to define a {@link Token#FUNCTION}'s argument list.
   */
  public boolean isOptionalArg() {
    return getBooleanProp(OPT_ARG_NAME);
  }

  /**
   * Sets whether this is a synthetic block that should not be considered
   * a real source block.
   */
  public void setIsSyntheticBlock(boolean val) {
    putBooleanProp(SYNTHETIC_BLOCK_PROP, val);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[467]++;
  }

  /**
   * Returns whether this is a synthetic block that should not be considered
   * a real source block.
   */
  public boolean isSyntheticBlock() {
    return getBooleanProp(SYNTHETIC_BLOCK_PROP);
  }

  /**
   * Sets the ES5 directives on this node.
   */
  public void setDirectives(Set<String> val) {
    putProp(DIRECTIVES, val);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[468]++;
  }

  /**
   * Returns the set of ES5 directives for this node.
   */
  @SuppressWarnings("unchecked")
  public Set<String> getDirectives() {
    return (Set<String>) getProp(DIRECTIVES);
  }

  /**
   * Adds a warning to be suppressed. This is indistinguishable
   * from having a {@code @suppress} tag in the code.
   */
  public void addSuppression(String warning) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[469]++;
int CodeCoverConditionCoverageHelper_C117;
    if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((getJSDocInfo() == null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[215]++;
      setJSDocInfo(new JSDocInfo(false));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[470]++;

    } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[216]++;}
    getJSDocInfo().addSuppression(warning);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[471]++;
  }

  /**
   * Sets whether this is a synthetic block that should not be considered
   * a real source block.
   */
  public void setWasEmptyNode(boolean val) {
    putBooleanProp(EMPTY_BLOCK, val);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[472]++;
  }

  /**
   * Returns whether this is a synthetic block that should not be considered
   * a real source block.
   */
  public boolean wasEmptyNode() {
    return getBooleanProp(EMPTY_BLOCK);
  }

  // There are four values of interest:
  //   global state changes
  //   this state changes
  //   arguments state changes
  //   whether the call throws an exception
  //   locality of the result
  // We want a value of 0 to mean "global state changes and
  // unknown locality of result".

  final public static int FLAG_GLOBAL_STATE_UNMODIFIED = 1;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[473]++;
  }
  final public static int FLAG_THIS_UNMODIFIED = 2;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[474]++;
  }
  final public static int FLAG_ARGUMENTS_UNMODIFIED = 4;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[475]++;
  }
  final public static int FLAG_NO_THROWS = 8;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[476]++;
  }
  final public static int FLAG_LOCAL_RESULTS = 16;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[477]++;
  }

  final public static int SIDE_EFFECTS_FLAGS_MASK = 31;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[478]++;
  }

  final public static int SIDE_EFFECTS_ALL = 0;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[479]++;
  }
  final public static int NO_SIDE_EFFECTS =
    FLAG_GLOBAL_STATE_UNMODIFIED
    | FLAG_THIS_UNMODIFIED
    | FLAG_ARGUMENTS_UNMODIFIED
    | FLAG_NO_THROWS;
  static {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[480]++;
  }

  /**
   * Marks this function or constructor call's side effect flags.
   * This property is only meaningful for {@link Token#CALL} and
   * {@link Token#NEW} nodes.
   */
  public void setSideEffectFlags(int flags) {
    Preconditions.checkArgument(
       getType() == Token.CALL || getType() == Token.NEW,
       "setIsNoSideEffectsCall only supports CALL and NEW nodes, got " +
       Token.name(getType()));
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[481]++;

    putIntProp(SIDE_EFFECT_FLAGS, flags);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[482]++;
  }

  public void setSideEffectFlags(SideEffectFlags flags) {
    setSideEffectFlags(flags.valueOf());
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[483]++;
  }

  /**
   * Returns the side effects flags for this node.
   */
  public int getSideEffectFlags() {
    return getIntProp(SIDE_EFFECT_FLAGS);
  }

  /**
   * A helper class for getting and setting the side-effect flags.
   * @author johnlenz@google.com (John Lenz)
   */
  public static class SideEffectFlags {
    private int value = Node.SIDE_EFFECTS_ALL;
  {
    CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[484]++;
  }

    public SideEffectFlags() {
    }

    public SideEffectFlags(int value) {
      this.value = value;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[485]++;
    }

    public int valueOf() {
      return value;
    }

    /** All side-effect occur and the returned results are non-local. */
    public void setAllFlags() {
      value = Node.SIDE_EFFECTS_ALL;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[486]++;
    }

    /** No side-effects occur and the returned results are local. */
    public void clearAllFlags() {
      value = Node.NO_SIDE_EFFECTS | Node.FLAG_LOCAL_RESULTS;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[487]++;
    }

    public boolean areAllFlagsSet() {
      return value == Node.SIDE_EFFECTS_ALL;
    }

    /**
     * Preserve the return result flag, but clear the others:
     *   no global state change, no throws, no this change, no arguments change
     */
    public void clearSideEffectFlags() {
      value |= Node.NO_SIDE_EFFECTS;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[488]++;
    }

    public void setMutatesGlobalState() {
      // Modify global means everything must be assumed to be modified.
      removeFlag(Node.FLAG_GLOBAL_STATE_UNMODIFIED);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[489]++;
      removeFlag(Node.FLAG_ARGUMENTS_UNMODIFIED);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[490]++;
      removeFlag(Node.FLAG_THIS_UNMODIFIED);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[491]++;
    }

    public void setThrows() {
      removeFlag(Node.FLAG_NO_THROWS);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[492]++;
    }

    public void setMutatesThis() {
      removeFlag(Node.FLAG_THIS_UNMODIFIED);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[493]++;
    }

    public void setMutatesArguments() {
      removeFlag(Node.FLAG_ARGUMENTS_UNMODIFIED);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[494]++;
    }

    public void setReturnsTainted() {
      removeFlag(Node.FLAG_LOCAL_RESULTS);
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[495]++;
    }

    private void removeFlag(int flag) {
      value &= ~flag;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[496]++;
    }
  }

  /**
   * @return Whether the only side-effect is "modifies this"
   */
  public boolean isOnlyModifiesThisCall() {
    return areBitFlagsSet(
        getSideEffectFlags() & Node.NO_SIDE_EFFECTS,
        Node.FLAG_GLOBAL_STATE_UNMODIFIED
            | Node.FLAG_ARGUMENTS_UNMODIFIED
            | Node.FLAG_NO_THROWS);
  }

  /**
   * Returns true if this node is a function or constructor call that
   * has no side effects.
   */
  public boolean isNoSideEffectsCall() {
    return areBitFlagsSet(getSideEffectFlags(), NO_SIDE_EFFECTS);
  }

  /**
   * Returns true if this node is a function or constructor call that
   * returns a primitive or a local object (an object that has no other
   * references).
   */
  public boolean isLocalResultCall() {
    return areBitFlagsSet(getSideEffectFlags(), FLAG_LOCAL_RESULTS);
  }

  /**
   * returns true if all the flags are set in value.
   */
  private boolean areBitFlagsSet(int value, int flags) {
    return (value & flags) == flags;
  }

  /**
   * This should only be called for STRING nodes children of OBJECTLIT.
   */
  public boolean isQuotedString() {
    return false;
  }

  /**
   * This should only be called for STRING nodes children of OBJECTLIT.
   */
  public void setQuotedString() {
    throw new IllegalStateException("not a StringNode");
  }

  static class NodeMismatch {
    final Node nodeA;
    final Node nodeB;

    NodeMismatch(Node nodeA, Node nodeB) {
      this.nodeA = nodeA;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[497]++;
      this.nodeB = nodeB;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[498]++;
    }

    @Override
    public boolean equals(Object object) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[499]++;
int CodeCoverConditionCoverageHelper_C118;
      if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((object instanceof NodeMismatch) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$8hcnruuor2mcf5.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[217]++;
CodeCoverCoverageCounter$8hcnruuor2mcf5.statements[500]++;
        NodeMismatch that = (NodeMismatch) object;
        return that.nodeA.equals(this.nodeA) && that.nodeB.equals(this.nodeB);

      } else {
  CodeCoverCoverageCounter$8hcnruuor2mcf5.branches[218]++;}
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(nodeA, nodeB);
    }
  }


  /*** AST type check methods ***/

  public boolean isAdd() {
    return this.getType() == Token.ADD;
  }

  public boolean isAnd() {
    return this.getType() == Token.AND;
  }

  public boolean isArrayLit() {
    return this.getType() == Token.ARRAYLIT;
  }

  public boolean isAssign() {
    return this.getType() == Token.ASSIGN;
  }

  public boolean isAssignAdd() {
    return this.getType() == Token.ASSIGN_ADD;
  }

  public boolean isBlock() {
    return this.getType() == Token.BLOCK;
  }

  public boolean isBreak() {
    return this.getType() == Token.BREAK;
  }

  public boolean isCall() {
    return this.getType() == Token.CALL;
  }

  public boolean isCase() {
    return this.getType() == Token.CASE;
  }

  public boolean isCast() {
    return this.getType() == Token.CAST;
  }

  public boolean isCatch() {
    return this.getType() == Token.CATCH;
  }

  public boolean isComma() {
    return this.getType() == Token.COMMA;
  }

  public boolean isContinue() {
    return this.getType() == Token.CONTINUE;
  }

  public boolean isDebugger() {
    return this.getType() == Token.DEBUGGER;
  }

  public boolean isDec() {
    return this.getType() == Token.DEC;
  }

  public boolean isDefaultCase() {
    return this.getType() == Token.DEFAULT_CASE;
  }

  public boolean isDelProp() {
    return this.getType() == Token.DELPROP;
  }

  public boolean isDo() {
    return this.getType() == Token.DO;
  }

  public boolean isEmpty() {
    return this.getType() == Token.EMPTY;
  }

  public boolean isExprResult() {
    return this.getType() == Token.EXPR_RESULT;
  }

  public boolean isFalse() {
    return this.getType() == Token.FALSE;
  }

  public boolean isFor() {
    return this.getType() == Token.FOR;
  }

  public boolean isFunction() {
    return this.getType() == Token.FUNCTION;
  }

  public boolean isGetterDef() {
    return this.getType() == Token.GETTER_DEF;
  }

  public boolean isGetElem() {
    return this.getType() == Token.GETELEM;
  }

  public boolean isGetProp() {
    return this.getType() == Token.GETPROP;
  }

  public boolean isHook() {
    return this.getType() == Token.HOOK;
  }

  public boolean isIf() {
    return this.getType() == Token.IF;
  }

  public boolean isIn() {
    return this.getType() == Token.IN;
  }

  public boolean isInc() {
    return this.getType() == Token.INC;
  }

  public boolean isInstanceOf() {
    return this.getType() == Token.INSTANCEOF;
  }

  public boolean isLabel() {
    return this.getType() == Token.LABEL;
  }

  public boolean isLabelName() {
    return this.getType() == Token.LABEL_NAME;
  }

  public boolean isName() {
    return this.getType() == Token.NAME;
  }

  public boolean isNE() {
    return this.getType() == Token.NE;
  }

  public boolean isNew() {
    return this.getType() == Token.NEW;
  }

  public boolean isNot() {
    return this.getType() == Token.NOT;
  }

  public boolean isNull() {
    return this.getType() == Token.NULL;
  }

  public boolean isNumber() {
    return this.getType() == Token.NUMBER;
  }

  public boolean isObjectLit() {
    return this.getType() == Token.OBJECTLIT;
  }

  public boolean isOr() {
    return this.getType() == Token.OR;
  }

  public boolean isParamList() {
    return this.getType() == Token.PARAM_LIST;
  }

  public boolean isRegExp() {
    return this.getType() == Token.REGEXP;
  }

  public boolean isReturn() {
    return this.getType() == Token.RETURN;
  }

  public boolean isScript() {
    return this.getType() == Token.SCRIPT;
  }

  public boolean isSetterDef() {
    return this.getType() == Token.SETTER_DEF;
  }

  public boolean isString() {
    return this.getType() == Token.STRING;
  }

  public boolean isStringKey() {
    return this.getType() == Token.STRING_KEY;
  }

  public boolean isSwitch() {
    return this.getType() == Token.SWITCH;
  }

  public boolean isThis() {
    return this.getType() == Token.THIS;
  }

  public boolean isThrow() {
    return this.getType() == Token.THROW;
  }

  public boolean isTrue() {
    return this.getType() == Token.TRUE;
  }

  public boolean isTry() {
    return this.getType() == Token.TRY;
  }

  public boolean isTypeOf() {
    return this.getType() == Token.TYPEOF;
  }

  public boolean isVar() {
    return this.getType() == Token.VAR;
  }

  public boolean isVoid() {
    return this.getType() == Token.VOID;
  }

  public boolean isWhile() {
    return this.getType() == Token.WHILE;
  }

  public boolean isWith() {
    return this.getType() == Token.WITH;
  }
}

class CodeCoverCoverageCounter$8hcnruuor2mcf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8hcnruuor2mcf5 ());
  }
    public static long[] statements = new long[501];
    public static long[] branches = new long[219];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[119];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.Node.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,2,1,3,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 118; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[79];

  public CodeCoverCoverageCounter$8hcnruuor2mcf5 () {
    super("com.google.javascript.rhino.Node.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 500; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 218; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 118; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 78; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.Node.java");
      for (int i = 1; i <= 500; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 218; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 118; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 26; i++) {
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

