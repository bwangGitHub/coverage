/*
 * Copyright 2010 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CompilerOptions.AliasTransformation;
import com.google.javascript.jscomp.CompilerOptions.AliasTransformationHandler;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.SourcePosition;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * Process aliases in goog.scope blocks.
 *
 * goog.scope(function() {
 *   var dom = goog.dom;
 *   var DIV = dom.TagName.DIV;
 *
 *   dom.createElement(DIV);
 * });
 *
 * should become
 *
 * goog.dom.createElement(goog.dom.TagName.DIV);
 *
 * @author robbyw@google.com (Robby Walker)
 */
class ScopedAliases implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.ping();
  }

  /** Name used to denote an scoped function block used for aliasing. */
  static final String SCOPING_METHOD_NAME = "goog.scope";
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final PreprocessorSymbolTable preprocessorSymbolTable;
  private final AliasTransformationHandler transformationHandler;

  // Errors
  static final DiagnosticType GOOG_SCOPE_USED_IMPROPERLY = DiagnosticType.error(
      "JSC_GOOG_SCOPE_USED_IMPROPERLY",
      "The call to goog.scope must be alone in a single statement.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[2]++;
  }

  static final DiagnosticType GOOG_SCOPE_HAS_BAD_PARAMETERS =
      DiagnosticType.error(
          "JSC_GOOG_SCOPE_HAS_BAD_PARAMETERS",
          "The call to goog.scope must take only a single parameter.  It must" +
              " be an anonymous function that itself takes no parameters.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[3]++;
  }

  static final DiagnosticType GOOG_SCOPE_REFERENCES_THIS = DiagnosticType.error(
      "JSC_GOOG_SCOPE_REFERENCES_THIS",
      "The body of a goog.scope function cannot reference 'this'.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[4]++;
  }

  static final DiagnosticType GOOG_SCOPE_USES_RETURN = DiagnosticType.error(
      "JSC_GOOG_SCOPE_USES_RETURN",
      "The body of a goog.scope function cannot use 'return'.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[5]++;
  }

  static final DiagnosticType GOOG_SCOPE_USES_THROW = DiagnosticType.error(
      "JSC_GOOG_SCOPE_USES_THROW",
      "The body of a goog.scope function cannot use 'throw'.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[6]++;
  }

  static final DiagnosticType GOOG_SCOPE_ALIAS_REDEFINED = DiagnosticType.error(
      "JSC_GOOG_SCOPE_ALIAS_REDEFINED",
      "The alias {0} is assigned a value more than once.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[7]++;
  }

  static final DiagnosticType GOOG_SCOPE_NON_ALIAS_LOCAL = DiagnosticType.error(
      "JSC_GOOG_SCOPE_NON_ALIAS_LOCAL",
      "The local variable {0} is in a goog.scope and is not an alias.");
  static {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[8]++;
  }

  ScopedAliases(AbstractCompiler compiler,
      @Nullable PreprocessorSymbolTable preprocessorSymbolTable,
      AliasTransformationHandler transformationHandler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[9]++;
    this.preprocessorSymbolTable = preprocessorSymbolTable;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[10]++;
    this.transformationHandler = transformationHandler;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[11]++;
  }

  @Override
  public void process(Node externs, Node root) {
    hotSwapScript(root, null);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[12]++;
  }

  @Override
  public void hotSwapScript(Node root, Node originalRoot) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[13]++;
    Traversal traversal = new Traversal();
    NodeTraversal.traverse(compiler, root, traversal);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[14]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((traversal.hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[1]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[1]++;



      // Apply the aliases.
      for (AliasUsage aliasUsage : traversal.getAliasUsages()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[1]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[2]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[3]++;
}
        aliasUsage.applyAlias();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[17]++;
      }
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[4]++;



      // Remove the alias definitions.
      for (Node aliasDefinition : traversal.getAliasDefinitionsInOrder()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[4]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[5]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[6]++;
}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((aliasDefinition.getParent().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((aliasDefinition.getParent().hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[3]++;
          aliasDefinition.getParent().detachFromParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[20]++;

        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[4]++;
          aliasDefinition.detachFromParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[21]++;
        }
      }
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[22]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[7]++;



      // Collapse the scopes.
      for (Node scopeCall : traversal.getScopeCalls()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[7]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[8]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[9]++;
}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[23]++;
        Node expressionWithScopeCall = scopeCall.getParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[24]++;
        Node scopeClosureBlock = scopeCall.getLastChild().getLastChild();
        scopeClosureBlock.detachFromParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[25]++;
        expressionWithScopeCall.getParent().replaceChild(
            expressionWithScopeCall,
            scopeClosureBlock);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[26]++;
        NodeUtil.tryMergeBlock(scopeClosureBlock);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[27]++;
      }
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((traversal.getAliasUsages().size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((traversal.getAliasDefinitionsInOrder().size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((traversal.getScopeCalls().size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[5]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[29]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[2]++;}
  }

  private interface AliasUsage {
    public void applyAlias();
  }

  private class AliasedNode implements AliasUsage {
    private final Node aliasReference;

    private final Node aliasDefinition;

    AliasedNode(Node aliasReference, Node aliasDefinition) {
      this.aliasReference = aliasReference;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[30]++;
      this.aliasDefinition = aliasDefinition;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[31]++;
    }

    @Override
    public void applyAlias() {
      aliasReference.getParent().replaceChild(
          aliasReference, aliasDefinition.cloneTree());
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[32]++;
    }
  }

  private class AliasedTypeNode implements AliasUsage {
    private final Node typeReference;
    private final Node aliasDefinition;
    private final String aliasName;

    AliasedTypeNode(Node typeReference, Node aliasDefinition,
        String aliasName) {
      this.typeReference = typeReference;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[33]++;
      this.aliasDefinition = aliasDefinition;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[34]++;
      this.aliasName = aliasName;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[35]++;
    }

    @Override
    public void applyAlias() {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[36]++;
      String typeName = typeReference.getString();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[37]++;
      String aliasExpanded =
          Preconditions.checkNotNull(aliasDefinition.getQualifiedName());
      Preconditions.checkState(typeName.startsWith(aliasName));
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[38]++;
      typeReference.setString(typeName.replaceFirst(aliasName, aliasExpanded));
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[39]++;
    }
  }


  private class Traversal implements NodeTraversal.ScopedCallback {
    // The job of this class is to collect these three data sets.

    // The order of this list determines the order that aliases are applied.
    private final List<Node> aliasDefinitionsInOrder = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[40]++;
  }

    private final List<Node> scopeCalls = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[41]++;
  }

    private final List<AliasUsage> aliasUsages = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[42]++;
  }

    // This map is temporary and cleared for each scope.
    private final Map<String, Var> aliases = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[43]++;
  }

    // Suppose you create an alias.
    // var x = goog.x;
    // As a side-effect, this means you can shadow the namespace 'goog'
    // in inner scopes. When we inline the namespaces, we have to rename
    // these shadows.
    //
    // Fortunately, we already have a name uniquifier that runs during tree
    // normalization (before optimizations). We run it here on a limited
    // set of variables, but only as a last resort (because this will screw
    // up warning messages downstream).
    private final Set<String> forbiddenLocals = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[44]++;
  }
    private boolean hasNamespaceShadows = false;
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[45]++;
  }

    private boolean hasErrors = false;
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[46]++;
  }

    private AliasTransformation transformation = null;
  {
    CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[47]++;
  }

    Collection<Node> getAliasDefinitionsInOrder() {
      return aliasDefinitionsInOrder;
    }

    private List<AliasUsage> getAliasUsages() {
      return aliasUsages;
    }

    List<Node> getScopeCalls() {
      return scopeCalls;
    }

    boolean hasErrors() {
      return hasErrors;
    }

    private boolean isCallToScopeMethod(Node n) {
      return n.isCall() &&
          SCOPING_METHOD_NAME.equals(n.getFirstChild().getQualifiedName());
    }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[48]++;
      Node n = t.getCurrentNode().getParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[49]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isCallToScopeMethod(n)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[7]++;
        transformation = transformationHandler.logAliasTransformation(
            n.getSourceFileName(), getSourceRegion(n));
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[50]++;
        findAliases(t);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[51]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[8]++;}
    }

    @Override
    public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[52]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((t.getScopeDepth() > 2) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[9]++;
        findNamespaceShadows(t);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[53]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[10]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[54]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((t.getScopeDepth() == 2) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[11]++;
        renameNamespaceShadows(t);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[55]++;
        aliases.clear();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[56]++;
        forbiddenLocals.clear();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[57]++;
        transformation = null;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[58]++;
        hasNamespaceShadows = false;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[59]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[12]++;}
    }

    @Override
    public final boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[60]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[13]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[61]++;
