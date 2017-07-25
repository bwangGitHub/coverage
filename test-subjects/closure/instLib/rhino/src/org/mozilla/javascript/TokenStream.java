/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.*;

/**
 * This class implements the JavaScript scanner.
 *
 * It is based on the C source files jsscan.c and jsscan.h
 * in the jsref package.
 *
 * @see org.mozilla.javascript.Parser
 *
 */

class TokenStream
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.ping();
  }

    /*
     * For chars - because we need something out-of-range
     * to check.  (And checking EOF by exception is annoying.)
     * Note distinction from EOF token type!
     */
    private final static int
        EOF_CHAR = -1;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[1]++;
  }

    private final static char BYTE_ORDER_MARK = '\uFEFF';
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[2]++;
  }

    TokenStream(Parser parser, Reader sourceReader, String sourceString,
                int lineno)
    {
        this.parser = parser;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[3]++;
        this.lineno = lineno;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[4]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sourceReader != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[1]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[3]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[7]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[4]++;}
            this.sourceReader = sourceReader;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[8]++;
            this.sourceBuffer = new char[512];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[9]++;
            this.sourceEnd = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[10]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[2]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sourceString == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[5]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[12]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[6]++;}
            this.sourceString = sourceString;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[13]++;
            this.sourceEnd = sourceString.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[14]++;
        }
        this.sourceCursor = this.cursor = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[15]++;
    }

    /* This function uses the cached op, string and number fields in
     * TokenStream; if getToken has been called since the passed token
     * was scanned, the op or string printed may be incorrect.
     */
    String tokenToString(int token)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[7]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[17]++;
            String name = Token.name(token);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[18]++;

            switch (token) {
            case Token.STRING:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[9]++;
            case Token.REGEXP:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[10]++;
            case Token.NAME:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[11]++;
                return name + " `" + this.string + "'";

            case Token.NUMBER:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[12]++;
                return "NUMBER " + this.number; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[13]++;
            }

            return name;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[8]++;}
        return "";
    }

    static boolean isKeyword(String s)
    {
        return Token.EOF != stringToKeyword(s);
    }

    private static int stringToKeyword(String name)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[19]++;
// #string_id_map#
// The following assumes that Token.EOF == 0
        final int
            Id_break         = Token.BREAK,
            Id_case          = Token.CASE,
            Id_continue      = Token.CONTINUE,
            Id_default       = Token.DEFAULT,
            Id_delete        = Token.DELPROP,
            Id_do            = Token.DO,
            Id_else          = Token.ELSE,
            Id_export        = Token.RESERVED,
            Id_false         = Token.FALSE,
            Id_for           = Token.FOR,
            Id_function      = Token.FUNCTION,
            Id_if            = Token.IF,
            Id_in            = Token.IN,
            Id_let           = Token.LET,  // reserved ES5 strict
            Id_new           = Token.NEW,
            Id_null          = Token.NULL,
            Id_return        = Token.RETURN,
            Id_switch        = Token.SWITCH,
            Id_this          = Token.THIS,
            Id_true          = Token.TRUE,
            Id_typeof        = Token.TYPEOF,
            Id_var           = Token.VAR,
            Id_void          = Token.VOID,
            Id_while         = Token.WHILE,
            Id_with          = Token.WITH,
            Id_yield         = Token.YIELD,  // reserved ES5 strict

            // the following are #ifdef RESERVE_JAVA_KEYWORDS in jsscan.c
            Id_abstract      = Token.RESERVED,  // ES3 only
            Id_boolean       = Token.RESERVED,  // ES3 only
            Id_byte          = Token.RESERVED,  // ES3 only
            Id_catch         = Token.CATCH,
            Id_char          = Token.RESERVED,  // ES3 only
            Id_class         = Token.RESERVED,
            Id_const         = Token.CONST,     // reserved
            Id_debugger      = Token.DEBUGGER,
            Id_double        = Token.RESERVED,  // ES3 only
            Id_enum          = Token.RESERVED,
            Id_extends       = Token.RESERVED,
            Id_final         = Token.RESERVED,  // ES3 only
            Id_finally       = Token.FINALLY,
            Id_float         = Token.RESERVED,  // ES3 only
            Id_goto          = Token.RESERVED,  // ES3 only
            Id_implements    = Token.RESERVED,  // ES3, ES5 strict
            Id_import        = Token.RESERVED,
            Id_instanceof    = Token.INSTANCEOF,
            Id_int           = Token.RESERVED,  // ES3
            Id_interface     = Token.RESERVED,  // ES3, ES5 strict
            Id_long          = Token.RESERVED,  // ES3 only
            Id_native        = Token.RESERVED,  // ES3 only
            Id_package       = Token.RESERVED,  // ES3, ES5 strict
            Id_private       = Token.RESERVED,  // ES3, ES5 strict
            Id_protected     = Token.RESERVED,  // ES3, ES5 strict
            Id_public        = Token.RESERVED,  // ES3, ES5 strict
            Id_short         = Token.RESERVED,  // ES3 only
            Id_static        = Token.RESERVED,  // ES3, ES5 strict
            Id_super         = Token.RESERVED,
            Id_synchronized  = Token.RESERVED,  // ES3 only
            Id_throw         = Token.THROW,
            Id_throws        = Token.RESERVED,  // ES3 only
            Id_transient     = Token.RESERVED,  // ES3 only
            Id_try           = Token.TRY,
            Id_volatile      = Token.RESERVED;  // ES3 only

        int id;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[20]++;
        String s = name;
// #generated# Last update: 2007-04-18 13:53:30 PDT
        L0: { id = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[21]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[22]++; String X = null; int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[23]++;
            L: switch (s.length()) {
            case 2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[14]++; c=s.charAt(1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[24]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[15]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[26]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='i') && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[17]++;id=Id_if;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[27]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[28]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[18]++;}
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[16]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[29]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[19]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[30]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='i') && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[21]++;id=Id_in;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[31]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[32]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[22]++;}
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[20]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[33]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[23]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[34]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='d') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[25]++;id=Id_do;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[35]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[36]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[26]++;}
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[24]++;}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[37]++;
                break L;
            case 3:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[27]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[38]++; switch (s.charAt(0)) {
                case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[28]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[39]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='r') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='o') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[29]++;id=Id_for;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[40]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[41]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[30]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[42]++; break L;
                case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[31]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[43]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='t') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='n') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[32]++;id=Id_int;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[44]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[45]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[33]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[46]++; break L;
                case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[34]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[47]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='t') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='e') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[35]++;id=Id_let;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[48]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[49]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[36]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[50]++; break L;
                case 'n':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[51]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='w') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='e') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[38]++;id=Id_new;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[52]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[53]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[39]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[54]++; break L;
                case 't':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[40]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[55]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='y') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='r') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[41]++;id=Id_try;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[56]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[57]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[42]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[58]++; break L;
                case 'v':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[43]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[59]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='r') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[44]++;id=Id_var;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[60]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[61]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[45]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[62]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[46]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[63]++; break L;
            case 4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[47]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[64]++; switch (s.charAt(0)) {
                case 'b':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[48]++; X="byte";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[65]++;id=Id_byte;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[66]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[67]++; break L;
                case 'c':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[49]++; c=s.charAt(3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[68]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;
                    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[50]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[70]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='s') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[52]++;id=Id_case;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[71]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[72]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[53]++;}
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[51]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[73]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[54]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[74]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='a') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='h') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[56]++;id=Id_char;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[75]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[76]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[57]++;}
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[55]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[77]++;
                    break L;
                case 'e':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[58]++; c=s.charAt(3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[78]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[59]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[80]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='s') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='l') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[61]++;id=Id_else;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[81]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[82]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[62]++;}
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[60]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[83]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((c=='m') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[63]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[84]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='u') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='n') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[65]++;id=Id_enum;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[85]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[86]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[66]++;}
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[64]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[87]++;
                    break L;
                case 'g':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[67]++; X="goto";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[88]++;id=Id_goto;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[89]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[90]++; break L;
                case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[68]++; X="long";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[91]++;id=Id_long;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[92]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[93]++; break L;
                case 'n':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[69]++; X="null";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[94]++;id=Id_null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[95]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[96]++; break L;
                case 't':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[70]++; c=s.charAt(3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[97]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[98]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[71]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[99]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='u') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='r') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[73]++;id=Id_true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[100]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[101]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[74]++;}
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[72]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[102]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[75]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[103]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='i') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='h') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[77]++;id=Id_this;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[104]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[105]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[78]++;}
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[76]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[106]++;
                    break L;
                case 'v':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[79]++; X="void";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[107]++;id=Id_void;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[108]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[109]++; break L;
                case 'w':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[80]++; X="with";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[110]++;id=Id_with;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[111]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[112]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[81]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[113]++; break L;
            case 5:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[82]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[114]++; switch (s.charAt(2)) {
                case 'a':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[83]++; X="class";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[115]++;id=Id_class;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[116]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[117]++; break L;
                case 'e':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[84]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[118]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[119]++;
int CodeCoverConditionCoverageHelper_C29;
                    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c=='b') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[85]++; X="break";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[120]++;id=Id_break;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[121]++;
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[86]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[122]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c=='y') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[87]++; X="yield";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[123]++;id=Id_yield;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[124]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[88]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[125]++;
                    break L;
                case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[89]++; X="while";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[126]++;id=Id_while;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[127]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[128]++; break L;
                case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[90]++; X="false";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[129]++;id=Id_false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[130]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[131]++; break L;
                case 'n':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[91]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[132]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[133]++;
