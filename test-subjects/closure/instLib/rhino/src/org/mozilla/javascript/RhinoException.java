/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class of exceptions thrown by the JavaScript engine.
 */
public abstract class RhinoException extends RuntimeException
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.ping();
  }


    RhinoException()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[1]++;
        Evaluator e = Context.createInterpreter();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((e != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[1]++;
            e.captureStackInfo(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[3]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[2]++;}
    }

    RhinoException(String details)
    {
        super(details);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[4]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[5]++;
        Evaluator e = Context.createInterpreter();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((e != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[3]++;
            e.captureStackInfo(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[7]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[4]++;}
    }

    @Override
    public final String getMessage()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[8]++;
        String details = details();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lineNumber <= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[5]++;
            return details;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[6]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[10]++;
        StringBuffer buf = new StringBuffer(details);
        buf.append(" (");
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[11]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[7]++;
            buf.append(sourceName);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[13]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[8]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lineNumber > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[9]++;
            buf.append('#');
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[15]++;
            buf.append(lineNumber);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[16]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[10]++;}
        buf.append(')');
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[17]++;
        return buf.toString();
    }

    public String details()
    {
        return super.getMessage();
    }

    /**
     * Get the uri of the script source containing the error, or null
     * if that information is not available.
     */
    public final String sourceName()
    {
        return sourceName;
    }

    /**
     * Initialize the uri of the script source containing the error.
     *
     * @param sourceName the uri of the script source responsible for the error.
     *                   It should not be <tt>null</tt>.
     *
     * @throws IllegalStateException if the method is called more then once.
     */
    public final void initSourceName(String sourceName)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[11]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[12]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[13]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[14]++;}
        this.sourceName = sourceName;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[20]++;
    }

    /**
     * Returns the line number of the statement causing the error,
     * or zero if not available.
     */
    public final int lineNumber()
    {
        return lineNumber;
    }

    /**
     * Initialize the line number of the script statement causing the error.
     *
     * @param lineNumber the line number in the script source.
     *                   It should be positive number.
     *
     * @throws IllegalStateException if the method is called more then once.
     */
    public final void initLineNumber(int lineNumber)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lineNumber <= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[15]++; throw new IllegalArgumentException(String.valueOf(lineNumber));
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[16]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.lineNumber > 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[17]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[18]++;}
        this.lineNumber = lineNumber;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[23]++;
    }

    /**
     * The column number of the location of the error, or zero if unknown.
     */
    public final int columnNumber()
    {
        return columnNumber;
    }

    /**
     * Initialize the column number of the script statement causing the error.
     *
     * @param columnNumber the column number in the script source.
     *                     It should be positive number.
     *
     * @throws IllegalStateException if the method is called more then once.
     */
    public final void initColumnNumber(int columnNumber)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[24]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((columnNumber <= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[19]++; throw new IllegalArgumentException(String.valueOf(columnNumber));
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[20]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[25]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.columnNumber > 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[21]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[22]++;}
        this.columnNumber = columnNumber;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[26]++;
    }

    /**
     * The source text of the line causing the error, or null if unknown.
     */
    public final String lineSource()
    {
        return lineSource;
    }

    /**
     * Initialize the text of the source line containing the error.
     *
     * @param lineSource the text of the source line responsible for the error.
     *                   It should not be <tt>null</tt>.
     *
     * @throws IllegalStateException if the method is called more then once.
     */
    public final void initLineSource(String lineSource)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[27]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lineSource == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[23]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[24]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[28]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.lineSource != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[25]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[26]++;}
        this.lineSource = lineSource;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[29]++;
    }

    final void recordErrorOrigin(String sourceName, int lineNumber,
                                 String lineSource, int columnNumber)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[30]++;
