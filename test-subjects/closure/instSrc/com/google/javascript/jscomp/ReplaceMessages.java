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

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Iterator;

import javax.annotation.Nullable;

/**
 * ReplaceMessages replaces user-visible messages with alternatives.
 * It uses Google specific JsMessageVisitor implementation.
 *
 */
class ReplaceMessages extends JsMessageVisitor {
  static {
    CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.ping();
  }

  private final MessageBundle bundle;
  private final boolean strictReplacement;

  static final DiagnosticType BUNDLE_DOES_NOT_HAVE_THE_MESSAGE =
      DiagnosticType.error("JSC_BUNDLE_DOES_NOT_HAVE_THE_MESSAGE",
          "Message with id = {0} could not be found in replacement bundle");
  static {
    CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[1]++;
  }

  ReplaceMessages(AbstractCompiler compiler, MessageBundle bundle,
      boolean checkDuplicatedMessages, JsMessage.Style style,
      boolean strictReplacement) {

    super(compiler, checkDuplicatedMessages, style, bundle.idGenerator());
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[2]++;

    this.bundle = bundle;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[3]++;
    this.strictReplacement = strictReplacement;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[4]++;
  }

  @Override
  void processMessageFallback(
      Node callNode, JsMessage message1, JsMessage message2) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[5]++;
    boolean isFirstMessageTranslated =
        (bundle.getMessage(message1.getId()) != null);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[6]++;
    boolean isSecondMessageTranslated =
        (bundle.getMessage(message2.getId()) != null);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[7]++;
    Node replacementNode =
        isSecondMessageTranslated && !isFirstMessageTranslated ?
        callNode.getChildAtIndex(2) : callNode.getChildAtIndex(1);
    callNode.getParent().replaceChild(callNode,
        replacementNode.detachFromParent());
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[8]++;
  }

  @Override
  void processJsMessage(JsMessage message,
      JsMessageDefinition definition) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[9]++;

    // Get the replacement.
    JsMessage replacement = bundle.getMessage(message.getId());
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((replacement == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[1]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((strictReplacement) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[3]++;
        compiler.report(JSError.make(message.getSourceName(),
            definition.getMessageNode(), BUNDLE_DOES_NOT_HAVE_THE_MESSAGE,
            message.getId()));
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[12]++;
        // Fallback to the default message
        return;

      } else {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[4]++;
        // In case if it is not a strict replacement we could leave original
        // message.
        replacement = message;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[13]++;
      }

    } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[2]++;}

    // Replace the message.
    Node newValue;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[14]++;
    Node msgNode = definition.getMessageNode();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[15]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      newValue = getNewValueNode(replacement, msgNode);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[16]++;
    } catch (MalformedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[6]++;
      compiler.report(JSError.make(message.getSourceName(),
          e.getNode(), MESSAGE_TREE_MALFORMED, e.getMessage()));
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[17]++;
      newValue = msgNode;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[18]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[5]++;
}
  }
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((newValue != msgNode) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[7]++;
      newValue.copyInformationFromForTree(msgNode);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[20]++;
      definition.getMessageParentNode().replaceChild(msgNode, newValue);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[21]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[22]++;

    } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[8]++;}
  }

  /**
   * Constructs a node representing a message's value, or, if possible, just
   * modifies {@code origValueNode} so that it accurately represents the
   * message's value.
   *
   * @param message  a message
   * @param origValueNode  the message's original value node
   * @return a Node that can replace {@code origValueNode}
   *
   * @throws MalformedException if the passed node's subtree structure is
   *   not as expected
   */
  private Node getNewValueNode(JsMessage message, Node origValueNode)
      throws MalformedException {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[23]++;
    switch (origValueNode.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[9]++;
        // The message is a function. Modify the function node.
        updateFunctionNode(message, origValueNode);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[24]++;
        return origValueNode;
      case Token.STRING:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[10]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[25]++;
        // The message is a simple string. Modify the string node.
        String newString = message.toString();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((origValueNode.getString().equals(newString)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[11]++;
          origValueNode.setString(newString);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[27]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[28]++;

        } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[12]++;}
        return origValueNode;
      case Token.ADD:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[13]++;
        // The message is a simple string. Create a string node.
        return IR.string(message.toString());
      case Token.CALL:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[14]++;
        // The message is a function call. Replace it with a string expression.
        return replaceCallNode(message, origValueNode);
      default:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[15]++;
        throw new MalformedException(
            "Expected FUNCTION, STRING, or ADD node; found: " +
                origValueNode.getType(), origValueNode);
    }
  }

  /**
   * Updates the descendants of a FUNCTION node to represent a message's value.
   * <p>
   * The tree looks something like:
   * <pre>
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
   * @param message  a message
   * @param functionNode  the message's original FUNCTION value node
   *
   * @throws MalformedException if the passed node's subtree structure is
   *         not as expected
   */
  private void updateFunctionNode(JsMessage message, Node functionNode)
      throws MalformedException {
    checkNode(functionNode, Token.FUNCTION);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[29]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[30]++;
    Node nameNode = functionNode.getFirstChild();
    checkNode(nameNode, Token.NAME);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[31]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[32]++;
    Node argListNode = nameNode.getNext();
    checkNode(argListNode, Token.PARAM_LIST);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[33]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[34]++;
    Node oldBlockNode = argListNode.getNext();
    checkNode(oldBlockNode, Token.BLOCK);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[35]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[36]++;

    Iterator<CharSequence> iterator = message.parts().iterator();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[37]++;
    Node valueNode = iterator.hasNext()
        ? constructAddOrStringNode(iterator, argListNode)
        : IR.string("");
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[38]++;
    Node newBlockNode = IR.block(IR.returnNode(valueNode));
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[39]++;
int CodeCoverConditionCoverageHelper_C5;

    // TODO(user): checkTreeEqual is overkill. I am in process of rewriting
    // these functions.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((newBlockNode.checkTreeEquals(oldBlockNode) != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[16]++;
      newBlockNode.copyInformationFromForTree(oldBlockNode);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[40]++;
      functionNode.replaceChild(oldBlockNode, newBlockNode);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[41]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[42]++;

    } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[17]++;}
  }

  /**
   * Creates a parse tree corresponding to the remaining message parts in
   * an iteration. The result will contain only STRING nodes, NAME nodes
   * (corresponding to placeholder references), and/or ADD nodes used to
   * combine the other two types.
   *
   * @param partsIterator  an iterator over message parts
   * @param argListNode  an LP node whose children are valid placeholder names
   * @return the root of the constructed parse tree
   *
   * @throws MalformedException if {@code partsIterator} contains a
   *   placeholder reference that does not correspond to a valid argument in
   *   the arg list
   */
  private Node constructAddOrStringNode(Iterator<CharSequence> partsIterator,
                                        Node argListNode)
      throws MalformedException {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[43]++;
    CharSequence part = partsIterator.next();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[44]++;
    Node partNode = null;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((part instanceof JsMessage.PlaceholderReference) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[18]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[46]++;
      JsMessage.PlaceholderReference phRef =
          (JsMessage.PlaceholderReference) part;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[47]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[1]++;



      for (Node node : argListNode.children()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[1]--;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[2]--;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[3]++;
}
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[48]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[20]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[49]++;
          String arg = node.getString();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;

          // We ignore the case here because the transconsole only supports
          // uppercase placeholder names, but function arguments in JavaScript
          // code can have mixed case.
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((arg.equalsIgnoreCase(phRef.getName())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[22]++;
            partNode = IR.name(arg);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[51]++;

          } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[23]++;}

        } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[21]++;}
      }
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[52]++;
int CodeCoverConditionCoverageHelper_C9;

      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((partNode == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[24]++;
        throw new MalformedException(
            "Unrecognized message placeholder referenced: " + phRef.getName(),
            argListNode);

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[25]++;}

    } else {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[19]++;
      // The part is just a string literal.
      partNode = IR.string(part.toString());
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[53]++;
    }
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((partsIterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[26]++;
      return IR.add(partNode,
                      constructAddOrStringNode(partsIterator, argListNode));

    } else {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[27]++;
      return partNode;
    }
  }

  /**
   * Replaces a CALL node with an inlined message value.
   *  <p>
   * The call tree looks something like:
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
   * <pre>
   * <p>
   * For that example, we'd return:
   * <pre>
   * add
   *  |-- string 'Hi '
   *  +-- add
   *      |-- name someUserName
   *      +-- add
   *          |-- string '! Welcome to '
   *          +-- add
   *              |-- call
   *              |   +-- name 'getProductName'
   *              +-- string '.'
   * </pre>
   * @param message  a message
   * @param callNode  the message's original CALL value node
   * @return a STRING node, or an ADD node that does string concatenation, if
   *   the message has one or more placeholders
   *
   * @throws MalformedException if the passed node's subtree structure is
   *   not as expected
   */
  private Node replaceCallNode(JsMessage message, Node callNode)
      throws MalformedException {
    checkNode(callNode, Token.CALL);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[55]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[56]++;
    Node getPropNode = callNode.getFirstChild();
    checkNode(getPropNode, Token.GETPROP);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[57]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[58]++;
    Node stringExprNode = getPropNode.getNext();
    checkStringExprNode(stringExprNode);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[59]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[60]++;
    Node objLitNode = stringExprNode.getNext();

    // Build the replacement tree.
    return constructStringExprNode(message.parts().iterator(), objLitNode);
  }

  /**
   * Creates a parse tree corresponding to the remaining message parts in an
   * iteration. The result consists of one or more STRING nodes, placeholder
   * replacement value nodes (which can be arbitrary expressions), and ADD
   * nodes.
   *
   * @param parts  an iterator over message parts
   * @param objLitNode  an OBJLIT node mapping placeholder names to values
   * @return the root of the constructed parse tree
   *
   * @throws MalformedException if {@code parts} contains a placeholder
   *   reference that does not correspond to a valid placeholder name
   */
  private Node constructStringExprNode(Iterator<CharSequence> parts,
      Node objLitNode) throws MalformedException {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[61]++;

    CharSequence part = parts.next();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[62]++;
    Node partNode = null;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((part instanceof JsMessage.PlaceholderReference) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[28]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[64]++;
      JsMessage.PlaceholderReference phRef =
          (JsMessage.PlaceholderReference) part;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[65]++;
int CodeCoverConditionCoverageHelper_C12;

      // The translated message is null
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((objLitNode == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[30]++;
        throw new MalformedException("Empty placeholder value map " +
            "for a translated message with placeholders.", objLitNode);

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[31]++;}
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[66]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;

      for (Node key = objLitNode.getFirstChild();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false);
           key = key.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[4]--;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[5]--;
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.loops[6]++;
}
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((key.getString().equals(phRef.getName())) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[32]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[68]++;
          Node valueNode = key.getFirstChild();
          partNode = valueNode.cloneTree();
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[69]++;

        } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[33]++;}
      }
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[70]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((partNode == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[34]++;
        throw new MalformedException(
            "Unrecognized message placeholder referenced: " + phRef.getName(),
            objLitNode);

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[35]++;}

    } else {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[29]++;
      // The part is just a string literal.
      partNode = IR.string(part.toString());
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[71]++;
    }
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[72]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((parts.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[36]++;
      return IR.add(partNode,
          constructStringExprNode(parts, objLitNode));

    } else {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[37]++;
      return partNode;
    }
  }

  /**
   * Checks that a node is a valid string expression (either a string literal
   * or a concatenation of string literals).
   *
   * @throws IllegalArgumentException if the node is null or the wrong type
   */
  private void checkStringExprNode(@Nullable Node node) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[73]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[38]++;
      throw new IllegalArgumentException("Expected a string; found: null");

    } else {
  CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[39]++;}
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[74]++;
    switch (node.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[40]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[75]++;
        break;
      case Token.ADD:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[41]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[76]++;
        Node c = node.getFirstChild();
        checkStringExprNode(c);
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[77]++;
        checkStringExprNode(c.getNext());
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[78]++;
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.statements[79]++;
        break;
      default:
CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t.branches[42]++;
        throw new IllegalArgumentException(
            "Expected a string; found: " + node.getType());
    }
  }
}

class CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t ());
  }
    public static long[] statements = new long[80];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReplaceMessages.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$9mhqpbfe2by04bqexweeocbg9qpu89t () {
    super("com.google.javascript.jscomp.ReplaceMessages.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 79; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ReplaceMessages.java");
      for (int i = 1; i <= 79; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