int CodeCoverConditionCoverageHelper_C31;
                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[92]++; X="const";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[134]++;id=Id_const;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[135]++;
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[93]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[136]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[94]++; X="final";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[137]++;id=Id_final;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[138]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[95]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[139]++;
                    break L;
                case 'o':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[96]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[140]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[141]++;
int CodeCoverConditionCoverageHelper_C33;
                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[97]++; X="float";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[142]++;id=Id_float;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[143]++;
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[98]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[144]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[99]++; X="short";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[145]++;id=Id_short;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[146]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[100]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[147]++;
                    break L;
                case 'p':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[101]++; X="super";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[148]++;id=Id_super;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[149]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[150]++; break L;
                case 'r':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[102]++; X="throw";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[151]++;id=Id_throw;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[152]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[153]++; break L;
                case 't':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[103]++; X="catch";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[154]++;id=Id_catch;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[155]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[156]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[104]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[157]++; break L;
            case 6:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[105]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[158]++; switch (s.charAt(1)) {
                case 'a':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[106]++; X="native";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[159]++;id=Id_native;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[160]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[161]++; break L;
                case 'e':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[107]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[162]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[163]++;
int CodeCoverConditionCoverageHelper_C35;
                    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((c=='d') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[108]++; X="delete";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[164]++;id=Id_delete;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[165]++;
 }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[109]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[166]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[110]++; X="return";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[167]++;id=Id_return;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[168]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[111]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[169]++;
                    break L;
                case 'h':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[112]++; X="throws";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[170]++;id=Id_throws;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[171]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[172]++; break L;
                case 'm':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[113]++; X="import";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[173]++;id=Id_import;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[174]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[175]++; break L;
                case 'o':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[114]++; X="double";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[176]++;id=Id_double;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[177]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[178]++; break L;
                case 't':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[115]++; X="static";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[179]++;id=Id_static;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[180]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[181]++; break L;
                case 'u':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[116]++; X="public";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[182]++;id=Id_public;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[183]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[184]++; break L;
                case 'w':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[117]++; X="switch";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[185]++;id=Id_switch;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[186]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[187]++; break L;
                case 'x':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[118]++; X="export";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[188]++;id=Id_export;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[189]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[190]++; break L;
                case 'y':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[119]++; X="typeof";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[191]++;id=Id_typeof;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[192]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[193]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[120]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[194]++; break L;
            case 7:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[121]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[195]++; switch (s.charAt(1)) {
                case 'a':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[122]++; X="package";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[196]++;id=Id_package;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[197]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[198]++; break L;
                case 'e':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[123]++; X="default";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[199]++;id=Id_default;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[200]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[201]++; break L;
                case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[124]++; X="finally";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[202]++;id=Id_finally;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[203]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[204]++; break L;
                case 'o':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[125]++; X="boolean";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[205]++;id=Id_boolean;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[206]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[207]++; break L;
                case 'r':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[126]++; X="private";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[208]++;id=Id_private;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[209]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[210]++; break L;
                case 'x':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[127]++; X="extends";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[211]++;id=Id_extends;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[212]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[213]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[128]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[214]++; break L;
            case 8:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[129]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[215]++; switch (s.charAt(0)) {
                case 'a':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[130]++; X="abstract";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[216]++;id=Id_abstract;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[217]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[218]++; break L;
                case 'c':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[131]++; X="continue";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[219]++;id=Id_continue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[220]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[221]++; break L;
                case 'd':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[132]++; X="debugger";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[222]++;id=Id_debugger;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[223]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[224]++; break L;
                case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[133]++; X="function";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[225]++;id=Id_function;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[226]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[227]++; break L;
                case 'v':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[134]++; X="volatile";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[228]++;id=Id_volatile;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[229]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[230]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[135]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[231]++; break L;
            case 9:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[136]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[232]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[233]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[137]++; X="interface";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[234]++;id=Id_interface;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[235]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[138]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[236]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[139]++; X="protected";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[237]++;id=Id_protected;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[238]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[140]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[239]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[141]++; X="transient";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[240]++;id=Id_transient;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[241]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[142]++;}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[242]++;
                break L;
            case 10:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[143]++; c=s.charAt(1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[243]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[244]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c=='m') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[144]++; X="implements";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[245]++;id=Id_implements;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[246]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[145]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[247]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[146]++; X="instanceof";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[248]++;id=Id_instanceof;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[249]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[147]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[250]++;
                break L;
            case 12:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[148]++; X="synchronized";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[251]++;id=Id_synchronized;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[252]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[253]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[149]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[254]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[150]++; id = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[255]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[151]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[256]++;
int CodeCoverConditionCoverageHelper_C43;
// #/generated#
// #/string_id_map#
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[152]++; return Token.EOF;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[153]++;}
        return id & 0xff;
    }

    final String getSourceString() { return sourceString; }

    final int getLineno() { return lineno; }

    final String getString() { return string; }

    final char getQuoteChar() {
        return (char) quoteChar;
    }

    final double getNumber() { return number; }
    final boolean isNumberOctal() { return isOctal; }

    final boolean eof() { return hitEOF; }

    final int getToken() throws IOException
    {
        int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[257]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[1]++;



    retry:
        for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[3]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[258]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[4]++;


            // Eat whitespace, possibly sensitive to newlines.
            for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[6]++;
}
                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[259]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[260]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((c == EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[154]++;
                    tokenBeg = cursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[261]++;
                    tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[262]++;
                    return Token.EOF;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[155]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[263]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[156]++;
                    dirtyLine = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[264]++;
                    tokenBeg = cursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[265]++;
                    tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[266]++;
                    return Token.EOL;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[157]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[267]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isJSSpace(c)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[158]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[268]++;
int CodeCoverConditionCoverageHelper_C49;
                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((c != '-') && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[160]++;
                        dirtyLine = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[269]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[161]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[270]++;
                    break;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[159]++;}
}
}
            }

            // Assume the token will be 1 char - fixed up below.
            tokenBeg = cursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[271]++;
            tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[272]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[273]++;
int CodeCoverConditionCoverageHelper_C50;

            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((c == '@') && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[162]++; return Token.XMLATTR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[163]++;}

            // identifier/keyword/instanceof?
            // watch out for starting with a <backslash>
            boolean identifierStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[274]++;
            boolean isUnicodeEscapeStart = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[275]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[164]++;
                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[276]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[277]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((c == 'u') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[166]++;
                    identifierStart = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[278]++;
                    isUnicodeEscapeStart = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[279]++;
                    stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[280]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[167]++;
                    identifierStart = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[281]++;
                    ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[282]++;
                    c = '\\';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[283]++;
                }

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[165]++;
                identifierStart = Character.isJavaIdentifierStart((char)c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[284]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[285]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((identifierStart) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[168]++;
                    stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[286]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[287]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[169]++;}
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[288]++;
int CodeCoverConditionCoverageHelper_C54;

            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((identifierStart) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[170]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[289]++;
                boolean containsEscape = isUnicodeEscapeStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[290]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[7]++;


                for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[9]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[291]++;
int CodeCoverConditionCoverageHelper_C56;
                    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isUnicodeEscapeStart) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[172]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[292]++;
                        // strictly speaking we should probably push-back
                        // all the bad characters if the <backslash>uXXXX
                        // sequence is malformed. But since there isn't a
                        // correct context(is there?) for a bad Unicode
                        // escape sequence in an identifier, we can report
                        // an error here.
                        int escapeVal = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[293]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[10]++;


int CodeCoverConditionCoverageHelper_C57;
                        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i != 4) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[10]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[11]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[12]++;
}
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[294]++;
                            escapeVal = Kit.xDigitToInt(c, escapeVal);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[295]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[296]++;
