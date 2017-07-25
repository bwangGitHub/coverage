/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.ast.*;  // we use basically every class

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * This class implements the JavaScript parser.<p>
 *
 * It is based on the SpiderMonkey C source files jsparse.c and jsparse.h in the
 * jsref package.<p>
 *
 * The parser generates an {@link AstRoot} parse tree representing the source
 * code.  No tree rewriting is permitted at this stage, so that the parse tree
 * is a faithful representation of the source for frontend processing tools and
 * IDEs.<p>
 *
 * This parser implementation is not intended to be reused after a parse
 * finishes, and will throw an IllegalStateException() if invoked again.<p>
 *
 * @see TokenStream
 *
 */
public class Parser
{
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.ping();
  }

    /**
     * Maximum number of allowed function or constructor arguments,
     * to follow SpiderMonkey.
     */
    public static final int ARGC_LIMIT = 1 << 16;
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1]++;
  }

    // TokenInformation flags : currentFlaggedToken stores them together
    // with token type
    final static int
        CLEAR_TI_MASK    = 0xFFFF,  // mask to clear token information bits
        TI_AFTER_EOL     = 1 << 16, // first token of the source line
        TI_CHECK_LABEL   = 1 << 17;
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[2]++;
  } // indicates to check for label

    CompilerEnvirons compilerEnv;
    private ErrorReporter errorReporter;
    private IdeErrorReporter errorCollector;
    private String sourceURI;
    private char[] sourceChars;

    boolean calledByCompileFunction;  // ugly - set directly by Context
    private boolean parseFinished;  // set when finished to prevent reuse

    private TokenStream ts;
    private int currentFlaggedToken = Token.EOF;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[3]++;
  }
    private int currentToken;
    private int syntaxErrorCount;

    private List<Comment> scannedComments;
    private Comment currentJsDocComment;

    protected int nestingOfFunction;
    private LabeledStatement currentLabel;
    private boolean inDestructuringAssignment;
    protected boolean inUseStrictDirective;

    // The following are per function variables and should be saved/restored
    // during function parsing.  See PerFunctionVariables class below.
    ScriptNode currentScriptOrFn;
    Scope currentScope;
    private int endFlags;
    private boolean inForInit;  // bound temporarily during forStatement()
    private Map<String,LabeledStatement> labelSet;
    private List<Loop> loopSet;
    private List<Jump> loopAndSwitchSet;
    // end of per function variables

    // Lacking 2-token lookahead, labels become a problem.
    // These vars store the token info of the last matched name,
    // iff it wasn't the last matched token.
    private int prevNameTokenStart;
    private String prevNameTokenString = "";
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[4]++;
  }
    private int prevNameTokenLineno;

    // Exception to unwind
    private static class ParserException extends RuntimeException
    {
        static final long serialVersionUID = 5882582646773765630L;
    }

    public Parser() {
        this(new CompilerEnvirons());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[5]++;
    }

    public Parser(CompilerEnvirons compilerEnv) {
        this(compilerEnv, compilerEnv.getErrorReporter());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[6]++;
    }

    public Parser(CompilerEnvirons compilerEnv, ErrorReporter errorReporter) {
        this.compilerEnv = compilerEnv;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[7]++;
        this.errorReporter = errorReporter;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[8]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorReporter instanceof IdeErrorReporter) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[1]++;
            errorCollector = (IdeErrorReporter)errorReporter;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[10]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[2]++;}
    }

    // Add a strict warning on the last matched token.
    void addStrictWarning(String messageId, String messageArg) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[11]++;
        int beg = -1, end = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ts != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[3]++;
            beg = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[13]++;
            end = ts.tokenEnd - ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[14]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[4]++;}
        addStrictWarning(messageId, messageArg, beg, end);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[15]++;
    }

    void addStrictWarning(String messageId, String messageArg,
                          int position, int length) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((compilerEnv.isStrictMode()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[5]++;
            addWarning(messageId, messageArg, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[17]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[6]++;}
    }

    void addWarning(String messageId, String messageArg) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[18]++;
        int beg = -1, end = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ts != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[7]++;
            beg = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[20]++;
            end = ts.tokenEnd - ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[21]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[8]++;}
        addWarning(messageId, messageArg, beg, end);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[22]++;
    }

    void addWarning(String messageId, int position, int length) {
        addWarning(messageId, null, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[23]++;
    }

    void addWarning(String messageId, String messageArg,
                    int position, int length)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[24]++;
        String message = lookupMessage(messageId, messageArg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((compilerEnv.reportWarningAsError()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[9]++;
            addError(messageId, messageArg, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[26]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[10]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[27]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((errorCollector != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[11]++;
            errorCollector.warning(message, sourceURI, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[28]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[12]++;
            errorReporter.warning(message, sourceURI, ts.getLineno(),
                                  ts.getLine(), ts.getOffset());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[29]++;
        }
}
    }

    void addError(String messageId) {
        addError(messageId, ts.tokenBeg, ts.tokenEnd - ts.tokenBeg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[30]++;
    }

    void addError(String messageId, int position, int length) {
        addError(messageId, null, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[31]++;
    }

    void addError(String messageId, String messageArg) {
        addError(messageId, messageArg, ts.tokenBeg,
                 ts.tokenEnd - ts.tokenBeg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[32]++;
    }

    void addError(String messageId, String messageArg, int position, int length)
    {
        ++syntaxErrorCount;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[33]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[34]++;
        String message = lookupMessage(messageId, messageArg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((errorCollector != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[13]++;
            errorCollector.error(message, sourceURI, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[36]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[14]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[37]++;
            int lineno = 1, offset = 1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[38]++;
            String line = "";
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ts != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[15]++;  // happens in some regression tests
                lineno = ts.getLineno();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[40]++;
                line = ts.getLine();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[41]++;
                offset = ts.getOffset();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[42]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[16]++;}
            errorReporter.error(message, sourceURI, lineno, line, offset);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[43]++;
        }
    }

    String lookupMessage(String messageId) {
        return lookupMessage(messageId, null);
    }

    String lookupMessage(String messageId, String messageArg) {
        return messageArg == null
            ? ScriptRuntime.getMessage0(messageId)
            : ScriptRuntime.getMessage1(messageId, messageArg);
    }

    void reportError(String messageId) {
        reportError(messageId, null);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[44]++;
    }

    void reportError(String messageId, String messageArg) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ts == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[17]++;  // happens in some regression tests
            reportError(messageId, messageArg, 1, 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[46]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[18]++;
            reportError(messageId, messageArg, ts.tokenBeg,
                        ts.tokenEnd - ts.tokenBeg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[47]++;
        }
    }

    void reportError(String messageId, int position, int length)
    {
        reportError(messageId, null, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[48]++;
    }

    void reportError(String messageId, String messageArg, int position,
                     int length)
    {
        addError(messageId, position, length);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[49]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[50]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((compilerEnv.recoverFromErrors()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[19]++;
            throw new ParserException();

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[20]++;}
    }

    // Computes the absolute end offset of node N.
    // Use with caution!  Assumes n.getPosition() is -absolute-, which
    // is only true before the node is added to its parent.
    private int getNodeEnd(AstNode n) {
        return n.getPosition() + n.getLength();
    }

    private void recordComment(int lineno, String comment) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((scannedComments == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[21]++;
            scannedComments = new ArrayList<Comment>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[52]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[22]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[53]++;
        Comment commentNode = new Comment(ts.tokenBeg,
                                          ts.getTokenLength(),
                                          ts.commentType,
                                          comment);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((ts.commentType == Token.CommentType.JSDOC) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((compilerEnv.isRecordingLocalJsDocComments()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[23]++;
            currentJsDocComment = commentNode;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[55]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[24]++;}
        commentNode.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[56]++;
        scannedComments.add(commentNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[57]++;
    }

    private Comment getAndResetJsDoc() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[58]++;
        Comment saved = currentJsDocComment;
        currentJsDocComment = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[59]++;
        return saved;
    }


    private int getNumberOfEols(String comment) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[60]++;
      int lines = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[61]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
      for (int i = comment.length()-1;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[1]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[2]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[3]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[62]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((comment.charAt(i) == '\n') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[25]++;
          lines++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[63]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[26]++;}
      }
      return lines;
    }


    // Returns the next token without consuming it.
    // If previous token was consumed, calls scanner to get new token.
    // If previous token was -not- consumed, returns it (idempotent).
    //
    // This function will not return a newline (Token.EOL - instead, it
    // gobbles newlines until it finds a non-newline token, and flags
    // that token as appearing just after a newline.
    //
    // This function will also not return a Token.COMMENT.  Instead, it
    // records comments in the scannedComments list.  If the token
    // returned by this function immediately follows a jsdoc comment,
    // the token is flagged as such.
    //
    // Note that this function always returned the un-flagged token!
    // The flags, if any, are saved in currentFlaggedToken.
    private int peekToken()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;
        // By far the most common case:  last token hasn't been consumed,
        // so return already-peeked token.
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((currentFlaggedToken != Token.EOF) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[27]++;
            return currentToken;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[28]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[65]++;

        int lineno = ts.getLineno();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[66]++;
        int tt = ts.getToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[67]++;
        boolean sawEOL = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[68]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;

        // process comments and whitespace
        while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((tt == Token.EOL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((tt == Token.COMMENT) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[4]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[5]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[6]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((tt == Token.EOL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[29]++;
                lineno++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[70]++;
                sawEOL = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[71]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[30]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((compilerEnv.isRecordingComments()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[31]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[73]++;
                    String comment = ts.getAndResetCurrentComment();
                    recordComment(lineno, comment);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[74]++;
                    // Comments may contain multiple lines, get the number
                    // of EoLs and increase the lineno
                    lineno += getNumberOfEols(comment);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[75]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[32]++;}
            }
            tt = ts.getToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[76]++;
        }

        currentToken = tt;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[77]++;
        currentFlaggedToken = tt | (sawEOL ? TI_AFTER_EOL : 0);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[78]++;
        return currentToken;  // return unflagged token
    }

    private int peekFlaggedToken()
        throws IOException
    {
        peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[79]++;
        return currentFlaggedToken;
    }

    private void consumeToken() {
        currentFlaggedToken = Token.EOF;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[80]++;
    }

    private int nextToken()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[81]++;
        int tt = peekToken();
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[82]++;
        return tt;
    }

    private int nextFlaggedToken()
        throws IOException
    {
        peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[83]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[84]++;
        int ttFlagged = currentFlaggedToken;
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[85]++;
        return ttFlagged;
    }

    private boolean matchToken(int toMatch)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[86]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((peekToken() != toMatch) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[34]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[87]++;
        return true;
    }

    // Returns Token.EOL if the current token follows a newline, else returns
    // the current token.  Used in situations where we don't consider certain
    // token types valid if they are preceded by a newline.  One example is the
    // postfix ++ or -- operator, which has to be on the same line as its
    // operand.
    private int peekTokenOrEOL()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[88]++;
        int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[89]++;
int CodeCoverConditionCoverageHelper_C20;
        // Check for last peeked token flags
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 (((currentFlaggedToken & TI_AFTER_EOL) != 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[35]++;
            tt = Token.EOL;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[90]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[36]++;}
        return tt;
    }

    private boolean mustMatchToken(int toMatch, String messageId)
        throws IOException
    {
        return mustMatchToken(toMatch, messageId, ts.tokenBeg,
                              ts.tokenEnd - ts.tokenBeg);
    }

    private boolean mustMatchToken(int toMatch, String msgId, int pos, int len)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[91]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((matchToken(toMatch)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[37]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[38]++;}
        reportError(msgId, pos, len);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[92]++;
        return false;
    }

    private void mustHaveXML() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[93]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((compilerEnv.isXmlAvailable()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[39]++;
            reportError("msg.XML.not.available");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[94]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[40]++;}
    }

    public boolean eof() {
        return ts.eof();
    }

    boolean insideFunction() {
        return nestingOfFunction != 0;
    }

    void pushScope(Scope scope) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[95]++;
        Scope parent = scope.getParentScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[96]++;
int CodeCoverConditionCoverageHelper_C23;
        // During codegen, parent scope chain may already be initialized,
        // in which case we just need to set currentScope variable.
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[41]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[97]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parent != currentScope) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[43]++;
                codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[98]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[44]++;}

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[42]++;
            currentScope.addChildScope(scope);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[99]++;
        }
        currentScope = scope;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[100]++;
    }

    void popScope() {
        currentScope = currentScope.getParentScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[101]++;
    }

    private void enterLoop(Loop loop) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[102]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((loopSet == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[45]++;
            loopSet = new ArrayList<Loop>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[103]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[46]++;}
        loopSet.add(loop);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[104]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[105]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((loopAndSwitchSet == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[47]++;
            loopAndSwitchSet = new ArrayList<Jump>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[106]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[48]++;}
        loopAndSwitchSet.add(loop);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[107]++;
        pushScope(loop);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[108]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[109]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((currentLabel != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[49]++;
            currentLabel.setStatement(loop);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[110]++;
            currentLabel.getFirstLabel().setLoop(loop);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[111]++;
            // This is the only time during parsing that we set a node's parent
            // before parsing the children.  In order for the child node offsets
            // to be correct, we adjust the loop's reported position back to an
            // absolute source offset, and restore it when we call exitLoop().
            loop.setRelative(-currentLabel.getPosition());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[112]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[50]++;}
    }

    private void exitLoop() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[113]++;
        Loop loop = loopSet.remove(loopSet.size() - 1);
        loopAndSwitchSet.remove(loopAndSwitchSet.size() - 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[114]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[115]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((loop.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[51]++;  // see comment in enterLoop
            loop.setRelative(loop.getParent().getPosition());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[116]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[52]++;}
        popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[117]++;
    }

    private void enterSwitch(SwitchStatement node) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[118]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((loopAndSwitchSet == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[53]++;
            loopAndSwitchSet = new ArrayList<Jump>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[119]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[54]++;}
        loopAndSwitchSet.add(node);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[120]++;
    }

    private void exitSwitch() {
        loopAndSwitchSet.remove(loopAndSwitchSet.size() - 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[121]++;
    }

    /**
     * Builds a parse tree from the given source string.
     *
     * @return an {@link AstRoot} object representing the parsed program.  If
     * the parse fails, {@code null} will be returned.  (The parse failure will
     * result in a call to the {@link ErrorReporter} from
     * {@link CompilerEnvirons}.)
     */
    public AstRoot parse(String sourceString, String sourceURI, int lineno)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[122]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((parseFinished) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[55]++; throw new IllegalStateException("parser reused");
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[56]++;}
        this.sourceURI = sourceURI;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[123]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[124]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[57]++;
            this.sourceChars = sourceString.toCharArray();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[125]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[58]++;}
        this.ts = new TokenStream(this, null, sourceString, lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[126]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[127]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return parse();
        } catch (IOException iox) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[60]++;
            // Should never happen
            throw new IllegalStateException();
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[59]++;
}
            parseFinished = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[128]++;
        }
    }

    /**
     * Builds a parse tree from the given sourcereader.
     * @see #parse(String,String,int)
     * @throws IOException if the {@link Reader} encounters an error
     */
    public AstRoot parse(Reader sourceReader, String sourceURI, int lineno)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[129]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((parseFinished) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[61]++; throw new IllegalStateException("parser reused");
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[62]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[130]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[63]++;
            return parse(readFully(sourceReader), sourceURI, lineno);

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[64]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[131]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            this.sourceURI = sourceURI;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[132]++;
            ts = new TokenStream(this, sourceReader, null, lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[133]++;
            return parse();
        } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[65]++;
}
            parseFinished = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[134]++;
        }
    }

    private AstRoot parse() throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[135]++;
        int pos = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[136]++;
        AstRoot root = new AstRoot(pos);
        currentScope = currentScriptOrFn = root;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[137]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[138]++;

        int baseLineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[139]++;  // line number where source starts
        int end = pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[140]++;  // in case source is empty

        boolean inDirectivePrologue = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[141]++;
        boolean savedStrictMode = inUseStrictDirective;
        // TODO: eval code should get strict mode from invoking code
        inUseStrictDirective = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[142]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[143]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[144]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[7]++;


            for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[7]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[8]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[9]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[145]++;
                int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[146]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((tt <= Token.EOF) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[67]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[147]++;
                    break;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[68]++;}

                AstNode n;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[148]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((tt == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[69]++;
                    consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[149]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[150]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                    try {
CodeCoverTryBranchHelper_Try4 = true;
                        n = function(calledByCompileFunction
                                     ? FunctionNode.FUNCTION_EXPRESSION
                                     : FunctionNode.FUNCTION_STATEMENT);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[151]++;
                    } catch (ParserException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[72]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[152]++;
                        break;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[71]++;
}
  }

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[70]++;
                    n = statement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[153]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[154]++;
int CodeCoverConditionCoverageHelper_C37;
                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((inDirectivePrologue) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[73]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[155]++;
                        String directive = getDirective(n);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[156]++;
int CodeCoverConditionCoverageHelper_C38;
                        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((directive == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[75]++;
                            inDirectivePrologue = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[157]++;

                        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[76]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[158]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((directive.equals("use strict")) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[77]++;
                            inUseStrictDirective = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[159]++;
                            root.setInStrictMode(true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[160]++;

                        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[78]++;}
}

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[74]++;}

                }
                end = getNodeEnd(n);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[161]++;
                root.addChildToBack(n);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[162]++;
                n.setParent(root);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[163]++;
            }
        } catch (StackOverflowError ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[79]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[164]++;
            String msg = lookupMessage("msg.too.deep.parser.recursion");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[165]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[80]++;
                throw Context.reportRuntimeError(msg, sourceURI,
                                                 ts.lineno, null, 0);
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[81]++;}
        } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[66]++;
}
            inUseStrictDirective = savedStrictMode;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[166]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[167]++;
int CodeCoverConditionCoverageHelper_C41;

        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.syntaxErrorCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[82]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[168]++;
            String msg = String.valueOf(this.syntaxErrorCount);
            msg = lookupMessage("msg.got.syntax.errors", msg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[169]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[170]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[84]++;
                throw errorReporter.runtimeError(msg, sourceURI, baseLineno,
                                                 null, 0);
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[85]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[83]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[171]++;
int CodeCoverConditionCoverageHelper_C43;

        // add comments to root in lexical order
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((scannedComments != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[86]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[172]++;
            // If we find a comment beyond end of our last statement or
            // function, extend the root bounds to the end of that comment.
            int last = scannedComments.size() - 1;
            end = Math.max(end, getNodeEnd(scannedComments.get(last)));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[173]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[174]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[10]++;


            for (Comment c : scannedComments) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[10]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[11]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[12]++;
}
                root.addComment(c);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[175]++;
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[87]++;}

        root.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[176]++;
        root.setSourceName(sourceURI);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[177]++;
        root.setBaseLineno(baseLineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[178]++;
        root.setEndLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[179]++;
        return root;
    }

    private AstNode parseFunctionBody()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[180]++;
        boolean isExpressionClosure = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[181]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((matchToken(Token.LC)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[88]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[182]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((compilerEnv.getLanguageVersion() < Context.VERSION_1_8) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[90]++;
                reportError("msg.no.brace.body");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[183]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[91]++;
                isExpressionClosure = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[184]++;
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[89]++;}
        ++nestingOfFunction;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[185]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[186]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[187]++;
        Block pn = new Block(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[188]++;  // starts at LC position

        boolean inDirectivePrologue = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[189]++;
        boolean savedStrictMode = inUseStrictDirective;
        // Don't set 'inUseStrictDirective' to false: inherit strict mode.

        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[190]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[191]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
        try {
CodeCoverTryBranchHelper_Try5 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[192]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((isExpressionClosure) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[93]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[193]++;
                ReturnStatement n = new ReturnStatement(ts.lineno);
                n.setReturnValue(assignExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[194]++;
                // expression closure flag is required on both nodes
                n.putProp(Node.EXPRESSION_CLOSURE_PROP, Boolean.TRUE);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[195]++;
                pn.putProp(Node.EXPRESSION_CLOSURE_PROP, Boolean.TRUE);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[196]++;
                pn.addStatement(n);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[197]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[94]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[198]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[13]++;


                bodyLoop: for (;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[13]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[14]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[15]++;
}
                    AstNode n;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[199]++;
                    int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[200]++;
                    switch (tt) {
                        case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[95]++;
                        case Token.EOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[96]++;
                        case Token.RC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[97]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[201]++;
                            break bodyLoop;

                        case Token.FUNCTION:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[98]++;
                            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[202]++;
                            n = function(FunctionNode.FUNCTION_STATEMENT);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[203]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[204]++;
                            break;
                        default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[99]++;
                            n = statement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[205]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[206]++;
int CodeCoverConditionCoverageHelper_C48;
                            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((inDirectivePrologue) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[100]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[207]++;
                                String directive = getDirective(n);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[208]++;
int CodeCoverConditionCoverageHelper_C49;
                                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((directive == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[102]++;
                                    inDirectivePrologue = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[209]++;

                                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[103]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[210]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((directive.equals("use strict")) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[104]++;
                                    inUseStrictDirective = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[211]++;

                                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[105]++;}
}

                            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[101]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[212]++;
                            break;
                    }
                    pn.addStatement(n);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[213]++;
                }
            }
        } catch (ParserException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[106]++;
            // Ignore it
        } finally {
if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[92]++;
}
            --nestingOfFunction;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[214]++;
            inUseStrictDirective = savedStrictMode;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[215]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[216]++;

        int end = ts.tokenEnd;
        getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[217]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[218]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((isExpressionClosure) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RC, "msg.no.brace.after.body")) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[107]++;
            end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[219]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[108]++;}
        pn.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[220]++;
        return pn;
    }

    private String getDirective(AstNode n) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[221]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((n instanceof ExpressionStatement) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[109]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[222]++;
            AstNode e = ((ExpressionStatement) n).getExpression();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[223]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((e instanceof StringLiteral) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[111]++;
                return ((StringLiteral) e).getValue();

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[112]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[110]++;}
        return null;
    }

    private void  parseFunctionParams(FunctionNode fnNode)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[224]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((matchToken(Token.RP)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[113]++;
            fnNode.setRp(ts.tokenBeg - fnNode.getPosition());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[225]++;
            return;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[114]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[226]++;
        // Would prefer not to call createDestructuringAssignment until codegen,
        // but the symbol definitions have to happen now, before body is parsed.
        Map<String, Node> destructuring = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[227]++;
        Set<String> paramNames = new HashSet<String>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[228]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[16]++;


int CodeCoverConditionCoverageHelper_C55;
        do {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[16]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[17]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[18]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[229]++;
            int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[230]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((tt == Token.LB) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((tt == Token.LC) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[115]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[231]++;
                AstNode expr = destructuringPrimaryExpr();
                markDestructuring(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[232]++;
                fnNode.addParam(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[233]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[234]++;
int CodeCoverConditionCoverageHelper_C57;
                // Destructuring assignment for parameters: add a dummy
                // parameter name, and add a statement to the body to initialize
                // variables from the destructuring assignment
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((destructuring == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[117]++;
                    destructuring = new HashMap<String, Node>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[235]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[118]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[236]++;
                String pname = currentScriptOrFn.getNextTempName();
                defineSymbol(Token.LP, pname, false);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[237]++;
                destructuring.put(pname, expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[238]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[116]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[239]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.NAME, "msg.no.parm")) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[119]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[240]++;
                    Name paramNameNode = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[241]++;
                    Comment jsdocNodeForName = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[242]++;
int CodeCoverConditionCoverageHelper_C59;
                    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((jsdocNodeForName != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[121]++;
                      paramNameNode.setJsDocNode(jsdocNodeForName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[243]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[122]++;}
                    fnNode.addParam(paramNameNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[244]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[245]++;
                    String paramName = ts.getString();
                    defineSymbol(Token.LP, paramName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[246]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[247]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[123]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[248]++;
int CodeCoverConditionCoverageHelper_C61;
                        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 (("eval".equals(paramName)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 (("arguments".equals(paramName)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false))
                        {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[125]++;
                            reportError("msg.bad.id.strict", paramName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[249]++;

                        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[126]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[250]++;
int CodeCoverConditionCoverageHelper_C62;
                        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((paramNames.contains(paramName)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[127]++;
                            addError("msg.dup.param.strict", paramName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[251]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[128]++;}
                        paramNames.add(paramName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[252]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[124]++;}

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[120]++;
                    fnNode.addParam(makeErrorNode());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[253]++;
                }
            }
        } while ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((matchToken(Token.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[254]++;
int CodeCoverConditionCoverageHelper_C63;

        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((destructuring != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[129]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[255]++;
            Node destructuringNode = new Node(Token.COMMA);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[256]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[19]++;


            // Add assignment helper for each destructuring parameter
            for (Map.Entry<String, Node> param: destructuring.entrySet()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[19]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[20]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[21]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[257]++;
                Node assign = createDestructuringAssignment(Token.VAR,
                        param.getValue(), createName(param.getKey()));
                destructuringNode.addChildToBack(assign);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[258]++;

            }
            fnNode.putProp(Node.DESTRUCTURING_PARAMS, destructuringNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[259]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[130]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[260]++;
int CodeCoverConditionCoverageHelper_C64;

        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.after.parms")) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[131]++;
            fnNode.setRp(ts.tokenBeg - fnNode.getPosition());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[261]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[132]++;}
    }

    private FunctionNode function(int type)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[262]++;
        int syntheticType = type;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[263]++;
        int baseLineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[264]++;  // line number where source starts
        int functionSourceStart = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[265]++;  // start of "function" kwd
        Name name = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[266]++;
        AstNode memberExprNode = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[267]++;
int CodeCoverConditionCoverageHelper_C65;

        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((matchToken(Token.NAME)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[133]++;
            name = createNameNode(true, Token.NAME);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[268]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[269]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[135]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[270]++;
                String id = name.getIdentifier();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[271]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 (("eval".equals(id)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 (("arguments".equals(id)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[137]++;
                    reportError("msg.bad.id.strict", id);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[272]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[138]++;}

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[136]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[273]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((matchToken(Token.LP)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[139]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[274]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((compilerEnv.isAllowMemberExprAsFunctionName()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[141]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[275]++;
                    AstNode memberExprHead = name;
                    name = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[276]++;
                    memberExprNode = memberExprTail(false, memberExprHead);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[277]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[142]++;}
                mustMatchToken(Token.LP, "msg.no.paren.parms");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[278]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[140]++;}

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[134]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[279]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((matchToken(Token.LP)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[143]++;

            // Anonymous function:  leave name as null
        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[144]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[280]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((compilerEnv.isAllowMemberExprAsFunctionName()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[145]++;
                // Note that memberExpr can not start with '(' like
                // in function (1+2).toString(), because 'function (' already
                // processed as anonymous function
                memberExprNode = memberExpr(false);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[281]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[146]++;}
            mustMatchToken(Token.LP, "msg.no.paren.parms");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[282]++;
        }
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[283]++;
        int lpPos = currentToken == Token.LP ? ts.tokenBeg : -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[284]++;
int CodeCoverConditionCoverageHelper_C72;

        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((memberExprNode != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[147]++;
            syntheticType = FunctionNode.FUNCTION_EXPRESSION;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[285]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[148]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[286]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (32)) == 0 || true) &&
 ((syntheticType != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((name.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[149]++;
            // Function statements define a symbol in the enclosing scope
            defineSymbol(Token.FUNCTION, name.getIdentifier());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[287]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[150]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[288]++;

        FunctionNode fnNode = new FunctionNode(functionSourceStart, name);
        fnNode.setFunctionType(type);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[289]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[290]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((lpPos != -1) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[151]++;
            fnNode.setLp(lpPos - functionSourceStart);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[291]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[152]++;}

        fnNode.setJsDocNode(getAndResetJsDoc());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[292]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[293]++;

        PerFunctionVariables savedVars = new PerFunctionVariables(fnNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[294]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
        try {
CodeCoverTryBranchHelper_Try6 = true;
            parseFunctionParams(fnNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[295]++;
            fnNode.setBody(parseFunctionBody());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[296]++;
            fnNode.setEncodedSourceBounds(functionSourceStart, ts.tokenEnd);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[297]++;
            fnNode.setLength(ts.tokenEnd - functionSourceStart);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[298]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[299]++;
int CodeCoverConditionCoverageHelper_C75;

            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((compilerEnv.isStrictMode()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((fnNode.getBody().hasConsistentReturnUsage()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[154]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[300]++;
                String msg = (name != null && name.length() > 0)
                           ? "msg.no.return.value"
                           : "msg.anon.no.return.value";
                addStrictWarning(msg, name == null ? "" : name.getIdentifier());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[301]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[155]++;}
        } finally {
if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[153]++;
}
            savedVars.restore();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[302]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[303]++;
int CodeCoverConditionCoverageHelper_C76;

        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((memberExprNode != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[156]++;
            // TODO(stevey): fix missing functionality
            Kit.codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[304]++;
            fnNode.setMemberExprNode(memberExprNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[305]++;
  // rewrite later
            /* old code:
            if (memberExprNode != null) {
                pn = nf.createAssignment(Token.ASSIGN, memberExprNode, pn);
                if (functionType != FunctionNode.FUNCTION_EXPRESSION) {
                    // XXX check JScript behavior: should it be createExprStatement?
                    pn = nf.createExprStatementNoReturn(pn, baseLineno);
                }
            }
            */
        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[157]++;}

        fnNode.setSourceName(sourceURI);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[306]++;
        fnNode.setBaseLineno(baseLineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[307]++;
        fnNode.setEndLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[308]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[309]++;
int CodeCoverConditionCoverageHelper_C77;

        // Set the parent scope.  Needed for finding undeclared vars.
        // Have to wait until after parsing the function to set its parent
        // scope, since defineSymbol needs the defining-scope check to stop
        // at the function boundary when checking for redeclarations.
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[158]++;
            fnNode.setParentScope(currentScope);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[310]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[159]++;}
        return fnNode;
    }

    // This function does not match the closing RC: the caller matches
    // the RC so it can provide a suitable error message if not matched.
    // This means it's up to the caller to set the length of the node to
    // include the closing RC.  The node start pos is set to the
    // absolute buffer start position, and the caller should fix it up
    // to be relative to the parent node.  All children of this block
    // node are given relative start positions and correct lengths.

    private AstNode statements(AstNode parent) throws IOException {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[311]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((currentToken != Token.LC) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[160]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[312]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[161]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[313]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[314]++;
        AstNode block = parent != null ? parent : new Block(pos);
        block.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[315]++;

        int tt;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[316]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[22]++;


        while ((tt = peekToken()) > Token.EOF && tt != Token.RC) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[22]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[23]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[24]++;
}
            block.addChild(statement());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[317]++;
        }
        block.setLength(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[318]++;
        return block;
    }

    private AstNode statements() throws IOException {
        return statements(null);
    }

    private static class ConditionData {
        AstNode condition;
        int lp = -1;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[319]++;
  }
        int rp = -1;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[320]++;
  }
    }

    // parse and return a parenthesized expression
    private ConditionData condition()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[321]++;
        ConditionData data = new ConditionData();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[322]++;
int CodeCoverConditionCoverageHelper_C80;

        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.cond")) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[162]++;
            data.lp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[323]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[163]++;}

        data.condition = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[324]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[325]++;
int CodeCoverConditionCoverageHelper_C81;

        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.after.cond")) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[164]++;
            data.rp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[326]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[165]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[327]++;
int CodeCoverConditionCoverageHelper_C82;

        // Report strict warning on code like "if (a = 7) ...". Suppress the
        // warning if the condition is parenthesized, like "if ((a = 7)) ...".
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((data.condition instanceof Assignment) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[166]++;
            addStrictWarning("msg.equal.as.assign", "",
                             data.condition.getPosition(),
                             data.condition.getLength());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[328]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[167]++;}
        return data;
    }

    private AstNode statement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[329]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[330]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[331]++;
            AstNode pn = statementHelper();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[332]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((pn != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[169]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[333]++;
int CodeCoverConditionCoverageHelper_C84;
                if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((compilerEnv.isStrictMode()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((pn.hasSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[171]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[334]++;
                    int beg = pn.getPosition();
                    beg = Math.max(beg, lineBeginningFor(beg));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[335]++;
                    addStrictWarning(pn instanceof EmptyStatement
                                     ? "msg.extra.trailing.semi"
                                     : "msg.no.side.effects",
                                     "", beg, nodeEnd(pn) - beg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[336]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[172]++;}
                return pn;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[170]++;}
        } catch (ParserException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[173]++;
            // an ErrorNode was added to the ErrorReporter
        } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[168]++;
}
  }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[337]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[25]++;



        // error:  skip ahead to a probable statement boundary
        guessingStatementEnd: for (;;) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[25]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[26]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[27]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[338]++;
            int tt = peekTokenOrEOL();
            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[339]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[340]++;
            switch (tt) {
              case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[174]++;
              case Token.EOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[175]++;
              case Token.EOL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[176]++;
              case Token.SEMI:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[177]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[341]++;
                break guessingStatementEnd; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[178]++;
            }
        }
        // We don't make error nodes explicitly part of the tree;
        // they get added to the ErrorReporter.  May need to do
        // something different here.
        return new EmptyStatement(pos, ts.tokenBeg - pos);
    }

    private AstNode statementHelper()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[342]++;
int CodeCoverConditionCoverageHelper_C86;
        // If the statement is set, then it's been told its label by now.
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((currentLabel != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((currentLabel.getStatement() != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[179]++;
            currentLabel = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[343]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[180]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[344]++;

        AstNode pn = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[345]++;
        int tt = peekToken(), pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[346]++;

        switch (tt) {
          case Token.IF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[181]++;
              return ifStatement();

          case Token.SWITCH:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[182]++;
              return switchStatement();

          case Token.WHILE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[183]++;
              return whileLoop();

          case Token.DO:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[184]++;
              return doLoop();

          case Token.FOR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[185]++;
              return forLoop();

          case Token.TRY:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[186]++;
              return tryStatement();

          case Token.THROW:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[187]++;
              pn = throwStatement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[347]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[348]++;
              break;

          case Token.BREAK:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[188]++;
              pn = breakStatement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[349]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[350]++;
              break;

          case Token.CONTINUE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[189]++;
              pn = continueStatement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[351]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[352]++;
              break;

          case Token.WITH:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[190]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[353]++;
int CodeCoverConditionCoverageHelper_C87;
              if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((this.inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[191]++;
                  reportError("msg.no.with.strict");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[354]++;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[192]++;}
              return withStatement();

          case Token.CONST:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[193]++;
          case Token.VAR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[194]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[355]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[356]++;
              int lineno = ts.lineno;
              pn = variables(currentToken, ts.tokenBeg, true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[357]++;
              pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[358]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[359]++;
              break;

          case Token.LET:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[195]++;
              pn = letStatement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[360]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[361]++;
int CodeCoverConditionCoverageHelper_C88;
              if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((pn instanceof VariableDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((peekToken() == Token.SEMI) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[196]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[362]++;
                  break;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[197]++;}
              return pn;

          case Token.RETURN:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[198]++;
          case Token.YIELD:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[199]++;
              pn = returnOrYield(tt, false);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[363]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[364]++;
              break;

          case Token.DEBUGGER:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[200]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[365]++;
              pn = new KeywordLiteral(ts.tokenBeg,
                                      ts.tokenEnd - ts.tokenBeg, tt);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[366]++;
              pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[367]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[368]++;
              break;

          case Token.LC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[201]++;
              return block();

          case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[202]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[369]++;
              return makeErrorNode();

          case Token.SEMI:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[203]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[370]++;
              pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[371]++;
              pn = new EmptyStatement(pos, ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[372]++;
              pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[373]++;
              return pn;

          case Token.FUNCTION:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[204]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[374]++;
              return function(FunctionNode.FUNCTION_EXPRESSION_STATEMENT);

          case Token.DEFAULT :
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[205]++;
              pn = defaultXmlNamespace();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[375]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[376]++;
              break;

          case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[206]++;
              pn = nameOrLabel();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[377]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[378]++;
int CodeCoverConditionCoverageHelper_C89;
              if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((pn instanceof ExpressionStatement) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[207]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[379]++;
                  break;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[208]++;}
              return pn;  // LabeledStatement

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[209]++;
              lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[380]++;
              pn = new ExpressionStatement(expr(), !insideFunction());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[381]++;
              pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[382]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[383]++;
              break;
        }

        autoInsertSemicolon(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[384]++;
        return pn;
    }

    private void autoInsertSemicolon(AstNode pn) throws IOException {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[385]++;
        int ttFlagged = peekFlaggedToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[386]++;
        int pos = pn.getPosition();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[387]++;
        switch (ttFlagged & CLEAR_TI_MASK) {
          case Token.SEMI:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[210]++;
              // Consume ';' as a part of expression
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[388]++;
              // extend the node bounds to include the semicolon.
              pn.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[389]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[390]++;
              break;
          case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[211]++;
          case Token.EOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[212]++;
          case Token.RC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[213]++;
              // Autoinsert ;
              warnMissingSemi(pos, nodeEnd(pn));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[391]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[392]++;
              break;
          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[214]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[393]++;
int CodeCoverConditionCoverageHelper_C90;
              if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 (((ttFlagged & TI_AFTER_EOL) == 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[215]++;
                  // Report error if no EOL or autoinsert ; otherwise
                  reportError("msg.no.semi.stmt");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[394]++;

              } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[216]++;
                  warnMissingSemi(pos, nodeEnd(pn));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[395]++;
              }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[396]++;
              break;
        }
    }

    private IfStatement ifStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[397]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((currentToken != Token.IF) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[217]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[398]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[218]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[399]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[400]++;
        int pos = ts.tokenBeg, lineno = ts.lineno, elsePos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[401]++;
        ConditionData data = condition();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[402]++;
        AstNode ifTrue = statement(), ifFalse = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[403]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((matchToken(Token.ELSE)) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[219]++;
            elsePos = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[404]++;
            ifFalse = statement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[405]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[220]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[406]++;
        int end = getNodeEnd(ifFalse != null ? ifFalse : ifTrue);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[407]++;
        IfStatement pn = new IfStatement(pos, end - pos);
        pn.setCondition(data.condition);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[408]++;
        pn.setParens(data.lp - pos, data.rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[409]++;
        pn.setThenPart(ifTrue);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[410]++;
        pn.setElsePart(ifFalse);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[411]++;
        pn.setElsePosition(elsePos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[412]++;
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[413]++;
        return pn;
    }

    private SwitchStatement switchStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[414]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((currentToken != Token.SWITCH) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[221]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[415]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[222]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[416]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[417]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[418]++;

        SwitchStatement pn = new SwitchStatement(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[419]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.switch")) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[223]++;
            pn.setLp(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[420]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[224]++;}
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[421]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[422]++;

        AstNode discriminant = expr();
        pn.setExpression(discriminant);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[423]++;
        enterSwitch(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[424]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[425]++;
boolean CodeCoverTryBranchHelper_Try8 = false;

        try {
CodeCoverTryBranchHelper_Try8 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[426]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.after.switch")) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[226]++;
                pn.setRp(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[427]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[227]++;}

            mustMatchToken(Token.LC, "msg.no.brace.switch");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[428]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[429]++;

            boolean hasDefault = false;
            int tt;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[430]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[28]++;


            switchLoop: for (;;) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[28]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[29]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[30]++;
}
                tt = nextToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[431]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[432]++;
                int casePos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[433]++;
                int caseLineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[434]++;
                AstNode caseExpression = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[435]++;
                switch (tt) {
                    case Token.RC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[228]++;
                        pn.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[436]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[437]++;
                        break switchLoop;

                    case Token.CASE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[229]++;
                        caseExpression = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[438]++;
                        mustMatchToken(Token.COLON, "msg.no.colon.case");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[439]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[440]++;
                        break;

                    case Token.DEFAULT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[230]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[441]++;
int CodeCoverConditionCoverageHelper_C97;
                        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((hasDefault) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[231]++;
                            reportError("msg.double.switch.default");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[442]++;

                        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[232]++;}
                        hasDefault = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[443]++;
                        caseExpression = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[444]++;
                        mustMatchToken(Token.COLON, "msg.no.colon.case");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[445]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[446]++;
                        break;

                    default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[233]++;
                        reportError("msg.bad.switch");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[447]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[448]++;
                        break switchLoop;
                }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[449]++;

                SwitchCase caseNode = new SwitchCase(casePos);
                caseNode.setExpression(caseExpression);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[450]++;
                caseNode.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[451]++;  // include colon
                caseNode.setLineno(caseLineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[452]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[453]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[31]++;



                while ((tt = peekToken()) != Token.RC && tt != Token.CASE && tt != Token.DEFAULT && tt != Token.EOF)
                {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[31]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[32]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[33]++;
}
                    caseNode.addStatement(statement());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[454]++;  // updates length
                }
                pn.addCase(caseNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[455]++;
            }
        } finally {
if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[225]++;
}
            exitSwitch();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[456]++;
        }
        return pn;
    }

    private WhileLoop whileLoop()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[457]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((currentToken != Token.WHILE) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[234]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[458]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[235]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[459]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[460]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[461]++;
        WhileLoop pn = new WhileLoop(pos);
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[462]++;
        enterLoop(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[463]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[464]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
        try {
CodeCoverTryBranchHelper_Try9 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[465]++;
            ConditionData data = condition();
            pn.setCondition(data.condition);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[466]++;
            pn.setParens(data.lp - pos, data.rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[467]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[468]++;
            AstNode body = statement();
            pn.setLength(getNodeEnd(body) - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[469]++;
            pn.setBody(body);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[470]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[236]++;
}
            exitLoop();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[471]++;
        }
        return pn;
    }

    private DoLoop doLoop()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[472]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((currentToken != Token.DO) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[237]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[473]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[238]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[474]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[475]++;
        int pos = ts.tokenBeg, end;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[476]++;
        DoLoop pn = new DoLoop(pos);
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[477]++;
        enterLoop(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[478]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[479]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
        try {
CodeCoverTryBranchHelper_Try10 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[480]++;
            AstNode body = statement();
            mustMatchToken(Token.WHILE, "msg.no.while.do");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[481]++;
            pn.setWhilePosition(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[482]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[483]++;
            ConditionData data = condition();
            pn.setCondition(data.condition);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[484]++;
            pn.setParens(data.lp - pos, data.rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[485]++;
            end = getNodeEnd(body);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[486]++;
            pn.setBody(body);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[487]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[239]++;
}
            exitLoop();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[488]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[489]++;
int CodeCoverConditionCoverageHelper_C101;
        // Always auto-insert semicolon to follow SpiderMonkey:
        // It is required by ECMAScript but is ignored by the rest of
        // world, see bug 238945
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((matchToken(Token.SEMI)) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[240]++;
            end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[490]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[241]++;}
        pn.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[491]++;
        return pn;
    }

    private Loop forLoop()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[492]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((currentToken != Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[242]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[493]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[243]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[494]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[495]++;
        int forPos = ts.tokenBeg, lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[496]++;
        boolean isForEach = false, isForIn = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[497]++;
        int eachPos = -1, inPos = -1, lp = -1, rp = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[498]++;
        AstNode init = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[499]++;  // init is also foo in 'foo in object'
        AstNode cond = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[500]++;  // cond is also object in 'foo in object'
        AstNode incr = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[501]++;
        Loop pn = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[502]++;

        Scope tempScope = new Scope();
        pushScope(tempScope);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[503]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[504]++;
boolean CodeCoverTryBranchHelper_Try11 = false;  // decide below what AST class to use
        try {
CodeCoverTryBranchHelper_Try11 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[505]++;
int CodeCoverConditionCoverageHelper_C103;
            // See if this is a for each () instead of just a for ()
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((matchToken(Token.NAME)) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[245]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[506]++;
int CodeCoverConditionCoverageHelper_C104;
                if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 (("each".equals(ts.getString())) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[247]++;
                    isForEach = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[507]++;
                    eachPos = ts.tokenBeg - forPos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[508]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[248]++;
                    reportError("msg.no.paren.for");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[509]++;
                }

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[246]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[510]++;
int CodeCoverConditionCoverageHelper_C105;

            if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.for")) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[249]++;
                lp = ts.tokenBeg - forPos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[511]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[250]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[512]++;
            int tt = peekToken();

            init = forLoopInit(tt);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[513]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[514]++;
int CodeCoverConditionCoverageHelper_C106;

            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((matchToken(Token.IN)) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[251]++;
                isForIn = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[515]++;
                inPos = ts.tokenBeg - forPos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[516]++;
                cond = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[517]++;
  // object over which we're iterating
            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[252]++;  // ordinary for-loop
                mustMatchToken(Token.SEMI, "msg.no.semi.for");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[518]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[519]++;
int CodeCoverConditionCoverageHelper_C107;
                if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((peekToken() == Token.SEMI) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[253]++;
                    // no loop condition
                    cond = new EmptyExpression(ts.tokenBeg, 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[520]++;
                    cond.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[521]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[254]++;
                    cond = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[522]++;
                }

                mustMatchToken(Token.SEMI, "msg.no.semi.for.cond");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[523]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[524]++;
                int tmpPos = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[525]++;
int CodeCoverConditionCoverageHelper_C108;
                if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((peekToken() == Token.RP) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[255]++;
                    incr = new EmptyExpression(tmpPos, 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[526]++;
                    incr.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[527]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[256]++;
                    incr = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[528]++;
                }
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[529]++;
int CodeCoverConditionCoverageHelper_C109;

            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.for.ctrl")) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[257]++;
                rp = ts.tokenBeg - forPos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[530]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[258]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[531]++;
int CodeCoverConditionCoverageHelper_C110;

            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((isForIn) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[259]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[532]++;
                ForInLoop fis = new ForInLoop(forPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[533]++;
int CodeCoverConditionCoverageHelper_C111;
                if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((init instanceof VariableDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[261]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[534]++;
int CodeCoverConditionCoverageHelper_C112;
                    // check that there was only one variable given
                    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((((VariableDeclaration)init).getVariables().size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[263]++;
                        reportError("msg.mult.index");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[535]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[264]++;}

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[262]++;}
                fis.setIterator(init);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[536]++;
                fis.setIteratedObject(cond);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[537]++;
                fis.setInPosition(inPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[538]++;
                fis.setIsForEach(isForEach);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[539]++;
                fis.setEachPosition(eachPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[540]++;
                pn = fis;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[541]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[260]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[542]++;
                ForLoop fl = new ForLoop(forPos);
                fl.setInitializer(init);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[543]++;
                fl.setCondition(cond);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[544]++;
                fl.setIncrement(incr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[545]++;
                pn = fl;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[546]++;
            }

            // replace temp scope with the new loop object
            currentScope.replaceWith(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[547]++;
            popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[548]++;

            // We have to parse the body -after- creating the loop node,
            // so that the loop node appears in the loopSet, allowing
            // break/continue statements to find the enclosing loop.
            enterLoop(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[549]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[550]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
            try {
CodeCoverTryBranchHelper_Try12 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[551]++;
                AstNode body = statement();
                pn.setLength(getNodeEnd(body) - forPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[552]++;
                pn.setBody(body);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[553]++;
            } finally {
if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[265]++;
}
                exitLoop();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[554]++;
            }

        } finally {
if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[244]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[555]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((currentScope == tempScope) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[266]++;
                popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[556]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[267]++;}
        }
        pn.setParens(lp, rp);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[557]++;
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[558]++;
        return pn;
    }

    private AstNode forLoopInit(int tt) throws IOException {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[559]++;
boolean CodeCoverTryBranchHelper_Try13 = false;
        try {
CodeCoverTryBranchHelper_Try13 = true;
            inForInit = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[560]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[561]++;  // checked by variables() and relExpr()
            AstNode init = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[562]++;
int CodeCoverConditionCoverageHelper_C114;
            if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((tt == Token.SEMI) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[269]++;
                init = new EmptyExpression(ts.tokenBeg, 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[563]++;
                init.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[564]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[270]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[565]++;
int CodeCoverConditionCoverageHelper_C115; if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((tt == Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((tt == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[271]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[566]++;
                init = variables(tt, ts.tokenBeg, false);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[567]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[272]++;
                init = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[568]++;
                markDestructuring(init);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[569]++;
            }
}
            return init;
        } finally {
if ( CodeCoverTryBranchHelper_Try13 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[268]++;
}
            inForInit = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[570]++;
        }
    }

    private TryStatement tryStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[571]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((currentToken != Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[273]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[572]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[274]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[573]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[574]++;

        // Pull out JSDoc info and reset it before recursing.
        Comment jsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[575]++;

        int tryPos = ts.tokenBeg, lineno = ts.lineno, finallyPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[576]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((peekToken() != Token.LC) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[275]++;
            reportError("msg.no.brace.try");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[577]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[276]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[578]++;
        AstNode tryBlock = statement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[579]++;
        int tryEnd = getNodeEnd(tryBlock);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[580]++;

        List<CatchClause> clauses = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[581]++;

        boolean sawDefaultCatch = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[582]++;
        int peek = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[583]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((peek == Token.CATCH) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[277]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[584]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[34]++;


int CodeCoverConditionCoverageHelper_C119;
            while ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((matchToken(Token.CATCH)) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[34]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[35]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[36]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[585]++;
                int catchLineNum = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[586]++;
int CodeCoverConditionCoverageHelper_C120;
                if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((sawDefaultCatch) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[279]++;
                    reportError("msg.catch.unreachable");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[587]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[280]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[588]++;
                int catchPos = ts.tokenBeg, lp = -1, rp = -1, guardPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[589]++;
int CodeCoverConditionCoverageHelper_C121;
                if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.catch")) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[281]++;
                    lp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[590]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[282]++;}

                mustMatchToken(Token.NAME, "msg.bad.catchcond");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[591]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[592]++;

                Name varName = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[593]++;
                Comment jsdocNodeForName = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[594]++;
int CodeCoverConditionCoverageHelper_C122;
                if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((jsdocNodeForName != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[283]++;
                  varName.setJsDocNode(jsdocNodeForName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[595]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[284]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[596]++;
                String varNameString = varName.getIdentifier();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[597]++;
int CodeCoverConditionCoverageHelper_C123;
                if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[285]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[598]++;
int CodeCoverConditionCoverageHelper_C124;
                    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (8)) == 0 || true) &&
 (("eval".equals(varNameString)) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 (("arguments".equals(varNameString)) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) && false))
                    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[287]++;
                        reportError("msg.bad.id.strict", varNameString);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[599]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[288]++;}

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[286]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[600]++;

                AstNode catchCond = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[601]++;
int CodeCoverConditionCoverageHelper_C125;
                if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((matchToken(Token.IF)) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[289]++;
                    guardPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[602]++;
                    catchCond = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[603]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[290]++;
                    sawDefaultCatch = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[604]++;
                }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[605]++;
int CodeCoverConditionCoverageHelper_C126;

                if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.bad.catchcond")) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[291]++;
                    rp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[606]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[292]++;}
                mustMatchToken(Token.LC, "msg.no.brace.catchblock");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[607]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[608]++;

                Block catchBlock = (Block)statements();
                tryEnd = getNodeEnd(catchBlock);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[609]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[610]++;
                CatchClause catchNode = new CatchClause(catchPos);
                catchNode.setVarName(varName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[611]++;
                catchNode.setCatchCondition(catchCond);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[612]++;
                catchNode.setBody(catchBlock);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[613]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[614]++;
int CodeCoverConditionCoverageHelper_C127;
                if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((guardPos != -1) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[293]++;
                    catchNode.setIfPosition(guardPos - catchPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[615]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[294]++;}
                catchNode.setParens(lp, rp);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[616]++;
                catchNode.setLineno(catchLineNum);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[617]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[618]++;
int CodeCoverConditionCoverageHelper_C128;

                if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RC, "msg.no.brace.after.body")) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[295]++;
                    tryEnd = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[619]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[296]++;}
                catchNode.setLength(tryEnd - catchPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[620]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[621]++;
int CodeCoverConditionCoverageHelper_C129;
                if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((clauses == null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[297]++;
                    clauses = new ArrayList<CatchClause>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[622]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[298]++;}
                clauses.add(catchNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[623]++;
            }

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[278]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[624]++;
int CodeCoverConditionCoverageHelper_C130; if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((peek != Token.FINALLY) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[299]++;
            mustMatchToken(Token.FINALLY, "msg.try.no.catchfinally");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[625]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[300]++;}
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[626]++;

        AstNode finallyBlock = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[627]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((matchToken(Token.FINALLY)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[301]++;
            finallyPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[628]++;
            finallyBlock = statement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[629]++;
            tryEnd = getNodeEnd(finallyBlock);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[630]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[302]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[631]++;

        TryStatement pn = new TryStatement(tryPos, tryEnd - tryPos);
        pn.setTryBlock(tryBlock);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[632]++;
        pn.setCatchClauses(clauses);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[633]++;
        pn.setFinallyBlock(finallyBlock);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[634]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[635]++;
int CodeCoverConditionCoverageHelper_C132;
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((finallyPos != -1) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[303]++;
            pn.setFinallyPosition(finallyPos - tryPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[636]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[304]++;}
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[637]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[638]++;
int CodeCoverConditionCoverageHelper_C133;

        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((jsdocNode != null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[305]++;
            pn.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[639]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[306]++;}

        return pn;
    }

    private ThrowStatement throwStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[640]++;
int CodeCoverConditionCoverageHelper_C134;
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((currentToken != Token.THROW) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[307]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[641]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[308]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[642]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[643]++;
        int pos = ts.tokenBeg, lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[644]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((peekTokenOrEOL() == Token.EOL) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[309]++;
            // ECMAScript does not allow new lines before throw expression,
            // see bug 256617
            reportError("msg.bad.throw.eol");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[645]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[310]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[646]++;
        AstNode expr = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[647]++;
        ThrowStatement pn = new ThrowStatement(pos, getNodeEnd(expr), expr);
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[648]++;
        return pn;
    }

    // If we match a NAME, consume the token and return the statement
    // with that label.  If the name does not match an existing label,
    // reports an error.  Returns the labeled statement node, or null if
    // the peeked token was not a name.  Side effect:  sets scanner token
    // information for the label identifier (tokenBeg, tokenEnd, etc.)

    private LabeledStatement matchJumpLabelName()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[649]++;
        LabeledStatement label = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[650]++;
int CodeCoverConditionCoverageHelper_C136;

        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((peekTokenOrEOL() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[311]++;
            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[651]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[652]++;
int CodeCoverConditionCoverageHelper_C137;
            if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((labelSet != null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[313]++;
                label = labelSet.get(ts.getString());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[653]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[314]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[654]++;
int CodeCoverConditionCoverageHelper_C138;
            if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[315]++;
                reportError("msg.undef.label");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[655]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[316]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[312]++;}

        return label;
    }

    private BreakStatement breakStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[656]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((currentToken != Token.BREAK) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[317]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[657]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[318]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[658]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[659]++;
        int lineno = ts.lineno, pos = ts.tokenBeg, end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[660]++;
        Name breakLabel = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[661]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((peekTokenOrEOL() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[319]++;
            breakLabel = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[662]++;
            end = getNodeEnd(breakLabel);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[663]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[320]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[664]++;

        // matchJumpLabelName only matches if there is one
        LabeledStatement labels = matchJumpLabelName();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[665]++;
        // always use first label as target
        Jump breakTarget = labels == null ? null : labels.getFirstLabel();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[666]++;
int CodeCoverConditionCoverageHelper_C141;

        if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((breakTarget == null) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((breakLabel == null) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[321]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[667]++;
int CodeCoverConditionCoverageHelper_C142;
            if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 ((loopAndSwitchSet == null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((loopAndSwitchSet.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[323]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[668]++;
int CodeCoverConditionCoverageHelper_C143;
                if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((breakLabel == null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[325]++;
                    reportError("msg.bad.break", pos, end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[669]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[326]++;}

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[324]++;
                breakTarget = loopAndSwitchSet.get(loopAndSwitchSet.size() - 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[670]++;
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[322]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[671]++;

        BreakStatement pn = new BreakStatement(pos, end - pos);
        pn.setBreakLabel(breakLabel);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[672]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[673]++;
int CodeCoverConditionCoverageHelper_C144;
        // can be null if it's a bad break in error-recovery mode
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((breakTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[327]++;
            pn.setBreakTarget(breakTarget);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[674]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[328]++;}
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[675]++;
        return pn;
    }

    private ContinueStatement continueStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[676]++;
int CodeCoverConditionCoverageHelper_C145;
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((currentToken != Token.CONTINUE) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[329]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[677]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[330]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[678]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[679]++;
        int lineno = ts.lineno, pos = ts.tokenBeg, end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[680]++;
        Name label = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[681]++;
int CodeCoverConditionCoverageHelper_C146;
        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((peekTokenOrEOL() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[331]++;
            label = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[682]++;
            end = getNodeEnd(label);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[683]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[332]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[684]++;

        // matchJumpLabelName only matches if there is one
        LabeledStatement labels = matchJumpLabelName();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[685]++;
        Loop target = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[686]++;
int CodeCoverConditionCoverageHelper_C147;
        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (8)) == 0 || true) &&
 ((labels == null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[333]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[687]++;
int CodeCoverConditionCoverageHelper_C148;
            if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (8)) == 0 || true) &&
 ((loopSet == null) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((loopSet.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[335]++;
                reportError("msg.continue.outside");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[688]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[336]++;
                target = loopSet.get(loopSet.size() - 1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[689]++;
            }

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[334]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[690]++;
int CodeCoverConditionCoverageHelper_C149;
            if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (8)) == 0 || true) &&
 ((labels == null) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((labels.getStatement() instanceof Loop) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[337]++;
                reportError("msg.continue.nonloop", pos, end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[691]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[338]++;}
            target = labels == null ? null : (Loop)labels.getStatement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[692]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[693]++;

        ContinueStatement pn = new ContinueStatement(pos, end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[694]++;
int CodeCoverConditionCoverageHelper_C150;
        if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[339]++;  // can be null in error-recovery mode
            pn.setTarget(target);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[695]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[340]++;}
        pn.setLabel(label);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[696]++;
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[697]++;
        return pn;
    }

    private WithStatement withStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[698]++;
int CodeCoverConditionCoverageHelper_C151;
        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((currentToken != Token.WITH) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[341]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[699]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[342]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[700]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[701]++;

        Comment withComment = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[702]++;

        int lineno = ts.lineno, pos = ts.tokenBeg, lp = -1, rp = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[703]++;
int CodeCoverConditionCoverageHelper_C152;
        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.with")) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[343]++;
            lp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[704]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[344]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[705]++;

        AstNode obj = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[706]++;
int CodeCoverConditionCoverageHelper_C153;

        if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.after.with")) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[345]++;
            rp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[707]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[346]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[708]++;

        AstNode body = statement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[709]++;

        WithStatement pn = new WithStatement(pos, getNodeEnd(body) - pos);
        pn.setJsDocNode(withComment);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[710]++;
        pn.setExpression(obj);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[711]++;
        pn.setStatement(body);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[712]++;
        pn.setParens(lp, rp);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[713]++;
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[714]++;
        return pn;
    }

    private AstNode letStatement()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[715]++;
int CodeCoverConditionCoverageHelper_C154;
        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((currentToken != Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[347]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[716]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[348]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[717]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[718]++;
        int lineno = ts.lineno, pos = ts.tokenBeg;
        AstNode pn;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[719]++;
int CodeCoverConditionCoverageHelper_C155;
        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((peekToken() == Token.LP) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[349]++;
            pn = let(true, pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[720]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[350]++;
            pn = variables(Token.LET, pos, true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[721]++;  // else, e.g.: let x=6, y=7;
        }
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[722]++;
        return pn;
    }

    /**
     * Returns whether or not the bits in the mask have changed to all set.
     * @param before bits before change
     * @param after bits after change
     * @param mask mask for bits
     * @return {@code true} if all the bits in the mask are set in "after"
     *          but not in "before"
     */
    private static final boolean nowAllSet(int before, int after, int mask) {
        return ((before & mask) != mask) && ((after & mask) == mask);
    }

    private AstNode returnOrYield(int tt, boolean exprContext)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[723]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((insideFunction()) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[351]++;
            reportError(tt == Token.RETURN ? "msg.bad.return"
                                           : "msg.bad.yield");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[724]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[352]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[725]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[726]++;
        int lineno = ts.lineno, pos = ts.tokenBeg, end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[727]++;

        AstNode e = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[728]++;
        // This is ugly, but we don't want to require a semicolon.
        switch (peekTokenOrEOL()) {
          case Token.SEMI:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[353]++; case Token.RC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[354]++;  case Token.RB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[355]++;    case Token.RP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[356]++;
          case Token.EOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[357]++;  case Token.EOL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[358]++; case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[359]++; case Token.YIELD:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[360]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[729]++;
            break;
          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[361]++;
            e = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[730]++;
            end = getNodeEnd(e);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[731]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[732]++;

        int before = endFlags;
        AstNode ret;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[733]++;
int CodeCoverConditionCoverageHelper_C157;

        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((tt == Token.RETURN) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[362]++;
            endFlags |= e == null ? Node.END_RETURNS : Node.END_RETURNS_VALUE;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[734]++;
            ret = new ReturnStatement(pos, end - pos, e);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[735]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[736]++;
int CodeCoverConditionCoverageHelper_C158;

            // see if we need a strict mode warning
            if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((nowAllSet(before, endFlags,
                    Node.END_RETURNS|Node.END_RETURNS_VALUE)) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[364]++;
                addStrictWarning("msg.return.inconsistent", "", pos, end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[737]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[365]++;}

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[363]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[738]++;
int CodeCoverConditionCoverageHelper_C159;
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((insideFunction()) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[366]++;
                reportError("msg.bad.yield");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[739]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[367]++;}
            endFlags |= Node.END_YIELDS;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[740]++;
            ret = new Yield(pos, end - pos, e);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[741]++;
            setRequiresActivation();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[742]++;
            setIsGenerator();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[743]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[744]++;
int CodeCoverConditionCoverageHelper_C160;
            if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((exprContext) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[368]++;
                ret = new ExpressionStatement(ret);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[745]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[369]++;}
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[746]++;
int CodeCoverConditionCoverageHelper_C161;

        // see if we are mixing yields and value returns.
        if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (8)) == 0 || true) &&
 ((insideFunction()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((nowAllSet(before, endFlags,
                    Node.END_YIELDS|Node.END_RETURNS_VALUE)) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[370]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[747]++;
            Name name = ((FunctionNode)currentScriptOrFn).getFunctionName();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[748]++;
int CodeCoverConditionCoverageHelper_C162;
            if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((name.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[372]++;
                addError("msg.anon.generator.returns", "");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[749]++;
}
            else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[373]++;
                addError("msg.generator.returns", name.getIdentifier());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[750]++;
}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[371]++;}

        ret.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[751]++;
        return ret;
    }

    private AstNode block()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[752]++;
int CodeCoverConditionCoverageHelper_C163;
        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((currentToken != Token.LC) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[374]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[753]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[375]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[754]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[755]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[756]++;
        Scope block = new Scope(pos);
        block.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[757]++;
        pushScope(block);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[758]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[759]++;
boolean CodeCoverTryBranchHelper_Try14 = false;
        try {
CodeCoverTryBranchHelper_Try14 = true;
            statements(block);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[760]++;
            mustMatchToken(Token.RC, "msg.no.brace.block");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[761]++;
            block.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[762]++;
            return block;
        } finally {
if ( CodeCoverTryBranchHelper_Try14 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[376]++;
}
            popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[763]++;
        }
    }

    private AstNode defaultXmlNamespace()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[764]++;
int CodeCoverConditionCoverageHelper_C164;
        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((currentToken != Token.DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[377]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[765]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[378]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[766]++;
        mustHaveXML();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[767]++;
        setRequiresActivation();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[768]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[769]++;
        int lineno = ts.lineno, pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[770]++;
int CodeCoverConditionCoverageHelper_C165;

        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C165 |= (8)) == 0 || true) &&
 ((matchToken(Token.NAME)) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 (("xml".equals(ts.getString())) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[379]++;
            reportError("msg.bad.namespace");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[771]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[380]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[772]++;
int CodeCoverConditionCoverageHelper_C166;
        if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C166 |= (8)) == 0 || true) &&
 ((matchToken(Token.NAME)) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 (("namespace".equals(ts.getString())) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[381]++;
            reportError("msg.bad.namespace");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[773]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[382]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[774]++;
int CodeCoverConditionCoverageHelper_C167;
        if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((matchToken(Token.ASSIGN)) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[383]++;
            reportError("msg.bad.namespace");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[775]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[384]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[776]++;

        AstNode e = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[777]++;
        UnaryExpression dxmln = new UnaryExpression(pos, getNodeEnd(e) - pos);
        dxmln.setOperator(Token.DEFAULTNAMESPACE);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[778]++;
        dxmln.setOperand(e);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[779]++;
        dxmln.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[780]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[781]++;

        ExpressionStatement es = new ExpressionStatement(dxmln, true);
        return es;
    }

    private void recordLabel(Label label, LabeledStatement bundle)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[782]++;
int CodeCoverConditionCoverageHelper_C168;
        // current token should be colon that primaryExpr left untouched
        if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((peekToken() != Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[385]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[783]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[386]++;}
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[784]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[785]++;
        String name = label.getName();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[786]++;
int CodeCoverConditionCoverageHelper_C169;
        if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((labelSet == null) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[387]++;
            labelSet = new HashMap<String,LabeledStatement>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[787]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[388]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[788]++;
            LabeledStatement ls = labelSet.get(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[789]++;
int CodeCoverConditionCoverageHelper_C170;
            if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((ls != null) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[389]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[790]++;
int CodeCoverConditionCoverageHelper_C171;
                if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[391]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[791]++;
                    Label dup = ls.getLabelByName(name);
                    reportError("msg.dup.label",
                                dup.getAbsolutePosition(), dup.getLength());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[792]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[392]++;}
                reportError("msg.dup.label",
                            label.getPosition(), label.getLength());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[793]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[390]++;}
        }
        bundle.addLabel(label);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[794]++;
        labelSet.put(name, bundle);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[795]++;
    }

    /**
     * Found a name in a statement context.  If it's a label, we gather
     * up any following labels and the next non-label statement into a
     * {@link LabeledStatement} "bundle" and return that.  Otherwise we parse
     * an expression and return it wrapped in an {@link ExpressionStatement}.
     */
    private AstNode nameOrLabel()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[796]++;
int CodeCoverConditionCoverageHelper_C172;
        if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((currentToken != Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[393]++; throw codeBug();
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[394]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[797]++;
        int pos = ts.tokenBeg;

        // set check for label and call down to primaryExpr
        currentFlaggedToken |= TI_CHECK_LABEL;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[798]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[799]++;
        AstNode expr = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[800]++;
int CodeCoverConditionCoverageHelper_C173;

        if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((expr.getType() != Token.LABEL) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[395]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[801]++;
            AstNode n = new ExpressionStatement(expr, !insideFunction());
            n.lineno = expr.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[802]++;
            return n;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[396]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[803]++;

        LabeledStatement bundle = new LabeledStatement(pos);
        recordLabel((Label)expr, bundle);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[804]++;
        bundle.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[805]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[806]++;
        // look for more labels
        AstNode stmt = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[807]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[37]++;


int CodeCoverConditionCoverageHelper_C174;
        while ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((peekToken() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[37]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[38]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[39]++;
}
            currentFlaggedToken |= TI_CHECK_LABEL;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[808]++;
            expr = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[809]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[810]++;
int CodeCoverConditionCoverageHelper_C175;
            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((expr.getType() != Token.LABEL) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[397]++;
                stmt = new ExpressionStatement(expr, !insideFunction());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[811]++;
                autoInsertSemicolon(stmt);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[812]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[813]++;
                break;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[398]++;}
            recordLabel((Label)expr, bundle);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[814]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[815]++;
boolean CodeCoverTryBranchHelper_Try15 = false;

        // no more labels; now parse the labeled statement
        try {
CodeCoverTryBranchHelper_Try15 = true;
            currentLabel = bundle;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[816]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[817]++;
int CodeCoverConditionCoverageHelper_C176;
            if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((stmt == null) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[400]++;
                stmt = statementHelper();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[818]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[401]++;}
        } finally {
if ( CodeCoverTryBranchHelper_Try15 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[399]++;
}
            currentLabel = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[819]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[820]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[40]++;


            // remove the labels for this statement from the global set
            for (Label lb : bundle.getLabels()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[40]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[41]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[42]++;
}
                labelSet.remove(lb.getName());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[821]++;
            }
        }

        // If stmt has parent assigned its position already is relative
        // (See bug #710225)
        bundle.setLength(stmt.getParent() == null
                     ? getNodeEnd(stmt) - pos
                     : getNodeEnd(stmt));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[822]++;
        bundle.setStatement(stmt);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[823]++;
        return bundle;
    }

    /**
     * Parse a 'var' or 'const' statement, or a 'var' init list in a for
     * statement.
     * @param declType A token value: either VAR, CONST, or LET depending on
     * context.
     * @param pos the position where the node should start.  It's sometimes
     * the var/const/let keyword, and other times the beginning of the first
     * token in the first variable declaration.
     * @return the parsed variable list
     */
    private VariableDeclaration variables(int declType, int pos, boolean isStatement)
        throws IOException
    {
        int end;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[824]++;
        VariableDeclaration pn = new VariableDeclaration(pos);
        pn.setType(declType);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[825]++;
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[826]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[827]++;
        Comment varjsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[828]++;
int CodeCoverConditionCoverageHelper_C177;
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((varjsdocNode != null) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[402]++;
            pn.setJsDocNode(varjsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[829]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[403]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[830]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[43]++;


        // Example:
        // var foo = {a: 1, b: 2}, bar = [3, 4];
        // var {b: s2, a: s1} = foo, x = 6, y, [s3, s4] = bar;
        for (;;) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[43]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[44]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[45]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[831]++;
            AstNode destructuring = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[832]++;
            Name name = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[833]++;
            int tt = peekToken(), kidPos = ts.tokenBeg;
            end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[834]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[835]++;
int CodeCoverConditionCoverageHelper_C179;

            if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (8)) == 0 || true) &&
 ((tt == Token.LB) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((tt == Token.LC) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[404]++;
                // Destructuring assignment, e.g., var [a,b] = ...
                destructuring = destructuringPrimaryExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[836]++;
                end = getNodeEnd(destructuring);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[837]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[838]++;
int CodeCoverConditionCoverageHelper_C180;
                if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((destructuring instanceof DestructuringForm) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[406]++;
                    reportError("msg.bad.assign.left", kidPos, end - kidPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[839]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[407]++;}
                markDestructuring(destructuring);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[840]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[405]++;
                // Simple variable name
                mustMatchToken(Token.NAME, "msg.bad.var");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[841]++;
                name = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[842]++;
                name.setLineno(ts.getLineno());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[843]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[844]++;
int CodeCoverConditionCoverageHelper_C181;
                if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[408]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[845]++;
                    String id = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[846]++;
int CodeCoverConditionCoverageHelper_C182;
                    if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (8)) == 0 || true) &&
 (("eval".equals(id)) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 (("arguments".equals(ts.getString())) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 2) && false))
                    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[410]++;
                        reportError("msg.bad.id.strict", id);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[847]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[411]++;}

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[409]++;}
                defineSymbol(declType, ts.getString(), inForInit);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[848]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[849]++;

            int lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[850]++;

            Comment jsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[851]++;

            AstNode init = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[852]++;
int CodeCoverConditionCoverageHelper_C183;
            if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((matchToken(Token.ASSIGN)) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[412]++;
                init = assignExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[853]++;
                end = getNodeEnd(init);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[854]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[413]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[855]++;

            VariableInitializer vi = new VariableInitializer(kidPos, end - kidPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[856]++;
int CodeCoverConditionCoverageHelper_C184;
            if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((destructuring != null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[414]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[857]++;
int CodeCoverConditionCoverageHelper_C185;
                if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (8)) == 0 || true) &&
 ((init == null) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((inForInit) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[416]++;
                    reportError("msg.destruct.assign.no.init");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[858]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[417]++;}
                vi.setTarget(destructuring);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[859]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[415]++;
                vi.setTarget(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[860]++;
            }
            vi.setInitializer(init);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[861]++;
            vi.setType(declType);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[862]++;
            vi.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[863]++;
            vi.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[864]++;
            pn.addVariable(vi);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[865]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[866]++;
int CodeCoverConditionCoverageHelper_C186;

            if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((matchToken(Token.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[418]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[867]++;
                break;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[419]++;}
        }
        pn.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[868]++;
        pn.setIsStatement(isStatement);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[869]++;
        return pn;
    }

    // have to pass in 'let' kwd position to compute kid offsets properly
    private AstNode let(boolean isStatement, int pos)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[870]++;
        LetNode pn = new LetNode(pos);
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[871]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[872]++;
int CodeCoverConditionCoverageHelper_C187;
        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.after.let")) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[420]++;
            pn.setLp(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[873]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[421]++;}
        pushScope(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[874]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[875]++;
boolean CodeCoverTryBranchHelper_Try16 = false;
        try {
CodeCoverTryBranchHelper_Try16 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[876]++;
            VariableDeclaration vars = variables(Token.LET, ts.tokenBeg, isStatement);
            pn.setVariables(vars);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[877]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[878]++;
int CodeCoverConditionCoverageHelper_C188;
            if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.let")) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[423]++;
                pn.setRp(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[879]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[424]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[880]++;
int CodeCoverConditionCoverageHelper_C189;
            if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (8)) == 0 || true) &&
 ((isStatement) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((peekToken() == Token.LC) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[425]++;
                // let statement
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[881]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[882]++;
                int beg = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[883]++;  // position stmt at LC
                AstNode stmt = statements();
                mustMatchToken(Token.RC, "msg.no.curly.let");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[884]++;
                stmt.setLength(ts.tokenEnd - beg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[885]++;
                pn.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[886]++;
                pn.setBody(stmt);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[887]++;
                pn.setType(Token.LET);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[888]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[426]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[889]++;
                // let expression
                AstNode expr = expr();
                pn.setLength(getNodeEnd(expr) - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[890]++;
                pn.setBody(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[891]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[892]++;
int CodeCoverConditionCoverageHelper_C190;
                if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((isStatement) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[427]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[893]++;
                    // let expression in statement context
                    ExpressionStatement es =
                            new ExpressionStatement(pn, !insideFunction());
                    es.setLineno(pn.getLineno());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[894]++;
                    return es;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[428]++;}
            }
        } finally {
if ( CodeCoverTryBranchHelper_Try16 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[422]++;
}
            popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[895]++;
        }
        return pn;
    }

    void defineSymbol(int declType, String name) {
        defineSymbol(declType, name, false);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[896]++;
    }

    void defineSymbol(int declType, String name, boolean ignoreNotInBlock) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[897]++;
int CodeCoverConditionCoverageHelper_C191;
        if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[429]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[898]++;
int CodeCoverConditionCoverageHelper_C192;
            if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[431]++;  // be robust in IDE-mode
                return;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[432]++;
                codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[899]++;
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[430]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[900]++;
        Scope definingScope = currentScope.getDefiningScope(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[901]++;
        Symbol symbol = definingScope != null
                        ? definingScope.getSymbol(name)
                        : null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[902]++;
        int symDeclType = symbol != null ? symbol.getDeclType() : -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[903]++;
int CodeCoverConditionCoverageHelper_C193;
        if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (512)) == 0 || true) &&
 ((symbol != null) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (256)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C193 |= (128)) == 0 || true) &&
 ((symDeclType == Token.CONST) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C193 |= (32)) == 0 || true) &&
 ((declType == Token.CONST) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C193 |= (8)) == 0 || true) &&
 ((definingScope == currentScope) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((symDeclType == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 5) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 5) && false))
        {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[433]++;
            addError(symDeclType == Token.CONST ? "msg.const.redecl" :
                     symDeclType == Token.LET ? "msg.let.redecl" :
                     symDeclType == Token.VAR ? "msg.var.redecl" :
                     symDeclType == Token.FUNCTION ? "msg.fn.redecl" :
                     "msg.parm.redecl", name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[904]++;
            return;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[434]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[905]++;
        switch (declType) {
          case Token.LET:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[435]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[906]++;
int CodeCoverConditionCoverageHelper_C194;
              if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C194 |= (32)) == 0 || true) &&
 ((ignoreNotInBlock) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (16)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C194 |= (8)) == 0 || true) &&
 ((currentScope.getType() == Token.IF) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((currentScope instanceof Loop) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[436]++;
                  addError("msg.let.decl.not.in.block");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[907]++;
                  return;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[437]++;}
              currentScope.putSymbol(new Symbol(declType, name));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[908]++;
              return;

          case Token.VAR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[438]++;
          case Token.CONST:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[439]++;
          case Token.FUNCTION:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[440]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[909]++;
int CodeCoverConditionCoverageHelper_C195;
              if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((symbol != null) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[441]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[910]++;
int CodeCoverConditionCoverageHelper_C196;
                  if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((symDeclType == Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[443]++;
                      addStrictWarning("msg.var.redecl", name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[911]++;
}
                  else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[444]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[912]++;
int CodeCoverConditionCoverageHelper_C197; if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((symDeclType == Token.LP) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[445]++;
                      addStrictWarning("msg.var.hides.arg", name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[913]++;

                  } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[446]++;}
}

              } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[442]++;
                  currentScriptOrFn.putSymbol(new Symbol(declType, name));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[914]++;
              }
              return;

          case Token.LP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[447]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[915]++;
int CodeCoverConditionCoverageHelper_C198;
              if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((symbol != null) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[448]++;
                  // must be duplicate parameter. Second parameter hides the
                  // first, so go ahead and add the second parameter
                  addWarning("msg.dup.parms", name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[916]++;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[449]++;}
              currentScriptOrFn.putSymbol(new Symbol(declType, name));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[917]++;
              return;

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[450]++;
              throw codeBug();
        }
    }

    private AstNode expr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[918]++;
        AstNode pn = assignExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[919]++;
        int pos = pn.getPosition();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[920]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[46]++;


int CodeCoverConditionCoverageHelper_C199;
        while ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((matchToken(Token.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[46]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[47]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[48]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[921]++;
            int opPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[922]++;
int CodeCoverConditionCoverageHelper_C200;
            if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (8)) == 0 || true) &&
 ((compilerEnv.isStrictMode()) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((pn.hasSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[451]++;
                addStrictWarning("msg.no.side.effects", "",
                                 pos, nodeEnd(pn) - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[923]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[452]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[924]++;
int CodeCoverConditionCoverageHelper_C201;
            if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((peekToken() == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[453]++;
                reportError("msg.yield.parenthesized");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[925]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[454]++;}
            pn = new InfixExpression(Token.COMMA, pn, assignExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[926]++;
        }
        return pn;
    }

    private AstNode assignExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[927]++;
        int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[928]++;
int CodeCoverConditionCoverageHelper_C202;
        if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((tt == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[455]++;
            return returnOrYield(tt, true);

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[456]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[929]++;
        AstNode pn = condExpr();
        tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[930]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[931]++;
int CodeCoverConditionCoverageHelper_C203;
        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (8)) == 0 || true) &&
 ((Token.FIRST_ASSIGN <= tt) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((tt <= Token.LAST_ASSIGN) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[457]++;
            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[932]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[933]++;

            // Pull out JSDoc info and reset it before recursing.
            Comment jsdocNode = getAndResetJsDoc();

            markDestructuring(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[934]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[935]++;
            int opPos = ts.tokenBeg;

            pn = new Assignment(tt, pn, assignExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[936]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[937]++;
int CodeCoverConditionCoverageHelper_C204;

            if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((jsdocNode != null) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[459]++;
                pn.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[938]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[460]++;}

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[458]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[939]++;
int CodeCoverConditionCoverageHelper_C205; if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((tt == Token.SEMI) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[461]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[940]++;
int CodeCoverConditionCoverageHelper_C206;
            // This may be dead code added intentionally, for JSDoc purposes.
            // For example: /** @type Number */ C.prototype.x;
            if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((currentJsDocComment != null) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[463]++;
                pn.setJsDocNode(getAndResetJsDoc());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[941]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[464]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[462]++;}
}
        return pn;
    }

    private AstNode condExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[942]++;
        AstNode pn = orExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[943]++;
int CodeCoverConditionCoverageHelper_C207;
        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((matchToken(Token.HOOK)) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[465]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[944]++;
            int line = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[945]++;
            int qmarkPos = ts.tokenBeg, colonPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[946]++;
            /*
             * Always accept the 'in' operator in the middle clause of a ternary,
             * where it's unambiguous, even if we might be parsing the init of a
             * for statement.
             */
            boolean wasInForInit = inForInit;
            inForInit = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[947]++;
            AstNode ifTrue;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[948]++;
boolean CodeCoverTryBranchHelper_Try17 = false;
            try {
CodeCoverTryBranchHelper_Try17 = true;
                ifTrue = assignExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[949]++;
            } finally {
if ( CodeCoverTryBranchHelper_Try17 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[467]++;
}
                inForInit = wasInForInit;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[950]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[951]++;
int CodeCoverConditionCoverageHelper_C208;
            if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.COLON, "msg.no.colon.cond")) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[468]++;
                colonPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[952]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[469]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[953]++;
            AstNode ifFalse = assignExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[954]++;
            int beg = pn.getPosition(), len = getNodeEnd(ifFalse) - beg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[955]++;
            ConditionalExpression ce = new ConditionalExpression(beg, len);
            ce.setLineno(line);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[956]++;
            ce.setTestExpression(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[957]++;
            ce.setTrueExpression(ifTrue);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[958]++;
            ce.setFalseExpression(ifFalse);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[959]++;
            ce.setQuestionMarkPosition(qmarkPos - beg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[960]++;
            ce.setColonPosition(colonPos - beg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[961]++;
            pn = ce;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[962]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[466]++;}
        return pn;
    }

    private AstNode orExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[963]++;
        AstNode pn = andExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[964]++;
int CodeCoverConditionCoverageHelper_C209;
        if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((matchToken(Token.OR)) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[470]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[965]++;
            int opPos = ts.tokenBeg;
            pn = new InfixExpression(Token.OR, pn, orExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[966]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[471]++;}
        return pn;
    }

    private AstNode andExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[967]++;
        AstNode pn = bitOrExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[968]++;
int CodeCoverConditionCoverageHelper_C210;
        if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((matchToken(Token.AND)) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[472]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[969]++;
            int opPos = ts.tokenBeg;
            pn = new InfixExpression(Token.AND, pn, andExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[970]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[473]++;}
        return pn;
    }

    private AstNode bitOrExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[971]++;
        AstNode pn = bitXorExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[972]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[49]++;


int CodeCoverConditionCoverageHelper_C211;
        while ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((matchToken(Token.BITOR)) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[49]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[50]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[51]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[973]++;
            int opPos = ts.tokenBeg;
            pn = new InfixExpression(Token.BITOR, pn, bitXorExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[974]++;
        }
        return pn;
    }

    private AstNode bitXorExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[975]++;
        AstNode pn = bitAndExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[976]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[52]++;


int CodeCoverConditionCoverageHelper_C212;
        while ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((matchToken(Token.BITXOR)) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[52]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[53]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[54]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[977]++;
            int opPos = ts.tokenBeg;
            pn = new InfixExpression(Token.BITXOR, pn, bitAndExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[978]++;
        }
        return pn;
    }

    private AstNode bitAndExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[979]++;
        AstNode pn = eqExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[980]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[55]++;


int CodeCoverConditionCoverageHelper_C213;
        while ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((matchToken(Token.BITAND)) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[55]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[56]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[57]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[981]++;
            int opPos = ts.tokenBeg;
            pn = new InfixExpression(Token.BITAND, pn, eqExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[982]++;
        }
        return pn;
    }

    private AstNode eqExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[983]++;
        AstNode pn = relExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[984]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[58]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[58]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[59]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[60]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[985]++;
            int tt = peekToken(), opPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[986]++;
            switch (tt) {
              case Token.EQ:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[474]++;
              case Token.NE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[475]++;
              case Token.SHEQ:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[476]++;
              case Token.SHNE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[477]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[987]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[988]++;
                int parseToken = tt;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[989]++;
int CodeCoverConditionCoverageHelper_C215;
                if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((compilerEnv.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[478]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[990]++;
int CodeCoverConditionCoverageHelper_C216;
                    // JavaScript 1.2 uses shallow equality for == and != .
                    if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((tt == Token.EQ) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[480]++;
                        parseToken = Token.SHEQ;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[991]++;
}
                    else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[481]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[992]++;
int CodeCoverConditionCoverageHelper_C217; if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((tt == Token.NE) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[482]++;
                        parseToken = Token.SHNE;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[993]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[483]++;}
}

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[479]++;}
                pn = new InfixExpression(parseToken, pn, relExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[994]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[995]++;
                continue; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[484]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[996]++;
            break;
        }
        return pn;
    }

    private AstNode relExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[997]++;
        AstNode pn = shiftExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[998]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[61]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[61]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[62]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[63]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[999]++;
            int tt = peekToken(), opPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1000]++;
            switch (tt) {
              case Token.IN:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[485]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1001]++;
int CodeCoverConditionCoverageHelper_C219;
                if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((inForInit) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[486]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1002]++;
                    break;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[487]++;}
                // fall through
              case Token.INSTANCEOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[488]++;
              case Token.LE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[489]++;
              case Token.LT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[490]++;
              case Token.GE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[491]++;
              case Token.GT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[492]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1003]++;
                pn = new InfixExpression(tt, pn, shiftExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1004]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1005]++;
                continue; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[493]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1006]++;
            break;
        }
        return pn;
    }

    private AstNode shiftExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1007]++;
        AstNode pn = addExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1008]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[64]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[64]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[65]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[66]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1009]++;
            int tt = peekToken(), opPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1010]++;
            switch (tt) {
              case Token.LSH:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[494]++;
              case Token.URSH:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[495]++;
              case Token.RSH:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[496]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1011]++;
                pn = new InfixExpression(tt, pn, addExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1012]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1013]++;
                continue; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[497]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1014]++;
            break;
        }
        return pn;
    }

    private AstNode addExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1015]++;
        AstNode pn = mulExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1016]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[67]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[67]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[68]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[69]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1017]++;
            int tt = peekToken(), opPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1018]++;
int CodeCoverConditionCoverageHelper_C222;
            if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (8)) == 0 || true) &&
 ((tt == Token.ADD) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((tt == Token.SUB) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[498]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1019]++;
                pn = new InfixExpression(tt, pn, mulExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1020]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1021]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[499]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1022]++;
            break;
        }
        return pn;
    }

    private AstNode mulExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1023]++;
        AstNode pn = unaryExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1024]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[70]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[70]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[71]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[72]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1025]++;
            int tt = peekToken(), opPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1026]++;
            switch (tt) {
              case Token.MUL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[500]++;
              case Token.DIV:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[501]++;
              case Token.MOD:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[502]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1027]++;
                pn = new InfixExpression(tt, pn, unaryExpr(), opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1028]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1029]++;
                continue; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[503]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1030]++;
            break;
        }
        return pn;
    }

    private AstNode unaryExpr()
        throws IOException
    {
        AstNode node;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1031]++;
        int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1032]++;
        int line = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1033]++;

        switch(tt) {
          case Token.VOID:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[504]++;
          case Token.NOT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[505]++;
          case Token.BITNOT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[506]++;
          case Token.TYPEOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[507]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1034]++;
              node = new UnaryExpression(tt, ts.tokenBeg, unaryExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1035]++;
              return node;

          case Token.ADD:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[508]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1036]++;
              // Convert to special POS token in parse tree
              node = new UnaryExpression(Token.POS, ts.tokenBeg, unaryExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1037]++;
              node.setLineno(line);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1038]++;
              return node;

          case Token.SUB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[509]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1039]++;
              // Convert to special NEG token in parse tree
              node = new UnaryExpression(Token.NEG, ts.tokenBeg, unaryExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1040]++;
              node.setLineno(line);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1041]++;
              return node;

          case Token.INC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[510]++;
          case Token.DEC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[511]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1042]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1043]++;
              UnaryExpression expr = new UnaryExpression(tt, ts.tokenBeg,
                                                         memberExpr(true));
              expr.setLineno(line);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1044]++;
              checkBadIncDec(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1045]++;
              return expr;

          case Token.DELPROP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[512]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1046]++;
              node = new UnaryExpression(tt, ts.tokenBeg, unaryExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1047]++;
              node.setLineno(line);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1048]++;
              return node;

          case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[513]++;
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1049]++;
              return makeErrorNode();

          case Token.LT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[514]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1050]++;
int CodeCoverConditionCoverageHelper_C224;
              // XML stream encountered in expression.
              if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((compilerEnv.isXmlAvailable()) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[515]++;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1051]++;
                  return memberExprTail(true, xmlInitializer());

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[516]++;}
              // Fall thru to the default handling of RELOP

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[517]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1052]++;
              AstNode pn = memberExpr(true);
              // Don't look across a newline boundary for a postfix incop.
              tt = peekTokenOrEOL();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1053]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1054]++;
int CodeCoverConditionCoverageHelper_C225;
              if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C225 |= (8)) == 0 || true) &&
 ((tt == Token.INC) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((tt == Token.DEC) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[518]++;
                  return pn;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[519]++;}
              consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1055]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1056]++;
              UnaryExpression uexpr =
                      new UnaryExpression(tt, ts.tokenBeg, pn, true);
              uexpr.setLineno(line);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1057]++;
              checkBadIncDec(uexpr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1058]++;
              return uexpr;
        }
    }

    private AstNode xmlInitializer()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1059]++;
int CodeCoverConditionCoverageHelper_C226;
        if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((currentToken != Token.LT) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[520]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1060]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[521]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1061]++;
        int pos = ts.tokenBeg, tt = ts.getFirstXMLToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1062]++;
int CodeCoverConditionCoverageHelper_C227;
        if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (8)) == 0 || true) &&
 ((tt != Token.XML) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((tt != Token.XMLEND) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[522]++;
            reportError("msg.syntax");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1063]++;
            return makeErrorNode();

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[523]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1064]++;

        XmlLiteral pn = new XmlLiteral(pos);
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1065]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1066]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[73]++;



        for (;;tt = ts.getNextXMLToken()) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[73]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[74]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[75]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1067]++;
            switch (tt) {
              case Token.XML:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[524]++;
                  pn.addFragment(new XmlString(ts.tokenBeg, ts.getString()));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1068]++;
                  mustMatchToken(Token.LC, "msg.syntax");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1069]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1070]++;
                  int beg = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1071]++;
                  AstNode expr = (peekToken() == Token.RC)
                                 ? new EmptyExpression(beg, ts.tokenEnd - beg)
                                 : expr();
                  mustMatchToken(Token.RC, "msg.syntax");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1072]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1073]++;
                  XmlExpression xexpr = new XmlExpression(beg, expr);
                  xexpr.setIsXmlAttribute(ts.isXMLAttribute());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1074]++;
                  xexpr.setLength(ts.tokenEnd - beg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1075]++;
                  pn.addFragment(xexpr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1076]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1077]++;
                  break;

              case Token.XMLEND:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[525]++;
                  pn.addFragment(new XmlString(ts.tokenBeg, ts.getString()));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1078]++;
                  return pn;

              default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[526]++;
                  reportError("msg.syntax");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1079]++;
                  return makeErrorNode();
            }
        }
    }

    private List<AstNode> argumentList()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1080]++;
int CodeCoverConditionCoverageHelper_C229;
        if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((matchToken(Token.RP)) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[527]++;
            return null;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[528]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1081]++;

        List<AstNode> result = new ArrayList<AstNode>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1082]++;
        boolean wasInForInit = inForInit;
        inForInit = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1083]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1084]++;
boolean CodeCoverTryBranchHelper_Try18 = false;
        try {
CodeCoverTryBranchHelper_Try18 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1085]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[76]++;


int CodeCoverConditionCoverageHelper_C230;
            do {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[76]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[77]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[78]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1086]++;
int CodeCoverConditionCoverageHelper_C231;
                if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((peekToken() == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[530]++;
                    reportError("msg.yield.parenthesized");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1087]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[531]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1088]++;
                AstNode en = assignExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1089]++;
int CodeCoverConditionCoverageHelper_C232;
                if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((peekToken() == Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[532]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1090]++;
boolean CodeCoverTryBranchHelper_Try19 = false;
                    try {
CodeCoverTryBranchHelper_Try19 = true;
                        result.add(generatorExpression(en, 0, true));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1091]++;
                    }
                    catch(IOException ex) {
CodeCoverTryBranchHelper_Try19 = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[535]++;
                        // #TODO
                    } finally {
    if ( CodeCoverTryBranchHelper_Try19 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[534]++;
}
  }

                }
                else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[533]++;
                    result.add(en);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1092]++;
                }
            } while ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((matchToken(Token.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false));
        } finally {
if ( CodeCoverTryBranchHelper_Try18 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[529]++;
}
            inForInit = wasInForInit;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1093]++;
        }

        mustMatchToken(Token.RP, "msg.no.paren.arg");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1094]++;
        return result;
    }

    /**
     * Parse a new-expression, or if next token isn't {@link Token#NEW},
     * a primary expression.
     * @param allowCallSyntax passed down to {@link #memberExprTail}
     */
    private AstNode memberExpr(boolean allowCallSyntax)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1095]++;
        int tt = peekToken(), lineno = ts.lineno;
        AstNode pn;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1096]++;
int CodeCoverConditionCoverageHelper_C233;

        if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((tt != Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[536]++;
            pn = primaryExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1097]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[537]++;
            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1098]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1099]++;
            int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1100]++;
            NewExpression nx = new NewExpression(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1101]++;

            AstNode target = memberExpr(false);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1102]++;
            int end = getNodeEnd(target);
            nx.setTarget(target);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1103]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1104]++;

            int lp = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1105]++;
int CodeCoverConditionCoverageHelper_C234;
            if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((matchToken(Token.LP)) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[538]++;
                lp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1106]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1107]++;
                List<AstNode> args = argumentList();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1108]++;
int CodeCoverConditionCoverageHelper_C235;
                if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (8)) == 0 || true) &&
 ((args != null) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((args.size() > ARGC_LIMIT) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[540]++;
                    reportError("msg.too.many.constructor.args");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1109]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[541]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1110]++;
                int rp = ts.tokenBeg;
                end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1111]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1112]++;
int CodeCoverConditionCoverageHelper_C236;
                if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((args != null) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[542]++;
                    nx.setArguments(args);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1113]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[543]++;}
                nx.setParens(lp - pos, rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1114]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[539]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1115]++;
int CodeCoverConditionCoverageHelper_C237;

            // Experimental syntax: allow an object literal to follow a new
            // expression, which will mean a kind of anonymous class built with
            // the JavaAdapter.  the object literal will be passed as an
            // additional argument to the constructor.
            if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((matchToken(Token.LC)) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[544]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1116]++;
                ObjectLiteral initializer = objectLiteral();
                end = getNodeEnd(initializer);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1117]++;
                nx.setInitializer(initializer);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1118]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[545]++;}
            nx.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1119]++;
            pn = nx;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1120]++;
        }
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1121]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1122]++;
        AstNode tail = memberExprTail(allowCallSyntax, pn);
        return tail;
    }

    /**
     * Parse any number of "(expr)", "[expr]" ".expr", "..expr",
     * or ".(expr)" constructs trailing the passed expression.
     * @param pn the non-null parent node
     * @return the outermost (lexically last occurring) expression,
     * which will have the passed parent node as a descendant
     */
    private AstNode memberExprTail(boolean allowCallSyntax, AstNode pn)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1123]++;
int CodeCoverConditionCoverageHelper_C238;
        // we no longer return null for errors, so this won't be null
        if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((pn == null) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[546]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1124]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[547]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1125]++;
        int pos = pn.getPosition();
        int lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1126]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[79]++;


      tailLoop:
        for (;;) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[79]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[80]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[81]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1127]++;
            int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1128]++;
            switch (tt) {
              case Token.DOT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[548]++;
              case Token.DOTDOT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[549]++;
                  lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1129]++;
                  pn = propertyAccess(tt, pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1130]++;
                  pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1131]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1132]++;
                  break;

              case Token.DOTQUERY:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[550]++;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1133]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1134]++;
                  int opPos = ts.tokenBeg, rp = -1;
                  lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1135]++;
                  mustHaveXML();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1136]++;
                  setRequiresActivation();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1137]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1138]++;
                  AstNode filter = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1139]++;
                  int end = getNodeEnd(filter);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1140]++;
