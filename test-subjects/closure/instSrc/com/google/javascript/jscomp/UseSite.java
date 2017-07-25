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

import com.google.javascript.rhino.Node;

/**
 * Information about the context in which a Definition is used.
 * Includes the referring node, and context in which the reference
 * occurs - including the module in which the reference appears.
 *
 */

class UseSite {
  static {
    CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69.ping();
  }

  final Node node;
  final Scope scope;
  final JSModule module;

  UseSite(Node node, Scope scope, JSModule module) {
    this.node = node;
CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69.statements[1]++;
    this.scope = scope;
CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69.statements[2]++;
    this.module = module;
CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69.statements[3]++;
  }

  // Use the node as the identifying feature to make the UseSite recreatable.

  @Override
  public int hashCode() {
    return this.node.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof UseSite && ((UseSite)(o)).node.equals(this.node));
  }
}

class CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69 ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2kavxfjo0sj9rjlqw69 () {
    super("com.google.javascript.jscomp.UseSite.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.UseSite.java");
      for (int i = 1; i <= 3; i++) {
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

