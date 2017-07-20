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
 * LineRenderer3D.java
 * -------------------
 * (C) Copyright 2004-2007, by Tobias Selb and Contributors.
 *
 * Original Author:  Tobias Selb (http://www.uepselon.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 15-Oct-2004 : Version 1 (TS);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities class to translate shapes (DG);
 * 26-Jan-2005 : Update for changes in super class (DG);
 * 13-Apr-2005 : Check item visibility in drawItem() method (DG);
 * 09-Jun-2005 : Use addItemEntity() in drawItem() method (DG);
 * 10-Jun-2005 : Fixed capitalisation of setXOffset() and setYOffset() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Fixed equals() and serialization (DG);
 * 17-Jan-2007 : Fixed bug in drawDomainGridline() method and added
 *               argument check to setWallPaint() (DG);
 * 03-Apr-2007 : Fixed bugs in drawBackground() method (DG);
 * 16-Oct-2007 : Fixed bug in range marker drawing (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.Effect3D;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ShapeUtilities;

/**
 * A line renderer with a 3D effect.
 */
public class LineRenderer3D extends LineAndShapeRenderer 
                            implements Effect3D, Serializable {
  static {
    CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.ping();
  }

   
    /** For serialization. */
    private static final long serialVersionUID = 5467931468380928736L;
  static {
    CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[1]++;
  }
    
    /** The default x-offset for the 3D effect. */
    public static final double DEFAULT_X_OFFSET = 12.0;
  static {
    CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[2]++;
  }

    /** The default y-offset for the 3D effect. */
    public static final double DEFAULT_Y_OFFSET = 8.0;
  static {
    CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[3]++;
  }
   
    /** The default wall paint. */
    public static final Paint DEFAULT_WALL_PAINT = new Color(0xDD, 0xDD, 0xDD);
  static {
    CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[4]++;
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
    public LineRenderer3D() {
        super(true, false);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[5]++;  //Create a line renderer only
        this.xOffset = DEFAULT_X_OFFSET;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[6]++;
        this.yOffset = DEFAULT_Y_OFFSET;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[7]++;
        this.wallPaint = DEFAULT_WALL_PAINT;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[8]++;
    }
   
    /**
     * Returns the x-offset for the 3D effect.
     *
     * @return The x-offset.
     * 
     * @see #setXOffset(double)
     * @see #getYOffset()
     */
    public double getXOffset() {
        return this.xOffset;
    }

    /**
     * Returns the y-offset for the 3D effect.
     *
     * @return The y-offset.
     * 
     * @see #setYOffset(double)
     * @see #getXOffset()
     */
    public double getYOffset() {
        return this.yOffset;
    }
   
    /**
     * Sets the x-offset and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param xOffset  the x-offset.
     * 
     * @see #getXOffset()
     */
    public void setXOffset(double xOffset) {
        this.xOffset = xOffset;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[9]++;
        fireChangeEvent();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[10]++;
    }

    /**
     * Sets the y-offset and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param yOffset  the y-offset.
     * 
     * @see #getYOffset()
     */
    public void setYOffset(double yOffset) {
        this.yOffset = yOffset;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[11]++;
        fireChangeEvent();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[12]++;
    }

    /**
     * Returns the paint used to highlight the left and bottom wall in the plot
     * background.
     *
     * @return The paint.
     * 
     * @see #setWallPaint(Paint)
     */
    public Paint getWallPaint() {
        return this.wallPaint;
    }

    /**
     * Sets the paint used to hightlight the left and bottom walls in the plot
     * background, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getWallPaint()
     */
    public void setWallPaint(Paint paint) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[2]++;}
        this.wallPaint = paint;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[14]++;
        fireChangeEvent();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[15]++;
    }
   
    /**
     * Draws the background for the plot.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area inside the axes.
     */
    public void drawBackground(Graphics2D g2, CategoryPlot plot, 
                               Rectangle2D dataArea) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[16]++;

        float x0 = (float) dataArea.getX();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[17]++;
        float x1 = x0 + (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[18]++;
        float x3 = (float) dataArea.getMaxX();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[19]++;
        float x2 = x3 - (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[20]++;

        float y0 = (float) dataArea.getMaxY();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[21]++;
        float y1 = y0 - (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[22]++;
        float y3 = (float) dataArea.getMinY();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[23]++;
        float y2 = y3 + (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[24]++;

        GeneralPath clip = new GeneralPath();
        clip.moveTo(x0, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[25]++;
        clip.lineTo(x0, y2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[26]++;
        clip.lineTo(x1, y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[27]++;
        clip.lineTo(x3, y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[28]++;
        clip.lineTo(x3, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[29]++;
        clip.lineTo(x2, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[30]++;
        clip.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[31]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[32]++;

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                plot.getBackgroundAlpha()));
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[33]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[34]++;

        // fill background...
        Paint backgroundPaint = plot.getBackgroundPaint();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[35]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[3]++;
            g2.setPaint(backgroundPaint);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[36]++;
            g2.fill(clip);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[37]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[4]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[38]++;

        GeneralPath leftWall = new GeneralPath();
        leftWall.moveTo(x0, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[39]++;
        leftWall.lineTo(x0, y2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[40]++;
        leftWall.lineTo(x1, y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[41]++;
        leftWall.lineTo(x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[42]++;
        leftWall.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[43]++;
        g2.setPaint(getWallPaint());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[44]++;
        g2.fill(leftWall);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[45]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[46]++;

        GeneralPath bottomWall = new GeneralPath();
        bottomWall.moveTo(x0, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[47]++;
        bottomWall.lineTo(x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[48]++;
        bottomWall.lineTo(x3, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[49]++;
        bottomWall.lineTo(x2, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[50]++;
        bottomWall.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[51]++;
        g2.setPaint(getWallPaint());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[52]++;
        g2.fill(bottomWall);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[53]++;

        // higlight the background corners...
        g2.setPaint(Color.lightGray);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[54]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[55]++;
        Line2D corner = new Line2D.Double(x0, y0, x1, y1);
        g2.draw(corner);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[56]++;
        corner.setLine(x1, y1, x1, y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[57]++;
        g2.draw(corner);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[58]++;
        corner.setLine(x1, y1, x3, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[59]++;
        g2.draw(corner);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[60]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[61]++;

        // draw background image, if there is one...
        Image backgroundImage = plot.getBackgroundImage();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[62]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((backgroundImage != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[5]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[63]++;
            Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX() 
                    + getXOffset(), dataArea.getY(), 
                    dataArea.getWidth() - getXOffset(), 
                    dataArea.getHeight() - getYOffset());
            plot.drawBackgroundImage(g2, adjusted);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[64]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[6]++;}
        
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[65]++;

    }

    /**
     * Draws the outline for the plot.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area inside the axes.
     */
    public void drawOutline(Graphics2D g2, CategoryPlot plot, 
                            Rectangle2D dataArea) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[66]++;

        float x0 = (float) dataArea.getX();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[67]++;
        float x1 = x0 + (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[68]++;
        float x3 = (float) dataArea.getMaxX();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[69]++;
        float x2 = x3 - (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[70]++;

        float y0 = (float) dataArea.getMaxY();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[71]++;
        float y1 = y0 - (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[72]++;
        float y3 = (float) dataArea.getMinY();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[73]++;
        float y2 = y3 + (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[74]++;

        GeneralPath clip = new GeneralPath();
        clip.moveTo(x0, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[75]++;
        clip.lineTo(x0, y2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[76]++;
        clip.lineTo(x1, y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[77]++;
        clip.lineTo(x3, y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[78]++;
        clip.lineTo(x3, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[79]++;
        clip.lineTo(x2, y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[80]++;
        clip.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[81]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[82]++;

        // put an outline around the data area...
        Stroke outlineStroke = plot.getOutlineStroke();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[83]++;
        Paint outlinePaint = plot.getOutlinePaint();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[84]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[7]++;
            g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[85]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[86]++;
            g2.draw(clip);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[87]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[8]++;}

    }

    /**
     * Draws a grid line against the domain axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area for plotting data (not yet adjusted for any 
     *                  3D effect).
     * @param value  the Java2D value at which the grid line should be drawn.
     *
     */
    public void drawDomainGridline(Graphics2D g2,
                                   CategoryPlot plot,
                                   Rectangle2D dataArea,
                                   double value) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[88]++;

        Line2D line1 = null;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[89]++;
        Line2D line2 = null;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[90]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[91]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[9]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[92]++;
            double y0 = value;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[93]++;
            double y1 = value - getYOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[94]++;
            double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[95]++;
            double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[96]++;
            double x2 = dataArea.getMaxX();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[97]++;
            line2 = new Line2D.Double(x1, y1, x2, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[98]++;

        }
        else {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[10]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[99]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[11]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[100]++;
            double x0 = value;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[101]++;
            double x1 = value + getXOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[102]++;
            double y0 = dataArea.getMaxY();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[103]++;
            double y1 = y0 - getYOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[104]++;
            double y2 = dataArea.getMinY();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[105]++;
            line2 = new Line2D.Double(x1, y1, x1, y2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[106]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[12]++;}
}
        g2.setPaint(plot.getDomainGridlinePaint());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[107]++;
        g2.setStroke(plot.getDomainGridlineStroke());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[108]++;
        g2.draw(line1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[109]++;
        g2.draw(line2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[110]++;

    }

    /**
     * Draws a grid line against the range axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any 
     *                  3D effect).
     * @param value  the value at which the grid line should be drawn.
     *
     */
    public void drawRangeGridline(Graphics2D g2,
                                  CategoryPlot plot,
                                  ValueAxis axis,
                                  Rectangle2D dataArea,
                                  double value) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[111]++;

        Range range = axis.getRange();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[112]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[13]++;
            return;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[14]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[113]++;

        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(),
                dataArea.getY() + getYOffset(),
                dataArea.getWidth() - getXOffset(),
                dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[114]++;

        Line2D line1 = null;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[115]++;
        Line2D line2 = null;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[116]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[117]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[15]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[118]++;
            double x0 = axis.valueToJava2D(value, adjusted, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[119]++;
            double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[120]++;
            double y0 = dataArea.getMaxY();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[121]++;
            double y1 = y0 - getYOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[122]++;
            double y2 = dataArea.getMinY();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[123]++;
            line2 = new Line2D.Double(x1, y1, x1, y2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[124]++;

        }
        else {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[16]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[125]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[17]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[126]++;
            double y0 = axis.valueToJava2D(value, adjusted,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[127]++;
            double y1 = y0 - getYOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[128]++;
            double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[129]++;
            double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[130]++;
            double x2 = dataArea.getMaxX();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[131]++;
            line2 = new Line2D.Double(x1, y1, x2, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[132]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[18]++;}
}
        g2.setPaint(plot.getRangeGridlinePaint());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[133]++;
        g2.setStroke(plot.getRangeGridlineStroke());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[134]++;
        g2.draw(line1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[135]++;
        g2.draw(line2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[136]++;

    }

    /**
     * Draws a range marker.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param marker  the marker.
     * @param dataArea  the area for plotting data (not including 3D effect).
     */
    public void drawRangeMarker(Graphics2D g2,
                                CategoryPlot plot,
                                ValueAxis axis,
                                Marker marker,
                                Rectangle2D dataArea) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[137]++;

        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), 
                dataArea.getY() + getYOffset(), 
                dataArea.getWidth() - getXOffset(), 
                dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[138]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[19]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[139]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[140]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[141]++;
            Range range = axis.getRange();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[142]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[21]++;
                return;

            } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[22]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[143]++;

            GeneralPath path = null;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[144]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[145]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[23]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[146]++;
                float x = (float) axis.valueToJava2D(value, adjusted, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[147]++;
                float y = (float) adjusted.getMaxY();
                path = new GeneralPath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[148]++;
                path.moveTo(x, y);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[149]++;
                path.lineTo((float) (x + getXOffset()), 
                        y - (float) getYOffset());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[150]++;
                path.lineTo((float) (x + getXOffset()), 
                        (float) (adjusted.getMinY() - getYOffset()));
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[151]++;
                path.lineTo(x, (float) adjusted.getMinY());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[152]++;
                path.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[153]++;

            }
            else {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[24]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[154]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[25]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[155]++;
                float y = (float) axis.valueToJava2D(value, adjusted, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[156]++;
                float x = (float) dataArea.getX();
                path = new GeneralPath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[157]++;
                path.moveTo(x, y);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[158]++;
                path.lineTo(x + (float) this.xOffset, y - (float) this.yOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[159]++;
                path.lineTo((float) (adjusted.getMaxX() + this.xOffset), 
                        y - (float) this.yOffset);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[160]++;
                path.lineTo((float) (adjusted.getMaxX()), y);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[161]++;
                path.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[162]++;

            } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[26]++;}
}
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[163]++;
            g2.fill(path);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[164]++;
            g2.setPaint(marker.getOutlinePaint());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[165]++;
            g2.draw(path);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[166]++;

        }
        else {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[20]++;
            super.drawRangeMarker(g2, plot, axis, marker, adjusted);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[167]++;
            // TODO: draw the interval marker with a 3D effect
        }
    }
   
   /**
     * Draw a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area in which the data is drawn.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         CategoryItemRendererState state,
                         Rectangle2D dataArea,
                         CategoryPlot plot,
                         CategoryAxis domainAxis,
                         ValueAxis rangeAxis,
                         CategoryDataset dataset,
                         int row,
                         int column,
                         int pass) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[168]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((getItemVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[27]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[28]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[169]++;
        
        // nothing is drawn for null...
        Number v = dataset.getValue(row, column);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[170]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[29]++;
            return;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[30]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[171]++;
       
        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(),
                dataArea.getY() + getYOffset(), 
                dataArea.getWidth() - getXOffset(),
                dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[172]++;
       
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[173]++;

        // current data point...
        double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                adjusted, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[174]++;
        double value = v.doubleValue();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[175]++;
        double y1 = rangeAxis.valueToJava2D(value, adjusted, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[176]++;

        Shape shape = getItemShape(row, column);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[177]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[31]++;
            shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[178]++;

        }
        else {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[32]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[179]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[33]++;
            shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[180]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[34]++;}
}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[181]++;
int CodeCoverConditionCoverageHelper_C18;
       
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((getItemLineVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[35]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[182]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((column != 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[37]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[183]++;

                Number previousValue = dataset.getValue(row, column - 1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[184]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((previousValue != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[39]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[185]++;

                    // previous data point...
                    double previous = previousValue.doubleValue();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[186]++;
                    double x0 = domainAxis.getCategoryMiddle(column - 1, 
                            getColumnCount(), adjusted, 
                            plot.getDomainAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[187]++;
                    double y0 = rangeAxis.valueToJava2D(previous, adjusted, 
                            plot.getRangeAxisEdge());
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[188]++;

                    double x2 = x0 + getXOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[189]++;
                    double y2 = y0 - getYOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[190]++;
                    double x3 = x1 + getXOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[191]++;
                    double y3 = y1 - getYOffset();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[192]++;
                   
                    GeneralPath clip = new GeneralPath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[193]++;
int CodeCoverConditionCoverageHelper_C21;
                   
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[41]++;
                        clip.moveTo((float) y0, (float) x0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[194]++;
                        clip.lineTo((float) y1, (float) x1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[195]++;
                        clip.lineTo((float) y3, (float) x3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[196]++;
                        clip.lineTo((float) y2, (float) x2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[197]++;
                        clip.lineTo((float) y0, (float) x0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[198]++;
                        clip.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[199]++;

                    }
                    else {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[42]++;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[200]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[43]++;
                        clip.moveTo((float) x0, (float) y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[201]++;
                        clip.lineTo((float) x1, (float) y1);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[202]++;
                        clip.lineTo((float) x3, (float) y3);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[203]++;
                        clip.lineTo((float) x2, (float) y2);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[204]++;
                        clip.lineTo((float) x0, (float) y0);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[205]++;
                        clip.closePath();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[206]++;

                    } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[44]++;}
}
                   
                    g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[207]++;
                    g2.fill(clip);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[208]++;
                    g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[209]++;
                    g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[210]++;
                    g2.draw(clip);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[211]++;

                } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[40]++;}

            } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[38]++;}

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[36]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[212]++;
int CodeCoverConditionCoverageHelper_C23;

        // draw the item label if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[45]++;
            drawItemLabel(g2, orientation, dataset, row, column, x1, y1, 
                    (value < 0.0));
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[213]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[46]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[214]++;

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[215]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[47]++;
            addItemEntity(entities, dataset, row, column, shape);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[216]++;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[48]++;}

    }
    
    /**
     * Checks this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[217]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[49]++;
            return true;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[50]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[218]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj instanceof LineRenderer3D) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[52]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[219]++;
        LineRenderer3D that = (LineRenderer3D) obj;
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[220]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.xOffset != that.xOffset) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[54]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[221]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.yOffset != that.yOffset) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[56]++;}
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[222]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.wallPaint, that.wallPaint)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.branches[58]++;}
        return super.equals(obj);
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
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[223]++;
        SerialUtilities.writePaint(this.wallPaint, stream);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[224]++;
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
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[225]++;
        this.wallPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081.statements[226]++;
    }

}

class CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081 ());
  }
    public static long[] statements = new long[227];
    public static long[] branches = new long[59];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[30];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.LineRenderer3D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 29; i++) {
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

  public CodeCoverCoverageCounter$196pvpqxoy6egqqqeub9ocbjjod081 () {
    super("org.jfree.chart.renderer.category.LineRenderer3D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 226; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 58; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 29; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.LineRenderer3D.java");
      for (int i = 1; i <= 226; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 58; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 29; i++) {
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

