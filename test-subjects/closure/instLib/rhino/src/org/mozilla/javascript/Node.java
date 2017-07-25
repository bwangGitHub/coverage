/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements the root of the intermediate representation.
 *
 */
public class Node implements Iterable<Node>
{
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.ping();
  }

    public static final int
        FUNCTION_PROP      =  1,
        LOCAL_PROP         =  2,
        LOCAL_BLOCK_PROP   =  3,
        REGEXP_PROP        =  4,
        CASEARRAY_PROP     =  5,

    //  the following properties are defined and manipulated by the
    //  optimizer -
    //  TARGETBLOCK_PROP - the block referenced by a branch node
    //  VARIABLE_PROP - the variable referenced by a BIND or NAME node
    //  ISNUMBER_PROP - this node generates code on Number children and
    //                  delivers a Number result (as opposed to Objects)
    //  DIRECTCALL_PROP - this call node should emit code to test the function
    //                    object against the known class and call direct if it
    //                    matches.

        TARGETBLOCK_PROP     =  6,
        VARIABLE_PROP        =  7,
        ISNUMBER_PROP        =  8,
        DIRECTCALL_PROP      =  9,
        SPECIALCALL_PROP     = 10,
        SKIP_INDEXES_PROP    = 11, // array of skipped indexes of array literal
        OBJECT_IDS_PROP      = 12, // array of properties for object literal
        INCRDECR_PROP        = 13, // pre or post type of increment/decrement
        CATCH_SCOPE_PROP     = 14, // index of catch scope block in catch
        LABEL_ID_PROP        = 15, // label id: code generation uses it
        MEMBER_TYPE_PROP     = 16, // type of element access operation
        NAME_PROP            = 17, // property name
        CONTROL_BLOCK_PROP   = 18, // flags a control block that can drop off
        PARENTHESIZED_PROP   = 19, // expression is parenthesized
        GENERATOR_END_PROP   = 20,
        DESTRUCTURING_ARRAY_LENGTH = 21,
        DESTRUCTURING_NAMES  = 22,
        DESTRUCTURING_PARAMS = 23,
        JSDOC_PROP           = 24,
        EXPRESSION_CLOSURE_PROP = 25, // JS 1.8 expression closure pseudo-return
        DESTRUCTURING_SHORTHAND = 26, // JS 1.8 destructuring shorthand
        LAST_PROP            = 26;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[1]++;
  }

    // values of ISNUMBER_PROP to specify
    // which of the children are Number types
    public static final int
        BOTH = 0,
        LEFT = 1,
        RIGHT = 2;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[2]++;
  }

    public static final int    // values for SPECIALCALL_PROP
        NON_SPECIALCALL  = 0,
        SPECIALCALL_EVAL = 1,
        SPECIALCALL_WITH = 2;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[3]++;
  }

    public static final int   // flags for INCRDECR_PROP
        DECR_FLAG = 0x1,
        POST_FLAG = 0x2;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[4]++;
  }

    public static final int   // flags for MEMBER_TYPE_PROP
        PROPERTY_FLAG    = 0x1, // property access: element is valid name
        ATTRIBUTE_FLAG   = 0x2, // x.@y or x..@y
        DESCENDANTS_FLAG = 0x4;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[5]++;
  } // x..y or x..@i

    private static class PropListItem
    {
        PropListItem next;
        int type;
        int intValue;
        Object objectValue;
    }

    public Node(int nodeType) {
        type = nodeType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[6]++;
    }

    public Node(int nodeType, Node child) {
        type = nodeType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[7]++;
        first = last = child;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[8]++;
        child.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[9]++;
    }

    public Node(int nodeType, Node left, Node right) {
        type = nodeType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[10]++;
        first = left;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[11]++;
        last = right;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[12]++;
        left.next = right;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[13]++;
        right.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[14]++;
    }

    public Node(int nodeType, Node left, Node mid, Node right) {
        type = nodeType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[15]++;
        first = left;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[16]++;
        last = right;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[17]++;
        left.next = mid;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[18]++;
        mid.next = right;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[19]++;
        right.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[20]++;
    }

    public Node(int nodeType, int line) {
        type = nodeType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[21]++;
        lineno = line;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[22]++;
    }

    public Node(int nodeType, Node child, int line) {
        this(nodeType, child);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[23]++;
        lineno = line;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[24]++;
    }

    public Node(int nodeType, Node left, Node right, int line) {
        this(nodeType, left, right);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[25]++;
        lineno = line;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[26]++;
    }

    public Node(int nodeType, Node left, Node mid, Node right, int line) {
        this(nodeType, left, mid, right);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[27]++;
        lineno = line;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[28]++;
    }

    public static Node newNumber(double number) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[29]++;
        NumberLiteral n = new NumberLiteral();
        n.setNumber(number);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[30]++;
        return n;
    }

    public static Node newString(String str) {
        return newString(Token.STRING, str);
    }

    public static Node newString(int type, String str) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[31]++;
        Name name = new Name();
        name.setIdentifier(str);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[32]++;
        name.setType(type);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[33]++;
        return name;
    }

    public int getType() {
        return type;
    }

    /**
     * Sets the node type and returns this node.
     */
    public Node setType(int type) {
        this.type = type;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[34]++;
        return this;
    }

    /**
     * Gets the JsDoc comment string attached to this node.
     * @return the comment string or {@code null} if no JsDoc is attached to
     *     this node
     */
    public String getJsDoc() {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[35]++;
        Comment comment = getJsDocNode();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[36]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((comment != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[1]++;
          return comment.getValue();

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[2]++;}
        return null;
    }

    /**
     * Gets the JsDoc Comment object attached to this node.
     * @return the Comment or {@code null} if no JsDoc is attached to
     *     this node
     */
    public Comment getJsDocNode() {
        return (Comment) getProp(JSDOC_PROP);
    }

    /**
     * Sets the JsDoc comment string attached to this node.
     */
    public void setJsDocNode(Comment jsdocNode) {
        putProp(JSDOC_PROP, jsdocNode);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[37]++;
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
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[38]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((child == first) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[3]++;
            return null;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[4]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[39]++;
        Node n = first;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.next != child) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[1]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[2]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[3]++;
}
            n = n.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[41]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[42]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[5]++;
                throw new RuntimeException("node is not a child");
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[6]++;}
        }
        return n;
    }

    public Node getLastSibling() {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[43]++;
        Node n = this;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[44]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.next != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[4]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[5]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[6]++;
}
            n = n.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[45]++;
        }
        return n;
    }

    public void addChildToFront(Node child) {
        child.next = first;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[46]++;
        first = child;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[47]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[48]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[7]++;
            last = child;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[49]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[8]++;}
    }

    public void addChildToBack(Node child) {
        child.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[50]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[9]++;
            first = last = child;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[52]++;
            return;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[10]++;}
        last.next = child;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[53]++;
        last = child;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[54]++;
    }

    public void addChildrenToFront(Node children) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[55]++;
        Node lastSib = children.getLastSibling();
        lastSib.next = first;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[56]++;
        first = children;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[57]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[58]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[11]++;
            last = lastSib;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[59]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[12]++;}
    }

    public void addChildrenToBack(Node children) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[60]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((last != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[13]++;
            last.next = children;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[61]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[14]++;}
        last = children.getLastSibling();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[62]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[63]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[15]++;
            first = children;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[64]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[16]++;}
    }

    /**
     * Add 'child' before 'node'.
     */
    public void addChildBefore(Node newChild, Node node) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[65]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((newChild.next != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[17]++;
            throw new RuntimeException(
                      "newChild had siblings in addChildBefore");
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[18]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((first == node) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[19]++;
            newChild.next = first;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[67]++;
            first = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[68]++;
            return;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[20]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[69]++;
        Node prev = getChildBefore(node);
        addChildAfter(newChild, prev);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[70]++;
    }

    /**
     * Add 'child' after 'node'.
     */
    public void addChildAfter(Node newChild, Node node) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[71]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((newChild.next != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[21]++;
            throw new RuntimeException(
                      "newChild had siblings in addChildAfter");
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[22]++;}
        newChild.next = node.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[72]++;
        node.next = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[73]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((last == node) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[23]++;
            last = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[75]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[24]++;}
    }

    public void removeChild(Node child) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[76]++;
        Node prev = getChildBefore(child);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[77]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((prev == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[25]++;
            first = first.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[78]++;
}
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[26]++;
            prev.next = child.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[79]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[80]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[27]++; last = prev;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[81]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[28]++;}
        child.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[82]++;
    }

    public void replaceChild(Node child, Node newChild) {
        newChild.next = child.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[83]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[84]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((child == first) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[29]++;
            first = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[85]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[30]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[86]++;
            Node prev = getChildBefore(child);
            prev.next = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[87]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[31]++;
            last = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[89]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[32]++;}
        child.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[90]++;
    }

    public void replaceChildAfter(Node prevChild, Node newChild) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[91]++;
        Node child = prevChild.next;
        newChild.next = child.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[92]++;
        prevChild.next = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[93]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[94]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((child == last) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[33]++;
            last = newChild;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[95]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[34]++;}
        child.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[96]++;
    }

    public void removeChildren() {
        first = last = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[97]++;
    }

    private static final Node NOT_SET = new Node(Token.ERROR);
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[98]++;
  }

    /**
     * Iterates over the children of this Node.  Supports child removal.  Not
     * thread-safe.  If anyone changes the child list before the iterator
     * finishes, the results are undefined and probably bad.
     */
    public class NodeIterator implements Iterator<Node> {
        private Node cursor;  // points to node to be returned next
        private Node prev = NOT_SET;
  {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[99]++;
  }
        private Node prev2;
        private boolean removed = false;
  {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[100]++;
  }

        public NodeIterator() {
            cursor = Node.this.first;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[101]++;
        }

        public boolean hasNext() {
            return cursor != null;
        }

        public Node next() {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[102]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((cursor == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[35]++;
                throw new NoSuchElementException();

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[36]++;}
            removed = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[103]++;
            prev2 = prev;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[104]++;
            prev = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[105]++;
            cursor = cursor.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[106]++;
            return prev;
        }

        public void remove() {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[107]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((prev == NOT_SET) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[37]++;
                throw new IllegalStateException("next() has not been called");

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[38]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[108]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[39]++;
                throw new IllegalStateException(
                    "remove() already called for current element");

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[40]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[109]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((prev == first) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[41]++;
                first = prev.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[110]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[42]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[111]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((prev == last) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[43]++;
                prev2.next = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[112]++;
                last = prev2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[113]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[44]++;
                prev2.next = cursor;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[114]++;
            }
}
        }
    }

    /**
     * Returns an {@link java.util.Iterator} over the node's children.
     */
    public Iterator<Node> iterator() {
        return new NodeIterator();
    }

    private static final String propToString(int propType)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[115]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[45]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[116]++;
            // If Context.printTrees is false, the compiler
            // can remove all these strings.
            switch (propType) {
                case FUNCTION_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[47]++;        return "function";
                case LOCAL_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[48]++;           return "local";
                case LOCAL_BLOCK_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[49]++;     return "local_block";
                case REGEXP_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[50]++;          return "regexp";
                case CASEARRAY_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[51]++;       return "casearray";

                case TARGETBLOCK_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[52]++;     return "targetblock";
                case VARIABLE_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[53]++;        return "variable";
                case ISNUMBER_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[54]++;        return "isnumber";
                case DIRECTCALL_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[55]++;      return "directcall";

                case SPECIALCALL_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[56]++;     return "specialcall";
                case SKIP_INDEXES_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[57]++;    return "skip_indexes";
                case OBJECT_IDS_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[58]++;      return "object_ids_prop";
                case INCRDECR_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[59]++;        return "incrdecr_prop";
                case CATCH_SCOPE_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[60]++;     return "catch_scope_prop";
                case LABEL_ID_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[61]++;        return "label_id_prop";
                case MEMBER_TYPE_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[62]++;     return "member_type_prop";
                case NAME_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[63]++;            return "name_prop";
                case CONTROL_BLOCK_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[64]++;   return "control_block_prop";
                case PARENTHESIZED_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[65]++;   return "parenthesized_prop";
                case GENERATOR_END_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[66]++;   return "generator_end";
                case DESTRUCTURING_ARRAY_LENGTH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[67]++;
                                           return "destructuring_array_length";
                case DESTRUCTURING_NAMES:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[68]++;  return "destructuring_names";
                case DESTRUCTURING_PARAMS:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[69]++; return "destructuring_params";

                default:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[70]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[117]++;
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[46]++;}
        return null;
    }

    private PropListItem lookupProperty(int propType)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[118]++;
        PropListItem x = propListHead;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[119]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[7]++;


int CodeCoverConditionCoverageHelper_C26;
        while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((propType != x.type) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[7]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[8]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[9]++;
}
            x = x.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[120]++;
        }
        return x;
    }

    private PropListItem ensureProperty(int propType)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[121]++;
        PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[122]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[71]++;
            item = new PropListItem();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[123]++;
            item.type = propType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[124]++;
            item.next = propListHead;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[125]++;
            propListHead = item;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[126]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[72]++;}
        return item;
    }

    public void removeProp(int propType)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[127]++;
        PropListItem x = propListHead;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[128]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[73]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[129]++;
            PropListItem prev = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[130]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[10]++;


