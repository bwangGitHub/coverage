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

import static com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.JsMessage.Builder;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.*;
import java.util.regex.*;

import javax.annotation.Nullable;

/**
 * Traverses across parsed tree and finds I18N messages. Then it passes it to
 * {@link JsMessageVisitor#processJsMessage(JsMessage, JsMessageDefinition)}.
 *
 * @author anatol@google.com (Anatol Pomazau)
 */
abstract class JsMessageVisitor extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.ping();
  }


  private static final String MSG_FUNCTION_NAME = "goog.getMsg";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[1]++;
  }
  private static final String MSG_FALLBACK_FUNCTION_NAME =
      "goog.getMsgWithFallback";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[2]++;
  }

  static final DiagnosticType MESSAGE_HAS_NO_DESCRIPTION =
      DiagnosticType.warning("JSC_MSG_HAS_NO_DESCRIPTION",
          "Message {0} has no description. Add @desc JsDoc tag.");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[3]++;
  }

  static final DiagnosticType MESSAGE_HAS_NO_TEXT =
      DiagnosticType.warning("JSC_MSG_HAS_NO_TEXT",
          "Message value of {0} is just an empty string. "
              + "Empty messages are forbidden." );
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[4]++;
  }

  static final DiagnosticType MESSAGE_TREE_MALFORMED =
      DiagnosticType.error("JSC_MSG_TREE_MALFORMED",
          "Message parse tree malformed. {0}");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[5]++;
  }

  static final DiagnosticType MESSAGE_HAS_NO_VALUE =
      DiagnosticType.error("JSC_MSG_HAS_NO_VALUE",
          "message node {0} has no value");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[6]++;
  }

  static final DiagnosticType MESSAGE_DUPLICATE_KEY =
      DiagnosticType.error("JSC_MSG_KEY_DUPLICATED",
          "duplicate message variable name found for {0}, " +
              "initial definition {1}:{2}");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[7]++;
  }

  static final DiagnosticType MESSAGE_NODE_IS_ORPHANED =
      DiagnosticType.warning("JSC_MSG_ORPHANED_NODE", MSG_FUNCTION_NAME +
          "() function could be used only with MSG_* property or variable");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[8]++;
  }

  static final DiagnosticType MESSAGE_NOT_INITIALIZED_USING_NEW_SYNTAX =
      DiagnosticType.error("JSC_MSG_NOT_INITIALIZED_USING_NEW_SYNTAX",
          "message not initialized using " + MSG_FUNCTION_NAME);
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[9]++;
  }

  static final DiagnosticType BAD_FALLBACK_SYNTAX =
      DiagnosticType.error("JSC_MSG_BAD_FALLBACK_SYNTAX",
          String.format(
              "Bad syntax. " +
              "Expected syntax: goog.getMsgWithFallback(MSG_1, MSG_2)",
              MSG_FALLBACK_FUNCTION_NAME));
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[10]++;
  }

  static final DiagnosticType FALLBACK_ARG_ERROR =
      DiagnosticType.error("JSC_MSG_FALLBACK_ARG_ERROR",
          "Could not find message entry for fallback argument {0}");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[11]++;
  }

  private static final String PH_JS_PREFIX = "{$";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[12]++;
  }
  private static final String PH_JS_SUFFIX = "}";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[13]++;
  }

  static final String MSG_PREFIX = "MSG_";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[14]++;
  }

  /**
   * Pattern for unnamed messages.
   * <p>
   * All JS messages in JS code should have unique name but messages in
   * generated code (i.e. from soy template) could have duplicated message names.
   * Later we replace the message names with ids constructed as a hash of the
   * message content.
   * <p>
   * <link href="http://code.google.com/p/closure-templates/">
   * Soy</link> generates messages with names MSG_UNNAMED_<NUMBER> . This
   * pattern recognizes such messages.
   */
  private static final Pattern MSG_UNNAMED_PATTERN =
      Pattern.compile("MSG_UNNAMED_\\d+");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[15]++;
  }

  private static final Pattern CAMELCASE_PATTERN =
      Pattern.compile("[a-z][a-zA-Z\\d]*[_\\d]*");
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[16]++;
  }

  static final String HIDDEN_DESC_PREFIX = "@hidden";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[17]++;
  }

  // For old-style JS messages
  private static final String DESC_SUFFIX = "_HELP";
  static {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[18]++;
  }

  private final boolean needToCheckDuplications;
  private final JsMessage.Style style;
  private final JsMessage.IdGenerator idGenerator;
  final AbstractCompiler compiler;

  /**
   * The names encountered associated with their defining node and source. We
   * use it for tracking duplicated message ids in the source code.
   */
  private final Map<String, MessageLocation> messageNames =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[19]++;
  }

  private final Map<Var, JsMessage> unnamedMessages =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[20]++;
  }

  /**
   * List of found goog.getMsg call nodes.
   *
   * When we visit goog.getMsg() node we add a pair node:sourcename and later
   * when we visit its parent we remove this pair. All nodes that are left at
   * the end of traversing are orphaned nodes. It means have no corresponding
   * var or property node.
   */
  private final Map<Node, String> googMsgNodes = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[21]++;
  }

  private final CheckLevel checkLevel;

  /**
   * Creates JS message visitor.
   *
   * @param compiler the compiler instance
   * @param needToCheckDuplications whether to check duplicated messages in
   *        traversed
   * @param style style that should be used during parsing
   * @param idGenerator generator that used for creating unique ID for the
   *        message
   */
  JsMessageVisitor(AbstractCompiler compiler,
      boolean needToCheckDuplications,
      JsMessage.Style style, JsMessage.IdGenerator idGenerator) {

    this.compiler = compiler;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[22]++;
    this.needToCheckDuplications = needToCheckDuplications;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[23]++;
    this.style = style;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[24]++;
    this.idGenerator = idGenerator;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[25]++;

    checkLevel = (style == JsMessage.Style.CLOSURE)
        ? CheckLevel.ERROR : CheckLevel.WARNING;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[26]++;

    // TODO(anatol): add flag that decides whether to process UNNAMED messages.
    // Some projects would not want such functionality (unnamed) as they don't
    // use SOY templates.
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[27]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[1]++;



    for (Map.Entry<Node, String> msgNode : googMsgNodes.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[1]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[2]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[3]++;
}
      compiler.report(JSError.make(msgNode.getValue(), msgNode.getKey(),
          checkLevel, MESSAGE_NODE_IS_ORPHANED));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[29]++;
    }
  }

  @Override
  public void visit(NodeTraversal traversal, Node node, Node parent) {
    String messageKey;
    boolean isVar;
    Node msgNode, msgNodeParent;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[30]++;

    switch (node.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[1]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[31]++;
int CodeCoverConditionCoverageHelper_C1;
        // var MSG_HELLO = 'Message'
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[2]++;
          messageKey = node.getString();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[32]++;
          isVar = true;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[33]++;

        } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[3]++;
          return;
        }

        msgNode = node.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[34]++;
        msgNodeParent = node;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[35]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[36]++;
        break;
      case Token.ASSIGN:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[4]++;
        // somenamespace.someclass.MSG_HELLO = 'Message'
        isVar = false;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[37]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[38]++;

        Node getProp = node.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[39]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getProp.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[5]++;
          return;

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[6]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[40]++;

        Node propNode = getProp.getLastChild();

        messageKey = propNode.getString();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[41]++;
        msgNode = node.getLastChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[42]++;
        msgNodeParent = node;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[43]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[44]++;
        break;
      case Token.CALL:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[7]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[45]++;
        // goog.getMsg()
        String fnName = node.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[46]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((MSG_FUNCTION_NAME.equals(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[8]++;
          googMsgNodes.put(node, traversal.getSourceName());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[47]++;

        } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[9]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[48]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((MSG_FALLBACK_FUNCTION_NAME.equals(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[10]++;
          visitFallbackFunctionCall(traversal, node);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[49]++;

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[11]++;}
}
        return;
      default:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[12]++;
        return;
    }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[50]++;

    // Is this a message name?
    boolean isNewStyleMessage =
        msgNode != null && msgNode.isCall();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[51]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isMessageName(messageKey, isNewStyleMessage)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[13]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[14]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[52]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((msgNode == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[15]++;
      compiler.report(
          traversal.makeError(node, MESSAGE_HAS_NO_VALUE, messageKey));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[53]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[16]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[54]++;
int CodeCoverConditionCoverageHelper_C7;

    // Just report a warning if a qualified messageKey that looks like a message
    // (e.g. "a.b.MSG_X") doesn't use goog.getMsg().
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isNewStyleMessage) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[17]++;
      googMsgNodes.remove(msgNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[55]++;

    } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[18]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[56]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((style != JsMessage.Style.LEGACY) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[19]++;
      compiler.report(traversal.makeError(node, checkLevel,
          MESSAGE_NOT_INITIALIZED_USING_NEW_SYNTAX));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[57]++;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[20]++;}
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[58]++;

    boolean isUnnamedMsg = isUnnamedMessageName(messageKey);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[59]++;

    Builder builder = new Builder(
        isUnnamedMsg ? null : messageKey);
    builder.setSourceName(traversal.getSourceName());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[60]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[61]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[62]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isVar) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[22]++;
        extractMessageFromVariable(builder, node, parent, parent.getParent());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[63]++;

      } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[23]++;
        extractMessageFromProperty(builder, node.getFirstChild(), node);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[64]++;
      }
    } catch (MalformedException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[24]++;
      compiler.report(traversal.makeError(ex.getNode(),
          MESSAGE_TREE_MALFORMED, ex.getMessage()));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[65]++;
      return;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[21]++;
}
  }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[66]++;

    JsMessage extractedMessage = builder.build(idGenerator);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[67]++;
