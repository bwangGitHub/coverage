/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.debugger;

import org.mozilla.javascript.*;
import org.mozilla.javascript.debug.*;
import java.util.*;
import java.io.*;
import java.net.URL;

/**
 * Dim or Debugger Implementation for Rhino.
 */
public class Dim {
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.ping();
  }


    // Constants for instructing the debugger what action to perform
    // to end interruption.  Used by 'returnValue'.
    public static final int STEP_OVER = 0;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[1]++;
  }
    public static final int STEP_INTO = 1;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[2]++;
  }
    public static final int STEP_OUT = 2;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[3]++;
  }
    public static final int GO = 3;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[4]++;
  }
    public static final int BREAK = 4;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[5]++;
  }
    public static final int EXIT = 5;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[6]++;
  }

    // Constants for the DimIProxy interface implementation class.
    private static final int IPROXY_DEBUG = 0;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[7]++;
  }
    private static final int IPROXY_LISTEN = 1;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[8]++;
  }
    private static final int IPROXY_COMPILE_SCRIPT = 2;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[9]++;
  }
    private static final int IPROXY_EVAL_SCRIPT = 3;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[10]++;
  }
    private static final int IPROXY_STRING_IS_COMPILABLE = 4;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[11]++;
  }
    private static final int IPROXY_OBJECT_TO_STRING = 5;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[12]++;
  }
    private static final int IPROXY_OBJECT_PROPERTY = 6;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[13]++;
  }
    private static final int IPROXY_OBJECT_IDS = 7;
  static {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[14]++;
  }

    /**
     * Interface to the debugger GUI.
     */
    private GuiCallback callback;

    /**
     * Whether the debugger should break.
     */
    private boolean breakFlag;

    /**
     * The ScopeProvider object that provides the scope in which to
     * evaluate script.
     */
    private ScopeProvider scopeProvider;

    /**
     * The SourceProvider object that provides the source of evaluated scripts.
     */
    private SourceProvider sourceProvider;

    /**
     * The index of the current stack frame.
     */
    private int frameIndex = -1;
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[15]++;
  }

    /**
     * Information about the current stack at the point of interruption.
     */
    private volatile ContextData interruptedContextData;

    /**
     * The ContextFactory to listen to for debugging information.
     */
    private ContextFactory contextFactory;

    /**
     * Synchronization object used to allow script evaluations to
     * happen when a thread is resumed.
     */
    private Object monitor = new Object();
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[16]++;
  }

    /**
     * Synchronization object used to wait for valid
     * {@link #interruptedContextData}.
     */
    private Object eventThreadMonitor = new Object();
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[17]++;
  }

    /**
     * The action to perform to end the interruption loop.
     */
    private volatile int returnValue = -1;
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[18]++;
  }

    /**
     * Whether the debugger is inside the interruption loop.
     */
    private boolean insideInterruptLoop;

    /**
     * The requested script string to be evaluated when the thread
     * has been resumed.
     */
    private String evalRequest;

    /**
     * The stack frame in which to evaluate {@link #evalRequest}.
     */
    private StackFrame evalFrame;

    /**
     * The result of evaluating {@link #evalRequest}.
     */
    private String evalResult;

    /**
     * Whether the debugger should break when a script exception is thrown.
     */
    private boolean breakOnExceptions;

    /**
     * Whether the debugger should break when a script function is entered.
     */
    private boolean breakOnEnter;

    /**
     * Whether the debugger should break when a script function is returned
     * from.
     */
    private boolean breakOnReturn;

    /**
     * Table mapping URLs to information about the script source.
     */
    private final Map<String,SourceInfo> urlToSourceInfo =
        Collections.synchronizedMap(new HashMap<String,SourceInfo>());
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[19]++;
  }

    /**
     * Table mapping function names to information about the function.
     */
    private final Map<String,FunctionSource> functionNames =
        Collections.synchronizedMap(new HashMap<String,FunctionSource>());
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[20]++;
  }

    /**
     * Table mapping functions to information about the function.
     */
    private final Map<DebuggableScript,FunctionSource> functionToSource =
        Collections.synchronizedMap(new HashMap<DebuggableScript,FunctionSource>());
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[21]++;
  }

    /**
     * ContextFactory.Listener instance attached to {@link #contextFactory}.
     */
    private DimIProxy listener;

    /**
     * Sets the GuiCallback object to use.
     */
    public void setGuiCallback(GuiCallback callback) {
        this.callback = callback;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[22]++;
    }

    /**
     * Tells the debugger to break at the next opportunity.
     */
    public void setBreak() {
        this.breakFlag = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[23]++;
    }

    /**
     * Sets the ScopeProvider to be used.
     */
    public void setScopeProvider(ScopeProvider scopeProvider) {
        this.scopeProvider = scopeProvider;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[24]++;
    }

    /**
     * Sets the ScopeProvider to be used.
     */
    public void setSourceProvider(final SourceProvider sourceProvider) {
        this.sourceProvider = sourceProvider;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[25]++;
    }

    /**
     * Switches context to the stack frame with the given index.
     */
    public void contextSwitch(int frameIndex) {
        this.frameIndex = frameIndex;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[26]++;
    }

    /**
     * Sets whether the debugger should break on exceptions.
     */
    public void setBreakOnExceptions(boolean breakOnExceptions) {
        this.breakOnExceptions = breakOnExceptions;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[27]++;
    }

    /**
     * Sets whether the debugger should break on function entering.
     */
    public void setBreakOnEnter(boolean breakOnEnter) {
        this.breakOnEnter = breakOnEnter;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[28]++;
    }

    /**
     * Sets whether the debugger should break on function return.
     */
    public void setBreakOnReturn(boolean breakOnReturn) {
        this.breakOnReturn = breakOnReturn;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[29]++;
    }

    /**
     * Attaches the debugger to the given ContextFactory.
     */
    public void attachTo(ContextFactory factory) {
        detach();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[30]++;
        this.contextFactory = factory;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[31]++;
        this.listener = new DimIProxy(this, IPROXY_LISTEN);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[32]++;
        factory.addListener(this.listener);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[33]++;
    }

    /**
     * Detaches the debugger from the current ContextFactory.
     */
    public void detach() {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[34]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((listener != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[1]++;
            contextFactory.removeListener(listener);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[35]++;
            contextFactory = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[36]++;
            listener = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[37]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[2]++;}
    }

    /**
     * Releases resources associated with this debugger.
     */
    public void dispose() {
        detach();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[38]++;
    }

    /**
     * Returns the FunctionSource object for the given script or function.
     */
    private FunctionSource getFunctionSource(DebuggableScript fnOrScript) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[39]++;
        FunctionSource fsource = functionSource(fnOrScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[40]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fsource == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[3]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[41]++;
            String url = getNormalizedUrl(fnOrScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[42]++;
            SourceInfo si = sourceInfo(url);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[43]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((si == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[5]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[44]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((fnOrScript.isGeneratedScript()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[7]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[45]++;
                    // Not eval or Function, try to load it from URL
                    String source = loadSource(url);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[46]++;
int CodeCoverConditionCoverageHelper_C5;
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((source != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[9]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[47]++;
                        DebuggableScript top = fnOrScript;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[48]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[1]++;


                        for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[1]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[2]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[3]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[49]++;
                            DebuggableScript parent = top.getParent();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[50]++;
int CodeCoverConditionCoverageHelper_C7;
                            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[11]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[51]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[12]++;}
                            top = parent;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[52]++;
                        }
                        registerTopScript(top, source);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[53]++;
                        fsource = functionSource(fnOrScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[54]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[10]++;}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[8]++;}

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[4]++;}
        return fsource;
    }

    /**
     * Loads the script at the given URL.
     */
    private String loadSource(String sourceUrl) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[55]++;
        String source = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[56]++;
        int hash = sourceUrl.indexOf('#');
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[57]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hash >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[13]++;
            sourceUrl = sourceUrl.substring(0, hash);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[58]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[14]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[59]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            InputStream is;
          openStream:
            {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[60]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sourceUrl.indexOf(':') < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[16]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[61]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                    // Can be a file name
                    try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[62]++;
int CodeCoverConditionCoverageHelper_C10;
                        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((sourceUrl.startsWith("~/")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[19]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[63]++;
                            String home = SecurityUtilities.getSystemProperty("user.home");
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[64]++;
int CodeCoverConditionCoverageHelper_C11;
                            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((home != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[21]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[65]++;
                                String pathFromHome = sourceUrl.substring(2);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[66]++;
                                File f = new File(new File(home), pathFromHome);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[67]++;
int CodeCoverConditionCoverageHelper_C12;
                                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((f.exists()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[23]++;
                                    is = new FileInputStream(f);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[68]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[69]++;
                                    break openStream;

                                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[24]++;}

                            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[22]++;}

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[20]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[70]++;
                        File f = new File(sourceUrl);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[71]++;
int CodeCoverConditionCoverageHelper_C13;
                        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((f.exists()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[25]++;
                            is = new FileInputStream(f);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[72]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[73]++;
                            break openStream;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[26]++;}
                    } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[27]++; } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[18]++;
}
  }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
                    // No existing file, assume missed http://
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((sourceUrl.startsWith("//")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[28]++;
                        sourceUrl = "http:" + sourceUrl;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[75]++;

                    } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[29]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[76]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((sourceUrl.startsWith("/")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[30]++;
                        sourceUrl = "http://127.0.0.1" + sourceUrl;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[77]++;

                    } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[31]++;
                        sourceUrl = "http://" + sourceUrl;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[78]++;
                    }
}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[17]++;}

                is = (new URL(sourceUrl)).openStream();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[79]++;
            }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[80]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

            try {
CodeCoverTryBranchHelper_Try3 = true;
                source = Kit.readReader(new InputStreamReader(is));
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[81]++;
            } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[32]++;
}
                is.close();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[82]++;
            }
        } catch (IOException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[33]++;
            System.err.println
                ("Failed to load source from "+sourceUrl+": "+ ex);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[83]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[15]++;
}
  }
        return source;
    }

    /**
     * Registers the given script as a top-level script in the debugger.
     */
    private void registerTopScript(DebuggableScript topScript, String source) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[84]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((topScript.isTopLevel()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[34]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[35]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[85]++;
        String url = getNormalizedUrl(topScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[86]++;
        DebuggableScript[] functions = getAllFunctions(topScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[87]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((sourceProvider != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[36]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[88]++;
            final String providedSource = sourceProvider.getSource(topScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[89]++;
int CodeCoverConditionCoverageHelper_C18;
            if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((providedSource != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[38]++;
                source = providedSource;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[90]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[39]++;}

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[37]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[91]++;

        final SourceInfo sourceInfo = new SourceInfo(source, functions, url);

        synchronized (urlToSourceInfo) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[92]++;
            SourceInfo old = urlToSourceInfo.get(url);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[93]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((old != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[40]++;
                sourceInfo.copyBreakpointsFrom(old);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[94]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[41]++;}
            urlToSourceInfo.put(url, sourceInfo);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[95]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[96]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i != sourceInfo.functionSourcesTop()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[4]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[5]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[6]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[97]++;
                FunctionSource fsource = sourceInfo.functionSource(i);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[98]++;
                String name = fsource.name();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[99]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((name.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[42]++;
                    functionNames.put(name, fsource);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[100]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[43]++;}
            }
        }

        synchronized (functionToSource) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[101]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i != functions.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[7]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[8]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[9]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[102]++;
                FunctionSource fsource = sourceInfo.functionSource(i);
                functionToSource.put(functions[i], fsource);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[103]++;
            }
        }

        callback.updateSourceText(sourceInfo);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[104]++;
    }

    /**
     * Returns the FunctionSource object for the given function or script.
     */
    private FunctionSource functionSource(DebuggableScript fnOrScript) {
        return functionToSource.get(fnOrScript);
    }

    /**
     * Returns an array of all function names.
     */
    public String[] functionNames() {
        synchronized (urlToSourceInfo) {
            return functionNames.keySet().toArray(new String[functionNames.size()]);
        }
    }

    /**
     * Returns the FunctionSource object for the function with the given name.
     */
    public FunctionSource functionSourceByName(String functionName) {
        return functionNames.get(functionName);
    }

    /**
     * Returns the SourceInfo object for the given URL.
     */
    public SourceInfo sourceInfo(String url) {
        return urlToSourceInfo.get(url);
    }

    /**
     * Returns the source URL for the given script or function.
     */
    private String getNormalizedUrl(DebuggableScript fnOrScript) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[105]++;
        String url = fnOrScript.getSourceName();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[106]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((url == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[44]++; url = "<stdin>";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[107]++;
 }
        else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[45]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[108]++;
            // Not to produce window for eval from different lines,
            // strip line numbers, i.e. replace all #[0-9]+\(eval\) by
            // (eval)
            // Option: similar teatment for Function?
            char evalSeparator = '#';
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[109]++;
            StringBuffer sb = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[110]++;
            int urlLength = url.length();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[111]++;
            int cursor = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[112]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[10]++;


            for (;;) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[10]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[11]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[12]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[113]++;
                int searchStart = url.indexOf(evalSeparator, cursor);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[114]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((searchStart < 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[46]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[115]++;
                    break;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[47]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[116]++;
                String replace = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[117]++;
                int i = searchStart + 1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[118]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[13]++;


int CodeCoverConditionCoverageHelper_C26;
                while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i != urlLength) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[13]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[14]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[15]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[119]++;
                    int c = url.charAt(i);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[120]++;
int CodeCoverConditionCoverageHelper_C27;
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[48]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[121]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[49]++;}
                    ++i;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[122]++;
                }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[123]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i != searchStart + 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[50]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[124]++;
int CodeCoverConditionCoverageHelper_C29;
                    // i points after #[0-9]+
                    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 (("(eval)".regionMatches(0, url, i, 6)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[52]++;
                        cursor = i + 6;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[125]++;
                        replace = "(eval)";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[126]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[53]++;}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[51]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[127]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((replace == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[54]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[128]++;
                    break;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[55]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[129]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[56]++;
                    sb = new StringBuffer();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[130]++;
                    sb.append(url.substring(0, searchStart));
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[131]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[57]++;}
                sb.append(replace);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[132]++;
            }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[133]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[58]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[134]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cursor != urlLength) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[60]++;
                    sb.append(url.substring(cursor));
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[135]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[61]++;}
                url = sb.toString();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[136]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[59]++;}
        }
        return url;
    }

    /**
     * Returns an array of all functions in the given script.
     */
    private static DebuggableScript[] getAllFunctions
            (DebuggableScript function) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[137]++;
        ObjArray functions = new ObjArray();
        collectFunctions_r(function, functions);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[138]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[139]++;
        DebuggableScript[] result = new DebuggableScript[functions.size()];
        functions.toArray(result);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[140]++;
        return result;
    }

    /**
     * Helper function for {@link #getAllFunctions(DebuggableScript)}.
     */
    private static void collectFunctions_r(DebuggableScript function,
                                             ObjArray array) {
        array.add(function);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[141]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[142]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[16]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i != function.getFunctionCount()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[16]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[17]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[18]++;
}
            collectFunctions_r(function.getFunction(i), array);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[143]++;
        }
    }

    /**
     * Clears all breakpoints.
     */
    public void clearAllBreakpoints() {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[144]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[19]++;


        for (SourceInfo si: urlToSourceInfo.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[19]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[20]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[21]++;
}
            si.removeAllBreakpoints();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[145]++;
        }
    }

    /**
     * Called when a breakpoint has been hit.
     */
    private void handleBreakpointHit(StackFrame frame, Context cx) {
        breakFlag = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[146]++;
        interrupted(cx, frame, null);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[147]++;
    }

    /**
     * Called when a script exception has been thrown.
     */
    private void handleExceptionThrown(Context cx, Throwable ex,
                                         StackFrame frame) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[148]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((breakOnExceptions) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[62]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[149]++;
            ContextData cd = frame.contextData();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[150]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((cd.lastProcessedException != ex) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[64]++;
                interrupted(cx, frame, ex);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[151]++;
                cd.lastProcessedException = ex;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[152]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[65]++;}

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[63]++;}
    }

    /**
     * Returns the current ContextData object.
     */
    public ContextData currentContextData() {
        return interruptedContextData;
    }

    /**
     * Sets the action to perform to end interruption.
     */
    public void setReturnValue(int returnValue) {
        synchronized (monitor) {
            this.returnValue = returnValue;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[153]++;
            monitor.notify();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[154]++;
        }
    }

    /**
     * Resumes execution of script.
     */
    public void go() {
        synchronized (monitor) {
            this.returnValue = GO;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[155]++;
            monitor.notifyAll();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[156]++;
        }
    }

    /**
     * Evaluates the given script.
     */
    public String eval(String expr) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[157]++;
        String result = "undefined";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[158]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((expr == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[66]++;
            return result;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[67]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[159]++;
        ContextData contextData = currentContextData();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[160]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((contextData == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((frameIndex >= contextData.frameCount()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[68]++;
            return result;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[69]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[161]++;
        StackFrame frame = contextData.getFrame(frameIndex);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[162]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((contextData.eventThreadFlag) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[70]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[163]++;
            Context cx = Context.getCurrentContext();
            result = do_eval(cx, frame, expr);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[164]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[71]++;
            synchronized (monitor) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[165]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((insideInterruptLoop) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[72]++;
                    evalRequest = expr;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[166]++;
                    evalFrame = frame;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[167]++;
                    monitor.notify();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[168]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[169]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[22]++;


int CodeCoverConditionCoverageHelper_C41;
                    do {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[22]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[23]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[24]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[170]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                        try {
CodeCoverTryBranchHelper_Try4 = true;
                            monitor.wait();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[171]++;
                        } catch (InterruptedException exc) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[75]++;
                            Thread.currentThread().interrupt();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[172]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[173]++;
                            break;
                        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[74]++;
}
  }
                    } while ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((evalRequest != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false));
                    result = evalResult;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[174]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[73]++;}
            }
        }
        return result;
    }

    /**
     * Compiles the given script.
     */
    public void compileScript(String url, String text) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[175]++;
        DimIProxy action = new DimIProxy(this, IPROXY_COMPILE_SCRIPT);
        action.url = url;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[176]++;
        action.text = text;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[177]++;
        action.withContext();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[178]++;
    }

    /**
     * Evaluates the given script.
     */
    public void evalScript(final String url, final String text) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[179]++;
        DimIProxy action = new DimIProxy(this, IPROXY_EVAL_SCRIPT);
        action.url = url;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[180]++;
        action.text = text;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[181]++;
        action.withContext();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[182]++;
    }

    /**
     * Converts the given script object to a string.
     */
    public String objectToString(Object object) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[183]++;
        DimIProxy action = new DimIProxy(this, IPROXY_OBJECT_TO_STRING);
        action.object = object;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[184]++;
        action.withContext();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[185]++;
        return action.stringResult;
    }

    /**
     * Returns whether the given string is syntactically valid script.
     */
    public boolean stringIsCompilableUnit(String str) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[186]++;
        DimIProxy action = new DimIProxy(this, IPROXY_STRING_IS_COMPILABLE);
        action.text = str;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[187]++;
        action.withContext();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[188]++;
        return action.booleanResult;
    }

    /**
     * Returns the value of a property on the given script object.
     */
    public Object getObjectProperty(Object object, Object id) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[189]++;
        DimIProxy action = new DimIProxy(this, IPROXY_OBJECT_PROPERTY);
        action.object = object;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[190]++;
        action.id = id;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[191]++;
        action.withContext();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[192]++;
        return action.objectResult;
    }

    /**
     * Returns an array of the property names on the given script object.
     */
    public Object[] getObjectIds(Object object) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[193]++;
        DimIProxy action = new DimIProxy(this, IPROXY_OBJECT_IDS);
        action.object = object;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[194]++;
        action.withContext();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[195]++;
        return action.objectArrayResult;
    }

    /**
     * Returns the value of a property on the given script object.
     */
    private Object getObjectPropertyImpl(Context cx, Object object,
                                           Object id) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[196]++;
        Scriptable scriptable = (Scriptable)object;
        Object result;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[197]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[76]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[198]++;
            String name = (String)id;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[199]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((name.equals("this")) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[78]++;
                result = scriptable;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[200]++;

            } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[79]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[201]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((name.equals("__proto__")) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[80]++;
                result = scriptable.getPrototype();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[202]++;

            } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[81]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[203]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((name.equals("__parent__")) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[82]++;
                result = scriptable.getParentScope();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[204]++;

            } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[83]++;
                result = ScriptableObject.getProperty(scriptable, name);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[205]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[206]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((result == ScriptableObject.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[84]++;
                    result = Undefined.instance;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[207]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[85]++;}
            }
}
}

        } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[77]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[208]++;
            int index = ((Integer)id).intValue();
            result = ScriptableObject.getProperty(scriptable, index);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[209]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[210]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((result == ScriptableObject.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[86]++;
                result = Undefined.instance;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[211]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[87]++;}
        }
        return result;
    }

    /**
     * Returns an array of the property names on the given script object.
     */
    private Object[] getObjectIdsImpl(Context cx, Object object) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[212]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((object instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((object == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[88]++;
            return Context.emptyArgs;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[89]++;}

        Object[] ids;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[213]++;
        Scriptable scriptable = (Scriptable)object;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[214]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((scriptable instanceof DebuggableObject) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[90]++;
            ids = ((DebuggableObject)scriptable).getAllIds();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[215]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[91]++;
            ids = scriptable.getIds();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[216]++;
        }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[217]++;

        Scriptable proto = scriptable.getPrototype();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[218]++;
        Scriptable parent = scriptable.getParentScope();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[219]++;
        int extra = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[220]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((proto != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[92]++;
            ++extra;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[221]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[93]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[222]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[94]++;
            ++extra;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[223]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[95]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[224]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((extra != 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[96]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[225]++;
            Object[] tmp = new Object[extra + ids.length];
            System.arraycopy(ids, 0, tmp, extra, ids.length);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[226]++;
            ids = tmp;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[227]++;
            extra = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[228]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[229]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((proto != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[98]++;
                ids[extra++] = "__proto__";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[230]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[99]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[231]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[100]++;
                ids[extra++] = "__parent__";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[232]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[101]++;}

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[97]++;}

        return ids;
    }

    /**
     * Interrupts script execution.
     */
    private void interrupted(Context cx, final StackFrame frame,
                               Throwable scriptException) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[233]++;
        ContextData contextData = frame.contextData();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[234]++;
        boolean eventThreadFlag = callback.isGuiEventThread();
        contextData.eventThreadFlag = eventThreadFlag;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[235]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[236]++;

        boolean recursiveEventThreadCall = false;

interruptedCheck:
        synchronized (eventThreadMonitor) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[237]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((eventThreadFlag) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[102]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[238]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((interruptedContextData != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[104]++;
                    recursiveEventThreadCall = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[239]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[240]++;
                    break interruptedCheck;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[105]++;}

            } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[103]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[241]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[25]++;


int CodeCoverConditionCoverageHelper_C57;
                while ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((interruptedContextData != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[25]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[26]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[27]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[242]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                    try {
CodeCoverTryBranchHelper_Try5 = true;
                        eventThreadMonitor.wait();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[243]++;
                    } catch (InterruptedException exc) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[107]++;
                        return;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[106]++;
}
  }
                }
            }
            interruptedContextData = contextData;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[244]++;
        }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[245]++;
int CodeCoverConditionCoverageHelper_C58;

        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((recursiveEventThreadCall) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[108]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[246]++;
            // XXX: For now the following is commented out as on Linux
            // too deep recursion of dispatchNextGuiEvent causes GUI lockout.
            // Note: it can make GUI unresponsive if long-running script
            // will be called on GUI thread while processing another interrupt
            if (false) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[110]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[247]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[28]++;


int CodeCoverConditionCoverageHelper_C60;
               // Run event dispatch until gui sets a flag to exit the initial
               // call to interrupted.
                while ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.returnValue == -1) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[28]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[29]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[30]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[248]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
                    try {
CodeCoverTryBranchHelper_Try6 = true;
                        callback.dispatchNextGuiEvent();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[249]++;
                    } catch (InterruptedException exc) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[113]++;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[112]++;
}
  }
                }

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[111]++;}
            return;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[109]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[250]++;