int CodeCoverConditionCoverageHelper_C29;
            while ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((x.type != propType) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[10]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[11]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[12]++;
}
                prev = x;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[131]++;
                x = x.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[132]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[133]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[75]++; return;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[76]++;}
            }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[134]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((prev == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[77]++;
                propListHead = x.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[135]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[78]++;
                prev.next = x.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[136]++;
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[74]++;}
    }

    public Object getProp(int propType)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[137]++;
        PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[138]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[79]++; return null;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[80]++;}
        return item.objectValue;
    }

    public int getIntProp(int propType, int defaultValue)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[139]++;
        PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[140]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[81]++; return defaultValue;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[82]++;}
        return item.intValue;
    }

    public int getExistingIntProp(int propType)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[141]++;
        PropListItem item = lookupProperty(propType);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[142]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[83]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[143]++;
 } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[84]++;}
        return item.intValue;
    }

    public void putProp(int propType, Object prop)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[144]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((prop == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[85]++;
            removeProp(propType);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[145]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[86]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[146]++;
            PropListItem item = ensureProperty(propType);
            item.objectValue = prop;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[147]++;
        }
    }

    public void putIntProp(int propType, int prop)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[148]++;
        PropListItem item = ensureProperty(propType);
        item.intValue = prop;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[149]++;
    }

    /**
     * Return the line number recorded for this node.
     * @return the line number
     */
    public int getLineno() {
        return lineno;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[150]++;
    }

    /** Can only be called when <tt>getType() == Token.NUMBER</tt> */
    public final double getDouble() {
        return ((NumberLiteral)this).getNumber();
    }

    public final void setDouble(double number) {
        ((NumberLiteral)this).setNumber(number);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[151]++;
    }

    /** Can only be called when node has String context. */
    public final String getString() {
        return ((Name)this).getIdentifier();
    }

    /** Can only be called when node has String context. */
    public final void setString(String s) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[152]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[87]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[153]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[88]++;}
        ((Name)this).setIdentifier(s);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[154]++;
    }

    /** Can only be called when node has String context. */
    public Scope getScope() {
        return ((Name)this).getScope();
    }

    /** Can only be called when node has String context. */
    public void setScope(Scope s) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[155]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[89]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[156]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[90]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[157]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this instanceof Name) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[91]++;
            throw Kit.codeBug();

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[92]++;}
        ((Name)this).setScope(s);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[158]++;
    }

    public static Node newTarget()
    {
        return new Node(Token.TARGET);
    }

    public final int labelId()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[159]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((type != Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((type != Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[93]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[160]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[94]++;}
        return getIntProp(LABEL_ID_PROP, -1);
    }

    public void labelId(int labelId)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[161]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((type != Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((type != Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[95]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[162]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[96]++;}
        putIntProp(LABEL_ID_PROP, labelId);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[163]++;
    }


    /**
     * Does consistent-return analysis on the function body when strict mode is
     * enabled.
     *
     *   function (x) { return (x+1) }
     * is ok, but
     *   function (x) { if (x &lt; 0) return (x+1); }
     * is not becuase the function can potentially return a value when the
     * condition is satisfied and if not, the function does not explicitly
     * return value.
     *
     * This extends to checking mismatches such as "return" and "return <value>"
     * used in the same function. Warnings are not emitted if inconsistent
     * returns exist in code that can be statically shown to be unreachable.
     * Ex.
     * <pre>function (x) { while (true) { ... if (..) { return value } ... } }
     * </pre>
     * emits no warning. However if the loop had a break statement, then a
     * warning would be emitted.
     *
     * The consistency analysis looks at control structures such as loops, ifs,
     * switch, try-catch-finally blocks, examines the reachable code paths and
     * warns the user about an inconsistent set of termination possibilities.
     *
     * Caveat: Since the parser flattens many control structures into almost
     * straight-line code with gotos, it makes such analysis hard. Hence this
     * analyser is written to taken advantage of patterns of code generated by
     * the parser (for loops, try blocks and such) and does not do a full
     * control flow analysis of the gotos and break/continue statements.
     * Future changes to the parser will affect this analysis.
     */

    /**
     * These flags enumerate the possible ways a statement/function can
     * terminate. These flags are used by endCheck() and by the Parser to
     * detect inconsistent return usage.
     *
     * END_UNREACHED is reserved for code paths that are assumed to always be
     * able to execute (example: throw, continue)
     *
     * END_DROPS_OFF indicates if the statement can transfer control to the
     * next one. Statement such as return dont. A compound statement may have
     * some branch that drops off control to the next statement.
     *
     * END_RETURNS indicates that the statement can return (without arguments)
     * END_RETURNS_VALUE indicates that the statement can return a value.
     *
     * A compound statement such as
     * if (condition) {
     *   return value;
     * }
     * Will be detected as (END_DROPS_OFF | END_RETURN_VALUE) by endCheck()
     */
    public static final int END_UNREACHED = 0;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[164]++;
  }
    public static final int END_DROPS_OFF = 1;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[165]++;
  }
    public static final int END_RETURNS = 2;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[166]++;
  }
    public static final int END_RETURNS_VALUE = 4;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[167]++;
  }
    public static final int END_YIELDS = 8;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[168]++;
  }

    /**
     * Checks that every return usage in a function body is consistent with the
     * requirements of strict-mode.
     * @return true if the function satisfies strict mode requirement.
     */
    public boolean hasConsistentReturnUsage()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[169]++;
        int n = endCheck();
        return (n & END_RETURNS_VALUE) == 0 ||
               (n & (END_DROPS_OFF|END_RETURNS|END_YIELDS)) == 0;
    }

    /**
     * Returns in the then and else blocks must be consistent with each other.
     * If there is no else block, then the return statement can fall through.
     * @return logical OR of END_* flags
     */
    private int endCheckIf()
    {
        Node th, el;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[170]++;
        int rv = END_UNREACHED;

        th = next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[171]++;
        el = ((Jump)this).target;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[172]++;

        rv = th.endCheck();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[173]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[174]++;
int CodeCoverConditionCoverageHelper_C41;

        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((el != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[97]++;
            rv |= el.endCheck();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[175]++;
}
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[98]++;
            rv |= END_DROPS_OFF;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[176]++;
}

        return rv;
    }

    /**
     * Consistency of return statements is checked between the case statements.
     * If there is no default, then the switch can fall through. If there is a
     * default,we check to see if all code paths in the default return or if
     * there is a code path that can fall through.
     * @return logical OR of END_* flags
     */
    private int endCheckSwitch()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[177]++;
        int rv = END_UNREACHED;

        // examine the cases
