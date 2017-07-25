/*
 * Copyright 2007 The Closure Compiler Authors.
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

import javax.annotation.Nullable;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Set;

/**
 * A pass for stripping a list of provided JavaScript object types.
 *
 * The stripping strategy is as follows:
 *   - Provide: 1) a list of types that should be stripped, and 2) a list of
 *     suffixes of field/variable names that should be stripped.
 *   - Remove declarations of variables that are initialized using static
 *     methods of strip types (e.g. var x = goog.debug.Logger.getLogger(...);).
 *   - Remove all references to variables that are stripped.
 *   - Remove all object literal keys with strip names.
 *   - Remove all assignments to 1) field names that are strip names and
 *     2) qualified names that begin with strip types.
 *   - Remove all statements containing calls to static methods of strip types.
 *
 */
class StripCode implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.ping();
  }


  // TODO(user): Try eliminating the need for a list of strip names by instead
  // recording which field names are assigned to debug types in each JS input.
  private final AbstractCompiler compiler;
  private final Set<String> stripTypes;
  private final Set<String> stripNameSuffixes;
  private final Set<String> stripTypePrefixes;
  private final Set<String> stripNamePrefixes;
  private final Set<Scope.Var> varsToRemove;

  static final DiagnosticType STRIP_TYPE_INHERIT_ERROR = DiagnosticType.error(
      "JSC_STRIP_TYPE_INHERIT_ERROR",
      "Non-strip type {0} cannot inherit from strip type {1}");
  static {
    CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[1]++;
  }

  static final DiagnosticType STRIP_ASSIGNMENT_ERROR = DiagnosticType.error(
      "JSC_STRIP_ASSIGNMENT_ERROR",
      "Unable to strip assignment to {0}");
  static {
    CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[2]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The compiler
   */
  StripCode(AbstractCompiler compiler,
            Set<String> stripTypes,
            Set<String> stripNameSuffixes,
            Set<String> stripTypePrefixes,
            Set<String> stripNamePrefixes) {

    this.compiler = compiler;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[3]++;
    this.stripTypes = Sets.newHashSet(stripTypes);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[4]++;
    this.stripNameSuffixes = Sets.newHashSet(stripNameSuffixes);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[5]++;
    this.stripTypePrefixes = Sets.newHashSet(stripTypePrefixes);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[6]++;
    this.stripNamePrefixes = Sets.newHashSet(stripNamePrefixes);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[7]++;
    this.varsToRemove = Sets.newHashSet();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[8]++;
  }

  /**
   * Enables stripping of goog.tweak functions.
   */
  public void enableTweakStripping() {
    stripTypes.add("goog.tweak");
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[9]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new Strip());
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[10]++;
  }

  // -------------------------------------------------------------------------

  /**
   * A callback that strips debug code from a JavaScript parse tree.
   */
  private class Strip extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[11]++;
      switch (n.getType()) {
        case Token.VAR:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[1]++;
          removeVarDeclarationsByNameOrRvalue(t, n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[12]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[13]++;
          break;

        case Token.NAME:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[2]++;
          maybeRemoveReferenceToRemovedVariable(t, n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[14]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[15]++;
          break;

        case Token.ASSIGN:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[3]++;
        case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[4]++;
        case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[5]++;
        case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[6]++;
        case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[7]++;
        case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[8]++;
        case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[9]++;
        case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[10]++;
        case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[11]++;
        case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[12]++;
        case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[13]++;
        case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[14]++;
          maybeEliminateAssignmentByLvalueName(t, n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[16]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[17]++;
          break;

        case Token.CALL:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[15]++;
        case Token.NEW:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[16]++;
          maybeRemoveCall(t, n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[18]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[19]++;
          break;

        case Token.OBJECTLIT:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[17]++;
          eliminateKeysWithStripNamesFromObjLit(t, n);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[20]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[21]++;
          break;

        case Token.EXPR_RESULT:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[18]++;
          maybeEliminateExpressionByName(t, n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[22]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[23]++;
          break; default : CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[19]++;
      }
    }

    /**
     * Removes declarations of any variables whose names are strip names or
     * whose whose r-values are static method calls on strip types. Builds a set
     * of removed variables so that all references to them can be removed.
     *
     * @param t The traversal
     * @param n A VAR node
     * @param parent {@code n}'s parent
     */
    void removeVarDeclarationsByNameOrRvalue(NodeTraversal t, Node n,
        Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
      for (Node nameNode = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameNode != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false);
          nameNode = nameNode.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[1]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[2]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[3]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[25]++;
        String name = nameNode.getString();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((isStripName(name)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isCallWhoseReturnValueShouldBeStripped(nameNode.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[20]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[27]++;
          // Remove the NAME.
          Scope scope = t.getScope();
          varsToRemove.add(scope.getVar(name));
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[28]++;
          n.removeChild(nameNode);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[29]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[30]++;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[21]++;}
      }
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[22]++;
        // Must also remove the VAR.
        replaceWithEmpty(n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[32]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[33]++;

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[23]++;}
    }

    /**
     * Removes a reference if it is a reference to a removed variable.
     *
     * @param t The traversal
     * @param n A NAME node
     * @param parent {@code n}'s parent
     */
    void maybeRemoveReferenceToRemovedVariable(NodeTraversal t, Node n,
                                               Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[34]++;
      switch (parent.getType()) {
        case Token.VAR:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[24]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[35]++;
          // This is a variable declaration, not a reference.
          break;

        case Token.GETPROP:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[25]++;
          // GETPROP
          //   NAME
          //   STRING (property name)
        case Token.GETELEM:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[26]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
          // GETELEM
          //   NAME
          //   NUMBER|STRING|NAME|...
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isReferenceToRemovedVar(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[27]++;
            replaceHighestNestedCallWithNull(parent, parent.getParent());
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[37]++;

          } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[28]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[38]++;
          break;

        case Token.ASSIGN:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[29]++;
        case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[30]++;
        case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[31]++;
        case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[32]++;
        case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[33]++;
        case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[34]++;
        case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[35]++;
        case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[36]++;
        case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[37]++;
        case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[38]++;
        case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[39]++;
        case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[40]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[39]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isReferenceToRemovedVar(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[41]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[43]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[41]++;
              Node gramps = parent.getParent();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
              if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((gramps.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[45]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[43]++;
                // Remove the assignment.
                Node greatGramps = gramps.getParent();
                replaceWithEmpty(gramps, greatGramps);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[44]++;
                compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[45]++;

              } else {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[46]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[46]++;
                // Substitute the r-value for the assignment.
                Node rvalue = n.getNext();
                parent.removeChild(rvalue);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[47]++;
                gramps.replaceChild(parent, rvalue);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[48]++;
                compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[49]++;
              }

            } else {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[44]++;
              // The var reference is the r-value. Replace it with null.
              replaceWithNull(n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[50]++;
              compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[51]++;
            }

          } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[42]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[52]++;
          break;

        default:
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[47]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isReferenceToRemovedVar(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[48]++;
            replaceWithNull(n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[54]++;
            compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[55]++;

          } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[49]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[56]++;
          break;
      }
    }

    /**
     * Use a while loop to get up out of any nested calls. For example,
     * if we have just detected that we need to remove the a.b() call
     * in a.b().c().d(), we'll have to remove all of the calls, and it
     * will take a few iterations through this loop to get up to d().
     */
    void replaceHighestNestedCallWithNull(Node node, Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[57]++;
      Node ancestor = parent;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[58]++;
      Node ancestorChild = node;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[59]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[4]++;


      while (true) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[4]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[5]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[6]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[60]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ancestor.getFirstChild() != ancestorChild) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[50]++;
          replaceWithNull(ancestorChild, ancestor);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[61]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[62]++;
          break;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[51]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ancestor.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[52]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[64]++;
          // Remove the entire expression statement.
          Node ancParent = ancestor.getParent();
          replaceWithEmpty(ancestor, ancParent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[65]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[66]++;
          break;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[53]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[67]++;
        int type = ancestor.getType();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((type != Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((type != Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type != Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[54]++;
          replaceWithNull(ancestorChild, ancestor);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[69]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[70]++;
          break;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[55]++;}
        ancestorChild = ancestor;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[71]++;
        ancestor = ancestor.getParent();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[72]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[73]++;
    }

    /**
     * Eliminates an assignment if the l-value is:
     *  - A field name that's a strip name
     *  - A qualified name that begins with a strip type
     *
     * @param t The traversal
     * @param n An ASSIGN node
     * @param parent {@code n}'s parent
     */
    void maybeEliminateAssignmentByLvalueName(NodeTraversal t, Node n,
                                              Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[74]++;
      // ASSIGN
      //   l-value
      //   r-value
      Node lvalue = n.getFirstChild();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[75]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((nameEndsWithFieldNameToStrip(lvalue)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((qualifiedNameBeginsWithStripType(lvalue)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[56]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[76]++;
int CodeCoverConditionCoverageHelper_C14;

        // Limit to EXPR_RESULT because it is not
        // safe to eliminate assignment in complex expressions,
        // e.g. in ((x = 7) + 8)
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[58]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[77]++;
          Node gramps = parent.getParent();
          replaceWithEmpty(parent, gramps);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[78]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[79]++;

        } else {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[59]++;
          t.report(n, STRIP_ASSIGNMENT_ERROR, lvalue.getQualifiedName());
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[80]++;
        }

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[57]++;}
    }

    /**
     * Eliminates an expression if it refers to:
     *  - A field name that's a strip name
     *  - A qualified name that begins with a strip type
     * This gets rid of construct like:
     *  a.prototype.logger; (used instead of a.prototype.logger = null;)
     * This expression is not an assignment and so will not be caught by
     * maybeEliminateAssignmentByLvalueName.
     * @param t The traversal
     * @param n An EXPR_RESULT node
     * @param parent {@code n}'s parent
     */
    void maybeEliminateExpressionByName(NodeTraversal t, Node n,
                                        Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[81]++;
      // EXPR_RESULT
      //   expression
      Node expression = n.getFirstChild();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[82]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((nameEndsWithFieldNameToStrip(expression)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((qualifiedNameBeginsWithStripType(expression)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[60]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[83]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[62]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[84]++;
          Node gramps = parent.getParent();
          replaceWithEmpty(parent, gramps);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[85]++;

        } else {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[63]++;
          replaceWithEmpty(n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[86]++;
        }
        compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[87]++;

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[61]++;}
    }

    /**
     * Removes a method call if {@link #isMethodOrCtorCallThatTriggersRemoval}
     * indicates that it should be removed.
     *
     * @param t The traversal
     * @param n A CALL node
     * @param parent {@code n}'s parent
     */
    void maybeRemoveCall(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[88]++;
int CodeCoverConditionCoverageHelper_C17;
      // CALL/NEW
      //   function
      //   arguments
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isMethodOrCtorCallThatTriggersRemoval(t, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[64]++;
        replaceHighestNestedCallWithNull(n, parent);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[89]++;

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[65]++;}
    }

    /**
     * Eliminates any object literal keys in an object literal declaration that
     * have strip names.
     *
     * @param t The traversal
     * @param n An OBJLIT node
     */
    void eliminateKeysWithStripNamesFromObjLit(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[90]++;
      // OBJLIT
      //   key1
      //     value1
      //   key2
      //   ...
      Node key = n.getFirstChild();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[91]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
      while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[7]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[8]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[9]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[92]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isStripName(key.getString())) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[66]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[93]++;
          Node next = key.getNext();
          n.removeChild(key);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[94]++;
          key = next;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[95]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[96]++;

        } else {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[67]++;
          key = key.getNext();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[97]++;
        }
      }
    }

    /**
     * Gets whether a node is a CALL node whose return value should be
     * stripped. A call's return value should be stripped if the function
     * getting called is a static method in a class that gets stripped. For
     * example, if "goog.debug.Logger" is a strip name, then this function
     * returns true for a call such as "goog.debug.Logger.getLogger(...)".  It
     * may also simply be a function that is getting stripped.  For example,
     * if "getLogger" is a strip name, but not "goog.debug.Logger", this will
     * still return true.
     *
     * @param n A node (typically a CALL node)
     * @return Whether the call's return value should be stripped
     */
    boolean isCallWhoseReturnValueShouldBeStripped(@Nullable Node n) {
      return n != null &&
          (n.isCall() ||
           n.isNew()) &&
          n.hasChildren() &&
          (qualifiedNameBeginsWithStripType(n.getFirstChild()) ||
              nameEndsWithFieldNameToStrip(n.getFirstChild()));
    }

    /**
     * Gets whether a qualified name begins with a strip name. The names
     * "goog.debug", "goog.debug.Logger", and "goog.debug.Logger.Level" are
     * examples of strip names that would result in this function returning
     * true for a node representing the name "goog.debug.Logger.Level".
     *
     * @param n A node (typically a NAME or GETPROP node)
     * @return Whether the name begins with a strip name
     */
    boolean qualifiedNameBeginsWithStripType(Node n) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[98]++;
      String name = n.getQualifiedName();
      return qualifiedNameBeginsWithStripType(name);
    }

    /**
     * Gets whether a qualified name begins with a strip name. The names
     * "goog.debug", "goog.debug.Logger", and "goog.debug.Logger.Level" are
     * examples of strip names that would result in this function returning
     * true for a node representing the name "goog.debug.Logger.Level".
     *
     * @param name A qualified class name
     * @return Whether the name begins with a strip name
     */
    boolean qualifiedNameBeginsWithStripType(String name) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[99]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[68]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[100]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[10]++;


        for (String type : stripTypes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[10]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[11]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[12]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[101]++;
int CodeCoverConditionCoverageHelper_C21;
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((name.equals(type)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((name.startsWith(type + ".")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[70]++;
            return true;

          } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[71]++;}
        }
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[102]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[13]++;


        for (String type : stripTypePrefixes) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[13]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[14]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[15]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[103]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((name.startsWith(type)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[72]++;
            return true;

          } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[73]++;}
        }

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[69]++;}
      return false;
    }

    /**
     * Determines whether a NAME node represents a reference to a variable that
     * has been removed.
     *
     * @param t The traversal
     * @param n A NAME node
     * @return Whether the variable was removed
     */
    boolean isReferenceToRemovedVar(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[104]++;
      String name = n.getString();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[105]++;
      Scope scope = t.getScope();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[106]++;
      Scope.Var var = scope.getVar(name);
      return varsToRemove.contains(var);
    }

    /**
     * Gets whether a CALL node triggers statement removal, based on the name
     * of the object whose method is being called, or the name of the method.
     * Checks whether the name begins with a strip type, ends with a field name
     * that's a strip name, or belongs to the set of global class-defining
     * functions (e.g. goog.inherits).
     *
     * @param t The traversal
     * @param n A CALL node
     * @return Whether the node triggers statement removal
     */
    boolean isMethodOrCtorCallThatTriggersRemoval(
        NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[107]++;
      // CALL/NEW
      //   GETPROP (function)         <-- we're interested in this, the function
      //     GETPROP (callee object)  <-- or the object on which it is called
      //       ...
      //       STRING (field name)
      //     STRING (method name)
      //   ... (arguments)

      Node function = n.getFirstChild();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[108]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((function == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((function.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[74]++;
        // We are only interested in calls on object references that are
        // properties. We don't need to eliminate method calls on variables
        // that are getting removed, since that's already done by the code
        // that removes all references to those variables.
        return false;

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[75]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[109]++;
int CodeCoverConditionCoverageHelper_C24;

      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[76]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[110]++;
        Node gramps = parent.getParent();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[111]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((gramps != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((gramps.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[78]++;
          // The call's return value is being used to initialize a newly
          // declared variable. We should leave the call intact for now.
          // That way, when the traversal reaches the variable declaration,
          // we'll recognize that the variable and all references to it need
          // to be eliminated.
          return false;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[79]++;}

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[77]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[112]++;

      Node callee = function.getFirstChild();
      return nameEndsWithFieldNameToStrip(callee) ||
          nameEndsWithFieldNameToStrip(function) ||
          qualifiedNameBeginsWithStripType(function) ||
          actsOnStripType(t, n);
    }

    /**
     * Gets whether a name ends with a field name that should be stripped. For
     * example, this function would return true when passed "this.logger" or
     * "a.b.c.myLogger" if "logger" is a strip name.
     *
     * @param n A node (typically a GETPROP node)
     * @return Whether the name ends with a field name that should be stripped
     */
    boolean nameEndsWithFieldNameToStrip(@Nullable Node n) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[113]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[80]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[114]++;
        Node propNode = n.getLastChild();
        return propNode != null && propNode.isString() &&
               isStripName(propNode.getString());

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[81]++;}
      return false;
    }

    /**
     * Determines whether the given node helps to define a
     * strip type. For example, goog.inherits(stripType, Object)
     * would be such a call.
     *
     * Also reports an error if a non-strip type inherits from a strip type.
     *
     * @param t The current traversal
     * @param callNode The CALL node
     */
    private boolean actsOnStripType(NodeTraversal t, Node callNode) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[115]++;
      SubclassRelationship classes =
          compiler.getCodingConvention().getClassesDefinedByCall(callNode);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[116]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((classes != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[82]++;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[117]++;
int CodeCoverConditionCoverageHelper_C28;
        // It's okay to strip a type that inherits from a non-stripped type
        // e.g. goog.inherits(goog.debug.Logger, Object)
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((qualifiedNameBeginsWithStripType(classes.subclassName)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[84]++;
          return true;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[85]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[118]++;
int CodeCoverConditionCoverageHelper_C29;

        // report an error if a non-strip type inherits from a
        // strip type.
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((qualifiedNameBeginsWithStripType(classes.superclassName)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[86]++;
          t.report(callNode, STRIP_TYPE_INHERIT_ERROR,
                   classes.subclassName, classes.superclassName);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[119]++;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[87]++;}

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[83]++;}

      return false;
    }

    /**
     * Gets whether a JavaScript identifier is the name of a variable or
     * property that should be stripped.
     *
     * @param name A JavaScript identifier
     * @return Whether {@code name} is a name that triggers removal
     */
    boolean isStripName(String name) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[120]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((stripNameSuffixes.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((stripNamePrefixes.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[88]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[89]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[121]++;
int CodeCoverConditionCoverageHelper_C31;

      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((name.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((Character.isUpperCase(name.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[90]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[91]++;}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[122]++;

      String lcName = name.toLowerCase();
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[123]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[16]++;


      for (String stripName : stripNamePrefixes) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[16]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[17]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[18]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[124]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((lcName.startsWith(stripName.toLowerCase())) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[92]++;
          return true;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[93]++;}
      }
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[125]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[19]++;



      for (String stripName : stripNameSuffixes) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[19]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[20]--;
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.loops[21]++;
}
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[126]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((lcName.endsWith(stripName.toLowerCase())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[94]++;
          return true;

        } else {
  CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.branches[95]++;}
      }

      return false;
    }

    /**
     * Replaces a node with a NULL node. This is useful where a value is
     * expected.
     *
     * @param n A node
     * @param parent {@code n}'s parent
     */
    void replaceWithNull(Node n, Node parent) {
      parent.replaceChild(n, IR.nullNode());
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[127]++;
    }

    /**
     * Replaces a node with an EMPTY node. This is useful where a statement is
     * expected.
     *
     * @param n A node
     * @param parent {@code n}'s parent
     */
    void replaceWithEmpty(Node n, Node parent) {
      NodeUtil.removeChild(parent, n);
CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5.statements[128]++;
    }
  }
}

class CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5 ());
  }
    public static long[] statements = new long[129];
    public static long[] branches = new long[96];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.StripCode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,2,1,1,1,1,0,1,1,3,2,1,2,1,1,1,1,1,2,1,2,2,2,2,1,1,1,2,2,1,1};
    for (int i = 1; i <= 33; i++) {
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

  public CodeCoverCoverageCounter$3imio0213smo87vn8fnhb5 () {
    super("com.google.javascript.jscomp.StripCode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 128; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 95; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.StripCode.java");
      for (int i = 1; i <= 128; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 95; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 33; i++) {
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

