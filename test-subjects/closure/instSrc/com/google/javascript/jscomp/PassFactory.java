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

/**
 * A factory for creating JSCompiler passes based on the Options
 * injected.  Contains all meta-data about compiler passes (like
 * whether it can be run multiple times, a human-readable name for
 * logging, etc.).
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public abstract class PassFactory {
  static {
    CodeCoverCoverageCounter$4rbbb6fb6wnt3157y18oimhy9.ping();
  }


  private final String name;
  private final boolean isOneTimePass;

  /**
   * @param name The name of the pass that this factory creates.
   * @param isOneTimePass If true, the pass produced by this factory can
   *     only be run once.
   */
  protected PassFactory(String name, boolean isOneTimePass) {
    this.name = name;
CodeCoverCoverageCounter$4rbbb6fb6wnt3157y18oimhy9.statements[1]++;
    this.isOneTimePass = isOneTimePass;
CodeCoverCoverageCounter$4rbbb6fb6wnt3157y18oimhy9.statements[2]++;
  }

  /**
   * @return The name of this pass.
   */
  String getName() {
    return name;
  }

  /**
   * @return Whether the pass produced by this factory can only be run once.
   */
  boolean isOneTimePass() {
    return isOneTimePass;
  }

  /**
   * Creates a new compiler pass to be run.
   */
  abstract CompilerPass create(AbstractCompiler compiler);

  /**
   * Any factory whose CompilerPass has a corresponding hot-swap version should
   * override this.
   *
   * @param compiler The compiler that can has been used to do the full compile.
   */
  HotSwapCompilerPass getHotSwapPass(AbstractCompiler compiler) {
    // TODO(bashir): If in future most of PassFactory's in DefaultPassConfig
    // turns out to be DefaultPassConfig.HotSwapPassFactory, we should probably
    // change the implementation here by the one in HotSwapPassFactory.
    return null;
  }
}

class CodeCoverCoverageCounter$4rbbb6fb6wnt3157y18oimhy9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4rbbb6fb6wnt3157y18oimhy9 ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$4rbbb6fb6wnt3157y18oimhy9 () {
    super("com.google.javascript.jscomp.PassFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.PassFactory.java");
      for (int i = 1; i <= 2; i++) {
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