//         for (n = first.next; n != null; n = n.next)
//         {
//             if (n.type == Token.CASE) {
//                 rv |= ((Jump)n).target.endCheck();
//             } else
//                 break;
//         }

//         // we don't care how the cases drop into each other
//         rv &= ~END_DROPS_OFF;

//         // examine the default
//         n = ((Jump)this).getDefault();
//         if (n != null)
//             rv |= n.endCheck();
//         else
//             rv |= END_DROPS_OFF;

//         // remove the switch block
//         rv |= getIntProp(CONTROL_BLOCK_PROP, END_UNREACHED);

        return rv;
    }

    /**
     * If the block has a finally, return consistency is checked in the
     * finally block. If all code paths in the finally returns, then the
     * returns in the try-catch blocks don't matter. If there is a code path
     * that does not return or if there is no finally block, the returns
     * of the try and catch blocks are checked for mismatch.
     * @return logical OR of END_* flags
     */
    private int endCheckTry()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[178]++;
        int rv = END_UNREACHED;

        // a TryStatement isn't a jump - needs rewriting

        // check the finally if it exists
//         n = ((Jump)this).getFinally();
//         if(n != null) {
//             rv = n.next.first.endCheck();
//         } else {
//             rv = END_DROPS_OFF;
//         }

//         // if the finally block always returns, then none of the returns
//         // in the try or catch blocks matter
//         if ((rv & END_DROPS_OFF) != 0) {
//             rv &= ~END_DROPS_OFF;

