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
 * --------------------------
 * DefaultTableXYDataset.java
 * --------------------------
 * (C) Copyright 2003-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   Jody Brownell;
 *                   David Gilbert (for Object Refinery Limited);
 *                   Andreas Schroeder;
 *
 * Changes:
 * --------
 * 27-Jul-2003 : XYDataset that forces each series to have a value for every 
 *               X-point which is essential for stacked XY area charts (RA);
 * 18-Aug-2003 : Fixed event notification when removing and updating 
 *               series (RA);
 * 22-Sep-2003 : Functionality moved from TableXYDataset to 
 *               DefaultTableXYDataset (RA);
 * 23-Dec-2003 : Added patch for large datasets, submitted by Jody 
 *               Brownell (DG);
 * 16-Feb-2004 : Added pruning methods (DG);
 * 31-Mar-2004 : Provisional implementation of IntervalXYDataset (AS);
 * 01-Apr-2004 : Sound implementation of IntervalXYDataset (AS);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 05-Oct-2005 : Made the interval delegate a dataset listener (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 
 */

package org.jfree.data.xy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.util.ObjectUtilities;

/**
 * An {@link XYDataset} where every series shares the same x-values (required 
 * for generating stacked area charts).
 */
public class DefaultTableXYDataset extends AbstractIntervalXYDataset 
                                   implements TableXYDataset, 
                                              IntervalXYDataset, DomainInfo {
  static {
    CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.ping();
  }

    
    /** 
     * Storage for the data - this list will contain zero, one or many 
     * XYSeries objects. 
     */
    private List data = null;
  {
    CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[1]++;
  }
    
    /** Storage for the x values. */
    private HashSet xPoints = null;
  {
    CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[2]++;
  }
    
    /** A flag that controls whether or not events are propogated. */
    private boolean propagateEvents = true;
  {
    CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[3]++;
  }
    
    /** A flag that controls auto pruning. */
    private boolean autoPrune = false;
  {
    CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[4]++;
  }

    /** The delegate used to control the interval width. */
    private IntervalXYDelegate intervalDelegate;

    /**
     * Creates a new empty dataset.
     */
    public DefaultTableXYDataset() {
        this(false);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[5]++;
    }
    
    /**
     * Creates a new empty dataset.
     * 
     * @param autoPrune  a flag that controls whether or not x-values are 
     *                   removed whenever the corresponding y-values are all 
     *                   <code>null</code>.
     */
    public DefaultTableXYDataset(boolean autoPrune) {
        this.autoPrune = autoPrune;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[6]++;
        this.data = new ArrayList();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[7]++;
        this.xPoints = new HashSet();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[8]++;
        this.intervalDelegate = new IntervalXYDelegate(this, false);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[9]++;
        addChangeListener(this.intervalDelegate);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[10]++;
    }

    /**
     * Returns the flag that controls whether or not x-values are removed from 
     * the dataset when the corresponding y-values are all <code>null</code>.
     * 
     * @return A boolean.
     */
    public boolean isAutoPrune() {
        return this.autoPrune;
    }

    /**
     * Adds a series to the collection and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.  The series should be configured to NOT 
     * allow duplicate x-values.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void addSeries(XYSeries series) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[1]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[2]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series.getAllowDuplicateXValues()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[3]++;
            throw new IllegalArgumentException(
                "Cannot accept XYSeries that allow duplicate values. "
                + "Use XYSeries(seriesName, <sort>, false) constructor."
            );

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[4]++;}
        updateXPoints(series);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[13]++;
        this.data.add(series);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[14]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[15]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[16]++;
    }

    /**
     * Adds any unique x-values from 'series' to the dataset, and also adds any
     * x-values that are in the dataset but not in 'series' to the series.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    private void updateXPoints(XYSeries series) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[5]++;
            throw new IllegalArgumentException("Null 'series' not permitted.");

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[6]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[18]++;
        HashSet seriesXPoints = new HashSet();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[19]++;
        boolean savedState = this.propagateEvents;
        this.propagateEvents = false;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[20]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int itemNo = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((itemNo < series.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); itemNo++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[1]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[2]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[3]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[22]++;
            Number xValue = series.getX(itemNo);
            seriesXPoints.add(xValue);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[23]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.xPoints.contains(xValue)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[7]++;
                this.xPoints.add(xValue);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[25]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[26]++;
                int seriesCount = this.data.size();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
                for (int seriesNo = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((seriesNo < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); seriesNo++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[4]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[5]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[6]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[28]++;
                    XYSeries dataSeries = (XYSeries) this.data.get(seriesNo);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dataSeries.equals(series)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[9]++;
                        dataSeries.add(xValue, null);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[30]++;

                    } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[10]++;} 
                }

            } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[8]++;}
        }
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[31]++;
        Iterator iterator = this.xPoints.iterator();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[7]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[8]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[9]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[33]++;
            Number xPoint = (Number) iterator.next();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((seriesXPoints.contains(xPoint)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[11]++;
                series.add(xPoint, null);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[35]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[12]++;}
        }
        this.propagateEvents = savedState;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[36]++;
    }

    /**
     * Updates the x-values for all the series in the dataset.
     */
    public void updateXPoints() {
        this.propagateEvents = false;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[37]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[38]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[10]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[10]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[11]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[12]++;
}
            updateXPoints((XYSeries) this.data.get(s));
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[39]++;
        }
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.autoPrune) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[13]++;
            prune();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[41]++;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[14]++;}
        this.propagateEvents = true;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[42]++;
    }

    /**
     * Returns the number of series in the collection.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.data.size();
    }

    /**
     * Returns the number of x values in the dataset.
     *
     * @return The number of x values in the dataset.
     */
    public int getItemCount() {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[43]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.xPoints == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[15]++;
            return 0;

        } 
        else {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[16]++;
            return this.xPoints.size();
        }
    }

    /**
     * Returns a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The series (never <code>null</code>).
     */
    public XYSeries getSeries(int series) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[44]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[17]++;
            throw new IllegalArgumentException("Index outside valid range.");

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[18]++;}
        return (XYSeries) this.data.get(series);
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The key for a series.
     */
    public Comparable getSeriesKey(int series) {
        // check arguments...delegated
        return getSeries(series).getKey();
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The number of items in the specified series.
     */
    public int getItemCount(int series) {
        // check arguments...delegated
        return getSeries(series).getItemCount();
    }

    /**
     * Returns the x-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value for the specified series and item.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[45]++;
        XYSeries s = (XYSeries) this.data.get(series);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[46]++;
        XYDataItem dataItem = s.getDataItem(item);
        return dataItem.getX();
    }
    
    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The starting X value.
     */
    public Number getStartX(int series, int item) {
        return this.intervalDelegate.getStartX(series, item);
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The ending X value.
     */
    public Number getEndX(int series, int item) {
        return this.intervalDelegate.getEndX(series, item);
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param index  the index of the item of interest (zero-based).
     *
     * @return The y-value for the specified series and item (possibly 
     *         <code>null</code>). 
     */
    public Number getY(int series, int index) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[47]++;
        XYSeries ts = (XYSeries) this.data.get(series);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[48]++;
        XYDataItem dataItem = ts.getDataItem(index);
        return dataItem.getY();
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The starting Y value.
     */
    public Number getStartY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The ending Y value.
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Removes all the series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     */
    public void removeAllSeries() {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[49]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[13]++;


int CodeCoverConditionCoverageHelper_C14;

        // Unregister the collection as a change listener to each series in
        // the collection.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[13]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[14]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[15]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[50]++;
            XYSeries series = (XYSeries) this.data.get(i);
            series.removeChangeListener(this);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[51]++;
        }

        // Remove all the series from the collection and notify listeners.
        this.data.clear();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[52]++;
        this.xPoints.clear();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[53]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[54]++;
    }

    /**
     * Removes a series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void removeSeries(XYSeries series) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[55]++;
int CodeCoverConditionCoverageHelper_C15;

        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[19]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[20]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;

        // remove the series...
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.data.contains(series)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[21]++;
            series.removeChangeListener(this);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[57]++;
            this.data.remove(series);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[58]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[59]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.data.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[23]++;
                this.xPoints.clear();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[60]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[24]++;}
            fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[61]++;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[22]++;}

    }

    /**
     * Removes a series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param series  the series (zero based index).
     */
    public void removeSeries(int series) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[62]++;
int CodeCoverConditionCoverageHelper_C18;

        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((series > getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[25]++;
            throw new IllegalArgumentException("Index outside valid range.");

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[26]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[63]++;

        // fetch the series, remove the change listener, then remove the series.
        XYSeries s = (XYSeries) this.data.get(series);
        s.removeChangeListener(this);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[64]++;
        this.data.remove(series);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[65]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[66]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.data.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[27]++;
            this.xPoints.clear();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[67]++;

        }
        else {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[28]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[68]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.autoPrune) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[29]++;
            prune();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[69]++;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[30]++;}
}
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[70]++;

    }

    /**
     * Removes the items from all series for a given x value.
     *
     * @param x  the x-value.
     */
    public void removeAllValuesForX(Number x) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[31]++; 
            throw new IllegalArgumentException("Null 'x' argument.");

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[32]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[72]++;
        boolean savedState = this.propagateEvents;
        this.propagateEvents = false;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[73]++;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[74]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[16]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((s < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[16]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[17]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[18]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[75]++;
            XYSeries series = (XYSeries) this.data.get(s);
            series.remove(x);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[76]++;
        }
        this.propagateEvents = savedState;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[77]++;
        this.xPoints.remove(x);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[78]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[79]++;
    }

    /**
     * Returns <code>true</code> if all the y-values for the specified x-value
     * are <code>null</code> and <code>false</code> otherwise.
     * 
     * @param x  the x-value.
     * 
     * @return A boolean.
     */
    protected boolean canPrune(Number x) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[80]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[19]++;


