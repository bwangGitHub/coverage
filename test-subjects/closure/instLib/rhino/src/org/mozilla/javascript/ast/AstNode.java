/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for AST node types.  The goal of the AST is to represent the
 * physical source code, to make it useful for code-processing tools such
 * as IDEs or pretty-printers.  The parser must not rewrite the parse tree
 * when producing this representation. <p>
 *
 * The {@code AstNode} hierarchy sits atop the older {@link Node} class,
 * which was designed for code generation.  The {@code Node} class is a
 * flexible, weakly-typed class suitable for creating and rewriting code
 * trees, but using it requires you to remember the exact ordering of the
 * child nodes, which are kept in a linked list.  The {@code AstNode}
 * hierarchy is a strongly-typed facade with named accessors for children
 * and common properties, but under the hood it's still using a linked list
 * of child nodes.  It isn't a very good idea to use the child list directly
 * unless you know exactly what you're doing.</p>
 *
 * Note that {@code AstNode} records additional information, including
 * the node's position, length, and parent node.  Also, some {@code AstNode}
 * subclasses record some of their child nodes in instance members, since
 * they are not needed for code generation.  In a nutshell, only the code
 * generator should be mixing and matching {@code AstNode} and {@code Node}
 * objects.<p>
 *
 * All offset fields in all subclasses of AstNode are relative to their
 * parent.  For things like paren, bracket and keyword positions, the
 * position is relative to the current node.  The node start position is
 * relative to the parent node. <p>
 *
 * During the actual parsing, node positions are absolute; adding the node to
 * its parent fixes up the offsets to be relative.  By the time you see the AST
 * (e.g. using the {@code Visitor} interface), the offsets are relative. <p>
 *
 * {@code AstNode} objects have property lists accessible via the
 * {@link #getProp} and {@link #putProp} methods.  The property lists are
 * integer-keyed with arbitrary {@code Object} values.  For the most part the
 * parser generating the AST avoids using properties, preferring fields for
 * elements that are always set.  Property lists are intended for user-defined
 * annotations to the tree.  The Rhino code generator acts as a client and
 * uses node properties extensively.  You are welcome to use the property-list
 * API for anything your client needs.<p>
 *
 * This hierarchy does not have separate branches for expressions and
 * statements, as the distinction in JavaScript is not as clear-cut as in
 * Java or C++. <p>
 */
public abstract class AstNode extends Node implements Comparable<AstNode> {
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.ping();
  }


    protected int position = -1;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[1]++;
  }
    protected int length = 1;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[2]++;
  }
    protected AstNode parent;

    private static Map<Integer,String> operatorNames =
            new HashMap<Integer,String>();
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[3]++;
  }

    static {
        operatorNames.put(Token.IN, "in");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[4]++;
        operatorNames.put(Token.TYPEOF, "typeof");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[5]++;
        operatorNames.put(Token.INSTANCEOF, "instanceof");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[6]++;
        operatorNames.put(Token.DELPROP, "delete");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[7]++;
        operatorNames.put(Token.COMMA, ",");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[8]++;
        operatorNames.put(Token.COLON, ":");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[9]++;
        operatorNames.put(Token.OR, "||");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[10]++;
        operatorNames.put(Token.AND, "&&");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[11]++;
        operatorNames.put(Token.INC, "++");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[12]++;
        operatorNames.put(Token.DEC, "--");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[13]++;
        operatorNames.put(Token.BITOR, "|");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[14]++;
        operatorNames.put(Token.BITXOR, "^");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[15]++;
        operatorNames.put(Token.BITAND, "&");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[16]++;
        operatorNames.put(Token.EQ, "==");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[17]++;
        operatorNames.put(Token.NE, "!=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[18]++;
        operatorNames.put(Token.LT, "<");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[19]++;
        operatorNames.put(Token.GT, ">");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[20]++;
        operatorNames.put(Token.LE, "<=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[21]++;
        operatorNames.put(Token.GE, ">=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[22]++;
        operatorNames.put(Token.LSH, "<<");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[23]++;
        operatorNames.put(Token.RSH, ">>");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[24]++;
        operatorNames.put(Token.URSH, ">>>");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[25]++;
        operatorNames.put(Token.ADD, "+");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[26]++;
        operatorNames.put(Token.SUB, "-");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[27]++;
        operatorNames.put(Token.MUL, "*");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[28]++;
        operatorNames.put(Token.DIV, "/");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[29]++;
        operatorNames.put(Token.MOD, "%");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[30]++;
        operatorNames.put(Token.NOT, "!");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[31]++;
        operatorNames.put(Token.BITNOT, "~");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[32]++;
        operatorNames.put(Token.POS, "+");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[33]++;
        operatorNames.put(Token.NEG, "-");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[34]++;
        operatorNames.put(Token.SHEQ, "===");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[35]++;
        operatorNames.put(Token.SHNE, "!==");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[36]++;
        operatorNames.put(Token.ASSIGN, "=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[37]++;
        operatorNames.put(Token.ASSIGN_BITOR, "|=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[38]++;
        operatorNames.put(Token.ASSIGN_BITAND, "&=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[39]++;
        operatorNames.put(Token.ASSIGN_LSH, "<<=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[40]++;
        operatorNames.put(Token.ASSIGN_RSH, ">>=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[41]++;
        operatorNames.put(Token.ASSIGN_URSH, ">>>=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[42]++;
        operatorNames.put(Token.ASSIGN_ADD, "+=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[43]++;
        operatorNames.put(Token.ASSIGN_SUB, "-=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[44]++;
        operatorNames.put(Token.ASSIGN_MUL, "*=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[45]++;
        operatorNames.put(Token.ASSIGN_DIV, "/=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[46]++;
        operatorNames.put(Token.ASSIGN_MOD, "%=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[47]++;
        operatorNames.put(Token.ASSIGN_BITXOR, "^=");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[48]++;
        operatorNames.put(Token.VOID, "void");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[49]++;
    }

    public static class PositionComparator implements Comparator<AstNode>, Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Sorts nodes by (relative) start position.  The start positions are
         * relative to their parent, so this comparator is only meaningful for
         * comparing siblings.
         */
        public int compare(AstNode n1, AstNode n2) {
            return n1.position - n2.position;
        }
    }

    public AstNode() {
        super(Token.ERROR);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[50]++;
    }

    /**
     * Constructs a new AstNode
     * @param pos the start position
     */
    public AstNode(int pos) {
        this();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[51]++;
        position = pos;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[52]++;
    }

    /**
     * Constructs a new AstNode
     * @param pos the start position
     * @param len the number of characters spanned by the node in the source
     * text
     */
    public AstNode(int pos, int len) {
        this();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[53]++;
        position = pos;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[54]++;
        length = len;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[55]++;
    }

    /**
     * Returns relative position in parent
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets relative position in parent
     */
    public void setPosition(int position) {
        this.position = position;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[56]++;
    }

    /**
     * Returns the absolute document position of the node.
     * Computes it by adding the node's relative position
     * to the relative positions of all its parents.
     */
    public int getAbsolutePosition() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[57]++;
        int pos = position;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[58]++;
        AstNode parent = this.parent;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[59]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[3]++;
}
            pos += parent.getPosition();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[60]++;
            parent = parent.getParent();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[61]++;
        }
        return pos;
    }

    /**
     * Returns node length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets node length
     */
    public void setLength(int length) {
        this.length = length;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[62]++;
    }

    /**
     * Sets the node start and end positions.
     * Computes the length as ({@code end} - {@code position}).
     */
    public void setBounds(int position, int end) {
        setPosition(position);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[63]++;
        setLength(end - position);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[64]++;
    }

    /**
     * Make this node's position relative to a parent.
     * Typically only used by the parser when constructing the node.
     * @param parentPosition the absolute parent position; the
     * current node position is assumed to be absolute and is
     * decremented by parentPosition.
     */
    public void setRelative(int parentPosition) {
        this.position -= parentPosition;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[65]++;
    }

    /**
     * Returns the node parent, or {@code null} if it has none
     */
    public AstNode getParent() {
        return parent;
    }

    /**
     * Sets the node parent.  This method automatically adjusts the
     * current node's start position to be relative to the new parent.
     * @param parent the new parent. Can be {@code null}.
     */
    public void setParent(AstNode parent) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[66]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parent == this.parent) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[1]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[2]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[67]++;
int CodeCoverConditionCoverageHelper_C3;

        // Convert position back to absolute.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.parent != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[3]++;
            setRelative(-this.parent.getPosition());
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[68]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[4]++;}

        this.parent = parent;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[69]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[70]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[5]++;
            setRelative(parent.getPosition());
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[71]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[6]++;}
    }

    /**
     * Adds a child or function to the end of the block.
     * Sets the parent of the child to this node, and fixes up
     * the start position of the child to be relative to this node.
     * Sets the length of this node to include the new child.
     * @param kid the child
     * @throws IllegalArgumentException if kid is {@code null}
     */
    public void addChild(AstNode kid) {
        assertNotNull(kid);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[72]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[73]++;
        int end = kid.getPosition() + kid.getLength();
        setLength(end - this.getPosition());
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[74]++;
        addChildToBack(kid);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[75]++;
        kid.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[76]++;
    }

    /**
     * Returns the root of the tree containing this node.
     * @return the {@link AstRoot} at the root of this node's parent
     * chain, or {@code null} if the topmost parent is not an {@code AstRoot}.
     */
    public AstRoot getAstRoot() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[77]++;
        AstNode parent = this;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[78]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;  // this node could be the AstRoot
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((parent instanceof AstRoot) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[6]++;
}
            parent = parent.getParent();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[79]++;
        }
        return (AstRoot)parent;
    }

    /**
     * Emits source code for this node.  Callee is responsible for calling this
     * function recursively on children, incrementing indent as appropriate.<p>
     *
     * Note: if the parser was in error-recovery mode, some AST nodes may have
     * {@code null} children that are expected to be non-{@code null}
     * when no errors are present.  In this situation, the behavior of the
     * {@code toSource} method is undefined: {@code toSource}
     * implementations may assume that the AST node is error-free, since it is
     * intended to be invoked only at runtime after a successful parse.<p>
     *
     * @param depth the current recursion depth, typically beginning at 0
     * when called on the root node.
     */
    public abstract String toSource(int depth);

    /**
     * Prints the source indented to depth 0.
     */
    public String toSource() {
        return this.toSource(0);
    }

    /**
     * Constructs an indentation string.
     * @param indent the number of indentation steps
     */
    public String makeIndent(int indent) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[80]++;
        StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[81]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < indent) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[9]++;
}
            sb.append("  ");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[82]++;
        }
        return sb.toString();
    }

    /**
     * Returns a short, descriptive name for the node, such as
     * "ArrayComprehension".
     */
    public String shortName() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[83]++;
        String classname = getClass().getName();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[84]++;
        int last = classname.lastIndexOf(".");
        return classname.substring(last + 1);
    }

    /**
     * Returns the string name for this operator.
     * @param op the token type, e.g. {@link Token#ADD} or {@link Token#TYPEOF}
     * @return the source operator string, such as "+" or "typeof"
     */
    public static String operatorToString(int op) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[85]++;
        String result = operatorNames.get(op);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[86]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[7]++;
            throw new IllegalArgumentException("Invalid operator: " + op);
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[8]++;}
        return result;
    }

    /**
     * Visits this node and its children in an arbitrary order. <p>
     *
     * It's up to each node subclass to decide the order for processing
     * its children.  The subclass also decides (and should document)
     * which child nodes are not passed to the {@code NodeVisitor}.
     * For instance, nodes representing keywords like {@code each} or
     * {@code in} may not be passed to the visitor object.  The visitor
     * can simply query the current node for these children if desired.<p>
     *
     * Generally speaking, the order will be deterministic; the order is
     * whatever order is decided by each child node.  Normally child nodes
     * will try to visit their children in lexical order, but there may
     * be exceptions to this rule.<p>
     *
     * @param visitor the object to call with this node and its children
     */
    public abstract void visit(NodeVisitor visitor);

    // subclasses with potential side effects should override this
    public boolean hasSideEffects()
    {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[87]++;
        switch (getType()) {
          case Token.ASSIGN:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[9]++;
          case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[10]++;
          case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[11]++;
          case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[12]++;
          case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[13]++;
          case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[14]++;
          case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[15]++;
          case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[16]++;
          case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[17]++;
          case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[18]++;
          case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[19]++;
          case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[20]++;
          case Token.BLOCK:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[21]++;
          case Token.BREAK:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[22]++;
          case Token.CALL:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[23]++;
          case Token.CATCH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[24]++;
          case Token.CATCH_SCOPE:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[25]++;
          case Token.CONST:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[26]++;
          case Token.CONTINUE:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[27]++;
          case Token.DEC:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[28]++;
          case Token.DELPROP:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[29]++;
          case Token.DEL_REF:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[30]++;
          case Token.DO:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[31]++;
          case Token.ELSE:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[32]++;
          case Token.ENTERWITH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[33]++;
          case Token.ERROR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[34]++;         // Avoid cascaded error messages
          case Token.EXPORT:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[35]++;
          case Token.EXPR_RESULT:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[36]++;
          case Token.FINALLY:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[37]++;
          case Token.FUNCTION:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[38]++;
          case Token.FOR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[39]++;
          case Token.GOTO:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[40]++;
          case Token.IF:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[41]++;
          case Token.IFEQ:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[42]++;
          case Token.IFNE:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[43]++;
          case Token.IMPORT:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[44]++;
          case Token.INC:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[45]++;
          case Token.JSR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[46]++;
          case Token.LABEL:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[47]++;
          case Token.LEAVEWITH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[48]++;
          case Token.LET:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[49]++;
          case Token.LETEXPR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[50]++;
          case Token.LOCAL_BLOCK:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[51]++;
          case Token.LOOP:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[52]++;
          case Token.NEW:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[53]++;
          case Token.REF_CALL:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[54]++;
          case Token.RETHROW:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[55]++;
          case Token.RETURN:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[56]++;
          case Token.RETURN_RESULT:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[57]++;
          case Token.SEMI:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[58]++;
          case Token.SETELEM:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[59]++;
          case Token.SETELEM_OP:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[60]++;
          case Token.SETNAME:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[61]++;
          case Token.SETPROP:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[62]++;
          case Token.SETPROP_OP:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[63]++;
          case Token.SETVAR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[64]++;
          case Token.SET_REF:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[65]++;
          case Token.SET_REF_OP:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[66]++;
          case Token.SWITCH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[67]++;
          case Token.TARGET:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[68]++;
          case Token.THROW:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[69]++;
          case Token.TRY:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[70]++;
          case Token.VAR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[71]++;
          case Token.WHILE:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[72]++;
          case Token.WITH:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[73]++;
          case Token.WITHEXPR:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[74]++;
          case Token.YIELD:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[75]++;
            return true;

          default:
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[76]++;
            return false;
        }
    }

    /**
     * Bounces an IllegalArgumentException up if arg is {@code null}.
     * @param arg any method argument
     * @throws IllegalArgumentException if the argument is {@code null}
     */
    protected void assertNotNull(Object arg) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[88]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((arg == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[77]++;
            throw new IllegalArgumentException("arg cannot be null");
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[78]++;}
    }

    /**
     * Prints a comma-separated item list into a {@link StringBuilder}.
     * @param items a list to print
     * @param sb a {@link StringBuilder} into which to print
     */
    protected <T extends AstNode> void printList(List<T> items,
                                                 StringBuilder sb) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[89]++;
        int max = items.size();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[90]++;
        int count = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[91]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[10]++;


        for (AstNode item : items) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[12]++;
}
            sb.append(item.toSource(0));
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[92]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[93]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((count++ < max-1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[79]++;
                sb.append(", ");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[94]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[80]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[95]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((item instanceof EmptyExpression) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[81]++;
                sb.append(",");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[96]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[82]++;}
}
        }
    }

    /**
     * @see Kit#codeBug
     */
    public static RuntimeException codeBug()
        throws RuntimeException
    {
        throw Kit.codeBug();
    }

    // TODO(stevey):  think of a way to have polymorphic toString
    // methods while keeping the ability to use Node.toString for
    // dumping the IR with Token.printTrees.  Most likely:  change
    // Node.toString to be Node.dumpTree and change callers to use that.
    // For now, need original toString, to compare output to old Rhino's.

