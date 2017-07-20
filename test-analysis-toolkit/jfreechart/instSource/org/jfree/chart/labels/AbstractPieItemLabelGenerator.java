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
 * ----------------------------------
 * AbstractPieItemLabelGenerator.java
 * ----------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 09-Nov-2004 : Version 1, draws out code from StandardPieItemLabelGenerator 
 *               and StandardPieToolTipGenerator (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-May-2006 : Fixed bug 1480978, a problem in the clone() method (DG);
 * 23-Nov-2007 : Implemented hashCode() (DG);
 * 
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.NumberFormat;

import org.jfree.chart.HashUtilities;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;

/**
 * A base class used for generating pie chart item labels.
 */
public class AbstractPieItemLabelGenerator implements Serializable {
  static {
    CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 7347703325267846275L;
  static {
    CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[1]++;
  }
    
    /** The label format string. */
    private String labelFormat;
    
    /** A number formatter for the value. */
    private NumberFormat numberFormat;
    
    /** A number formatter for the percentage. */
    private NumberFormat percentFormat;
    
    /**
     * Creates an item label generator using the specified number formatters.
     *
     * @param labelFormat  the label format string (<code>null</code> not
     *                     permitted).
     * @param numberFormat  the format object for the values (<code>null</code>
     *                      not permitted).
     * @param percentFormat  the format object for the percentages
     *                       (<code>null</code> not permitted).
     */
    protected AbstractPieItemLabelGenerator(String labelFormat,
                                            NumberFormat numberFormat, 
                                            NumberFormat percentFormat) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((labelFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[1]++;
            throw new IllegalArgumentException("Null 'labelFormat' argument.");

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[2]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((numberFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[3]++;
            throw new IllegalArgumentException("Null 'numberFormat' argument.");

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[4]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[4]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((percentFormat == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'percentFormat' argument.");
   
        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[6]++;}
        this.labelFormat = labelFormat;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[5]++;
        this.numberFormat = numberFormat;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[6]++;
        this.percentFormat = percentFormat;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[7]++;

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
     * @return The formatter (never <code>null</code>).
     */
    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    /**
     * Returns the percent formatter.
     *
     * @return The formatter (never <code>null</code>).
     */
    public NumberFormat getPercentFormat() {
        return this.percentFormat;
    }

    /**
     * Creates the array of items that can be passed to the 
     * {@link MessageFormat} class for creating labels.  The returned array
     * contains four values:
     * <ul>
     * <li>result[0] = the section key converted to a <code>String</code>;</li>
     * <li>result[1] = the formatted data value;</li>
     * <li>result[2] = the formatted percentage (of the total);</li>
     * <li>result[3] = the formatted total value.</li>
     * </ul>
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(PieDataset dataset, Comparable key) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[8]++;
        Object[] result = new Object[4];
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[9]++;
        double total = DatasetUtilities.calculatePieDatasetTotal(dataset);
        result[0] = key.toString();
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[10]++;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[11]++;
        Number value = dataset.getValue(key);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[7]++;
            result[1] = this.numberFormat.format(value);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[13]++;
  
        }
        else {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[8]++;
            result[1] = "null";
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[14]++;
        }
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[15]++;
        double percent = 0.0;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[9]++;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[17]++;
            double v = value.doubleValue();
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((v > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[11]++;
                percent = v / total;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[19]++;
 
            } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[10]++;}       
        result[2] = this.percentFormat.format(percent);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[20]++;
        result[3] = this.numberFormat.format(total);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[21]++;
        return result;
    }
    
    /**
     * Generates a label for a pie section.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the section key (<code>null</code> not permitted).
     * 
     * @return The label (possibly <code>null</code>).
     */
    protected String generateSectionLabel(PieDataset dataset, Comparable key) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[22]++;
        String result = null;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;    
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[13]++;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[24]++;
            Object[] items = createItemArray(dataset, key);
            result = MessageFormat.format(this.labelFormat, items);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[25]++;

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[14]++;}
        return result;
    }

    /**
     * Tests the generator for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[16]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractPieItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[18]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[28]++;
        
        AbstractPieItemLabelGenerator that 
                = (AbstractPieItemLabelGenerator) obj;
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.labelFormat.equals(that.labelFormat)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[20]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.numberFormat.equals(that.numberFormat)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[21]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[22]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[31]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.percentFormat.equals(that.percentFormat)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[23]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[24]++;}
        return true;

    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[32]++;
        int result = 127;
        result = HashUtilities.hashCode(result, this.labelFormat);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[33]++;
        result = HashUtilities.hashCode(result, this.numberFormat);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[34]++;
        result = HashUtilities.hashCode(result, this.percentFormat);
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[35]++;
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
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[36]++;      
        AbstractPieItemLabelGenerator clone 
                = (AbstractPieItemLabelGenerator) super.clone();
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.numberFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[25]++;
            clone.numberFormat = (NumberFormat) this.numberFormat.clone();
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[38]++;

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[26]++;}
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.percentFormat != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[27]++;
            clone.percentFormat = (NumberFormat) this.percentFormat.clone();
CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.statements[40]++;

        } else {
  CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9.branches[28]++;}
        return clone;
    }

}

class CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9 ());
  }
    public static long[] statements = new long[41];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.AbstractPieItemLabelGenerator.java";
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

  public CodeCoverCoverageCounter$2aeo38a9c1kjr4736gihglnnkr4rbscl05rg4pda0aykxd9foz1i9 () {
    super("org.jfree.chart.labels.AbstractPieItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 40; i++) {
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
    log.startNamedSection("org.jfree.chart.labels.AbstractPieItemLabelGenerator.java");
      for (int i = 1; i <= 40; i++) {
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

