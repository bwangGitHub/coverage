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
 * ------------------
 * BarRenderer3D.java
 * ------------------
 * (C) Copyright 2001-2007, by Serge V. Grachov and Contributors.
 *
 * Original Author:  Serge V. Grachov;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Tin Luu;
 *                   Milo Simpson;
 *                   Richard Atkinson;
 *                   Rich Unger;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 31-Oct-2001 : First version, contributed by Serge V. Grachov (DG);
 * 15-Nov-2001 : Modified to allow for null data values (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Added fix for single category or single series datasets, 
 *               pointed out by Taoufik Romdhane (DG);
 * 24-May-2002 : Incorporated tooltips into chart entities (DG);
 * 11-Jun-2002 : Added check for (permitted) null info object, bug and fix 
 *               reported by David Basten.  Also updated Javadocs. (DG);
 * 19-Jun-2002 : Added code to draw labels on bars (TL);
 * 26-Jun-2002 : Added bar clipping to avoid PRExceptions (DG);
 * 05-Aug-2002 : Small modification to drawCategoryItem method to support URLs 
 *               for HTML image maps (RA);
 * 06-Aug-2002 : Value labels now use number formatter, thanks to Milo 
 *               Simpson (DG);
 * 08-Aug-2002 : Applied fixed in bug id 592218 (DG);
 * 20-Sep-2002 : Added fix for categoryPaint by Rich Unger, and fixed errors 
 *               reported by Checkstyle (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 06-Nov-2002 : Moved to the com.jrefinery.chart.renderer package (DG);
 * 28-Jan-2003 : Added an attribute to control the shading of the left and 
 *               bottom walls in the plot background (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 10-Apr-2003 : Removed category paint usage (DG);
 * 13-May-2003 : Renamed VerticalBarRenderer3D --> BarRenderer3D and merged with
 *               HorizontalBarRenderer3D (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 19-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 08-Oct-2003 : Removed clipping (replaced with flag in CategoryPlot to 
 *               control order in which the data items are processed) (DG);
 * 20-Oct-2003 : Fixed bug (outline stroke not being used for bar 
 *               outlines) (DG);
 * 21-Oct-2003 : Bar width moved into CategoryItemRendererState (DG);
 * 24-Nov-2003 : Fixed bug 846324 (item labels not showing) (DG);
 * 27-Nov-2003 : Added code to respect maxBarWidth setting (DG);
 * 02-Feb-2004 : Fixed bug where 'drawBarOutline' flag is not respected (DG);
 * 10-Feb-2004 : Small change to drawItem() method to make cut-and-paste 
 *               overriding easier (DG);
 * 04-Oct-2004 : Fixed bug with item label positioning when plot alignment is 
 *               horizontal (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 25-Apr-2005 : Override initialise() method to fix bug 1189642 (DG);
 * 09-Jun-2005 : Use addEntityItem from super class (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 07-Dec-2006 : Implemented equals() override (DG);
 * 17-Jan-2007 : Fixed bug in drawDomainGridline() method (DG);
 * 03-Apr-2007 : Fixed bugs in drawBackground() method (DG);
 * 16-Oct-2007 : Fixed bug in range marker drawing (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
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
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A renderer for bars with a 3D effect, for use with the 
 * {@link org.jfree.chart.plot.CategoryPlot} class.
 */
public class BarRenderer3D extends BarRenderer 
                           implements Effect3D, Cloneable, PublicCloneable, 
                                      Serializable {
  static {
    CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7686976503536003636L;
  static {
    CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[1]++;
  }
    
    /** The default x-offset for the 3D effect. */
    public static final double DEFAULT_X_OFFSET = 12.0;
  static {
    CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[2]++;
  }

    /** The default y-offset for the 3D effect. */
    public static final double DEFAULT_Y_OFFSET = 8.0;
  static {
    CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[3]++;
  }

    /** The default wall paint. */
    public static final Paint DEFAULT_WALL_PAINT = new Color(0xDD, 0xDD, 0xDD);
  static {
    CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[4]++;
  }

    /** The size of x-offset for the 3D effect. */
    private double xOffset;

    /** The size of y-offset for the 3D effect. */
    private double yOffset;

    /** The paint used to shade the left and lower 3D wall. */
    private transient Paint wallPaint;

    /**
     * Default constructor, creates a renderer with a default '3D effect'.
     */
    public BarRenderer3D() {
        this(DEFAULT_X_OFFSET, DEFAULT_Y_OFFSET);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[5]++;
    }

    /**
     * Constructs a new renderer with the specified '3D effect'.
     *
     * @param xOffset  the x-offset for the 3D effect.
     * @param yOffset  the y-offset for the 3D effect.
     */
    public BarRenderer3D(double xOffset, double yOffset) {

        super();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[6]++;
        this.xOffset = xOffset;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[7]++;
        this.yOffset = yOffset;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[8]++;
        this.wallPaint = DEFAULT_WALL_PAINT;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[9]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[10]++;
        // set the default item label positions
        ItemLabelPosition p1 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, 
                TextAnchor.TOP_CENTER);
        setBasePositiveItemLabelPosition(p1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[11]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[12]++;
        ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, 
                TextAnchor.TOP_CENTER);
        setBaseNegativeItemLabelPosition(p2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[13]++;

    }

    /**
     * Returns the x-offset for the 3D effect.
     *
     * @return The 3D effect.
     * 
     * @see #getYOffset()
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
     * background, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getWallPaint()
     */
    public void setWallPaint(Paint paint) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[2]++;}
        this.wallPaint = paint;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[16]++;
    }


    /**
     * Initialises the renderer and returns a state object that will be passed 
     * to subsequent calls to the drawItem method.  This method gets called 
     * once at the start of the process of drawing a chart.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data is to be plotted.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  collects chart rendering information for return to caller.
     * 
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Graphics2D g2,
                                                Rectangle2D dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[17]++;

        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), 
                dataArea.getY() + getYOffset(), dataArea.getWidth() 
                - getXOffset(), dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[18]++;
        CategoryItemRendererState state = super.initialise(g2, adjusted, plot, 
                rendererIndex, info);
        return state;
        
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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[19]++;

        float x0 = (float) dataArea.getX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[20]++;
        float x1 = x0 + (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[21]++;
        float x3 = (float) dataArea.getMaxX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[22]++;
        float x2 = x3 - (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[23]++;

        float y0 = (float) dataArea.getMaxY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[24]++;
        float y1 = y0 - (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[25]++;
        float y3 = (float) dataArea.getMinY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[26]++;
        float y2 = y3 + (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[27]++;

        GeneralPath clip = new GeneralPath();
        clip.moveTo(x0, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[28]++;
        clip.lineTo(x0, y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[29]++;
        clip.lineTo(x1, y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[30]++;
        clip.lineTo(x3, y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[31]++;
        clip.lineTo(x3, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[32]++;
        clip.lineTo(x2, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[33]++;
        clip.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[34]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[35]++;

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                plot.getBackgroundAlpha()));
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[36]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[37]++;
        
        // fill background...
        Paint backgroundPaint = plot.getBackgroundPaint();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[38]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[3]++;
            g2.setPaint(backgroundPaint);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[39]++;
            g2.fill(clip);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[40]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[4]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[41]++;

        GeneralPath leftWall = new GeneralPath();
        leftWall.moveTo(x0, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[42]++;
        leftWall.lineTo(x0, y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[43]++;
        leftWall.lineTo(x1, y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[44]++;
        leftWall.lineTo(x1, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[45]++;
        leftWall.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[46]++;
        g2.setPaint(getWallPaint());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[47]++;
        g2.fill(leftWall);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[48]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[49]++;

        GeneralPath bottomWall = new GeneralPath();
        bottomWall.moveTo(x0, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[50]++;
        bottomWall.lineTo(x1, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[51]++;
        bottomWall.lineTo(x3, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[52]++;
        bottomWall.lineTo(x2, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[53]++;
        bottomWall.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[54]++;
        g2.setPaint(getWallPaint());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[55]++;
        g2.fill(bottomWall);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[56]++;

        // highlight the background corners...
        g2.setPaint(Color.lightGray);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[57]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[58]++;
        Line2D corner = new Line2D.Double(x0, y0, x1, y1);
        g2.draw(corner);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[59]++;
        corner.setLine(x1, y1, x1, y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[60]++;
        g2.draw(corner);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[61]++;
        corner.setLine(x1, y1, x3, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[62]++;
        g2.draw(corner);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[63]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[64]++;
                
        // draw background image, if there is one...
        Image backgroundImage = plot.getBackgroundImage();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[65]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((backgroundImage != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[5]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[66]++;
            Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX() 
                    + getXOffset(), dataArea.getY(), 
                    dataArea.getWidth() - getXOffset(), 
                    dataArea.getHeight() - getYOffset());
            plot.drawBackgroundImage(g2, adjusted);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[67]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[6]++;}
        
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[68]++;

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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[69]++;

        float x0 = (float) dataArea.getX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[70]++;
        float x1 = x0 + (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[71]++;
        float x3 = (float) dataArea.getMaxX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[72]++;
        float x2 = x3 - (float) Math.abs(this.xOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[73]++;

        float y0 = (float) dataArea.getMaxY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[74]++;
        float y1 = y0 - (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[75]++;
        float y3 = (float) dataArea.getMinY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[76]++;
        float y2 = y3 + (float) Math.abs(this.yOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[77]++;

        GeneralPath clip = new GeneralPath();
        clip.moveTo(x0, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[78]++;
        clip.lineTo(x0, y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[79]++;
        clip.lineTo(x1, y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[80]++;
        clip.lineTo(x3, y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[81]++;
        clip.lineTo(x3, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[82]++;
        clip.lineTo(x2, y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[83]++;
        clip.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[84]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[85]++;

        // put an outline around the data area...
        Stroke outlineStroke = plot.getOutlineStroke();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[86]++;
        Paint outlinePaint = plot.getOutlinePaint();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[87]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[7]++;
            g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[88]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[89]++;
            g2.draw(clip);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[90]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[8]++;}

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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[91]++;

        Line2D line1 = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[92]++;
        Line2D line2 = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[93]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[94]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[9]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[95]++;
            double y0 = value;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[96]++;
            double y1 = value - getYOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[97]++;
            double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[98]++;
            double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[99]++;
            double x2 = dataArea.getMaxX();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[100]++;
            line2 = new Line2D.Double(x1, y1, x2, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[101]++;

        }
        else {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[10]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[102]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[11]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[103]++;
            double x0 = value;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[104]++;
            double x1 = value + getXOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[105]++;
            double y0 = dataArea.getMaxY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[106]++;
            double y1 = y0 - getYOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[107]++;
            double y2 = dataArea.getMinY();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[108]++;
            line2 = new Line2D.Double(x1, y1, x1, y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[109]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[12]++;}
}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[110]++;
        Paint paint = plot.getDomainGridlinePaint();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[111]++;
        Stroke stroke = plot.getDomainGridlineStroke();
        g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[112]++;
        g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[113]++;
        g2.draw(line1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[114]++;
        g2.draw(line2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[115]++;

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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[116]++;

        Range range = axis.getRange();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[117]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[13]++;
            return;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[14]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[118]++;

        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(),
                dataArea.getY() + getYOffset(), dataArea.getWidth() 
                - getXOffset(), dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[119]++;

        Line2D line1 = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[120]++;
        Line2D line2 = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[121]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[122]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[15]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[123]++;
            double x0 = axis.valueToJava2D(value, adjusted, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[124]++;
            double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[125]++;
            double y0 = dataArea.getMaxY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[126]++;
            double y1 = y0 - getYOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[127]++;
            double y2 = dataArea.getMinY();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[128]++;
            line2 = new Line2D.Double(x1, y1, x1, y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[129]++;

        }
        else {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[16]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[130]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[17]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[131]++;
            double y0 = axis.valueToJava2D(value, adjusted, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[132]++;
            double y1 = y0 - getYOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[133]++;
            double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[134]++;
            double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[135]++;
            double x2 = dataArea.getMaxX();
            line1 = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[136]++;
            line2 = new Line2D.Double(x1, y1, x2, y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[137]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[18]++;}
}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[138]++;
        Paint paint = plot.getRangeGridlinePaint();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[139]++;
        Stroke stroke = plot.getRangeGridlineStroke();
        g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[140]++;
        g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[141]++;
        g2.draw(line1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[142]++;
        g2.draw(line2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[143]++;

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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[144]++;


        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), 
                dataArea.getY() + getYOffset(), dataArea.getWidth() 
                - getXOffset(), dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[145]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[19]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[146]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[147]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[148]++;
            Range range = axis.getRange();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[149]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[21]++;
                return;

            } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[22]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[150]++;

            GeneralPath path = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[151]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[152]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[23]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[153]++;
                float x = (float) axis.valueToJava2D(value, adjusted, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[154]++;
                float y = (float) adjusted.getMaxY();
                path = new GeneralPath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[155]++;
                path.moveTo(x, y);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[156]++;
                path.lineTo((float) (x + getXOffset()), 
                        y - (float) getYOffset());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[157]++;
                path.lineTo((float) (x + getXOffset()), 
                        (float) (adjusted.getMinY() - getYOffset()));
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[158]++;
                path.lineTo(x, (float) adjusted.getMinY());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[159]++;
                path.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[160]++;

            }
            else {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[24]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[161]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[25]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[162]++;
                float y = (float) axis.valueToJava2D(value, adjusted, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[163]++;
                float x = (float) dataArea.getX();
                path = new GeneralPath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[164]++;
                path.moveTo(x, y);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[165]++;
                path.lineTo(x + (float) this.xOffset, y - (float) this.yOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[166]++;
                path.lineTo((float) (adjusted.getMaxX() + this.xOffset), 
                        y - (float) this.yOffset);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[167]++;
                path.lineTo((float) (adjusted.getMaxX()), y);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[168]++;
                path.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[169]++;

            } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[26]++;}
}
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[170]++;
            g2.fill(path);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[171]++;
            g2.setPaint(marker.getOutlinePaint());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[172]++;
            g2.draw(path);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[173]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[174]++;
        
            String label = marker.getLabel();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[175]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[176]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[27]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[177]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[178]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[179]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[180]++;
                Point2D coordinates = calculateRangeMarkerTextAnchorPoint(
                        g2, orientation, dataArea, path.getBounds2D(), 
                        marker.getLabelOffset(), LengthAdjustmentType.EXPAND, 
                        anchor);
                TextUtilities.drawAlignedString(label, g2, 
                        (float) coordinates.getX(), (float) coordinates.getY(), 
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[181]++;

            } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[28]++;}

        
        }
        else {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[20]++;
            super.drawRangeMarker(g2, plot, axis, marker, adjusted);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[182]++;
            // TODO: draw the interval marker with a 3D effect
        }
    }

    /**
     * Draws a 3D bar to represent one data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area for plotting the data.
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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[183]++;
    
        // check the value we are plotting...
        Number dataValue = dataset.getValue(row, column);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[184]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[29]++;
            return;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[30]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[185]++;
        
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[186]++;
        
        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(),
                dataArea.getY() + getYOffset(), 
                dataArea.getWidth() - getXOffset(), 
                dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[187]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[188]++;
        
        double barW0 = calculateBarW0(plot, orientation, adjusted, domainAxis, 
                state, row, column);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[189]++;
        double[] barL0L1 = calculateBarL0L1(value);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[190]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((barL0L1 == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[31]++;
            return;
  // the bar is not visible
        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[32]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[191]++;

        RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[192]++;
        double transL0 = rangeAxis.valueToJava2D(barL0L1[0], adjusted, edge);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[193]++;
        double transL1 = rangeAxis.valueToJava2D(barL0L1[1], adjusted, edge);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[194]++;
        double barL0 = Math.min(transL0, transL1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[195]++;
        double barLength = Math.abs(transL1 - transL0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[196]++;
        
        // draw the bar...
        Rectangle2D bar = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[197]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[33]++;
            bar = new Rectangle2D.Double(barL0, barW0, barLength, 
                    state.getBarWidth());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[198]++;

        }
        else {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[34]++;
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), 
                    barLength);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[199]++;
        }
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[200]++;
        Paint itemPaint = getItemPaint(row, column);
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[201]++;
        g2.fill(bar);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[202]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[203]++;

        double x0 = bar.getMinX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[204]++;
        double x1 = x0 + getXOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[205]++;
        double x2 = bar.getMaxX();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[206]++;
        double x3 = x2 + getXOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[207]++;
        
        double y0 = bar.getMinY() - getYOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[208]++;
        double y1 = bar.getMinY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[209]++;
        double y2 = bar.getMaxY() - getYOffset();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[210]++;
        double y3 = bar.getMaxY();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[211]++;
        
        GeneralPath bar3dRight = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[212]++;
        GeneralPath bar3dTop = null;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[213]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((barLength > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[35]++;
            bar3dRight = new GeneralPath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[214]++;
            bar3dRight.moveTo((float) x2, (float) y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[215]++;
            bar3dRight.lineTo((float) x2, (float) y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[216]++;
            bar3dRight.lineTo((float) x3, (float) y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[217]++;
            bar3dRight.lineTo((float) x3, (float) y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[218]++;
            bar3dRight.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[219]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[220]++;
int CodeCoverConditionCoverageHelper_C19;

            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((itemPaint instanceof Color) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[37]++;
                g2.setPaint(((Color) itemPaint).darker());
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[221]++;

            } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[38]++;}
            g2.fill(bar3dRight);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[222]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[36]++;}

        bar3dTop = new GeneralPath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[223]++;
        bar3dTop.moveTo((float) x0, (float) y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[224]++;
        bar3dTop.lineTo((float) x1, (float) y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[225]++;
        bar3dTop.lineTo((float) x3, (float) y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[226]++;
        bar3dTop.lineTo((float) x2, (float) y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[227]++;
        bar3dTop.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[228]++;
        g2.fill(bar3dTop);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[229]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[230]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[39]++;
            g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[231]++;
            g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[232]++;
            g2.draw(bar);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[233]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[234]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((bar3dRight != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[41]++;
                g2.draw(bar3dRight);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[235]++;

            } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[42]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[236]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((bar3dTop != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[43]++;
                g2.draw(bar3dTop);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[237]++;

            } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[40]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[238]++;

        CategoryItemLabelGenerator generator 
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[239]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[45]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    (value < 0.0));
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[240]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[46]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[241]++;        

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[242]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[47]++;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[243]++;
            GeneralPath barOutline = new GeneralPath();
            barOutline.moveTo((float) x0, (float) y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[244]++;
            barOutline.lineTo((float) x0, (float) y1);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[245]++;
            barOutline.lineTo((float) x1, (float) y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[246]++;
            barOutline.lineTo((float) x3, (float) y0);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[247]++;
            barOutline.lineTo((float) x3, (float) y2);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[248]++;
            barOutline.lineTo((float) x2, (float) y3);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[249]++;
            barOutline.closePath();
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[250]++;
            addItemEntity(entities, dataset, row, column, barOutline);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[251]++;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[48]++;}

    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[252]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[49]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[50]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[253]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj instanceof BarRenderer3D) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[52]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[254]++;
        BarRenderer3D that = (BarRenderer3D) obj;
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[255]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.xOffset != that.xOffset) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[54]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[256]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.yOffset != that.yOffset) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[56]++;}
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[257]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.wallPaint, that.wallPaint)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.branches[58]++;}
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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[258]++;
        SerialUtilities.writePaint(this.wallPaint, stream);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[259]++;
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
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[260]++;
        this.wallPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9.statements[261]++;
    }

}

class CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9 ());
  }
    public static long[] statements = new long[262];
    public static long[] branches = new long[59];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[30];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.BarRenderer3D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$5iq5lyu1jooggdqbvnwugjxap2e9 () {
    super("org.jfree.chart.renderer.category.BarRenderer3D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 261; i++) {
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
    log.startNamedSection("org.jfree.chart.renderer.category.BarRenderer3D.java");
      for (int i = 1; i <= 261; i++) {
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