//             // examine the try block
//             rv |= first.endCheck();

//             // check each catch block
//             n = ((Jump)this).target;
//             if (n != null)
//             {
//                 // point to the first catch_scope
//                 for (n = n.next.first; n != null; n = n.next.next)
//                 {
//                     // check the block of user code in the catch_scope
//                     rv |= n.next.first.next.first.endCheck();
//                 }
//             }
//         }

        return rv;
    }

    /**
     * Return statement in the loop body must be consistent. The default
     * assumption for any kind of a loop is that it will eventually terminate.
     * The only exception is a loop with a constant true condition. Code that
     * follows such a loop is examined only if one can statically determine
     * that there is a break out of the loop.
     * <pre>
     *  for(&lt;&gt; ; &lt;&gt;; &lt;&gt;) {}
     *  for(&lt;&gt; in &lt;&gt; ) {}
     *  while(&lt;&gt;) { }
     *  do { } while(&lt;&gt;)
     * </pre>
     * @return logical OR of END_* flags
     */
    private int endCheckLoop()
    {
        Node n;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[179]++;
        int rv = END_UNREACHED;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[180]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[13]++;


int CodeCoverConditionCoverageHelper_C42;

        // To find the loop body, we look at the second to last node of the
        // loop node, which should be the predicate that the loop should
        // satisfy.
        // The target of the predicate is the loop-body for all 4 kinds of
        // loops.
        for (n = first;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((n.next != last) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); n = n.next) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[13]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[14]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[15]++;
}
            /* skip */
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[181]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((n.type != Token.IFEQ) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[99]++;
            return END_DROPS_OFF;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[100]++;}

        // The target's next is the loop body block
        rv = ((Jump)n).target.next.endCheck();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[182]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[183]++;