int CodeCoverConditionCoverageHelper_C14;
        // XXX: for compatibility allow for now -1 to mean 0
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lineNumber == -1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[27]++;
            lineNumber = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[31]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[28]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[32]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[29]++;
            initSourceName(sourceName);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[33]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[30]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[34]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((lineNumber != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[31]++;
            initLineNumber(lineNumber);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[35]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[32]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[36]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((lineSource != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[33]++;
            initLineSource(lineSource);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[37]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[34]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[38]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((columnNumber != 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[35]++;
            initColumnNumber(columnNumber);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[39]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[36]++;}
    }

    private String generateStackTrace()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[40]++;
        // Get stable reference to work properly with concurrent access
        CharArrayWriter writer = new CharArrayWriter();
        super.printStackTrace(new PrintWriter(writer));
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[41]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[42]++;
        String origStackTrace = writer.toString();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[43]++;
        Evaluator e = Context.createInterpreter();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[44]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((e != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[37]++;
            return e.getPatchedStack(this, origStackTrace);
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[38]++;}
        return null;
    }

    /**
     * Get a string representing the script stack of this exception.
     * If optimization is enabled, this includes java stack elements
     * whose source and method names suggest they have been generated
     * by the Rhino script compiler.
     * @return a script stack dump
     * @since 1.6R6
     */
    public String getScriptStackTrace()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[45]++;
        StringBuilder buffer = new StringBuilder();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[46]++;
        String lineSeparator = SecurityUtilities.getSystemProperty("line.separator");
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[47]++;
        ScriptStackElement[] stack = getScriptStack();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[48]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[1]++;


        for (ScriptStackElement elem : stack) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[1]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[2]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[3]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[49]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((useMozillaStackStyle) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[39]++;
                elem.renderMozillaStyle(buffer);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[50]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[40]++;
                elem.renderJavaStyle(buffer);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[51]++;
            }
            buffer.append(lineSeparator);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[52]++;
        }
        return buffer.toString();
    }

    /**
     * Get a string representing the script stack of this exception.
     * @deprecated the filter argument is ignored as we are able to
     * recognize script stack elements by our own. Use
     * #getScriptStackTrace() instead.
     * @param filter ignored
     * @return a script stack dump
     * @since 1.6R6
     */
    public String getScriptStackTrace(FilenameFilter filter)
    {
        return getScriptStackTrace();
    }

    /**
     * Get the script stack of this exception as an array of
     * {@link ScriptStackElement}s.
     * If optimization is enabled, this includes java stack elements
     * whose source and method names suggest they have been generated
     * by the Rhino script compiler.
     * @return the script stack for this exception
     * @since 1.7R3
     */
    public ScriptStackElement[] getScriptStack() {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[53]++;
        List<ScriptStackElement> list = new ArrayList<ScriptStackElement>();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[54]++;
        ScriptStackElement[][] interpreterStack = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[55]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((interpreterStackInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[41]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[56]++;
            Evaluator interpreter = Context.createInterpreter();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[57]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((interpreter instanceof Interpreter) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[43]++;
                interpreterStack = ((Interpreter) interpreter).getScriptStackElements(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[58]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[42]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[59]++;
        int interpreterStackIndex = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[60]++;
        StackTraceElement[] stack = getStackTrace();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[61]++;
        // Pattern to recover function name from java method name -
        // see Codegen.getBodyMethodName()
        // kudos to Marc Guillemot for coming up with this
        Pattern pattern = Pattern.compile("_c_(.*)_\\d+");
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[62]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[4]++;


        for (StackTraceElement e : stack) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[4]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[5]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[6]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[63]++;
            String fileName = e.getFileName();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[64]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (128)) == 0 || true) &&
 ((e.getMethodName().startsWith("_c_")) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((e.getLineNumber() > -1) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((fileName != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((fileName.endsWith(".java")) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 4) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 4) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[45]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[65]++;
                String methodName = e.getMethodName();
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[66]++;
                Matcher match = pattern.matcher(methodName);
                // the method representing the main script is always "_c_script_0" -
                // at least we hope so
                methodName = !"_c_script_0".equals(methodName) && match.find() ?
                        match.group(1) : null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[67]++;
                list.add(new ScriptStackElement(fileName, methodName, e.getLineNumber()));
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[68]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[46]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[69]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (128)) == 0 || true) &&
 (("org.mozilla.javascript.Interpreter".equals(e.getClassName())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 (("interpretLoop".equals(e.getMethodName())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((interpreterStack != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((interpreterStack.length > interpreterStackIndex) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 4) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 4) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[47]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[70]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[7]++;


                for (ScriptStackElement elem : interpreterStack[interpreterStackIndex++]) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[7]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[8]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.loops[9]++;
}
                    list.add(elem);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[71]++;
                }

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[48]++;}
}
        }
        return list.toArray(new ScriptStackElement[list.size()]);
    }


    @Override
    public void printStackTrace(PrintWriter s)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[72]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((interpreterStackInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[49]++;
            super.printStackTrace(s);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[73]++;

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[50]++;
            s.print(generateStackTrace());
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[74]++;
        }
    }

    @Override
    public void printStackTrace(PrintStream s)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[75]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((interpreterStackInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[51]++;
            super.printStackTrace(s);
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[76]++;

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.branches[52]++;
            s.print(generateStackTrace());
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[77]++;
        }
    }

    /**
     * Returns true if subclasses of <code>RhinoException</code>
     * use the Mozilla/Firefox style of rendering script stacks
     * (<code>functionName()@fileName:lineNumber</code>)
     * instead of Rhino's own Java-inspired format
     * (<code>    at fileName:lineNumber (functionName)</code>).
     * @return true if stack is rendered in Mozilla/Firefox style
     * @see ScriptStackElement
     * @since 1.7R3
     */
    public static boolean usesMozillaStackStyle() {
        return useMozillaStackStyle;
    }

    /**
     * Tell subclasses of <code>RhinoException</code> whether to
     * use the Mozilla/Firefox style of rendering script stacks
     * (<code>functionName()@fileName:lineNumber</code>)
     * instead of Rhino's own Java-inspired format
     * (<code>    at fileName:lineNumber (functionName)</code>)
     * @param flag whether to render stacks in Mozilla/Firefox style
     * @see ScriptStackElement
     * @since 1.7R3
     */
    public static void useMozillaStackStyle(boolean flag) {
        useMozillaStackStyle = flag;
CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[78]++;
    }

    static final long serialVersionUID = 1883500631321581169L;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[79]++;
  }
    
    private static boolean useMozillaStackStyle = false;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9.statements[80]++;
  }

    private String sourceName;
    private int lineNumber;
    private String lineSource;
    private int columnNumber;

    Object interpreterStackInfo;
    int[] interpreterLineData;
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9 ());
  }
    public static long[] statements = new long[81];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-RhinoException.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,1,1};
    for (int i = 1; i <= 26; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevwnvcgmzue9qntw3b7rrrcm80u99i9 () {
    super("org.mozilla.javascript.RHINO-SRC-RhinoException.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 80; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-RhinoException.java");
      for (int i = 1; i <= 80; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
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

