/*
 * @(#)TreeTableModelAdapter.java       1.2 98/10/27
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

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/**
 * This is a wrapper class takes a TreeTableModel and implements
 * the table model interface. The implementation is trivial, with
 * all of the event dispatching support provided by the superclass:
 * the AbstractTableModel.
 *
 * @version 1.2 10/27/98
 *
 */
public class TreeTableModelAdapter extends AbstractTableModel {
  static {
    CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.ping();
  }

    private static final long serialVersionUID = 48741114609209052L;
  static {
    CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[1]++;
  }
    JTree tree;
    TreeTableModel treeTableModel;

    public TreeTableModelAdapter(TreeTableModel treeTableModel, JTree tree) {
        this.tree = tree;
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[2]++;
        this.treeTableModel = treeTableModel;
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[3]++;

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            // Don't use fireTableRowsInserted() here; the selection model
            // would get updated twice.
            public void treeExpanded(TreeExpansionEvent event) {
              fireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[5]++;
            }
            public void treeCollapsed(TreeExpansionEvent event) {
              fireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[6]++;
            }
        });
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[4]++;

        // Install a TreeModelListener that can update the table when
        // tree changes. We use delayedFireTableDataChanged as we can
        // not be guaranteed the tree will have finished processing
        // the event before us.
        treeTableModel.addTreeModelListener(new TreeModelListener() {
            public void treeNodesChanged(TreeModelEvent e) {
                delayedFireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[8]++;
            }

            public void treeNodesInserted(TreeModelEvent e) {
                delayedFireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[9]++;
            }

            public void treeNodesRemoved(TreeModelEvent e) {
                delayedFireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[10]++;
            }

            public void treeStructureChanged(TreeModelEvent e) {
                delayedFireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[11]++;
            }
        });
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[7]++;
    }

    // Wrappers, implementing TableModel interface.

    public int getColumnCount() {
        return treeTableModel.getColumnCount();
    }

    @Override
    public String getColumnName(int column) {
        return treeTableModel.getColumnName(column);
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return treeTableModel.getColumnClass(column);
    }

    public int getRowCount() {
        return tree.getRowCount();
    }

    protected Object nodeForRow(int row) {
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[12]++;
        TreePath treePath = tree.getPathForRow(row);
        return treePath.getLastPathComponent();
    }

    public Object getValueAt(int row, int column) {
        return treeTableModel.getValueAt(nodeForRow(row), column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
         return treeTableModel.isCellEditable(nodeForRow(row), column);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        treeTableModel.setValueAt(value, nodeForRow(row), column);
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[13]++;
    }

    /**
     * Invokes fireTableDataChanged after all the pending events have been
     * processed. SwingUtilities.invokeLater is used to handle this.
     */
    protected void delayedFireTableDataChanged() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fireTableDataChanged();
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[15]++;
            }
        });
CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x.statements[14]++;
    }
}

class CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$41o4hx3ypdhj1vpdwi1m7exiakw42o1s3odltcl9d5vfnvadvezesy8x () {
    super("org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-TreeTableModelAdapter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.debugger.treetable.RHINO-TOO-TreeTableModelAdapter.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