int CodeCoverConditionCoverageHelper_C44;

        // check to see if the loop condition is true
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((n.first.type == Token.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[101]++;
            rv &= ~END_DROPS_OFF;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[184]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[102]++;}

        // look for effect of breaks
        rv |= getIntProp(CONTROL_BLOCK_PROP, END_UNREACHED);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[185]++;

        return rv;
    }

    /**
     * A general block of code is examined statement by statement. If any
     * statement (even compound ones) returns in all branches, then subsequent
     * statements are not examined.
     * @return logical OR of END_* flags
     */
    private int endCheckBlock()
    {
        Node n;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[186]++;
        int rv = END_DROPS_OFF;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[187]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[16]++;


int CodeCoverConditionCoverageHelper_C45;

        // check each statment and if the statement can continue onto the next
        // one, then check the next statement
        for (n=first;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 (((rv & END_DROPS_OFF) != 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false); n = n.next)
        {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[16]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[17]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[18]++;
}
            rv &= ~END_DROPS_OFF;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[188]++;
            rv |= n.endCheck();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[189]++;
        }
        return rv;
    }

    /**
     * A labelled statement implies that there maybe a break to the label. The
     * function processes the labelled statement and then checks the
     * CONTROL_BLOCK_PROP property to see if there is ever a break to the
     * particular label.
     * @return logical OR of END_* flags
     */
    private int endCheckLabel()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[190]++;
        int rv = END_UNREACHED;

        rv = next.endCheck();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[191]++;
        rv |= getIntProp(CONTROL_BLOCK_PROP, END_UNREACHED);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[192]++;

        return rv;
    }

    /**
     * When a break is encountered annotate the statement being broken
     * out of by setting its CONTROL_BLOCK_PROP property.
     * @return logical OR of END_* flags
     */
    private int endCheckBreak()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[193]++;
        Node n = ((Jump) this).getJumpStatement();
        n.putIntProp(CONTROL_BLOCK_PROP, END_DROPS_OFF);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[194]++;
        return END_UNREACHED;
    }

    /**
     * endCheck() examines the body of a function, doing a basic reachability
     * analysis and returns a combination of flags END_* flags that indicate
     * how the function execution can terminate. These constitute only the
     * pessimistic set of termination conditions. It is possible that at
     * runtime certain code paths will never be actually taken. Hence this
     * analysis will flag errors in cases where there may not be errors.
     * @return logical OR of END_* flags
     */
    private int endCheck()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[195]++;
        switch(type)
        {
            case Token.BREAK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[103]++;
                return endCheckBreak();

            case Token.EXPR_VOID:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[104]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[196]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.first != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[105]++;
                    return first.endCheck();
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[106]++;}
                return END_DROPS_OFF;

            case Token.YIELD:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[107]++;
                return END_YIELDS;

            case Token.CONTINUE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[108]++;
            case Token.THROW:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[109]++;
                return END_UNREACHED;

            case Token.RETURN:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[110]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[197]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.first != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[111]++;
                    return END_RETURNS_VALUE;
}
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[112]++;
                    return END_RETURNS;
}

            case Token.TARGET:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[113]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[198]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[114]++;
                    return next.endCheck();
}
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[115]++;
                    return END_DROPS_OFF;
}

            case Token.LOOP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[116]++;
                return endCheckLoop();

            case Token.LOCAL_BLOCK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[117]++;
            case Token.BLOCK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[118]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[199]++;
