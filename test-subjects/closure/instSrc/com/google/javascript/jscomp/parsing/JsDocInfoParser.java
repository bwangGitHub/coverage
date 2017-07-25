/*
 * Copyright 2007 The Closure Compiler Authors.
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
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.parsing.Config.LanguageMode;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSDocInfo.Visibility;
import com.google.javascript.rhino.JSDocInfoBuilder;
import com.google.javascript.rhino.JSTypeExpression;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.ScriptRuntime;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.head.ErrorReporter;
import com.google.javascript.rhino.head.ast.Comment;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A parser for JSDoc comments.
 *
 */
// TODO(nicksantos): Unify all the JSDocInfo stuff into one package, instead of
// spreading it across multiple packages.
public final class JsDocInfoParser {
  static {
    CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.ping();
  }


  private final JsDocTokenStream stream;
  private final JSDocInfoBuilder jsdocBuilder;
  private final StaticSourceFile sourceFile;
  private final Node associatedNode;
  private final ErrorReporter errorReporter;
  private final ErrorReporterParser parser = new ErrorReporterParser();
  {
    CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[1]++;
  }

  // Use a template node for properties set on all nodes to minimize the
  // memory footprint associated with these (similar to IRFactory).
  private final Node templateNode;

  private class ErrorReporterParser {
    void addParserWarning(String messageId, String messageArg, int lineno,
        int charno) {
      errorReporter.warning(ScriptRuntime.getMessage1(messageId, messageArg),
          getSourceName(), lineno, null, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[2]++;
    }

    void addParserWarning(String messageId, int lineno, int charno) {
      errorReporter.warning(ScriptRuntime.getMessage0(messageId),
          getSourceName(), lineno, null, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[3]++;
    }

    void addTypeWarning(String messageId, String messageArg, int lineno,
                    int charno) {
      errorReporter.warning(
          "Bad type annotation. " +
          ScriptRuntime.getMessage1(messageId, messageArg),
          getSourceName(), lineno, null, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[4]++;
    }

    void addTypeWarning(String messageId, int lineno, int charno) {
      errorReporter.warning(
          "Bad type annotation. " +
          ScriptRuntime.getMessage0(messageId),
          getSourceName(), lineno, null, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[5]++;
    }
  }

  // The DocInfo with the fileoverview tag for the whole file.
  private JSDocInfo fileOverviewJSDocInfo = null;
  {
    CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[6]++;
  }
  private State state;

  private final Map<String, Annotation> annotationNames;
  private final Set<String> suppressionNames;
  static private final Set<String> modifiesAnnotationKeywords =
      ImmutableSet.<String>of("this", "arguments");
  static {
    CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[7]++;
  }

  private Node.FileLevelJsDocBuilder fileLevelJsDocBuilder;

  /**
   * Sets the JsDocBuilder for the file-level (root) node of this parse. The
   * parser uses the builder to append any preserve annotations it encounters
   * in JsDoc comments.
   *
   * @param fileLevelJsDocBuilder
   */
  void setFileLevelJsDocBuilder(
      Node.FileLevelJsDocBuilder fileLevelJsDocBuilder) {
    this.fileLevelJsDocBuilder = fileLevelJsDocBuilder;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[8]++;
  }

  /**
   * Sets the file overview JSDocInfo, in order to warn about multiple uses of
   * the @fileoverview tag in a file.
   */
  void setFileOverviewJSDocInfo(JSDocInfo fileOverviewJSDocInfo) {
    this.fileOverviewJSDocInfo = fileOverviewJSDocInfo;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[9]++;
  }

  private enum State {
    SEARCHING_ANNOTATION,
    SEARCHING_NEWLINE,
    NEXT_IS_ANNOTATION
  }

  JsDocInfoParser(JsDocTokenStream stream,
                  Comment commentNode,
                  Node associatedNode,
                  Config config,
                  ErrorReporter errorReporter) {
    this.stream = stream;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[10]++;
    this.associatedNode = associatedNode;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[11]++;

    // Sometimes this will be null in tests.
    this.sourceFile = associatedNode == null
        ? null : associatedNode.getStaticSourceFile();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[12]++;

    this.jsdocBuilder = new JSDocInfoBuilder(config.parseJsDocDocumentation);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[13]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((commentNode != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[1]++;
      this.jsdocBuilder.recordOriginalCommentString(commentNode.getValue());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[15]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[2]++;}
    this.annotationNames = config.annotationNames;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[16]++;
    this.suppressionNames = config.suppressionNames;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[17]++;

    this.errorReporter = errorReporter;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[18]++;
    this.templateNode = this.createTemplateNode();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[19]++;
  }

  private String getSourceName() {
    return sourceFile == null ? null : sourceFile.getName();
  }

  public JSDocInfo parseInlineTypeDoc() {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[20]++;
    Node typeAst = parseAndRecordTypeNode(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[21]++;
    JSTypeExpression expr = createJSTypeExpression(typeAst);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((expr != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[3]++;
      jsdocBuilder.recordType(expr);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[23]++;
      return retrieveAndResetParsedJSDocInfo();

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[4]++;}
    return null;
  }

  /**
   * Parses a string containing a JsDoc type declaration, returning the
   * type if the parsing succeeded or {@code null} if it failed.
   */
  public static Node parseTypeString(String typeString) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[24]++;
    Config config = new Config(
        Sets.<String>newHashSet(),
        Sets.<String>newHashSet(),
        false,
        LanguageMode.ECMASCRIPT3,
        false);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[25]++;
    JsDocInfoParser parser = new JsDocInfoParser(
        new JsDocTokenStream(typeString),
        null,
        null,
        config,
        NullErrorReporter.forNewRhino());

    return parser.parseTopLevelTypeExpression(parser.next());
  }

  /**
   * Parses a {@link JSDocInfo} object. This parsing method reads all tokens
   * returned by the {@link JsDocTokenStream#getJsDocToken()} method until the
   * {@link JsDocToken#EOC} is returned.
   *
   * @return {@code true} if JSDoc information was correctly parsed,
   *     {@code false} otherwise
   */
  @SuppressWarnings("incomplete-switch")
  boolean parse() {
    int lineno;
    int charno;

    // JSTypes are represented as Rhino AST nodes, and then resolved later.
    JSTypeExpression type;

    state = State.SEARCHING_ANNOTATION;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[26]++;
    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[27]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[28]++;

    JsDocToken token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[29]++;

    List<ExtendedTypeInfo> extendedTypes = Lists.newArrayList();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;

    // Always record that we have a comment.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[5]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[31]++;
      ExtractionInfo blockInfo = extractBlockComment(token);
      token = blockInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[32]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((blockInfo.string.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[7]++;
        jsdocBuilder.recordBlockDescription(blockInfo.string);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[34]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[8]++;}

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[6]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((token != JsDocToken.ANNOTATION) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((token != JsDocToken.EOC) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[9]++;
        // Mark that there was a description, but don't bother marking
        // what it was.
        jsdocBuilder.recordBlockDescription("");
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[36]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[10]++;}
    }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[37]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[1]++;



    // Parse the actual JsDoc.
    retry: for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[1]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[2]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[3]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[38]++;
      switch (token) {
        case ANNOTATION:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[11]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((state == State.SEARCHING_ANNOTATION) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[12]++;
            state = State.SEARCHING_NEWLINE;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[40]++;
            lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[41]++;
            charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[42]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[43]++;

            String annotationName = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[44]++;
            Annotation annotation = annotationNames.get(annotationName);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[14]++;
              parser.addParserWarning("msg.bad.jsdoc.tag", annotationName,
                  stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[46]++;

            } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[15]++;
              // Mark the beginning of the annotation.
              jsdocBuilder.markAnnotation(annotationName, lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[47]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[48]++;

              switch (annotation) {
                case NG_INJECT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[16]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
                  if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((jsdocBuilder.isNgInjectRecorded()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[17]++;
                    parser.addParserWarning("msg.jsdoc.nginject.extra",
                      stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[50]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[18]++;
                    jsdocBuilder.recordNgInject(true);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[51]++;
                  }
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[52]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[53]++;
                  continue retry;

                case AUTHOR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[19]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
                  if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[20]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[55]++;
                    ExtractionInfo authorInfo = extractSingleLineBlock();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[56]++;
                    String author = authorInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;

                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((author.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[22]++;
                      parser.addParserWarning("msg.jsdoc.authormissing",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[58]++;

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[23]++;
                      jsdocBuilder.addAuthor(author);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[59]++;
                    }
                    token = authorInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[60]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[21]++;
                    token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[61]++;
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[62]++;
                  continue retry;

                case CONSISTENTIDGENERATOR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[24]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[63]++;
int CodeCoverConditionCoverageHelper_C12;
                  if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordConsistentIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[25]++;
                    parser.addParserWarning("msg.jsdoc.consistidgen",
                      stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[64]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[26]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[65]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[66]++;
                  continue retry;

                case STRUCT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[27]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[67]++;
int CodeCoverConditionCoverageHelper_C13;
                  if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordStruct()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[28]++;
                    parser.addTypeWarning("msg.jsdoc.incompat.type",
                                          stream.getLineno(),
                                          stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[68]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[29]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[69]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[70]++;
                  continue retry;

                case DICT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[30]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[71]++;
int CodeCoverConditionCoverageHelper_C14;
                  if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordDict()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[31]++;
                    parser.addTypeWarning("msg.jsdoc.incompat.type",
                                          stream.getLineno(),
                                          stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[72]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[32]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[73]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[74]++;
                  continue retry;

                case CONSTRUCTOR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[33]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
                  if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[34]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((jsdocBuilder.isInterfaceRecorded()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[36]++;
                      parser.addTypeWarning("msg.jsdoc.interface.constructor",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[77]++;

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[37]++;
                      parser.addTypeWarning("msg.jsdoc.incompat.type",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[78]++;
                    }

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[35]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[79]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[80]++;
                  continue retry;

                case DEPRECATED:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[38]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
                  if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordDeprecated()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[39]++;
                    parser.addParserWarning("msg.jsdoc.deprecated",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[82]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[40]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[83]++;

                  // Find the reason/description, if any.
                  ExtractionInfo reasonInfo =
                      extractMultilineTextualBlock(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[84]++;

                  String reason = reasonInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[85]++;
int CodeCoverConditionCoverageHelper_C18;

                  if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((reason.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[41]++;
                    jsdocBuilder.recordDeprecationReason(reason);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[86]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[42]++;}

                  token = reasonInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[87]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[88]++;
                  continue retry;

                case INTERFACE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[43]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[89]++;
int CodeCoverConditionCoverageHelper_C19;
                  if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordInterface()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[44]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[90]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((jsdocBuilder.isConstructorRecorded()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[46]++;
                      parser.addTypeWarning("msg.jsdoc.interface.constructor",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[91]++;

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[47]++;
                      parser.addTypeWarning("msg.jsdoc.incompat.type",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[92]++;
                    }

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[45]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[93]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[94]++;
                  continue retry;

                case DESC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[48]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[95]++;
int CodeCoverConditionCoverageHelper_C21;
                  if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((jsdocBuilder.isDescriptionRecorded()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[49]++;
                    parser.addParserWarning("msg.jsdoc.desc.extra",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[96]++;
                    token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[97]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[98]++;
                    continue retry;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[50]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[99]++;
                    ExtractionInfo descriptionInfo =
                        extractMultilineTextualBlock(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[100]++;

                    String description = descriptionInfo.string;

                    jsdocBuilder.recordDescription(description);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[101]++;
                    token = descriptionInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[102]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[103]++;
                    continue retry;
                  }

                case FILE_OVERVIEW:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[51]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[104]++;
                  String fileOverview = "";
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[105]++;
int CodeCoverConditionCoverageHelper_C22;
                  if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[52]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[106]++;
                    ExtractionInfo fileOverviewInfo =
                        extractMultilineTextualBlock(token,
                            WhitespaceOption.TRIM);

                    fileOverview = fileOverviewInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[107]++;

                    token = fileOverviewInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[108]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[53]++;
                    token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[109]++;
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[110]++;
int CodeCoverConditionCoverageHelper_C23;

                  if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordFileOverview(fileOverview)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[54]++;
                    parser.addParserWarning("msg.jsdoc.fileoverview.extra",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[111]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[55]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[112]++;
                  continue retry;

                case LICENSE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[56]++;
                case PRESERVE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[57]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[113]++;
                  ExtractionInfo preserveInfo =
                      extractMultilineTextualBlock(token,
                                                   WhitespaceOption.PRESERVE);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[114]++;

                  String preserve = preserveInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[115]++;
int CodeCoverConditionCoverageHelper_C24;

                  if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((preserve.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[58]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[116]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((fileLevelJsDocBuilder != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[60]++;
                      fileLevelJsDocBuilder.append(preserve);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[117]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[61]++;}

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[59]++;}

                  token = preserveInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[118]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[119]++;
                  continue retry;

                case ENUM:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[62]++;
                  token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[120]++;
                  lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[121]++;
                  charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[122]++;

                  type = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[123]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[124]++;
int CodeCoverConditionCoverageHelper_C26;
                  if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((token != JsDocToken.EOL) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((token != JsDocToken.EOC) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[63]++;
                    type = createJSTypeExpression(
                        parseAndRecordTypeNode(token));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[125]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[64]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[126]++;
int CodeCoverConditionCoverageHelper_C27;

                  if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[65]++;
                    type = createJSTypeExpression(newStringNode("number"));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[127]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[66]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[128]++;
int CodeCoverConditionCoverageHelper_C28;
                  if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordEnumParameterType(type)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[67]++;
                    parser.addTypeWarning(
                        "msg.jsdoc.incompat.type", lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[129]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[68]++;}
                  token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[130]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[131]++;
                  continue retry;

                case EXPORT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[69]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[132]++;
int CodeCoverConditionCoverageHelper_C29;
                  if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordExport()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[70]++;
                    parser.addParserWarning("msg.jsdoc.export",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[133]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[71]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[134]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[135]++;
                  continue retry;

                case EXPOSE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[72]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[136]++;
int CodeCoverConditionCoverageHelper_C30;
                  if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordExpose()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[73]++;
                    parser.addParserWarning("msg.jsdoc.expose",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[137]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[74]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[138]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[139]++;
                  continue retry;

                case EXTERNS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[75]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[140]++;
int CodeCoverConditionCoverageHelper_C31;
                  if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordExterns()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[76]++;
                    parser.addParserWarning("msg.jsdoc.externs",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[141]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[77]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[142]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[143]++;
                  continue retry;

                case JAVA_DISPATCH:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[78]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[144]++;
int CodeCoverConditionCoverageHelper_C32;
                  if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordJavaDispatch()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[79]++;
                    parser.addParserWarning("msg.jsdoc.javadispatch",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[145]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[80]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[146]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[147]++;
                  continue retry;

                case EXTENDS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[81]++;
                case IMPLEMENTS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[82]++;
                  skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[148]++;
                  token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[149]++;
                  lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[150]++;
                  charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[151]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[152]++;
                  boolean matchingRc = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[153]++;
int CodeCoverConditionCoverageHelper_C33;

                  if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[83]++;
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[154]++;
                    matchingRc = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[155]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[84]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[156]++;
int CodeCoverConditionCoverageHelper_C34;

                  if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((token == JsDocToken.STRING) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[85]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[157]++;
                    Node typeNode = parseAndRecordTypeNameNode(
                        token, lineno, charno, matchingRc);

                    lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[158]++;
                    charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[159]++;

                    typeNode = wrapNode(Token.BANG, typeNode);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[160]++;
                    type = createJSTypeExpression(typeNode);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[161]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[162]++;
int CodeCoverConditionCoverageHelper_C35;

                    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((annotation == Annotation.EXTENDS) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[87]++;
                      // record the extended type, check later
                      extendedTypes.add(new ExtendedTypeInfo(
                          type, stream.getLineno(), stream.getCharno()));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[163]++;

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[88]++;
                      Preconditions.checkState(
                          annotation == Annotation.IMPLEMENTS);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[164]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[165]++;
int CodeCoverConditionCoverageHelper_C36;
                      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordImplementedInterface(type)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[89]++;
                        parser.addTypeWarning("msg.jsdoc.implements.duplicate",
                            lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[166]++;

                      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[90]++;}
                    }
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[167]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[168]++;
int CodeCoverConditionCoverageHelper_C37;
                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((matchingRc) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[91]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[169]++;
int CodeCoverConditionCoverageHelper_C38;
                      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((token != JsDocToken.RC) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[93]++;
                        parser.addTypeWarning("msg.jsdoc.missing.rc",
                            stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[170]++;

                      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[94]++;}

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[92]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[171]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((token != JsDocToken.EOL) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((token != JsDocToken.EOF) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((token != JsDocToken.EOC) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[95]++;
                      parser.addTypeWarning("msg.end.annotation.expected",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[172]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[96]++;}
}

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[86]++;
                    parser.addTypeWarning("msg.no.type.name", lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[173]++;
                  }
                  token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[174]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[175]++;
                  continue retry;

                case HIDDEN:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[97]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[176]++;
int CodeCoverConditionCoverageHelper_C40;
                  if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordHiddenness()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[98]++;
                    parser.addParserWarning("msg.jsdoc.hidden",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[177]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[99]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[178]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[179]++;
                  continue retry;

                case LENDS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[100]++;
                  skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[180]++;

                  matchingRc = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[181]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[182]++;
int CodeCoverConditionCoverageHelper_C41;
                  if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((match(JsDocToken.LC)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[101]++;
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[183]++;
                    matchingRc = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[184]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[102]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[185]++;
int CodeCoverConditionCoverageHelper_C42;

                  if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((match(JsDocToken.STRING)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[103]++;
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[186]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[187]++;
int CodeCoverConditionCoverageHelper_C43;
                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordLends(stream.getString())) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[105]++;
                      parser.addTypeWarning("msg.jsdoc.lends.incompatible",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[188]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[106]++;}

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[104]++;
                    parser.addTypeWarning("msg.jsdoc.lends.missing",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[189]++;
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[190]++;
int CodeCoverConditionCoverageHelper_C44;

                  if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((matchingRc) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[107]++;
                    parser.addTypeWarning("msg.jsdoc.missing.rc",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[191]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[108]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[192]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[193]++;
                  continue retry;

                case MEANING:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[109]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[194]++;
                  ExtractionInfo meaningInfo =
                      extractMultilineTextualBlock(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[195]++;
                  String meaning = meaningInfo.string;
                  token = meaningInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[196]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[197]++;
int CodeCoverConditionCoverageHelper_C45;
                  if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordMeaning(meaning)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[110]++;
                    parser.addParserWarning("msg.jsdoc.meaning.extra",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[198]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[111]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[199]++;
                  continue retry;

                case NO_ALIAS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[112]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[200]++;
int CodeCoverConditionCoverageHelper_C46;
                  if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordNoAlias()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[113]++;
                    parser.addParserWarning("msg.jsdoc.noalias",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[201]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[114]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[202]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[203]++;
                  continue retry;

                case NO_COMPILE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[115]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[204]++;
int CodeCoverConditionCoverageHelper_C47;
                  if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordNoCompile()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[116]++;
                    parser.addParserWarning("msg.jsdoc.nocompile",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[205]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[117]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[206]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[207]++;
                  continue retry;

                case NO_TYPE_CHECK:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[118]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[208]++;
int CodeCoverConditionCoverageHelper_C48;
                  if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordNoTypeCheck()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[119]++;
                    parser.addParserWarning("msg.jsdoc.nocheck",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[209]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[120]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[210]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[211]++;
                  continue retry;

                case NOT_IMPLEMENTED:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[121]++;
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[212]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[213]++;
                  continue retry;

                case INHERIT_DOC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[122]++;
                case OVERRIDE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[123]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[214]++;
int CodeCoverConditionCoverageHelper_C49;
                  if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordOverride()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[124]++;
                    parser.addTypeWarning("msg.jsdoc.override",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[215]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[125]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[216]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[217]++;
                  continue retry;

                case THROWS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[126]++;
                  skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[218]++;
                  token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[219]++;
                  lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[220]++;
                  charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[221]++;
                  type = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[222]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[223]++;
int CodeCoverConditionCoverageHelper_C50;

                  if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[127]++;
                    type = createJSTypeExpression(
                        parseAndRecordTypeNode(token));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[224]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[225]++;
int CodeCoverConditionCoverageHelper_C51;

                    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[129]++;
                      // parsing error reported during recursive descent
                      // recovering parsing
                      token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[226]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[227]++;
                      continue retry;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[130]++;}

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[128]++;}

                  // *Update* the token to that after the type annotation.
                  token = current();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[228]++;

                  // Save the throw type.
                  jsdocBuilder.recordThrowType(type);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[229]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[230]++;
int CodeCoverConditionCoverageHelper_C52;

                  // Find the throw's description (if applicable).
                  if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[131]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[231]++;
                    ExtractionInfo descriptionInfo =
                        extractMultilineTextualBlock(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[232]++;

                    String description = descriptionInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[233]++;
int CodeCoverConditionCoverageHelper_C53;

                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((description.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[133]++;
                      jsdocBuilder.recordThrowDescription(type, description);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[234]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[134]++;}

                    token = descriptionInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[235]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[132]++;
                    token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[236]++;
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[237]++;
                  continue retry;

                case PARAM:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[135]++;
                  skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[238]++;
                  token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[239]++;
                  lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[240]++;
                  charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[241]++;
                  type = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[242]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[243]++;
int CodeCoverConditionCoverageHelper_C54;

                  if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[136]++;
                    type = createJSTypeExpression(
                        parseAndRecordParamTypeNode(token));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[244]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[245]++;
int CodeCoverConditionCoverageHelper_C55;

                    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[138]++;
                      // parsing error reported during recursive descent
                      // recovering parsing
                      token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[246]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[247]++;
                      continue retry;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[139]++;}
                    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[248]++;
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[249]++;
                    lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[250]++;
                    charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[251]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[137]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[252]++;

                  String name = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[253]++;
                  boolean isBracketedParam = JsDocToken.LB == token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[254]++;
int CodeCoverConditionCoverageHelper_C56;
                  if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isBracketedParam) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[140]++;
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[255]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[141]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[256]++;
int CodeCoverConditionCoverageHelper_C57;

                  if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((JsDocToken.STRING != token) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[142]++;
                    parser.addTypeWarning("msg.missing.variable.name",
                        lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[257]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[143]++;
                    name = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[258]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[259]++;
int CodeCoverConditionCoverageHelper_C58;

                    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((isBracketedParam) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[144]++;
                      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[260]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[261]++;
int CodeCoverConditionCoverageHelper_C59;

                      // Throw out JsDocToolkit's "default" parameter
                      // annotation.  It makes no sense under our type
                      // system.
                      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((JsDocToken.EQUALS == token) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[146]++;
                        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[262]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[263]++;
int CodeCoverConditionCoverageHelper_C60;
                        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((JsDocToken.STRING == token) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[148]++;
                          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[264]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[149]++;}

                      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[147]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[265]++;
int CodeCoverConditionCoverageHelper_C61;

                      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((JsDocToken.RB != token) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[150]++;
                        reportTypeSyntaxWarning("msg.jsdoc.missing.rb");
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[266]++;

                      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[151]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[267]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[152]++;
                        // Make the type expression optional, if it isn't
                        // already.
                        type = JSTypeExpression.makeOptionalArg(type);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[268]++;

                      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[153]++;}
}

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[145]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[269]++;
int CodeCoverConditionCoverageHelper_C63;

                    // If the param name has a DOT in it, just throw it out
                    // quietly. We do not handle the JsDocToolkit method
                    // for handling properties of params.
                    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((name.indexOf('.') > -1) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[154]++;
                      name = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[270]++;

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[155]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[271]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordParameter(name, type)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[156]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[272]++;
int CodeCoverConditionCoverageHelper_C65;
                      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((jsdocBuilder.hasParameter(name)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[158]++;
                        parser.addTypeWarning("msg.dup.variable.name", name,
                            lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[273]++;

                      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[159]++;
                        parser.addTypeWarning("msg.jsdoc.incompat.type", name,
                            lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[274]++;
                      }

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[157]++;}
}
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[275]++;
int CodeCoverConditionCoverageHelper_C66;

                  if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[160]++;
                    token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[276]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[277]++;
                    continue retry;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[161]++;}

                  jsdocBuilder.markName(name, sourceFile, lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[278]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[279]++;
int CodeCoverConditionCoverageHelper_C67;

                  // Find the parameter's description (if applicable).
                  if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[162]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[280]++;
                    ExtractionInfo paramDescriptionInfo =
                        extractMultilineTextualBlock(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[281]++;

                    String paramDescription = paramDescriptionInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[282]++;
int CodeCoverConditionCoverageHelper_C68;

                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((paramDescription.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[164]++;
                      jsdocBuilder.recordParameterDescription(name,
                          paramDescription);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[283]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[165]++;}

                    token = paramDescriptionInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[284]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[163]++;
                    token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[285]++;
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[286]++;
                  continue retry;

                case PRESERVE_TRY:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[166]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[287]++;
int CodeCoverConditionCoverageHelper_C69;
                  if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordPreserveTry()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[167]++;
                    parser.addParserWarning("msg.jsdoc.preservertry",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[288]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[168]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[289]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[290]++;
                  continue retry;

                case NO_SHADOW:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[169]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[291]++;
int CodeCoverConditionCoverageHelper_C70;
                  if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordNoShadow()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[170]++;
                    parser.addParserWarning("msg.jsdoc.noshadow",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[292]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[171]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[293]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[294]++;
                  continue retry;

                case NO_SIDE_EFFECTS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[172]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[295]++;
int CodeCoverConditionCoverageHelper_C71;
                  if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordNoSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[173]++;
                    parser.addParserWarning("msg.jsdoc.nosideeffects",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[296]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[174]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[297]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[298]++;
                  continue retry;

                case MODIFIES:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[175]++;
                  token = parseModifiesTag(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[299]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[300]++;
                  continue retry;

                case IMPLICIT_CAST:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[176]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[301]++;
int CodeCoverConditionCoverageHelper_C72;
                  if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordImplicitCast()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[177]++;
                    parser.addTypeWarning("msg.jsdoc.implicitcast",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[302]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[178]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[303]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[304]++;
                  continue retry;

                case SEE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[179]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[305]++;
int CodeCoverConditionCoverageHelper_C73;
                  if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[180]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[306]++;
                    ExtractionInfo referenceInfo = extractSingleLineBlock();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[307]++;
                    String reference = referenceInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[308]++;
int CodeCoverConditionCoverageHelper_C74;

                    if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((reference.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[182]++;
                      parser.addParserWarning("msg.jsdoc.seemissing",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[309]++;

                    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[183]++;
                      jsdocBuilder.addReference(reference);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[310]++;
                    }

                    token = referenceInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[311]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[181]++;
                    token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[312]++;
                  }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[313]++;
                  continue retry;

                case STABLEIDGENERATOR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[184]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[314]++;
int CodeCoverConditionCoverageHelper_C75;
                  if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordStableIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[185]++;
                    parser.addParserWarning("msg.jsdoc.stableidgen",
                      stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[315]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[186]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[316]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[317]++;
                  continue retry;

                case SUPPRESS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[187]++;
                  token = parseSuppressTag(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[318]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[319]++;
                  continue retry;

                case TEMPLATE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[188]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[320]++;
                  ExtractionInfo templateInfo = extractSingleLineBlock();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[321]++;
                  List<String> names = Lists.newArrayList(
                      Splitter.on(',')
                          .trimResults()
                          .split(templateInfo.string));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[322]++;
int CodeCoverConditionCoverageHelper_C76;

                  if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((names.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((names.get(0).length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[189]++;
                    parser.addTypeWarning("msg.jsdoc.templatemissing",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[323]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[190]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[324]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordTemplateTypeNames(names)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[191]++;
                    parser.addTypeWarning("msg.jsdoc.template.at.most.once",
                        stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[325]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[192]++;}
}

                  token = templateInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[326]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[327]++;
                  continue retry;

                case IDGENERATOR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[193]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[328]++;
int CodeCoverConditionCoverageHelper_C78;
                  if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[194]++;
                    parser.addParserWarning("msg.jsdoc.idgen",
                      stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[329]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[195]++;}
                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[330]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[331]++;
                  continue retry;

                case VERSION:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[196]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[332]++;
                  ExtractionInfo versionInfo = extractSingleLineBlock();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[333]++;
                  String version = versionInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[334]++;
int CodeCoverConditionCoverageHelper_C79;

                  if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((version.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[197]++;
                    parser.addParserWarning("msg.jsdoc.versionmissing",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[335]++;

                  } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[198]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[336]++;
int CodeCoverConditionCoverageHelper_C80;
                    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordVersion(version)) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[199]++;
                       parser.addParserWarning("msg.jsdoc.extraversion",
                          stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[337]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[200]++;}
                  }

                  token = versionInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[338]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[339]++;
                  continue retry;

                case CONSTANT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[201]++;
                case DEFINE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[202]++;
                case RETURN:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[203]++;
                case PRIVATE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[204]++;
                case PROTECTED:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[205]++;
                case PUBLIC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[206]++;
                case THIS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[207]++;
                case TYPE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[208]++;
                case TYPEDEF:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[209]++;
                  lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[340]++;
                  charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[341]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[342]++;

                  Node typeNode = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[343]++;
                  boolean hasType = lookAheadForTypeAnnotation();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[344]++;
                  boolean isAlternateTypeAnnotation =
                      (annotation == Annotation.PRIVATE ||
                       annotation == Annotation.PROTECTED ||
                       annotation == Annotation.PUBLIC ||
                       annotation == Annotation.CONSTANT);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[345]++;
                  boolean canSkipTypeAnnotation =
                      (isAlternateTypeAnnotation ||
                       annotation == Annotation.RETURN);
                  type = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[346]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[347]++;
int CodeCoverConditionCoverageHelper_C81;
                  if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((hasType) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((canSkipTypeAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[210]++;
                    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[348]++;
                    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[349]++;
                    typeNode = parseAndRecordTypeNode(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[350]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[351]++;
int CodeCoverConditionCoverageHelper_C82;

                    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((annotation == Annotation.THIS) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[212]++;
                      typeNode = wrapNode(Token.BANG, typeNode);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[352]++;

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[213]++;}
                    type = createJSTypeExpression(typeNode);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[353]++;

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[211]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[354]++;

                  // The error was reported during recursive descent
                  // recovering parsing
                  boolean hasError = type == null && !canSkipTypeAnnotation;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[355]++;
int CodeCoverConditionCoverageHelper_C83;
                  if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((hasError) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[214]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[356]++;
int CodeCoverConditionCoverageHelper_C84;
                    // Record types for @type.
                    // If the @private, @protected, or @public annotations
                    // have a type attached, pretend that they actually wrote:
                    // @type {type}\n@private
                    // This will have some weird behavior in some cases
                    // (for example, @private can now be used as a type-cast),
                    // but should be mostly OK.
                    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C84 |= (32)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((isAlternateTypeAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((annotation == Annotation.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 3) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 3) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[216]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[357]++;
int CodeCoverConditionCoverageHelper_C85;
                      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordType(type)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[218]++;
                        parser.addTypeWarning(
                            "msg.jsdoc.incompat.type", lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[358]++;

                      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[219]++;}

                    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[217]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[359]++;

                    switch (annotation) {
                      case CONSTANT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[220]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[360]++;
int CodeCoverConditionCoverageHelper_C86;
                        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordConstancy()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[221]++;
                          parser.addParserWarning("msg.jsdoc.const",
                              stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[361]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[222]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[362]++;
                        break;

                      case DEFINE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[223]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[363]++;
int CodeCoverConditionCoverageHelper_C87;
                        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordDefineType(type)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[224]++;
                          parser.addParserWarning("msg.jsdoc.define",
                              lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[364]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[225]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[365]++;
                        break;

                      case PRIVATE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[226]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[366]++;
int CodeCoverConditionCoverageHelper_C88;
                        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordVisibility(Visibility.PRIVATE)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[227]++;
                          parser.addParserWarning(
                              "msg.jsdoc.visibility.private",
                              lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[367]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[228]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[368]++;
                        break;

                      case PROTECTED:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[229]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[369]++;
int CodeCoverConditionCoverageHelper_C89;
                        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordVisibility(Visibility.PROTECTED)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[230]++;
                          parser.addParserWarning(
                              "msg.jsdoc.visibility.protected",
                              lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[370]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[231]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[371]++;
                        break;

                      case PUBLIC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[232]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[372]++;
int CodeCoverConditionCoverageHelper_C90;
                        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordVisibility(Visibility.PUBLIC)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[233]++;
                          parser.addParserWarning(
                              "msg.jsdoc.visibility.public",
                              lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[373]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[234]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[374]++;
                        break;

                      case RETURN:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[235]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[375]++;
int CodeCoverConditionCoverageHelper_C91;
                        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[236]++;
                          type = createJSTypeExpression(newNode(Token.QMARK));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[376]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[237]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[377]++;
int CodeCoverConditionCoverageHelper_C92;

                        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordReturnType(type)) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[238]++;
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[378]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[379]++;
                          break;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[239]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[380]++;
int CodeCoverConditionCoverageHelper_C93;

                        // Find the return's description (if applicable).
                        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((jsdocBuilder.shouldParseDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[240]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[381]++;
                          ExtractionInfo returnDescriptionInfo =
                              extractMultilineTextualBlock(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[382]++;

                          String returnDescription =
                              returnDescriptionInfo.string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[383]++;
int CodeCoverConditionCoverageHelper_C94;

                          if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((returnDescription.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[242]++;
                            jsdocBuilder.recordReturnDescription(
                                returnDescription);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[384]++;

                          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[243]++;}

                          token = returnDescriptionInfo.token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[385]++;

                        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[241]++;
                          token = eatTokensUntilEOL(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[386]++;
                        }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[387]++;
                        continue retry;

                      case THIS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[244]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[388]++;
int CodeCoverConditionCoverageHelper_C95;
                        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordThisType(type)) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[245]++;
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[389]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[246]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[390]++;
                        break;

                      case TYPEDEF:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[247]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[391]++;
int CodeCoverConditionCoverageHelper_C96;
                        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordTypedef(type)) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[248]++;
                          parser.addTypeWarning(
                              "msg.jsdoc.incompat.type", lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[392]++;

                        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[249]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[393]++;
                        break; default : CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[250]++;
                    }

                  } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[215]++;}

                  token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[394]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[395]++;
                  continue retry; default : CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[251]++;
              }
            }

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[13]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[396]++;
          break;

        case EOC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[252]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[397]++;
int CodeCoverConditionCoverageHelper_C97;
          if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((hasParsedFileOverviewDocInfo()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[253]++;
            fileOverviewJSDocInfo = retrieveAndResetParsedJSDocInfo();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[398]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[254]++;}
          checkExtendedTypes(extendedTypes);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[399]++;
          return true;

        case EOF:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[255]++;
          // discard any accumulated information
          jsdocBuilder.build(null);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[400]++;
          parser.addParserWarning("msg.unexpected.eof",
              stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[401]++;
          checkExtendedTypes(extendedTypes);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[402]++;
          return false;

        case EOL:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[256]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[403]++;
int CodeCoverConditionCoverageHelper_C98;
          if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((state == State.SEARCHING_NEWLINE) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[257]++;
            state = State.SEARCHING_ANNOTATION;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[404]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[258]++;}
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[405]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[406]++;
          continue retry;

        default:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[259]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[407]++;
int CodeCoverConditionCoverageHelper_C99;
          if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((token == JsDocToken.STAR) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((state == State.SEARCHING_ANNOTATION) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[260]++;
            token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[408]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[409]++;
            continue retry;

          } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[261]++;
            state = State.SEARCHING_NEWLINE;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[410]++;
            token = eatTokensUntilEOL();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[411]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[412]++;
            continue retry;
          }
      }

      // next token
      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[413]++;
    }
  }

  private void checkExtendedTypes(List<ExtendedTypeInfo> extendedTypes) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[414]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[4]++;


    for (ExtendedTypeInfo typeInfo : extendedTypes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[4]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[5]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[6]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[415]++;
int CodeCoverConditionCoverageHelper_C100;
      // If interface, record the multiple extended interfaces
      if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((jsdocBuilder.isInterfaceRecorded()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[262]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[416]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordExtendedInterface(typeInfo.type)) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[264]++;
          parser.addParserWarning("msg.jsdoc.extends.duplicate",
              typeInfo.lineno, typeInfo.charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[417]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[265]++;}

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[263]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[418]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordBaseType(typeInfo.type)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[266]++;
          parser.addTypeWarning("msg.jsdoc.incompat.type",
              typeInfo.lineno, typeInfo.charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[419]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[267]++;}
      }
    }
  }

  /**
   * Parse a {@code @suppress} tag of the form
   * {@code @suppress&#123;warning1|warning2&#125;}.
   *
   * @param token The current token.
   */
  private JsDocToken parseSuppressTag(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[420]++;
int CodeCoverConditionCoverageHelper_C103;
    if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[268]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[421]++;
      Set<String> suppressions = new HashSet<String>();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[422]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[7]++;


      while (true) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[7]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[8]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[9]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[423]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((match(JsDocToken.STRING)) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[270]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[424]++;
          String name = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[425]++;
int CodeCoverConditionCoverageHelper_C106;
          if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((suppressionNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[272]++;
            parser.addParserWarning("msg.jsdoc.suppress.unknown", name,
                stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[426]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[273]++;}

          suppressions.add(stream.getString());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[427]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[428]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[271]++;
          parser.addParserWarning("msg.jsdoc.suppress",
              stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[429]++;
          return token;
        }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[430]++;
int CodeCoverConditionCoverageHelper_C107;

        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((match(JsDocToken.PIPE)) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[274]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[431]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[275]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[432]++;
          break;
        }
      }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[433]++;
int CodeCoverConditionCoverageHelper_C108;

      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[276]++;
        parser.addParserWarning("msg.jsdoc.suppress",
            stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[434]++;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[277]++;
        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[435]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[436]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordSuppressions(suppressions)) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[278]++;
          parser.addParserWarning("msg.jsdoc.suppress.duplicate",
              stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[437]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[279]++;}
      }

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[269]++;}
    return token;
  }

  /**
   * Parse a {@code @modifies} tag of the form
   * {@code @modifies&#123;this|arguments|param&#125;}.
   *
   * @param token The current token.
   */
  private JsDocToken parseModifiesTag(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[438]++;
int CodeCoverConditionCoverageHelper_C110;
    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[280]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[439]++;
      Set<String> modifies = new HashSet<String>();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[440]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[10]++;


      while (true) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[10]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[11]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[12]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[441]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((match(JsDocToken.STRING)) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[282]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[442]++;
          String name = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[443]++;
int CodeCoverConditionCoverageHelper_C113;
          if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C113 |= (8)) == 0 || true) &&
 ((modifiesAnnotationKeywords.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((jsdocBuilder.hasParameter(name)) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[284]++;
              parser.addParserWarning("msg.jsdoc.modifies.unknown", name,
                  stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[444]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[285]++;}

          modifies.add(stream.getString());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[445]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[446]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[283]++;
          parser.addParserWarning("msg.jsdoc.modifies",
              stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[447]++;
          return token;
        }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[448]++;
int CodeCoverConditionCoverageHelper_C114;

        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((match(JsDocToken.PIPE)) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[286]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[449]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[287]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[450]++;
          break;
        }
      }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[451]++;
int CodeCoverConditionCoverageHelper_C115;

      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[288]++;
        parser.addParserWarning("msg.jsdoc.modifies",
            stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[452]++;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[289]++;
        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[453]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[454]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((jsdocBuilder.recordModifies(modifies)) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[290]++;
          parser.addParserWarning("msg.jsdoc.modifies.duplicate",
              stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[455]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[291]++;}
      }

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[281]++;}
    return token;
  }

  /**
   * Looks for a type expression at the current token and if found,
   * returns it. Note that this method consumes input.
   *
   * @param token The current token.
   * @return The type expression found or null if none.
   */
  Node parseAndRecordTypeNode(JsDocToken token) {
    return parseAndRecordTypeNode(token, token == JsDocToken.LC);
  }

  /**
   * Looks for a type expression at the current token and if found,
   * returns it. Note that this method consumes input.
   *
   * @param token The current token.
   * @param matchingLC Whether the type expression starts with a "{".
   * @return The type expression found or null if none.
   */
  private Node parseAndRecordTypeNode(JsDocToken token, boolean matchingLC) {
    return parseAndRecordTypeNode(token, stream.getLineno(), stream.getCharno(),
        matchingLC, false);
  }

  /**
   * Looks for a type expression at the current token and if found,
   * returns it. Note that this method consumes input.
   *
   * @param token The current token.
   * @param lineno The line of the type expression.
   * @param startCharno The starting character position of the type expression.
   * @param matchingLC Whether the type expression starts with a "{".
   * @return The type expression found or null if none.
   */
  private Node parseAndRecordTypeNameNode(JsDocToken token, int lineno,
                                          int startCharno, boolean matchingLC) {
    return parseAndRecordTypeNode(token, lineno, startCharno, matchingLC, true);
  }

  /**
   * Looks for a type expression at the current token and if found,
   * returns it. Note that this method consumes input.
   *
   * Parameter type expressions are special for two reasons:
   * <ol>
   *   <li>They must begin with '{', to distinguish type names from param names.
   *   <li>They may end in '=', to denote optionality.
   * </ol>
   *
   * @param token The current token.
   * @return The type expression found or null if none.
   */
  private Node parseAndRecordParamTypeNode(JsDocToken token) {
    Preconditions.checkArgument(token == JsDocToken.LC);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[456]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[457]++;
    int lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[458]++;
    int startCharno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[459]++;

    Node typeNode = parseParamTypeExpressionAnnotation(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[460]++;
int CodeCoverConditionCoverageHelper_C117;
    if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((typeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[292]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[461]++;
      int endLineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[462]++;
      int endCharno = stream.getCharno();

      jsdocBuilder.markTypeNode(typeNode, lineno, startCharno,
          endLineno, endCharno, true);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[463]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[293]++;}
    return typeNode;
  }

  /**
   * Looks for a parameter type expression at the current token and if found,
   * returns it. Note that this method consumes input.
   *
   * @param token The current token.
   * @param lineno The line of the type expression.
   * @param startCharno The starting character position of the type expression.
   * @param matchingLC Whether the type expression starts with a "{".
   * @param onlyParseSimpleNames If true, only simple type names are parsed
   *     (via a call to parseTypeNameAnnotation instead of
   *     parseTypeExpressionAnnotation).
   * @return The type expression found or null if none.
   */
  private Node parseAndRecordTypeNode(JsDocToken token, int lineno,
                                      int startCharno,
                                      boolean matchingLC,
                                      boolean onlyParseSimpleNames) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[464]++;
    Node typeNode = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[465]++;
int CodeCoverConditionCoverageHelper_C118;

    if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((onlyParseSimpleNames) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[294]++;
      typeNode = parseTypeNameAnnotation(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[466]++;

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[295]++;
      typeNode = parseTypeExpressionAnnotation(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[467]++;
    }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[468]++;
int CodeCoverConditionCoverageHelper_C119;

    if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((typeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[296]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[469]++;
      int endLineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[470]++;
      int endCharno = stream.getCharno();

      jsdocBuilder.markTypeNode(
          typeNode, lineno, startCharno, endLineno, endCharno, matchingLC);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[471]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[297]++;}

    return typeNode;
  }

  /**
   * Converts a JSDoc token to its string representation.
   */
  private String toString(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[472]++;
    switch (token) {
      case ANNOTATION:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[298]++;
        return "@" + stream.getString();

      case BANG:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[299]++;
        return "!";

      case COMMA:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[300]++;
        return ",";

      case COLON:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[301]++;
        return ":";

      case GT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[302]++;
        return ">";

      case LB:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[303]++;
        return "[";

      case LC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[304]++;
        return "{";

      case LP:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[305]++;
        return "(";

      case LT:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[306]++;
        return ".<";

      case QMARK:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[307]++;
        return "?";

      case PIPE:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[308]++;
        return "|";

      case RB:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[309]++;
        return "]";

      case RC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[310]++;
        return "}";

      case RP:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[311]++;
        return ")";

      case STAR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[312]++;
        return "*";

      case ELLIPSIS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[313]++;
        return "...";

      case EQUALS:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[314]++;
        return "=";

      case STRING:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[315]++;
        return stream.getString();

      default:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[316]++;
        throw new IllegalStateException(token.toString());
    }
  }

  /**
   * Constructs a new {@code JSTypeExpression}.
   * @param n A node. May be null.
   */
  JSTypeExpression createJSTypeExpression(Node n) {
    return n == null ? null :
        new JSTypeExpression(n, getSourceName());
  }

  /**
   * Tuple for returning both the string extracted and the
   * new token following a call to any of the extract*Block
   * methods.
   */
  private static class ExtractionInfo {
    private final String string;
    private final JsDocToken token;

    public ExtractionInfo(String string, JsDocToken token) {
      this.string = string;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[473]++;
      this.token = token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[474]++;
    }
  }

  /**
   * Tuple for recording extended types
   */
  private static class ExtendedTypeInfo {
    final JSTypeExpression type;
    final int lineno;
    final int charno;

    public ExtendedTypeInfo(JSTypeExpression type, int lineno, int charno) {
      this.type = type;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[475]++;
      this.lineno = lineno;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[476]++;
      this.charno = charno;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[477]++;
    }
  }

  /**
   * Extracts the text found on the current line starting at token. Note that
   * token = token.info; should be called after this method is used to update
   * the token properly in the parser.
   *
   * @return The extraction information.
   */
  private ExtractionInfo extractSingleLineBlock() {

    // Get the current starting point.
    stream.update();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[478]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[479]++;
    int lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[480]++;
    int charno = stream.getCharno() + 1;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[481]++;

    String line = stream.getRemainingJSDocLine().trim();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[482]++;
int CodeCoverConditionCoverageHelper_C120;

    // Record the textual description.
    if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((line.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[317]++;
      jsdocBuilder.markText(line, lineno, charno, lineno,
                            charno + line.length());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[483]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[318]++;}

    return new ExtractionInfo(line, next());
  }

  private ExtractionInfo extractMultilineTextualBlock(JsDocToken token) {
    return extractMultilineTextualBlock(token, WhitespaceOption.SINGLE_LINE);
  }

  private enum WhitespaceOption {
    /**
     * Preserves all whitespace and formatting. Needed for licenses and
     * purposely formatted text.
     */
    PRESERVE,

    /** Preserves newlines but trims the output. */
    TRIM,

    /** Removes newlines and turns the output into a single line string. */
    SINGLE_LINE
  }

  /**
   * Extracts the text found on the current line and all subsequent
   * until either an annotation, end of comment or end of file is reached.
   * Note that if this method detects an end of line as the first token, it
   * will quit immediately (indicating that there is no text where it was
   * expected).  Note that token = info.token; should be called after this
   * method is used to update the token properly in the parser.
   *
   * @param token The start token.
   * @param option How to handle whitespace.
   *
   * @return The extraction information.
   */
  @SuppressWarnings("fallthrough")
  private ExtractionInfo extractMultilineTextualBlock(JsDocToken token,
                                                      WhitespaceOption option) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[484]++;
int CodeCoverConditionCoverageHelper_C121;

    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (32)) == 0 || true) &&
 ((token == JsDocToken.EOC) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C121 |= (8)) == 0 || true) &&
 ((token == JsDocToken.EOL) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((token == JsDocToken.EOF) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 3) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 3) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[319]++;
      return new ExtractionInfo("", token);

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[320]++;}

    stream.update();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[485]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[486]++;
    int startLineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[487]++;
    int startCharno = stream.getCharno() + 1;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[488]++;

    // Read the content from the first line.
    String line = stream.getRemainingJSDocLine();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[489]++;
int CodeCoverConditionCoverageHelper_C122;
    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((option != WhitespaceOption.PRESERVE) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[321]++;
      line = line.trim();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[490]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[322]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[491]++;

    StringBuilder builder = new StringBuilder();
    builder.append(line);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[492]++;

    state = State.SEARCHING_ANNOTATION;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[493]++;
    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[494]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[495]++;

    boolean ignoreStar = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[496]++;

    // Track the start of the line to count whitespace that
    // the tokenizer skipped. Because this case is rare, it's easier
    // to do this here than in the tokenizer.
    int lineStartChar = -1;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[497]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[13]++;



    do {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[13]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[14]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[15]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[498]++;
      switch (token) {
        case STAR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[323]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[499]++;
int CodeCoverConditionCoverageHelper_C124;
          if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((ignoreStar) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[324]++;
            // Mark the position after the star as the new start of the line.
            lineStartChar = stream.getCharno() + 1;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[500]++;

          } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[325]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[501]++;
int CodeCoverConditionCoverageHelper_C125;
            // The star is part of the comment.
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((builder.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[326]++;
              builder.append(' ');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[502]++;

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[327]++;}

            builder.append('*');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[503]++;
          }

          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[504]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[505]++;
          continue;

        case EOL:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[328]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[506]++;
int CodeCoverConditionCoverageHelper_C126;
          if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((option != WhitespaceOption.SINGLE_LINE) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[329]++;
            builder.append("\n");
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[507]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[330]++;}

          ignoreStar = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[508]++;
          lineStartChar = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[509]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[510]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[511]++;
          continue;

        default:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[331]++;
          ignoreStar = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[512]++;
          state = State.SEARCHING_ANNOTATION;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[513]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[514]++;

          boolean isEOC = token == JsDocToken.EOC;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[515]++;
int CodeCoverConditionCoverageHelper_C127;
          if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((isEOC) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[332]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[516]++;
int CodeCoverConditionCoverageHelper_C128;
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((lineStartChar != -1) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((option == WhitespaceOption.PRESERVE) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[334]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[517]++;
              int numSpaces = stream.getCharno() - lineStartChar;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[518]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[16]++;


int CodeCoverConditionCoverageHelper_C129;
              for (int i = 0;(((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((i < numSpaces) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[16]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[17]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[18]++;
}
                builder.append(' ');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[519]++;
              }
              lineStartChar = -1;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[520]++;

            } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[335]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[521]++;
int CodeCoverConditionCoverageHelper_C130; if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((builder.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[336]++;
              // All tokens must be separated by a space.
              builder.append(' ');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[522]++;

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[337]++;}
}

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[333]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[523]++;
int CodeCoverConditionCoverageHelper_C131;

          if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (128)) == 0 || true) &&
 ((token == JsDocToken.EOC) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C131 |= (32)) == 0 || true) &&
 ((token == JsDocToken.EOF) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C131 |= (8)) == 0 || true) &&
 ((token == JsDocToken.ANNOTATION) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((option != WhitespaceOption.PRESERVE) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 4) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 4) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[338]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[524]++;
            String multilineText = builder.toString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[525]++;
int CodeCoverConditionCoverageHelper_C132;

            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((option != WhitespaceOption.PRESERVE) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[340]++;
              multilineText = multilineText.trim();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[526]++;

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[341]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[527]++;

            int endLineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[528]++;
            int endCharno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[529]++;
int CodeCoverConditionCoverageHelper_C133;

            if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((multilineText.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[342]++;
              jsdocBuilder.markText(multilineText, startLineno, startCharno,
                  endLineno, endCharno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[530]++;

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[343]++;}

            return new ExtractionInfo(multilineText, token);

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[339]++;}

          builder.append(toString(token));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[531]++;

          line = stream.getRemainingJSDocLine();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[532]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[533]++;
int CodeCoverConditionCoverageHelper_C134;

          if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((option != WhitespaceOption.PRESERVE) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[344]++;
            line = trimEnd(line);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[534]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[345]++;}

          builder.append(line);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[535]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[536]++;
      }
    } while (true);
  }


  /**
   * Extracts the top-level block comment from the JsDoc comment, if any.
   * This method differs from the extractMultilineTextualBlock in that it
   * terminates under different conditions (it doesn't have the same
   * prechecks), it does not first read in the remaining of the current
   * line and its conditions for ignoring the "*" (STAR) are different.
   *
   * @param token The starting token.
   *
   * @return The extraction information.
   */
  private ExtractionInfo extractBlockComment(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[537]++;
    StringBuilder builder = new StringBuilder();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[538]++;

    boolean ignoreStar = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[539]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[19]++;



    do {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[19]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[20]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[21]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[540]++;
      switch (token) {
        case ANNOTATION:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[346]++;
        case EOC:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[347]++;
        case EOF:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[348]++;
          return new ExtractionInfo(builder.toString().trim(), token);

        case STAR:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[349]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[541]++;
int CodeCoverConditionCoverageHelper_C136;
          if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((ignoreStar) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[350]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[542]++;
int CodeCoverConditionCoverageHelper_C137;
            if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((builder.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[352]++;
              builder.append(' ');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[543]++;

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[353]++;}

            builder.append('*');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[544]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[351]++;}

          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[545]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[546]++;
          continue;

        case EOL:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[354]++;
          ignoreStar = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[547]++;
          builder.append('\n');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[548]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[549]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[550]++;
          continue;

        default:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[355]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[551]++;
int CodeCoverConditionCoverageHelper_C138;
          if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C138 |= (8)) == 0 || true) &&
 ((ignoreStar) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((builder.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[356]++;
            builder.append(' ');
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[552]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[357]++;}

          ignoreStar = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[553]++;

          builder.append(toString(token));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[554]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[555]++;

          String line = stream.getRemainingJSDocLine();
          line = trimEnd(line);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[556]++;
          builder.append(line);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[557]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[558]++;
      }
    } while (true);
  }

  /**
   * Trim characters from only the end of a string.
   * This method will remove all whitespace characters
   * (defined by Character.isWhitespace(char), in addition to the characters
   * provided, from the end of the provided string.
   *
   * @param s String to be trimmed
   * @return String with whitespace and characters in extraChars removed
   *                   from the end.
   */
  private static String trimEnd(String s) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[559]++;
    int trimCount = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[560]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[22]++;


int CodeCoverConditionCoverageHelper_C139;
    while ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((trimCount < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[22]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[23]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[24]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[561]++;
      char ch = s.charAt(s.length() - trimCount - 1);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[562]++;
int CodeCoverConditionCoverageHelper_C140;
      if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(ch)) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[358]++;
        trimCount++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[563]++;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[359]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[564]++;
        break;
      }
    }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[565]++;
int CodeCoverConditionCoverageHelper_C141;

    if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((trimCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[360]++;
      return s;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[361]++;}
    return s.substring(0, s.length() - trimCount);
  }

  // Based on ES4 grammar proposed on July 10, 2008.
  // http://wiki.ecmascript.org/doku.php?id=spec:spec
  // Deliberately written to line up with the actual grammar rules,
  // for maximum flexibility.

  // TODO(nicksantos): The current implementation tries to maintain backwards
  // compatibility with previous versions of the spec whenever we can.
  // We should try to gradually withdraw support for these.

  /**
   * TypeExpressionAnnotation := TypeExpression |
   *     '{' TopLevelTypeExpression '}'
   */
  private Node parseTypeExpressionAnnotation(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[566]++;
int CodeCoverConditionCoverageHelper_C142;
    if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[362]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[567]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[568]++;
      Node typeNode = parseTopLevelTypeExpression(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[569]++;
int CodeCoverConditionCoverageHelper_C143;
      if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((typeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[364]++;
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[570]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[571]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[366]++;
          reportTypeSyntaxWarning("msg.jsdoc.missing.rc");
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[572]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[367]++;
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[573]++;
        }

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[365]++;}

      return typeNode;

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[363]++;
      return parseTypeExpression(token);
    }
  }

  /**
   * ParamTypeExpressionAnnotation :=
   *     '{' OptionalParameterType '}' |
   *     '{' TopLevelTypeExpression '}' |
   *     '{' '...' TopLevelTypeExpression '}'
   *
   * OptionalParameterType :=
   *     TopLevelTypeExpression '='
   */
  private Node parseParamTypeExpressionAnnotation(JsDocToken token) {
    Preconditions.checkArgument(token == JsDocToken.LC);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[574]++;

    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[575]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[576]++;

    boolean restArg = false;
    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[577]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[578]++;
int CodeCoverConditionCoverageHelper_C145;
    if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((token == JsDocToken.ELLIPSIS) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[368]++;
      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[579]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[580]++;
int CodeCoverConditionCoverageHelper_C146;
      if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((token == JsDocToken.RC) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[370]++;
        // EMPTY represents the UNKNOWN type in the Type AST.
        return wrapNode(Token.ELLIPSIS, IR.empty());

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[371]++;}
      restArg = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[581]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[369]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[582]++;

    Node typeNode = parseTopLevelTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[583]++;
int CodeCoverConditionCoverageHelper_C147;
    if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((typeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[372]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[584]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[585]++;
int CodeCoverConditionCoverageHelper_C148;
      if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((restArg) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[374]++;
        typeNode = wrapNode(Token.ELLIPSIS, typeNode);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[586]++;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[375]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[587]++;
int CodeCoverConditionCoverageHelper_C149; if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((match(JsDocToken.EQUALS)) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[376]++;
        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[588]++;
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[589]++;
        typeNode = wrapNode(Token.EQUALS, typeNode);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[590]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[377]++;}
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[591]++;
int CodeCoverConditionCoverageHelper_C150;

      if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[378]++;
        reportTypeSyntaxWarning("msg.jsdoc.missing.rc");
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[592]++;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[379]++;
        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[593]++;
      }

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[373]++;}

    return typeNode;
  }

  /**
   * TypeNameAnnotation := TypeName | '{' TypeName '}'
   */
  private Node parseTypeNameAnnotation(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[594]++;
int CodeCoverConditionCoverageHelper_C151;
    if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[380]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[595]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[596]++;
      Node typeNode = parseTypeName(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[597]++;
int CodeCoverConditionCoverageHelper_C152;
      if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((typeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[382]++;
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[598]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[599]++;
int CodeCoverConditionCoverageHelper_C153;
        if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[384]++;
          reportTypeSyntaxWarning("msg.jsdoc.missing.rc");
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[600]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[385]++;
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[601]++;
        }

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[383]++;}

      return typeNode;

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[381]++;
      return parseTypeName(token);
    }
  }

  /**
   * TopLevelTypeExpression := TypeExpression
   *     | TypeUnionList
   *
   * We made this rule up, for the sake of backwards compatibility.
   */
  private Node parseTopLevelTypeExpression(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[602]++;
    Node typeExpr = parseTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[603]++;
int CodeCoverConditionCoverageHelper_C154;
    if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((typeExpr != null) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[386]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[604]++;
int CodeCoverConditionCoverageHelper_C155;
      // top-level unions are allowed
      if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((match(JsDocToken.PIPE)) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[388]++;
        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[605]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[606]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((match(JsDocToken.PIPE)) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[390]++;
          // We support double pipes for backwards-compatibility.
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[607]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[391]++;}
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[608]++;
        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[609]++;
        return parseUnionTypeWithAlternate(token, typeExpr);

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[389]++;}

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[387]++;}
    return typeExpr;
  }

  /**
   * TypeExpressionList := TopLevelTypeExpression
   *     | TopLevelTypeExpression ',' TypeExpressionList
   */
  private Node parseTypeExpressionList(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[610]++;
    Node typeExpr = parseTopLevelTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[611]++;
int CodeCoverConditionCoverageHelper_C157;
    if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((typeExpr == null) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[392]++;
      return null;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[393]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[612]++;
    Node typeList = IR.block();
    typeList.addChildToBack(typeExpr);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[613]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[614]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[25]++;


int CodeCoverConditionCoverageHelper_C158;
    while ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[25]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[26]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[27]++;
}
      next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[615]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[616]++;
      typeExpr = parseTopLevelTypeExpression(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[617]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[618]++;
int CodeCoverConditionCoverageHelper_C159;
      if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((typeExpr == null) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[394]++;
        return null;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[395]++;}
      typeList.addChildToBack(typeExpr);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[619]++;
    }
    return typeList;
  }

  /**
   * TypeExpression := BasicTypeExpression
   *     | '?' BasicTypeExpression
   *     | '!' BasicTypeExpression
   *     | BasicTypeExpression '?'
   *     | BasicTypeExpression '!'
   *     | '?'
   */
  private Node parseTypeExpression(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[620]++;
int CodeCoverConditionCoverageHelper_C160;
    if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((token == JsDocToken.QMARK) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[396]++;
      // A QMARK could mean that a type is nullable, or that it's unknown.
      // We use look-ahead 1 to determine whether it's unknown. Otherwise,
      // we assume it means nullable. There are 5 cases:
      // {?} - right curly
      // {?=} - equals
      // {function(?, number)} - comma
      // {function(number, ?)} - right paren
      // {function(number, ...[?])} - right bracket
      // {function(): ?|number} - pipe
      // {Array.<?>} - greater than
      // I'm not a big fan of using look-ahead for this, but it makes
      // the type language a lot nicer.
      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[621]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[622]++;
int CodeCoverConditionCoverageHelper_C161;
      if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (8192)) == 0 || true) &&
 ((token == JsDocToken.COMMA) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (4096)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C161 |= (2048)) == 0 || true) &&
 ((token == JsDocToken.EQUALS) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C161 |= (512)) == 0 || true) &&
 ((token == JsDocToken.RB) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C161 |= (128)) == 0 || true) &&
 ((token == JsDocToken.RC) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C161 |= (32)) == 0 || true) &&
 ((token == JsDocToken.RP) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C161 |= (8)) == 0 || true) &&
 ((token == JsDocToken.PIPE) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((token == JsDocToken.GT) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 7) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 7) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[398]++;
        restoreLookAhead(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[623]++;
        return newNode(Token.QMARK);

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[399]++;}

      return wrapNode(Token.QMARK, parseBasicTypeExpression(token));

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[397]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[624]++;
int CodeCoverConditionCoverageHelper_C162; if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((token == JsDocToken.BANG) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[400]++;
      return wrapNode(Token.BANG, parseBasicTypeExpression(next()));

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[401]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[625]++;
      Node basicTypeExpr = parseBasicTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[626]++;
int CodeCoverConditionCoverageHelper_C163;
      if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((basicTypeExpr != null) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[402]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[627]++;
int CodeCoverConditionCoverageHelper_C164;
        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((match(JsDocToken.QMARK)) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[404]++;
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[628]++;
          return wrapNode(Token.QMARK, basicTypeExpr);

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[405]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[629]++;
int CodeCoverConditionCoverageHelper_C165; if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((match(JsDocToken.BANG)) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[406]++;
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[630]++;
          return wrapNode(Token.BANG, basicTypeExpr);

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[407]++;}
}

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[403]++;}

      return basicTypeExpr;
    }
}
  }

  /**
   * BasicTypeExpression := '*' | 'null' | 'undefined' | TypeName
   *     | FunctionType | UnionType | RecordType | ArrayType
   */
  private Node parseBasicTypeExpression(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[631]++;
int CodeCoverConditionCoverageHelper_C166;
    if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((token == JsDocToken.STAR) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[408]++;
      return newNode(Token.STAR);

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[409]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[632]++;
int CodeCoverConditionCoverageHelper_C167; if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LB) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[410]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[633]++;
      return parseArrayType(next());

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[411]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[634]++;
int CodeCoverConditionCoverageHelper_C168; if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LC) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[412]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[635]++;
      return parseRecordType(next());

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[413]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[636]++;
int CodeCoverConditionCoverageHelper_C169; if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((token == JsDocToken.LP) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[414]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[637]++;
      return parseUnionType(next());

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[415]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[638]++;
int CodeCoverConditionCoverageHelper_C170; if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((token == JsDocToken.STRING) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[416]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[639]++;
      String string = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[640]++;
int CodeCoverConditionCoverageHelper_C171;
      if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 (("function".equals(string)) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[418]++;
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[641]++;
        return parseFunctionType(next());

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[419]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[642]++;
int CodeCoverConditionCoverageHelper_C172; if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (8)) == 0 || true) &&
 (("null".equals(string)) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 (("undefined".equals(string)) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[420]++;
        return newStringNode(string);

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[421]++;
        return parseTypeName(token);
      }
}

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[417]++;}
}
}
}
}

    restoreLookAhead(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[643]++;
    return reportGenericTypeSyntaxWarning();
  }

  /**
   * TypeName := NameExpression | NameExpression TypeApplication
   * TypeApplication := '.<' TypeExpressionList '>'
   */
  private Node parseTypeName(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[644]++;
int CodeCoverConditionCoverageHelper_C173;
    if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((token != JsDocToken.STRING) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[422]++;
      return reportGenericTypeSyntaxWarning();

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[423]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[645]++;

    String typeName = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[646]++;
    int lineno = stream.getLineno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[647]++;
    int charno = stream.getCharno();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[648]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[28]++;


int CodeCoverConditionCoverageHelper_C174;
    while ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (8)) == 0 || true) &&
 ((match(JsDocToken.EOL)) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((typeName.charAt(typeName.length() - 1) == '.') && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 2) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[28]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[29]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[30]++;
}
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[649]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[650]++;
int CodeCoverConditionCoverageHelper_C175;
      if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((match(JsDocToken.STRING)) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[424]++;
        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[651]++;
        typeName += stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[652]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[425]++;}
    }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[653]++;

    Node typeNameNode = newStringNode(typeName, lineno, charno);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[654]++;
int CodeCoverConditionCoverageHelper_C176;

    if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((match(JsDocToken.LT)) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[426]++;
      next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[655]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[656]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[657]++;
      Node memberType = parseTypeExpressionList(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[658]++;
int CodeCoverConditionCoverageHelper_C177;
      if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((memberType != null) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[428]++;
        typeNameNode.addChildToFront(memberType);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[659]++;

        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[660]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[661]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((match(JsDocToken.GT)) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[430]++;
          return reportTypeSyntaxWarning("msg.jsdoc.missing.gt");

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[431]++;}

        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[662]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[429]++;}

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[427]++;}
    return typeNameNode;
  }

  /**
   * FunctionType := 'function' FunctionSignatureType
   * FunctionSignatureType :=
   *    TypeParameters '(' 'this' ':' TypeName, ParametersType ')' ResultType
   */
  private Node parseFunctionType(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[663]++;
int CodeCoverConditionCoverageHelper_C179;
    // NOTE(nicksantos): We're not implementing generics at the moment, so
    // just throw out TypeParameters.
    if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((token != JsDocToken.LP) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[432]++;
      restoreLookAhead(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[664]++;
      return reportTypeSyntaxWarning("msg.jsdoc.missing.lp");

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[433]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[665]++;

    Node functionType = newNode(Token.FUNCTION);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[666]++;
    Node parameters = null;
    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[667]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[668]++;
int CodeCoverConditionCoverageHelper_C180;
    if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RP)) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[434]++;
      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[669]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[670]++;

      boolean hasParams = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[671]++;
int CodeCoverConditionCoverageHelper_C181;
      if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((token == JsDocToken.STRING) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[436]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[672]++;
        String tokenStr = stream.getString();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[673]++;
        boolean isThis = "this".equals(tokenStr);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[674]++;
        boolean isNew = "new".equals(tokenStr);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[675]++;
int CodeCoverConditionCoverageHelper_C182;
        if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (8)) == 0 || true) &&
 ((isThis) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((isNew) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[438]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[676]++;
int CodeCoverConditionCoverageHelper_C183;
          if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COLON)) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[440]++;
            next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[677]++;
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[678]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[679]++;
            Node contextType = wrapNode(
                isThis ? Token.THIS : Token.NEW,
                parseTypeName(next()));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[680]++;
int CodeCoverConditionCoverageHelper_C184;
            if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((contextType == null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[442]++;
              return null;

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[443]++;}

            functionType.addChildToFront(contextType);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[681]++;

          } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[441]++;
            return reportTypeSyntaxWarning("msg.jsdoc.missing.colon");
          }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[682]++;
int CodeCoverConditionCoverageHelper_C185;

          if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[444]++;
            next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[683]++;
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[684]++;
            token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[685]++;

          } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[445]++;
            hasParams = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[686]++;
          }

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[439]++;}

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[437]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[687]++;
int CodeCoverConditionCoverageHelper_C186;

      if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((hasParams) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[446]++;
        parameters = parseParametersType(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[688]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[689]++;
int CodeCoverConditionCoverageHelper_C187;
        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[448]++;
          return null;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[449]++;}

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[447]++;}

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[435]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[690]++;
int CodeCoverConditionCoverageHelper_C188;

    if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((parameters != null) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[450]++;
      functionType.addChildToBack(parameters);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[691]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[451]++;}

    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[692]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[693]++;
int CodeCoverConditionCoverageHelper_C189;
    if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RP)) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[452]++;
      return reportTypeSyntaxWarning("msg.jsdoc.missing.rp");

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[453]++;}

    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[694]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[695]++;
    Node resultType = parseResultType(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[696]++;
int CodeCoverConditionCoverageHelper_C190;
    if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((resultType == null) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[454]++;
      return null;

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[455]++;
      functionType.addChildToBack(resultType);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[697]++;
    }
    return functionType;
  }

  /**
   * ParametersType := RestParameterType | NonRestParametersType
   *     | NonRestParametersType ',' RestParameterType
   * RestParameterType := '...' Identifier
   * NonRestParametersType := ParameterType ',' NonRestParametersType
   *     | ParameterType
   *     | OptionalParametersType
   * OptionalParametersType := OptionalParameterType
   *     | OptionalParameterType, OptionalParametersType
   * OptionalParameterType := ParameterType=
   * ParameterType := TypeExpression | Identifier ':' TypeExpression
   */
  // NOTE(nicksantos): The official ES4 grammar forces optional and rest
  // arguments to come after the required arguments. Our parser does not
  // enforce this. Instead we allow them anywhere in the function at parse-time,
  // and then warn about them during type resolution.
  //
  // In theory, it might be mathematically nicer to do the order-checking here.
  // But in practice, the order-checking for structural functions is exactly
  // the same as the order-checking for @param annotations. And the latter
  // has to happen during type resolution. Rather than duplicate the
  // order-checking in two places, we just do all of it in type resolution.
  private Node parseParametersType(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[698]++;
    Node paramsType = newNode(Token.PARAM_LIST);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[699]++;
    boolean isVarArgs = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[700]++;
    Node paramType = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[701]++;
int CodeCoverConditionCoverageHelper_C191;
    if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((token != JsDocToken.RP) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[456]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[702]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[31]++;


int CodeCoverConditionCoverageHelper_C192;
      do {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[31]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[32]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[33]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[703]++;
int CodeCoverConditionCoverageHelper_C193;
        if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((paramType != null) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[458]++;
          // skip past the comma
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[704]++;
          skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[705]++;
          token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[706]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[459]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[707]++;
int CodeCoverConditionCoverageHelper_C194;

        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((token == JsDocToken.ELLIPSIS) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[460]++;
          // In the latest ES4 proposal, there are no type constraints allowed
          // on variable arguments. We support the old syntax for backwards
          // compatibility, but we should gradually tear it out.
          skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[708]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[709]++;
int CodeCoverConditionCoverageHelper_C195;
          if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RP)) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[462]++;
            paramType = newNode(Token.ELLIPSIS);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[710]++;

          } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[463]++;
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[711]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[712]++;
int CodeCoverConditionCoverageHelper_C196;
            if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((match(JsDocToken.LB)) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[464]++;
              return reportTypeSyntaxWarning("msg.jsdoc.missing.lb");

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[465]++;}

            next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[713]++;
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[714]++;
            paramType = wrapNode(Token.ELLIPSIS, parseTypeExpression(next()));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[715]++;
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[716]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[717]++;
int CodeCoverConditionCoverageHelper_C197;
            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RB)) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[466]++;
              return reportTypeSyntaxWarning("msg.jsdoc.missing.rb");

            } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[467]++;}
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[718]++;
            next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[719]++;
          }

          isVarArgs = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[720]++;

        } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[461]++;
          paramType = parseTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[721]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[722]++;
int CodeCoverConditionCoverageHelper_C198;
          if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((match(JsDocToken.EQUALS)) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[468]++;
            skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[723]++;
            next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[724]++;
            paramType = wrapNode(Token.EQUALS, paramType);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[725]++;

          } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[469]++;}
        }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[726]++;
int CodeCoverConditionCoverageHelper_C199;

        if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((paramType == null) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[470]++;
          return null;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[471]++;}
        paramsType.addChildToBack(paramType);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[727]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[728]++;
int CodeCoverConditionCoverageHelper_C200;
        if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((isVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[472]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[729]++;
          break;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[473]++;}
      } while ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false));

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[457]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[730]++;
int CodeCoverConditionCoverageHelper_C201;

    if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (8)) == 0 || true) &&
 ((isVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[474]++;
      return reportTypeSyntaxWarning("msg.jsdoc.function.varargs");

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[475]++;}

    // The right paren will be checked by parseFunctionType

    return paramsType;
  }

  /**
   * ResultType := <empty> | ':' void | ':' TypeExpression
   */
  private Node parseResultType(JsDocToken token) {
    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[731]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[732]++;
int CodeCoverConditionCoverageHelper_C202;
    if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COLON)) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[476]++;
      return newNode(Token.EMPTY);

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[477]++;}

    token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[733]++;
    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[734]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[735]++;
int CodeCoverConditionCoverageHelper_C203;
    if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (8)) == 0 || true) &&
 ((match(JsDocToken.STRING)) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 (("void".equals(stream.getString())) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[478]++;
      next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[736]++;
      return newNode(Token.VOID);

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[479]++;
      return parseTypeExpression(next());
    }
  }

  /**
   * UnionType := '(' TypeUnionList ')'
   * TypeUnionList := TypeExpression | TypeExpression '|' TypeUnionList
   *
   * We've removed the empty union type.
   */
  private Node parseUnionType(JsDocToken token) {
    return parseUnionTypeWithAlternate(token, null);
  }

  /**
   * Create a new union type, with an alternate that has already been
   * parsed. The alternate may be null.
   */
  private Node parseUnionTypeWithAlternate(JsDocToken token, Node alternate) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[737]++;
    Node union = newNode(Token.PIPE);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[738]++;
int CodeCoverConditionCoverageHelper_C204;
    if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((alternate != null) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[480]++;
      union.addChildToBack(alternate);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[739]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[481]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[740]++;

    Node expr = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[741]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[34]++;


int CodeCoverConditionCoverageHelper_C205;
    do {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[34]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[35]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[36]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[742]++;
int CodeCoverConditionCoverageHelper_C206;
      if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((expr != null) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[482]++;
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[743]++;
        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[744]++;
        Preconditions.checkState(
            token == JsDocToken.PIPE || token == JsDocToken.COMMA);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[745]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[746]++;

        boolean isPipe = token == JsDocToken.PIPE;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[747]++;
int CodeCoverConditionCoverageHelper_C207;
        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (8)) == 0 || true) &&
 ((isPipe) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((match(JsDocToken.PIPE)) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 2) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 2) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[484]++;
          // We support double pipes for backwards compatibility.
          next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[748]++;

        } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[485]++;}
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[749]++;
        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[750]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[483]++;}
      expr = parseTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[751]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[752]++;
int CodeCoverConditionCoverageHelper_C208;
      if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((expr == null) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[486]++;
        return null;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[487]++;}

      union.addChildToBack(expr);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[753]++;
      // We support commas for backwards compatibility.
    } while ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((match(JsDocToken.PIPE, JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[754]++;
int CodeCoverConditionCoverageHelper_C209;

    if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((alternate == null) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[488]++;
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[755]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[756]++;
int CodeCoverConditionCoverageHelper_C210;
      if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RP)) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[490]++;
        return reportTypeSyntaxWarning("msg.jsdoc.missing.rp");

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[491]++;}
      next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[757]++;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[489]++;}
    return union;
  }

  /**
   * ArrayType := '[' ElementTypeList ']'
   * ElementTypeList := <empty> | TypeExpression | '...' TypeExpression
   *     | TypeExpression ',' ElementTypeList
   */
  private Node parseArrayType(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[758]++;
    Node array = newNode(Token.LB);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[759]++;
    Node arg = null;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[760]++;
    boolean hasVarArgs = false;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[761]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[37]++;


int CodeCoverConditionCoverageHelper_C211;

    do {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[37]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[38]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[39]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[762]++;
int CodeCoverConditionCoverageHelper_C212;
      if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[492]++;
        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[763]++;
        skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[764]++;
        token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[765]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[493]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[766]++;
int CodeCoverConditionCoverageHelper_C213;
      if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((token == JsDocToken.ELLIPSIS) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[494]++;
        arg = wrapNode(Token.ELLIPSIS, parseTypeExpression(next()));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[767]++;
        hasVarArgs = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[768]++;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[495]++;
        arg = parseTypeExpression(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[769]++;
      }
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[770]++;
int CodeCoverConditionCoverageHelper_C214;

      if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((arg == null) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[496]++;
        return null;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[497]++;}

      array.addChildToBack(arg);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[771]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[772]++;
int CodeCoverConditionCoverageHelper_C215;
      if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((hasVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[498]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[773]++;
        break;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[499]++;}
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[774]++;
    } while ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false));
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[775]++;
int CodeCoverConditionCoverageHelper_C216;

    if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RB)) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[500]++;
      return reportTypeSyntaxWarning("msg.jsdoc.missing.rb");

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[501]++;}
    next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[776]++;
    return array;
  }

  /**
   * RecordType := '{' FieldTypeList '}'
   */
  private Node parseRecordType(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[777]++;
    Node recordType = newNode(Token.LC);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[778]++;
    Node fieldTypeList = parseFieldTypeList(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[779]++;
int CodeCoverConditionCoverageHelper_C217;

    if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((fieldTypeList == null) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[502]++;
      return reportGenericTypeSyntaxWarning();

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[503]++;}

    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[780]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[781]++;
int CodeCoverConditionCoverageHelper_C218;
    if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((match(JsDocToken.RC)) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[504]++;
      return reportTypeSyntaxWarning("msg.jsdoc.missing.rc");

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[505]++;}

    next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[782]++;

    recordType.addChildToBack(fieldTypeList);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[783]++;
    return recordType;
  }

  /**
   * FieldTypeList := FieldType | FieldType ',' FieldTypeList
   */
  private Node parseFieldTypeList(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[784]++;
    Node fieldTypeList = newNode(Token.LB);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[785]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[40]++;



    do {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[40]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[41]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[42]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[786]++;
      Node fieldType = parseFieldType(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[787]++;
int CodeCoverConditionCoverageHelper_C220;

      if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[506]++;
        return null;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[507]++;}

      fieldTypeList.addChildToBack(fieldType);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[788]++;

      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[789]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[790]++;
int CodeCoverConditionCoverageHelper_C221;
      if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[508]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[791]++;
        break;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[509]++;}

      // Move to the comma token.
      next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[792]++;

      // Move to the token passed the comma.
      skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[793]++;
      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[794]++;
    } while (true);

    return fieldTypeList;
  }

  /**
   * FieldType := FieldName | FieldName ':' TypeExpression
   */
  private Node parseFieldType(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[795]++;
    Node fieldName = parseFieldName(token);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[796]++;
int CodeCoverConditionCoverageHelper_C222;

    if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((fieldName == null) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[510]++;
      return null;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[511]++;}

    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[797]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[798]++;
int CodeCoverConditionCoverageHelper_C223;
    if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((match(JsDocToken.COLON)) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[512]++;
      return fieldName;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[513]++;}

    // Move to the colon.
    next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[799]++;

    // Move to the token after the colon and parse
    // the type expression.
    skipEOLs();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[800]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[801]++;
    Node typeExpression = parseTypeExpression(next());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[802]++;
int CodeCoverConditionCoverageHelper_C224;

    if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((typeExpression == null) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[514]++;
      return null;

    } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[515]++;}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[803]++;

    Node fieldType = newNode(Token.COLON);
    fieldType.addChildToBack(fieldName);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[804]++;
    fieldType.addChildToBack(typeExpression);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[805]++;
    return fieldType;
  }

  /**
   * FieldName := NameExpression | StringLiteral | NumberLiteral |
   * ReservedIdentifier
   */
  private Node parseFieldName(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[806]++;
    switch (token) {
      case STRING:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[516]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[807]++;
        String string = stream.getString();
        return newStringNode(string);

      default:
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[517]++;
        return null;
    }
  }

  private Node wrapNode(int type, Node n) {
    return n == null ? null :
        new Node(type, n, stream.getLineno(),
            stream.getCharno()).clonePropsFrom(templateNode);
  }

  private Node newNode(int type) {
    return new Node(type, stream.getLineno(),
        stream.getCharno()).clonePropsFrom(templateNode);
  }

  private Node newStringNode(String s) {
    return newStringNode(s, stream.getLineno(), stream.getCharno());
  }

  private Node newStringNode(String s, int lineno, int charno) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[808]++;
    Node n = Node.newString(s, lineno, charno).clonePropsFrom(templateNode);
    n.setLength(s.length());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[809]++;
    return n;
  }

  // This is similar to IRFactory.createTemplateNode to share common props
  // e.g., source-name, between all nodes.
  private Node createTemplateNode() {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[810]++;
    // The Node type choice is arbitrary.
    Node templateNode = IR.script();
    templateNode.setStaticSourceFile(
      this.associatedNode != null ?
      this.associatedNode.getStaticSourceFile() :
      null);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[811]++;
    return templateNode;
  }

  private Node reportTypeSyntaxWarning(String warning) {
    parser.addTypeWarning(warning, stream.getLineno(), stream.getCharno());
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[812]++;
    return null;
  }

  private Node reportGenericTypeSyntaxWarning() {
    return reportTypeSyntaxWarning("msg.jsdoc.type.syntax");
  }

  /**
   * Eats tokens until {@link JsDocToken#EOL} included, and switches back the
   * state to {@link State#SEARCHING_ANNOTATION}.
   */
  private JsDocToken eatTokensUntilEOL() {
    return eatTokensUntilEOL(next());
  }

  /**
   * Eats tokens until {@link JsDocToken#EOL} included, and switches back the
   * state to {@link State#SEARCHING_ANNOTATION}.
   */
  private JsDocToken eatTokensUntilEOL(JsDocToken token) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[813]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[43]++;


    do {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[43]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[44]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[45]++;
}
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[814]++;
int CodeCoverConditionCoverageHelper_C226;
      if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (32)) == 0 || true) &&
 ((token == JsDocToken.EOL) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C226 |= (8)) == 0 || true) &&
 ((token == JsDocToken.EOC) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((token == JsDocToken.EOF) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 3) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 3) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[518]++;
        state = State.SEARCHING_ANNOTATION;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[815]++;
        return token;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[519]++;}
      token = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[816]++;
    } while (true);
  }

  /**
   * Specific value indicating that the {@link #unreadToken} contains no token.
   */
  private static final JsDocToken NO_UNREAD_TOKEN = null;
  static {
    CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[817]++;
  }

  /**
   * One token buffer.
   */
  private JsDocToken unreadToken = NO_UNREAD_TOKEN;
  {
    CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[818]++;
  }

  /** Restores the lookahead token to the token stream */
  private void restoreLookAhead(JsDocToken token) {
    unreadToken = token;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[819]++;
  }

  /**
   * Tests whether the next symbol of the token stream matches the specific
   * token.
   */
  private boolean match(JsDocToken token) {
    unreadToken = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[820]++;
    return unreadToken == token;
  }

  /**
   * Tests that the next symbol of the token stream matches one of the specified
   * tokens.
   */
  private boolean match(JsDocToken token1, JsDocToken token2) {
    unreadToken = next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[821]++;
    return unreadToken == token1 || unreadToken == token2;
  }

  /**
   * Gets the next token of the token stream or the buffered token if a matching
   * was previously made.
   */
  private JsDocToken next() {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[822]++;
int CodeCoverConditionCoverageHelper_C227;
    if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((unreadToken == NO_UNREAD_TOKEN) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[520]++;
      return stream.getJsDocToken();

    } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[521]++;
      return current();
    }
  }

  /**
   * Gets the current token, invalidating it in the process.
   */
  private JsDocToken current() {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[823]++;
    JsDocToken t = unreadToken;
    unreadToken = NO_UNREAD_TOKEN;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[824]++;
    return t;
  }

  /**
   * Skips all EOLs and all empty lines in the JSDoc. Call this method if you
   * want the JSDoc entry to span multiple lines.
   */
  private void skipEOLs() {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[825]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[46]++;


int CodeCoverConditionCoverageHelper_C228;
    while ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((match(JsDocToken.EOL)) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[46]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[47]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[48]++;
}
      next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[826]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[827]++;
int CodeCoverConditionCoverageHelper_C229;
      if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((match(JsDocToken.STAR)) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[522]++;
        next();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[828]++;

      } else {
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[523]++;}
    }
  }

  /**
   * Determines whether the parser has been populated with docinfo with a
   * fileoverview tag.
   */
  private boolean hasParsedFileOverviewDocInfo() {
    return jsdocBuilder.isPopulatedWithFileOverview();
  }

  boolean hasParsedJSDocInfo() {
    return jsdocBuilder.isPopulated();
  }

  JSDocInfo retrieveAndResetParsedJSDocInfo() {
    return jsdocBuilder.build(associatedNode);
  }

  /**
   * Gets the fileoverview JSDocInfo, if any.
   */
  JSDocInfo getFileOverviewJSDocInfo() {
    return fileOverviewJSDocInfo;
  }

  /**
   * Look ahead for a type annotation by advancing the character stream.
   * Does not modify the token stream.
   * This is kind of a hack, and is only necessary because we use the token
   * stream to parse types, but need the underlying character stream to get
   * JsDoc descriptions.
   * @return Whether we found a type annotation.
   */
  private boolean lookAheadForTypeAnnotation() {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[829]++;
    boolean matchedLc = false;
    int c;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[830]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[49]++;


    while (true) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[49]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[50]--;
  CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.loops[51]++;
}
      c = stream.getChar();
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[831]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[832]++;
int CodeCoverConditionCoverageHelper_C231;
      if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((c == ' ') && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[524]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[833]++;
        continue;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[525]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[834]++;
int CodeCoverConditionCoverageHelper_C232; if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((c == '{') && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[526]++;
        matchedLc = true;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[835]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[836]++;
        break;

      } else {
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.branches[527]++;
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[837]++;
        break;
      }
}
    }
    stream.ungetChar(c);
CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1.statements[838]++;
    return matchedLc;
  }
}

class CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1 ());
  }
    public static long[] statements = new long[839];
    public static long[] branches = new long[528];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[233];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.parsing.JsDocInfoParser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,0,1,1,1,1,1,1,0,1,2,1,1,1,1,1,1,1,3,1,0,1,1,1,1,2,1,1,3,1,1,1,0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,3,1,1,1,0,1,1};
    for (int i = 1; i <= 232; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[52];

  public CodeCoverCoverageCounter$8p2tk4d8a7onx5ump6go8zacaeaswo1 () {
    super("com.google.javascript.jscomp.parsing.JsDocInfoParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 838; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 527; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 232; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 51; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.JsDocInfoParser.java");
      for (int i = 1; i <= 838; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 527; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 232; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 17; i++) {
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

