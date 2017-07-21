/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ------------------------
 * XYDatasetTableModel.java
 * ------------------------
 * (C)opyright 2003-2007, by Bryan Scott and Contributors.
 *
 * Original Author:  Bryan Scott ;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 01-Jul-2003 : Version 1 contributed by Bryan Scott (DG);
 * 27-Apr-2005 : Change XYDataset --> TableXYDataset because the table model
 *               assumes all series share the same x-values, and this is not
 *               enforced by XYDataset.  Also fixed bug 1191046, a problem
 *               in the getValueAt() method (DG);
 * 
 */

package org.jfree.data.xy;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;

/**
 * A READ-ONLY wrapper around a {@link TableXYDataset} to convert it to a
 * table model for use in a JTable.  The first column of the table shows the
 * x-values, the remaining columns show the y-values for each series (series 0
 * appears in column 1, series 1 appears in column 2, etc).
 * <P>
 * TO DO:
 * <ul>
 * <li>implement proper naming for x axis (getColumnName)</li>
 * <li>implement setValueAt to remove READ-ONLY constraint (not sure how)</li>
 * </ul>
 */
public class XYDatasetTableModel extends AbstractTableModel
                                 implements TableModel, DatasetChangeListener  {
  static {
    CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.ping();
  }


    /** The dataset. */
    TableXYDataset model = null;
  {
    CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[1]++;
  }

    /**
     * Default constructor.
     */
    public XYDatasetTableModel() {
        super();
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[2]++;
    }

    /**
     * Creates a new table model based on the specified dataset.
     *
     * @param dataset  the dataset.
     */
    public XYDatasetTableModel(TableXYDataset dataset) {
        this();
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[3]++;
        this.model = dataset;
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[4]++;
        this.model.addChangeListener(this);
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[5]++;
    }

    /**
     * Sets the model (dataset).
     *
     * @param dataset  the dataset.
     */
    public void setModel(TableXYDataset dataset) {
        this.model = dataset;
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[6]++;
        this.model.addChangeListener(this);
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[7]++;
        fireTableDataChanged();
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[8]++;
    }

    /**
     * Returns the number of rows.
     *
     * @return The row count.
     */
    public int getRowCount() {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.model == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[1]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[2]++;}
        return this.model.getItemCount();
    }

    /**
     * Gets the number of columns in the model.
     *
     * @return The number of columns in the model.
     */
    public int getColumnCount() {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.model == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[3]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[4]++;}
        return this.model.getSeriesCount() + 1;
    }

    /**
     * Returns the column name.
     *
     * @param column  the column index.
     *
     * @return The column name.
     */
    public String getColumnName(int column) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.model == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[5]++;
            return super.getColumnName(column);

        } else {
  CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[6]++;}
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((column < 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[7]++;
            return "X Value";

        }
        else {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[8]++;
            return this.model.getSeriesKey(column - 1).toString();
        }
    }

    /**
     * Returns a value of the specified cell.
     * Column 0 is the X axis, Columns 1 and over are the Y axis
     *
     * @param row  the row number.
     * @param column  the column number.
     *
     * @return The value of the specified cell.
     */
    public Object getValueAt(int row, int column) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.model == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[9]++;
            return null;

        } else {
  CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[10]++;}
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((column < 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[11]++;
            return this.model.getX(0, row);

        }
        else {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[12]++;
            return this.model.getY(column - 1, row);
        }
    }

    /**
     * Receives notification that the underlying dataset has changed.
    *
     * @param event  the event
     *
     * @see DatasetChangeListener
     */
    public void datasetChanged(DatasetChangeEvent event) {
        fireTableDataChanged();
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[15]++;
    }

    /**
     * Returns a flag indicating whether or not the specified cell is editable.
     *
     * @param row  the row number.
     * @param column  the column number.
     *
     * @return <code>true</code> if the specified cell is editable.
     */
    public boolean isCellEditable(int row, int column) {
        return false;
   }

    /**
     * Updates the {@link XYDataset} if allowed.
     *
     * @param value  the new value.
     * @param row  the row.
     * @param column  the column.
     */
    public void setValueAt(Object value, int row, int column) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isCellEditable(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[13]++;

            // XYDataset only provides methods for reading a dataset...
        } else {
  CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt.branches[14]++;}
    }

//    /**
//     * Run a demonstration of the table model interface.
//     *
//     * @param args  ignored.
//     *
//     * @throws Exception when an error occurs.
//     */
//    public static void main(String args[]) throws Exception {
//        JFrame frame = new JFrame();
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//
//        XYSeries s1 = new XYSeries("Series 1", true, false);
//        for (int i = 0; i < 10; i++) {
//            s1.add(i, Math.random());   
//        }
//        XYSeries s2 = new XYSeries("Series 2", true, false);
//        for (int i = 0; i < 15; i++) {
//            s2.add(i, Math.random());   
//        }
//        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
//        dataset.addSeries(s1);
//        dataset.addSeries(s2);
//        XYDatasetTableModel tablemodel = new XYDatasetTableModel();
//
//        tablemodel.setModel(dataset);
//
//        JTable dataTable = new JTable(tablemodel);
//        JScrollPane scroll = new JScrollPane(dataTable);
//        scroll.setPreferredSize(new Dimension(600, 150));
//
//        JFreeChart chart = ChartFactory.createXYLineChart(
//            "XY Series Demo",
//            "X", "Y", dataset, PlotOrientation.VERTICAL,
//            true,
//            true,
//            false
//        );
//
//        ChartPanel chartPanel = new ChartPanel(chart);
//
//        panel.add(chartPanel, BorderLayout.CENTER);
//        panel.add(scroll, BorderLayout.SOUTH);
//
//        frame.setContentPane(panel);
//        frame.setSize(600, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.show();
//        RefineryUtilities.centerFrameOnScreen(frame);
//    }

}

class CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XYDatasetTableModel.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$kd1oy4f97s74ygmpkkk3rnfs4zxpmerdn22xt () {
    super("org.jfree.data.xy.XYDatasetTableModel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.XYDatasetTableModel.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

