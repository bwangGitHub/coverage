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
 * -------------------------------------
 * DefaultMultiValueCategoryDataset.java
 * -------------------------------------
 * (C) Copyright 2007, by David Forslund and Contributors.
 *
 * Original Author:  David Forslund;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);;
 *
 * Changes
 * -------
 * 08-Oct-2007 : Version 1, see patch 1780779 (DG);
 * 06-Nov-2007 : Return EMPTY_LIST not null from getValues() (DG);
 */

package org.jfree.data.statistics;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.KeyedObjects2D;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.PublicCloneable;

/**
 * A category dataset that defines multiple values for each item.
 * 
 * @since 1.0.7
 */
public class DefaultMultiValueCategoryDataset extends AbstractDataset 
        implements MultiValueCategoryDataset, RangeInfo, PublicCloneable {
  static {
    CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.ping();
  }


    /**
     * Storage for the data.
     */
    protected KeyedObjects2D data;
    
    /**
     * The minimum range value.
     */
    private Number minimumRangeValue;

    /**
     * The maximum range value.
     */
    private Number maximumRangeValue;

    /**
     * The range of values.
     */
    private Range rangeBounds;

    /**
     * Creates a new dataset.
     */
    public DefaultMultiValueCategoryDataset() {
        this.data = new KeyedObjects2D();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[1]++;
        this.minimumRangeValue = null;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[2]++;
        this.maximumRangeValue = null;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[3]++;
        this.rangeBounds = new Range(0.0, 0.0);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[4]++;
    }

    /**
     * Adds a list of values to the dataset (<code>null</code> and Double.NaN 
     * items are automatically removed) and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param values  a list of values (<code>null</code> not permitted).
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     */
    public void add(List values, Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[1]++;
            throw new IllegalArgumentException("Null 'values' argument.");

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[2]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rowKey == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[3]++;
            throw new IllegalArgumentException("Null 'rowKey' argument.");

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[4]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[5]++;
            throw new IllegalArgumentException("Null 'columnKey' argument.");

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[6]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[8]++;
        List vlist = new ArrayList(values.size());
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[9]++;
        Iterator iterator = values.listIterator();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[1]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[2]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[3]++;
}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[11]++;
            Object obj = iterator.next();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[7]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[13]++;
                Number n = (Number) obj;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[14]++;
                double v = n.doubleValue();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Double.isNaN(v)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[9]++;
                    vlist.add(n);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[16]++;

                } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[10]++;}

            } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[8]++;}
        }
        Collections.sort(vlist);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[17]++;
        this.data.addObject(vlist, rowKey, columnKey);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[18]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((vlist.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[11]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[20]++;
            double maxval = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[21]++;
            double minval = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < vlist.size()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[4]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[5]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[6]++;
}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[23]++;
                Number n = (Number) vlist.get(i);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[24]++;
                double v = n.doubleValue();   
                minval = Math.min(minval, v);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[25]++;
                maxval = Math.max(maxval, v);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[26]++;
            }
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        
            // update the cached range values...
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.maximumRangeValue == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[13]++;
                this.maximumRangeValue = new Double(maxval);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[28]++;

            } 
            else {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[14]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[29]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((maxval > this.maximumRangeValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[15]++;
                this.maximumRangeValue = new Double(maxval);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[30]++;

            } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[16]++;}
}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;

            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.minimumRangeValue == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[17]++;
                this.minimumRangeValue = new Double(minval);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[32]++;

            } 
            else {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[18]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[33]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((minval < this.minimumRangeValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[19]++;
                this.minimumRangeValue = new Double(minval);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[34]++;

            } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[20]++;}
}
            this.rangeBounds = new Range(this.minimumRangeValue.doubleValue(),
                    this.maximumRangeValue.doubleValue());
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[35]++;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[12]++;}

        fireDatasetChanged();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[36]++;
    }

    /**
     * Returns a list (possibly empty) of the values for the specified item.
     * The returned list should be unmodifiable.
     * 
     * @param row  the row index (zero-based).
     * @param column   the column index (zero-based).
     * 
     * @return The list of values.
     */
    public List getValues(int row, int column) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[37]++;
        List values = (List) this.data.getObject(row, column);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[38]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((values != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[21]++;
            return Collections.unmodifiableList(values);

        }
        else {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[22]++;
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Returns a list (possibly empty) of the values for the specified item.
     * The returned list should be unmodifiable.
     * 
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     *
     * @return The list of values.
     */
    public List getValues(Comparable rowKey, Comparable columnKey) {
        return Collections.unmodifiableList((List) this.data.getObject(rowKey, 
                columnKey));
    }

    /**
     * Returns the average value for the specified item.
     *
     * @param row  the row key.
     * @param column  the column key.
     * 
     * @return The average value.
     */
    public Number getValue(Comparable row, Comparable column) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[39]++;
        List l = (List) this.data.getObject(row, column);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[40]++;
        double average = 0.0d;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[41]++;
        int count = 0;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[42]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((l != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((l.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[23]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[43]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[7]++;


int CodeCoverConditionCoverageHelper_C15;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < l.size()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[7]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[8]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[9]++;
}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[44]++;
                Number n = (Number) l.get(i);
                average += n.doubleValue();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[45]++;
                count += 1;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[46]++;
            }
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[47]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[25]++;
                average = average / count;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[48]++;

            } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[24]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[49]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[27]++;
            return null;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[28]++;}
        return new Double(average);
    }

    /**
     * Returns the average value for the specified item.
     *
     * @param row  the row index.
     * @param column  the column index.
     * 
     * @return The average value.
     */
    public Number getValue(int row, int column) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[50]++;
        List l = (List) this.data.getObject(row, column);
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[51]++;
        double average = 0.0d;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[52]++;
        int count = 0;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[53]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((l != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((l.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[29]++;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[54]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < l.size()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[10]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[11]--;
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.loops[12]++;
}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[55]++;
                Number n = (Number) l.get(i);
                average += n.doubleValue();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[56]++;
                count += 1;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[57]++;
            }
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[58]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[31]++;
                average = average / count;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[59]++;

            } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[30]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[60]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[33]++;
            return null;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[34]++;}
        return new Double(average);
    }

    /**
     * Returns the column index for a given key.
     *
     * @param key  the column key.
     * 
     * @return The column index.
     */
    public int getColumnIndex(Comparable key) {
        return this.data.getColumnIndex(key);
    }

    /**
     * Returns a column key.
     *
     * @param column the column index (zero-based).
     * 
     * @return The column key.
     */
    public Comparable getColumnKey(int column) {
        return this.data.getColumnKey(column);
    }

    /**
     * Returns the column keys.
     *
     * @return The keys.
     */
    public List getColumnKeys() {
        return this.data.getColumnKeys();
    }

    /**
     * Returns the row index for a given key.
     *
     * @param key the row key.
     * 
     * @return The row index.
     */
    public int getRowIndex(Comparable key) {
        return this.data.getRowIndex(key);
    }

    /**
     * Returns a row key.
     *
     * @param row the row index (zero-based).
     * 
     * @return The row key.
     */
    public Comparable getRowKey(int row) {
        return this.data.getRowKey(row);
    }

    /**
     * Returns the row keys.
     *
     * @return The keys.
     */
    public List getRowKeys() {
        return this.data.getRowKeys();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return The row count.
     */
    public int getRowCount() {
        return this.data.getRowCount();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return The column count.
     */
    public int getColumnCount() {
        return this.data.getColumnCount();
    }

    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval a flag that determines whether or not the
     *                        y-interval is taken into account.
     *                        
     * @return The minimum value.
     */
    public double getRangeLowerBound(boolean includeInterval) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[61]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[62]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.minimumRangeValue != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[35]++;
            result = this.minimumRangeValue.doubleValue();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[63]++;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[36]++;}
        return result;
    }

    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval a flag that determines whether or not the
     *                        y-interval is taken into account.
     *                        
     * @return The maximum value.
     */
    public double getRangeUpperBound(boolean includeInterval) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[64]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[65]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.maximumRangeValue != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[37]++;
            result = this.maximumRangeValue.doubleValue();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[66]++;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[38]++;}
        return result;
    }

    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param includeInterval a flag that determines whether or not the
     *                        y-interval is taken into account.
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval) {
        return this.rangeBounds;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[67]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[39]++;
            return true;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[40]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[68]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultMultiValueCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.branches[42]++;}
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[69]++;
        DefaultMultiValueCategoryDataset that 
                = (DefaultMultiValueCategoryDataset) obj;
        return this.data.equals(that.data);
    }
    
    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the dataset cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[70]++;
        DefaultMultiValueCategoryDataset clone 
                = (DefaultMultiValueCategoryDataset) super.clone();
        clone.data = (KeyedObjects2D) this.data.clone();
CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp.statements[71]++;
        return clone;
    }
}

class CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp ());
  }
    public static long[] statements = new long[72];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.DefaultMultiValueCategoryDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$nx23faqrqtqeq384c4knq0iqhxjfpfwr5bb18psv348ws96efkdp83nmp () {
    super("org.jfree.data.statistics.DefaultMultiValueCategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 71; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.DefaultMultiValueCategoryDataset.java");
      for (int i = 1; i <= 71; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

