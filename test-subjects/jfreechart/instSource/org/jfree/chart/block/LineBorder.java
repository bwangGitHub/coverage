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
 * LineBorder.java
 * ---------------
 * (C) Copyright 2007, by Christo Zietsman and Contributors.
 *
 * Original Author:  Christo Zietsman;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 16-Mar-2007 : Version 1, contributed by Christo Zietsman with
 *               modifications by DG (DG);
 * 13-Jun-2007 : Don't draw if area doesn't have positive dimensions (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A line border for any {@link AbstractBlock}. 
 * 
 * @since 1.0.5
 */
public class LineBorder implements BlockFrame, Serializable {
  static {
    CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = 4630356736707233924L;
  static {
    CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[1]++;
  }

    /** The line color. */
    private transient Paint paint;
    
    /** The line stroke. */
    private transient Stroke stroke;
    
    /** The insets. */
    private RectangleInsets insets;
    
    /**
     * Creates a default border.
     */
    public LineBorder() {
        this(Color.black, new BasicStroke(1.0f), new RectangleInsets(1.0, 1.0, 
                1.0, 1.0));
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[2]++;
    }
    
    /**
     * Creates a new border with the specified color.
     * 
     * @param paint  the color (<code>null</code> not permitted).
     * @param stroke  the border stroke (<code>null</code> not permitted).
     * @param insets  the insets (<code>null</code> not permitted).
     */    
    public LineBorder(Paint paint, Stroke stroke, RectangleInsets insets) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[2]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[3]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[4]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((insets == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[5]++;
            throw new IllegalArgumentException("Null 'insets' argument.");

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[6]++;}
        this.paint = paint;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[6]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[7]++;
        this.insets = insets;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[8]++;
    }  
    
    /**
     * Returns the paint.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Returns the insets.
     * 
     * @return The insets (never <code>null</code>).
     */
    public RectangleInsets getInsets() {
        return this.insets;
    }

    /**
     * Returns the stroke.
     * 
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getStroke() {
        return this.stroke;
    }
    
    /**
     * Draws the border by filling in the reserved space (in black).
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[9]++;
        double w = area.getWidth();
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[10]++;
        double h = area.getHeight();
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        // if the area has zero height or width, we shouldn't draw anything
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((w <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((h <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[7]++;
            return;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[8]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[12]++;
        double t = this.insets.calculateTopInset(h);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[13]++;
        double b = this.insets.calculateBottomInset(h);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[14]++;
        double l = this.insets.calculateLeftInset(w);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[15]++;
        double r = this.insets.calculateRightInset(w);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[16]++;
        double x = area.getX();
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[17]++;
        double y = area.getY();
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[18]++;
        double x0 = x + l / 2.0;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[19]++;
        double x1 = x + w - r / 2.0;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[20]++;
        double y0 = y + h - b / 2.0;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[21]++;
        double y1 = y + t / 2.0;
        g2.setPaint(getPaint());
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[22]++;
        g2.setStroke(getStroke());
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[23]++;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[24]++;
        Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((t > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[9]++;
            line.setLine(x0, y1, x1, y1);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[26]++;
            g2.draw(line);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[27]++;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[10]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((b > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[11]++;
            line.setLine(x0, y0, x1, y0);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[29]++;
            g2.draw(line);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[30]++;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[12]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((l > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[13]++;
            line.setLine(x0, y0, x0, y1);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[32]++;
            g2.draw(line);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[33]++;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[14]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((r > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[15]++;
            line.setLine(x1, y0, x1, y1);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[35]++;
            g2.draw(line);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[36]++;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[16]++;}        
    }    

    /**
     * Tests this border for equality with an arbitrary instance.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[17]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[18]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj instanceof LineBorder) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[19]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[20]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[39]++;
        LineBorder that = (LineBorder) obj;
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[22]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[24]++;}
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.insets.equals(that.insets)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.branches[26]++;}
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
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[43]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[44]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[45]++;
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
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[46]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[47]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1.statements[48]++;
    }    
}

class CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1 ());
  }
    public static long[] statements = new long[49];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.LineBorder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$mwgfetz77c0rboscnlbvtk1 () {
    super("org.jfree.chart.block.LineBorder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 48; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.LineBorder.java");
      for (int i = 1; i <= 48; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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


                 
