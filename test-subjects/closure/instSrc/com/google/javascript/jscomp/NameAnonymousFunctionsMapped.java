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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.*;
import java.util.logging.*;

/**
 * Gives anonymous function names that are optimized to be small and provides a
 * mapping back to the original names. This makes it way easier to debug because
 * debuggers and stack traces use the function names. So if you have
 *
 * goog.string.htmlEscape = function(str) {
 * }
 *
 * It will become
 *
 * goog.string.htmlEscape = function $qv(str) {
 * }
 *
 * And there will be mapping from $qv to goog.string.htmlEscape
 *
 */
class NameAnonymousFunctionsMapped implements CompilerPass {
  static {
    CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.ping();
  }


  private static Logger logger = Logger.getLogger(
      NameAnonymousFunctionsMapped.class.getName());
  static {
    CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[1]++;
  }

  static final char PREFIX = '$';
  static {
    CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[2]++;
  }
  static final String PREFIX_STRING = "$";
  static {
    CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[3]++;
  }

  private final AbstractCompiler compiler;
  private final NameGenerator nameGenerator;
  private final VariableMap previousMap;
  private final Map<String, String> renameMap;

  private int namedCount = 0;
  {
    CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[4]++;
  }
  private int bytesUsed = 0;
  {
    CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[5]++;
  }

  NameAnonymousFunctionsMapped(
      AbstractCompiler compiler, VariableMap previousMap) {
    this.compiler = compiler;
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[6]++;
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[7]++;
    Set<String> reserved =
        previousMap != null ?
            previousMap.getNewNameToOriginalNameMap().keySet() :
            Collections.<String>emptySet();
    this.nameGenerator = new NameGenerator(reserved, PREFIX_STRING, null);
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[8]++;
    this.previousMap = previousMap;
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[9]++;
    this.renameMap = Maps.newHashMap();
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[10]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[11]++;
    AnonymousFunctionNamingCallback namingCallback =
        new AnonymousFunctionNamingCallback(new MappedFunctionNamer());
    NodeTraversal.traverse(compiler, root, namingCallback);
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[12]++;
    logger.fine("Named " + namedCount + " anon functions using " +
        bytesUsed + " bytes");
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[13]++;
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((namedCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[1]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[15]++;

    } else {
  CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[2]++;}
  }

  /**
   * Names anonymous functions. The function names don't have to be globally
   * unique or even locally unique. We make them somewhat unique because of a
   * bug in IE (and there may be other bugs we haven't found). See unit test for
   * more info.
   */
  private class MappedFunctionNamer
      implements AnonymousFunctionNamingCallback.FunctionNamer {
    static final char DELIMITER = '.';

    @Override
    public final String getName(Node node) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[16]++;
      switch (node.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[3]++;
        case Token.STRING:
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[4]++;
        case Token.STRING_KEY:
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[5]++;
          return node.getString();
        default:
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[6]++;
          return new CodePrinter.Builder(node).build();
      }
    }

    @Override
    public final void setFunctionName(String name, Node fnNode) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[17]++;
      Node fnNameNode = fnNode.getFirstChild();
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[18]++;
      String newName = getAlternateName(name);
      fnNameNode.setString(newName);
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[19]++;
      namedCount++;
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[20]++;
      bytesUsed += newName.length();
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[21]++;
    }

    String getAlternateName(String name) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[22]++;
      String newName = renameMap.get(name);
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((newName == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[7]++;
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        // Use the previously used name, if possible.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((previousMap != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[9]++;
          newName = previousMap.lookupNewName(name);
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[25]++;

        } else {
  CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[10]++;}
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((newName == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[11]++;
          // otherwise generate a new name.
          newName = nameGenerator.generateNextName();
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[27]++;

        } else {
  CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[12]++;}
        renameMap.put(name, newName);
CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.statements[28]++;

      } else {
  CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep.branches[8]++;}
      return newName;
    }

    @Override
    public final String getCombinedName(String lhs, String rhs) {
      return lhs + DELIMITER + rhs;
    }
  }

  /**
   * Gets the function renaming map (the "answer key").
   *
   * @return A mapping from original names to new names
   */
  VariableMap getFunctionMap() {
    return new VariableMap(ImmutableMap.copyOf(renameMap));
  }
}

class CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NameAnonymousFunctionsMapped.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$dw3xghnqtp6g5rowl6giq4dorxf66dr3khany3mh73pvsc8mtep () {
    super("com.google.javascript.jscomp.NameAnonymousFunctionsMapped.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameAnonymousFunctionsMapped.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