//     @Override
//     public String toString() {
//         return this.getClass().getName() + ": " +
//             Token.typeToName(getType());
//     }

    /**
     * Returns the innermost enclosing function, or {@code null} if not in a
     * function.  Begins the search with this node's parent.
     * @return the {@link FunctionNode} enclosing this node, else {@code null}
     */
    public FunctionNode getEnclosingFunction() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[97]++;
        AstNode parent = this.getParent();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[98]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[13]++;


int CodeCoverConditionCoverageHelper_C11;
        while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((parent instanceof FunctionNode) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[13]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[14]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[15]++;
}
            parent = parent.getParent();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[99]++;
        }
        return (FunctionNode)parent;
    }

    /**
     * Returns the innermost enclosing {@link Scope} node, or {@code null}
     * if we're not nested in a scope.  Begins the search with this node's parent.
     * Note that this is not the same as the defining scope for a {@link Name}.
     *
     * @return the {@link Scope} enclosing this node, else {@code null}
     */
    public Scope getEnclosingScope() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[100]++;
        AstNode parent = this.getParent();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[101]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;
        while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parent instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[16]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[17]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[18]++;
}
            parent = parent.getParent();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[102]++;
        }
        return (Scope)parent;
    }

    /**
     * Permits AST nodes to be sorted based on start position and length.
     * This makes it easy to sort Comment and Error nodes into a set of
     * other AST nodes:  just put them all into a {@link java.util.SortedSet},
     * for instance.
     * @param other another node
     * @return -1 if this node's start position is less than {@code other}'s
     * start position.  If tied, -1 if this node's length is less than
     * {@code other}'s length.  If the lengths are equal, sorts abitrarily
     * on hashcode unless the nodes are the same per {@link #equals}.
     */
    public int compareTo(AstNode other) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[103]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.equals(other)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[83]++; return 0;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[84]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[104]++;
        int abs1 = this.getAbsolutePosition();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[105]++;
        int abs2 = other.getAbsolutePosition();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[106]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((abs1 < abs2) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[85]++; return -1;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[86]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[107]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((abs2 < abs1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[87]++; return 1;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[88]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[108]++;
        int len1 = this.getLength();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[109]++;
        int len2 = other.getLength();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[110]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((len1 < len2) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[89]++; return -1;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[90]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[111]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((len2 < len1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[91]++; return 1;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[92]++;}
        return this.hashCode() - other.hashCode();
    }

    /**
     * Returns the depth of this node.  The root is depth 0, its
     * children are depth 1, and so on.
     * @return the node depth in the tree
     */
    public int depth() {
        return parent == null ? 0 : 1 + parent.depth();
    }

    protected static class DebugPrintVisitor implements NodeVisitor {
        private StringBuilder buffer;
        private static final int DEBUG_INDENT = 2;
        public DebugPrintVisitor(StringBuilder buf) {
            buffer = buf;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[112]++;
        }
        public String toString() {
            return buffer.toString();
        }
        private String makeIndent(int depth) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[113]++;
            StringBuilder sb = new StringBuilder(DEBUG_INDENT * depth);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[114]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[19]++;


int CodeCoverConditionCoverageHelper_C18;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < (DEBUG_INDENT * depth)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[19]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[20]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.loops[21]++;
}
                sb.append(" ");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[115]++;
            }
            return sb.toString();
        }
        public boolean visit(AstNode node) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[116]++;
            int tt = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[117]++;
            String name = Token.typeToName(tt);
            buffer.append(node.getAbsolutePosition()).append("\t");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[118]++;
            buffer.append(makeIndent(node.depth()));
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[119]++;
            buffer.append(name).append(" ");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[120]++;
            buffer.append(node.getPosition()).append(" ");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[121]++;
            buffer.append(node.getLength());
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[122]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[123]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((tt == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[93]++;
                buffer.append(" ").append(((Name)node).getIdentifier());
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[124]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[94]++;}
            buffer.append("\n");
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[125]++;
            return true;  // process kids
        }
    }

    /**
     * Return the line number recorded for this node.
     * If no line number was recorded, searches the parent chain.
     * @return the nearest line number, or -1 if none was found
     */
    @Override
    public int getLineno() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[126]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((lineno != -1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[95]++;
            return lineno;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[96]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[127]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[97]++;
            return parent.getLineno();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.branches[98]++;}
        return -1;
    }

    /**
     * Returns a debugging representation of the parse tree
     * starting at this node.
     * @return a very verbose indented printout of the tree.
     * The format of each line is:  abs-pos  name position length [identifier]
     */
    public String debugPrint() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[128]++;
        DebugPrintVisitor dpv = new DebugPrintVisitor(new StringBuilder(1000));
        visit(dpv);
CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5.statements[129]++;
        return dpv.toString();
    }
}

class CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5 ());
  }
    public static long[] statements = new long[130];
    public static long[] branches = new long[99];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-AstNode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$di175yxae5e37vt02f3sad0u423zbamqn5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-AstNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 129; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 98; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-AstNode.java");
      for (int i = 1; i <= 129; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 98; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

