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
 * ---------------------------------
 * AbstractXYItemLabelGenerator.java
 * ---------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Feb-2004 : Version 1 (DG);
 * 12-May-2004 : Moved default tool tip format to 
 *               StandardXYToolTipGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 08-Oct-2004 : Modified createItemArray() method to handle null values (DG);
 * 10-Jan-2005 : Updated createItemArray() to use x, y primitives if 
 *               possible (DG);
 * ------------- JFREECHART 1.0.x --------------------------------------------
 * 26-Jan-2006 : Minor API doc update (DG);
 * 25-Jan-2007 : Added new constructor and fixed bug in clone() method (DG);
 * 16-Oct-2007 : Removed redundant code (DG);
 * 23-Nov-2007 : Implemented hashCode() (DG);
 * 
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.jfree.chart.HashUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A base class for creating item label generators.
 */
public class AbstractXYItemLabelGenerator implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 5869744396278660636L;
  static {
    CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[1]++;
  }
    
    /** The item label format string. */
    private String formatString;
    
    /** A number formatter for the x value. */
    private NumberFormat xFormat;
    
    /** A date formatter for the x value. */
    private DateFormat xDateFormat;

    /** A formatter for the y value. */
    private NumberFormat yFormat;

    /** A date formatter for the y value. */
    private DateFormat yDateFormat;
    
    /** The string used to represent 'null' for the y-value. */
    private String nullYString = "null";
  {
    CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[2]++;
  }
    
    /**
     * Creates an item label generator using default number formatters.
     */
    protected AbstractXYItemLabelGenerator() {
        this("{2}", NumberFormat.getNumberInstance(), 
                NumberFormat.getNumberInstance());
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[3]++;
    }

    /**
     * Creates an item label generator using the specified number formatters.
     *
     * @param formatString  the item label format string (<code>null</code> 
     *                      not permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     */
    protected AbstractXYItemLabelGenerator(String formatString,
                                           NumberFormat xFormat, 
                                           NumberFormat yFormat) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((formatString == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[1]++;
            throw new IllegalArgumentException("Null 'formatString' argument.");

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[2]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[3]++;
            throw new IllegalArgumentException("Null 'xFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[4]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((yFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[5]++;
            throw new IllegalArgumentException("Null 'yFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[6]++;}
        this.formatString = formatString;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[7]++;
        this.xFormat = xFormat;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[8]++;
        this.yFormat = yFormat;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[9]++;

    }

    /**
     * Creates an item label generator using the specified number formatters.
     *
     * @param formatString  the item label format string (<code>null</code> 
     *                      not permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     */
    protected AbstractXYItemLabelGenerator(String formatString,
                                           DateFormat xFormat, 
                                           NumberFormat yFormat) {

        this(formatString, NumberFormat.getInstance(), yFormat);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[10]++;
        this.xDateFormat = xFormat;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[11]++;
    
    }
    
    /**
     * Creates an item label generator using the specified formatters (a 
     * number formatter for the x-values and a date formatter for the 
     * y-values).
     *
     * @param formatString  the item label format string (<code>null</code> 
     *                      not permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     *                 
     * @since 1.0.4
     */
    protected AbstractXYItemLabelGenerator(String formatString,
            NumberFormat xFormat, DateFormat yFormat) {
        
        this(formatString, xFormat, NumberFormat.getInstance());
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[12]++;
        this.yDateFormat = yFormat;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[13]++;
    }
    
    /**
     * Creates an item label generator using the specified number formatters.
     *
     * @param formatString  the item label format string (<code>null</code> 
     *                      not permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     */
    protected AbstractXYItemLabelGenerator(String formatString,
                                           DateFormat xFormat, 
                                           DateFormat yFormat) {

        this(formatString, NumberFormat.getInstance(), 
                NumberFormat.getInstance());
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[14]++;
        this.xDateFormat = xFormat;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[15]++;
        this.yDateFormat = yFormat;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[16]++;
    
    }
    
    /**
     * Returns the format string (this controls the overall structure of the 
     * label).
     * 
     * @return The format string (never <code>null</code>).
     */
    public String getFormatString() {
        return this.formatString;
    }
    
    /**
     * Returns the number formatter for the x-values.
     *
     * @return The number formatter (possibly <code>null</code>).
     */
    public NumberFormat getXFormat() {
        return this.xFormat;
    }

    /**
     * Returns the date formatter for the x-values.
     *
     * @return The date formatter (possibly <code>null</code>).
     */
    public DateFormat getXDateFormat() {
        return this.xDateFormat;
    }

    /**
     * Returns the number formatter for the y-values.
     *
     * @return The number formatter (possibly <code>null</code>).
     */
    public NumberFormat getYFormat() {
        return this.yFormat;
    }

    /**
     * Returns the date formatter for the y-values.
     *
     * @return The date formatter (possibly <code>null</code>).
     */
    public DateFormat getYDateFormat() {
        return this.yDateFormat;
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
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[17]++;
        String result = null;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[18]++;    
        Object[] items = createItemArray(dataset, series, item);
        result = MessageFormat.format(this.formatString, items);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[19]++;
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
     * @return An array of three items from the dataset formatted as
     *         <code>String</code> objects (never <code>null</code>).
     */
    protected Object[] createItemArray(XYDataset dataset, int series, 
                                       int item) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[20]++;
        Object[] result = new Object[3];
        result[0] = dataset.getSeriesKey(series).toString();
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[21]++;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[22]++;
        
        double x = dataset.getXValue(series, item);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.xDateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[7]++;
            result[1] = this.xDateFormat.format(new Date((long) x));
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[24]++;
   
        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[8]++;
            result[1] = this.xFormat.format(x);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[25]++;
        }
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[26]++;
        
        double y = dataset.getYValue(series, item);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((Double.isNaN(y)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((dataset.getY(series, item) == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[9]++;
            result[2] = this.nullYString;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[28]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[10]++;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.yDateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[11]++;
                result[2] = this.yDateFormat.format(new Date((long) y));
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[30]++;
   
            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[12]++;
                result[2] = this.yFormat.format(y);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[31]++;
            }
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
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[14]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractXYItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[16]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[34]++;
        AbstractXYItemLabelGenerator that = (AbstractXYItemLabelGenerator) obj;
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.formatString.equals(that.formatString)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[17]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[18]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.xFormat, that.xFormat)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[19]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[20]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.xDateFormat, that.xDateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[21]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[22]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.yFormat, that.yFormat)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[23]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[24]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[39]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.yDateFormat, that.yDateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[25]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[26]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[40]++;
        int result = 127;
        result = HashUtilities.hashCode(result, this.formatString);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[41]++;
        result = HashUtilities.hashCode(result, this.xFormat);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[42]++;
        result = HashUtilities.hashCode(result, this.xDateFormat);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[43]++;
        result = HashUtilities.hashCode(result, this.yFormat);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[44]++;
        result = HashUtilities.hashCode(result, this.yDateFormat);
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[45]++;
        return result;
    }
    
    /**
     * Returns an independent copy of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[46]++;
        AbstractXYItemLabelGenerator clone 
                = (AbstractXYItemLabelGenerator) super.clone();
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[47]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.xFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[27]++;
            clone.xFormat = (NumberFormat) this.xFormat.clone();
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[48]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[28]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[49]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.yFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[29]++;
            clone.yFormat = (NumberFormat) this.yFormat.clone();
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[50]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[30]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[51]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.xDateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[31]++;
            clone.xDateFormat = (DateFormat) this.xDateFormat.clone();
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[52]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[32]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[53]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.yDateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[33]++;
            clone.yDateFormat = (DateFormat) this.yDateFormat.clone();
CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.statements[54]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh.branches[34]++;}        
        return clone;
    }
    
}

class CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh ());
  }
    public static long[] statements = new long[55];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.AbstractXYItemLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$bl6rgcoxr7ya0e06uwrhwlgum11kzx23lcw4y4rxjjmyyhf7qsh () {
    super("org.jfree.chart.labels.AbstractXYItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 54; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.AbstractXYItemLabelGenerator.java");
      for (int i = 1; i <= 54; i++) {
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
    for (int i = 1; i <= 17; i++) {
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

