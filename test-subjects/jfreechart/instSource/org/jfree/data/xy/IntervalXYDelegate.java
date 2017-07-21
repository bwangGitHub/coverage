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
 * -----------------------
 * IntervalXYDelegate.java
 * -----------------------
 * (C) Copyright 2004, 2005, 2007, by Andreas Schroeder and Contributors.
 *
 * Original Author:  Andreas Schroeder;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 31-Mar-2004 : Version 1 (AS);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 04-Nov-2004 : Added argument check for setIntervalWidth() method (DG);
 * 17-Nov-2004 : New methods to reflect changes in DomainInfo (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for the 1.0.0 
 *               release (DG);
 * 21-Feb-2005 : Made public and added equals() method (DG);
 * 06-Oct-2005 : Implemented DatasetChangeListener to recalculate 
 *               autoIntervalWidth (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *   
 */

package org.jfree.data.xy;

import java.io.Serializable;

import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A delegate that handles the specification or automatic calculation of the
 * interval surrounding the x-values in a dataset.  This is used to extend
 * a regular {@link XYDataset} to support the {@link IntervalXYDataset} 
 * interface.
 * <p> 
 * The decorator pattern was not used because of the several possibly 
 * implemented interfaces of the decorated instance (e.g. 
 * {@link TableXYDataset}, {@link RangeInfo}, {@link DomainInfo} etc.).
 * <p>
 * The width can be set manually or calculated automatically. The switch
 * autoWidth allows to determine which behavior is used. The auto width 
 * calculation tries to find the smallest gap between two x-values in the
 * dataset.  If there is only one item in the series, the auto width 
 * calculation fails and falls back on the manually set interval width (which 
 * is itself defaulted to 1.0). 
 */
public class IntervalXYDelegate implements DatasetChangeListener,
                                           DomainInfo, Serializable, 
                                           Cloneable, PublicCloneable {
  static {
    CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -685166711639592857L;
  static {
    CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[1]++;
  }
    
    /**
     * The dataset to enhance. 
     */
    private XYDataset dataset;

    /**
     * A flag to indicate whether the width should be calculated automatically.
     */
    private boolean autoWidth;
    
    /**
     * A value between 0.0 and 1.0 that indicates the position of the x-value
     * within the interval.
     */
    private double intervalPositionFactor; 
    
    /**
     * The fixed interval width (defaults to 1.0).
     */
    private double fixedIntervalWidth;
    
    /**
     * The automatically calculated interval width.
     */
    private double autoIntervalWidth;
    
    /**
     * Creates a new delegate that.
     * 
     * @param dataset  the underlying dataset (<code>null</code> not permitted).
     */
    public IntervalXYDelegate(XYDataset dataset) {
        this(dataset, true);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[2]++;
    }
    
    /**
     * Creates a new delegate for the specified dataset.
     * 
     * @param dataset  the underlying dataset (<code>null</code> not permitted).
     * @param autoWidth  a flag that controls whether the interval width is 
     *                   calculated automatically.
     */
    public IntervalXYDelegate(XYDataset dataset, boolean autoWidth) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[1]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[2]++;}
        this.dataset = dataset;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[4]++;
        this.autoWidth = autoWidth;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[5]++;
        this.intervalPositionFactor = 0.5;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[6]++;
        this.autoIntervalWidth = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[7]++; 
        this.fixedIntervalWidth = 1.0;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[8]++;
    }
    
    /**
     * Returns <code>true</code> if the interval width is automatically 
     * calculated, and <code>false</code> otherwise.
     * 
     * @return A boolean.
     */
    public boolean isAutoWidth() {
        return this.autoWidth;
    }
    
    /**
     * Sets the flag that indicates whether the interval width is automatically
     * calculated.  If the flag is set to <code>true</code>, the interval is
     * recalculated.
     * <p>
     * Note: recalculating the interval amounts to changing the data values
     * represented by the dataset.  The calling dataset must fire an
     * appropriate {@link DatasetChangeEvent}.
     * 
     * @param b  a boolean.
     */
    public void setAutoWidth(boolean b) {
        this.autoWidth = b;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[9]++;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((b) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[3]++;
            this.autoIntervalWidth = recalculateInterval();
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[11]++;

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[4]++;}
    }
    
    /**
     * Returns the interval position factor.
     * 
     * @return The interval position factor.
     */
    public double getIntervalPositionFactor() {
        return this.intervalPositionFactor;
    }

    /**
     * Sets the interval position factor.  This controls how the interval is
     * aligned to the x-value.  For a value of 0.5, the interval is aligned
     * with the x-value in the center.  For a value of 0.0, the interval is
     * aligned with the x-value at the lower end of the interval, and for a 
     * value of 1.0, the interval is aligned with the x-value at the upper
     * end of the interval.
     * 
     * Note that changing the interval position factor amounts to changing the 
     * data values represented by the dataset.  Therefore, the dataset that is 
     * using this delegate is responsible for generating the 
     * appropriate {@link DatasetChangeEvent}.     
     * 
     * @param d  the new interval position factor (in the range 
     *           <code>0.0</code> to <code>1.0</code> inclusive).
     */
    public void setIntervalPositionFactor(double d) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((d < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((1.0 < d) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[5]++;
            throw new IllegalArgumentException(
                    "Argument 'd' outside valid range.");

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[6]++;}
        this.intervalPositionFactor = d;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[13]++;
    }

    /**
     * Returns the fixed interval width.
     * 
     * @return The fixed interval width.
     */
    public double getFixedIntervalWidth() {
        return this.fixedIntervalWidth;
    }
    
    /**
     * Sets the fixed interval width and, as a side effect, sets the
     * <code>autoWidth</code> flag to <code>false</code>.  
     * 
     * Note that changing the interval width amounts to changing the data 
     * values represented by the dataset.  Therefore, the dataset
     * that is using this delegate is responsible for generating the 
     * appropriate {@link DatasetChangeEvent}.
     * 
     * @param w  the width (negative values not permitted).
     */
    public void setFixedIntervalWidth(double w) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((w < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[7]++;
            throw new IllegalArgumentException("Negative 'w' argument.");

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[8]++;}
        this.fixedIntervalWidth = w;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[15]++;
        this.autoWidth = false;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[16]++;
    }
    
    /**
     * Returns the interval width.  This method will return either the 
     * auto calculated interval width or the manually specified interval
     * width, depending on the {@link #isAutoWidth()} result.
     * 
     * @return The interval width to use.
     */
    public double getIntervalWidth() {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((isAutoWidth()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Double.isInfinite(this.autoIntervalWidth)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[9]++;
            // everything is fine: autoWidth is on, and an autoIntervalWidth 
            // was set.
            return this.autoIntervalWidth;

        }
        else {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[10]++;
            // either autoWidth is off or autoIntervalWidth was not set.
            return this.fixedIntervalWidth;
        }
    }

    /**
     * Returns the start value of the x-interval for an item within a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The start value of the x-interval (possibly <code>null</code>).
     * 
     * @see #getStartXValue(int, int)
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[18]++;
        Number startX = null;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[19]++;
        Number x = this.dataset.getX(series, item);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[11]++;
            startX = new Double(x.doubleValue() 
                     - (getIntervalPositionFactor() * getIntervalWidth()));
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[21]++;
 
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[12]++;}
        return startX;
    }
    
    /**
     * Returns the start value of the x-interval for an item within a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The start value of the x-interval.
     * 
     * @see #getStartX(int, int)
     */
    public double getStartXValue(int series, int item) {
        return this.dataset.getXValue(series, item) 
                - getIntervalPositionFactor() * getIntervalWidth();
    }
    
    /**
     * Returns the end value of the x-interval for an item within a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The end value of the x-interval (possibly <code>null</code>).
     * 
     * @see #getEndXValue(int, int)
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[22]++;
        Number endX = null;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[23]++;
        Number x = this.dataset.getX(series, item);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[13]++;
            endX = new Double(x.doubleValue() 
                + ((1.0 - getIntervalPositionFactor()) * getIntervalWidth()));
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[25]++;
 
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[14]++;}
        return endX;
    }

    /**
     * Returns the end value of the x-interval for an item within a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The end value of the x-interval.
     * 
     * @see #getEndX(int, int)
     */
    public double getEndXValue(int series, int item) {
        return this.dataset.getXValue(series, item) 
                + (1.0 - getIntervalPositionFactor()) * getIntervalWidth();
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
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[26]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[27]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[15]++;
            result = r.getLowerBound();
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[29]++;

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[16]++;}
        return result;
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
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[30]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[31]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[17]++;
            result = r.getUpperBound();
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[33]++;

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[18]++;}
        return result;
    }

    /**
     * Returns the range of the values in the dataset's domain, including
     * or excluding the interval around each x-value as specified.
     *
     * @param includeInterval  a flag that determines whether or not the 
     *                         x-interval should be taken into account.
     * 
     * @return The range.
     */
    public Range getDomainBounds(boolean includeInterval) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[34]++;
        // first get the range without the interval, then expand it for the
        // interval width
        Range range = DatasetUtilities.findDomainBounds(this.dataset, false);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((range != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[19]++;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[36]++;
            double lowerAdj = getIntervalWidth() * getIntervalPositionFactor();
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[37]++;
            double upperAdj = getIntervalWidth() - lowerAdj;
            range = new Range(range.getLowerBound() - lowerAdj, 
                range.getUpperBound() + upperAdj);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[38]++;

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[20]++;}
        return range;
    }
    
    /**
     * Handles events from the dataset by recalculating the interval if 
     * necessary.
     * 
     * @param e  the event.
     */    
    public void datasetChanged(DatasetChangeEvent e) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[39]++;
int CodeCoverConditionCoverageHelper_C11;
        // TODO: by coding the event with some information about what changed
        // in the dataset, we could make the recalculation of the interval
        // more efficient in some cases...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.autoWidth) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[21]++;
            this.autoIntervalWidth = recalculateInterval();
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[40]++;

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[22]++;}
    }
    
    /**
     * Recalculate the minimum width "from scratch".
     * 
     * @return The minimum width.
     */
    private double recalculateInterval() {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[41]++;
        double result = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[42]++;
        int seriesCount = this.dataset.getSeriesCount();
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[43]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int series = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[1]--;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[2]--;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[3]++;
}
            result = Math.min(result, calculateIntervalForSeries(series));
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[44]++;
        }
        return result;
    }
    
    /**
     * Calculates the interval width for a given series.
     *  
     * @param series  the series index.
     * 
     * @return The interval width.
     */
    private double calculateIntervalForSeries(int series) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[45]++;
        double result = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[46]++;
        int itemCount = this.dataset.getItemCount(series);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[47]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((itemCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[23]++;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[48]++;
            double prev = this.dataset.getXValue(series, 0);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[49]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
            for (int item = 1;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[4]--;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[5]--;
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.loops[6]++;
}
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[50]++;
                double x = this.dataset.getXValue(series, item);
                result = Math.min(result, x - prev);
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[51]++;
                prev = x;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[52]++;
            }

        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[24]++;}
        return result;
    }
    
    /**
     * Tests the delegate for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[25]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[26]++;}
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[54]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof IntervalXYDelegate) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[27]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[28]++;}
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[55]++;
        IntervalXYDelegate that = (IntervalXYDelegate) obj;
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[56]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.autoWidth != that.autoWidth) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[30]++;}
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.intervalPositionFactor != that.intervalPositionFactor) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[31]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[32]++;}
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.statements[58]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.fixedIntervalWidth != that.fixedIntervalWidth) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt.branches[34]++;}
        return true;
    }
    
    /**
     * @return A clone of this delegate.
     * 
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}

class CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt ());
  }
    public static long[] statements = new long[59];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.IntervalXYDelegate.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$2doghvz88rwjdg075bqh2qw2o3egaypa0qxt () {
    super("org.jfree.data.xy.IntervalXYDelegate.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 58; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.IntervalXYDelegate.java");
      for (int i = 1; i <= 58; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

