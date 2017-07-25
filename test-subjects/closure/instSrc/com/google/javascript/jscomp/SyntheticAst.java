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

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;


/**
 * An AST generated totally by the compiler.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public class SyntheticAst implements SourceAst {
  static {
    CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[1]++;
  }

  private final InputId inputId;
  private final SourceFile sourceFile;

  private Node root;

  SyntheticAst(String sourceName) {
    this.inputId = new InputId(sourceName);
CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[2]++;
    this.sourceFile = new SourceFile(sourceName);
CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[3]++;
    clearAst();
CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[4]++;
  }

  @Override
  public Node getAstRoot(AbstractCompiler compiler) {
    return root;
  }

  @Override
  public void clearAst() {
    root = IR.script();
CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[5]++;
    root.setInputId(inputId);
CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[6]++;
    root.setStaticSourceFile(sourceFile);
CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5.statements[7]++;
  }

  @Override
  public InputId getInputId() {
    return inputId;
  }

  @Override
  public SourceFile getSourceFile() {
    return sourceFile;
  }

  @Override
  public void setSourceFile(SourceFile file) {
    throw new IllegalStateException(
        "Cannot set a source file for a synthetic AST");
  }
}

class CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5 ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$z54e6wexzn0uphqj7mgjivr1b5 () {
    super("com.google.javascript.jscomp.SyntheticAst.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.SyntheticAst.java");
      for (int i = 1; i <= 7; i++) {
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

