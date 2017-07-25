/*
 * Copyright 2009 The Closure Compiler Authors.
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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.deps.DependencyInfo;
import com.google.javascript.jscomp.deps.JsFileParser;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A class for the internal representation of an input to the compiler.
 * Wraps a {@link SourceAst} and maintain state such as module for the input and
 * whether the input is an extern. Also calculates provided and required types.
 *
 */
public class CompilerInput
    implements SourceAst, DependencyInfo {
  static {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.ping();
  }


  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[1]++;
  }

  // Info about where the file lives.
  private JSModule module;
  final private InputId id;

  // The AST.
  private final SourceAst ast;

  // Provided and required symbols.
  private final Set<String> provides = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[2]++;
  }
  private final Set<String> requires = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[3]++;
  }
  private boolean generatedDependencyInfoFromSource = false;
  {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[4]++;
  }

  // An AbstractCompiler for doing parsing.
  // We do not want to persist this across serialized state.
  private transient AbstractCompiler compiler;

  public CompilerInput(SourceAst ast) {
    this(ast, ast.getSourceFile().getName(), false);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[5]++;
  }

  public CompilerInput(SourceAst ast, boolean isExtern) {
    this(ast, ast.getInputId(), isExtern);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[6]++;
  }

  public CompilerInput(SourceAst ast, String inputId, boolean isExtern) {
    this(ast, new InputId(inputId), isExtern);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[7]++;
  }

  public CompilerInput(SourceAst ast, InputId inputId, boolean isExtern) {
    this.ast = ast;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[8]++;
    this.id = inputId;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[9]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

    // TODO(nicksantos): Add a precondition check here. People are passing
    // in null, but they should not be.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((ast != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((ast.getSourceFile() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[1]++;
      ast.getSourceFile().setIsExtern(isExtern);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[11]++;

    } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[2]++;}
  }

  public CompilerInput(SourceFile file) {
    this(file, false);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[12]++;
  }

  public CompilerInput(SourceFile file, boolean isExtern) {
    this(new JsAst(file), isExtern);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[13]++;
  }

  /** Returns a name for this input. Must be unique across all inputs. */
  @Override
  public InputId getInputId() {
    return id;
  }

  /** Returns a name for this input. Must be unique across all inputs. */
  @Override
  public String getName() {
    return id.getIdName();
  }

  public SourceAst getAst() {
    return ast;
  }

  /** Gets the path relative to closure-base, if one is available. */
  @Override
  public String getPathRelativeToClosureBase() {
    // TODO(nicksantos): Implement me.
    throw new UnsupportedOperationException();
  }

  @Override
  public Node getAstRoot(AbstractCompiler compiler) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[14]++;
    Node root = ast.getAstRoot(compiler);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
    // The root maybe null if the AST can not be created.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[3]++;
      Preconditions.checkState(root.isScript());
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[16]++;
      Preconditions.checkNotNull(root.getInputId());
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[17]++;

    } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[4]++;}
    return root;
  }

  @Override
  public void clearAst() {
    ast.clearAst();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[18]++;
  }

  @Override
  public SourceFile getSourceFile() {
    return ast.getSourceFile();
  }

  @Override
  public void setSourceFile(SourceFile file) {
    ast.setSourceFile(file);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[19]++;
  }

  /** Returns the SourceAst object on which this input is based. */
  public SourceAst getSourceAst() {
    return ast;
  }

  /** Sets an abstract compiler for doing parsing. */
  public void setCompiler(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[20]++;
  }

  private void checkErrorManager() {
    Preconditions.checkNotNull(compiler,
        "Expected setCompiler to be called first: " + this);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[21]++;
    Preconditions.checkNotNull(compiler.getErrorManager(),
        "Expected compiler to call an error manager: " + this);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[22]++;
  }

  /** Gets a list of types depended on by this input. */
  @Override
  public Collection<String> getRequires() {
    checkErrorManager();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[23]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[24]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      regenerateDependencyInfoIfNecessary();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[25]++;
      return Collections.<String>unmodifiableSet(requires);
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[6]++;
      compiler.getErrorManager().report(CheckLevel.ERROR,
          JSError.make(AbstractCompiler.READ_ERROR, getName()));
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[26]++;
      return ImmutableList.<String>of();
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[5]++;
}
  }
  }

  /** Gets a list of types provided by this input. */
  @Override
  public Collection<String> getProvides() {
    checkErrorManager();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[27]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[28]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      regenerateDependencyInfoIfNecessary();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[29]++;
      return Collections.<String>unmodifiableSet(provides);
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[8]++;
      compiler.getErrorManager().report(CheckLevel.ERROR,
          JSError.make(AbstractCompiler.READ_ERROR, getName()));
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[30]++;
      return ImmutableList.<String>of();
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[7]++;
}
  }
  }

  // TODO(nicksantos): Remove addProvide/addRequire/removeRequire once
  // there is better support for discovering non-closure dependencies.
  void addProvide(String provide) {
    getProvides();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[31]++;
    provides.add(provide);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[32]++;
  }

  void addRequire(String require) {
    getRequires();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[33]++;
    requires.add(require);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[34]++;
  }

  public void removeRequire(String require) {
    getRequires();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[35]++;
    requires.remove(require);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[36]++;
  }

  /**
   * Regenerates the provides/requires if we need to do so.
   */
  private void regenerateDependencyInfoIfNecessary() throws IOException {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[37]++;
int CodeCoverConditionCoverageHelper_C3;
    // If the code is NOT a JsAst, then it was not originally JS code.
    // Look at the Ast for dependency info.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ast instanceof JsAst) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[9]++;
      Preconditions.checkNotNull(compiler,
          "Expected setCompiler to be called first");
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[38]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[39]++;
      DepsFinder finder = new DepsFinder();
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[40]++;
      Node root = getAstRoot(compiler);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[41]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[12]++;}

      finder.visitTree(getAstRoot(compiler));
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[42]++;

      // TODO(nicksantos|user): This caching behavior is a bit
      // odd, and only works if you assume the exact call flow that
      // clients are currently using.  In that flow, they call
      // getProvides(), then remove the goog.provide calls from the
      // AST, and then call getProvides() again.
      //
      // This won't work for any other call flow, or any sort of incremental
      // compilation scheme. The API needs to be fixed so callers aren't
      // doing weird things like this, and then we should get rid of the
      // multiple-scan strategy.

      provides.addAll(finder.provides);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[43]++;
      requires.addAll(finder.requires);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[44]++;

    } else {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[10]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[45]++;
int CodeCoverConditionCoverageHelper_C5;
      // Otherwise, look at the source code.
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((generatedDependencyInfoFromSource) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[13]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[46]++;
        // Note: it's OK to use getName() instead of
        // getPathRelativeToClosureBase() here because we're not using
        // this to generate deps files. (We're only using it for
        // symbol dependencies.)
        DependencyInfo info =
            (new JsFileParser(compiler.getErrorManager()))
            .setIncludeGoogBase(true)
            .parseFile(getName(), getName(), getCode());

        provides.addAll(info.getProvides());
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[47]++;
        requires.addAll(info.getRequires());
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[48]++;

        generatedDependencyInfoFromSource = true;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[49]++;

      } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[14]++;}
    }
  }

  private static class DepsFinder {
    private final List<String> provides = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[50]++;
  }
    private final List<String> requires = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[51]++;
  }
    private final CodingConvention codingConvention =
        new ClosureCodingConvention();
  {
    CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[52]++;
  }

    void visitTree(Node n) {
      visitSubtree(n, null);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[53]++;
    }

    void visitSubtree(Node n, Node parent) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[54]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[15]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[55]++;
        String require =
            codingConvention.extractClassNameIfRequire(n, parent);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[56]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((require != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[17]++;
          requires.add(require);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[57]++;

        } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[18]++;}
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[58]++;

        String provide =
            codingConvention.extractClassNameIfProvide(n, parent);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[59]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((provide != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[19]++;
          provides.add(provide);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[60]++;

        } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[20]++;}
        return;

      } else {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[16]++;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[61]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[21]++;
        return;

      } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[22]++;}
}
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[62]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;

      for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.loops[1]--;
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.loops[2]--;
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.loops[3]++;
}
        visitSubtree(child, n);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[63]++;
      }
    }
  }

  /**
   * Gets the source line for the indicated line number.
   *
   * @param lineNumber the line number, 1 being the first line of the file.
   * @return The line indicated. Does not include the newline at the end
   *     of the file. Returns {@code null} if it does not exist,
   *     or if there was an IO exception.
   */
  public String getLine(int lineNumber) {
    return getSourceFile().getLine(lineNumber);
  }

  /**
   * Get a region around the indicated line number. The exact definition of a
   * region is implementation specific, but it must contain the line indicated
   * by the line number. A region must not start or end by a carriage return.
   *
   * @param lineNumber the line number, 1 being the first line of the file.
   * @return The line indicated. Returns {@code null} if it does not exist,
   *     or if there was an IO exception.
   */
  public Region getRegion(int lineNumber) {
    return getSourceFile().getRegion(lineNumber);
  }

  public String getCode() throws IOException {
    return getSourceFile().getCode();
  }

  /** Returns the module to which the input belongs. */
  public JSModule getModule() {
    return module;
  }

  /** Sets the module to which the input belongs. */
  public void setModule(JSModule module) {
    // An input may only belong to one module.
    Preconditions.checkArgument(
        module == null || this.module == null || this.module == module);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[64]++;
    this.module = module;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[65]++;
  }

  /** Overrides the module to which the input belongs. */
  void overrideModule(JSModule module) {
    this.module = module;
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[66]++;
  }

  public boolean isExtern() {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[67]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((ast == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ast.getSourceFile() == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[23]++;
      return false;

    } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[24]++;}
    return ast.getSourceFile().isExtern();
  }

  void setIsExtern(boolean isExtern) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((ast == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ast.getSourceFile() == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[25]++;
      return;

    } else {
  CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.branches[26]++;}
    ast.getSourceFile().setIsExtern(isExtern);
CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1.statements[69]++;
  }

  public int getLineOffset(int lineno) {
    return ast.getSourceFile().getLineOffset(lineno);
  }

  /** @return The number of lines in this input. */
  public int getNumLines() {
    return ast.getSourceFile().getNumLines();
  }

  @Override
  public String toString() {
    return getName();
  }
}

class CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1 ());
  }
    public static long[] statements = new long[70];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CompilerInput.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,3,1,2,2};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$5lvte4l2r0jxkl63x73ac541ntk1 () {
    super("com.google.javascript.jscomp.CompilerInput.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 69; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CompilerInput.java");
      for (int i = 1; i <= 69; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

