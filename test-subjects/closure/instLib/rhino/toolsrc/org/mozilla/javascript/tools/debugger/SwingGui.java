/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.debugger;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.EventQueue;
import java.awt.ActiveEvent;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuComponent;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.io.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.lang.reflect.Method;

import org.mozilla.javascript.Kit;
import org.mozilla.javascript.SecurityUtilities;

import org.mozilla.javascript.tools.shell.ConsoleTextArea;

import org.mozilla.javascript.tools.debugger.treetable.JTreeTable;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModel;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModelAdapter;

/**
 * GUI for the Rhino debugger.
 */
public class SwingGui extends JFrame implements GuiCallback {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -8217029773456711621L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1]++;
  }

    /**
     * The debugger.
     */
    Dim dim;

    /**
     * The action to run when the 'Exit' menu item is chosen or the
     * frame is closed.
     */
    private Runnable exitAction;

    /**
     * The {@link JDesktopPane} that holds the script windows.
     */
    private JDesktopPane desk;

    /**
     * The {@link JPanel} that shows information about the context.
     */
    private ContextWindow context;

    /**
     * The menu bar.
     */
    private Menubar menubar;

    /**
     * The tool bar.
     */
    private JToolBar toolBar;

    /**
     * The console that displays I/O from the script.
     */
    private JSInternalConsole console;

    /**
     * The {@link JSplitPane} that separates {@link #desk} from
     * {@link org.mozilla.javascript.Context}.
     */
    private JSplitPane split1;

    /**
     * The status bar.
     */
    private JLabel statusBar;

    /**
     * Hash table of internal frame names to the internal frames themselves.
     */
    private final Map<String,JFrame> toplevels =
        Collections.synchronizedMap(new HashMap<String,JFrame>());
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[2]++;
  }

    /**
     * Hash table of script URLs to their internal frames.
     */
    private final Map<String,FileWindow> fileWindows =
        Collections.synchronizedMap(new HashMap<String,FileWindow>());
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[3]++;
  }


    /**
     * The {@link FileWindow} that last had the focus.
     */
    private FileWindow currentWindow;

    /**
     * File choose dialog for loading a script.
     */
    JFileChooser dlg;

    /**
     * The AWT EventQueue.  Used for manually pumping AWT events from
     * {@link #dispatchNextGuiEvent()}.
     */
    private EventQueue awtEventQueue;

    /**
     * Creates a new SwingGui.
     */
    public SwingGui(Dim dim, String title) {
        super(title);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[4]++;
        this.dim = dim;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[5]++;
        init();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[6]++;
        dim.setGuiCallback(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[7]++;
    }

    /**
     * Returns the Menubar of this debugger frame.
     */
    public Menubar getMenubar() {
        return menubar;
    }

    /**
     * Sets the {@link Runnable} that will be run when the "Exit" menu
     * item is chosen.
     */
    public void setExitAction(Runnable r) {
        exitAction = r;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[8]++;
    }

    /**
     * Returns the debugger console component.
     */
    public JSInternalConsole getConsole() {
        return console;
    }

    /**
     * Sets the visibility of the debugger GUI.
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[9]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((b) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[1]++;
            // this needs to be done after the window is visible
            console.consoleTextArea.requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[11]++;
            context.split.setDividerLocation(0.5);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[12]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                console.setMaximum(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[14]++;
                console.setSelected(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[15]++;
                console.show();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[16]++;
                console.consoleTextArea.requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[17]++;
            } catch (Exception exc) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[4]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[3]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[2]++;}
    }

    /**
     * Records a new internal frame.
     */
    void addTopLevel(String key, JFrame frame) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((frame != this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[5]++;
            toplevels.put(key, frame);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[19]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[6]++;}
    }

    /**
     * Constructs the debugger GUI.
     */
    private void init() {
        menubar = new Menubar(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[20]++;
        setJMenuBar(menubar);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[21]++;
        toolBar = new JToolBar();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[22]++;
        JButton button;
        JButton breakButton, goButton, stepIntoButton,
            stepOverButton, stepOutButton;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[23]++;
        String [] toolTips = {"Break (Pause)",
                              "Go (F5)",
                              "Step Into (F11)",
                              "Step Over (F7)",
                              "Step Out (F8)"};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[24]++;
        int count = 0;
        button = breakButton = new JButton("Break");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[25]++;
        button.setToolTipText("Break");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[26]++;
        button.setActionCommand("Break");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[27]++;
        button.addActionListener(menubar);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[28]++;
        button.setEnabled(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[29]++;
        button.setToolTipText(toolTips[count++]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[30]++;

        button = goButton = new JButton("Go");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[31]++;
        button.setToolTipText("Go");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[32]++;
        button.setActionCommand("Go");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[33]++;
        button.addActionListener(menubar);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[34]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[35]++;
        button.setToolTipText(toolTips[count++]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[36]++;

        button = stepIntoButton = new JButton("Step Into");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[37]++;
        button.setToolTipText("Step Into");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[38]++;
        button.setActionCommand("Step Into");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[39]++;
        button.addActionListener(menubar);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[40]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[41]++;
        button.setToolTipText(toolTips[count++]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[42]++;

        button = stepOverButton = new JButton("Step Over");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[43]++;
        button.setToolTipText("Step Over");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[44]++;
        button.setActionCommand("Step Over");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[45]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[46]++;
        button.addActionListener(menubar);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[47]++;
        button.setToolTipText(toolTips[count++]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[48]++;

        button = stepOutButton = new JButton("Step Out");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[49]++;
        button.setToolTipText("Step Out");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[50]++;
        button.setActionCommand("Step Out");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[51]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[52]++;
        button.addActionListener(menubar);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[53]++;
        button.setToolTipText(toolTips[count++]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[54]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[55]++;

        Dimension dim = stepOverButton.getPreferredSize();
        breakButton.setPreferredSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[56]++;
        breakButton.setMinimumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[57]++;
        breakButton.setMaximumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[58]++;
        breakButton.setSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[59]++;
        goButton.setPreferredSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[60]++;
        goButton.setMinimumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[61]++;
        goButton.setMaximumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[62]++;
        stepIntoButton.setPreferredSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[63]++;
        stepIntoButton.setMinimumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[64]++;
        stepIntoButton.setMaximumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[65]++;
        stepOverButton.setPreferredSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[66]++;
        stepOverButton.setMinimumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[67]++;
        stepOverButton.setMaximumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[68]++;
        stepOutButton.setPreferredSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[69]++;
        stepOutButton.setMinimumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[70]++;
        stepOutButton.setMaximumSize(dim);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[71]++;
        toolBar.add(breakButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[72]++;
        toolBar.add(goButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[73]++;
        toolBar.add(stepIntoButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[74]++;
        toolBar.add(stepOverButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[75]++;
        toolBar.add(stepOutButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[76]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[77]++;

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[78]++;
        getContentPane().add(toolBar, BorderLayout.NORTH);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[79]++;
        getContentPane().add(contentPane, BorderLayout.CENTER);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[80]++;
        desk = new JDesktopPane();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[81]++;
        desk.setPreferredSize(new Dimension(600, 300));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[82]++;
        desk.setMinimumSize(new Dimension(150, 50));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[83]++;
        desk.add(console = new JSInternalConsole("JavaScript Console"));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[84]++;
        context = new ContextWindow(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[85]++;
        context.setPreferredSize(new Dimension(600, 120));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[86]++;
        context.setMinimumSize(new Dimension(50, 50));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[87]++;

        split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, desk,
                                          context);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[88]++;
        split1.setOneTouchExpandable(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[89]++;
        SwingGui.setResizeWeight(split1, 0.66);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[90]++;
        contentPane.add(split1, BorderLayout.CENTER);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[91]++;
        statusBar = new JLabel();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[92]++;
        statusBar.setText("Thread: ");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[93]++;
        contentPane.add(statusBar, BorderLayout.SOUTH);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[94]++;
        dlg = new JFileChooser();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[95]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[96]++;

        javax.swing.filechooser.FileFilter filter =
            new javax.swing.filechooser.FileFilter() {
                    @Override
                    public boolean accept(File f) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[97]++;
int CodeCoverConditionCoverageHelper_C3;
                        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((f.isDirectory()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[7]++;
                            return true;

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[8]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[98]++;
                        String n = f.getName();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[99]++;
                        int i = n.lastIndexOf('.');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[100]++;
int CodeCoverConditionCoverageHelper_C4;
                        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < n.length() -1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[9]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[101]++;
                            String ext = n.substring(i + 1).toLowerCase();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[102]++;
int CodeCoverConditionCoverageHelper_C5;
                            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ext.equals("js")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[11]++;
                                return true;

                            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[12]++;}

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[10]++;}
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "JavaScript Files (*.js)";
                    }
                };
        dlg.addChoosableFileFilter(filter);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[103]++;
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    exit();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[105]++;
                }
            });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[104]++;
    }

    /**
     * Runs the {@link #exitAction}.
     */
    private void exit() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[106]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((exitAction != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[13]++;
            SwingUtilities.invokeLater(exitAction);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[107]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[14]++;}
        dim.setReturnValue(Dim.EXIT);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[108]++;
    }

    /**
     * Returns the {@link FileWindow} for the given URL.
     */
    FileWindow getFileWindow(String url) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[109]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((url == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((url.equals("<stdin>")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[15]++;
            return null;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[16]++;}
        return fileWindows.get(url);
    }

    /**
     * Returns a short version of the given URL.
     */
    static String getShortName(String url) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[110]++;
        int lastSlash = url.lastIndexOf('/');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[111]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lastSlash < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[17]++;
            lastSlash = url.lastIndexOf('\\');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[112]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[18]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[113]++;
        String shortName = url;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[114]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((lastSlash >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((lastSlash + 1 < url.length()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[19]++;
            shortName = url.substring(lastSlash + 1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[115]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[20]++;}
        return shortName;
    }

    /**
     * Closes the given {@link FileWindow}.
     */
    void removeWindow(FileWindow w) {
        fileWindows.remove(w.getUrl());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[116]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[117]++;
        JMenu windowMenu = getWindowMenu();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[118]++;
        int count = windowMenu.getItemCount();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[119]++;
        JMenuItem lastItem = windowMenu.getItem(count -1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[120]++;
        String name = getShortName(w.getUrl());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[121]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 5;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[1]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[2]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[3]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[122]++;
            JMenuItem item = windowMenu.getItem(i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[123]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[21]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[124]++; continue;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[22]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[125]++; // separator
            String text = item.getText();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[126]++;
            //1 D:\foo.js
            //2 D:\bar.js
            int pos = text.indexOf(' ');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[127]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((text.substring(pos + 1).equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[23]++;
                windowMenu.remove(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[128]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[129]++;
int CodeCoverConditionCoverageHelper_C13;
                // Cascade    [0]
                // Tile       [1]
                // -------    [2]
                // Console    [3]
                // -------    [4]
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((count == 6) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[25]++;
                    // remove the final separator
                    windowMenu.remove(4);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[130]++;

                } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[26]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[131]++;
                    int j = i - 4;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[132]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
                    for (;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < count -1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[4]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[5]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[6]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[133]++;
                        JMenuItem thisItem = windowMenu.getItem(i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[134]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((thisItem != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[27]++;
                            //1 D:\foo.js
                            //2 D:\bar.js
                            text = thisItem.getText();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[135]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[136]++;
int CodeCoverConditionCoverageHelper_C16;
                            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((text.equals("More Windows...")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[29]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[137]++;
                                break;

                            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[30]++;
                                pos = text.indexOf(' ');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[138]++;
                                thisItem.setText((char)('0' + j) + " " +
                                                 text.substring(pos + 1));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[139]++;
                                thisItem.setMnemonic('0' + j);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[140]++;
                                j++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[141]++;
                            }

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[28]++;}
                    }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[142]++;
int CodeCoverConditionCoverageHelper_C17;
                    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((count - 6 == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((lastItem != item) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[31]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[143]++;
int CodeCoverConditionCoverageHelper_C18;
                        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lastItem.getText().equals("More Windows...")) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[33]++;
                            windowMenu.remove(lastItem);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[144]++;

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[34]++;}

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[32]++;}
                }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[145]++;
                break;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[24]++;}
        }
        windowMenu.revalidate();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[146]++;
    }

    /**
     * Shows the line at which execution in the given stack frame just stopped.
     */
    void showStopLine(Dim.StackFrame frame) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[147]++;
        String sourceName = frame.getUrl();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[148]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((sourceName.equals("<stdin>")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[35]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[149]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((console.isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[37]++;
                console.show();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[150]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[38]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[36]++;
            showFileWindow(sourceName, -1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[151]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[152]++;
            int lineNumber = frame.getLineNumber();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[153]++;
            FileWindow w = getFileWindow(sourceName);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[154]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((w != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[39]++;
                setFilePosition(w, lineNumber);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[155]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[40]++;}
        }
    }

    /**
     * Shows a {@link FileWindow} for the given source, creating it
     * if it doesn't exist yet. if <code>lineNumber</code> is greater
     * than -1, it indicates the line number to select and display.
     * @param sourceUrl the source URL
     * @param lineNumber the line number to select, or -1
     */
    protected void showFileWindow(String sourceUrl, int lineNumber) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[156]++;
        FileWindow w = getFileWindow(sourceUrl);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[157]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((w == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[41]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[158]++;
            Dim.SourceInfo si = dim.sourceInfo(sourceUrl);
            createFileWindow(si, -1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[159]++;
            w = getFileWindow(sourceUrl);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[160]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[42]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[161]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((lineNumber > -1) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[43]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[162]++;
            int start = w.getPosition(lineNumber-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[163]++;
            int end = w.getPosition(lineNumber)-1;
            w.textArea.select(start);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[164]++;
            w.textArea.setCaretPosition(start);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[165]++;
            w.textArea.moveCaretPosition(end);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[166]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[44]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[167]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[168]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((w.isIcon()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[46]++;
                w.setIcon(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[169]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[47]++;}
            w.setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[170]++;
            w.moveToFront();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[171]++;
            w.setSelected(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[172]++;
            requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[173]++;
            w.requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[174]++;
            w.textArea.requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[175]++;
        } catch (Exception exc) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[48]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[45]++;
}
  }
    }

    /**
     * Creates and shows a new {@link FileWindow} for the given source.
     */
    protected void createFileWindow(Dim.SourceInfo sourceInfo, int line) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[176]++;
        boolean activate = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[177]++;

        String url = sourceInfo.url();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[178]++;
        FileWindow w = new FileWindow(this, sourceInfo);
        fileWindows.put(url, w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[179]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[180]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((line != -1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[49]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[181]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((currentWindow != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[51]++;
                currentWindow.setPosition(-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[182]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[52]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[183]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try {
CodeCoverTryBranchHelper_Try3 = true;
                w.setPosition(w.textArea.getLineStartOffset(line-1));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[184]++;
            } catch (BadLocationException exc) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[54]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[185]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                try {
CodeCoverTryBranchHelper_Try4 = true;
                    w.setPosition(w.textArea.getLineStartOffset(0));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[186]++;
                } catch (BadLocationException ee) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[56]++;
                    w.setPosition(-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[187]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[55]++;
}
  }
            } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[53]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[50]++;}
        desk.add(w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[188]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[189]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((line != -1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[57]++;
            currentWindow = w;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[190]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[58]++;}
        menubar.addFile(url);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[191]++;
        w.setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[192]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[193]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((activate) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[59]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[194]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
            try {
CodeCoverTryBranchHelper_Try5 = true;
                w.setMaximum(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[195]++;
                w.setSelected(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[196]++;
                w.moveToFront();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[197]++;
            } catch (Exception exc) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[62]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[61]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[60]++;}
    }

    /**
     * Update the source text for <code>sourceInfo</code>. This returns true
     * if a {@link FileWindow} for the given source exists and could be updated.
     * Otherwise, this does nothing and returns false.
     * @param sourceInfo the source info
     * @return true if a {@link FileWindow} for the given source exists
     *              and could be updated, false otherwise.
     */
    protected boolean updateFileWindow(Dim.SourceInfo sourceInfo) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[198]++;
        String fileName = sourceInfo.url();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[199]++;
        FileWindow w = getFileWindow(fileName);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[200]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((w != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[63]++;
            w.updateText(sourceInfo);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[201]++;
            w.show();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[202]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[64]++;}
        return false;
    }

    /**
     * Moves the current position in the given {@link FileWindow} to the
     * given line.
     */
    private void setFilePosition(FileWindow w, int line) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[203]++;
        boolean activate = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[204]++;
        JTextArea ta = w.textArea;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[205]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
        try {
CodeCoverTryBranchHelper_Try6 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[206]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((line == -1) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[66]++;
                w.setPosition(-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[207]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[208]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((currentWindow == w) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[68]++;
                    currentWindow = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[209]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[69]++;}

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[67]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[210]++;
                int loc = ta.getLineStartOffset(line-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[211]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((currentWindow != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((currentWindow != w) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[70]++;
                    currentWindow.setPosition(-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[212]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[71]++;}
                w.setPosition(loc);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[213]++;
                currentWindow = w;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[214]++;
            }
        } catch (BadLocationException exc) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[72]++;
            // fix me
        } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[65]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[215]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((activate) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[73]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[216]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((w.isIcon()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[75]++;
                desk.getDesktopManager().deiconifyFrame(w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[217]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[76]++;}
            desk.getDesktopManager().activateFrame(w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[218]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[219]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
            try {
CodeCoverTryBranchHelper_Try7 = true;
                w.show();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[220]++;
                w.toFront();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[221]++;  // required for correct frame layering (JDK 1.4.1)
                w.setSelected(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[222]++;
            } catch (Exception exc) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[78]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[77]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[74]++;}
    }

    /**
     * Handles script interruption.
     */
    void enterInterruptImpl(Dim.StackFrame lastFrame,
                            String threadTitle, String alertMessage) {
        statusBar.setText("Thread: " + threadTitle);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[223]++;

        showStopLine(lastFrame);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[224]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[225]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((alertMessage != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[79]++;
            MessageDialogWrapper.showMessageDialog(this,
                                                   alertMessage,
                                                   "Exception in Script",
                                                   JOptionPane.ERROR_MESSAGE);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[226]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[80]++;}

        updateEnabled(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[227]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[228]++;

        Dim.ContextData contextData = lastFrame.contextData();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[229]++;

        JComboBox ctx = context.context;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[230]++;
        List<String> toolTips = context.toolTips;
        context.disableUpdate();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[231]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[232]++;
        int frameCount = contextData.frameCount();
        ctx.removeAllItems();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[233]++;
        // workaround for JDK 1.4 bug that caches selected value even after
        // removeAllItems() is called
        ctx.setSelectedItem(null);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[234]++;
        toolTips.clear();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[235]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[236]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[7]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < frameCount) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[7]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[8]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[9]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[237]++;
            Dim.StackFrame frame = contextData.getFrame(i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[238]++;
            String url = frame.getUrl();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[239]++;
            int lineNumber = frame.getLineNumber();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[240]++;
            String shortName = url;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[241]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((url.length() > 20) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[81]++;
                shortName = "..." + url.substring(url.length() - 17);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[242]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[82]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[243]++;
            String location = "\"" + shortName + "\", line " + lineNumber;
            ctx.insertItemAt(location, i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[244]++;
            location = "\"" + url + "\", line " + lineNumber;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[245]++;
            toolTips.add(location);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[246]++;
        }
        context.enableUpdate();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[247]++;
        ctx.setSelectedIndex(0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[248]++;
        ctx.setMinimumSize(new Dimension(50, ctx.getMinimumSize().height));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[249]++;
    }

    /**
     * Returns the 'Window' menu.
     */
    private JMenu getWindowMenu() {
        return menubar.getMenu(3);
    }

    /**
     * Displays a {@link JFileChooser} and returns the selected filename.
     */
    private String chooseFile(String title) {
        dlg.setDialogTitle(title);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[250]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[251]++;
        File CWD = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[252]++;
        String dir = SecurityUtilities.getSystemProperty("user.dir");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[253]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((dir != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[83]++;
            CWD = new File(dir);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[254]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[84]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[255]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((CWD != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[85]++;
            dlg.setCurrentDirectory(CWD);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[256]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[86]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[257]++;
        int returnVal = dlg.showOpenDialog(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[258]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((returnVal == JFileChooser.APPROVE_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[87]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[259]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
            try {
CodeCoverTryBranchHelper_Try8 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[260]++;
                String result = dlg.getSelectedFile().getCanonicalPath();
                CWD = dlg.getSelectedFile().getParentFile();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[261]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[262]++;
                Properties props = System.getProperties();
                props.put("user.dir", CWD.getPath());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[263]++;
                System.setProperties(props);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[264]++;
                return result;
            } catch (IOException ignored) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[90]++;
            } catch (SecurityException ignored) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[91]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[89]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[88]++;}
        return null;
    }

    /**
     * Returns the current selected internal frame.
     */
    private JInternalFrame getSelectedFrame() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[265]++;
       JInternalFrame[] frames = desk.getAllFrames();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[266]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[10]++;


int CodeCoverConditionCoverageHelper_C41;
       for (int i = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < frames.length) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[10]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[11]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[12]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[267]++;
int CodeCoverConditionCoverageHelper_C42;
           if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((frames[i].isShowing()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[92]++;
               return frames[i];

           } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[93]++;}
       }
       return frames[frames.length - 1];
    }

    /**
     * Enables or disables the menu and tool bars with respect to the
     * state of script execution.
     */
    private void updateEnabled(boolean interrupted) {
        ((Menubar)getJMenuBar()).updateEnabled(interrupted);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[268]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[269]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[13]++;


int CodeCoverConditionCoverageHelper_C43;
        for (int ci = 0, cc = toolBar.getComponentCount();(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ci < cc) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); ci++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[13]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[14]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[15]++;
}
            boolean enableButton;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[270]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((ci == 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[94]++;
                // Break
                enableButton = !interrupted;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[271]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[95]++;
                enableButton = interrupted;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[272]++;
            }
            toolBar.getComponent(ci).setEnabled(enableButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[273]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[274]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((interrupted) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[96]++;
            toolBar.setEnabled(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[275]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[276]++;
            // raise the debugger window
            int state = getExtendedState();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[277]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((state == Frame.ICONIFIED) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[98]++;
                setExtendedState(Frame.NORMAL);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[278]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[99]++;}
            toFront();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[279]++;
            context.setEnabled(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[280]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[97]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[281]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((currentWindow != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[100]++; currentWindow.setPosition(-1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[282]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[101]++;}
            context.setEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[283]++;
        }
    }

    /**
     * Calls {@link JSplitPane#setResizeWeight} via reflection.
     * For compatibility, since JDK &lt; 1.3 does not have this method.
     */
    static void setResizeWeight(JSplitPane pane, double weight) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[284]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
        try {
CodeCoverTryBranchHelper_Try9 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[285]++;
            Method m = JSplitPane.class.getMethod("setResizeWeight",
                                                  new Class[]{double.class});
            m.invoke(pane, new Object[]{new Double(weight)});
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[286]++;
        } catch (NoSuchMethodException exc) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[103]++;
        } catch (IllegalAccessException exc) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[104]++;
        } catch (java.lang.reflect.InvocationTargetException exc) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[105]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[102]++;
}
  }
    }

    /**
     * Reads the file with the given name and returns its contents as a String.
     */
    private String readFile(String fileName) {
        String text;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[287]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
        try {
CodeCoverTryBranchHelper_Try10 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[288]++;
            Reader r = new FileReader(fileName);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[289]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
            try {
CodeCoverTryBranchHelper_Try11 = true;
                text = Kit.readReader(r);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[290]++;
            } finally {
if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[107]++;
}
                r.close();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[291]++;
            }
        } catch (IOException ex) {
CodeCoverTryBranchHelper_Try10 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[108]++;
            MessageDialogWrapper.showMessageDialog(this,
                                                   ex.getMessage(),
                                                   "Error reading "+fileName,
                                                   JOptionPane.ERROR_MESSAGE);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[292]++;
            text = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[293]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[106]++;
}
  }
        return text;
    }

    // GuiCallback

    /**
     * Called when the source text for a script has been updated.
     */
    public void updateSourceText(Dim.SourceInfo sourceInfo) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[294]++;
        RunProxy proxy = new RunProxy(this, RunProxy.UPDATE_SOURCE_TEXT);
        proxy.sourceInfo = sourceInfo;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[295]++;
        SwingUtilities.invokeLater(proxy);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[296]++;
    }

    /**
     * Called when the interrupt loop has been entered.
     */
    public void enterInterrupt(Dim.StackFrame lastFrame,
                               String threadTitle,
                               String alertMessage) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[297]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((SwingUtilities.isEventDispatchThread()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[109]++;
            enterInterruptImpl(lastFrame, threadTitle, alertMessage);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[298]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[110]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[299]++;
            RunProxy proxy = new RunProxy(this, RunProxy.ENTER_INTERRUPT);
            proxy.lastFrame = lastFrame;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[300]++;
            proxy.threadTitle = threadTitle;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[301]++;
            proxy.alertMessage = alertMessage;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[302]++;
            SwingUtilities.invokeLater(proxy);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[303]++;
        }
    }

    /**
     * Returns whether the current thread is the GUI event thread.
     */
    public boolean isGuiEventThread() {
        return SwingUtilities.isEventDispatchThread();
    }

    /**
     * Processes the next GUI event.
     */
    public void dispatchNextGuiEvent() throws InterruptedException {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[304]++;
        EventQueue queue = awtEventQueue;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[305]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((queue == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[111]++;
            queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[306]++;
            awtEventQueue = queue;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[307]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[112]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[308]++;
        AWTEvent event = queue.getNextEvent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[309]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((event instanceof ActiveEvent) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[113]++;
            ((ActiveEvent)event).dispatch();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[310]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[114]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[311]++;
            Object source = event.getSource();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[312]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((source instanceof Component) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[115]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[313]++;
                Component comp = (Component)source;
                comp.dispatchEvent(event);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[314]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[116]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[315]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((source instanceof MenuComponent) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[117]++;
                ((MenuComponent)source).dispatchEvent(event);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[316]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[118]++;}
}
        }
    }

    // ActionListener

    /**
     * Performs an action from the menu or toolbar.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[317]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[318]++;
        int returnValue = -1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[319]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((cmd.equals("Cut")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((cmd.equals("Copy")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((cmd.equals("Paste")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[119]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[320]++;
            JInternalFrame f = getSelectedFrame();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[321]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((f != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((f instanceof ActionListener) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[121]++;
                ((ActionListener)f).actionPerformed(e);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[322]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[122]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[120]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[323]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((cmd.equals("Step Over")) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[123]++;
            returnValue = Dim.STEP_OVER;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[324]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[124]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[325]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((cmd.equals("Step Into")) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[125]++;
            returnValue = Dim.STEP_INTO;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[326]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[126]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[327]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((cmd.equals("Step Out")) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[127]++;
            returnValue = Dim.STEP_OUT;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[328]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[128]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[329]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((cmd.equals("Go")) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[129]++;
            returnValue = Dim.GO;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[330]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[130]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[331]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((cmd.equals("Break")) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[131]++;
            dim.setBreak();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[332]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[132]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[333]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((cmd.equals("Exit")) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[133]++;
            exit();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[334]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[134]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[335]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((cmd.equals("Open")) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[135]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[336]++;
            String fileName = chooseFile("Select a file to compile");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[337]++;
int CodeCoverConditionCoverageHelper_C62;
            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((fileName != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[137]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[338]++;
                String text = readFile(fileName);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[339]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((text != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[139]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[340]++;
                    RunProxy proxy = new RunProxy(this, RunProxy.OPEN_FILE);
                    proxy.fileName = fileName;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[341]++;
                    proxy.text = text;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[342]++;
                    new Thread(proxy).start();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[343]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[140]++;}

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[138]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[136]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[344]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((cmd.equals("Load")) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[141]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[345]++;
            String fileName = chooseFile("Select a file to execute");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[346]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((fileName != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[143]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[347]++;
                String text = readFile(fileName);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[348]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((text != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[145]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[349]++;
                    RunProxy proxy = new RunProxy(this, RunProxy.LOAD_FILE);
                    proxy.fileName = fileName;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[350]++;
                    proxy.text = text;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[351]++;
                    new Thread(proxy).start();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[352]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[146]++;}

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[144]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[142]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[353]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((cmd.equals("More Windows...")) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[147]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[354]++;
            MoreWindows dlg = new MoreWindows(this, fileWindows,
                                              "Window", "Files");
            dlg.showDialog(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[355]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[148]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[356]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((cmd.equals("Console")) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[149]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[357]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((console.isIcon()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[151]++;
                desk.getDesktopManager().deiconifyFrame(console);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[358]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[152]++;}
            console.show();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[359]++;
            desk.getDesktopManager().activateFrame(console);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[360]++;
            console.consoleTextArea.requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[361]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[150]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[362]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((cmd.equals("Cut")) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[153]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[154]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[363]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((cmd.equals("Copy")) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[155]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[156]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[364]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((cmd.equals("Paste")) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[157]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[158]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[365]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((cmd.equals("Go to function...")) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[159]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[366]++;
            FindFunction dlg = new FindFunction(this, "Go to function",
                                                "Function");
            dlg.showDialog(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[367]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[160]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[368]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((cmd.equals("Tile")) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[161]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[369]++;
            JInternalFrame[] frames = desk.getAllFrames();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[370]++;
            int count = frames.length;
            int rows, cols;
            rows = cols = (int)Math.sqrt(count);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[371]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[372]++;
int CodeCoverConditionCoverageHelper_C75;
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((rows*cols < count) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[163]++;
                cols++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[373]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[374]++;
int CodeCoverConditionCoverageHelper_C76;
                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((rows * cols < count) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[165]++;
                    rows++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[375]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[166]++;}

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[164]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[376]++;
            Dimension size = desk.getSize();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[377]++;
            int w = size.width/cols;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[378]++;
            int h = size.height/rows;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[379]++;
            int x = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[380]++;
            int y = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[381]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[16]++;


int CodeCoverConditionCoverageHelper_C77;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((i < rows) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[16]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[17]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[18]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[382]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[19]++;


int CodeCoverConditionCoverageHelper_C78;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((j < cols) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[19]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[20]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[21]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[383]++;
                    int index = (i*cols) + j;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[384]++;
int CodeCoverConditionCoverageHelper_C79;
                    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((index >= frames.length) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[167]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[385]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[168]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[386]++;
                    JInternalFrame f = frames[index];
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[387]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
                    try {
CodeCoverTryBranchHelper_Try12 = true;
                        f.setIcon(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[388]++;
                        f.setMaximum(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[389]++;
                    } catch (Exception exc) {
CodeCoverTryBranchHelper_Try12 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[170]++;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[169]++;
}
  }
                    desk.getDesktopManager().setBoundsForFrame(f, x, y,
                                                               w, h);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[390]++;
                    x += w;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[391]++;
                }
                y += h;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[392]++;
                x = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[393]++;
            }

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[162]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[394]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((cmd.equals("Cascade")) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[171]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[395]++;
            JInternalFrame[] frames = desk.getAllFrames();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[396]++;
            int count = frames.length;
            int x, y, w, h;
            x = y = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[397]++;
            h = desk.getHeight();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[398]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[399]++;
            int d = h / count;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[400]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((d > 30) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[173]++; d = 30;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[401]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[174]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[402]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[22]++;


int CodeCoverConditionCoverageHelper_C82;
            for (int i = count -1;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); i--, x += d, y += d) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[22]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[23]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[24]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[403]++;
                JInternalFrame f = frames[i];
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[404]++;
boolean CodeCoverTryBranchHelper_Try13 = false;
                try {
CodeCoverTryBranchHelper_Try13 = true;
                    f.setIcon(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[405]++;
                    f.setMaximum(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[406]++;
                } catch (Exception exc) {
CodeCoverTryBranchHelper_Try13 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[176]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try13 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[175]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[407]++;
                Dimension dimen = f.getPreferredSize();
                w = dimen.width;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[408]++;
                h = dimen.height;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[409]++;
                desk.getDesktopManager().setBoundsForFrame(f, x, y, w, h);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[410]++;
            }

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[172]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[411]++;
            Object obj = getFileWindow(cmd);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[412]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[177]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[413]++;
                FileWindow w = (FileWindow)obj;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[414]++;
boolean CodeCoverTryBranchHelper_Try14 = false;
                try {
CodeCoverTryBranchHelper_Try14 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[415]++;
int CodeCoverConditionCoverageHelper_C84;
                    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((w.isIcon()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[180]++;
                        w.setIcon(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[416]++;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[181]++;}
                    w.setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[417]++;
                    w.moveToFront();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[418]++;
                    w.setSelected(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[419]++;
                } catch (Exception exc) {
CodeCoverTryBranchHelper_Try14 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[182]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try14 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[179]++;
}
  }

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[178]++;}
        }
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[420]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((returnValue != -1) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[183]++;
            updateEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[421]++;
            dim.setReturnValue(returnValue);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[422]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[184]++;}
    }
}

/**
 * Helper class for showing a message dialog.
 */
class MessageDialogWrapper {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Shows a message dialog, wrapping the <code>msg</code> at 60
     * columns.
     */
    public static void showMessageDialog(Component parent, String msg,
                                         String title, int flags) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[423]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((msg.length() > 60) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[185]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[424]++;
            StringBuffer buf = new StringBuffer();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[425]++;
            int len = msg.length();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[426]++;
            int j = 0;
            int i;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[427]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[25]++;


int CodeCoverConditionCoverageHelper_C87;
            for (i = 0;(((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false); i++, j++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[25]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[26]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[27]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[428]++;
                char c = msg.charAt(i);
                buf.append(c);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[429]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[430]++;
int CodeCoverConditionCoverageHelper_C88;
                if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(c)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[187]++;
                    int k;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[431]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[28]++;


int CodeCoverConditionCoverageHelper_C89;
                    for (k = i + 1;(((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((k < len) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[28]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[29]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[30]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[432]++;
int CodeCoverConditionCoverageHelper_C90;
                        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(msg.charAt(k))) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[189]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[433]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[190]++;}
                    }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[434]++;
int CodeCoverConditionCoverageHelper_C91;
                    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((k < len) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[191]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[435]++;
                        int nextWordLen = k - i;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[436]++;
int CodeCoverConditionCoverageHelper_C92;
                        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((j + nextWordLen > 60) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[193]++;
                            buf.append('\n');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[437]++;
                            j = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[438]++;

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[194]++;}

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[192]++;}

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[188]++;}
            }
            msg = buf.toString();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[439]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[186]++;}
        JOptionPane.showMessageDialog(parent, msg, title, flags);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[440]++;
    }
}

/**
 * Extension of JTextArea for script evaluation input.
 */
class EvalTextArea
    extends JTextArea
    implements KeyListener, DocumentListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -3918033649601064194L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[441]++;
  }

    /**
     * The debugger GUI.
     */
    private SwingGui debugGui;

    /**
     * History of expressions that have been evaluated
     */
    private List<String> history;

    /**
     * Index of the selected history item.
     */
    private int historyIndex = -1;
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[442]++;
  }

    /**
     * Position in the display where output should go.
     */
    private int outputMark;

    /**
     * Creates a new EvalTextArea.
     */
    public EvalTextArea(SwingGui debugGui) {
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[443]++;
        history = Collections.synchronizedList(new ArrayList<String>());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[444]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[445]++;
        Document doc = getDocument();
        doc.addDocumentListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[446]++;
        addKeyListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[447]++;
        setLineWrap(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[448]++;
        setFont(new Font("Monospaced", 0, 12));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[449]++;
        append("% ");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[450]++;
        outputMark = doc.getLength();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[451]++;
    }

    /**
     * Selects a subrange of the text.
     */
    @Override
    public void select(int start, int end) {
        //requestFocus();
        super.select(start, end);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[452]++;
    }

    /**
     * Called when Enter is pressed.
     */
    private synchronized void returnPressed() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[453]++;
        Document doc = getDocument();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[454]++;
        int len = doc.getLength();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[455]++;
        Segment segment = new Segment();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[456]++;
boolean CodeCoverTryBranchHelper_Try15 = false;
        try {
CodeCoverTryBranchHelper_Try15 = true;
            doc.getText(outputMark, len - outputMark, segment);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[457]++;
        } catch (javax.swing.text.BadLocationException ignored) {
CodeCoverTryBranchHelper_Try15 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[196]++;
            ignored.printStackTrace();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[458]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try15 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[195]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[459]++;
        String text = segment.toString();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[460]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((debugGui.dim.stringIsCompilableUnit(text)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[197]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[461]++;
int CodeCoverConditionCoverageHelper_C94;
            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((text.trim().length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[199]++;
               history.add(text);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[462]++;
               historyIndex = history.size();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[463]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[200]++;}
            append("\n");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[464]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[465]++;
            String result = debugGui.dim.eval(text);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[466]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((result.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[201]++;
                append(result);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[467]++;
                append("\n");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[468]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[202]++;}
            append("% ");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[469]++;
            outputMark = doc.getLength();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[470]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[198]++;
            append("\n");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[471]++;
        }
    }

    /**
     * Writes output into the text area.
     */
    public synchronized void write(String str) {
        insert(str, outputMark);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[472]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[473]++;
        int len = str.length();
        outputMark += len;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[474]++;
        select(outputMark, outputMark);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[475]++;
    }

    // KeyListener

    /**
     * Called when a key is pressed.
     */
    public void keyPressed(KeyEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[476]++;
        int code = e.getKeyCode();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[477]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (8)) == 0 || true) &&
 ((code == KeyEvent.VK_BACK_SPACE) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[203]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[478]++;
int CodeCoverConditionCoverageHelper_C97;
            if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((outputMark == getCaretPosition()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[205]++;
                e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[479]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[206]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[204]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[480]++;
int CodeCoverConditionCoverageHelper_C98; if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_HOME) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[207]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[481]++;
           int caretPos = getCaretPosition();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[482]++;
int CodeCoverConditionCoverageHelper_C99;
           if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((caretPos == outputMark) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[209]++;
               e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[483]++;

           } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[210]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[484]++;
int CodeCoverConditionCoverageHelper_C100; if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((caretPos > outputMark) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[211]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[485]++;
int CodeCoverConditionCoverageHelper_C101;
               if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((e.isControlDown()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[213]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[486]++;
int CodeCoverConditionCoverageHelper_C102;
                   if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((e.isShiftDown()) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[215]++;
                       moveCaretPosition(outputMark);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[487]++;

                   } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[216]++;
                       setCaretPosition(outputMark);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[488]++;
                   }
                   e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[489]++;

               } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[214]++;}

           } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[212]++;}
}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[208]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[490]++;
int CodeCoverConditionCoverageHelper_C103; if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_ENTER) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[217]++;
            returnPressed();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[491]++;
            e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[492]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[218]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[493]++;
int CodeCoverConditionCoverageHelper_C104; if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_UP) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[219]++;
            historyIndex--;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[494]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[495]++;
int CodeCoverConditionCoverageHelper_C105;
            if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((historyIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[221]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[496]++;
int CodeCoverConditionCoverageHelper_C106;
                if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((historyIndex >= history.size()) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[223]++;
                    historyIndex = history.size() -1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[497]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[224]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[498]++;
int CodeCoverConditionCoverageHelper_C107;
                if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((historyIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[225]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[499]++;
                    String str = history.get(historyIndex);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[500]++;
                    int len = getDocument().getLength();
                    replaceRange(str, outputMark, len);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[501]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[502]++;
                    int caretPos = outputMark + str.length();
                    select(caretPos, caretPos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[503]++;

                } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[226]++;
                    historyIndex++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[504]++;
                }

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[222]++;
                historyIndex++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[505]++;
            }
            e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[506]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[220]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[507]++;
int CodeCoverConditionCoverageHelper_C108; if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_DOWN) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[227]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[508]++;
            int caretPos = outputMark;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[509]++;
int CodeCoverConditionCoverageHelper_C109;
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((history.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[229]++;
                historyIndex++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[510]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[511]++;
int CodeCoverConditionCoverageHelper_C110;
                if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((historyIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[231]++;historyIndex = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[512]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[232]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[513]++;
                int len = getDocument().getLength();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[514]++;
int CodeCoverConditionCoverageHelper_C111;
                if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((historyIndex < history.size()) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[233]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[515]++;
                    String str = history.get(historyIndex);
                    replaceRange(str, outputMark, len);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[516]++;
                    caretPos = outputMark + str.length();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[517]++;

                } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[234]++;
                    historyIndex = history.size();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[518]++;
                    replaceRange("", outputMark, len);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[519]++;
                }

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[230]++;}
            select(caretPos, caretPos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[520]++;
            e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[521]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[228]++;}
}
}
}
}
    }

    /**
     * Called when a key is typed.
     */
    public void keyTyped(KeyEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[522]++;
        int keyChar = e.getKeyChar();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[523]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((keyChar == 0x8) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false) /* KeyEvent.VK_BACK_SPACE */) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[235]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[524]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((outputMark == getCaretPosition()) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[237]++;
                e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[525]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[238]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[236]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[526]++;
int CodeCoverConditionCoverageHelper_C114; if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((getCaretPosition() < outputMark) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[239]++;
            setCaretPosition(outputMark);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[527]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[240]++;}
}
    }

    /**
     * Called when a key is released.
     */
    public synchronized void keyReleased(KeyEvent e) {
    }

    // DocumentListener

    /**
     * Called when text was inserted into the text area.
     */
    public synchronized void insertUpdate(DocumentEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[528]++;
        int len = e.getLength();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[529]++;
        int off = e.getOffset();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[530]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((outputMark > off) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[241]++;
            outputMark += len;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[531]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[242]++;}
    }

    /**
     * Called when text was removed from the text area.
     */
    public synchronized void removeUpdate(DocumentEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[532]++;
        int len = e.getLength();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[533]++;
        int off = e.getOffset();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[534]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((outputMark > off) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[243]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[535]++;
int CodeCoverConditionCoverageHelper_C117;
            if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((outputMark >= off + len) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[245]++;
                outputMark -= len;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[536]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[246]++;
                outputMark = off;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[537]++;
            }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[244]++;}
    }

    /**
     * Attempts to clean up the damage done by {@link #updateUI()}.
     */
    public synchronized void postUpdateUI() {
        //requestFocus();
        setCaret(getCaret());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[538]++;
        select(outputMark, outputMark);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[539]++;
    }

    /**
     * Called when text has changed in the text area.
     */
    public synchronized void changedUpdate(DocumentEvent e) {
    }
}

/**
 * An internal frame for evaluating script.
 */
class EvalWindow extends JInternalFrame implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -2860585845212160176L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[540]++;
  }

    /**
     * The text area into which expressions can be typed.
     */
    private EvalTextArea evalTextArea;

    /**
     * Creates a new EvalWindow.
     */
    public EvalWindow(String name, SwingGui debugGui) {
        super(name, true, false, true, true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[541]++;
        evalTextArea = new EvalTextArea(debugGui);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[542]++;
        evalTextArea.setRows(24);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[543]++;
        evalTextArea.setColumns(80);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[544]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[545]++;
        JScrollPane scroller = new JScrollPane(evalTextArea);
        setContentPane(scroller);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[546]++;
        //scroller.setPreferredSize(new Dimension(600, 400));
        pack();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[547]++;
        setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[548]++;
    }

    /**
     * Sets whether the text area is enabled.
     */
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[549]++;
        evalTextArea.setEnabled(b);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[550]++;
    }

    // ActionListener

    /**
     * Performs an action on the text area.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[551]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[552]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((cmd.equals("Cut")) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[247]++;
            evalTextArea.cut();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[553]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[248]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[554]++;
int CodeCoverConditionCoverageHelper_C119; if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((cmd.equals("Copy")) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[249]++;
            evalTextArea.copy();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[555]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[250]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[556]++;
int CodeCoverConditionCoverageHelper_C120; if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((cmd.equals("Paste")) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[251]++;
            evalTextArea.paste();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[557]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[252]++;}
}
}
    }
}

/**
 * Internal frame for the console.
 */
class JSInternalConsole extends JInternalFrame implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -5523468828771087292L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[558]++;
  }

    /**
     * Creates a new JSInternalConsole.
     */
    public JSInternalConsole(String name) {
        super(name, true, false, true, true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[559]++;
        consoleTextArea = new ConsoleTextArea(null);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[560]++;
        consoleTextArea.setRows(24);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[561]++;
        consoleTextArea.setColumns(80);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[562]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[563]++;
        JScrollPane scroller = new JScrollPane(consoleTextArea);
        setContentPane(scroller);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[564]++;
        pack();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[565]++;
        addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameActivated(InternalFrameEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[567]++;
int CodeCoverConditionCoverageHelper_C121;
                    // hack
                    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((consoleTextArea.hasFocus()) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[253]++;
                        consoleTextArea.getCaret().setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[568]++;
                        consoleTextArea.getCaret().setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[569]++;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[254]++;}
                }
            });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[566]++;
    }

    /**
     * The console text area.
     */
    ConsoleTextArea consoleTextArea;

    /**
     * Returns the input stream of the console text area.
     */
    public InputStream getIn() {
        return consoleTextArea.getIn();
    }

    /**
     * Returns the output stream of the console text area.
     */
    public PrintStream getOut() {
        return consoleTextArea.getOut();
    }

    /**
     * Returns the error stream of the console text area.
     */
    public PrintStream getErr() {
        return consoleTextArea.getErr();
    }

    // ActionListener

    /**
     * Performs an action on the text area.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[570]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[571]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((cmd.equals("Cut")) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[255]++;
            consoleTextArea.cut();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[572]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[256]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[573]++;
int CodeCoverConditionCoverageHelper_C123; if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((cmd.equals("Copy")) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[257]++;
            consoleTextArea.copy();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[574]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[258]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[575]++;
int CodeCoverConditionCoverageHelper_C124; if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((cmd.equals("Paste")) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[259]++;
            consoleTextArea.paste();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[576]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[260]++;}
}
}
    }
}

/**
 * Popup menu class for right-clicking on {@link FileTextArea}s.
 */
class FilePopupMenu extends JPopupMenu {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 3589525009546013565L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[577]++;
  }

    /**
     * The popup x position.
     */
    int x;

    /**
     * The popup y position.
     */
    int y;

    /**
     * Creates a new FilePopupMenu.
     */
    public FilePopupMenu(FileTextArea w) {
        JMenuItem item;
        add(item = new JMenuItem("Set Breakpoint"));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[578]++;
        item.addActionListener(w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[579]++;
        add(item = new JMenuItem("Clear Breakpoint"));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[580]++;
        item.addActionListener(w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[581]++;
        add(item = new JMenuItem("Run"));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[582]++;
        item.addActionListener(w);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[583]++;
    }

    /**
     * Displays the menu at the given coordinates.
     */
    public void show(JComponent comp, int x, int y) {
        this.x = x;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[584]++;
        this.y = y;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[585]++;
        super.show(comp, x, y);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[586]++;
    }
}

/**
 * Text area to display script source.
 */
class FileTextArea
    extends JTextArea
    implements ActionListener, PopupMenuListener, KeyListener, MouseListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -25032065448563720L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[587]++;
  }

    /**
     * The owning {@link FileWindow}.
     */
    private FileWindow w;

    /**
     * The popup menu.
     */
    private FilePopupMenu popup;

    /**
     * Creates a new FileTextArea.
     */
    public FileTextArea(FileWindow w) {
        this.w = w;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[588]++;
        popup = new FilePopupMenu(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[589]++;
        popup.addPopupMenuListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[590]++;
        addMouseListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[591]++;
        addKeyListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[592]++;
        setFont(new Font("Monospaced", 0, 12));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[593]++;
    }

    /**
     * Moves the selection to the given offset.
     */
    public void select(int pos) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[594]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[261]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[595]++;
boolean CodeCoverTryBranchHelper_Try16 = false;
            try {
CodeCoverTryBranchHelper_Try16 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[596]++;
                int line = getLineOfOffset(pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[597]++;
                Rectangle rect = modelToView(pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[598]++;
int CodeCoverConditionCoverageHelper_C126;
                if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((rect == null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[264]++;
                    select(pos, pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[599]++;

                } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[265]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[600]++;
boolean CodeCoverTryBranchHelper_Try17 = false;
                    try {
CodeCoverTryBranchHelper_Try17 = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[601]++;
                        Rectangle nrect =
                            modelToView(getLineStartOffset(line + 1));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[602]++;
int CodeCoverConditionCoverageHelper_C127;
                        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((nrect != null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[267]++;
                            rect = nrect;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[603]++;

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[268]++;}
                    } catch (Exception exc) {
CodeCoverTryBranchHelper_Try17 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[269]++;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try17 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[266]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[604]++;
                    JViewport vp = (JViewport)getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[605]++;
                    Rectangle viewRect = vp.getViewRect();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[606]++;
int CodeCoverConditionCoverageHelper_C128;
                    if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((viewRect.y + viewRect.height > rect.y) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[270]++;
                        // need to scroll up
                        select(pos, pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[607]++;

                    } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[271]++;
                        // need to scroll down
                        rect.y += (viewRect.height - rect.height)/2;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[608]++;
                        scrollRectToVisible(rect);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[609]++;
                        select(pos, pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[610]++;
                    }
                }
            } catch (BadLocationException exc) {
CodeCoverTryBranchHelper_Try16 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[272]++;
                select(pos, pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[611]++;
                //exc.printStackTrace();
            } finally {
    if ( CodeCoverTryBranchHelper_Try16 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[263]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[262]++;}
    }

    /**
     * Checks if the popup menu should be shown.
     */
    private void checkPopup(MouseEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[612]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((e.isPopupTrigger()) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[273]++;
            popup.show(this, e.getX(), e.getY());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[613]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[274]++;}
    }

    // MouseListener

    /**
     * Called when a mouse button is pressed.
     */
    public void mousePressed(MouseEvent e) {
        checkPopup(e);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[614]++;
    }

    /**
     * Called when the mouse is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        checkPopup(e);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[615]++;
        requestFocus();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[616]++;
        getCaret().setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[617]++;
    }

    /**
     * Called when the mouse enters the component.
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Called when the mouse exits the component.
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Called when a mouse button is released.
     */
    public void mouseReleased(MouseEvent e) {
        checkPopup(e);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[618]++;
    }

    // PopupMenuListener

    /**
     * Called before the popup menu will become visible.
     */
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
    }

    /**
     * Called before the popup menu will become invisible.
     */
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    /**
     * Called when the popup menu is cancelled.
     */
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    // ActionListener

    /**
     * Performs an action.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[619]++;
        int pos = viewToModel(new Point(popup.x, popup.y));
        popup.setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[620]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[621]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[622]++;
        int line = -1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[623]++;
boolean CodeCoverTryBranchHelper_Try18 = false;
        try {
CodeCoverTryBranchHelper_Try18 = true;
            line = getLineOfOffset(pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[624]++;
        } catch (Exception exc) {
CodeCoverTryBranchHelper_Try18 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[276]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try18 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[275]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[625]++;
int CodeCoverConditionCoverageHelper_C130;
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((cmd.equals("Set Breakpoint")) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[277]++;
            w.setBreakPoint(line + 1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[626]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[278]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[627]++;
int CodeCoverConditionCoverageHelper_C131; if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((cmd.equals("Clear Breakpoint")) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[279]++;
            w.clearBreakPoint(line + 1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[628]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[280]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[629]++;
int CodeCoverConditionCoverageHelper_C132; if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((cmd.equals("Run")) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[281]++;
            w.load();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[630]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[282]++;}
}
}
    }

    // KeyListener

    /**
     * Called when a key is pressed.
     */
    public void keyPressed(KeyEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[631]++;
        switch (e.getKeyCode()) {
        case KeyEvent.VK_BACK_SPACE:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[283]++;
        case KeyEvent.VK_ENTER:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[284]++;
        case KeyEvent.VK_DELETE:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[285]++;
        case KeyEvent.VK_TAB:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[286]++;
            e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[632]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[633]++;
            break; default : CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[287]++;
        }
    }

    /**
     * Called when a key is typed.
     */
    public void keyTyped(KeyEvent e) {
        e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[634]++;
    }

    /**
     * Called when a key is released.
     */
    public void keyReleased(KeyEvent e) {
        e.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[635]++;
    }
}

/**
 * Dialog to list the available windows.
 */
class MoreWindows extends JDialog implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 5177066296457377546L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[636]++;
  }

    /**
     * Last selected value.
     */
    private String value;

    /**
     * The list component.
     */
    private JList list;

    /**
     * Our parent frame.
     */
    private SwingGui swingGui;

    /**
     * The "Select" button.
     */
    private JButton setButton;

    /**
     * The "Cancel" button.
     */
    private JButton cancelButton;

    /**
     * Creates a new MoreWindows.
     */
    MoreWindows(SwingGui frame, Map<String,FileWindow> fileWindows, String title,
                String labelText) {
        super(frame, title, true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[637]++;
        this.swingGui = frame;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[638]++;
        //buttons
        cancelButton = new JButton("Cancel");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[639]++;
        setButton = new JButton("Select");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[640]++;
        cancelButton.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[641]++;
        setButton.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[642]++;
        getRootPane().setDefaultButton(setButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[643]++;

        //dim part of the dialog
        list = new JList(new DefaultListModel());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[644]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[645]++;
        DefaultListModel model = (DefaultListModel)list.getModel();
        model.clear();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[646]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[647]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[31]++;


        //model.fireIntervalRemoved(model, 0, size);
        for (String data: fileWindows.keySet()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[31]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[32]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[33]++;
}
            model.addElement(data);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[648]++;
        }
        list.setSelectedIndex(0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[649]++;
        //model.fireIntervalAdded(model, 0, data.length);
        setButton.setEnabled(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[650]++;
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[651]++;
        list.addMouseListener(new MouseHandler());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[652]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[653]++;
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(320, 240));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[654]++;
        //XXX: Must do the following, too, or else the scroller thinks
        //XXX: it's taller than it is:
        listScroller.setMinimumSize(new Dimension(250, 80));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[655]++;
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[656]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[657]++;

        //Create a container so that we can add a title around
        //the scroll pane.  Can't add a title directly to the
        //scroll pane because its background would be white.
        //Lay out the label and scroll pane from top to button.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[658]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[659]++;
        JLabel label = new JLabel(labelText);
        label.setLabelFor (list);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[660]++;
        listPane.add(label);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[661]++;
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[662]++;
        listPane.add(listScroller);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[663]++;
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[664]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[665]++;

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[666]++;
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[667]++;
        buttonPane.add(Box.createHorizontalGlue());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[668]++;
        buttonPane.add(cancelButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[669]++;
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[670]++;
        buttonPane.add(setButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[671]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[672]++;

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[673]++;
        contentPane.add(buttonPane, BorderLayout.SOUTH);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[674]++;
        pack();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[675]++;
        addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent ke) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[677]++;
                    int code = ke.getKeyCode();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[678]++;
int CodeCoverConditionCoverageHelper_C133;
                    if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_ESCAPE) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[288]++;
                        ke.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[679]++;
                        value = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[680]++;
                        setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[681]++;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[289]++;}
                }
            });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[676]++;
    }

    /**
     * Shows the dialog.
     */
    public String showDialog(Component comp) {
        value = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[682]++;
        setLocationRelativeTo(comp);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[683]++;
        setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[684]++;
        return value;
    }

    // ActionListener

    /**
     * Performs an action.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[685]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[686]++;
int CodeCoverConditionCoverageHelper_C134;
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((cmd.equals("Cancel")) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[290]++;
            setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[687]++;
            value = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[688]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[291]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[689]++;
int CodeCoverConditionCoverageHelper_C135; if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((cmd.equals("Select")) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[292]++;
            value = (String)list.getSelectedValue();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[690]++;
            setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[691]++;
            swingGui.showFileWindow(value, -1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[692]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[293]++;}
}
    }

    /**
     * MouseListener implementation for {@link #list}.
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[693]++;
int CodeCoverConditionCoverageHelper_C136;
            if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((e.getClickCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[294]++;
                setButton.doClick();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[694]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[295]++;}
        }
    }
}

/**
 * Find function dialog.
 */
class FindFunction extends JDialog implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 559491015232880916L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[695]++;
  }

    /**
     * Last selected function.
     */
    private String value;

    /**
     * List of functions.
     */
    private JList list;

    /**
     * The debug GUI frame.
     */
    private SwingGui debugGui;

    /**
     * The "Select" button.
     */
    private JButton setButton;

    /**
     * The "Cancel" button.
     */
    private JButton cancelButton;

    /**
     * Creates a new FindFunction.
     */
    public FindFunction(SwingGui debugGui, String title, String labelText) {
        super(debugGui, title, true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[696]++;
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[697]++;

        cancelButton = new JButton("Cancel");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[698]++;
        setButton = new JButton("Select");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[699]++;
        cancelButton.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[700]++;
        setButton.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[701]++;
        getRootPane().setDefaultButton(setButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[702]++;

        list = new JList(new DefaultListModel());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[703]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[704]++;
        DefaultListModel model = (DefaultListModel)list.getModel();
        model.clear();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[705]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[706]++;

        String[] a = debugGui.dim.functionNames();
        java.util.Arrays.sort(a);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[707]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[708]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[34]++;


int CodeCoverConditionCoverageHelper_C137;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((i < a.length) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[34]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[35]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[36]++;
}
            model.addElement(a[i]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[709]++;
        }
        list.setSelectedIndex(0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[710]++;

        setButton.setEnabled(a.length > 0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[711]++;
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[712]++;
        list.addMouseListener(new MouseHandler());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[713]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[714]++;
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(320, 240));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[715]++;
        listScroller.setMinimumSize(new Dimension(250, 80));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[716]++;
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[717]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[718]++;

        //Create a container so that we can add a title around
        //the scroll pane.  Can't add a title directly to the
        //scroll pane because its background would be white.
        //Lay out the label and scroll pane from top to button.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[719]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[720]++;
        JLabel label = new JLabel(labelText);
        label.setLabelFor (list);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[721]++;
        listPane.add(label);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[722]++;
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[723]++;
        listPane.add(listScroller);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[724]++;
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[725]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[726]++;

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[727]++;
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[728]++;
        buttonPane.add(Box.createHorizontalGlue());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[729]++;
        buttonPane.add(cancelButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[730]++;
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[731]++;
        buttonPane.add(setButton);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[732]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[733]++;

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[734]++;
        contentPane.add(buttonPane, BorderLayout.SOUTH);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[735]++;
        pack();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[736]++;
        addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent ke) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[738]++;
                    int code = ke.getKeyCode();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[739]++;
int CodeCoverConditionCoverageHelper_C138;
                    if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_ESCAPE) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[296]++;
                        ke.consume();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[740]++;
                        value = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[741]++;
                        setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[742]++;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[297]++;}
                }
            });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[737]++;
    }

    /**
     * Shows the dialog.
     */
    public String showDialog(Component comp) {
        value = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[743]++;
        setLocationRelativeTo(comp);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[744]++;
        setVisible(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[745]++;
        return value;
    }

    // ActionListener

    /**
     * Performs an action.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[746]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[747]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((cmd.equals("Cancel")) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[298]++;
            setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[748]++;
            value = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[749]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[299]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[750]++;
int CodeCoverConditionCoverageHelper_C140; if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((cmd.equals("Select")) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[300]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[751]++;
int CodeCoverConditionCoverageHelper_C141;
            if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((list.getSelectedIndex() < 0) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[302]++;
                return;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[303]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[752]++;
boolean CodeCoverTryBranchHelper_Try19 = false;
            try {
CodeCoverTryBranchHelper_Try19 = true;
                value = (String)list.getSelectedValue();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[753]++;
            } catch (ArrayIndexOutOfBoundsException exc) {
CodeCoverTryBranchHelper_Try19 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[305]++;
                return;
            } finally {
    if ( CodeCoverTryBranchHelper_Try19 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[304]++;
}
  }
            setVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[754]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[755]++;
            Dim.FunctionSource item = debugGui.dim.functionSourceByName(value);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[756]++;
int CodeCoverConditionCoverageHelper_C142;
            if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[306]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[757]++;
                Dim.SourceInfo si = item.sourceInfo();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[758]++;
                String url = si.url();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[759]++;
                int lineNumber = item.firstLine();
                debugGui.showFileWindow(url, lineNumber);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[760]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[307]++;}

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[301]++;}
}
    }

    /**
     * MouseListener implementation for {@link #list}.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[761]++;
int CodeCoverConditionCoverageHelper_C143;
            if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((e.getClickCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[308]++;
                setButton.doClick();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[762]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[309]++;}
        }
    }
}

/**
 * Gutter for FileWindows.
 */
class FileHeader extends JPanel implements MouseListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -2858905404778259127L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[763]++;
  }

    /**
     * The line that the mouse was pressed on.
     */
    private int pressLine = -1;
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[764]++;
  }

    /**
     * The owning FileWindow.
     */
    private FileWindow fileWindow;

    /**
     * Creates a new FileHeader.
     */
    public FileHeader(FileWindow fileWindow) {
        this.fileWindow = fileWindow;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[765]++;
        addMouseListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[766]++;
        update();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[767]++;
    }

    /**
     * Updates the gutter.
     */
    public void update() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[768]++;
        FileTextArea textArea = fileWindow.textArea;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[769]++;
        Font font = textArea.getFont();
        setFont(font);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[770]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[771]++;
        FontMetrics metrics = getFontMetrics(font);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[772]++;
        int h = metrics.getHeight();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[773]++;
        int lineCount = textArea.getLineCount() + 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[774]++;
        String dummy = Integer.toString(lineCount);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[775]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((dummy.length() < 2) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[310]++;
            dummy = "99";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[776]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[311]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[777]++;
        Dimension d = new Dimension();
        d.width = metrics.stringWidth(dummy) + 16;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[778]++;
        d.height = lineCount * h + 100;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[779]++;
        setPreferredSize(d);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[780]++;
        setSize(d);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[781]++;
    }

    /**
     * Paints the component.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[782]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[783]++;
        FileTextArea textArea = fileWindow.textArea;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[784]++;
        Font font = textArea.getFont();
        g.setFont(font);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[785]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[786]++;
        FontMetrics metrics = getFontMetrics(font);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[787]++;
        Rectangle clip = g.getClipBounds();
        g.setColor(getBackground());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[788]++;
        g.fillRect(clip.x, clip.y, clip.width, clip.height);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[789]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[790]++;
        int ascent = metrics.getMaxAscent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[791]++;
        int h = metrics.getHeight();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[792]++;
        int lineCount = textArea.getLineCount() + 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[793]++;
        String dummy = Integer.toString(lineCount);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[794]++;
int CodeCoverConditionCoverageHelper_C145;
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((dummy.length() < 2) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[312]++;
            dummy = "99";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[795]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[313]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[796]++;
        int startLine = clip.y / h;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[797]++;
        int endLine = (clip.y + clip.height) / h + 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[798]++;
        int width = getWidth();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[799]++;
int CodeCoverConditionCoverageHelper_C146;
        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((endLine > lineCount) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[314]++; endLine = lineCount;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[800]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[315]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[801]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[37]++;


int CodeCoverConditionCoverageHelper_C147;
        for (int i = startLine;(((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((i < endLine) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[37]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[38]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[39]++;
}
            String text;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[802]++;
            int pos = -2;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[803]++;
boolean CodeCoverTryBranchHelper_Try20 = false;
            try {
CodeCoverTryBranchHelper_Try20 = true;
                pos = textArea.getLineStartOffset(i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[804]++;
            } catch (BadLocationException ignored) {
CodeCoverTryBranchHelper_Try20 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[317]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try20 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[316]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[805]++;
            boolean isBreakPoint = fileWindow.isBreakPoint(i + 1);
            text = Integer.toString(i + 1) + " ";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[806]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[807]++;
            int y = i * h;
            g.setColor(Color.blue);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[808]++;
            g.drawString(text, 0, y + ascent);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[809]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[810]++;
            int x = width - ascent;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[811]++;
int CodeCoverConditionCoverageHelper_C148;
            if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((isBreakPoint) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[318]++;
                g.setColor(new Color(0x80, 0x00, 0x00));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[812]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[813]++;
                int dy = y + ascent - 9;
                g.fillOval(x, dy, 9, 9);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[814]++;
                g.drawOval(x, dy, 8, 8);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[815]++;
                g.drawOval(x, dy, 9, 9);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[816]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[319]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[817]++;
int CodeCoverConditionCoverageHelper_C149;
            if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((pos == fileWindow.currentPos) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[320]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[818]++;
                Polygon arrow = new Polygon();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[819]++;
                int dx = x;
                y += ascent - 10;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[820]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[821]++;
                int dy = y;
                arrow.addPoint(dx, dy + 3);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[822]++;
                arrow.addPoint(dx + 5, dy + 3);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[823]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[824]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[40]++;


int CodeCoverConditionCoverageHelper_C150;
                for (x = dx + 5;(((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((x <= dx + 10) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false); x++, y++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[40]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[41]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[42]++;
}
                    arrow.addPoint(x, y);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[825]++;
                }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[826]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[43]++;


int CodeCoverConditionCoverageHelper_C151;
                for (x = dx + 9;(((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((x >= dx + 5) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false); x--, y++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[43]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[44]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[45]++;
}
                    arrow.addPoint(x, y);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[827]++;
                }
                arrow.addPoint(dx + 5, dy + 7);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[828]++;
                arrow.addPoint(dx, dy + 7);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[829]++;
                g.setColor(Color.yellow);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[830]++;
                g.fillPolygon(arrow);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[831]++;
                g.setColor(Color.black);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[832]++;
                g.drawPolygon(arrow);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[833]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[321]++;}
        }
    }

    // MouseListener

    /**
     * Called when the mouse enters the component.
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Called when a mouse button is pressed.
     */
    public void mousePressed(MouseEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[834]++;
        Font font = fileWindow.textArea.getFont();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[835]++;
        FontMetrics metrics = getFontMetrics(font);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[836]++;
        int h = metrics.getHeight();
        pressLine = e.getY() / h;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[837]++;
    }

    /**
     * Called when the mouse is clicked.
     */
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Called when the mouse exits the component.
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Called when a mouse button is released.
     */
    public void mouseReleased(MouseEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[838]++;
int CodeCoverConditionCoverageHelper_C152;
        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (8)) == 0 || true) &&
 ((e.getComponent() == this) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[322]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[839]++;
            int y = e.getY();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[840]++;
            Font font = fileWindow.textArea.getFont();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[841]++;
            FontMetrics metrics = getFontMetrics(font);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[842]++;
            int h = metrics.getHeight();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[843]++;
            int line = y/h;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[844]++;
int CodeCoverConditionCoverageHelper_C153;
            if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((line == pressLine) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[324]++;
                fileWindow.toggleBreakPoint(line + 1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[845]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[325]++;
                pressLine = -1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[846]++;
            }

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[323]++;}
    }
}

/**
 * An internal frame for script files.
 */
class FileWindow extends JInternalFrame implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = -6212382604952082370L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[847]++;
  }

    /**
     * The debugger GUI.
     */
    private SwingGui debugGui;

    /**
     * The SourceInfo object that describes the file.
     */
    private Dim.SourceInfo sourceInfo;

    /**
     * The FileTextArea that displays the file.
     */
    FileTextArea textArea;

    /**
     * The FileHeader that is the gutter for {@link #textArea}.
     */
    private FileHeader fileHeader;

    /**
     * Scroll pane for containing {@link #textArea}.
     */
    private JScrollPane p;

    /**
     * The current offset position.
     */
    int currentPos;

    /**
     * Loads the file.
     */
    void load() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[848]++;
        String url = getUrl();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[849]++;
int CodeCoverConditionCoverageHelper_C154;
        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[326]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[850]++;
            RunProxy proxy = new RunProxy(debugGui, RunProxy.LOAD_FILE);
            proxy.fileName = url;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[851]++;
            proxy.text = sourceInfo.source();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[852]++;
            new Thread(proxy).start();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[853]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[327]++;}
    }

    /**
     * Returns the offset position for the given line.
     */
    public int getPosition(int line) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[854]++;
        int result = -1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[855]++;
boolean CodeCoverTryBranchHelper_Try21 = false;
        try {
CodeCoverTryBranchHelper_Try21 = true;
            result = textArea.getLineStartOffset(line);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[856]++;
        } catch (javax.swing.text.BadLocationException exc) {
CodeCoverTryBranchHelper_Try21 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[329]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try21 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[328]++;
}
  }
        return result;
    }

    /**
     * Returns whether the given line has a breakpoint.
     */
    public boolean isBreakPoint(int line) {
        return sourceInfo.breakableLine(line) && sourceInfo.breakpoint(line);
    }

    /**
     * Toggles the breakpoint on the given line.
     */
    public void toggleBreakPoint(int line) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[857]++;
int CodeCoverConditionCoverageHelper_C155;
        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((isBreakPoint(line)) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[330]++;
            setBreakPoint(line);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[858]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[331]++;
            clearBreakPoint(line);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[859]++;
        }
    }

    /**
     * Sets a breakpoint on the given line.
     */
    public void setBreakPoint(int line) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[860]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((sourceInfo.breakableLine(line)) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[332]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[861]++;
            boolean changed = sourceInfo.breakpoint(line, true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[862]++;
int CodeCoverConditionCoverageHelper_C157;
            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[334]++;
                fileHeader.repaint();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[863]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[335]++;}

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[333]++;}
    }

    /**
     * Clears a breakpoint from the given line.
     */
    public void clearBreakPoint(int line) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[864]++;
int CodeCoverConditionCoverageHelper_C158;
        if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((sourceInfo.breakableLine(line)) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[336]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[865]++;
            boolean changed = sourceInfo.breakpoint(line, false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[866]++;
int CodeCoverConditionCoverageHelper_C159;
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[338]++;
                fileHeader.repaint();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[867]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[339]++;}

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[337]++;}
    }

    /**
     * Creates a new FileWindow.
     */
    public FileWindow(SwingGui debugGui, Dim.SourceInfo sourceInfo) {
        super(SwingGui.getShortName(sourceInfo.url()),
              true, true, true, true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[868]++;
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[869]++;
        this.sourceInfo = sourceInfo;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[870]++;
        updateToolTip();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[871]++;
        currentPos = -1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[872]++;
        textArea = new FileTextArea(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[873]++;
        textArea.setRows(24);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[874]++;
        textArea.setColumns(80);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[875]++;
        p = new JScrollPane();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[876]++;
        fileHeader = new FileHeader(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[877]++;
        p.setViewportView(textArea);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[878]++;
        p.setRowHeaderView(fileHeader);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[879]++;
        setContentPane(p);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[880]++;
        pack();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[881]++;
        updateText(sourceInfo);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[882]++;
        textArea.select(0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[883]++;
    }

    /**
     * Updates the tool tip contents.
     */
    private void updateToolTip() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[884]++;
        // Try to set tool tip on frame. On Mac OS X 10.5,
        // the number of components is different, so try to be safe.
        int n = getComponentCount() - 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[885]++;
int CodeCoverConditionCoverageHelper_C160;
        if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((n > 1) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[340]++;
            n = 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[886]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[341]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[887]++;
int CodeCoverConditionCoverageHelper_C161; if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[342]++;
            return;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[343]++;}
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[888]++;
        Component c = getComponent(n);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[889]++;
int CodeCoverConditionCoverageHelper_C162;
        // this will work at least for Metal L&F
        if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((c instanceof JComponent) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[344]++;
            ((JComponent)c).setToolTipText(getUrl());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[890]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[345]++;}
    }

    /**
     * Returns the URL of the source.
     */
    public String getUrl() {
        return sourceInfo.url();
    }

    /**
     * Called when the text of the script has changed.
     */
    public void updateText(Dim.SourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[891]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[892]++;
        String newText = sourceInfo.source();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[893]++;
int CodeCoverConditionCoverageHelper_C163;
        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((textArea.getText().equals(newText)) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[346]++;
            textArea.setText(newText);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[894]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[895]++;
            int pos = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[896]++;
int CodeCoverConditionCoverageHelper_C164;
            if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((currentPos != -1) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[348]++;
                pos = currentPos;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[897]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[349]++;}
            textArea.select(pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[898]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[347]++;}
        fileHeader.update();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[899]++;
        fileHeader.repaint();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[900]++;
    }

    /**
     * Sets the cursor position.
     */
    public void setPosition(int pos) {
        textArea.select(pos);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[901]++;
        currentPos = pos;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[902]++;
        fileHeader.repaint();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[903]++;
    }

    /**
     * Selects a range of characters.
     */
    public void select(int start, int end) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[904]++;
        int docEnd = textArea.getDocument().getLength();
        textArea.select(docEnd, docEnd);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[905]++;
        textArea.select(start, end);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[906]++;
    }

    /**
     * Disposes this FileWindow.
     */
    @Override
    public void dispose() {
        debugGui.removeWindow(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[907]++;
        super.dispose();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[908]++;
    }

    // ActionListener

    /**
     * Performs an action.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[909]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[910]++;
int CodeCoverConditionCoverageHelper_C165;
        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((cmd.equals("Cut")) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[350]++;

            // textArea.cut();
        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[351]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[911]++;
int CodeCoverConditionCoverageHelper_C166; if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((cmd.equals("Copy")) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[352]++;
            textArea.copy();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[912]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[353]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[913]++;
int CodeCoverConditionCoverageHelper_C167; if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((cmd.equals("Paste")) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[354]++;

            // textArea.paste();
        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[355]++;}
}
}
    }
}

/**
 * Table model class for watched expressions.
 */
class MyTableModel extends AbstractTableModel {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 2971618907207577000L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[914]++;
  }

    /**
     * The debugger GUI.
     */
    private SwingGui debugGui;

    /**
     * List of watched expressions.
     */
    private List<String> expressions;

    /**
     * List of values from evaluated from {@link #expressions}.
     */
    private List<String> values;

    /**
     * Creates a new MyTableModel.
     */
    public MyTableModel(SwingGui debugGui) {
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[915]++;
        expressions = Collections.synchronizedList(new ArrayList<String>());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[916]++;
        values = Collections.synchronizedList(new ArrayList<String>());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[917]++;
        expressions.add("");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[918]++;
        values.add("");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[919]++;
    }

    /**
     * Returns the number of columns in the table (2).
     */
    public int getColumnCount() {
        return 2;
    }

    /**
     * Returns the number of rows in the table.
     */
    public int getRowCount() {
        return expressions.size();
    }

    /**
     * Returns the name of the given column.
     */
    @Override
    public String getColumnName(int column) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[920]++;
        switch (column) {
        case 0:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[356]++;
            return "Expression";
        case 1:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[357]++;
            return "Value"; default : CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[358]++;
        }
        return null;
    }

    /**
     * Returns whether the given cell is editable.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    /**
     * Returns the value in the given cell.
     */
    public Object getValueAt(int row, int column) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[921]++;
        switch (column) {
        case 0:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[359]++;
            return expressions.get(row);
        case 1:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[360]++;
            return values.get(row); default : CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[361]++;
        }
        return "";
    }

    /**
     * Sets the value in the given cell.
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[922]++;
        switch (column) {
        case 0:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[362]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[923]++;
            String expr = value.toString();
            expressions.set(row, expr);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[924]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[925]++;
            String result = "";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[926]++;
int CodeCoverConditionCoverageHelper_C168;
            if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((expr.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[363]++;
                result = debugGui.dim.eval(expr);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[927]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[928]++;
int CodeCoverConditionCoverageHelper_C169;
                if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[365]++; result = "";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[929]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[366]++;}

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[364]++;}
            values.set(row, result);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[930]++;
            updateModel();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[931]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[932]++;
int CodeCoverConditionCoverageHelper_C170;
            if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((row + 1 == expressions.size()) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[367]++;
                expressions.add("");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[933]++;
                values.add("");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[934]++;
                fireTableRowsInserted(row + 1, row + 1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[935]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[368]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[936]++;
            break;
        case 1:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[369]++;
            // just reset column 2; ignore edits
            fireTableDataChanged();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[937]++; default : CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[370]++;
        }
    }

    /**
     * Re-evaluates the expressions in the table.
     */
    void updateModel() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[938]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[46]++;


int CodeCoverConditionCoverageHelper_C171;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((i < expressions.size()) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[46]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[47]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[48]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[939]++;
            String expr = expressions.get(i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[940]++;
            String result = "";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[941]++;
int CodeCoverConditionCoverageHelper_C172;
            if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((expr.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[371]++;
                result = debugGui.dim.eval(expr);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[942]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[943]++;
int CodeCoverConditionCoverageHelper_C173;
                if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[373]++; result = "";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[944]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[374]++;}

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[372]++;
                result = "";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[945]++;
            }
            result = result.replace('\n', ' ');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[946]++;
            values.set(i, result);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[947]++;
        }
        fireTableDataChanged();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[948]++;
    }
}

/**
 * A table for evaluated expressions.
 */
class Evaluator extends JTable {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 8133672432982594256L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[949]++;
  }

    /**
     * The {@link TableModel} for this table.
     */
    MyTableModel tableModel;

    /**
     * Creates a new Evaluator.
     */
    public Evaluator(SwingGui debugGui) {
        super(new MyTableModel(debugGui));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[950]++;
        tableModel = (MyTableModel)getModel();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[951]++;
    }
}

/**
 * Tree model for script object inspection.
 */
class VariableModel implements TreeTableModel {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final String[] cNames = { " Name", " Value" };
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[952]++;
  }

    /**
     * Tree column types.
     */
    private static final Class<?>[] cTypes =
        { TreeTableModel.class, String.class };
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[953]++;
  }

    /**
     * Empty {@link VariableNode} array.
     */
    private static final VariableNode[] CHILDLESS = new VariableNode[0];
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[954]++;
  }

    /**
     * The debugger.
     */
    private Dim debugger;

    /**
     * The root node.
     */
    private VariableNode root;

    /**
     * Creates a new VariableModel.
     */
    public VariableModel() {
    }

    /**
     * Creates a new VariableModel.
     */
    public VariableModel(Dim debugger, Object scope) {
        this.debugger = debugger;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[955]++;
        this.root = new VariableNode(scope, "this");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[956]++;
    }

    // TreeTableModel

    /**
     * Returns the root node of the tree.
     */
    public Object getRoot() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[957]++;
int CodeCoverConditionCoverageHelper_C174;
        if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((debugger == null) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[375]++;
            return null;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[376]++;}
        return root;
    }

    /**
     * Returns the number of children of the given node.
     */
    public int getChildCount(Object nodeObj) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[958]++;
int CodeCoverConditionCoverageHelper_C175;
        if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((debugger == null) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[377]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[378]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[959]++;
        VariableNode node = (VariableNode) nodeObj;
        return children(node).length;
    }

    /**
     * Returns a child of the given node.
     */
    public Object getChild(Object nodeObj, int i) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[960]++;
int CodeCoverConditionCoverageHelper_C176;
        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((debugger == null) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[379]++;
            return null;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[380]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[961]++;
        VariableNode node = (VariableNode) nodeObj;
        return children(node)[i];
    }

    /**
     * Returns whether the given node is a leaf node.
     */
    public boolean isLeaf(Object nodeObj) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[962]++;
int CodeCoverConditionCoverageHelper_C177;
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((debugger == null) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[381]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[382]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[963]++;
        VariableNode node = (VariableNode) nodeObj;
        return children(node).length == 0;
    }

    /**
     * Returns the index of a node under its parent.
     */
    public int getIndexOfChild(Object parentObj, Object childObj) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[964]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((debugger == null) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[383]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[384]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[965]++;
        VariableNode parent = (VariableNode) parentObj;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[966]++;
        VariableNode child = (VariableNode) childObj;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[967]++;
        VariableNode[] children = children(parent);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[968]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[49]++;


int CodeCoverConditionCoverageHelper_C179;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((i != children.length) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[49]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[50]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[51]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[969]++;
int CodeCoverConditionCoverageHelper_C180;
            if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((children[i] == child) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[385]++;
                return i;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[386]++;}
        }
        return -1;
    }

    /**
     * Returns whether the given cell is editable.
     */
    public boolean isCellEditable(Object node, int column) {
        return column == 0;
    }

    /**
     * Sets the value at the given cell.
     */
    public void setValueAt(Object value, Object node, int column) { }

    /**
     * Adds a TreeModelListener to this tree.
     */
    public void addTreeModelListener(TreeModelListener l) { }

    /**
     * Removes a TreeModelListener from this tree.
     */
    public void removeTreeModelListener(TreeModelListener l) { }

    public void valueForPathChanged(TreePath path, Object newValue) { }

    // TreeTableNode

    /**
     * Returns the number of columns.
     */
    public int getColumnCount() {
        return cNames.length;
    }

    /**
     * Returns the name of the given column.
     */
    public String getColumnName(int column) {
        return cNames[column];
    }

    /**
     * Returns the type of value stored in the given column.
     */
    public Class<?> getColumnClass(int column) {
        return cTypes[column];
    }

    /**
     * Returns the value at the given cell.
     */
    public Object getValueAt(Object nodeObj, int column) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[970]++;
int CodeCoverConditionCoverageHelper_C181;
        if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((debugger == null) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[387]++; return null;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[388]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[971]++;
        VariableNode node = (VariableNode)nodeObj;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[972]++;
        switch (column) {
        case 0:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[389]++; // Name
            return node.toString();
        case 1:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[390]++; // Value
            String result;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[973]++;
boolean CodeCoverTryBranchHelper_Try22 = false;
            try {
CodeCoverTryBranchHelper_Try22 = true;
                result = debugger.objectToString(getValue(node));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[974]++;
            } catch (RuntimeException exc) {
CodeCoverTryBranchHelper_Try22 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[392]++;
                result = exc.getMessage();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[975]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try22 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[391]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[976]++;
            StringBuffer buf = new StringBuffer();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[977]++;
            int len = result.length();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[978]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[52]++;


int CodeCoverConditionCoverageHelper_C182;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[52]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[53]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[54]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[979]++;
                char ch = result.charAt(i);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[980]++;
int CodeCoverConditionCoverageHelper_C183;
                if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((Character.isISOControl(ch)) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[393]++;
                    ch = ' ';
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[981]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[394]++;}
                buf.append(ch);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[982]++;
            }
            return buf.toString(); default : CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[395]++;
        }
        return null;
    }

    /**
     * Returns an array of the children of the given node.
     */
    private VariableNode[] children(VariableNode node) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[983]++;
int CodeCoverConditionCoverageHelper_C184;
        if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((node.children != null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[396]++;
            return node.children;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[397]++;}

        VariableNode[] children;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[984]++;

        Object value = getValue(node);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[985]++;
        Object[] ids = debugger.getObjectIds(value);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[986]++;
int CodeCoverConditionCoverageHelper_C185;
        if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (8)) == 0 || true) &&
 ((ids == null) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((ids.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[398]++;
            children = CHILDLESS;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[987]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[399]++;
            Arrays.sort(ids, new Comparator<Object>() {
                    public int compare(Object l, Object r)
                    {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[989]++;
int CodeCoverConditionCoverageHelper_C186;
                        if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((l instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[400]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[990]++;
int CodeCoverConditionCoverageHelper_C187;
                            if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((r instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[402]++;
                                return -1;

                            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[403]++;}
                            return ((String)l).compareToIgnoreCase((String)r);

                        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[401]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[991]++;
int CodeCoverConditionCoverageHelper_C188;
                            if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((r instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[404]++;
                                return 1;

                            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[405]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[992]++;
                            int lint = ((Integer)l).intValue();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[993]++;
                            int rint = ((Integer)r).intValue();
                            return lint - rint;
                        }
                    }
            });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[988]++;
            children = new VariableNode[ids.length];
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[994]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[995]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[55]++;


int CodeCoverConditionCoverageHelper_C189;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((i != ids.length) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[55]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[56]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[57]++;
}
                children[i] = new VariableNode(value, ids[i]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[996]++;
            }
        }
        node.children = children;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[997]++;
        return children;
    }

    /**
     * Returns the value of the given node.
     */
    public Object getValue(VariableNode node) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[998]++;
boolean CodeCoverTryBranchHelper_Try23 = false;
        try {
CodeCoverTryBranchHelper_Try23 = true;
            return debugger.getObjectProperty(node.object, node.id);
        } catch (Exception exc) {
CodeCoverTryBranchHelper_Try23 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[407]++;
            return "undefined";
        } finally {
    if ( CodeCoverTryBranchHelper_Try23 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[406]++;
}
  }
    }

    /**
     * A variable node in the tree.
     */
    private static class VariableNode {

        /**
         * The script object.
         */
        private Object object;

        /**
         * The object name.  Either a String or an Integer.
         */
        private Object id;

        /**
         * Array of child nodes.  This is filled with the properties of
         * the object.
         */
        private VariableNode[] children;

        /**
         * Creates a new VariableNode.
         */
        public VariableNode(Object object, Object id) {
            this.object = object;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[999]++;
            this.id = id;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1000]++;
        }

        /**
         * Returns a string representation of this node.
         */
        @Override
        public String toString() {
            return id instanceof String
                ? (String) id : "[" + ((Integer) id).intValue() + "]";
        }
    }
}

/**
 * A tree table for browsing script objects.
 */
class MyTreeTable extends JTreeTable {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 3457265548184453049L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1001]++;
  }

    /**
     * Creates a new MyTreeTable.
     */
    public MyTreeTable(VariableModel model) {
        super(model);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1002]++;
    }

    /**
     * Initializes a tree for this tree table.
     */
    public JTree resetTree(TreeTableModel treeTableModel) {
        tree = new TreeTableCellRenderer(treeTableModel);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1003]++;

        // Install a tableModel representing the visible rows in the tree.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1004]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1005]++;

        // Force the JTable and JTree to share their row selection models.
        ListToTreeSelectionModelWrapper selectionWrapper = new
            ListToTreeSelectionModelWrapper();
        tree.setSelectionModel(selectionWrapper);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1006]++;
        setSelectionModel(selectionWrapper.getListSelectionModel());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1007]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1008]++;
int CodeCoverConditionCoverageHelper_C190;

        // Make the tree and table row heights the same.
        if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((tree.getRowHeight() < 1) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[408]++;
            // Metal looks better like this.
            setRowHeight(18);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1009]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[409]++;}

        // Install the tree editor renderer and editor.
        setDefaultRenderer(TreeTableModel.class, tree);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1010]++;
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1011]++;
        setShowGrid(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1012]++;
        setIntercellSpacing(new Dimension(1,1));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1013]++;
        tree.setRootVisible(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1014]++;
        tree.setShowsRootHandles(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1015]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1016]++;
        DefaultTreeCellRenderer r = (DefaultTreeCellRenderer)tree.getCellRenderer();
        r.setOpenIcon(null);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1017]++;
        r.setClosedIcon(null);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1018]++;
        r.setLeafIcon(null);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1019]++;
        return tree;
    }

    /**
     * Returns whether the cell under the coordinates of the mouse
     * in the {@link EventObject} is editable.
     */
    public boolean isCellEditable(EventObject e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1020]++;
int CodeCoverConditionCoverageHelper_C191;
        if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((e instanceof MouseEvent) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[410]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1021]++;
            MouseEvent me = (MouseEvent)e;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1022]++;
int CodeCoverConditionCoverageHelper_C192;
            // If the modifiers are not 0 (or the left mouse button),
            // tree may try and toggle the selection, and table
            // will then try and toggle, resulting in the
            // selection remaining the same. To avoid this, we
            // only dispatch when the modifiers are 0 (or the left mouse
            // button).
            if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (32)) == 0 || true) &&
 ((me.getModifiers() == 0) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C192 |= (8)) == 0 || true) &&
 (((me.getModifiers() & (InputEvent.BUTTON1_MASK|1024)) != 0) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 (((me.getModifiers() &
                  (InputEvent.SHIFT_MASK |
                   InputEvent.CTRL_MASK |
                   InputEvent.ALT_MASK |
                   InputEvent.BUTTON2_MASK |
                   InputEvent.BUTTON3_MASK |
                   64   | //SHIFT_DOWN_MASK
                   128  | //CTRL_DOWN_MASK
                   512  | // ALT_DOWN_MASK
                   2048 | //BUTTON2_DOWN_MASK
                   4096   //BUTTON3_DOWN_MASK
                   )) == 0) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 3) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 3) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[412]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1023]++;
                int row = rowAtPoint(me.getPoint());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1024]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[58]++;


int CodeCoverConditionCoverageHelper_C193;
                for (int counter = getColumnCount() - 1;(((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((counter >= 0) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false);
                     counter--) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[58]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[59]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[60]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1025]++;
int CodeCoverConditionCoverageHelper_C194;
                    if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((TreeTableModel.class == getColumnClass(counter)) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[414]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1026]++;
                        MouseEvent newME = new MouseEvent
                            (MyTreeTable.this.tree, me.getID(),
                             me.getWhen(), me.getModifiers(),
                             me.getX() - getCellRect(row, counter, true).x,
                             me.getY(), me.getClickCount(),
                             me.isPopupTrigger());
                        MyTreeTable.this.tree.dispatchEvent(newME);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1027]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1028]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[415]++;}
                }

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[413]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1029]++;
int CodeCoverConditionCoverageHelper_C195;
            if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((me.getClickCount() >= 3) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[416]++;
                return true;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[417]++;}
            return false;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[411]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1030]++;
int CodeCoverConditionCoverageHelper_C196;
        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((e == null) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[418]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[419]++;}
        return false;
    }
}

/**
 * Panel that shows information about the context.
 */
class ContextWindow extends JPanel implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 2306040975490228051L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1031]++;
  }

    /**
     * The debugger GUI.
     */
    private SwingGui debugGui;

    /**
     * The combo box that holds the stack frames.
     */
    JComboBox context;

    /**
     * Tool tips for the stack frames.
     */
    List<String> toolTips;

    /**
     * Tabbed pane for "this" and "locals".
     */
    private JTabbedPane tabs;

    /**
     * Tabbed pane for "watch" and "evaluate".
     */
    private JTabbedPane tabs2;

    /**
     * The table showing the "this" object.
     */
    private MyTreeTable thisTable;

    /**
     * The table showing the stack local variables.
     */
    private MyTreeTable localsTable;

    /**
     * The {@link #evaluator}'s table model.
     */
    private MyTableModel tableModel;

    /**
     * The script evaluator table.
     */
    private Evaluator evaluator;

    /**
     * The script evaluation text area.
     */
    private EvalTextArea cmdLine;

    /**
     * The split pane.
     */
    JSplitPane split;

    /**
     * Whether the ContextWindow is enabled.
     */
    private boolean enabled;

    /**
     * Creates a new ContextWindow.
     */
    public ContextWindow(final SwingGui debugGui) {
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1032]++;
        enabled = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1033]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1034]++;
        JPanel left = new JPanel();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1035]++;
        JToolBar t1 = new JToolBar();
        t1.setName("Variables");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1036]++;
        t1.setLayout(new GridLayout());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1037]++;
        t1.add(left);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1038]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1039]++;
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1040]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1041]++;
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1042]++;
        p1.add(t1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1043]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1044]++;
        JLabel label = new JLabel("Context:");
        context = new JComboBox();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1045]++;
        context.setLightWeightPopupEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1046]++;
        toolTips = Collections.synchronizedList(new java.util.ArrayList<String>());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1047]++;
        label.setBorder(context.getBorder());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1048]++;
        context.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1049]++;
        context.setActionCommand("ContextSwitch");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1050]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1051]++;
        GridBagLayout layout = new GridBagLayout();
        left.setLayout(layout);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1052]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1053]++;
        GridBagConstraints lc = new GridBagConstraints();
        lc.insets.left = 5;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1054]++;
        lc.anchor = GridBagConstraints.WEST;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1055]++;
        lc.ipadx = 5;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1056]++;
        layout.setConstraints(label, lc);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1057]++;
        left.add(label);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1058]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1059]++;
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1060]++;
        c.fill = GridBagConstraints.HORIZONTAL;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1061]++;
        c.anchor = GridBagConstraints.WEST;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1062]++;
        layout.setConstraints(context, c);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1063]++;
        left.add(context);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1064]++;
        tabs = new JTabbedPane(SwingConstants.BOTTOM);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1065]++;
        tabs.setPreferredSize(new Dimension(500,300));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1066]++;
        thisTable = new MyTreeTable(new VariableModel());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1067]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1068]++;
        JScrollPane jsp = new JScrollPane(thisTable);
        jsp.getViewport().setViewSize(new Dimension(5,2));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1069]++;
        tabs.add("this", jsp);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1070]++;
        localsTable = new MyTreeTable(new VariableModel());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1071]++;
        localsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1072]++;
        localsTable.setPreferredSize(null);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1073]++;
        jsp = new JScrollPane(localsTable);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1074]++;
        tabs.add("Locals", jsp);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1075]++;
        c.weightx  = c.weighty = 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1076]++;
        c.gridheight = GridBagConstraints.REMAINDER;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1077]++;
        c.fill = GridBagConstraints.BOTH;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1078]++;
        c.anchor = GridBagConstraints.WEST;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1079]++;
        layout.setConstraints(tabs, c);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1080]++;
        left.add(tabs);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1081]++;
        evaluator = new Evaluator(debugGui);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1082]++;
        cmdLine = new EvalTextArea(debugGui);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1083]++;
        //cmdLine.requestFocus();
        tableModel = evaluator.tableModel;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1084]++;
        jsp = new JScrollPane(evaluator);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1085]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1086]++;
        JToolBar t2 = new JToolBar();
        t2.setName("Evaluate");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1087]++;
        tabs2 = new JTabbedPane(SwingConstants.BOTTOM);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1088]++;
        tabs2.add("Watch", jsp);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1089]++;
        tabs2.add("Evaluate", new JScrollPane(cmdLine));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1090]++;
        tabs2.setPreferredSize(new Dimension(500,300));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1091]++;
        t2.setLayout(new GridLayout());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1092]++;
        t2.add(tabs2);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1093]++;
        p2.add(t2);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1094]++;
        evaluator.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1095]++;
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                               p1, p2);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1096]++;
        split.setOneTouchExpandable(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1097]++;
        SwingGui.setResizeWeight(split, 0.5);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1098]++;
        setLayout(new BorderLayout());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1099]++;
        add(split, BorderLayout.CENTER);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1100]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1101]++;

        final JToolBar finalT1 = t1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1102]++;
        final JToolBar finalT2 = t2;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1103]++;
        final JPanel finalP1 = p1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1104]++;
        final JPanel finalP2 = p2;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1105]++;
        final JSplitPane finalSplit = split;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1106]++;
        final JPanel finalThis = this;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1107]++;

        ComponentListener clistener = new ComponentListener() {
                boolean t2Docked = true;
                void check(Component comp) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1108]++;
                    Component thisParent = finalThis.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1109]++;
