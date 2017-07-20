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
 * -------------------------
 * CustomXYURLGenerator.java
 * -------------------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributors:     David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 05-Aug-2002 : Version 1, contributed by Richard Atkinson;
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Mar-2003 : Implemented Serializable (DG);
 * 20-Jan-2005 : Minor Javadoc update (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.urls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.xy.XYDataset;

/**
 * A custom URL generator.
 */
public class CustomXYURLGenerator implements XYURLGenerator, Serializable {
  static {
    CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8565933356596551832L;
  static {
    CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[1]++;
  }
    
    /** Storage for the URLs. */
    private ArrayList urlSeries = new ArrayList();
  {
    CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[2]++;
  }

    /**
     * Default constructor.
     */
    public CustomXYURLGenerator() {
        super();
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[3]++;
    }

    /**
     * Returns the number of URL lists stored by the renderer.
     *
     * @return The list count.
     */
    public int getListCount() {
        return this.urlSeries.size();
    }

    /**
     * Returns the number of URLs in a given list.
     *
     * @param list  the list index (zero based).
     *
     * @return The URL count.
     */
    public int getURLCount(int list) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[4]++;
        int result = 0;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[5]++;
        List urls = (List) this.urlSeries.get(list);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((urls != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[1]++;
            result = urls.size();
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[7]++;

        } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[2]++;}
        return result;
    }

    /**
     * Returns the URL for an item.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The URL (possibly <code>null</code>).
     */
    public String getURL(int series, int item) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[8]++;
        String result = null;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series < getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[3]++;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[10]++;
            List urls = (List) this.urlSeries.get(series);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((urls != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[5]++;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((item < urls.size()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[7]++;
                    result = (String) urls.get(item);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[13]++;

                } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[8]++;}

            } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[4]++;}
        return result;
    }

    /**
     * Generates a URL.
     *
     * @param dataset  the dataset.
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return A string containing the URL (possibly <code>null</code>).
     */
    public String generateURL(XYDataset dataset, int series, int item) {
        return getURL(series, item);
    }

    /**
     * Adds a list of URLs.
     *
     * @param urls  the list of URLs.
     */
    public void addURLSeries(List urls) {
        this.urlSeries.add(urls);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[14]++;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param o  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object o) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((o == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[10]++;}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((o == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[12]++;}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((o instanceof CustomXYURLGenerator) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[14]++;}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[18]++;
        CustomXYURLGenerator generator = (CustomXYURLGenerator) o;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[19]++;
        int listCount = getListCount();
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((listCount != generator.getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[16]++;}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;

        for (int series = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((series < listCount) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[1]--;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[2]--;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[3]++;
}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[22]++;
            int urlCount = getURLCount(series);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((urlCount != generator.getURLCount(series)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[17]++;
                return false;

            } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[18]++;}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;

            for (int item = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((item < urlCount) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[4]--;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[5]--;
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.loops[6]++;
}
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[25]++;
                String u1 = getURL(series, item);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[26]++;
                String u2 = generator.getURL(series, item);
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[27]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((u1 != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[19]++;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[28]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((u1.equals(u2)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[21]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[22]++;}

                }
                else {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[20]++;
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.statements[29]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((u2 != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[23]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129.branches[24]++;}
                }
            }
        }
        return true;

    }

}

class CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129 ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.urls.CustomXYURLGenerator.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$32k84kyiek5by6jpgsw4e0eiryyzg54gctpg129 () {
    super("org.jfree.chart.urls.CustomXYURLGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.urls.CustomXYURLGenerator.java");
      for (int i = 1; i <= 29; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
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

