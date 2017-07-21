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
 * --------------
 * TickUnits.java
 * --------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Nov-2001 : Version 1 (DG);
 * 18-Feb-2002 : Fixed bug in getNearestTickUnit (thanks to Mario Inchiosa for 
 *               reporting this, SourceForge bug id 518073) (DG);
 * 25-Feb-2002 : Moved createStandardTickUnits() method from NumberAxis, and 
 *               added createIntegerTickUnits() method (DG);
 * 01-May-2002 : Updated for changes to the TickUnit class (DG);
 * 18-Sep-2002 : Added standardTickUnit methods which take a Locale 
 *               instance (AS);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 23-Sep-2003 : Implemented TickUnitSource interface (DG);
 * 03-Dec-2003 : Adding null values now throws exceptions (TM);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * 
 */

package org.jfree.chart.axis;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A collection of tick units, used by the {@link DateAxis} and 
 * {@link NumberAxis} classes.
 */
public class TickUnits implements TickUnitSource, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1134174035901467545L;
  static {
    CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[1]++;
  }
    
    /** Storage for the tick units. */
    private List tickUnits;

    /**
     * Constructs a new collection of tick units.
     */
    public TickUnits() {
        this.tickUnits = new ArrayList();
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[2]++;
    }

    /**
     * Adds a tick unit to the collection.  The tick units are maintained in 
     * ascending order.
     *
     * @param unit  the tick unit to add (<code>null</code> not permitted).
     */
    public void add(TickUnit unit) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((unit == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[1]++;
            throw new NullPointerException("Null 'unit' argument.");

        } else {
  CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[2]++;}
        this.tickUnits.add(unit);
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[4]++;
        Collections.sort(this.tickUnits);
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[5]++;
    }

    /**
     * Returns the number of tick units in this collection.
     * <P>
     * This method is required for the XML writer.
     *
     * @return The number of units in this collection.
     */
    public int size() {
        return this.tickUnits.size();
    }

    /**
     * Returns the tickunit on the given position.
     * <P>
     * This method is required for the XML writer.
     *
     * @param pos the position in the list.
     * 
     * @return The tickunit.
     */
    public TickUnit get(int pos) {
        return (TickUnit) this.tickUnits.get(pos);
    }

    /**
     * Returns a tick unit that is larger than the supplied unit.
     *
     * @param unit   the unit.
     *
     * @return A tick unit that is larger than the supplied unit.
     */
    public TickUnit getLargerTickUnit(TickUnit unit) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[6]++;

        int index = Collections.binarySearch(this.tickUnits, unit);
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[3]++;
            index = index + 1;
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[8]++;

        }
        else {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[4]++;
            index = -index;
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[9]++;
        }

        return (TickUnit) this.tickUnits.get(Math.min(index, 
                this.tickUnits.size() - 1));

    }

    /**
     * Returns the tick unit in the collection that is greater than or equal
     * to (in size) the specified unit.
     *
     * @param unit  the unit.
     *
     * @return A unit from the collection.
     */
    public TickUnit getCeilingTickUnit(TickUnit unit) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[10]++;

        int index = Collections.binarySearch(this.tickUnits, unit);
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[5]++;
            return (TickUnit) this.tickUnits.get(index);

        }
        else {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[6]++;
            index = -(index + 1);
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[12]++;
            return (TickUnit) this.tickUnits.get(Math.min(index, 
                    this.tickUnits.size() - 1));
        }

    }

    /**
     * Returns the tick unit in the collection that is greater than or equal
     * to the specified size.
     *
     * @param size  the size.
     *
     * @return A unit from the collection.
     */
    public TickUnit getCeilingTickUnit(double size) {
        return getCeilingTickUnit(new NumberTickUnit(size, 
                NumberFormat.getInstance()));
    }

    /**
     * Returns a clone of the collection.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if an item in the collection does not 
     *         support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[13]++;
        TickUnits clone = (TickUnits) super.clone();
        clone.tickUnits = new java.util.ArrayList(this.tickUnits);
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[14]++;
        return clone;
    }

    /**
     * Tests an object for equality with this instance.
     *
     * @param obj  the object to test (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[8]++;}
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof TickUnits) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.branches[10]++;}
CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d.statements[17]++;
        TickUnits that = (TickUnits) obj;
        return that.tickUnits.equals(this.tickUnits);
    }

}

class CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.TickUnits.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$3k2s4ujdzq45uxawoe2i5d () {
    super("org.jfree.chart.axis.TickUnits.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.TickUnits.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