int CodeCoverConditionCoverageHelper_C240;
                  if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren")) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[551]++;
                      rp = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1141]++;
                      end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1142]++;

                  } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[552]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1143]++;
                  XmlDotQuery q = new XmlDotQuery(pos, end - pos);
                  q.setLeft(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1144]++;
                  q.setRight(filter);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1145]++;
                  q.setOperatorPosition(opPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1146]++;
                  q.setRp(rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1147]++;
                  q.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1148]++;
                  pn = q;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1149]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1150]++;
                  break;

              case Token.LB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[553]++;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1151]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1152]++;
                  int lb = ts.tokenBeg, rb = -1;
                  lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1153]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1154]++;
                  AstNode expr = expr();
                  end = getNodeEnd(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1155]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1156]++;
int CodeCoverConditionCoverageHelper_C241;
                  if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RB, "msg.no.bracket.index")) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[554]++;
                      rb = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1157]++;
                      end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1158]++;

                  } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[555]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1159]++;
                  ElementGet g = new ElementGet(pos, end - pos);
                  g.setTarget(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1160]++;
                  g.setElement(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1161]++;
                  g.setParens(lb, rb);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1162]++;
                  g.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1163]++;
                  pn = g;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1164]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1165]++;
                  break;

              case Token.LP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[556]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1166]++;
