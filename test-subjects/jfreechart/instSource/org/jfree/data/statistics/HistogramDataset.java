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
 * ---------------------
 * HistogramDataset.java
 * ---------------------
 * (C) Copyright 2003-2007, by Jelai Wang and Contributors.
 *
 * Original Author:  Jelai Wang (jelaiw AT mindspring.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Cameron Hayne;
 *                   Rikard Bj?rklind;
 *
 * Changes
 * -------
 * 06-Jul-2003 : Version 1, contributed by Jelai Wang (DG);
 * 07-Jul-2003 : Changed package and added Javadocs (DG);
 * 15-Oct-2003 : Updated Javadocs and removed array sorting (JW);
 * 09-Jan-2004 : Added fix by "Z." posted in the JFreeChart forum (DG);
 * 01-Mar-2004 : Added equals() and clone() methods and implemented 
 *               Serializable.  Also added new addSeries() method (DG);
 * 06-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 20-May-2005 : Speed up binning - see patch 1026151 contributed by Cameron
 *               Hayne (DG);
 * 08-Jun-2005 : Fixed bug in getSeriesKey() method (DG);
 * 22-Nov-2005 : Fixed cast in getSeriesKey() method - see patch 1329287 (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 03-Aug-2006 : Improved precision of bin boundary calculation (DG);
 * 07-Sep-2006 : Fixed bug 1553088 (DG);
 * 
 */

package org.jfree.data.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A dataset that can be used for creating histograms.
 * 
 * @see SimpleHistogramDataset
 */
public class HistogramDataset extends AbstractIntervalXYDataset 
                              implements IntervalXYDataset, 
                                         Cloneable, PublicCloneable, 
                                         Serializable {
  static {
    CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6341668077370231153L;
  static {
    CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[1]++;
  }
    
    /** A list of maps. */
    private List list;
    
    /** The histogram type. */
    private HistogramType type;

    /**
     * Creates a new (empty) dataset with a default type of 
     * {@link HistogramType}.FREQUENCY.
     */
    public HistogramDataset() {
        this.list = new ArrayList();
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[2]++;
        this.type = HistogramType.FREQUENCY;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[3]++;
    }
    
    /**
     * Returns the histogram type. 
     * 
     * @return The type (never <code>null</code>).
     */
    public HistogramType getType() { 
        return this.type; 
    }

    /**
     * Sets the histogram type and sends a {@link DatasetChangeEvent} to all 
     * registered listeners.
     * 
     * @param type  the type (<code>null</code> not permitted).
     */
    public void setType(HistogramType type) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[1]++;
            throw new IllegalArgumentException("Null 'type' argument");

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[2]++;}
        this.type = type;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[5]++;   
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[6]++;
    }

    /**
     * Adds a series to the dataset, using the specified number of bins.
     * 
     * @param key  the series key (<code>null</code> not permitted).
     * @param values the values (<code>null</code> not permitted).
     * @param bins  the number of bins (must be at least 1).
     */
    public void addSeries(Comparable key, double[] values, int bins) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[7]++;
        // defer argument checking...
        double minimum = getMinimum(values);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[8]++;
        double maximum = getMaximum(values);
        addSeries(key, values, bins, minimum, maximum);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[9]++;
    }

    /**
     * Adds a series to the dataset. Any data value less than minimum will be
     * assigned to the first bin, and any data value greater than maximum will
     * be assigned to the last bin.  Values falling on the boundary of 
     * adjacent bins will be assigned to the higher indexed bin.
     * 
     * @param key  the series key (<code>null</code> not permitted).
     * @param values  the raw observations.
     * @param bins  the number of bins (must be at least 1).
     * @param minimum  the lower bound of the bin range.
     * @param maximum  the upper bound of the bin range.
     */
    public void addSeries(Comparable key, 
                          double[] values, 
                          int bins, 
                          double minimum, 
                          double maximum) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[3]++;
            throw new IllegalArgumentException("Null 'key' argument.");
   
        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[4]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[5]++;
            throw new IllegalArgumentException("Null 'values' argument.");

        }
        else {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[6]++;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[12]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((bins < 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[7]++;
            throw new IllegalArgumentException(
                    "The 'bins' value must be at least 1.");

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[8]++;}
}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[13]++;
        double binWidth = (maximum - minimum) / bins;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[14]++;

        double lower = minimum;
        double upper;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[15]++;
        List binList = new ArrayList(bins);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < bins) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[1]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[2]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[3]++;
}
            HistogramBin bin;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
            // make sure bins[bins.length]'s upper boundary ends at maximum
            // to avoid the rounding issue. the bins[0] lower boundary is
            // guaranteed start from min
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i == bins - 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[9]++;
                bin = new HistogramBin(lower, maximum);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[18]++;

            }
            else {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[10]++;
                upper = minimum + (i + 1) * binWidth;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[19]++;
                bin = new HistogramBin(lower, upper);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[20]++;
                lower = upper;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[21]++;
            }
            binList.add(bin);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[22]++;
        }
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;        
        // fill the bins
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[4]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[5]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[6]++;
}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[24]++;
            int binIndex = bins - 1;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((values[i] < maximum) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[11]++;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[26]++;
                double fraction = (values[i] - minimum) / (maximum - minimum);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((fraction < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[13]++;
                    fraction = 0.0;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[28]++;

                } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[14]++;}
                binIndex = (int) (fraction * bins);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[29]++;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
                // rounding could result in binIndex being equal to bins
                // which will cause an IndexOutOfBoundsException - see bug
                // report 1553088
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((binIndex >= bins) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[15]++;
                    binIndex = bins - 1;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[31]++;

                } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[16]++;}

            } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[12]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[32]++;
            HistogramBin bin = (HistogramBin) binList.get(binIndex);
            bin.incrementCount();
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[33]++;
        }
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[34]++;
        // generic map for each series
        Map map = new HashMap();
        map.put("key", key);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[35]++;
        map.put("bins", binList);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[36]++;
        map.put("values.length", new Integer(values.length));
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[37]++;
        map.put("bin width", new Double(binWidth));
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[38]++;
        this.list.add(map);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[39]++;
    }
    
    /**
     * Returns the minimum value in an array of values.
     * 
     * @param values  the values (<code>null</code> not permitted and 
     *                zero-length array not permitted).
     * 
     * @return The minimum value.
     */
    private double getMinimum(double[] values) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((values.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[17]++;
            throw new IllegalArgumentException(
                    "Null or zero length 'values' argument.");

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[18]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[41]++;
        double min = Double.MAX_VALUE;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[42]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[7]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[8]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[9]++;
}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((values[i] < min) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[19]++;
                min = values[i];
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[44]++;

            } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[20]++;}
        }
        return min;
    }

    /**
     * Returns the maximum value in an array of values.
     * 
     * @param values  the values (<code>null</code> not permitted and 
     *                zero-length array not permitted).
     * 
     * @return The maximum value.
     */
    private double getMaximum(double[] values) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[45]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((values.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[21]++;
            throw new IllegalArgumentException(
                    "Null or zero length 'values' argument.");

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[22]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[46]++;
        double max = -Double.MAX_VALUE;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[47]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[10]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[11]--;
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.loops[12]++;
}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[48]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((values[i] > max) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[23]++;
                max = values[i];
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[49]++;

            } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[24]++;}
        }
        return max;
    }

    /**
     * Returns the bins for a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * 
     * @return A list of bins.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    List getBins(int series) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[50]++;
        Map map = (Map) this.list.get(series);
        return (List) map.get("bins"); 
    }

    /**
     * Returns the total number of observations for a series.
     * 
     * @param series  the series index.
     * 
     * @return The total.
     */
    private int getTotal(int series) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[51]++;
        Map map = (Map) this.list.get(series);
        return ((Integer) map.get("values.length")).intValue(); 
    }

    /**
     * Returns the bin width for a series.
     * 
     * @param series  the series index (zero based).
     * 
     * @return The bin width.
     */
    private double getBinWidth(int series) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[52]++;
        Map map = (Map) this.list.get(series);
        return ((Double) map.get("bin width")).doubleValue(); 
    }

    /**
     * Returns the number of series in the dataset.
     * 
     * @return The series count.
     */
    public int getSeriesCount() { 
        return this.list.size(); 
    }
    
    /**
     * Returns the key for a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * 
     * @return The series key.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[53]++;
        Map map = (Map) this.list.get(series);
        return (Comparable) map.get("key"); 
    }

    /**
     * Returns the number of data items for a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * 
     * @return The item count.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public int getItemCount(int series) {
        return getBins(series).size(); 
    }

    /**
     * Returns the X value for a bin.  This value won't be used for plotting 
     * histograms, since the renderer will ignore it.  But other renderers can 
     * use it (for example, you could use the dataset to create a line
     * chart).
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (zero based).
     * 
     * @return The start value.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[54]++;
        List bins = getBins(series);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[55]++;
        HistogramBin bin = (HistogramBin) bins.get(item);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[56]++;
        double x = (bin.getStartBoundary() + bin.getEndBoundary()) / 2.;
        return new Double(x);
    }

    /**
     * Returns the y-value for a bin (calculated to take into account the 
     * histogram type).
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (zero based).
     * 
     * @return The y-value.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public Number getY(int series, int item) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[57]++;
        List bins = getBins(series);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[58]++;
        HistogramBin bin = (HistogramBin) bins.get(item);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[59]++;
        double total = getTotal(series);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[60]++;
        double binWidth = getBinWidth(series);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[61]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.type == HistogramType.FREQUENCY) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[25]++;
            return new Double(bin.getCount());

        }
        else {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[26]++;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[62]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.type == HistogramType.RELATIVE_FREQUENCY) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[27]++;
            return new Double(bin.getCount() / total);

        }
        else {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[28]++;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[63]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.type == HistogramType.SCALE_AREA_TO_1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[29]++;
            return new Double(bin.getCount() / (binWidth * total));

        }
        else {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[30]++; // pretty sure this shouldn't ever happen
            throw new IllegalStateException();
        }
}
}
    }

    /**
     * Returns the start value for a bin.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (zero based).
     * 
     * @return The start value.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[64]++;
        List bins = getBins(series);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[65]++;
        HistogramBin bin = (HistogramBin) bins.get(item);
        return new Double(bin.getStartBoundary());
    }

    /**
     * Returns the end value for a bin.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (zero based).
     * 
     * @return The end value.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[66]++;
        List bins = getBins(series);
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[67]++;
        HistogramBin bin = (HistogramBin) bins.get(item);
        return new Double(bin.getEndBoundary());
    }

    /**
     * Returns the start y-value for a bin (which is the same as the y-value, 
     * this method exists only to support the general form of the 
     * {@link IntervalXYDataset} interface).
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (zero based).
     * 
     * @return The y-value.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */
    public Number getStartY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the end y-value for a bin (which is the same as the y-value, 
     * this method exists only to support the general form of the 
     * {@link IntervalXYDataset} interface).
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (zero based).
     * 
     * @return The Y value.
     * 
     * @throws IndexOutOfBoundsException if <code>series</code> is outside the
     *     specified range.
     */    
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[68]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[31]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[32]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[69]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj instanceof HistogramDataset) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[34]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[70]++;
        HistogramDataset that = (HistogramDataset) obj;
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[71]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.type, that.type)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[36]++;}
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.statements[72]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.list, that.list)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1.branches[38]++;}
        return true;   
    }

    /**
     * Returns a clone of the dataset.
     * 
     * @return A clone of the dataset.
     * 
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1 ());
  }
    public static long[] statements = new long[73];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.HistogramDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$1o5dpur05yq5fg0mb5rjrl2p28c9qevs1 () {
    super("org.jfree.data.statistics.HistogramDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 72; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.HistogramDataset.java");
      for (int i = 1; i <= 72; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

