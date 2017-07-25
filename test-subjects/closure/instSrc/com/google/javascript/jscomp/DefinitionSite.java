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

import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.rhino.Node;

/**
 * Information about the context in which a Definition appears.
 * Includes the definition node, and context in which the definition
 * occurs - including the definition module.
 *
 */

class DefinitionSite {
  static {
    CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9.ping();
  }

  final Node node;
  final Definition definition;
  final JSModule module;
  final boolean inGlobalScope;
  final boolean inExterns;

  DefinitionSite(Node node,
                 Definition definition,
                 JSModule module,
                 boolean inGlobalScope,
                 boolean inExterns) {
    this.node = node;
CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9.statements[1]++;
    this.definition = definition;
CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9.statements[2]++;
    this.module = module;
CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9.statements[3]++;
    this.inGlobalScope = inGlobalScope;
CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9.statements[4]++;
    this.inExterns = inExterns;
CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9.statements[5]++;
  }
}

class CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9 ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$14g2mmumy2lj7bkbqs5k91ai2ad0m9 () {
    super("com.google.javascript.jscomp.DefinitionSite.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.DefinitionSite.java");
      for (int i = 1; i <= 5; i++) {
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

