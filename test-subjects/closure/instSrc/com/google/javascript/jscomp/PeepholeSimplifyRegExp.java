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

import com.google.javascript.jscomp.regex.RegExpTree;
import com.google.javascript.rhino.Node;

/**
 * Simplifies regular expression patterns and flags.
 *
 * @author Mike Samuel <mikesamuel@gmail.com>
 */
class PeepholeSimplifyRegExp extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.ping();
  }


  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((subtree.isRegExp()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[1]++;
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[2]++;
      // Split regexp into pattern and flags.
      String pattern = subtree.getFirstChild().getString();
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[3]++;
      String flags = subtree.getChildCount() == 2
          ? subtree.getLastChild().getString() : "";
      // Parse to an AST and optimize.
      RegExpTree regexTree;
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[4]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        regexTree = RegExpTree.parseRegExp(pattern, flags);
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[5]++;
      } catch (IllegalArgumentException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[4]++;
        // Warnings are propagated in the CheckRegExp pass.
        return subtree;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[3]++;
}
  }
      regexTree = regexTree.simplify(flags);
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[6]++;
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[7]++;
      // Decompose the AST.
      String literal = regexTree.toString();
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[8]++;
      String newPattern = literal.substring(1, literal.length() - 1);
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[9]++;
      // Remove unnecessary flags and order them consistently for gzip.
      String newFlags = (
          // The g flags cannot match or replace more than one instance if it is
          // anchored at the front and back as in /^foo$/ and if the anchors are
          // relative to the whole string.
          // But if the regex has capturing groups, then the match operator
          // would return capturing groups without the g flag.
          (flags.contains("g")
           && (!RegExpTree.matchesWholeInput(regexTree, flags)
               || regexTree.hasCapturingGroup())
           ? "g" : "")
          // Remove the i flag if it doesn't have any effect.
          // E.g. /[a-z0-9_]/i -> /\w/
          + (flags.contains("i") && regexTree.isCaseSensitive() ? "i" : "")
          // If the regular expression contains no anchors, then the m flag has
          // no effect.
          + (flags.contains("m") && regexTree.containsAnchor() ? "m" : ""));
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
      // Update the original if something was done.
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((newPattern.equals(pattern)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((newFlags.equals(flags)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[5]++;
        subtree.getFirstChild().setString(newPattern);
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[11]++;
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 (("".equals(newFlags)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[7]++;
          subtree.getLastChild().setString(newFlags);
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[13]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[8]++;
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[14]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((subtree.getChildCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[9]++;
          subtree.getLastChild().detachFromParent();
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[15]++;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[10]++;}
}
        reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.statements[16]++;

      } else {
  CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5.branches[2]++;}
    return subtree;
  }
}

class CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5 ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeSimplifyRegExp.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1};
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

  public CodeCoverCoverageCounter$5533np9o0t6kft267a2pr8irou4651bzfjk8s2m5b5 () {
    super("com.google.javascript.jscomp.PeepholeSimplifyRegExp.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.PeepholeSimplifyRegExp.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
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

