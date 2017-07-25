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

package com.google.debugging.sourcemap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Detect and parse the provided source map.
 * @author johnlenz@google.com (John Lenz)
 */
public class SourceMapConsumerFactory {
  static {
    CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.ping();
  }


  /** not constructible */
  private SourceMapConsumerFactory() {}

  /**
   * @param contents The string representing the source map file contents.
   * @return The parsed source map.
   * @throws SourceMapParseException
   */
  public static SourceMapping parse(String contents)
      throws SourceMapParseException {
     return parse(contents, null);
  }

  /**
   * @param contents The string representing the source map file contents.
   * @param supplier A supplier for any referenced maps.
   * @return The parsed source map.
   * @throws SourceMapParseException
   */
  public static SourceMapping parse(String contents, SourceMapSupplier supplier)
      throws SourceMapParseException {
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    // Version 1, starts with a magic string
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((contents.startsWith("/** Begin line maps. **/")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[1]++;
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[2]++;
      SourceMapConsumerV1 consumer =  new SourceMapConsumerV1();
      consumer.parse(contents);
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[3]++;
      return consumer;

    } else {
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[2]++;
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[4]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((contents.startsWith("{")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)){
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[3]++;
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[5]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[6]++;
        // Revision 2 and 3, are JSON Objects
        JSONObject sourceMapRoot = new JSONObject(contents);
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[7]++;
        // Check basic assertions about the format.
        int version = sourceMapRoot.getInt("version");
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[8]++;
        switch (version) {
          case 2:
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[6]++; {
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[9]++;
            SourceMapConsumerV2 consumer =  new SourceMapConsumerV2();
            consumer.parse(sourceMapRoot);
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[10]++;
            return consumer;
          }
          case 3:
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[7]++; {
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[11]++;
            SourceMapConsumerV3 consumer =  new SourceMapConsumerV3();
            consumer.parse(sourceMapRoot, supplier);
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.statements[12]++;
            return consumer;
          }
          default:
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[8]++;
            throw new SourceMapParseException(
                "Unknown source map version:" + version);
        }
      } catch (JSONException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[9]++;
        throw new SourceMapParseException("JSON parse exception: " + ex);
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[5]++;
}
  }

    } else {
  CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd.branches[4]++;}
}

    throw new SourceMapParseException("unable to detect source map format");
  }
}

class CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[10];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapConsumerFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$7ht8rjqgreifqpwefgypo999vxeq0t2tbqw9vdw8helpd () {
    super("com.google.debugging.sourcemap.SourceMapConsumerFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 9; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapConsumerFactory.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 9; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

