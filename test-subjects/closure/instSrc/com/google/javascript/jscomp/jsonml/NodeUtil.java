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

package com.google.javascript.jscomp.jsonml;

import com.google.common.base.Preconditions;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Non public methods copied from com.google.javascript.jscomp.NodeUtil class.
 *
 * @author dhans@google.com (Daniel Hans)
 */
class NodeUtil {
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.ping();
  }


  /**
   * @return Whether the node represents a FOR-IN loop.
   */
  static boolean isForIn(Node n) {
    return n.getType() == Token.FOR
        && n.getChildCount() == 3;
  }

  /**
   * @return Whether the node is used as a statement.
   */
  static boolean isStatement(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[1]++;
    Node parent = n.getParent();
    // It is not possible to determine definitely if a node is a statement
    // or not if it is not part of the AST.  A FUNCTION node can be
    // either part of an expression or a statement.
    Preconditions.checkState(parent != null);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[2]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[3]++;
    switch (parent.getType()) {
      case Token.SCRIPT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[1]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[2]++;
      case Token.LABEL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[3]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[4]++;
        return false;
    }
  }

  /**
   * Is this node a function declaration? A function declaration is a function
   * that has a name that is added to the current scope (i.e. a function that
   * is not part of a expression).
   */
  static boolean isFunctionDeclaration(Node n) {
    return n.getType() == Token.FUNCTION && isStatement(n);
  }

  /**
   * Is this node a hoisted function declaration? A function declaration in the
   * scope root is hoisted to the top of the scope.
   * See {@link #isFunctionDeclaration}).
   */
  static boolean isHoistedFunctionDeclaration(Node n) {
    return isFunctionDeclaration(n)
        && (n.getParent().getType() == Token.SCRIPT
            || n.getParent().getParent().getType() == Token.FUNCTION);
  }
}

class CodeCoverCoverageCounter$gqhemrjldeicuv9al10h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gqhemrjldeicuv9al10h ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[5];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$gqhemrjldeicuv9al10h () {
    super("com.google.javascript.jscomp.jsonml.NodeUtil.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.NodeUtil.java");
      for (int i = 1; i <= 3; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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

