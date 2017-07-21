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
 * -----------------------------------
 * MultipleXYSeriesLabelGenerator.java
 * -----------------------------------
 * (C) Copyright 2004, 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Nov-2004 : Version 1 (DG);
 * 18-Apr-2005 : Use StringBuffer (DG);
 * 20-Feb-2007 : Fixed for equals() and cloning() (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 * A series label generator for plots that use data from 
 * an {@link org.jfree.data.xy.XYDataset}.
 */
public class MultipleXYSeriesLabelGenerator implements XYSeriesLabelGenerator, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 138976236941898560L;
  static {
    CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[1]++;
  }
    
    /** The default item label format. */
    public static final String DEFAULT_LABEL_FORMAT = "{0}";
  static {
    CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[2]++;
  }
    
    /** The format pattern for the initial part of the label. */
    private String formatPattern;
    
    /** The format pattern for additional labels. */
    private String additionalFormatPattern;
    
    /** Storage for the additional series labels. */
    private Map seriesLabelLists;

    /**
     * Creates an item label generator using default number formatters.
     */
    public MultipleXYSeriesLabelGenerator() {
        this(DEFAULT_LABEL_FORMAT);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[3]++;
    }
    
    /**
     * Creates a new series label generator.
     * 
     * @param format  the format pattern (<code>null</code> not permitted).
     */
    public MultipleXYSeriesLabelGenerator(String format) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((format == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[1]++;
            throw new IllegalArgumentException("Null 'format' argument.");

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[2]++;}
        this.formatPattern = format;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[5]++;
        this.additionalFormatPattern = "\n{0}";
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[6]++;
        this.seriesLabelLists = new HashMap();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[7]++;
    }
    
    /**
     * Adds an extra label for the specified series.
     * 
     * @param series  the series index.
     * @param label  the label.
     */
    public void addSeriesLabel(int series, String label) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[8]++;
        Integer key = new Integer(series);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[9]++;
        List labelList = (List) this.seriesLabelLists.get(key);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((labelList == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[3]++;
            labelList = new java.util.ArrayList();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[11]++;
            this.seriesLabelLists.put(key, labelList);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[12]++;

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[4]++;}
        labelList.add(label);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[13]++;
    }
    
    /**
     * Clears the extra labels for the specified series.
     * 
     * @param series  the series index.
     */
    public void clearSeriesLabels(int series) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[14]++;
        Integer key = new Integer(series);
        this.seriesLabelLists.put(key, null);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[15]++;       
    }

    /**
     * Generates a label for the specified series.  This label will be
     * used for the chart legend.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series.
     * 
     * @return A series label.
     */
    public String generateLabel(XYDataset dataset, int series) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[5]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[6]++;}
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[17]++;
        StringBuffer label = new StringBuffer();
        label.append(MessageFormat.format(this.formatPattern, 
                createItemArray(dataset, series)));
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[18]++;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[19]++;
        Integer key = new Integer(series);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[20]++;
        List extraLabels = (List) this.seriesLabelLists.get(key);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((extraLabels != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[7]++;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[22]++;
            Object[] temp = new Object[1];
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < extraLabels.size()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[1]--;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[2]--;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[3]++;
}
                temp[0] = extraLabels.get(i);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[24]++;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[25]++;
                String labelAddition = MessageFormat.format(
                        this.additionalFormatPattern, temp);
                label.append(labelAddition);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[26]++;
            }

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[8]++;}
        return label.toString();
    }

    /**
     * Creates the array of items that can be passed to the 
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series (zero-based index).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(XYDataset dataset, int series) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[27]++;
        Object[] result = new Object[1];
        result[0] = dataset.getSeriesKey(series).toString();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[28]++;
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
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[29]++; 
        MultipleXYSeriesLabelGenerator clone 
                = (MultipleXYSeriesLabelGenerator) super.clone();
        clone.seriesLabelLists = new HashMap();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[30]++;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[31]++;
        Set keys = this.seriesLabelLists.keySet();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[32]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[4]--;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[5]--;
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.loops[6]++;
}
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[34]++;
            Object key = iterator.next();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[35]++;
            Object entry = this.seriesLabelLists.get(key);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[36]++;
            Object toAdd = entry;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((entry instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[9]++;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[38]++;
                PublicCloneable pc = (PublicCloneable) entry;
                toAdd = pc.clone();
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[39]++;

            } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[10]++;}
            clone.seriesLabelLists.put(key, toAdd);
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[40]++;
        }
        return clone;
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[12]++;}
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof MultipleXYSeriesLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[14]++;}
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[43]++;
        MultipleXYSeriesLabelGenerator that 
                = (MultipleXYSeriesLabelGenerator) obj;
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.formatPattern.equals(that.formatPattern)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[16]++;}
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.additionalFormatPattern.equals(
                that.additionalFormatPattern)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[18]++;}
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.seriesLabelLists.equals(that.seriesLabelLists)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29.branches[20]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29 ());
  }
    public static long[] statements = new long[47];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.MultipleXYSeriesLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$ja80thpfjt3iwpqb0ht76w4agg1y2k12l38htcusppkpoa2y07wx29 () {
    super("org.jfree.chart.labels.MultipleXYSeriesLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 46; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.MultipleXYSeriesLabelGenerator.java");
      for (int i = 1; i <= 46; i++) {
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
    for (int i = 1; i <= 12; i++) {
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

