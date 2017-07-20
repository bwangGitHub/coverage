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
 * StrokeMap.java
 * --------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 27-Sep-2006 : Version 1 (DG);
 *
 */

package org.jfree.chart;

import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;

/**
 * A storage structure that maps <code>Comparable</code> instances with
 * <code>Stroke</code> instances.  
 * <br><br>
 * To support cloning and serialization, you should only use keys that are 
 * cloneable and serializable.  Special handling for the <code>Stroke</code>
 * instances is included in this class.
 * 
 * @since 1.0.3
 */
public class StrokeMap implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -8148916785963525169L;
  static {
    CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[1]++;
  }

    /** Storage for the keys and values. */
    private transient Map store;
    
    /**
     * Creates a new (empty) map.
     */
    public StrokeMap() {
        this.store = new TreeMap();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[2]++;    
    }
    
    /**
     * Returns the stroke associated with the specified key, or 
     * <code>null</code>.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @return The stroke, or <code>null</code>.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     */
    public Stroke getStroke(Comparable key) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[1]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[2]++;}
        return (Stroke) this.store.get(key);
    }
    
    /**
     * Returns <code>true</code> if the map contains the specified key, and
     * <code>false</code> otherwise.
     * 
     * @param key  the key.
     * 
     * @return <code>true</code> if the map contains the specified key, and
     * <code>false</code> otherwise.
     */
    public boolean containsKey(Comparable key) {
        return this.store.containsKey(key);
    }
    
    /**
     * Adds a mapping between the specified <code>key</code> and 
     * <code>stroke</code> values.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * @param stroke  the stroke.
     */
    public void put(Comparable key, Stroke stroke) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[3]++; 
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[4]++;}
        this.store.put(key, stroke);
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[5]++;
    }
    
    /**
     * Resets the map to empty.
     */
    public void clear() {
        this.store.clear();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[6]++;
    }
    
    /**
     * Tests this map for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[6]++;}
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof StrokeMap) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[8]++;}
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[9]++;
        StrokeMap that = (StrokeMap) obj;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.store.size() != that.store.size()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[10]++;}
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[11]++;
        Set keys = this.store.keySet();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[12]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[1]--;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[2]--;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[3]++;
}
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[14]++;
            Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[15]++;
            Stroke s1 = getStroke(key);
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[16]++;
            Stroke s2 = that.getStroke(key);
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(s1, s2)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[11]++;
                return false;

            } else {
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.branches[12]++;}
        }
        return true;
    }
    
    /**
     * Returns a clone of this <code>StrokeMap</code>.
     * 
     * @return A clone of this instance.
     * 
     * @throws CloneNotSupportedException if any key is not cloneable.
     */
    public Object clone() throws CloneNotSupportedException {
        // TODO: I think we need to make sure the keys are actually cloned,
        // whereas the stroke instances are always immutable so they're OK
        return super.clone();
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[18]++;
        stream.writeInt(this.store.size());
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[19]++;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[20]++;
        Set keys = this.store.keySet();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[21]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[4]--;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[5]--;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[6]++;
}
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[23]++;
            Comparable key = (Comparable) iterator.next();
            stream.writeObject(key);
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[24]++;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[25]++;
            Stroke stroke = getStroke(key);
            SerialUtilities.writeStroke(stroke, stream);
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[26]++;
        }
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[27]++;
        this.store = new TreeMap();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[28]++;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[29]++;
        int keyCount = stream.readInt();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[30]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < keyCount) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[7]--;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[8]--;
  CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.loops[9]++;
}
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[31]++;
            Comparable key = (Comparable) stream.readObject();
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[32]++;
            Stroke stroke = SerialUtilities.readStroke(stream);
            this.store.put(key, stroke);
CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt.statements[33]++;
        }
    }
    
}

class CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.chart.StrokeMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$3imio0yqjhz81hm71rfbdt () {
    super("org.jfree.chart.StrokeMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.StrokeMap.java");
      for (int i = 1; i <= 33; i++) {
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
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

