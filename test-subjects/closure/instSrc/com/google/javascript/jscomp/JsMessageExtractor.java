/*
 * Copyright 2004 The Closure Compiler Authors.
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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


/**
 * Extracts messages and message comments from JS code.
 *
 * <p> Uses a special prefix (e.g. {@code MSG_}) to determine which variables
 * are messages. Here are the recognized formats:
 *
 *   <code>
 *   var MSG_FOO = "foo";
 *   var MSG_FOO_HELP = "this message is used for foo";
 *   </code>
 *
 *   <code>
 *   var MSG_BAR = function(a, b) {
 *     return a + " bar " + b;
 *   }
 *   var MSG_BAR_HELP = "the bar message";
 *   </code>
 *
 * <p>This class enforces the policy that message variable names must be unique
 * across all JS files.
 *
 */
public class JsMessageExtractor {
  static {
    CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.ping();
  }


  private final JsMessage.Style style;
  private final JsMessage.IdGenerator idGenerator;
  private final CompilerOptions options;

  public JsMessageExtractor(
      JsMessage.IdGenerator idGenerator,
      JsMessage.Style style) {
    this(idGenerator, style, new CompilerOptions());
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[1]++;
  }

  public JsMessageExtractor(
      JsMessage.IdGenerator idGenerator,
      JsMessage.Style style,
      CompilerOptions options) {
    this.idGenerator = idGenerator;
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[2]++;
    this.style = style;
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[3]++;
    this.options = options;
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[4]++;
  }

  /**
   * Visitor that collects messages.
   */
  private class ExtractMessagesVisitor extends JsMessageVisitor {
    // We use List here as we want to preserve insertion-order for found
    // messages.
    // Take into account that messages with the same id could be present in the
    // result list. Message could have the same id only in case if they are
    // unnamed and have the same text but located in different source files.
    private final List<JsMessage> messages = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[5]++;
  }

    private ExtractMessagesVisitor(AbstractCompiler compiler) {
      super(compiler, true, style, idGenerator);
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[6]++;
    }

    @Override
    void processJsMessage(JsMessage message,
        JsMessageDefinition definition) {
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((message.isExternal()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.branches[1]++;
        messages.add(message);
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[8]++;

      } else {
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.branches[2]++;}
    }

    /**
     * Returns extracted messages.
     *
     * @return collection of JsMessage objects that was found in js sources.
     */
    public Collection<JsMessage> getMessages() {
      return messages;
    }
  }

  /**
   * Extracts JS messages from JavaScript code.
   */
  public Collection<JsMessage> extractMessages(SourceFile... inputs)
      throws IOException {
    return extractMessages(ImmutableList.copyOf(inputs));
  }


  /**
   * Extracts JS messages from JavaScript code.
   *
   * @param inputs  the JavaScript source code inputs
   * @return the extracted messages collection
   * @throws IOException if there is a problem reading the JS code
   * @throws RuntimeException if there are problems parsing the JS code or the
   *     JS messages, or if two messages have the same key
   */
  public <T extends SourceFile> Collection<JsMessage> extractMessages(
      Iterable<T> inputs) throws IOException {
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[9]++;

    Compiler compiler = new Compiler();
    compiler.init(
        ImmutableList.<SourceFile>of(),
        Lists.newArrayList(inputs),
        options);
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[10]++;
    compiler.parseInputs();
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[11]++;
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[12]++;

    ExtractMessagesVisitor extractCompilerPass =
        new ExtractMessagesVisitor(compiler);
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((compiler.getErrors().length == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.branches[3]++;
      extractCompilerPass.process(null, compiler.getRoot());
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[14]++;

    } else {
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.branches[4]++;}
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[15]++;

    JSError[] errors = compiler.getErrors();
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    // Check for errors.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((errors.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.branches[5]++;
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[17]++;
      StringBuilder msg = new StringBuilder("JSCompiler errors\n");
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[18]++;
      MessageFormatter formatter = new LightweightMessageFormatter(compiler);
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.loops[1]++;


      for (JSError e : errors) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.loops[1]--;
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.loops[2]--;
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.loops[3]++;
}
        msg.append(formatter.formatError(e));
CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.statements[20]++;
      }
      throw new RuntimeException(msg.toString());

    } else {
  CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh.branches[6]++;}

    return extractCompilerPass.getMessages();
  }
}

class CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JsMessageExtractor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$2ev9aoe5nvk1rz0jmdkvtc13sd6zl3nepysh () {
    super("com.google.javascript.jscomp.JsMessageExtractor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JsMessageExtractor.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

