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
import com.google.common.base.Supplier;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  Find all Functions, VARs, and Exception names and make them
 *  unique.  Specifically, it will not modify object properties.
 *  @author johnlenz@google.com (John Lenz)
 *  TODO(johnlenz): Try to merge this with the ScopeCreator.
 */
class MakeDeclaredNamesUnique
    implements NodeTraversal.ScopedCallback {
  static {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.ping();
  }


  // Arguments is special cased to handle cases where a local name shadows
  // the arguments declaration.
  public static final String ARGUMENTS = "arguments";
  static {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[1]++;
  }

  // The name stack is similar to how we model scopes but handles some
  // additional cases that are not handled by the current Scope object.
  // Specifically, a Scope currently has only two concepts of scope (global,
  // and function local).  But there are in reality a couple of additional
  // case to worry about:
  //   catch expressions
  //   function expressions names
  // Both belong to a scope by themselves.
  private Deque<Renamer> nameStack = new ArrayDeque<Renamer>();
  {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[2]++;
  }
  private final Renamer rootRenamer;

  MakeDeclaredNamesUnique() {
    this(new ContextualRenamer());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[3]++;
  }

  MakeDeclaredNamesUnique(Renamer renamer) {
    this.rootRenamer = renamer;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[4]++;
  }

  static CompilerPass getContextualRenameInverter(AbstractCompiler compiler) {
    return new ContextualRenameInverter(compiler);
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[5]++;
    Node declarationRoot = t.getScopeRoot();
    Renamer renamer;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameStack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[1]++;
      // If the contextual renamer is being used, the starting context can not
      // be a function.
      Preconditions.checkState(
          !declarationRoot.isFunction() ||
          !(rootRenamer instanceof ContextualRenamer));
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[7]++;
      Preconditions.checkState(t.inGlobalScope());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[8]++;
      renamer = rootRenamer;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[9]++;

    } else {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[2]++;
      renamer = nameStack.peek().forChildScope();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[10]++;
    }
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((declarationRoot.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[3]++;
      // Add the block declarations
      findDeclaredNames(declarationRoot, null, renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[12]++;

    } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[4]++;}
    nameStack.push(renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[13]++;
  }

  @Override
  public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[5]++;
      nameStack.pop();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[15]++;

    } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[6]++;}
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[16]++;

    switch (n.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[7]++;
        {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[17]++;
          // Add recursive function name, if needed.
          // NOTE: "enterScope" is called after we need to pick up this name.
          Renamer renamer = nameStack.peek().forChildScope();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[18]++;

          // If needed, add the function recursive name.
          String name = n.getFirstChild().getString();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[8]++;
            renamer.addDeclaredName(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[20]++;

          } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[9]++;}

          nameStack.push(renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[21]++;
        }
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[22]++;
        break;

      case Token.PARAM_LIST:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[10]++; {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[23]++;
          Renamer renamer = nameStack.peek().forChildScope();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;

          // Add the function parameters
          for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[1]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[2]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[3]++;
}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[25]++;
            String name = c.getString();
            renamer.addDeclaredName(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[26]++;
          }
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[27]++;

          // Add the function body declarations
          Node functionBody = n.getNext();
          findDeclaredNames(functionBody, null, renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[28]++;

          nameStack.push(renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[29]++;
        }
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[30]++;
        break;

      case Token.CATCH:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[11]++;
        {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[31]++;
          Renamer renamer = nameStack.peek().forChildScope();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[32]++;

          String name = n.getFirstChild().getString();
          renamer.addDeclaredName(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[33]++;

          nameStack.push(renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[34]++;
        }
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[35]++;
        break; default : CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[12]++;
    }

    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[36]++;
    switch (n.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[13]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[37]++;
        String newName = getReplacementName(n.getString());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((newName != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[14]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[39]++;
          Renamer renamer = nameStack.peek();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((renamer.stripConstIfReplaced()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[16]++;
            // TODO(johnlenz): Do we need to do anything about the Javadoc?
            n.removeProp(Node.IS_CONSTANT_NAME);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[41]++;

          } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[17]++;}
          n.setString(newName);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[42]++;
          t.getCompiler().reportCodeChange();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[43]++;

        } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[15]++;}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[44]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[18]++;
        // Remove the function body scope
        nameStack.pop();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[45]++;
        // Remove function recursive name (if any).
        nameStack.pop();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[46]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[47]++;
        break;

      case Token.PARAM_LIST:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[19]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[48]++;
        // Note: The parameters and function body variables live in the
        // same scope, we introduce the scope when in the "shouldTraverse"
        // visit of LP, but remove it when when we exit the function above.
        break;

      case Token.CATCH:
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[20]++;
        // Remove catch except name from the stack of names.
        nameStack.pop();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[49]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[50]++;
        break; default : CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[21]++;
    }
  }

  /**
   * Walks the stack of name maps and finds the replacement name for the
   * current scope.
   */
  private String getReplacementName(String oldName) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[51]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[4]++;


    for (Renamer names : nameStack) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[4]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[5]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[6]++;
}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[52]++;
      String newName = names.getReplacementName(oldName);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((newName != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[22]++;
        return newName;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[23]++;}
    }
    return null;
  }

  /**
   * Traverses the current scope and collects declared names.  Does not
   * decent into functions or add CATCH exceptions.
   */
  private void findDeclaredNames(Node n, Node parent, Renamer renamer) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
    // Do a shallow traversal, so don't traverse into function declarations,
    // except for the name of the function itself.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[24]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[26]++;
        renamer.addDeclaredName(n.getString());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[56]++;

      } else {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[27]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[57]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[28]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[58]++;
        Node nameNode = n.getFirstChild();
        renamer.addDeclaredName(nameNode.getString());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[59]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[29]++;}
}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[60]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;

      for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[7]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[8]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[9]++;
}
        findDeclaredNames(c, n, renamer);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[61]++;
      }

    } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[25]++;}
  }

  /**
   * Declared names renaming policy interface.
   */
  interface Renamer {

    /**
     * Called when a declared name is found in the local current scope.
     */
    void addDeclaredName(String name);

    /**
     * @return A replacement name, null if oldName is unknown or should not
     * be replaced.
     */
    String getReplacementName(String oldName);

    /**
     * @return Whether the constant-ness of a name should be removed.
     */
    boolean stripConstIfReplaced();

    /**
     * @return A Renamer for a scope within the scope of the current Renamer.
     */
    Renamer forChildScope();
  }

  /**
   * Inverts the transformation by {@link ContextualRenamer}, when possible.
   */
  static class ContextualRenameInverter
      implements ScopedCallback, CompilerPass {
    private final AbstractCompiler compiler;

    // The set of names referenced in the current scope.
    private Set<String> referencedNames = ImmutableSet.of();
  {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[62]++;
  }

    // Stack reference sets.
    private Deque<Set<String>> referenceStack = new ArrayDeque<Set<String>>();
  {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[63]++;
  }

    // Name are globally unique initially, so we don't need a per-scope map.
    private Map<String, List<Node>> nameMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[64]++;
  }

    private ContextualRenameInverter(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[65]++;
    }

    @Override
    public void process(Node externs, Node js) {
      NodeTraversal.traverse(compiler, js, this);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[66]++;
    }

    public static String getOrginalName(String name) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[67]++;
      int index = indexOfSeparator(name);
      return (index == -1) ? name : name.substring(0, index);
    }

    private static int indexOfSeparator(String name) {
      return name.lastIndexOf(ContextualRenamer.UNIQUE_ID_SEPARATOR);
    }

    private boolean containsSeparator(String name) {
      return name.indexOf(ContextualRenamer.UNIQUE_ID_SEPARATOR) != -1;
    }

    /**
     * Prepare a set for the new scope.
     */
    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[68]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[30]++;
        return;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[31]++;}

      referenceStack.push(referencedNames);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[69]++;
      referencedNames = Sets.newHashSet();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[70]++;
    }

    /**
     * Rename vars for the current scope, and merge any referenced
     * names into the parent scope reference set.
     */
    @Override
    public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[71]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[32]++;
        return;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[33]++;}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[72]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;

      for (Iterator<Var> it = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false);) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[10]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[11]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[12]++;
}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[73]++;
        Var v = it.next();
        handleScopeVar(v);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[74]++;
      }
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[75]++;

      // Merge any names that were referenced but not declared in the current
      // scope.
      Set<String> current = referencedNames;
      referencedNames = referenceStack.pop();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[76]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
      // If there isn't anything left in the stack we will be going into the
      // global scope: don't try to build a set of referenced names for the
      // global scope.
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((referenceStack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[34]++;
        referencedNames.addAll(current);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[78]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[35]++;}
    }

    /**
     * For the Var declared in the current scope determine if it is possible
     * to revert the name to its original form without conflicting with other
     * values.
     */
    void handleScopeVar(Var v) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[79]++;
      String name  = v.getName();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((containsSeparator(name)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((getOrginalName(name).isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[36]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[81]++;
        String newName = findReplacementName(name);
        referencedNames.remove(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[82]++;
        // Adding a reference to the new name to prevent either the parent
        // scopes or the current scope renaming another var to this new name.
        referencedNames.add(newName);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[83]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[84]++;
        List<Node> references = nameMap.get(name);
        Preconditions.checkState(references != null);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[85]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[86]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[13]++;


        for (Node n : references) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[13]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[14]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[15]++;
}
          Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[87]++;
          n.setString(newName);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[88]++;
        }
        compiler.reportCodeChange();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[89]++;
        nameMap.remove(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[90]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[37]++;}
    }

    /**
     * Find a name usable in the local scope.
     */
    private String findReplacementName(String name) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[91]++;
      String original = getOrginalName(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[92]++;
      String newName = original;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[93]++;
      int i = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[94]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[16]++;


int CodeCoverConditionCoverageHelper_C18;
      while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isValidName(newName)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[16]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[17]--;
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.loops[18]++;
}
        newName = original +
            ContextualRenamer.UNIQUE_ID_SEPARATOR + String.valueOf(i++);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[95]++;
      }
      return newName;
    }

    /**
     * @return Whether the name is valid to use in the local scope.
     */
    private boolean isValidName(String name) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[96]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(name)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((referencedNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((name.equals(ARGUMENTS)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[38]++;
        return true;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[39]++;}
      return false;
    }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node node, Node parent) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[97]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[40]++;
        return;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[41]++;}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[98]++;