int CodeCoverConditionCoverageHelper_C242;
                  if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((allowCallSyntax) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[557]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1167]++;
                      break tailLoop;

                  } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[558]++;}
                  lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1168]++;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1169]++;
                  checkCallRequiresActivation(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1170]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1171]++;
                  FunctionCall f = new FunctionCall(pos);
                  f.setTarget(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1172]++;
                  // Assign the line number for the function call to where
                  // the paren appeared, not where the name expression started.
                  f.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1173]++;
                  f.setLp(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1174]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1175]++;
                  List<AstNode> args = argumentList();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1176]++;
int CodeCoverConditionCoverageHelper_C243;
                  if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (8)) == 0 || true) &&
 ((args != null) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((args.size() > ARGC_LIMIT) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[559]++;
                      reportError("msg.too.many.function.args");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1177]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[560]++;}
                  f.setArguments(args);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1178]++;
                  f.setRp(ts.tokenBeg - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1179]++;
                  f.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1180]++;
                  pn = f;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1181]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1182]++;
                  break;

              default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[561]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1183]++;
                  break tailLoop;
            }
        }
        return pn;
    }

    /**
     * Handles any construct following a "." or ".." operator.
     * @param pn the left-hand side (target) of the operator.  Never null.
     * @return a PropertyGet, XmlMemberGet, or ErrorNode
     */
    private AstNode propertyAccess(int tt, AstNode pn)
            throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1184]++;
