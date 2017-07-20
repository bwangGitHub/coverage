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
 * DefaultIntervalCategoryDataset.java
 * -----------------------------------
 * (C) Copyright 2002-2007, by Jeremy Bowman and Contributors.
 *
 * Original Author:  Jeremy Bowman;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 29-Apr-2002 : Version 1, contributed by Jeremy Bowman (DG);
 * 24-Oct-2002 : Amendments for changes made to the dataset interface (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 08-Mar-2007 : Added equals() and clone() overrides (DG);
 *
 */

package org.jfree.data.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.jfree.data.DataUtilities;
import org.jfree.data.UnknownKeyException;
import org.jfree.data.general.AbstractSeriesDataset;

/**
 * A convenience class that provides a default implementation of the
 * {@link IntervalCategoryDataset} interface.
 * <p>
 * The standard constructor accepts data in a two dimensional array where the
 * first dimension is the series, and the second dimension is the category.
 */
public class DefaultIntervalCategoryDataset extends AbstractSeriesDataset
                                            implements IntervalCategoryDataset {
  static {
    CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.ping();
  }


    /** The series keys. */
    private Comparable[] seriesKeys;

    /** The category keys. */
    private Comparable[] categoryKeys;

    /** Storage for the start value data. */
    private Number[][] startData;

    /** Storage for the end value data. */
    private Number[][] endData;

    /**
     * Creates a new dataset.
     *
     * @param starts  the starting values for the intervals.
     * @param ends  the ending values for the intervals.
     */
    public DefaultIntervalCategoryDataset(double[][] starts, double[][] ends) {
        this(DataUtilities.createNumberArray2D(starts),
                DataUtilities.createNumberArray2D(ends));
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[1]++;
    }

    /**
     * Constructs a dataset and populates it with data from the array.
     * <p>
     * The arrays are indexed as data[series][category].  Series and category
     * names are automatically generated - you can change them using the
     * {@link #setSeriesKeys(Comparable[])} and 
     * {@link #setCategoryKeys(Comparable[])} methods.
     *
     * @param starts  the start values data.
     * @param ends  the end values data.
     */
    public DefaultIntervalCategoryDataset(Number[][] starts, Number[][] ends) {
        this(null, null, starts, ends);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[2]++;
    }

    /**
     * Constructs a DefaultIntervalCategoryDataset, populates it with data
     * from the arrays, and uses the supplied names for the series.
     * <p>
     * Category names are generated automatically ("Category 1", "Category 2",
     * etc).
     *
     * @param seriesNames  the series names.
     * @param starts  the start values data, indexed as data[series][category].
     * @param ends  the end values data, indexed as data[series][category].
     */
    public DefaultIntervalCategoryDataset(String[] seriesNames,
                                          Number[][] starts,
                                          Number[][] ends) {

        this(seriesNames, null, starts, ends);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[3]++;

    }

    /**
     * Constructs a DefaultIntervalCategoryDataset, populates it with data
     * from the arrays, and uses the supplied names for the series and the
     * supplied objects for the categories.
     *
     * @param seriesKeys the series keys.
     * @param categoryKeys  the categories.
     * @param starts  the start values data, indexed as data[series][category].
     * @param ends  the end values data, indexed as data[series][category].
     */
    public DefaultIntervalCategoryDataset(Comparable[] seriesKeys,
                                          Comparable[] categoryKeys,
                                          Number[][] starts,
                                          Number[][] ends) {

        this.startData = starts;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[4]++;
        this.endData = ends;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[5]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((starts != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((ends != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[1]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[7]++;

            String baseName = "org.jfree.data.resources.DataPackageResources";
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[8]++;
            ResourceBundle resources = ResourceBundle.getBundle(baseName);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[9]++;

            int seriesCount = starts.length;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((seriesCount != ends.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[3]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[11]++;
                String errMsg = "DefaultIntervalCategoryDataset: the number "
                    + "of series in the start value dataset does "
                    + "not match the number of series in the end "
                    + "value dataset.";
                throw new IllegalArgumentException(errMsg);

            } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[4]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[5]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;

                // set up the series names...
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((seriesKeys != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[7]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;

                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((seriesKeys.length != seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[9]++;
                        throw new IllegalArgumentException(
                                "The number of series keys does not "
                                + "match the number of series in the data.");

                    } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[10]++;}

                    this.seriesKeys = seriesKeys;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[15]++;

                }
                else {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[8]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[16]++;
                    String prefix = resources.getString(
                            "series.default-prefix") + " ";
                    this.seriesKeys = generateKeys(seriesCount, prefix);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[17]++;
                }
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[18]++;

                // set up the category names...
                int categoryCount = starts[0].length;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((categoryCount != ends[0].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[11]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[20]++;
                    String errMsg = "DefaultIntervalCategoryDataset: the "
                                + "number of categories in the start value "
                                + "dataset does not match the number of "
                                + "categories in the end value dataset.";
                    throw new IllegalArgumentException(errMsg);

                } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[12]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((categoryKeys != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[13]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((categoryKeys.length != categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[15]++;
                        throw new IllegalArgumentException(
                                "The number of category keys does not match "
                                + "the number of categories in the data.");

                    } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[16]++;}
                    this.categoryKeys = categoryKeys;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[23]++;

                }
                else {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[14]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[24]++;
                    String prefix = resources.getString(
                            "categories.default-prefix") + " ";
                    this.categoryKeys = generateKeys(categoryCount, prefix);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[25]++;
                }


            }
            else {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[6]++;
                this.seriesKeys = null;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[26]++;
                this.categoryKeys = null;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[27]++;
            }

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[2]++;}

    }

    /**
     * Returns the number of series in the dataset (possibly zero).
     *
     * @return The number of series in the dataset.
     * 
     * @see #getRowCount()
     * @see #getCategoryCount()
     */
    public int getSeriesCount() {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[28]++;
        int result = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.startData != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[17]++;
            result = this.startData.length;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[30]++;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[18]++;}
        return result;
    }

    /**
     * Returns a series index.
     *
     * @param seriesKey  the series key.
     *
     * @return The series index.
     * 
     * @see #getRowIndex(Comparable)
     * @see #getSeriesKey(int)
     */
    public int getSeriesIndex(Comparable seriesKey) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[31]++;
        int result = -1;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[32]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < this.seriesKeys.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[1]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[2]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[3]++;
}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((seriesKey.equals(this.seriesKeys[i])) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[19]++;
                result = i;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[34]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[35]++;
                break;

            } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[20]++;}
        }
        return result;
    }

    /**
     * Returns the name of the specified series.
     *
     * @param series  the index of the required series (zero-based).
     *
     * @return The name of the specified series.
     * 
     * @see #getSeriesIndex(Comparable)
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[21]++;
            throw new IllegalArgumentException("No such series : " + series);

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[22]++;}
        return this.seriesKeys[series];
    }

    /**
     * Sets the names of the series in the dataset.
     *
     * @param seriesKeys  the new keys (<code>null</code> not permitted, the 
     *         length of the array must match the number of series in the 
     *         dataset).
     *         
     * @see #setCategoryKeys(Comparable[])
     */
    public void setSeriesKeys(Comparable[] seriesKeys) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((seriesKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[23]++;
            throw new IllegalArgumentException("Null 'seriesKeys' argument.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[24]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[38]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((seriesKeys.length != getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[25]++;
            throw new IllegalArgumentException(
                    "The number of series keys does not match the data.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[26]++;}
        this.seriesKeys = seriesKeys;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[39]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[40]++;
    }

    /**
     * Returns the number of categories in the dataset.
     *
     * @return The number of categories in the dataset.
     * 
     * @see #getColumnCount()
     */
    public int getCategoryCount() {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[41]++;
        int result = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.startData != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[27]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getSeriesCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[29]++;
                result = this.startData[0].length;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[44]++;

            } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[28]++;}
        return result;
    }
    
    /**
     * Returns a list of the categories in the dataset.  This method supports 
     * the {@link CategoryDataset} interface.
     *
     * @return A list of the categories in the dataset.
     * 
     * @see #getRowKeys()
     */
    public List getColumnKeys() {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;
        // the CategoryDataset interface expects a list of categories, but
        // we've stored them in an array...
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.categoryKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[31]++;
            return new ArrayList();

        }
        else {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[32]++;
            return Collections.unmodifiableList(Arrays.asList(
                    this.categoryKeys));
        }
    }

    /**
     * Sets the categories for the dataset.
     *
     * @param categoryKeys  an array of objects representing the categories in 
     *                      the dataset.
     *                      
     * @see #getRowKeys()
     * @see #setSeriesKeys(Comparable[])
     */
    public void setCategoryKeys(Comparable[] categoryKeys) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[46]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((categoryKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[33]++;
            throw new IllegalArgumentException("Null 'categoryKeys' argument.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[34]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[47]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((categoryKeys.length != this.startData[0].length) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[35]++;
            throw new IllegalArgumentException(
                    "The number of categories does not match the data.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[36]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[48]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < categoryKeys.length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[4]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[5]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[6]++;
}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[49]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((categoryKeys[i] == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[37]++;
                throw new IllegalArgumentException(
                    "DefaultIntervalCategoryDataset.setCategoryKeys(): "
                    + "null category not permitted.");

            } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[38]++;}
        }
        this.categoryKeys = categoryKeys;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[50]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[51]++;
    }

    /**
     * Returns the data value for one category in a series.
     * <P>
     * This method is part of the CategoryDataset interface.  Not particularly
     * meaningful for this class...returns the end value.
     * 
     * @param series    The required series (zero based index).
     * @param category  The required category.
     * 
     * @return The data value for one category in a series (null possible).
     * 
     * @see #getEndValue(Comparable, Comparable)
     */
    public Number getValue(Comparable series, Comparable category) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[52]++;
        int seriesIndex = getSeriesIndex(series);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[53]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((seriesIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[39]++;
            throw new UnknownKeyException("Unknown 'series' key.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[40]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[54]++;
        int itemIndex = getColumnIndex(category);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[55]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((itemIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[41]++;
            throw new UnknownKeyException("Unknown 'category' key.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[42]++;}
        return getValue(seriesIndex, itemIndex);
    }

    /**
     * Returns the data value for one category in a series.
     * <P>
     * This method is part of the CategoryDataset interface.  Not particularly
     * meaningful for this class...returns the end value.
     *
     * @param series  the required series (zero based index).
     * @param category  the required category.
     *
     * @return The data value for one category in a series (null possible).
     * 
     * @see #getEndValue(int, int)
     */
    public Number getValue(int series, int category) {
        return getEndValue(series, category);
    }

    /**
     * Returns the start data value for one category in a series.
     *
     * @param series  the required series.
     * @param category  the required category.
     *
     * @return The start data value for one category in a series 
     *         (possibly <code>null</code>).
     *         
     * @see #getStartValue(int, int)
     */
    public Number getStartValue(Comparable series, Comparable category) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[56]++;
        int seriesIndex = getSeriesIndex(series);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[57]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((seriesIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[43]++;
            throw new UnknownKeyException("Unknown 'series' key.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[44]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[58]++;
        int itemIndex = getColumnIndex(category);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[59]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((itemIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[45]++;
            throw new UnknownKeyException("Unknown 'category' key.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[46]++;}
        return getStartValue(seriesIndex, itemIndex);
    }

    /**
     * Returns the start data value for one category in a series.
     *
     * @param series  the required series (zero based index).
     * @param category  the required category.
     *
     * @return The start data value for one category in a series 
     *         (possibly <code>null</code>).
     *         
     * @see #getStartValue(Comparable, Comparable)
     */
    public Number getStartValue(int series, int category) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[60]++;
int CodeCoverConditionCoverageHelper_C26;

        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[47]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.getValue(): "
                + "series index out of range.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[48]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[61]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((category < 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((category >= getCategoryCount()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[49]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.getValue(): "
                + "category index out of range.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[50]++;}

        // fetch the value...
        return this.startData[series][category];

    }

    /**
     * Returns the end data value for one category in a series.
     *
     * @param series  the required series.
     * @param category  the required category.
     *
     * @return The end data value for one category in a series (null possible).
     * 
     * @see #getEndValue(int, int)
     */
    public Number getEndValue(Comparable series, Comparable category) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[62]++;
        int seriesIndex = getSeriesIndex(series);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[63]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((seriesIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[51]++;
            throw new UnknownKeyException("Unknown 'series' key.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[52]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[64]++;
        int itemIndex = getColumnIndex(category);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[65]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((itemIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[53]++;
            throw new UnknownKeyException("Unknown 'category' key.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[54]++;}
        return getEndValue(seriesIndex, itemIndex);
    }

    /**
     * Returns the end data value for one category in a series.
     *
     * @param series  the required series (zero based index).
     * @param category  the required category.
     *
     * @return The end data value for one category in a series (null possible).
     * 
     * @see #getEndValue(Comparable, Comparable)
     */
    public Number getEndValue(int series, int category) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[66]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[55]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.getValue(): "
                + "series index out of range.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[56]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[67]++;
int CodeCoverConditionCoverageHelper_C31;

        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((category < 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((category >= getCategoryCount()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[57]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.getValue(): "
                + "category index out of range.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[58]++;}

        return this.endData[series][category];
    }

    /**
     * Sets the start data value for one category in a series.
     * 
     * @param series  the series (zero-based index).
     * @param category  the category.
     * 
     * @param value The value.
     * 
     * @see #setEndValue(int, Comparable, Number)
     */
    public void setStartValue(int series, Comparable category, Number value) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[68]++;
int CodeCoverConditionCoverageHelper_C32;

        // does the series exist?
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((series > getSeriesCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[59]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.setValue: "
                + "series outside valid range.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[60]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[69]++;

        // is the category valid?
        int categoryIndex = getCategoryIndex(category);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[70]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((categoryIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[61]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.setValue: "
                + "unrecognised category.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[62]++;}

        // update the data...
        this.startData[series][categoryIndex] = value;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[71]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[72]++;

    }

    /**
     * Sets the end data value for one category in a series.
     *
     * @param series  the series (zero-based index).
     * @param category  the category.
     *
     * @param value the value.
     * 
     * @see #setStartValue(int, Comparable, Number)
     */
    public void setEndValue(int series, Comparable category, Number value) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[73]++;
int CodeCoverConditionCoverageHelper_C34;

        // does the series exist?
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((series > getSeriesCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[63]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.setValue: "
                + "series outside valid range.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[64]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[74]++;

        // is the category valid?
        int categoryIndex = getCategoryIndex(category);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[75]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((categoryIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[65]++;
            throw new IllegalArgumentException(
                "DefaultIntervalCategoryDataset.setValue: "
                + "unrecognised category.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[66]++;}

        // update the data...
        this.endData[series][categoryIndex] = value;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[76]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[77]++;

    }

    /**
     * Returns the index for the given category.
     *
     * @param category  the category (<code>null</code> not permitted).
     *
     * @return The index.
     * 
     * @see #getColumnIndex(Comparable)
     */
    public int getCategoryIndex(Comparable category) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[78]++;
        int result = -1;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[79]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[7]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < this.categoryKeys.length) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[7]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[8]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[9]++;
}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[80]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((category.equals(this.categoryKeys[i])) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[67]++;
                result = i;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[81]++;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[82]++;
                break;

            } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[68]++;}
        }
        return result;
    }

    /**
     * Generates an array of keys, by appending a space plus an integer
     * (starting with 1) to the supplied prefix string.
     *
     * @param count  the number of keys required.
     * @param prefix  the name prefix.
     *
     * @return An array of <i>prefixN</i> with N = { 1 .. count}.
     */
    private Comparable[] generateKeys(int count, String prefix) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[83]++;
        Comparable[] result = new Comparable[count];
        String name;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[84]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[10]++;


int CodeCoverConditionCoverageHelper_C38;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[10]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[11]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[12]++;
}
            name = prefix + (i + 1);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[85]++;
            result[i] = name;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[86]++;
        }
        return result;
    }

    /**
     * Returns a column key.
     *
     * @param column  the column index.
     *
     * @return The column key.
     * 
     * @see #getRowKey(int)
     */
    public Comparable getColumnKey(int column) {
        return this.categoryKeys[column];
    }

    /**
     * Returns a column index.
     *
     * @param columnKey  the column key (<code>null</code> not permitted).
     *
     * @return The column index.
     * 
     * @see #getCategoryIndex(Comparable)
     */
    public int getColumnIndex(Comparable columnKey) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[87]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[69]++;
            throw new IllegalArgumentException("Null 'columnKey' argument.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[70]++;}
        return getCategoryIndex(columnKey);
    }

    /**
     * Returns a row index.
     *
     * @param rowKey  the row key.
     *
     * @return The row index.
     * 
     * @see #getSeriesIndex(Comparable)
     */
    public int getRowIndex(Comparable rowKey) {
        return getSeriesIndex(rowKey);
    }

    /**
     * Returns a list of the series in the dataset.  This method supports the 
     * {@link CategoryDataset} interface.
     *
     * @return A list of the series in the dataset.
     * 
     * @see #getColumnKeys()
     */
    public List getRowKeys() {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[88]++;
int CodeCoverConditionCoverageHelper_C40;
        // the CategoryDataset interface expects a list of series, but
        // we've stored them in an array...
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.seriesKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[71]++;
            return new java.util.ArrayList();

        }
        else {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[72]++;
            return Collections.unmodifiableList(Arrays.asList(this.seriesKeys));
        }
    }

    /**
     * Returns the name of the specified series.
     *
     * @param row  the index of the required row/series (zero-based).
     *
     * @return The name of the specified series.
     * 
     * @see #getColumnKey(int)
     */
    public Comparable getRowKey(int row) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[89]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((row >= getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((row < 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[73]++;
            throw new IllegalArgumentException(
                    "The 'row' argument is out of bounds.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[74]++;}
        return this.seriesKeys[row];
    }

    /**
     * Returns the number of categories in the dataset.  This method is part of 
     * the {@link CategoryDataset} interface.
     *
     * @return The number of categories in the dataset.
     * 
     * @see #getCategoryCount()
     * @see #getRowCount()
     */
    public int getColumnCount() {
        return this.categoryKeys.length;
    }

    /**
     * Returns the number of series in the dataset (possibly zero).
     *
     * @return The number of series in the dataset.
     * 
     * @see #getSeriesCount()
     * @see #getColumnCount()
     */
    public int getRowCount() {
        return this.seriesKeys.length;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[90]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[75]++;
            return true;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[76]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[91]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultIntervalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[78]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[92]++;
        DefaultIntervalCategoryDataset that 
                = (DefaultIntervalCategoryDataset) obj;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[93]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.seriesKeys, that.seriesKeys)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[80]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[94]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.categoryKeys, that.categoryKeys)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[82]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[95]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((equal(this.startData, that.startData)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[84]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[96]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((equal(this.endData, that.endData)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[86]++;}
        // seem to be the same...
        return true;
    }

    /**
     * Returns a clone of this dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the
     *         dataset.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[97]++;
        DefaultIntervalCategoryDataset clone 
                = (DefaultIntervalCategoryDataset) super.clone();
        clone.categoryKeys = (Comparable[]) this.categoryKeys.clone();
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[98]++;
        clone.seriesKeys = (Comparable[]) this.seriesKeys.clone();
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[99]++;
        clone.startData = clone(this.startData);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[100]++;
        clone.endData = clone(this.endData);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[101]++;
        return clone;
    }
    
    /**
     * Tests two double[][] arrays for equality.
     * 
     * @param array1  the first array (<code>null</code> permitted).
     * @param array2  the second arrray (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    private static boolean equal(Number[][] array1, Number[][] array2) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[102]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((array1 == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[87]++;
            return (array2 == null);

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[88]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[103]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((array2 == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[90]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[104]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((array1.length != array2.length) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[92]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[105]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[13]++;


int CodeCoverConditionCoverageHelper_C51;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((i < array1.length) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[13]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[14]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[15]++;
}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[106]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((Arrays.equals(array1[i], array2[i])) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[93]++;
                return false;

            } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[94]++;}
        }
        return true;
    }
    
    /**
     * Clones a two dimensional array of <code>Number</code> objects.
     * 
     * @param array  the array (<code>null</code> not permitted).
     * 
     * @return A clone of the array.
     */
    private static Number[][] clone(Number[][] array) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[107]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[95]++;
            throw new IllegalArgumentException("Null 'array' argument.");

        } else {
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[96]++;}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[108]++;
        Number[][] result = new Number[array.length][];
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[109]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[16]++;


int CodeCoverConditionCoverageHelper_C54;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < array.length) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[16]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[17]--;
  CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.loops[18]++;
}
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[110]++;
            Number[] child = array[i];
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[111]++;
            Number[] copychild = new Number[child.length];
            System.arraycopy(child, 0, copychild, 0, child.length);
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[112]++;
            result[i] = copychild;
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[113]++;
        }
        return result;
    }

    /**
     * Returns a list of the series in the dataset.
     *
     * @return A list of the series in the dataset.
     * 
     * @deprecated Use {@link #getRowKeys()} instead.
     */
    public List getSeries() {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.statements[114]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.seriesKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[97]++;
            return new java.util.ArrayList();

        }
        else {
CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1.branches[98]++;
            return Collections.unmodifiableList(Arrays.asList(this.seriesKeys));
        }
    }

    /**
     * Returns a list of the categories in the dataset.
     *
     * @return A list of the categories in the dataset.
     * 
     * @deprecated Use {@link #getColumnKeys()} instead.
     */
    public List getCategories() {
        return getColumnKeys();
    }

    /**
     * Returns the item count.
     *
     * @return The item count.
     * 
     * @deprecated Use {@link #getCategoryCount()} instead.
     */
    public int getItemCount() {
        return this.categoryKeys.length;
    }

}

class CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1 ());
  }
    public static long[] statements = new long[115];
    public static long[] branches = new long[99];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[56];
  static {
    final String SECTION_NAME = "org.jfree.data.category.DefaultIntervalCategoryDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,2,2,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 55; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$h0zz9vdn73xtyf0ljhgnxzprxmtkg4r5rbvzi4opfli10gbcrjdnc1 () {
    super("org.jfree.data.category.DefaultIntervalCategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 114; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 98; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.category.DefaultIntervalCategoryDataset.java");
      for (int i = 1; i <= 114; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 98; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