int CodeCoverConditionCoverageHelper_C21;

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.isReferenceName(node)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[42]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[99]++;
        String name = node.getString();
        // Add all referenced names to the set so it is possible to check for
        // conflicts.
        referencedNames.add(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[100]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[101]++;
int CodeCoverConditionCoverageHelper_C22;
        // Store only references to candidate names in the node map.
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((containsSeparator(name)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[44]++;
          addCandidateNameReference(name, node);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[102]++;

        } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[45]++;}

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[43]++;}
    }

    private void addCandidateNameReference(String name, Node n) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[103]++;
      List<Node> nodes = nameMap.get(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((null == nodes) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[46]++;
        nodes = Lists.newLinkedList();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[105]++;
        nameMap.put(name, nodes);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[106]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[47]++;}
      nodes.add(n);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[107]++;
    }
  }

  /**
   * Rename every locally name to be unique, the first encountered declaration
   * (specifically global names) are left in their original form. Those that are
   * renamed are made unique by giving them a unique suffix based on
   * the number of declarations of the name.
   *
   * The root ContextualRenamer is assumed to be in GlobalScope.
   *
   * Used by the Normalize pass.
   * @see Normalize
   */
  static class ContextualRenamer implements Renamer {
    private final Multiset<String> nameUsage;
    private final Map<String, String> declarations = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[108]++;
  }
    private final boolean global;

    final static String UNIQUE_ID_SEPARATOR = "$$";

    ContextualRenamer() {
      this.global = true;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[109]++;
      nameUsage = HashMultiset.create();
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[110]++;
    }

    /**
     * Constructor for child scopes.
     */
    private ContextualRenamer(Multiset<String> nameUsage) {
      this.global = false;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[111]++;
      this.nameUsage = nameUsage;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[112]++;
    }

    /**
     * Create a ContextualRenamer
     */
    @Override
    public Renamer forChildScope() {
      return new ContextualRenamer(nameUsage);
    }

    /**
     * Adds a name to the map of names declared in this scope.
     */
    @Override
    public void addDeclaredName(String name) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[113]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((name.equals(ARGUMENTS)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[48]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[114]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((global) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[50]++;
          reserveName(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[115]++;

        } else {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[51]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[116]++;
int CodeCoverConditionCoverageHelper_C26;
          // It hasn't been declared locally yet, so increment the count.
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((declarations.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[52]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[117]++;
            int id = incrementNameCount(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[118]++;
            String newName = null;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[119]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[54]++;
              newName = getUniqueName(name, id);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[120]++;

            } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[55]++;}
            declarations.put(name, newName);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[121]++;

          } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[53]++;}
        }

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[49]++;}
    }

    @Override
    public String getReplacementName(String oldName) {
      return declarations.get(oldName);
    }

    /**
     * Given a name and the associated id, create a new unique name.
     */
    private String getUniqueName(String name, int id) {
      return name + UNIQUE_ID_SEPARATOR + id;
    }

    private void reserveName(String name) {
      nameUsage.setCount(name, 0, 1);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[122]++;
    }

    private int incrementNameCount(String name) {
      return nameUsage.add(name, 1);
    }

    @Override
    public boolean stripConstIfReplaced() {
      return false;
    }
  }


  /**
   * Rename every declared name to be unique. Typically this would be used
   * when injecting code to insure that names do not conflict with existing
   * names.
   *
   * Used by the FunctionInjector
   * @see FunctionInjector
   */
  static class InlineRenamer implements Renamer {
    private final Map<String, String> declarations = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[123]++;
  }
    private final Supplier<String> uniqueIdSupplier;
    private final String idPrefix;
    private final boolean removeConstness;
    private final CodingConvention convention;

    InlineRenamer(
        CodingConvention convention,
        Supplier<String> uniqueIdSupplier,
        String idPrefix,
        boolean removeConstness) {
      this.convention = convention;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[124]++;
      this.uniqueIdSupplier = uniqueIdSupplier;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[125]++;
      // To ensure that the id does not conflict with the id from the
      // ContextualRenamer some prefix is needed.
      Preconditions.checkArgument(!idPrefix.isEmpty());
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[126]++;
      this.idPrefix = idPrefix;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[127]++;
      this.removeConstness = removeConstness;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[128]++;
    }

    @Override
    public void addDeclaredName(String name) {
      Preconditions.checkState(!name.equals(ARGUMENTS));
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[129]++;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[130]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((declarations.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[56]++;
        declarations.put(name, getUniqueName(name));
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[131]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[57]++;}
    }

    private String getUniqueName(String name) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[132]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[58]++;
        return name;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[59]++;}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[133]++;
