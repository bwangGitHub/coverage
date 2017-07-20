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
 * ------------
 * DialCap.java
 * ------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 17-Oct-2007 : Updated equals() method (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A regular dial layer that can be used to draw a cap over the center of 
 * the dial (the base of the dial pointer(s)).
 * 
 * @since 1.0.7
 */
public class DialCap extends AbstractDialLayer implements DialLayer, Cloneable, 
        PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$21waky7g325avijhmch.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = -2929484264982524463L;
  static {
    CodeCoverCoverageCounter$21waky7g325avijhmch.statements[1]++;
  }

    /**
     * The radius of the cap, as a percentage of the framing rectangle.
     */
    private double radius;
    
    /** 
     * The fill paint.  This field is transient because it requires special
     * handling for serialization.
     */
    private transient Paint fillPaint;
    
    /** 
     * The paint used to draw the cap outline (this should never be 
     * <code>null</code>).  This field is transient because it requires
     * special handling for serialization.
     */
    private transient Paint outlinePaint;
    
    /** 
     * The stroke used to draw the cap outline (this should never be 
     * <code>null</code>).   This field is transient because it requires
     * special handling for serialization.
     */
    private transient Stroke outlineStroke;
    
    /** 
     * Creates a new instance of <code>StandardDialBackground</code>.  The 
     * default background paint is <code>Color.white</code>.
     */
    public DialCap() {
        this.radius = 0.05;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[2]++;
        this.fillPaint = Color.white;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[3]++;
        this.outlinePaint = Color.black;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[4]++;
        this.outlineStroke = new BasicStroke(2.0f);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[5]++;
    }
    
    /**
     * Returns the radius of the cap, as a percentage of the dial's framing
     * rectangle.
     *
     * @return The radius.
     *
     * @see #setRadius(double)
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Sets the radius of the cap, as a percentage of the dial's framing
     * rectangle, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     *
     * @param radius  the radius (must be greater than zero).
     *
     * @see #getRadius()
     */
    public void setRadius(double radius) {
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((radius <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[1]++;
            throw new IllegalArgumentException("Requires radius > 0.0.");

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[2]++;}
        this.radius = radius;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[7]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[8]++;
    }
    
    /**
     * Returns the paint used to fill the cap. 
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setFillPaint(Paint)
     */
    public Paint getFillPaint() {
        return this.fillPaint;
    }
    
    /**
     * Sets the paint for the cap background and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getFillPaint()
     */
    public void setFillPaint(Paint paint) {
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[4]++;}
        this.fillPaint = paint;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[10]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[11]++;
    }
        
    /**
     * Returns the paint used to draw the outline of the cap. 
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setOutlinePaint(Paint)
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    /**
     * Sets the paint used to draw the outline of the cap and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getOutlinePaint()
     */
    public void setOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[5]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[6]++;}
        this.outlinePaint = paint;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[13]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[14]++;
    }
        
    /**
     * Returns the stroke used to draw the outline of the cap. 
     *
     * @return The stroke (never <code>null</code>).
     *
     * @see #setOutlineStroke(Stroke)
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    /**
     * Sets the stroke used to draw the outline of the cap and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[7]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[8]++;}
        this.outlineStroke = stroke;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[16]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[17]++;
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

        g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[18]++;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[19]++;
        
        Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, 
                this.radius);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[20]++;
        Ellipse2D e = new Ellipse2D.Double(f.getX(), f.getY(), f.getWidth(), 
                f.getHeight());
        g2.fill(e);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[21]++;
        g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[22]++;
        g2.setStroke(this.outlineStroke);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[23]++;
        g2.draw(e);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[24]++;
        
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[10]++;}
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof DialCap) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[12]++;}
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[27]++;
        DialCap that = (DialCap) obj;
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.radius != that.radius) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[14]++;}
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[16]++;}
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[30]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[18]++;}
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.outlineStroke.equals(that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21waky7g325avijhmch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21waky7g325avijhmch.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21waky7g325avijhmch.branches[20]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[32]++;
        int result = 193;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[33]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.outlinePaint);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[34]++;
        result = 37 * result + this.outlineStroke.hashCode();
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[35]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     *
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some attribute of the cap cannot
     *     be cloned.
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
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[36]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[37]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[38]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[39]++;
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
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[40]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[41]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[42]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$21waky7g325avijhmch.statements[43]++;
    }
    
}

class CodeCoverCoverageCounter$21waky7g325avijhmch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21waky7g325avijhmch ());
  }
    public static long[] statements = new long[44];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.DialCap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$21waky7g325avijhmch () {
    super("org.jfree.chart.plot.dial.DialCap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 43; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.DialCap.java");
      for (int i = 1; i <= 43; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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


