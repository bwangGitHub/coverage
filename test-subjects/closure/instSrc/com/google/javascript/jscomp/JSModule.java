/*
 * Copyright 2005 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.deps.DependencyInfo;
import com.google.javascript.jscomp.deps.SortedDependencies;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A JavaScript module has a unique name, consists of a list of compiler inputs,
 * and can depend on other modules.
 *
 */
public class JSModule implements DependencyInfo, Serializable {
  static {
    CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.ping();
  }

  private static final long serialVersionUID = 1;
  static {
    CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[1]++;
  }

  static final DiagnosticType CIRCULAR_DEPENDENCY_ERROR =
      DiagnosticType.error("JSC_CIRCULAR_DEP",
          "Circular dependency detected: {0}");
  static {
    CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[2]++;
  }

  /** Module name */
  private final String name;

  /** Source code inputs */
  private final List<CompilerInput> inputs = new ArrayList<CompilerInput>();
  {
    CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[3]++;
  }

  /** Modules that this module depends on */
  private final List<JSModule> deps = new ArrayList<JSModule>();
  {
    CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[4]++;
  }

  private int depth;
  /**
   * Creates an instance.
   *
   * @param name A unique name for the module
   */
  public JSModule(String name) {
    this.name = name;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[5]++;
    this.depth = -1;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[6]++;
  }

  /** Gets the module name. */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<String> getProvides() {
    return ImmutableList.<String>of(name);
  }

