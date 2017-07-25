/*
 * Copyright 2008 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Replaces references to aliasable keyword literals (true, false,
 * null) with variables and statements (throw) with functions declared in the
 * global scope. When combined with RenameVars, this pass helps to reduce the
 * number of bytes taken up by references to these keywords by replacing them
 * with references to variables and functions with shorter names.</p>
 *
 */
class AliasKeywords implements CompilerPass {
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.ping();
  }

  /** Callback that finds the nodes that we will alias. */
  private class FindAliasableNodes extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[1]++;
      final int type = n.getType();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isAliasableType(type)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[1]++;
        visitAliasableNode(n, parent);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[3]++;

      } else {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[2]++;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[4]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[3]++;
        visitNameNode(n);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[5]++;

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[4]++;}
}
    }

    /**
     * Find the AliasSpecification associated with the node, and tell
     * that AliasSpecification about the new node.
     */
    private void visitAliasableNode(Node n, Node parent) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[6]++;
      AliasSpecification aliasableNodes = aliasTypes.get(n.getType());
      aliasableNodes.visit(n, parent);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[7]++;
    }

    /**
     * Sanity check that our aliases are not already defined by
     * someone else.
     */
    private void visitNameNode(Node n) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isAliasDefinition(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[5]++;
        throw new IllegalStateException(
            "Existing alias definition for " + Token.name(n.getType()));

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[6]++;}
    }
  }

  /**
   * An AliasSpecification encapsulates all of the logic to find
   * aliasable nodes and alias those nodes, for a given alias name. Subclasses
   * fill in template methods, allowing for various kinds of aliasing.
   */
  private abstract static class AliasSpecification {

    /** List of nodes to alias (e.g. all 'null' nodes). */
    private final Map<Node, Node> nodes = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[9]++;
  }

    /**
     * Have we declared the alias (e.g. did we inject var
     * $$ALIAS_NULL=null; into the parse tree)?
     */
    private boolean isAliased = false;
  {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[10]++;
  }

    private String aliasName;

    private int tokenId;

    /**
     * @param aliasName name being used as alias
     * @param tokenId type of node being replaced with alias
     */
    public AliasSpecification(String aliasName, int tokenId) {
      this.aliasName = aliasName;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[11]++;
      this.tokenId = tokenId;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[12]++;
    }

    public void visit(Node n, Node parent) {
      nodes.put(n, parent);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[13]++;
    }

    /**
     * Insert a node that declares our alias into the parse tree, as a
     * child of the specified var node. Only do so if we haven't
     * already and there are enough references to the aliased node to
     * save bytes.
     * @return Whether the alias has been inserted.
     */
    boolean maybeInsertAliasDeclarationIntoParseTree(Node codeRoot) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((nodes.size() >= minOccurrencesRequiredToAlias()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[7]++;
        insertAliasDeclaration(codeRoot);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[15]++;
        isAliased = true;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[16]++;
        return true;

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[8]++;}
      return false;
    }

    /**
     * Update all of the nodes with a reference to the corresponding
     * alias node.
     */
    public void doAlias(AbstractCompiler compiler) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isAliased) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[9]++;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[1]++;


        for (Map.Entry<Node, Node> entry : nodes.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[1]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[2]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[3]++;
}
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[19]++;
          Node n = entry.getKey();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[20]++;
          Node parent = entry.getValue();
          aliasNode(n, parent);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[21]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[22]++;
        }

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[10]++;}
    }

    public int getTokenId() {
      return tokenId;
    }

    public String getAliasName() {
      return aliasName;
    }

    /**
     * Returns the minimum number of nodes that should be present for aliasing
     * to take place.
     */
    protected abstract int minOccurrencesRequiredToAlias();

    /**
     * Creates a node that defines the alias and attaches it to the parse tree.
     *
     * @param codeRoot The root of the script. Functions can be attached here,
     *     e.g., <code>function alias(p){throw p;}</code>.
     */
    protected abstract void insertAliasDeclaration(Node codeRoot);

    /** Replaces the node n with its alias. */
    protected abstract void aliasNode(Node n, Node parent);
  }

  /** Aliases throw statements with a function call. */
  // TODO(user): Generalize this to work with typeof expressions.
  private class ThrowAliasSpecification extends AliasSpecification {
    ThrowAliasSpecification(String aliasName) {
      super(aliasName, Token.THROW);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[23]++;
    }

    @Override
    protected void aliasNode(Node throwNode, Node parent) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[24]++;
      Node name = NodeUtil.newName(
          compiler.getCodingConvention(),
          getAliasName(), throwNode, getAliasName());
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[25]++;
      Node aliasCall = IR.call( name, throwNode.removeFirstChild());
      aliasCall.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[26]++;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[27]++;
      Node exprResult = IR.exprResult(aliasCall);
      parent.replaceChild(throwNode, exprResult);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[28]++;
    }

    @Override
    /**
     * Adds alias function to codeRoot. See {@link #createAliasFunctionNode}).
     */
    protected void insertAliasDeclaration(Node codeRoot) {
      codeRoot.addChildToFront(createAliasFunctionNode(getAliasName()));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[29]++;
    }

    @Override
    protected int minOccurrencesRequiredToAlias() {
      return MIN_OCCURRENCES_REQUIRED_TO_ALIAS_THROW;
    }
  }

  /**
   * Calculates the minimum number of occurrences of throw needed to alias
   * throw.
   */
  static int estimateMinOccurrencesRequriedToAlias() {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[30]++;
    // Assuming that the alias function name is two bytes in length, two bytes
    // will be saved per occurrence of throw:
    //   <code>throw e;</code>, compared to
    //   <code>TT(e);</code>.
    // However, the alias definition is some length, N, e.g.,
    //   <code>function TT(t){throw t;}</code>
    // Hence there must be more than N/2 occurrences of throw to reduce
    // the code size.
    Node alias = createAliasFunctionNode("TT");
    return InlineCostEstimator.getCost(alias) / 2 + 1;
  }

  /**
   * Creates a function node that takes a single argument, the object to
   * throw. The function throws the object.
   */
  private static Node createAliasFunctionNode(String aliasName) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[31]++;
    final String PARAM_NAME = "jscomp_throw_param";
    return IR.function(
        IR.name(aliasName),
        IR.paramList(IR.name(PARAM_NAME)),
        IR.block(
            IR.throwNode(IR.name(PARAM_NAME))));
  }

  /** Aliases literal keywords (e.g., null) with variable names. */
  private class KeywordAliasSpecification extends AliasSpecification {
    KeywordAliasSpecification(String aliasName, int tokenId) {
      super(aliasName, tokenId);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[32]++;
    }

    @Override
    protected int minOccurrencesRequiredToAlias() {
      return MIN_OCCURRENCES_REQUIRED_TO_ALIAS_LITERAL;
    }

    @Override
    protected void aliasNode(Node n, Node parent) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[33]++;
      Node aliasNode = NodeUtil.newName(
          compiler.getCodingConvention(), getAliasName(), n, getAliasName());
      parent.replaceChild(n, aliasNode);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[34]++;
    }

    @Override
    /**
     * Create the alias declaration (e.g. var $$ALIAS_NULL=null;).
     */
    protected void insertAliasDeclaration(Node codeRoot) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[35]++;
      Node varNode = new Node(Token.VAR);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[36]++;
      Node value = new Node(getTokenId());
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[37]++;
      Node name = NodeUtil.newName(
          compiler.getCodingConvention(), getAliasName(),
          varNode, getAliasName());
      name.addChildToBack(value);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[38]++;
      varNode.addChildToBack(name);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[39]++;
      codeRoot.addChildrenToFront(varNode);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[40]++;
    }
  }

  /** Aliases literal keywords (e.g., null) with variable names. */
  private class VoidKeywordAliasSpecification extends AliasSpecification {
    VoidKeywordAliasSpecification(String aliasName, int tokenId) {
      super(aliasName, tokenId);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[41]++;
    }

    @Override
    public void visit(Node n, Node parent) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[42]++;
      Node value = n.getFirstChild();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((value.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value.getDouble() == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[11]++;
        super.visit(n, parent);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[44]++;

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[12]++;}
    }

    @Override
    protected int minOccurrencesRequiredToAlias() {
      return MIN_OCCURRENCES_REQUIRED_TO_ALIAS_LITERAL;
    }

    @Override
    protected void aliasNode(Node n, Node parent) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[45]++;
      Node aliasNode = NodeUtil.newName(
          compiler.getCodingConvention(), getAliasName(), n, getAliasName());
      parent.replaceChild(n, aliasNode);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[46]++;
    }

    @Override
    /**
     * Create the alias declaration (e.g. var $$ALIAS_VOID=void 0;).
     */
    protected void insertAliasDeclaration(Node codeRoot) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[47]++;
      Node varNode = new Node(Token.VAR);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[48]++;
      Node value = IR.voidNode(IR.number(0));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[49]++;
      Node name = NodeUtil.newName(
          compiler.getCodingConvention(), getAliasName(),
          varNode, getAliasName());
      name.addChildToBack(value);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[50]++;
      varNode.addChildToBack(name);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[51]++;
      codeRoot.addChildrenToFront(varNode);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[52]++;
    }
  }


  static final String ALIAS_NULL = "JSCompiler_alias_NULL";
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[53]++;
  }
  static final String ALIAS_TRUE = "JSCompiler_alias_TRUE";
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[54]++;
  }
  static final String ALIAS_FALSE = "JSCompiler_alias_FALSE";
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[55]++;
  }
  static final String ALIAS_THROW = "JSCompiler_alias_THROW";
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[56]++;
  }
  static final String ALIAS_VOID = "JSCompiler_alias_VOID";
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[57]++;
  }

  /**
   * Don't alias a keyword unless it's referenced at least
   * MIN_OCCURRENCES_REQUIRED_TO_ALIAS_LITERAL times. Aliasing a keyword has a
   * cost (e.g. 'var XX=true;' costs 12 bytes). We make up for this
   * cost by replacing references to the keyword with variables that
   * have shorter names. If there are only a few references to a
   * keyword, the cost outweighs the benefit. It is not possible to
   * determine the exact break-even point without compiling twice
   * (once with aliasing, another without) and comparing the
   * post-gzipped size, so we define a minimum number of references
   * required in order to alias. We choose 6 because the alias cost is
   * ~7-12 bytes (12 bytes for 'var XX=true;', 7 bytes for a
   * subsequent declaration that does not require its own 'var ' or
   * semicolon, e.g. var XX=true,XY=null;), but each reference saves
   * 2-3 bytes (2 for true and null, 3 for false). Thus, the break
   * even point is 3 at best, and 6 at worst. We could use a
   * CostEstimator to be precise, but requiring a constant number of
   * occurrences is much simpler, and the added precision of a
   * CostEstimator would save us <10 bytes for some unlikely edge
   * cases (e.g. where false is referenced exactly 5 times, but does
   * not get aliased).
   */
  static final int MIN_OCCURRENCES_REQUIRED_TO_ALIAS_LITERAL = 6;
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[58]++;
  }

  /**
   * Don't alias throw statements unless throw is used at least
   * MIN_OCCURRENCES_REQUIRED_TO_ALIAS_THROW times.
   */
  static final int MIN_OCCURRENCES_REQUIRED_TO_ALIAS_THROW =
      estimateMinOccurrencesRequriedToAlias();
  static {
    CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[59]++;
  }

  /** Reference to JS Compiler */
  private final AbstractCompiler compiler;

  /** List of alias specifications, stored in order which transformations
   * should be applied. See {@link #createAliasSpecifications}.
   */
  private final List<AliasSpecification> aliasSpecifications;

  /** Map from rhino nodes to the corresponding AliasSpecification */
  private final Map<Integer, AliasSpecification> aliasTypes;

  /** Set of alias names. */
  private final Set<String> aliasNames;

  AliasKeywords(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[60]++;
    aliasSpecifications = createAliasSpecifications();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[61]++;
    aliasTypes = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[62]++;
    aliasNames = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[63]++;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[64]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[4]++;


    for (AliasSpecification specification : aliasSpecifications) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[4]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[5]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[6]++;
}
      aliasTypes.put(specification.getTokenId(), specification);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[65]++;
      aliasNames.add(specification.getAliasName());
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[66]++;
    }
  }

  /**
   * Do all processing on the root node.
   */
  @Override
  public void process(Node externs, Node root) {
    // Find candidates to alias.
    NodeTraversal.traverse(compiler, root, new FindAliasableNodes());
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[67]++;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[68]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((needsAliases()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[13]++;
      // Inject alias nodes for null, true, and false into the global scope.
      addAliasNodes(compiler.getNodeForCodeInsertion(null));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[69]++;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[70]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[7]++;



      // Update references to null/true/false with references to the aliases.
      for (AliasSpecification spec : aliasSpecifications) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[7]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[8]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[9]++;
}
        spec.doAlias(compiler);
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[71]++;
      }

    } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[14]++;}
  }

  private boolean needsAliases() {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[72]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[10]++;


    for (AliasSpecification spec : aliasSpecifications) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[10]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[11]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[12]++;
}
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[73]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((spec.nodes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[15]++;
        return true;

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[16]++;}
    }

    return false;
  }

  /**
   * Inject alias nodes into the global scope. e.g.
   * var $$ALIAS_NULL=null,$$ALIAS_TRUE=true,$$ALIAS_FALSE=false;.
   */
  private void addAliasNodes(Node codeRoot) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[74]++;
    boolean codeChanged = false;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[75]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[13]++;



    for (AliasSpecification spec : aliasSpecifications) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[13]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[14]--;
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.loops[15]++;
}
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[76]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((spec.maybeInsertAliasDeclarationIntoParseTree(codeRoot)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[17]++;
        codeChanged = true;
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[77]++;

      } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[18]++;}
    }
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[78]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((codeChanged) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[19]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[79]++;

    } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[20]++;}
  }

  /**
   * Does the given node define one of our aliases?
   */
  private boolean isAliasDefinition(Node n) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[80]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[21]++;
      return false;

    } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[22]++;}
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[81]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isAliasName(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[23]++;
      // The given Node's string contents is not an alias. Skip it.
      return false;

    } else {
  CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.branches[24]++;}

    /*
     * A definition must have a child node (otherwise it's just a
     * reference to the alias).
     */
    return n.getFirstChild() != null;
  }

  /**
   * Is this one of the Rhino token types that we're aliasing?
   */
  private boolean isAliasableType(int type) {
    return aliasTypes.containsKey(type);
  }

  /**
   * Is this one of our alias names?
   */
  private boolean isAliasName(String name) {
    return aliasNames.contains(name);
  }

  /**
   * Create the AliasSpecifications, one for each type we're aliasing. The
   * order of the elements in the list is significant. Transformations should
   * be applied in the given order.
   */
  private List<AliasSpecification> createAliasSpecifications() {
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[82]++;
    List<AliasSpecification> l = Lists.newArrayList();
    l.add(new KeywordAliasSpecification(ALIAS_FALSE, Token.FALSE));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[83]++;
    l.add(new KeywordAliasSpecification(ALIAS_NULL, Token.NULL));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[84]++;
    l.add(new KeywordAliasSpecification(ALIAS_TRUE, Token.TRUE));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[85]++;
    l.add(new VoidKeywordAliasSpecification(ALIAS_VOID, Token.VOID));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[86]++;
    // Process throw nodes after literal keyword nodes. This is important when
    // a literal keyword is thrown (e.g., throw true;).
    // KeywordAliasSpecification needs to know what the parent of the node being
    // replaced with an alias is. Because ThrowAliasSpecification replaces the
    // parent of the node being aliased, ThrowAliasSpecification invalidates the
    // record of the node's parent that KeywordAliasSpecification stores.
    l.add(new ThrowAliasSpecification(ALIAS_THROW));
CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1.statements[87]++;
    return l;
  }
}

class CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1 ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AliasKeywords.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$5fuzwkz6e2fotfpvdsfh0juqhug1 () {
    super("com.google.javascript.jscomp.AliasKeywords.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AliasKeywords.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