int CodeCoverConditionCoverageHelper_C197;
                    if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((thisParent == null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[420]++;
                        return;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[421]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1110]++;
                    Component parent = finalT1.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1111]++;
                    boolean leftDocked = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1112]++;
                    boolean rightDocked = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1113]++;
                    boolean adjustVerticalSplit = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1114]++;
int CodeCoverConditionCoverageHelper_C198;
                    if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[422]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1115]++;
int CodeCoverConditionCoverageHelper_C199;
                        if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((parent != finalP1) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[424]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1116]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[61]++;


int CodeCoverConditionCoverageHelper_C200;
                            while ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((parent instanceof JFrame) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[61]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[62]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[63]++;
}
                                parent = parent.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1117]++;
                            }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1118]++;
                            JFrame frame = (JFrame)parent;
                            debugGui.addTopLevel("Variables", frame);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1119]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1120]++;
int CodeCoverConditionCoverageHelper_C201;

                            // We need the following hacks because:
                            // - We want an undocked toolbar to be
                            //   resizable.
                            // - We are using JToolbar as a container of a
                            //   JComboBox. Without this JComboBox's popup
                            //   can get left floating when the toolbar is
                            //   re-docked.
                            //
                            // We make the frame resizable and then
                            // remove JToolbar's window listener
                            // and insert one of our own that first ensures
                            // the JComboBox's popup window is closed
                            // and then calls JToolbar's window listener.
                            if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((frame.isResizable()) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[426]++;
                                frame.setResizable(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1121]++;
                                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1122]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1123]++;
                                final EventListener[] l =
                                    frame.getListeners(WindowListener.class);
                                frame.removeWindowListener((WindowListener)l[0]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1124]++;
                                frame.addWindowListener(new WindowAdapter() {
                                        @Override
                                        public void windowClosing(WindowEvent e) {
                                            context.hidePopup();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1126]++;
                                            ((WindowListener)l[0]).windowClosing(e);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1127]++;
                                        }
                                    });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1125]++;

                                //adjustVerticalSplit = true;
                            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[427]++;}
                            leftDocked = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1128]++;

                        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[425]++;
                            leftDocked = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1129]++;
                        }

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[423]++;}
                    parent = finalT2.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1130]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1131]++;
