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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Rewrites
 * <code>new JSCompiler_ObjectPropertyString(window, foo.prototype.bar)</code>
 * to <code>new JSCompiler_ObjectPropertyString(foo.prototype, 'bar')</code>
 *
 * Rewrites
 * <code>new JSCompiler_ObjectPropertyString(window, foo[bar])</code>
 * to <code>new JSCompiler_ObjectPropertyString(foo, bar)</code>

 * Rewrites
 * <code>new JSCompiler_ObjectPropertyString(window, foo$bar$baz)</code> to
 * <code>new JSCompiler_ObjectPropertyString(window, 'foo$bar$baz')</code>
 *
 * @see ObjectPropertyStringPreprocess
 *
 */
class ObjectPropertyStringPostprocess implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.ping();
  }

  private final AbstractCompiler compiler;

  public ObjectPropertyStringPostprocess(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[1]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new Callback());
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[2]++;
  }

  private class Callback extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[1]++;
        return;

      } else {
  CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[2]++;}
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[4]++;

      Node objectName = n.getFirstChild();
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;

      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ObjectPropertyStringPreprocess.EXTERN_OBJECT_PROPERTY_STRING.equals(
              objectName.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[3]++;
        return;

      } else {
  CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[4]++;}
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[6]++;

      Node firstArgument = objectName.getNext();
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[7]++;
      Node secondArgument = firstArgument.getNext();
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[8]++;
      int secondArgumentType = secondArgument.getType();
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((secondArgumentType == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[5]++;
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[10]++;
        // Rewrite "new goog.testing.ObjectPropertyString(window, foo.bar)"
        // as "new goog.testing.ObjectPropertyString(foo, 'bar')".
        Node newChild = secondArgument.getFirstChild();
        secondArgument.removeChild(newChild);
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[11]++;
        n.replaceChild(firstArgument, newChild);
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[12]++;
        n.replaceChild(secondArgument,
            IR.string(secondArgument.getFirstChild().getString()));
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[13]++;

      } else {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[6]++;
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[14]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((secondArgumentType == Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[7]++;
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[15]++;
        // Rewrite "new goog.testing.ObjectPropertyString(window, foo[bar])"
        // as "new goog.testing.ObjectPropertyString(foo, bar)".
        Node newFirstArgument = secondArgument.getFirstChild();
        secondArgument.removeChild(newFirstArgument);
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[16]++;
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[17]++;
        Node newSecondArgument = secondArgument.getLastChild();
        secondArgument.removeChild(newSecondArgument);
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[18]++;
        n.replaceChild(firstArgument, newFirstArgument);
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[19]++;
        n.replaceChild(secondArgument, newSecondArgument);
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[20]++;

      } else {
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.branches[8]++;
        // Rewrite "new goog.testing.ObjectPropertyString(window, foo)" as
        // "new goog.testing.ObjectPropertyString(window, 'foo')"
        n.replaceChild(secondArgument,
            IR.string(secondArgument.getString()));
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[21]++;
      }
}
      compiler.reportCodeChange();
CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5.statements[22]++;
    }
  }
}

class CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5 ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ObjectPropertyStringPostprocess.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
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

  public CodeCoverCoverageCounter$3wjfhje9jyprtkbae0beu05ikovg5avo95zr65s2i5h1knpouqh655r5 () {
    super("com.google.javascript.jscomp.ObjectPropertyStringPostprocess.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ObjectPropertyStringPostprocess.java");
      for (int i = 1; i <= 22; i++) {
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