int CodeCoverConditionCoverageHelper_C61;

        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((interruptedContextData == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[114]++; Kit.codeBug();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[251]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[115]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[252]++;
boolean CodeCoverTryBranchHelper_Try7 = false;

        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[253]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[31]++;


            do {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[31]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[32]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[33]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[254]++;
                int frameCount = contextData.frameCount();
                this.frameIndex = frameCount -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[255]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[256]++;

                final String threadTitle = Thread.currentThread().toString();
                final String alertMessage;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[257]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((scriptException == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[117]++;
                    alertMessage = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[258]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[118]++;
                    alertMessage = scriptException.toString();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[259]++;
                }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[260]++;

                int returnValue = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[261]++;
int CodeCoverConditionCoverageHelper_C64;
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((eventThreadFlag) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[119]++;
                    synchronized (monitor) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[262]++;
int CodeCoverConditionCoverageHelper_C65;
                        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((insideInterruptLoop) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[121]++; Kit.codeBug();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[263]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[122]++;}
                        this.insideInterruptLoop = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[264]++;
                        this.evalRequest = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[265]++;
                        this.returnValue = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[266]++;
                        callback.enterInterrupt(frame, threadTitle,
                                                alertMessage);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[267]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[268]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
                        try {
CodeCoverTryBranchHelper_Try8 = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[269]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[34]++;


                            for (;;) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[34]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[35]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[36]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[270]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
                                try {
CodeCoverTryBranchHelper_Try9 = true;
                                    monitor.wait();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[271]++;
                                } catch (InterruptedException exc) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[125]++;
                                    Thread.currentThread().interrupt();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[272]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[273]++;
                                    break;
                                } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[124]++;
}
  }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[274]++;
int CodeCoverConditionCoverageHelper_C67;
                                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((evalRequest != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[126]++;
                                    this.evalResult = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[275]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[276]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
                                    try {
CodeCoverTryBranchHelper_Try10 = true;
                                        evalResult = do_eval(cx, evalFrame,
                                                             evalRequest);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[277]++;
                                    } finally {
if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[128]++;
}
                                        evalRequest = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[278]++;
                                        evalFrame = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[279]++;
                                        monitor.notify();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[280]++;
                                    }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[281]++;
                                    continue;

                                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[127]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[282]++;
int CodeCoverConditionCoverageHelper_C68;
                                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.returnValue != -1) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[129]++;
                                    returnValue = this.returnValue;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[283]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[284]++;
                                    break;

                                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[130]++;}
                            }
                        } finally {
if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[123]++;
}
                            insideInterruptLoop = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[285]++;
                        }
                    }

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[120]++;
                    this.returnValue = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[286]++;
                    callback.enterInterrupt(frame, threadTitle, alertMessage);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[287]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[288]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[37]++;