int CodeCoverConditionCoverageHelper_C58;
                            // Next check takes care about c < 0 and bad escape
                            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((escapeVal < 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[174]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[297]++; break;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[175]++;}
                        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[298]++;
int CodeCoverConditionCoverageHelper_C59;
                        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((escapeVal < 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[176]++;
                            parser.addError("msg.invalid.escape");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[299]++;
                            return Token.ERROR;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[177]++;}
                        addToString(escapeVal);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[300]++;
                        isUnicodeEscapeStart = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[301]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[173]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[302]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[303]++;
int CodeCoverConditionCoverageHelper_C60;
                        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[178]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[304]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[305]++;
int CodeCoverConditionCoverageHelper_C61;
                            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((c == 'u') && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[180]++;
                                isUnicodeEscapeStart = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[306]++;
                                containsEscape = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[307]++;

                            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[181]++;
                                parser.addError("msg.illegal.character");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[308]++;
                                return Token.ERROR;
                            }

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[179]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[309]++;
int CodeCoverConditionCoverageHelper_C62;
                            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (32)) == 0 || true) &&
 ((c == EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((c == BYTE_ORDER_MARK) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart((char)c)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) && false))
                            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[182]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[310]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[183]++;}
                            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[311]++;
                        }
                    }
                }
                ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[312]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[313]++;

                String str = getStringFromBuffer();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[314]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((containsEscape) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[184]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[315]++;
                    // OPT we shouldn't have to make a string (object!) to
                    // check if it's a keyword.

                    // Return the corresponding token if it's a keyword
                    int result = stringToKeyword(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[316]++;
int CodeCoverConditionCoverageHelper_C64;
                    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((result != Token.EOF) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[186]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[317]++;
int CodeCoverConditionCoverageHelper_C65;
                        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C65 |= (32)) == 0 || true) &&
 ((result == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((result == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((parser.compilerEnv.getLanguageVersion()
                               < Context.VERSION_1_7) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 3) && false))
                        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[188]++;
                            // LET and YIELD are tokens only in 1.7 and later
                            string = result == Token.LET ? "let" : "yield";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[318]++;
                            result = Token.NAME;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[319]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[189]++;}
                        // Save the string in case we need to use in
                        // object literal definitions.
                        this.string = (String)allStrings.intern(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[320]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[321]++;
int CodeCoverConditionCoverageHelper_C66;
                        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((result != Token.RESERVED) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[190]++;
                            return result;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[191]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[322]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((parser.compilerEnv.
                                        isReservedKeywordAsIdentifier()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false))
                        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[192]++;
                            return result;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[193]++;}
}

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[187]++;}

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[185]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[323]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isKeyword(str)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[194]++;
                    // If a string contains unicodes, and converted to a keyword,
                    // we convert the last character back to unicode
                    str = convertLastCharToHex(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[324]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[195]++;}
}
                this.string = (String)allStrings.intern(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[325]++;
                return Token.NAME;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[171]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[326]++;
int CodeCoverConditionCoverageHelper_C69;

            // is it a number?
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((isDigit(peekChar())) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[196]++;
                isOctal = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[327]++;
                stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[328]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[329]++;
                int base = 10;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[330]++;
int CodeCoverConditionCoverageHelper_C70;

                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((c == '0') && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[198]++;
                    c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[331]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[332]++;
int CodeCoverConditionCoverageHelper_C71;
                    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((c == 'x') && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((c == 'X') && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[200]++;
                        base = 16;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[333]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[334]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[201]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[335]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[202]++;
                        base = 8;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[336]++;
                        isOctal = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[337]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[203]++;
                        addToString('0');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[338]++;
                    }
}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[199]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[339]++;
int CodeCoverConditionCoverageHelper_C73;

                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((base == 16) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[204]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[340]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[13]++;


int CodeCoverConditionCoverageHelper_C74;
                    while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((0 <= Kit.xDigitToInt(c, 0)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[13]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[14]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[15]++;
}
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[341]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[342]++;
                    }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[205]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[343]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[16]++;


int CodeCoverConditionCoverageHelper_C75;
                    while ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[16]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[17]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[18]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[344]++;
int CodeCoverConditionCoverageHelper_C76;
                        /*
                         * We permit 08 and 09 as decimal numbers, which
                         * makes our behavior a superset of the ECMA
                         * numeric grammar.  We might not always be so
                         * permissive, so we warn about it.
                         */
                        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((base == 8) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((c >= '8') && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[206]++;
                            parser.addWarning("msg.bad.octal.literal",
                                              c == '8' ? "8" : "9");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[345]++;
                            base = 10;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[346]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[207]++;}
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[347]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[348]++;
                    }
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[349]++;

                boolean isInteger = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[350]++;
int CodeCoverConditionCoverageHelper_C77;

                if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (128)) == 0 || true) &&
 ((base == 10) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C77 |= (32)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C77 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((c == 'e') && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((c == 'E') && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 4) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 4) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[208]++;
                    isInteger = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[351]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[352]++;
int CodeCoverConditionCoverageHelper_C78;
                    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[210]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[353]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[19]++;


int CodeCoverConditionCoverageHelper_C79;
                        do {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[19]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[20]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[21]++;
}
                            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[354]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[355]++;
                        } while ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false));

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[211]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[356]++;
int CodeCoverConditionCoverageHelper_C80;
                    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((c == 'e') && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((c == 'E') && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[212]++;
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[357]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[358]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[359]++;
int CodeCoverConditionCoverageHelper_C81;
                        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[214]++;
                            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[360]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[361]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[215]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[362]++;
int CodeCoverConditionCoverageHelper_C82;
                        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[216]++;
                            parser.addError("msg.missing.exponent");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[363]++;
                            return Token.ERROR;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[217]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[364]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[22]++;


int CodeCoverConditionCoverageHelper_C83;
                        do {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[22]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[23]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[24]++;
}
                            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[365]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[366]++;
                        } while ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false));

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[213]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[209]++;}
                ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[367]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[368]++;
                String numString = getStringFromBuffer();
                this.string = numString;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[369]++;

                double dval;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[370]++;
int CodeCoverConditionCoverageHelper_C84;
                if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((base == 10) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((isInteger) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[218]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[371]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                    try {
CodeCoverTryBranchHelper_Try1 = true;
                        // Use Java conversion to number from string...
                        dval = Double.parseDouble(numString);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[372]++;
                    }
                    catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[221]++;
                        parser.addError("msg.caught.nfe");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[373]++;
                        return Token.ERROR;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[220]++;
}
  }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[219]++;
                    dval = ScriptRuntime.stringToNumber(numString, 0, base);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[374]++;
                }

                this.number = dval;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[375]++;
                return Token.NUMBER;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[197]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[376]++;
