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
 * Rewrites <code>new goog.testing.ObjectPropertyString(foo, 'bar')</code> to
 * <code>new JSCompiler_ObjectPropertyString(window, foo.bar)</code>.
 *
 * These two passes are for use with goog.testing.PropertyReplacer.
 *
 * <code>
 * var ops = new goog.testing.ObjectPropertyString(foo.prototype, 'bar');
 * propertyReplacer.set(ops,object, ops.propertyString, baz);
 * </code>
 *
 * @see ObjectPropertyStringPostprocess
 *
 */
public class ObjectPropertyStringPreprocess implements CompilerPass {
  static {
    CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.ping();
  }

  static final String OBJECT_PROPERTY_STRING =
      "goog.testing.ObjectPropertyString";
  static {
    CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[1]++;
  }

  public static final String EXTERN_OBJECT_PROPERTY_STRING =
      "JSCompiler_ObjectPropertyString";
  static {
    CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[2]++;
  }

  static final DiagnosticType INVALID_NUM_ARGUMENTS_ERROR =
      DiagnosticType.error("JSC_OBJECT_PROPERTY_STRING_NUM_ARGS",
          "goog.testing.ObjectPropertyString instantiated with \"{0}\" " +
          "arguments, expected 2.");
  static {
    CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[3]++;
  }

  static final DiagnosticType QUALIFIED_NAME_EXPECTED_ERROR =
      DiagnosticType.error("JSC_OBJECT_PROPERTY_STRING_QUALIFIED_NAME_EXPECTED",
          "goog.testing.ObjectPropertyString instantiated with invalid " +
          "argument, qualified name expected. Was \"{0}\".");
  static {
    CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[4]++;
  }

  static final DiagnosticType STRING_LITERAL_EXPECTED_ERROR =
      DiagnosticType.error("JSC_OBJECT_PROPERTY_STRING_STRING_LITERAL_EXPECTED",
          "goog.testing.ObjectPropertyString instantiated with invalid " +
          "argument, string literal expected. Was \"{0}\".");
  static {
    CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[5]++;
  }

  private final AbstractCompiler compiler;

  ObjectPropertyStringPreprocess(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
    addExternDeclaration(externs,
        IR.var(
            IR.name(EXTERN_OBJECT_PROPERTY_STRING)));
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[7]++;
    NodeTraversal.traverse(compiler, root, new Callback());
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[8]++;
  }

  private void addExternDeclaration(Node externs, Node declarationStmt) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[9]++;
    Node script = externs.getLastChild();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((script == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((script.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[1]++;
      script = IR.script();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[11]++;
      externs.addChildToBack(script);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[12]++;

    } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[2]++;}
    script.addChildToBack(declarationStmt);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[13]++;
  }

  private class Callback extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((OBJECT_PROPERTY_STRING.equals(n.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[3]++;
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[15]++;
        Node newName = IR.name(EXTERN_OBJECT_PROPERTY_STRING);
        newName.copyInformationFrom(n);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[16]++;
        parent.replaceChild(n, newName);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[17]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[18]++;
        return;

      } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[4]++;}
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;

      // Rewrite "new goog.testing.ObjectPropertyString(foo, 'bar')" to
      // "new goog.testing.ObjectPropertyString(window, foo.bar)" and
      // issues errors if bad arguments are encountered.
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[5]++;
        return;

      } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[6]++;}
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[20]++;

      Node objectName = n.getFirstChild();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;

      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((EXTERN_OBJECT_PROPERTY_STRING.equals(
              objectName.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[7]++;
        return;

      } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[8]++;}
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.getChildCount() != 3) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[9]++;
        compiler.report(t.makeError(n, INVALID_NUM_ARGUMENTS_ERROR,
            "" + n.getChildCount()));
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[23]++;
        return;

      } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[10]++;}
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[24]++;

      Node firstArgument = objectName.getNext();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((firstArgument.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[11]++;
        compiler.report(t.makeError(firstArgument,
            QUALIFIED_NAME_EXPECTED_ERROR,
            Token.name(firstArgument.getType())));
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[26]++;
        return;

      } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[12]++;}
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[27]++;

      Node secondArgument = firstArgument.getNext();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((secondArgument.isString()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[13]++;
        compiler.report(t.makeError(secondArgument,
            STRING_LITERAL_EXPECTED_ERROR,
            Token.name(secondArgument.getType())));
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[29]++;
        return;

      } else {
  CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.branches[14]++;}
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[30]++;

      Node newFirstArgument = NodeUtil.newQualifiedNameNode(
          compiler.getCodingConvention(),
          compiler.getCodingConvention().getGlobalObject())
              .srcrefTree(firstArgument);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[31]++;

      Node newSecondArgument = NodeUtil.newQualifiedNameNode(
          compiler.getCodingConvention(),
          firstArgument.getQualifiedName() + "." +
          firstArgument.getNext().getString())
              .srcrefTree(secondArgument);

      n.replaceChild(firstArgument, newFirstArgument);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[32]++;
      n.replaceChild(secondArgument, newSecondArgument);
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[33]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p.statements[34]++;
    }
  }
}

class CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ObjectPropertyStringPreprocess.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$jrhd5jfyvbhz3f35cf3rue9xa8x7hubujj24vr98jloqn8mq9pgn6p () {
    super("com.google.javascript.jscomp.ObjectPropertyStringPreprocess.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ObjectPropertyStringPreprocess.java");
      for (int i = 1; i <= 34; i++) {
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
    for (int i = 1; i <= 7; i++) {
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

