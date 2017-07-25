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

package com.google.javascript.jscomp.parsing;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.parsing.Config.LanguageMode;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.head.ErrorReporter;
import com.google.javascript.rhino.head.Token.CommentType;
import com.google.javascript.rhino.head.ast.ArrayLiteral;
import com.google.javascript.rhino.head.ast.Assignment;
import com.google.javascript.rhino.head.ast.AstNode;
import com.google.javascript.rhino.head.ast.AstRoot;
import com.google.javascript.rhino.head.ast.Block;
import com.google.javascript.rhino.head.ast.BreakStatement;
import com.google.javascript.rhino.head.ast.CatchClause;
import com.google.javascript.rhino.head.ast.Comment;
import com.google.javascript.rhino.head.ast.ConditionalExpression;
import com.google.javascript.rhino.head.ast.ContinueStatement;
import com.google.javascript.rhino.head.ast.DoLoop;
import com.google.javascript.rhino.head.ast.ElementGet;
import com.google.javascript.rhino.head.ast.EmptyExpression;
import com.google.javascript.rhino.head.ast.EmptyStatement;
import com.google.javascript.rhino.head.ast.ExpressionStatement;
import com.google.javascript.rhino.head.ast.ForInLoop;
import com.google.javascript.rhino.head.ast.ForLoop;
import com.google.javascript.rhino.head.ast.FunctionCall;
import com.google.javascript.rhino.head.ast.FunctionNode;
import com.google.javascript.rhino.head.ast.IfStatement;
import com.google.javascript.rhino.head.ast.InfixExpression;
import com.google.javascript.rhino.head.ast.KeywordLiteral;
import com.google.javascript.rhino.head.ast.Label;
import com.google.javascript.rhino.head.ast.LabeledStatement;
import com.google.javascript.rhino.head.ast.Name;
import com.google.javascript.rhino.head.ast.NewExpression;
import com.google.javascript.rhino.head.ast.NumberLiteral;
import com.google.javascript.rhino.head.ast.ObjectLiteral;
import com.google.javascript.rhino.head.ast.ObjectProperty;
import com.google.javascript.rhino.head.ast.ParenthesizedExpression;
import com.google.javascript.rhino.head.ast.PropertyGet;
import com.google.javascript.rhino.head.ast.RegExpLiteral;
import com.google.javascript.rhino.head.ast.ReturnStatement;
import com.google.javascript.rhino.head.ast.Scope;
import com.google.javascript.rhino.head.ast.StringLiteral;
import com.google.javascript.rhino.head.ast.SwitchCase;
import com.google.javascript.rhino.head.ast.SwitchStatement;
import com.google.javascript.rhino.head.ast.ThrowStatement;
import com.google.javascript.rhino.head.ast.TryStatement;
import com.google.javascript.rhino.head.ast.UnaryExpression;
import com.google.javascript.rhino.head.ast.VariableDeclaration;
import com.google.javascript.rhino.head.ast.VariableInitializer;
import com.google.javascript.rhino.head.ast.WhileLoop;
import com.google.javascript.rhino.head.ast.WithStatement;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.util.Set;

/**
 * IRFactory transforms the new AST to the old AST.
 *
 */