int CodeCoverConditionCoverageHelper_C69;
                    while ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.returnValue == -1) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[37]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[38]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[39]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[289]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
                        try {
CodeCoverTryBranchHelper_Try11 = true;
                            callback.dispatchNextGuiEvent();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[290]++;
                        } catch (InterruptedException exc) {
CodeCoverTryBranchHelper_Try11 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[132]++;
                        } finally {
    if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[131]++;
}
  }
                    }
                    returnValue = this.returnValue;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[291]++;
                }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[292]++;
                switch (returnValue) {
                case STEP_OVER:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[133]++;
                    contextData.breakNextLine = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[293]++;
                    contextData.stopAtFrameDepth = contextData.frameCount();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[294]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[295]++;
                    break;
                case STEP_INTO:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[134]++;
                    contextData.breakNextLine = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[296]++;
                    contextData.stopAtFrameDepth = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[297]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[298]++;
                    break;
                case STEP_OUT:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[135]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[299]++;
int CodeCoverConditionCoverageHelper_C70;
                    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((contextData.frameCount() > 1) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[136]++;
                        contextData.breakNextLine = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[300]++;
                        contextData.stopAtFrameDepth
                            = contextData.frameCount() -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[301]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[137]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[302]++;
                    break; default : CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[138]++;
                }
            } while (false);
        } finally {
if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[116]++;
}
            synchronized (eventThreadMonitor) {
                interruptedContextData = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[303]++;
                eventThreadMonitor.notifyAll();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[304]++;
            }
        }

    }

    /**
     * Evaluates script in the given stack frame.
     */
    private static String do_eval(Context cx, StackFrame frame, String expr) {
        String resultString;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[305]++;
        Debugger saved_debugger = cx.getDebugger();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[306]++;
        Object saved_data = cx.getDebuggerContextData();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[307]++;
        int saved_level = cx.getOptimizationLevel();

        cx.setDebugger(null, null);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[308]++;
        cx.setOptimizationLevel(-1);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[309]++;
        cx.setGeneratingDebug(false);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[310]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[311]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
        try {
CodeCoverTryBranchHelper_Try12 = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[312]++;
            Callable script = (Callable)cx.compileString(expr, "", 0, null);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[313]++;
            Object result = script.call(cx, frame.scope, frame.thisObj,
                                        ScriptRuntime.emptyArgs);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[314]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((result == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[140]++;
                resultString = "";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[315]++;

            } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[141]++;
                resultString = ScriptRuntime.toString(result);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[316]++;
            }
        } catch (Exception exc) {
CodeCoverTryBranchHelper_Try12 = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[142]++;
            resultString = exc.getMessage();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[317]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[139]++;
}
            cx.setGeneratingDebug(true);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[318]++;
            cx.setOptimizationLevel(saved_level);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[319]++;
            cx.setDebugger(saved_debugger, saved_data);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[320]++;
        }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[321]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((resultString == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[143]++;
            resultString = "null";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[322]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[144]++;}
        return resultString;
    }

    /**
     * Proxy class to implement debug interfaces without bloat of class
     * files.
     */
    private static class DimIProxy
        implements ContextAction, ContextFactory.Listener, Debugger {

        /**
         * The debugger.
         */
        private Dim dim;

        /**
         * The interface implementation type.  One of the IPROXY_* constants
         * defined in {@link Dim}.
         */
        private int type;

        /**
         * The URL origin of the script to compile or evaluate.
         */
        private String url;

        /**
         * The text of the script to compile, evaluate or test for compilation.
         */
        private String text;

        /**
         * The object to convert, get a property from or enumerate.
         */
        private Object object;

        /**
         * The property to look up in {@link #object}.
         */
        private Object id;

        /**
         * The boolean result of the action.
         */
        private boolean booleanResult;

        /**
         * The String result of the action.
         */
        private String stringResult;

        /**
         * The Object result of the action.
         */
        private Object objectResult;

        /**
         * The Object[] result of the action.
         */
        private Object[] objectArrayResult;

        /**
         * Creates a new DimIProxy.
         */
        private DimIProxy(Dim dim, int type) {
            this.dim = dim;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[323]++;
            this.type = type;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[324]++;
        }

        // ContextAction

        /**
         * Performs the action given by {@link #type}.
         */
        public Object run(Context cx) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[325]++;
            switch (type) {
              case IPROXY_COMPILE_SCRIPT:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[145]++;
                cx.compileString(text, url, 1, null);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[326]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[327]++;
                break;

              case IPROXY_EVAL_SCRIPT:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[146]++;
                {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[328]++;
                    Scriptable scope = null;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[329]++;
int CodeCoverConditionCoverageHelper_C73;
                    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((dim.scopeProvider != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[147]++;
                        scope = dim.scopeProvider.getScope();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[330]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[148]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[331]++;
int CodeCoverConditionCoverageHelper_C74;
                    if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[149]++;
                        scope = new ImporterTopLevel(cx);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[332]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[150]++;}
                    cx.evaluateString(scope, text, url, 1, null);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[333]++;
                }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[334]++;
                break;

              case IPROXY_STRING_IS_COMPILABLE:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[151]++;
                booleanResult = cx.stringIsCompilableUnit(text);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[335]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[336]++;
                break;

              case IPROXY_OBJECT_TO_STRING:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[152]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[337]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((object == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[153]++;
                    stringResult = "undefined";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[338]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[154]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[339]++;
int CodeCoverConditionCoverageHelper_C76; if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[155]++;
                    stringResult = "null";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[340]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[156]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[341]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((object instanceof NativeCall) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[157]++;
                    stringResult = "[object Call]";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[342]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[158]++;
                    stringResult = Context.toString(object);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[343]++;
                }
}
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[344]++;
                break;

              case IPROXY_OBJECT_PROPERTY:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[159]++;
                objectResult = dim.getObjectPropertyImpl(cx, object, id);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[345]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[346]++;
                break;

              case IPROXY_OBJECT_IDS:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[160]++;
                objectArrayResult = dim.getObjectIdsImpl(cx, object);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[347]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[348]++;
                break;

              default:
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[161]++;
                throw Kit.codeBug();
            }
            return null;
        }

        /**
         * Performs the action given by {@link #type} with the attached
         * {@link ContextFactory}.
         */
        private void withContext() {
            dim.contextFactory.call(this);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[349]++;
        }

        // ContextFactory.Listener

        /**
         * Called when a Context is created.
         */
        public void contextCreated(Context cx) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[350]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((type != IPROXY_LISTEN) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[162]++; Kit.codeBug();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[351]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[163]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[352]++;
            ContextData contextData = new ContextData();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[353]++;
            Debugger debugger = new DimIProxy(dim, IPROXY_DEBUG);
            cx.setDebugger(debugger, contextData);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[354]++;
            cx.setGeneratingDebug(true);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[355]++;
            cx.setOptimizationLevel(-1);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[356]++;
        }

        /**
         * Called when a Context is destroyed.
         */
        public void contextReleased(Context cx) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[357]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((type != IPROXY_LISTEN) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[164]++; Kit.codeBug();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[358]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[165]++;}
        }

        // Debugger

        /**
         * Returns a StackFrame for the given function or script.
         */
        public DebugFrame getFrame(Context cx, DebuggableScript fnOrScript) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[359]++;
int CodeCoverConditionCoverageHelper_C80;
            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((type != IPROXY_DEBUG) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[166]++; Kit.codeBug();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[360]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[167]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[361]++;

            FunctionSource item = dim.getFunctionSource(fnOrScript);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[362]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[168]++;
                // Can not debug if source is not available
                return null;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[169]++;}
            return new StackFrame(cx, dim, item);
        }

        /**
         * Called when compilation is finished.
         */
        public void handleCompilationDone(Context cx,
                                          DebuggableScript fnOrScript,
                                          String source) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[363]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((type != IPROXY_DEBUG) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[170]++; Kit.codeBug();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[364]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[171]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[365]++;
int CodeCoverConditionCoverageHelper_C83;

            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((fnOrScript.isTopLevel()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[172]++;
                return;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[173]++;}
            dim.registerTopScript(fnOrScript, source);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[366]++;
        }
    }

    /**
     * Class to store information about a stack.
     */
    public static class ContextData {

        /**
         * The stack frames.
         */
        private ObjArray frameStack = new ObjArray();
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[367]++;
  }

        /**
         * Whether the debugger should break at the next line in this context.
         */
        private boolean breakNextLine;

        /**
         * The frame depth the debugger should stop at.  Used to implement
         * "step over" and "step out".
         */
        private int stopAtFrameDepth = -1;
  {
    CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[368]++;
  }

        /**
         * Whether this context is in the event thread.
         */
        private boolean eventThreadFlag;

        /**
         * The last exception that was processed.
         */
        private Throwable lastProcessedException;

        /**
         * Returns the ContextData for the given Context.
         */
        public static ContextData get(Context cx) {
            return (ContextData) cx.getDebuggerContextData();
        }

        /**
         * Returns the number of stack frames.
         */
        public int frameCount() {
            return frameStack.size();
        }

        /**
         * Returns the stack frame with the given index.
         */
        public StackFrame getFrame(int frameNumber) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[369]++;
            int num = frameStack.size() - frameNumber - 1;
            return (StackFrame) frameStack.get(num);
        }

        /**
         * Pushes a stack frame on to the stack.
         */
        private void pushFrame(StackFrame frame) {
            frameStack.push(frame);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[370]++;
        }

        /**
         * Pops a stack frame from the stack.
         */
        private void popFrame() {
            frameStack.pop();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[371]++;
        }
    }

    /**
     * Object to represent one stack frame.
     */
    public static class StackFrame implements DebugFrame {

        /**
         * The debugger.
         */
        private Dim dim;

        /**
         * The ContextData for the Context being debugged.
         */
        private ContextData contextData;

        /**
         * The scope.
         */
        private Scriptable scope;

        /**
         * The 'this' object.
         */
        private Scriptable thisObj;

        /**
         * Information about the function.
         */
        private FunctionSource fsource;

        /**
         * Array of breakpoint state for each source line.
         */
        private boolean[] breakpoints;

        /**
         * Current line number.
         */
        private int lineNumber;

        /**
         * Creates a new StackFrame.
         */
        private StackFrame(Context cx, Dim dim, FunctionSource fsource) {
            this.dim = dim;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[372]++;
            this.contextData = ContextData.get(cx);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[373]++;
            this.fsource = fsource;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[374]++;
            this.breakpoints = fsource.sourceInfo().breakpoints;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[375]++;
            this.lineNumber = fsource.firstLine();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[376]++;
        }

        /**
         * Called when the stack frame is entered.
         */
        public void onEnter(Context cx, Scriptable scope,
                            Scriptable thisObj, Object[] args) {
            contextData.pushFrame(this);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[377]++;
            this.scope = scope;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[378]++;
            this.thisObj = thisObj;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[379]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[380]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((dim.breakOnEnter) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[174]++;
                dim.handleBreakpointHit(this, cx);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[381]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[175]++;}
        }

        /**
         * Called when the current position has changed.
         */
        public void onLineChange(Context cx, int lineno) {
            this.lineNumber = lineno;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[382]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[383]++;
int CodeCoverConditionCoverageHelper_C85;

            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((breakpoints[lineno]) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((dim.breakFlag) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[176]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[384]++;
                boolean lineBreak = contextData.breakNextLine;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[385]++;
int CodeCoverConditionCoverageHelper_C86;
                if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((lineBreak) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((contextData.stopAtFrameDepth >= 0) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[178]++;
                    lineBreak = (contextData.frameCount()
                                 <= contextData.stopAtFrameDepth);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[386]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[179]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[387]++;
int CodeCoverConditionCoverageHelper_C87;
                if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((lineBreak) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[180]++;
                    return;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[181]++;}
                contextData.stopAtFrameDepth = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[388]++;
                contextData.breakNextLine = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[389]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[177]++;}

            dim.handleBreakpointHit(this, cx);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[390]++;
        }

        /**
         * Called when an exception has been thrown.
         */
        public void onExceptionThrown(Context cx, Throwable exception) {
            dim.handleExceptionThrown(cx, exception, this);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[391]++;
        }

        /**
         * Called when the stack frame has been left.
         */
        public void onExit(Context cx, boolean byThrow,
                           Object resultOrException) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[392]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((dim.breakOnReturn) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((byThrow) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[182]++;
                dim.handleBreakpointHit(this, cx);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[393]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[183]++;}
            contextData.popFrame();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[394]++;
        }

        /**
         * Called when a 'debugger' statement is executed.
         */
        public void onDebuggerStatement(Context cx) {
            dim.handleBreakpointHit(this, cx);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[395]++;
        }

        /**
         * Returns the SourceInfo object for the function.
         */
        public SourceInfo sourceInfo() {
            return fsource.sourceInfo();
        }

        /**
         * Returns the ContextData object for the Context.
         */
        public ContextData contextData() {
            return contextData;
        }

        /**
         * Returns the scope object for this frame.
         */
        public Object scope() {
            return scope;
        }

        /**
         * Returns the 'this' object for this frame.
         */
        public Object thisObj() {
            return thisObj;
        }

        /**
         * Returns the source URL.
         */
        public String getUrl() {
            return fsource.sourceInfo().url();
        }

        /**
         * Returns the current line number.
         */
        public int getLineNumber() {
            return lineNumber;
        }
        
        /**
         * Returns the current function name.
         */
        public String getFunctionName() {
            return fsource.name();
        }
    }

    /**
     * Class to store information about a function.
     */
    public static class FunctionSource {

        /**
         * Information about the source of the function.
         */
        private SourceInfo sourceInfo;

        /**
         * Line number of the first line of the function.
         */
        private int firstLine;

        /**
         * The function name.
         */
        private String name;

        /**
         * Creates a new FunctionSource.
         */
        private FunctionSource(SourceInfo sourceInfo, int firstLine,
                                 String name) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[396]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[184]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[185]++;}
            this.sourceInfo = sourceInfo;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[397]++;
            this.firstLine = firstLine;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[398]++;
            this.name = name;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[399]++;
        }

        /**
         * Returns the SourceInfo object that describes the source of the
         * function.
         */
        public SourceInfo sourceInfo() {
            return sourceInfo;
        }

        /**
         * Returns the line number of the first line of the function.
         */
        public int firstLine() {
            return firstLine;
        }

        /**
         * Returns the name of the function.
         */
        public String name() {
            return name;
        }
    }

    /**
     * Class to store information about a script source.
     */
    public static class SourceInfo {

        /**
         * An empty array of booleans.
         */
        private static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];

        /**
         * The script.
         */
        private String source;

        /**
         * The URL of the script.
         */
        private String url;

        /**
         * Array indicating which lines can have breakpoints set.
         */
        private boolean[] breakableLines;

        /**
         * Array indicating whether a breakpoint is set on the line.
         */
        private boolean[] breakpoints;

        /**
         * Array of FunctionSource objects for the functions in the script.
         */
        private FunctionSource[] functionSources;

        /**
         * Creates a new SourceInfo object.
         */
        private SourceInfo(String source, DebuggableScript[] functions,
                             String normilizedUrl) {
            this.source = source;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[400]++;
            this.url = normilizedUrl;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[401]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[402]++;

            int N = functions.length;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[403]++;
            int[][] lineArrays = new int[N][];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[404]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[40]++;


int CodeCoverConditionCoverageHelper_C90;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[40]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[41]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[42]++;
}
                lineArrays[i] = functions[i].getLineNumbers();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[405]++;
            }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[406]++;

            int minAll = 0, maxAll = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[407]++;
            int[] firstLines = new int[N];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[408]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[43]++;


