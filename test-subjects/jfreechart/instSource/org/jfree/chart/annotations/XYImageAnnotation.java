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
 * ----------------------
 * XYImageAnnotation.java
 * ----------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Mike Harris;
 *
 * Changes:
 * --------
 * 01-Dec-2003 : Version 1 (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 18-May-2004 : Fixed bug with plot orientation (DG);
 * 29-Sep-2004 : Now extends AbstractXYAnnotation, with modified draw() 
 *               method signature and updated equals() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Added anchor attribute (see patch 1584860 from 
 *               Mike Harris) (DG); 
 */

package org.jfree.chart.annotations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * An annotation that allows an image to be placed at some location on 
 * an {@link XYPlot}.
 * 
 * TODO:  implement serialization properly (image is not serializable).
 */
public class XYImageAnnotation extends AbstractXYAnnotation
                               implements Cloneable, PublicCloneable, 
                                          Serializable {
  static {
    CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4364694501921559958L;
  static {
    CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[1]++;
  }
    
    /** The x-coordinate (in data space). */
    private double x;

    /** The y-coordinate (in data space). */
    private double y;

    /** The image. */
    private transient Image image;

    /** 
     * The image anchor point. 
     * 
     * @since 1.0.4
     */
    private RectangleAnchor anchor;
    
    /**
     * Creates a new annotation to be displayed at the specified (x, y) 
     * location.
     *
     * @param x  the x-coordinate (in data space).
     * @param y  the y-coordinate (in data space).
     * @param image  the image (<code>null</code> not permitted).
     */
    public XYImageAnnotation(double x, double y, Image image) {
        this(x, y, image, RectangleAnchor.CENTER);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[2]++;
    }
    
    /**
     * Creates a new annotation to be displayed at the specified (x, y) 
     * location.
     *
     * @param x  the x-coordinate (in data space).
     * @param y  the y-coordinate (in data space).
     * @param image  the image (<code>null</code> not permitted).
     * @param anchor  the image anchor (<code>null</code> not permitted).
     * 
     * @since 1.0.4
     */
    public XYImageAnnotation(double x, double y, Image image, 
            RectangleAnchor anchor) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((image == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[1]++;
            throw new IllegalArgumentException("Null 'image' argument.");
      
        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[2]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[3]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[4]++;}
        this.x = x;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[5]++;
        this.y = y;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[6]++;
        this.image = image;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[7]++;
        this.anchor = anchor;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[8]++;
    }    
    
    /**
     * Returns the x-coordinate (in data space) for the annotation.
     * 
     * @return The x-coordinate.
     * 
     * @since 1.0.4
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Returns the y-coordinate (in data space) for the annotation.
     * 
     * @return The y-coordinate.
     * 
     * @since 1.0.4
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Returns the image for the annotation.
     * 
     * @return The image.
     * 
     * @since 1.0.4
     */
    public Image getImage() {
        return this.image;
    }
    
    /**
     * Returns the image anchor for the annotation.
     * 
     * @return The image anchor.
     * 
     * @since 1.0.4
     */
    public RectangleAnchor getImageAnchor() {
        return this.anchor;
    }

    /**
     * Draws the annotation.  This method is called by the drawing code in the 
     * {@link XYPlot} class, you don't normally need to call this method 
     * directly.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  if supplied, this info object will be populated with
     *              entity information.
     */
    public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis, 
                     int rendererIndex,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[9]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[10]++;
        AxisLocation domainAxisLocation = plot.getDomainAxisLocation();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[11]++;
        AxisLocation rangeAxisLocation = plot.getRangeAxisLocation();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[12]++;
        RectangleEdge domainEdge 
            = Plot.resolveDomainAxisLocation(domainAxisLocation, orientation);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[13]++;
        RectangleEdge rangeEdge 
            = Plot.resolveRangeAxisLocation(rangeAxisLocation, orientation);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[14]++;
        float j2DX 
            = (float) domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[15]++;
        float j2DY 
            = (float) rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[16]++;
        float xx = 0.0f;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[17]++;
        float yy = 0.0f;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[5]++;
            xx = j2DY;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[19]++;
            yy = j2DX;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[20]++;

        }
        else {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[6]++;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[21]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[7]++;
            xx = j2DX;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[22]++;
            yy = j2DY;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[23]++;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[8]++;}
}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[24]++;
        int w = this.image.getWidth(null);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[25]++;
        int h = this.image.getHeight(null);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[26]++;
        
        Rectangle2D imageRect = new Rectangle2D.Double(0, 0, w, h);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[27]++;
        Point2D anchorPoint = RectangleAnchor.coordinates(imageRect, 
                this.anchor);
        xx = xx - (float) anchorPoint.getX();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[28]++;
        yy = yy - (float) anchorPoint.getY();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[29]++;
        g2.drawImage(this.image, (int) xx, (int) yy, null);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[30]++;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[31]++;
        
        String toolTip = getToolTipText();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[32]++;
        String url = getURL();
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((toolTip != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[9]++;
            addEntity(info, new Rectangle2D.Float(xx, yy, w, h), rendererIndex, 
                    toolTip, url);
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[34]++;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[10]++;}
    }

    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[35]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[12]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        // now try to reject equality...
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[14]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof XYImageAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[16]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[38]++;
        XYImageAnnotation that = (XYImageAnnotation) obj;
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[18]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[40]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[20]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[41]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.image, that.image)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[22]++;}
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[42]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.anchor.equals(that.anchor)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.branches[24]++;}
        // seems to be the same...
        return true;
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return this.image.hashCode();
    }
    
    /**
     * Returns a clone of the annotation.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the annotation can't be cloned.
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
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[43]++;
        //SerialUtilities.writeImage(this.image, stream);
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
CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9.statements[44]++;
        //this.image = SerialUtilities.readImage(stream);
    }


}

class CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9 ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYImageAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$ehv7k27i2jf4d01wq7oe6d317wt32ibty9 () {
    super("org.jfree.chart.annotations.XYImageAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.XYImageAnnotation.java");
      for (int i = 1; i <= 44; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

