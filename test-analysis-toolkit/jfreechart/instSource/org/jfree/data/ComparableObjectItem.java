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
 * ComparableObjectItem.java
 * -------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Oct-2006 : New class, based on XYDataItem (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;

import org.jfree.util.ObjectUtilities;

/**
 * Represents one (Comparable, Object) data item for use in a 
 * {@link ComparableObjectSeries}.
 *
 * @since 1.0.3
 */
public class ComparableObjectItem implements Cloneable, Comparable, 
        Serializable {
  static {
    CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2751513470325494890L;
  static {
    CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[1]++;
  }
    
    /** The x-value. */
    private Comparable x;

    /** The y-value. */
    private Object obj;

    /**
     * Constructs a new data item.
     *
     * @param x  the x-value (<code>null</code> NOT permitted).
     * @param y  the y-value (<code>null</code> permitted).
     */
    public ComparableObjectItem(Comparable x, Object y) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[1]++;
            throw new IllegalArgumentException("Null 'x' argument.");

        } else {
  CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[2]++;}
        this.x = x;
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[3]++;
        this.obj = y;
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[4]++;
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never <code>null</code>).
     */
    protected Comparable getComparable() {
        return this.x;
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value (possibly <code>null</code>).
     */
    protected Object getObject() {
        return this.obj;
    }

    /**
     * Sets the y-value for this data item.  Note that there is no 
     * corresponding method to change the x-value.
     *
     * @param y  the new y-value (<code>null</code> permitted).
     */
    protected void setObject(Object y) {
        this.obj = y;
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[5]++;
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
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;

        // CASE 1 : Comparing to another ComparableObjectItem object
        // ---------------------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((o1 instanceof ComparableObjectItem) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[3]++;
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[7]++;
            ComparableObjectItem that = (ComparableObjectItem) o1;
            return this.x.compareTo(that.x);

        }

        // CASE 2 : Comparing to a general object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[4]++;
            // consider these to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[8]++;
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
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[6]++;}
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof ComparableObjectItem) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[8]++;}
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[11]++;
        ComparableObjectItem that = (ComparableObjectItem) obj;
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.x.equals(that.x)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[10]++;}
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.obj, that.obj)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.branches[12]++;}
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
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[14]++;
        result = 29 * result + (this.obj != null ? this.obj.hashCode() : 0);
CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d.statements[15]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.data.ComparableObjectItem.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$32iu5d7ytryfx1gac0mfew0anbqyavmrtmtc19d () {
    super("org.jfree.data.ComparableObjectItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.ComparableObjectItem.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

