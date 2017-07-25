/*
 * Copyright 2008 The Closure Compiler Authors.
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
 * For use with CodeGenerator to determine the cost of generated code.
 *
 * @see CodeGenerator
 * @see CodePrinter
 */
class InlineCostEstimator {
  static {
    CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.ping();
  }

  // For now simply assume identifiers are 2 characters.
  private static final String ESTIMATED_IDENTIFIER = "ab";
  static {
    CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[1]++;
  }
  static final int ESTIMATED_IDENTIFIER_COST = ESTIMATED_IDENTIFIER.length();
  static {
    CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[2]++;
  }

  private InlineCostEstimator() {
  }

  /**
   * Determines the size of the JS code.
   */
  static int getCost(Node root) {
    return getCost(root, Integer.MAX_VALUE);
  }

  /**
   * Determines the estimated size of the JS snippet represented by the node.
   */
  static int getCost(Node root, int costThreshhold) {
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[3]++;
    CompiledSizeEstimator estimator = new CompiledSizeEstimator(costThreshhold);
    estimator.add(root);
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[4]++;
    return estimator.getCost();
  }

  /**
   * Code consumer that estimates compiled size by assuming names are
   * shortened and all whitespace is stripped.
   */
  private static class CompiledSizeEstimator extends CodeConsumer {
    private int maxCost;
    private int cost = 0;
  {
    CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[5]++;
  }
    private char last = '\0';
  {
    CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[6]++;
  }
    private boolean continueProcessing = true;
  {
    CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[7]++;
  }

    CompiledSizeEstimator(int costThreshhold) {
      this.maxCost = costThreshhold;
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[8]++;
    }

    void add(Node root) {
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[9]++;
      CodeGenerator cg = CodeGenerator.forCostEstimation(this);
      cg.add(root);
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[10]++;
    }

    int getCost() {
      return cost;
    }

    @Override
    boolean continueProcessing() {
      return continueProcessing;
    }

    @Override
    char getLastChar() {
      return last;
    }

    @Override
    void append(String str){
      last = str.charAt(str.length() - 1);
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[11]++;
      cost += str.length();
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[12]++;
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((maxCost <= cost) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.branches[1]++;
        continueProcessing = false;
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[14]++;

      } else {
  CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.branches[2]++;}
    }

    @Override
    void addIdentifier(String identifier) {
      add(ESTIMATED_IDENTIFIER);
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[15]++;
    }

    /**
     * Constants (true, false, null) are considered basically free,
     * because it's likely that they will get folded when we're done.
     */
    @Override
    void addConstant(String newcode) {
      add("0");
CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt.statements[16]++;
    }
  }
}

class CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InlineCostEstimator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$gx9w01iyx6tvscasdtxivx9x7rwv52siexjdt () {
    super("com.google.javascript.jscomp.InlineCostEstimator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InlineCostEstimator.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