  @Override
  public List<String> getRequires() {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[7]++;
    ImmutableList.Builder<String> builder = ImmutableList.builder();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[1]++;


    for (JSModule m : deps) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[1]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[2]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[3]++;
}
      builder.add(m.getName());
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[9]++;
    }
    return builder.build();
  }

  @Override
  public String getPathRelativeToClosureBase() {
    throw new UnsupportedOperationException();
  }

  /** Adds a source file input to this module. */
  public void add(SourceFile file) {
    add(new CompilerInput(file));
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[10]++;
  }

  /** Adds a source file input to this module. */
  public void addFirst(SourceFile file) {
    addFirst(new CompilerInput(file));
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[11]++;
  }

  /** Adds a source code input to this module. */
  public void add(CompilerInput input) {
    inputs.add(input);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[12]++;
    input.setModule(this);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[13]++;
  }

  /**
   * Adds a source code input to this module. Call only if the input might
   * already be associated with a module. Otherwise, use
   * add(CompilerInput input).
   */
  void addAndOverrideModule(CompilerInput input) {
    inputs.add(input);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[14]++;
    input.overrideModule(this);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[15]++;
  }

  /** Adds a source code input to this module. */
  public void addFirst(CompilerInput input) {
    inputs.add(0, input);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[16]++;
    input.setModule(this);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[17]++;
  }

  /** Adds a source code input to this module directly after other. */
  public void addAfter(CompilerInput input, CompilerInput other) {
    Preconditions.checkState(inputs.contains(other));
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[18]++;
    inputs.add(inputs.indexOf(other), input);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[19]++;
    input.setModule(this);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[20]++;
  }

  /** Adds a dependency on another module. */
  public void addDependency(JSModule dep) {
    Preconditions.checkNotNull(dep);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[21]++;
    Preconditions.checkState(dep != this);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[22]++;
    deps.add(dep);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[23]++;
  }

  /** Removes an input from this module. */
  public void remove(CompilerInput input) {
    input.setModule(null);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[24]++;
    inputs.remove(input);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[25]++;
  }

  /** Removes all of the inputs from this module. */
  public void removeAll() {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[4]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[4]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[5]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[6]++;
}
      input.setModule(null);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[27]++;
    }
    inputs.clear();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[28]++;
  }

  /**
   * Gets the list of modules that this module depends on.
   *
   * @return A list that may be empty but not null
   */
  public List<JSModule> getDependencies() {
    return deps;
  }

  /**
   * Gets the names of the modules that this module depends on,
   * sorted alphabetically.
   */
  List<String> getSortedDependencyNames() {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[29]++;
    List<String> names = Lists.newArrayList();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[30]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[7]++;


    for (JSModule module : getDependencies()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[7]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[8]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[9]++;
}
      names.add(module.getName());
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[31]++;
    }
    Collections.sort(names);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[32]++;
    return names;
  }

  /**
   * Returns the transitive closure of dependencies starting from the
   * dependencies of this module.
   */
  public Set<JSModule> getAllDependencies() {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[33]++;
    Set<JSModule> allDeps = Sets.newHashSet(deps);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[34]++;
    List<JSModule> workList = Lists.newArrayList(deps);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[35]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[10]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((workList.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[10]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[11]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[12]++;
}
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[36]++;
      JSModule module = workList.remove(workList.size() - 1);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[37]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[13]++;


      for (JSModule dep : module.getDependencies()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[13]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[14]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[15]++;
}
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[38]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((allDeps.add(dep)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[1]++;
          workList.add(dep);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[39]++;

        } else {
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[2]++;}
      }
    }
    return allDeps;
  }

  /** Returns this module and all of its dependencies in one list. */
  public Set<JSModule> getThisAndAllDependencies() {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[40]++;
    Set<JSModule> deps = getAllDependencies();
    deps.add(this);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[41]++;
    return deps;
  }

  /**
   * Gets this module's list of source code inputs.
   *
   * @return A list that may be empty but not null
   */
  public List<CompilerInput> getInputs() {
    return inputs;
  }

  /** Returns the input with the given name or null if none. */
  public CompilerInput getByName(String name) {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[42]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[16]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[16]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[17]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[18]++;
}
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[43]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((name.equals(input.getName())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[3]++;
        return input;

      } else {
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[4]++;}
    }
    return null;
  }

  /**
   * Removes any input with the given name. Returns whether any were removed.
   */
  public boolean removeByName(String name) {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[44]++;
    boolean found = false;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[45]++;
    Iterator<CompilerInput> iter = inputs.iterator();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[46]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[19]++;


int CodeCoverConditionCoverageHelper_C4;
    while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[19]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[20]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[21]++;
}
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[47]++;
      CompilerInput file = iter.next();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[48]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name.equals(file.getName())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[5]++;
        iter.remove();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[49]++;
        file.setModule(null);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[50]++;
        found = true;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[51]++;

      } else {
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[6]++;}
    }
    return found;
  }

  /** Returns the module name (primarily for debugging). */
  @Override
  public String toString() {
    return name;
  }

  /**
   * Removes any references to nodes of the AST.  This method is needed to
   * allow the ASTs to be garbage collected if the modules are kept around.
   */
  public void clearAsts() {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[52]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[22]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[22]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[23]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[24]++;
}
      input.clearAst();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[53]++;
    }
  }

  /**
   * Puts the JS files into a topologically sorted order by their dependencies.
   */
  public void sortInputsByDeps(Compiler compiler) {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[54]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[25]++;


    // Set the compiler, so that we can parse requires/provides and report
    // errors properly.
    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[25]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[26]--;
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.loops[27]++;
}
      input.setCompiler(compiler);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[55]++;
    }
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[56]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

    // Sort the JSModule in this order.
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[57]++;
      List<CompilerInput> sortedList =
          (new SortedDependencies<CompilerInput>(
              Collections.<CompilerInput>unmodifiableList(inputs)))
          .getSortedList();
      inputs.clear();
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[58]++;
      inputs.addAll(sortedList);
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[59]++;
    } catch (CircularDependencyException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[8]++;
      compiler.report(
          JSError.make(CIRCULAR_DEPENDENCY_ERROR, e.getMessage()));
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[60]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.branches[7]++;
}
  }
  }

  /**
   * Returns the given collection of modules in topological order.
   *
   * Note that this will return the modules in the same order if they are
   * already sorted, and in general, will only change the order as necessary to
   * satisfy the ordering dependencies.  This can be important for cases where
   * the modules do not properly specify all dependencies.
   */
  public static JSModule[] sortJsModules(Collection<JSModule> modules)
      throws CircularDependencyException {
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[61]++;
    // Sort the JSModule in this order.
    List<JSModule> sortedList = (new SortedDependencies<JSModule>(
            Lists.newArrayList(modules))).getSortedList();
    return sortedList.toArray(new JSModule[sortedList.size()]);
  }

  /**
   * @param dep the depth to set
   */
  public void setDepth(int dep) {
    this.depth = dep;
CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5.statements[62]++;
  }

  /**
   * @return the depth
   */
  public int getDepth() {
    return depth;
  }
}

class CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5 ());
  }
    public static long[] statements = new long[63];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JSModule.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$fuwyh4u8lzeluzlrvdr5 () {
    super("com.google.javascript.jscomp.JSModule.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 62; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JSModule.java");
      for (int i = 1; i <= 62; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