int CodeCoverConditionCoverageHelper_C202;
                    if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[428]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1132]++;
int CodeCoverConditionCoverageHelper_C203;
                        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((parent != finalP2) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[430]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1133]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[64]++;


int CodeCoverConditionCoverageHelper_C204;
                            while ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((parent instanceof JFrame) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[64]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[65]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[66]++;
}
                                parent = parent.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1134]++;
                            }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1135]++;
                            JFrame frame = (JFrame)parent;
                            debugGui.addTopLevel("Evaluate", frame);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1136]++;
                            frame.setResizable(true);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1137]++;
                            rightDocked = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1138]++;

                        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[431]++;
                            rightDocked = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1139]++;
                        }

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[429]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1140]++;
int CodeCoverConditionCoverageHelper_C205;
                    if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (128)) == 0 || true) &&
 ((leftDocked) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C205 |= (32)) == 0 || true) &&
 ((t2Docked) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C205 |= (8)) == 0 || true) &&
 ((rightDocked) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((t2Docked) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 4) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 4) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[432]++;
                        // no change
                        return;

                    } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[433]++;}
                    t2Docked = rightDocked;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1141]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1142]++;
                    JSplitPane split = (JSplitPane)thisParent;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1143]++;
int CodeCoverConditionCoverageHelper_C206;
                    if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((leftDocked) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[434]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1144]++;
