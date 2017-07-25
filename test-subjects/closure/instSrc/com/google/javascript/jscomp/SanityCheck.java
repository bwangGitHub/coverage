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

import com.google.javascript.rhino.Node;

/**
 * A compiler pass that verifies the structure of the AST conforms
 * to a number of invariants. Because this can add a lot of overhead,
 * we only run this in development mode.
 *
 */
class SanityCheck implements CompilerPass {
  static {
    CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.ping();
  }


  static final DiagnosticType CANNOT_PARSE_GENERATED_CODE =
      DiagnosticType.error("JSC_CANNOT_PARSE_GENERATED_CODE",
          "Internal compiler error. Cannot parse generated code: {0}");
  static {
    CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[1]++;
  }

  static final DiagnosticType GENERATED_BAD_CODE = DiagnosticType.error(
      "JSC_GENERATED_BAD_CODE",
      "Internal compiler error. Generated bad code." +
      "----------------------------------------\n" +
      "Expected:\n{0}\n" +
      "----------------------------------------\n" +
      "Actual:\n{1}");
  static {
    CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[2]++;
  }

  private final AbstractCompiler compiler;

  private final AstValidator astValidator = new AstValidator();
  {
    CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[3]++;
  }

  SanityCheck(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
    sanityCheckAst(externs, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[5]++;
    sanityCheckNormalization(externs, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[6]++;
    sanityCheckCodeGeneration(root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[7]++;
    sanityCheckVars(externs, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[8]++;
  }

  /**
   * Sanity check the AST is structurally accurate.
   */
  private void sanityCheckAst(Node externs, Node root) {
    astValidator.validateCodeRoot(externs);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[9]++;
    astValidator.validateCodeRoot(root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[10]++;
  }

  private void sanityCheckVars(Node externs, Node root) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((compiler.getLifeCycleStage().isNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[1]++;
      (new VarCheck(compiler, true)).process(externs, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[12]++;

    } else {
  CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[2]++;}
  }

  /**
   * Sanity checks code generation by performing it once, parsing the result,
   * then generating code from the second parse tree to verify that it matches
   * the code generated from the first parse tree.
   *
   * @return The regenerated parse tree. Null on error.
   */
  private Node sanityCheckCodeGeneration(Node root) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((compiler.hasHaltingErrors()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[3]++;
      // Don't even bother checking code generation if we already know the
      // the code is bad.
      return null;

    } else {
  CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[4]++;}
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[14]++;

    String source = compiler.toSource(root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[15]++;
    Node root2 = compiler.parseSyntheticCode(source);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((compiler.hasHaltingErrors()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[5]++;
      compiler.report(JSError.make(CANNOT_PARSE_GENERATED_CODE,
              Strings.truncateAtMaxLength(source, 100, true)));
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[17]++;

      // Throw an exception, so that the infrastructure will tell us
      // which pass violated the sanity check.
      throw new IllegalStateException("Sanity Check failed");

    } else {
  CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[6]++;}
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[18]++;

    String source2 = compiler.toSource(root2);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((source.equals(source2)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[7]++;
      compiler.report(JSError.make(GENERATED_BAD_CODE, source, source2));
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[20]++;

      // Throw an exception, so that the infrastructure will tell us
      // which pass violated the sanity check.
      throw new IllegalStateException("Sanity Check failed");

    } else {
  CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[8]++;}

    return root2;
  }

  /**
   * Sanity checks the AST. This is by verifying the normalization passes do
   * nothing.
   */
  private void sanityCheckNormalization(Node externs, Node root) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[21]++;
    // Verify nothing has inappropriately denormalize the AST.
    CodeChangeHandler handler =
        new CodeChangeHandler.ForbiddenChange();
    compiler.addChangeHandler(handler);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[22]++;

    // TODO(johnlenz): Change these normalization checks Preconditions and
    // Exceptions into Errors so that it is easier to find the root cause
    // when there are cascading issues.
    new PrepareAst(compiler, true).process(null, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[23]++;
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((compiler.getLifeCycleStage().isNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[9]++;
      (new Normalize(compiler, true)).process(externs, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[25]++;
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((compiler.getLifeCycleStage().isNormalizedUnobfuscated()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[11]++;
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[27]++;
        boolean checkUserDeclarations = true;
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[28]++;
        CompilerPass pass = new Normalize.VerifyConstants(
            compiler, checkUserDeclarations);
        pass.process(externs, root);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[29]++;

      } else {
  CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[12]++;}

    } else {
  CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.branches[10]++;}

    compiler.removeChangeHandler(handler);
CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5.statements[30]++;
  }
}

class CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5 ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SanityCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$4xphjj3lnip3wzqblzcowu5b5 () {
    super("com.google.javascript.jscomp.SanityCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SanityCheck.java");
      for (int i = 1; i <= 30; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

