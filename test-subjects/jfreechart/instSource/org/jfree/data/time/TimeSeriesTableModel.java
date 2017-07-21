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
 * -------------------------
 * TimeSeriesTableModel.java
 * -------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 14-Nov-2001 : Version 1 (DG);
 * 05-Apr-2002 : Removed redundant first column (DG);
 * 24-Jun-2002 : Removed unnecessary local variable (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package org.jfree.data.time;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesChangeListener;

/**
 * Wrapper around a time series to convert it to a table model for use in 
 * a <code>JTable</code>.
 */
public class TimeSeriesTableModel extends AbstractTableModel 
                                  implements SeriesChangeListener {
  static {
    CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.ping();
  }


    /** The series. */
    private TimeSeries series;

    /** A flag that controls whether the series is editable. */
    private boolean editable;

    /** The new time period. */
    private RegularTimePeriod newTimePeriod;

    /** The new value. */
    private Number newValue;

    /**
     * Default constructor.
     */
    public TimeSeriesTableModel() {
        this(new TimeSeries("Untitled"));
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[1]++;
    }

    /**
     * Constructs a table model for a time series.
     *
     * @param series  the time series.
     */
    public TimeSeriesTableModel(TimeSeries series) {
        this(series, false);
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[2]++;
    }

    /**
     * Creates a table model based on a time series.
     *
     * @param series  the time series.
     * @param editable  if <ocde>true</code>, the table is editable.
     */
    public TimeSeriesTableModel(TimeSeries series, boolean editable) {
        this.series = series;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[3]++;
        this.series.addChangeListener(this);
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[4]++;
        this.editable = editable;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[5]++;
    }

    /**
     * Returns the number of columns in the table model.  For this particular
     * model, the column count is fixed at 2.
     *
     * @return The column count.
     */
    public int getColumnCount() {
        return 2;
    }

    /**
     * Returns the column class in the table model.
     *
     * @param column    The column index.
     * 
     * @return The column class in the table model.
     */
    public Class getColumnClass(int column) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[1]++;
            return String.class;

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[2]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[3]++;
                return Double.class;

            }
            else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[4]++;
                return null;
            }
        }
    }

    /**
     * Returns the name of a column
     *
     * @param column  the column index.
     *
     * @return The name of a column.
     */
    public String getColumnName(int column) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[5]++;
            return "Period:";

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[6]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[7]++;
                return "Value:";

            }
            else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[8]++;
                return null;
            }
        }

    }

    /**
     * Returns the number of rows in the table model.
     *
     * @return The row count.
     */
    public int getRowCount() {
        return this.series.getItemCount();
    }

    /**
     * Returns the data value for a cell in the table model.
     *
     * @param row  the row number.
     * @param column  the column number.
     * 
     * @return The data value for a cell in the table model.
     */
    public Object getValueAt(int row, int column) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((row < this.series.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[9]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[11]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[11]++;
                return this.series.getTimePeriod(row);

            }
            else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[12]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[12]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[13]++;
                    return this.series.getValue(row);

                }
                else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[14]++;
                    return null;
                }
            }

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[10]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[13]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[15]++;
                return this.newTimePeriod;

            }
            else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[16]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[14]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[17]++;
                    return this.newValue;

                }
                else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[18]++;
                    return null;
                }
            }
        }

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
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[15]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.editable) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[19]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[16]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[21]++;
                return true;

            }
            else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[22]++;
                return false;
            }

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[20]++;
            return false;
        }
    }

    /**
     * Updates the time series.
     *
     * @param value  the new value.
     * @param row  the row.
     * @param column  the column.
     */
    public void setValueAt(Object value, int row, int column) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[17]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((row < this.series.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[23]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[18]++;
int CodeCoverConditionCoverageHelper_C13;

            // update the time series appropriately
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[25]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[19]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[20]++;
                    Double v = Double.valueOf(value.toString());
                    this.series.update(row, v);
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[21]++;

                }
                catch (NumberFormatException nfe) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[28]++;
                    System.err.println("Number format exception");
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[22]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[27]++;
}
  }

            } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[26]++;}

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[24]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[23]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[29]++;
                // this.series.getClass().valueOf(value.toString());
                this.newTimePeriod = null;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[24]++;

            }
            else {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[30]++;
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[25]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((column == 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[31]++;
                this.newValue = Double.valueOf(value.toString());
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[26]++;

            } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.branches[32]++;}
}
        }
    }

    /**
     * Receives notification that the time series has been changed.  Responds
     * by firing a table data change event.
     *
     * @param event  the event.
     */
    public void seriesChanged(SeriesChangeEvent event) {
        fireTableDataChanged();
CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep.statements[27]++;
    }

}

class CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimeSeriesTableModel.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$3uchsdzsboshx4jp2zee25xqs9w4xwm2f66r9ep () {
    super("org.jfree.data.time.TimeSeriesTableModel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimeSeriesTableModel.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

