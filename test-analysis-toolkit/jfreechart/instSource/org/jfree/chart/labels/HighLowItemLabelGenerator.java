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
 * ------------------------------
 * HighLowItemLabelGenerator.java
 * ------------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   David Basten;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1 (DG);
 * 16-Jan-2002 : Completed Javadocs (DG);
 * 23-Apr-2002 : Added date to the tooltip string (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 21-Mar-2003 : Implemented Serializable (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 17-Nov-2003 : Implemented PublicCloneable (DG);
 * 25-Feb-2004 : Renamed XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 25-May-2004 : Added number formatter (see patch 890496) (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 20-Apr-2005 : Renamed XYLabelGenerator --> XYItemLabelGenerator (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 * A standard item label generator for plots that use data from a 
 * {@link OHLCDataset}.
 */
public class HighLowItemLabelGenerator implements XYItemLabelGenerator, 
                                                  XYToolTipGenerator,
                                                  Cloneable, 
                                                  PublicCloneable,
                                                  Serializable {
  static {
    CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5617111754832211830L;
  static {
    CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[1]++;
  }
    
    /** The date formatter. */
    private DateFormat dateFormatter;

    /** The number formatter. */
    private NumberFormat numberFormatter;

    /**
     * Creates an item label generator using the default date and number 
     * formats.
     */
    public HighLowItemLabelGenerator() {
        this(DateFormat.getInstance(), NumberFormat.getInstance());
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[2]++;
    }

    /**
     * Creates a tool tip generator using the supplied date formatter.
     *
     * @param dateFormatter  the date formatter (<code>null</code> not 
     *                       permitted).
     * @param numberFormatter  the number formatter (<code>null</code> not 
     *                         permitted).
     */
    public HighLowItemLabelGenerator(DateFormat dateFormatter, 
                                     NumberFormat numberFormatter) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dateFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[1]++;
            throw new IllegalArgumentException(
                    "Null 'dateFormatter' argument.");
   
        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[2]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((numberFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'numberFormatter' argument.");

        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[4]++;}
        this.dateFormatter = dateFormatter;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[5]++;
        this.numberFormatter = numberFormatter;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[6]++;
    }

    /**
     * Generates a tooltip text item for a particular item within a series.
     *
     * @param dataset  the dataset.
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The tooltip text.
     */
    public String generateToolTip(XYDataset dataset, int series, int item) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[7]++;

        String result = null;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof OHLCDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[5]++;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[9]++;
            OHLCDataset d = (OHLCDataset) dataset;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[10]++;
            Number high = d.getHigh(series, item);
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[11]++;
            Number low = d.getLow(series, item);
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[12]++;
            Number open = d.getOpen(series, item);
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[13]++;
            Number close = d.getClose(series, item);
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[14]++;
            Number x = d.getX(series, item);

            result = d.getSeriesKey(series).toString();
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[15]++;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;

            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[7]++;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[17]++;
                Date date = new Date(x.longValue());
                result = result + "--> Date=" + this.dateFormatter.format(date);
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[18]++;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((high != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[9]++;
                    result = result + " High=" 
                             + this.numberFormatter.format(high.doubleValue());
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[20]++;

                } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[10]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((low != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[11]++;
                    result = result + " Low=" 
                             + this.numberFormatter.format(low.doubleValue());
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[22]++;

                } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[12]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((open != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[13]++;
                    result = result + " Open=" 
                             + this.numberFormatter.format(open.doubleValue());
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[24]++;

                } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[14]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((close != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[15]++;
                    result = result + " Close=" 
                             + this.numberFormatter.format(close.doubleValue());
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[26]++;

                } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[16]++;}

            } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[8]++;}


        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[6]++;}

        return result;

    }

    /**
     * Generates a label for the specified item. The label is typically a 
     * formatted version of the data value, but any text can be used.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index (zero-based).
     * @param category  the category index (zero-based).
     *
     * @return The label (possibly <code>null</code>).
     */
    public String generateLabel(XYDataset dataset, int series, int category) {
        return null;  //TODO: implement this method properly
    }

    /**
     * Returns an independent copy of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[27]++;
        
        HighLowItemLabelGenerator clone 
            = (HighLowItemLabelGenerator) super.clone();
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.dateFormatter != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[17]++;
            clone.dateFormatter = (DateFormat) this.dateFormatter.clone();
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[29]++;

        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[18]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.numberFormatter != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[19]++;
            clone.numberFormatter = (NumberFormat) this.numberFormatter.clone();
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[31]++;

        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[20]++;}
        
        return clone;
        
    }
    
    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[21]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[22]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[33]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof HighLowItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[24]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[34]++;
        HighLowItemLabelGenerator generator = (HighLowItemLabelGenerator) obj;
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[35]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.dateFormatter.equals(generator.dateFormatter)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[26]++;}
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.statements[36]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.numberFormatter.equals(generator.numberFormatter)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[27]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d.branches[28]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.HighLowItemLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 14; i++) {
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

  public CodeCoverCoverageCounter$1a960zqtsicnfhoeqt2g0ai0t3p9kt71dxjbzqot370hm5d () {
    super("org.jfree.chart.labels.HighLowItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.HighLowItemLabelGenerator.java");
      for (int i = 1; i <= 36; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
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

