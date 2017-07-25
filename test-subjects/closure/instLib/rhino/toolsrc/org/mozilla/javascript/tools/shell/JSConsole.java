/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.shell;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.mozilla.javascript.SecurityUtilities;

public class JSConsole extends JFrame implements ActionListener
{
  static {
    CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.ping();
  }

    static final long serialVersionUID = 2551225560631876300L;
  static {
    CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[1]++;
  }

    private File CWD;
    private JFileChooser dlg;
    private ConsoleTextArea consoleTextArea;

    public String chooseFile() {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((CWD == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[1]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[3]++;
            String dir = SecurityUtilities.getSystemProperty("user.dir");
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
            if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dir != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[3]++;
                CWD = new File(dir);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[5]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[2]++;}
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((CWD != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[5]++;
            dlg.setCurrentDirectory(CWD);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[7]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[6]++;}
        dlg.setDialogTitle("Select a file to load");
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[8]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[9]++;
        int returnVal = dlg.showOpenDialog(this);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((returnVal == JFileChooser.APPROVE_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[7]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[11]++;
            String result = dlg.getSelectedFile().getPath();
            CWD = new File(dlg.getSelectedFile().getParent());
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[12]++;
            return result;

        } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[8]++;}
        return null;
    }

    public static void main(String args[]) {
        new JSConsole(args);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[13]++;
    }

    public void createFileChooser() {
        dlg = new JFileChooser();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[14]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[15]++;
        javax.swing.filechooser.FileFilter filter =
            new javax.swing.filechooser.FileFilter() {
                   @Override
                    public boolean accept(File f) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
                        if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((f.isDirectory()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[9]++;
                            return true;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[10]++;}
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[17]++;
                        String name = f.getName();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[18]++;
                        int i = name.lastIndexOf('.');
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
                        if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < name.length() -1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[11]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[20]++;
                            String ext = name.substring(i + 1).toLowerCase();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
                            if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ext.equals("js")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[13]++;
                                return true;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[14]++;}

                        } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[12]++;}
                        return false;
                    }

                   @Override
                    public String getDescription() {
                        return "JavaScript Files (*.js)";
                    }
                };
        dlg.addChoosableFileFilter(filter);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[22]++;

    }

    public JSConsole(String[] args) {
        super("Rhino JavaScript Console");
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[23]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[24]++;
        JMenuBar menubar = new JMenuBar();
        createFileChooser();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[25]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[26]++;
        String[] fileItems  = {"Load...", "Exit"};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[27]++;
        String[] fileCmds  = {"Load", "Exit"};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[28]++;
        char[] fileShortCuts = {'L', 'X'};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[29]++;
        String[] editItems = {"Cut", "Copy", "Paste"};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[30]++;
        char[] editShortCuts = {'T', 'C', 'P'};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[31]++;
        String[] plafItems = {"Metal", "Windows", "Motif"};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[32]++;
        boolean [] plafState = {true, false, false};
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[33]++;
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[34]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[35]++;
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[36]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[37]++;
        JMenu plafMenu = new JMenu("Platform");
        plafMenu.setMnemonic('P');
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[38]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[39]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < fileItems.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[1]--;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[2]--;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[3]++;
}
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[40]++;
            JMenuItem item = new JMenuItem(fileItems[i],
                                           fileShortCuts[i]);
            item.setActionCommand(fileCmds[i]);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[41]++;
            item.addActionListener(this);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[42]++;
            fileMenu.add(item);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[43]++;
        }
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[44]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < editItems.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[4]--;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[5]--;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[6]++;
}
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[45]++;
            JMenuItem item = new JMenuItem(editItems[i],
                                           editShortCuts[i]);
            item.addActionListener(this);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[46]++;
            editMenu.add(item);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[47]++;
        }
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[48]++;
        ButtonGroup group = new ButtonGroup();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[49]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < plafItems.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[7]--;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[8]--;
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.loops[9]++;
}
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[50]++;
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(plafItems[i],
                                                                 plafState[i]);
            group.add(item);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[51]++;
            item.addActionListener(this);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[52]++;
            plafMenu.add(item);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[53]++;
        }
        menubar.add(fileMenu);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[54]++;
        menubar.add(editMenu);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[55]++;
        menubar.add(plafMenu);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[56]++;
        setJMenuBar(menubar);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[57]++;
        consoleTextArea = new ConsoleTextArea(args);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[58]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[59]++;
        JScrollPane scroller = new JScrollPane(consoleTextArea);
        setContentPane(scroller);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[60]++;
        consoleTextArea.setRows(24);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[61]++;
        consoleTextArea.setColumns(80);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[62]++;
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[64]++;
                }
            });
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[63]++;
        pack();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[65]++;
        setVisible(true);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[66]++;
        // System.setIn(consoleTextArea.getIn());
        // System.setOut(consoleTextArea.getOut());
        // System.setErr(consoleTextArea.getErr());
        Main.setIn(consoleTextArea.getIn());
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[67]++;
        Main.setOut(consoleTextArea.getOut());
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[68]++;
        Main.setErr(consoleTextArea.getErr());
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[69]++;
        Main.main(args);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[70]++;
    }

    public void actionPerformed(ActionEvent e) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[71]++;
        String cmd = e.getActionCommand();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[72]++;
        String plaf_name = null;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[73]++;