int CodeCoverConditionCoverageHelper_C49;
                // there are several special kinds of blocks
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[119]++;
                    return END_DROPS_OFF;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[120]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[200]++;

                switch(first.type) {
                    case Token.LABEL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[121]++;
                        return first.endCheckLabel();

                    case Token.IFNE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[122]++;
                        return first.endCheckIf();

                    case Token.SWITCH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[123]++;
                        return first.endCheckSwitch();

                    case Token.TRY:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[124]++;
                        return first.endCheckTry();

                    default:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[125]++;
                        return endCheckBlock();
                }

            default:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[126]++;
                return END_DROPS_OFF;
        }
    }

    public boolean hasSideEffects()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[201]++;
        switch (type) {
          case Token.EXPR_VOID:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[127]++;
          case Token.COMMA:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[128]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[202]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((last != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[129]++;
                return last.hasSideEffects();
}
            else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[130]++;
                return true;
}

          case Token.HOOK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[131]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[203]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (32)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((first.next == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((first.next.next == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[132]++;
                Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[204]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[133]++;}
            return first.next.hasSideEffects() &&
                   first.next.next.hasSideEffects();

          case Token.AND:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[134]++;
          case Token.OR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[135]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[205]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[136]++;
                Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[206]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[137]++;}
            return first.hasSideEffects() || last.hasSideEffects();

          case Token.ERROR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[138]++;         // Avoid cascaded error messages
          case Token.EXPR_RESULT:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[139]++;
          case Token.ASSIGN:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[140]++;
          case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[141]++;
          case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[142]++;
          case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[143]++;
          case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[144]++;
          case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[145]++;
          case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[146]++;
          case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[147]++;
          case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[148]++;
          case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[149]++;
          case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[150]++;
          case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[151]++;
          case Token.ENTERWITH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[152]++;
          case Token.LEAVEWITH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[153]++;
          case Token.RETURN:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[154]++;
          case Token.GOTO:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[155]++;
          case Token.IFEQ:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[156]++;
          case Token.IFNE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[157]++;
          case Token.NEW:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[158]++;
          case Token.DELPROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[159]++;
          case Token.SETNAME:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[160]++;
          case Token.SETPROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[161]++;
          case Token.SETELEM:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[162]++;
          case Token.CALL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[163]++;
          case Token.THROW:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[164]++;
          case Token.RETHROW:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[165]++;
          case Token.SETVAR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[166]++;
          case Token.CATCH_SCOPE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[167]++;
          case Token.RETURN_RESULT:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[168]++;
          case Token.SET_REF:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[169]++;
          case Token.DEL_REF:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[170]++;
          case Token.REF_CALL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[171]++;
          case Token.TRY:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[172]++;
          case Token.SEMI:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[173]++;
          case Token.INC:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[174]++;
          case Token.DEC:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[175]++;
          case Token.IF:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[176]++;
          case Token.ELSE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[177]++;
          case Token.SWITCH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[178]++;
          case Token.WHILE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[179]++;
          case Token.DO:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[180]++;
          case Token.FOR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[181]++;
          case Token.BREAK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[182]++;
          case Token.CONTINUE:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[183]++;
          case Token.VAR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[184]++;
          case Token.CONST:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[185]++;
          case Token.LET:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[186]++;
          case Token.LETEXPR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[187]++;
          case Token.WITH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[188]++;
          case Token.WITHEXPR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[189]++;
          case Token.CATCH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[190]++;
          case Token.FINALLY:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[191]++;
          case Token.BLOCK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[192]++;
          case Token.LABEL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[193]++;
          case Token.TARGET:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[194]++;
          case Token.LOOP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[195]++;
          case Token.JSR:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[196]++;
          case Token.SETPROP_OP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[197]++;
          case Token.SETELEM_OP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[198]++;
          case Token.LOCAL_BLOCK:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[199]++;
          case Token.SET_REF_OP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[200]++;
          case Token.YIELD:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[201]++;
            return true;

          default:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[202]++;
            return false;
        }
    }

    /**
     * Recursively unlabel every TARGET or YIELD node in the tree.
     *
     * This is used and should only be used for inlining finally blocks where
     * jsr instructions used to be. It is somewhat hackish, but implementing
     * a clone() operation would take much, much more effort.
     *
     * This solution works for inlining finally blocks because you should never
     * be writing any given block to the class file simultaneously. Therefore,
     * an unlabeling will never occur in the middle of a block.
     */
    public void resetTargets()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[207]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((type == Token.FINALLY) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[203]++;
            resetTargets_r();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[208]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[204]++;
            Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[209]++;
        }
    }

    private void resetTargets_r()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[210]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((type == Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((type == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[205]++;
            labelId(-1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[211]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[206]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[212]++;
        Node child = first;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[213]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[19]++;


int CodeCoverConditionCoverageHelper_C55;
        while ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[19]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[20]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[21]++;
}
            child.resetTargets_r();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[214]++;
            child = child.next;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[215]++;
        }
    }

    @Override
    public String toString()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[216]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[207]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[217]++;
            StringBuffer sb = new StringBuffer();
            toString(new ObjToIntMap(), sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[218]++;
            return sb.toString();

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[208]++;}
        return String.valueOf(type);
    }

    private void toString(ObjToIntMap printIds, StringBuffer sb)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[219]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[209]++;
            sb.append(Token.name(type));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[220]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[221]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this instanceof Name) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[211]++;
                sb.append(' ');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[222]++;
                sb.append(getString());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[223]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[224]++;
                Scope scope = getScope();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[225]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[213]++;
                    sb.append("[scope: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[226]++;
                    appendPrintId(scope, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[227]++;
                    sb.append("]");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[228]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[214]++;}

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[212]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[229]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[215]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[230]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this instanceof ScriptNode) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[217]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[231]++;
                    ScriptNode sof = (ScriptNode)this;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[232]++;
