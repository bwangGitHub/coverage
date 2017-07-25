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

import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.javascript.rhino.Node;

/**
 * This describes the jQuery specific JavaScript coding conventions.
 */
public class JqueryCodingConvention extends CodingConventions.Proxy {
  static {
    CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[1]++;
  }

  public JqueryCodingConvention() {
    this(CodingConventions.getDefault());
CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[2]++;
  }

  public JqueryCodingConvention(CodingConvention wrapped) {
    super(wrapped);
CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[3]++;
  }

  @Override
  public String getGlobalObject() {
    return "window";
  }

  private final static Set<String> propertyTestFunctions = ImmutableSet.of(
      "jQuery.isPlainObject", "jQuery.isFunction", "jQuery.isNumeric",
      "jQuery.isEmptyObject");
  static {
    CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[4]++;
  }

  @Override
  public boolean isPropertyTestFunction(Node call) {
    Preconditions.checkArgument(call.isCall());
CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[5]++;
    return propertyTestFunctions.contains(
        call.getFirstChild().getQualifiedName());
  }

  private final static Set<String> prototypeAliases = ImmutableSet.of(
      "jQuery.fn", "jQuerySub.fn");
  static {
    CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[6]++;
  }

  @Override
  public boolean isPrototypeAlias(Node getProp) {
    Preconditions.checkArgument(getProp.isGetProp());
CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t.statements[7]++;
    return prototypeAliases.contains(getProp.getQualifiedName());
  }
}

class CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$4rdqhyxiigrhaallktztq3dzts20zc4h74xq1qki1t () {
    super("com.google.javascript.jscomp.JqueryCodingConvention.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.JqueryCodingConvention.java");
      for (int i = 1; i <= 7; i++) {
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

