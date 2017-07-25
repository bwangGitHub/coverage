/*
 * Copyright 2012 The Closure Compiler Authors.
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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.JsMessage.PlaceholderReference;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collections;
import java.util.List;


/**
 * Replaces user-visible messages with appropriate calls to
 * chrome.i18n.getMessage. The first argument to getMessage is the id of the
 * message, as a string. If the message contains placeholders, the second
 * argument is an array of the values being used for the placeholders, in the
 * order they appear in the source code.
 *
 */
class ReplaceMessagesForChrome extends JsMessageVisitor {
  static {
    CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.ping();
  }


  ReplaceMessagesForChrome(AbstractCompiler compiler,
      JsMessage.IdGenerator idGenerator,
      boolean checkDuplicatedMessages, JsMessage.Style style) {

    super(compiler, checkDuplicatedMessages, style, idGenerator);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[1]++;
  }

  private static Node getChromeI18nGetMessageNode(String messageId) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[2]++;
    Node chromeI18n = IR.getprop(IR.name("chrome"), IR.string("i18n"));
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[3]++;
    Node getMessage =  IR.getprop(chromeI18n, IR.string("getMessage"));
    return IR.call(getMessage, IR.string(messageId));
  }

  @Override
  protected void processJsMessage(
      JsMessage message, JsMessageDefinition definition) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[4]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[5]++;
      Node msgNode = definition.getMessageNode();
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[6]++;
      Node newValue = getNewValueNode(msgNode, message);
      newValue.copyInformationFromForTree(msgNode);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[7]++;

      definition.getMessageParentNode().replaceChild(msgNode, newValue);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[8]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[9]++;
    } catch (MalformedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[2]++;
      compiler.report(JSError.make(message.getSourceName(), e.getNode(),
          MESSAGE_TREE_MALFORMED, e.getMessage()));
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[10]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[1]++;
}
  }
  }

  private Node getNewValueNode(Node origNode, JsMessage message)
      throws MalformedException {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[11]++;
    Node newValueNode = getChromeI18nGetMessageNode(message.getId());
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((message.placeholders().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[3]++;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[13]++;
      Node placeholderValues = origNode.getLastChild();
      checkNode(placeholderValues, Token.OBJECTLIT);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[14]++;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[15]++;

      // Output the placeholders, sorted alphabetically by placeholder name,
      // regardless of what order they appear in the original message.
      List<String> placeholderNames = Lists.newArrayList();
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[1]++;


      for (CharSequence cs : message.parts()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[1]--;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[2]--;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[3]++;
}
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cs instanceof PlaceholderReference) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[5]++;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[18]++;
          String placeholderName = ((PlaceholderReference) cs).getName();
          placeholderNames.add(placeholderName);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[19]++;

        } else {
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[6]++;}
      }
      Collections.sort(placeholderNames);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[20]++;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[21]++;

      Node placeholderValueArray = IR.arraylit();
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[4]++;


      for (String name : placeholderNames) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[4]--;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[5]--;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[6]++;
}
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[23]++;
        Node value = getPlaceholderValue(placeholderValues, name);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[7]++;
          throw new MalformedException(
              "No value was provided for placeholder " + name,
              origNode);

        } else {
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[8]++;}
        placeholderValueArray.addChildToBack(value);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[25]++;
      }
      newValueNode.addChildToBack(placeholderValueArray);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[26]++;

    } else {
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[4]++;}

    newValueNode.copyInformationFromForTree(origNode);
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[27]++;
    return newValueNode;
  }

  private Node getPlaceholderValue(
      Node placeholderValues, String placeholderName) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[7]++;


    for (Node key : placeholderValues.children()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[7]--;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[8]--;
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.loops[9]++;
}
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key.getString().equals(placeholderName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[9]++;
        return key.getFirstChild().cloneTree();

      } else {
  CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl.branches[10]++;}
    }
    return null;
  }
}

class CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReplaceMessagesForChrome.java";
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$7eg9qt560szx3e6glha1djvg3hwi3vifudp7aojoz9obl () {
    super("com.google.javascript.jscomp.ReplaceMessagesForChrome.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ReplaceMessagesForChrome.java");
      for (int i = 1; i <= 29; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