int CodeCoverConditionCoverageHelper_C244;
        if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((pn == null) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[562]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1185]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[563]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1186]++;
        int memberTypeFlags = 0, lineno = ts.lineno, dotPos = ts.tokenBeg;
        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1187]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1188]++;
int CodeCoverConditionCoverageHelper_C245;

        if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((tt == Token.DOTDOT) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[564]++;
            mustHaveXML();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1189]++;
            memberTypeFlags = Node.DESCENDANTS_FLAG;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1190]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[565]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1191]++;
int CodeCoverConditionCoverageHelper_C246;

        if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((compilerEnv.isXmlAvailable()) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[566]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1192]++;
            int maybeName = nextToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1193]++;
int CodeCoverConditionCoverageHelper_C247;
            if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (32)) == 0 || true) &&
 ((maybeName != Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C247 |= (8)) == 0 || true) &&
 ((compilerEnv.isReservedKeywordAsIdentifier()) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((TokenStream.isKeyword(ts.getString())) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[568]++;
              reportError("msg.no.name.after.dot");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1194]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[569]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1195]++;

            Name name = createNameNode(true, Token.GETPROP);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1196]++;
            PropertyGet pg = new PropertyGet(pn, name, dotPos);
            pg.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1197]++;
            return pg;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[567]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1198]++;

        AstNode ref = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1199]++;  // right side of . or .. operator

        int token = nextToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1200]++;
        switch (token) {
          case Token.THROW:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[570]++;
              // needed for generator.throw();
              saveNameTokenData(ts.tokenBeg, "throw", ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1201]++;
              ref = propertyName(-1, "throw", memberTypeFlags);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1202]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1203]++;
              break;

          case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[571]++;
              // handles: name, ns::name, ns::*, ns::[expr]
              ref = propertyName(-1, ts.getString(), memberTypeFlags);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1204]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1205]++;
              break;

          case Token.MUL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[572]++;
              // handles: *, *::name, *::*, *::[expr]
              saveNameTokenData(ts.tokenBeg, "*", ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1206]++;
              ref = propertyName(-1, "*", memberTypeFlags);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1207]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1208]++;
              break;

          case Token.XMLATTR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[573]++;
              // handles: '@attr', '@ns::attr', '@ns::*', '@ns::*',
              //          '@::attr', '@::*', '@*', '@*::attr', '@*::*'
              ref = attributeAccess();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1209]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1210]++;
              break;

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[574]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1211]++;
int CodeCoverConditionCoverageHelper_C248;
              if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((compilerEnv.isReservedKeywordAsIdentifier()) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[575]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1212]++;
                  // allow keywords as property names, e.g. ({if: 1})
                  String name = Token.keywordToName(token);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1213]++;
