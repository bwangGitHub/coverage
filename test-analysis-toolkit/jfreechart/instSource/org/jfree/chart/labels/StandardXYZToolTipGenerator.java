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
 * --------------------------------
 * StandardXYZToolTipGenerator.java
 * --------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-May-2003 : Version 1, split from StandardXYZItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getZ() and getZValue() methods (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A standard item label generator for use with {@link XYZDataset} data.  Each 
 * value can be formatted as a number or as a date.
 */
public class StandardXYZToolTipGenerator extends StandardXYToolTipGenerator
                                         implements XYZToolTipGenerator,
                                                    Serializable {
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2961577421889473503L;
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[1]++;
  }
    
    /** The default tooltip format. */
    public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2}, {3})";
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[2]++;
  }

    /** 
     * A number formatter for the z value - if this is null, then zDateFormat 
     * must be non-null. 
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
    public StandardXYZToolTipGenerator() {
        this(
            DEFAULT_TOOL_TIP_FORMAT,
            NumberFormat.getNumberInstance(),
            NumberFormat.getNumberInstance(),
            NumberFormat.getNumberInstance()
        );
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[3]++;
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
    public StandardXYZToolTipGenerator(String formatString,
                                       NumberFormat xFormat,
                                       NumberFormat yFormat,
                                       NumberFormat zFormat) {
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[4]++;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[1]++;
            throw new IllegalArgumentException("Null 'zFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[2]++;}
        this.zFormat = zFormat;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[6]++;
    }

    /**
     * Constructs a new tool tip generator using the specified date formatters.
     *
     * @param formatString  the format string.
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     * @param zFormat  the format object for the z values (<code>null</code> 
     *                 not permitted).
     */
    public StandardXYZToolTipGenerator(String formatString,
                                       DateFormat xFormat,
                                       DateFormat yFormat,
                                       DateFormat zFormat) {
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[7]++;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((zFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[3]++;
            throw new IllegalArgumentException("Null 'zFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[4]++;}
        this.zDateFormat = zFormat;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[9]++;
    }

    // TODO:  add constructors for combinations of number and date formatters.
    
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
     * Generates a tool tip text item for a particular item within a series.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The tooltip text (possibly <code>null</code>).
     */
    public String generateToolTip(XYZDataset dataset, int series, int item) {
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
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[10]++;
        String result = null;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[11]++;    
        Object[] items = createItemArray((XYZDataset) dataset, series, item);
        result = MessageFormat.format(getFormatString(), items);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[12]++;
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
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[13]++;

        Object[] result = new Object[4];
        result[0] = dataset.getSeriesKey(series).toString();
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[14]++;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[15]++;
        
        Number x = dataset.getX(series, item);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[16]++;
        DateFormat xf = getXDateFormat();
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((xf != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[5]++;
            result[1] = xf.format(x);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[18]++;
   
        }
        else {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[6]++;
            result[1] = getXFormat().format(x);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[19]++;
        }
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[20]++;
        
        Number y = dataset.getY(series, item);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[21]++;
        DateFormat yf = getYDateFormat();
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((yf != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[7]++;
            result[2] = yf.format(y);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[8]++;
            result[2] = getYFormat().format(y);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[24]++;
        }
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[25]++;
        
        Number z = dataset.getZ(series, item);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.zDateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[9]++;
            result[3] = this.zDateFormat.format(z);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[27]++;
   
        }
        else {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[10]++;
            result[3] = this.zFormat.format(z);
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[28]++;   
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
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[12]++;}
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof StandardXYZToolTipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[14]++;}
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[16]++;}
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[32]++;
        StandardXYZToolTipGenerator that = (StandardXYZToolTipGenerator) obj;
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.zFormat, that.zFormat)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[18]++;}
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.zDateFormat, that.zDateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l.branches[20]++;}
        return true;

    }

}

class CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.StandardXYZToolTipGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$22vp8kmedfvmiavcuba97cvb0mwm5opj03n25xi6fjuk1txh7l () {
    super("org.jfree.chart.labels.StandardXYZToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.StandardXYZToolTipGenerator.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

