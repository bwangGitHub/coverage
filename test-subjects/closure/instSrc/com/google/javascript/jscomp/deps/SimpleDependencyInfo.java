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

package com.google.javascript.jscomp.deps;

import com.google.common.base.Objects;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A class to hold JS dependency information for a single .js file.
 *
 * @author agrieve@google.com (Andrew Grieve)
 */
public class SimpleDependencyInfo implements DependencyInfo {
  static {
    CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.ping();
  }


  /** A list of provided symbols. */
  private final List<String> provides;

  /** A list of required symbols. */
  private final List<String> requires;

  /** The path of the file relative to closure. */
  private final String srcPathRelativeToClosure;

  /** The path to the file from which we extracted the dependency information.*/
  private final String pathOfDefiningFile;

  /**
   * Constructs a DependencyInfo object with the given list of provides &
   * requires. This does *not* copy the given lists, but uses them directly.
   *
   * @param srcPathRelativeToClosure The closure-relative path of the file
   *     associated with this DependencyInfo.
   * @param pathOfDefiningFile The path to the file from which this dependency
   *     information was extracted.
   * @param provides List of provided symbols.
   * @param requires List of required symbols.
   */
  public SimpleDependencyInfo(
      String srcPathRelativeToClosure, String pathOfDefiningFile,
      List<String> provides, List<String> requires) {
    this.srcPathRelativeToClosure = srcPathRelativeToClosure;
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.statements[1]++;
    this.pathOfDefiningFile = pathOfDefiningFile;
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.statements[2]++;
    this.provides = provides;
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.statements[3]++;
    this.requires = requires;
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.statements[4]++;
  }

  @Override
  public String getName() {
    return pathOfDefiningFile;
  }

  @Override
  public String getPathRelativeToClosureBase() {
    return srcPathRelativeToClosure;
  }

  @Override
  public Collection<String> getProvides() {
    return Collections.<String>unmodifiableList(provides);
  }

  @Override
  public Collection<String> getRequires() {
    return Collections.<String>unmodifiableList(requires);
  }

  @Override
  public boolean equals(Object obj) {
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj instanceof SimpleDependencyInfo) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.branches[2]++;}
CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t.statements[6]++;
    SimpleDependencyInfo other = (SimpleDependencyInfo)obj;
    return Objects.equal(other.srcPathRelativeToClosure,
            srcPathRelativeToClosure) &&
        Objects.equal(other.pathOfDefiningFile, pathOfDefiningFile) &&
        Objects.equal(other.requires, this.requires) &&
        Objects.equal(other.provides, this.provides);
  }

  @Override
  public String toString() {
    return String.format("DependencyInfo(relativePath='%1$s', path='%2$s', "
        + "provides=%3$s, requires=%4$s)", srcPathRelativeToClosure,
        pathOfDefiningFile, provides, requires);
  }
}

class CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.SimpleDependencyInfo.java";
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

  public CodeCoverCoverageCounter$3sphpf8xzgse3fpqtuqv5h7ht4emgi05y4c5l5t () {
    super("com.google.javascript.jscomp.deps.SimpleDependencyInfo.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.deps.SimpleDependencyInfo.java");
      for (int i = 1; i <= 6; i++) {
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

