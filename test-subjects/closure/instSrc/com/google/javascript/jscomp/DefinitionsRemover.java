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
import com.google.common.collect.ImmutableSet;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Set;

/**
 * Models an assignment that defines a variable and the removal of it.
 *
 */
class DefinitionsRemover {
  static {
    CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.ping();
  }


  /**
   * @return an {@link Definition} object if the node contains a definition or
   *     {@code null} otherwise.
   */
  static Definition getDefinition(Node n, boolean isExtern) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[1]++;
    // TODO(user): Since we have parent pointers handy. A lot of constructors
    // can be simplified.

    // This logic must match #isDefinitionNode
    Node parent = n.getParent();
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[1]++;
      return null;

    } else {
  CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[2]++;}
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[3]++;
      return new VarDefinition(n, isExtern);

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[4]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[4]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[5]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[5]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[7]++;
        return new NamedFunctionDefinition(parent, isExtern);

      } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[8]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[6]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.getString().equals("")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[9]++;
        return new FunctionExpressionDefinition(parent, isExtern);

      } else {
  CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[10]++;}
}

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[6]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[7]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[11]++;
      return new AssignmentDefinition(parent, isExtern);

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[12]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[8]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[13]++;
      return new ObjectLiteralPropertyDefinition(parent, n, n.getFirstChild(),
          isExtern);

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[14]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[9]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((parent.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[15]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[10]++;
      Node function = parent.getParent();
      return new FunctionArgumentDefinition(function, n, isExtern);

    } else {
  CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[16]++;}
}
}
}
}
    return null;
  }

  /**
   * @return Whether a definition object can be created.
   */
  static boolean isDefinitionNode(Node n) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[11]++;
    // This logic must match #getDefinition
    Node parent = n.getParent();
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[12]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[17]++;
      return false;

    } else {
  CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[18]++;}
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[13]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[19]++;
      return true;

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[20]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[14]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[21]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[15]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(parent)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[23]++;
        return true;

      } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[24]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[16]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.getString().equals("")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[25]++;
        return true;

      } else {
  CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[26]++;}
}

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[22]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[17]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[27]++;
      return true;

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[28]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[18]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[29]++;
      return true;

    } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[30]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[19]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((parent.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[31]++;
      return true;

    } else {
  CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[32]++;}
}
}
}
}
    return false;
  }


  static abstract class Definition {

    private final boolean isExtern;

    Definition(boolean isExtern) {
      this.isExtern = isExtern;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[20]++;
    }

    /**
     * Removes this definition from the AST if it is not an extern.
     *
     * This method should not be called on a definition for which isExtern()
     * is true.
     */
    public void remove() {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[21]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[33]++;
        performRemove();
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[22]++;

      } else {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[34]++;
        throw new IllegalStateException("Attempt to remove() an extern" +
            " definition.");
      }
    }

    /**
     * Subclasses should override to remove the definition from the AST.
     */
    protected abstract void performRemove();

    /**
     * Variable or property name represented by this definition.
     * For example, in the case of assignments this method would
     * return the NAME, GETPROP or GETELEM expression that acts as the
     * assignment left hand side.
     *
     * @return the L-Value associated with this definition.
     *         The node's type is always NAME, GETPROP or GETELEM.
     */
    public abstract Node getLValue();

    /**
     * Value expression that acts as the right hand side of the
     * definition statement.
     */
    public abstract Node getRValue();

    /**
     * Returns true if the definition is an extern.
     */
    public boolean isExtern() {
      return isExtern;
    }
  }

  /**
   * Represents an name-only external definition.  The definition's
   * RHS is missing.
   */
  abstract static class IncompleteDefinition extends Definition {
    private static final Set<Integer> ALLOWED_TYPES =
        ImmutableSet.of(Token.NAME, Token.GETPROP, Token.GETELEM);
    private final Node lValue;

    IncompleteDefinition(Node lValue, boolean inExterns) {
      super(inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[23]++;
      Preconditions.checkNotNull(lValue);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[24]++;
      Preconditions.checkArgument(ALLOWED_TYPES.contains(lValue.getType()),
          "Unexpected lValue type %s", Token.name(lValue.getType()));
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[25]++;
      this.lValue = lValue;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[26]++;
    }

    @Override
    public Node getLValue() {
      return lValue;
    }

    @Override
    public Node getRValue() {
      return null;
    }
  }

  /**
   * Represents an unknown definition.
   */
  static final class UnknownDefinition extends IncompleteDefinition {
    UnknownDefinition(Node lValue, boolean inExterns) {
      super(lValue, inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[27]++;
    }

    @Override
    public void performRemove() {
      throw new IllegalArgumentException("Can't remove an UnknownDefinition");
    }
  }

  /**
   * Represents an name-only external definition.  The definition's
   * RHS is missing.
   */
  static final class ExternalNameOnlyDefinition extends IncompleteDefinition {

    ExternalNameOnlyDefinition(Node lValue) {
      super(lValue, true);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[28]++;
    }

    @Override
    public void performRemove() {
      throw new IllegalArgumentException(
          "Can't remove external name-only definition");
    }
  }

  /**
   * Represents a function formal parameter. The definition's RHS is missing.
   */
  static final class FunctionArgumentDefinition extends IncompleteDefinition {
    FunctionArgumentDefinition(Node function,
        Node argumentName,
        boolean inExterns) {
      super(argumentName, inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[29]++;
      Preconditions.checkArgument(function.isFunction());
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[30]++;
      Preconditions.checkArgument(argumentName.isName());
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[31]++;
    }

    @Override
    public void performRemove() {
      throw new IllegalArgumentException(
          "Can't remove a FunctionArgumentDefinition");
    }
  }

  /**
   * Represents a function declaration or function expression.
   */
  abstract static class FunctionDefinition extends Definition {

    protected final Node function;

    FunctionDefinition(Node node, boolean inExterns) {
      super(inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[32]++;
      Preconditions.checkArgument(node.isFunction());
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[33]++;
      function = node;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[34]++;
    }

    @Override
    public Node getLValue() {
      return function.getFirstChild();
    }

    @Override
    public Node getRValue() {
      return function;
    }
  }

  /**
   * Represents a function declaration without assignment node such as
   * {@code function foo()}.
   */
  static final class NamedFunctionDefinition extends FunctionDefinition {
    NamedFunctionDefinition(Node node, boolean inExterns) {
      super(node, inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[35]++;
    }

    @Override
    public void performRemove() {
      function.detachFromParent();
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[36]++;
    }
  }

  /**
   * Represents a function expression that acts as a RHS.  The defined
   * name is only reachable from within the function.
   */
  static final class FunctionExpressionDefinition extends FunctionDefinition {
    FunctionExpressionDefinition(Node node, boolean inExterns) {
      super(node, inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[37]++;
      Preconditions.checkArgument(
          NodeUtil.isFunctionExpression(node));
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[38]++;
    }

    @Override
    public void performRemove() {
      // replace internal name with ""
      function.replaceChild(function.getFirstChild(), IR.name(""));
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[39]++;
    }
  }

  /**
   * Represents a declaration within an assignment.
   */
  static final class AssignmentDefinition extends Definition {
    private final Node assignment;

    AssignmentDefinition(Node node, boolean inExterns) {
      super(inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[40]++;
      Preconditions.checkArgument(node.isAssign());
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[41]++;
      assignment = node;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[42]++;
    }

    @Override
    public void performRemove() {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[43]++;
      // A simple assignment. foo = bar() -> bar();
      Node parent = assignment.getParent();
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[44]++;
      Node last = assignment.getLastChild();
      assignment.removeChild(last);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[45]++;
      parent.replaceChild(assignment, last);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[46]++;
    }

    @Override
    public Node getLValue() {
      return assignment.getFirstChild();
    }

    @Override
    public Node getRValue() {
      return assignment.getLastChild();
    }
  }

  /**
   * Represents member declarations using a object literal.
   * Example: var x = { e : function() { } };
   */
  static final class ObjectLiteralPropertyDefinition extends Definition {

    private final Node literal;
    private final Node name;
    private final Node value;

    ObjectLiteralPropertyDefinition(Node lit, Node name, Node value,
          boolean isExtern) {
      super(isExtern);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[47]++;

      this.literal = lit;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[48]++;
      this.name = name;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[49]++;
      this.value = value;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[50]++;
    }

    @Override
    public void performRemove() {
      literal.removeChild(name);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[51]++;
    }

    @Override
    public Node getLValue() {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[52]++;
      // TODO(user) revisit: object literal definitions are an example
      // of definitions whose LHS doesn't correspond to a node that
      // exists in the AST.  We will have to change the return type of
      // getLValue sooner or later in order to provide this added
      // flexibility.

      switch (name.getType()) {
        case Token.SETTER_DEF:
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[35]++;
        case Token.GETTER_DEF:
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[36]++;
        case Token.STRING_KEY:
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[37]++;
          // TODO(johnlenz): return a GETELEM for quoted strings.
          return IR.getprop(
              IR.objectlit(),
              IR.string(name.getString()));
        default:
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.branches[38]++;
          throw new IllegalStateException("unexpected");
      }
    }

    @Override
    public Node getRValue() {
      return value;
    }
  }

  /**
   * Represents a VAR declaration with an assignment.
   */
  static final class VarDefinition extends Definition {
    private final Node name;
    VarDefinition(Node node, boolean inExterns) {
      super(inExterns);
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[53]++;
      Preconditions.checkArgument(NodeUtil.isVarDeclaration(node));
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[54]++;
      Preconditions.checkArgument(node.hasChildren(),
          "VAR Declaration of %sshould be assigned a value.", node.getString());
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[55]++;
      name = node;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[56]++;
    }

    @Override
    public void performRemove() {
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[57]++;
      Node var = name.getParent();
      Preconditions.checkState(var.getFirstChild() == var.getLastChild(),
          "AST should be normalized first");
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[58]++;
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[59]++;
      Node parent = var.getParent();
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[60]++;
      Node rValue = name.removeFirstChild();
      Preconditions.checkState(!parent.isFor());
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[61]++;
      parent.replaceChild(var, NodeUtil.newExpr(rValue));
CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh.statements[62]++;
    }

    @Override
    public Node getLValue() {
      return name;
    }

    @Override
    public Node getRValue() {
      return name.getFirstChild();
    }
  }
}

class CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh ());
  }
    public static long[] statements = new long[63];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DefinitionsRemover.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,1,1,2,1,1,1,2,2,1,1,2,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$27sy6pvrm67af41zgsb1qhu648wvcpaxvxwh () {
    super("com.google.javascript.jscomp.DefinitionsRemover.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 62; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DefinitionsRemover.java");
      for (int i = 1; i <= 62; i++) {
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
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