int CodeCoverConditionCoverageHelper_C85;

            // is it a string?
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((c == '"') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((c == '\'') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[222]++;
                // We attempt to accumulate a string the fast way, by
                // building it directly out of the reader.  But if there
                // are any escaped characters in the string, we revert to
                // building it out of a StringBuffer.

                quoteChar = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[377]++;
                stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[378]++;

                c = getChar(false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[379]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[380]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[25]++;


int CodeCoverConditionCoverageHelper_C86;
            strLoop: while ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((c != quoteChar) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[25]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[26]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[27]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[381]++;
int CodeCoverConditionCoverageHelper_C87;
                    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((c == EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[224]++;
                        ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[382]++;
                        tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[383]++;
                        parser.addError("msg.unterminated.string.lit");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[384]++;
                        return Token.ERROR;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[225]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[385]++;
int CodeCoverConditionCoverageHelper_C88;

                    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[226]++;
                        // We've hit an escaped character
                        int escapeVal;

                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[386]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[387]++;
                        switch (c) {
                        case 'b':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[228]++; c = '\b';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[388]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[389]++; break;
                        case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[229]++; c = '\f';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[390]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[391]++; break;
                        case 'n':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[230]++; c = '\n';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[392]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[393]++; break;
                        case 'r':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[231]++; c = '\r';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[394]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[395]++; break;
                        case 't':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[232]++; c = '\t';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[396]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[397]++; break;

                        // \v a late addition to the ECMA spec,
                        // it is not in Java, so use 0xb
                        case 'v':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[233]++; c = 0xb;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[398]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[399]++; break;

                        case 'u':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[234]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[400]++;
                            // Get 4 hex digits; if the u escape is not
                            // followed by 4 hex digits, use 'u' + the
                            // literal character sequence that follows.
                            int escapeStart = stringBufferTop;
                            addToString('u');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[401]++;
                            escapeVal = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[402]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[403]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[28]++;


int CodeCoverConditionCoverageHelper_C89;
                            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((i != 4) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[28]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[29]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[30]++;
}
                                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[404]++;
                                escapeVal = Kit.xDigitToInt(c, escapeVal);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[405]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[406]++;
int CodeCoverConditionCoverageHelper_C90;
                                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((escapeVal < 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[235]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[407]++;
                                    continue strLoop;

                                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[236]++;}
                                addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[408]++;
                            }
                            // prepare for replace of stored 'u' sequence
                            // by escape value
                            stringBufferTop = escapeStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[409]++;
                            c = escapeVal;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[410]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[411]++;
                            break;
                        case 'x':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[237]++;
                            // Get 2 hex digits, defaulting to 'x'+literal
                            // sequence, as above.
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[412]++;
                            escapeVal = Kit.xDigitToInt(c, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[413]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[414]++;
int CodeCoverConditionCoverageHelper_C91;
                            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((escapeVal < 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[238]++;
                                addToString('x');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[415]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[416]++;
                                continue strLoop;

                            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[239]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[417]++;
                                int c1 = c;
                                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[418]++;
                                escapeVal = Kit.xDigitToInt(c, escapeVal);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[419]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[420]++;
int CodeCoverConditionCoverageHelper_C92;
                                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((escapeVal < 0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[240]++;
                                    addToString('x');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[421]++;
                                    addToString(c1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[422]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[423]++;
                                    continue strLoop;

                                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[241]++;
                                    // got 2 hex digits
                                    c = escapeVal;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[424]++;
                                }
                            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[425]++;
                            break;

                        case '\n':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[242]++;
                            // Remove line terminator after escape to follow
                            // SpiderMonkey and C/C++
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[426]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[427]++;
                            continue strLoop;

                        default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[243]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[428]++;
int CodeCoverConditionCoverageHelper_C93;
                            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((c < '8') && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[244]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[429]++;
                                int val = c - '0';
                                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[430]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[431]++;
int CodeCoverConditionCoverageHelper_C94;
                                if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((c < '8') && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[246]++;
                                    val = 8 * val + c - '0';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[432]++;
                                    c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[433]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[434]++;
int CodeCoverConditionCoverageHelper_C95;
                                    if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (32)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((c < '8') && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((val <= 037) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[248]++;
                                        // c is 3rd char of octal sequence only
                                        // if the resulting val <= 0377
                                        val = 8 * val + c - '0';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[435]++;
                                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[436]++;

                                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[249]++;}

                                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[247]++;}
                                ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[437]++;
                                c = val;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[438]++;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[245]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[227]++;}
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[439]++;
                    c = getChar(false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[440]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[441]++;

                String str = getStringFromBuffer();
                this.string = (String)allStrings.intern(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[442]++;
                return Token.STRING;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[223]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[443]++;

            switch (c) {
            case ';':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[250]++; return Token.SEMI;
            case '[':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[251]++; return Token.LB;
            case ']':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[252]++; return Token.RB;
            case '{':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[253]++; return Token.LC;
            case '}':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[254]++; return Token.RC;
            case '(':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[255]++; return Token.LP;
            case ')':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[256]++; return Token.RP;
            case ',':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[257]++; return Token.COMMA;
            case '?':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[258]++; return Token.HOOK;
            case ':':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[259]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[444]++;
int CodeCoverConditionCoverageHelper_C96;
                if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((matchChar(':')) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[260]++;
                    return Token.COLONCOLON;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[261]++;
                    return Token.COLON;
                }
            case '.':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[262]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[445]++;
int CodeCoverConditionCoverageHelper_C97;
                if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((matchChar('.')) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[263]++;
                    return Token.DOTDOT;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[264]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[446]++;
int CodeCoverConditionCoverageHelper_C98; if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((matchChar('(')) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[265]++;
                    return Token.DOTQUERY;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[266]++;
                    return Token.DOT;
                }
}

            case '|':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[267]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[447]++;
int CodeCoverConditionCoverageHelper_C99;
                if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((matchChar('|')) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[268]++;
                    return Token.OR;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[269]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[448]++;
int CodeCoverConditionCoverageHelper_C100; if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[270]++;
                    return Token.ASSIGN_BITOR;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[271]++;
                    return Token.BITOR;
                }
}

            case '^':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[272]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[449]++;
int CodeCoverConditionCoverageHelper_C101;
                if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[273]++;
                    return Token.ASSIGN_BITXOR;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[274]++;
                    return Token.BITXOR;
                }

            case '&':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[275]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[450]++;
int CodeCoverConditionCoverageHelper_C102;
                if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((matchChar('&')) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[276]++;
                    return Token.AND;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[277]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[451]++;
int CodeCoverConditionCoverageHelper_C103; if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[278]++;
                    return Token.ASSIGN_BITAND;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[279]++;
                    return Token.BITAND;
                }
}

            case '=':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[280]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[452]++;
int CodeCoverConditionCoverageHelper_C104;
                if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[281]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[453]++;
int CodeCoverConditionCoverageHelper_C105;
                    if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[283]++;
                        return Token.SHEQ;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[284]++;
                        return Token.EQ;
                    }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[282]++;
                    return Token.ASSIGN;
                }

            case '!':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[285]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[454]++;
int CodeCoverConditionCoverageHelper_C106;
                if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[286]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[455]++;
int CodeCoverConditionCoverageHelper_C107;
                    if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[288]++;
                        return Token.SHNE;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[289]++;
                        return Token.NE;
                    }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[287]++;
                    return Token.NOT;
                }

            case '<':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[290]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[456]++;
int CodeCoverConditionCoverageHelper_C108;
                /* NB:treat HTML begin-comment as comment-till-eol */
                if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((matchChar('!')) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[291]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[457]++;
int CodeCoverConditionCoverageHelper_C109;
                    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((matchChar('-')) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[293]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[458]++;
int CodeCoverConditionCoverageHelper_C110;
                        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((matchChar('-')) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[295]++;
                            tokenBeg = cursor - 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[459]++;
                            skipLine();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[460]++;
                            commentType = Token.CommentType.HTML;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[461]++;
                            return Token.COMMENT;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[296]++;}
                        ungetCharIgnoreLineEnd('-');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[462]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[294]++;}
                    ungetCharIgnoreLineEnd('!');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[463]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[292]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[464]++;
int CodeCoverConditionCoverageHelper_C111;
                if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((matchChar('<')) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[297]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[465]++;
int CodeCoverConditionCoverageHelper_C112;
                    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[299]++;
                        return Token.ASSIGN_LSH;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[300]++;
                        return Token.LSH;
                    }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[298]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[466]++;
int CodeCoverConditionCoverageHelper_C113;
                    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[301]++;
                        return Token.LE;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[302]++;
                        return Token.LT;
                    }
                }

            case '>':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[303]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[467]++;
int CodeCoverConditionCoverageHelper_C114;
                if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((matchChar('>')) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[304]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[468]++;
int CodeCoverConditionCoverageHelper_C115;
                    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((matchChar('>')) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[306]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[469]++;
int CodeCoverConditionCoverageHelper_C116;
                        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[308]++;
                            return Token.ASSIGN_URSH;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[309]++;
                            return Token.URSH;
                        }

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[307]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[470]++;
int CodeCoverConditionCoverageHelper_C117;
                        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[310]++;
                            return Token.ASSIGN_RSH;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[311]++;
                            return Token.RSH;
                        }
                    }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[305]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[471]++;
int CodeCoverConditionCoverageHelper_C118;
                    if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[312]++;
                        return Token.GE;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[313]++;
                        return Token.GT;
                    }
                }

            case '*':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[314]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[472]++;
int CodeCoverConditionCoverageHelper_C119;
                if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[315]++;
                    return Token.ASSIGN_MUL;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[316]++;
                    return Token.MUL;
                }

            case '/':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[317]++;
                markCommentStart();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[473]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[474]++;
int CodeCoverConditionCoverageHelper_C120;
                // is it a // comment?
                if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((matchChar('/')) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[318]++;
                    tokenBeg = cursor - 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[475]++;
                    skipLine();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[476]++;
                    commentType = Token.CommentType.LINE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[477]++;
                    return Token.COMMENT;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[319]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[478]++;
int CodeCoverConditionCoverageHelper_C121;
                // is it a /* or /** comment?
                if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((matchChar('*')) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[320]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[479]++;
                    boolean lookForSlash = false;
                    tokenBeg = cursor - 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[480]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[481]++;
int CodeCoverConditionCoverageHelper_C122;
                    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((matchChar('*')) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[322]++;
                        lookForSlash = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[482]++;
                        commentType = Token.CommentType.JSDOC;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[483]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[323]++;
                        commentType = Token.CommentType.BLOCK_COMMENT;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[484]++;
                    }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[485]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[31]++;


                    for (;;) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[31]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[32]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[33]++;
}
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[486]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[487]++;
int CodeCoverConditionCoverageHelper_C124;
                        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((c == EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[324]++;
                            tokenEnd = cursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[488]++;
                            parser.addError("msg.unterminated.comment");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[489]++;
                            return Token.COMMENT;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[325]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[490]++;
int CodeCoverConditionCoverageHelper_C125; if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((c == '*') && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[326]++;
                            lookForSlash = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[491]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[327]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[492]++;
int CodeCoverConditionCoverageHelper_C126; if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[328]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[493]++;
int CodeCoverConditionCoverageHelper_C127;
                            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((lookForSlash) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[330]++;
                                tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[494]++;
                                return Token.COMMENT;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[331]++;}

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[329]++;
                            lookForSlash = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[495]++;
                            tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[496]++;
                        }
}
}
                    }

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[321]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[497]++;
int CodeCoverConditionCoverageHelper_C128;

                if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[332]++;
                    return Token.ASSIGN_DIV;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[333]++;
                    return Token.DIV;
                }

            case '%':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[334]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[498]++;
int CodeCoverConditionCoverageHelper_C129;
                if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[335]++;
                    return Token.ASSIGN_MOD;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[336]++;
                    return Token.MOD;
                }

            case '~':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[337]++;
                return Token.BITNOT;

            case '+':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[338]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[499]++;
int CodeCoverConditionCoverageHelper_C130;
                if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[339]++;
                    return Token.ASSIGN_ADD;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[340]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[500]++;
int CodeCoverConditionCoverageHelper_C131; if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((matchChar('+')) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[341]++;
                    return Token.INC;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[342]++;
                    return Token.ADD;
                }
}

            case '-':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[343]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[501]++;
int CodeCoverConditionCoverageHelper_C132;
                if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((matchChar('=')) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[344]++;
                    c = Token.ASSIGN_SUB;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[502]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[345]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[503]++;
int CodeCoverConditionCoverageHelper_C133; if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((matchChar('-')) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[346]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[504]++;
int CodeCoverConditionCoverageHelper_C134;
                    if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((dirtyLine) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[348]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[505]++;
int CodeCoverConditionCoverageHelper_C135;
                        // treat HTML end-comment after possible whitespace
                        // after line start as comment-until-eol
                        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((matchChar('>')) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[350]++;
                            markCommentStart("--");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[506]++;
                            skipLine();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[507]++;
                            commentType = Token.CommentType.HTML;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[508]++;
                            return Token.COMMENT;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[351]++;}

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[349]++;}
                    c = Token.DEC;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[509]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[347]++;
                    c = Token.SUB;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[510]++;
                }
}
                dirtyLine = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[511]++;
                return c;

            default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[352]++;
                parser.addError("msg.illegal.character");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[512]++;
                return Token.ERROR;
            }
        }
    }

    private static boolean isAlpha(int c)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[513]++;