class IRFactory {
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.ping();
  }


  static final String GETTER_ERROR_MESSAGE =
      "getters are not supported in older versions of JS. " +
      "If you are targeting newer versions of JS, " +
      "set the appropriate language_in option.";
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[1]++;
  }

  static final String SETTER_ERROR_MESSAGE =
      "setters are not supported in older versions of JS. " +
      "If you are targeting newer versions of JS, " +
      "set the appropriate language_in option.";
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[2]++;
  }

  static final String SUSPICIOUS_COMMENT_WARNING =
      "Non-JSDoc comment has annotations. " +
      "Did you mean to start it with '/**'?";
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[3]++;
  }

  static final String MISPLACED_TYPE_ANNOTATION =
      "Type annotations are not allowed here. Are you missing parentheses?";
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[4]++;
  }

  private final String sourceString;
  private final StaticSourceFile sourceFile;
  private final String sourceName;
  private final Config config;
  private final ErrorReporter errorReporter;
  private final TransformDispatcher transformDispatcher;

  private static final ImmutableSet<String> ALLOWED_DIRECTIVES =
      ImmutableSet.of("use strict");
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[5]++;
  }

  private static final ImmutableSet<String> ES5_RESERVED_KEYWORDS =
      ImmutableSet.of(
          // From Section 7.6.1.2
          "class", "const", "enum", "export", "extends", "import", "super");
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[6]++;
  }
  private static final ImmutableSet<String> ES5_STRICT_RESERVED_KEYWORDS =
      ImmutableSet.of(
          // From Section 7.6.1.2
          "class", "const", "enum", "export", "extends", "import", "super",
          "implements", "interface", "let", "package", "private", "protected",
          "public", "static", "yield");
  static {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[7]++;
  }

  private final Set<String> reservedKeywords;
  private final Set<Comment> parsedComments = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[8]++;
  }

  // @license text gets appended onto the fileLevelJsDocBuilder as found,
  // and stored in JSDocInfo for placeholder node.
  Node rootNodeJsDocHolder = new Node(Token.SCRIPT);
  {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[9]++;
  }
  Node.FileLevelJsDocBuilder fileLevelJsDocBuilder =
      rootNodeJsDocHolder.getJsDocBuilderForNode();
  {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[10]++;
  }
  JSDocInfo fileOverviewInfo = null;
  {
    CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[11]++;
  }

  // Use a template node for properties set on all nodes to minimize the
  // memory footprint associated with these.
  private Node templateNode;

  // TODO(johnlenz): Consider creating a template pool for ORIGINALNAME_PROP.

  private IRFactory(String sourceString,
                    StaticSourceFile sourceFile,
                    Config config,
                    ErrorReporter errorReporter) {
    this.sourceString = sourceString;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[12]++;
    this.sourceFile = sourceFile;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[13]++;

    // Sometimes this will be null in tests.
    this.sourceName = sourceFile == null ? null : sourceFile.getName();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[14]++;

    this.config = config;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[15]++;
    this.errorReporter = errorReporter;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[16]++;
    this.transformDispatcher = new TransformDispatcher();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[17]++;
    // The template node properties are applied to all nodes in this transform.
    this.templateNode = createTemplateNode();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[18]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[19]++;

    switch (config.languageMode) {
      case ECMASCRIPT3:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[1]++;
        // Reserved words are handled by the Rhino parser.
        reservedKeywords = null;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[20]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[21]++;
        break;
      case ECMASCRIPT5:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[2]++;
        reservedKeywords = ES5_RESERVED_KEYWORDS;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[22]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[23]++;
        break;
      case ECMASCRIPT5_STRICT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[3]++;
        reservedKeywords = ES5_STRICT_RESERVED_KEYWORDS;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[24]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[25]++;
        break;
      default:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[4]++;
        throw new IllegalStateException("unknown language mode");
    }
  }

  // Create a template node to use as a source of common attributes, this allows
  // the prop structure to be shared among all the node from this source file.
  // This reduces the cost of these properties to O(nodes) to O(files).
  private Node createTemplateNode() {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[26]++;
    // The Node type choice is arbitrary.
    Node templateNode = new Node(Token.SCRIPT);
    templateNode.setStaticSourceFile(sourceFile);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[27]++;
    return templateNode;
  }

  public static Node transformTree(AstRoot node,
                                   StaticSourceFile sourceFile,
                                   String sourceString,
                                   Config config,
                                   ErrorReporter errorReporter) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[28]++;
    IRFactory irFactory = new IRFactory(sourceString, sourceFile,
        config, errorReporter);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[29]++;
    Node irNode = irFactory.transform(node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.getComments() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[5]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[1]++;


      for (Comment comment : node.getComments()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[1]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[2]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[3]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[32]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((comment.getCommentType() == CommentType.JSDOC) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((irFactory.parsedComments.contains(comment)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[7]++;
          irFactory.handlePossibleFileOverviewJsDoc(comment, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[33]++;

        } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[8]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[34]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((comment.getCommentType() == CommentType.BLOCK_COMMENT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[9]++;
          irFactory.handleBlockComment(comment);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[35]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[10]++;}
}
      }

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[6]++;}

    irFactory.setFileOverviewJsDoc(irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[36]++;

    return irNode;
  }

  private void setFileOverviewJsDoc(Node irNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[37]++;
    // Only after we've seen all @fileoverview entries, attach the
    // last one to the root node, and copy the found license strings
    // to that node.
    JSDocInfo rootNodeJsDoc = rootNodeJsDocHolder.getJSDocInfo();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((rootNodeJsDoc != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[11]++;
      irNode.setJSDocInfo(rootNodeJsDoc);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[39]++;
      rootNodeJsDoc.setAssociatedNode(irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[40]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[12]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((fileOverviewInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[13]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((irNode.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((irNode.getJSDocInfo().getLicense() != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[15]++;
        fileOverviewInfo.setLicense(irNode.getJSDocInfo().getLicense());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[43]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[16]++;}
      irNode.setJSDocInfo(fileOverviewInfo);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[44]++;
      fileOverviewInfo.setAssociatedNode(irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[45]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[14]++;}
  }

  private Node transformBlock(AstNode node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[46]++;
    Node irNode = transform(node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((irNode.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[17]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((irNode.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[19]++;
        irNode.setType(Token.BLOCK);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[49]++;
        irNode.setWasEmptyNode(true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[50]++;

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[20]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[51]++;
        Node newBlock = newNode(Token.BLOCK, irNode);
        newBlock.setLineno(irNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[52]++;
        newBlock.setCharno(irNode.getCharno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[53]++;
        maybeSetLengthFrom(newBlock, node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[54]++;
        irNode = newBlock;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[55]++;
      }

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[18]++;}
    return irNode;
  }

  /**
   * Check to see if the given block comment looks like it should be JSDoc.
   */
  private void handleBlockComment(Comment comment) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[56]++;
    String value = comment.getValue();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[57]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((value.indexOf("/* @") != -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value.indexOf("\n * @") != -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[21]++;
      errorReporter.warning(
          SUSPICIOUS_COMMENT_WARNING,
          sourceName,
          comment.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[58]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[22]++;}
  }

  /**
   * @return true if the jsDocParser represents a fileoverview.
   */
  private boolean handlePossibleFileOverviewJsDoc(
      JsDocInfoParser jsDocParser) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((jsDocParser.getFileOverviewJSDocInfo() != fileOverviewInfo) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[23]++;
      fileOverviewInfo = jsDocParser.getFileOverviewJSDocInfo();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[60]++;
      return true;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[24]++;}
    return false;
  }

  private void handlePossibleFileOverviewJsDoc(Comment comment, Node irNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[61]++;
    JsDocInfoParser jsDocParser = createJsDocInfoParser(comment, irNode);
    parsedComments.add(comment);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[62]++;
    handlePossibleFileOverviewJsDoc(jsDocParser);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[63]++;
  }

  private JSDocInfo handleJsDoc(AstNode node, Node irNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[64]++;
    Comment comment = node.getJsDocNode();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[65]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((comment != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[25]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[66]++;
      JsDocInfoParser jsDocParser = createJsDocInfoParser(comment, irNode);
      parsedComments.add(comment);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[67]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((handlePossibleFileOverviewJsDoc(jsDocParser)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[27]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[69]++;
        JSDocInfo info = jsDocParser.retrieveAndResetParsedJSDocInfo();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[70]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[29]++;
          validateTypeAnnotations(info, node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[71]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[30]++;}
        return info;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[28]++;}

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[26]++;}
    return null;
  }

  private void validateTypeAnnotations(JSDocInfo info, AstNode node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[72]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[31]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[73]++;
      boolean valid = false;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[74]++;
      switch (node.getType()) {
        // Casts are valid
        case com.google.javascript.rhino.head.Token.LP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[33]++;
          valid = node instanceof ParenthesizedExpression;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[75]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[76]++;
          break;
        // Variable declarations are valid
        case com.google.javascript.rhino.head.Token.VAR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[34]++;
          valid = true;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[77]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[78]++;
          break;
        // Function declarations are valid
        case com.google.javascript.rhino.head.Token.FUNCTION:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[35]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[79]++;
          FunctionNode fnNode = (FunctionNode)node;
          valid = fnNode.getFunctionType() == FunctionNode.FUNCTION_STATEMENT;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[80]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[81]++;
          break;
        // Object literal properties and catch declarations are valid.
        case com.google.javascript.rhino.head.Token.NAME:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[36]++;
          valid = node.getParent() instanceof ObjectProperty
              || node.getParent() instanceof CatchClause
              || node.getParent() instanceof FunctionNode;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[82]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[83]++;
          break;
        // Object literal properties are valid
        case com.google.javascript.rhino.head.Token.GET:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[37]++;
        case com.google.javascript.rhino.head.Token.SET:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[38]++;
        case com.google.javascript.rhino.head.Token.NUMBER:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[39]++;
        case com.google.javascript.rhino.head.Token.STRING:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[40]++;
          valid = node.getParent() instanceof ObjectProperty;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[84]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[85]++;
          break;

        // Property assignments are valid, if at the root of an expression.
        case com.google.javascript.rhino.head.Token.ASSIGN:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[41]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[86]++;
int CodeCoverConditionCoverageHelper_C15;
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((node instanceof Assignment) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[42]++;
            valid = isExprStmt(node.getParent())
                && isPropAccess(((Assignment)node).getLeft());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[87]++;

          } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[43]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[88]++;
          break;

        // Property definitions are valid, if at the root of an expression.
        case com.google.javascript.rhino.head.Token.GETPROP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[44]++;
        case com.google.javascript.rhino.head.Token.GETELEM:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[45]++;
          valid = isExprStmt(node.getParent());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[89]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[90]++;
          break; default : CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[46]++;
      }
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[91]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((valid) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[47]++;
        errorReporter.warning(MISPLACED_TYPE_ANNOTATION,
            sourceName,
            node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[92]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[48]++;}

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[32]++;}
  }

  private boolean isPropAccess(AstNode node) {
    return node.getType() == com.google.javascript.rhino.head.Token.GETPROP
        || node.getType() == com.google.javascript.rhino.head.Token.GETELEM;
  }

  private boolean isExprStmt(AstNode node) {
    return node.getType() == com.google.javascript.rhino.head.Token.EXPR_RESULT
        || node.getType() == com.google.javascript.rhino.head.Token.EXPR_VOID;
  }

  private Node transform(AstNode node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[93]++;
    Node irNode = justTransform(node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[94]++;
    JSDocInfo jsDocInfo = handleJsDoc(node, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[95]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((jsDocInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[49]++;
      irNode = maybeInjectCastNode(node, jsDocInfo, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[96]++;
      irNode.setJSDocInfo(jsDocInfo);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[97]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[50]++;}
    setSourceInfo(irNode, node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[98]++;
    return irNode;
  }

  private Node maybeInjectCastNode(AstNode node, JSDocInfo info, Node irNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[99]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (128)) == 0 || true) &&
 ((node.getType() == com.google.javascript.rhino.head.Token.LP) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((node instanceof ParenthesizedExpression) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((irNode.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[51]++;
      irNode = newNode(Token.CAST, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[100]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[52]++;}
    return irNode;
  }

  /**
   * Parameter NAMEs are special, because they can have inline type docs
   * attached.
   *
   * function f(/** string &#42;/ x) {}
   * annotates 'x' as a string.
   *
   * @see http://code.google.com/p/jsdoc-toolkit/wiki/InlineDocs
   */
  private Node transformParameter(AstNode node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[101]++;
    Node irNode = justTransform(node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[102]++;
    Comment comment = node.getJsDocNode();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[103]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((comment != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[53]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[104]++;
      JSDocInfo info = parseInlineTypeDoc(comment, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[105]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[55]++;
        irNode.setJSDocInfo(info);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[106]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[56]++;}

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[54]++;}
    setSourceInfo(irNode, node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[107]++;
    return irNode;
  }

  private Node transformNameAsString(Name node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[108]++;
    Node irNode = transformDispatcher.processName(node, true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[109]++;
    JSDocInfo jsDocInfo = handleJsDoc(node, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[110]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((jsDocInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[57]++;
      irNode.setJSDocInfo(jsDocInfo);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[111]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[58]++;}
    setSourceInfo(irNode, node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[112]++;
    return irNode;
  }

  private Node transformNumberAsString(NumberLiteral literalNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[113]++;
    Node irNode = newStringNode(getStringValue(literalNode.getNumber()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[114]++;
    JSDocInfo jsDocInfo = handleJsDoc(literalNode, irNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[115]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((jsDocInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[59]++;
      irNode.setJSDocInfo(jsDocInfo);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[116]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[60]++;}
    setSourceInfo(irNode, literalNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[117]++;
    return irNode;
  }

  private static String getStringValue(double value) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[118]++;
    long longValue = (long) value;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[119]++;
int CodeCoverConditionCoverageHelper_C23;

    // Return "1" instead of "1.0"
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((longValue == value) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[61]++;
      return Long.toString(longValue);

    } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[62]++;
      return Double.toString(value);
    }
  }

  private void setSourceInfo(Node irNode, AstNode node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[120]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((irNode.getLineno() == -1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[63]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[121]++;
      // If we didn't already set the line, then set it now. This avoids
      // cases like ParenthesizedExpression where we just return a previous
      // node, but don't want the new node to get its parent's line number.
      int lineno = node.getLineno();
      irNode.setLineno(lineno);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[122]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[123]++;
      int charno = position2charno(node.getAbsolutePosition());
      irNode.setCharno(charno);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[124]++;
      maybeSetLengthFrom(irNode, node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[125]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[64]++;}
  }

  /**
   * Creates a JsDocInfoParser and parses the JsDoc string.
   *
   * Used both for handling individual JSDoc comments and for handling
   * file-level JSDoc comments (@fileoverview and @license).
   *
   * @param node The JsDoc Comment node to parse.
   * @param irNode
   * @return A JsDocInfoParser. Will contain either fileoverview JsDoc, or
   *     normal JsDoc, or no JsDoc (if the method parses to the wrong level).
   */
  private JsDocInfoParser createJsDocInfoParser(Comment node, Node irNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[126]++;
    String comment = node.getValue();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[127]++;
    int lineno = node.getLineno();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[128]++;
    int position = node.getAbsolutePosition();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[129]++;

    // The JsDocInfoParser expects the comment without the initial '/**'.
    int numOpeningChars = 3;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[130]++;
    JsDocInfoParser jsdocParser =
      new JsDocInfoParser(
          new JsDocTokenStream(comment.substring(numOpeningChars),
                               lineno,
                               position2charno(position) + numOpeningChars),
          node,
          irNode,
          config,
          errorReporter);
    jsdocParser.setFileLevelJsDocBuilder(fileLevelJsDocBuilder);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[131]++;
    jsdocParser.setFileOverviewJSDocInfo(fileOverviewInfo);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[132]++;
    jsdocParser.parse();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[133]++;
    return jsdocParser;
  }

  /**
   * Parses inline type info.
   */
  private JSDocInfo parseInlineTypeDoc(Comment node, Node irNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[134]++;
    String comment = node.getValue();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[135]++;
    int lineno = node.getLineno();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[136]++;
    int position = node.getAbsolutePosition();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[137]++;

    // The JsDocInfoParser expects the comment without the initial '/**'.
    int numOpeningChars = 3;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[138]++;
    JsDocInfoParser parser =
      new JsDocInfoParser(
          new JsDocTokenStream(comment.substring(numOpeningChars),
                               lineno,
                               position2charno(position) + numOpeningChars),
          node,
          irNode,
          config,
          errorReporter);
    return parser.parseInlineTypeDoc();
  }

  // Set the length on the node if we're in IDE mode.
  private void maybeSetLengthFrom(Node node, AstNode source) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[139]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((config.isIdeMode) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[65]++;
      node.setLength(source.getLength());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[140]++;

    } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[66]++;}
  }

  private int position2charno(int position) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[141]++;
    int lineIndex = sourceString.lastIndexOf('\n', position);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[142]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lineIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[67]++;
      return position;

    } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[68]++;
      // Subtract one for initial position being 0.
      return position - lineIndex - 1;
    }
  }

  private Node justTransform(AstNode node) {
    return transformDispatcher.process(node);
  }

  private class TransformDispatcher extends TypeSafeDispatcher<Node> {
    private Node processGeneric(
        com.google.javascript.rhino.head.Node n) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[143]++;
      Node node = newNode(transformTokenType(n.getType()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[144]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[4]++;


      for (com.google.javascript.rhino.head.Node child : n) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[4]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[5]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[6]++;
}
        node.addChildToBack(transform((AstNode)child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[145]++;
      }
      return node;
    }

    /**
     * Transforms the given node and then sets its type to Token.STRING if it
     * was Token.NAME. If its type was already Token.STRING, then quotes it.
     * Used for properties, as the old AST uses String tokens, while the new one
     * uses Name tokens for unquoted strings. For example, in
     * var o = {'a' : 1, b: 2};
     * the string 'a' is quoted, while the name b is turned into a string, but
     * unquoted.
     */
    private Node transformAsString(AstNode n) {
      Node ret;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[146]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((n instanceof Name) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[69]++;
        ret = transformNameAsString((Name)n);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[147]++;

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[70]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[148]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n instanceof NumberLiteral) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[71]++;
        ret = transformNumberAsString((NumberLiteral)n);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[149]++;
        ret.putBooleanProp(Node.QUOTED_PROP, true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[150]++;

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[72]++;
        ret = transform(n);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[151]++;
        ret.putBooleanProp(Node.QUOTED_PROP, true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[152]++;
      }
}
      Preconditions.checkState(ret.isString());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[153]++;
      return ret;
    }

    @Override
    Node processArrayLiteral(ArrayLiteral literalNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[154]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((literalNode.isDestructuring()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[73]++;
        reportDestructuringAssign(literalNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[155]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[74]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[156]++;

      Node node = newNode(Token.ARRAYLIT);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[157]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[7]++;


      for (AstNode child : literalNode.getElements()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[7]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[8]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[9]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[158]++;
        Node c = transform(child);
        node.addChildToBack(c);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[159]++;
      }
      return node;
    }

    @Override
    Node processAssignment(Assignment assignmentNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[160]++;
      Node assign = processInfixExpression(assignmentNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[161]++;
      Node target = assign.getFirstChild();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[162]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((validAssignmentTarget(target)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[75]++;
        errorReporter.error(
          "invalid assignment target",
          sourceName,
          target.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[163]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[76]++;}
      return assign;
    }

    @Override
    Node processAstRoot(AstRoot rootNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[164]++;
      Node node = newNode(Token.SCRIPT);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[165]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[10]++;


      for (com.google.javascript.rhino.head.Node child : rootNode) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[10]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[11]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[12]++;
}
        node.addChildToBack(transform((AstNode)child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[166]++;
      }
      parseDirectives(node);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[167]++;
      return node;
    }

    /**
     * Parse the directives, encode them in the AST, and remove their nodes.
     *
     * For information on ES5 directives, see section 14.1 of
     * ECMA-262, Edition 5.
     *
     * It would be nice if Rhino would eventually take care of this for
     * us, but right now their directive-processing is a one-off.
     */
    private void parseDirectives(Node node) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[168]++;
      // Remove all the directives, and encode them in the AST.
      Set<String> directives = null;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[169]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[13]++;


int CodeCoverConditionCoverageHelper_C31;
      while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isDirective(node.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[13]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[14]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[15]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[170]++;
        String directive = node.removeFirstChild().getFirstChild().getString();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[171]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((directives == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[77]++;
          directives = Sets.newHashSet(directive);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[172]++;

        } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[78]++;
          directives.add(directive);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[173]++;
        }
      }
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[174]++;
int CodeCoverConditionCoverageHelper_C33;

      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((directives != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[79]++;
        node.setDirectives(directives);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[175]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[80]++;}
    }

    private boolean isDirective(Node n) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[176]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[81]++; return false;
} else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[82]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[177]++;

      int nType = n.getType();
      return nType == Token.EXPR_RESULT &&
          n.getFirstChild().isString() &&
          ALLOWED_DIRECTIVES.contains(n.getFirstChild().getString());
    }

    @Override
    Node processBlock(Block blockNode) {
      return processGeneric(blockNode);
    }

    @Override
    Node processBreakStatement(BreakStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[178]++;
      Node node = newNode(Token.BREAK);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[179]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((statementNode.getBreakLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[83]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[180]++;
        Node labelName = transform(statementNode.getBreakLabel());
        // Change the NAME to LABEL_NAME
        labelName.setType(Token.LABEL_NAME);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[181]++;
        node.addChildToBack(labelName);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[182]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[84]++;}
      return node;
    }

    @Override
    Node processCatchClause(CatchClause clauseNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[183]++;
      AstNode catchVar = clauseNode.getVarName();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[184]++;
      Node node = newNode(Token.CATCH, transform(catchVar));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[185]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((clauseNode.getCatchCondition() != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[85]++;
        errorReporter.error(
            "Catch clauses are not supported",
            sourceName,
            clauseNode.getCatchCondition().getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[186]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[86]++;}
      node.addChildToBack(transformBlock(clauseNode.getBody()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[187]++;
      return node;
    }

    @Override
    Node processConditionalExpression(ConditionalExpression exprNode) {
      return newNode(
          Token.HOOK,
          transform(exprNode.getTestExpression()),
          transform(exprNode.getTrueExpression()),
          transform(exprNode.getFalseExpression()));
    }

    @Override
    Node processContinueStatement(ContinueStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[188]++;
      Node node = newNode(Token.CONTINUE);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[189]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((statementNode.getLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[87]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[190]++;
        Node labelName = transform(statementNode.getLabel());
        // Change the NAME to LABEL_NAME
        labelName.setType(Token.LABEL_NAME);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[191]++;
        node.addChildToBack(labelName);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[192]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[88]++;}
      return node;
    }

    @Override
    Node processDoLoop(DoLoop loopNode) {
      return newNode(
          Token.DO,
          transformBlock(loopNode.getBody()),
          transform(loopNode.getCondition()));
    }

    @Override
    Node processElementGet(ElementGet getNode) {
      return newNode(
          Token.GETELEM,
          transform(getNode.getTarget()),
          transform(getNode.getElement()));
    }

    @Override
    Node processEmptyExpression(EmptyExpression exprNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[193]++;
      Node node = newNode(Token.EMPTY);
      return node;
    }

    @Override
    Node processEmptyStatement(EmptyStatement exprNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[194]++;
      Node node = newNode(Token.EMPTY);
      return node;
    }

    @Override
    Node processExpressionStatement(ExpressionStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[195]++;
      Node node = newNode(transformTokenType(statementNode.getType()));
      node.addChildToBack(transform(statementNode.getExpression()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[196]++;
      return node;
    }

    @Override
    Node processForInLoop(ForInLoop loopNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[197]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((loopNode.isForEach()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[89]++;
        errorReporter.error(
            "unsupported language extension: for each",
            sourceName,
            loopNode.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[198]++;

        // Return the bare minimum to put the AST in a valid state.
        return newNode(Token.EXPR_RESULT, Node.newNumber(0));

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[90]++;}
      return newNode(
          Token.FOR,
          transform(loopNode.getIterator()),
          transform(loopNode.getIteratedObject()),
          transformBlock(loopNode.getBody()));
    }

    @Override
    Node processForLoop(ForLoop loopNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[199]++;
      Node node = newNode(
          Token.FOR,
          transform(loopNode.getInitializer()),
          transform(loopNode.getCondition()),
          transform(loopNode.getIncrement()));
      node.addChildToBack(transformBlock(loopNode.getBody()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[200]++;
      return node;
    }

    @Override
    Node processFunctionCall(FunctionCall callNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[201]++;
      Node node = newNode(transformTokenType(callNode.getType()),
                           transform(callNode.getTarget()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[202]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[16]++;


      for (AstNode child : callNode.getArguments()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[16]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[17]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[18]++;
}
        node.addChildToBack(transform(child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[203]++;
      }

      node.setLineno(node.getFirstChild().getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[204]++;
      node.setCharno(node.getFirstChild().getCharno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[205]++;
      maybeSetLengthFrom(node, callNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[206]++;
      return node;
    }

    @Override
    Node processFunctionNode(FunctionNode functionNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[207]++;
      Name name = functionNode.getFunctionName();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[208]++;
      Boolean isUnnamedFunction = false;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[209]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[91]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[210]++;
        int functionType = functionNode.getFunctionType();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[211]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((functionType != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[93]++;
          errorReporter.error(
            "unnamed function statement",
            sourceName,
            functionNode.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[212]++;

          // Return the bare minimum to put the AST in a valid state.
          return newNode(Token.EXPR_RESULT, Node.newNumber(0));

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[94]++;}
        name = new Name();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[213]++;
        name.setIdentifier("");
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[214]++;
        isUnnamedFunction = true;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[215]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[92]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[216]++;
      Node node = newNode(Token.FUNCTION);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[217]++;
      Node newName = transform(name);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[218]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isUnnamedFunction) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[95]++;
        // Old Rhino tagged the empty name node with the line number of the
        // declaration.
        newName.setLineno(functionNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[219]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[220]++;
        // TODO(bowdidge) Mark line number of paren correctly.
        // Same problem as below - the left paren might not be on the
        // same line as the function keyword.
        int lpColumn = functionNode.getAbsolutePosition() +
            functionNode.getLp();
        newName.setCharno(position2charno(lpColumn));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[221]++;
        maybeSetLengthFrom(newName, name);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[222]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[96]++;}

      node.addChildToBack(newName);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[223]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[224]++;
      Node lp = newNode(Token.PARAM_LIST);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[225]++;
      // The left paren's complicated because it's not represented by an
      // AstNode, so there's nothing that has the actual line number that it
      // appeared on.  We know the paren has to appear on the same line as the
      // function name (or else a semicolon will be inserted.)  If there's no
      // function name, assume the paren was on the same line as the function.
      // TODO(bowdidge): Mark line number of paren correctly.
      Name fnName = functionNode.getFunctionName();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[226]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((fnName != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[97]++;
        lp.setLineno(fnName.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[227]++;

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[98]++;
        lp.setLineno(functionNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[228]++;
      }
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[229]++;
      int lparenCharno = functionNode.getLp() +
          functionNode.getAbsolutePosition();

      lp.setCharno(position2charno(lparenCharno));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[230]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[231]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[19]++;


      for (AstNode param : functionNode.getParams()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[19]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[20]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[21]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[232]++;
        Node paramNode = transformParameter(param);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[233]++;
int CodeCoverConditionCoverageHelper_C43;
        // When in ideMode Rhino can generate a param list with only a single
        // ErrorNode. This is transformed into an EMPTY node. Drop this node in
        // ideMode to keep the AST in a valid state.
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((paramNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[99]++;
          lp.addChildToBack(paramNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[234]++;

        } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[100]++;
          // We expect this in ideMode or when there is an error handling
          // destructuring parameter assignments which aren't supported
          // (an error has already been reported).
          Preconditions.checkState(
              config.isIdeMode
              || paramNode.isObjectLit()
              || paramNode.isArrayLit());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[235]++;
        }
      }
      node.addChildToBack(lp);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[236]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[237]++;

      Node bodyNode = transform(functionNode.getBody());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[238]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((bodyNode.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[101]++;
        // When in ideMode Rhino tries to parse some constructs the compiler
        // doesn't support, repair it here. see Rhino's
        // Parser#parseFunctionBodyExpr.
        Preconditions.checkState(config.isIdeMode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[239]++;
        bodyNode = IR.block();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[240]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[102]++;}
      parseDirectives(bodyNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[241]++;
      node.addChildToBack(bodyNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[242]++;
     return node;
    }

    @Override
    Node processIfStatement(IfStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[243]++;
      Node node = newNode(Token.IF);
      node.addChildToBack(transform(statementNode.getCondition()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[244]++;
      node.addChildToBack(transformBlock(statementNode.getThenPart()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[245]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[246]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((statementNode.getElsePart() != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[103]++;
        node.addChildToBack(transformBlock(statementNode.getElsePart()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[247]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[104]++;}
      return node;
    }

    @Override
    Node processInfixExpression(InfixExpression exprNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[248]++;
      Node n =  newNode(
          transformTokenType(exprNode.getType()),
          transform(exprNode.getLeft()),
          transform(exprNode.getRight()));
      n.setLineno(exprNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[249]++;
      n.setCharno(position2charno(exprNode.getAbsolutePosition()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[250]++;
      maybeSetLengthFrom(n, exprNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[251]++;
      return n;
    }

    @Override
    Node processKeywordLiteral(KeywordLiteral literalNode) {
      return newNode(transformTokenType(literalNode.getType()));
    }

    @Override
    Node processLabel(Label labelNode) {
      return newStringNode(Token.LABEL_NAME, labelNode.getName());
    }

    @Override
    Node processLabeledStatement(LabeledStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[252]++;
      Node node = newNode(Token.LABEL);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[253]++;
      Node prev = null;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[254]++;
      Node cur = node;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[255]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[22]++;


      for (Label label : statementNode.getLabels()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[22]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[23]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[24]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[256]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((prev != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[105]++;
          prev.addChildToBack(cur);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[257]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[106]++;}
        cur.addChildToBack(transform(label));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[258]++;

        cur.setLineno(label.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[259]++;
        maybeSetLengthFrom(cur, label);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[260]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[261]++;

        int clauseAbsolutePosition =
            position2charno(label.getAbsolutePosition());
        cur.setCharno(clauseAbsolutePosition);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[262]++;

        prev = cur;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[263]++;
        cur = newNode(Token.LABEL);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[264]++;
      }
      prev.addChildToBack(transform(statementNode.getStatement()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[265]++;
      return node;
    }

    @Override
    Node processName(Name nameNode) {
      return processName(nameNode, false);
    }

    Node processName(Name nameNode, boolean asString) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[266]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((asString) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[107]++;
        return newStringNode(Token.STRING, nameNode.getIdentifier());

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[108]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[267]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isReservedKeyword(nameNode.getIdentifier())) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[109]++;
          errorReporter.error(
            "identifier is a reserved word",
            sourceName,
            nameNode.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[268]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[110]++;}
        return newStringNode(Token.NAME, nameNode.getIdentifier());
      }
    }

    /**
     * @return Whether the
     */
    private boolean isReservedKeyword(String identifier) {
      return reservedKeywords != null && reservedKeywords.contains(identifier);
    }

    @Override
    Node processNewExpression(NewExpression exprNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[269]++;
      Node node = newNode(
          transformTokenType(exprNode.getType()),
          transform(exprNode.getTarget()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[270]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[25]++;


      for (AstNode child : exprNode.getArguments()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[25]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[26]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[27]++;
}
        node.addChildToBack(transform(child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[271]++;
      }
      node.setLineno(exprNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[272]++;
      node.setCharno(position2charno(exprNode.getAbsolutePosition()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[273]++;
      maybeSetLengthFrom(node, exprNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[274]++;
      return node;
    }

    @Override
    Node processNumberLiteral(NumberLiteral literalNode) {
      return newNumberNode(literalNode.getNumber());
    }

    @Override
    Node processObjectLiteral(ObjectLiteral literalNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[275]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((literalNode.isDestructuring()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[111]++;
        reportDestructuringAssign(literalNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[276]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[112]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[277]++;

      Node node = newNode(Token.OBJECTLIT);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[278]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[28]++;


      for (ObjectProperty el : literalNode.getElements()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[28]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[29]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[30]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[279]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((config.languageMode == LanguageMode.ECMASCRIPT3) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[113]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[280]++;
int CodeCoverConditionCoverageHelper_C51;
          if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((el.isGetter()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[115]++;
            reportGetter(el);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[281]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[282]++;
            continue;

          } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[116]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[283]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((el.isSetter()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[117]++;
            reportSetter(el);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[284]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[285]++;
            continue;

          } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[118]++;}
}

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[114]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[286]++;

        Node key = transformAsString(el.getLeft());
        key.setType(Token.STRING_KEY);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[287]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[288]++;

        Node value = transform(el.getRight());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[289]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((el.isGetter()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[119]++;
          key.setType(Token.GETTER_DEF);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[290]++;
          Preconditions.checkState(value.isFunction());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[291]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[292]++;
int CodeCoverConditionCoverageHelper_C54;
          if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((getFnParamNode(value).hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[121]++;
            reportGetterParam(el.getLeft());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[293]++;

          } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[122]++;}

        } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[120]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[294]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((el.isSetter()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[123]++;
          key.setType(Token.SETTER_DEF);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[295]++;
          Preconditions.checkState(value.isFunction());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[296]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[297]++;
int CodeCoverConditionCoverageHelper_C56;
          if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((getFnParamNode(value).hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[125]++;
            reportSetterParam(el.getLeft());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[298]++;

          } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[126]++;}

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[124]++;}
}
        key.addChildToFront(value);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[299]++;
        node.addChildToBack(key);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[300]++;
      }
      return node;
    }

    /**
     * @param fnNode The function.
     * @return The Node containing the Function parameters.
     */
   Node getFnParamNode(Node fnNode) {
     // Function NODE: [ FUNCTION -> NAME, LP -> ARG1, ARG2, ... ]
     Preconditions.checkArgument(fnNode.isFunction());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[301]++;
     return fnNode.getFirstChild().getNext();
   }

    @Override
    Node processObjectProperty(ObjectProperty propertyNode) {
      return processInfixExpression(propertyNode);
    }

    @Override
    Node processParenthesizedExpression(ParenthesizedExpression exprNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[302]++;
      Node node = transform(exprNode.getExpression());
      return node;
    }

    @Override
    Node processPropertyGet(PropertyGet getNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[303]++;
      Node leftChild = transform(getNode.getTarget());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[304]++;
      Node newNode = newNode(
          Token.GETPROP, leftChild, transformAsString(getNode.getProperty()));
      newNode.setLineno(leftChild.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[305]++;
      newNode.setCharno(leftChild.getCharno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[306]++;
      maybeSetLengthFrom(newNode, getNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[307]++;
      return newNode;
    }

    @Override
    Node processRegExpLiteral(RegExpLiteral literalNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[308]++;
      Node literalStringNode = newStringNode(literalNode.getValue());
      // assume it's on the same line.
      literalStringNode.setLineno(literalNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[309]++;
      maybeSetLengthFrom(literalStringNode, literalNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[310]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[311]++;
      Node node = newNode(Token.REGEXP, literalStringNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[312]++;
      String flags = literalNode.getFlags();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[313]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((flags != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((flags.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[127]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[314]++;
        Node flagsNode = newStringNode(flags);
        // Assume the flags are on the same line as the literal node.
        flagsNode.setLineno(literalNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[315]++;
        maybeSetLengthFrom(flagsNode, literalNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[316]++;
        node.addChildToBack(flagsNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[317]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[128]++;}
      return node;
    }

    @Override
    Node processReturnStatement(ReturnStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[318]++;
      Node node = newNode(Token.RETURN);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[319]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((statementNode.getReturnValue() != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[129]++;
        node.addChildToBack(transform(statementNode.getReturnValue()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[320]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[130]++;}
      return node;
    }

    @Override
    Node processScope(Scope scopeNode) {
      return processGeneric(scopeNode);
    }

    @Override
    Node processStringLiteral(StringLiteral literalNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[321]++;
      String value = literalNode.getValue();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[322]++;
      Node n = newStringNode(value);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[323]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((value.indexOf('\u000B') != -1) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[131]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[324]++;
        // NOTE(nicksantos): In JavaScript, there are 3 ways to
        // represent a vertical tab: \v, \x0B, \u000B.
        // The \v notation was added later, and is not understood
        // on IE. So we need to preserve it as-is. This is really
        // obnoxious, because we do not have a good way to represent
        // how the original string was encoded without making the
        // representation of strings much more complicated.
        //
        // To handle this, we look at the original source test, and
        // mark the string as \v-encoded or not. If a string is
        // \v encoded, then all the vertical tabs in that string
        // will be encoded with a \v.
        int start = literalNode.getAbsolutePosition();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[325]++;
        int end = start + literalNode.getLength();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[326]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((start < sourceString.length()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((sourceString.substring(
                 start, Math.min(sourceString.length(), end))
             .indexOf("\\v") != -1) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[133]++;
          n.putBooleanProp(Node.SLASH_V, true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[327]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[134]++;}

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[132]++;}
      return n;
    }

    @Override
    Node processSwitchCase(SwitchCase caseNode) {
      Node node;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[328]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((caseNode.isDefault()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[135]++;
        node = newNode(Token.DEFAULT_CASE);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[329]++;

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[136]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[330]++;
        AstNode expr = caseNode.getExpression();
        node = newNode(Token.CASE, transform(expr));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[331]++;
      }
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[332]++;
      Node block = newNode(Token.BLOCK);
      block.putBooleanProp(Node.SYNTHETIC_BLOCK_PROP, true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[333]++;
      block.setLineno(caseNode.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[334]++;
      block.setCharno(position2charno(caseNode.getAbsolutePosition()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[335]++;
      maybeSetLengthFrom(block, caseNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[336]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[337]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((caseNode.getStatements() != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[137]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[338]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[31]++;


        for (AstNode child : caseNode.getStatements()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[31]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[32]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[33]++;
}
          block.addChildToBack(transform(child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[339]++;
        }

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[138]++;}
      node.addChildToBack(block);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[340]++;
      return node;
    }

    @Override
    Node processSwitchStatement(SwitchStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[341]++;
      Node node = newNode(Token.SWITCH,
          transform(statementNode.getExpression()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[342]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[34]++;


      for (AstNode child : statementNode.getCases()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[34]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[35]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[36]++;
}
        node.addChildToBack(transform(child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[343]++;
      }
      return node;
    }

    @Override
    Node processThrowStatement(ThrowStatement statementNode) {
      return newNode(Token.THROW,
          transform(statementNode.getExpression()));
    }

    @Override
    Node processTryStatement(TryStatement statementNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[344]++;
      Node node = newNode(Token.TRY,
          transformBlock(statementNode.getTryBlock()));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[345]++;
      Node block = newNode(Token.BLOCK);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[346]++;
      boolean lineSet = false;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[347]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[37]++;



      for (CatchClause cc : statementNode.getCatchClauses()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[37]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[38]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[39]++;
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[348]++;
int CodeCoverConditionCoverageHelper_C63;
        // Mark the enclosing block at the same line as the first catch
        // clause.
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((lineSet == false) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[139]++;
          block.setLineno(cc.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[349]++;
          maybeSetLengthFrom(block, cc);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[350]++;
          lineSet = true;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[351]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[140]++;}
        block.addChildToBack(transform(cc));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[352]++;
      }
      node.addChildToBack(block);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[353]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[354]++;

      AstNode finallyBlock = statementNode.getFinallyBlock();
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[355]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[141]++;
        node.addChildToBack(transformBlock(finallyBlock));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[356]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[142]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[357]++;
int CodeCoverConditionCoverageHelper_C65;

      // If we didn't set the line on the catch clause, then
      // we've got an empty catch clause.  Set its line to be the same
      // as the finally block (to match Old Rhino's behavior.)
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((lineSet == false) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[143]++;
        block.setLineno(finallyBlock.getLineno());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[358]++;
        maybeSetLengthFrom(block, finallyBlock);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[359]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[144]++;}

      return node;
    }

    @Override
    Node processUnaryExpression(UnaryExpression exprNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[360]++;
      int type = transformTokenType(exprNode.getType());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[361]++;
      Node operand = transform(exprNode.getOperand());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[362]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((type == Token.NEG) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((operand.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[145]++;
        operand.setDouble(-operand.getDouble());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[363]++;
        return operand;

      } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[146]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[364]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (128)) == 0 || true) &&
 ((type == Token.DELPROP) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (64)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C67 |= (32)) == 0 || true) &&
 ((operand.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((operand.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((operand.isName()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 4) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 4) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[147]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[365]++;
          String msg =
              "Invalid delete operand. Only properties can be deleted.";
          errorReporter.error(
              msg,
              sourceName,
              operand.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[366]++;

        } else {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[148]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[367]++;
int CodeCoverConditionCoverageHelper_C68;  if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((type == Token.INC) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((type == Token.DEC) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[149]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[368]++;
int CodeCoverConditionCoverageHelper_C69;
          if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((validAssignmentTarget(operand)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[151]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[369]++;
            String msg = (type == Token.INC)
                ? "invalid increment target"
                : "invalid decrement target";
            errorReporter.error(
                msg,
                sourceName,
                operand.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[370]++;

          } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[152]++;}

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[150]++;}
}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[371]++;

        Node node = newNode(type, operand);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[372]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((exprNode.isPostfix()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[153]++;
          node.putBooleanProp(Node.INCRDECR_PROP, true);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[373]++;

        } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[154]++;}
        return node;
      }
    }

    private boolean validAssignmentTarget(Node target) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[374]++;
      switch (target.getType()) {
        case Token.CAST:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[155]++; // CAST is a bit weird, but syntactically valid.
        case Token.NAME:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[156]++;
        case Token.GETPROP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[157]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[158]++;
          return true; default : CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[159]++;
      }
      return false;
    }

    @Override
    Node processVariableDeclaration(VariableDeclaration declarationNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[375]++;
int CodeCoverConditionCoverageHelper_C71;
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((config.acceptConstKeyword) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((declarationNode.getType() ==
          com.google.javascript.rhino.head.Token.CONST) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[160]++;
        processIllegalToken(declarationNode);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[376]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[161]++;}
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[377]++;

      Node node = newNode(Token.VAR);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[378]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[40]++;


      for (VariableInitializer child : declarationNode.getVariables()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[40]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[41]--;
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.loops[42]++;
}
        node.addChildToBack(transform(child));
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[379]++;
      }
      return node;
    }

    @Override
    Node processVariableInitializer(VariableInitializer initializerNode) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[380]++;
      Node node = transform(initializerNode.getTarget());
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[381]++;
int CodeCoverConditionCoverageHelper_C72;
      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((initializerNode.getInitializer() != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$339026hg2nefroryalxobl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[162]++;
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[382]++;
        Node initalizer = transform(initializerNode.getInitializer());
        node.addChildToBack(initalizer);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[383]++;

      } else {
  CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[163]++;}
      return node;
    }

    @Override
    Node processWhileLoop(WhileLoop loopNode) {
      return newNode(
          Token.WHILE,
          transform(loopNode.getCondition()),
          transformBlock(loopNode.getBody()));
    }

    @Override
    Node processWithStatement(WithStatement statementNode) {
      return newNode(
          Token.WITH,
          transform(statementNode.getExpression()),
          transformBlock(statementNode.getStatement()));
    }

    @Override
    Node processIllegalToken(AstNode node) {
      errorReporter.error(
          "Unsupported syntax: " +
          com.google.javascript.rhino.head.Token.typeToName(
              node.getType()),
          sourceName,
          node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[384]++;
      return newNode(Token.EMPTY);
    }

    void reportDestructuringAssign(AstNode node) {
      errorReporter.error(
          "destructuring assignment forbidden",
          sourceName,
          node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[385]++;
    }

    void reportGetter(AstNode node) {
      errorReporter.error(
          GETTER_ERROR_MESSAGE,
          sourceName,
          node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[386]++;
    }

    void reportSetter(AstNode node) {
      errorReporter.error(
          SETTER_ERROR_MESSAGE,
          sourceName,
          node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[387]++;
    }

    void reportGetterParam(AstNode node) {
      errorReporter.error(
          "getters may not have parameters",
          sourceName,
          node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[388]++;
    }

    void reportSetterParam(AstNode node) {
      errorReporter.error(
          "setters must have exactly one parameter",
          sourceName,
          node.getLineno(), "", 0);
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[389]++;
    }
  }

  private static int transformTokenType(int token) {
CodeCoverCoverageCounter$339026hg2nefroryalxobl.statements[390]++;
    switch (token) {
      case com.google.javascript.rhino.head.Token.RETURN:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[164]++;
        return Token.RETURN;
      case com.google.javascript.rhino.head.Token.BITOR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[165]++;
        return Token.BITOR;
      case com.google.javascript.rhino.head.Token.BITXOR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[166]++;
        return Token.BITXOR;
      case com.google.javascript.rhino.head.Token.BITAND:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[167]++;
        return Token.BITAND;
      case com.google.javascript.rhino.head.Token.EQ:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[168]++;
        return Token.EQ;
      case com.google.javascript.rhino.head.Token.NE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[169]++;
        return Token.NE;
      case com.google.javascript.rhino.head.Token.LT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[170]++;
        return Token.LT;
      case com.google.javascript.rhino.head.Token.LE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[171]++;
        return Token.LE;
      case com.google.javascript.rhino.head.Token.GT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[172]++;
        return Token.GT;
      case com.google.javascript.rhino.head.Token.GE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[173]++;
        return Token.GE;
      case com.google.javascript.rhino.head.Token.LSH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[174]++;
        return Token.LSH;
      case com.google.javascript.rhino.head.Token.RSH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[175]++;
        return Token.RSH;
      case com.google.javascript.rhino.head.Token.URSH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[176]++;
        return Token.URSH;
      case com.google.javascript.rhino.head.Token.ADD:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[177]++;
        return Token.ADD;
      case com.google.javascript.rhino.head.Token.SUB:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[178]++;
        return Token.SUB;
      case com.google.javascript.rhino.head.Token.MUL:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[179]++;
        return Token.MUL;
      case com.google.javascript.rhino.head.Token.DIV:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[180]++;
        return Token.DIV;
      case com.google.javascript.rhino.head.Token.MOD:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[181]++;
        return Token.MOD;
      case com.google.javascript.rhino.head.Token.NOT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[182]++;
        return Token.NOT;
      case com.google.javascript.rhino.head.Token.BITNOT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[183]++;
        return Token.BITNOT;
      case com.google.javascript.rhino.head.Token.POS:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[184]++;
        return Token.POS;
      case com.google.javascript.rhino.head.Token.NEG:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[185]++;
        return Token.NEG;
      case com.google.javascript.rhino.head.Token.NEW:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[186]++;
        return Token.NEW;
      case com.google.javascript.rhino.head.Token.DELPROP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[187]++;
        return Token.DELPROP;
      case com.google.javascript.rhino.head.Token.TYPEOF:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[188]++;
        return Token.TYPEOF;
      case com.google.javascript.rhino.head.Token.GETPROP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[189]++;
        return Token.GETPROP;
      case com.google.javascript.rhino.head.Token.GETELEM:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[190]++;
        return Token.GETELEM;
      case com.google.javascript.rhino.head.Token.CALL:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[191]++;
        return Token.CALL;
      case com.google.javascript.rhino.head.Token.NAME:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[192]++;
        return Token.NAME;
      case com.google.javascript.rhino.head.Token.NUMBER:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[193]++;
        return Token.NUMBER;
      case com.google.javascript.rhino.head.Token.STRING:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[194]++;
        return Token.STRING;
      case com.google.javascript.rhino.head.Token.NULL:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[195]++;
        return Token.NULL;
      case com.google.javascript.rhino.head.Token.THIS:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[196]++;
        return Token.THIS;
      case com.google.javascript.rhino.head.Token.FALSE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[197]++;
        return Token.FALSE;
      case com.google.javascript.rhino.head.Token.TRUE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[198]++;
        return Token.TRUE;
      case com.google.javascript.rhino.head.Token.SHEQ:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[199]++;
        return Token.SHEQ;
      case com.google.javascript.rhino.head.Token.SHNE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[200]++;
        return Token.SHNE;
      case com.google.javascript.rhino.head.Token.REGEXP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[201]++;
        return Token.REGEXP;
      case com.google.javascript.rhino.head.Token.THROW:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[202]++;
        return Token.THROW;
      case com.google.javascript.rhino.head.Token.IN:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[203]++;
        return Token.IN;
      case com.google.javascript.rhino.head.Token.INSTANCEOF:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[204]++;
        return Token.INSTANCEOF;
      case com.google.javascript.rhino.head.Token.ARRAYLIT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[205]++;
        return Token.ARRAYLIT;
      case com.google.javascript.rhino.head.Token.OBJECTLIT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[206]++;
        return Token.OBJECTLIT;
      case com.google.javascript.rhino.head.Token.TRY:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[207]++;
        return Token.TRY;
      // The LP represents a parameter list
      case com.google.javascript.rhino.head.Token.LP:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[208]++;
        return Token.PARAM_LIST;
      case com.google.javascript.rhino.head.Token.COMMA:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[209]++;
        return Token.COMMA;
      case com.google.javascript.rhino.head.Token.ASSIGN:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[210]++;
        return Token.ASSIGN;
      case com.google.javascript.rhino.head.Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[211]++;
        return Token.ASSIGN_BITOR;
      case com.google.javascript.rhino.head.Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[212]++;
        return Token.ASSIGN_BITXOR;
      case com.google.javascript.rhino.head.Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[213]++;
        return Token.ASSIGN_BITAND;
      case com.google.javascript.rhino.head.Token.ASSIGN_LSH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[214]++;
        return Token.ASSIGN_LSH;
      case com.google.javascript.rhino.head.Token.ASSIGN_RSH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[215]++;
        return Token.ASSIGN_RSH;
      case com.google.javascript.rhino.head.Token.ASSIGN_URSH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[216]++;
        return Token.ASSIGN_URSH;
      case com.google.javascript.rhino.head.Token.ASSIGN_ADD:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[217]++;
        return Token.ASSIGN_ADD;
      case com.google.javascript.rhino.head.Token.ASSIGN_SUB:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[218]++;
        return Token.ASSIGN_SUB;
      case com.google.javascript.rhino.head.Token.ASSIGN_MUL:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[219]++;
        return Token.ASSIGN_MUL;
      case com.google.javascript.rhino.head.Token.ASSIGN_DIV:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[220]++;
        return Token.ASSIGN_DIV;
      case com.google.javascript.rhino.head.Token.ASSIGN_MOD:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[221]++;
        return Token.ASSIGN_MOD;
      case com.google.javascript.rhino.head.Token.HOOK:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[222]++;
        return Token.HOOK;
      case com.google.javascript.rhino.head.Token.OR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[223]++;
        return Token.OR;
      case com.google.javascript.rhino.head.Token.AND:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[224]++;
        return Token.AND;
      case com.google.javascript.rhino.head.Token.INC:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[225]++;
        return Token.INC;
      case com.google.javascript.rhino.head.Token.DEC:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[226]++;
        return Token.DEC;
      case com.google.javascript.rhino.head.Token.FUNCTION:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[227]++;
        return Token.FUNCTION;
      case com.google.javascript.rhino.head.Token.IF:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[228]++;
        return Token.IF;
      case com.google.javascript.rhino.head.Token.SWITCH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[229]++;
        return Token.SWITCH;
      case com.google.javascript.rhino.head.Token.CASE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[230]++;
        return Token.CASE;
      case com.google.javascript.rhino.head.Token.DEFAULT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[231]++;
        return Token.DEFAULT_CASE;
      case com.google.javascript.rhino.head.Token.WHILE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[232]++;
        return Token.WHILE;
      case com.google.javascript.rhino.head.Token.DO:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[233]++;
        return Token.DO;
      case com.google.javascript.rhino.head.Token.FOR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[234]++;
        return Token.FOR;
      case com.google.javascript.rhino.head.Token.BREAK:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[235]++;
        return Token.BREAK;
      case com.google.javascript.rhino.head.Token.CONTINUE:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[236]++;
        return Token.CONTINUE;
      case com.google.javascript.rhino.head.Token.VAR:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[237]++;
        return Token.VAR;
      case com.google.javascript.rhino.head.Token.WITH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[238]++;
        return Token.WITH;
      case com.google.javascript.rhino.head.Token.CATCH:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[239]++;
        return Token.CATCH;
      case com.google.javascript.rhino.head.Token.VOID:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[240]++;
        return Token.VOID;
      case com.google.javascript.rhino.head.Token.EMPTY:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[241]++;
        return Token.EMPTY;
      case com.google.javascript.rhino.head.Token.BLOCK:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[242]++;
        return Token.BLOCK;
      case com.google.javascript.rhino.head.Token.LABEL:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[243]++;
        return Token.LABEL;
      case com.google.javascript.rhino.head.Token.EXPR_VOID:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[244]++;
      case com.google.javascript.rhino.head.Token.EXPR_RESULT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[245]++;
        return Token.EXPR_RESULT;
      case com.google.javascript.rhino.head.Token.SCRIPT:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[246]++;
        return Token.SCRIPT;
      case com.google.javascript.rhino.head.Token.GET:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[247]++;
        return Token.GETTER_DEF;
      case com.google.javascript.rhino.head.Token.SET:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[248]++;
        return Token.SETTER_DEF;
      case com.google.javascript.rhino.head.Token.CONST:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[249]++;
        return Token.CONST;
      case com.google.javascript.rhino.head.Token.DEBUGGER:
CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[250]++;
        return Token.DEBUGGER; default : CodeCoverCoverageCounter$339026hg2nefroryalxobl.branches[251]++;
    }

    // Token without name
    throw new IllegalStateException(String.valueOf(token));
  }

  // Simple helper to create nodes and set the initial node properties.
  private Node newNode(int type) {
    return new Node(type).clonePropsFrom(templateNode);
  }

  private Node newNode(int type, Node child1) {
    return new Node(type, child1).clonePropsFrom(templateNode);
  }

  private Node newNode(int type, Node child1, Node child2) {
    return new Node(type, child1, child2).clonePropsFrom(templateNode);
  }

  private Node newNode(int type, Node child1, Node child2, Node child3) {
    return new Node(type, child1, child2, child3).clonePropsFrom(templateNode);
  }

  private Node newStringNode(String value) {
    return IR.string(value).clonePropsFrom(templateNode);
  }

  private Node newStringNode(int type, String value) {
    return Node.newString(type, value).clonePropsFrom(templateNode);
  }

  private Node newNumberNode(Double value) {
    return IR.number(value).clonePropsFrom(templateNode);
  }
}

class CodeCoverCoverageCounter$339026hg2nefroryalxobl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$339026hg2nefroryalxobl ());
  }
    public static long[] statements = new long[391];
    public static long[] branches = new long[252];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.parsing.IRFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,2,3,2,1,1,2,1};
    for (int i = 1; i <= 72; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$339026hg2nefroryalxobl () {
    super("com.google.javascript.jscomp.parsing.IRFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 390; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 251; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.IRFactory.java");
      for (int i = 1; i <= 390; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 251; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