int CodeCoverConditionCoverageHelper_C30;

      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((name.indexOf(ContextualRenamer.UNIQUE_ID_SEPARATOR) != -1) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[60]++;
          name = name.substring(
              0, name.lastIndexOf(ContextualRenamer.UNIQUE_ID_SEPARATOR));
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[134]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[61]++;}
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[135]++;
int CodeCoverConditionCoverageHelper_C31;

      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((convention.isExported(name)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[62]++;
        // The google internal coding convention includes a naming convention
        // to export names starting with "_".  Simply strip "_" those to avoid
        // exporting names.
        name = "JSCompiler_" + name;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[136]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[63]++;}

      // By using the same separator the id will be stripped if it isn't
      // needed when variable renaming is turned off.
      return name + ContextualRenamer.UNIQUE_ID_SEPARATOR
          + idPrefix + uniqueIdSupplier.get();
    }

    @Override
    public String getReplacementName(String oldName) {
      return declarations.get(oldName);
    }

    @Override
    public Renamer forChildScope() {
      return new InlineRenamer(
          convention, uniqueIdSupplier, idPrefix, removeConstness);
    }

    @Override
    public boolean stripConstIfReplaced() {
      return removeConstness;
    }
  }

  /**
   * For injecting boilerplate libraries. Leaves global names alone
   * and renames local names like InlineRenamer.
   */
  static class BoilerplateRenamer extends ContextualRenamer {
    private final Supplier<String> uniqueIdSupplier;
    private final String idPrefix;
    private final CodingConvention convention;

    BoilerplateRenamer(
        CodingConvention convention,
        Supplier<String> uniqueIdSupplier,
        String idPrefix) {
      this.convention = convention;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[137]++;
      this.uniqueIdSupplier = uniqueIdSupplier;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[138]++;
      this.idPrefix = idPrefix;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[139]++;
    }

    @Override
    public Renamer forChildScope() {
      return new InlineRenamer(convention, uniqueIdSupplier, idPrefix, false);
    }
  }

  /** Only rename things that match the whitelist. Wraps another renamer. */
  static class WhitelistedRenamer implements Renamer {
    private Renamer delegate;
    private Set<String> whitelist;

    WhitelistedRenamer(Renamer delegate, Set<String> whitelist) {
      this.delegate = delegate;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[140]++;
      this.whitelist = whitelist;
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[141]++;
    }

    @Override public void addDeclaredName(String name) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[142]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((whitelist.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[64]++;
        delegate.addDeclaredName(name);
CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.statements[143]++;

      } else {
  CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh.branches[65]++;}
    }

    @Override public String getReplacementName(String oldName) {
      return whitelist.contains(oldName)
          ? delegate.getReplacementName(oldName) : null;
    }

    @Override public boolean stripConstIfReplaced() {
      return delegate.stripConstIfReplaced();
    }

    @Override public Renamer forChildScope() {
      return new WhitelistedRenamer(delegate.forChildScope(), whitelist);
    }
  }

}

class CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh ());
  }
    public static long[] statements = new long[144];
    public static long[] branches = new long[66];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[33];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MakeDeclaredNamesUnique.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,1,1,1,1,3,1,1,1,1,1,1,1,2,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 32; i++) {
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

  public CodeCoverCoverageCounter$z6stsd1de890sig0e3zdpgrm7rlpdztkm4h7dq6epgh () {
    super("com.google.javascript.jscomp.MakeDeclaredNamesUnique.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 143; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 65; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MakeDeclaredNamesUnique.java");
      for (int i = 1; i <= 143; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 65; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 32; i++) {
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

