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
 * -------------------
 * DialBackground.java
 * -------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 16-Oct-2007 : The equals() method needs to call super.equals() (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A regular dial layer that can be used to draw the background for a dial.
 * 
 * @since 1.0.7
 */
public class DialBackground extends AbstractDialLayer implements DialLayer, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = -9019069533317612375L;
  static {
    CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[1]++;
  }

    /** 
     * The background paint.  This field is transient because serialization
     * requires special handling.
     */
    private transient Paint paint;
    
    /** 
     * The transformer used when the background paint is an instance of
     * <code>GradientPaint</code>. 
     */
    private GradientPaintTransformer gradientPaintTransformer;
    
    /** 
     * Creates a new instance of <code>DialBackground</code>.  The 
     * default background paint is <code>Color.white</code>.
     */
    public DialBackground() {
        this(Color.white);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[2]++;
    }
    
    /**
     * Creates a new instance of <code>DialBackground</code>.  The 
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @throws IllegalArgumentException if <code>paint</code> is 
     *     <code>null</code>.
     */
    public DialBackground(Paint paint) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[2]++;}
        this.paint = paint;
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[4]++;
        this.gradientPaintTransformer = new StandardGradientPaintTransformer();
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[5]++;
    }
    
    /**
     * Returns the paint used to fill the background. 
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Sets the paint for the dial background and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[4]++;}
        this.paint = paint;
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[7]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[8]++;
    }
    
    /**
     * Returns the transformer used to adjust the coordinates of any 
     * <code>GradientPaint</code> instance used for the background paint.
     *
     * @return The transformer (never <code>null</code>).
     *
     * @see #setGradientPaintTransformer(GradientPaintTransformer)
     */
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;
    }
    
    /**
     * Sets the transformer used to adjust the coordinates of any
     * <code>GradientPaint</code> instance used for the background paint, and
     * sends a {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param t  the transformer (<code>null</code> not permitted).
     *
     * @see #getGradientPaintTransformer()
     */
    public void setGradientPaintTransformer(GradientPaintTransformer t) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[5]++;
            throw new IllegalArgumentException("Null 't' argument.");

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[6]++;}
        this.gradientPaintTransformer = t;
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[10]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[11]++;
    }
    
    /**
     * Returns <code>true</code> to indicate that this layer should be 
     * clipped within the dial window. 
     *
     * @return <code>true</code>.
     */
    public boolean isClippedToWindow() {
        return true;
    }
    
    /**
     * Draws the background to the specified graphics device.  If the dial
     * frame specifies a window, the clipping region will already have been 
     * set to this window before this method is called.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param plot  the plot (ignored here).
     * @param frame  the dial frame (ignored here).
     * @param view  the view rectangle (<code>null</code> not permitted). 
     */
    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[12]++;

        Paint p = this.paint;
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[7]++;
            p = this.gradientPaintTransformer.transform((GradientPaint) p, 
                    view);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[14]++;

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[8]++;}
        g2.setPaint(p);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[15]++;
        g2.fill(view);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[16]++;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[10]++;}
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof DialBackground) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[12]++;}
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[19]++;
        DialBackground that = (DialBackground) obj;
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[14]++;}
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.gradientPaintTransformer.equals(
                that.gradientPaintTransformer)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.branches[16]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[22]++;
        int result = 193;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[23]++;
        result = 37 * result + this.gradientPaintTransformer.hashCode();
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[24]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     *
     * @return The clone.
     *
     * @throws CloneNotSupportedException if some attribute of this instance
     *     cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
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
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[25]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[26]++;
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
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[27]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x.statements[28]++;
    }
    
}

class CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.DialBackground.java";
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

  public CodeCoverCoverageCounter$14gejmsr035nlbxfu18aoxffdjaj4x () {
    super("org.jfree.chart.plot.dial.DialBackground.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.dial.DialBackground.java");
      for (int i = 1; i <= 28; i++) {
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

