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

package com.google.javascript.jscomp.type;

import com.google.javascript.rhino.Node;

/**
 * This interface defines what reversed abstract interpreters provide.
 * <p>Abstract interpretation is the process of interpreting a program at an
 * abstracted level (such as at the type level) instead of the concrete level
 * (the flow of values). This reversed abstract interpreter reverses the
 * abstract interpretation process by knowing the outcome of some computation
 * and calculating a preciser view of the world than the view without knowing
 * the outcome of the computation.</p>
 *
 */
public interface ReverseAbstractInterpreter {
  /**
   * Calculates a precise version of the scope knowing the outcome of the
   * condition.
   *
   *  @param condition the condition's expression
   *  @param blindScope the scope without knowledge about the outcome of the
   *  condition
   *  @param outcome the outcome of the condition
   */
  FlowScope getPreciserScopeKnowingConditionOutcome(Node condition,
      FlowScope blindScope, boolean outcome);
}

class CodeCoverCoverageCounter$ae9xj71pjeah3gbpo34w45k7i6uuiag5zwda3y4dfc8iak4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ae9xj71pjeah3gbpo34w45k7i6uuiag5zwda3y4dfc8iak4h ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$ae9xj71pjeah3gbpo34w45k7i6uuiag5zwda3y4dfc8iak4h () {
    super("com.google.javascript.jscomp.type.ReverseAbstractInterpreter.java");
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
    log.startNamedSection("com.google.javascript.jscomp.type.ReverseAbstractInterpreter.java");
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

