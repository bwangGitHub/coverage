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
import java.util.List;

/**
 * Base type for {@link AstRoot} and {@link FunctionNode} nodes, which need to
 * collect much of the same information.
 */
public class ScriptNode extends Scope {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.ping();
  }


    private int encodedSourceStart = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[1]++;
  }
    private int encodedSourceEnd = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[2]++;
  }
    private String sourceName;
    private String encodedSource;
    private int endLineno = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[3]++;
  }

    private List<FunctionNode> functions;
    private List<RegExpLiteral> regexps;
    private List<FunctionNode> EMPTY_LIST = Collections.emptyList();
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[4]++;
  }

    private List<Symbol> symbols = new ArrayList<Symbol>(4);
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[5]++;
  }
    private int paramCount = 0;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[6]++;
  }
    private String[] variableNames;
    private boolean[] isConsts;

    private Object compilerData;
    private int tempNumber = 0;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[7]++;
  }

    {
        // during parsing, a ScriptNode or FunctionNode's top scope is itself
        this.top = this;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[8]++;
        this.type = Token.SCRIPT;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[9]++;
    }

    public ScriptNode() {
    }

    public ScriptNode(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[10]++;
    }

    /**
     * Returns the URI, path or descriptive text indicating the origin
     * of this script's source code.
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * Sets the URI, path or descriptive text indicating the origin
     * of this script's source code.
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[11]++;
    }

    /**
     * Returns the start offset of the encoded source.
     * Only valid if {@link #getEncodedSource} returns non-{@code null}.
     */
    public int getEncodedSourceStart() {
        return encodedSourceStart;
    }

    /**
     * Used by code generator.
     * @see #getEncodedSource
     */
    public void setEncodedSourceStart(int start) {
        this.encodedSourceStart = start;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[12]++;
    }

    /**
     * Returns the end offset of the encoded source.
     * Only valid if {@link #getEncodedSource} returns non-{@code null}.
     */
    public int getEncodedSourceEnd() {
        return encodedSourceEnd;
    }

    /**
     * Used by code generator.
     * @see #getEncodedSource
     */
    public void setEncodedSourceEnd(int end) {
        this.encodedSourceEnd = end;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[13]++;
    }

    /**
     * Used by code generator.
     * @see #getEncodedSource
     */
    public void setEncodedSourceBounds(int start, int end) {
        this.encodedSourceStart = start;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[14]++;
        this.encodedSourceEnd = end;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[15]++;
    }

    /**
     * Used by the code generator.
     * @see #getEncodedSource
     */
    public void setEncodedSource(String encodedSource) {
        this.encodedSource = encodedSource;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[16]++;
    }

    /**
     * Returns a canonical version of the source for this script or function,
     * for use in implementing the {@code Object.toSource} method of
     * JavaScript objects.  This source encoding is only recorded during code
     * generation.  It must be passed back to
     * {@link org.mozilla.javascript.Decompiler#decompile} to construct the
     * human-readable source string.<p>
     *
     * Given a parsed AST, you can always convert it to source code using the
     * {@link AstNode#toSource} method, although it's not guaranteed to produce
     * exactly the same results as {@code Object.toSource} with respect to
     * formatting, parenthesization and other details.
     *
     * @return the encoded source, or {@code null} if it was not recorded.
     */
    public String getEncodedSource() {
        return encodedSource;
    }

    public int getBaseLineno() {
        return lineno;
    }

    /**
     * Sets base (starting) line number for this script or function.
     * This is a one-time operation, and throws an exception if the
     * line number has already been set.
     */
    public void setBaseLineno(int lineno) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((lineno < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.lineno >= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[1]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[18]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[2]++;}
        this.lineno = lineno;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[19]++;
    }

    public int getEndLineno() {
        return endLineno;
    }

    public void setEndLineno(int lineno) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        // One time action
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((lineno < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((endLineno >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[3]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[21]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[4]++;}
        endLineno = lineno;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[22]++;
    }

    public int getFunctionCount() {
        return functions == null ? 0 : functions.size();
    }

    public FunctionNode getFunctionNode(int i) {
        return functions.get(i);
    }

    public List<FunctionNode> getFunctions() {
        return functions == null ? EMPTY_LIST : functions;
    }

    /**
     * Adds a {@link FunctionNode} to the functions table for codegen.
     * Does not set the parent of the node.
     * @return the index of the function within its parent
     */
    public int addFunction(FunctionNode fnNode) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((fnNode == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[5]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[24]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[6]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((functions == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[7]++;
            functions = new ArrayList<FunctionNode>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[26]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[8]++;}
        functions.add(fnNode);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[27]++;
        return functions.size() - 1;
    }

    public int getRegexpCount() {
        return regexps == null ? 0 : regexps.size();
    }

    public String getRegexpString(int index) {
        return regexps.get(index).getValue();
    }

    public String getRegexpFlags(int index) {
        return regexps.get(index).getFlags();
    }

    /**
     * Called by IRFactory to add a RegExp to the regexp table.
     */
    public void addRegExp(RegExpLiteral re) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((re == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[9]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[29]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[10]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((regexps == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[11]++;
            regexps = new ArrayList<RegExpLiteral>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[31]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[12]++;}
        regexps.add(re);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[32]++;
        re.putIntProp(REGEXP_PROP, regexps.size() - 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[33]++;
    }

    public int getIndexForNameNode(Node nameNode) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((variableNames == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[13]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[35]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[14]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[36]++;
        Scope node = nameNode.getScope();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[37]++;
        Symbol symbol = node == null
            ? null
            : node.getSymbol(((Name)nameNode).getIdentifier());
        return (symbol == null) ? -1 : symbol.getIndex();
    }

    public String getParamOrVarName(int index) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((variableNames == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[15]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[39]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[16]++;}
        return variableNames[index];
    }

    public int getParamCount() {
        return paramCount;
    }

    public int getParamAndVarCount() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((variableNames == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[17]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[41]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[18]++;}
        return symbols.size();
    }

    public String[] getParamAndVarNames() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((variableNames == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[19]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[43]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[20]++;}
        return variableNames;
    }

    public boolean[] getParamAndVarConst() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((variableNames == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[21]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[45]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[22]++;}
        return isConsts;
    }

    void addSymbol(Symbol symbol) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((variableNames != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[23]++; codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[47]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[24]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((symbol.getDeclType() == Token.LP) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[25]++;
            paramCount++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[49]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[26]++;}
        symbols.add(symbol);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[50]++;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[51]++;
    }

    /**
     * Assign every symbol a unique integer index. Generate arrays of variable
     * names and constness that can be indexed by those indices.
     *
     * @param flattenAllTables if true, flatten all symbol tables,
     * included nested block scope symbol tables. If false, just flatten the
     * script's or function's symbol table.
     */
    public void flattenSymbolTable(boolean flattenAllTables) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((flattenAllTables) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[27]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[53]++;
            List<Symbol> newSymbols = new ArrayList<Symbol>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.symbolTable != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[29]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[55]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[1]++;


int CodeCoverConditionCoverageHelper_C16;
                // Just replace "symbols" with the symbols in this object's
                // symbol table. Can't just work from symbolTable map since
                // we need to retain duplicate parameters.
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < symbols.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[56]++;
                    Symbol symbol = symbols.get(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[57]++;
int CodeCoverConditionCoverageHelper_C17;
                    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((symbol.getContainingTable() == this) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[31]++;
                        newSymbols.add(symbol);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[58]++;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[32]++;}
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[30]++;}
            symbols = newSymbols;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[59]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[28]++;}
        variableNames = new String[symbols.size()];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[60]++;
        isConsts = new boolean[symbols.size()];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[61]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[62]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < symbols.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[63]++;
            Symbol symbol = symbols.get(i);
            variableNames[i] = symbol.getName();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[64]++;
            isConsts[i] = symbol.getDeclType() == Token.CONST;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[65]++;
            symbol.setIndex(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[66]++;
        }
    }

    public Object getCompilerData() {
        return compilerData;
    }

    public void setCompilerData(Object data) {
        assertNotNull(data);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[67]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
        // Can only call once
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((compilerData != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[33]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[34]++;}
        compilerData = data;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[69]++;
    }

    public String getNextTempName() {
        return "$" + tempNumber++;
    }

    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[35]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[71]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[7]++;


            for (Node kid : this) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.loops[9]++;
}
                ((AstNode)kid).visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.statements[72]++;
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735.branches[36]++;}
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735 ());
  }
    public static long[] statements = new long[73];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ScriptNode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw9kx0ve51jkoxlndeauze735 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ScriptNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 72; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ScriptNode.java");
      for (int i = 1; i <= 72; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

