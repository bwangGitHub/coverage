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

import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;

import java.io.Serializable;

/**
 * An interface for accessing the AST root of an input.
 *
 */
public interface SourceAst extends Serializable {
  /**
   * Gets the root node of the AST for the source file this represents. The AST
   * is lazily instantiated and cached.
   */
  public Node getAstRoot(AbstractCompiler compiler);

  /**
   * Removes any references to root node of the AST.  If it is requested again,
   * another parse will be performed.  This method is needed to allow the ASTs
   * to be garbage collected if the inputs are still around after compilation.
   */
  public void clearAst();

  /** @return The input id associated with this AST */
  public InputId getInputId();

  /** Returns the source file the generated AST represents. */
  public SourceFile getSourceFile();

  /**
   * Sets the source file the generated AST represents. This can be called after
   * deserializing if access to the source file is needed. If a different file
   * is provided than that with which this was created, an IllegalStateException
   * will be thrown.
   */
  public void setSourceFile(SourceFile file);
}

class CodeCoverCoverageCounter$3ilgcn9pep7j2ynzh1paht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3ilgcn9pep7j2ynzh1paht ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3ilgcn9pep7j2ynzh1paht () {
    super("com.google.javascript.jscomp.SourceAst.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.SourceAst.java");
      for (int i = 1; i <= -1; i++) {
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