int CodeCoverConditionCoverageHelper_C23;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((s < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[19]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[20]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[21]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[81]++;
            XYSeries series = (XYSeries) this.data.get(s);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[82]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((series.getY(series.indexOf(x)) != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[33]++;
                return false;

            } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[34]++;}
        }
        return true;
    }
    
    /**
     * Removes all x-values for which all the y-values are <code>null</code>.
     */
    public void prune() {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[83]++;
        HashSet hs = (HashSet) this.xPoints.clone();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[84]++;
        Iterator iterator = hs.iterator();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[85]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[22]++;


int CodeCoverConditionCoverageHelper_C25;
        while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[22]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[23]--;
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.loops[24]++;
}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[86]++;
            Number x = (Number) iterator.next();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[87]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((canPrune(x)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[35]++;
                removeAllValuesForX(x);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[88]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[36]++;}
        }
    }
    
    /**
     * This method receives notification when a series belonging to the dataset
     * changes.  It responds by updating the x-points for the entire dataset 
     * and sending a {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param event  information about the change.
     */
    public void seriesChanged(SeriesChangeEvent event) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[89]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.propagateEvents) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[37]++;
            updateXPoints();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[90]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[91]++;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[38]++;}
    }

    /**
     * Tests this collection for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[92]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[39]++;
            return true;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[40]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[93]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultTableXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[42]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[94]++;
        DefaultTableXYDataset that = (DefaultTableXYDataset) obj;
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[95]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.autoPrune != that.autoPrune) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[44]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[96]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.propagateEvents != that.propagateEvents) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[45]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[46]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[97]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.intervalDelegate.equals(that.intervalDelegate)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[47]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[48]++;}
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[98]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[50]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = (this.data != null ? this.data.hashCode() : 0);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[99]++;
        result = 29 * result 
                 + (this.xPoints != null ? this.xPoints.hashCode() : 0);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[100]++;
        result = 29 * result + (this.propagateEvents ? 1 : 0);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[101]++;
        result = 29 * result + (this.autoPrune ? 1 : 0);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[102]++;
        return result;
    }
    
    /**
     * Returns the minimum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The minimum value.
     */
    public double getDomainLowerBound(boolean includeInterval) {
        return this.intervalDelegate.getDomainLowerBound(includeInterval);
    }

    /**
     * Returns the maximum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The maximum value.
     */
    public double getDomainUpperBound(boolean includeInterval) {
        return this.intervalDelegate.getDomainUpperBound(includeInterval);
    }

    /**
     * Returns the range of the values in this dataset's domain.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getDomainBounds(boolean includeInterval) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[103]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[51]++;
            return this.intervalDelegate.getDomainBounds(includeInterval);

        }
        else {
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.branches[52]++;
            return DatasetUtilities.iterateDomainBounds(this, includeInterval);
        }
    }
    
    /**
     * Returns the interval position factor. 
     * 
     * @return The interval position factor.
     */
    public double getIntervalPositionFactor() {
        return this.intervalDelegate.getIntervalPositionFactor();
    }

    /**
     * Sets the interval position factor. Must be between 0.0 and 1.0 inclusive.
     * If the factor is 0.5, the gap is in the middle of the x values. If it
     * is lesser than 0.5, the gap is farther to the left and if greater than
     * 0.5 it gets farther to the right.
     *  
     * @param d the new interval position factor.
     */
    public void setIntervalPositionFactor(double d) {
        this.intervalDelegate.setIntervalPositionFactor(d);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[104]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[105]++;
    }

    /**
     * returns the full interval width. 
     * 
     * @return The interval width to use.
     */
    public double getIntervalWidth() {
        return this.intervalDelegate.getIntervalWidth();
    }

    /**
     * Sets the interval width to a fixed value, and sends a 
     * {@link DatasetChangeEvent} to all registered listeners. 
     * 
     * @param d  the new interval width (must be > 0).
     */
    public void setIntervalWidth(double d) {
        this.intervalDelegate.setFixedIntervalWidth(d);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[106]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[107]++;
    }

    /**
     * Returns whether the interval width is automatically calculated or not.
     * 
     * @return A flag that determines whether or not the interval width is 
     *         automatically calculated.
     */
    public boolean isAutoWidth() {
        return this.intervalDelegate.isAutoWidth();
    }

    /**
     * Sets the flag that indicates whether the interval width is automatically
     * calculated or not. 
     * 
     * @param b  a boolean.
     */
    public void setAutoWidth(boolean b) {
        this.intervalDelegate.setAutoWidth(b);
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[108]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1.statements[109]++;
    }
 
}

class CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1 ());
  }
    public static long[] statements = new long[110];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[35];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultTableXYDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 34; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$m553c7l0lap58gq45rfby5fwor31xlh4lje5fbs1 () {
    super("org.jfree.data.xy.DefaultTableXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 109; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.DefaultTableXYDataset.java");
      for (int i = 1; i <= 109; i++) {
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
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