int CodeCoverConditionCoverageHelper_C11;
        if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cmd.equals("Load")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[15]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[74]++;
            String f = chooseFile();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[75]++;
int CodeCoverConditionCoverageHelper_C12;
            if((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((f != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[17]++;
                f = f.replace('\\', '/');
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[76]++;
                consoleTextArea.eval("load(\"" + f + "\");");
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[77]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[18]++;}

        } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[16]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[78]++;
int CodeCoverConditionCoverageHelper_C13; if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((cmd.equals("Exit")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[19]++;
            System.exit(0);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[79]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[20]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[80]++;
int CodeCoverConditionCoverageHelper_C14; if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((cmd.equals("Cut")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[21]++;
            consoleTextArea.cut();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[81]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[22]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[82]++;
int CodeCoverConditionCoverageHelper_C15; if((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((cmd.equals("Copy")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[23]++;
            consoleTextArea.copy();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[83]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[24]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[84]++;
int CodeCoverConditionCoverageHelper_C16; if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((cmd.equals("Paste")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[25]++;
            consoleTextArea.paste();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[85]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[26]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[86]++;
int CodeCoverConditionCoverageHelper_C17;
            if((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((cmd.equals("Metal")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[27]++;
                plaf_name = "javax.swing.plaf.metal.MetalLookAndFeel";
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[87]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[28]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[88]++;
int CodeCoverConditionCoverageHelper_C18; if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((cmd.equals("Windows")) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[29]++;
                plaf_name = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[89]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[30]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[90]++;
int CodeCoverConditionCoverageHelper_C19; if((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((cmd.equals("Motif")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[31]++;
                plaf_name = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[91]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[32]++;}
}
}
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[92]++;
int CodeCoverConditionCoverageHelper_C20;
            if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((plaf_name != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[33]++;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[93]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    UIManager.setLookAndFeel(plaf_name);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[94]++;
                    SwingUtilities.updateComponentTreeUI(this);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[95]++;
                    consoleTextArea.postUpdateUI();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[96]++;
                    // updateComponentTreeUI seems to mess up the file
                    // chooser dialog, so just create a new one
                    createFileChooser();
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[97]++;
                } catch(Exception exc) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[36]++;
                    JOptionPane.showMessageDialog(this,
                                                  exc.getMessage(),
                                                  "Platform",
                                                  JOptionPane.ERROR_MESSAGE);
CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.statements[98]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[35]++;
}
  }

            } else {
  CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h.branches[34]++;}
        }
}
}
}
}

    }

}

class CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h ());
  }
    public static long[] statements = new long[99];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-JSConsole.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
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

  public CodeCoverCoverageCounter$iypomt0ag81t84rjssgdl3dt3isoqnbrqn04h () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-JSConsole.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 98; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-JSConsole.java");
      for (int i = 1; i <= 98; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
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