int CodeCoverConditionCoverageHelper_C207;
                        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((rightDocked) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[436]++;
                            finalSplit.setDividerLocation(0.5);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1145]++;

                        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[437]++;
                            finalSplit.setDividerLocation(1.0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1146]++;
                        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1147]++;
int CodeCoverConditionCoverageHelper_C208;
                        if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((adjustVerticalSplit) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[438]++;
                            split.setDividerLocation(0.66);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1148]++;

                        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[439]++;}


                    } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[435]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1149]++;
int CodeCoverConditionCoverageHelper_C209; if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((rightDocked) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[440]++;
                            finalSplit.setDividerLocation(0.0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1150]++;
                            split.setDividerLocation(0.66);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1151]++;

                    } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[441]++;
                        // both undocked
                        split.setDividerLocation(1.0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1152]++;
                    }
}
                }
                public void componentHidden(ComponentEvent e) {
                    check(e.getComponent());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1153]++;
                }
                public void componentMoved(ComponentEvent e) {
                    check(e.getComponent());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1154]++;
                }
                public void componentResized(ComponentEvent e) {
                    check(e.getComponent());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1155]++;
                }
                public void componentShown(ComponentEvent e) {
                    check(e.getComponent());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1156]++;
                }
            };
        p1.addContainerListener(new ContainerListener() {
            public void componentAdded(ContainerEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1158]++;
                Component thisParent = finalThis.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1159]++;
                JSplitPane split = (JSplitPane)thisParent;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1160]++;
