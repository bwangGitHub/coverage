/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for a simple name.  A simple name is an identifier that is
 * not a keyword. Node type is {@link Token#NAME}.<p>
 *
 * This node type is also used to represent certain non-identifier names that
 * are part of the language syntax.  It's used for the "get" and "set"
 * pseudo-keywords for object-initializer getter/setter properties, and it's
 * also used for the "*" wildcard in E4X XML namespace and name expressions.
 */
public class Name extends AstNode {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.ping();
  }


    private String identifier;
    private Scope scope;

    {
        type = Token.NAME;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[1]++;
    }

    public Name() {
    }

    public Name(int pos) {
        super(pos);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[2]++;
    }

    public Name(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[3]++;
    }

    /**
     * Constructs a new {@link Name}
     * @param pos node start position
     * @param len node length
     * @param name the identifier associated with this {@code Name} node
     */
    public Name(int pos, int len, String name) {
        super(pos, len);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[4]++;
        setIdentifier(name);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[5]++;
    }

    public Name(int pos, String name) {
        super(pos);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[6]++;
        setIdentifier(name);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[7]++;
        setLength(name.length());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[8]++;
    }

    /**
     * Returns the node's identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the node's identifier
     * @throws IllegalArgumentException if identifier is null
     */
    public void setIdentifier(String identifier) {
        assertNotNull(identifier);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[9]++;
        this.identifier = identifier;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[10]++;
        setLength(identifier.length());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[11]++;
    }

    /**
     * Set the {@link Scope} associated with this node.  This method does not
     * set the scope's ast-node field to this node.  The field exists only
     * for temporary storage by the code generator.  Not every name has an
     * associated scope - typically only function and variable names (but not
     * property names) are registered in a scope.
     *
     * @param s the scope.  Can be null.  Doesn't set any fields in the
     * scope.
     */
    public void setScope(Scope s) {
        scope = s;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[12]++;
    }

    /**
     * Return the {@link Scope} associated with this node.  This is
     * <em>only</em> used for (and set by) the code generator, so it will always
     * be null in frontend AST-processing code.  Use {@link #getDefiningScope}
     * to find the lexical {@code Scope} in which this {@code Name} is defined,
     * if any.
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * Returns the {@link Scope} in which this {@code Name} is defined.
     * @return the scope in which this name is defined, or {@code null}
     * if it's not defined in the current lexical scope chain
     */
    public Scope getDefiningScope() {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[13]++;
        Scope enclosing = getEnclosingScope();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[14]++;
        String name = getIdentifier();
        return enclosing == null ? null : enclosing.getDefiningScope(name);
    }

    /**
     * Return true if this node is known to be defined as a symbol in a
     * lexical scope other than the top-level (global) scope.
     *
     * @return {@code true} if this name appears as local variable, a let-bound
     * variable not in the global scope, a function parameter, a loop
     * variable, the property named in a {@link PropertyGet}, or in any other
     * context where the node is known not to resolve to the global scope.
     * Returns {@code false} if the node is defined in the top-level scope
     * (i.e., its defining scope is an {@link AstRoot} object), or if its
     * name is not defined as a symbol in the symbol table, in which case it
     * may be an external or built-in name (or just an error of some sort.)
     */
    public boolean isLocalName() {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[15]++;
        Scope scope = getDefiningScope();
        return scope != null && scope.getParentScope() != null;
    }

    /**
     * Return the length of this node's identifier, to let you pretend
     * it's a {@link String}.  Don't confuse this method with the
     * {@link AstNode#getLength} method, which returns the range of
     * characters that this node overlaps in the source input.
     */
    public int length() {
        return identifier == null ? 0 : identifier.length();
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth) + (identifier == null ? "<null>" : identifier);
    }

    /**
     * Visits this node.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp.statements[16]++;
    }
}

class CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1cnp1i09zf4s7wx3birx4cvlc18jmp () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Name.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Name.java");
      for (int i = 1; i <= 16; i++) {
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

