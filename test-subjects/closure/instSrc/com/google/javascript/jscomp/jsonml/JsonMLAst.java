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

package com.google.javascript.jscomp.jsonml;

import com.google.common.base.Preconditions;
import com.google.javascript.jscomp.AbstractCompiler;
import com.google.javascript.jscomp.AstValidator;
import com.google.javascript.jscomp.SourceAst;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Generates an AST from a JsonML source file.
 *
 * JsonML format for representation of JavaScript is specified
 * <a href="http://code.google.com/p/es-lab/wiki/JsonMLASTFormat">here.</a>
 *
 * @author dhans@google.com (Daniel Hans)
 *
 */
public class JsonMLAst implements SourceAst {
  static {
    CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[1]++;
  }
  private static final String DEFAULT_SOURCE_NAME = "[[jsonmlsource]]";
  static {
    CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[2]++;
  }

  /*
   * Root element of JavaScript source which is represented by a JsonML tree.
   * See JsonML class for more details.
   */
  private JsonML jsonml;

  /*
   * Root node of internal JS Compiler AST which represents the same source.
   * In order to get the tree, getAstRoot() has to be called.
   */
  private Node root;

  private final SourceFile sourceFile;
  private final InputId inputId;

  public JsonMLAst(JsonML jsonml) {
    this.jsonml = jsonml;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[3]++;
    this.inputId = new InputId(getSourceName());
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[4]++;
    this.sourceFile = new SourceFile(getSourceName());
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[5]++;
  }

  @Override
  public void clearAst() {
    root = null;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[6]++;
  }

  /**
   * Generates AST based on AST representation
   * @see com.google.javascript.jscomp.SourceAst#getAstRoot(AbstractCompiler)
   */
  @Override
  public Node getAstRoot(AbstractCompiler compiler) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[1]++;
      createAst(compiler);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[8]++;

    } else {
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[2]++;}
    return root;
  }

  @Override
  public SourceFile getSourceFile() {
    return null;
  }

  @Override
  public void setSourceFile(SourceFile file) {
    throw new UnsupportedOperationException(
        "JsonMLAst cannot be associated with a SourceFile instance.");
  }

  public String getSourceName() {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[9]++;
    Object obj = jsonml.getAttribute(TagAttr.SOURCE);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[3]++;
      return (String) obj;

    } else {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[4]++;
      return DEFAULT_SOURCE_NAME;
    }
  }

  private void createAst(AbstractCompiler compiler) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[11]++;
    Reader translator = new Reader();
    translator.setRootElement(jsonml);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[12]++;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      root = translator.parse(compiler);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[14]++;
      root.setInputId(inputId);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[15]++;
      root.setStaticSourceFile(sourceFile);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[16]++;
      new AstValidator().validateScript(root);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[17]++;
    } catch (JsonMLException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[6]++;
      // compiler should already have JSErrors
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[5]++;
}
  }
  }

  public JsonML convertToJsonML () {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[7]++;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[19]++;
      Writer converter = new Writer();
      return converter.processAst(root);

    } else {
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[8]++;}
    return null;
  }

  /**
   * Returns a JsonML element with the specified number from the tree in
   * pre-order walk.
   *
   * @return nth node or null if the node does not exists
   */
  public JsonML getElementPreOrder(int n) {
    Preconditions.checkState(jsonml != null);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[20]++;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[9]++;
      return jsonml;

    } else {
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[10]++;}
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[22]++;

    Deque<WalkHelper> stack =
        new ArrayDeque<WalkHelper>();
    stack.push(new WalkHelper(jsonml, 0));
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[23]++;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[24]++;
    int i = 0;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[25]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
    while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((i <= n) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((stack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.loops[1]--;
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.loops[2]--;
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.loops[3]++;
}
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[26]++;
      WalkHelper current = stack.pop();
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[27]++;
      JsonML element = current.element;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[28]++;
      Integer childno = current.childno;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;

      // not all the children of this node have been visited
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((childno < element.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[11]++;
        stack.push(new WalkHelper(element, childno + 1));
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[30]++;
        // we visit the next child
        i++;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[31]++;
        element = element.getChild(childno);
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[32]++;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i == n) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[13]++;
          return element;

        } else {
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[14]++;}

        // put the next child on the stack to preserve pre-order
        stack.push(new WalkHelper(element, 0));
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[34]++;

      } else {
  CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.branches[12]++;}
    }
    return null;
  }

  /*
   * Represents a walk step while the JsonML tree is traversed.
   */
  private static class WalkHelper {
    // JsonML element that corresponds to this step
    final JsonML element;

    // number of children of the element which has already been visited
    final int childno;

    WalkHelper(JsonML element, int childno) {
      this.element = element;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[35]++;
      this.childno = childno;
CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl.statements[36]++;
    }
  }

  @Override
  public InputId getInputId() {
    return inputId;
  }
}

class CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.JsonMLAst.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$34yp6qkwqkoyohoua2z0bl () {
    super("com.google.javascript.jscomp.jsonml.JsonMLAst.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.JsonMLAst.java");
      for (int i = 1; i <= 36; i++) {
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