int CodeCoverConditionCoverageHelper_C210;
                if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((e.getChild() == finalT1) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[442]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1161]++;
int CodeCoverConditionCoverageHelper_C211;
                    if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((finalT2.getParent() == finalP2) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[444]++;
                        // both docked
                        finalSplit.setDividerLocation(0.5);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1162]++;

                    } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[445]++;
                        // left docked only
                        finalSplit.setDividerLocation(1.0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1163]++;
                    }
                    split.setDividerLocation(0.66);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1164]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[443]++;}
            }
            public void componentRemoved(ContainerEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1165]++;
                Component thisParent = finalThis.getParent();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1166]++;
                JSplitPane split = (JSplitPane)thisParent;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1167]++;
int CodeCoverConditionCoverageHelper_C212;
                if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((e.getChild() == finalT1) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[446]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1168]++;
int CodeCoverConditionCoverageHelper_C213;
                    if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((finalT2.getParent() == finalP2) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[448]++;
                        // right docked only
                        finalSplit.setDividerLocation(0.0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1169]++;
                        split.setDividerLocation(0.66);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1170]++;

                    } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[449]++;
                        // both undocked
                        split.setDividerLocation(1.0);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1171]++;
                    }

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[447]++;}
            }
            });
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1157]++;
        t1.addComponentListener(clistener);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1172]++;
        t2.addComponentListener(clistener);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1173]++;
        setEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1174]++;
    }

    /**
     * Enables or disables the component.
     */
    @Override
    public void setEnabled(boolean enabled) {
        context.setEnabled(enabled);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1175]++;
        thisTable.setEnabled(enabled);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1176]++;
        localsTable.setEnabled(enabled);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1177]++;
        evaluator.setEnabled(enabled);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1178]++;
        cmdLine.setEnabled(enabled);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1179]++;
    }

    /**
     * Disables updating of the component.
     */
    public void disableUpdate() {
        enabled = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1180]++;
    }

    /**
     * Enables updating of the component.
     */
    public void enableUpdate() {
        enabled = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1181]++;
    }

    // ActionListener

    /**
     * Performs an action.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1182]++;
int CodeCoverConditionCoverageHelper_C214;
        if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((enabled) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[450]++; return;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[451]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1183]++;
int CodeCoverConditionCoverageHelper_C215;
        if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((e.getActionCommand().equals("ContextSwitch")) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[452]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1184]++;
            Dim.ContextData contextData = debugGui.dim.currentContextData();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1185]++;
int CodeCoverConditionCoverageHelper_C216;
            if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((contextData == null) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[454]++; return;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[455]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1186]++;
            int frameIndex = context.getSelectedIndex();
            context.setToolTipText(toolTips.get(frameIndex));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1187]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1188]++;
            int frameCount = contextData.frameCount();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1189]++;
int CodeCoverConditionCoverageHelper_C217;
            if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((frameIndex >= frameCount) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[456]++;
                return;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[457]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1190]++;
            Dim.StackFrame frame = contextData.getFrame(frameIndex);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1191]++;
            Object scope = frame.scope();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1192]++;
            Object thisObj = frame.thisObj();
            thisTable.resetTree(new VariableModel(debugGui.dim, thisObj));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1193]++;
            VariableModel scopeModel;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1194]++;
int CodeCoverConditionCoverageHelper_C218;
            if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((scope != thisObj) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[458]++;
                scopeModel = new VariableModel(debugGui.dim, scope);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1195]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[459]++;
                scopeModel = new VariableModel();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1196]++;
            }
            localsTable.resetTree(scopeModel);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1197]++;
            debugGui.dim.contextSwitch(frameIndex);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1198]++;
            debugGui.showStopLine(frame);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1199]++;
            tableModel.updateModel();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1200]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[453]++;}
    }
}

/**
 * The debugger frame menu bar.
 */
