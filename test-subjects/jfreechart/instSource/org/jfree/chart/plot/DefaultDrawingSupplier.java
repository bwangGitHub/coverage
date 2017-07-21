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
 * ---------------------------
 * DefaultDrawingSupplier.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Jeremy Bowman;
 *
 * Changes
 * -------
 * 16-Jan-2003 : Version 1 (DG);
 * 17-Jan-2003 : Added stroke method, renamed DefaultPaintSupplier 
 *               --> DefaultDrawingSupplier (DG)
 * 27-Jan-2003 : Incorporated code from SeriesShapeFactory, originally 
 *               contributed by Jeremy Bowman (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Jun-2007 : Added fillPaintSequence (DG);
 *
 */

 package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import org.jfree.chart.ChartColor;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A default implementation of the {@link DrawingSupplier} interface.  All
 * {@link Plot} instances have a new instance of this class installed by 
 * default.
 */
public class DefaultDrawingSupplier implements DrawingSupplier, Cloneable, 
        PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7339847061039422538L;
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[1]++;
  }
    
    /** The default fill paint sequence. */
    public static final Paint[] DEFAULT_PAINT_SEQUENCE 
            = ChartColor.createDefaultPaintArray();
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[2]++;
  }

    /** The default outline paint sequence. */
    public static final Paint[] DEFAULT_OUTLINE_PAINT_SEQUENCE = new Paint[] {
            Color.lightGray};
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[3]++;
  }

    /** The default fill paint sequence. */
    public static final Paint[] DEFAULT_FILL_PAINT_SEQUENCE = new Paint[] {
            Color.white};
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[4]++;
  }

    /** The default stroke sequence. */
    public static final Stroke[] DEFAULT_STROKE_SEQUENCE = new Stroke[] {
            new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, 
                    BasicStroke.JOIN_BEVEL)};
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[5]++;
  }

    /** The default outline stroke sequence. */
    public static final Stroke[] DEFAULT_OUTLINE_STROKE_SEQUENCE 
            = new Stroke[] {new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, 
                    BasicStroke.JOIN_BEVEL)};
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[6]++;
  }

    /** The default shape sequence. */
    public static final Shape[] DEFAULT_SHAPE_SEQUENCE 
            = createStandardSeriesShapes();
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[7]++;
  }

    /** The paint sequence. */
    private transient Paint[] paintSequence;

    /** The current paint index. */
    private int paintIndex;

    /** The outline paint sequence. */
    private transient Paint[] outlinePaintSequence;

    /** The current outline paint index. */
    private int outlinePaintIndex;

    /** The fill paint sequence. */
    private transient Paint[] fillPaintSequence;

    /** The current fill paint index. */
    private int fillPaintIndex;

    /** The stroke sequence. */
    private transient Stroke[] strokeSequence;

    /** The current stroke index. */
    private int strokeIndex;

    /** The outline stroke sequence. */
    private transient Stroke[] outlineStrokeSequence;

    /** The current outline stroke index. */
    private int outlineStrokeIndex;

    /** The shape sequence. */
    private transient Shape[] shapeSequence;

    /** The current shape index. */
    private int shapeIndex;

    /**
     * Creates a new supplier, with default sequences for fill paint, outline 
     * paint, stroke and shapes.
     */
    public DefaultDrawingSupplier() {

        this(DEFAULT_PAINT_SEQUENCE, DEFAULT_FILL_PAINT_SEQUENCE,
             DEFAULT_OUTLINE_PAINT_SEQUENCE,
             DEFAULT_STROKE_SEQUENCE,
             DEFAULT_OUTLINE_STROKE_SEQUENCE,
             DEFAULT_SHAPE_SEQUENCE);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[8]++;

    }

    /**
     * Creates a new supplier.
     *
     * @param paintSequence  the fill paint sequence.
     * @param outlinePaintSequence  the outline paint sequence.
     * @param strokeSequence  the stroke sequence.
     * @param outlineStrokeSequence  the outline stroke sequence.
     * @param shapeSequence  the shape sequence.
     */
    public DefaultDrawingSupplier(Paint[] paintSequence,
                                  Paint[] outlinePaintSequence,
                                  Stroke[] strokeSequence,
                                  Stroke[] outlineStrokeSequence,
                                  Shape[] shapeSequence) {

        this.paintSequence = paintSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[9]++;
        this.fillPaintSequence = DEFAULT_FILL_PAINT_SEQUENCE;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[10]++;
        this.outlinePaintSequence = outlinePaintSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[11]++;
        this.strokeSequence = strokeSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[12]++;
        this.outlineStrokeSequence = outlineStrokeSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[13]++;
        this.shapeSequence = shapeSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[14]++;

    }

    /**
     * Creates a new supplier.
     *
     * @param paintSequence  the paint sequence.
     * @param fillPaintSequence  the fill paint sequence.
     * @param outlinePaintSequence  the outline paint sequence.
     * @param strokeSequence  the stroke sequence.
     * @param outlineStrokeSequence  the outline stroke sequence.
     * @param shapeSequence  the shape sequence.
     * 
     * @since 1.0.6
     */
    public DefaultDrawingSupplier(Paint[] paintSequence, 
            Paint[] fillPaintSequence, Paint[] outlinePaintSequence,
            Stroke[] strokeSequence, Stroke[] outlineStrokeSequence,
            Shape[] shapeSequence) {

        this.paintSequence = paintSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[15]++;
        this.fillPaintSequence = fillPaintSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[16]++;
        this.outlinePaintSequence = outlinePaintSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[17]++;
        this.strokeSequence = strokeSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[18]++;
        this.outlineStrokeSequence = outlineStrokeSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[19]++;
        this.shapeSequence = shapeSequence;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[20]++;
    }

    /**
     * Returns the next paint in the sequence.
     *
     * @return The paint.
     */
    public Paint getNextPaint() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[21]++;
        Paint result 
            = this.paintSequence[this.paintIndex % this.paintSequence.length];
        this.paintIndex++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[22]++;
        return result;
    }

    /**
     * Returns the next outline paint in the sequence.
     *
     * @return The paint.
     */
    public Paint getNextOutlinePaint() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[23]++;
        Paint result = this.outlinePaintSequence[
                this.outlinePaintIndex % this.outlinePaintSequence.length];
        this.outlinePaintIndex++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[24]++;
        return result;
    }

    /**
     * Returns the next fill paint in the sequence.
     *
     * @return The paint.
     * 
     * @since 1.0.6
     */
    public Paint getNextFillPaint() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[25]++;
        Paint result = this.fillPaintSequence[this.fillPaintIndex 
                % this.fillPaintSequence.length];
        this.fillPaintIndex++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[26]++;
        return result;
    }

    /**
     * Returns the next stroke in the sequence.
     *
     * @return The stroke.
     */
    public Stroke getNextStroke() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[27]++;
        Stroke result = this.strokeSequence[
                this.strokeIndex % this.strokeSequence.length];
        this.strokeIndex++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[28]++;
        return result;
    }

    /**
     * Returns the next outline stroke in the sequence.
     *
     * @return The stroke.
     */
    public Stroke getNextOutlineStroke() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[29]++;
        Stroke result = this.outlineStrokeSequence[
                this.outlineStrokeIndex % this.outlineStrokeSequence.length];
        this.outlineStrokeIndex++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[30]++;
        return result;
    }

    /**
     * Returns the next shape in the sequence.
     *
     * @return The shape.
     */
    public Shape getNextShape() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[31]++;
        Shape result = this.shapeSequence[
                this.shapeIndex % this.shapeSequence.length];
        this.shapeIndex++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[32]++;
        return result;
    }

    /**
     * Creates an array of standard shapes to display for the items in series 
     * on charts.
     *
     * @return The array of shapes.
     */
    public static Shape[] createStandardSeriesShapes() {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[33]++;

        Shape[] result = new Shape[10];
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[34]++;

        double size = 6.0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[35]++;
        double delta = size / 2.0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[36]++;
        int[] xpoints = null;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[37]++;
        int[] ypoints = null;

        // square
        result[0] = new Rectangle2D.Double(-delta, -delta, size, size);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[38]++;
        // circle
        result[1] = new Ellipse2D.Double(-delta, -delta, size, size);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[39]++;

        // up-pointing triangle
        xpoints = intArray(0.0, delta, -delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[40]++;
        ypoints = intArray(-delta, delta, delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[41]++;
        result[2] = new Polygon(xpoints, ypoints, 3);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[42]++;

        // diamond
        xpoints = intArray(0.0, delta, 0.0, -delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[43]++;
        ypoints = intArray(-delta, 0.0, delta, 0.0);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[44]++;
        result[3] = new Polygon(xpoints, ypoints, 4);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[45]++;

        // horizontal rectangle
        result[4] = new Rectangle2D.Double(-delta, -delta / 2, size, size / 2);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[46]++;

        // down-pointing triangle
        xpoints = intArray(-delta, +delta, 0.0);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[47]++;
        ypoints = intArray(-delta, -delta, delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[48]++;
        result[5] = new Polygon(xpoints, ypoints, 3);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[49]++;

        // horizontal ellipse
        result[6] = new Ellipse2D.Double(-delta, -delta / 2, size, size / 2);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[50]++;

        // right-pointing triangle
        xpoints = intArray(-delta, delta, -delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[51]++;
        ypoints = intArray(-delta, 0.0, delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[52]++;
        result[7] = new Polygon(xpoints, ypoints, 3);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[53]++;

        // vertical rectangle
        result[8] = new Rectangle2D.Double(-delta / 2, -delta, size / 2, size);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[54]++;

        // left-pointing triangle
        xpoints = intArray(-delta, delta, delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[55]++;
        ypoints = intArray(0.0, -delta, +delta);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[56]++;
        result[9] = new Polygon(xpoints, ypoints, 3);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[57]++;

        return result;

    }

    /**
     * Tests this object for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[58]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[2]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[59]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultDrawingSupplier) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[4]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[60]++;

        DefaultDrawingSupplier that = (DefaultDrawingSupplier) obj;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[61]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.paintSequence, that.paintSequence)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[6]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[62]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.paintIndex != that.paintIndex) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[8]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[63]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.outlinePaintSequence, 
                that.outlinePaintSequence)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[10]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[64]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.outlinePaintIndex != that.outlinePaintIndex) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[12]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[65]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.strokeSequence, that.strokeSequence)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[14]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[66]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.strokeIndex != that.strokeIndex) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[15]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[16]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[67]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.outlineStrokeSequence, 
                that.outlineStrokeSequence)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[18]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[68]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.outlineStrokeIndex != that.outlineStrokeIndex) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[19]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[20]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[69]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((equalShapes(this.shapeSequence, that.shapeSequence)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[22]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[70]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.shapeIndex != that.shapeIndex) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[24]++;}
        return true;

    }
    
    /**
     * A utility method for testing the equality of two arrays of shapes.
     * 
     * @param s1  the first array (<code>null</code> permitted).
     * @param s2  the second array (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    private boolean equalShapes(Shape[] s1, Shape[] s2) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[71]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s1 == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[25]++;
            return s2 == null;
   
        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[26]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[72]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((s2 == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[27]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[28]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[73]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((s1.length != s2.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[30]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[74]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[1]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < s1.length) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[1]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[2]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[3]++;
}
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[75]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(s1[i], s2[i])) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[31]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.branches[32]++;}
        }
        return true;
    }

    /**
     * Handles serialization.
     *
     * @param stream  the output stream.
     *
     * @throws IOException if there is an I/O problem.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[76]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[77]++;

        int paintCount = this.paintSequence.length;
        stream.writeInt(paintCount);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[78]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[79]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < paintCount) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[4]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[5]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[6]++;
}
            SerialUtilities.writePaint(this.paintSequence[i], stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[80]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[81]++;

        int outlinePaintCount = this.outlinePaintSequence.length;
        stream.writeInt(outlinePaintCount);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[82]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[83]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[7]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < outlinePaintCount) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[7]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[8]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[9]++;
}
            SerialUtilities.writePaint(this.outlinePaintSequence[i], stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[84]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[85]++;

        int strokeCount = this.strokeSequence.length;
        stream.writeInt(strokeCount);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[86]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[87]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[10]++;


int CodeCoverConditionCoverageHelper_C20;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < strokeCount) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[10]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[11]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[12]++;
}
            SerialUtilities.writeStroke(this.strokeSequence[i], stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[88]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[89]++;

        int outlineStrokeCount = this.outlineStrokeSequence.length;
        stream.writeInt(outlineStrokeCount);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[90]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[91]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[13]++;


int CodeCoverConditionCoverageHelper_C21;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < outlineStrokeCount) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[13]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[14]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[15]++;
}
            SerialUtilities.writeStroke(this.outlineStrokeSequence[i], stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[92]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[93]++;

        int shapeCount = this.shapeSequence.length;
        stream.writeInt(shapeCount);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[94]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[95]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[16]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < shapeCount) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[16]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[17]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[18]++;
}
            SerialUtilities.writeShape(this.shapeSequence[i], stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[96]++;
        }

    }

    /**
     * Restores a serialized object.
     *
     * @param stream  the input stream.
     *
     * @throws IOException if there is an I/O problem.
     * @throws ClassNotFoundException if there is a problem loading a class.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[97]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[98]++;

        int paintCount = stream.readInt();
        this.paintSequence = new Paint[paintCount];
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[99]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[100]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[19]++;


int CodeCoverConditionCoverageHelper_C23;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < paintCount) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[19]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[20]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[21]++;
}
            this.paintSequence[i] = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[101]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[102]++;

        int outlinePaintCount = stream.readInt();
        this.outlinePaintSequence = new Paint[outlinePaintCount];
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[103]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[104]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[22]++;


int CodeCoverConditionCoverageHelper_C24;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < outlinePaintCount) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[22]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[23]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[24]++;
}
            this.outlinePaintSequence[i] = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[105]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[106]++;

        int strokeCount = stream.readInt();
        this.strokeSequence = new Stroke[strokeCount];
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[107]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[108]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[25]++;


int CodeCoverConditionCoverageHelper_C25;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < strokeCount) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[25]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[26]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[27]++;
}
            this.strokeSequence[i] = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[109]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[110]++;

        int outlineStrokeCount = stream.readInt();
        this.outlineStrokeSequence = new Stroke[outlineStrokeCount];
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[111]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[112]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[28]++;


int CodeCoverConditionCoverageHelper_C26;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < outlineStrokeCount) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[28]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[29]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[30]++;
}
            this.outlineStrokeSequence[i] = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[113]++;
        }
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[114]++;

        int shapeCount = stream.readInt();
        this.shapeSequence = new Shape[shapeCount];
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[115]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[116]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[31]++;


int CodeCoverConditionCoverageHelper_C27;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < shapeCount) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[31]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[32]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.loops[33]++;
}
            this.shapeSequence[i] = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[117]++;
        }

    }

    /**
     * Helper method to avoid lots of explicit casts in getShape().  Returns
     * an array containing the provided doubles cast to ints.
     *
     * @param a  x
     * @param b  y
     * @param c  z
     *
     * @return int[3] with converted params.
     */
    private static int[] intArray(double a, double b, double c) {
        return new int[] {(int) a, (int) b, (int) c};
    }

    /**
     * Helper method to avoid lots of explicit casts in getShape().  Returns
     * an array containing the provided doubles cast to ints.
     *
     * @param a  x
     * @param b  y
     * @param c  z
     * @param d  t
     *
     * @return int[4] with converted params.
     */
    private static int[] intArray(double a, double b, double c, double d) {
        return new int[] {(int) a, (int) b, (int) c, (int) d};
    }

    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if a component of the supplier does 
     *                                    not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h.statements[118]++;
        DefaultDrawingSupplier clone = (DefaultDrawingSupplier) super.clone(); 
        return clone;
    }
}

class CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h ());
  }
    public static long[] statements = new long[119];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.DefaultDrawingSupplier.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$4dgk7qtxg7g0y81pgd5gd6xlhzbvs0bzxte0yhg50h () {
    super("org.jfree.chart.plot.DefaultDrawingSupplier.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 118; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.DefaultDrawingSupplier.java");
      for (int i = 1; i <= 118; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

