/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools;

import org.mozilla.javascript.*;

import java.text.MessageFormat;
import java.io.*;
import java.util.*;

/**
 * Error reporter for tools.
 *
 * Currently used by both the shell and the compiler.
 */
public class ToolErrorReporter implements ErrorReporter {
  static {
    CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.ping();
  }


    public ToolErrorReporter(boolean reportWarnings) {
        this(reportWarnings, System.err);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[1]++;
    }

    public ToolErrorReporter(boolean reportWarnings, PrintStream err) {
        this.reportWarnings = reportWarnings;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[2]++;
        this.err = err;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[3]++;
    }

    /**
     * Look up the message corresponding to messageId in the
     * org.mozilla.javascript.tools.shell.resources.Messages property file.
     * For internationalization support.
     */
    public static String getMessage(String messageId) {
        return getMessage(messageId, (Object []) null);
    }

    public static String getMessage(String messageId, String argument) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[4]++;
        Object[] args = { argument };
        return getMessage(messageId, args);
    }

    public static String getMessage(String messageId, Object arg1, Object arg2)
    {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[5]++;
        Object[] args = { arg1, arg2 };
        return getMessage(messageId, args);
    }

    public static String getMessage(String messageId, Object[] args) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[6]++;
        Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[7]++;
        Locale locale = cx == null ? Locale.getDefault() : cx.getLocale();
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[8]++;

        // ResourceBundle does caching.
        ResourceBundle rb = ResourceBundle.getBundle
            ("org.mozilla.javascript.tools.resources.Messages", locale);

        String formatString;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[9]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            formatString = rb.getString(messageId);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[10]++;
        } catch (java.util.MissingResourceException mre) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[2]++;
            throw new RuntimeException("no message resource found for message property "
                                       + messageId);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[1]++;
}
  }
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[3]++;
            return formatString;

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[4]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[12]++;
            MessageFormat formatter = new MessageFormat(formatString);
            return formatter.format(args);
        }
    }

    private static String getExceptionMessage(RhinoException ex)
    {
        String msg;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ex instanceof JavaScriptException) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[5]++;
            msg = getMessage("msg.uncaughtJSException", ex.details());
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[14]++;

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[6]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[15]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ex instanceof EcmaError) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[7]++;
            msg = getMessage("msg.uncaughtEcmaError", ex.details());
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[16]++;

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[8]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[17]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ex instanceof EvaluatorException) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[9]++;
            msg = ex.details();
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[18]++;

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[10]++;
            msg = ex.toString();
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[19]++;
        }
}
}
        return msg;
    }

    public void warning(String message, String sourceName, int line,
                        String lineSource, int lineOffset)
    {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((reportWarnings) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[11]++;
            return;
} else {
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[12]++;}
        reportErrorMessage(message, sourceName, line, lineSource, lineOffset,
                           true);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[21]++;
    }

    public void error(String message, String sourceName, int line,
                      String lineSource, int lineOffset)
    {
        hasReportedErrorFlag = true;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[22]++;
        reportErrorMessage(message, sourceName, line, lineSource, lineOffset,
                           false);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[23]++;
    }

    public EvaluatorException runtimeError(String message, String sourceName,
                                           int line, String lineSource,
                                           int lineOffset)
    {
        return new EvaluatorException(message, sourceName, line,
                                      lineSource, lineOffset);
    }

    public boolean hasReportedError() {
        return hasReportedErrorFlag;
    }

    public boolean isReportingWarnings() {
        return this.reportWarnings;
    }

    public void setIsReportingWarnings(boolean reportWarnings) {
        this.reportWarnings = reportWarnings;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[24]++;
    }

    public static void reportException(ErrorReporter er, RhinoException ex)
    {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((er instanceof ToolErrorReporter) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[13]++;
            ((ToolErrorReporter)er).reportException(ex);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[26]++;

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[14]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[27]++;
            String msg = getExceptionMessage(ex);
            er.error(msg, ex.sourceName(), ex.lineNumber(),
                     ex.lineSource(), ex.columnNumber());
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[28]++;
        }
    }

    public void reportException(RhinoException ex)
    {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ex instanceof WrappedException) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[15]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[30]++;
            WrappedException we = (WrappedException)ex;
            we.printStackTrace(err);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[31]++;

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[16]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[32]++;
            String lineSeparator =
                SecurityUtilities.getSystemProperty("line.separator");
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[33]++;
            String msg = getExceptionMessage(ex) + lineSeparator +
                ex.getScriptStackTrace();
            reportErrorMessage(msg, ex.sourceName(), ex.lineNumber(),
                               ex.lineSource(), ex.columnNumber(), false);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[34]++;
        }
    }

    private void reportErrorMessage(String message, String sourceName, int line,
                                    String lineSource, int lineOffset,
                                    boolean justWarning)
    {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((line > 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[17]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[36]++;
            String lineStr = String.valueOf(line);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[19]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[38]++;
                Object[] args = { sourceName, lineStr, message };
                message = getMessage("msg.format3", args);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[39]++;

            } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[20]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[40]++;
                Object[] args = { lineStr, message };
                message = getMessage("msg.format2", args);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[41]++;
            }

        } else {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[18]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[42]++;
            Object[] args = { message };
            message = getMessage("msg.format1", args);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[43]++;
        }
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((justWarning) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[21]++;
            message = getMessage("msg.warning", message);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[45]++;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[22]++;}
        err.println(messagePrefix + message);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[46]++;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((null != lineSource) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[23]++;
            err.println(messagePrefix + lineSource);
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[48]++;
            err.println(messagePrefix + buildIndicator(lineOffset));
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[49]++;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.branches[24]++;}
    }

    private String buildIndicator(int offset){
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[50]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[51]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < offset-1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.loops[1]--;
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.loops[2]--;
  CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.loops[3]++;
}
            sb.append(".");
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[52]++;
  }
        sb.append("^");
CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[53]++;
        return sb.toString();
    }

    private final static String messagePrefix = "js: ";
  static {
    CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9.statements[54]++;
  }
    private boolean hasReportedErrorFlag;
    private boolean reportWarnings;
    private PrintStream err;
}

class CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9 ());
  }
    public static long[] statements = new long[55];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.RHINO-TOO-ToolErrorReporter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$21tu3emdl0lib608zkpe2j1hqtmnp0h15nbvvn1xesqbyqo2u9 () {
    super("org.mozilla.javascript.tools.RHINO-TOO-ToolErrorReporter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 54; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.RHINO-TOO-ToolErrorReporter.java");
      for (int i = 1; i <= 54; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

