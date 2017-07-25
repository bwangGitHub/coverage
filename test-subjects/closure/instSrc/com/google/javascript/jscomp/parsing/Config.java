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

package com.google.javascript.jscomp.parsing;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Set;

/**
 * Configuration for the AST factory. Should be shared across AST creation
 * for all files of a compilation process.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public class Config {
  static {
    CodeCoverCoverageCounter$a8rj0197qvj26sr4x.ping();
  }


  public enum LanguageMode {
    ECMASCRIPT3,
    ECMASCRIPT5,
    ECMASCRIPT5_STRICT,
  }

  /**
   * Whether to parse the descriptions of JsDoc comments.
   */
  final boolean parseJsDocDocumentation;

  /**
   * Whether we're in IDE mode.
   */
  final boolean isIdeMode;

  /**
   * Recognized JSDoc annotations, mapped from their name to their internal
   * representation.
   */
  final Map<String, Annotation> annotationNames;

  /**
   * Recognized names in a {@code @suppress} tag.
   */
  final Set<String> suppressionNames;

  /**
   * Accept ECMAScript5 syntax, such as getter/setter.
   */
  final LanguageMode languageMode;

  /**
   * Accept `const' keyword.
   */
  final boolean acceptConstKeyword;

  /**
   * Annotation names.
   */

  Config(Set<String> annotationWhitelist, Set<String> suppressionNames,
      boolean isIdeMode, LanguageMode languageMode,
      boolean acceptConstKeyword) {
    this.annotationNames = buildAnnotationNames(annotationWhitelist);
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[1]++;
    this.parseJsDocDocumentation = isIdeMode;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[2]++;
    this.suppressionNames = suppressionNames;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[3]++;
    this.isIdeMode = isIdeMode;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[4]++;
    this.languageMode = languageMode;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[5]++;
    this.acceptConstKeyword = acceptConstKeyword;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[6]++;
  }

  /**
   * Create the annotation names from the user-specified
   * annotation whitelist.
   */
  private static Map<String, Annotation> buildAnnotationNames(
      Set<String> annotationWhitelist) {
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[7]++;
    ImmutableMap.Builder<String, Annotation> annotationBuilder =
        ImmutableMap.builder();
    annotationBuilder.putAll(Annotation.recognizedAnnotations);
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[8]++;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.loops[1]++;


    for (String unrecognizedAnnotation : annotationWhitelist) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$a8rj0197qvj26sr4x.loops[1]--;
  CodeCoverCoverageCounter$a8rj0197qvj26sr4x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$a8rj0197qvj26sr4x.loops[2]--;
  CodeCoverCoverageCounter$a8rj0197qvj26sr4x.loops[3]++;
}
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Annotation.recognizedAnnotations.containsKey(
              unrecognizedAnnotation)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a8rj0197qvj26sr4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$a8rj0197qvj26sr4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.branches[1]++;
        annotationBuilder.put(
            unrecognizedAnnotation, Annotation.NOT_IMPLEMENTED);
CodeCoverCoverageCounter$a8rj0197qvj26sr4x.statements[11]++;

      } else {
  CodeCoverCoverageCounter$a8rj0197qvj26sr4x.branches[2]++;}
    }
    return annotationBuilder.build();
  }
}

class CodeCoverCoverageCounter$a8rj0197qvj26sr4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$a8rj0197qvj26sr4x ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.parsing.Config.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$a8rj0197qvj26sr4x () {
    super("com.google.javascript.jscomp.parsing.Config.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.Config.java");
      for (int i = 1; i <= 11; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