class Menubar extends JMenuBar implements ActionListener {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    /**
     * Serializable magic number.
     */
    private static final long serialVersionUID = 3217170497245911461L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1201]++;
  }

    /**
     * Items that are enabled only when interrupted.
     */
    private List<JMenuItem> interruptOnlyItems =
        Collections.synchronizedList(new ArrayList<JMenuItem>());
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1202]++;
  }

    /**
     * Items that are enabled only when running.
     */
    private List<JMenuItem> runOnlyItems =
        Collections.synchronizedList(new ArrayList<JMenuItem>());
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1203]++;
  }

    /**
     * The debugger GUI.
     */
    private SwingGui debugGui;

    /**
     * The menu listing the internal frames.
     */
    private JMenu windowMenu;

    /**
     * The "Break on exceptions" menu item.
     */
    private JCheckBoxMenuItem breakOnExceptions;

    /**
     * The "Break on enter" menu item.
     */
    private JCheckBoxMenuItem breakOnEnter;

    /**
     * The "Break on return" menu item.
     */
    private JCheckBoxMenuItem breakOnReturn;

    /**
     * Creates a new Menubar.
     */
    Menubar(SwingGui debugGui) {
        super();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1204]++;
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1205]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1206]++;
        String[] fileItems  = {"Open...", "Run...", "", "Exit"};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1207]++;
        String[] fileCmds  = {"Open", "Load", "", "Exit"};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1208]++;
        char[] fileShortCuts = {'0', 'N', 0, 'X'};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1209]++;
        int[] fileAccelerators = {KeyEvent.VK_O,
                                  KeyEvent.VK_N,
                                  0,
                                  KeyEvent.VK_Q};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1210]++;
        String[] editItems = {"Cut", "Copy", "Paste", "Go to function..."};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1211]++;
        char[] editShortCuts = {'T', 'C', 'P', 'F'};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1212]++;
        String[] debugItems = {"Break", "Go", "Step Into", "Step Over", "Step Out"};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1213]++;
        char[] debugShortCuts = {'B', 'G', 'I', 'O', 'T'};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1214]++;
        String[] plafItems = {"Metal", "Windows", "Motif"};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1215]++;
        char [] plafShortCuts = {'M', 'W', 'F'};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1216]++;
        int[] debugAccelerators = {KeyEvent.VK_PAUSE,
                                   KeyEvent.VK_F5,
                                   KeyEvent.VK_F11,
                                   KeyEvent.VK_F7,
                                   KeyEvent.VK_F8,
                                   0, 0};
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1217]++;

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1218]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1219]++;
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1220]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1221]++;
        JMenu plafMenu = new JMenu("Platform");
        plafMenu.setMnemonic('P');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1222]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1223]++;
        JMenu debugMenu = new JMenu("Debug");
        debugMenu.setMnemonic('D');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1224]++;
        windowMenu = new JMenu("Window");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1225]++;
        windowMenu.setMnemonic('W');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1226]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1227]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[67]++;


