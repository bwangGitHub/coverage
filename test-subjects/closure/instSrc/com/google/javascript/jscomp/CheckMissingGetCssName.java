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

import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ensures string literals matching certain patterns are only used as
 * goog.getCssName parameters.
 *
 * @author mkretzschmar@google.com (Martin Kretzschmar)
 */
class CheckMissingGetCssName
    extends AbstractPostOrderCallback implements CompilerPass {
  static {
    CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.ping();
  }

  private final AbstractCompiler compiler;
  private final CheckLevel level;
  private final Matcher blacklist;

  static final String GET_CSS_NAME_FUNCTION = "goog.getCssName";
  static {
    CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[1]++;
  }
  static final String GET_UNIQUE_ID_FUNCTION = ".getUniqueId";
  static {
    CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[2]++;
  }

  static final DiagnosticType MISSING_GETCSSNAME =
      DiagnosticType.disabled(
          "JSC_MISSING_GETCSSNAME",
          "missing goog.getCssName around literal ''{0}''");
  static {
    CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[3]++;
  }

  CheckMissingGetCssName(AbstractCompiler compiler, CheckLevel level,
      String blacklistRegex) {
    this.compiler = compiler;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[4]++;
    this.level = level;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[5]++;
    this.blacklist =
        Pattern.compile("\\b(?:" + blacklistRegex + ")").matcher("");
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[7]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((n.isString()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent.isRegExp()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[1]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[9]++;
      String s = n.getString();
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

      for (blacklist.reset(s);(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((blacklist.find()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.loops[1]--;
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.loops[2]--;
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.loops[3]++;
}
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((insideGetCssNameCall(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[3]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[12]++;
          continue;

        } else {
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[4]++;}
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((insideGetUniqueIdCall(n)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[5]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[14]++;
          continue;

        } else {
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[6]++;}
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((insideAssignmentToIdConstant(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[7]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[16]++;
          continue;

        } else {
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[8]++;}
        compiler.report(t.makeError(n, level, MISSING_GETCSSNAME,
                blacklist.group()));
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[17]++;
      }

    } else {
  CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[2]++;}
  }

  /** Returns whether the node is an argument of a goog.getCssName call. */
  private boolean insideGetCssNameCall(Node n) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[18]++;
    Node parent = n.getParent();
    return parent.isCall() &&
        GET_CSS_NAME_FUNCTION.equals(
            parent.getFirstChild().getQualifiedName());
  }

  /**
   * Returns whether the node is an argument of a function that returns
   * a unique id (the last part of the qualified name matches
   * GET_UNIQUE_ID_FUNCTION).
   */
  private boolean insideGetUniqueIdCall(Node n) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[19]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[20]++;
    String name = parent.isCall() ?
        parent.getFirstChild().getQualifiedName() : null;

    return name != null && name.endsWith(GET_UNIQUE_ID_FUNCTION);
  }

  /**
   * Returns whether the node is the right hand side of an assignment or
   * initialization of a variable named *_ID of *_ID_.
   */
  private boolean insideAssignmentToIdConstant(Node n) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[21]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[9]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[23]++;
      String qname = parent.getFirstChild().getQualifiedName();
      return qname != null && isIdName(qname);

    } else {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[10]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[24]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[11]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[25]++;
      Node grandParent = parent.getParent();
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((grandParent != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((grandParent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[13]++;
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.statements[27]++;
        String name = parent.getString();
        return isIdName(name);

      } else {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[14]++;
        return false;
      }

    } else {
CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5.branches[12]++;
      return false;
    }
}
  }

  private boolean isIdName(String name) {
    return name.endsWith("ID") || name.endsWith("ID_");
  }
}

class CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5 ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckMissingGetCssName.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1,2};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$4b6nidkgr6vd9xhxz0hbmkz2tdjmgoar0mhgsjwrj5 () {
    super("com.google.javascript.jscomp.CheckMissingGetCssName.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckMissingGetCssName.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

