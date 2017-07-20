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
 * ---------------------------------------
 * AbstractCategoryItemLabelGenerator.java
 * ---------------------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-May-2004 : Version 1, distilled from StandardCategoryLabelGenerator (DG);
 * 31-Jan-2005 : Added methods to return row and column labels (DG);
 * 17-May-2005 : Added percentage to item array (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-May-2006 : Added new constructor (DG);
 * 23-Nov-2007 : Implemented hashCode() (DG);
 * 
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

import org.jfree.chart.HashUtilities;
import org.jfree.data.DataUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A base class that can be used to create a label or tooltip generator that 
 * can be assigned to a 
 * {@link org.jfree.chart.renderer.category.CategoryItemRenderer}.
 */
public abstract class AbstractCategoryItemLabelGenerator 
                implements PublicCloneable, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7108591260223293197L;
  static {
    CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[1]++;
  }
    
    /** 
     * The label format string used by a <code>MessageFormat</code> object to 
     * combine the standard items:  {0} = series name, {1} = category, 
     * {2} = value, {3} = value as a percentage of the column total.
     */
    private String labelFormat;
    
    /** The string used to represent a null value. */
    private String nullValueString;
    
    /** 
     * A number formatter used to preformat the value before it is passed to 
     * the MessageFormat object. 
     */
    private NumberFormat numberFormat;

    /**
     * A date formatter used to preformat the value before it is passed to the
     * MessageFormat object.
     */ 
    private DateFormat dateFormat;
    
    /**
     * A number formatter used to preformat the percentage value before it is 
     * passed to the MessageFormat object.
     */ 
    private NumberFormat percentFormat;
    
    /**
     * Creates a label generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     */
    protected AbstractCategoryItemLabelGenerator(String labelFormat, 
                                                 NumberFormat formatter) {
        this(labelFormat, formatter, NumberFormat.getPercentInstance());
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[2]++;
    }

    /**
     * Creates a label generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     * @param percentFormatter  the percent formatter (<code>null</code> not
     *     permitted).
     *     
     * @since 1.0.2
     */
    protected AbstractCategoryItemLabelGenerator(String labelFormat, 
            NumberFormat formatter, NumberFormat percentFormatter) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((labelFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[1]++;
            throw new IllegalArgumentException("Null 'labelFormat' argument.");

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[2]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[3]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[4]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((percentFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'percentFormatter' argument.");

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[6]++;}
        this.labelFormat = labelFormat;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[6]++;
        this.numberFormat = formatter;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[7]++;
        this.percentFormat = percentFormatter;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[8]++;
        this.dateFormat = null;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[9]++;
        this.nullValueString = "-";
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[10]++;
    }

    /**
     * Creates a label generator with the specified date formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the date formatter (<code>null</code> not permitted).
     */
    protected AbstractCategoryItemLabelGenerator(String labelFormat, 
                                                 DateFormat formatter) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((labelFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[7]++;
            throw new IllegalArgumentException("Null 'labelFormat' argument.");

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[8]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[9]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[10]++;}
        this.labelFormat = labelFormat;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[13]++;
        this.numberFormat = null;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[14]++;
        this.percentFormat = NumberFormat.getPercentInstance();
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[15]++;
        this.dateFormat = formatter;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[16]++;
        this.nullValueString = "-";
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[17]++;
    }
    
    /**
     * Generates a label for the specified row.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * 
     * @return The label.
     */
    public String generateRowLabel(CategoryDataset dataset, int row) {
        return dataset.getRowKey(row).toString();   
    }
    
    /**
     * Generates a label for the specified row.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param column  the column index (zero-based).
     * 
     * @return The label.
     */
    public String generateColumnLabel(CategoryDataset dataset, int column) {
        return dataset.getColumnKey(column).toString();   
    }

    /**
     * Returns the label format string.
     * 
     * @return The label format string (never <code>null</code>).
     */
    public String getLabelFormat() {
        return this.labelFormat;   
    }
    
    /**
     * Returns the number formatter.
     *
     * @return The number formatter (possibly <code>null</code>).
     */
    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    /**
     * Returns the date formatter.
     *
     * @return The date formatter (possibly <code>null</code>).
     */
    public DateFormat getDateFormat() {
        return this.dateFormat;
    }

    /**
     * Generates a for the specified item.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The label (possibly <code>null</code>).
     */
    protected String generateLabelString(CategoryDataset dataset, 
                                         int row, int column) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[11]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[12]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[19]++;
        String result = null;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[20]++;   
        Object[] items = createItemArray(dataset, row, column);
        result = MessageFormat.format(this.labelFormat, items);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[21]++;
        return result;

    }

    /**
     * Creates the array of items that can be passed to the 
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(CategoryDataset dataset, 
                                       int row, int column) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[22]++;
        Object[] result = new Object[4];
        result[0] = dataset.getRowKey(row).toString();
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[23]++;
        result[1] = dataset.getColumnKey(column).toString();
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[24]++;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[25]++;
        Number value = dataset.getValue(row, column);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[13]++;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.numberFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[15]++;
                result[2] = this.numberFormat.format(value);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[28]++;
  
            }
            else {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[16]++;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[29]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.dateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[17]++;
                result[2] = this.dateFormat.format(value);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[30]++;

            } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[18]++;}
}

        }
        else {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[14]++;
            result[2] = this.nullValueString;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[31]++;   
        }
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[19]++;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[33]++;
            double total = DataUtilities.calculateColumnTotal(dataset, column);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[34]++;
            double percent = value.doubleValue() / total;
            result[3] = this.percentFormat.format(percent);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[35]++;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[20]++;}
       
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
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[21]++;
            return true;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[22]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractCategoryItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[24]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[38]++;
        
        AbstractCategoryItemLabelGenerator that 
            = (AbstractCategoryItemLabelGenerator) obj;
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[39]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.labelFormat.equals(that.labelFormat)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[26]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[40]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.dateFormat, that.dateFormat)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[28]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[41]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.numberFormat, that.numberFormat)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[30]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[42]++;
        int result = 127;
        result = HashUtilities.hashCode(result, this.labelFormat);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[43]++;
        result = HashUtilities.hashCode(result, this.nullValueString);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[44]++;
        result = HashUtilities.hashCode(result, this.dateFormat);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[45]++;
        result = HashUtilities.hashCode(result, this.numberFormat);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[46]++;
        result = HashUtilities.hashCode(result, this.percentFormat);
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[47]++;
        return result;
    }
    
    /**
     * Returns an independent copy of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  should not happen.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[48]++;
        AbstractCategoryItemLabelGenerator clone 
            = (AbstractCategoryItemLabelGenerator) super.clone();
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[49]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.numberFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[31]++;
            clone.numberFormat = (NumberFormat) this.numberFormat.clone();
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[50]++;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[32]++;}
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[51]++;
int CodeCoverConditionCoverageHelper_C17; 
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.dateFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[33]++;
            clone.dateFormat = (DateFormat) this.dateFormat.clone();
CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.statements[52]++;

        } else {
  CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp.branches[34]++;} 
        return clone;
    }

}

class CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp ());
  }
    public static long[] statements = new long[53];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.AbstractCategoryItemLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$w48qb4ywxez3z2uer77twk6d5u8xjq30c14o20fpa2a4lumlhjye3zbccjmp () {
    super("org.jfree.chart.labels.AbstractCategoryItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 52; i++) {
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
    log.startNamedSection("org.jfree.chart.labels.AbstractCategoryItemLabelGenerator.java");
      for (int i = 1; i <= 52; i++) {
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