int CodeCoverConditionCoverageHelper_C91;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[43]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[44]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[45]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[409]++;
                int[] lines = lineArrays[i];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[410]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((lines == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((lines.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[186]++;
                    firstLines[i] = -1;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[411]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[187]++;
                    int min, max;
                    min = max = lines[0];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[412]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[413]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[46]++;


int CodeCoverConditionCoverageHelper_C93;
                    for (int j = 1;(((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((j != lines.length) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[46]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[47]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[48]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[414]++;
                        int line = lines[j];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[415]++;
int CodeCoverConditionCoverageHelper_C94;
                        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((line < min) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[188]++;
                            min = line;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[416]++;

                        } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[189]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[417]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((line > max) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[190]++;
                            max = line;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[418]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[191]++;}
}
                    }
                    firstLines[i] = min;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[419]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[420]++;
int CodeCoverConditionCoverageHelper_C96;
                    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((minAll > maxAll) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[192]++;
                        minAll = min;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[421]++;
                        maxAll = max;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[422]++;

                    } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[193]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[423]++;
int CodeCoverConditionCoverageHelper_C97;
                        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((min < minAll) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[194]++;
                            minAll = min;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[424]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[195]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[425]++;
int CodeCoverConditionCoverageHelper_C98;
                        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((max > maxAll) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[196]++;
                            maxAll = max;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[426]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[197]++;}
                    }
                }
            }
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[427]++;
int CodeCoverConditionCoverageHelper_C99;

            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((minAll > maxAll) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[198]++;
                // No line information
                this.breakableLines = EMPTY_BOOLEAN_ARRAY;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[428]++;
                this.breakpoints = EMPTY_BOOLEAN_ARRAY;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[429]++;

            } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[199]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[430]++;
int CodeCoverConditionCoverageHelper_C100;
                if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((minAll < 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[200]++;
                    // Line numbers can not be negative
                    throw new IllegalStateException(String.valueOf(minAll));

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[201]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[431]++;
                int linesTop = maxAll + 1;
                this.breakableLines = new boolean[linesTop];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[432]++;
                this.breakpoints = new boolean[linesTop];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[433]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[434]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[49]++;


int CodeCoverConditionCoverageHelper_C101;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[49]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[50]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[51]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[435]++;
                    int[] lines = lineArrays[i];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[436]++;
int CodeCoverConditionCoverageHelper_C102;
                    if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((lines != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((lines.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[202]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[437]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[52]++;


int CodeCoverConditionCoverageHelper_C103;
                        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((j != lines.length) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false); ++j) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[52]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[53]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[54]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[438]++;
                            int line = lines[j];
                            this.breakableLines[line] = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[439]++;
                        }

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[203]++;}
                }
            }
            this.functionSources = new FunctionSource[N];
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[440]++;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[441]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[55]++;


int CodeCoverConditionCoverageHelper_C104;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[55]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[56]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[57]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[442]++;
                String name = functions[i].getFunctionName();
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[443]++;
int CodeCoverConditionCoverageHelper_C105;
                if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[204]++;
                    name = "";
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[444]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[205]++;}
                this.functionSources[i]
                    = new FunctionSource(this, firstLines[i], name);
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[445]++;
            }
        }

        /**
         * Returns the source text.
         */
        public String source() {
            return this.source;
        }

        /**
         * Returns the script's origin URL.
         */
        public String url() {
            return this.url;
        }

        /**
         * Returns the number of FunctionSource objects stored in this object.
         */
        public int functionSourcesTop() {
            return functionSources.length;
        }

        /**
         * Returns the FunctionSource object with the given index.
         */
        public FunctionSource functionSource(int i) {
            return functionSources[i];
        }

        /**
         * Copies the breakpoints from the given SourceInfo object into this
         * one.
         */
        private void copyBreakpointsFrom(SourceInfo old) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[446]++;
            int end = old.breakpoints.length;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[447]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((end > this.breakpoints.length) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[206]++;
                end = this.breakpoints.length;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[448]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[207]++;}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[449]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[58]++;


int CodeCoverConditionCoverageHelper_C107;
            for (int line = 0;(((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((line != end) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false); ++line) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[58]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[59]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[60]++;
}
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[450]++;
int CodeCoverConditionCoverageHelper_C108;
                if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((old.breakpoints[line]) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[208]++;
                    this.breakpoints[line] = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[451]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[209]++;}
            }
        }

        /**
         * Returns whether the given line number can have a breakpoint set on
         * it.
         */
        public boolean breakableLine(int line) {
            return (line < this.breakableLines.length)
                   && this.breakableLines[line];
        }

        /**
         * Returns whether there is a breakpoint set on the given line.
         */
        public boolean breakpoint(int line) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[452]++;
int CodeCoverConditionCoverageHelper_C109;
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((breakableLine(line)) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[210]++;
                throw new IllegalArgumentException(String.valueOf(line));

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[211]++;}
            return line < this.breakpoints.length && this.breakpoints[line];
        }

        /**
         * Sets or clears the breakpoint flag for the given line.
         */
        public boolean breakpoint(int line, boolean value) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[453]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((breakableLine(line)) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[212]++;
                throw new IllegalArgumentException(String.valueOf(line));

            } else {
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[213]++;}
            boolean changed;
            synchronized (breakpoints) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[454]++;
int CodeCoverConditionCoverageHelper_C111;
                if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((breakpoints[line] != value) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[214]++;
                    breakpoints[line] = value;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[455]++;
                    changed = true;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[456]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.branches[215]++;
                    changed = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[457]++;
                }
            }
            return changed;
        }

        /**
         * Removes all breakpoints from the script.
         */
        public void removeAllBreakpoints() {
            synchronized (breakpoints) {
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[458]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[61]++;


int CodeCoverConditionCoverageHelper_C112;
                for (int line = 0;(((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((line != breakpoints.length) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false); ++line) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[61]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[62]--;
  CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.loops[63]++;
}
                    breakpoints[line] = false;
CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d.statements[459]++;
                }
            }
        }
    }
}

class CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d ());
  }
    public static long[] statements = new long[460];
    public static long[] branches = new long[216];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[113];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.debugger.RHINO-TOO-Dim.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 112; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[64];

  public CodeCoverCoverageCounter$6ubyiuewjke1au16g457jdp3i99d () {
    super("org.mozilla.javascript.tools.debugger.RHINO-TOO-Dim.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 459; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 215; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 112; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 63; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.debugger.RHINO-TOO-Dim.java");
      for (int i = 1; i <= 459; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 215; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 112; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 21; i++) {
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