int CodeCoverConditionCoverageHelper_C136;
        // Use 'Z' < 'a'
        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[353]++;
            return 'A' <= c;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[354]++;
            return 'a' <= c && c <= 'z';
        }
    }

    static boolean isDigit(int c)
    {
        return '0' <= c && c <= '9';
    }

    /* As defined in ECMA.  jsscan.c uses C isspace() (which allows
     * \v, I think.)  note that code in getChar() implicitly accepts
     * '\r' == \u000D as well.
     */
    static boolean isJSSpace(int c)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[514]++;
int CodeCoverConditionCoverageHelper_C137;
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((c <= 127) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[355]++;
            return c == 0x20 || c == 0x9 || c == 0xC || c == 0xB;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[356]++;
            return c == 0xA0 || c == BYTE_ORDER_MARK
                || Character.getType((char)c) == Character.SPACE_SEPARATOR;
        }
    }

    private static boolean isJSFormatChar(int c)
    {
        return c > 127 && Character.getType((char)c) == Character.FORMAT;
    }

    /**
     * Parser calls the method when it gets / or /= in literal context.
     */
    void readRegExp(int startToken)
        throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[515]++;
        int start = tokenBeg;
        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[516]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[517]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((startToken == Token.ASSIGN_DIV) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[357]++;
            // Miss-scanned /=
            addToString('=');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[518]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[358]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[519]++;
int CodeCoverConditionCoverageHelper_C139;
            if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((startToken != Token.DIV) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[359]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[520]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[360]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[521]++;

        boolean inCharSet = false; // true if inside a '['..']' pair
        int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[522]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[34]++;


        while ((c = getChar()) != '/' || inCharSet) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[34]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[35]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[36]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[523]++;
int CodeCoverConditionCoverageHelper_C141;
            if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((c == EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[361]++;
                ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[524]++;
                tokenEnd = cursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[525]++;
                this.string = new String(stringBuffer, 0, stringBufferTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[526]++;
                parser.reportError("msg.unterminated.re.lit");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[527]++;
                return;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[362]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[528]++;
int CodeCoverConditionCoverageHelper_C142;
            if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[363]++;
                addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[529]++;
                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[530]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[364]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[531]++;
int CodeCoverConditionCoverageHelper_C143; if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((c == '[') && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[365]++;
                inCharSet = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[532]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[366]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[533]++;
int CodeCoverConditionCoverageHelper_C144; if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((c == ']') && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[367]++;
                inCharSet = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[534]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[368]++;}
}
}
            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[535]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[536]++;
        int reEnd = stringBufferTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[537]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[37]++;



        while (true) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[37]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[38]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[39]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[538]++;
int CodeCoverConditionCoverageHelper_C146;
            if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((matchChar('g')) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[369]++;
                addToString('g');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[539]++;
}
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[370]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[540]++;
int CodeCoverConditionCoverageHelper_C147; if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((matchChar('i')) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[371]++;
                addToString('i');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[541]++;
}
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[372]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[542]++;
int CodeCoverConditionCoverageHelper_C148; if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((matchChar('m')) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[373]++;
                addToString('m');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[543]++;
}
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[374]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[544]++;
int CodeCoverConditionCoverageHelper_C149; if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((matchChar('y')) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[375]++;  // FireFox 3
                addToString('y');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[545]++;
}
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[376]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[546]++;
                break;
}
}
}
}
        }
        tokenEnd = start + stringBufferTop + 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[547]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[548]++;