int CodeCoverConditionCoverageHelper_C219;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((i < fileItems.length) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[67]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[68]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[69]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1228]++;
int CodeCoverConditionCoverageHelper_C220;
            if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((fileItems[i].length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[460]++;
                fileMenu.addSeparator();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1229]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[461]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1230]++;
                JMenuItem item = new JMenuItem(fileItems[i],
                                               fileShortCuts[i]);
                item.setActionCommand(fileCmds[i]);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1231]++;
                item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1232]++;
                fileMenu.add(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1233]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1234]++;
int CodeCoverConditionCoverageHelper_C221;
                if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((fileAccelerators[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[462]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1235]++;
                    KeyStroke k = KeyStroke.getKeyStroke(fileAccelerators[i], Event.CTRL_MASK);
                    item.setAccelerator(k);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1236]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[463]++;}
            }
        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1237]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[70]++;


int CodeCoverConditionCoverageHelper_C222;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((i < editItems.length) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[70]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[71]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[72]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1238]++;
            JMenuItem item = new JMenuItem(editItems[i],
                                           editShortCuts[i]);
            item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1239]++;
            editMenu.add(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1240]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1241]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[73]++;


int CodeCoverConditionCoverageHelper_C223;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((i < plafItems.length) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[73]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[74]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[75]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1242]++;
            JMenuItem item = new JMenuItem(plafItems[i],
                                           plafShortCuts[i]);
            item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1243]++;
            plafMenu.add(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1244]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1245]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[76]++;


