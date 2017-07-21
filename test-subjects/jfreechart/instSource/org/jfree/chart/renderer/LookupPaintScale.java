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
 * ---------------------
 * LookupPaintScale.java
 * ---------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Jul-2006 : Version 1 (DG);
 * 31-Jan-2007 : Fixed serialization support (DG);
 * 09-Mar-2007 : Fixed cloning (DG);
 * 14-Jun-2007 : Use double primitive in PaintItem (DG);
 * 
 */

package org.jfree.chart.renderer;

import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A paint scale that uses a lookup table to associate paint instances
 * with data value ranges.
 * 
 * @since 1.0.4
 */
public class LookupPaintScale 
        implements PaintScale, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.ping();
  }


    /**
     * Stores the paint for a value.
     */
    class PaintItem implements Comparable, Serializable {
        
        /** For serialization. */
        static final long serialVersionUID = 698920578512361570L;
        
        /** The value. */
        double value;
        
        /** The paint. */
        transient Paint paint;
        
        /**
         * Creates a new instance.
         * 
         * @param value  the value.
         * @param paint  the paint.
         */
        public PaintItem(double value, Paint paint) {
            this.value = value;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[1]++;
            this.paint = paint;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[2]++;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(Object obj) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[3]++;
            PaintItem that = (PaintItem) obj;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[4]++;
            double d1 = this.value;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[5]++;
            double d2 = that.value;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((d1 > d2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[1]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[2]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((d1 < d2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[3]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[4]++;}
            return 0;
        }

        /**
         * Tests this item for equality with an arbitrary object.
         * 
         * @param obj  the object (<code>null</code> permitted).
         * 
         * @return A boolean.
         */
        public boolean equals(Object obj) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[5]++;
                return true;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[6]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof PaintItem) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[7]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[8]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[10]++;
            PaintItem that = (PaintItem) obj;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.value != that.value) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[9]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[10]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[11]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[12]++;}
            return true;
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
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[13]++;
            SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[14]++;
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
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[15]++;
            this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[16]++;
        }
        
    }
    
    /** For serialization. */
    static final long serialVersionUID = -5239384246251042006L;
  static {
    CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[17]++;
  }
    
    /** The lower bound. */
    private double lowerBound;
    
    /** The upper bound. */
    private double upperBound;
    
    /** The default paint. */
    private transient Paint defaultPaint; 
    
    /** The lookup table. */
    private List lookupTable;
    
    /**
     * Creates a new paint scale.
     */
    public LookupPaintScale() {
        this(0.0, 1.0, Color.lightGray);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[18]++;    
    }
    
    /**
     * Creates a new paint scale with the specified default paint.
     * 
     * @param lowerBound  the lower bound.
     * @param upperBound  the upper bound.
     * @param defaultPaint  the default paint (<code>null</code> not 
     *     permitted).
     */
    public LookupPaintScale(double lowerBound, double upperBound, 
            Paint defaultPaint) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lowerBound >= upperBound) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[13]++;
            throw new IllegalArgumentException(
                    "Requires lowerBound < upperBound.");

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[14]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((defaultPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[16]++;}
        this.lowerBound = lowerBound;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[21]++;
        this.upperBound = upperBound;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[22]++;
        this.defaultPaint = defaultPaint;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[23]++;
        this.lookupTable = new java.util.ArrayList();
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[24]++;
    }
    
    /**
     * Returns the default paint (never <code>null</code>).
     * 
     * @return The default paint.
     */
    public Paint getDefaultPaint() {
        return this.defaultPaint;
    }
    
    /**
     * Returns the lower bound.
     * 
     * @return The lower bound.
     * 
     * @see #getUpperBound()
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper bound.
     * 
     * @return The upper bound.
     * 
     * @see #getLowerBound()
     */
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * Adds an entry to the lookup table.  Any values from <code>n</code> up
     * to but not including the next value in the table take on the specified
     * <code>paint</code>.
     * 
     * @param value  the data value (<code>null</code> not permitted).
     * @param paint  the paint.
     * 
     * @deprecated Use {@link #add(double, Paint)}.
     */
    public void add(Number value, Paint paint) {
        add(value.doubleValue(), paint);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[25]++;
    }
    
    /**
     * Adds an entry to the lookup table.  Any values from <code>n</code> up
     * to but not including the next value in the table take on the specified
     * <code>paint</code>.
     * 
     * @param value  the data value.
     * @param paint  the paint.
     * 
     * @since 1.0.6
     */
    public void add(double value, Paint paint) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[26]++;
        PaintItem item = new PaintItem(value, paint);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[27]++;
        int index = Collections.binarySearch(this.lookupTable, item);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[17]++;
            this.lookupTable.set(index, item);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[18]++;
            this.lookupTable.add(-(index + 1), item);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[30]++;
        }
    }
    
    /**
     * Returns the paint associated with the specified value.
     * 
     * @param value  the value.
     * 
     * @return The paint.
     * 
     * @see #getDefaultPaint()
     */
    public Paint getPaint(double value) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
        
        // handle value outside bounds...
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value < this.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[19]++;
            return this.defaultPaint;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[20]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value > this.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[21]++;
            return this.defaultPaint;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[22]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[33]++;
        
        int count = this.lookupTable.size();
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[34]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[23]++;
            return this.defaultPaint;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[24]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[35]++;

        // handle special case where value is less that item zero
        PaintItem item = (PaintItem) this.lookupTable.get(0);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value < item.value) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[25]++;
            return this.defaultPaint;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[26]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[37]++;

        // for value in bounds, do the lookup...
        int low = 0;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[38]++;
        int high = this.lookupTable.size() - 1;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[39]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
        while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((high - low > 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.loops[1]--;
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.loops[2]--;
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.loops[3]++;
}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[40]++;
            int current = (low + high) / 2;
            item = (PaintItem) this.lookupTable.get(current);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[41]++;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((value >= item.value) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[27]++;
                low = current;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[43]++;

            }
            else {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[28]++;
                high = current;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[44]++;
            }
        }
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[45]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((high > low) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[29]++;
            item = (PaintItem) this.lookupTable.get(high);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[46]++;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[47]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value < item.value) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[31]++;
                item = (PaintItem) this.lookupTable.get(low);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[48]++;

            } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[30]++;}
        return (item != null ? item.paint : this.defaultPaint);
    }
    
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[49]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[33]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[34]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[50]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof LookupPaintScale) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[36]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[51]++;
        LookupPaintScale that = (LookupPaintScale) obj;
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[52]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.lowerBound != that.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[38]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[53]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.upperBound != that.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[40]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[54]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.defaultPaint, that.defaultPaint)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[42]++;}
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[55]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.lookupTable.equals(that.lookupTable)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.branches[44]++;}
        return true;
    }
    
    /**
     * Returns a clone of the instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the
     *     instance.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[56]++;
        LookupPaintScale clone = (LookupPaintScale) super.clone();
        clone.lookupTable = new java.util.ArrayList(this.lookupTable);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[57]++;
        return clone;
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
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[58]++;
        SerialUtilities.writePaint(this.defaultPaint, stream);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[59]++;
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
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[60]++;
        this.defaultPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl.statements[61]++;
    }

}

class CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.LookupPaintScale.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$1rhp0cbw1tqu3jvtev4ycanl6cgvishnl () {
    super("org.jfree.chart.renderer.LookupPaintScale.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.LookupPaintScale.java");
      for (int i = 1; i <= 61; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

