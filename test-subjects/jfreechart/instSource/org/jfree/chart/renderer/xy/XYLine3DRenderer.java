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
 * XYLine3DRenderer.java
 * ---------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 14-Jan-2005 : Added standard header (DG);
 * 01-May-2007 : Fixed equals() and serialization bugs (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.Effect3D;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A XYLineAndShapeRenderer that adds a shadow line to the graph
 * to emulate a 3D-effect.
 */
public class XYLine3DRenderer extends XYLineAndShapeRenderer 
                              implements Effect3D, Serializable {
  static {
    CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 588933208243446087L;
  static {
    CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[1]++;
  }
    
    /** The default x-offset for the 3D effect. */
    public static final double DEFAULT_X_OFFSET = 12.0;
  static {
    CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[2]++;
  }

    /** The default y-offset for the 3D effect. */
    public static final double DEFAULT_Y_OFFSET = 8.0;
  static {
    CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[3]++;
  }

    /** The default wall paint. */
    public static final Paint DEFAULT_WALL_PAINT = new Color(0xDD, 0xDD, 0xDD);
  static {
    CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[4]++;
  }

    /** The size of x-offset for the 3D effect. */
    private double xOffset;

    /** The size of y-offset for the 3D effect. */
    private double yOffset;

    /** The paint used to shade the left and lower 3D wall. */
    private transient Paint wallPaint;

    /**
     * Creates a new renderer.
     */
    public XYLine3DRenderer() {
        this.wallPaint = DEFAULT_WALL_PAINT;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[5]++;
        this.xOffset = DEFAULT_X_OFFSET;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[6]++;
        this.yOffset = DEFAULT_Y_OFFSET;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[7]++;
    }

    /**
     * Returns the x-offset for the 3D effect.
     *
     * @return The 3D effect.
     */
    public double getXOffset() {
        return this.xOffset;
    }

    /**
     * Returns the y-offset for the 3D effect.
     *
     * @return The 3D effect.
     */
    public double getYOffset() {
        return this.yOffset;
    }

    /**
     * Sets the x-offset and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param xOffset  the x-offset.
     */
    public void setXOffset(double xOffset) {
        this.xOffset = xOffset;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[8]++;
        fireChangeEvent();
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[9]++;
    }

    /**
     * Sets the y-offset and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param yOffset  the y-offset.
     */
    public void setYOffset(double yOffset) {
        this.yOffset = yOffset;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[10]++;
        fireChangeEvent();
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[11]++;
    }

    /**
     * Returns the paint used to highlight the left and bottom wall in the plot
     * background.
     *
     * @return The paint.
     */
    public Paint getWallPaint() {
        return this.wallPaint;
    }

    /**
     * Sets the paint used to hightlight the left and bottom walls in the plot 
     * background and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paint  the paint.
     */
    public void setWallPaint(Paint paint) {
        this.wallPaint = paint;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[12]++;
        fireChangeEvent();
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[13]++;
    }

    /**
     * Returns the number of passes through the data that the renderer requires 
     * in order to draw the chart.  Most charts will require a single pass, 
     * but some require two passes.
     *
     * @return The pass count.
     */
    public int getPassCount() {
        return 3;
    }

    /**
     * Returns <code>true</code> if the specified pass involves drawing lines.
     * 
     * @param pass  the pass.
     * 
     * @return A boolean.
     */
    protected boolean isLinePass(int pass) {
        return pass == 0 || pass == 1;
    }

    /**
     * Returns <code>true</code> if the specified pass involves drawing items.
     * 
     * @param pass  the pass.
     * 
     * @return A boolean.
     */
    protected boolean isItemPass(int pass) {
        return pass == 2;
    }

    /**
     * Returns <code>true</code> if the specified pass involves drawing shadows.
     * 
     * @param pass  the pass.
     * 
     * @return A boolean.
     */
    protected boolean isShadowPass (int pass) {
        return pass == 0;
    }

    /**
     * Overrides the method in the subclass to draw a shadow in the first pass.
     * 
     * @param g2  the graphics device.
     * @param pass  the pass.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param shape  the shape.
     */
    protected void drawFirstPassShape(Graphics2D g2,
                                      int pass,
                                      int series,
                                      int item,
                                      Shape shape) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isShadowPass(pass)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[1]++;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getWallPaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[3]++;
                g2.setStroke(getItemStroke(series, item));
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[16]++;
                g2.setPaint(getWallPaint());
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[17]++;
                g2.translate(getXOffset(), getYOffset());
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[18]++;
                g2.draw(shape);
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[19]++;
                g2.translate(-getXOffset(), -getYOffset());
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[20]++;

            } else {
  CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[4]++;}

        }
        else {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[2]++;
            // now draw the real shape
            super.drawFirstPassShape(g2, pass, series, item, shape);
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[21]++;
        }
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[6]++;}
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof XYLine3DRenderer) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[8]++;}
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[24]++;
        XYLine3DRenderer that = (XYLine3DRenderer) obj;
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.xOffset != that.xOffset) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[10]++;}
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.yOffset != that.yOffset) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[12]++;}
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.wallPaint, that.wallPaint)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.branches[14]++;}
        return super.equals(obj);
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
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[28]++;
        this.wallPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[29]++;
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
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[30]++;
        SerialUtilities.writePaint(this.wallPaint, stream);
CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp.statements[31]++;
    }

}

class CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYLine3DRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$21dyarujtxzhn62fhntz55vnx4gw8cjmp () {
    super("org.jfree.chart.renderer.xy.XYLine3DRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYLine3DRenderer.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