int CodeCoverConditionCoverageHelper_C249;
                  if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[577]++;
                      saveNameTokenData(ts.tokenBeg, name, ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1214]++;
                      ref = propertyName(-1, name, memberTypeFlags);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1215]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1216]++;
                      break;

                  } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[578]++;}

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[576]++;}
              reportError("msg.no.name.after.dot");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1217]++;
              return makeErrorNode();
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1218]++;

        boolean xml = ref instanceof XmlRef;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1219]++;
        InfixExpression result = xml ? new XmlMemberGet() : new PropertyGet();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1220]++;
int CodeCoverConditionCoverageHelper_C250;
        if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (8)) == 0 || true) &&
 ((xml) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((tt == Token.DOT) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[579]++;
            result.setType(Token.DOT);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1221]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[580]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1222]++;
        int pos = pn.getPosition();
        result.setPosition(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1223]++;
        result.setLength(getNodeEnd(ref) - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1224]++;
        result.setOperatorPosition(dotPos - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1225]++;
        result.setLineno(pn.getLineno());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1226]++;
        result.setLeft(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1227]++;  // do this after setting position
        result.setRight(ref);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1228]++;
        return result;
    }

    /**
     * Xml attribute expression:<p>
     *   {@code @attr}, {@code @ns::attr}, {@code @ns::*}, {@code @ns::*},
     *   {@code @*}, {@code @*::attr}, {@code @*::*}, {@code @ns::[expr]},
     *   {@code @*::[expr]}, {@code @[expr]} <p>
     * Called if we peeked an '@' token.
     */
    private AstNode attributeAccess()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1229]++;
        int tt = nextToken(), atPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1230]++;

        switch (tt) {
          // handles: @name, @ns::name, @ns::*, @ns::[expr]
          case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[581]++;
              return propertyName(atPos, ts.getString(), 0);

          // handles: @*, @*::name, @*::*, @*::[expr]
          case Token.MUL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[582]++;
              saveNameTokenData(ts.tokenBeg, "*", ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1231]++;
              return propertyName(atPos, "*", 0);

          // handles @[expr]
          case Token.LB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[583]++;
              return xmlElemRef(atPos, null, -1);

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[584]++;
              reportError("msg.no.name.after.xmlAttr");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1232]++;
              return makeErrorNode();
        }
    }

    /**
     * Check if :: follows name in which case it becomes a qualified name.
     *
     * @param atPos a natural number if we just read an '@' token, else -1
     *
     * @param s the name or string that was matched (an identifier, "throw" or
     * "*").
     *
     * @param memberTypeFlags flags tracking whether we're a '.' or '..' child
     *
     * @return an XmlRef node if it's an attribute access, a child of a
     * '..' operator, or the name is followed by ::.  For a plain name,
     * returns a Name node.  Returns an ErrorNode for malformed XML
     * expressions.  (For now - might change to return a partial XmlRef.)
     */
    private AstNode propertyName(int atPos, String s, int memberTypeFlags)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1233]++;
        int pos = atPos != -1 ? atPos : ts.tokenBeg, lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1234]++;
        int colonPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1235]++;
        Name name = createNameNode(true, currentToken);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1236]++;
        Name ns = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1237]++;
