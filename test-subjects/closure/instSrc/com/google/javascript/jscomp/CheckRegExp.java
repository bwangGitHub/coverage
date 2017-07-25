/*
 * Copyright 2010 The Closure Compiler Authors.
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

import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.regex.RegExpTree;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.Node;

/**
 * Look for references to the global RegExp object that would cause
 * regular expressions to be unoptimizable, and checks that regular expressions
 * are syntactically valid.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class CheckRegExp extends AbstractPostOrderCallback implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.ping();
  }


  static final DiagnosticType REGEXP_REFERENCE =
    DiagnosticType.warning("JSC_REGEXP_REFERENCE",
        "References to the global RegExp object prevents " +
        "optimization of regular expressions.");
  static {
    CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[1]++;
  }
  static final DiagnosticType MALFORMED_REGEXP = DiagnosticType.warning(
        "JSC_MALFORMED_REGEXP",
        "Malformed Regular Expression: {0}");
  static {
    CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[2]++;
  }

  private final AbstractCompiler compiler;
  private boolean globalRegExpPropertiesUsed = false;
  {
    CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[3]++;
  }

  public boolean isGlobalRegExpPropertiesUsed() {
    return globalRegExpPropertiesUsed;
  }

  public CheckRegExp(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[5]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((NodeUtil.isReferenceName(n)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[1]++;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[7]++;
      String name = n.getString();
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((name.equals("RegExp")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((t.getScope().getVar(name) == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[3]++;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[9]++;
        int parentType = parent.getType();
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[10]++;
        boolean first = (n == parent.getFirstChild());
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!((
(((CodeCoverConditionCoverageHelper_C3 |= (2048)) == 0 || true) &&
 ((parentType == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (512)) == 0 || true) &&
 ((first) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C3 |= (128)) == 0 || true) &&
 ((parentType == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((first) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parentType == Token.INSTANCEOF) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((first) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 6) || true)) || (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 6) && false)) {
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[5]++;
          t.report(n, REGEXP_REFERENCE);
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[12]++;
          globalRegExpPropertiesUsed = true;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[13]++;

        } else {
  CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[6]++;}

      } else {
  CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[4]++;}


    // Check the syntax of regular expression patterns.
    } else {
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[2]++;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[14]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isRegExp()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[7]++;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[15]++;
      String pattern = n.getFirstChild().getString();
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[16]++;
      String flags = n.getChildCount() == 2
          ? n.getLastChild().getString() : "";
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[17]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        RegExpTree.parseRegExp(pattern, flags);
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[18]++;
      } catch (IllegalArgumentException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[10]++;
        t.report(n, MALFORMED_REGEXP, ex.getMessage());
CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.statements[19]++;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[9]++;
}
  }

    } else {
  CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t.branches[8]++;}
}
  }
}

class CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckRegExp.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,3,1};
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

  public CodeCoverCoverageCounter$3znybighrbxj5ze3mg7jlj61t () {
    super("com.google.javascript.jscomp.CheckRegExp.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.CheckRegExp.java");
      for (int i = 1; i <= 19; i++) {
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

