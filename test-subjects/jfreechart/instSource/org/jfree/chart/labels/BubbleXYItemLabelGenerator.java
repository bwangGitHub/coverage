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
 * -------------------------------
 * BubbleXYItemLabelGenerator.java
 * -------------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 13-Dec-2005 : Version 1, based on StandardXYZToolTipGenerator (DG);
 * 26-Jan-2006 : Renamed StandardXYZItemLabelGenerator 
 *               --> BubbleXYItemLabelGenerator (DG);
 * 23-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.ObjectUtilities;

/**
 * An item label generator defined for use with the {@link XYBubbleRenderer}
 * class, or any other class that uses an {@link XYZDataset}.
 * 
 * @since 1.0.1
 */
public class BubbleXYItemLabelGenerator extends AbstractXYItemLabelGenerator
        implements XYItemLabelGenerator, Serializable {
  static {
    CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = -8458568928021240922L;
  static {
    CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[1]++;
  }

    /** The default item label format. */
    public static final String DEFAULT_FORMAT_STRING = "{3}";
  static {
    CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[2]++;
  }

    /** 
     * A number formatter for the z value - if this is <code>null</code>, then 
     * zDateFormat must be non-null. 
     */
    private NumberFormat zFormat;
    
    /** 
     * A date formatter for the z-value - if this is null, then zFormat must be 
     * non-null. 
     */
    private DateFormat zDateFormat;

    /**
     * Creates a new tool tip generator using default number formatters for the
     * x, y and z-values.
     */
    public BubbleXYItemLabelGenerator() {
        this(DEFAULT_FORMAT_STRING, NumberFormat.getNumberInstance(),
                NumberFormat.getNumberInstance(), 
                NumberFormat.getNumberInstance());
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[3]++;
    }

    /**
     * Constructs a new tool tip generator using the specified number 
     * formatters.
     *
     * @param formatString  the format string.
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     * @param zFormat  the format object for the z values (<code>null</code> 
     *                 not permitted).
     */
    public BubbleXYItemLabelGenerator(String formatString, 
            NumberFormat xFormat, NumberFormat yFormat, NumberFormat zFormat) {
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[4]++;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[1]++;
            throw new IllegalArgumentException("Null 'zFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[2]++;}
        this.zFormat = zFormat;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[6]++;
    }

    /**
     * Constructs a new item label generator using the specified date 
     * formatters.
     *
     * @param formatString  the format string.
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     * @param zFormat  the format object for the z values (<code>null</code> 
     *                 not permitted).
     */
    public BubbleXYItemLabelGenerator(String formatString, 
            DateFormat xFormat, DateFormat yFormat, DateFormat zFormat) {
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[7]++;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((zFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[3]++;
            throw new IllegalArgumentException("Null 'zFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[4]++;}
        this.zDateFormat = zFormat;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[9]++;
    }
    
    /**
     * Returns the number formatter for the z-values.
     *
     * @return The number formatter (possibly <code>null</code>).
     */
    public NumberFormat getZFormat() {
        return this.zFormat;
    }
    
    /**
     * Returns the date formatter for the z-values.
     *
     * @return The date formatter (possibly <code>null</code>).
     */
    public DateFormat getZDateFormat() {
        return this.zDateFormat;   
    }

    /**
     * Generates an item label for a particular item within a series.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The item label (possibly <code>null</code>).
     */
    public String generateLabel(XYDataset dataset, int series, int item) {
        return generateLabelString(dataset, series, item);
    }
    
    /**
     * Generates a label string for an item in the dataset.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The label (possibly <code>null</code>).
     */
    public String generateLabelString(XYDataset dataset, int series, int item) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[10]++;
        String result = null;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[11]++;    
        Object[] items = null;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof XYZDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[5]++;
            items = createItemArray((XYZDataset) dataset, series, item);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[13]++;

        }
        else {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[6]++;
            items = createItemArray(dataset, series, item);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[14]++;
        }
        result = MessageFormat.format(getFormatString(), items);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[15]++;
        return result;
    }

    /**
     * Creates the array of items that can be passed to the 
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(XYZDataset dataset, 
                                       int series, int item) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[16]++;

        Object[] result = new Object[4];
        result[0] = dataset.getSeriesKey(series).toString();
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[17]++;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[18]++;
 
        Number x = dataset.getX(series, item);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[19]++;
        DateFormat xf = getXDateFormat();
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((xf != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[7]++;
            result[1] = xf.format(x);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[21]++;
   
        }
        else {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[8]++;
            result[1] = getXFormat().format(x);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[22]++;
        }
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[23]++;
        
        Number y = dataset.getY(series, item);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[24]++;
        DateFormat yf = getYDateFormat();
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((yf != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[9]++;
            result[2] = yf.format(y);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[26]++;

        }
        else {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[10]++;
            result[2] = getYFormat().format(y);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[27]++;
        }
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[28]++;
        
        Number z = dataset.getZ(series, item);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.zDateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[11]++;
            result[3] = this.zDateFormat.format(z);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[30]++;
   
        }
        else {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[12]++;
            result[3] = this.zFormat.format(z);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[31]++;   
        }
        
        return result;
        
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[14]++;}
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof BubbleXYItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[16]++;}
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[18]++;}
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[35]++;
        BubbleXYItemLabelGenerator that = (BubbleXYItemLabelGenerator) obj;
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.zFormat, that.zFormat)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[20]++;}
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.zDateFormat, that.zDateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.branches[22]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[38]++;
        int h = super.hashCode();
        h = HashUtilities.hashCode(h, this.zFormat);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[39]++;
        h = HashUtilities.hashCode(h, this.zDateFormat);
CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd.statements[40]++;
        return h;
    }

}

class CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd ());
  }
    public static long[] statements = new long[41];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.BubbleXYItemLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$8dvorz4dp5booatzoyaz5d84zzr58kubvn57ntwdzg4wpbhd () {
    super("org.jfree.chart.labels.BubbleXYItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 40; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.BubbleXYItemLabelGenerator.java");
      for (int i = 1; i <= 40; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