int CodeCoverConditionCoverageHelper_C224;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((i < debugItems.length) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[76]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[77]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[78]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1246]++;
            JMenuItem item = new JMenuItem(debugItems[i],
                                           debugShortCuts[i]);
            item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1247]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1248]++;
int CodeCoverConditionCoverageHelper_C225;
            if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((debugAccelerators[i] != 0) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[464]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1249]++;
                KeyStroke k = KeyStroke.getKeyStroke(debugAccelerators[i], 0);
                item.setAccelerator(k);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1250]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[465]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1251]++;
int CodeCoverConditionCoverageHelper_C226;
            if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[466]++;
                interruptOnlyItems.add(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1252]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[467]++;
                runOnlyItems.add(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1253]++;
            }
            debugMenu.add(item);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1254]++;
        }
        breakOnExceptions = new JCheckBoxMenuItem("Break on Exceptions");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1255]++;
        breakOnExceptions.setMnemonic('X');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1256]++;
        breakOnExceptions.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1257]++;
        breakOnExceptions.setSelected(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1258]++;
        debugMenu.add(breakOnExceptions);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1259]++;

        breakOnEnter = new JCheckBoxMenuItem("Break on Function Enter");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1260]++;
        breakOnEnter.setMnemonic('E');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1261]++;
        breakOnEnter.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1262]++;
        breakOnEnter.setSelected(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1263]++;
        debugMenu.add(breakOnEnter);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1264]++;

        breakOnReturn = new JCheckBoxMenuItem("Break on Function Return");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1265]++;
        breakOnReturn.setMnemonic('R');
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1266]++;
        breakOnReturn.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1267]++;
        breakOnReturn.setSelected(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1268]++;
        debugMenu.add(breakOnReturn);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1269]++;

        add(fileMenu);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1270]++;
        add(editMenu);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1271]++;
        //add(plafMenu);
        add(debugMenu);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1272]++;
        JMenuItem item;
        windowMenu.add(item = new JMenuItem("Cascade", 'A'));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1273]++;
        item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1274]++;
        windowMenu.add(item = new JMenuItem("Tile", 'T'));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1275]++;
        item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1276]++;
        windowMenu.addSeparator();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1277]++;
        windowMenu.add(item = new JMenuItem("Console", 'C'));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1278]++;
        item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1279]++;
        add(windowMenu);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1280]++;

        updateEnabled(false);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1281]++;
    }

    /**
     * Returns the "Break on exceptions" menu item.
     */
    public JCheckBoxMenuItem getBreakOnExceptions() {
        return breakOnExceptions;
    }

    /**
     * Returns the "Break on enter" menu item.
     */
    public JCheckBoxMenuItem getBreakOnEnter() {
        return breakOnEnter;
    }

    /**
     * Returns the "Break on return" menu item.
     */
    public JCheckBoxMenuItem getBreakOnReturn() {
        return breakOnReturn;
    }

    /**
     * Returns the "Debug" menu.
     */
    public JMenu getDebugMenu() {
        return getMenu(2);
    }

    // ActionListener

    /**
     * Performs an action.
     */
    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1282]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1283]++;
        String plaf_name = null;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1284]++;
int CodeCoverConditionCoverageHelper_C227;
        if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((cmd.equals("Metal")) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[468]++;
            plaf_name = "javax.swing.plaf.metal.MetalLookAndFeel";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1285]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[469]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1286]++;
int CodeCoverConditionCoverageHelper_C228; if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((cmd.equals("Windows")) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[470]++;
            plaf_name = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1287]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[471]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1288]++;
int CodeCoverConditionCoverageHelper_C229; if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((cmd.equals("Motif")) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[472]++;
            plaf_name = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1289]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[473]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1290]++;
            Object source = e.getSource();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1291]++;
int CodeCoverConditionCoverageHelper_C230;
            if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((source == breakOnExceptions) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[474]++;
                debugGui.dim.setBreakOnExceptions(breakOnExceptions.isSelected());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1292]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[475]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1293]++;
int CodeCoverConditionCoverageHelper_C231; if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((source == breakOnEnter) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[476]++;
                debugGui.dim.setBreakOnEnter(breakOnEnter.isSelected());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1294]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[477]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1295]++;
int CodeCoverConditionCoverageHelper_C232; if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((source == breakOnReturn) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[478]++;
                debugGui.dim.setBreakOnReturn(breakOnReturn.isSelected());
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1296]++;

            } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[479]++;
                debugGui.actionPerformed(e);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1297]++;
            }
}
}
            return;
        }
}
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1298]++;
boolean CodeCoverTryBranchHelper_Try24 = false;
        try {
CodeCoverTryBranchHelper_Try24 = true;
            UIManager.setLookAndFeel(plaf_name);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1299]++;
            SwingUtilities.updateComponentTreeUI(debugGui);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1300]++;
            SwingUtilities.updateComponentTreeUI(debugGui.dlg);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1301]++;
        } catch (Exception ignored) {
CodeCoverTryBranchHelper_Try24 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[481]++;
            //ignored.printStackTrace();
        } finally {
    if ( CodeCoverTryBranchHelper_Try24 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[480]++;
}
  }
    }

    /**
     * Adds a file to the window menu.
     */
    public void addFile(String url) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1302]++;
        int count = windowMenu.getItemCount();
        JMenuItem item;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1303]++;
int CodeCoverConditionCoverageHelper_C233;
        if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((count == 4) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[482]++;
            windowMenu.addSeparator();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1304]++;
            count++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1305]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[483]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1306]++;
        JMenuItem lastItem = windowMenu.getItem(count -1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1307]++;
        boolean hasMoreWin = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1308]++;
        int maxWin = 5;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1309]++;
int CodeCoverConditionCoverageHelper_C234;
        if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (8)) == 0 || true) &&
 ((lastItem != null) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((lastItem.getText().equals("More Windows...")) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[484]++;
            hasMoreWin = true;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1310]++;
            maxWin++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1311]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[485]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1312]++;
int CodeCoverConditionCoverageHelper_C235;
        if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C235 |= (8)) == 0 || true) &&
 ((hasMoreWin) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((count - 4 == 5) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[486]++;
            windowMenu.add(item = new JMenuItem("More Windows...", 'M'));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1313]++;
            item.setActionCommand("More Windows...");
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1314]++;
            item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1315]++;
            return;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[487]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1316]++;
int CodeCoverConditionCoverageHelper_C236; if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((count - 4 <= maxWin) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[488]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1317]++;
int CodeCoverConditionCoverageHelper_C237;
            if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((hasMoreWin) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[490]++;
                count--;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1318]++;
                windowMenu.remove(lastItem);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1319]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[491]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1320]++;
            String shortName = SwingGui.getShortName(url);

            windowMenu.add(item = new JMenuItem((char)('0' + (count-4)) + " " + shortName, '0' + (count - 4)));
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1321]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1322]++;
int CodeCoverConditionCoverageHelper_C238;
            if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((hasMoreWin) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[492]++;
                windowMenu.add(lastItem);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1323]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[493]++;}

        } else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[489]++;
            return;
        }
}
        item.setActionCommand(url);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1324]++;
        item.addActionListener(this);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1325]++;
    }

    /**
     * Updates the enabledness of menu items.
     */
    public void updateEnabled(boolean interrupted) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1326]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[79]++;


int CodeCoverConditionCoverageHelper_C239;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((i != interruptOnlyItems.size()) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[79]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[80]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[81]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1327]++;
            JMenuItem item = interruptOnlyItems.get(i);
            item.setEnabled(interrupted);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1328]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1329]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[82]++;


int CodeCoverConditionCoverageHelper_C240;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((i != runOnlyItems.size()) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[82]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[83]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.loops[84]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1330]++;
            JMenuItem item = runOnlyItems.get(i);
            item.setEnabled(!interrupted);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1331]++;
        }
    }
}

/**
 * Class to consolidate all cases that require to implement Runnable
 * to avoid class generation bloat.
 */
class RunProxy implements Runnable {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.ping();
  }


    // Constants for 'type'.
    static final int OPEN_FILE = 1;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1332]++;
  }
    static final int LOAD_FILE = 2;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1333]++;
  }
    static final int UPDATE_SOURCE_TEXT = 3;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1334]++;
  }
    static final int ENTER_INTERRUPT = 4;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1335]++;
  }

    /**
     * The debugger GUI.
     */
    private SwingGui debugGui;

    /**
     * The type of Runnable this object is.  Takes one of the constants
     * defined in this class.
     */
    private int type;

    /**
     * The name of the file to open or load.
     */
    String fileName;

    /**
     * The source text to update.
     */
    String text;

    /**
     * The source for which to update the text.
     */
    Dim.SourceInfo sourceInfo;

    /**
     * The frame to interrupt in.
     */
    Dim.StackFrame lastFrame;

    /**
     * The name of the interrupted thread.
     */
    String threadTitle;

    /**
     * The message of the exception thrown that caused the thread
     * interruption, if any.
     */
    String alertMessage;

    /**
     * Creates a new RunProxy.
     */
    public RunProxy(SwingGui debugGui, int type) {
        this.debugGui = debugGui;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1336]++;
        this.type = type;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1337]++;
    }

    /**
     * Runs this Runnable.
     */
    public void run() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1338]++;
        switch (type) {
          case OPEN_FILE:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[494]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1339]++;
boolean CodeCoverTryBranchHelper_Try25 = false;
            try {
CodeCoverTryBranchHelper_Try25 = true;
                debugGui.dim.compileScript(fileName, text);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1340]++;
            } catch (RuntimeException ex) {
CodeCoverTryBranchHelper_Try25 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[496]++;
                MessageDialogWrapper.showMessageDialog(
                    debugGui, ex.getMessage(), "Error Compiling "+fileName,
                    JOptionPane.ERROR_MESSAGE);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1341]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try25 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[495]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1342]++;
            break;

          case LOAD_FILE:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[497]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1343]++;
boolean CodeCoverTryBranchHelper_Try26 = false;
            try {
CodeCoverTryBranchHelper_Try26 = true;
                debugGui.dim.evalScript(fileName, text);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1344]++;
            } catch (RuntimeException ex) {
CodeCoverTryBranchHelper_Try26 = false;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[499]++;
                MessageDialogWrapper.showMessageDialog(
                    debugGui, ex.getMessage(), "Run error for "+fileName,
                    JOptionPane.ERROR_MESSAGE);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1345]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try26 ) {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[498]++;
}
  }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1346]++;
            break;

          case UPDATE_SOURCE_TEXT:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[500]++;
            {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1347]++;
                String fileName = sourceInfo.url();
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1348]++;
int CodeCoverConditionCoverageHelper_C241;
                if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C241 |= (8)) == 0 || true) &&
 ((debugGui.updateFileWindow(sourceInfo)) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((fileName.equals("<stdin>")) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[501]++;
                    debugGui.createFileWindow(sourceInfo, -1);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1349]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[502]++;}
            }
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1350]++;
            break;

          case ENTER_INTERRUPT:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[503]++;
            debugGui.enterInterruptImpl(lastFrame, threadTitle, alertMessage);
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1351]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.statements[1352]++;
            break;

          default:
CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201.branches[504]++;
            throw new IllegalArgumentException(String.valueOf(type));

        }
    }
}

class CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201 ());
  }
    public static long[] statements = new long[1353];
    public static long[] branches = new long[505];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[242];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.debugger.RHINO-TOO-SwingGui.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,2,1,2,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,2};
    for (int i = 1; i <= 241; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[85];

  public CodeCoverCoverageCounter$2o08iygcpwmr67f4ixpu8z3wc15dbtyft201 () {
    super("org.mozilla.javascript.tools.debugger.RHINO-TOO-SwingGui.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1352; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 504; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 241; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 84; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.debugger.RHINO-TOO-SwingGui.java");
      for (int i = 1; i <= 1352; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 504; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 241; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 28; i++) {
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

