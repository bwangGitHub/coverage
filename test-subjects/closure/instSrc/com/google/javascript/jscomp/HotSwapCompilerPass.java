/*
 * Copyright 2011 The Closure Compiler Authors.
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

/**
 * Interface for compiler passes that can be used in a hot-swap fashion.
 * <p>
 * The additional method is {@code hotSwapScript} which runs this pass on a
 * subtree of the AST. Each pass that is intended to support hot-swap style
 * should implement this interface.
 * <p>
 * It is assumed that {@code Node} argument of {@code hotSwapScript} is the root
 * of a sub-tree in AST that represents a JS file and so is of type {@code
 * Token.SCRIPT}.
 *
 * @author bashir@google.com (Bashir Sadjad)
 */
public interface HotSwapCompilerPass extends CompilerPass {

  /**
   * Process the JS with root node root. This is supposed to be significantly
   * faster compared to corresponding full-compiler passes.
   *
   * @param scriptRoot Root node corresponding to the file that is modified,
   *        should be of type {@code Token.SCRIPT}.
   * @param originalRoot Root node corresponding to the original version of the
   *        file that is modified. Should be of type {@code token.SCRIPT}.
   */
  void hotSwapScript(Node scriptRoot, Node originalRoot);

}

class CodeCoverCoverageCounter$gp0e4osjuwowlfifd2uhmegpf202sai0dq7oh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gp0e4osjuwowlfifd2uhmegpf202sai0dq7oh ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$gp0e4osjuwowlfifd2uhmegpf202sai0dq7oh () {
    super("com.google.javascript.jscomp.HotSwapCompilerPass.java");
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
    log.startNamedSection("com.google.javascript.jscomp.HotSwapCompilerPass.java");
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
