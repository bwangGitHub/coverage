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
 * ----------------
 * OutlierList.java
 * ----------------
 * (C) Copyright 2003, 2004, 2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 28-Aug-2003 : Minor tidy-up including Javadocs (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */
package org.jfree.chart.renderer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of outliers for a single entity in a box and whisker plot.
 *
 * Outliers are grouped in lists for each entity. Lists contain
 * one or more outliers, determined by whether overlaps have
 * occured. Overlapping outliers are grouped in the same list.
 *
 * Each list contains an averaged outlier, which is the same as a single
 * outlier if there is only one outlier in the list, but the average of
 * all the outliers in the list if there is more than one.
 *
 * NB This is simply my scheme for displaying outliers, and might not be
 * acceptable by the wider community.
 */
public class OutlierList {
  static {
    CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.ping();
  }


    /** Storage for the outliers. */
    private List outliers;
    
    /** The averaged outlier. */
    private Outlier averagedOutlier;
    
    /** 
     * A flag that indicates whether or not there are multiple outliers in the 
     * list. 
     */
    private boolean multiple = false;
  {
    CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[1]++;
  }

    /**
     * Creates a new list containing a single outlier.
     * 
     * @param outlier  the outlier.
     */
    public OutlierList(Outlier outlier) {
        this.outliers = new ArrayList();
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[2]++;
        setAveragedOutlier(outlier);
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[3]++;
    }

    /**
     * Adds an outlier to the list.
     * 
     * @param outlier  the outlier.
     * 
     * @return A boolean.
     */
    public boolean add(Outlier outlier) {
        return this.outliers.add(outlier);    
    }
    
    /**
     * Returns the number of outliers in the list.
     * 
     * @return The item count.
     */
    public int getItemCount() {
        return this.outliers.size();
    }
    
    /**
     * Returns the averaged outlier. 
     * 
     * @return The averaged outlier.
     */
    public Outlier getAveragedOutlier() {
        return this.averagedOutlier;
    }

    /**
     * Sets the averaged outlier.
     * 
     * @param averagedOutlier  the averaged outlier.
     */
    public void setAveragedOutlier(Outlier averagedOutlier) {
        this.averagedOutlier = averagedOutlier;
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[4]++;
    }

    /**
     * Returns <code>true</code> if the list contains multiple outliers, and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     */
    public boolean isMultiple() {
        return this.multiple;
    }

    /**
     * Sets the flag that indicates whether or not this list represents 
     * multiple outliers.
     * 
     * @param multiple  the flag.
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[5]++;
    }

    /**
     * Returns <code>true</code> if the outlier overlaps, and 
     * <code>false</code> otherwise.
     * 
     * @param other  the outlier.
     * 
     * @return A boolean.
     */
    public boolean isOverlapped(Outlier other) {
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.branches[1]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.branches[2]++;}
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[7]++;
        
        boolean result = other.overlaps(getAveragedOutlier());
        return result;
        
    }

    /**
     * Updates the averaged outlier.
     *
     */
    public void updateAveragedOutlier() {
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[8]++;
        double totalXCoords = 0.0;
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[9]++;
        double totalYCoords = 0.0;
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[10]++;
        int size = getItemCount();
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (Iterator iterator = this.outliers.iterator();(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.loops[1]--;
  CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.loops[2]--;
  CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.loops[3]++;
}
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[12]++;
            Outlier o = (Outlier) iterator.next();
            totalXCoords += o.getX();
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[13]++;
            totalYCoords += o.getY();
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[14]++;
        }
        getAveragedOutlier().getPoint().setLocation(
            new Point2D.Double(totalXCoords / size, totalYCoords / size)
        );
CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt.statements[15]++;
    }

}

class CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.OutlierList.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$4pckzv4au482zr8vz3jim8ndt () {
    super("org.jfree.chart.renderer.OutlierList.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.OutlierList.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

