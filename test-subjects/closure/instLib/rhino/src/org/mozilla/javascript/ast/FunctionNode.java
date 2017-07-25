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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A JavaScript function declaration or expression.<p>
 * Node type is {@link Token#FUNCTION}.<p>
 *
 * <pre><i>FunctionDeclaration</i> :
 *        <b>function</b> Identifier ( FormalParameterListopt ) { FunctionBody }
 * <i>FunctionExpression</i> :
 *        <b>function</b> Identifieropt ( FormalParameterListopt ) { FunctionBody }
 * <i>FormalParameterList</i> :
 *        Identifier
 *        FormalParameterList , Identifier
 * <i>FunctionBody</i> :
 *        SourceElements
 * <i>Program</i> :
 *        SourceElements
 * <i>SourceElements</i> :
 *        SourceElement
 *        SourceElements SourceElement
 * <i>SourceElement</i> :
 *        Statement
 *        FunctionDeclaration</pre>
 *
 * JavaScript 1.8 introduces "function closures" of the form
 *  <pre>function ([params] ) Expression</pre>
 *
 * In this case the FunctionNode node will have no body but will have an
 * expression.
 */
public class FunctionNode extends ScriptNode {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.ping();
  }


    /**
     * There are three types of functions that can be defined. The first
     * is a function statement. This is a function appearing as a top-level
     * statement (i.e., not nested inside some other statement) in either a
     * script or a function.<p>
     *
     * The second is a function expression, which is a function appearing in
     * an expression except for the third type, which is...<p>
     *
     * The third type is a function expression where the expression is the
     * top-level expression in an expression statement.<p>
     *
     * The three types of functions have different treatment and must be
     * distinguished.<p>
     */
    public static final int FUNCTION_STATEMENT            = 1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[1]++;
  }
    public static final int FUNCTION_EXPRESSION           = 2;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[2]++;
  }
    public static final int FUNCTION_EXPRESSION_STATEMENT = 3;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[3]++;
  }

    public static enum Form { FUNCTION, GETTER, SETTER }

    private static final List<AstNode> NO_PARAMS =
        Collections.unmodifiableList(new ArrayList<AstNode>());
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[4]++;
  }

    private Name functionName;
    private List<AstNode> params;
    private AstNode body;
    private boolean isExpressionClosure;
    private Form functionForm = Form.FUNCTION;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[5]++;
  }
    private int lp = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[6]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[7]++;
  }

    // codegen variables
    private int functionType;
    private boolean needsActivation;
    private boolean isGenerator;
    private List<Node> generatorResumePoints;
    private Map<Node,int[]> liveLocals;
    private AstNode memberExprNode;

    {
        type = Token.FUNCTION;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[8]++;
    }

    public FunctionNode() {
    }

    public FunctionNode(int pos) {
        super(pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[9]++;
    }

    public FunctionNode(int pos, Name name) {
        super(pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[10]++;
        setFunctionName(name);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[11]++;
    }

    /**
     * Returns function name
     * @return function name, {@code null} for anonymous functions
     */
    public Name getFunctionName() {
        return functionName;
    }

    /**
     * Sets function name, and sets its parent to this node.
     * @param name function name, {@code null} for anonymous functions
     */
    public void setFunctionName(Name name) {
        functionName = name;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[12]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[1]++;
            name.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[14]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[2]++;}
    }

    /**
     * Returns the function name as a string
     * @return the function name, {@code ""} if anonymous
     */
    public String getName() {
        return functionName != null ? functionName.getIdentifier() : "";
    }

    /**
     * Returns the function parameter list
     * @return the function parameter list.  Returns an immutable empty
     *         list if there are no parameters.
     */
    public List<AstNode> getParams() {
        return params != null ? params : NO_PARAMS;
    }

    /**
     * Sets the function parameter list, and sets the parent for
     * each element of the list.
     * @param params the function parameter list, or {@code null} if no params
     */
    public void setParams(List<AstNode> params) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((params == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[3]++;
            this.params = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[16]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[4]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.params != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[5]++;
                this.params.clear();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[18]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[6]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[1]++;


            for (AstNode param : params) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[3]++;
}
                addParam(param);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[20]++;
  }
        }
    }

    /**
     * Adds a parameter to the function parameter list.
     * Sets the parent of the param node to this node.
     * @param param the parameter
     * @throws IllegalArgumentException if param is {@code null}
     */
    public void addParam(AstNode param) {
        assertNotNull(param);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[21]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((params == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[7]++;
            params = new ArrayList<AstNode>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[23]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[8]++;}
        params.add(param);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[24]++;
        param.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[25]++;
    }

    /**
     * Returns true if the specified {@link AstNode} node is a parameter
     * of this Function node.  This provides a way during AST traversal
     * to disambiguate the function name node from the parameter nodes.
     */
    public boolean isParam(AstNode node) {
        return params == null ? false : params.contains(node);
    }

    /**
     * Returns function body.  Normally a {@link Block}, but can be a plain
     * {@link AstNode} if it's a function closure.
     *
     * @return the body.  Can be {@code null} only if the AST is malformed.
     */
    public AstNode getBody() {
        return body;
    }

    /**
     * Sets function body, and sets its parent to this node.
     * Also sets the encoded source bounds based on the body bounds.
     * Assumes the function node absolute position has already been set,
     * and the body node's absolute position and length are set.<p>
     *
     * @param body function body.  Its parent is set to this node, and its
     * position is updated to be relative to this node.
     *
     * @throws IllegalArgumentException if body is {@code null}
     */
    public void setBody(AstNode body) {
        assertNotNull(body);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[26]++;
        this.body = body;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[27]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(body.getProp(Node.EXPRESSION_CLOSURE_PROP))) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[9]++;
            setIsExpressionClosure(true);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[29]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[10]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[30]++;
        int absEnd = body.getPosition() + body.getLength();
        body.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[31]++;
        this.setLength(absEnd - this.position);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[32]++;
        setEncodedSourceBounds(this.position, absEnd);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[33]++;
    }

    /**
     * Returns left paren position, -1 if missing
     */
    public int getLp() {
        return lp;
    }

    /**
     * Sets left paren position
     */
    public void setLp(int lp) {
        this.lp = lp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[34]++;
    }

    /**
     * Returns right paren position, -1 if missing
     */
    public int getRp() {
        return rp;
    }

    /**
     * Sets right paren position
     */
    public void setRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[35]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[36]++;
        this.rp = rp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[37]++;
    }

    /**
     * Returns whether this is a 1.8 function closure
     */
    public boolean isExpressionClosure() {
        return isExpressionClosure;
    }

    /**
     * Sets whether this is a 1.8 function closure
     */
    public void setIsExpressionClosure(boolean isExpressionClosure) {
        this.isExpressionClosure = isExpressionClosure;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[38]++;
    }

    /**
     * Return true if this function requires an Ecma-262 Activation object.
     * The Activation object is implemented by
     * {@link org.mozilla.javascript.NativeCall}, and is fairly expensive
     * to create, so when possible, the interpreter attempts to use a plain
     * call frame instead.
     *
     * @return true if this function needs activation.  It could be needed
     * if there is a lexical closure, or in a number of other situations.
     */
    public boolean requiresActivation() {
        return needsActivation;
    }

    public void setRequiresActivation() {
        needsActivation = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[39]++;
    }

    public boolean isGenerator() {
      return isGenerator;
    }

    public void setIsGenerator() {
        isGenerator = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[40]++;
    }

    public void addResumptionPoint(Node target) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((generatorResumePoints == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[11]++;
            generatorResumePoints = new ArrayList<Node>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[42]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[12]++;}
        generatorResumePoints.add(target);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[43]++;
    }

    public List<Node> getResumptionPoints() {
        return generatorResumePoints;
    }

    public Map<Node,int[]> getLiveLocals() {
        return liveLocals;
    }

    public void addLiveLocals(Node node, int[] locals) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((liveLocals == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[13]++;
            liveLocals = new HashMap<Node,int[]>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[45]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[14]++;}
        liveLocals.put(node, locals);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[46]++;
    }

    @Override
    public int addFunction(FunctionNode fnNode) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[47]++;
        int result = super.addFunction(fnNode);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getFunctionCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[15]++;
            needsActivation = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[49]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[16]++;}
        return result;
    }

    /**
     * Returns the function type (statement, expr, statement expr)
     */
    public int getFunctionType() {
        return functionType;
    }

    public void setFunctionType(int type) {
        functionType = type;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[50]++;
    }

    public boolean isGetterOrSetter() {
        return functionForm == Form.GETTER || functionForm == Form.SETTER;
    }

    public boolean isGetter() {
        return functionForm == Form.GETTER;
    }

    public boolean isSetter() {
        return functionForm == Form.SETTER;
    }

    public void setFunctionIsGetter() {
        functionForm = Form.GETTER;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[51]++;
    }

    public void setFunctionIsSetter() {
        functionForm = Form.SETTER;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[52]++;
    }

    /**
     * Rhino supports a nonstandard Ecma extension that allows you to
     * say, for instance, function a.b.c(arg1, arg) {...}, and it will
     * be rewritten at codegen time to:  a.b.c = function(arg1, arg2) {...}
     * If we detect an expression other than a simple Name in the position
     * where a function name was expected, we record that expression here.
     * <p>
     * This extension is only available by setting the CompilerEnv option
     * "isAllowMemberExprAsFunctionName" in the Parser.
     */
    public void setMemberExprNode(AstNode node) {
        memberExprNode = node;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[53]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[17]++;
            node.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[55]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[18]++;}
    }

    public AstNode getMemberExprNode() {
        return memberExprNode;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[56]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[57]++;
        sb.append("function");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[58]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[19]++;
            sb.append(" ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[60]++;
            sb.append(functionName.toSource(0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[61]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[20]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[62]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((params == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[21]++;
            sb.append("() ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[63]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[22]++;
            sb.append("(");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[64]++;
            printList(params, sb);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[65]++;
            sb.append(") ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[66]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[67]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isExpressionClosure) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[23]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[68]++;
            AstNode body = getBody();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[69]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((body.getLastChild() instanceof ReturnStatement) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[25]++;
                // omit "return" keyword, just print the expression
                body = ((ReturnStatement) body.getLastChild()).getReturnValue();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[70]++;
                sb.append(body.toSource(0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[71]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[72]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((functionType == FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[27]++;
                    sb.append(";");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[73]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[28]++;}

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[26]++;
                // should never happen
                sb.append(" ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[74]++;
                sb.append(body.toSource(0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[75]++;
            }

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[24]++;
            sb.append(getBody().toSource(depth).trim());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[76]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[77]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((functionType == FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[29]++;
            sb.append("\n");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[78]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[30]++;}
        return sb.toString();
    }

    /**
     * Visits this node, the function name node if supplied,
     * the parameters, and the body.  If there is a member-expr node,
     * it is visited last.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[79]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[31]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[33]++;
                functionName.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[81]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[34]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[82]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[4]++;


            for (AstNode param : getParams()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.loops[6]++;
}
                param.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[83]++;
            }
            getBody().visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[84]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[85]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isExpressionClosure) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[35]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[86]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((memberExprNode != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[37]++;
                    memberExprNode.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.statements[87]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd.branches[32]++;}
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-FunctionNode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623cpfhzvcjtcbh9pd () {
    super("org.mozilla.javascript.ast.RHINO-SRC-FunctionNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-FunctionNode.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

