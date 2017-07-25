/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.debugger;

import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.JFrame;

import org.mozilla.javascript.*;
import org.mozilla.javascript.tools.shell.Global;

/**
 * Rhino script debugger main class.  This class links together a
 * debugger object ({@link Dim}) and a debugger GUI object ({@link SwingGui}).
 */
public class Main {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.ping();
  }


    /**
     * The debugger.
     */
    private Dim dim;

    /**
     * The debugger frame.
     */
    private SwingGui debugGui;

    /**
     * Creates a new Main.
     */
    public Main(String title) {
        dim = new Dim();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[1]++;
        debugGui = new SwingGui(dim, title);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[2]++;
    }

    /**
     * Returns the debugger window {@link JFrame}.
     */
    public JFrame getDebugFrame() {
        return debugGui;
    }

    /**
     * Breaks execution of the script.
     */
    public void doBreak() {
        dim.setBreak();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[3]++;
    }

    /**
     * Sets whether execution should break when a script exception is thrown.
     */
    public void setBreakOnExceptions(boolean value) {
        dim.setBreakOnExceptions(value);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[4]++;
        debugGui.getMenubar().getBreakOnExceptions().setSelected(value);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[5]++;
    }

    /**
     * Sets whether execution should break when a function is entered.
     */
    public void setBreakOnEnter(boolean value) {
        dim.setBreakOnEnter(value);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[6]++;
        debugGui.getMenubar().getBreakOnEnter().setSelected(value);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[7]++;
    }

    /**
     * Sets whether execution should break when a function is left.
     */
    public void setBreakOnReturn(boolean value) {
        dim.setBreakOnReturn(value);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[8]++;
        debugGui.getMenubar().getBreakOnReturn().setSelected(value);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[9]++;
    }

    /**
     * Removes all breakpoints.
     */
    public void clearAllBreakpoints() {
        dim.clearAllBreakpoints();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[10]++;
    }

    /**
     * Resumes execution of the script.
     */
    public void go() {
        dim.go();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[11]++;
    }

    /**
     * Sets the scope to be used for script evaluation.
     */
    public void setScope(Scriptable scope) {
        setScopeProvider(IProxy.newScopeProvider(scope));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[12]++;
    }

    /**
     * Sets the {@link ScopeProvider} that provides a scope to be used
     * for script evaluation.
     */
    public void setScopeProvider(ScopeProvider p) {
        dim.setScopeProvider(p);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[13]++;
    }

    /**
     * Sets the {@link SourceProvider} that provides the source to be displayed
     * for script evaluation.
     */
    public void setSourceProvider(final SourceProvider sourceProvider) {
        dim.setSourceProvider(sourceProvider);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[14]++;
    }

    /**
     * Assign a Runnable object that will be invoked when the user
     * selects "Exit..." or closes the Debugger main window.
     */
    public void setExitAction(Runnable r) {
        debugGui.setExitAction(r);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[15]++;
    }

    /**
     * Returns an {@link InputStream} for stdin from the debugger's internal
     * Console window.
     */
    public InputStream getIn() {
        return debugGui.getConsole().getIn();
    }

    /**
     * Returns a {@link PrintStream} for stdout to the debugger's internal
     * Console window.
     */
    public PrintStream getOut() {
        return debugGui.getConsole().getOut();
    }

    /**
     * Returns a {@link PrintStream} for stderr in the Debugger's internal
     * Console window.
     */
    public PrintStream getErr() {
        return debugGui.getConsole().getErr();
    }

    /**
     * Packs the debugger GUI frame.
     */
    public void pack() {
        debugGui.pack();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[16]++;
    }

    /**
     * Sets the debugger GUI frame dimensions.
     */
    public void setSize(int w, int h) {
        debugGui.setSize(w, h);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[17]++;
    }

    /**
     * Sets the visibility of the debugger GUI frame.
     */
    public void setVisible(boolean flag) {
        debugGui.setVisible(flag);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[18]++;
    }

    /**
     * Returns whether the debugger GUI frame is visible.
     */
    public boolean isVisible() {
        return debugGui.isVisible();
    }

    /**
     * Frees any resources held by the debugger.
     */
    public void dispose() {
        clearAllBreakpoints();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[19]++;
        dim.go();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[20]++;
        debugGui.dispose();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[21]++;
        dim = null;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[22]++;
    }

    /**
     * Attaches the debugger to the given {@link ContextFactory}.
     */
    public void attachTo(ContextFactory factory) {
        dim.attachTo(factory);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[23]++;
    }

    /**
     * Detaches from the current {@link ContextFactory}.
     */
    public void detach() {
        dim.detach();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[24]++;
    }

    /**
     * Main entry point.  Creates a debugger attached to a Rhino
     * {@link org.mozilla.javascript.tools.shell.Main} shell session.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[25]++;
        Main main = new Main("Rhino JavaScript Debugger");
        main.doBreak();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[26]++;
        main.setExitAction(new IProxy(IProxy.EXIT_ACTION));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[27]++;

        System.setIn(main.getIn());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[28]++;
        System.setOut(main.getOut());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[29]++;
        System.setErr(main.getErr());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[30]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[31]++;

        Global global = org.mozilla.javascript.tools.shell.Main.getGlobal();
        global.setIn(main.getIn());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[32]++;
        global.setOut(main.getOut());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[33]++;
        global.setErr(main.getErr());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[34]++;

        main.attachTo(
            org.mozilla.javascript.tools.shell.Main.shellContextFactory);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[35]++;

        main.setScope(global);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[36]++;

        main.pack();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[37]++;
        main.setSize(600, 460);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[38]++;
        main.setVisible(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[39]++;

        org.mozilla.javascript.tools.shell.Main.exec(args);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[40]++;
    }

    /**
     * Entry point for embedded applications.  This method attaches
     * to the global {@link ContextFactory} with a scope of a newly
     * created {@link Global} object.  No I/O redirection is performed
     * as with {@link #main(String[])}.
     */
    public static Main mainEmbedded(String title) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[41]++;
        ContextFactory factory = ContextFactory.getGlobal();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[42]++;
        Global global = new Global();
        global.init(factory);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[43]++;
        return mainEmbedded(factory, global, title);
    }

    /**
     * Entry point for embedded applications.  This method attaches
     * to the given {@link ContextFactory} with the given scope.  No
     * I/O redirection is performed as with {@link #main(String[])}.
     */
    public static Main mainEmbedded(ContextFactory factory,
                                    Scriptable scope,
                                    String title) {
        return mainEmbeddedImpl(factory, scope, title);
    }

    /**
     * Entry point for embedded applications.  This method attaches
     * to the given {@link ContextFactory} with the given scope.  No
     * I/O redirection is performed as with {@link #main(String[])}.
     */
    public static Main mainEmbedded(ContextFactory factory,
                                    ScopeProvider scopeProvider,
                                    String title) {
        return mainEmbeddedImpl(factory, scopeProvider, title);
    }

    /**
     * Helper method for {@link #mainEmbedded(String)}, etc.
     */
    private static Main mainEmbeddedImpl(ContextFactory factory,
                                         Object scopeProvider,
                                         String title) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[44]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((title == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[1]++;
            title = "Rhino JavaScript Debugger (embedded usage)";
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[45]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[2]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[46]++;
        Main main = new Main(title);
        main.doBreak();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[47]++;
        main.setExitAction(new IProxy(IProxy.EXIT_ACTION));
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[48]++;

        main.attachTo(factory);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[49]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[50]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((scopeProvider instanceof ScopeProvider) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[3]++;
            main.setScopeProvider((ScopeProvider)scopeProvider);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[51]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[4]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[52]++;
            Scriptable scope = (Scriptable)scopeProvider;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[53]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((scope instanceof Global) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[5]++;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[54]++;
                Global global = (Global)scope;
                global.setIn(main.getIn());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[55]++;
                global.setOut(main.getOut());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[56]++;
                global.setErr(main.getErr());
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[57]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[6]++;}
            main.setScope(scope);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[58]++;
        }

        main.pack();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[59]++;
        main.setSize(600, 460);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[60]++;
        main.setVisible(true);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[61]++;
        return main;
    }

    // Deprecated methods

    /**
     * @deprecated Use {@link #setSize(int, int)} instead.
     */
    public void setSize(java.awt.Dimension dimension) {
        debugGui.setSize(dimension.width, dimension.height);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[62]++;
    }

    /**
     * @deprecated
     * The method does nothing and is only present for compatibility.
     */
    public void setOptimizationLevel(int level) {
    }

    /**
     * @deprecated
     * The method is only present for compatibility and should not be called.
     */
    public void contextEntered(Context cx) {
        throw new IllegalStateException();
    }

    /**
     * @deprecated
     * The method is only present for compatibility and should not be called.
     */
    public void contextExited(Context cx) {
        throw new IllegalStateException();
    }

    /**
     * @deprecated
     * The method is only present for compatibility and should not be called.
     */
    public void contextCreated(Context cx) {
        throw new IllegalStateException();
    }

    /**
     * @deprecated
     * The method is only present for compatibility and should not be called.
     */
    public void contextReleased(Context cx)
    {
        throw new IllegalStateException();
    }

    /**
     * Class to consolidate all internal implementations of interfaces
     * to avoid class generation bloat.
     */
    private static class IProxy implements Runnable, ScopeProvider {

        // Constants for 'type'.
        public static final int EXIT_ACTION = 1;
        public static final int SCOPE_PROVIDER = 2;

        /**
         * The type of interface.
         */
        private final int type;

        /**
         * The scope object to expose when {@link #type} =
         * {@link #SCOPE_PROVIDER}.
         */
        private Scriptable scope;

        /**
         * Creates a new IProxy.
         */
        public IProxy(int type) {
            this.type = type;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[63]++;
        }

        /**
         * Creates a new IProxy that acts as a {@link ScopeProvider}.
         */
        public static ScopeProvider newScopeProvider(Scriptable scope) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[64]++;
            IProxy scopeProvider = new IProxy(SCOPE_PROVIDER);
            scopeProvider.scope = scope;
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[65]++;
            return scopeProvider;
        }

        // ContextAction

        /**
         * Exit action.
         */
        public void run() {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[66]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type != EXIT_ACTION) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[7]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[67]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[8]++;}
            System.exit(0);
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[68]++;
        }

        // ScopeProvider

        /**
         * Returns the scope for script evaluations.
         */
        public Scriptable getScope() {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[69]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type != SCOPE_PROVIDER) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[9]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[70]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[10]++;}
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[71]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[11]++; Kit.codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.statements[72]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x.branches[12]++;}
            return scope;
        }
    }
}

class CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x ());
  }
    public static long[] statements = new long[73];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.debugger.RHINO-TOO-Main.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1cnp1i09zf4zt91ketiflejxo84k0x () {
    super("org.mozilla.javascript.tools.debugger.RHINO-TOO-Main.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 72; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.debugger.RHINO-TOO-Main.java");
      for (int i = 1; i <= 72; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

