/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a scope in the lexical scope chain.  Base type for
 * all {@link AstNode} implementations that can introduce a new scope.
 */
public class Scope extends Jump {
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.ping();
  }


    // Use LinkedHashMap so that the iteration order is the insertion order
    protected Map<String,Symbol> symbolTable;
    protected Scope parentScope;
    protected ScriptNode top;     // current script or function scope

    private List<Scope> childScopes;

    {
        this.type = Token.BLOCK;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[1]++;
    }

    public Scope() {
    }

    public Scope(int pos) {
        this.position = pos;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[2]++;
    }

    public Scope(int pos, int len) {
        this(pos);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[3]++;
        this.length = len;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[4]++;
    }

    public Scope getParentScope() {
        return parentScope;
    }

    /**
     * Sets parent scope
     */
    public void setParentScope(Scope parentScope) {
        this.parentScope = parentScope;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[5]++;
        this.top = parentScope == null ? (ScriptNode)this : parentScope.top;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[6]++;
    }

    /**
     * Used only for code generation.
     */
    public void clearParentScope() {
        this.parentScope = null;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[7]++;
    }

    /**
     * Return a list of the scopes whose parent is this scope.
     * @return the list of scopes we enclose, or {@code null} if none
     */
    public List<Scope> getChildScopes() {
        return childScopes;
    }

    /**
     * Add a scope to our list of child scopes.
     * Sets the child's parent scope to this scope.
     * @throws IllegalStateException if the child's parent scope is
     * non-{@code null}
     */
    public void addChildScope(Scope child) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((childScopes == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[1]++;
            childScopes = new ArrayList<Scope>();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[9]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[2]++;}
        childScopes.add(child);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[10]++;
        child.setParentScope(this);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[11]++;
    }

    /**
     * Used by the parser; not intended for typical use.
     * Changes the parent-scope links for this scope's child scopes
     * to the specified new scope.  Copies symbols from this scope
     * into new scope.
     *
     * @param newScope the scope that will replace this one on the
     *        scope stack.
     */
    public void replaceWith(Scope newScope) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((childScopes != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[3]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[1]++;


            for (Scope kid : childScopes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[1]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[2]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[3]++;
}
                newScope.addChildScope(kid);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[14]++;  // sets kid's parent
            }
            childScopes.clear();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[15]++;
            childScopes = null;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[16]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[4]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((symbolTable != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((symbolTable.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[5]++;
            joinScopes(this, newScope);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[18]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[6]++;}
    }

    /**
     * Returns current script or function scope
     */
    public ScriptNode getTop() {
        return top;
    }

    /**
     * Sets top current script or function scope
     */
    public void setTop(ScriptNode top) {
        this.top = top;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[19]++;
    }

    /**
     * Creates a new scope node, moving symbol table information
     * from "scope" to the new node, and making "scope" a nested
     * scope contained by the new node.
     * Useful for injecting a new scope in a scope chain.
     */
    public static Scope splitScope(Scope scope) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[20]++;
        Scope result = new Scope(scope.getType());
        result.symbolTable = scope.symbolTable;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[21]++;
        scope.symbolTable = null;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[22]++;
        result.parent = scope.parent;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[23]++;
        result.setParentScope(scope.getParentScope());
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[24]++;
        result.setParentScope(result);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[25]++;
        scope.parent = result;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[26]++;
        result.top = scope.top;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[27]++;
        return result;
    }

    /**
     * Copies all symbols from source scope to dest scope.
     */
    public static void joinScopes(Scope source, Scope dest) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[28]++;
        Map<String,Symbol> src = source.ensureSymbolTable();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[29]++;
        Map<String,Symbol> dst = dest.ensureSymbolTable();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Collections.disjoint(src.keySet(), dst.keySet())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[7]++;
            codeBug();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[31]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[8]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[4]++;


        for (Map.Entry<String, Symbol> entry: src.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[4]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[5]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[6]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[33]++;
            Symbol sym = entry.getValue();
            sym.setContainingTable(dest);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[34]++;
            dst.put(entry.getKey(), sym);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[35]++;
        }
    }

    /**
     * Returns the scope in which this name is defined
     * @param name the symbol to look up
     * @return this {@link Scope}, one of its parent scopes, or {@code null} if
     * the name is not defined any this scope chain
     */
    public Scope getDefiningScope(String name) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[36]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
        for (Scope s = this;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); s = s.parentScope) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[7]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[8]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[9]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[37]++;
            Map<String,Symbol> symbolTable = s.getSymbolTable();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((symbolTable != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((symbolTable.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[9]++;
                return s;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[10]++;}
        }
        return null;
    }

    /**
     * Looks up a symbol in this scope.
     * @param name the symbol name
     * @return the Symbol, or {@code null} if not found
     */
    public Symbol getSymbol(String name) {
        return symbolTable == null ? null : symbolTable.get(name);
    }

    /**
     * Enters a symbol into this scope.
     */
    public void putSymbol(Symbol symbol) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((symbol.getName() == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[11]++;
            throw new IllegalArgumentException("null symbol name");
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[12]++;}
        ensureSymbolTable();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[40]++;
        symbolTable.put(symbol.getName(), symbol);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[41]++;
        symbol.setContainingTable(this);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[42]++;
        top.addSymbol(symbol);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[43]++;
    }

    /**
     * Returns the symbol table for this scope.
     * @return the symbol table.  May be {@code null}.
     */
    public Map<String,Symbol> getSymbolTable() {
        return symbolTable;
    }

    /**
     * Sets the symbol table for this scope.  May be {@code null}.
     */
    public void setSymbolTable(Map<String, Symbol> table) {
        symbolTable = table;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[44]++;
    }

    private Map<String,Symbol> ensureSymbolTable() {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((symbolTable == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[13]++;
            symbolTable = new LinkedHashMap<String,Symbol>(5);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[46]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[14]++;}
        return symbolTable;
    }

    /**
     * Returns a copy of the child list, with each child cast to an
     * {@link AstNode}.
     * @throws ClassCastException if any non-{@code AstNode} objects are
     * in the child list, e.g. if this method is called after the code
     * generator begins the tree transformation.
     */
    public List<AstNode> getStatements() {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[47]++;
        List<AstNode> stmts = new ArrayList<AstNode>();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[48]++;
        Node n = getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[49]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[10]++;


int CodeCoverConditionCoverageHelper_C9;
        while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[10]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[11]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[12]++;
}
            stmts.add((AstNode)n);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[50]++;
            n = n.getNext();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[51]++;
        }
        return stmts;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[52]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[53]++;
        sb.append("{\n");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[54]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[55]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[13]++;


        for (Node kid : this) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[13]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[14]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[15]++;
}
            sb.append(((AstNode)kid).toSource(depth+1));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[56]++;
        }
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[57]++;
        sb.append("}\n");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[58]++;
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[15]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[60]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[16]++;


            for (Node kid : this) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[16]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[17]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.loops[18]++;
}
                ((AstNode)kid).visit(v);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.statements[61]++;
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht.branches[16]++;}
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-Scope.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,2,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bps5gkubuo2ti6ht () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Scope.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Scope.java");
      for (int i = 1; i <= 61; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

