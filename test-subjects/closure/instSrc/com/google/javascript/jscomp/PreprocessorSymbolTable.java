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

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.SimpleReference;
import com.google.javascript.rhino.jstype.SimpleSlot;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSymbolTable;

import java.util.Collections;
import java.util.Map;

/**
 * A symbol table for references that are removed by preprocessor passes
 * (like {@code ProcessClosurePrimitives}).
 *
 * @author nicksantos@google.com (Nick Santos)
 */
final class PreprocessorSymbolTable
    implements StaticScope<JSType>,
               StaticSymbolTable<SimpleSlot,
                                 PreprocessorSymbolTable.Reference> {
  static {
    CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.ping();
  }


  /**
   * All preprocessor symbols are globals.
   */
  private final Map<String, SimpleSlot> symbols = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[1]++;
  }

  private final Multimap<String, Reference> refs =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[2]++;
  }

  private final Node root;

  PreprocessorSymbolTable(Node root) {
    this.root = root;
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[3]++;
  }

  @Override
  public Node getRootNode() { return root; }

  @Override
  public JSType getTypeOfThis() { return null; }

  @Override
  public StaticScope<JSType> getParentScope() { return null; }

  @Override
  public SimpleSlot getSlot(String name) {
    return symbols.get(name);
  }

  @Override
  public SimpleSlot getOwnSlot(String name) {
    return getSlot(name);
  }

  @Override
  public Iterable<Reference> getReferences(SimpleSlot symbol) {
    return Collections.unmodifiableCollection(refs.get(symbol.getName()));
  }

  @Override
  public Iterable<SimpleSlot> getAllSymbols() {
    return Collections.unmodifiableCollection(symbols.values());
  }

  @Override
  public StaticScope<JSType> getScope(SimpleSlot slot) {
    return this;
  }

  void addReference(Node node) {
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[4]++;
    String name = node.getQualifiedName();
    Preconditions.checkNotNull(name);
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[5]++;
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((symbols.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.branches[1]++;
      symbols.put(name, new SimpleSlot(name, null, true));
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[7]++;

    } else {
  CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.branches[2]++;}

    refs.put(name, new Reference(symbols.get(name), node));
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[8]++;
  }

  static final class Reference extends SimpleReference<SimpleSlot> {
    Reference(SimpleSlot symbol, Node node) {
      super(symbol, node);
CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1.statements[9]++;
    }
  }
}

class CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1 ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PreprocessorSymbolTable.java";
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

  public CodeCoverCoverageCounter$10kzzfnpjkbocd1eraquixji0nj8v34xxl0uxqtq9co1 () {
    super("com.google.javascript.jscomp.PreprocessorSymbolTable.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.PreprocessorSymbolTable.java");
      for (int i = 1; i <= 9; i++) {
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