int CodeCoverConditionCoverageHelper_C150;  // include slashes

        if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((isAlpha(peekChar())) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[377]++;
            parser.reportError("msg.invalid.re.flag");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[549]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[378]++;}

        this.string = new String(stringBuffer, 0, reEnd);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[550]++;
        this.regExpFlags = new String(stringBuffer, reEnd,
                                      stringBufferTop - reEnd);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[551]++;
    }

    String readAndClearRegExpFlags() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[552]++;
        String flags = this.regExpFlags;
        this.regExpFlags = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[553]++;
        return flags;
    }

    boolean isXMLAttribute()
    {
        return xmlIsAttribute;
    }

    int getFirstXMLToken() throws IOException
    {
        xmlOpenTagsCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[554]++;
        xmlIsAttribute = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[555]++;
        xmlIsTagContent = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[556]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[557]++;
int CodeCoverConditionCoverageHelper_C151;
        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((canUngetChar()) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[379]++;
            return Token.ERROR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[380]++;}
        ungetChar('<');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[558]++;
        return getNextXMLToken();
    }

    int getNextXMLToken() throws IOException
    {
        tokenBeg = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[559]++;
        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[560]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[561]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[40]++;


int CodeCoverConditionCoverageHelper_C152; // remember the XML

        for (int c = getChar();(((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((c != EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false); c = getChar()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[40]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[41]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[42]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[562]++;
int CodeCoverConditionCoverageHelper_C153;
            if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((xmlIsTagContent) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[381]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[563]++;
                switch (c) {
                case '>':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[383]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[564]++;
                    xmlIsTagContent = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[565]++;
                    xmlIsAttribute = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[566]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[567]++;
                    break;
                case '/':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[384]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[568]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[569]++;
int CodeCoverConditionCoverageHelper_C154;
                    if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((peekChar() == '>') && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[385]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[570]++;
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[571]++;
                        xmlIsTagContent = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[572]++;
                        xmlOpenTagsCount--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[573]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[386]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[574]++;
                    break;
                case '{':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[387]++;
                    ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[575]++;
                    this.string = getStringFromBuffer();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[576]++;
                    return Token.XML;
                case '\'':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[388]++;
                case '"':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[389]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[577]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[578]++;
int CodeCoverConditionCoverageHelper_C155;
                    if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((readQuotedString(c)) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[390]++; return Token.ERROR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[391]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[579]++;
                    break;
                case '=':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[392]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[580]++;
                    xmlIsAttribute = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[581]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[582]++;
                    break;
                case ' ':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[393]++;
                case '\t':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[394]++;
                case '\r':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[395]++;
                case '\n':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[396]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[583]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[584]++;
                    break;
                default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[397]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[585]++;
                    xmlIsAttribute = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[586]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[587]++;
                    break;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[588]++;
int CodeCoverConditionCoverageHelper_C156;

                if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C156 |= (8)) == 0 || true) &&
 ((xmlIsTagContent) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((xmlOpenTagsCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[398]++;
                    this.string = getStringFromBuffer();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[589]++;
                    return Token.XMLEND;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[399]++;}

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[382]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[590]++;
                switch (c) {
                case '<':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[400]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[591]++;
                    c = peekChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[592]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[593]++;
                    switch (c) {
                    case '!':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[401]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[594]++; // Skip !
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[595]++;
                        c = peekChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[596]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[597]++;
                        switch (c) {
                        case '-':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[402]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[598]++; // Skip -
                            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[599]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[600]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[601]++;
int CodeCoverConditionCoverageHelper_C157;
                            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[403]++;
                                addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[602]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[603]++;
int CodeCoverConditionCoverageHelper_C158;
                                if((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((readXmlComment()) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[405]++; return Token.ERROR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[406]++;}

                            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[404]++;
                                // throw away the string in progress
                                stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[604]++;
                                this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[605]++;
                                parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[606]++;
                                return Token.ERROR;
                            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[607]++;
                            break;
                        case '[':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[407]++;
                            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[608]++; // Skip [
                            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[609]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[610]++;
int CodeCoverConditionCoverageHelper_C159;
                            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2048)) == 0 || true) &&
 ((getChar() == 'C') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (512)) == 0 || true) &&
 ((getChar() == 'D') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (128)) == 0 || true) &&
 ((getChar() == 'A') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (32)) == 0 || true) &&
 ((getChar() == 'T') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (8)) == 0 || true) &&
 ((getChar() == 'A') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((getChar() == '[') && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 6) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 6) && false))
                            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[408]++;
                                addToString('C');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[611]++;
                                addToString('D');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[612]++;
                                addToString('A');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[613]++;
                                addToString('T');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[614]++;
                                addToString('A');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[615]++;
                                addToString('[');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[616]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[617]++;
int CodeCoverConditionCoverageHelper_C160;
                                if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((readCDATA()) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[410]++; return Token.ERROR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[411]++;}


                            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[409]++;
                                // throw away the string in progress
                                stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[618]++;
                                this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[619]++;
                                parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[620]++;
                                return Token.ERROR;
                            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[621]++;
                            break;
                        default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[412]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[622]++;
int CodeCoverConditionCoverageHelper_C161;
                            if((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((readEntity()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[413]++; return Token.ERROR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[414]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[623]++;
                            break;
                        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[624]++;
                        break;
                    case '?':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[415]++;
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[625]++; // Skip ?
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[626]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[627]++;
int CodeCoverConditionCoverageHelper_C162;
                        if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((readPI()) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[416]++; return Token.ERROR;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[417]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[628]++;
                        break;
                    case '/':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[418]++;
                        // End tag
                        c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[629]++; // Skip /
                        addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[630]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[631]++;
int CodeCoverConditionCoverageHelper_C163;
                        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((xmlOpenTagsCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[419]++;
                            // throw away the string in progress
                            stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[632]++;
                            this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[633]++;
                            parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[634]++;
                            return Token.ERROR;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[420]++;}
                        xmlIsTagContent = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[635]++;
                        xmlOpenTagsCount--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[636]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[637]++;
                        break;
                    default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[421]++;
                        // Start tag
                        xmlIsTagContent = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[638]++;
                        xmlOpenTagsCount++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[639]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[640]++;
                        break;
                    }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[641]++;
                    break;
                case '{':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[422]++;
                    ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[642]++;
                    this.string = getStringFromBuffer();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[643]++;
                    return Token.XML;
                default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[423]++;
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[644]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[645]++;
                    break;
                }
            }
        }

        tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[646]++;
        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[647]++; // throw away the string in progress
        this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[648]++;
        parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[649]++;
        return Token.ERROR;
    }

    /**
     *
     */
    private boolean readQuotedString(int quote) throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[650]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[43]++;


int CodeCoverConditionCoverageHelper_C164;
        for (int c = getChar();(((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((c != EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false); c = getChar()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[43]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[44]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[45]++;
}
            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[651]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[652]++;
int CodeCoverConditionCoverageHelper_C165;
            if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((c == quote) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[424]++; return true;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[425]++;}
        }

        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[653]++; // throw away the string in progress
        this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[654]++;
        parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[655]++;
        return false;
    }

    /**
     *
     */
    private boolean readXmlComment() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[656]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[46]++;


int CodeCoverConditionCoverageHelper_C166;
        for (int c = getChar();(((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((c != EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false);) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[46]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[47]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[48]++;
}
            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[657]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[658]++;
int CodeCoverConditionCoverageHelper_C167;
            if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (8)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C167 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((peekChar() == '-') && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[426]++;
                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[659]++;
                addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[660]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[661]++;
int CodeCoverConditionCoverageHelper_C168;
                if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((peekChar() == '>') && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[428]++;
                    c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[662]++; // Skip >
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[663]++;
                    return true;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[429]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[664]++;
                    continue;
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[427]++;}
            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[665]++;
        }

        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[666]++; // throw away the string in progress
        this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[667]++;
        parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[668]++;
        return false;
    }

    /**
     *
     */
    private boolean readCDATA() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[669]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[49]++;


int CodeCoverConditionCoverageHelper_C169;
        for (int c = getChar();(((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((c != EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false);) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[49]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[50]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[51]++;
}
            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[670]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[671]++;
int CodeCoverConditionCoverageHelper_C170;
            if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (8)) == 0 || true) &&
 ((c == ']') && 
  ((CodeCoverConditionCoverageHelper_C170 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((peekChar() == ']') && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[430]++;
                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[672]++;
                addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[673]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[674]++;
int CodeCoverConditionCoverageHelper_C171;
                if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((peekChar() == '>') && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[432]++;
                    c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[675]++; // Skip >
                    addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[676]++;
                    return true;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[433]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[677]++;
                    continue;
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[431]++;}
            c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[678]++;
        }

        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[679]++; // throw away the string in progress
        this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[680]++;
        parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[681]++;
        return false;
    }

    /**
     *
     */
    private boolean readEntity() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[682]++;
        int declTags = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[683]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[52]++;


int CodeCoverConditionCoverageHelper_C172;
        for (int c = getChar();(((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((c != EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false); c = getChar()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[52]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[53]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[54]++;
}
            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[684]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[685]++;
            switch (c) {
            case '<':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[434]++;
                declTags++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[686]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[687]++;
                break;
            case '>':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[435]++;
                declTags--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[688]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[689]++;
int CodeCoverConditionCoverageHelper_C173;
                if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((declTags == 0) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[436]++; return true;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[437]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[690]++;
                break; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[438]++;
            }
        }

        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[691]++; // throw away the string in progress
        this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[692]++;
        parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[693]++;
        return false;
    }

    /**
     *
     */
    private boolean readPI() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[694]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[55]++;


int CodeCoverConditionCoverageHelper_C174;
        for (int c = getChar();(((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((c != EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false); c = getChar()) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[55]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[56]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[57]++;
}
            addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[695]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[696]++;
int CodeCoverConditionCoverageHelper_C175;
            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (8)) == 0 || true) &&
 ((c == '?') && 
  ((CodeCoverConditionCoverageHelper_C175 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((peekChar() == '>') && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[439]++;
                c = getChar();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[697]++; // Skip >
                addToString(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[698]++;
                return true;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[440]++;}
        }

        stringBufferTop = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[699]++; // throw away the string in progress
        this.string = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[700]++;
        parser.addError("msg.XML.bad.form");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[701]++;
        return false;
    }

    private String getStringFromBuffer()
    {
        tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[702]++;
        return new String(stringBuffer, 0, stringBufferTop);
    }

    private void addToString(int c)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[703]++;
        int N = stringBufferTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[704]++;
int CodeCoverConditionCoverageHelper_C176;
        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((N == stringBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[441]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[705]++;
            char[] tmp = new char[stringBuffer.length * 2];
            System.arraycopy(stringBuffer, 0, tmp, 0, N);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[706]++;
            stringBuffer = tmp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[707]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[442]++;}
        stringBuffer[N] = (char)c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[708]++;
        stringBufferTop = N + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[709]++;
    }

    private boolean canUngetChar() {
        return ungetCursor == 0 || ungetBuffer[ungetCursor - 1] != '\n';
    }

    private void ungetChar(int c)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[710]++;
int CodeCoverConditionCoverageHelper_C177;
        // can not unread past across line boundary
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (8)) == 0 || true) &&
 ((ungetCursor != 0) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((ungetBuffer[ungetCursor - 1] == '\n') && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[443]++;
            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[711]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[444]++;}
        ungetBuffer[ungetCursor++] = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[712]++;
        cursor--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[713]++;
    }

    private boolean matchChar(int test) throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[714]++;
        int c = getCharIgnoreLineEnd();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[715]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((c == test) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[445]++;
            tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[716]++;
            return true;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[446]++;
            ungetCharIgnoreLineEnd(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[717]++;
            return false;
        }
    }

    private int peekChar() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[718]++;
        int c = getChar();
        ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[719]++;
        return c;
    }

    private int getChar() throws IOException
    {
        return getChar(true);
    }

    private int getChar(boolean skipFormattingChars) throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[720]++;
int CodeCoverConditionCoverageHelper_C179;
        if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((ungetCursor != 0) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[447]++;
            cursor++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[721]++;
            return ungetBuffer[--ungetCursor];

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[448]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[722]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[58]++;



        for(;;) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[58]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[59]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[60]++;
}
            int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[723]++;
int CodeCoverConditionCoverageHelper_C181;
            if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[449]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[724]++;
int CodeCoverConditionCoverageHelper_C182;
                if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((sourceCursor == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[451]++;
                    hitEOF = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[725]++;
                    return EOF_CHAR;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[452]++;}
                cursor++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[726]++;
                c = sourceString.charAt(sourceCursor++);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[727]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[450]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[728]++;
int CodeCoverConditionCoverageHelper_C183;
                if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((sourceCursor == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[453]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[729]++;
int CodeCoverConditionCoverageHelper_C184;
                    if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((fillSourceBuffer()) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[455]++;
                        hitEOF = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[730]++;
                        return EOF_CHAR;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[456]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[454]++;}
                cursor++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[731]++;
                c = sourceBuffer[sourceCursor++];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[732]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[733]++;
int CodeCoverConditionCoverageHelper_C185;

            if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((lineEndChar >= 0) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[457]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[734]++;
int CodeCoverConditionCoverageHelper_C186;
                if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (8)) == 0 || true) &&
 ((lineEndChar == '\r') && 
  ((CodeCoverConditionCoverageHelper_C186 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[459]++;
                    lineEndChar = '\n';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[735]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[736]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[460]++;}
                lineEndChar = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[737]++;
                lineStart = sourceCursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[738]++;
                lineno++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[739]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[458]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[740]++;
int CodeCoverConditionCoverageHelper_C187;

            if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((c <= 127) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[461]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[741]++;
int CodeCoverConditionCoverageHelper_C188;
                if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C188 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[463]++;
                    lineEndChar = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[742]++;
                    c = '\n';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[743]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[464]++;}

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[462]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[744]++;
int CodeCoverConditionCoverageHelper_C189;
                if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((c == BYTE_ORDER_MARK) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[465]++; return c;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[466]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[745]++;
int CodeCoverConditionCoverageHelper_C190; // BOM is considered whitespace
                if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (8)) == 0 || true) &&
 ((skipFormattingChars) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((isJSFormatChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[467]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[746]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[468]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[747]++;
int CodeCoverConditionCoverageHelper_C191;
                if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSLineTerminator(c)) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[469]++;
                    lineEndChar = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[748]++;
                    c = '\n';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[749]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[470]++;}
            }
            return c;
        }
    }

    private int getCharIgnoreLineEnd() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[750]++;
int CodeCoverConditionCoverageHelper_C192;
        if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((ungetCursor != 0) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[471]++;
            cursor++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[751]++;
            return ungetBuffer[--ungetCursor];

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[472]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[752]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[61]++;



        for(;;) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[61]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[62]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[63]++;
}
            int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[753]++;
int CodeCoverConditionCoverageHelper_C194;
            if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[473]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[754]++;
int CodeCoverConditionCoverageHelper_C195;
                if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((sourceCursor == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[475]++;
                    hitEOF = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[755]++;
                    return EOF_CHAR;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[476]++;}
                cursor++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[756]++;
                c = sourceString.charAt(sourceCursor++);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[757]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[474]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[758]++;
int CodeCoverConditionCoverageHelper_C196;
                if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((sourceCursor == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[477]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[759]++;
int CodeCoverConditionCoverageHelper_C197;
                    if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((fillSourceBuffer()) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[479]++;
                        hitEOF = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[760]++;
                        return EOF_CHAR;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[480]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[478]++;}
                cursor++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[761]++;
                c = sourceBuffer[sourceCursor++];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[762]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[763]++;
int CodeCoverConditionCoverageHelper_C198;

            if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((c <= 127) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[481]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[764]++;
int CodeCoverConditionCoverageHelper_C199;
                if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C199 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[483]++;
                    lineEndChar = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[765]++;
                    c = '\n';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[766]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[484]++;}

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[482]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[767]++;
int CodeCoverConditionCoverageHelper_C200;
                if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((c == BYTE_ORDER_MARK) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[485]++; return c;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[486]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[768]++;
int CodeCoverConditionCoverageHelper_C201; // BOM is considered whitespace
                if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((isJSFormatChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[487]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[769]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[488]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[770]++;
int CodeCoverConditionCoverageHelper_C202;
                if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSLineTerminator(c)) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[489]++;
                    lineEndChar = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[771]++;
                    c = '\n';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[772]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[490]++;}
            }
            return c;
        }
    }

    private void ungetCharIgnoreLineEnd(int c)
    {
        ungetBuffer[ungetCursor++] = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[773]++;
        cursor--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[774]++;
    }

    private void skipLine() throws IOException
    {
        // skip to end of line
        int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[775]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[64]++;


        while ((c = getChar()) != EOF_CHAR && c != '\n') {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[64]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[65]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[66]++;
} }
        ungetChar(c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[776]++;
        tokenEnd = cursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[777]++;
    }

    /**
     * Returns the offset into the current line.
     */
    final int getOffset()
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[778]++;
        int n = sourceCursor - lineStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[779]++;
int CodeCoverConditionCoverageHelper_C204;
        if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((lineEndChar >= 0) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[491]++; --n;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[780]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[492]++;}
        return n;
    }

    final String getLine()
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[781]++;
int CodeCoverConditionCoverageHelper_C205;
        if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[493]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[782]++;
            // String case
            int lineEnd = sourceCursor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[783]++;
int CodeCoverConditionCoverageHelper_C206;
            if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((lineEndChar >= 0) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[495]++;
                --lineEnd;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[784]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[496]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[785]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[67]++;


int CodeCoverConditionCoverageHelper_C207;
                for (;(((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((lineEnd != sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false); ++lineEnd) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[67]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[68]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[69]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[786]++;
                    int c = sourceString.charAt(lineEnd);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[787]++;
int CodeCoverConditionCoverageHelper_C208;
                    if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSLineTerminator(c)) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[497]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[788]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[498]++;}
                }
            }
            return sourceString.substring(lineStart, lineEnd);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[494]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[789]++;
            // Reader case
            int lineLength = sourceCursor - lineStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[790]++;
int CodeCoverConditionCoverageHelper_C209;
            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((lineEndChar >= 0) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[499]++;
                --lineLength;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[791]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[500]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[792]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[70]++;


                // Read until the end of line
                for (;; ++lineLength) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[70]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[71]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[72]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[793]++;
                    int i = lineStart + lineLength;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[794]++;
int CodeCoverConditionCoverageHelper_C211;
                    if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((i == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[501]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[795]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[796]++;
int CodeCoverConditionCoverageHelper_C212;
                            if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((fillSourceBuffer()) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[504]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[797]++; break;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[505]++;}
                        } catch (IOException ioe) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[506]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[798]++;
                            // ignore it, we're already displaying an error...
                            break;
                        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[503]++;
}
  }
                        // i recalculuation as fillSourceBuffer can move saved
                        // line buffer and change lineStart
                        i = lineStart + lineLength;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[799]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[502]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[800]++;
                    int c = sourceBuffer[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[801]++;
int CodeCoverConditionCoverageHelper_C213;
                    if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSLineTerminator(c)) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[507]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[802]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[508]++;}
                }
            }
            return new String(sourceBuffer, lineStart, lineLength);
        }
    }

    private boolean fillSourceBuffer() throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[803]++;
int CodeCoverConditionCoverageHelper_C214;
        if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[509]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[804]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[510]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[805]++;
int CodeCoverConditionCoverageHelper_C215;
        if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((sourceEnd == sourceBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[511]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[806]++;
int CodeCoverConditionCoverageHelper_C216;
            if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (8)) == 0 || true) &&
 ((lineStart != 0) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((isMarkingComment()) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[513]++;
                System.arraycopy(sourceBuffer, lineStart, sourceBuffer, 0,
                                 sourceEnd - lineStart);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[807]++;
                sourceEnd -= lineStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[808]++;
                sourceCursor -= lineStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[809]++;
                lineStart = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[810]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[514]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[811]++;
                char[] tmp = new char[sourceBuffer.length * 2];
                System.arraycopy(sourceBuffer, 0, tmp, 0, sourceEnd);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[812]++;
                sourceBuffer = tmp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[813]++;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[512]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[814]++;
        int n = sourceReader.read(sourceBuffer, sourceEnd,
                                  sourceBuffer.length - sourceEnd);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[815]++;
int CodeCoverConditionCoverageHelper_C217;
        if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[515]++;
            return false;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[516]++;}
        sourceEnd += n;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[816]++;
        return true;
    }

    /**
     * Return the current position of the scanner cursor.
     */
    public int getCursor() {
        return cursor;
    }

    /**
     * Return the absolute source offset of the last scanned token.
     */
    public int getTokenBeg() {
        return tokenBeg;
    }

    /**
     * Return the absolute source end-offset of the last scanned token.
     */
    public int getTokenEnd() {
        return tokenEnd;
    }

    /**
     * Return tokenEnd - tokenBeg
     */
    public int getTokenLength() {
        return tokenEnd - tokenBeg;
    }

    /**
     * Return the type of the last scanned comment.
     * @return type of last scanned comment, or 0 if none have been scanned.
     */
    public Token.CommentType getCommentType() {
        return commentType;
    }

    private void markCommentStart() {
        markCommentStart("");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[817]++;
    }

    private void markCommentStart(String prefix) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[818]++;
int CodeCoverConditionCoverageHelper_C218;
        if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (8)) == 0 || true) &&
 ((parser.compilerEnv.isRecordingComments()) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((sourceReader != null) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[517]++;
            commentPrefix = prefix;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[819]++;
            commentCursor = sourceCursor - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[820]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[518]++;}
    }

    private boolean isMarkingComment() {
        return commentCursor != -1;
    }

     final String getAndResetCurrentComment() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[821]++;
int CodeCoverConditionCoverageHelper_C219;
        if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[519]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[822]++;
int CodeCoverConditionCoverageHelper_C220;
            if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((isMarkingComment()) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[521]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[823]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[522]++;}
            return sourceString.substring(tokenBeg, tokenEnd);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[520]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[824]++;
int CodeCoverConditionCoverageHelper_C221;
            if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((isMarkingComment()) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[523]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[825]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.branches[524]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[826]++;
            StringBuilder comment = new StringBuilder(commentPrefix);
            comment.append(sourceBuffer, commentCursor,
                getTokenLength() - commentPrefix.length());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[827]++;
            commentCursor = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[828]++;
            return comment.toString();
        }
    }

    private String convertLastCharToHex(String str) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[829]++;
      int lastIndex = str.length()-1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[830]++;
      StringBuffer buf = new StringBuffer(
          str.substring(0, lastIndex));
      buf.append("\\u");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[831]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[832]++;
      String hexCode = Integer.toHexString(str.charAt(lastIndex));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[833]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[73]++;


int CodeCoverConditionCoverageHelper_C222;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((i < 4-hexCode.length()) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[73]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[74]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.loops[75]++;
}
        buf.append('0');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[834]++;
      }
      buf.append(hexCode);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[835]++;
      return buf.toString();
    }

    // stuff other than whitespace since start of line
    private boolean dirtyLine;

    String regExpFlags;

    // Set this to an initial non-null value so that the Parser has
    // something to retrieve even if an error has occurred and no
    // string is found.  Fosters one class of error, but saves lots of
    // code.
    private String string = "";
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[836]++;
  }
    private double number;
    private boolean isOctal;

    // delimiter for last string literal scanned
    private int quoteChar;

    private char[] stringBuffer = new char[128];
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[837]++;
  }
    private int stringBufferTop;
    private ObjToIntMap allStrings = new ObjToIntMap(50);
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[838]++;
  }

    // Room to backtrace from to < on failed match of the last - in <!--
    private final int[] ungetBuffer = new int[3];
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[839]++;
  }
    private int ungetCursor;

    private boolean hitEOF = false;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[840]++;
  }

    private int lineStart = 0;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[841]++;
  }
    private int lineEndChar = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[842]++;
  }
    int lineno;

    private String sourceString;
    private Reader sourceReader;
    private char[] sourceBuffer;
    private int sourceEnd;

    // sourceCursor is an index into a small buffer that keeps a
    // sliding window of the source stream.
    int sourceCursor;

    // cursor is a monotonically increasing index into the original
    // source stream, tracking exactly how far scanning has progressed.
    // Its value is the index of the next character to be scanned.
    int cursor;

    // Record start and end positions of last scanned token.
    int tokenBeg;
    int tokenEnd;

    // Type of last comment scanned.
    Token.CommentType commentType;

    // for xml tokenizer
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;

    private Parser parser;

    private String commentPrefix = "";
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[843]++;
  }
    private int commentCursor = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5.statements[844]++;
  }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5 ());
  }
    public static long[] statements = new long[845];
    public static long[] branches = new long[525];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[223];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-TokenStream.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,3,1,1,3,1,1,1,3,1,2,1,1,1,2,2,3,1,1,2,2,1,1,2,2,1,2,1,1,1,1,1,2,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2,1,1,1,0,1,1,1,1,1,1,1,1,1,1,2,1,1,3,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,2,1,1,0,1,1,1,1,1,2,1,2,1,2,1,1,0,1,1,1,1,1,2,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,2,1,2,1,1,1,1};
    for (int i = 1; i <= 222; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[76];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9idid260helurhdzor2y4pc78f5 () {
    super("org.mozilla.javascript.RHINO-SRC-TokenStream.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 844; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 524; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 222; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 75; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-TokenStream.java");
      for (int i = 1; i <= 844; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 524; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 222; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 25; i++) {
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