int CodeCoverConditionCoverageHelper_C10;

    // If asked to check named internal messages.
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((needToCheckDuplications) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((isUnnamedMsg) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((extractedMessage.isExternal()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[25]++;
      checkIfMessageDuplicated(messageKey, msgNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[68]++;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[26]++;}
    trackMessage(traversal, extractedMessage,
        messageKey, msgNode, isUnnamedMsg);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[69]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[70]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((extractedMessage.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[27]++;
      // value of the message is an empty string. Translators do not like it.
      compiler.report(traversal.makeError(node, MESSAGE_HAS_NO_TEXT,
          messageKey));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[71]++;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[28]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[72]++;

    // New-style messages must have descriptions. We don't emit a warning
    // for legacy-style messages, because there are thousands of
    // them in legacy code that are not worth the effort to fix, since they've
    // already been translated anyway.
    String desc = extractedMessage.getDesc();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[73]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (128)) == 0 || true) &&
 ((isNewStyleMessage) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((desc == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((desc.trim().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((extractedMessage.isExternal()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 4) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 4) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[29]++;
      compiler.report(traversal.makeError(node, MESSAGE_HAS_NO_DESCRIPTION,
          messageKey));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[74]++;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[30]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[75]++;

    JsMessageDefinition msgDefinition = new JsMessageDefinition(
        node, msgNode, msgNodeParent);
    processJsMessage(extractedMessage, msgDefinition);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[76]++;
  }

  /**
   * Track a message for later retrieval.
   *
   * This is used for tracking duplicates, and for figuring out message
   * fallback. Not all message types are trackable, because that would
   * require a more sophisticated analysis. e.g.,
   * function f(s) { s.MSG_UNNAMED_X = 'Some untrackable message'; }
   */
  private void trackMessage(
      NodeTraversal t, JsMessage message, String msgName,
      Node msgNode, boolean isUnnamedMessage) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[77]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isUnnamedMessage) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[31]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[78]++;
      MessageLocation location = new MessageLocation(message, msgNode);
      messageNames.put(msgName, location);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[79]++;

    } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[32]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[80]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((msgNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[33]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[81]++;
      Var var = t.getScope().getVar(msgName);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[82]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[35]++;
        unnamedMessages.put(var, message);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[83]++;

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[36]++;}

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[34]++;}
}
  }

  /** Get a previously tracked message. */
  private JsMessage getTrackedMessage(NodeTraversal t, String msgName) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[84]++;
    boolean isUnnamedMessage = isUnnamedMessageName(msgName);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[85]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isUnnamedMessage) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[37]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[86]++;
      MessageLocation location = messageNames.get(msgName);
      return location == null ? null : location.message;

    } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[38]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[87]++;
      Var var = t.getScope().getVar(msgName);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[88]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[39]++;
        return unnamedMessages.get(var);

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[40]++;}
    }
    return null;
  }

  /**
   * Checks if message already processed. If so - it generates 'message
   * duplicated' compiler error.
   *
   * @param msgName the name of the message
   * @param msgNode the node that represents JS message
   */
  private void checkIfMessageDuplicated(String msgName, Node msgNode) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[89]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((messageNames.containsKey(msgName)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[41]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[90]++;
      MessageLocation location = messageNames.get(msgName);
      compiler.report(JSError.make(msgNode, MESSAGE_DUPLICATE_KEY,
          msgName, location.messageNode.getSourceFileName(),
          Integer.toString(location.messageNode.getLineno())));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[91]++;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[42]++;}
  }

  /**
   * Creates a {@link JsMessage} for a JS message defined using a JS variable
   * declaration (e.g <code>var MSG_X = ...;</code>).
   *
   * @param builder the message builder
   * @param nameNode a NAME node for a JS message variable
   * @param parentNode a VAR node, parent of {@code nameNode}
   * @param grandParentNode the grandparent of {@code nameNode}. This node is
   *        only used to get meta data about the message that might be
   *        surrounding it (e.g. a message description). This argument may be
   *        null if the meta data is not needed.
   * @throws MalformedException if {@code varNode} does not
   *         correspond to a valid JS message VAR node
   */
  private void extractMessageFromVariable(
      Builder builder, Node nameNode, Node parentNode,
      @Nullable Node grandParentNode) throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[92]++;

    // Determine the message's value
    Node valueNode = nameNode.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[93]++;
    switch (valueNode.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[43]++;
      case Token.ADD:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[44]++;
        maybeInitMetaDataFromJsDocOrHelpVar(builder, parentNode,
            grandParentNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[94]++;
        builder.appendStringPart(extractStringFromStringExprNode(valueNode));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[95]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[96]++;
        break;
      case Token.FUNCTION:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[45]++;
        maybeInitMetaDataFromJsDocOrHelpVar(builder, parentNode,
            grandParentNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[97]++;
        extractFromFunctionNode(builder, valueNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[98]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[99]++;
        break;
      case Token.CALL:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[46]++;
        maybeInitMetaDataFromJsDoc(builder, parentNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[100]++;
        extractFromCallNode(builder, valueNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[101]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[102]++;
        break;
      default:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[47]++;
        throw new MalformedException("Cannot parse value of message "
            + builder.getKey(), valueNode);
    }
  }

  /**
   * Creates a {@link JsMessage} for a JS message defined using an assignment to
   * a qualified name (e.g <code>a.b.MSG_X = goog.getMsg(...);</code>).
   *
   * @param builder the message builder
   * @param getPropNode a GETPROP node in a JS message assignment
   * @param assignNode an ASSIGN node, parent of {@code getPropNode}.
   * @throws MalformedException if {@code getPropNode} does not
   *         correspond to a valid JS message node
   */
  private void extractMessageFromProperty(
      Builder builder, Node getPropNode, Node assignNode)
      throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[103]++;
    Node callNode = getPropNode.getNext();
    maybeInitMetaDataFromJsDoc(builder, assignNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[104]++;
    extractFromCallNode(builder, callNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[105]++;
  }

  /**
   * Initializes the meta data in a JsMessage by examining the nodes just before
   * and after a message VAR node.
   *
   * @param builder the message builder whose meta data will be initialized
   * @param varNode the message VAR node
   * @param parentOfVarNode {@code varNode}'s parent node
   */
  private void maybeInitMetaDataFromJsDocOrHelpVar(
      Builder builder, Node varNode, @Nullable Node parentOfVarNode)
      throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[106]++;
int CodeCoverConditionCoverageHelper_C19;

    // First check description in @desc
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((maybeInitMetaDataFromJsDoc(builder, varNode)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[48]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[49]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[107]++;
int CodeCoverConditionCoverageHelper_C20;

    // Check the preceding node for meta data
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((parentOfVarNode != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((maybeInitMetaDataFromHelpVar(builder,
            parentOfVarNode.getChildBefore(varNode))) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[50]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[51]++;}

    // Check the subsequent node for meta data
    maybeInitMetaDataFromHelpVar(builder, varNode.getNext());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[108]++;
  }

  /**
   * Initializes the meta data in a JsMessage by examining a node just before or
   * after a message VAR node.
   *
   * @param builder the message builder whose meta data will be initialized
   * @param sibling a node adjacent to the message VAR node
   * @return true iff message has corresponding description variable
   */
  private boolean maybeInitMetaDataFromHelpVar(Builder builder,
      @Nullable Node sibling) throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[109]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((sibling != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((sibling.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[52]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[110]++;
      Node nameNode = sibling.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[111]++;
      String name = nameNode.getString();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[112]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((name.equals(builder.getKey() + DESC_SUFFIX)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[54]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[113]++;
        Node valueNode = nameNode.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[114]++;
        String desc = extractStringFromStringExprNode(valueNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[115]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((desc.startsWith(HIDDEN_DESC_PREFIX)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[56]++;
          builder.setDesc(desc.substring(HIDDEN_DESC_PREFIX.length()).trim());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[116]++;
          builder.setIsHidden(true);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[117]++;

        } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[57]++;
          builder.setDesc(desc);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[118]++;
        }
        return true;

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[55]++;}

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[53]++;}
    return false;
  }

  /**
   * Initializes the meta data in a message builder given a node that may
   * contain JsDoc properties.
   *
   * @param builder the message builder whose meta data will be initialized
   * @param node the node with the message's JSDoc properties
   * @return true if message has JsDoc with valid description in @desc
   *         annotation
   */
  private boolean maybeInitMetaDataFromJsDoc(Builder builder, Node node) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[119]++;
    boolean messageHasDesc = false;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[120]++;
    JSDocInfo info = node.getJSDocInfo();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[121]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[58]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[122]++;
      String desc = info.getDescription();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[123]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((desc != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[60]++;
        builder.setDesc(desc);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[124]++;
        messageHasDesc = true;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[125]++;

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[61]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[126]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((info.isHidden()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[62]++;
        builder.setIsHidden(true);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[127]++;

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[63]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[128]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((info.getMeaning() != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[64]++;
        builder.setMeaning(info.getMeaning());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[129]++;

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[65]++;}

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[59]++;}

    return messageHasDesc;
  }

  /**
   * Returns the string value associated with a node representing a JS string or
   * several JS strings added together (e.g. {@code 'str'} or {@code 's' + 't' +
   * 'r'}).
   *
   * @param node the node from where we extract the string
   * @return String representation of the node
   * @throws MalformedException if the parsed message is invalid
   */
  private static String extractStringFromStringExprNode(Node node)
      throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[130]++;
    switch (node.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[66]++;
        return node.getString();
      case Token.ADD:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[67]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[131]++;
        StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[132]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[4]++;


        for (Node child : node.children()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[4]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[5]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[6]++;
}
          sb.append(extractStringFromStringExprNode(child));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[133]++;
        }
        return sb.toString();
      default:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[68]++;
        throw new MalformedException("STRING or ADD node expected; found: " +
                getReadableTokenName(node), node);
    }
  }

  /**
   * Initializes a message builder from a FUNCTION node.
   * <p>
   * <pre>
   * The tree should look something like:
   *
   * function
   *  |-- name
   *  |-- lp
   *  |   |-- name <arg1>
   *  |    -- name <arg2>
   *   -- block
   *      |
   *       --return
   *           |
   *            --add
   *               |-- string foo
   *                -- name <arg1>
   * </pre>
   *
   * @param builder the message builder
   * @param node the function node that contains a message
   * @throws MalformedException if the parsed message is invalid
   */
  private void extractFromFunctionNode(Builder builder, Node node)
      throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[134]++;
    Set<String> phNames = Sets.newHashSet();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[135]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[7]++;



    for (Node fnChild : node.children()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[7]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[8]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[9]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[136]++;
      switch (fnChild.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[69]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[137]++;
          // This is okay. The function has a name, but it is empty.
          break;
        case Token.PARAM_LIST:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[70]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[138]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[10]++;


          // Parse the placeholder names from the function argument list.
          for (Node argumentNode : fnChild.children()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[10]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[11]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[12]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[139]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((argumentNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[71]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[140]++;
              String phName = argumentNode.getString();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[141]++;
int CodeCoverConditionCoverageHelper_C29;
              if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((phNames.contains(phName)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[73]++;
                throw new MalformedException("Duplicate placeholder name: "
                    + phName, argumentNode);

              } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[74]++;
                phNames.add(phName);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[142]++;
              }

            } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[72]++;}
          }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[143]++;
          break;
        case Token.BLOCK:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[75]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[144]++;
          // Build the message's value by examining the return statement
          Node returnNode = fnChild.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[145]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((returnNode.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[76]++;
            throw new MalformedException("RETURN node expected; found: "
                + getReadableTokenName(returnNode), returnNode);

          } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[77]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[146]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[13]++;


          for (Node child : returnNode.children()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[13]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[14]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[15]++;
}
            extractFromReturnDescendant(builder, child);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[147]++;
          }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[148]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[16]++;



          // Check that all placeholders from the message text have appropriate
          // object literal keys
          for (String phName : builder.getPlaceholders()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[16]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[17]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[18]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[149]++;
int CodeCoverConditionCoverageHelper_C31;
            if((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((phNames.contains(phName)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[78]++;
              throw new MalformedException(
                  "Unrecognized message placeholder referenced: " + phName,
                  returnNode);

            } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[79]++;}
          }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[150]++;
          break;
        default:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[80]++;
          throw new MalformedException(
              "NAME, LP, or BLOCK node expected; found: "
                  + getReadableTokenName(node), fnChild);
      }
    }
  }

  /**
   * Appends value parts to the message builder by traversing the descendants
   * of the given RETURN node.
   *
   * @param builder the message builder
   * @param node the node from where we extract a message
   * @throws MalformedException if the parsed message is invalid
   */
  private void extractFromReturnDescendant(Builder builder, Node node)
      throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[151]++;

    switch (node.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[81]++;
        builder.appendStringPart(node.getString());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[152]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[153]++;
        break;
      case Token.NAME:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[82]++;
        builder.appendPlaceholderReference(node.getString());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[154]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[155]++;
        break;
      case Token.ADD:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[83]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[156]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[19]++;


        for (Node child : node.children()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[19]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[20]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[21]++;
}
          extractFromReturnDescendant(builder, child);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[157]++;
        }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[158]++;
        break;
      default:
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[84]++;
        throw new MalformedException(
            "STRING, NAME, or ADD node expected; found: "
                + getReadableTokenName(node), node);
    }
  }

  /**
   * Initializes a message builder from a CALL node.
   * <p>
   * The tree should look something like:
   *
   * <pre>
   * call
   *  |-- getprop
   *  |   |-- name 'goog'
   *  |   +-- string 'getMsg'
   *  |
   *  |-- string 'Hi {$userName}! Welcome to {$product}.'
   *  +-- objlit
   *      |-- string 'userName'
   *      |-- name 'someUserName'
   *      |-- string 'product'
   *      +-- call
   *          +-- name 'getProductName'
   * </pre>
   *
   * @param builder the message builder
   * @param node the call node from where we extract the message
   * @throws MalformedException if the parsed message is invalid
   */
  private void extractFromCallNode(Builder builder,
      Node node) throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[159]++;
int CodeCoverConditionCoverageHelper_C32;
    // Check the function being called
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((node.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[85]++;
      throw new MalformedException(
          "Message must be initialized using " + MSG_FUNCTION_NAME +
          " function.", node);

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[86]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[160]++;

    Node fnNameNode = node.getFirstChild();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[161]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((MSG_FUNCTION_NAME.equals(fnNameNode.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[87]++;
      throw new MalformedException(
          "Message initialized using unrecognized function. " +
          "Please use " + MSG_FUNCTION_NAME + "() instead.", fnNameNode);

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[88]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[162]++;

    // Get the message string
    Node stringLiteralNode = fnNameNode.getNext();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[163]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((stringLiteralNode == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[89]++;
      throw new MalformedException("Message string literal expected",
          stringLiteralNode);

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[90]++;}
    // Parse the message string and append parts to the builder
    parseMessageTextNode(builder, stringLiteralNode);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[164]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[165]++;

    Node objLitNode = stringLiteralNode.getNext();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[166]++;
    Set<String> phNames = Sets.newHashSet();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[167]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((objLitNode != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[91]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[168]++;
int CodeCoverConditionCoverageHelper_C36;
      // Register the placeholder names
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((objLitNode.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[93]++;
        throw new MalformedException("OBJLIT node expected", objLitNode);

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[94]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[169]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[22]++;


int CodeCoverConditionCoverageHelper_C37;
      for (Node aNode = objLitNode.getFirstChild();(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((aNode != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false);
           aNode = aNode.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[22]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[23]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[24]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[170]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((aNode.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[95]++;
          throw new MalformedException("STRING_KEY node expected as OBJLIT key",
              aNode);

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[96]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[171]++;
        String phName = aNode.getString();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[172]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((isLowerCamelCaseWithNumericSuffixes(phName)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[97]++;
          throw new MalformedException(
              "Placeholder name not in lowerCamelCase: " + phName, aNode);

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[98]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[173]++;
int CodeCoverConditionCoverageHelper_C40;

        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((phNames.contains(phName)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[99]++;
          throw new MalformedException("Duplicate placeholder name: "
              + phName, aNode);

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[100]++;}

        phNames.add(phName);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[174]++;
      }

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[92]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[175]++;

    // Check that all placeholders from the message text have appropriate objlit
    // values
    Set<String> usedPlaceholders = builder.getPlaceholders();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[176]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[25]++;


    for (String phName : usedPlaceholders) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[25]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[26]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[27]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[177]++;
int CodeCoverConditionCoverageHelper_C41;
      if((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((phNames.contains(phName)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[101]++;
        throw new MalformedException(
            "Unrecognized message placeholder referenced: " + phName,
            objLitNode);

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[102]++;}
    }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[178]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[28]++;



    // Check that objLiteral have only names that are present in the
    // message text
    for (String phName : phNames) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[28]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[29]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[30]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[179]++;
int CodeCoverConditionCoverageHelper_C42;
      if((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((usedPlaceholders.contains(phName)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[103]++;
        throw new MalformedException(
            "Unused message placeholder: " + phName,
            objLitNode);

      } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[104]++;}
    }
  }

  /**
   * Appends the message parts in a JS message value extracted from the given
   * text node.
   *
   * @param builder the JS message builder to append parts to
   * @param node the node with string literal that contains the message text
   * @throws MalformedException if {@code value} contains a reference to
   *         an unregistered placeholder
   */
  private void parseMessageTextNode(Builder builder, Node node)
      throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[180]++;
    String value = extractStringFromStringExprNode(node);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[181]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[31]++;



    while(true) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[31]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[32]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[33]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[182]++;
      int phBegin = value.indexOf(PH_JS_PREFIX);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[183]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((phBegin < 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[105]++;
        // Just a string literal
        builder.appendStringPart(value);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[184]++;
        return;

      } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[106]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[185]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((phBegin > 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[107]++;
          // A string literal followed by a placeholder
          builder.appendStringPart(value.substring(0, phBegin));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[186]++;

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[108]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[187]++;

        // A placeholder. Find where it ends
        int phEnd = value.indexOf(PH_JS_SUFFIX, phBegin);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[188]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((phEnd < 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[109]++;
          throw new MalformedException(
              "Placeholder incorrectly formatted in: " + builder.getKey(),
              node);

        } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[110]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[189]++;

        String phName = value.substring(phBegin + PH_JS_PREFIX.length(),
            phEnd);
        builder.appendPlaceholderReference(phName);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[190]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[191]++;
        int nextPos = phEnd + PH_JS_SUFFIX.length();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[192]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((nextPos < value.length()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[111]++;
          // Iterate on the rest of the message value
          value = value.substring(nextPos);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[193]++;

        } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[112]++;
          // The message is parsed
          return;
        }
      }
    }
  }

  /** Visit a call to goog.getMsgWithFallback. */
  private void visitFallbackFunctionCall(NodeTraversal t, Node call) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[194]++;
int CodeCoverConditionCoverageHelper_C48;
    // Check to make sure the function call looks like:
    // goog.getMsgWithFallback(MSG_1, MSG_2);
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (32)) == 0 || true) &&
 ((call.getChildCount() != 3) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((call.getChildAtIndex(1).isName()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((call.getChildAtIndex(2).isName()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[113]++;
      compiler.report(t.makeError(call, BAD_FALLBACK_SYNTAX));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[195]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[114]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[196]++;

    Node firstArg = call.getChildAtIndex(1);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[197]++;
    JsMessage firstMessage = getTrackedMessage(t, firstArg.getString());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[198]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((firstMessage == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[115]++;
      compiler.report(
          t.makeError(firstArg, FALLBACK_ARG_ERROR, firstArg.getString()));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[199]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[116]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[200]++;

    Node secondArg = firstArg.getNext();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[201]++;
    JsMessage secondMessage = getTrackedMessage(
        t, call.getChildAtIndex(2).getString());
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[202]++;
int CodeCoverConditionCoverageHelper_C50;
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((secondMessage == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[117]++;
      compiler.report(
          t.makeError(secondArg, FALLBACK_ARG_ERROR, secondArg.getString()));
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[203]++;
      return;

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[118]++;}

    processMessageFallback(call, firstMessage, secondMessage);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[204]++;
  }


  /**
   * Processes found JS message. Several examples of "standard" processing
   * routines are:
   * <ol>
   * <li>extract all JS messages
   * <li>replace JS messages with localized versions for some specific language
   * <li>check that messages have correct syntax and present in localization
   *     bundle
   * </ol>
   *
   * @param message the found message
   * @param definition the definition of the object and usually contains all
   *        additional message information like message node/parent's node
   */
  abstract void processJsMessage(JsMessage message,
      JsMessageDefinition definition);

  /**
   * Processes the goog.getMsgWithFallback primitive.
   * goog.getMsgWithFallback(MSG_1, MSG_2);
   *
   * By default, does nothing.
   */
  void processMessageFallback(Node callNode, JsMessage message1,
      JsMessage message2) {}

  /**
   * Returns whether the given JS identifier is a valid JS message name.
   */
  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
    return identifier.startsWith(MSG_PREFIX) &&
        (style == JsMessage.Style.CLOSURE || isNewStyleMessage ||
         !identifier.endsWith(DESC_SUFFIX));
  }

  /**
   * Returns whether the given message name is in the unnamed namespace.
   */
  private static boolean isUnnamedMessageName(String identifier) {
    return MSG_UNNAMED_PATTERN.matcher(identifier).matches();
  }

  /**
   * Returns whether a string is nonempty, begins with a lowercase letter, and
   * contains only digits and underscores after the first underscore.
   */
  static boolean isLowerCamelCaseWithNumericSuffixes(String input) {
    return CAMELCASE_PATTERN.matcher(input).matches();
  }

  /**
   * Returns human-readable name of the given node's type.
   */
  private static String getReadableTokenName(Node node) {
    return Token.name(node.getType());
  }

  /**
   * Converts the given string from upper-underscore case to lower-camel case,
   * preserving numeric suffixes. For example: "NAME" -> "name" "A4_LETTER" ->
   * "a4Letter" "START_SPAN_1_23" -> "startSpan_1_23".
   */
  static String toLowerCamelCaseWithNumericSuffixes(String input) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[205]++;
    // Determine where the numeric suffixes begin
    int suffixStart = input.length();
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[206]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[34]++;


int CodeCoverConditionCoverageHelper_C51;
    while ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((suffixStart > 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[34]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[35]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[36]++;
}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[207]++;
      char ch = '\0';
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[208]++;
      int numberStart = suffixStart;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[209]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[37]++;


int CodeCoverConditionCoverageHelper_C52;
      while ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((numberStart > 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[37]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[38]--;
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.loops[39]++;
}
        ch = input.charAt(numberStart - 1);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[210]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[211]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((Character.isDigit(ch)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[119]++;
          numberStart--;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[212]++;

        } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[120]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[213]++;
          break;
        }
      }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[214]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C54 |= (32)) == 0 || true) &&
 ((numberStart > 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((numberStart < suffixStart) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ch == '_') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[121]++;
        suffixStart = numberStart - 1;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[215]++;

      } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[122]++;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[216]++;
        break;
      }
    }
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[217]++;
int CodeCoverConditionCoverageHelper_C55;

    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((suffixStart == input.length()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[123]++;
      return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, input);

    } else {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[124]++;
      return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
          input.substring(0, suffixStart)) +
          input.substring(suffixStart);
    }
  }

  /**
   * Checks a node's type.
   *
   * @throws MalformedException if the node is null or the wrong type
   */
  protected void checkNode(@Nullable Node node, int type) throws MalformedException {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[218]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[125]++;
      throw new MalformedException(
          "Expected node type " + type + "; found: null", node);

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[126]++;}
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[219]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((node.getType() != type) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[127]++;
      throw new MalformedException(
          "Expected node type " + type + "; found: " + node.getType(), node);

    } else {
  CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.branches[128]++;}
  }

  static class MalformedException extends Exception {
    private static final long serialVersionUID = 1L;

    private final Node node;

    MalformedException(String message, Node node) {
      super(message);
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[220]++;
      this.node = node;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[221]++;
    }

    Node getNode() {
      return node;
    }
  }

  private static class MessageLocation {
    private final JsMessage message;
    private final Node messageNode;

    private MessageLocation(JsMessage message, Node messageNode) {
      this.message = message;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[222]++;
      this.messageNode = messageNode;
CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5.statements[223]++;
    }
  }
}

class CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5 ());
  }
    public static long[] statements = new long[224];
    public static long[] branches = new long[129];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[58];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JsMessageVisitor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,3,1,3,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,3,1,1,1,1,1,3,1,1,1};
    for (int i = 1; i <= 57; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$1puc7huw340be1k9mlyfefz1c5fj2f8f5 () {
    super("com.google.javascript.jscomp.JsMessageVisitor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 223; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 128; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JsMessageVisitor.java");
      for (int i = 1; i <= 223; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 128; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