int CodeCoverConditionCoverageHelper_C62;
                    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((this instanceof FunctionNode) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[219]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[233]++;
                        FunctionNode fn = (FunctionNode)this;
                        sb.append(' ');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[234]++;
                        sb.append(fn.getName());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[235]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[220]++;}
                    sb.append(" [source name: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[236]++;
                    sb.append(sof.getSourceName());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[237]++;
                    sb.append("] [encoded source length: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[238]++;
                    sb.append(sof.getEncodedSourceEnd()
                              - sof.getEncodedSourceStart());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[239]++;
                    sb.append("] [base line: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[240]++;
                    sb.append(sof.getBaseLineno());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[241]++;
                    sb.append("] [end line: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[242]++;
                    sb.append(sof.getEndLineno());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[243]++;
                    sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[244]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[218]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[245]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((((Scope)this).getSymbolTable() != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[221]++;
                    sb.append(" [scope ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[246]++;
                    appendPrintId(this, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[247]++;
                    sb.append(": ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[248]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[249]++;
                    Iterator<String> iter =
                        ((Scope) this).getSymbolTable().keySet().iterator();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[250]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[22]++;


int CodeCoverConditionCoverageHelper_C64;
                    while ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[22]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[23]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[24]++;
}
                        sb.append(iter.next());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[251]++;
                        sb.append(" ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[252]++;
                    }
                    sb.append("]");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[253]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[222]++;}

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[216]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[254]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this instanceof Jump) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[223]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[255]++;
                Jump jump = (Jump)this;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[256]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((type == Token.BREAK) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((type == Token.CONTINUE) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[225]++;
                    sb.append(" [label: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[257]++;
                    appendPrintId(jump.getJumpStatement(), printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[258]++;
                    sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[259]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[226]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[260]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((type == Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[227]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[261]++;
                    Node catchNode = jump.target;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[262]++;
                    Node finallyTarget = jump.getFinally();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[263]++;
int CodeCoverConditionCoverageHelper_C68;
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((catchNode != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[229]++;
                        sb.append(" [catch: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[264]++;
                        appendPrintId(catchNode, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[265]++;
                        sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[266]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[230]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[267]++;
int CodeCoverConditionCoverageHelper_C69;
                    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((finallyTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[231]++;
                        sb.append(" [finally: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[268]++;
                        appendPrintId(finallyTarget, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[269]++;
                        sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[270]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[232]++;}

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[228]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[271]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (32)) == 0 || true) &&
 ((type == Token.LABEL) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((type == Token.LOOP) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((type == Token.SWITCH) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) && false))
                {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[233]++;
                    sb.append(" [break: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[272]++;
                    appendPrintId(jump.target, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[273]++;
                    sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[274]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[275]++;
int CodeCoverConditionCoverageHelper_C71;
                    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((type == Token.LOOP) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[235]++;
                        sb.append(" [continue: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[276]++;
                        appendPrintId(jump.getContinue(), printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[277]++;
                        sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[278]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[236]++;}

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[234]++;
                    sb.append(" [target: ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[279]++;
                    appendPrintId(jump.target, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[280]++;
                    sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[281]++;
                }
}
}

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[224]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[282]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[237]++;
                sb.append(' ');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[283]++;
                sb.append(getDouble());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[284]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[238]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[285]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((type == Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[239]++;
                sb.append(' ');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[286]++;
                appendPrintId(this, printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[287]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[240]++;}
}
}
}
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[288]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((lineno != -1) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[241]++;
                sb.append(' ');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[289]++;
                sb.append(lineno);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[290]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[242]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[291]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[25]++;


int CodeCoverConditionCoverageHelper_C75;

            for (PropListItem x = propListHead;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); x = x.next) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[25]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[26]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[27]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[292]++;
                int type = x.type;
                sb.append(" [");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[293]++;
                sb.append(propToString(type));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[294]++;
                sb.append(": ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[295]++;
                String value;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[296]++;
                switch (type) {
                  case TARGETBLOCK_PROP :
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[243]++; // can't add this as it recurses
                    value = "target block property";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[297]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[298]++;
                    break;
                  case LOCAL_BLOCK_PROP :
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[244]++;     // can't add this as it is dull
                    value = "last local block";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[299]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[300]++;
                    break;
                  case ISNUMBER_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[245]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[301]++;
                    switch (x.intValue) {
                      case BOTH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[246]++;
                        value = "both";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[302]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[303]++;
                        break;
                      case RIGHT:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[247]++;
                        value = "right";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[304]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[305]++;
                        break;
                      case LEFT:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[248]++;
                        value = "left";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[306]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[307]++;
                        break;
                      default:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[249]++;
                        throw Kit.codeBug();
                    }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[308]++;
                    break;
                  case SPECIALCALL_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[250]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[309]++;
                    switch (x.intValue) {
                      case SPECIALCALL_EVAL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[251]++;
                        value = "eval";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[310]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[311]++;
                        break;
                      case SPECIALCALL_WITH:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[252]++;
                        value = "with";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[312]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[313]++;
                        break;
                      default:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[253]++;
                        // NON_SPECIALCALL should not be stored
                        throw Kit.codeBug();
                    }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[314]++;
                    break;
                  case OBJECT_IDS_PROP:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[254]++; {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[315]++;
                    Object[] a = (Object[]) x.objectValue;
                    value = "[";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[316]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[317]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[28]++;


int CodeCoverConditionCoverageHelper_C76;
                    for (int i=0;(((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((i < a.length) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[28]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[29]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[30]++;
}
                        value += a[i].toString();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[318]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[319]++;
int CodeCoverConditionCoverageHelper_C77;
                        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((i+1 < a.length) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[255]++;
                            value += ", ";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[320]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[256]++;}
                    }
                    value += "]";
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[321]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[322]++;
                    break;
                  }
                  default :
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[257]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[323]++;
                    Object obj = x.objectValue;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[324]++;
int CodeCoverConditionCoverageHelper_C78;
                    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[258]++;
                        value = obj.toString();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[325]++;

                    } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[259]++;
                        value = String.valueOf(x.intValue);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[326]++;
                    }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[327]++;
                    break;
                }
                sb.append(value);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[328]++;
                sb.append(']');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[329]++;
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[210]++;}
    }

    public String toStringTree(ScriptNode treeTop) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[330]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[260]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[331]++;
            StringBuffer sb = new StringBuffer();
            toStringTreeHelper(treeTop, this, null, 0, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[332]++;
            return sb.toString();

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[261]++;}
        return null;
    }

    private static void toStringTreeHelper(ScriptNode treeTop, Node n,
                                           ObjToIntMap printIds,
                                           int level, StringBuffer sb)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[333]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[262]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[334]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((printIds == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[264]++;
                printIds = new ObjToIntMap();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[335]++;
                generatePrintIds(treeTop, printIds);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[336]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[265]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[337]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[31]++;


int CodeCoverConditionCoverageHelper_C82;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i != level) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[31]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[32]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[33]++;
}
                sb.append("    ");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[338]++;
            }
            n.toString(printIds, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[339]++;
            sb.append('\n');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[340]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[341]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[34]++;


int CodeCoverConditionCoverageHelper_C83;
            for (Node cursor = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((cursor != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false);
                 cursor = cursor.getNext())
            {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[34]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[35]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[36]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[342]++;
int CodeCoverConditionCoverageHelper_C84;
                if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((cursor.getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[266]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[343]++;
                    int fnIndex = cursor.getExistingIntProp(Node.FUNCTION_PROP);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[344]++;
                    FunctionNode fn = treeTop.getFunctionNode(fnIndex);
                    toStringTreeHelper(fn, fn, null, level + 1, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[345]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[267]++;
                    toStringTreeHelper(treeTop, cursor, printIds, level+1, sb);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[346]++;
                }
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[263]++;}
    }

    private static void generatePrintIds(Node n, ObjToIntMap map)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[347]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[268]++;
            map.put(n, map.size());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[348]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[349]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[37]++;


int CodeCoverConditionCoverageHelper_C86;
            for (Node cursor = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((cursor != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false);
                 cursor = cursor.getNext())
            {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[37]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[38]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.loops[39]++;
}
                generatePrintIds(cursor, map);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[350]++;
            }

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[269]++;}
    }

    private static void appendPrintId(Node n, ObjToIntMap printIds,
                                      StringBuffer sb)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[351]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[270]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[352]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[272]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[353]++;
                int id = printIds.get(n, -1);
                sb.append('#');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[354]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[355]++;
int CodeCoverConditionCoverageHelper_C89;
                if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((id != -1) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[274]++;
                    sb.append(id + 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[356]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[275]++;
                    sb.append("<not_available>");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[357]++;
                }

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[273]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.branches[271]++;}
    }

    protected int type = Token.ERROR;
  {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[358]++;
  } // type of the node, e.g. Token.NAME
    protected Node next;             // next sibling
    protected Node first;    // first element of a linked list of children
    protected Node last;     // last element of a linked list of children
    protected int lineno = -1;
  {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9.statements[359]++;
  }

    /**
     * Linked list of properties. Since vast majority of nodes would have
     * no more then 2 properties, linked list saves memory and provides
     * fast lookup. If this does not holds, propListHead can be replaced
     * by UintMap.
     */
    protected PropListItem propListHead;
}

class CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9 ());
  }
    public static long[] statements = new long[360];
    public static long[] branches = new long[276];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[90];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Node.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,2,1,1,1,1,1,3,2,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 89; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bizkciwex9krq9 () {
    super("org.mozilla.javascript.RHINO-SRC-Node.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 359; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 275; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 89; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Node.java");
      for (int i = 1; i <= 359; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 275; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 89; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

