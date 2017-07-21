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
 * ---------------
 * XYDataItem.java
 * ---------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Aug-2003 : Renamed XYDataPair --> XYDataItem (DG);
 * 03-Feb-2004 : Fixed bug in equals() method (DG);
 * 21-Feb-2005 : Added setY(double) method (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;

import org.jfree.util.ObjectUtilities;

/**
 * Represents one (x, y) data item for an {@link XYSeries}.
 */
public class XYDataItem implements Cloneable, Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2751513470325494890L;
  static {
    CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[1]++;
  }
    
    /** The x-value. */
    private Number x;

    /** The y-value. */
    private Number y;

    /**
     * Constructs a new data item.
     *
     * @param x  the x-value (<code>null</code> NOT permitted).
     * @param y  the y-value (<code>null</code> permitted).
     */
    public XYDataItem(Number x, Number y) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[1]++;
            throw new IllegalArgumentException("Null 'x' argument.");

        } else {
  CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[2]++;}
        this.x = x;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[3]++;
        this.y = y;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[4]++;
    }

    /**
     * Constructs a new data item.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     */
    public XYDataItem(double x, double y) {
        this(new Double(x), new Double(y));
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[5]++;
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never <code>null</code>).
     */
    public Number getX() {
        return this.x;
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value (possibly <code>null</code>).
     */
    public Number getY() {
        return this.y;
    }

    /**
     * Sets the y-value for this data item.  Note that there is no 
     * corresponding method to change the x-value.
     *
     * @param y  the new y-value.
     */
    public void setY(double y) {
        setY(new Double(y));
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[6]++;   
    }
    
    /**
     * Sets the y-value for this data item.  Note that there is no 
     * corresponding method to change the x-value.
     *
     * @param y  the new y-value (<code>null</code> permitted).
     */
    public void setY(Number y) {
        this.y = y;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[7]++;
    }

    /**
     * Returns an integer indicating the order of this object relative to 
     * another object.
     * <P>
     * For the order we consider only the x-value:
     * negative == "less-than", zero == "equal", positive == "greater-than".
     *
     * @param o1  the object being compared to.
     *
     * @return An integer indicating the order of this data pair object
     *      relative to another object.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;

        // CASE 1 : Comparing to another TimeSeriesDataPair object
        // -------------------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((o1 instanceof XYDataItem) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[3]++;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[9]++;
            XYDataItem dataItem = (XYDataItem) o1;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[10]++;
            double compare = this.x.doubleValue() 
                             - dataItem.getX().doubleValue();
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((compare > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[5]++;
                result = 1;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[12]++;

            }
            else {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[6]++;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((compare < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[7]++;
                    result = -1;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[14]++;

                }
                else {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[8]++;
                    result = 0;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[15]++;
                }
            }

        }

        // CASE 2 : Comparing to a general object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[4]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[16]++;
        }

        return result;

    }

    /**
     * Returns a clone of this object.
     *
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class, but 
     *         subclasses may differ.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the object to test against for equality (<code>null</code>
     *             permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[10]++;}
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof XYDataItem) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[12]++;}
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[19]++;
        XYDataItem that = (XYDataItem) obj;
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.x.equals(that.x)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[14]++;}
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.y, that.y)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.branches[16]++;}
        return true;        
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = this.x.hashCode();
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[22]++;
        result = 29 * result + (this.y != null ? this.y.hashCode() : 0);
CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35.statements[23]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35 ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XYDataItem.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$qh90zg0dohxtrnnx3q7qn35 () {
    super("org.jfree.data.xy.XYDataItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.XYDataItem.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

