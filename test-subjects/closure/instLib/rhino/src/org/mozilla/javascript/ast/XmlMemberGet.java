/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for E4X ".@" and ".." expressions, such as
 * {@code foo..bar}, {@code foo..@bar}, {@code @foo.@bar}, and
 * {@code foo..@ns::*}.  The right-hand node is always an
 * {@link XmlRef}. <p>
 *
 * Node type is {@link Token#DOT} or {@link Token#DOTDOT}.
 */
public class XmlMemberGet extends InfixExpression {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.ping();
  }


    {
        type = Token.DOTDOT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[1]++;
    }

    public XmlMemberGet() {
    }

    public XmlMemberGet(int pos) {
        super(pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[2]++;
    }

    public XmlMemberGet(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[3]++;
    }

    public XmlMemberGet(int pos, int len, AstNode target, XmlRef ref) {
        super(pos, len, target, ref);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[4]++;
    }

    /**
     * Constructs a new {@code XmlMemberGet} node.
     * Updates bounds to include {@code target} and {@code ref} nodes.
     */
    public XmlMemberGet(AstNode target, XmlRef ref) {
        super(target, ref);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[5]++;
    }

    public XmlMemberGet(AstNode target, XmlRef ref, int opPos) {
        super(Token.DOTDOT, target, ref, opPos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[6]++;
    }

    /**
     * Returns the object on which the XML member-ref expression
     * is being evaluated.  Should never be {@code null}.
     */
    public AstNode getTarget() {
        return getLeft();
    }

    /**
     * Sets target object, and sets its parent to this node.
     * @throws IllegalArgumentException if {@code target} is {@code null}
     */
    public void setTarget(AstNode target) {
        setLeft(target);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[7]++;
    }

    /**
     * Returns the right-side XML member ref expression.
     * Should never be {@code null} unless the code is malformed.
     */
    public XmlRef getMemberRef() {
        return (XmlRef)getRight();
    }

    /**
     * Sets the XML member-ref expression, and sets its parent
     * to this node.
     * @throws IllegalArgumentException if property is {@code null}
     */
    public void setProperty(XmlRef ref) {
        setRight(ref);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[8]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[9]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[10]++;
        sb.append(getLeft().toSource(0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[11]++;
        sb.append(operatorToString(getType()));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[12]++;
        sb.append(getRight().toSource(0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h.statements[13]++;
        return sb.toString();
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmpr31zwydubszzcteiya11al10h () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlMemberGet.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlMemberGet.java");
      for (int i = 1; i <= 13; i++) {
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