int CodeCoverConditionCoverageHelper_C251;

        if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((matchToken(Token.COLONCOLON)) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[585]++;
            ns = name;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1238]++;
            colonPos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1239]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1240]++;

            switch (nextToken()) {
              // handles name::name
              case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[587]++;
                  name = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1241]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1242]++;
                  break;

              // handles name::*
              case Token.MUL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[588]++;
                  saveNameTokenData(ts.tokenBeg, "*", ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1243]++;
                  name = createNameNode(false, -1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1244]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1245]++;
                  break;

              // handles name::[expr] or *::[expr]
              case Token.LB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[589]++;
                  return xmlElemRef(atPos, ns, colonPos);

              default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[590]++;
                  reportError("msg.no.name.after.coloncolon");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1246]++;
                  return makeErrorNode();
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[586]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1247]++;
int CodeCoverConditionCoverageHelper_C252;

        if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (32)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C252 |= (8)) == 0 || true) &&
 ((memberTypeFlags == 0) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((atPos == -1) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[591]++;
            return name;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[592]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1248]++;

        XmlPropRef ref = new XmlPropRef(pos, getNodeEnd(name) - pos);
        ref.setAtPos(atPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1249]++;
        ref.setNamespace(ns);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1250]++;
        ref.setColonPos(colonPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1251]++;
        ref.setPropName(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1252]++;
        ref.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1253]++;
        return ref;
    }

    /**
     * Parse the [expr] portion of an xml element reference, e.g.
     * @[expr], @*::[expr], or ns::[expr].
     */
    private XmlElemRef xmlElemRef(int atPos, Name namespace, int colonPos)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1254]++;
        int lb = ts.tokenBeg, rb = -1, pos = atPos != -1 ? atPos : lb;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1255]++;
        AstNode expr = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1256]++;
        int end = getNodeEnd(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1257]++;
int CodeCoverConditionCoverageHelper_C253;
        if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RB, "msg.no.bracket.index")) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[593]++;
            rb = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1258]++;
            end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1259]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[594]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1260]++;
        XmlElemRef ref = new XmlElemRef(pos, end - pos);
        ref.setNamespace(namespace);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1261]++;
        ref.setColonPos(colonPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1262]++;
        ref.setAtPos(atPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1263]++;
        ref.setExpression(expr);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1264]++;
        ref.setBrackets(lb, rb);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1265]++;
        return ref;
    }

    private AstNode destructuringPrimaryExpr()
        throws IOException, ParserException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1266]++;
boolean CodeCoverTryBranchHelper_Try20 = false;
        try {
CodeCoverTryBranchHelper_Try20 = true;
            inDestructuringAssignment = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1267]++;
            return primaryExpr();
        } finally {
if ( CodeCoverTryBranchHelper_Try20 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[595]++;
}
            inDestructuringAssignment = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1268]++;
        }
    }

    private AstNode primaryExpr()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1269]++;
        int ttFlagged = nextFlaggedToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1270]++;
        int tt = ttFlagged & CLEAR_TI_MASK;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1271]++;

        switch(tt) {
          case Token.FUNCTION:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[596]++;
              return function(FunctionNode.FUNCTION_EXPRESSION);

          case Token.LB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[597]++;
              return arrayLiteral();

          case Token.LC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[598]++;
              return objectLiteral();

          case Token.LET:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[599]++;
              return let(false, ts.tokenBeg);

          case Token.LP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[600]++;
              return parenExpr();

          case Token.XMLATTR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[601]++;
              mustHaveXML();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1272]++;
              return attributeAccess();

          case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[602]++;
              return name(ttFlagged, tt);

          case Token.NUMBER:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[603]++; {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1273]++;
              String s = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1274]++;
int CodeCoverConditionCoverageHelper_C254;
              if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (8)) == 0 || true) &&
 ((this.inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((ts.isNumberOctal()) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[604]++;
                  reportError("msg.no.octal.strict");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1275]++;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[605]++;}
              return new NumberLiteral(ts.tokenBeg,
                                       s,
                                       ts.getNumber());
          }

          case Token.STRING:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[606]++;
              return createStringLiteral();

          case Token.DIV:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[607]++;
          case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[608]++;
              // Got / or /= which in this context means a regexp
              ts.readRegExp(tt);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1276]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1277]++;
              int pos = ts.tokenBeg, end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1278]++;
              RegExpLiteral re = new RegExpLiteral(pos, end - pos);
              re.setValue(ts.getString());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1279]++;
              re.setFlags(ts.readAndClearRegExpFlags());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1280]++;
              return re;

          case Token.NULL:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[609]++;
          case Token.THIS:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[610]++;
          case Token.FALSE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[611]++;
          case Token.TRUE:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[612]++;
              pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1281]++; end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1282]++;
              return new KeywordLiteral(pos, end - pos, tt);

          case Token.RESERVED:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[613]++;
              reportError("msg.reserved.id");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1283]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1284]++;
              break;

          case Token.ERROR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[614]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1285]++;
              // the scanner or one of its subroutines reported the error.
              break;

          case Token.EOF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[615]++;
              reportError("msg.unexpected.eof");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1286]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1287]++;
              break;

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[616]++;
              reportError("msg.syntax");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1288]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1289]++;
              break;
        }
        // should only be reachable in IDE/error-recovery mode
        return makeErrorNode();
    }

    private AstNode parenExpr() throws IOException {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1290]++;
        boolean wasInForInit = inForInit;
        inForInit = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1291]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1292]++;
boolean CodeCoverTryBranchHelper_Try21 = false;
        try {
CodeCoverTryBranchHelper_Try21 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1293]++;
            Comment jsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1294]++;
            int lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1295]++;
            int begin = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1296]++;
            AstNode e = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1297]++;
int CodeCoverConditionCoverageHelper_C255;
            if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((peekToken() == Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[618]++;
                return generatorExpression(e, begin);

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[619]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1298]++;
            ParenthesizedExpression pn = new ParenthesizedExpression(e);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1299]++;
int CodeCoverConditionCoverageHelper_C256;
            if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((jsdocNode == null) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[620]++;
                jsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1300]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[621]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1301]++;
int CodeCoverConditionCoverageHelper_C257;
            if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((jsdocNode != null) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[622]++;
                pn.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1302]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[623]++;}
            mustMatchToken(Token.RP, "msg.no.paren");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1303]++;
            pn.setLength(ts.tokenEnd - pn.getPosition());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1304]++;
            pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1305]++;
            return pn;
        } finally {
if ( CodeCoverTryBranchHelper_Try21 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[617]++;
}
            inForInit = wasInForInit;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1306]++;
        }
    }

    private AstNode name(int ttFlagged, int tt) throws IOException {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1307]++;
        String nameString = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1308]++;
        int namePos = ts.tokenBeg, nameLineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1309]++;
int CodeCoverConditionCoverageHelper_C258;
        if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (8)) == 0 || true) &&
 ((0 != (ttFlagged & TI_CHECK_LABEL)) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((peekToken() == Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[624]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1310]++;
            // Do not consume colon.  It is used as an unwind indicator
            // to return to statementHelper.
            Label label = new Label(namePos, ts.tokenEnd - namePos);
            label.setName(nameString);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1311]++;
            label.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1312]++;
            return label;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[625]++;}
        // Not a label.  Unfortunately peeking the next token to check for
        // a colon has biffed ts.tokenBeg, ts.tokenEnd.  We store the name's
        // bounds in instance vars and createNameNode uses them.
        saveNameTokenData(namePos, nameString, nameLineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1313]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1314]++;
int CodeCoverConditionCoverageHelper_C259;

        if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((compilerEnv.isXmlAvailable()) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[626]++;
            return propertyName(-1, nameString, 0);

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[627]++;
            return createNameNode(true, Token.NAME);
        }
    }

    /**
     * May return an {@link ArrayLiteral} or {@link ArrayComprehension}.
     */
    private AstNode arrayLiteral()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1315]++;
int CodeCoverConditionCoverageHelper_C260;
        if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((currentToken != Token.LB) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[628]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1316]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[629]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1317]++;
        int pos = ts.tokenBeg, end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1318]++;
        List<AstNode> elements = new ArrayList<AstNode>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1319]++;
        ArrayLiteral pn = new ArrayLiteral(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1320]++;
        boolean after_lb_or_comma = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1321]++;
        int afterComma = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1322]++;
        int skipCount = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1323]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[82]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[82]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[83]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[84]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1324]++;
            int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1325]++;
int CodeCoverConditionCoverageHelper_C262;
            if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((tt == Token.COMMA) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[630]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1326]++;
                afterComma = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1327]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1328]++;
int CodeCoverConditionCoverageHelper_C263;
                if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((after_lb_or_comma) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[632]++;
                    after_lb_or_comma = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1329]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[633]++;
                    elements.add(new EmptyExpression(ts.tokenBeg, 1));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1330]++;
                    skipCount++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1331]++;
                }

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[631]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1332]++;
int CodeCoverConditionCoverageHelper_C264; if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((tt == Token.RB) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[634]++;
                consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1333]++;
                // for ([a,] in obj) is legal, but for ([a] in obj) is
                // not since we have both key and value supplied. The
                // trick is that [a,] and [a] are equivalent in other
                // array literal contexts. So we calculate a special
                // length value just for destructuring assignment.
                end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1334]++;
                pn.setDestructuringLength(elements.size() +
                                          (after_lb_or_comma ? 1 : 0));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1335]++;
                pn.setSkipCount(skipCount);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1336]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1337]++;
int CodeCoverConditionCoverageHelper_C265;
                if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((afterComma != -1) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[636]++;
                    warnTrailingComma(pos, elements, afterComma);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1338]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[637]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1339]++;
                break;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[635]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1340]++;
int CodeCoverConditionCoverageHelper_C266; if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (32)) == 0 || true) &&
 ((tt == Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C266 |= (8)) == 0 || true) &&
 ((after_lb_or_comma) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((elements.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 3) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[638]++;
                return arrayComprehension(elements.get(0), pos);

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[639]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1341]++;
int CodeCoverConditionCoverageHelper_C267; if ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((tt == Token.EOF) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[640]++;
                reportError("msg.no.bracket.arg");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1342]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1343]++;
                break;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[641]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1344]++;
int CodeCoverConditionCoverageHelper_C268;
                if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((after_lb_or_comma) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[642]++;
                    reportError("msg.no.bracket.arg");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1345]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[643]++;}
                elements.add(assignExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1346]++;
                after_lb_or_comma = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1347]++;
                afterComma = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1348]++;
            }
}
}
}
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1349]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[85]++;


        for (AstNode e : elements) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[85]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[86]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[87]++;
}
            pn.addElement(e);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1350]++;
        }
        pn.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1351]++;
        return pn;
    }

    /**
     * Parse a JavaScript 1.7 Array comprehension.
     * @param result the first expression after the opening left-bracket
     * @param pos start of LB token that begins the array comprehension
     * @return the array comprehension or an error node
     */
    private AstNode arrayComprehension(AstNode result, int pos)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1352]++;
        List<ArrayComprehensionLoop> loops =
                new ArrayList<ArrayComprehensionLoop>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1353]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[88]++;


int CodeCoverConditionCoverageHelper_C269;
        while ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((peekToken() == Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[88]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[89]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[90]++;
}
            loops.add(arrayComprehensionLoop());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1354]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1355]++;
        int ifPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1356]++;
        ConditionData data = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1357]++;
int CodeCoverConditionCoverageHelper_C270;
        if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((peekToken() == Token.IF) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[644]++;
            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1358]++;
            ifPos = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1359]++;
            data = condition();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1360]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[645]++;}
        mustMatchToken(Token.RB, "msg.no.bracket.arg");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1361]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1362]++;
        ArrayComprehension pn = new ArrayComprehension(pos, ts.tokenEnd - pos);
        pn.setResult(result);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1363]++;
        pn.setLoops(loops);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1364]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1365]++;
int CodeCoverConditionCoverageHelper_C271;
        if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[646]++;
            pn.setIfPosition(ifPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1366]++;
            pn.setFilter(data.condition);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1367]++;
            pn.setFilterLp(data.lp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1368]++;
            pn.setFilterRp(data.rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1369]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[647]++;}
        return pn;
    }

    private ArrayComprehensionLoop arrayComprehensionLoop()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1370]++;
int CodeCoverConditionCoverageHelper_C272;
        if ((((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((nextToken() != Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[648]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1371]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[649]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1372]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1373]++;
        int eachPos = -1, lp = -1, rp = -1, inPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1374]++;
        ArrayComprehensionLoop pn = new ArrayComprehensionLoop(pos);

        pushScope(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1375]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1376]++;
boolean CodeCoverTryBranchHelper_Try22 = false;
        try {
CodeCoverTryBranchHelper_Try22 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1377]++;
int CodeCoverConditionCoverageHelper_C273;
            if ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((matchToken(Token.NAME)) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[651]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1378]++;
int CodeCoverConditionCoverageHelper_C274;
                if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((ts.getString().equals("each")) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[653]++;
                    eachPos = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1379]++;

                } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[654]++;
                    reportError("msg.no.paren.for");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1380]++;
                }

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[652]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1381]++;
int CodeCoverConditionCoverageHelper_C275;
            if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.for")) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[655]++;
                lp = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1382]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[656]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1383]++;

            AstNode iter = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1384]++;
            switch (peekToken()) {
              case Token.LB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[657]++;
              case Token.LC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[658]++;
                  // handle destructuring assignment
                  iter = destructuringPrimaryExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1385]++;
                  markDestructuring(iter);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1386]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1387]++;
                  break;
              case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[659]++;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1388]++;
                  iter = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1389]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1390]++;
                  break;
              default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[660]++;
                  reportError("msg.bad.var");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1391]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1392]++;
int CodeCoverConditionCoverageHelper_C276;

            // Define as a let since we want the scope of the variable to
            // be restricted to the array comprehension
            if ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((iter.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[661]++;
                defineSymbol(Token.LET, ts.getString(), true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1393]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[662]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1394]++;
int CodeCoverConditionCoverageHelper_C277;

            if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.IN, "msg.in.after.for.name")) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[663]++;
                inPos = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1395]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[664]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1396]++;
            AstNode obj = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1397]++;
int CodeCoverConditionCoverageHelper_C278;
            if ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.for.ctrl")) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[665]++;
                rp = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1398]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[666]++;}

            pn.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1399]++;
            pn.setIterator(iter);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1400]++;
            pn.setIteratedObject(obj);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1401]++;
            pn.setInPosition(inPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1402]++;
            pn.setEachPosition(eachPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1403]++;
            pn.setIsForEach(eachPos != -1);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1404]++;
            pn.setParens(lp, rp);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1405]++;
            return pn;
        } finally {
if ( CodeCoverTryBranchHelper_Try22 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[650]++;
}
            popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1406]++;
        }
    }

    private AstNode generatorExpression(AstNode result, int pos)
        throws IOException
    {
        return generatorExpression(result, pos, false);
    }

    private AstNode generatorExpression(AstNode result, int pos, boolean inFunctionParams)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1407]++;

        List<GeneratorExpressionLoop> loops =
                new ArrayList<GeneratorExpressionLoop>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1408]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[91]++;


int CodeCoverConditionCoverageHelper_C279;
        while ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((peekToken() == Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[91]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[92]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[93]++;
}
            loops.add(generatorExpressionLoop());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1409]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1410]++;
        int ifPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1411]++;
        ConditionData data = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1412]++;
int CodeCoverConditionCoverageHelper_C280;
        if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((peekToken() == Token.IF) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[667]++;
            consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1413]++;
            ifPos = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1414]++;
            data = condition();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1415]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[668]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1416]++;
int CodeCoverConditionCoverageHelper_C281;
        if((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((inFunctionParams) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[669]++;
            mustMatchToken(Token.RP, "msg.no.paren.let");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1417]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[670]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1418]++;
        GeneratorExpression pn = new GeneratorExpression(pos, ts.tokenEnd - pos);
        pn.setResult(result);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1419]++;
        pn.setLoops(loops);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1420]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1421]++;
int CodeCoverConditionCoverageHelper_C282;
        if ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[671]++;
            pn.setIfPosition(ifPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1422]++;
            pn.setFilter(data.condition);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1423]++;
            pn.setFilterLp(data.lp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1424]++;
            pn.setFilterRp(data.rp - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1425]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[672]++;}
        return pn;
    }

    private GeneratorExpressionLoop generatorExpressionLoop()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1426]++;
int CodeCoverConditionCoverageHelper_C283;
        if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((nextToken() != Token.FOR) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[673]++; codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1427]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[674]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1428]++;
        int pos = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1429]++;
        int lp = -1, rp = -1, inPos = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1430]++;
        GeneratorExpressionLoop pn = new GeneratorExpressionLoop(pos);

        pushScope(pn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1431]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1432]++;
boolean CodeCoverTryBranchHelper_Try23 = false;
        try {
CodeCoverTryBranchHelper_Try23 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1433]++;
int CodeCoverConditionCoverageHelper_C284;
            if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.LP, "msg.no.paren.for")) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[676]++;
                lp = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1434]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[677]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1435]++;

            AstNode iter = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1436]++;
            switch (peekToken()) {
              case Token.LB:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[678]++;
              case Token.LC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[679]++;
                  // handle destructuring assignment
                  iter = destructuringPrimaryExpr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1437]++;
                  markDestructuring(iter);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1438]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1439]++;
                  break;
              case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[680]++;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1440]++;
                  iter = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1441]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1442]++;
                  break;
              default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[681]++;
                  reportError("msg.bad.var");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1443]++;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1444]++;
int CodeCoverConditionCoverageHelper_C285;

            // Define as a let since we want the scope of the variable to
            // be restricted to the array comprehension
            if ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((iter.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[682]++;
                defineSymbol(Token.LET, ts.getString(), true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1445]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[683]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1446]++;
int CodeCoverConditionCoverageHelper_C286;

            if ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.IN, "msg.in.after.for.name")) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[684]++;
                inPos = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1447]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[685]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1448]++;
            AstNode obj = expr();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1449]++;
int CodeCoverConditionCoverageHelper_C287;
            if ((((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((mustMatchToken(Token.RP, "msg.no.paren.for.ctrl")) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[686]++;
                rp = ts.tokenBeg - pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1450]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[687]++;}

            pn.setLength(ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1451]++;
            pn.setIterator(iter);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1452]++;
            pn.setIteratedObject(obj);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1453]++;
            pn.setInPosition(inPos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1454]++;
            pn.setParens(lp, rp);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1455]++;
            return pn;
        } finally {
if ( CodeCoverTryBranchHelper_Try23 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[675]++;
}
            popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1456]++;
        }
    }

    private static final int PROP_ENTRY = 1;
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1457]++;
  }
    private static final int GET_ENTRY  = 2;
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1458]++;
  }
    private static final int SET_ENTRY  = 4;
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1459]++;
  }

    private ObjectLiteral objectLiteral()
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1460]++;
        int pos = ts.tokenBeg, lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1461]++;
        int afterComma = -1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1462]++;
        List<ObjectProperty> elems = new ArrayList<ObjectProperty>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1463]++;
        Set<String> getterNames = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1464]++;
        Set<String> setterNames = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1465]++;
