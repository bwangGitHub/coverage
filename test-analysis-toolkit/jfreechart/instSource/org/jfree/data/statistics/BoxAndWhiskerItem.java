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
 * ----------------------
 * BoxAndWhiskerItem.java
 * ----------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Aug-2003 : Version 1 (DG); 
 * 01-Mar-2004 : Added equals() method and implemented Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 15-Nov-2006 : Added toString() method override (DG);
 * 02-Oct-2007 : Added new constructor (for convenience) (DG);
 * 
 */

package org.jfree.data.statistics;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.jfree.util.ObjectUtilities;

/**
 * Represents one data item within a box-and-whisker dataset.  Instances of 
 * this class are immutable.
 */
public class BoxAndWhiskerItem implements Serializable {
  static {
    CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 7329649623148167423L;
  static {
    CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[1]++;
  }
    
    /** The mean. */
    private Number mean;
    
    /** The median. */
    private Number median;
    
    /** The first quarter. */
    private Number q1;
    
    /** The third quarter. */
    private Number q3;
    
    /** The minimum regular value. */
    private Number minRegularValue;
    
    /** The maximum regular value. */
    private Number maxRegularValue;
    
    /** The minimum outlier. */
    private Number minOutlier;
    
    /** The maximum outlier. */
    private Number maxOutlier;
    
    /** The outliers. */
    private List outliers;
    
    /**
     * Creates a new box-and-whisker item.
     * 
     * @param mean  the mean (<code>null</code> permitted).
     * @param median  the median (<code>null</code> permitted).
     * @param q1  the first quartile (<code>null</code> permitted).
     * @param q3  the third quartile (<code>null</code> permitted).
     * @param minRegularValue  the minimum regular value (<code>null</code> 
     *                         permitted).
     * @param maxRegularValue  the maximum regular value (<code>null</code> 
     *                         permitted).
     * @param minOutlier  the minimum outlier (<code>null</code> permitted).
     * @param maxOutlier  the maximum outlier (<code>null</code> permitted).
     * @param outliers  the outliers (<code>null</code> permitted).
     */
    public BoxAndWhiskerItem(Number mean,
                             Number median,
                             Number q1,
                             Number q3,
                             Number minRegularValue,
                             Number maxRegularValue,
                             Number minOutlier,
                             Number maxOutlier,
                             List outliers) {
                                 
        this.mean = mean;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[2]++;
        this.median = median;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[3]++;    
        this.q1 = q1;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[4]++;
        this.q3 = q3;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[5]++;
        this.minRegularValue = minRegularValue;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[6]++;
        this.maxRegularValue = maxRegularValue;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[7]++;
        this.minOutlier = minOutlier;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[8]++;
        this.maxOutlier = maxOutlier;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[9]++;
        this.outliers = outliers;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[10]++;
        
    }

    /**
     * Creates a new box-and-whisker item.
     * 
     * @param mean  the mean.
     * @param median  the median
     * @param q1  the first quartile.
     * @param q3  the third quartile.
     * @param minRegularValue  the minimum regular value.
     * @param maxRegularValue  the maximum regular value.
     * @param minOutlier  the minimum outlier value.
     * @param maxOutlier  the maximum outlier value.
     * @param outliers  a list of the outliers.
     * 
     * @since 1.0.7
     */
    public BoxAndWhiskerItem(double mean, double median, double q1, double q3,
            double minRegularValue, double maxRegularValue, double minOutlier,
            double maxOutlier, List outliers) {
        
        // pass values to other constructor
        this(new Double(mean), new Double(median), new Double(q1), 
                new Double(q3), new Double(minRegularValue), 
                new Double(maxRegularValue), new Double(minOutlier), 
                new Double(maxOutlier), outliers);
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[11]++;
        
    }

    /**
     * Returns the mean.
     * 
     * @return The mean (possibly <code>null</code>).
     */
    public Number getMean() {
        return this.mean;
    }
    
    /**
     * Returns the median.
     * 
     * @return The median (possibly <code>null</code>).
     */
    public Number getMedian() {
        return this.median;
    }
    
    /**
     * Returns the first quartile. 
     * 
     * @return The first quartile (possibly <code>null</code>).
     */
    public Number getQ1() {
        return this.q1;
    }
    
    /**
     * Returns the third quartile. 
     * 
     * @return The third quartile (possibly <code>null</code>).
     */
    public Number getQ3() {
        return this.q3;
    }
    
    /**
     * Returns the minimum regular value.
     * 
     * @return The minimum regular value (possibly <code>null</code>).
     */
    public Number getMinRegularValue() {
        return this.minRegularValue;
    }
    
    /**
     * Returns the maximum regular value. 
     * 
     * @return The maximum regular value (possibly <code>null</code>).
     */
    public Number getMaxRegularValue() {
        return this.maxRegularValue;
    }
    
    /**
     * Returns the minimum outlier.
     * 
     * @return The minimum outlier (possibly <code>null</code>).
     */
    public Number getMinOutlier() {
        return this.minOutlier;
    }
    
    /**
     * Returns the maximum outlier.
     * 
     * @return The maximum outlier (possibly <code>null</code>).
     */
    public Number getMaxOutlier() {
        return this.maxOutlier;
    }
    
    /**
     * Returns a list of outliers.
     * 
     * @return A list of outliers (possibly <code>null</code>).
     */
    public List getOutliers() {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.outliers == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[2]++;}
        return Collections.unmodifiableList(this.outliers);
    }
    
    /**
     * Returns a string representation of this instance, primarily for
     * debugging purposes.
     * 
     * @return A string representation of this instance.
     */
    public String toString() {
        return super.toString() + "[mean=" + this.mean + ",median=" 
                + this.median + ",q1=" + this.q1 + ",q3=" + this.q3 + "]";
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[3]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[4]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof BoxAndWhiskerItem) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[6]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[15]++;
        BoxAndWhiskerItem that = (BoxAndWhiskerItem) obj;
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.mean, that.mean)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[8]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.median, that.median)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[10]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.q1, that.q1)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[12]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.q3, that.q3)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[14]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.minRegularValue, 
                that.minRegularValue)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[16]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.maxRegularValue, 
                that.maxRegularValue)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[18]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[22]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.minOutlier, that.minOutlier)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[20]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[23]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.maxOutlier, that.maxOutlier)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[22]++;}
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.statements[24]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outliers, that.outliers)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t.branches[24]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.BoxAndWhiskerItem.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$awfdiop88q4vpzj3tissplpdr2pznmwy1t () {
    super("org.jfree.data.statistics.BoxAndWhiskerItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.BoxAndWhiskerItem.java");
      for (int i = 1; i <= 24; i++) {
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
    for (int i = 1; i <= 12; i++) {
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

