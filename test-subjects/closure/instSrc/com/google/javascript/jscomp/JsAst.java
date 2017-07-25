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

import com.google.javascript.jscomp.parsing.ParserRunner;

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;
import java.io.IOException;

import java.util.logging.Logger;

/**
 * Generates an AST for a JavaScript source file.
 *
 */
public class JsAst implements SourceAst {
  static {
    CodeCoverCoverageCounter$1l92l7x08blm3hpd.ping();
  }

  private static final Logger logger_ = Logger.getLogger(JsAst.class.getName());
  static {
    CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[1]++;
  }
  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[2]++;
  }

  private transient InputId inputId;
  private transient SourceFile sourceFile;
  private String fileName;
  private Node root;

  public JsAst(SourceFile sourceFile) {
    this.inputId = new InputId(sourceFile.getName());
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[3]++;
    this.sourceFile = sourceFile;
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[4]++;
    this.fileName = sourceFile.getName();
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[5]++;
  }

  @Override
  public Node getAstRoot(AbstractCompiler compiler) {
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1l92l7x08blm3hpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1l92l7x08blm3hpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1l92l7x08blm3hpd.branches[1]++;
      parse(compiler);
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[7]++;
      root.setInputId(inputId);
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[8]++;

    } else {
  CodeCoverCoverageCounter$1l92l7x08blm3hpd.branches[2]++;}
    return root;
  }

  @Override
  public void clearAst() {
    root = null;
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[9]++;
    // While we're at it, clear out any saved text in the source file on
    // the assumption that if we're dumping the parse tree, then we probably
    // assume regenerating everything else is a smart idea also.
    sourceFile.clearCachedSource();
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[10]++;
  }

  @Override
  public InputId getInputId() {
    return inputId;
  }

  @Override
  public SourceFile getSourceFile() {
    return sourceFile;
  }

  @Override
  public void setSourceFile(SourceFile file) {
    Preconditions.checkState(fileName.equals(file.getName()));
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[11]++;
    sourceFile = file;
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[12]++;
  }

  private void parse(AbstractCompiler compiler) {
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      logger_.fine("Parsing: " + sourceFile.getName());
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[14]++;
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[15]++;
      ParserRunner.ParseResult result = ParserRunner.parse(sourceFile, sourceFile.getCode(),
          compiler.getParserConfig(),
          compiler.getDefaultErrorReporter(),
          logger_);
      root = result.ast;
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[16]++;
      compiler.setOldParseTree(sourceFile.getName(), result.oldAst);
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[17]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1l92l7x08blm3hpd.branches[4]++;
      compiler.report(
          JSError.make(AbstractCompiler.READ_ERROR, sourceFile.getName()));
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[18]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1l92l7x08blm3hpd.branches[3]++;
}
  }
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((compiler.hasHaltingErrors()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1l92l7x08blm3hpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1l92l7x08blm3hpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1l92l7x08blm3hpd.branches[5]++;
      // There was a parse error or IOException, so use a dummy block.
      root = IR.script();
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[20]++;

    } else {
CodeCoverCoverageCounter$1l92l7x08blm3hpd.branches[6]++;
      compiler.prepareAst(root);
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[21]++;
    }

    // Set the source name so that the compiler passes can track
    // the source file and module.
    root.setStaticSourceFile(sourceFile);
CodeCoverCoverageCounter$1l92l7x08blm3hpd.statements[22]++;
  }
}

class CodeCoverCoverageCounter$1l92l7x08blm3hpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1l92l7x08blm3hpd ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JsAst.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1l92l7x08blm3hpd () {
    super("com.google.javascript.jscomp.JsAst.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JsAst.java");
      for (int i = 1; i <= 22; i++) {
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
    for (int i = 1; i <= 2; i++) {
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