int CodeCoverConditionCoverageHelper_C288;
        if ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((this.inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[688]++;
            getterNames = new HashSet<String>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1466]++;
            setterNames = new HashSet<String>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1467]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[689]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1468]++;
        Comment objJsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1469]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[94]++;



      commaLoop:
        for (;;) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[94]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[95]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[96]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1470]++;
            String propertyName = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1471]++;
            int entryKind = PROP_ENTRY;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1472]++;
            int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1473]++;
            Comment jsdocNode = getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1474]++;
            switch(tt) {
              case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[690]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1475]++;
                  Name name = createNameNode();
                  propertyName = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1476]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1477]++;
                  int ppos = ts.tokenBeg;
                  consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1478]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1479]++;

                  // This code path needs to handle both destructuring object
                  // literals like:
                  // var {get, b} = {get: 1, b: 2};
                  // and getters like:
                  // var x = {get 1() { return 2; };
                  // So we check a whitelist of tokens to check if we're at the
                  // first case. (Because of keywords, the second case may be
                  // many tokens.)
                  int peeked = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1480]++;
                  boolean maybeGetterOrSetter =
                          "get".equals(propertyName)
                          || "set".equals(propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1481]++;
int CodeCoverConditionCoverageHelper_C290;
                  if ((((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (128)) == 0 || true) &&
 ((maybeGetterOrSetter) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C290 |= (32)) == 0 || true) &&
 ((peeked != Token.COMMA) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C290 |= (8)) == 0 || true) &&
 ((peeked != Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 ((peeked != Token.RC) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 4) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 4) && false))
                  {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[691]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1482]++;
                      boolean isGet = "get".equals(propertyName);
                      entryKind = isGet ? GET_ENTRY : SET_ENTRY;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1483]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1484]++;
                      AstNode pname = objliteralProperty();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1485]++;
int CodeCoverConditionCoverageHelper_C291;
                      if ((((((CodeCoverConditionCoverageHelper_C291 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C291 |= (2)) == 0 || true) &&
 ((pname == null) && 
  ((CodeCoverConditionCoverageHelper_C291 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[693]++;
                          propertyName = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1486]++;

                      } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[694]++;
                          propertyName = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1487]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1488]++;
                          ObjectProperty objectProp = getterSetterProperty(
                                  ppos, pname, isGet);
                          pname.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1489]++;
                          elems.add(objectProp);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1490]++;
                      }

                  } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[692]++;
                      name.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1491]++;
                      elems.add(plainProperty(name, tt));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1492]++;
                  }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1493]++;
                  break;

              case Token.RC:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[695]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1494]++;
int CodeCoverConditionCoverageHelper_C292;
                  if ((((((CodeCoverConditionCoverageHelper_C292 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C292 |= (2)) == 0 || true) &&
 ((afterComma != -1) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[696]++;
                      warnTrailingComma(pos, elems, afterComma);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1495]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[697]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1496]++;
                  break commaLoop;

              default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[698]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1497]++;
                  AstNode pname = objliteralProperty();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1498]++;
int CodeCoverConditionCoverageHelper_C293;
                  if ((((((CodeCoverConditionCoverageHelper_C293 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C293 |= (2)) == 0 || true) &&
 ((pname == null) && 
  ((CodeCoverConditionCoverageHelper_C293 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[699]++;
                      propertyName = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1499]++;

                  } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[700]++;
                      propertyName = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1500]++;
                      pname.setJsDocNode(jsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1501]++;
                      elems.add(plainProperty(pname, tt));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1502]++;
                  }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1503]++;
                  break;
            }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1504]++;
int CodeCoverConditionCoverageHelper_C294;

            if ((((((CodeCoverConditionCoverageHelper_C294 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C294 |= (8)) == 0 || true) &&
 ((this.inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C294 |= (2)) == 0 || true) &&
 ((propertyName != null) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[701]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1505]++;
                switch (entryKind) {
                case PROP_ENTRY:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[703]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1506]++;
int CodeCoverConditionCoverageHelper_C295;
                    if ((((((CodeCoverConditionCoverageHelper_C295 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C295 |= (8)) == 0 || true) &&
 ((getterNames.contains(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C295 |= (2)) == 0 || true) &&
 ((setterNames.contains(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[704]++;
                        addError("msg.dup.obj.lit.prop.strict", propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1507]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[705]++;}
                    getterNames.add(propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1508]++;
                    setterNames.add(propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1509]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1510]++;
                    break;
                case GET_ENTRY:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[706]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1511]++;
int CodeCoverConditionCoverageHelper_C296;
                    if ((((((CodeCoverConditionCoverageHelper_C296 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C296 |= (2)) == 0 || true) &&
 ((getterNames.contains(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C296 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[707]++;
                        addError("msg.dup.obj.lit.prop.strict", propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1512]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[708]++;}
                    getterNames.add(propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1513]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1514]++;
                    break;
                case SET_ENTRY:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[709]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1515]++;
int CodeCoverConditionCoverageHelper_C297;
                    if ((((((CodeCoverConditionCoverageHelper_C297 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C297 |= (2)) == 0 || true) &&
 ((setterNames.contains(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C297 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[710]++;
                        addError("msg.dup.obj.lit.prop.strict", propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1516]++;

                    } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[711]++;}
                    setterNames.add(propertyName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1517]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1518]++;
                    break; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[712]++;
                }

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[702]++;}

            // Eat any dangling jsdoc in the property.
            getAndResetJsDoc();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1519]++;
            jsdocNode = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1520]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1521]++;
int CodeCoverConditionCoverageHelper_C298;

            if ((((((CodeCoverConditionCoverageHelper_C298 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C298 |= (2)) == 0 || true) &&
 ((matchToken(Token.COMMA)) && 
  ((CodeCoverConditionCoverageHelper_C298 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[713]++;
                afterComma = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1522]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[714]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1523]++;
                break commaLoop;
            }
        }

        mustMatchToken(Token.RC, "msg.no.brace.prop");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1524]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1525]++;
        ObjectLiteral pn = new ObjectLiteral(pos, ts.tokenEnd - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1526]++;
int CodeCoverConditionCoverageHelper_C299;
        if ((((((CodeCoverConditionCoverageHelper_C299 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C299 |= (2)) == 0 || true) &&
 ((objJsdocNode != null) && 
  ((CodeCoverConditionCoverageHelper_C299 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[715]++;
            pn.setJsDocNode(objJsdocNode);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1527]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[716]++;}
        pn.setElements(elems);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1528]++;
        pn.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1529]++;
        return pn;
    }

    private AstNode objliteralProperty() throws IOException {
        AstNode pname;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1530]++;
        int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1531]++;
        switch(tt) {
          case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[717]++;
              pname = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1532]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1533]++;
              break;

          case Token.STRING:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[718]++;
              pname = createStringLiteral();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1534]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1535]++;
              break;

          case Token.NUMBER:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[719]++;
              pname = new NumberLiteral(
                      ts.tokenBeg, ts.getString(), ts.getNumber());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1536]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1537]++;
              break;

          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[720]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1538]++;
int CodeCoverConditionCoverageHelper_C300;
              if ((((((CodeCoverConditionCoverageHelper_C300 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C300 |= (8)) == 0 || true) &&
 ((compilerEnv.isReservedKeywordAsIdentifier()) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C300 |= (2)) == 0 || true) &&
 ((TokenStream.isKeyword(ts.getString())) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[721]++;
                  // convert keyword to property name, e.g. ({if: 1})
                  pname = createNameNode();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1539]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1540]++;
                  break;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[722]++;}
              reportError("msg.bad.prop");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1541]++;
              return null;
        }

        consumeToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1542]++;
        return pname;
    }

    private ObjectProperty plainProperty(AstNode property, int ptt)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1543]++;
        // Support, e.g., |var {x, y} = o| as destructuring shorthand
        // for |var {x: x, y: y} = o|, as implemented in spidermonkey JS 1.8.
        int tt = peekToken();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1544]++;
int CodeCoverConditionCoverageHelper_C301;
        if ((((((CodeCoverConditionCoverageHelper_C301 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C301 |= (128)) == 0 || true) &&
 ((tt == Token.COMMA) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C301 |= (32)) == 0 || true) &&
 ((tt == Token.RC) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (16)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C301 |= (8)) == 0 || true) &&
 ((ptt == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C301 |= (2)) == 0 || true) &&
 ((compilerEnv.getLanguageVersion() >= Context.VERSION_1_8) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 4) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 4) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[723]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1545]++;
int CodeCoverConditionCoverageHelper_C302;
            if ((((((CodeCoverConditionCoverageHelper_C302 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C302 |= (2)) == 0 || true) &&
 ((inDestructuringAssignment) && 
  ((CodeCoverConditionCoverageHelper_C302 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[725]++;
                reportError("msg.bad.object.init");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1546]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[726]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1547]++;
            AstNode nn = new Name(property.getPosition(), property.getString());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1548]++;
            ObjectProperty pn = new ObjectProperty();
            pn.putProp(Node.DESTRUCTURING_SHORTHAND, Boolean.TRUE);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1549]++;
            pn.setLeftAndRight(property, nn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1550]++;
            return pn;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[724]++;}
        mustMatchToken(Token.COLON, "msg.no.colon.prop");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1551]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1552]++;
        ObjectProperty pn = new ObjectProperty();
        pn.setOperatorPosition(ts.tokenBeg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1553]++;
        pn.setLeftAndRight(property, assignExpr());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1554]++;
        return pn;
    }

    private ObjectProperty getterSetterProperty(int pos, AstNode propName,
                                                boolean isGetter)
        throws IOException
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1555]++;
        FunctionNode fn = function(FunctionNode.FUNCTION_EXPRESSION);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1556]++;
        // We've already parsed the function name, so fn should be anonymous.
        Name name = fn.getFunctionName();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1557]++;
int CodeCoverConditionCoverageHelper_C303;
        if ((((((CodeCoverConditionCoverageHelper_C303 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C303 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C303 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C303 |= (2)) == 0 || true) &&
 ((name.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C303 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[727]++;
            reportError("msg.bad.prop");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1558]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[728]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1559]++;
        ObjectProperty pn = new ObjectProperty(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1560]++;
int CodeCoverConditionCoverageHelper_C304;
        if ((((((CodeCoverConditionCoverageHelper_C304 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C304 |= (2)) == 0 || true) &&
 ((isGetter) && 
  ((CodeCoverConditionCoverageHelper_C304 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[729]++;
            pn.setIsGetter();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1561]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[730]++;
            pn.setIsSetter();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1562]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1563]++;
        int end = getNodeEnd(fn);
        pn.setLeft(propName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1564]++;
        pn.setRight(fn);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1565]++;
        pn.setLength(end - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1566]++;
        return pn;
    }

    private Name createNameNode() {
        return createNameNode(false, Token.NAME);
    }

    /**
     * Create a {@code Name} node using the token info from the
     * last scanned name.  In some cases we need to either synthesize
     * a name node, or we lost the name token information by peeking.
     * If the {@code token} parameter is not {@link Token#NAME}, then
     * we use token info saved in instance vars.
     */
    private Name createNameNode(boolean checkActivation, int token) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1567]++;
        int beg = ts.tokenBeg;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1568]++;
        String s = ts.getString();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1569]++;
        int lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1570]++;
int CodeCoverConditionCoverageHelper_C305;
        if ((((((CodeCoverConditionCoverageHelper_C305 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C305 |= (2)) == 0 || true) &&
 (("".equals(prevNameTokenString)) && 
  ((CodeCoverConditionCoverageHelper_C305 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[731]++;
            beg = prevNameTokenStart;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1571]++;
            s = prevNameTokenString;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1572]++;
            lineno = prevNameTokenLineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1573]++;
            prevNameTokenStart = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1574]++;
            prevNameTokenString = "";
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1575]++;
            prevNameTokenLineno = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1576]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[732]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1577]++;
int CodeCoverConditionCoverageHelper_C306;
        if ((((((CodeCoverConditionCoverageHelper_C306 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C306 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C306 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[733]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1578]++;
int CodeCoverConditionCoverageHelper_C307;
            if ((((((CodeCoverConditionCoverageHelper_C307 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C307 |= (2)) == 0 || true) &&
 ((compilerEnv.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C307 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[735]++;
                s = "";
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1579]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[736]++;
                codeBug();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1580]++;
            }

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[734]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1581]++;
        Name name = new Name(beg, s);
        name.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1582]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1583]++;
int CodeCoverConditionCoverageHelper_C308;
        if ((((((CodeCoverConditionCoverageHelper_C308 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C308 |= (2)) == 0 || true) &&
 ((checkActivation) && 
  ((CodeCoverConditionCoverageHelper_C308 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[737]++;
            checkActivationName(s, token);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1584]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[738]++;}
        return name;
    }

    private StringLiteral createStringLiteral() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1585]++;
        int pos = ts.tokenBeg, end = ts.tokenEnd;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1586]++;
        StringLiteral s = new StringLiteral(pos, end - pos);
        s.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1587]++;
        s.setValue(ts.getString());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1588]++;
        s.setQuoteCharacter(ts.getQuoteChar());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1589]++;
        return s;
    }

    protected void checkActivationName(String name, int token) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1590]++;
int CodeCoverConditionCoverageHelper_C309;
        if ((((((CodeCoverConditionCoverageHelper_C309 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C309 |= (2)) == 0 || true) &&
 ((insideFunction()) && 
  ((CodeCoverConditionCoverageHelper_C309 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[739]++;
            return;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[740]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1591]++;
        boolean activation = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1592]++;
int CodeCoverConditionCoverageHelper_C310;
        if ((((((CodeCoverConditionCoverageHelper_C310 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C310 |= (32)) == 0 || true) &&
 (("arguments".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C310 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C310 |= (8)) == 0 || true) &&
 ((compilerEnv.getActivationNames() != null) && 
  ((CodeCoverConditionCoverageHelper_C310 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C310 |= (2)) == 0 || true) &&
 ((compilerEnv.getActivationNames().contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C310 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 3) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 3) && false))
        {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[741]++;
            activation = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1593]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[742]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1594]++;
int CodeCoverConditionCoverageHelper_C311; if ((((((CodeCoverConditionCoverageHelper_C311 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C311 |= (2)) == 0 || true) &&
 (("length".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C311 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[743]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1595]++;
int CodeCoverConditionCoverageHelper_C312;
            if ((((((CodeCoverConditionCoverageHelper_C312 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C312 |= (8)) == 0 || true) &&
 ((token == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C312 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C312 |= (2)) == 0 || true) &&
 ((compilerEnv.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C312 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 2) && false))
            {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[745]++;
                // Use of "length" in 1.2 requires an activation object.
                activation = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1596]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[746]++;}

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[744]++;}
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1597]++;
int CodeCoverConditionCoverageHelper_C313;
        if ((((((CodeCoverConditionCoverageHelper_C313 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C313 |= (2)) == 0 || true) &&
 ((activation) && 
  ((CodeCoverConditionCoverageHelper_C313 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[747]++;
            setRequiresActivation();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1598]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[748]++;}
    }

    protected void setRequiresActivation() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1599]++;
int CodeCoverConditionCoverageHelper_C314;
        if ((((((CodeCoverConditionCoverageHelper_C314 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C314 |= (2)) == 0 || true) &&
 ((insideFunction()) && 
  ((CodeCoverConditionCoverageHelper_C314 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[749]++;
            ((FunctionNode)currentScriptOrFn).setRequiresActivation();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1600]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[750]++;}
    }

    private void checkCallRequiresActivation(AstNode pn) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1601]++;
int CodeCoverConditionCoverageHelper_C315;
        if ((((((CodeCoverConditionCoverageHelper_C315 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C315 |= (128)) == 0 || true) &&
 ((pn.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C315 |= (32)) == 0 || true) &&
 (("eval".equals(((Name)pn).getIdentifier())) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C315 |= (8)) == 0 || true) &&
 ((pn.getType() == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C315 |= (2)) == 0 || true) &&
 (("eval".equals(((PropertyGet)pn).getProperty().getIdentifier())) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 4) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 4) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[751]++;
            setRequiresActivation();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1602]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[752]++;}
    }

    protected void setIsGenerator() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1603]++;
int CodeCoverConditionCoverageHelper_C316;
        if ((((((CodeCoverConditionCoverageHelper_C316 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C316 |= (2)) == 0 || true) &&
 ((insideFunction()) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[753]++;
            ((FunctionNode)currentScriptOrFn).setIsGenerator();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1604]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[754]++;}
    }

    private void checkBadIncDec(UnaryExpression expr) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1605]++;
        AstNode op = removeParens(expr.getOperand());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1606]++;
        int tt = op.getType();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1607]++;
int CodeCoverConditionCoverageHelper_C317;
        if ((((((CodeCoverConditionCoverageHelper_C317 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C317 |= (512)) == 0 || true) &&
 ((tt == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C317 |= (128)) == 0 || true) &&
 ((tt == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C317 |= (32)) == 0 || true) &&
 ((tt == Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C317 |= (8)) == 0 || true) &&
 ((tt == Token.GET_REF) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C317 |= (2)) == 0 || true) &&
 ((tt == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 5) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 5) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[755]++;
            reportError(expr.getType() == Token.INC
                        ? "msg.bad.incr"
                        : "msg.bad.decr");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1608]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[756]++;}
    }

    private ErrorNode makeErrorNode() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1609]++;
        ErrorNode pn = new ErrorNode(ts.tokenBeg, ts.tokenEnd - ts.tokenBeg);
        pn.setLineno(ts.lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1610]++;
        return pn;
    }

    // Return end of node.  Assumes node does NOT have a parent yet.
    private int nodeEnd(AstNode node) {
        return node.getPosition() + node.getLength();
    }

    private void saveNameTokenData(int pos, String name, int lineno) {
        prevNameTokenStart = pos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1611]++;
        prevNameTokenString = name;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1612]++;
        prevNameTokenLineno = lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1613]++;
    }

    /**
     * Return the file offset of the beginning of the input source line
     * containing the passed position.
     *
     * @param pos an offset into the input source stream.  If the offset
     * is negative, it's converted to 0, and if it's beyond the end of
     * the source buffer, the last source position is used.
     *
     * @return the offset of the beginning of the line containing pos
     * (i.e. 1+ the offset of the first preceding newline).  Returns -1
     * if the {@link CompilerEnvirons} is not set to ide-mode,
     * and {@link #parse(java.io.Reader,String,int)} was used.
     */
    private int lineBeginningFor(int pos) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1614]++;
int CodeCoverConditionCoverageHelper_C318;
        if ((((((CodeCoverConditionCoverageHelper_C318 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C318 |= (2)) == 0 || true) &&
 ((sourceChars == null) && 
  ((CodeCoverConditionCoverageHelper_C318 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[757]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[758]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1615]++;
int CodeCoverConditionCoverageHelper_C319;
        if ((((((CodeCoverConditionCoverageHelper_C319 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C319 |= (2)) == 0 || true) &&
 ((pos <= 0) && 
  ((CodeCoverConditionCoverageHelper_C319 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[759]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[760]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1616]++;
        char[] buf = sourceChars;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1617]++;
int CodeCoverConditionCoverageHelper_C320;
        if ((((((CodeCoverConditionCoverageHelper_C320 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C320 |= (2)) == 0 || true) &&
 ((pos >= buf.length) && 
  ((CodeCoverConditionCoverageHelper_C320 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[761]++;
            pos = buf.length - 1;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1618]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[762]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1619]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[97]++;


int CodeCoverConditionCoverageHelper_C321;
        while ((((((CodeCoverConditionCoverageHelper_C321 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C321 |= (2)) == 0 || true) &&
 ((--pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C321 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 1) && false)) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[97]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[98]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[99]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1620]++;
            char c = buf[pos];
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1621]++;
int CodeCoverConditionCoverageHelper_C322;
            if ((((((CodeCoverConditionCoverageHelper_C322 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C322 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C322 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C322 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C322 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 2) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[763]++;
                return pos + 1;
 // want position after the newline
            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[764]++;}
        }
        return 0;
    }

    private void warnMissingSemi(int pos, int end) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1622]++;
int CodeCoverConditionCoverageHelper_C323;
        // Should probably change this to be a CompilerEnvirons setting,
        // with an enum Never, Always, Permissive, where Permissive means
        // don't warn for 1-line functions like function (s) {return x+2}
        if ((((((CodeCoverConditionCoverageHelper_C323 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C323 |= (2)) == 0 || true) &&
 ((compilerEnv.isStrictMode()) && 
  ((CodeCoverConditionCoverageHelper_C323 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[765]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1623]++;
            int beg = Math.max(pos, lineBeginningFor(end));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1624]++;
int CodeCoverConditionCoverageHelper_C324;
            if ((((((CodeCoverConditionCoverageHelper_C324 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C324 |= (2)) == 0 || true) &&
 ((end == -1) && 
  ((CodeCoverConditionCoverageHelper_C324 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[767]++;
                end = ts.cursor;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1625]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[768]++;}
            addStrictWarning("msg.missing.semi", "",
                             beg, end - beg);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1626]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[766]++;}
    }

    private void warnTrailingComma(int pos, List<?> elems, int commaPos) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1627]++;
int CodeCoverConditionCoverageHelper_C325;
        if ((((((CodeCoverConditionCoverageHelper_C325 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C325 |= (2)) == 0 || true) &&
 ((compilerEnv.getWarnTrailingComma()) && 
  ((CodeCoverConditionCoverageHelper_C325 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[769]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1628]++;
int CodeCoverConditionCoverageHelper_C326;
            // back up from comma to beginning of line or array/objlit
            if ((((((CodeCoverConditionCoverageHelper_C326 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C326 |= (2)) == 0 || true) &&
 ((elems.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C326 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[771]++;
                pos = ((AstNode)elems.get(0)).getPosition();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1629]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[772]++;}
            pos = Math.max(pos, lineBeginningFor(commaPos));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1630]++;
            addWarning("msg.extra.trailing.comma", pos, commaPos - pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1631]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[770]++;}
    }


    private String readFully(Reader reader) throws IOException {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1632]++;
        BufferedReader in = new BufferedReader(reader);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1633]++;
boolean CodeCoverTryBranchHelper_Try24 = false;
        try {
CodeCoverTryBranchHelper_Try24 = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1634]++;
            char[] cbuf = new char[1024];
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1635]++;
            StringBuilder sb = new StringBuilder(1024);
            int bytes_read;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1636]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[100]++;


            while ((bytes_read = in.read(cbuf, 0, 1024)) != -1) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[100]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[101]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[102]++;
}
                sb.append(cbuf, 0, bytes_read);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1637]++;
            }
            return sb.toString();
        } finally {
if ( CodeCoverTryBranchHelper_Try24 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[773]++;
}
            in.close();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1638]++;
        }
    }

    // helps reduce clutter in the already-large function() method
    protected class PerFunctionVariables
    {
        private ScriptNode savedCurrentScriptOrFn;
        private Scope savedCurrentScope;
        private int savedEndFlags;
        private boolean savedInForInit;
        private Map<String,LabeledStatement> savedLabelSet;
        private List<Loop> savedLoopSet;
        private List<Jump> savedLoopAndSwitchSet;

        PerFunctionVariables(FunctionNode fnNode) {
            savedCurrentScriptOrFn = Parser.this.currentScriptOrFn;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1639]++;
            Parser.this.currentScriptOrFn = fnNode;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1640]++;

            savedCurrentScope = Parser.this.currentScope;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1641]++;
            Parser.this.currentScope = fnNode;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1642]++;

            savedLabelSet = Parser.this.labelSet;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1643]++;
            Parser.this.labelSet = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1644]++;

            savedLoopSet = Parser.this.loopSet;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1645]++;
            Parser.this.loopSet = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1646]++;

            savedLoopAndSwitchSet = Parser.this.loopAndSwitchSet;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1647]++;
            Parser.this.loopAndSwitchSet = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1648]++;

            savedEndFlags = Parser.this.endFlags;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1649]++;
            Parser.this.endFlags = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1650]++;

            savedInForInit = Parser.this.inForInit;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1651]++;
            Parser.this.inForInit = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1652]++;
        }

        void restore() {
            Parser.this.currentScriptOrFn = savedCurrentScriptOrFn;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1653]++;
            Parser.this.currentScope = savedCurrentScope;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1654]++;
            Parser.this.labelSet = savedLabelSet;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1655]++;
            Parser.this.loopSet = savedLoopSet;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1656]++;
            Parser.this.loopAndSwitchSet = savedLoopAndSwitchSet;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1657]++;
            Parser.this.endFlags = savedEndFlags;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1658]++;
            Parser.this.inForInit = savedInForInit;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1659]++;
        }
    }

    /**
     * Given a destructuring assignment with a left hand side parsed
     * as an array or object literal and a right hand side expression,
     * rewrite as a series of assignments to the variables defined in
     * left from property accesses to the expression on the right.
     * @param type declaration type: Token.VAR or Token.LET or -1
     * @param left array or object literal containing NAME nodes for
     *        variables to assign
     * @param right expression to assign from
     * @return expression that performs a series of assignments to
     *         the variables defined in left
     */
    Node createDestructuringAssignment(int type, Node left, Node right)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1660]++;
        String tempName = currentScriptOrFn.getNextTempName();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1661]++;
        Node result = destructuringAssignmentHelper(type, left, right,
            tempName);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1662]++;
        Node comma = result.getLastChild();
        comma.addChildToBack(createName(tempName));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1663]++;
        return result;
    }

    Node destructuringAssignmentHelper(int variableType, Node left,
                                       Node right, String tempName)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1664]++;
        Scope result = createScopeNode(Token.LETEXPR, left.getLineno());
        result.addChildToFront(new Node(Token.LET,
            createName(Token.NAME, tempName, right)));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1665]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1666]++;
boolean CodeCoverTryBranchHelper_Try25 = false;
        try {
CodeCoverTryBranchHelper_Try25 = true;
            pushScope(result);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1667]++;
            defineSymbol(Token.LET, tempName, true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1668]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try25 ) {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[774]++;
}
            popScope();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1669]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1670]++;
        Node comma = new Node(Token.COMMA);
        result.addChildToBack(comma);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1671]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1672]++;
        List<String> destructuringNames = new ArrayList<String>();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1673]++;
        boolean empty = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1674]++;
        switch (left.getType()) {
          case Token.ARRAYLIT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[775]++;
              empty = destructuringArray((ArrayLiteral)left,
                                         variableType, tempName, comma,
                                         destructuringNames);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1675]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1676]++;
              break;
          case Token.OBJECTLIT:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[776]++;
              empty = destructuringObject((ObjectLiteral)left,
                                          variableType, tempName, comma,
                                          destructuringNames);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1677]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1678]++;
              break;
          case Token.GETPROP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[777]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[778]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1679]++;
              switch (variableType) {
                  case Token.CONST:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[779]++;
                  case Token.LET:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[780]++;
                  case Token.VAR:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[781]++;
                      reportError("msg.bad.assign.left");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1680]++; default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[782]++;
              }
              comma.addChildToBack(simpleAssignment(left, createName(tempName)));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1681]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1682]++;
              break;
          default:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[783]++;
              reportError("msg.bad.assign.left");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1683]++;
        }
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1684]++;
int CodeCoverConditionCoverageHelper_C328;
        if ((((((CodeCoverConditionCoverageHelper_C328 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C328 |= (2)) == 0 || true) &&
 ((empty) && 
  ((CodeCoverConditionCoverageHelper_C328 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[784]++;
            // Don't want a COMMA node with no children. Just add a zero.
            comma.addChildToBack(createNumber(0));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1685]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[785]++;}
        result.putProp(Node.DESTRUCTURING_NAMES, destructuringNames);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1686]++;
        return result;
    }

    boolean destructuringArray(ArrayLiteral array,
                               int variableType,
                               String tempName,
                               Node parent,
                               List<String> destructuringNames)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1687]++;
        boolean empty = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1688]++;
        int setOp = variableType == Token.CONST
            ? Token.SETCONST : Token.SETNAME;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1689]++;
        int index = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1690]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[103]++;


        for (AstNode n : array.getElements()) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[103]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[104]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[105]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1691]++;
