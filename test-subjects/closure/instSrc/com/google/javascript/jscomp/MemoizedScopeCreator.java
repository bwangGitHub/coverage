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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.StaticSymbolTable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Memoize a scope creator.
 *
 * This allows you to make multiple passes, without worrying about
 * the expense of generating Scope objects over and over again.
 *
 * On the other hand, you also have to be more aware of what your passes
 * are doing. Scopes are memoized stupidly, so if the underlying tree
 * changes, the scope may be out of sync.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class MemoizedScopeCreator
    implements ScopeCreator, StaticSymbolTable<Var, Var> {
  static {
    CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.ping();
  }


  private final Map<Node, Scope> scopes = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[1]++;
  }
  private final ScopeCreator delegate;

  /**
   * @param delegate The real source of Scope objects.
   */
  MemoizedScopeCreator(ScopeCreator delegate) {
    this.delegate = delegate;
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[2]++;
  }

  @Override
  public Iterable<Var> getReferences(Var var) {
    return ImmutableList.of(var);
  }

  @Override
  public Scope getScope(Var var) {
    return var.scope;
  }

  @Override
  public Iterable<Var> getAllSymbols() {
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[3]++;
    List<Var> vars = Lists.newArrayList();
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[1]++;


    for (Scope s : scopes.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[1]--;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[2]--;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[3]++;
}
      Iterables.addAll(vars, s.getAllSymbols());
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[5]++;
    }
    return vars;
  }

  @Override
  public Scope createScope(Node n, Scope parent) {
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[6]++;
    Scope scope = scopes.get(n);
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.branches[1]++;
      scope = delegate.createScope(n, parent);
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[8]++;
      scopes.put(n, scope);
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[9]++;

    } else {
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.branches[2]++;
      Preconditions.checkState(parent == scope.getParent());
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[10]++;
    }
    return scope;
  }

  Collection<Scope> getAllMemoizedScopes() {
    return Collections.unmodifiableCollection(scopes.values());
  }

  Scope getScopeIfMemoized(Node n) {
    return scopes.get(n);
  }

  /**
   * Removes all scopes with root nodes from a given script file.
   *
   * @param scriptName the name of the script file to remove nodes for.
   */
  void removeScopesForScript(String scriptName) {
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[11]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[4]++;


    for (Node scopeRoot : ImmutableSet.copyOf(scopes.keySet())) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[4]--;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[5]--;
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.loops[6]++;
}
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((scriptName.equals(scopeRoot.getSourceFileName())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.branches[3]++;
        scopes.remove(scopeRoot);
CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.statements[13]++;

      } else {
  CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh.branches[4]++;}
    }
  }
}

class CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MemoizedScopeCreator.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$3iuk0izj0yh7zpn5lvzquvbolh1f7kw0ezqm7oh () {
    super("com.google.javascript.jscomp.MemoizedScopeCreator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MemoizedScopeCreator.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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
      for (int i = 1; i <= 2; i++) {
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

