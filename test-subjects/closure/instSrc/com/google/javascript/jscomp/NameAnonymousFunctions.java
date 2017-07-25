/*
 * Copyright 2004 The Closure Compiler Authors.
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

import com.google.javascript.rhino.Node;

import java.util.logging.*;

/**
 * Gives anonymous function names. This makes it way easier to debug because
 * debuggers and stack traces use the function names. So if you have
 *
 * goog.string.htmlEscape = function(str) {
 * }
 *
 * It will become
 *
 * goog.string.htmlEscape = function $goog$string$htmlEscape$(str) {
 * }
 *
 */
class NameAnonymousFunctions implements CompilerPass {
  static {
    CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.ping();
  }

  private static final Logger logger = Logger.getLogger(
      NameAnonymousFunctions.class.getName());
  static {
    CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[1]++;
  }

  static final char DELIMITER = '$';
  static {
    CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[2]++;
  }

  private final AbstractCompiler compiler;

  private int namedCount = 0;
  {
    CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[3]++;
  }
  private int bytesUsed = 0;
  {
    CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[4]++;
  }

  NameAnonymousFunctions(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[6]++;
    AnonymousFunctionNamingCallback namingCallback =
        new AnonymousFunctionNamingCallback(new AnonymousFunctionNamer());
    NodeTraversal.traverse(compiler, root, namingCallback);
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[7]++;
    logger.fine("Named " + namedCount + " anon functions using " +
        bytesUsed + " bytes");
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[8]++;
  }

  /**
   * Names anonymous functions. The function names don't have to be globally
   * unique or even locally unique. We make them somewhat unique because of a
   * bug in IE (and there may be other bugs we haven't found). See unit test for
   * more info.
   */
  private class AnonymousFunctionNamer
      implements AnonymousFunctionNamingCallback.FunctionNamer {
    private NodeNameExtractor nameExtractor;

    AnonymousFunctionNamer() {
      this.nameExtractor = new NodeNameExtractor(DELIMITER);
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[9]++;
    }

    /**
     * Returns a likely not conflicting name to make IE happy. See unit test
     * for more info.
     */
    private String getLikelyNonConflictingName(String name) {
      return DELIMITER + name + DELIMITER;
    }

    @Override
    public final String getName(Node node) {
      return nameExtractor.getName(node);
    }

    @Override
    public final void setFunctionName(String name, Node fnNode) {
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[10]++;
      Node fnNameNode = fnNode.getFirstChild();
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[11]++;
      String uniqueName = getLikelyNonConflictingName(name);
      fnNameNode.setString(uniqueName);
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[12]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[13]++;
      namedCount++;
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[14]++;
      bytesUsed += uniqueName.length();
CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh.statements[15]++;
    }

    @Override
    public final String getCombinedName(String lhs, String rhs) {
      return lhs + DELIMITER + rhs;
    }
  }
}

class CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$50g25jc24fewxlxfojul7xhlbevows1g6m69m4ulwh () {
    super("com.google.javascript.jscomp.NameAnonymousFunctions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameAnonymousFunctions.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
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