int CodeCoverConditionCoverageHelper_C8;
        // Do not traverse in to functions except for goog.scope functions.
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isCallToScopeMethod(parent)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[15]++;
          return false;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[16]++;}

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[14]++;}
      return true;
    }

    private SourcePosition<AliasTransformation> getSourceRegion(Node n) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[62]++;
      Node testNode = n;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[63]++;
      Node next = null;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[64]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[10]++;


int CodeCoverConditionCoverageHelper_C9;
      for (;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((testNode.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false);) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[10]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[11]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[12]++;
}
        next = testNode.getNext();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[65]++;
        testNode = testNode.getParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[66]++;
      }
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[67]++;

      int endLine = next == null ? Integer.MAX_VALUE : next.getLineno();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[68]++;
      int endChar = next == null ? Integer.MAX_VALUE : next.getCharno();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[69]++;
      SourcePosition<AliasTransformation> pos =
          new SourcePosition<AliasTransformation>() {};
      pos.setPositionInformation(
          n.getLineno(), n.getCharno(), endLine, endChar);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[70]++;
      return pos;
    }

    private void report(NodeTraversal t, Node n, DiagnosticType error,
        String... arguments) {
      compiler.report(t.makeError(n, error, arguments));
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[71]++;
      hasErrors = true;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[72]++;
    }

    private void findAliases(NodeTraversal t) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[73]++;
      Scope scope = t.getScope();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[74]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[13]++;


      for (Var v : scope.getVarIterable()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[13]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[14]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[15]++;
}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[75]++;
        Node n = v.getNode();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[76]++;
        Node parent = n.getParent();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[77]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[17]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[78]++;
          String name = n.getString();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[79]++;
          Var aliasVar = scope.getVar(name);
          aliases.put(name, aliasVar);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[80]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[81]++;

          String qualifiedName =
              aliasVar.getInitialValue().getQualifiedName();
          transformation.addAlias(name, qualifiedName);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[82]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[83]++;

          int rootIndex = qualifiedName.indexOf(".");
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[84]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((rootIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[19]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[85]++;
            String qNameRoot = qualifiedName.substring(0, rootIndex);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[86]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((aliases.containsKey(qNameRoot)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[21]++;
              forbiddenLocals.add(qNameRoot);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[87]++;

            } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[22]++;}

          } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[20]++;}

        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[18]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[88]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((v.isBleedingFunction()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[23]++;

          // Bleeding functions already get a BAD_PARAMETERS error, so just
          // do nothing.
        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[24]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[89]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.getType() == Token.LP) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[25]++;

          // Parameters of the scope function also get a BAD_PARAMETERS
          // error.
        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[26]++;
          // TODO(robbyw): Support using locals for private variables.
          report(t, n, GOOG_SCOPE_NON_ALIAS_LOCAL, n.getString());
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[90]++;
        }
}
}
      }
    }

    /** Find out if there are any local shadows of namespaces. */
    private void findNamespaceShadows(NodeTraversal t) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[91]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((hasNamespaceShadows) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[27]++;
        return;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[28]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[92]++;

      Scope scope = t.getScope();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[93]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[16]++;


      for (Var v : scope.getVarIterable()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[16]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[17]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[18]++;
}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[94]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((forbiddenLocals.contains(v.getName())) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[29]++;
          hasNamespaceShadows = true;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[95]++;
          return;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[30]++;}
      }
    }

    /**
     * Rename any local shadows of namespaces.
     * This should be a very rare occurrence, so only do this traversal
     * if we know that we need it.
     */
    private void renameNamespaceShadows(NodeTraversal t) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[96]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((hasNamespaceShadows) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[31]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[97]++;
        MakeDeclaredNamesUnique.Renamer renamer =
            new MakeDeclaredNamesUnique.WhitelistedRenamer(
                new MakeDeclaredNamesUnique.ContextualRenamer(),
                forbiddenLocals);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[98]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[19]++;


        for (String s : forbiddenLocals) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[19]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[20]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[21]++;
}
          renamer.addDeclaredName(s);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[99]++;
        }
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[100]++;
        MakeDeclaredNamesUnique uniquifier =
            new MakeDeclaredNamesUnique(renamer);
        NodeTraversal.traverse(compiler, t.getScopeRoot(), uniquifier);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[101]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[32]++;}
    }

    private void validateScopeCall(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[102]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((preprocessorSymbolTable != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[33]++;
        preprocessorSymbolTable.addReference(n.getFirstChild());
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[103]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[34]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[104]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[35]++;
        report(t, n, GOOG_SCOPE_USED_IMPROPERLY);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[105]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[36]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[106]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n.getChildCount() != 2) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[37]++;
        // The goog.scope call should have exactly 1 parameter.  The first
        // child is the "goog.scope" and the second should be the parameter.
        report(t, n, GOOG_SCOPE_HAS_BAD_PARAMETERS);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[107]++;

      } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[38]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[108]++;
        Node anonymousFnNode = n.getChildAtIndex(1);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[109]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((anonymousFnNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((NodeUtil.getFunctionName(anonymousFnNode) != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.getFunctionParameters(anonymousFnNode).hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[39]++;
          report(t, anonymousFnNode, GOOG_SCOPE_HAS_BAD_PARAMETERS);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[110]++;

        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[40]++;
          scopeCalls.add(n);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[111]++;
        }
      }
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[112]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isCallToScopeMethod(n)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[41]++;
        validateScopeCall(t, n, n.getParent());
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[113]++;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[42]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[114]++;
int CodeCoverConditionCoverageHelper_C23;

      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((t.getScopeDepth() < 2) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[43]++;
        return;

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[44]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[115]++;

      int type = n.getType();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[116]++;
      Var aliasVar = null;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[117]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((type == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[45]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[118]++;
        String name = n.getString();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[119]++;
        Var lexicalVar = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[120]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((lexicalVar != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((lexicalVar == aliases.get(name)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[47]++;
          aliasVar = lexicalVar;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[121]++;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[48]++;}

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[46]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[122]++;
int CodeCoverConditionCoverageHelper_C26;

      // Validate the top-level of the goog.scope block.
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((t.getScopeDepth() == 2) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[49]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[123]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((aliasVar != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[51]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[124]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((aliasVar.getNode() == n) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[53]++;
            aliasDefinitionsInOrder.add(n);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[125]++;

            // Return early, to ensure that we don't record a definition
            // twice.
            return;

          } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[54]++;
            report(t, n, GOOG_SCOPE_ALIAS_REDEFINED, n.getString());
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[126]++;
          }

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[52]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[127]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((type == Token.RETURN) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[55]++;
          report(t, n, GOOG_SCOPE_USES_RETURN);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[128]++;

        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[56]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[129]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type == Token.THIS) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[57]++;
          report(t, n, GOOG_SCOPE_REFERENCES_THIS);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[130]++;

        } else {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[58]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[131]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((type == Token.THROW) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[59]++;
          report(t, n, GOOG_SCOPE_USES_THROW);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[132]++;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[60]++;}
}
}

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[50]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[133]++;
int CodeCoverConditionCoverageHelper_C32;

      // Validate all descendent scopes of the goog.scope block.
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((t.getScopeDepth() >= 2) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[61]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[134]++;
int CodeCoverConditionCoverageHelper_C33;
        // Check if this name points to an alias.
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((aliasVar != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[63]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[135]++;
          // Note, to support the transitive case, it's important we don't
          // clone aliasedNode here.  For example,
          // var g = goog; var d = g.dom; d.createElement('DIV');
          // The node in aliasedNode (which is "g") will be replaced in the
          // changes pass above with "goog".  If we cloned here, we'd end up
          // with <code>g.dom.createElement('DIV')</code>.
          Node aliasedNode = aliasVar.getInitialValue();
          aliasUsages.add(new AliasedNode(n, aliasedNode));
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[136]++;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[64]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[137]++;

        JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[138]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[65]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[139]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[22]++;


          for (Node node : info.getTypeNodes()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[22]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[23]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[24]++;
}
            fixTypeNode(node);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[140]++;
          }

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[66]++;}


        // TODO(robbyw): Error for goog.scope not at root.
      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[62]++;}
    }

    private void fixTypeNode(Node typeNode) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[141]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((typeNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[67]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[142]++;
        String name = typeNode.getString();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[143]++;
        int endIndex = name.indexOf('.');
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[144]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((endIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[69]++;
          endIndex = name.length();
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[145]++;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[70]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[146]++;
        String baseName = name.substring(0, endIndex);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[147]++;
        Var aliasVar = aliases.get(baseName);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[148]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((aliasVar != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[71]++;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[149]++;
          Node aliasedNode = aliasVar.getInitialValue();
          aliasUsages.add(new AliasedTypeNode(typeNode, aliasedNode, baseName));
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[150]++;

        } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[72]++;}

      } else {
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.branches[68]++;}
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[151]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[25]++;


int CodeCoverConditionCoverageHelper_C38;

      for (Node child = typeNode.getFirstChild();(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false);
           child = child.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[25]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[26]--;
  CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.loops[27]++;
}
        fixTypeNode(child);
CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl.statements[152]++;
      }
    }
  }
}

class CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl ());
  }
    public static long[] statements = new long[153];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ScopedAliases.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,3,2,1,1,2,2,2,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 38; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$6xn5vlv6424abxia9hl11bvak6zl () {
    super("com.google.javascript.jscomp.ScopedAliases.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 152; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 72; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ScopedAliases.java");
      for (int i = 1; i <= 152; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 72; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

