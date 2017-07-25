/*
 * @(#)JTreeTable.java  1.2 98/10/27
 *
 * Copyright 1997, 1998 Sun Microsystems, Inc.  All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.mozilla.javascript.tools.debugger.treetable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import java.util.EventObject;

/**
 * This example shows how to create a simple JTreeTable component,
 * by using a JTree as a renderer (and editor) for the cells in a
 * particular column in the JTable.
 *
 * @version 1.2 10/27/98
 *
 */
public class JTreeTable extends JTable {
  static {
    CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.ping();
  }

    /**
     *
     */
    private static final long serialVersionUID = -2103973006456695515L;
  static {
    CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[1]++;
  }
    /** A subclass of JTree. */
    protected TreeTableCellRenderer tree;

    public JTreeTable(TreeTableModel treeTableModel) {
        super();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[2]++;

        // Create the tree. It will be used as a renderer and editor.
        tree = new TreeTableCellRenderer(treeTableModel);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[3]++;

        // Install a tableModel representing the visible rows in the tree.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[4]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[5]++;

        // Force the JTable and JTree to share their row selection models.
        ListToTreeSelectionModelWrapper selectionWrapper = new
                                ListToTreeSelectionModelWrapper();
        tree.setSelectionModel(selectionWrapper);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[6]++;
        setSelectionModel(selectionWrapper.getListSelectionModel());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[7]++;

        // Install the tree editor renderer and editor.
        setDefaultRenderer(TreeTableModel.class, tree);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[8]++;
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[9]++;

        // No grid.
        setShowGrid(false);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[10]++;

        // No intercell spacing
        setIntercellSpacing(new Dimension(0, 0));
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[11]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;

        // And update the height of the trees row to match that of
        // the table.
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((tree.getRowHeight() < 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[1]++;
            // Metal looks better like this.
            setRowHeight(18);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[13]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[2]++;}
    }

    /**
     * Overridden to message super and forward the method to the tree.
     * Since the tree is not actually in the component hierarchy it will
     * never receive this unless we forward it in this manner.
     */
    @Override
    public void updateUI() {
        super.updateUI();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[14]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((tree != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[3]++;
            tree.updateUI();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[16]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[4]++;}
        // Use the tree's default foreground and background colors in the
        // table.
        LookAndFeel.installColorsAndFont(this, "Tree.background",
                                         "Tree.foreground", "Tree.font");
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[17]++;
    }

    /* Workaround for BasicTableUI anomaly. Make sure the UI never tries to
     * paint the editor. The UI currently uses different techniques to
     * paint the renderers and editors and overriding setBounds() below
     * is not the right thing to do for an editor. Returning -1 for the
     * editing row in this case, ensures the editor is never painted.
     */
    @Override
    public int getEditingRow() {
        return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 :
                editingRow;
    }

    /**
     * Overridden to pass the new rowHeight to the tree.
     */
    @Override
    public void setRowHeight(int rowHeight) {
        super.setRowHeight(rowHeight);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[18]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((tree != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((tree.getRowHeight() != rowHeight) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[5]++;
            tree.setRowHeight(getRowHeight());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[20]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[6]++;}
    }

    /**
     * Returns the tree that is being shared between the model.
     */
    public JTree getTree() {
        return tree;
    }

    /**
     * A TreeCellRenderer that displays a JTree.
     */
    public class TreeTableCellRenderer extends JTree implements TableCellRenderer {
        private static final long serialVersionUID = -193867880014600717L;
        /** Last table/tree row asked to renderer. */
        protected int visibleRow;

        public TreeTableCellRenderer(TreeModel model) {
            super(model);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[21]++;
        }

        /**
         * updateUI is overridden to set the colors of the Tree's renderer
         * to match that of the table.
         */
        @Override
        public void updateUI() {
            super.updateUI();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[22]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[23]++;
            // Make the tree's cell renderer use the table's cell selection
            // colors.
            TreeCellRenderer tcr = getCellRenderer();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((tcr instanceof DefaultTreeCellRenderer) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[7]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[25]++;
                DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer)tcr);
                // For 1.1 uncomment this, 1.2 has a bug that will cause an
                // exception to be thrown if the border selection color is
                // null.
                // dtcr.setBorderSelectionColor(null);
                dtcr.setTextSelectionColor(UIManager.getColor
                                           ("Table.selectionForeground"));
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[26]++;
                dtcr.setBackgroundSelectionColor(UIManager.getColor
                                                ("Table.selectionBackground"));
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[27]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[8]++;}
        }

        /**
         * Sets the row height of the tree, and forwards the row height to
         * the table.
         */
        @Override
        public void setRowHeight(int rowHeight) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((rowHeight > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[9]++;
                super.setRowHeight(rowHeight);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[29]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((JTreeTable.this != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((JTreeTable.this.getRowHeight() != rowHeight) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[11]++;
                    JTreeTable.this.setRowHeight(getRowHeight());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[31]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[12]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[10]++;}
        }

        /**
         * This is overridden to set the height to match that of the JTable.
         */
        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, 0, w, JTreeTable.this.getHeight());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[32]++;
        }

        /**
         * Sublcassed to translate the graphics such that the last visible
         * row will be drawn at 0,0.
         */
        @Override
        public void paint(Graphics g) {
            g.translate(0, -visibleRow * getRowHeight());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[33]++;
            super.paint(g);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[34]++;
        }

        /**
         * TreeCellRenderer method. Overridden to update the visible row.
         */
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row, int column) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
            if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isSelected) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[13]++;
                setBackground(table.getSelectionBackground());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[36]++;
}
            else {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[14]++;
                setBackground(table.getBackground());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[37]++;
}

            visibleRow = row;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[38]++;
            return this;
        }
    }


    /**
     * TreeTableCellEditor implementation. Component returned is the
     * JTree.
     */
    public class TreeTableCellEditor extends AbstractCellEditor implements
                 TableCellEditor {
        public Component getTableCellEditorComponent(JTable table,
                                                     Object value,
                                                     boolean isSelected,
                                                     int r, int c) {
            return tree;
        }

        /**
         * Overridden to return false, and if the event is a mouse event
         * it is forwarded to the tree.<p>
         * The behavior for this is debatable, and should really be offered
         * as a property. By returning false, all keyboard actions are
         * implemented in terms of the table. By returning true, the
         * tree would get a chance to do something with the keyboard
         * events. For the most part this is ok. But for certain keys,
         * such as left/right, the tree will expand/collapse where as
         * the table focus should really move to a different column. Page
         * up/down should also be implemented in terms of the table.
         * By returning false this also has the added benefit that clicking
         * outside of the bounds of the tree node, but still in the tree
         * column will select the row, whereas if this returned true
         * that wouldn't be the case.
         * <p>By returning false we are also enforcing the policy that
         * the tree will never be editable (at least by a key sequence).
         */
        @Override
        public boolean isCellEditable(EventObject e) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((e instanceof MouseEvent) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[15]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
                for (int counter = getColumnCount() - 1;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((counter >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false);
                     counter--) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getColumnClass(counter) == TreeTableModel.class) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[17]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[42]++;
                        MouseEvent me = (MouseEvent)e;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[43]++;
                        MouseEvent newME = new MouseEvent(tree, me.getID(),
                                   me.getWhen(), me.getModifiers(),
                                   me.getX() - getCellRect(0, counter, true).x,
                                   me.getY(), me.getClickCount(),
                                   me.isPopupTrigger());
                        tree.dispatchEvent(newME);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[44]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[45]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[18]++;}
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[16]++;}
            return false;
        }
    }


    /**
     * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel
     * to listen for changes in the ListSelectionModel it maintains. Once
     * a change in the ListSelectionModel happens, the paths are updated
     * in the DefaultTreeSelectionModel.
     */
    public class ListToTreeSelectionModelWrapper
        extends DefaultTreeSelectionModel
    {
        private static final long serialVersionUID = 8168140829623071131L;

        /** Set to true when we are updating the ListSelectionModel. */
        protected boolean         updatingListSelectionModel;

        public ListToTreeSelectionModelWrapper() {
            super();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[46]++;
            getListSelectionModel().addListSelectionListener
                                    (createListSelectionListener());
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[47]++;
        }

        /**
         * Returns the list selection model. ListToTreeSelectionModelWrapper
         * listens for changes to this model and updates the selected paths
         * accordingly.
         */
        public ListSelectionModel getListSelectionModel() {
            return listSelectionModel;
        }

        /**
         * This is overridden to set <code>updatingListSelectionModel</code>
         * and message super. This is the only place DefaultTreeSelectionModel
         * alters the ListSelectionModel.
         */
        @Override
        public void resetRowSelection() {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
            if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((updatingListSelectionModel) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[19]++;
                updatingListSelectionModel = true;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[49]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[50]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    super.resetRowSelection();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[51]++;
                }
                finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[21]++;
}
                    updatingListSelectionModel = false;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[52]++;
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[20]++;}
            // Notice how we don't message super if
            // updatingListSelectionModel is true. If
            // updatingListSelectionModel is true, it implies the
            // ListSelectionModel has already been updated and the
            // paths are the only thing that needs to be updated.
        }

        /**
         * Creates and returns an instance of ListSelectionHandler.
         */
        protected ListSelectionListener createListSelectionListener() {
            return new ListSelectionHandler();
        }

        /**
         * If <code>updatingListSelectionModel</code> is false, this will
         * reset the selected paths from the selected rows in the list
         * selection model.
         */
        protected void updateSelectedPathsFromSelectedRows() {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
            if((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((updatingListSelectionModel) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[22]++;
                updatingListSelectionModel = true;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[54]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[55]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[56]++;
                    // This is way expensive, ListSelectionModel needs an
                    // enumerator for iterating.
                    int        min = listSelectionModel.getMinSelectionIndex();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[57]++;
                    int        max = listSelectionModel.getMaxSelectionIndex();

                    clearSelection();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[58]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[59]++;
int CodeCoverConditionCoverageHelper_C13;
                    if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((min != -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((max != -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[25]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[60]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
                        for(int counter = min;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((counter <= max) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); counter++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;
                            if((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((listSelectionModel.isSelectedIndex(counter)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[27]++;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[62]++;
                                TreePath     selPath = tree.getPathForRow
                                                            (counter);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[63]++;
int CodeCoverConditionCoverageHelper_C16;

                                if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((selPath != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[29]++;
                                    addSelectionPath(selPath);
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[64]++;

                                } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[30]++;}

                            } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[28]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[26]++;}
                }
                finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[24]++;
}
                    updatingListSelectionModel = false;
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[65]++;
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.branches[23]++;}
        }

        /**
         * Class responsible for calling updateSelectedPathsFromSelectedRows
         * when the selection of the list changse.
         */
        class ListSelectionHandler implements ListSelectionListener {
            public void valueChanged(ListSelectionEvent e) {
                updateSelectedPathsFromSelectedRows();
CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl.statements[66]++;
            }
        }
    }
}

class CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl ());
  }
    public static long[] statements = new long[67];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-JTreeTable.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,2,1,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 16; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$3quun66a2bd8vttvwss53qr1v4ml4hjc2s8ekbl () {
    super("org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-JTreeTable.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 66; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-JTreeTable.java");
      for (int i = 1; i <= 66; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

