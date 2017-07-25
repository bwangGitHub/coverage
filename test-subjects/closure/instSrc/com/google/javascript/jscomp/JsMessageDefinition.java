/*
 * Copyright 2008 The Closure Compiler Authors.
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
 * Container class that holds information about JS message source.
 *
 * This class is specific to our JsMessage syntax. Allows you to use the
 * new-style or the old-style messages.
 *
 * Old-style:
 * <code>
 * var MSG_LEOPARD = 'Leopard';
 * var MSG_LEOPARD_HELP = 'The Leopard operating system';
 * </code>
 *
 * New-style:
 * <code>
 * /** @desc The leopard operating system * /
 * var MSG_LEOPARD = goog.getMsg('Leopard');
 * </code>
 *
 * @author anatol@google.com (Anatol Pomazau)
 */
class JsMessageDefinition {
  static {
    CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1.ping();
  }


  private final Node messageNode;
  private final Node messageParentNode;
  private final Node visitingNode;

  /**
   * Constructs JS message definition.
   *
   * @param visitingNode Node that is visited by
   *     {@link JsMessageVisitor}. Take into
   *     account that visiting node could differ from the node the message
   *     was found.
   * @param messageNode A node that contains the message. It could be node with
   *     goog.getMsg() call or string/function for old-style messages.
   * @param messageParentNode The parent of the message node.
   */
  JsMessageDefinition(Node visitingNode, Node messageNode,
      Node messageParentNode) {

    this.messageNode = messageNode;
CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1.statements[1]++;
    this.visitingNode = visitingNode;
CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1.statements[2]++;
    this.messageParentNode = messageParentNode;
CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1.statements[3]++;
  }

  Node getMessageNode() {
    return messageNode;
  }

  Node getVisitingNode() {
    return visitingNode;
  }

 Node getMessageParentNode() {
    return messageParentNode;
  }
}

class CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1 ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$h5qa3xgo9sekmwzjxsddj0a6a8ldeh4l2k0o1 () {
    super("com.google.javascript.jscomp.JsMessageDefinition.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.JsMessageDefinition.java");
      for (int i = 1; i <= 3; i++) {
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