int CodeCoverConditionCoverageHelper_C329;
            if ((((((CodeCoverConditionCoverageHelper_C329 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C329 |= (2)) == 0 || true) &&
 ((n.getType() == Token.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C329 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[786]++;
                index++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1692]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1693]++;
                continue;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[787]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1694]++;
            Node rightElem = new Node(Token.GETELEM,
                                      createName(tempName),
                                      createNumber(index));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1695]++;
int CodeCoverConditionCoverageHelper_C330;
            if ((((((CodeCoverConditionCoverageHelper_C330 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C330 |= (2)) == 0 || true) &&
 ((n.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C330 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[788]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1696]++;
                String name = n.getString();
                parent.addChildToBack(new Node(setOp,
                                              createName(Token.BINDNAME,
                                                         name, null),
                                              rightElem));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1697]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1698]++;
int CodeCoverConditionCoverageHelper_C331;
                if ((((((CodeCoverConditionCoverageHelper_C331 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C331 |= (2)) == 0 || true) &&
 ((variableType != -1) && 
  ((CodeCoverConditionCoverageHelper_C331 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[790]++;
                    defineSymbol(variableType, name, true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1699]++;
                    destructuringNames.add(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1700]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[791]++;}

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[789]++;
                parent.addChildToBack
                    (destructuringAssignmentHelper
                     (variableType, n,
                      rightElem,
                      currentScriptOrFn.getNextTempName()));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1701]++;
            }
            index++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1702]++;
            empty = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1703]++;
        }
        return empty;
    }

    boolean destructuringObject(ObjectLiteral node,
                                int variableType,
                                String tempName,
                                Node parent,
                                List<String> destructuringNames)
    {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1704]++;
        boolean empty = true;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1705]++;
        int setOp = variableType == Token.CONST
            ? Token.SETCONST : Token.SETNAME;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1706]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[106]++;



        for (ObjectProperty prop : node.getElements()) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[106]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[107]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[108]++;
}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1707]++;
            int lineno = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1708]++;
int CodeCoverConditionCoverageHelper_C332;
            // This function is sometimes called from the IRFactory when
            // when executing regression tests, and in those cases the
            // tokenStream isn't set.  Deal with it.
            if ((((((CodeCoverConditionCoverageHelper_C332 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C332 |= (2)) == 0 || true) &&
 ((ts != null) && 
  ((CodeCoverConditionCoverageHelper_C332 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[792]++;
              lineno = ts.lineno;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1709]++;

            } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[793]++;}
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1710]++;
            AstNode id = prop.getLeft();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1711]++;
            Node rightElem = null;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1712]++;
int CodeCoverConditionCoverageHelper_C333;
            if ((((((CodeCoverConditionCoverageHelper_C333 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C333 |= (2)) == 0 || true) &&
 ((id instanceof Name) && 
  ((CodeCoverConditionCoverageHelper_C333 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[794]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1713]++;
                Node s = Node.newString(((Name)id).getIdentifier());
                rightElem = new Node(Token.GETPROP, createName(tempName), s);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1714]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[795]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1715]++;
int CodeCoverConditionCoverageHelper_C334; if ((((((CodeCoverConditionCoverageHelper_C334 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C334 |= (2)) == 0 || true) &&
 ((id instanceof StringLiteral) && 
  ((CodeCoverConditionCoverageHelper_C334 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[796]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1716]++;
                Node s = Node.newString(((StringLiteral)id).getValue());
                rightElem = new Node(Token.GETPROP, createName(tempName), s);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1717]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[797]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1718]++;
int CodeCoverConditionCoverageHelper_C335; if ((((((CodeCoverConditionCoverageHelper_C335 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C335 |= (2)) == 0 || true) &&
 ((id instanceof NumberLiteral) && 
  ((CodeCoverConditionCoverageHelper_C335 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[798]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1719]++;
                Node s = createNumber((int)((NumberLiteral)id).getNumber());
                rightElem = new Node(Token.GETELEM, createName(tempName), s);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1720]++;

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[799]++;
                throw codeBug();
            }
}
}
            rightElem.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1721]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1722]++;
            AstNode value = prop.getRight();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1723]++;
int CodeCoverConditionCoverageHelper_C336;
            if ((((((CodeCoverConditionCoverageHelper_C336 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C336 |= (2)) == 0 || true) &&
 ((value.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C336 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[800]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1724]++;
                String name = ((Name)value).getIdentifier();
                parent.addChildToBack(new Node(setOp,
                                              createName(Token.BINDNAME,
                                                         name, null),
                                              rightElem));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1725]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1726]++;
int CodeCoverConditionCoverageHelper_C337;
                if ((((((CodeCoverConditionCoverageHelper_C337 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C337 |= (2)) == 0 || true) &&
 ((variableType != -1) && 
  ((CodeCoverConditionCoverageHelper_C337 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[802]++;
                    defineSymbol(variableType, name, true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1727]++;
                    destructuringNames.add(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1728]++;

                } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[803]++;}

            } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[801]++;
                parent.addChildToBack
                    (destructuringAssignmentHelper
                     (variableType, value, rightElem,
                      currentScriptOrFn.getNextTempName()));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1729]++;
            }
            empty = false;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1730]++;
        }
        return empty;
    }

    protected Node createName(String name) {
        checkActivationName(name, Token.NAME);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1731]++;
        return Node.newString(Token.NAME, name);
    }

    protected Node createName(int type, String name, Node child) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1732]++;
        Node result = createName(name);
        result.setType(type);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1733]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1734]++;
int CodeCoverConditionCoverageHelper_C338;
        if ((((((CodeCoverConditionCoverageHelper_C338 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C338 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C338 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[804]++;
            result.addChildToBack(child);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1735]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[805]++;}
        return result;
    }

    protected Node createNumber(double number) {
        return Node.newNumber(number);
    }

    /**
     * Create a node that can be used to hold lexically scoped variable
     * definitions (via let declarations).
     *
     * @param token the token of the node to create
     * @param lineno line number of source
     * @return the created node
     */
    protected Scope createScopeNode(int token, int lineno) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1736]++;
        Scope scope =new Scope();
        scope.setType(token);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1737]++;
        scope.setLineno(lineno);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1738]++;
        return scope;
    }

    // Quickie tutorial for some of the interpreter bytecodes.
    //
    // GETPROP - for normal foo.bar prop access; right side is a name
    // GETELEM - for normal foo[bar] element access; rhs is an expr
    // SETPROP - for assignment when left side is a GETPROP
    // SETELEM - for assignment when left side is a GETELEM
    // DELPROP - used for delete foo.bar or foo[bar]
    //
    // GET_REF, SET_REF, DEL_REF - in general, these mean you're using
    // get/set/delete on a right-hand side expression (possibly with no
    // explicit left-hand side) that doesn't use the normal JavaScript
    // Object (i.e. ScriptableObject) get/set/delete functions, but wants
    // to provide its own versions instead.  It will ultimately implement
    // Ref, and currently SpecialRef (for __proto__ etc.) and XmlName
    // (for E4X XML objects) are the only implementations.  The runtime
    // notices these bytecodes and delegates get/set/delete to the object.
    //
    // BINDNAME:  used in assignments.  LHS is evaluated first to get a
    // specific object containing the property ("binding" the property
    // to the object) so that it's always the same object, regardless of
    // side effects in the RHS.

    protected Node simpleAssignment(Node left, Node right) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1739]++;
        int nodeType = left.getType();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1740]++;
        switch (nodeType) {
          case Token.NAME:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[806]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1741]++;
int CodeCoverConditionCoverageHelper_C339;
              if ((((((CodeCoverConditionCoverageHelper_C339 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C339 |= (8)) == 0 || true) &&
 ((inUseStrictDirective) && 
  ((CodeCoverConditionCoverageHelper_C339 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C339 |= (2)) == 0 || true) &&
 (("eval".equals(((Name) left).getIdentifier())) && 
  ((CodeCoverConditionCoverageHelper_C339 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 2) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 2) && false))
              {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[807]++;
                  reportError("msg.bad.id.strict",
                              ((Name) left).getIdentifier());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1742]++;

              } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[808]++;}
              left.setType(Token.BINDNAME);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1743]++;
              return new Node(Token.SETNAME, left, right);

          case Token.GETPROP:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[809]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[810]++; {
              Node obj, id;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1744]++;
int CodeCoverConditionCoverageHelper_C340;
              // If it's a PropertyGet or ElementGet, we're in the parse pass.
              // We could alternately have PropertyGet and ElementGet
              // override getFirstChild/getLastChild and return the appropriate
              // field, but that seems just as ugly as this casting.
              if ((((((CodeCoverConditionCoverageHelper_C340 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C340 |= (2)) == 0 || true) &&
 ((left instanceof PropertyGet) && 
  ((CodeCoverConditionCoverageHelper_C340 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[811]++;
                  obj = ((PropertyGet)left).getTarget();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1745]++;
                  id = ((PropertyGet)left).getProperty();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1746]++;

              } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[812]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1747]++;
int CodeCoverConditionCoverageHelper_C341; if ((((((CodeCoverConditionCoverageHelper_C341 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C341 |= (2)) == 0 || true) &&
 ((left instanceof ElementGet) && 
  ((CodeCoverConditionCoverageHelper_C341 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[813]++;
                  obj = ((ElementGet)left).getTarget();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1748]++;
                  id = ((ElementGet)left).getElement();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1749]++;

              } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[814]++;
                  // This branch is called during IRFactory transform pass.
                  obj = left.getFirstChild();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1750]++;
                  id = left.getLastChild();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1751]++;
              }
}
              int type;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1752]++;
int CodeCoverConditionCoverageHelper_C342;
              if ((((((CodeCoverConditionCoverageHelper_C342 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C342 |= (2)) == 0 || true) &&
 ((nodeType == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C342 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[815]++;
                  type = Token.SETPROP;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1753]++;
                  // TODO(stevey) - see https://bugzilla.mozilla.org/show_bug.cgi?id=492036
                  // The new AST code generates NAME tokens for GETPROP ids where the old parser
                  // generated STRING nodes. If we don't set the type to STRING below, this will
                  // cause java.lang.VerifyError in codegen for code like
                  // "var obj={p:3};[obj.p]=[9];"
                  id.setType(Token.STRING);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1754]++;

              } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[816]++;
                  type = Token.SETELEM;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1755]++;
              }
              return new Node(type, obj, id, right);
          }
          case Token.GET_REF:
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[817]++; {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1756]++;
              Node ref = left.getFirstChild();
              checkMutableReference(ref);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1757]++;
              return new Node(Token.SET_REF, ref, right);
          } default : CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[818]++;
        }

        throw codeBug();
    }

    protected void checkMutableReference(Node n) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1758]++;
        int memberTypeFlags = n.getIntProp(Node.MEMBER_TYPE_PROP, 0);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1759]++;
int CodeCoverConditionCoverageHelper_C343;
        if ((((((CodeCoverConditionCoverageHelper_C343 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C343 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.DESCENDANTS_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C343 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[819]++;
            reportError("msg.bad.assign.left");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1760]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[820]++;}
    }

    // remove any ParenthesizedExpression wrappers
    protected AstNode removeParens(AstNode node) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1761]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[109]++;


int CodeCoverConditionCoverageHelper_C344;
        while ((((((CodeCoverConditionCoverageHelper_C344 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C344 |= (2)) == 0 || true) &&
 ((node instanceof ParenthesizedExpression) && 
  ((CodeCoverConditionCoverageHelper_C344 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) && false)) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[109]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[110]--;
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.loops[111]++;
}
            node = ((ParenthesizedExpression)node).getExpression();
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1762]++;
        }
        return node;
    }

    void markDestructuring(AstNode node) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1763]++;
int CodeCoverConditionCoverageHelper_C345;
        if ((((((CodeCoverConditionCoverageHelper_C345 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C345 |= (2)) == 0 || true) &&
 ((node instanceof DestructuringForm) && 
  ((CodeCoverConditionCoverageHelper_C345 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[821]++;
            ((DestructuringForm)node).setIsDestructuring(true);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1764]++;

        } else {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[822]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1765]++;
int CodeCoverConditionCoverageHelper_C346; if ((((((CodeCoverConditionCoverageHelper_C346 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C346 |= (2)) == 0 || true) &&
 ((node instanceof ParenthesizedExpression) && 
  ((CodeCoverConditionCoverageHelper_C346 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[823]++;
            markDestructuring(((ParenthesizedExpression)node).getExpression());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.statements[1766]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9.branches[824]++;}
}
    }

    // throw a failed-assertion with some helpful debugging info
    private RuntimeException codeBug()
        throws RuntimeException
    {
        throw Kit.codeBug("ts.cursor=" + ts.cursor
                          + ", ts.tokenBeg=" + ts.tokenBeg
                          + ", currentToken=" + currentToken);
    }
}

class CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9 ());
  }
    public static long[] statements = new long[1767];
    public static long[] branches = new long[825];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[347];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Parser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,2,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,3,1,2,1,1,2,0,1,1,1,1,2,0,2,1,2,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,0,2,1,1,2,1,1,2,1,1,1,2,1,1,1,3,3,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,0,0,2,0,1,2,1,2,0,1,1,1,1,1,1,2,1,1,1,0,1,1,1,2,1,1,1,3,1,1,2,1,3,1,2,1,1,1,2,1,1,0,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3,1,1,1,2,2,1,1,1,1,2,3,1,2,1,1,1,1,1,1,3,1,2,1,1,3,1,3,1,1,1,1,2,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 346; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[112];

  public CodeCoverCoverageCounter$1wcjkiz20v4sksnrmuw2k2smss2rs4ti9 () {
    super("org.mozilla.javascript.RHINO-SRC-Parser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1766; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 824; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 346; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 111; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Parser.java");
      for (int i = 1; i <= 1766; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 824; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 346; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 37; i++) {
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

