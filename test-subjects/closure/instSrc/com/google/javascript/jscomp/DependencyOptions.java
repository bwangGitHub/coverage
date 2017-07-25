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

import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Options for how to manage dependencies between input files.
 *
 * Dependency information is usually pulled out from the JS code by
 * looking for primitive dependency functions (like Closure Library's
 * goog.provide/goog.require). Analysis of this dependency information is
 * controlled by {@code CodingConvention}, which lets you define those
 * dependency primitives.
 *
 * This options class determines how we use that dependency information
 * to change how code is built.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public class DependencyOptions implements Serializable {
  static {
    CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[1]++;
  }

  private boolean sortDependencies = false;
  {
    CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[2]++;
  }
  private boolean pruneDependencies = false;
  {
    CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[3]++;
  }
  private boolean dropMoochers = false;
  {
    CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[4]++;
  }
  private final Set<String> entryPoints = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[5]++;
  }

  /**
   * Enables or disables dependency sorting mode.
   *
   * If true, we will sort the input files based on dependency information
   * in them. Otherwise, we will use the order of files specified
   * on the command-line.
   * @return this for easy building.
   */
  public DependencyOptions setDependencySorting(boolean enabled) {
    this.sortDependencies = enabled;
CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[6]++;
    return this;
  }

  /**
   * Enables or disables dependency pruning mode.
   *
   * In dependency pruning mode, we will look for all files that provide a
   * symbol. Unless that file is a transitive dependency of a file that
   * we're using, we will remove it from the compilation job.
   *
   * This does not affect how we handle files that do not provide symbols.
   * See setMoocherDropping for information on how these are handled.
   *
   * @return this for easy chaining.
   */
  public DependencyOptions setDependencyPruning(boolean enabled) {
    this.pruneDependencies = enabled;
CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[7]++;
    return this;
  }

  /**
   * Enables or disables moocher dropping mode.
   *
   * A 'moocher' is a file that does not provide any symbols (though they
   * may require symbols). This is usually because they don't want to
   * tie themselves to a particular dependency system (e.g., Closure's
   * goog.provide, CommonJS modules). So they rely on other people to
   * manage dependencies on them.
   *
   * If true, we drop these files when we prune dependencies.
   * If false, we always keep these files an anything they depend on.
   * The default is false.
   *
   * Notice that this option only makes sense if dependency pruning is on,
   * and a set of entry points is specified.
   *
   * @return this for easy chaining.
   */
  public DependencyOptions setMoocherDropping(boolean enabled) {
    this.dropMoochers = enabled;
CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[8]++;
    return this;
  }

  /**
   * Adds a collection of symbols to always keep.
   *
   * In dependency pruning mode, we will automatically keep all the
   * transitive dependencies of these symbols.
   *
   * The syntactic form of a symbol depends on the type of dependency
   * primitives we're using. For example, goog.provide('foo.bar')
   * provides the symbol 'foo.bar'.
   *
   * @return this for easy chaining.
   */
  public DependencyOptions setEntryPoints(Collection<String> symbols) {
    entryPoints.clear();
CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[9]++;
    entryPoints.addAll(symbols);
CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9.statements[10]++;
    return this;
  }

  /** Returns whether re-ordering of files is needed. */
  boolean needsManagement() {
    return sortDependencies || pruneDependencies;
  }

  boolean shouldSortDependencies() {
    return sortDependencies;
  }

  boolean shouldPruneDependencies() {
    return pruneDependencies;
  }

  boolean shouldDropMoochers() {
    return pruneDependencies && dropMoochers;
  }

  Collection<String> getEntryPoints() {
    return entryPoints;
  }
}

class CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$b80c8i1br35r0ufb7o353nwyti5yuho4m9 () {
    super("com.google.javascript.jscomp.DependencyOptions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.DependencyOptions.java");
      for (int i = 1; i <= 10; i++) {
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

